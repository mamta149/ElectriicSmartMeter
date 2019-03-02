package e.mamtanegi.electricsmartmeter;

public class UserLoginData {
    String uname;
    double phonenumber;
    float meterno;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public double getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(double phonenumber) {
        this.phonenumber = phonenumber;
    }

    public float getMeterno() {
        return meterno;
    }

    public void setMeterno(float meterno) {
        this.meterno = meterno;
    }

    public UserLoginData() {
    }

    public UserLoginData(String uname, double phonenumber, float meterno) {
        this.uname = uname;
        this.phonenumber = phonenumber;
        this.meterno = meterno;
    }

}
