package org.misarch.notification.graphql

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import org.misarch.notification.graphql.dataloader.NotificationDataLoader
import org.misarch.notification.graphql.model.Notification
import org.misarch.notification.persistence.repository.NotificationRepository
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.CompletableFuture

/**
 * Defines GraphQL queries
 *
 * @property notificationRepository repository for notifications
 */
@Component
class Query(
    private val notificationRepository: NotificationRepository,
) : Query {

    @GraphQLDescription("Get a notification by id")
    fun notification(
        @GraphQLDescription("The id of the notification")
        id: UUID,
        dfe: DataFetchingEnvironment
    ): CompletableFuture<Notification> {
        return dfe.getDataLoader<UUID, Notification>(NotificationDataLoader::class.simpleName!!).load(id).thenApply {
            val authorizedUser = dfe.authorizedUser
            if (it.userId != authorizedUser.id) {
                authorizedUser.checkIsEmployee()
            }
            it
        }
    }

}