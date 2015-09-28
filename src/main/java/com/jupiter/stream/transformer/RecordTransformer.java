package com.jupiter.stream.transformer;

import com.jupiter.stream.json.JsonTransformer;
import org.apache.avro.generic.GenericRecord;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by hmohamed on 9/16/15.
 */
@MessageEndpoint
public class RecordTransformer {

    @Autowired
    JsonTransformer transformer;

    @Transformer(inputChannel="input", outputChannel="output")
    public String process(GenericRecord record) {

        String json = record.toString();
        String transformedJson = null;
        try {
            transformedJson = transformer.transform(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transformedJson;
    }
}
