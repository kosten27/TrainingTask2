package task

class Subscription {

    User user
    User followingUser

    static constraints = {
        user blank: false
        followingUser blank: false
        followingUser validator: { User u, Subscription s ->
            s.user != u
        }
    }

    static mapping = {
        table 'subscription'
        user column: 'user_id', type: 'bigint', joinTable: 'users'
        followingUser column: 'following_user', type: 'bigint', joinTable: 'users'
    }
}
