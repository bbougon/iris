package fr.bbougon.iris;

import fr.bbougon.iris.entrepot.mongo.PropriétésMongo;
import org.mongolink.Settings;

import java.util.Locale;
import java.util.ResourceBundle;

public class Configuration {

    private static final String CONFIGURATION = "configuration";
    private static final ResourceBundle configuration = ResourceBundle.getBundle(CONFIGURATION, Locale.FRANCE);

    public static Settings configurationMongo() {
        ConfigurationMongo configuration = new ConfigurationMongo(Configuration.configuration);
        return new PropriétésMongo(configuration.dataBase, configuration.port, configuration.host, configuration.user, configuration.password).ajouteLesPropriétés(Settings.defaultInstance());
    }

    public static ConfigurationServeur configurationServeur() {
        return new ConfigurationServeur(Configuration.configuration);
    }

    private static class ConfigurationMongo {
        public ConfigurationMongo(ResourceBundle configuration) {
            dataBase = configuration.getString("mongo.database");
            port = configuration.getString("mongo.port");
            host =  configuration.getString("mongo.host");
            user = configuration.getString("mongo.user");
            password = configuration.getString("mongo.password");
        }

        private final String dataBase;
        private final String port;
        private final String host;
        private final String user;
        private final String password;
    }

    public static class ConfigurationServeur {
        public ConfigurationServeur(ResourceBundle configuration) {
            descriptor = configuration.getString("serveur.descriptor");
        }

        public String getDescriptor() {
            return descriptor;
        }

        private final String descriptor;
    }
}
