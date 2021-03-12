package src.revoc_keygen;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;


public class RevocPublicKey {

    private Pairing pairing;
    private Element h;
    private Element y1;
    private Element y2;
    private Element y3;

    public  RevocPublicKey(final Pairing pairing, final Element h, final Element y1, final Element y2, final Element y3) {
        this.pairing = pairing;
        this.h =h;
        this.y1 =y1;
        this.y2 =y2;
        this.y3=y3;
    }

    public Pairing getPairing() {
        return pairing;
    }

    public Element geth() {
        return h;
    }

    public Element gety1() {
        return y1;
    }
    public Element gety2() {
        return y2;
    }
    public Element gety3() {
        return y3;
    }

}
