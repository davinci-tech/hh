package defpackage;

import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.deduplication.Deduplicator;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uyk {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f17600a = vha.a((Class<?>) uyk.class);
    private static uyk b;

    public static uyk b() {
        uyk uykVar;
        synchronized (uyk.class) {
            if (b == null) {
                b = new uyk();
            }
            uykVar = b;
        }
        return uykVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Deduplicator e(Configuration configuration) {
        char c;
        String str = (String) configuration.a((BasicDefinition) CoapConfig.o);
        str.hashCode();
        switch (str.hashCode()) {
            case -1928175876:
                if (str.equals("NO_DEDUPLICATOR")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1008521042:
                if (str.equals("MARK_AND_SWEEP")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1510904352:
                if (str.equals("PEERS_MARK_AND_SWEEP")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1583112269:
                if (str.equals("CROP_ROTATION")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return new uyo();
        }
        if (c == 1) {
            return new uyl(configuration);
        }
        if (c == 2) {
            return new uym(configuration);
        }
        if (c == 3) {
            return new uyj(configuration);
        }
        f17600a.warn("configuration contains unsupported deduplicator type, duplicate detection will be turned off");
        return new uyo();
    }
}
