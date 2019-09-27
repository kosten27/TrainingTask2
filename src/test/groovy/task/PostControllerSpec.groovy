package task

import TrainingTask2.PostService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PostController)
@Mock(PostService)
class PostControllerSpec extends Specification {

    def setup() {
        controller.postService = Stub(PostService)
    }

    def cleanup() {
    }

    void "test something"() {
        when:
        controller.index()

        then:
        response.status == HttpServletResponse.SC_OK
    }
}
