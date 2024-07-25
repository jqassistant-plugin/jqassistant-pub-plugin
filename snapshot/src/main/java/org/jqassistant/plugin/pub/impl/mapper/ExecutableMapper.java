package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.ExecutableDescriptor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExecutableMapper extends DescriptorMapper<Map.Entry<String, String>, ExecutableDescriptor> {

    ExecutableMapper INSTANCE = getMapper(ExecutableMapper.class);

    default ExecutableDescriptor map(Map.Entry<String, String> value, Scanner scanner) {
        ExecutableDescriptor descriptor = resolve(null, ExecutableDescriptor.class, scanner);
        descriptor.setName(value.getKey());
        descriptor.setPath(value.getValue());
        return descriptor;
    }

    default List<ExecutableDescriptor> mapList(Map<String, String> source, @Context Scanner scanner) {
        if (source == null) {
            return new ArrayList<>();
        }
        return source.entrySet().stream().map(entry -> map(entry, scanner)).collect(Collectors.toList());
    }

}
