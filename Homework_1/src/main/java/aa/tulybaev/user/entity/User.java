package aa.tulybaev.user.entity;

public class User {

    private String uuid;

    private final String name;

    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String uuid, String name, String password) {
        this.uuid = uuid;
        this.name = name;
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
