package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;

/* loaded from: classes5.dex */
public class jc {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7116a = new byte[0];
    private static jc b;
    private int c;
    private ex d;

    public boolean a(int i, String str) {
        if (!TextUtils.isEmpty(str) && this.c > 0) {
            EventMonitorRecord a2 = this.d.a(str);
            if (a2 != null) {
                this.d.a(a2.a(), System.currentTimeMillis());
                return true;
            }
            EventMonitorRecord eventMonitorRecord = new EventMonitorRecord();
            eventMonitorRecord.a(System.currentTimeMillis());
            eventMonitorRecord.a(i);
            eventMonitorRecord.a(str);
            this.d.a(eventMonitorRecord, this.c);
        }
        return false;
    }

    private static jc b(Context context) {
        jc jcVar;
        synchronized (f7116a) {
            if (b == null) {
                b = new jc(context);
            }
            b.c = fh.b(context).bv() * 100;
            jcVar = b;
        }
        return jcVar;
    }

    public static jc a(Context context) {
        return b(context);
    }

    public jc(Context context) {
        this.d = ex.a(context);
    }
}
