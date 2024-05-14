package org.jqassistant.plugin.pub.impl.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.jqassistant.plugin.pub.impl.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DependencyDeserializer extends StdDeserializer<List<Dependency>> {

    public DependencyDeserializer() {
        this(null);
    }

    public DependencyDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Dependency> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JacksonException {

        List<Dependency> result = new ArrayList<>();

        JsonNode dependenciesNode = p.getCodec().readTree(p);
        for (Iterator<Map.Entry<String, JsonNode>> it = dependenciesNode.fields(); it.hasNext(); ) {
            var dependencyMapping = it.next();
            String name = dependencyMapping.getKey();
            if (dependencyMapping.getValue().isValueNode()) { // inline pub.dev HostedDependency
                var dep = HostedDependency.builder()
                    .name(name)
                    .hosted("https://pub.dev")
                    .version(dependencyMapping.getValue().asText())
                    .build();
                result.add(dep);
            } else if (dependencyMapping.getValue().isContainerNode()) {
                Map<String, JsonNode> content = dependencyMapping.getValue().properties()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                if (content.containsKey("sdk")) {
                    var dep = SdkDependency.builder()
                        .name(name)
                        .sdk(content.get("sdk").asText())
                        .build();
                    result.add(dep);
                } else if (content.containsKey("hosted")) {
                    var dep = HostedDependency.builder()
                        .name(name)
                        .hosted(content.get("hosted").asText())
                        .version(content.get("version").asText())
                        .build();
                    result.add(dep);
                } else if (content.containsKey("git")) {
                    Map<String, JsonNode> gitContent = content.get("git").properties()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    var dep = GitDependency.builder()
                        .name(name)
                        .url(gitContent.get("url").asText())
                        .ref(gitContent.containsKey("ref") ? gitContent.get("ref").asText() : null)
                        .path(gitContent.containsKey("path") ? gitContent.get("path").asText() : null)
                        .build();
                    result.add(dep);
                } else if (content.containsKey("path")) {
                    var dep = PathDependency.builder()
                        .name(name)
                        .path(content.get("path").asText())
                        .build();
                    result.add(dep);
                } else { // Mapping pub.dev HostedDependency
                    var dep = HostedDependency.builder()
                        .name(name)
                        .hosted("https://pub.dev")
                        .version(content.get("version").asText())
                        .build();
                    result.add(dep);
                }
            }
        }

        return result;
    }
}
