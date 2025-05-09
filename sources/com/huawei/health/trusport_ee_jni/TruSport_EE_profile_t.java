package com.huawei.health.trusport_ee_jni;

import defpackage.gls;

/* loaded from: classes4.dex */
public class TruSport_EE_profile_t {
    protected transient boolean d;
    private transient long e;

    protected TruSport_EE_profile_t(long j, boolean z) {
        this.d = z;
        this.e = j;
    }

    public static long c(TruSport_EE_profile_t truSport_EE_profile_t) {
        if (truSport_EE_profile_t == null) {
            return 0L;
        }
        return truSport_EE_profile_t.e;
    }

    protected void finalize() {
        c();
    }

    public void c() {
        synchronized (this) {
            long j = this.e;
            if (j != 0) {
                if (this.d) {
                    this.d = false;
                    trusport_eeJNI.delete_TruSport_EE_profile_t(j);
                }
                this.e = 0L;
            }
        }
    }

    public void a(short s) {
        trusport_eeJNI.TruSport_EE_profile_t_age_set(this.e, this, s);
    }

    public void c(float f) {
        trusport_eeJNI.TruSport_EE_profile_t_height_set(this.e, this, f);
    }

    public void b(float f) {
        trusport_eeJNI.TruSport_EE_profile_t_weight_set(this.e, this, f);
    }

    public void e(gls glsVar) {
        trusport_eeJNI.TruSport_EE_profile_t_sex_set(this.e, this, glsVar.e());
    }

    public TruSport_EE_profile_t() {
        this(trusport_eeJNI.new_TruSport_EE_profile_t(), true);
    }
}
