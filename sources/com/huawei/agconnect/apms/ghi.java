package com.huawei.agconnect.apms;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public class ghi extends opq {
    public final pqr abc;
    public final SharedPreferences bcd;
    public final SharedPreferences.Editor cde;
    public final Lock def;

    public ghi(Context context) {
        pqr pqrVar = new pqr();
        this.abc = pqrVar;
        pqrVar.bcd();
        this.def = new ReentrantLock();
        SharedPreferences sharedPreferences = context.getSharedPreferences(abc(context.getPackageName()), 0);
        this.bcd = sharedPreferences;
        this.cde = sharedPreferences.edit();
    }

    public final boolean abc(String str, boolean z) {
        try {
            return this.bcd.getBoolean(str, z);
        } catch (Throwable unused) {
            return false;
        }
    }

    public final void bcd(String str, boolean z) {
        this.def.lock();
        try {
            this.cde.putBoolean(str, z);
            this.cde.apply();
        } finally {
            this.def.unlock();
        }
    }

    @Override // com.huawei.agconnect.apms.opq, com.huawei.agconnect.apms.qrs
    public void ghi() {
        this.def.lock();
        try {
            this.cde.clear();
            this.cde.apply();
            this.abc.bcd();
        } finally {
            this.def.unlock();
        }
    }

    public String jkl() {
        return bcd("apms.cdn_header_name");
    }

    public String klm() {
        return bcd("apms.disabled_app_versions");
    }

    public boolean lmn() {
        return abc("apms.enable_anr_monitor", true);
    }

    public boolean mno() {
        return abc("apms.disable_collection", false);
    }

    public boolean nop() {
        return abc("apms.disable_collection_by_user", false);
    }

    public boolean opq() {
        return abc("apms.enable_web_view_monitor", true);
    }

    public final String abc(String str) {
        return "com.huawei.agconnect.apms.v1_" + str;
    }

    public final String bcd(String str) {
        if (!this.bcd.contains(str)) {
            return "";
        }
        try {
            return this.bcd.getString(str, "");
        } catch (Throwable unused) {
            return "";
        }
    }
}
