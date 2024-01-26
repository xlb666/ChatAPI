package cn.xlb.ai.api.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

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
}
