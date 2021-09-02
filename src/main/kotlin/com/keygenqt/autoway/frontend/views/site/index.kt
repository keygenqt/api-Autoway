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


fun HTML.index() {
    mainLayout {
        unsafe {
            raw("<style>.index-header-body {  display: inline-block; }</style>")
        }
        div(classes = "about-page") {
            div(classes = "round-top")
            div(classes = "about-page-data") {
                div(classes = "row") {
                    div(classes = "cell") {
                        div(classes = "phone") {
                            img(src = "images/site/phone.png")
                        }
                    }
                    div(classes = "cell") {
                        div(classes = "title") {
                            span {
                                +"Auto"
                            }
                            +"way"
                        }
                        div(classes = "text") {
                            h3 {
                                +"Platform Architecture"
                            }
                        }
                    }
                }
            }
            div(classes = "round-bottom")
        }
    }
}