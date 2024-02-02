package cn.xlb.ai.api.test;


import okhttp3.HttpUrl;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 单元测试
 *
 * @author 19159
 * &#064;created 2024/1/24
 */
public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        get.addHeader("cookie", "zsxq_access_token=11411592-9149-0A83-6995-4273F8245AD3_AA5683D4EFB9652A; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22585152581822424%22%2C%22first_id%22%3A%2218d3efd6d36f1d-023841bcff5e2ec-4c657b58-1296000-18d3efd6d371a99%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkM2VmZDZkMzZmMWQtMDIzODQxYmNmZjVlMmVjLTRjNjU3YjU4LTEyOTYwMDAtMThkM2VmZDZkMzcxYTk5IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTg1MTUyNTgxODIyNDI0In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22585152581822424%22%7D%2C%22%24device_id%22%3A%2218d3efd6d36f1d-023841bcff5e2ec-4c657b58-1296000-18d3efd6d371a99%22%7D;");
        get.addHeader("Content-Type", "application/json; charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/588482112452854/comments");

        post.addHeader("cookie", "zsxq_access_token=11411592-9149-0A83-6995-4273F8245AD3_AA5683D4EFB9652A; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22585152581822424%22%2C%22first_id%22%3A%2218d3efd6d36f1d-023841bcff5e2ec-4c657b58-1296000-18d3efd6d371a99%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkM2VmZDZkMzZmMWQtMDIzODQxYmNmZjVlMmVjLTRjNjU3YjU4LTEyOTYwMDAtMThkM2VmZDZkMzcxYTk5IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTg1MTUyNTgxODIyNDI0In0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22585152581822424%22%7D%2C%22%24device_id%22%3A%2218d3efd6d36f1d-023841bcff5e2ec-4c657b58-1296000-18d3efd6d371a99%22%7D; zsxqsessionid=869087cdcaabb9506b6b65f167222643");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\"req_data\":{\"text\":\"测试测试\\n\",\"image_ids\":[],\"mentioned_user_ids\":[]}}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void testChatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

//        HttpPost post = new HttpPost("https://api.openai-proxy.com/v1/chat/completions");
//        post.addHeader("Authorization", "Bearer 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");

        HttpPost post = new HttpPost("https://oneapi-xwuz.onrender.com/v1/chat/completions");
        post.addHeader("Authorization", "Bearer sk-4kxsFT54XdXodCc45d08AeEe5c2148E8AaE81177778d145f");

//        HttpPost post = new HttpPost("https://awvrvhqs.cloud.sealos.io/v1/chat/completions");
//        post.addHeader("Authorization", "Bearer sk-AqwMWS2tYJeNe93fD145E4D975024e2182F678D6A330E184");


//        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
//        post.addHeader("Authorization", "Bearer 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");


        post.addHeader("Content-Type", "application/json");
        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo-0301\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"1+1\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        String res = EntityUtils.toString(response.getEntity());
        System.out.println(res);
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            String res = EntityUtils.toString(response.getEntity());
//            System.out.println(res);
//        } else {
//            System.out.println(response.getStatusLine().getStatusCode());
//        }
    }
    @Test
    public void TestXunFeiAI() throws IOException {
        // 设置请求地址和参数
        String url = "https://api.xfyun.cn/v1/aiui";
        String appId = "bcbec80d"; // 替换成你的appId
        String apiSecret = "MmRhNWUyNmU2ZjM5NDljMGM1ZGIzZTcz"; // 替换成你的apiSecret
        String apiKey = "dbd16ca0430a32a15ae091aad043ef79"; // 替换成你的apiKey
        String text = "你好，小助手"; // 替换成你要发送的文本内容

        // 构建请求体
        StringBuilder builder = new StringBuilder();
        builder.append("{\"header\":{\"app_id\":\"").append(appId).append("\",\"status\":3},\"parameter\":{\"aue\":\"raw\",\"result_level\":\"plain\",\"auth_id\":\"").append(apiKey).append("\",\"data_type\":\"text\",\"scene\":\"main\"},\"payload\":{\"text\":\"").append(text).append("\"}}");
        String body = builder.toString();

        // 创建连接并发送请求
        URL realUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Date", System.currentTimeMillis() / 1000 + "");
        connection.setRequestProperty("Digest", "SHA-256=" + org.apache.commons.codec.digest.DigestUtils.sha256Hex(body));
        connection.setRequestProperty("Authorization", org.apache.commons.codec.binary.Base64.encodeBase64String((apiKey + ":" + apiSecret).getBytes()));
        connection.connect();
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        out.write(body);
        out.flush();
        out.close();

        // 获取响应结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();

        // 输出响应结果
        System.out.println(response.toString());
    }

    @Test
    public void TestTongYi() throws IOException {
        String KEY="sk-e00ec19a55364f0a876f92ce163a8ec0";
        String url="https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(url);
        post.addHeader("Authorization", "Bearer sk-e00ec19a55364f0a876f92ce163a8ec0");


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
                "                \"content\": \"你好，哪个公园距离我最近？\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"parameters\": {\n" +
                "    }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        String res = EntityUtils.toString(response.getEntity());
        System.out.println(res);
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            String res = EntityUtils.toString(response.getEntity());
//            System.out.println(res);
//        } else {
//            System.out.println(response.getStatusLine().getStatusCode());
//        }
    }



}

