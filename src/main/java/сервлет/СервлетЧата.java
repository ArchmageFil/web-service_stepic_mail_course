package сервлет;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import чат.ВебСокетЧата;
import чат.СервисЧата;

@WebServlet(name = "СервлетЧата", urlPatterns = {"/chat"})
public class СервлетЧата extends WebSocketServlet {
    long автозакрытие = 1000 * 60 * 2; // 2 минуты
    СервисЧата сервисОтправки;

    public СервлетЧата() {
        this.сервисОтправки = new СервисЧата();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(автозакрытие);
        factory.setCreator((req, resp) -> new ВебСокетЧата(сервисОтправки));
    }
}