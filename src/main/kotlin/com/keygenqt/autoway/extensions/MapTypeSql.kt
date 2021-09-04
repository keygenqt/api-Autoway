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

package com.keygenqt.autoway.extensions

import com.google.gson.JsonObject
import io.ktor.http.*
import java.sql.ResultSet

enum class Type {
    INTEGER,
    VARCHAR,
    TEXT,
    REAL,
    TIMESTAMP
}

fun Map<String, Type>.toLike(text: String?, operator: String = "OR", where: Boolean = true): String = text?.let {
    val query = this.map { "${it.key} LIKE '%$text%'" }.joinToString(" $operator ")
    if (query.isNotEmpty()) {
        "${if (where) " where " else ""} $query"
    } else {
        ""
    }
} ?: ""

fun Map<String, Type>.toJsonObject(rs: ResultSet): JsonObject {
    return JsonObject().apply {
        forEach {
            when (it.value) {
                Type.INTEGER -> addProperty(it.key, rs.getInt(it.key))
                Type.VARCHAR, Type.TEXT -> addProperty(it.key, rs.getString(it.key))
                Type.REAL -> addProperty(it.key, rs.getDouble(it.key))
                Type.TIMESTAMP -> addProperty(it.key, rs.getTimestamp(it.key).time / 1000)
            }
        }
    }
}

fun Map<String, Type>.toKeysList(p: Parameters): String? = map {
    if (p.contains(it.key)) {
        it.key
    } else {
        null
    }
}.filterNotNull().let {
    if (it.isEmpty()) {
        null
    } else {
        it.joinToString(", ")
    }
}

fun Map<String, Type>.toValuesList(p: Parameters): String? = map {
    if (p.contains(it.key)) {
        "'${p[it.key].toString()}'"
    } else {
        null
    }
}.filterNotNull().let {
    if (it.isEmpty()) {
        null
    } else {
        it.joinToString(", ")
    }
}