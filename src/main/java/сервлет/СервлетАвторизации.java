package сервлет;

import бд.наборДанных.НаборДанныхПользователя;
import логика.базовый.СервисУчеток;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class СервлетАвторизации extends HttpServlet {
    private final СервисУчеток сервисУчеток;

    public СервлетАвторизации(СервисУчеток сервисУчеток) {
        this.сервисУчеток = сервисУчеток;
    }

    @Override
    protected void doPost(HttpServletRequest запрос, HttpServletResponse ответ) throws IOException {

        НаборДанныхПользователя пользователь = сервисУчеток.получитьПрофильПоЛогину(запрос.getParameter("login"));
        boolean успех = (пользователь != null) && (запрос.getParameter("password")
                .equals(пользователь.getПароль()));
        ответ.setContentType("text/html;charset=utf-8");
        if (успех) {
            ответ.getWriter().print("Authorized: " + пользователь.getЛогин());
            ответ.setStatus(HttpServletResponse.SC_OK);
        } else {
            ответ.getWriter().print("Unauthorized");
            ответ.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
