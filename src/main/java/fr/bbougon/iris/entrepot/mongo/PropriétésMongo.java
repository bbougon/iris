package fr.bbougon.iris.entrepot.mongo;

import com.google.common.collect.Lists;
import com.mongodb.ServerAddress;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropriétésMongo {

    public Settings ajouteLesPropriétés(Settings settings) {
        return settings
                .withDbName(getNomBaseDeDonnees())
                .withAddresses(Lists.newArrayList(getServerAddress()))
                .withDefaultUpdateStrategy(getDefaultStrategy())
                .withAuthentication(getUser(), getPassword());
    }

    String getNomBaseDeDonnees() {
        return bundle.getString("mongo.database");
    }

    ServerAddress getServerAddress() {
        try {
            return new ServerAddress(getHost(), Integer.parseInt(getPort()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getPort() {
        return bundle.getString("mongo.port");
    }

    private String getHost() {
        return bundle.getString("mongo.host");
    }

    UpdateStrategies getDefaultStrategy() {
        return UpdateStrategies.DIFF;
    }

    String getUser() {
        return bundle.getString("mongo.user");
    }

    String getPassword() {
        return bundle.getString("mongo.password");
    }

    private static final String CONFIGURATION = "configuration";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(CONFIGURATION, Locale.FRANCE);
}
