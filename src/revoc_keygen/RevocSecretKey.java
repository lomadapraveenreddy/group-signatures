package src.revoc_keygen;

import it.unisa.dia.gas.plaf.jpbc.field.z.ZrElement;


public class RevocSecretKey {

    private ZrElement x1;
    private ZrElement x2;
    private ZrElement x3;
    private ZrElement x4;
    private ZrElement x5;

    public RevocSecretKey(final ZrElement x1, final ZrElement x2, final ZrElement x3, final ZrElement x4,
            final ZrElement x5) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;

    }

    public ZrElement getx1() {
        return x1;
    }

    public ZrElement getx2() {
        return x2;
    }

    public ZrElement getx3() {
        return x3;
    }

    public ZrElement getx4() {
        return x4;
    }

    public ZrElement getx5() {
        return x5;
    }
}
