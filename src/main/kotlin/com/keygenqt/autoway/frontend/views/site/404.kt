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
 
package com.keygenqt.autoway.frontend.views.site

import com.keygenqt.autoway.frontend.views.layouts.mainLayout
import kotlinx.html.*

fun HTML.error404() {
    mainLayout {
        div(classes = "container-body error-page") {
            div(classes = "error-page-cell") {
                div(classes = "error mx-auto") {
                    +"404"
                }
                p(classes = "lead text-gray-800 mb-5") {
                    +"Page Not Found"
                }
                p(classes = "text-gray-500") {
                    +"It looks like you found a glitch in the matrix..."
                }
                a(href = "/") {
                    style = "padding-bottom: 5px"
                    span { +"←" }
                }
            }
        }
    }
}