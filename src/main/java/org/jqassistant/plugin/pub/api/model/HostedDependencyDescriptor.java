package org.jqassistant.plugin.pub.api.model;

import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("Hosted")
public interface HostedDependencyDescriptor extends DependencyDescriptor {

    String getVersion();

    void setVersion(String version);

    String getHost();

    void setHost(String host);

}
