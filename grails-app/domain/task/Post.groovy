package task

import org.grails.datastore.gorm.GormEntity

class Post implements GormEntity<Post> {

    String message
    User user

    static constraints = {
    }
}
