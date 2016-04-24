package fr.bbougon.iris.rules;

import fr.bbougon.iris.entrepot.Entrepots;
import fr.bbougon.iris.entrepot.mongo.EntrepotsMongos;
import org.junit.rules.ExternalResource;
import org.mongolink.test.MongolinkRule;

public class AvecEntrepotsMongo extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        mongolinkRule = MongolinkRule.withPackage("fr.bbougon.iris.entrepot.mongo.mapping");
        mongolinkRule.before();
        Entrepots.initialise(new EntrepotsMongos(mongolinkRule.getCurrentSession()));
    }

    @Override
    protected void after() {
        mongolinkRule.after();
    }

    public void cleanSession() {
        mongolinkRule.cleanSession();
    }

    private MongolinkRule mongolinkRule;
}
