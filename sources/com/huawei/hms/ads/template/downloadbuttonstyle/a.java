package com.huawei.hms.ads.template.downloadbuttonstyle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    protected Context f4344a;
    protected int b;
    protected WeakReference<AppDownloadButton> c;
    private BroadcastReceiver d;

    public abstract void a();

    protected abstract void b(Context context);

    public void b() {
        if (this.d != null) {
            this.f4344a.getApplicationContext().unregisterReceiver(this.d);
            this.d = null;
        }
    }

    /* renamed from: com.huawei.hms.ads.template.downloadbuttonstyle.a$a, reason: collision with other inner class name */
    static class C0080a extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        Context f4345a;
        a b;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                ho.b("BaseDwnButtonStyle", "on Receive");
                a aVar = this.b;
                if (aVar != null) {
                    aVar.b = fh.b(this.f4345a).cg();
                    this.b.a();
                }
            } catch (Throwable th) {
                ho.c("BaseDwnButtonStyle", "ui mode receiver err, %s", th.getClass().getSimpleName());
            }
        }

        public C0080a(Context context, a aVar) {
            this.f4345a = context.getApplicationContext();
            this.b = aVar;
        }
    }

    public void a(Context context) {
        b(context);
        a();
        c();
    }

    private void c() {
        this.d = new C0080a(this.f4344a, this);
        ao.a(this.f4344a.getApplicationContext(), this.d, new IntentFilter(Constants.UI_CHANGE_ACTION));
    }

    public a(Context context, AppDownloadButton appDownloadButton) {
        Context applicationContext = context.getApplicationContext();
        this.f4344a = applicationContext;
        this.b = fh.b(applicationContext).cg();
        this.c = new WeakReference<>(appDownloadButton);
    }
}
