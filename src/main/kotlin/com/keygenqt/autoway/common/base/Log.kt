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