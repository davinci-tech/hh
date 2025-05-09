package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public abstract class ta {

    /* renamed from: a, reason: collision with root package name */
    protected Context f7529a;
    protected ContentRecord b;
    private String c = null;
    private ta d;

    public abstract boolean a();

    public String c() {
        ta taVar;
        String str = this.c;
        return (str != null || (taVar = this.d) == null) ? str : taVar.c();
    }

    public boolean b() {
        ta taVar = this.d;
        if (taVar != null) {
            return taVar.a();
        }
        return false;
    }

    public void b(String str) {
        this.c = str;
    }

    public void a(ta taVar) {
        this.d = taVar;
    }

    public ta(Context context, ContentRecord contentRecord) {
        this.f7529a = context;
        this.b = contentRecord;
    }

    public ta() {
    }
}
