package src.revoc_keygen;

import it.unisa.dia.gas.jpbc.Pairing;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.field.z.ZrElement;
import src.utils.GSPairing;

public class RevocKeyGen {


    public static RevocSecretKey createSecretKey(final GSPairing gsPairing) {
        final Pairing pairing=gsPairing.getPairing();
        return new RevocSecretKey(
                (ZrElement) pairing.getZr().newRandomElement().getImmutable(),
                (ZrElement) pairing.getZr().newRandomElement().getImmutable(),
                (ZrElement) pairing.getZr().newRandomElement().getImmutable(),
                (ZrElement) pairing.getZr().newRandomElement().getImmutable(),
                (ZrElement) pairing.getZr().newRandomElement().getImmutable());
    }

    public static RevocPublicKey createPublicKey(final GSPairing gsPairing, final RevocSecretKey sk) {
        final Element g1 = gsPairing.getPairing().getG1().newRandomElement().getImmutable();
        final Element g2 = gsPairing.getPairing().getG1().newRandomElement().getImmutable();
        
        // System.out.println("in create h --");
        // System.out.println(h);
        final Element c = g1.powZn(sk.getx1()).mul(g2.powZn(sk.getx2()));
        final Element d= g1.powZn(sk.gety1()).mul(g2.powZn(sk.gety2()));
        final Element h= g1.powZn(sk.getz());
        
        return new RevocPublicKey(gsPairing.getPairing(),g1,g2,c,d,h);
    }

}
