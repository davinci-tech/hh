package com.huawei.hms.kit.awareness.b;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import com.huawei.health.R;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.kit.awareness.a;
import com.huawei.hms.kit.awareness.b.a.b;
import com.huawei.hms.kit.awareness.internal.hmscore.AwarenessInBean;
import com.huawei.hms.kit.awareness.internal.hmscore.AwarenessOutBean;
import com.huawei.hms.kit.awareness.quickapp.SubAppInfo;
import com.huawei.hms.support.common.ActivityMgr;
import com.huawei.hms.utils.UIUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
class HHF implements AvailableAdapter.AvailableCallBack {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4815a = "AwarenessConnection";
    private static final long b = 5000;
    private static volatile HHF c;
    private HHC d;
    private final Context e;
    private volatile Intent g;
    private volatile com.huawei.hms.kit.awareness.a h;
    private b.a i = b.a.a();
    private b.a j = b.a.a();
    private volatile int k = 0;
    private volatile boolean l = true;
    private int m = 0;
    private final ServiceConnection f = new ServiceConnection() { // from class: com.huawei.hms.kit.awareness.b.HHF.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            HHF.this.b();
            HHF.this.c();
            com.huawei.hms.kit.awareness.b.a.c.b(HHF.f4815a, "AwarenessService Connect Failed.", new Object[0]);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            HHF.this.h = a.AbstractBinderC0121a.a(iBinder);
            HHF.this.i.b();
            com.huawei.hms.kit.awareness.b.a.c.b(HHF.f4815a, "AwarenessService Connect Success.", new Object[0]);
        }
    };

    @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
    public void onComplete(int i) {
        b();
        com.huawei.hms.kit.awareness.b.a.c.b(f4815a, "Update HWID Complete", new Object[0]);
    }

    void a(boolean z) {
        com.huawei.hms.kit.awareness.b.a.c.b(f4815a, "setEnableUpdateWindow: " + z, new Object[0]);
        this.l = z;
    }

    void a(SubAppInfo subAppInfo) {
        try {
            HHC hhc = new HHC(this.e);
            this.d = hhc;
            hhc.setSubAppId(subAppInfo.getSubAppId());
            com.huawei.hms.kit.awareness.b.a.c.b(f4815a, "sub appId : " + subAppInfo.getSubAppId() + "sub URL: " + subAppInfo.getUrls() + "sub pkg : " + subAppInfo.getSubAppId(), new Object[0]);
            AwarenessInBean awarenessInBean = new AwarenessInBean();
            awarenessInBean.setInBeanJsonString(subAppInfo);
            this.d.a(awarenessInBean);
        } catch (ApiException unused) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "set Awareness API failed", new Object[0]);
        }
    }

    final void a(int i) {
        synchronized (this) {
            b(i);
            if (this.g == null || this.h == null) {
                if (this.m >= 3) {
                    throw new HHG(10001, "failed time of binding service is more than three.");
                }
                d();
                if (!this.j.a(5000L)) {
                    com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "Waiting intent timeout!", new Object[0]);
                    throw new HHG(10003, "Wait Intent.");
                }
                if (!this.i.a(5000L)) {
                    com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "Binding service timeout!", new Object[0]);
                    throw new HHG(10003, "Bind Service.");
                }
                if (this.k == 0) {
                    return;
                }
                c();
                throw new HHJ(this.k);
            }
        }
    }

    com.huawei.hms.kit.awareness.a a() {
        return this.h;
    }

    private String e() {
        JSONObject b2 = HHA.a().b();
        if (b2 == null) {
            return null;
        }
        try {
            int callingUid = Binder.getCallingUid();
            String nameForUid = this.e.getPackageManager().getNameForUid(callingUid);
            String string = b2.getString("packageName");
            if (!com.huawei.hms.kit.awareness.barrier.internal.f.c.a(nameForUid) && nameForUid.equals(string)) {
                b2.put("uid", callingUid);
                return b2.toString();
            }
            com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "pkg name from agcFile is not equals pkg name from PackageManager: " + string, new Object[0]);
            return null;
        } catch (JSONException unused) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "set uid failed ", new Object[0]);
            return null;
        }
    }

    private void d() {
        this.d.c().addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.hms.kit.awareness.b.HHF$$ExternalSyntheticLambda0
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                HHF.this.a((AwarenessOutBean) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.hms.kit.awareness.b.HHF$$ExternalSyntheticLambda1
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                HHF.a(exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.i = b.a.a();
        this.j = b.a.a();
    }

    private boolean b(Intent intent) {
        Activity a2 = HHB.a();
        if (a2 == null) {
            return false;
        }
        try {
            a2.startActivityForResult(intent, 0);
            return true;
        } catch (ActivityNotFoundException e) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "AwarenessKit apk showDialog error: " + e.getMessage(), new Object[0]);
            return false;
        }
    }

    private void b(Context context) {
        AvailableAdapter availableAdapter;
        int isHuaweiMobileServicesAvailable;
        if (this.l && (isHuaweiMobileServicesAvailable = (availableAdapter = new AvailableAdapter(context.getResources().getInteger(R.integer._2131623936_res_0x7f0e0000))).isHuaweiMobileServicesAvailable(context)) != 0 && availableAdapter.isUserResolvableError(isHuaweiMobileServicesAvailable)) {
            Activity currentActivity = ActivityMgr.INST.getCurrentActivity();
            if (currentActivity != null) {
                availableAdapter.startResolution(currentActivity, this);
                return;
            }
            Activity a2 = HHB.a();
            if (a2 == null || a2.isDestroyed()) {
                return;
            }
            availableAdapter.startResolution(a2, this);
        }
    }

    private void b(int i) {
        com.huawei.hms.kit.awareness.b.a.c.b(f4815a, "set current api level : " + i + " last success api level: " + this.d.b(), new Object[0]);
        if (i > this.d.b()) {
            if (this.h != null) {
                try {
                    this.e.unbindService(this.f);
                    com.huawei.hms.kit.awareness.b.a.c.b(f4815a, "setApiLevel, Unbind service success", new Object[0]);
                } catch (IllegalArgumentException unused) {
                    com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "setApiLevel, unbindService IllegalArgumentException!", new Object[0]);
                }
            }
            c();
            b();
        }
        this.d.a(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.g = null;
        this.h = null;
    }

    private boolean a(Parcelable parcelable) {
        if (!this.l) {
            return false;
        }
        if (parcelable instanceof Intent) {
            return b((Intent) parcelable);
        }
        if (parcelable instanceof PendingIntent) {
            return a((PendingIntent) parcelable);
        }
        return false;
    }

    private boolean a(Intent intent) {
        com.huawei.hms.kit.awareness.b.a.c.b(f4815a, "bindService: get intent from core successfully", new Object[0]);
        String e = e();
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a(e)) {
            return false;
        }
        this.g = intent;
        this.g.putExtra(com.huawei.hms.kit.awareness.b.a.a.f4822a, e);
        this.g.setType(this.e.getPackageName() + Binder.getCallingUid());
        try {
            this.e.bindService(this.g, this.f, 1);
            return true;
        } catch (SecurityException unused) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "bind AwarenessService failed!", new Object[0]);
            this.m++;
            return false;
        }
    }

    private boolean a(PendingIntent pendingIntent) {
        if (!this.l) {
            return false;
        }
        if (UIUtil.isBackground(this.e)) {
            com.huawei.hms.kit.awareness.b.a.c.a(f4815a, "is background, do not show Dialog", new Object[0]);
            return false;
        }
        Activity a2 = HHB.a();
        if (a2 == null) {
            return false;
        }
        try {
            a2.startIntentSenderForResult(pendingIntent.getIntentSender(), 0, null, 0, 0, 0);
            return true;
        } catch (IntentSender.SendIntentException e) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "AwarenessKit apk showDialog error: " + e.getMessage(), new Object[0]);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Exception exc) {
        com.huawei.hms.kit.awareness.b.a.c.d(f4815a, "Connect to Core Error.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AwarenessOutBean awarenessOutBean) {
        b.a aVar;
        this.k = awarenessOutBean.getCode();
        int code = awarenessOutBean.getCode();
        if (code == 0) {
            this.d.a();
            Intent intent = awarenessOutBean.getIntent();
            if (intent == null || !a(intent)) {
                return;
            } else {
                aVar = this.j;
            }
        } else {
            if (code == 1212) {
                this.j.b();
                this.i.b();
                b();
                com.huawei.hms.kit.awareness.b.a.c.a(f4815a, "showDialog: " + a(awarenessOutBean.getParcelable()), new Object[0]);
                return;
            }
            if (code == 907135003) {
                this.j.b();
                this.i.b();
                PendingIntent pendingIntent = awarenessOutBean.getPendingIntent();
                if (pendingIntent != null) {
                    a(pendingIntent);
                    return;
                } else {
                    b(this.e);
                    return;
                }
            }
            this.j.b();
            aVar = this.i;
        }
        aVar.b();
    }

    static HHF a(Context context) {
        if (c == null) {
            synchronized (HHF.class) {
                if (c == null) {
                    c = new HHF(context);
                }
            }
        }
        return c;
    }

    private HHF(Context context) {
        this.d = new HHC(context);
        this.e = context.getApplicationContext();
    }
}
