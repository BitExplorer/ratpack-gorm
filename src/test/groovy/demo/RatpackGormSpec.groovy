package demo

import groovy.json.JsonSlurper
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class RatpackGormSpec extends Specification {

    @AutoCleanup
    @Shared
    GroovyRatpackMainApplicationUnderTest test = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient client = test.httpClient

    void "Get data from database"() {
        when:
        get()

        then:
        response.statusCode == 200

        when:
        List<Person> json = (ArrayList<Person>) new JsonSlurper().parseText(response.body.text)
        println json.getClass()
        json.forEach { println it }

        then:
        json.size() == 3
        json*.firstName == ['Matthew', 'brian', 'Kathryn']
        json*.lastName == ['Henning', 'henning', 'Henning']
    }
}
