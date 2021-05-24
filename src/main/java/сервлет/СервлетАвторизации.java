package сервлет;

import com.google.gson.Gson;
import акки.ПрофильПользователя;
import акки.СервисУчеток;

import javax.servlet.ServletException;
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
    protected void doPost(HttpServletRequest запрос, HttpServletResponse ответ) throws ServletException, IOException {

        ПрофильПользователя пользователь = сервисУчеток.получитьПрофильПоЛогину(запрос.getParameter("login"));
        boolean успех = (пользователь != null) && (запрос.getParameter("password")
                .equals(пользователь.getПароль()));
        ответ.setContentType("text/html;charset=utf-8");
        if (успех){
//            String ответВЖисоне = new Gson().toJson(пользователь);
//            ответ.getWriter().print(ответВЖисоне);
            ответ.getWriter().print("Authorized: " + пользователь.getЛогин());
            ответ.setStatus(HttpServletResponse.SC_OK);
        } else {
            ответ.getWriter().print("Unauthorized");
            ответ.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
