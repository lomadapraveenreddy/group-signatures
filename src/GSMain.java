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
        final GroupManagerCert grpManagerCert = new GroupManagerCert(managerKeyPair);
        //managerKeyPair.print();
        final RevocKeyPair revocKeyPair = generateRevocKeyPair(gsPairing);
        //revocKeyPair.print();
        final Element pSent= gsPairing.getg().powZn(gsPairing.getPairing().getZr().newRandomElement().getImmutable());
        System.out.println("** P sent **\n"+pSent+"\n\n");
        final GroupMemberCert grpMemberCert = joinRequest(gsPairing, managerKeyPair.getSk(),pSent);
         grpManagerCert.addDetailsOfNewMember(gsPairing,pSent);
        GroupSignature gs= GroupSignature.sign(gsPairing, grpMemberCert,revocKeyPair.getPk(),pSent, gsPairing.getPairing().getZr().newRandomElement().getImmutable());
        System.out.println("-------\n signature verfied:"+gs.verify(gsPairing,revocKeyPair.getSk(), managerKeyPair.getPk().getY()));
        grpManagerCert.grpMemberDetailsStored.get(0).getpCalculated();
        System.out.println("\n-- P stored--\n"+grpManagerCert.grpMemberDetailsStored.get(0).getpCalculated());
        
        System.out.println("\n\n---------\n P verified: "+gs.verifyPFromSign(revocKeyPair.getSk(),gsPairing.getPairing().pairing(pSent, gsPairing.getg())));
        

    }    
}
