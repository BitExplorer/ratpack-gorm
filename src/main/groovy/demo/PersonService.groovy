package demo

import org.grails.orm.hibernate.HibernateDatastore
import ratpack.exec.Blocking
import ratpack.service.Service
import ratpack.service.StartEvent

class PersonService implements Service {
    void onStart(StartEvent e) throws Exception {
        println 'Person Service onStart'
        e.getRegistry().get(HibernateDatastore)
        Blocking.exec {
            Person.withNewSession {
                if( Person.findByFirstName('Kathryn') == null ) {
                    new Person(firstName: 'Kathryn', lastName: 'Henning', middleName: 'x').save()
                    println 'completed added Kathryn - with save()'
                }

                if( Person.findByFirstName('Matthew') == null ) {
                    new Person(firstName: 'Matthew', lastName: 'Henning', middleName: 'x').save()
                    println 'completed added Matthew - with save()'
                }

                if( Person.findByFirstName('Brian') == null ) {
                    new Person(firstName: 'Brian', lastName: 'Henning', middleName: 'x').save()
                    println 'completed added Brian - with save()'
                }
            }
        }
        println 'completed Person Service onStart'
    }
}
