package defpackage;

import com.huawei.openalliance.ad.constant.Constants;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.MessageIdTracker;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.ClockUtil;

/* loaded from: classes7.dex */
public class uxz implements MessageIdTracker {

    /* renamed from: a, reason: collision with root package name */
    private final long f17584a;
    private final int b;
    private final long[] c;
    private int d;
    private final int e;
    private final int g;
    private final int j;

    public uxz(int i, int i2, int i3, Configuration configuration) {
        if (i2 >= i3) {
            throw new IllegalArgumentException("max. MID " + i3 + " must be larger than min. MID " + i2 + "!");
        }
        if (i < i2 || i3 <= i) {
            throw new IllegalArgumentException("initial MID " + i + " must be in range [" + i2 + Constants.LINK + i3 + ")!");
        }
        this.f17584a = configuration.a(CoapConfig.p, TimeUnit.NANOSECONDS).longValue();
        this.d = i - i2;
        this.b = i2;
        this.j = i3 - i2;
        int intValue = ((Integer) configuration.a((BasicDefinition) CoapConfig.ah)).intValue();
        this.e = intValue;
        this.g = ((r5 + intValue) - 1) / intValue;
        long[] jArr = new long[intValue];
        this.c = jArr;
        Arrays.fill(jArr, ClockUtil.d() - 1000);
    }

    @Override // org.eclipse.californium.core.network.MessageIdTracker
    public int getNextMessageId() {
        long d = ClockUtil.d();
        synchronized (this) {
            int i = (this.d & 65535) % this.j;
            int i2 = i / this.g;
            int i3 = this.e;
            long[] jArr = this.c;
            if (jArr[(i2 + 1) % i3] - d < 0) {
                jArr[i2] = d + this.f17584a;
                this.d = i + 1;
                return i + this.b;
            }
            throw new IllegalStateException("No MID available, all [" + this.b + Constants.LINK + (this.b + this.j) + ") MID-groups in use! (MID lifetime " + (TimeUnit.NANOSECONDS.toSeconds(this.f17584a) + "s") + "!)");
        }
    }
}
