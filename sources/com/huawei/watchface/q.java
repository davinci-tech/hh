package com.huawei.watchface;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceListBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.LanguageUtils;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes7.dex */
public class q {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11162a = new Object();
    private static q b;
    private Context c;
    private HwWatchFaceBtManager d;

    private q(Context context) {
        this.c = context;
    }

    public static q a() {
        q qVar;
        synchronized (f11162a) {
            if (b == null) {
                b = new q(Environment.getApplicationContext());
            }
            qVar = b;
        }
        return qVar;
    }

    public void b() {
        HwLog.i("HwWatchFaceEntranceManager", "getRecommendWatchFace");
        if (this.d == null) {
            HwWatchFaceBtManager hwWatchFaceBtManager = HwWatchFaceBtManager.getInstance(this.c);
            this.d = hwWatchFaceBtManager;
            hwWatchFaceBtManager.registerHealthResponseListener();
        }
        if (this.d.getConnectWatchDeviceInfo() == null) {
            HwLog.w("HwWatchFaceEntranceManager", "getRecommendWatchFace current device info is null return");
        } else {
            ThreadPoolManager.getInstance().tagExecute("HwWatchFaceEntranceManager", new Runnable() { // from class: com.huawei.watchface.q.1
                @Override // java.lang.Runnable
                public void run() {
                    q.this.c();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        HwLog.i("HwWatchFaceEntranceManager", "getDeviceAndWatchFaceInfo() is called");
        HwWatchFaceBtManager hwWatchFaceBtManager = this.d;
        if (hwWatchFaceBtManager == null) {
            return;
        }
        hwWatchFaceBtManager.getDeviceInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.q.2
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i, Object obj) {
                q.this.a(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 101) {
            HwLog.i("HwWatchFaceEntranceManager", "dealResponse getDeviceInfoForUi success");
            if (HwWatchFaceBtManager.getInstance(this.c).getConnectWatchDeviceInfo() == null) {
                HwLog.w("HwWatchFaceEntranceManager", "dealResponse current device info is null");
                return;
            }
            a(HwWatchFaceApi.getInstance(this.c).getDeviceIdentify());
            HwWatchFaceBtManager hwWatchFaceBtManager = this.d;
            if (hwWatchFaceBtManager == null) {
                return;
            }
            hwWatchFaceBtManager.getWatchInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.q$$ExternalSyntheticLambda0
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public final void onResponse(int i2, Object obj) {
                    q.a(i2, obj);
                }
            });
            return;
        }
        HwLog.w("HwWatchFaceEntranceManager", "dealResponse getDeviceInfoForUi errorCode:" + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(int i, Object obj) {
        HwLog.i("HwWatchFaceEntranceManager", "dealResponse getWatchInfoForUi errorCode:" + i);
    }

    public void a(final String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("HwWatchFaceEntranceManager", "getAndSaveTopFace deviceIdentify is empty.");
        } else {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.q$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    q.this.b(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str) {
        String a2 = dp.a(this.c);
        String b2 = dp.b(this.c);
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.c).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            return;
        }
        String watchFaceMaxVersion = watchFaceSupportInfo.getWatchFaceMaxVersion();
        boolean h = dp.h(this.c);
        HwLog.i("HwWatchFaceEntranceManager", "getAndSaveTopFace savedVersion: " + b2 + ", recentVersion: " + watchFaceMaxVersion + "，needRefreshWatchFace：" + h);
        if (dp.c(this.c) && a2.equalsIgnoreCase(db.a(str)) && dp.f(this.c, LanguageUtils.a(false)) && !h && b2.equals(watchFaceMaxVersion)) {
            if (WatchFaceHttpUtil.b() == null) {
                d();
                return;
            } else {
                e();
                return;
            }
        }
        a(false);
    }

    private void d() {
        HwLog.i("HwWatchFaceEntranceManager", "getSignAndGetLocal() watchFaceSignBean is null.");
        bo boVar = new bo();
        if (boVar.c(boVar.c()) == null) {
            HwLog.w("HwWatchFaceEntranceManager", "getSignAndGetLocal watchFaceSignBean is null.");
        } else {
            e();
        }
    }

    private void e() {
        HwLog.i("HwWatchFaceEntranceManager", "getLocalWatchFace enter.");
        try {
            b((WatchFaceListBean) new Gson().fromJson(dp.d(this.c), WatchFaceListBean.class));
        } catch (JsonSyntaxException unused) {
            HwLog.e("HwWatchFaceEntranceManager", "getLocalWatchFace JsonSyntaxException");
            a(true);
        }
    }

    private void a(boolean z) {
        HwWatchFaceBtManager hwWatchFaceBtManager;
        HwLog.i("HwWatchFaceEntranceManager", "getTopWatchFace() useCache: " + z);
        if (z && (hwWatchFaceBtManager = this.d) != null) {
            String watchVersion = hwWatchFaceBtManager.getWatchVersion();
            if (TextUtils.isEmpty(watchVersion)) {
                HwLog.w("HwWatchFaceEntranceManager", "getTopWatchFace() watchVersion is empty.");
                return;
            }
            new ce(null).c();
            bt btVar = new bt();
            boolean booleanValue = btVar.c(btVar.a("")).booleanValue();
            dp.a(this.c, booleanValue);
            HwLog.i("HwWatchFaceEntranceManager", "getTopWatchFace isBihHotTest：" + booleanValue);
            br brVar = new br(booleanValue);
            WatchFaceListBean c = brVar.c(brVar.a(watchVersion));
            if (c == null) {
                HwLog.w("HwWatchFaceEntranceManager", "getTopWatchFace() getWatchFaceThread fail.");
                return;
            } else {
                a(c);
                return;
            }
        }
        HwLog.i("HwWatchFaceEntranceManager", "getTopWatchFace() watchFaceSignBean is null.");
        bo boVar = new bo();
        if (boVar.c(boVar.c()) == null) {
            HwLog.w("HwWatchFaceEntranceManager", "getTopWatchFace watchFaceSignBean is null.");
        } else {
            a(true);
        }
    }

    private void a(WatchFaceListBean watchFaceListBean) {
        if (watchFaceListBean == null) {
            HwLog.w("HwWatchFaceEntranceManager", "saveTopWatchFace watchFaceListBean is null return");
            return;
        }
        b(watchFaceListBean);
        try {
            dp.h(this.c, new Gson().toJson(watchFaceListBean));
        } catch (JsonIOException unused) {
            HwLog.e("HwWatchFaceEntranceManager", "saveTopWatchFace JsonIOException");
        }
        dp.e(this.c, new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(new Date()));
        if (HwWatchFaceBtManager.getInstance(this.c).getConnectWatchDeviceInfo() == null) {
            HwLog.w("HwWatchFaceEntranceManager", "current device info is null");
            return;
        }
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.c).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.w("HwWatchFaceEntranceManager", "getWatchFaceSupportInfo is null");
            return;
        }
        Context context = this.c;
        dp.c(context, db.a(HwWatchFaceApi.getInstance(context).getDeviceIdentify()));
        dp.d(this.c, watchFaceSupportInfo.getWatchFaceMaxVersion());
        dp.g(this.c, LanguageUtils.a(false));
        dp.b(this.c, false);
    }

    private void b(WatchFaceListBean watchFaceListBean) {
        HwLog.i("HwWatchFaceEntranceManager", "sendGetWatchFaceBroadcast() enter.");
        Intent intent = new Intent();
        intent.setAction("com.huawei.watchface.action.ACTION_WATCHFACE_LIST");
        intent.putExtra("watchFaceBeanList", watchFaceListBean);
        LocalBroadcastManager.getInstance(this.c).sendBroadcast(intent);
    }
}
