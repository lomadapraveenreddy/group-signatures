package src.revoc_keygen;

import it.unisa.dia.gas.plaf.jpbc.field.z.ZrElement;


public class RevocSecretKey {

    private ZrElement x1;
    private ZrElement x2;
    private ZrElement y1;
    private ZrElement y2;
    private ZrElement z;

    public RevocSecretKey(final ZrElement x1, final ZrElement x2, final ZrElement y1, final ZrElement y2,
            final ZrElement z) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z = z;

    }

    public ZrElement getx1() {
        return x1;
    }

    public ZrElement getx2() {
        return x2;
    }

    public ZrElement gety1() {
        return y1;
    }

    public ZrElement gety2() {
        return y2;
    }

    public ZrElement getz() {
        return z;
    }
}
