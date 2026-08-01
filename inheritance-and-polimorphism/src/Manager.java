public class Manager extends Employee {
    
    private String login;
    private String password;
    private double comission;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getComission() {
        return comission;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

    @Override /* this overrides Employee getCode() function and uses this one, super is the father's function */
    public String getCode() {
        return "MANAGER" + super.getCode();
    }

}
