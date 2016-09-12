package fr.bbougon.iris.domaine;

import java.util.Objects;

public class Telephone {

    @SuppressWarnings("Pour MongoLink")
    Telephone() {
    }

    private Telephone(String numero, TypeTelephone typeTelephone) {
        this.numero = numero;
        this.type = typeTelephone;
    }

    public String getNumero() {
        return numero;
    }

    public TypeTelephone getType() {
        return type;
    }

    public static Telephone créer(String numero, TypeTelephone typeTelephone) {
        return new Telephone(numero, typeTelephone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone = (Telephone) o;
        return Objects.equals(numero, telephone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, type);
    }

    private String numero;
    private TypeTelephone type;
}
