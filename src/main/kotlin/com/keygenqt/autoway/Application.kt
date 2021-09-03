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
import com.keygenqt.autoway.common.util.AppArgParser
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {

    AppArgParser.parse(args)

    val serverArg = mutableListOf(
        "-port=${AppArgParser.port}",
        "-host=${AppArgParser.domain}",
    )

    // logger
    (LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger).apply {
        level = if (AppArgParser.isDebug) Level.DEBUG else Level.OFF
    }

    println(
        """| Welcome to ${"Aut".toGreen()}${"oway".toYellow()}!
           | The server is up and running.
           | 
           | You can get acquainted with the application by the link:
           | ${"http://${AppArgParser.domain}:${AppArgParser.port}".toBlue()}
           | 
           | Rest api is available here:
           | ${"http://${AppArgParser.domain}:${AppArgParser.port}".toBlue()}/gen/{table}"""
            .trimMargin("|")
    )

    embeddedServer(Netty, environment = commandLineEnvironment(*serverArg.toTypedArray())).start(wait = true)
}