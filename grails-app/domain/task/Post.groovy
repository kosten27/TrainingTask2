package task

import org.grails.datastore.gorm.GormEntity

//@Resource(uri = '/post', formats = ['json', 'xml'])
class Post implements GormEntity<Post> {

    String message
    User user

    static constraints = {
    }
}
