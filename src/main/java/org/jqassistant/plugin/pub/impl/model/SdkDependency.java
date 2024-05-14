package org.jqassistant.plugin.pub.impl.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
public class SdkDependency extends Dependency {
    private String sdk;
}
