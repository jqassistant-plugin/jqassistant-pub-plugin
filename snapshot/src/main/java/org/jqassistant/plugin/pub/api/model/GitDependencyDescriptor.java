package org.jqassistant.plugin.pub.api.model;

import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("Git")
public interface GitDependencyDescriptor extends DependencyDescriptor {

    String getUrl();

    void setUrl(String url);

    String getRef();

    void setRef(String ref);

    String getPath();

    void setPath(String path);
}
