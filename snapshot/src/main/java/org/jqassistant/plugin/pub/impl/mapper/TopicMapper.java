package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.TopicDescriptor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TopicMapper extends DescriptorMapper<String, TopicDescriptor> {

    TopicMapper INSTANCE = getMapper(TopicMapper.class);

    @Override
    default TopicDescriptor toDescriptor(String value, @Context Scanner scanner) {
        TopicDescriptor descriptor = resolve(value, TopicDescriptor.class, scanner);
        descriptor.setName(value);
        return descriptor;
    }

    List<TopicDescriptor> mapList(List<String> source, @Context Scanner scanner);

}
