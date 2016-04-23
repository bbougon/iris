package fr.bbougon.iris.entrepot;

import java.util.List;

public interface Entrepot<T> {

    List<T> tous();

    void persiste(T entity);

    T parId(String identifiant);
}
