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

import com.keygenqt.autoway.api.routing.apiRoute
import com.keygenqt.autoway.common.di.moduleServicesDI
import com.keygenqt.autoway.common.util.JsonMapper
import com.keygenqt.autoway.frontend.routing.frontendRoute
import com.keygenqt.autoway.frontend.views.site.error404
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.koin.core.context.startKoin


fun Application.module() {

    // di
    startKoin {
        printLogger()
        modules(
            moduleServicesDI,
        )
    }

    // serialization
    install(ContentNegotiation) {
        json(JsonMapper.defaultMapper)
    }

    // db
    DBConfig.connectAndMigrate()

    // init ktor
    install(DefaultHeaders)
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respondHtml(status = HttpStatusCode.NotFound) { error404() }
        }
    }

    install(Routing) {
        apiRoute()
        frontendRoute()
    }

}