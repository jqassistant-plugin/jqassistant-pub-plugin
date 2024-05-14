package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.FundingDescriptor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FundingMapper extends DescriptorMapper<String, FundingDescriptor> {

    FundingMapper INSTANCE = getMapper(FundingMapper.class);

    @Override
    default FundingDescriptor toDescriptor(String value, @Context Scanner scanner) {
        FundingDescriptor descriptor = resolve(value, FundingDescriptor.class, scanner);
        descriptor.setUrl(value);
        return descriptor;
    }

    List<FundingDescriptor> mapList(List<String> source, @Context Scanner scanner);

}
