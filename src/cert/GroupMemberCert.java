package src.cert;

import it.unisa.dia.gas.jpbc.Element;

public class GroupMemberCert {
    private final Element a;
    private final Element b;
    private final Element c;
    private final Element pSent;

    public GroupMemberCert(Element a, Element b, Element c,Element pSent) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.pSent= pSent;
    }
    public void print(){
        System.out.println("---- Grp Member Cert ----");
        System.out.println("** a  "+a);
        System.out.println("** b  "+b);
        System.out.println("** c  "+c);
    }
    public Element geta() {
        return a;
    }
    public Element getb() {
        return b;
    }
    public Element getc() {
        return c;
   }
   public Element getpSent() {
    return pSent;
}
}
