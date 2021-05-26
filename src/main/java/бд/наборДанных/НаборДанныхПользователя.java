package бд.наборДанных;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "пользователи")
public class НаборДанныхПользователя implements Serializable {
    private static final long serialVersionUID = -870_668_971_432_613_000_1L;
    @Id
    @Column(name = "ид")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ид;
    @Column(name = "логин", unique = true, updatable = false)
    private String логин;
    @Column(name = "пароль")
    private String пароль;
    @Column(name = "элПочта")
    private String элПочта;

    public НаборДанныхПользователя() {
    }

    public НаборДанныхПользователя(String логин, String пароль) {
        this.логин = логин;
        this.пароль = пароль;
    }
}
