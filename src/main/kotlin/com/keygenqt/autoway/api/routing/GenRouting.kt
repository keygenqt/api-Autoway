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
 
package com.keygenqt.autoway.api.routing

import com.keygenqt.autoway.common.service.CommonService
import com.keygenqt.autoway.extensions.toPrettyString
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.genRoute() {

    val service: CommonService by inject()

    route("/{table}") {
        get {

            val search = call.parameters["search"]
            val limit = call.parameters["limit"]?.toIntOrNull() ?: -1
            val offset = call.parameters["offset"]?.toIntOrNull() ?: 0

            call.parameters["table"]
                ?.let { service.find(it, limit, offset, search) }
                ?.let { call.respondText(it.toPrettyString()) }
                ?: call.respond(HttpStatusCode.NotFound)
        }
        get("/{id}") {
            call.parameters["table"]
                ?.let { service.getModel(it, call.parameters["id"]!!) }
                ?.let { call.respondText(it.toPrettyString()) }
                ?: call.respond(HttpStatusCode.NotFound)
        }
    }
}