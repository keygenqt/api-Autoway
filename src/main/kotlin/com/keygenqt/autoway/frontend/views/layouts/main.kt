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
 
package com.keygenqt.autoway.frontend.views.layouts

import kotlinx.html.*

fun HTML.mainLayout(body: DIV.() -> Unit) {
    head {
        title { +"Autoway: Backend autogeneration on migrations" }
        styleLink("/css/main.css")
        styleLink("/css/media.css")
        script(src = "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js") {}
        script(src = "/js/main.js") {}
        link(rel="icon", href="/images/common/favicon.png") {
            attributes["sizes"]="57x57"
        }
    }
    body {
        div(classes = "container-table") {
            div(classes = "container-row") {
                div(classes = "container-cell cell-header") {
                    div(classes = "container-body") {
                        div(classes = "menu") {
                            div(classes = "menu-logo") {
                                a(href = "/") {
                                    span {
                                        +"Auto"
                                    }
                                    +"way"
                                }
                            }
                            ul(classes = "menu-links") {
                                li(classes = "active") {
                                    a(href = "/") {
                                        +"Home"
                                    }
                                }
                            }
                        }
                        div(classes = "index-header-body") {
                            div(classes = "index-header-text") {
                                +"Backend autogeneration on migrations"
                            }
                            a(
                                classes = "btn btn-green",
                                target = "_blank",
                                href = "https://github.com/keygenqt/api-Autoway"
                            ) {
                                +"Visit GitHub >"
                            }
                        }
                    }
                }
            }
            div(classes = "container-row") {
                div(classes = "container-cell cell-body") {
                    div(classes = "container-body about-page-container") {
                        body()
                    }
                }
            }
            div(classes = "container-row") {
                div(classes = "container-cell cell-footer") {
                    div(classes = "container-body") {
                        div(classes = "index-footer-body") {
                            div(classes = "index-footer-text") {
                                +"If you have any questions, please contact. I will always be happy to help, if possible."
                                div(classes = "links") {
                                    a(classes = "links", href = "mailto:dev@keygenqt.com") {
                                        +"dev@keygenqt.com"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}