package step.learning;

public class User {
    private int id; // Уникальный идентификатор пользователя
    private String firstName; // Имя
    private String lastName; // Фамилия
    private String email; // Email
    private String password; // Хеш пароля
    // Другие поля, например роль или статус

    public User(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Геттеры и сеттеры...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Можно добавить другие методы, например, для проверки роли пользователя
}

