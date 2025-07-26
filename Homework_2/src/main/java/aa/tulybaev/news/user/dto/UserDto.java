package aa.tulybaev.news.user.dto;

public class UserDto {

    private final Integer id;
    private final String username;

    public UserDto(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}

