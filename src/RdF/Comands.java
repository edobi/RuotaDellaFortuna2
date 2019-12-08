package RdF;

public enum Comands {
    SPIN(1), SOLUT(2), VOWEL(3), NEW(4), JOIN(5), DISCONNECT(6), VIEW(7), STAT(8), CHANGE(9), OBSERVER(10), LOGIN(11),
    REGIST(12), JOLLY(13), YT(14), NFRASE(15), CFRASE(16), DFRASE(17), ACTIV(18), PSWRESE(19), CPROF(20), SLOAD(21), CPSW(22),
    CSVLOAD(23), CONS(24), END(25), PING(26);
    private int code;

    Comands(int i) {
        this.code = i;
    }

  public int getCode() {
        return code;
    }

}