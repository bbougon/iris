package fr.bbougon.iris.entrepot.memoire;

import com.google.common.collect.Lists;
import fr.bbougon.iris.entrepot.Entrepot;

import java.util.List;

public abstract class EntrepotMemoire<T> implements Entrepot<T> {

    @Override
    public void persiste(T entity) {
        liste.add(entity);
    }

    @Override
    public List<T> tous() {
        return liste;
    }
    List<T> liste = Lists.newArrayList();
}
