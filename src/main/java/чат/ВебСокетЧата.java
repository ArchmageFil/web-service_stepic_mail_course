package чат;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class ВебСокетЧата {
    Session сессия;
    СервисЧата сервисОтправки;

    public ВебСокетЧата(СервисЧата сервисОтправки) {
        this.сервисОтправки = сервисОтправки;
    }

    @OnWebSocketConnect
    public void приЗапуске(Session сессия) {
        this.сессия = сессия;
        сервисОтправки.подписаться(this);
        java.util.logging.Logger.getGlobal().info("Чат стартовал" + сессия);
    }

    @OnWebSocketMessage
    public void сообщение(String сообщение) {
        сервисОтправки.отправитьСообщение(сообщение);
        java.util.logging.Logger.getGlobal().info(сообщение);
    }

    @OnWebSocketClose
    public void закрыться(int кодСтатуса, String причина) {
        сервисОтправки.отписаться(this);
        java.util.logging.Logger.getGlobal().info(причина + "Статус: " + кодСтатуса);
    }

    void отправитьСообщение(String сообщение) {
        try {
            сессия.getRemote().sendString(сообщение);
        } catch (IOException искл) {
            java.util.logging.Logger.getGlobal().info(искл.getMessage() + "\r\n" + искл.getCause());
        }
    }
}
