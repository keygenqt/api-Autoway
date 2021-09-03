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
 
package com.keygenqt.autoway.common.util

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required

enum class Mode {
    COLD,
    HOT,
}

object AppArgParser {

    private val argParser = ArgParser("Autoway")

    val migration by argParser.option(
        ArgType.String,
        fullName = "migration",
        description = "Path to directory with sql migrations (for flyway)"
    ).required()

    val port by argParser.option(
        ArgType.Int,
        fullName = "port",
        description = "Port localhost"
    ).default(9090)

    val domain by argParser.option(
        ArgType.String,
        fullName = "domain",
        description = "Domain ip"
    ).default("localhost")

    val isDebug by argParser.option(
        ArgType.Boolean,
        fullName = "debug",
        description = "Debug mode"
    ).default(false)

    val mode by argParser.option(
        ArgType.Choice<Mode>(),
        fullName = "mode",
        description = "Start mode"
    ).default(Mode.HOT)

    fun parse(args: Array<String>) {
        argParser.parse(args)
    }
}