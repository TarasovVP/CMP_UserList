package com.tarasovvp.cmpuserlist.data.mapper

import com.tarasovvp.cmpuserlist.data.network.model.RemoteUser
import kotlin.test.Test
import kotlin.test.assertEquals

class UserMapperTest {
    @Test
    fun `remote user maps every field to the shared domain model`() {
        val remote = RemoteUser(
            firstName = "Ada",
            lastName = "Lovelace",
            maidenName = "Byron",
            age = 36,
            gender = "female",
            email = "ada@example.com",
            phone = "+44 0000",
            birthDate = "1815-12-10",
            image = "https://example.com/ada.png",
        )

        val user = remote.toDomain()

        assertEquals(remote.firstName, user.firstName)
        assertEquals(remote.lastName, user.lastName)
        assertEquals(remote.maidenName, user.maidenName)
        assertEquals(remote.age, user.age)
        assertEquals(remote.gender, user.gender)
        assertEquals(remote.email, user.email)
        assertEquals(remote.phone, user.phone)
        assertEquals(remote.birthDate, user.birthDate)
        assertEquals(remote.image, user.image)
    }
}
