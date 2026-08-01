public class User {
    protected String name;
    protected String email;
    protected String password;
    protected boolean administrator;

    public User(String name, String email, String password, boolean administrator) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.administrator = administrator;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Common methods
    public void login() {
        System.out.println(name + " logged in.");
    }

    public void logoff() {
        System.out.println(name + " logged off.");
    }

    public void updateData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}