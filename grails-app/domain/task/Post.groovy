package task

import grails.rest.Resource

@Resource(uri = '/post', formats = ['json', 'xml'])
class Post {

    String message
    User user

    static constraints = {
    }
}
