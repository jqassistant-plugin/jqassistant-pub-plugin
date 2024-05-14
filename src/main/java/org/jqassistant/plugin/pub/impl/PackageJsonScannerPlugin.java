package org.jqassistant.plugin.pub.impl;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.core.scanner.api.ScannerPlugin.Requires;
import com.buschmais.jqassistant.core.scanner.api.Scope;
import com.buschmais.jqassistant.core.store.api.Store;
import com.buschmais.jqassistant.plugin.common.api.model.NamedDescriptor;
import com.buschmais.jqassistant.plugin.common.api.scanner.AbstractScannerPlugin;
import com.buschmais.jqassistant.plugin.common.api.scanner.filesystem.FileResource;
import com.buschmais.jqassistant.plugin.yaml2.api.model.YMLFileDescriptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.jqassistant.plugin.pub.api.model.PackageDescriptor;
import org.jqassistant.plugin.pub.impl.mapper.PackageMapper;
import org.jqassistant.plugin.pub.impl.model.Package;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Scanner plugin for pubspec.yaml files.
 */
@Requires(YMLFileDescriptor.class)
public class PackageJsonScannerPlugin extends AbstractScannerPlugin<FileResource, PackageDescriptor> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory()).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    @Override
    public boolean accepts(FileResource fileResource, String path, Scope scope) {
        return path.endsWith("/pubspec.yaml");
    }

    @Override
    public PackageDescriptor scan(FileResource fileResource, String path, Scope scope, Scanner scanner) throws IOException {
        YMLFileDescriptor ymlFileDescriptor = scanner.getContext()
            .peek(YMLFileDescriptor.class);
        Store store = scanner.getContext()
            .getStore();

        Package value = OBJECT_MAPPER.readValue(fileResource.createStream(), Package.class);
        PackageDescriptor packageDescriptor = store.addDescriptorType(ymlFileDescriptor, PackageDescriptor.class);

        return PackageMapper.INSTANCE.updateDescriptor(value, packageDescriptor, scanner);
    }

//    private PersonDescriptor getPersonDescriptor(Package.Person author, Store store) {
//        PersonDescriptor authorDescriptor = store.create(PersonDescriptor.class);
//        authorDescriptor.setName(author.getName());
//        authorDescriptor.setEmail(author.getEmail());
//        authorDescriptor.setUrl(author.getUrl());
//        return authorDescriptor;
//    }

    private <T extends NamedDescriptor> List<T> map(Map<String, String> map, Class<T> descriptorType, BiConsumer<T, String> valueConsumer, Store store) {
        if (map != null) {
            return map.entrySet()
                .stream()
                .map(entry -> {
                    T descriptor = store.create(descriptorType);
                    descriptor.setName(entry.getKey());
                    valueConsumer.accept(descriptor, entry.getValue());
                    return descriptor;
                })
                .collect(toList());
        }
        return emptyList();
    }
}
