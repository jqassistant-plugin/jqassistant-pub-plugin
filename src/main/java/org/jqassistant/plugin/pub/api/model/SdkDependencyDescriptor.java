package org.jqassistant.plugin.pub.api.model;

import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("SDK")
public interface SdkDependencyDescriptor extends DependencyDescriptor {

    String getSdk();

    void setSdk(String sdk);

}
