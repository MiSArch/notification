package org.misarch.notification.graphql.model

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.federation.directives.FieldSet
import com.expediagroup.graphql.generator.federation.directives.KeyDirective
import graphql.schema.DataFetchingEnvironment
import org.misarch.notification.graphql.authorizedUser
import org.misarch.notification.graphql.authorizedUserOrNull
import org.misarch.notification.graphql.model.connection.NotificationConnection
import org.misarch.notification.graphql.model.connection.NotificationOrder
import org.misarch.notification.persistence.model.NotificationEntity
import org.misarch.notification.persistence.repository.NotificationRepository
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@GraphQLDescription("A user.")
@KeyDirective(fields = FieldSet("id"))
class User(
    id: UUID
) : Node(id) {

    @GraphQLDescription("Get all notifications the user received")
    fun notifications(
        @GraphQLDescription("Number of items to return")
        first: Int? = null,
        @GraphQLDescription("Number of items to skip")
        skip: Int? = null,
        @GraphQLDescription("Ordering")
        orderBy: NotificationOrder? = null,
        @GraphQLIgnore
        @Autowired
        productVariantRepository: NotificationRepository,
        dfe: DataFetchingEnvironment
    ): NotificationConnection {
        if (dfe.authorizedUser.id != id) {
            dfe.authorizedUser.checkIsEmployee()
        }
        return NotificationConnection(
            first, skip, NotificationEntity.ENTITY.userId.eq(id), orderBy, productVariantRepository, dfe.authorizedUserOrNull
        )
    }

}