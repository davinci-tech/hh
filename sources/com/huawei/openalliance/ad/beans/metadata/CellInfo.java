package com.huawei.openalliance.ad.beans.metadata;

import android.util.Pair;
import com.huawei.openalliance.ad.annotations.a;

/* loaded from: classes5.dex */
public class CellInfo {

    @a
    private String mcc;

    @a
    private String mnc;

    @a
    private int networkType;

    public int c() {
        return this.networkType;
    }

    public String b() {
        return this.mnc;
    }

    public void a(Pair<Integer, Pair<String, String>> pair) {
        if (pair == null) {
            return;
        }
        if (pair.first != null) {
            this.networkType = ((Integer) pair.first).intValue();
        }
        Pair pair2 = (Pair) pair.second;
        if (pair2 != null) {
            this.mcc = (String) pair2.first;
            this.mnc = (String) pair2.second;
        }
    }

    public String a() {
        return this.mcc;
    }
}
