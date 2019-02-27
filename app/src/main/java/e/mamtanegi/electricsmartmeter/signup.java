package e.mamtanegi.electricsmartmeter;

public class signup {
    private String username;
    private int meterno;
    private float phoneno;
    public signup() {

    }

    public signup(String username, float phoneno, int meterno) {
        this.username = username;
        this.phoneno = phoneno;
        this.meterno = meterno;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMeterno() {
        return meterno;
    }

    public void setMeterno(int meterno) {
        this.meterno = meterno;
    }

    public float getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(float phoneno) {
        this.phoneno = phoneno;
    }
}