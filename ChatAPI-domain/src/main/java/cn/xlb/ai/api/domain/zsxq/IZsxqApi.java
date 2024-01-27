package cn.xlb.ai.api.domain.zsxq;

import cn.xlb.ai.api.domain.zsxq.model.aggregates.TopicsQuestionsAggregates;

import java.io.IOException;

/**
 * 知识星球API接口
 *
 * @author 19159
 * &#064;created 2024/1/26
 */
public interface IZsxqApi {
    /**
     * 获取评论中问题的信息
     * @param groupId   每个知识星球都有一个group_id，传入group_id才能知道是哪个星球。
     * @param cookie    这里未实现自动登录，所以要手动传入自己的cookie
     * @return  返回的TopicsQuestionsAggregates是一个存储返回信息的类
     */
    TopicsQuestionsAggregates queryQuestionsTopicId(String groupId, String cookie) throws IOException;

    /**
     *
     * @param groupId   传入星球的专属id才知道回复哪个
     * @param cookie    传入cookie
     * @param topicId   topic_id表明是具体的回复的评论
     * @param text  要回复的文本
     * @return  成功true，失败false
     */
    boolean answer(String groupId, String cookie, String topicId, String text) throws IOException;
}
