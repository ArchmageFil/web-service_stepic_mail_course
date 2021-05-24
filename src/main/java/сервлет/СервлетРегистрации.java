package сервлет;

import акки.ПрофильПользователя;
import акки.СервисУчеток;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class СервлетРегистрации extends HttpServlet {
    private final СервисУчеток сервисУчеток;

    public СервлетРегистрации(СервисУчеток сервисУчеток) {
        this.сервисУчеток = сервисУчеток;
    }

    @Override
    protected void doPost(HttpServletRequest запрос, HttpServletResponse resp) throws ServletException, IOException {
        сервисУчеток.добавитьПользователяПоЛогину(new ПрофильПользователя(
                запрос.getParameter("login"),
                запрос.getParameter("password"),
                запрос.getParameter("login")));
    }
}
