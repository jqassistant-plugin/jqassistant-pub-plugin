package org.jqassistant.plugin.pub.impl.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
public class GitDependency extends Dependency {
    private String url;
    private String ref;
    private String path;
}
