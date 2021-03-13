package src.sign;

import it.unisa.dia.gas.jpbc.Element;
import src.cert.GroupMemberCert;
import src.utils.GSPairing;

public class GroupSignature {
    private Element aBar;
    private Element bBar;
    private Element cCap;
    private Element message;
    public GroupSignature(Element message,Element aBar, Element bBar,Element cCap){
        this.message= message;
        this.aBar=aBar;
        this.bBar= bBar;
        this.cCap = cCap;
    }
    public static GroupSignature sign(GSPairing gsPairing,GroupMemberCert grpMemberCert,Element message){
            final Element r=gsPairing.getPairing().getZr().newRandomElement().getImmutable();
            final Element r1=gsPairing.getPairing().getZr().newRandomElement().getImmutable();
            final Element aBar=grpMemberCert.geta().powZn(r1); 
            final Element bBar=grpMemberCert.getb().powZn(r1);
            final Element cCap=grpMemberCert.getc().powZn(r1.mulZn(r));
            return new GroupSignature(message,aBar,bBar,cCap);
    }
    public boolean verify(GSPairing gsPairing,Element Y){
        return gsPairing.getPairing().pairing(aBar,Y).isEqual(gsPairing.getPairing().pairing(gsPairing.getg(), bBar));
    }
    public Element message() {
        return message;
    }
    public Element getaBar(){
        return aBar;
    }
    public Element getbBar(){
        return bBar;
    }
    public Element getcCap(){
        return cCap;
    }
}
