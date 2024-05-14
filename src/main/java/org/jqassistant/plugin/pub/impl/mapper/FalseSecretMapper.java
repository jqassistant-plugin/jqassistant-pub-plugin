package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.FalseSecretDescriptor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FalseSecretMapper extends DescriptorMapper<String, FalseSecretDescriptor> {

    FalseSecretMapper INSTANCE = getMapper(FalseSecretMapper.class);

    @Override
    default FalseSecretDescriptor toDescriptor(String value, @Context Scanner scanner) {
        FalseSecretDescriptor descriptor = resolve(value, FalseSecretDescriptor.class, scanner);
        descriptor.setPath(value);
        return descriptor;
    }

    List<FalseSecretDescriptor> mapList(List<String> source, @Context Scanner scanner);

}
