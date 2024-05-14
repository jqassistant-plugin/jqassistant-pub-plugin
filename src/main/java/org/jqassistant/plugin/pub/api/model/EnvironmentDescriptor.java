package org.jqassistant.plugin.pub.api.model;

import com.buschmais.jqassistant.plugin.common.api.model.NamedDescriptor;
import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("Environment")
public interface EnvironmentDescriptor extends PubDescriptor, NamedDescriptor {

    String getVersion();

    void setVersion(String version);

}
