package fr.bbougon.iris.web.ressources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class IrisMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public IrisMapperProvider() {
        defaultObjectMapper = createIrisMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    private static ObjectMapper createIrisMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.configure(SerializationFeature.INDENT_OUTPUT, true);
        return result;
    }

}
