package cn.xlb.ai.api.application.job;

import cn.xlb.ai.api.domain.chat.ITongYi;
import cn.xlb.ai.api.domain.zsxq.IZsxqApi;
import cn.xlb.ai.api.domain.zsxq.model.aggregates.TopicsQuestionsAggregates;
import cn.xlb.ai.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.*;

/**
 * 回复问题任务
 */
@EnableScheduling
@Configuration
public class ChatSchedule {

    private final Logger logger = LoggerFactory.getLogger(ChatSchedule.class);

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

    // 表达式：cron.qqe2.com
    @Scheduled(cron = "0/30 * * * * ?")//每三十秒运行一次
    public void run() {
        try {
            if (new Random().nextBoolean()) {//随机生成布尔值
                logger.info("随机打烊中...");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);//获取当前小时数
            if (hour > 22 || hour < 7) {
                logger.info("打烊时间不工作，AI 下班了！");
                return;
            }

            // 1. 检索问题
            TopicsQuestionsAggregates topicsQuestionsAggregates = zsxqApi.queryQuestionsTopicId(groupId, cookie);
            logger.info("检索结果：{}", JSON.toJSONString(topicsQuestionsAggregates));
            List<Topics> topics = topicsQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次检索未查询到待会答问题");
                return;
            }

            // 2. AI 回答
            Topics topic1 = null;
            String answer = null;
            for (Topics topic : topics) {
                String topicId = topic.getTopic_id();
                String text = topic.getTalk().getText();
//                logger.info("topicId：{} text：{}", topicId, text);

                // 判断userId是自己就获取，只获取一条
                if (Objects.equals(topic.getTalk().getOwner().getUser_id(), userId)) {
                    topic1 = topic;
                    answer = tongyiApi.doChat(topic.getTalk().getText(), tyKey);
                    break;
                }
            }

            // 3. 问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic1.getTopic_id(), answer);
            logger.info("编号：{} 问题：{} 回答：{} 状态：{}", topic1.getTopic_id(), topic1.getTalk().getText(), answer, status);
        } catch (Exception e) {
            logger.error("自动回答问题异常", e);
        }
    }

}