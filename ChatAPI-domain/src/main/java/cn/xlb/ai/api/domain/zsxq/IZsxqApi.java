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
    TopicsQuestionsAggregates queryQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text) throws IOException;
}
