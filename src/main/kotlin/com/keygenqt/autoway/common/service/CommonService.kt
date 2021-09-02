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
 
package com.keygenqt.autoway.common.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.json.simple.JSONArray
import org.json.simple.JSONObject

class CommonService {

    suspend fun getAll(table: String, limit: Int, offset: Int): String = newSuspendedTransaction {
        val queryColumns = "SELECT name, type FROM PRAGMA_TABLE_INFO('$table')"
        val query = "SELECT * FROM $table LIMIT $limit OFFSET $offset"
        val columns = mutableMapOf<String, String>()
        val result = JSONArray()

        exec(queryColumns) { rs ->
            while (rs.next()) {
                columns[rs.getString("name")] = rs.getString("type").substringBefore("(").uppercase()
            }
        }

        exec(query) { rs ->
            while (rs.next()) {
                val obj = JSONObject()
                columns.forEach {
                    when (it.value) {
                        "INTEGER" -> obj[it.key] = rs.getInt(it.key)
                        "VARCHAR" -> obj[it.key] = rs.getString(it.key)
                    }
                }
                result.add(obj)
            }
        }

        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        val jsonObjectAlt: JsonArray = JsonParser.parseString(result.toString()).asJsonArray
        gson.toJson(jsonObjectAlt)
    }
}