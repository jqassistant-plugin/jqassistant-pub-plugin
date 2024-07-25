package org.jqassistant.plugin.pub.impl.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
public class PathDependency extends Dependency {
    private String path;
}
