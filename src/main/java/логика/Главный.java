package логика;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import акки.ПрофильПользователя;
import акки.СервисУчеток;
import сервлет.СервлетАвторизации;
import сервлет.СервлетНаЗеркало;
import сервлет.СервлетРегистрации;

public class Главный {
    public static void main(String[] args) throws Exception {
        СервисУчеток сервисУчеток = new СервисУчеток();

        сервисУчеток.добавитьПользователяПоЛогину(new ПрофильПользователя("admin"));
        сервисУчеток.добавитьПользователяПоЛогину(new ПрофильПользователя("test"));

        ServletContextHandler обработчик = new ServletContextHandler(ServletContextHandler.SESSIONS);
        обработчик.addServlet(new ServletHolder(new СервлетНаЗеркало()), "/mirror");
        обработчик.addServlet(new ServletHolder(new СервлетРегистрации(сервисУчеток)), "/signup");
        обработчик.addServlet(new ServletHolder(new СервлетАвторизации(сервисУчеток)), "/signin");

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
