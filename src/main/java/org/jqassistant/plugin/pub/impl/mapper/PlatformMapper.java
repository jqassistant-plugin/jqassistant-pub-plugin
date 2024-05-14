package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.PlatformDescriptor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlatformMapper extends DescriptorMapper<Map.Entry<String, String>, PlatformDescriptor> {

    PlatformMapper INSTANCE = getMapper(PlatformMapper.class);

    default PlatformDescriptor map(Map.Entry<String, String> value, Scanner scanner) {
        PlatformDescriptor descriptor = resolve(null, PlatformDescriptor.class, scanner);
        descriptor.setName(value.getKey());
        return descriptor;
    }

    default List<PlatformDescriptor> mapList(Map<String, String> source, @Context Scanner scanner) {
        if (source == null) {
            return new ArrayList<>();
        }
        return source.entrySet().stream().map(entry -> map(entry, scanner)).collect(Collectors.toList());
    }

}
