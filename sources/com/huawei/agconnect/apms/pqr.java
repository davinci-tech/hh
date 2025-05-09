package com.huawei.agconnect.apms;

import com.huawei.operation.OpAnalyticsConstants;

/* loaded from: classes2.dex */
public class pqr {
    public static pqr abc;
    public int bcd;
    public int cde;
    public int def;
    public int efg;
    public long fgh;
    public long ghi;

    public static pqr abc() {
        if (abc == null) {
            synchronized (pqr.class) {
                if (abc == null) {
                    pqr pqrVar = new pqr();
                    abc = pqrVar;
                    pqrVar.bcd();
                }
            }
        }
        return abc;
    }

    public void bcd() {
        this.bcd = 60;
        this.efg = 100;
        this.cde = 600;
        this.def = 1000;
        this.fgh = 240L;
        this.ghi = OpAnalyticsConstants.H5_LOADING_DELAY;
    }
}
