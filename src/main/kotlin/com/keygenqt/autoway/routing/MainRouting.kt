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

package com.keygenqt.autoway.routing

import com.keygenqt.autoway.common.base.ResponseSuccess
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.apiRoute() {
    route("/") {
        get {
            call.respond(
                status = HttpStatusCode.NotFound,
                message = ResponseSuccess("Welcome to Autoway! Rest api is available here: http://${call.request.host()}:${call.request.port()}/gen/{table}")
            )
        }
    }
    route("/gen") {
        genRoute()
    }
}