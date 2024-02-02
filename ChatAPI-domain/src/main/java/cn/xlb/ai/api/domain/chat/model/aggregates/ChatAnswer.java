package cn.xlb.ai.api.domain.chat.model.aggregates;

import cn.xlb.ai.api.domain.chat.model.vo.Output;

/**
 * 响应结果的封装
 */
public class ChatAnswer
{
    private Output output;

    private String request_id;

    public void setOutput(Output output){
        this.output = output;
    }
    public Output getOutput(){
        return this.output;
    }
    public void setRequest_id(String request_id){
        this.request_id = request_id;
    }
    public String getRequest_id(){
        return this.request_id;
    }
}