package e.mamtanegi.electricsmartmeter;

public class signup{
    private double phoneno;
    public signup(float v) {

    }

    public signup(String username, double phoneno, int meterno) {
        this.phoneno = phoneno;

    }

    public double getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(float phoneno) {
        this.phoneno = phoneno;
    }
}