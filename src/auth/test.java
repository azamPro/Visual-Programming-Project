package auth;

import auth.AdminLogin;
import auth.Login;
import auth.Register;
import auth.Entry;

public class test {
    String un, pass, mail;

    public test(String u, String p, String m) {
        un = u;
        pass = p;
        mail = m;
    }

    public void setData(String u, String p, String m) {
        un = u;
        pass = p;
        mail = m;
    }

    public void setUserName(String a) {
        un = a;
    }

    public void setPwd(String a) {
        pass = a;
    }

    public void setMail(String a) {
        mail = a;
    }

    String getMail() {
        return mail;
    }

    String getPwd() {
        return pass;
    }

    String getUn() {
        return un;
    }
}
