package de.mxzx.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@RequiredArgsConstructor
@Getter
public class HibernateController {

    private SessionFactory sessionFactory;

    public void setUp() {
        final SessionFactory sessionFactory;
        // A SessionFactory is set up once for an application!

        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            sessionFactory =
                    new MetadataSources(registry)
                            .buildMetadata()
                            .buildSessionFactory();
            this.sessionFactory = sessionFactory;
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
