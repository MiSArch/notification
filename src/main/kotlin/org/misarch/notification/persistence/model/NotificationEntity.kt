package org.misarch.notification.persistence.model

import org.misarch.notification.graphql.model.Notification
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime
import java.util.*

/**
 * Entity for notifications
 *
 * @property title title of the notification
 * @property body body of the notification
 * @property dateSent date the notification was sent
 * @property dateRead date the notification was read
 * @property id unique identifier of the notification
 */
@Table
class NotificationEntity(
    val title: String,
    val body: String,
    val dateSent: OffsetDateTime,
    var dateRead: OffsetDateTime?,
    val userId: UUID,
    @Id
    override val id: UUID?
) : BaseEntity<Notification> {

    companion object {
        /**
         * Querydsl entity
         */
        val ENTITY = QNotificationEntity.notificationEntity!!
    }

    override fun toDTO(): Notification {
        return Notification(
            id = id!!,
            title = title,
            body = body,
            dateSent = dateSent,
            dateRead = dateRead,
            userId = userId
        )
    }

}