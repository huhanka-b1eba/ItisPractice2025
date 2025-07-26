package aa.tulybaev.user.dto;

public class UserDTO {

    private final String uuid;

    private final String name;

    public UserDTO(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
