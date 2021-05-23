package сервлет;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class СервлетНаЗеркало extends HttpServlet {

    public void doGet(HttpServletRequest запрос, HttpServletResponse ответ) throws ServletException, IOException {
        //
        String параметр = Stream.of(запрос.getParameterMap())
                .flatMap(x -> x.values().stream())
                .flatMap(Arrays::stream)
                .collect(Collectors.joining());

        ответ.setContentType("text/html;charset=utf-8");
        ответ.getWriter().print(параметр);
        ответ.setStatus(HttpServletResponse.SC_OK);
    }


}
