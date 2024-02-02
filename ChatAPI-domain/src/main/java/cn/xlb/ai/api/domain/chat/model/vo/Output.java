package cn.xlb.ai.api.domain.chat.model.vo;

/**
 * 获取的结果
 */
public class Output
{
    private String finish_reason;

    private String text;

    public void setFinish_reason(String finish_reason){
        this.finish_reason = finish_reason;
    }
    public String getFinish_reason(){
        return this.finish_reason;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
}