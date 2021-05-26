package чат;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class СервисЧата {
    private Set<ВебСокетЧата> чаты;

    public СервисЧата() {
        this.чаты = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void подписаться(ВебСокетЧата чат) {
        чаты.add(чат);
    }

    public void отписаться(ВебСокетЧата чат) {
        чаты.remove(чат);
    }

    public void отправитьСообщение(String сообщение) {
        чаты.forEach(чат -> чат.отправитьСообщение(сообщение));

    }
}
