package src.utils;

import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import it.unisa.dia.gas.jpbc.PairingParameters;

public class GSPairing {
    private Pairing pairing;
    private Element g;
    private Element gt;

    public  Pairing createPairing() {
        int rBits = 160;
        int qBits = 512;

        final TypeACurveGenerator pairingGenerator = new TypeACurveGenerator(rBits, qBits);
        final PairingParameters params = pairingGenerator.generate();
        return PairingFactory.getPairing(params);
    }

    public GSPairing() {
        this.pairing = createPairing();
        this.g=pairing.getG1().newRandomElement().getImmutable();
        this.gt=pairing.getGT().newRandomElement().getImmutable();
    }

    public Pairing getPairing(){
        return pairing;
    }

    public Element getg(){
        return g;
    }
    public Element getgt(){
        return gt;
    }
}
