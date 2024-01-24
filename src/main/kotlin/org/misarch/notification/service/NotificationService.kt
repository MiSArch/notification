package org.misarch.notification.service

import kotlinx.coroutines.reactor.awaitSingle
import org.misarch.notification.event.model.NotificationDTO
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
     * @param notificationDTO the notification to create
     * @return the created notification
     */
    suspend fun createNotification(notificationDTO: NotificationDTO): NotificationEntity {
        if (!userRepository.existsById(notificationDTO.userId).awaitSingle()) {
            throw IllegalArgumentException("User with id ${notificationDTO.userId} does not exist")
        }
        val notification = NotificationEntity(
            title = notificationDTO.title,
            body = notificationDTO.body,
            dateSent = OffsetDateTime.now(),
            dateRead = null,
            userId = notificationDTO.userId,
            id = null
        )
        val savedNotification = repository.save(notification).awaitSingle()
        return savedNotification
    }

}