package cn.xlb.ai.api.test;

import cn.xlb.ai.api.domain.chat.ITongYi;
import cn.xlb.ai.api.domain.zsxq.IZsxqApi;
import cn.xlb.ai.api.domain.zsxq.model.aggregates.TopicsQuestionsAggregates;
import cn.xlb.ai.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * SpringBoot运行测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private final Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${ChatAPI.groupId}")
    private String groupId;
    @Value("${ChatAPI.cookie}")
    private String cookie;
    @Value("${ChatAPI.userId}")
    private String userId;
    @Value("${ChatAPI.tyKey}")
    private String tyKey;

    @Resource//用来标记需要注入的资源
    private IZsxqApi zsxqApi;

    @Resource
    private ITongYi tongyiApi;

    @Test
    public void test_zsxqApi() throws IOException {
        TopicsQuestionsAggregates topicsQuestionsAggregates = zsxqApi.queryQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(topicsQuestionsAggregates));

        List<Topics> topics = topicsQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getTalk().getText();
            logger.info("topicId：{} text：{}", topicId, text);

            // 判断userId是自己就回复
            if (Objects.equals(topic.getTalk().getOwner().getUser_id(), userId)) {
                zsxqApi.answer(groupId, cookie, topicId, text);
            }
        }
    }

    @Test
    public void test_TongYiApi() throws IOException {
        String res = tongyiApi.doChat("1+1等于几", tyKey);
        logger.info("测试结果：{}", res);
    }
}