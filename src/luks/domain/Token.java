package luks.domain;

public class Token {

    private int access;
    private String token;

    public Token(int access, String token) {
        setAccess(access);
        setToken(token);
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
