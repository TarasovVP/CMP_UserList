package com.tarasovvp.cmpuserlist.data.database.datasource

import app.cash.sqldelight.async.coroutines.awaitAsList
import com.tarasovvp.cmpuserlist.data.database.SharedDatabase
import com.tarasovvp.cmpuserlist.data.mapper.toDomain
import com.tarasovvp.cmpuserlist.data.mapper.toEntity
import com.tarasovvp.cmpuserlist.domain.model.User

class LocalUserDataSourceImpl(private val db: SharedDatabase) : LocalUserDataSource {
    override suspend fun getUsers(): List<User> = db { database ->
        database.userDatabaseQueries
            .selectAll()
            .awaitAsList()
            .map {
                it.toDomain()
            }
    }

    override suspend fun replaceAll(users: List<User>) = db { database ->
        val q = database.userDatabaseQueries
        q.transaction {
            q.deleteAll()
            users.forEach { user ->
                val entity = user.toEntity()
                q.insertUser(
                    email = entity.email,
                    firstName = entity.firstName,
                    lastName = entity.lastName,
                    maidenName = entity.maidenName,
                    age = entity.age,
                    gender = entity.gender,
                    phone = entity.phone,
                    birthDate = entity.birthDate,
                    image = entity.image
                )
            }
        }
    }
}