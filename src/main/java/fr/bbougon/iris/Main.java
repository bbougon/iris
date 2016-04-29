package fr.bbougon.iris;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

public class Main {


    public static void main(String[] args) throws Exception {
        Configuration.configurationServeur().getPort();
        LOGGER.info("Serveur en cours de démarrage sur le port ");
        new Serveur(new InetSocketAddress(8282)).démarre();
    }

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getCanonicalName());
}
