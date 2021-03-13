package src.join;

import src.cert.GroupMemberCert;
import src.keygen.SecretKey;
import src.utils.GSPairing;
import it.unisa.dia.gas.jpbc.Element;

public class Join {
    public static GroupMemberCert joinRequest(final GSPairing gsPairing,final SecretKey sk,final Element pReceived){
        final Element r=gsPairing.getPairing().getZr().newRandomElement().getImmutable();
        final Element a=gsPairing.getg().powZn(r);
        final Element b=a.powZn(sk.gety());
        final Element rxy = r.mul((Element)sk.getx()).mul((Element)sk.gety());
        final Element prxy = pReceived.powZn(rxy);
        final Element ax= a.powZn(sk.getx());
        
        final Element c= ax.mul(prxy);
        return new GroupMemberCert(a,b,c,pReceived);
    }
}
