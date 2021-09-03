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

private const val RESET = "\u001B[0m"
private const val RED = "\u001B[31m"
private const val GREEN = "\u001B[32m"
private const val YELLOW = "\u001B[33m"
private const val BLUE = "\u001B[34m"
private const val PURPLE = "\u001B[35m"
private const val CYAN = "\u001B[36m"
private const val WHITE = "\u001B[37m"
private const val UNDERLINE = "\\033[1m"

fun String.toGreen() = "$GREEN$this$RESET"

fun String.toRed() = "$RED$this$RESET"

fun String.toYellow() = "$YELLOW$this$RESET"

fun String.toBlue() = "$BLUE$this$RESET"

fun String.toPurple() = "$PURPLE$this$RESET"

fun String.toCyan() = "$CYAN$this$RESET"

fun String.toWhite() = "$WHITE$this$RESET"

fun String.underline() = "$UNDERLINE$this$RESET"