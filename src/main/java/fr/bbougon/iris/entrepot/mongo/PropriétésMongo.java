package fr.bbougon.iris.entrepot.mongo;

import com.google.common.collect.Lists;
import com.mongodb.ServerAddress;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;

public class PropriétésMongo {

    public PropriétésMongo(String dataBase, String port, String host, String user, String password) {
        this.dataBase = dataBase;
        this.port = port;
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public Settings ajouteLesPropriétés(Settings settings) {
        return settings
                .withDbName(getNomBaseDeDonnees())
                .withAddresses(Lists.newArrayList(getServerAddress()))
                .withDefaultUpdateStrategy(getDefaultStrategy())
                .withAuthentication(getUser(), getPassword());
    }

    String getNomBaseDeDonnees() {
        return dataBase;
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
        return port;
    }

    private String getHost() {
        return host;
    }

    UpdateStrategies getDefaultStrategy() {
        return UpdateStrategies.DIFF;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

    private final String dataBase;
    private final String port;
    private final String host;
    private final String user;
    private final String password;
}
