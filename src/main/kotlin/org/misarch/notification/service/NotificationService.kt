package org.misarch.notification.service

import kotlinx.coroutines.reactor.awaitSingle
import org.misarch.notification.graphql.input.NotificationInput
import org.misarch.notification.graphql.input.UpdateNotificationInput
import org.misarch.notification.persistence.model.NotificationEntity
import org.misarch.notification.persistence.repository.NotificationRepository
import org.misarch.notification.persistence.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

/**
 * Service for [NotificationEntity]s
 *
 * @param repository the provided repository
 * @param userRepository the user repository
 */
@Service
class NotificationService(
    repository: NotificationRepository,
    private val userRepository: UserRepository
) : BaseService<NotificationEntity, NotificationRepository>(repository) {

    /**
     * Creates a notification
     *
     * @param notificationInput the notification to create
     * @return the created notification
     */
    suspend fun createNotification(notificationInput: NotificationInput): NotificationEntity {
        if (!userRepository.existsById(notificationInput.userId).awaitSingle()) {
            throw IllegalArgumentException("User with id ${notificationInput.userId} does not exist")
        }
        val notification = NotificationEntity(
            title = notificationInput.title,
            body = notificationInput.body,
            dateSent = OffsetDateTime.now(),
            dateRead = null,
            userId = notificationInput.userId,
            id = null
        )
        val savedNotification = repository.save(notification).awaitSingle()
        return savedNotification
    }

    /**
     * Updates a notification
     *
     * @param input defines which notification to update and how
     * @return the updated notification
     */
    suspend fun updateNotification(input: UpdateNotificationInput): NotificationEntity {
        val notification = repository.findById(input.id).awaitSingle()
        if ((notification.dateRead != null) != input.isRead) {
            notification.dateRead = if (input.isRead) {
                OffsetDateTime.now()
            } else {
                null
            }
        }
        val savedNotification = repository.save(notification).awaitSingle()
        return savedNotification
    }

}