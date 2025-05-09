package com.huawei.watchface;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class as {
    private static as b;
    private a c;
    private at d;
    private IBaseResponseCallback f;

    /* renamed from: a, reason: collision with root package name */
    public static volatile Executor f10906a = Executors.newFixedThreadPool(2);
    private static final Object e = new Object();

    private as() {
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.as$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                as.this.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        String commonCountryCode = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode();
        try {
            if (!HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea() && "CN".equals(commonCountryCode)) {
                String a2 = a(Environment.getApplicationContext(), "SupportOnlineInfo");
                if (!TextUtils.isEmpty(a2)) {
                    this.d = at.f(a2);
                }
            } else {
                String a3 = a(Environment.getApplicationContext(), "overseasSupportOnlineInfo");
                if (!TextUtils.isEmpty(a3)) {
                    this.d = at.f(a3);
                }
            }
        } catch (Exception e2) {
            HwLog.e("OnlineStateManager", "OnlineStateManager Exception:" + HwLog.printException(e2));
        }
    }

    public at a() {
        return this.d;
    }

    public static as b() {
        as asVar;
        synchronized (as.class) {
            if (b == null) {
                b = new as();
            }
            asVar = b;
        }
        return asVar;
    }

    public void c() {
        synchronized (e) {
            a aVar = this.c;
            if (aVar != null) {
                if (aVar.isCancelled()) {
                    this.c.cancel(true);
                }
                this.c = null;
            }
            try {
                a aVar2 = new a(this);
                this.c = aVar2;
                aVar2.executeOnExecutor(f10906a, new Void[0]);
            } catch (Exception e2) {
                HwLog.e("OnlineStateManager", "queryOnlineState Exception:" + HwLog.printException(e2));
            }
        }
    }

    static class a extends AsyncTask<Void, at, at> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<as> f10907a;

        public a(as asVar) {
            this.f10907a = new WeakReference<>(asVar);
        }

        private as b() {
            return this.f10907a.get();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public at doInBackground(Void... voidArr) {
            return a();
        }

        public at a() {
            if (b() == null) {
                return null;
            }
            return b().d();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(at atVar) {
            super.onPostExecute(atVar);
            if (b() == null) {
                return;
            }
            b().a(atVar);
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        this.f = iBaseResponseCallback;
    }

    public void a(at atVar) {
        IBaseResponseCallback iBaseResponseCallback = this.f;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.onResponse(0, atVar);
        }
        if (atVar == null) {
            HwLog.d("OnlineStateManager", "supportType == null");
            return;
        }
        at atVar2 = this.d;
        if (atVar2 == null || !atVar2.equals(atVar)) {
            this.d = atVar;
        }
    }

    public at d() {
        if (WatchFaceHttpUtil.m() == null) {
            HwLog.i("OnlineStateManager", "getSupportTypeFromService null == hostName");
            return new at();
        }
        bm bmVar = new bm();
        int i = 3;
        while (true) {
            if (i <= 0) {
                break;
            }
            i--;
            at c = bmVar.c(bmVar.a(""));
            this.d = c;
            if (c != null) {
                HwLog.i("OnlineStateManager", "request state success reTryCount = " + i);
                break;
            }
            if (!CommonUtils.f()) {
                break;
            }
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e2) {
                HwLog.e("OnlineStateManager", "mJsonFileAsyncTask InterruptedException error:" + HwLog.printException((Exception) e2));
            }
        }
        return this.d;
    }

    public static void a(Context context, String str, String str2) {
        synchronized (as.class) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String a2 = ao.a(str2, "storagePw");
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            SharedPreferences.Editor edit = dp.a(context, "supportonline").edit();
            edit.putString("onlineState_" + str, a2);
            edit.commit();
        }
    }

    public static String a(Context context, String str) {
        synchronized (as.class) {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            SharedPreferences a2 = dp.a(context, "supportonline");
            String str2 = "";
            if (a2 != null) {
                if (a2.contains("onlineState_" + str)) {
                    str2 = ao.b(a2.getString("onlineState_" + str, ""), "storagePw");
                }
            }
            return str2;
        }
    }

    public void e() {
        HwLog.d("OnlineStateManager", "OnlineStateManager clear");
        this.f = null;
        a aVar = this.c;
        if (aVar != null) {
            aVar.cancel(true);
            this.c = null;
        }
    }
}
