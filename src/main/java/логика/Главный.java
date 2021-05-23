package логика;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import сервлет.СервлетНаЗеркало;

public class Главный {
    public static void main(String[] args) throws Exception {
        СервлетНаЗеркало сервлет = new СервлетНаЗеркало();
        ServletContextHandler обработчик = new ServletContextHandler(ServletContextHandler.SESSIONS);
        обработчик.addServlet(new ServletHolder(сервлет), "/mirror");

        Server сервер = new Server(8080);
        сервер.setHandler(обработчик);

        сервер.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        сервер.join();

        //Stream.of(pageVariables).flatMap(x -> x.values().stream()).flatMap(Arrays::stream).forEach(System.out::print);
    }
}
