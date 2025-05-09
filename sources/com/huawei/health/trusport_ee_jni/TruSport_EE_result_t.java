package com.huawei.health.trusport_ee_jni;

/* loaded from: classes4.dex */
public class TruSport_EE_result_t {

    /* renamed from: a, reason: collision with root package name */
    private transient long f3471a;
    protected transient boolean c;

    protected TruSport_EE_result_t(long j, boolean z) {
        this.c = z;
        this.f3471a = j;
    }

    public static long e(TruSport_EE_result_t truSport_EE_result_t) {
        if (truSport_EE_result_t == null) {
            return 0L;
        }
        return truSport_EE_result_t.f3471a;
    }

    protected void finalize() {
        d();
    }

    public void d() {
        synchronized (this) {
            long j = this.f3471a;
            if (j != 0) {
                if (this.c) {
                    this.c = false;
                    trusport_eeJNI.delete_TruSport_EE_result_t(j);
                }
                this.f3471a = 0L;
            }
        }
    }

    public float a() {
        return trusport_eeJNI.TruSport_EE_result_t_total_get(this.f3471a, this);
    }

    public float c() {
        return trusport_eeJNI.TruSport_EE_result_t_active_get(this.f3471a, this);
    }

    public float b() {
        return trusport_eeJNI.TruSport_EE_result_t_resting_get(this.f3471a, this);
    }

    public TruSport_EE_result_t() {
        this(trusport_eeJNI.new_TruSport_EE_result_t(), true);
    }
}
