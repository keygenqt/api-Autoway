/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.keygenqt.autoway.common.config

import com.keygenqt.autoway.common.util.AppArgParser
import com.keygenqt.autoway.common.util.Mode
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.slf4j.LoggerFactory
import java.io.File
import javax.sql.DataSource

object DBConfig {

    private val log = LoggerFactory.getLogger(this::class.java)
    private const val nameDb = "autoway.db"

    fun connectAndMigrate() {
        if (AppArgParser.mode == Mode.COLD) {
            val file = File("${AppArgParser.migration}/$nameDb")
            if (file.isFile) {
                log.info("Remove database")
                file.delete()
            }
        }

        log.info("Initialising database")
        val dataSource = hikari()
        Database.connect(dataSource)
        runFlyway(dataSource)
    }

    private fun hikari(): DataSource {
        val config = HikariConfig().apply {
            driverClassName = "org.sqlite.JDBC"
            jdbcUrl = "jdbc:sqlite:${AppArgParser.migration}/$nameDb"
            validate()
        }
        return HikariDataSource(config)
    }

    private fun runFlyway(datasource: DataSource) {
        val flyway = Flyway.configure()
            .locations("filesystem:${AppArgParser.migration}")
            .dataSource(datasource)
            .load()
        try {
            flyway.info()
            flyway.migrate()
        } catch (e: Exception) {
            log.error("Exception running flyway migration", e)
            throw e
        }
        log.info("Flyway migration has finished")
    }

    suspend fun <T> dbQuery(block: suspend Transaction.() -> T): T? = newSuspendedTransaction {
        try {
            block(this)
        } catch (ex: ExposedSQLException) {
            log.debug(ex.message)
            null
        }
    }
}