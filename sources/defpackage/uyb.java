package defpackage;

import com.huawei.openalliance.ad.constant.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.MessageIdTracker;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.ClockUtil;

/* loaded from: classes7.dex */
public class uyb implements MessageIdTracker {

    /* renamed from: a, reason: collision with root package name */
    private final int f17587a;
    private final long b;
    private final Map<Integer, Long> c;
    private int d;
    private final int e;

    public uyb(int i, int i2, int i3, Configuration configuration) {
        if (i2 >= i3) {
            throw new IllegalArgumentException("max. MID " + i3 + " must be larger than min. MID " + i2 + "!");
        }
        if (i < i2 || i3 <= i) {
            throw new IllegalArgumentException("initial MID " + i + " must be in range [" + i2 + Constants.LINK + i3 + ")!");
        }
        this.b = configuration.a(CoapConfig.p, TimeUnit.NANOSECONDS).longValue();
        this.d = i - i2;
        this.f17587a = i2;
        int i4 = i3 - i2;
        this.e = i4;
        this.c = new HashMap(i4);
    }

    @Override // org.eclipse.californium.core.network.MessageIdTracker
    public int getNextMessageId() {
        int i;
        Long l;
        long d = ClockUtil.d();
        synchronized (this.c) {
            int i2 = this.d;
            int i3 = this.e;
            int i4 = (i2 & 65535) % i3;
            this.d = i4;
            do {
                int i5 = this.d;
                if (i5 < i4 + i3) {
                    this.d = i5 + 1;
                    i = i5 % this.e;
                    l = this.c.get(Integer.valueOf(i));
                    if (l == null) {
                        break;
                    }
                } else {
                    throw new IllegalStateException("No MID available, all [" + this.f17587a + Constants.LINK + (this.f17587a + this.e) + ") MIDs in use! (MID lifetime " + (TimeUnit.NANOSECONDS.toSeconds(this.b) + "s") + "!)");
                }
            } while (l.longValue() - d > 0);
            this.c.put(Integer.valueOf(i), Long.valueOf(d + this.b));
            return i + this.f17587a;
        }
    }
}
