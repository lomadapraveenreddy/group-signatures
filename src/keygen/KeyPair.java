package src.keygen;


public class KeyPair {
    private PublicKey pk;
    private SecretKey sk;

    public KeyPair(final PublicKey pk, final SecretKey sk) {
        this.pk = pk;
        this.sk = sk;
    }

    public PublicKey getPk() {
        return pk;
    }

    public SecretKey getSk() {
        return sk;
    }
    public void print(){
        System.out.println("------ Key Pair -------\n");
        System.out.println("** Private Key(x,y,z)  ( "+sk.getx()+' '+sk.gety()+' '+sk.getz()+" )\n");
        System.out.println("** Public Key \n order -- "+pk.getPairing().getG1().getOrder());
        
    }
}
