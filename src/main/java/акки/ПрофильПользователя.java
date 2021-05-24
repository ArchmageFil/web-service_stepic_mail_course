package акки;

public class ПрофильПользователя {
    private final String логин;
    private final String пароль;
    private final String элПочта;

    public ПрофильПользователя(String логин, String пароль, String элПочта) {
        this.логин = логин;
        this.пароль = пароль;
        this.элПочта = элПочта;
    }

    public ПрофильПользователя(String логин) {
        this.логин = логин;
        this.пароль = логин;
        this.элПочта = логин;
    }

    public String getЛогин() {
        return логин;
    }

    public String getПароль() {
        return пароль;
    }

    public String getЭлПочта() {
        return элПочта;
    }
}
