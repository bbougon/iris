package fr.bbougon.iris.entrepot.mongo;

import fr.bbougon.iris.entrepot.Entrepot;
import org.mongolink.MongoSession;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

public class EntrepotMongo<T> implements Entrepot<T> {

    public EntrepotMongo(MongoSession session) {
        this.session = session;
    }

    public void persiste(T entity) {
        session.save(entity);
    }

    @Override
    public T parId(String identifiant) {
        return session.get(UUID.fromString(identifiant), persistentType());
    }

    @Override
    public void supprime(T entity) {
        session.delete(entity);
    }

    public List<T> tous() {
        return session.getAll(persistentType());
    }

    protected final Class<T> persistentType() {
        final ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) superclass.getActualTypeArguments()[0];
    }

    protected final MongoSession session;
}
