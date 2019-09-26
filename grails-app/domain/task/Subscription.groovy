package task

class Subscription {

    User user
    User followingUser

    static constraints = {
        user blank: false
        user followingUser: false
    }
}
