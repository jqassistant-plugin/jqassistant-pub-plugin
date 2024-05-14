package org.jqassistant.plugin.pub.api.model;

import com.buschmais.jqassistant.plugin.common.api.model.NamedDescriptor;
import com.buschmais.xo.neo4j.api.annotation.Label;
import com.buschmais.xo.neo4j.api.annotation.Relation;

import java.util.List;

/**
 * Represents a pubspec.yaml file.
 */
@Label("Package")
public interface PackageDescriptor extends PubDescriptor, NamedDescriptor {

    String getVersion();

    void setVersion(String version);

    String getDescription();

    void setDescription(String description);

    @Relation("HAS_ENVIRONMENT")
    List<EnvironmentDescriptor> getEnvironments();

    String getHomepage();

    void setHomepage(String homepage);

    String getRepository();

    void setRepository(String repository);

    String getIssueTracker();

    void setIssueTracker(String issueTracker);

    String getDocumentation();

    void setDocumentation(String documentation);

    @Relation("HAS_DEPENDENCY")
    List<DependencyDescriptor> getDependencies();

    @Relation("HAS_DEV_DEPENDENCY")
    List<DependencyDescriptor> getDevDependencies();

    @Relation("HAS_DEPENDENCY_OVERRIDE")
    List<DependencyDescriptor> getDependencyOverrides();

    @Relation("HAS_EXECUTABLE")
    List<ExecutableDescriptor> getExecutables();

    @Relation("SUPPORTS_PLATFORM")
    List<PlatformDescriptor> getPlatforms();

    String getPublishTo();

    void setPublishTo(String publishTo);

    @Relation("FUNDED_THROUGH")
    List<FundingDescriptor> getFundings();

    @Relation("HAS_FALSE_SECRET")
    List<FalseSecretDescriptor> getFalseSecrets();

    @Relation("HAS_SCREENSHOT")
    List<ScreenshotDescriptor> getScreenshots();

    @Relation("HAS_TOPIC")
    List<TopicDescriptor> getTopics();

    @Relation("HAS_IGNORED_ADVISORY")
    List<IgnoredAdvisoryDescriptor> getIgnoredAdvisories();

}
