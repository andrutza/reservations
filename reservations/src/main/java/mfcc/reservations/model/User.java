package mfcc.reservations.model;


public class User extends Model{

    private String username;
    private String password;
    private String name;

    public User(Integer id, String username, String password, String name) {
        super(id, ModelType.USER);
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
