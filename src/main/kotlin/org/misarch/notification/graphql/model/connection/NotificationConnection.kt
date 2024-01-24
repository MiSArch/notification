package org.misarch.notification.graphql.model.connection

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.generator.federation.directives.ShareableDirective
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.ComparableExpression
import com.querydsl.sql.SQLQuery
import org.misarch.notification.graphql.model.Notification
import org.misarch.notification.graphql.model.connection.base.BaseConnection
import org.misarch.notification.graphql.model.connection.base.BaseOrder
import org.misarch.notification.graphql.model.connection.base.BaseOrderField
import org.misarch.notification.graphql.model.connection.base.OrderDirection
import org.misarch.notification.persistence.model.NotificationEntity
import org.misarch.notification.persistence.repository.NotificationRepository

/**
 * A GraphQL connection for [Notification]s.
 *
 * @param first The maximum number of items to return
 * @param skip The number of items to skip
 * @param predicate The predicate to filter the items by
 * @param order The order to sort the items by
 * @param repository The repository to fetch the items from
 * @param applyJoin A function to apply a join to the query
 */
@GraphQLDescription("A connection to a list of `Notification` values.")
@ShareableDirective
class NotificationConnection(
    first: Int?,
    skip: Int?,
    predicate: Predicate?,
    order: NotificationOrder?,
    repository: NotificationRepository,
    applyJoin: (query: SQLQuery<*>) -> SQLQuery<*> = { it }
) : BaseConnection<Notification, NotificationEntity>(
    first,
    skip,
    predicate,
    (order ?: NotificationOrder.DEFAULT).toOrderSpecifier(NotificationOrderField.ID),
    repository,
    NotificationEntity.ENTITY,
    applyJoin
) {

    override val primaryKey: ComparableExpression<*> get() = NotificationEntity.ENTITY.id
}

@GraphQLDescription("Notification order fields")
enum class NotificationOrderField(override vararg val expressions: Expression<out Comparable<*>>) : BaseOrderField {
    @GraphQLDescription("Order notifications by their id")
    ID(NotificationEntity.ENTITY.id),


}

@GraphQLDescription("Notification order")
class NotificationOrder(
    direction: OrderDirection?, field: NotificationOrderField?
) : BaseOrder<NotificationOrderField>(direction, field) {

    companion object {
        val DEFAULT = NotificationOrder(OrderDirection.ASC, NotificationOrderField.ID)
    }
}