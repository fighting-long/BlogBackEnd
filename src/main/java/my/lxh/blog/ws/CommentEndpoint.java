package my.lxh.blog.ws;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;

/**
 * 思路：当登陆后台时建立连接，当有新评论时，发送信息到服务器，服务器发送信息给管理后台
 * 管理后台接收到信息后，需要更新评论
 * @author lxh
 * @date 2021-01-07 11:44
 */
@Component
@ServerEndpoint("/comment")
public class CommentEndpoint {

    public static Session userSession;

    /**
     * 建立连接时 的操作
     * 如果不是null，给前端发送信息，说明现在有其他连接上来了
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        synchronized (CommentEndpoint.class){
            if(Objects.nonNull(userSession)){
                try {
                    userSession.getBasicRemote().sendObject(250);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
            userSession=session;
        }
    }

    /**
     * 接收到客户端消息时的操作
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){
        synchronized (CommentEndpoint.class) {
            if ("reLogin".equals(message) && Objects.nonNull(userSession)) {
                try {
                    userSession.getBasicRemote().sendObject(250);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
            userSession=session;
        }
    }

    /**
     * 连接关闭时的操作
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        userSession=null;
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
