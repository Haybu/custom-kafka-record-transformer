package com.jupiter.stream.options;

import org.springframework.xd.module.options.spi.ModuleOption;

/**
 * Created by hmohamed on 9/26/15.
 */
public class TransformerOptionsMetadata {

    private String mappingFilePath;

    public String getMappingFilePath() {
        return mappingFilePath;
    }

    @ModuleOption("the path to the mapping file")
    public void setMappingFilePath(String mappingFilePath) {
        this.mappingFilePath = mappingFilePath;
    }

}
