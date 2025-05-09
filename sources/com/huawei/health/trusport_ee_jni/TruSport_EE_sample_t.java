package com.huawei.health.trusport_ee_jni;

import java.math.BigInteger;

/* loaded from: classes4.dex */
public class TruSport_EE_sample_t {
    private transient long b;
    protected transient boolean e;

    protected TruSport_EE_sample_t(long j, boolean z) {
        this.e = z;
        this.b = j;
    }

    public static long d(TruSport_EE_sample_t truSport_EE_sample_t) {
        if (truSport_EE_sample_t == null) {
            return 0L;
        }
        return truSport_EE_sample_t.b;
    }

    protected void finalize() {
        b();
    }

    public void b() {
        synchronized (this) {
            long j = this.b;
            if (j != 0) {
                if (this.e) {
                    this.e = false;
                    trusport_eeJNI.delete_TruSport_EE_sample_t(j);
                }
                this.b = 0L;
            }
        }
    }

    public void b(BigInteger bigInteger) {
        trusport_eeJNI.TruSport_EE_sample_t_timestamp_set(this.b, this, bigInteger);
    }

    public void d(float f) {
        trusport_eeJNI.TruSport_EE_sample_t_speed_set(this.b, this, f);
    }

    public void e(float f) {
        trusport_eeJNI.TruSport_EE_sample_t_steprate_set(this.b, this, f);
    }

    public void b(short s) {
        trusport_eeJNI.TruSport_EE_sample_t_hr_set(this.b, this, s);
    }

    public TruSport_EE_sample_t() {
        this(trusport_eeJNI.new_TruSport_EE_sample_t(), true);
    }
}
