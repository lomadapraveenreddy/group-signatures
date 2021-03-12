package src.revoc_keygen;

import it.unisa.dia.gas.jpbc.Pairing;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.field.z.ZrElement;
import src.revoc_keygen.RevocKeyPair;
import src.utils.GSPairing;

public class RevocKeyGen {


    public static RevocSecretKey createSecretKey(final GSPairing gsPairing) {
        final Pairing pairing=gsPairing.getPairing();
        return new RevocSecretKey((ZrElement) pairing.getZr().newRandomElement().getImmutable(),
                (ZrElement) pairing.getZr().newRandomElement().getImmutable(),(ZrElement) pairing.getZr().newRandomElement().getImmutable(),(ZrElement) pairing.getZr().newRandomElement().getImmutable(),(ZrElement) pairing.getZr().newRandomElement().getImmutable());
    }

    public static RevocPublicKey createPublicKey(final GSPairing gsPairing, final RevocSecretKey sk) {
        final Element h = gsPairing.getPairing().getG1().newRandomElement().getImmutable();
        final Element gt = gsPairing.getgt();
        // System.out.println("in create h --");
        // System.out.println(h);
        final Element y1 = h.powZn(sk.getx2()).mulZn(gt.powZn(sk.getx1()));
        final Element y2= h.powZn(sk.getx4()).mulZn(gt.powZn(sk.getx3()));
        final Element y3= gt.powZn(sk.getx5());
        
        return new RevocPublicKey(gsPairing.getPairing(),h,y1,y2,y3);
    }

}
