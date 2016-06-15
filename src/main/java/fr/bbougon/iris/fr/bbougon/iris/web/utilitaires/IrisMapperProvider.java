package fr.bbougon.iris.fr.bbougon.iris.web.utilitaires;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class IrisMapperProvider implements ContextResolver<ObjectMapper> {

    public IrisMapperProvider() {
        defaultObjectMapper = createIrisMapper();
    }

    private static ObjectMapper createIrisMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.configure(SerializationFeature.INDENT_OUTPUT, true);
        return result;
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }
    final ObjectMapper defaultObjectMapper;

}
