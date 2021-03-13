package src.revoc_keygen;

public class RevocKeyPair {
    private RevocPublicKey pk;
    private RevocSecretKey sk;

    public  RevocKeyPair(final RevocPublicKey pk, final RevocSecretKey sk) {
        this.pk = pk;
        this.sk = sk;
    }

    public RevocPublicKey getPk() {
        return pk;
    }

    public RevocSecretKey getSk() {
        return sk;
    }
    public void print(){
        System.out.println("------ Revoc Key Pair -------\n");
        System.out.println("** Private Key(x1,x2,x3,x4,x5)  ( "+sk.getx1()+' '+sk.getx2()+' '+sk.gety1()+' '+sk.gety2()+' '+sk.getz()+" )\n");
        System.out.println("** Public Key \n order -- "+pk.getPairing().getG1().getOrder());
    }
}
