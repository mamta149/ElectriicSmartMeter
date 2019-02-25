package e.mamtanegi.electricsmartmeter;

public class signup {
   private String username,createpwd,confirmpwd,email;
   private int meterno;
    public signup(){

    }
    public signup(String username,String createpwd,String confirmpwd ,int meterno){
        this.username=username;
        this.createpwd=createpwd;
        this.confirmpwd=confirmpwd;
        this.meterno=meterno;
    }

    public String getUsername() {
        return username;
    }

    public String getCreatepwd() {
        return createpwd;
    }

    public String getConfirmpwd() {
        return confirmpwd;
    }

    public String getEmail() {
        return email;
    }

    public int getMeterno() {
        return meterno;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreatepwd(String createpwd) {
        this.createpwd = createpwd;
    }

    public void setConfirmpwd(String confirmpwd) {
        this.confirmpwd = confirmpwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMeterno(int meterno) {
        this.meterno = meterno;
    }
}
