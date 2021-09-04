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
 
package com.keygenqt.autoway.common.base

import com.keygenqt.autoway.common.config.DBConfig
import com.keygenqt.autoway.extensions.Type
import org.slf4j.LoggerFactory

open class BaseService {

    private val log = LoggerFactory.getLogger(this::class.java)

    suspend fun pk(table: String): String? = DBConfig.dbQuery {
        exec("SELECT name, pk FROM PRAGMA_TABLE_INFO('$table')") { rs ->
            while (rs.next()) {
                if (rs.getBoolean("pk")) {
                    return@exec rs.getString("name")
                }
            }
            return@exec ""
        }.let { if (it.isNullOrEmpty()) null else it }
    }

    suspend fun columns(table: String): Map<String, Type>? = DBConfig.dbQuery {
        val columns = mutableMapOf<String, Type>()
        exec("SELECT name, type, pk FROM PRAGMA_TABLE_INFO('$table')") { rs ->
            while (rs.next()) {
                val name = rs.getString("name")
                when (val type = rs.getString("type").substringBefore("(").uppercase()) {
                    Type.INTEGER.name -> columns[name] = Type.INTEGER
                    Type.VARCHAR.name -> columns[name] = Type.VARCHAR
                    Type.TEXT.name -> columns[name] = Type.TEXT
                    Type.REAL.name -> columns[name] = Type.REAL
                    Type.TIMESTAMP.name -> columns[name] = Type.TIMESTAMP
                    else -> log.error("Type not found: $type")
                }
            }
        }
        if (columns.isEmpty()) null else columns
    }
}