package part05.lesson17.task02;

public class DummyUser {

    /* Поля объекта, которые повторяют таблицу USER и которые используем для поведения - when(mock).thenReturn(value) */
    int userId;
    String name;
    String birthday;
    String loginId;
    String city;
    String email;
    String description;


    public DummyUser() {
    }

    /* Сеттеры класса */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* Геттеры класса */
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }
}
