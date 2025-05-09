package defpackage;

import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public class uzj {

    /* renamed from: a, reason: collision with root package name */
    private final int f17615a;
    private final float b;
    private final float c;
    private final int d;
    private final int e;
    private final int i;

    uzj(int i, int i2, float f, float f2, int i3, int i4) {
        this.e = i;
        this.d = i2;
        this.b = f;
        this.c = f2;
        this.f17615a = i3;
        this.i = i4;
    }

    public int e() {
        return this.e;
    }

    public int d() {
        return this.d;
    }

    public float a() {
        return this.b;
    }

    public float b() {
        return this.c;
    }

    public int f() {
        return this.f17615a;
    }

    public int g() {
        return this.i;
    }

    public static c c() {
        return new c();
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private int f17616a;
        private float b;
        private float c;
        private int d;
        private int e;
        private int g;

        private c() {
        }

        public c a(Configuration configuration) {
            this.d = configuration.d(CoapConfig.c, TimeUnit.MILLISECONDS);
            this.f17616a = configuration.d(CoapConfig.v, TimeUnit.MILLISECONDS);
            this.b = ((Float) configuration.a((BasicDefinition) CoapConfig.d)).floatValue();
            this.c = ((Float) configuration.a((BasicDefinition) CoapConfig.f15852a)).floatValue();
            this.e = ((Integer) configuration.a((BasicDefinition) CoapConfig.ac)).intValue();
            this.g = ((Integer) configuration.a((BasicDefinition) CoapConfig.an)).intValue();
            e();
            return this;
        }

        public uzj c() {
            e();
            return new uzj(this.d, this.f17616a, this.b, this.c, this.e, this.g);
        }

        private void e() {
            if (this.f17616a < this.d) {
                throw new IllegalStateException("Maximum ack timeout " + this.f17616a + "ms must not be less than ack timeout " + this.d + "ms!");
            }
            if (1 > this.e) {
                throw new IllegalStateException("Maxium retransmit " + this.e + " must not be less than 1!");
            }
            if (1 > this.g) {
                throw new IllegalStateException("Nstart " + this.g + " must not be less than 1!");
            }
            if (1.0d > this.b) {
                throw new IllegalStateException("Ack random factor " + this.b + " must not be less than 1.0!");
            }
            if (1.0d <= this.c) {
                return;
            }
            throw new IllegalStateException("Ack scale factor " + this.c + " must not be less than 1.0!");
        }
    }
}
