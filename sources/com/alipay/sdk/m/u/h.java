package com.alipay.sdk.m.u;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.APayEntranceActivity;
import com.alipay.sdk.app.AlipayResultActivity;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import com.huawei.operation.utils.Constants;
import defpackage.kh;
import defpackage.kl;
import defpackage.kr;
import defpackage.lv;
import defpackage.ma;
import defpackage.mc;
import defpackage.md;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    public g f873a;
    public volatile IAlixPay b;
    public boolean d;
    public Activity e;
    public final lv i;
    public final Object c = IAlixPay.class;
    public boolean g = false;
    public String h = null;
    public String f = null;

    public class a implements APayEntranceActivity.a {
        public final /* synthetic */ Object b;

        public a(Object obj) {
            this.b = obj;
        }

        @Override // com.alipay.sdk.app.APayEntranceActivity.a
        public void a(String str) {
            h.this.f = str;
            synchronized (this.b) {
                try {
                    this.b.notify();
                } finally {
                }
            }
        }
    }

    public class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f874a;
        public final /* synthetic */ Intent e;

        public b(Intent intent, Object obj) {
            this.e = intent;
            this.f874a = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (h.this.e != null) {
                    h.this.e.startActivity(this.e);
                } else {
                    kl.c(h.this.i, "biz", "ErrActNull2", "");
                    Context a2 = h.this.i.a();
                    if (a2 != null) {
                        a2.startActivity(this.e);
                    }
                }
            } finally {
            }
        }
    }

    public class c implements AlipayResultActivity.a {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f875a;

        public c(CountDownLatch countDownLatch) {
            this.f875a = countDownLatch;
        }

        @Override // com.alipay.sdk.app.AlipayResultActivity.a
        public void a(int i, String str, String str2) {
            h.this.h = kh.c(i, str, str2);
            this.f875a.countDown();
        }
    }

    public class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ APayEntranceActivity.a f876a;

        public d(APayEntranceActivity.a aVar) {
            this.f876a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (h.this.i == null || h.this.i.b()) {
                return;
            }
            kl.c(h.this.i, "biz", "ErrActNotCreated", "");
            if (kr.a().ab()) {
                h.this.i.e(true);
                this.f876a.a(kh.a());
            }
        }
    }

    public class e extends IRemoteServiceCallback.Stub {
        public e() {
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public int getVersion() throws RemoteException {
            return 4;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public boolean isHideLoadingScreen() throws RemoteException {
            return false;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void payEnd(boolean z, String str) throws RemoteException {
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void r03(String str, String str2, Map map) throws RemoteException {
            kl.a(h.this.i, "wlt", str, str2);
            if (TextUtils.equals(str2, "ActivityStartSuccess")) {
                if (h.this.f873a != null) {
                    h.this.f873a.a();
                }
                if (h.this.i != null) {
                    h.this.i.c(true);
                }
            }
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            if (bundle == null) {
                bundle = new Bundle();
            }
            try {
                bundle.putInt("CallingPid", i);
                intent.putExtras(bundle);
            } catch (Exception e) {
                kl.e(h.this.i, "biz", "ErrIntentEx", e);
            }
            intent.setClassName(str, str2);
            try {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                ActivityManager.getMyMemoryState(runningAppProcessInfo);
                kl.a(h.this.i, "biz", "isFg", runningAppProcessInfo.processName + "|" + runningAppProcessInfo.importance + "|");
            } catch (Throwable unused) {
            }
            try {
                if (h.this.e == null) {
                    kl.c(h.this.i, "biz", "ErrActNull", "");
                    Context a2 = h.this.i.a();
                    if (a2 != null) {
                        a2.startActivity(intent);
                        return;
                    }
                    return;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                h.this.e.startActivity(intent);
                kl.a(h.this.i, "biz", "stAct2", "" + (SystemClock.elapsedRealtime() - elapsedRealtime));
            } catch (Throwable th) {
                kl.e(h.this.i, "biz", "ErrActEx", th);
                throw th;
            }
        }

        public /* synthetic */ e(h hVar, c cVar) {
            this();
        }
    }

    public interface g {
        void a();

        void b();
    }

    /* renamed from: com.alipay.sdk.m.u.h$h, reason: collision with other inner class name */
    public class ServiceConnectionC0013h implements ServiceConnection {
        public ServiceConnectionC0013h() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            kl.b(h.this.i, "biz", "srvCon");
            synchronized (h.this.c) {
                h.this.b = IAlixPay.Stub.asInterface(iBinder);
                h.this.c.notify();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            kl.b(h.this.i, "biz", "srvDis");
            h.this.b = null;
        }

        public /* synthetic */ ServiceConnectionC0013h(h hVar, c cVar) {
            this();
        }
    }

    public h(Activity activity, lv lvVar, g gVar) {
        this.e = activity;
        this.i = lvVar;
        this.f873a = gVar;
        ma.d("mspl", "alipaySdk");
    }

    private String d(String str, String str2) {
        JSONObject jSONObject;
        Object obj = new Object();
        String a2 = md.a(32);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        kl.a(this.i, "biz", "BSAStart", a2 + "|" + elapsedRealtime);
        lv.e.e(this.i, a2);
        a aVar = new a(obj);
        APayEntranceActivity.d.put(a2, aVar);
        try {
            HashMap<String, String> b2 = lv.b(this.i);
            b2.put("ts_intent", String.valueOf(elapsedRealtime));
            jSONObject = new JSONObject(b2);
        } catch (Throwable th) {
            try {
                kl.e(this.i, "biz", "BSALocEx", th);
                jSONObject = null;
            } catch (InterruptedException e2) {
                kl.e(this.i, "biz", "BSAWaiting", e2);
                return kh.c(com.alipay.sdk.m.j.c.PAY_WAITTING.b(), com.alipay.sdk.m.j.c.PAY_WAITTING.a(), "");
            } catch (Throwable th2) {
                kl.e(this.i, "biz", "BSAEx", th2);
                md.c("alipaySdk", "startActivityEx", this.e, this.i);
                return "scheme_failed";
            }
        }
        Intent intent = new Intent(this.e, (Class<?>) APayEntranceActivity.class);
        intent.putExtra("ap_order_info", str);
        intent.putExtra("ap_target_packagename", str2);
        intent.putExtra("ap_session", a2);
        if (jSONObject != null) {
            intent.putExtra("ap_local_info", jSONObject.toString());
        }
        new Handler(Looper.getMainLooper()).postDelayed(new d(aVar), kr.a().n());
        Activity activity = this.e;
        lv lvVar = this.i;
        kl.c(activity, lvVar, str, lvVar.b);
        if (kr.a().z()) {
            new Handler(Looper.getMainLooper()).post(new b(intent, obj));
        } else {
            try {
                Activity activity2 = this.e;
                if (activity2 != null) {
                    activity2.startActivity(intent);
                } else {
                    kl.c(this.i, "biz", "ErrActNull", "");
                    Context a3 = this.i.a();
                    if (a3 != null) {
                        a3.startActivity(intent);
                    }
                }
            } catch (Throwable th3) {
                kl.e(this.i, "biz", "ErrActEx", th3);
                throw th3;
            }
        }
        synchronized (obj) {
            obj.wait();
        }
        String str3 = this.f;
        String str4 = "unknown";
        try {
            String str5 = mc.c(this.i, str3).get("resultStatus");
            str4 = str5 == null ? Constants.NULL : str5;
        } catch (Throwable th4) {
            kl.e(this.i, "biz", "BSAStatEx", th4);
        }
        kl.b(this.i, "biz", "BSADone-" + str4);
        if (!TextUtils.isEmpty(str3)) {
            return str3;
        }
        kl.b(this.i, "biz", "BSAEmpty");
        return "scheme_failed";
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005a A[Catch: all -> 0x00ae, TryCatch #2 {all -> 0x00ae, blocks: (B:12:0x0023, B:14:0x002b, B:17:0x0033, B:20:0x003c, B:22:0x0040, B:25:0x004d, B:26:0x0056, B:28:0x005a, B:29:0x005c, B:31:0x0066, B:72:0x0052), top: B:11:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0066 A[Catch: all -> 0x00ae, TRY_LEAVE, TryCatch #2 {all -> 0x00ae, blocks: (B:12:0x0023, B:14:0x002b, B:17:0x0033, B:20:0x003c, B:22:0x0040, B:25:0x004d, B:26:0x0056, B:28:0x005a, B:29:0x005c, B:31:0x0066, B:72:0x0052), top: B:11:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String c(java.lang.String r10, boolean r11) {
        /*
            r9 = this;
            java.lang.String r0 = ""
            r1 = 0
            kr r2 = defpackage.kr.a()     // Catch: java.lang.Throwable -> Lb4
            java.util.List r2 = r2.k()     // Catch: java.lang.Throwable -> Lb4
            kr r3 = defpackage.kr.a()     // Catch: java.lang.Throwable -> Lb4
            boolean r3 = r3.o     // Catch: java.lang.Throwable -> Lb4
            if (r3 == 0) goto L15
            if (r2 != 0) goto L17
        L15:
            java.util.List<kr$e> r2 = defpackage.kg.d     // Catch: java.lang.Throwable -> Lb4
        L17:
            lv r3 = r9.i     // Catch: java.lang.Throwable -> Lb4
            android.app.Activity r4 = r9.e     // Catch: java.lang.Throwable -> Lb4
            md$d r2 = defpackage.md.a(r3, r4, r2)     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r3 = "failed"
            if (r2 == 0) goto Lb3
            lv r4 = r9.i     // Catch: java.lang.Throwable -> Lae
            boolean r4 = r2.c(r4)     // Catch: java.lang.Throwable -> Lae
            if (r4 != 0) goto Lb3
            boolean r4 = r2.e()     // Catch: java.lang.Throwable -> Lae
            if (r4 == 0) goto L33
            goto Lb3
        L33:
            android.content.pm.PackageInfo r4 = r2.e     // Catch: java.lang.Throwable -> Lae
            boolean r4 = defpackage.md.be_(r4)     // Catch: java.lang.Throwable -> Lae
            if (r4 == 0) goto L3c
            return r3
        L3c:
            android.content.pm.PackageInfo r3 = r2.e     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto L52
            java.lang.String r3 = "com.eg.android.AlipayGphone"
            android.content.pm.PackageInfo r4 = r2.e     // Catch: java.lang.Throwable -> Lae
            java.lang.String r4 = r4.packageName     // Catch: java.lang.Throwable -> Lae
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto L4d
            goto L52
        L4d:
            android.content.pm.PackageInfo r3 = r2.e     // Catch: java.lang.Throwable -> Lae
            java.lang.String r0 = r3.packageName     // Catch: java.lang.Throwable -> Lae
            goto L56
        L52:
            java.lang.String r0 = defpackage.md.a()     // Catch: java.lang.Throwable -> Lae
        L56:
            android.content.pm.PackageInfo r3 = r2.e     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto L5c
            android.content.pm.PackageInfo r1 = r2.e     // Catch: java.lang.Throwable -> Lae
        L5c:
            kr r3 = defpackage.kr.a()     // Catch: java.lang.Throwable -> Lae
            java.lang.String r3 = r3.i()     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto Lc3
            int r4 = r3.length()     // Catch: java.lang.Throwable -> Lae
            if (r4 <= 0) goto Lc3
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lc3
            r4.<init>(r3)     // Catch: java.lang.Throwable -> Lc3
            org.json.JSONObject r3 = r4.optJSONObject(r0)     // Catch: java.lang.Throwable -> Lc3
            if (r3 == 0) goto Lc3
            int r4 = r3.length()     // Catch: java.lang.Throwable -> Lc3
            if (r4 <= 0) goto Lc3
            java.util.Iterator r4 = r3.keys()     // Catch: java.lang.Throwable -> Lc3
        L81:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> Lc3
            if (r5 == 0) goto Lc3
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> Lc3
            int r6 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Throwable -> Lc3
            if (r1 == 0) goto L81
            int r7 = r1.versionCode     // Catch: java.lang.Throwable -> Lc3
            if (r7 < r6) goto L81
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lc3
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lc3
            kr r6 = defpackage.kr.a()     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lc3
            android.app.Activity r7 = r9.e     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lc3
            boolean r5 = r6.b(r7, r5)     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lc3
            r9.g = r5     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lc3
            if (r5 == 0) goto L81
            goto Lc3
        Lae:
            r3 = move-exception
            r8 = r2
            r2 = r1
            r1 = r8
            goto Lb7
        Lb3:
            return r3
        Lb4:
            r2 = move-exception
            r3 = r2
            r2 = r1
        Lb7:
            lv r4 = r9.i
            java.lang.String r5 = "biz"
            java.lang.String r6 = "CheckClientSignEx"
            defpackage.kl.e(r4, r5, r6, r3)
            r8 = r2
            r2 = r1
            r1 = r8
        Lc3:
            lv r3 = r9.i
            boolean r3 = defpackage.md.e(r3)
            if (r11 != 0) goto Lcf
            boolean r11 = r9.g
            if (r11 == 0) goto Le0
        Lcf:
            if (r3 != 0) goto Le0
            android.app.Activity r11 = r9.e
            lv r3 = r9.i
            boolean r11 = a(r0, r11, r3)
            if (r11 == 0) goto Le0
            java.lang.String r10 = r9.aW_(r10, r0, r1)
            return r10
        Le0:
            java.lang.String r10 = r9.aX_(r10, r0, r1, r2)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.m.u.h.c(java.lang.String, boolean):java.lang.String");
    }

    private void c(md.d dVar) throws InterruptedException {
        PackageInfo packageInfo;
        if (dVar == null || (packageInfo = dVar.e) == null) {
            return;
        }
        String str = packageInfo.packageName;
        Intent intent = new Intent();
        intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
        try {
            this.e.startActivity(intent);
        } catch (Throwable th) {
            kl.e(this.i, "biz", "StartLaunchAppTransEx", th);
        }
        Thread.sleep(200L);
    }

    private String aW_(String str, String str2, PackageInfo packageInfo) {
        String str3 = packageInfo != null ? packageInfo.versionName : "";
        ma.d("mspl", "pay payInvokeAct");
        kl.a(this.i, "biz", "PgWltVer", str2 + "|" + str3);
        Activity activity = this.e;
        lv lvVar = this.i;
        kl.c(activity, lvVar, str, lvVar.b);
        return d(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018a A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String aX_(java.lang.String r9, java.lang.String r10, android.content.pm.PackageInfo r11, md.d r12) {
        /*
            Method dump skipped, instructions count: 395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.m.u.h.aX_(java.lang.String, java.lang.String, android.content.pm.PackageInfo, md$d):java.lang.String");
    }

    public static boolean a(String str, Context context, lv lvVar) {
        try {
            Intent intent = new Intent();
            intent.setClassName(str, "com.alipay.android.app.flybird.ui.window.FlyBirdWindowActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) != null) {
                return true;
            }
            kl.b(lvVar, "biz", "BSADetectFail");
            return false;
        } catch (Throwable th) {
            kl.e(lvVar, "biz", "BSADetectFail", th);
            return false;
        }
    }

    private String c(String str, String str2) {
        String str3;
        String str4;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String a2 = md.a(32);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        kl.a(this.i, "biz", "BSPStart", a2 + "|" + elapsedRealtime);
        lv.e.e(this.i, a2);
        AlipayResultActivity.e.put(a2, new c(countDownLatch));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sourcePid", Binder.getCallingPid());
            jSONObject.put("external_info", str);
            jSONObject.put(SdkCfgSha256Record.PKGNAME, this.e.getPackageName());
            jSONObject.put("session", a2);
            String encodeToString = Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 2);
            Uri.Builder appendQueryParameter = new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", "20000125");
            appendQueryParameter.appendQueryParameter("mqpSchemePay", encodeToString);
            try {
                HashMap<String, String> b2 = lv.b(this.i);
                b2.put("ts_scheme", String.valueOf(elapsedRealtime));
                appendQueryParameter.appendQueryParameter("mqpLoc", new JSONObject(b2).toString());
            } catch (Throwable th) {
                kl.e(this.i, "biz", "BSPLocEx", th);
            }
            String uri = appendQueryParameter.build().toString();
            Intent intent = new Intent();
            intent.setPackage(str2);
            intent.addFlags(268435456);
            intent.setData(Uri.parse(uri));
            Activity activity = this.e;
            lv lvVar = this.i;
            kl.c(activity, lvVar, str, lvVar.b);
            this.e.startActivity(intent);
            ma.d("mspl", "pay scheme waiting " + uri);
            countDownLatch.await();
            str3 = this.h;
            try {
                str4 = mc.c(this.i, str3).get("resultStatus");
                if (str4 == null) {
                    str4 = Constants.NULL;
                }
            } catch (Throwable th2) {
                kl.e(this.i, "biz", "BSPStatEx", th2);
                str4 = "unknown";
            }
            kl.b(this.i, "biz", "BSPDone-" + str4);
        } catch (InterruptedException e2) {
            kl.e(this.i, "biz", "BSPWaiting", e2);
            return kh.c(com.alipay.sdk.m.j.c.PAY_WAITTING.b(), com.alipay.sdk.m.j.c.PAY_WAITTING.a(), "");
        } catch (Throwable th3) {
            kl.e(this.i, "biz", "BSPEx", th3);
        }
        if (!TextUtils.isEmpty(str3)) {
            return str3;
        }
        kl.b(this.i, "biz", "BSPEmpty");
        return "scheme_failed";
    }

    public static boolean b(String str, Context context, lv lvVar) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.setClassName(str, "com.alipay.android.msp.ui.views.MspContainerActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) != null) {
                return true;
            }
            kl.b(lvVar, "biz", "BSPDetectFail");
            return false;
        } catch (Throwable th) {
            kl.e(lvVar, "biz", "BSPDetectFail", th);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.util.Pair<java.lang.String, java.lang.Boolean> aV_(java.lang.String r18, java.lang.String r19, defpackage.lv r20) {
        /*
            Method dump skipped, instructions count: 895
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.m.u.h.aV_(java.lang.String, java.lang.String, lv):android.util.Pair");
    }

    public void a() {
        this.e = null;
        this.f873a = null;
    }
}
