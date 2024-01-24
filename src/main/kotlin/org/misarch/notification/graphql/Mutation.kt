package org.misarch.notification.graphql

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Mutation
import org.misarch.notification.graphql.input.CreateNotificationInput
import org.misarch.notification.graphql.input.UpdateNotificationInput
import org.misarch.notification.graphql.model.Notification
import org.misarch.notification.service.NotificationService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * Defines GraphQL mutations
 *
 * @property notificationService service used to create and update notifications
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
class Mutation(
    private val notificationService: NotificationService
) : Mutation {

    @GraphQLDescription("Create a new notification")
    suspend fun createNotification(
        @GraphQLDescription("Input for the createNotification mutation")
        input: CreateNotificationInput
    ): Notification {
        val notification = notificationService.createNotification(input)
        return notification.toDTO()
    }

    @GraphQLDescription("Update a notification")
    suspend fun updateNotification(
        @GraphQLDescription("Input for the updateNotification mutation")
        input: UpdateNotificationInput
    ): Notification {
        val notification = notificationService.updateNotification(input)
        return notification.toDTO()
    }
}