package org.jqassistant.plugin.pub.impl.mapper;

import com.buschmais.jqassistant.plugin.common.api.mapper.DescriptorMapper;
import org.jqassistant.plugin.pub.api.model.ScreenshotDescriptor;
import org.jqassistant.plugin.pub.impl.model.Screenshot;
import org.mapstruct.Mapper;

@Mapper
public interface ScreenshotMapper extends DescriptorMapper<Screenshot, ScreenshotDescriptor> {

}
