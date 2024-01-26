package cn.xlb.ai.api.domain.zsxq.service;

import cn.xlb.ai.api.domain.zsxq.IZsxqApi;
import cn.xlb.ai.api.domain.zsxq.model.aggregates.TopicsQuestionsAggregates;
import cn.xlb.ai.api.domain.zsxq.model.req.AnswerReq;
import cn.xlb.ai.api.domain.zsxq.model.req.ReqData;
import cn.xlb.ai.api.domain.zsxq.model.res.AnswerRes;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 知识星球接口实现
 *
 * @author 19159
 * &#064;created 2024/1/26
 */
@Service
public class ZsxqApi implements IZsxqApi {
    private final Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public TopicsQuestionsAggregates queryQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=all&count=20");

        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json; charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("拉取提问数据。groupId：{} jsonStr：{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, TopicsQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryQuestionsTopicId Err Code is " + response.getStatusLine().getStatusCode());
        }

    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/comments");
        post.addHeader("cookie", cookie);
        post.addHeader("Content-Type", "application/json;charset=utf8");
        post.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0");

        /* 测试数据
            String paramJson = "{\"req_data\":{\"text\":\"测试测试\\n\",\"image_ids\":[],\"mentioned_user_ids\":[]}}";
         */

        AnswerReq answerReq = new AnswerReq(new ReqData(text));
        String paramJson = JSONObject.fromObject(answerReq).toString();

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}

