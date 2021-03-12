package src;

import it.unisa.dia.gas.jpbc.Pairing;
import src.keygen.KeyGen;
import src.keygen.KeyPair;
import src.keygen.PublicKey;
import src.keygen.SecretKey;
import src.revoc_keygen.RevocKeyGen;
import src.revoc_keygen.RevocKeyPair;
import src.revoc_keygen.RevocPublicKey;
import src.revoc_keygen.RevocSecretKey;
import src.utils.GSPairing;

public class GSMain {
     
    public static KeyPair generateKeyPair(Pairing pairing,final int messageSize){
        final SecretKey sk = KeyGen.createSecretKey(pairing, messageSize);
        final PublicKey pk = KeyGen.createPublicKey(pairing, sk);
        return new KeyPair(pk, sk);
    }

    public static RevocKeyPair generateRevocKeyPair(GSPairing gsPairing){
        final RevocSecretKey sk = RevocKeyGen.createSecretKey(gsPairing);
        final RevocPublicKey pk = RevocKeyGen.createPublicKey(gsPairing, sk);
        return new RevocKeyPair(pk, sk);
    }
    public static void main(String[] args){
        
        GSPairing gsPairing = new GSPairing();
        final KeyPair managerKeyPair = generateKeyPair(gsPairing.getPairing(),1);
         managerKeyPair.print();
        final RevocKeyPair revocKeyPair =  generateRevocKeyPair(gsPairing); 
        revocKeyPair.print();

        
    }
}
