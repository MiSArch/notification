package org.misarch.notification.persistence.model

import org.misarch.notification.graphql.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table
class UserEntity(
    @Id
    override val id: UUID
) : BaseEntity<User> {
    override fun toDTO(): User {
        return User(
            id = id
        )
    }

}