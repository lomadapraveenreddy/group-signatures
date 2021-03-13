package src.cert;

import java.util.ArrayList;
import java.util.List;

import it.unisa.dia.gas.jpbc.Element;
import src.join.GrpMemberDetailsToBeStored;
import src.keygen.KeyPair;
import src.utils.GSPairing;

public class GroupManagerCert {
    public KeyPair keyPair;
    public List<GrpMemberDetailsToBeStored> grpMemberDetailsStored=new ArrayList<>();;
    public GroupManagerCert(KeyPair keyPair){
        this.keyPair= keyPair;
    }

    public void addDetailsOfNewMember(GSPairing gsPairing,Element pReceived ){
        grpMemberDetailsStored.add(new GrpMemberDetailsToBeStored(gsPairing,pReceived));

    }
}
