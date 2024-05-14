package org.jqassistant.plugin.pub.api.model;

import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("FalseSecret")
public interface FalseSecretDescriptor extends PubDescriptor {

    String getPath();

    void setPath(String path);

}
