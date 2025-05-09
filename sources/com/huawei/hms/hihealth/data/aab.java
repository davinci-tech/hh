package com.huawei.hms.hihealth.data;

import android.os.Parcelable;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import java.util.List;

/* loaded from: classes4.dex */
public class aab extends aabq {
    public static final Parcelable.Creator<aab> CREATOR = new aabq.aab(aab.class);

    @aaby(id = 1)
    private List<String> aab;

    @aaby(id = 2)
    private int aaba;

    @aaby(id = 3)
    private int aabb;
    private String aabc;

    public String toString() {
        StringBuilder aab = com.huawei.hms.health.aab.aab("BgLimitPolicy{interfaceNames=");
        aab.append(this.aab);
        aab.append(", limitTime=");
        aab.append(this.aaba);
        aab.append(", autoUnbindTime=");
        aab.append(this.aabb);
        aab.append(", whiteAppIds='");
        aab.append(this.aabc);
        aab.append('\'');
        aab.append('}');
        return aab.toString();
    }

    public int aabb() {
        return this.aaba;
    }

    public List<String> aaba() {
        return this.aab;
    }

    public int aab() {
        return this.aabb;
    }

    @aabw
    public aab(@aabv(id = 1) List<String> list, @aabv(id = 2) int i, @aabv(id = 3) int i2) {
        this.aab = list;
        this.aaba = i;
        this.aabb = i2;
    }
}
