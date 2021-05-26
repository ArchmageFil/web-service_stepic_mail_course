package акки;

import lombok.Value;
import бд.БДсервис;
import бд.наборДанных.НаборДанныхПользователя;

import java.util.HashMap;
import java.util.Map;

@Value @SuppressWarnings("unused")
public class СервисУчеток {
    БДсервис бд;
    Map<String, НаборДанныхПользователя> входПоИдСессии;

    public СервисУчеток(БДсервис бд) {
        this.бд = бд;
        входПоИдСессии = new HashMap<>();
    }

    public void добавитьПользователя(НаборДанныхПользователя пользователь) {
        бд.создатьНовогоПользователя(пользователь);
    }

    public НаборДанныхПользователя получитьПрофильПоЛогину(String логин) {
        return бд.получитьПользователяПоЛогину(логин);
    }


    public void добавитьСессию(String сессия, НаборДанныхПользователя пользователь) {
        входПоИдСессии.put(сессия, пользователь);
    }

    public НаборДанныхПользователя получитьПрофильПоИдСессии(String сессия) {
        return входПоИдСессии.get(сессия);
    }

    public void удалитьСессию(String сессия) {
        входПоИдСессии.remove(сессия);
    }
}
