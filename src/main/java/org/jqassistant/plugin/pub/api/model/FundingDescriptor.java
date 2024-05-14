package org.jqassistant.plugin.pub.api.model;

import com.buschmais.xo.neo4j.api.annotation.Label;

@Label("Funding")
public interface FundingDescriptor extends PubDescriptor {

    String getUrl();

    void setUrl(String url);

}
