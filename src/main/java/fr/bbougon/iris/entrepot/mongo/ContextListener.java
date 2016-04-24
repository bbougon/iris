package fr.bbougon.iris.entrepot.mongo;

import fr.bbougon.iris.entrepot.Entrepots;
import org.mongolink.MongoSession;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        MongoSession session = MongoConfiguration.startSession();
        Entrepots.initialise(new EntrepotsMongos(session));

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        MongoConfiguration.stopSession();

    }
}
