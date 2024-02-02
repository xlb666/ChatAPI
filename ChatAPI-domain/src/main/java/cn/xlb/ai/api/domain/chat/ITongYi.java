package cn.xlb.ai.api.domain.chat;

import java.io.IOException;

/**
 * 通义千问接口
 *
 * @author 19159
 * &#064;created 2024/2/2
 */
public interface ITongYi {
    String doChat(String question, String tyKey) throws IOException;
}
