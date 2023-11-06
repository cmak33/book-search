package booksearch.service.factory.auxiliary;

import booksearch.model.encoder.implementations.DefaultEncoder;
import booksearch.model.encoder.interfaces.Encoder;

public class AuxiliaryFactory {

    private static final Encoder encoder = new DefaultEncoder();

    public static Encoder getEncoder(){
        return encoder;
    }
}
