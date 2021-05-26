package бд;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import бд.дао.ДаоПользователя;
import бд.наборДанных.НаборДанныхПользователя;
import логика.базовый.БДсервис;

public class БДсервисПретворение implements БДсервис {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "validate";//validate

    private final SessionFactory фабрикаСессий;

    public БДсервисПретворение() {
        фабрикаСессий = создатьФабрикуСессий(КонфигурацияMySQL());
    }

    public void создатьНовогоПользователя(НаборДанныхПользователя пользователь) {
        try (Session сессия = фабрикаСессий.openSession()) {
            Transaction транзакция = сессия.beginTransaction();
            ДаоПользователя дао = new ДаоПользователя(сессия);

            НаборДанныхПользователя п = получитьПользователяПоЛогину(пользователь.getЛогин());
            if (п != null) return;

            long id = дао.добавитьПользователя(пользователь);
            транзакция.commit();
            транзакция = сессия.beginTransaction();
            java.util.logging.Logger.getGlobal().info("добавлен: " + пользователь + "ИД: " + id);
            id = дао.получитьПользователя(пользователь.getЛогин()).getИд();
            транзакция.commit();
            java.util.logging.Logger.getGlobal().info("добавлен: " + пользователь + "ИД: " + id);
            пользователь.setИд(id);
        }

    }

    public НаборДанныхПользователя получитьПользователяПоЛогину(String логин) {
        try (Session сессия = фабрикаСессий.openSession()) {
            //Transaction транзакция = сессия.beginTransaction();
            ДаоПользователя дао = new ДаоПользователя(сессия);
            return дао.получитьПользователя(логин);
        }
    }


    @SuppressWarnings("NonAsciiCharacters")
    private Configuration КонфигурацияMySQL() {
        Configuration конфиг = new Configuration();
        конфиг.addAnnotatedClass(НаборДанныхПользователя.class);
        конфиг.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        конфиг.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        конфиг.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/my" + "db" + "test");
        конфиг.setProperty("hibernate.connection.username", "admin");
        конфиг.setProperty("hibernate.connection.password", "admin");
        конфиг.setProperty("hibernate.show_sql", hibernate_show_sql);
        конфиг.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return конфиг;
    }

    private static SessionFactory создатьФабрикуСессий(Configuration конфиг) {
        StandardServiceRegistryBuilder сб = new StandardServiceRegistryBuilder();
        сб.applySettings(конфиг.getProperties());
        ServiceRegistry ср = сб.build();
        return конфиг.buildSessionFactory(ср);
    }
}
