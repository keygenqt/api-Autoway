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

package com.keygenqt.autoway

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.keygenqt.autoway.common.base.toBlue
import com.keygenqt.autoway.common.base.toGreen
import com.keygenqt.autoway.common.base.toYellow
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {

    val parser = ArgParser("Autoway")

    val port by parser.option(
        ArgType.Int,
        fullName = "port",
        description = "Port localhost"
    ).default(9090)

    val domain by parser.option(
        ArgType.String,
        fullName = "domain",
        description = "Domain ip"
    ).default("localhost")

    val isDebug by parser.option(
        ArgType.Boolean,
        fullName = "debug",
        description = "Debug mode"
    ).default(false)

    parser.parse(args)

    val serverArg = mutableListOf(
        "-port=$port",
        "-host=$domain",
    )

    // logger
    if (!isDebug) {
        (LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger).apply {
            level = if (isDebug) Level.DEBUG else Level.OFF
        }
    }

    println(
        """| Welcome to ${"Aut".toGreen()}${"oway".toYellow()}!
           | The server is up and running.
           | 
           | You can get acquainted with the application by the link:
           | ${"http://$domain:$port".toBlue()}
           | 
           | Rest api is available here:
           | ${"http://$domain:$port".toBlue()}/gen/{table}"""
            .trimMargin("|")
    )

    embeddedServer(Netty, environment = commandLineEnvironment(*serverArg.toTypedArray())).start(wait = true)
}