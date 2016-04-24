package fr.bbougon.iris;

import com.google.common.collect.Lists;
import com.mongodb.ServerAddress;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;

import java.util.Locale;
import java.util.ResourceBundle;

public class Propriétés {

    public Settings ajouteLesPropriétés(Settings settings) {
        return settings
                .withDbName(getNomBaseDeDonnees())
                .withAddresses(Lists.newArrayList(getServerAddress()))
                .withDefaultUpdateStrategy(getDefaultStrategy())
                .withAuthentication(getUser(), getPassword());
    }

    String getNomBaseDeDonnees() {
        return ResourceBundle.getBundle(CONFIGURATION, Locale.FRANCE).getString("database");
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
        return ResourceBundle.getBundle(CONFIGURATION).getString("port");
    }

    private String getHost() {
        return ResourceBundle.getBundle(CONFIGURATION).getString("host");
    }

    UpdateStrategies getDefaultStrategy() {
        return UpdateStrategies.DIFF;
    }

    String getUser() {
        return ResourceBundle.getBundle(CONFIGURATION).getString("user");
    }

    String getPassword() {
        return ResourceBundle.getBundle(CONFIGURATION).getString("password");
    }

    private static final String CONFIGURATION = "configuration";
}
