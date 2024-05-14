package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.EnvironmentDescriptor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EnvironmentMapper extends DescriptorMapper<Map.Entry<String, String>, EnvironmentDescriptor> {

    EnvironmentMapper INSTANCE = getMapper(EnvironmentMapper.class);

    default EnvironmentDescriptor map(Map.Entry<String, String> value, Scanner scanner) {
        EnvironmentDescriptor descriptor = resolve(null, EnvironmentDescriptor.class, scanner);
        descriptor.setName(value.getKey());
        descriptor.setVersion(value.getValue());
        return descriptor;
    }

    default List<EnvironmentDescriptor> mapList(Map<String, String> source, @Context Scanner scanner) {
        return source.entrySet().stream().map(entry -> map(entry, scanner)).collect(Collectors.toList());
    }

}
