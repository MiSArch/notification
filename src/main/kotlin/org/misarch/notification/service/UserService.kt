package org.misarch.notification.service

import kotlinx.coroutines.reactor.awaitSingle
import org.misarch.notification.event.model.UserDTO
import org.misarch.notification.persistence.model.UserEntity
import org.misarch.notification.persistence.repository.UserRepository
import org.springframework.stereotype.Service

/**
 * Service for [UserEntity]s
 *
 * @param repository the repository for [UserEntity]s
 */
@Service
class UserService(
    repository: UserRepository
) : BaseService<UserEntity, UserRepository>(repository) {

    /**
     * Registers a user
     *
     * @param userDTO the user to register
     */
    suspend fun registerUser(userDTO: UserDTO) {
        repository.createUser(userDTO.id)
    }

}