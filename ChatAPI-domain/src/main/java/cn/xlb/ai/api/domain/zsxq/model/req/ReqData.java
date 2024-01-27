package cn.xlb.ai.api.domain.zsxq.model.req;

/**
 * 请求对象，发送回答的请求，这里只传text。
 */
public class ReqData {

    private String text;
    private String[] image_ids = new String[]{};


    public ReqData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(String[] image_ids) {
        this.image_ids = image_ids;
    }

}