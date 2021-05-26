package сервлет;

import акки.СервисУчеток;
import бд.наборДанных.НаборДанныхПользователя;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class СервлетРегистрации extends HttpServlet {
    private final СервисУчеток сервисУчеток;

    public СервлетРегистрации(СервисУчеток сервисУчеток) {
        this.сервисУчеток = сервисУчеток;
    }

    @Override
    protected void doPost(HttpServletRequest запрос, HttpServletResponse resp) {
        сервисУчеток.добавитьПользователя(new НаборДанныхПользователя(
                запрос.getParameter("login"),
                запрос.getParameter("password")));
    }
}
