package adventofcode23.lib;

public class TokenInformation {

    public int line;

    public String token;

    public int position;

    public TokenInformation(String token, int line, int position) {
        this.line = line;
        this.token = token;
        this.position = position;
    }


    public String toString() {
        return token+"@"+line+":"+position;
    }
}
