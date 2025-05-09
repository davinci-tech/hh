package com.huawei.agconnect.apms;

/* loaded from: classes2.dex */
public class nml {
    public String abc;
    public long bcd;
    public long cde = -1;
    public String def = "";
    public boolean efg = false;

    public nml(String str, long j) {
        this.abc = str;
        this.bcd = j;
    }

    public String abc() {
        return this.def;
    }

    public long bcd() {
        return this.bcd;
    }

    public long cde() {
        return this.cde;
    }

    public String def() {
        return this.abc;
    }

    public boolean efg() {
        if (this.efg) {
            return true;
        }
        if (this.cde == -1) {
            return false;
        }
        this.efg = true;
        return true;
    }
}
