package src;

import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.Element;

import src.keygen.KeyGen;
import src.keygen.KeyPair;
import src.cert.GroupManagerCert;
import src.cert.GroupMemberCert;
import src.join.Join;
import src.keygen.PublicKey;
import src.keygen.SecretKey;
import src.revoc_keygen.RevocKeyGen;
import src.revoc_keygen.RevocKeyPair;
import src.revoc_keygen.RevocPublicKey;
import src.revoc_keygen.RevocSecretKey;
import src.sign.GroupSignature;
import src.utils.GSPairing;


public class GSMain {
    
    public static KeyPair generateKeyPair(GSPairing gsPairing, final int messageSize) {
        final SecretKey sk = KeyGen.createSecretKey(gsPairing.getPairing(), messageSize);
        final PublicKey pk = KeyGen.createPublicKey(gsPairing, sk);
        return new KeyPair(pk, sk);
    }

    public static RevocKeyPair generateRevocKeyPair(GSPairing gsPairing) {
        final RevocSecretKey sk = RevocKeyGen.createSecretKey(gsPairing);
        final RevocPublicKey pk = RevocKeyGen.createPublicKey(gsPairing, sk);
        return new RevocKeyPair(pk, sk);
    }
    public static GroupMemberCert joinRequest(GSPairing gsPairing,SecretKey sk,Element pReceived){
        return Join.joinRequest(gsPairing, sk, pReceived);
    }
    public static void main(String[] args) {
        GSPairing gsPairing = new GSPairing();
        final KeyPair managerKeyPair = generateKeyPair(gsPairing, 1);
        // final GroupManagerCert grpManagerCert = new GroupManagerCert(managerKeyPair);
        //managerKeyPair.print();
        final RevocKeyPair revocKeyPair = generateRevocKeyPair(gsPairing);
        //revocKeyPair.print();
        final Element pReceived= gsPairing.getg().powZn(gsPairing.getPairing().getZr().newRandomElement().getImmutable());
        final GroupMemberCert grpMemberCert = joinRequest(gsPairing, managerKeyPair.getSk(),pReceived);
        // grpManagerCert.addDetailsOfNewMember(gsPairing,pReceived);
        GroupSignature gs= GroupSignature.sign(gsPairing, grpMemberCert, gsPairing.getPairing().getZr().newRandomElement().getImmutable());
        System.out.println(gs.verify(gsPairing, managerKeyPair.getPk().getY()));
        // grpMemberCert.print();
        // byte[] hash='';
        // gsPairing.getPairing().getZr().newElement().setFromHash(hash, 0, hash.length);

    }    
}
