package org.jqassistant.plugin.pub.api.model;

import com.buschmais.jqassistant.core.store.api.model.Descriptor;
import com.buschmais.xo.api.annotation.Abstract;
import com.buschmais.xo.neo4j.api.annotation.Label;

/**
 * Base label for pub-related nodes.
 */
@Label("Pub")
@Abstract
public interface PubDescriptor extends Descriptor {
}
