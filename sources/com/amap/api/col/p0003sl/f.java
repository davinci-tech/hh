package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import com.autonavi.aps.amapapi.utils.b;
import com.autonavi.aps.amapapi.utils.i;

/* loaded from: classes2.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    e f1032a;
    Context b;
    Messenger c = null;

    public f(Context context) {
        this.f1032a = null;
        this.b = null;
        this.b = context.getApplicationContext();
        this.f1032a = new e(this.b);
    }

    public final IBinder a(Intent intent) {
        this.f1032a.b(intent);
        this.f1032a.a(intent);
        Messenger messenger = new Messenger(this.f1032a.b());
        this.c = messenger;
        return messenger.getBinder();
    }

    public final void a() {
        try {
            e.d();
            this.f1032a.j = i.b();
            this.f1032a.k = i.a();
            this.f1032a.a();
        } catch (Throwable th) {
            b.a(th, "ApsServiceCore", "onCreate");
        }
    }

    public final void b() {
        try {
            e eVar = this.f1032a;
            if (eVar != null) {
                eVar.b().sendEmptyMessage(11);
            }
        } catch (Throwable th) {
            b.a(th, "ApsServiceCore", "onDestroy");
        }
    }
}
