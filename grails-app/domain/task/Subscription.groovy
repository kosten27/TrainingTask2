package task

class Subscription {

    User user
    User followingUser

    static constraints = {
        user blank: false
        followingUser blank: false
        followingUser validator: { User u, Subscription s -> s.user != u }
    }
}
