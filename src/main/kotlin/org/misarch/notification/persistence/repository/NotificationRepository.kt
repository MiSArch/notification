package org.misarch.notification.persistence.repository

import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepository
import org.misarch.notification.persistence.model.NotificationEntity
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Repository for [NotificationEntity]s
 */
@Repository
interface NotificationRepository : QuerydslR2dbcRepository<NotificationEntity, UUID>