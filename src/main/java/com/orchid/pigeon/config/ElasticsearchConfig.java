package com.orchid.pigeon.config;

import javafx.scene.NodeBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.orchid.pigeon")
@PropertySource("classpath:application.properties")
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clusterName}")
    private String EsClusterName;

    @Bean
    public TransportClient client()  {
        Settings elasticsearchSettings = Settings.builder()
                .put("cluster.name", EsClusterName)
                .build();

        TransportClient transportClient = null;
        try {
            transportClient = new PreBuiltTransportClient(elasticsearchSettings)
                    .addTransportAddress(new InetSocketTransportAddress(
                    InetAddress.getByName("localhost"), 9200));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return transportClient;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}
