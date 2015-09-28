package com.jupiter.stream.json;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hxm3459 on 9/22/15.
 */
@Component
@Slf4j
public class JsonTransformer {

    private static final Log logger = LogFactory.getLog(JsonTransformer.class);

    @Autowired
    ResourceContent resourceContent;

    @Value("${mappingFilePath:}")
    String mappingFilePath;


    public MappingConfig getMappingConfig() {

        String configJson = jsonContent();

        if (configJson == null) {
            return null;
        }

        MappingConfig mappingConfig = null;

        final ObjectMapper mapper = new ObjectMapper();
        try {
            mappingConfig = mapper.readValue(configJson, MappingConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mappingConfig;
    }

    private String jsonContent() {
        return resourceContent.getContent(mappingFilePath);
    }

    /**
     * Transforms a JSON segment to another mapped json
     *
     * @param jsonSegment
     * @return
     */
    public String transform(String jsonSegment) throws IOException {

        if (StringUtils.isEmpty(mappingFilePath)) {
            return jsonSegment;
        }

        if (jsonSegment == null) {
            return null;
        }

        Map<String,Object> map = new ObjectMapper().readValue(jsonSegment, HashMap.class);

        MappingConfig mappingConfig = getMappingConfig();
        Map<String,Object> transformedMap = mappingConfig.mapFields(map);

        String json = jsonSegment;
        if (transformedMap != null) {
            Gson gson = new Gson();
            json = gson.toJson(transformedMap);
            log.info("Transformed JSON: %s", json);
        }

        return json;
    }

}
