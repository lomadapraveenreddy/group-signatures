package src.sign;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.field.z.ZrElement;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import src.join.GrpMemberDetailsToBeStored;
import src.cert.GroupMemberCert;
import src.utils.HashSHA256;
import src.revoc_keygen.RevocKeyPair;
import src.revoc_keygen.RevocPublicKey;
import src.revoc_keygen.RevocSecretKey;
import src.utils.GSPairing;

public class GroupSignature {
    private Element aBar;
    private Element bBar;
    private Element cCap;
    private boolean hashFormed;
    private Element u1;
    private Element u2;
    private Element e;
    private Element v;
    private Element message;

    public GroupSignature(Element message, Element aBar, Element bBar, Element cCap) {
        this.message = message;
        this.aBar = aBar;
        this.bBar = bBar;
        this.cCap = cCap;
        this.hashFormed = false;
    }

    public GroupSignature(Element message, Element aBar, Element bBar, Element cCap, Element u1, Element u2, Element e,
            Element v) {
        this.hashFormed = true;
        this.message = message;
        this.aBar = aBar;
        this.bBar = bBar;
        this.cCap = cCap;
        this.u1 = u1;
        this.u2 = u2;
        this.e = e;
        this.v = v;
    }

    public static GroupSignature sign(GSPairing gsPairing, GroupMemberCert grpMemberCert, RevocPublicKey revocPublicKey,
            Element pSent, Element message) {
        final Element r = gsPairing.getPairing().getZr().newRandomElement().getImmutable();
        final Element r1 = gsPairing.getPairing().getZr().newRandomElement().getImmutable();
        final Element aBar = grpMemberCert.geta().powZn(r1);
        final Element bBar = grpMemberCert.getb().powZn(r1);
        final Element cCap = grpMemberCert.getc().powZn(r1.mulZn(r));
        final Element k = (Element)gsPairing.getPairing().getZr().newRandomElement().getImmutable();
        final Element u1 = revocPublicKey.getg1().powZn(k);
        final Element u2 = revocPublicKey.getg2().powZn(k);
        final Element encryptValue = pSent;//gsPairing.getPairing().getG1().newRandomElement().getImmutable();
        System.out.println("\n-- value of p to be encrypted --\n\n"+encryptValue);
        final Element e = (revocPublicKey.geth().powZn(k)).mul(encryptValue);//gsPairing.getPairing().pairing(pSent,gsPairing.getg())
        //System.out.println("\n--- P while signing---\n"+gsPairing.getPairing().pairing(pSent,gsPairing.getg()));
        try {
            final BigInteger alpha = new BigInteger(1, HashSHA256.getHash(u1.add(u2).add(e).toString()));
            final Element v = (revocPublicKey.getc().powZn(k)).mul(revocPublicKey.getd().powZn(k.mul(alpha)));
            return new GroupSignature(message, aBar, bBar, cCap, u1, u2, e, v);
        } catch (NoSuchAlgorithmException e1) {
            System.out.println("---- error in calculating hash while signing -----");

        }
        return new GroupSignature(message, aBar, bBar, cCap);
    }

    public boolean verify(GSPairing gsPairing, RevocSecretKey revocSecretKey, Element Y) {
        if (hashFormed) {
            final boolean a=gsPairing.getPairing().pairing(aBar, Y)
                    .isEqual(gsPairing.getPairing().pairing(gsPairing.getg(), bBar));
            boolean isValid=false;
            try {
                final BigInteger alpha = new BigInteger(1, HashSHA256.getHash(u1.add(u2).add(e).toString()));
                final Element u1x1u2x2= u1.powZn(revocSecretKey.getx1()).mul(u2.powZn(revocSecretKey.getx2()));
                final Element u1y1u2y2= u1.powZn(revocSecretKey.gety1()).mul(u2.powZn(revocSecretKey.gety2()));
                isValid= a && u1x1u2x2.mul(u1y1u2y2.pow(alpha)).isEqual(v);
                if(isValid){
                    System.out.println("---- value of P from verify is -----\n\n"+e.div((u1.powZn(revocSecretKey.getz()))));
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println("---- error in calculating hash while verifying -----");
            }

            return isValid;
        } else {
            return gsPairing.getPairing().pairing(aBar, Y)
                    .isEqual(gsPairing.getPairing().pairing(gsPairing.getg(), bBar));
        }
    }
    public String getUserFromP(GSPairing gsPairing,RevocSecretKey revocSecretKey,Element pSent,List<GrpMemberDetailsToBeStored> list){
        for (int i = 0; i < list.size(); i++) {
            if( gsPairing.getPairing().pairing(pSent,gsPairing.getg()).isEqual(list.get(i).getpCalculated())){
                return String.valueOf(i);
            }
        }
        return "Invalid group member";
        
    }
    public Element message() {
        return message;
    }

    public Element getaBar() {
        return aBar;
    }

    public Element getbBar() {
        return bBar;
    }

    public Element getcCap() {
        return cCap;
    }
}
