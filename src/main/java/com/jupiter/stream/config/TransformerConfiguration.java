package com.jupiter.stream.config;

import com.jupiter.stream.transformer.RecordTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * Created by hmohamed on 9/26/15.
 */
@Configuration
@EnableIntegration
public class TransformerConfiguration {

    @Autowired
    RecordTransformer transformer;

    @Bean
    public MessageChannel input() {
        return new DirectChannel();
    }

    @Bean
    MessageChannel output() {
        return new DirectChannel();
    }
}
