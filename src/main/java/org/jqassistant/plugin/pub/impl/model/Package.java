package org.jqassistant.plugin.pub.impl.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jqassistant.plugin.pub.impl.deserializer.DependencyDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Used for unmarshalling pubspec.yaml files using Jackson.
 */
@Getter
@Setter
@ToString
public class Package {

    private String name;

    private String version;

    private String description;

    @JsonAlias("environment")
    private Map<String, String> environments;

    private String homepage;

    private String repository;

    @JsonAlias("issue_tracker")
    private String issueTracker;

    private String documentation;

    @JsonDeserialize(using = DependencyDeserializer.class)
    private List<Dependency> dependencies = new ArrayList<>();

    @JsonAlias("dev_dependencies")
    @JsonDeserialize(using = DependencyDeserializer.class)
    private List<Dependency> devDependencies = new ArrayList<>();

    @JsonAlias("dependency_overrides")
    @JsonDeserialize(using = DependencyDeserializer.class)
    private List<Dependency> dependencyOverrides = new ArrayList<>();

    private Map<String, String> executables;

    private Map<String, String> platforms;

    @JsonAlias("publish_to")
    private String publishTo;

    private List<String> funding = new ArrayList<>();

    @JsonAlias("false_secrets")
    private List<String> falseSecrets = new ArrayList<>();

    private List<Screenshot> screenshots = new ArrayList<>();

    private List<String> topics = new ArrayList<>();

    @JsonAlias("ignored_advisories")
    private List<String> ignoredAdvisories = new ArrayList<>();

}
