package com.huawei.hms.hihealth.data;

import android.os.Parcelable;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import java.util.List;

/* loaded from: classes4.dex */
public class aabc extends aabq {
    public static final Parcelable.Creator<aabc> CREATOR = new aabq.aab(aabc.class);

    @aaby(id = 1)
    private aab aab;

    @aaby(id = 2)
    private List<aabb> aaba;

    @aaby(id = 3)
    private aaba aabb;

    public String toString() {
        StringBuilder aab = com.huawei.hms.health.aab.aab("InterfacePolicy{bgLimitPolicy=");
        aab.append(this.aab);
        aab.append(", cachePolicies=");
        aab.append(this.aaba);
        aab.append(", flowControlPolicy=");
        aab.append(this.aabb);
        aab.append('}');
        return aab.toString();
    }

    public aaba aabb() {
        return this.aabb;
    }

    public List<aabb> aaba() {
        return this.aaba;
    }

    public aab aab() {
        return this.aab;
    }

    @aabw
    public aabc(@aabv(id = 1) aab aabVar, @aabv(id = 2) List<aabb> list, @aabv(id = 3) aaba aabaVar) {
        this.aab = aabVar;
        this.aaba = list;
        this.aabb = aabaVar;
    }

    public aabc() {
    }
}
