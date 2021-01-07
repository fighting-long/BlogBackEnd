package my.lxh.blog.ws;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 思路：当登陆后台时建立连接，当有新评论时，发送信息到服务器，服务器发送信息给管理后台
 * 管理后台接收到信息后，需要更新评论
 * @author lxh
 * @date 2021-01-07 11:44
 */
@Component
@ServerEndpoint("/comment")
public class CommentEndpoint {

    public static List<Session> sessions=new ArrayList<>();

    /**
     * 建立连接时 的操作
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        sessions.add(session);
    }

    /**
     * 接收到客户端时的操作
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){
    }

    /**
     * 连接关闭时的操作
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
