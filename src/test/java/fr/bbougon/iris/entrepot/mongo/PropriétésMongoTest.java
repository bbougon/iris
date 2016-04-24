package fr.bbougon.iris.entrepot.mongo;

import org.junit.Test;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;

import static org.fest.assertions.api.Assertions.assertThat;

public class PropriétésMongoTest {

    @Test
    public void onPeutAjouterDesProprietes() {
        Settings settings = Settings.defaultInstance();
        PropriétésMongo proprietes = new PropriétésMongo();

        proprietes.ajouteLesPropriétés(settings);

        assertThat(proprietes.getNomBaseDeDonnees()).isEqualTo("iris");
        assertThat(proprietes.getServerAddress().getHost()).isEqualTo("127.0.0.1");
        assertThat(proprietes.getServerAddress().getPort()).isEqualTo(27017);
        assertThat(proprietes.getDefaultStrategy()).isEqualTo(UpdateStrategies.DIFF);
        assertThat(proprietes.getUser()).isEqualTo("user");
        assertThat(proprietes.getPassword()).isEqualTo("password");
    }

}