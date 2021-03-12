package src.keygen;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.field.z.ZrElement;


import java.util.List;
import java.util.stream.Collectors;

public class KeyGen {
    public static PublicKey createPublicKey(final Pairing pairing, final SecretKey sk) {
        final Element generator = pairing.getG1().newRandomElement().getImmutable();
        final Element generatorT = pairing.getGT().newRandomElement().getImmutable();
        final Element X = generator.powZn(sk.getx());
        final Element Y = generator.powZn(sk.gety());
        final List<Element> Z = sk.getz().stream()
                .map(generator::powZn).collect(Collectors.toList());
        final List<Element> W = sk.getz().stream()
                .map(Y::powZn).collect(Collectors.toList());
        return new PublicKey(pairing, generator, generatorT,
                X, Y, Z, W);
    }

    public static SecretKey createSecretKey(final Pairing pairing, final int messageSize) {
        final ZrElement[] z = new ZrElement[messageSize];
        for (int i = 0; i < messageSize; i++) {
            z[i] = (ZrElement) pairing.getZr().newRandomElement().getImmutable();
        }
        return new SecretKey((ZrElement) pairing.getZr().newRandomElement().getImmutable(),
                (ZrElement) pairing.getZr().newRandomElement().getImmutable(), z);
    }

    
}
