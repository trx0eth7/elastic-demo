package com.demo.elastic.search.component;

import com.demo.elastic.config.ElasticConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;


@Component("demo_RestClient")
public class RestClientFactory extends AbstractFactoryBean<RestHighLevelClient> {

    private ElasticConfig elasticConfig;

    @Autowired
    public RestClientFactory(ElasticConfig elasticConfig) {
        this.elasticConfig = elasticConfig;
    }

    @Override
    public Class<?> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    protected RestHighLevelClient createInstance() throws Exception {
        HttpHost httpHost =
                new HttpHost(elasticConfig.getHost(), elasticConfig.getPort(), elasticConfig.getScheme());

        return new RestHighLevelClient(RestClient.builder(httpHost));
    }

    @Override
    protected void destroyInstance(RestHighLevelClient client) throws Exception {
        client.close();
        super.destroyInstance(client);
    }
}
