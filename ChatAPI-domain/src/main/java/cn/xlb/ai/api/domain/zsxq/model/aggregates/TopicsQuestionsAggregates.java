package cn.xlb.ai.api.domain.zsxq.model.aggregates;

import cn.xlb.ai.api.domain.zsxq.model.res.RespData;

/**
 * 评论问题聚合信息
 */
public class TopicsQuestionsAggregates {
    private boolean succeeded;

    private RespData resp_data;

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public boolean getSucceeded() {
        return this.succeeded;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }

    public RespData getResp_data() {
        return this.resp_data;
    }
}