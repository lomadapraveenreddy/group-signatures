package src;

import it.unisa.dia.gas.jpbc.Pairing;
import src.keygen.KeyGen;
import src.keygen.KeyPair;
import src.keygen.PublicKey;
import src.keygen.SecretKey;

public class GSMain {
    public static KeyPair generateKeyPair(final int messageSize){
        final Pairing pairing = KeyGen.createPairing();
        final SecretKey sk = KeyGen.createSecretKey(pairing, messageSize);
        final PublicKey pk = KeyGen.createPublicKey(pairing, sk);
        return new KeyPair(pk, sk);
    }
    public static void main(String[] args){
        generateKeyPair(1).print();
    }
}
