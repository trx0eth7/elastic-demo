package com.demo.elastic.service;

import com.demo.elastic.config.ElasticConfig;
import com.demo.elastic.helper.ResLoaderHelper;
import com.demo.elastic.search.model.ElasticDocument;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ElasticService {

    private RestHighLevelClient restClient;

    private ElasticConfig elasticConfig;

    private ResLoaderHelper resLoaderHelper;

    private Gson gson;

    public void createIndex() {
        CreateIndexRequest request = new CreateIndexRequest(elasticConfig.getIndexName())
                .settings(resLoaderHelper
                        .loadResFileContent(elasticConfig.getResPathSetting()), XContentType.JSON)
                .mapping(resLoaderHelper
                        .loadResFileContent(elasticConfig.getResPathMapping()), XContentType.JSON);

        try {
            CreateIndexResponse response = restClient.indices().create(request, RequestOptions.DEFAULT);

            if (response.isAcknowledged()) {
                log.info("Index '{}' is created", response.index());
                return;
            }

            log.info("Index '{}' is not created", response.index());

        } catch (IOException e) {
            log.debug("Error occurred while creating index", e);
        }
    }

    public void indexDocumentAsync(ElasticDocument document) {
        IndexRequest request = new IndexRequest()
                .index(elasticConfig.getIndexName())
                .id(document.getId().toString())
                .source(gson.toJson(document), XContentType.JSON)
                .opType(DocWriteRequest.OpType.CREATE);

        restClient.indexAsync(request, RequestOptions.DEFAULT, new DocumentIndexListener());

    }

    public void updateDocumentAsync(ElasticDocument document) {
        UpdateRequest request = new UpdateRequest()
                .index(elasticConfig.getIndexName())
                .id(document.getId().toString())
                .doc(gson.toJson(document), XContentType.JSON)
                .docAsUpsert(true);

        restClient.updateAsync(request, RequestOptions.DEFAULT, new DocumentUpdateListener());
    }


    public void deleteDocumentAsync(UUID docId) {
        DeleteRequest request = new DeleteRequest()
                .index(elasticConfig.getIndexName())
                .id(docId.toString());

        restClient.deleteAsync(request, RequestOptions.DEFAULT, new DocumentDeleteListener());
    }

    static class DocumentIndexListener implements ActionListener<IndexResponse> {

        @Override
        public void onResponse(IndexResponse response) {
            RestStatus status = response.status();

            if (status == RestStatus.CREATED) {
                log.info("Document[{}-{}] is created", response.getId(), response.getId());
            }

            if (status != RestStatus.OK && status != RestStatus.CREATED) {
                log.info("Document[{}-{}] is not create", response.getId(), response.getId());
            }
        }

        @Override
        public void onFailure(Exception e) {
            log.debug("Document indexing is failed", e);
        }
    }

    static class DocumentUpdateListener implements ActionListener<UpdateResponse> {

        @Override
        public void onResponse(UpdateResponse response) {
            RestStatus status = response.status();

            if (status == RestStatus.CREATED) {
                log.info("Document[{}-{}] is updated", response.getId(), response.getId());
            }

            if (status != RestStatus.OK && status != RestStatus.CREATED) {
                log.info("Document[{}-{}] is not update", response.getId(), response.getId());
            }

        }

        @Override
        public void onFailure(Exception e) {
            log.debug("Document updating is failed", e);
        }
    }


    static class DocumentDeleteListener implements ActionListener<DeleteResponse> {

        @Override
        public void onResponse(DeleteResponse response) {
            RestStatus status = response.status();

            if (status == RestStatus.OK) {
                log.info("Document[{}-{}] is deleted", response.getId(), response.getId());
            }

            if (status != RestStatus.OK) {
                log.info("Document[{}-{}] is not deleted", response.getId(), response.getId());
            }
        }

        @Override
        public void onFailure(Exception e) {
            log.debug("Document removing is failed", e);
        }
    }
}
