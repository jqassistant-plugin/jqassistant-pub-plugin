package org.jqassistant.plugin.pub.api.model;

import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("Path")
public interface PathDependencyDescriptor extends DependencyDescriptor {

    String getPath();

    void setPath(String path);

}
