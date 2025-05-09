package defpackage;

import com.huawei.openalliance.ad.constant.Constants;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.californium.core.network.MessageIdTracker;

/* loaded from: classes7.dex */
public class uye implements MessageIdTracker {

    /* renamed from: a, reason: collision with root package name */
    private final int f17589a;
    private final int b;
    private final AtomicInteger c;

    public uye(int i, int i2, int i3) {
        AtomicInteger atomicInteger = new AtomicInteger();
        this.c = atomicInteger;
        if (i2 >= i3) {
            throw new IllegalArgumentException("max. MID " + i3 + " must be larger than min. MID " + i2 + "!");
        }
        if (i < i2 || i3 <= i) {
            throw new IllegalArgumentException("initial MID " + i + " must be in range [" + i2 + Constants.LINK + i3 + ")!");
        }
        atomicInteger.set(i - i2);
        this.b = i2;
        this.f17589a = i3 - i2;
    }

    @Override // org.eclipse.californium.core.network.MessageIdTracker
    public int getNextMessageId() {
        int andIncrement = this.c.getAndIncrement();
        int i = this.f17589a;
        if (andIncrement % i == i - 1) {
            this.c.addAndGet(-i);
        }
        return this.b + andIncrement;
    }
}
