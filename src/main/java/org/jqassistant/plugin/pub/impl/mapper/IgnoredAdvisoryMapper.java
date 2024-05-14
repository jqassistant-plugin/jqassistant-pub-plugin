package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.IgnoredAdvisoryDescriptor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IgnoredAdvisoryMapper extends DescriptorMapper<String, IgnoredAdvisoryDescriptor> {

    IgnoredAdvisoryMapper INSTANCE = getMapper(IgnoredAdvisoryMapper.class);

    @Override
    default IgnoredAdvisoryDescriptor toDescriptor(String value, @Context Scanner scanner) {
        IgnoredAdvisoryDescriptor descriptor = resolve(value, IgnoredAdvisoryDescriptor.class, scanner);
        descriptor.setName(value);
        return descriptor;
    }

    List<IgnoredAdvisoryDescriptor> mapList(List<String> source, @Context Scanner scanner);

}
