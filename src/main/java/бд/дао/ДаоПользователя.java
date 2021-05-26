package бд.дао;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import бд.наборДанных.НаборДанныхПользователя;

public class ДаоПользователя {
    private final Session сессия;

    public ДаоПользователя(Session сессия) {
        this.сессия = сессия;
    }

    @SuppressWarnings("UnusedDeclaration")
    public НаборДанныхПользователя получитьПользователя(long ид) {
        return сессия.get(
                НаборДанныхПользователя.class, ид);
    }

    public НаборДанныхПользователя получитьПользователя(String логин) {
        @SuppressWarnings("deprecation")
        Criteria критерии = сессия.createCriteria(НаборДанныхПользователя.class);

        return (НаборДанныхПользователя)
                критерии.add(Restrictions.eq("логин", логин)).uniqueResult();
    }

    public long добавитьПользователя(НаборДанныхПользователя пользователь) {
        return (long) сессия.save(пользователь);
    }
}
