package defpackage;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.MessageIdProvider;
import org.eclipse.californium.core.network.MessageIdTracker;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache;
import org.eclipse.californium.elements.util.NetworkInterfacesUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uyd implements MessageIdProvider {
    private static final Logger b = vha.a((Class<?>) uyd.class);

    /* renamed from: a, reason: collision with root package name */
    private final MessageIdTracker f17588a;
    private final Configuration c;
    private final CoapConfig.TrackerMode d;
    private final int e;
    private final Random f;
    private final LeastRecentlyUpdatedCache<InetSocketAddress, MessageIdTracker> i;

    public uyd(Configuration configuration) {
        if (configuration == null) {
            throw new NullPointerException("Config must not be null");
        }
        this.d = (CoapConfig.TrackerMode) configuration.a((BasicDefinition) CoapConfig.ae);
        this.c = configuration;
        if (((Boolean) configuration.a((BasicDefinition) CoapConfig.av)).booleanValue()) {
            this.f = new Random(ClockUtil.d());
        } else {
            this.f = null;
        }
        this.i = new LeastRecentlyUpdatedCache<>(((Integer) configuration.a((BasicDefinition) CoapConfig.w)).intValue(), configuration.a(CoapConfig.u, TimeUnit.SECONDS).longValue(), TimeUnit.SECONDS);
        int intValue = ((Integer) configuration.a((BasicDefinition) CoapConfig.ai)).intValue();
        if (intValue > 0) {
            this.e = intValue;
            Random random = this.f;
            this.f17588a = c(random == null ? intValue : random.nextInt(65536 - intValue) + intValue, intValue, 65536, configuration);
        } else {
            this.e = 65536;
            this.f17588a = null;
        }
    }

    @Override // org.eclipse.californium.core.network.MessageIdProvider
    public int getNextMessageId(InetSocketAddress inetSocketAddress) {
        MessageIdTracker d = d(inetSocketAddress);
        if (d == null) {
            throw new IllegalStateException("No MID available, max. peers " + this.i.j() + " exhausted! (Timeout " + (this.i.b(TimeUnit.SECONDS) + "s") + ".)");
        }
        return d.getNextMessageId();
    }

    private MessageIdTracker d(InetSocketAddress inetSocketAddress) {
        if (NetworkInterfacesUtil.b(inetSocketAddress.getAddress())) {
            if (this.f17588a == null) {
                b.warn("Destination address {} is a multicast address, please configure NetworkConfig to support multicast messaging", inetSocketAddress);
            }
            return this.f17588a;
        }
        MessageIdTracker c = this.i.c(inetSocketAddress);
        if (c == null) {
            Random random = this.f;
            MessageIdTracker c2 = c(random == null ? 0 : random.nextInt(this.e), 0, this.e, this.c);
            this.i.g().lock();
            try {
                MessageIdTracker c3 = this.i.c(inetSocketAddress);
                if (c3 == null) {
                    if (this.i.d(inetSocketAddress, c2)) {
                        return c2;
                    }
                    this.i.g().unlock();
                    return null;
                }
                this.i.g().unlock();
                c = c3;
            } finally {
                this.i.g().unlock();
            }
        }
        if (c != null) {
            this.i.e((LeastRecentlyUpdatedCache<InetSocketAddress, MessageIdTracker>) inetSocketAddress);
        }
        return c;
    }

    /* renamed from: uyd$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[CoapConfig.TrackerMode.values().length];
            c = iArr;
            try {
                iArr[CoapConfig.TrackerMode.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[CoapConfig.TrackerMode.MAPBASED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[CoapConfig.TrackerMode.GROUPED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private MessageIdTracker c(int i, int i2, int i3, Configuration configuration) {
        int i4 = AnonymousClass5.c[this.d.ordinal()];
        if (i4 == 1) {
            return new uye(i, i2, i3);
        }
        if (i4 == 2) {
            return new uyb(i, i2, i3, configuration);
        }
        return new uxz(i, i2, i3, configuration);
    }
}
