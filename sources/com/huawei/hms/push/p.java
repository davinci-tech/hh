package com.huawei.hms.push;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes9.dex */
public class p extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private Context f5683a;
    private o b;

    public p(Context context, o oVar) {
        this.f5683a = context;
        this.b = oVar;
    }

    private static Intent a(Context context, o oVar) {
        if (oVar == null) {
            return null;
        }
        Intent b = e.b(context, oVar.c());
        if (oVar.m() == null) {
            if (oVar.a() != null) {
                Intent intent = new Intent(oVar.a());
                if (e.a(context, oVar.c(), intent).booleanValue()) {
                    b = intent;
                }
            }
            b.setPackage(oVar.c());
            return b;
        }
        try {
            Intent parseUri = Intent.parseUri(oVar.m(), 0);
            parseUri.setSelector(null);
            if (parseUri.getClipData() == null) {
                parseUri.setClipData(ClipData.newPlainText("avoid intent add read permission flags", "avoid"));
            }
            HMSLog.d("PushSelfShowLog", "Intent.parseUri(msg.intentUri, 0), action:" + parseUri.getAction());
            return e.a(context, oVar.c(), parseUri).booleanValue() ? parseUri : b;
        } catch (Exception e) {
            HMSLog.w("PushSelfShowLog", "intentUri error," + e.toString());
            return b;
        }
    }

    private boolean b(Context context) {
        if ("cosa".equals(this.b.h())) {
            return a(context);
        }
        return true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        HMSLog.i("PushSelfShowLog", "enter run()");
        try {
            if (!b(this.f5683a) || b(this.f5683a, this.b)) {
                return;
            }
            n.a(this.f5683a, this.b);
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", e.toString());
        }
    }

    private boolean b(Context context, o oVar) {
        if (!"cosa".equals(oVar.h()) || a(context, oVar) != null) {
            return false;
        }
        HMSLog.d("PushSelfShowLog", "launchCosaApp,intent == null");
        return true;
    }

    private boolean a(Context context) {
        return e.c(context, this.b.c());
    }
}
