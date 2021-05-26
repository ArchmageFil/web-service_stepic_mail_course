package логика;

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

        ResourceHandler обработчикСтатики = new ResourceHandler();
        обработчикСтатики.setResourceBase("resources");

        HandlerList обработчики = new HandlerList(обработчикСтатики, обработчик);


        Server сервер = new Server(8080);
        сервер.setHandler(обработчики);
        сервер.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        сервер.join();


    }
}
