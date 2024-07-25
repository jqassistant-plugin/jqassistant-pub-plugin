package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.*;
import org.jqassistant.plugin.pub.impl.model.*;
import org.mapstruct.*;

@Mapper
public interface DependencyMapper extends DescriptorMapper<Dependency, DependencyDescriptor> {

    @Override
    @SubclassMapping(source = HostedDependency.class, target = HostedDependencyDescriptor.class)
    @SubclassMapping(source = GitDependency.class, target = GitDependencyDescriptor.class)
    @SubclassMapping(source = PathDependency.class, target = PathDependencyDescriptor.class)
    @SubclassMapping(source = SdkDependency.class, target = SdkDependencyDescriptor.class)
    DependencyDescriptor toDescriptor(Dependency value, @Context Scanner scanner);

    @Mapping(source = "hosted", target = "host")
    HostedDependencyDescriptor mapHostedDependency(HostedDependency value, @Context Scanner scanner);

    GitDependencyDescriptor mapGitDependency(GitDependency value, @Context Scanner scanner);

    PathDependencyDescriptor mapPathDependency(PathDependency value, @Context Scanner scanner);

    SdkDependencyDescriptor mapSdkDependency(SdkDependency value, @Context Scanner scanner);

    @ObjectFactory
    default HostedDependencyDescriptor resolveHostedDependency(HostedDependency value, @TargetType Class<HostedDependencyDescriptor> descriptorType, @Context Scanner scanner) {
        return scanner.getContext()
            .getStore()
            .create(descriptorType);
    }

    @ObjectFactory
    default GitDependencyDescriptor resolveGitDependency(GitDependency value, @TargetType Class<GitDependencyDescriptor> descriptorType, @Context Scanner scanner) {
        return scanner.getContext()
            .getStore()
            .create(descriptorType);
    }

    @ObjectFactory
    default PathDependencyDescriptor resolvePathDependency(PathDependency value, @TargetType Class<PathDependencyDescriptor> descriptorType, @Context Scanner scanner) {
        return scanner.getContext()
            .getStore()
            .create(descriptorType);
    }

    @ObjectFactory
    default SdkDependencyDescriptor resolveSdkDependency(SdkDependency value, @TargetType Class<SdkDependencyDescriptor> descriptorType, @Context Scanner scanner) {
        return scanner.getContext()
            .getStore()
            .create(descriptorType);
    }
}
