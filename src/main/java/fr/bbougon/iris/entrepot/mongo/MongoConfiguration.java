package fr.bbougon.iris.entrepot.mongo;

import fr.bbougon.iris.Configuration;
import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.domain.mapper.ContextBuilder;

public class MongoConfiguration {

    public static MongoSession createSession() {
        session = IrisSession.INSTANCE.mongoSessionManager.createSession();
        return session;
    }

    public static void stop() {
        IrisSession.INSTANCE.mongoSessionManager.close();
    }

    public static void flushAndStopSession() {
        session.flush();
        session.stop();
    }

    private static MongoSession session;

    private enum IrisSession {
        INSTANCE;

        IrisSession() {
            ContextBuilder builder = new ContextBuilder("fr.bbougon.iris.entrepot.mongo.mapping");
            mongoSessionManager = MongoSessionManager.create(builder, Configuration.configurationMongo());
        }

        public MongoSessionManager mongoSessionManager;
    }
}