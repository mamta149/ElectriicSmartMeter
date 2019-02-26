package e.mamtanegi.electricsmartmeter;

public class signup {
    private String username;
    private int meterno, phoneno,id;

    public signup() {

    }

    public signup(String username,int id, int phoneno, int meterno) {
        this.username = username;
        this.phoneno = phoneno;
        this.meterno = meterno;
        this.id=id;
    }
public int getid(){
        return id;
}
    public String getUsername() {
        return username;
    }

    public int getMeterno() {
        return meterno;
    }

    public int getPhoneno() {
        return phoneno;
    }
}