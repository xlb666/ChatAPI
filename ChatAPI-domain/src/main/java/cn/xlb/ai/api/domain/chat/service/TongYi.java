package cn.xlb.ai.api.domain.chat.service;

import cn.xlb.ai.api.domain.chat.ITongYi;
import cn.xlb.ai.api.domain.chat.model.aggregates.ChatAnswer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 通义千问接口实现
 */
@Service
public class TongYi implements ITongYi {

    private final Logger logger = LoggerFactory.getLogger(TongYi.class);

    @Override
    public String doChat(String question, String tyKey) throws IOException {
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(url);
        post.addHeader("Authorization", "Bearer " + tyKey);


        post.addHeader("Content-Type", "application/json");
        String paramJson = "{\n" +
                "    \"model\": \"qwen-turbo\",\n" +
                "    \"input\":{\n" +
                "        \"messages\":[      \n" +
                "            {\n" +
                "                \"role\": \"system\",\n" +
                "                \"content\": \"You are a helpful assistant.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"role\": \"user\",\n" +
                "                \"content\": \"" + question + "\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"parameters\": {\n" +
                "    }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            ChatAnswer chatAnswer = JSON.parseObject(jsonStr, ChatAnswer.class);//将获取的响应json转成想要的类型
            return chatAnswer.getOutput().getText();//返回文本
        } else {
            throw new RuntimeException("Error Code is"+response.getStatusLine().getStatusCode());
        }
    }

}