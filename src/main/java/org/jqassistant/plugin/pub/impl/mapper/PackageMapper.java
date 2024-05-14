package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import org.jqassistant.plugin.pub.api.model.*;
import org.jqassistant.plugin.pub.impl.model.Package;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(uses = {EnvironmentMapper.class, DependencyMapper.class, ExecutableMapper.class, PlatformMapper.class,
    FundingMapper.class, FalseSecretMapper.class, ScreenshotMapper.class, TopicMapper.class, IgnoredAdvisoryMapper.class})
public interface PackageMapper {

    PackageMapper INSTANCE = getMapper(PackageMapper.class);

    @Mapping(source = "environments", target = "environments", qualifiedByName = "mapEnvironments")
    @Mapping(source = "executables", target = "executables", qualifiedByName = "mapExecutables")
    @Mapping(source = "platforms", target = "platforms", qualifiedByName = "mapPlatforms")
    @Mapping(source = "funding", target = "fundings", qualifiedByName = "mapFundings")
    @Mapping(source = "falseSecrets", target = "falseSecrets", qualifiedByName = "mapFalseSecrets")
    @Mapping(source = "topics", target = "topics", qualifiedByName = "mapTopics")
    @Mapping(source = "ignoredAdvisories", target = "ignoredAdvisories", qualifiedByName = "mapIgnoredAdvisories")
    PackageDescriptor updateDescriptor(Package source, @MappingTarget PackageDescriptor packageDescriptor, @Context Scanner scanner);

    @Named("mapEnvironments")
    default List<EnvironmentDescriptor> mapEnvironments(Map<String, String> value, @Context Scanner scanner) {
        return EnvironmentMapper.INSTANCE.mapList(value, scanner);
    }

    @Named("mapExecutables")
    default List<ExecutableDescriptor> mapExecutables(Map<String, String> value, @Context Scanner scanner) {
        return ExecutableMapper.INSTANCE.mapList(value, scanner);
    }

    @Named("mapPlatforms")
    default List<PlatformDescriptor> mapPlatforms(Map<String, String> value, @Context Scanner scanner) {
        return PlatformMapper.INSTANCE.mapList(value, scanner);
    }

    @Named("mapFundings")
    default List<FundingDescriptor> mapFundings(List<String> value, @Context Scanner scanner) {
        return FundingMapper.INSTANCE.mapList(value, scanner);
    }

    @Named("mapFalseSecrets")
    default List<FalseSecretDescriptor> mapFalseSecrets(List<String> value, @Context Scanner scanner) {
        return FalseSecretMapper.INSTANCE.mapList(value, scanner);
    }

    @Named("mapTopics")
    default List<TopicDescriptor> mapTopics(List<String> value, @Context Scanner scanner) {
        return TopicMapper.INSTANCE.mapList(value, scanner);
    }

    @Named("mapIgnoredAdvisories")
    default List<IgnoredAdvisoryDescriptor> mapIgnoredAdvisories(List<String> value, @Context Scanner scanner) {
        return IgnoredAdvisoryMapper.INSTANCE.mapList(value, scanner);
    }

    @ObjectFactory
    default PackageDescriptor resolve(Package value, @TargetType Class<PackageDescriptor> descriptorType, @Context Scanner scanner) {
        return scanner.getContext()
            .getStore()
            .create(descriptorType);
    }

}
