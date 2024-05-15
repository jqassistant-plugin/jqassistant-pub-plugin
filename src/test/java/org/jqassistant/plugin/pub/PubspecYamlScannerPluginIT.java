package org.jqassistant.plugin.pub;

import com.buschmais.jqassistant.core.scanner.api.DefaultScope;
import com.buschmais.jqassistant.core.test.plugin.AbstractPluginIT;
import org.jqassistant.plugin.pub.api.model.*;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class PubspecYamlScannerPluginIT extends AbstractPluginIT {

    @Test
    void minimal() {
        File file = new File(getClassesDirectory(PubspecYamlScannerPluginIT.class), "/minimal/pubspec.yaml");

        PackageDescriptor pubspecYaml = getScanner().scan(file, "/minimal/pubspec.yaml", DefaultScope.NONE);

        store.beginTransaction();
        assertThat(pubspecYaml).isNotNull();
        assertThat(pubspecYaml.getName()).isEqualTo("minimal-package");
        assertThat(pubspecYaml.getVersion()).isEqualTo("1.0.0");
        store.commitTransaction();
    }

    @Test
    void full() {
        File file = new File(getClassesDirectory(PubspecYamlScannerPluginIT.class), "/full/pubspec.yaml");

        PackageDescriptor pubspecYaml = getScanner().scan(file, "/full/pubspec.yaml", DefaultScope.NONE);

        store.beginTransaction();
        assertThat(pubspecYaml).isNotNull();
        assertThat(pubspecYaml.getName()).isEqualTo("full-package");
        assertThat(pubspecYaml.getVersion()).isEqualTo("1.0.0");
        assertThat(pubspecYaml.getDescription()).isEqualTo("This is a longer description. It spans multiple lines.");

        assertThat(pubspecYaml.getEnvironments()).hasSize(1);
        EnvironmentDescriptor environment = pubspecYaml.getEnvironments().get(0);
        assertThat(environment.getName()).isEqualTo("sdk");
        assertThat(environment.getVersion()).isEqualTo("^3.0.0");

        assertThat(pubspecYaml.getHomepage()).isEqualTo("https://example.com/home");
        assertThat(pubspecYaml.getRepository()).isEqualTo("https://example.com/repo");
        assertThat(pubspecYaml.getIssueTracker()).isEqualTo("https://example.com/issues");
        assertThat(pubspecYaml.getDocumentation()).isEqualTo("https://example.com/docs");

        assertThat(pubspecYaml.getDependencies()).hasSize(6)
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_simple_dependency");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "1.0.0")
                    .hasFieldOrPropertyWithValue("host", "https://pub.dev");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_simple_dependency2");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "1.1.0")
                    .hasFieldOrPropertyWithValue("host", "https://pub.dev");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_sdk_dependency");
                assertThat(dep).isInstanceOf(SdkDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("sdk", "flutter");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_hosted_dependency");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "1.0.0")
                    .hasFieldOrPropertyWithValue("host", "https://some-package-server.com");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_git_dependency");
                assertThat(dep).isInstanceOf(GitDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("url", "git@github.com:user/myrepo.git")
                    .hasFieldOrPropertyWithValue("ref", "some-branch")
                    .hasFieldOrPropertyWithValue("path", "path/to/package");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_path_dependency");
                assertThat(dep).isInstanceOf(PathDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("path", "path/to/dependency");
            });

        assertThat(pubspecYaml.getDevDependencies()).hasSize(6)
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_simple_dev_dependency");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "1.0.0")
                    .hasFieldOrPropertyWithValue("host", "https://pub.dev");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_simple_dev_dependency2");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "1.1.0")
                    .hasFieldOrPropertyWithValue("host", "https://pub.dev");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_sdk_dev_dependency");
                assertThat(dep).isInstanceOf(SdkDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("sdk", "flutter");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_hosted_dev_dependency");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "1.0.0")
                    .hasFieldOrPropertyWithValue("host", "https://some-package-server.com");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_git_dev_dependency");
                assertThat(dep).isInstanceOf(GitDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("url", "git@github.com:user/myrepo.git")
                    .hasFieldOrPropertyWithValue("ref", "some-branch")
                    .hasFieldOrPropertyWithValue("path", "path/to/dev_package");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_path_dev_dependency");
                assertThat(dep).isInstanceOf(PathDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("path", "path/to/dev_dependency");
            });

        assertThat(pubspecYaml.getDependencyOverrides()).hasSize(6)
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_simple_dependency");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "2.0.0")
                    .hasFieldOrPropertyWithValue("host", "https://pub.dev");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_simple_dependency2");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "2.2.0")
                    .hasFieldOrPropertyWithValue("host", "https://pub.dev");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_sdk_dependency");
                assertThat(dep).isInstanceOf(SdkDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("sdk", "other-sdk");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_hosted_dependency");
                assertThat(dep).isInstanceOf(HostedDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("version", "2.0.0")
                    .hasFieldOrPropertyWithValue("host", "https://some-other-package-server.com");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_git_dependency");
                assertThat(dep).isInstanceOf(GitDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("url", "git@github.com:user/myotherrepo.git")
                    .hasFieldOrPropertyWithValue("ref", "some-branch")
                    .hasFieldOrPropertyWithValue("path", "path/to/package");
            })
            .anySatisfy(dep -> {
                assertThat(dep.getName()).isEqualTo("my_path_dependency");
                assertThat(dep).isInstanceOf(PathDependencyDescriptor.class)
                    .hasFieldOrPropertyWithValue("path", "some/other/path");
            });

        assertThat(pubspecYaml.getExecutables()).hasSize(2)
            .anySatisfy(exec -> {
                assertThat(exec.getName()).isEqualTo("script1");
                assertThat(exec.getPath()).isEqualTo("main");
            })
            .anySatisfy(exec -> {
                assertThat(exec.getName()).isEqualTo("my-script");
                assertThat(exec.getPath()).isNull();
            });

        assertThat(pubspecYaml.getPlatforms()).hasSize(2)
            .anySatisfy(platform -> {
                assertThat(platform.getName()).isEqualTo("linux");
            })
            .anySatisfy(platform -> {
                assertThat(platform.getName()).isEqualTo("android");
            });

        assertThat(pubspecYaml.getPublishTo()).isEqualTo("none");

        assertThat(pubspecYaml.getFundings()).hasSize(2)
            .anySatisfy(funding -> {
                assertThat(funding.getUrl()).isEqualTo("https://www.buymeacoffee.com/example_user");
            })
            .anySatisfy(funding -> {
                assertThat(funding.getUrl()).isEqualTo("https://www.patreon.com/some-account");
            });

        assertThat(pubspecYaml.getFalseSecrets()).hasSize(2)
            .anySatisfy(fs -> {
                assertThat(fs.getPath()).isEqualTo("/lib/src/hardcoded_api_key.dart");
            })
            .anySatisfy(fs -> {
                assertThat(fs.getPath()).isEqualTo("/test/localhost_certificates/*.pem");
            });

        assertThat(pubspecYaml.getScreenshots()).hasSize(2)
            .anySatisfy(s -> {
                assertThat(s.getDescription()).isEqualTo("This screenshot shows the transformation of a number of bytes to a human-readable expression.");
                assertThat(s.getPath()).isEqualTo("path/to/image/in/package/500x500.webp");
            })
            .anySatisfy(s -> {
                assertThat(s.getDescription()).isEqualTo("This screenshot shows a stack trace returning a human-readable representation.");
                assertThat(s.getPath()).isEqualTo("path/to/image/in/package.png");
            });

        assertThat(pubspecYaml.getTopics()).hasSize(2)
            .anySatisfy(topic -> {
                assertThat(topic.getName()).isEqualTo("network");
            })
            .anySatisfy(topic -> {
                assertThat(topic.getName()).isEqualTo("http");
            });

        assertThat(pubspecYaml.getIgnoredAdvisories()).hasSize(1)
            .anySatisfy(topic -> {
                assertThat(topic.getName()).isEqualTo("GHSA-4rgh-jx4f-qfcq");
            });

        store.commitTransaction();
    }
}
