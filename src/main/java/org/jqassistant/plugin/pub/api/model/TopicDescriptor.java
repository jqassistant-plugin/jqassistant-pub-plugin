package org.jqassistant.plugin.pub.api.model;

import com.buschmais.jqassistant.plugin.common.api.model.NamedDescriptor;
import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("Topic")
public interface TopicDescriptor extends PubDescriptor, NamedDescriptor {

}
