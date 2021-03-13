package src.revoc_keygen;

import java.math.BigInteger;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;


public class RevocPublicKey {

    private Pairing pairing;
    private BigInteger q;
    private Element g1;
    private Element g2;
    private Element c;
    private Element d;
    private Element h;

    public  RevocPublicKey(final Pairing pairing, final Element g1, final Element g2, final Element c, final Element d,final Element h) {
        this.pairing = pairing;
        this.q = pairing.getG1().getOrder();
        this.g1 =g1;
        this.g2 =g2;
        this.c =c;
        this.d =d;
        this.h=h;
    }

    public Pairing getPairing() {
        return pairing;
    }
    public BigInteger getOrder() {
        return q;
    }
    public Element getg1() {
        return g1;
    }
    public Element getg2() {
        return g2;
    }

    public Element getc() {
        return c;
    }
    public Element getd() {
        return d;
    }
    public Element geth() {
        return h;
    }

}
