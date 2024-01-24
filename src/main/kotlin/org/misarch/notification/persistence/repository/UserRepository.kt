package org.misarch.notification.persistence.repository

import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepository
import org.misarch.notification.persistence.model.UserEntity
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Repository for [UserEntity]s
 */
@Repository
interface UserRepository : QuerydslR2dbcRepository<UserEntity, UUID>