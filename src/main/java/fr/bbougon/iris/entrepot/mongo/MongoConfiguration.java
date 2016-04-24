package fr.bbougon.iris.entrepot.mongo;

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
        try {
            ContextBuilder builder = new ContextBuilder("fr.bbougon.iris.entrepot.mongo.mapping");
            sessionManager = MongoSessionManager.create(builder, new PropriétésMongo().ajouteLesPropriétés(Settings.defaultInstance()));
            session = sessionManager.createSession();
            session.start();
            return session;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static MongoSessionManager sessionManager;
    static MongoSession session;
}