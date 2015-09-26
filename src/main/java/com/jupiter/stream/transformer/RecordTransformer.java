package com.jupiter.stream.transformer;

import org.apache.avro.generic.GenericRecord;

/**
 * Created by hmohamed on 9/16/15.
 */
public class RecordTransformer {
    public String process(GenericRecord record) {
        return record.toString();
    }
}
