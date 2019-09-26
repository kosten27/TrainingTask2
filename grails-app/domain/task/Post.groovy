package task

import org.grails.datastore.gorm.GormEntity

class Post implements GormEntity<Post> {

    User user
    Date date
    String message

    static constraints = {
        user blank: false
        date blank: false
        message blank: false
    }
}
