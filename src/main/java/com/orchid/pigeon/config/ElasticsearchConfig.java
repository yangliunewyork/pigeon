package com.orchid.pigeon.config;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.orchid.pigeon.repository")
@ComponentScan(basePackages = {"com.orchid.pigeon.service"})
@PropertySource("classpath:application.properties")
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clusterName}")
    private String EsClusterName;


    private Settings buildElasticsearchSettings() {
        return Settings.builder()
                .put("cluster.name", EsClusterName)
                .build();
    }

    @Bean
    public TransportClient buildElasticsearchTransportclient()  {

        TransportClient transportClient = null;
        try {
            transportClient = new PreBuiltTransportClient(buildElasticsearchSettings())
                    .addTransportAddress(new InetSocketTransportAddress(
                    InetAddress.getByName("localhost"), EsPort));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return transportClient;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(buildElasticsearchTransportclient());
    }
}
