package demo

import com.google.inject.AbstractModule
import com.google.inject.Provides
import org.grails.orm.hibernate.HibernateDatastore

class GormModule extends AbstractModule {
    @Override
    protected void configure() {}

    @Provides
    static HibernateDatastore hibernateDatastore() {
        Map configuration = [
                'hibernate.hbm2ddl.auto'    : 'update',
                'dataSource.driverClassName': 'org.postgresql.Driver',
                'dataSource.url'            : 'jdbc:postgresql://localhost:5432/finance_db',
                'dataSource.username'       : 'henninb',
                'dataSource.password'       : 'monday1',
                'dataSource.dialect'        : 'org.hibernate.dialect.PostgreSQLDialect'
        ]

        HibernateDatastore datastore = new HibernateDatastore(configuration, Person)
        datastore
    }
}
