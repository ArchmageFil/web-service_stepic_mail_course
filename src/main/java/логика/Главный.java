package логика;

import org.eclipse.jetty.server.Handler;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import акки.СервисУчетокПретворение;
import бд.БДсервисПретворение;
import бд.наборДанных.НаборДанныхПользователя;
import логика.базовый.БДсервис;
import логика.базовый.СервисУчеток;
import сервлет.СервлетАвторизации;
import сервлет.СервлетНаЗеркало;
import сервлет.СервлетРегистрации;
import сервлет.СервлетЧата;

public class Главный {
    public static final String auth = "/sign" + "in";

    public static void main(String[] args) throws Exception {
        БДсервис бДсервис = new БДсервисПретворение();
        СервисУчеток сервисУчеток = new СервисУчетокПретворение(бДсервис);

        сервисУчеток.добавитьПользователя(new НаборДанныхПользователя("admin", "admin"));
        сервисУчеток.добавитьПользователя(new НаборДанныхПользователя("test", "test"));

        ServletContextHandler обработчик = new ServletContextHandler(ServletContextHandler.SESSIONS);
        обработчик.addServlet(new ServletHolder(new СервлетНаЗеркало()), "/mirror");
        обработчик.addServlet(new ServletHolder(new СервлетРегистрации(сервисУчеток)), "/signup");
        обработчик.addServlet(new ServletHolder(new СервлетАвторизации(сервисУчеток)), auth);
        обработчик.addServlet(new ServletHolder(new СервлетЧата()), "/chat");

        ResourceHandler обработчикСтатики = new ResourceHandler();
        обработчикСтатики.setResourceBase("resources");

        HandlerList обработчики = new HandlerList();
        обработчики.setHandlers(new Handler[]{обработчикСтатики, обработчик});

        Server сервер = new Server(8080);
        сервер.setHandler(обработчики);
        сервер.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        сервер.join();

    }
}
