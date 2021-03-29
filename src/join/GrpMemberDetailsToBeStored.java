package src.join;

import it.unisa.dia.gas.jpbc.Element;
import src.utils.GSPairing;

public class GrpMemberDetailsToBeStored {
    private Element pReceived;
    private Element pCalculated;
    
    public GrpMemberDetailsToBeStored(GSPairing gsPairing,Element pReceived){
        this.pReceived= pReceived;
        this.pCalculated= gsPairing.getPairing().pairing(pReceived,gsPairing.getg());
        System.out.println("*** P Calculated ***\n"+this.pCalculated+"\n\n");
    }
    public Element getpReceived(){
        return pReceived;
    }

    public Element getpCalculated(){
        return pCalculated;
    }
}
