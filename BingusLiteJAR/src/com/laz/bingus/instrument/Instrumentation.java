package com.laz.bingus.instrument;

import com.laz.bingus.instrument.transformer.Transformer;

import java.util.ArrayList;
import java.util.List;

public class Instrumentation {
    public static Instrumentation instance;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<Transformer> transformers = new ArrayList<>();

    public Instrumentation() {
        instance = this;
    }

    public void addTransformer(Transformer transformer) {
        transformers.add(transformer);
    }

    public native boolean retransformClass(Class<?> clazz);
}
