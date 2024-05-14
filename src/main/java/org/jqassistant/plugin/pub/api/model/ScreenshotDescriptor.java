package org.jqassistant.plugin.pub.api.model;

import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("Screenshot")
public interface ScreenshotDescriptor extends PubDescriptor {

    String getDescription();

    void setDescription(String description);

    String getPath();

    void setPath(String path);

}
