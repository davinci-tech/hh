package com.huawei.hms.health;

import android.os.IInterface;
import com.huawei.hms.hihealth.HiHealthKitClient;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class aack implements aaco {
    private static final Object aabh = new Object();
    private static volatile aack aabi;
    private com.huawei.hms.hihealth.aabc aab;
    private com.huawei.hms.hihealth.aabe aaba;
    private com.huawei.hms.hihealth.aabg aabb;
    private com.huawei.hms.hihealth.aabj aabc;
    private com.huawei.hms.hihealth.aabl aabd;
    private com.huawei.hms.hihealth.aabp aabe;
    private com.huawei.hms.hihealth.aabn aabf;
    private com.huawei.hms.hihealth.aabi aabg;

    com.huawei.hms.hihealth.aabp aabd() {
        return this.aabe;
    }

    com.huawei.hms.hihealth.aabj aabc() {
        return this.aabc;
    }

    com.huawei.hms.hihealth.aabi aabb() {
        return this.aabg;
    }

    com.huawei.hms.hihealth.aabg aaba() {
        return this.aabb;
    }

    public void aab(com.huawei.hms.hihealth.aabp aabpVar) {
        this.aabe = aabpVar;
    }

    public void aab(com.huawei.hms.hihealth.aabn aabnVar) {
        this.aabf = aabnVar;
    }

    public void aab(com.huawei.hms.hihealth.aabl aablVar) {
        this.aabd = aablVar;
    }

    public void aab(com.huawei.hms.hihealth.aabj aabjVar) {
        this.aabc = aabjVar;
    }

    public void aab(com.huawei.hms.hihealth.aabi aabiVar) {
        this.aabg = aabiVar;
    }

    public void aab(com.huawei.hms.hihealth.aabg aabgVar) {
        this.aabb = aabgVar;
    }

    public void aab(com.huawei.hms.hihealth.aabe aabeVar) {
        this.aaba = aabeVar;
    }

    public void aab(com.huawei.hms.hihealth.aabc aabcVar) {
        this.aab = aabcVar;
    }

    com.huawei.hms.hihealth.aabc aab() {
        return this.aab;
    }

    public IInterface aab(int i) {
        if (i == 1) {
            return this.aabd;
        }
        if (i == 2) {
            return this.aab;
        }
        if (i == 4) {
            return this.aabb;
        }
        if (i == 5) {
            return this.aabe;
        }
        if (i == 100) {
            return this.aabg;
        }
        if (i == 7) {
            return this.aaba;
        }
        if (i == 8) {
            return this.aabc;
        }
        if (i == 9) {
            return this.aabf;
        }
        aabz.aab("ControllerImpl", "unknown apiType");
        return null;
    }

    public static aack aabe() {
        if (aabi == null) {
            synchronized (aabh) {
                if (aabi == null) {
                    aabi = new aack();
                }
            }
        }
        return aabi;
    }

    private aack() {
        HiHealthKitClient.getInstance().addServiceDisconnectedListener(new aacn(new WeakReference(this)));
    }
}
