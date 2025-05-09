package defpackage;

import java.util.Random;
import kotlin.Metadata;
import kotlin.random.AbstractPlatformRandom;

@Metadata(d1 = {"\u0000\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\b\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/random/FallbackThreadLocalRandom;", "Lkotlin/random/AbstractPlatformRandom;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "implStorage", "kotlin/random/FallbackThreadLocalRandom$implStorage$1", "Lkotlin/random/FallbackThreadLocalRandom$implStorage$1;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uin extends AbstractPlatformRandom {
    private final e e = new e();

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0014¨\u0006\u0004"}, d2 = {"kotlin/random/FallbackThreadLocalRandom$implStorage$1", "Ljava/lang/ThreadLocal;", "Ljava/util/Random;", "initialValue", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class e extends ThreadLocal<Random> {
        e() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Random initialValue() {
            return new Random();
        }
    }

    @Override // kotlin.random.AbstractPlatformRandom
    public Random getImpl() {
        Random random = this.e.get();
        uhy.a(random, "");
        return random;
    }
}
