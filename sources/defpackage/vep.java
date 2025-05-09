package defpackage;

import java.security.SecureRandom;
import java.util.Random;

/* loaded from: classes7.dex */
public class vep {
    private static final long d = System.currentTimeMillis();
    private static final ThreadLocal<SecureRandom> e = new ThreadLocal<SecureRandom>() { // from class: vep.5
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SecureRandom initialValue() {
            return new SecureRandom();
        }
    };
    private static final ThreadLocal<Random> b = new ThreadLocal<Random>() { // from class: vep.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Random initialValue() {
            return new Random(vep.d + Thread.currentThread().getId());
        }
    };

    public static SecureRandom d() {
        return e.get();
    }

    public static Random e() {
        return b.get();
    }
}
