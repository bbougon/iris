package fr.bbougon.iris.domaine;

public class TéléphoneTestBuilder {
    public TéléphoneTestBuilder avecNuméro(String numéro) {
        this.numéro = numéro;
        return this;
    }

    public TéléphoneTestBuilder avecType(TypeTelephone typeTelephone) {
        this.typeTelephone = typeTelephone;
        return this;
    }

    public Telephone build() {
        return Telephone.créer(numéro, TypeTelephone.PERSONNEL);
    }

    private String numéro;
    private TypeTelephone typeTelephone;
}
