package fr.bbougon.iris.entrepot.mongo;

import fr.bbougon.iris.Propriétés;
import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.mapper.ContextBuilder;

public class MongoConfiguration {

    public static void stopSession() {
        session.stop();
        sessionManager.close();
    }

    public static MongoSession startSession() {
        ContextBuilder builder = new ContextBuilder("fr.bbougon.iris.entrepot.mongo.mapping");
        sessionManager = MongoSessionManager.create(builder, new Propriétés().ajouteLesPropriétés(Settings.defaultInstance()));
        session = sessionManager.createSession();
        session.start();
        return session;
    }

    static MongoSessionManager sessionManager;
    static MongoSession session;
}