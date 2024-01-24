package org.misarch.notification.event.model

import java.util.UUID

/**
 * Notification DTO used for events
 *
 * @property title title of the notification
 * @property body body of the notification
 * @property userId id of the user the notification was sent to
 */
data class NotificationDTO(
    val title: String,
    val body: String,
    val userId: UUID,
)