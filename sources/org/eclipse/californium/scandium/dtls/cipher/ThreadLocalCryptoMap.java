package org.eclipse.californium.scandium.dtls.cipher;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;

/* loaded from: classes7.dex */
public class ThreadLocalCryptoMap<TL extends ThreadLocalCrypto<?>> {
    private final Factory<TL> c;
    private final ConcurrentMap<String, TL> e = new ConcurrentHashMap();

    public interface Factory<T> {
        T getInstance(String str);
    }

    public ThreadLocalCryptoMap(Factory<TL> factory) {
        this.c = factory;
    }

    public TL a(String str) {
        TL tl = this.e.get(str);
        if (tl != null) {
            return tl;
        }
        TL factory = this.c.getInstance(str);
        TL putIfAbsent = this.e.putIfAbsent(str, factory);
        return putIfAbsent == null ? factory : putIfAbsent;
    }
}
