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

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.keygenqt.autoway.common.base.BaseService
import com.keygenqt.autoway.common.base.ResponseError
import com.keygenqt.autoway.common.config.DBConfig.dbQuery
import com.keygenqt.autoway.extensions.*
import io.ktor.http.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction


class CommonService : BaseService() {

    suspend fun find(table: String, limit: Int, offset: Int, search: String?): JsonArray? = dbQuery {
        columns(table)?.let { columns ->
            exec("SELECT * FROM $table ${columns.toLike(search)} LIMIT $limit OFFSET $offset") { rs ->
                val array = JsonArray()
                while (rs.next()) {
                    array.add(columns.toJsonObject(rs))
                }
                return@exec array
            }
        }
    }

    suspend fun getModel(table: String, id: String): JsonObject? = dbQuery {
        columns(table)?.let { columns ->
            pk(table)?.let { pk ->
                exec("SELECT * FROM $table WHERE $pk='$id'") { rs ->
                    while (rs.next()) {
                        return@exec columns.toJsonObject(rs)
                    }
                    JsonObject()
                }
            }
        }?.let { if (it.size() == 0) null else it }
    }

    suspend fun delete(table: String, id: String): Boolean? = dbQuery {
        pk(table)?.let { pk ->
            var result: Boolean? = null
            exec("SELECT * FROM $table WHERE $pk='$id'") { rs ->
                while (rs.next()) {
                    exec("DELETE FROM $table WHERE $pk='$id'")
                    result = true
                    break
                }
            }
            result
        }
    }

    suspend fun create(table: String, parameters: Parameters): Any? = newSuspendedTransaction {
        columns(table)?.let { columns ->
            columns.toKeysList(parameters)?.let { keys ->
                val values = columns.toValuesList(parameters)!!
                try {
                    var id = ""
                    exec("insert into $table ($keys) values ($values)")
                    exec("select last_insert_rowid()") { rs ->
                        id = rs.getString("last_insert_rowid()")
                    }
                    commit()
                    getModel(table, id)
                } catch (ex: Exception) {
                    ResponseError(ex.message ?: "Error model params")
                }
            }
        }
    }

    suspend fun update(table: String, id: String, parameters: Parameters): Any? = newSuspendedTransaction {
        columns(table)?.let { columns ->
            pk(table)?.let { pk ->
                columns.toSetList(parameters)?.let { set ->
                    try {
                        exec("update $table SET $set WHERE $pk=$id")
                        commit()
                        getModel(table, id)
                    } catch (ex: Exception) {
                        ResponseError(ex.message ?: "Error model params")
                    }
                }
            }
        }
    }
}