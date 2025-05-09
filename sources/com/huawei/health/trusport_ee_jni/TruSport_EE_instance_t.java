package com.huawei.health.trusport_ee_jni;

/* loaded from: classes4.dex */
public class TruSport_EE_instance_t {

    /* renamed from: a, reason: collision with root package name */
    private transient long f3470a;
    protected transient boolean e;

    protected TruSport_EE_instance_t(long j, boolean z) {
        this.e = z;
        this.f3470a = j;
    }

    public static long c(TruSport_EE_instance_t truSport_EE_instance_t) {
        if (truSport_EE_instance_t == null) {
            return 0L;
        }
        return truSport_EE_instance_t.f3470a;
    }

    protected void finalize() {
        b();
    }

    public void b() {
        synchronized (this) {
            long j = this.f3470a;
            if (j != 0) {
                if (this.e) {
                    this.e = false;
                    trusport_eeJNI.delete_TruSport_EE_instance_t(j);
                }
                this.f3470a = 0L;
            }
        }
    }

    public TruSport_EE_instance_t() {
        this(trusport_eeJNI.new_TruSport_EE_instance_t(), true);
    }
}
