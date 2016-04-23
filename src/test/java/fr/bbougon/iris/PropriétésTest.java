package fr.bbougon.iris;

import org.junit.Test;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;

import static org.fest.assertions.api.Assertions.assertThat;

public class PropriétésTest {

    @Test
    public void onPeutAjouterDesProprietes() {
        Settings settings = Settings.defaultInstance();
        Propriétés proprietes = new Propriétés();

        proprietes.ajouteLesPropriétés(settings);

        assertThat(proprietes.getNomBaseDeDonnees()).isEqualTo("basededonnees");
        assertThat(proprietes.getServerAddress().getHost()).isEqualTo("127.0.0.1");
        assertThat(proprietes.getServerAddress().getPort()).isEqualTo(27017);
        assertThat(proprietes.getDefaultStrategy()).isEqualTo(UpdateStrategies.DIFF);
        assertThat(proprietes.getUser()).isEqualTo("user");
        assertThat(proprietes.getPassword()).isEqualTo("password");
    }

}