package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.ntc;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class ntd {
    private static volatile ntd e;
    private volatile boolean c = true;

    private ntd() {
    }

    public static ntd b() {
        if (e == null) {
            synchronized (ntd.class) {
                if (e == null) {
                    e = new ntd();
                }
            }
        }
        return e;
    }

    public void cMD_(final View view, final boolean z) {
        boolean o = Utils.o();
        if (view == null || o || !this.c) {
            Object[] objArr = new Object[6];
            objArr[0] = "isViewNull: ";
            objArr[1] = Boolean.valueOf(view == null);
            objArr[2] = ", isOversea: ";
            objArr[3] = Boolean.valueOf(o);
            objArr[4] = ", mHasFileDownLoaded: ";
            objArr[5] = Boolean.valueOf(this.c);
            LogUtil.a("OneClickGrayingManager", objArr);
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: ntd.1
            @Override // java.lang.Runnable
            public void run() {
                if (ntd.this.m()) {
                    if (!z || ntd.this.g()) {
                        nrn.cKd_(view);
                    } else {
                        nrn.cKe_(view);
                    }
                }
            }
        });
    }

    public void cMC_(final Dialog dialog) {
        boolean o = Utils.o();
        if (dialog == null || o || !this.c) {
            Object[] objArr = new Object[6];
            objArr[0] = "isDialogNull: ";
            objArr[1] = Boolean.valueOf(dialog == null);
            objArr[2] = ", isOversea: ";
            objArr[3] = Boolean.valueOf(o);
            objArr[4] = ", mHasFileDownLoaded: ";
            objArr[5] = Boolean.valueOf(this.c);
            LogUtil.a("OneClickGrayingManager", objArr);
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: ntd.4
            @Override // java.lang.Runnable
            public void run() {
                if (ntd.this.m()) {
                    if (ntd.this.g()) {
                        nrn.cKd_(dialog.getWindow().getDecorView());
                    } else {
                        nrn.cKe_(dialog.getWindow().getDecorView());
                    }
                }
            }
        });
    }

    public void cME_(final Fragment fragment, final View view) {
        boolean o = Utils.o();
        if (fragment == null || view == null || o || !this.c) {
            Object[] objArr = new Object[8];
            objArr[0] = "isFragmentNull: ";
            objArr[1] = Boolean.valueOf(fragment == null);
            objArr[2] = ", isViewNull: ";
            objArr[3] = Boolean.valueOf(view == null);
            objArr[4] = ", isOversea: ";
            objArr[5] = Boolean.valueOf(o);
            objArr[6] = ", mHasFileDownLoaded: ";
            objArr[7] = Boolean.valueOf(this.c);
            LogUtil.a("OneClickGrayingManager", objArr);
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: ntd.2
            @Override // java.lang.Runnable
            public void run() {
                if (ntd.this.m()) {
                    if (ntd.this.c(fragment)) {
                        nrn.cKd_(view);
                    } else {
                        nrn.cKe_(view);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Fragment fragment) {
        if (fragment == null) {
            LogUtil.a("OneClickGrayingManager", "fragment is null");
            return false;
        }
        List<Integer> j = j();
        if (koq.b(j)) {
            LogUtil.a("OneClickGrayingManager", "scopeList is empty in isNeedGray");
            return false;
        }
        List<String> e2 = e(j);
        return (e2.contains("HomeFragment") && (fragment.toString().contains("HomeFragment") || (fragment.toString().contains("InnerMarketingFragment") && i()))) || (e2.contains("SportEntranceFragment") && (fragment.toString().contains(String.valueOf(4040)) || (fragment.toString().contains("InnerMarketingFragment") && f())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        List<Integer> j = j();
        if (!koq.b(j)) {
            return (i() && j.contains(1)) || (f() && j.contains(2));
        }
        LogUtil.a("OneClickGrayingManager", "scopeList is empty in isNeedGray");
        return false;
    }

    private boolean i() {
        Activity wa_ = BaseApplication.wa_();
        if (!(wa_ instanceof BaseActivity) || !wa_.getLocalClassName().contains("MainActivity")) {
            LogUtil.a("OneClickGrayingManager", "topActivity is not MainActivity");
            return false;
        }
        for (Fragment fragment : new ArrayList(((BaseActivity) wa_).getSupportFragmentManager().getFragments())) {
            if (fragment != null && fragment.getUserVisibleHint() && fragment.toString().contains("HomeFragment")) {
                return true;
            }
        }
        return false;
    }

    private boolean f() {
        Activity wa_ = BaseApplication.wa_();
        if (!(wa_ instanceof BaseActivity) || !wa_.getLocalClassName().contains("MainActivity")) {
            LogUtil.a("OneClickGrayingManager", "topActivity is not MainActivity");
            return false;
        }
        for (Fragment fragment : new ArrayList(((BaseActivity) wa_).getSupportFragmentManager().getFragments())) {
            if (fragment != null && fragment.getUserVisibleHint() && fragment.toString().contains("SportEntranceFragment")) {
                for (Fragment fragment2 : new ArrayList(fragment.getChildFragmentManager().getFragments())) {
                    if (fragment2 != null && fragment2.toString().contains(String.valueOf(4040)) && fragment2.getUserVisibleHint()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<String> e(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        if (list.contains(1)) {
            arrayList.add("HomeFragment");
        }
        if (list.contains(2)) {
            arrayList.add("SportEntranceFragment");
        }
        return arrayList;
    }

    private List<Integer> j() {
        ntc.e h = h();
        if (h == null || h.b() == null) {
            LogUtil.a("OneClickGrayingManager", "pagesTypeList is null in getScopeList");
            return new ArrayList();
        }
        return h.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        ntc.e h = h();
        if (h == null) {
            LogUtil.a("OneClickGrayingManager", "bean is null in grayingConfig");
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("OneClickGrayingManager", "grayingConfig: ", h.toString());
        ReleaseLogUtil.e("TimeEat_OneClickGrayingManager", "currentTime: ", Long.valueOf(currentTimeMillis), "startTime: ", Long.valueOf(h.d()), " endTime, ", Long.valueOf(h.c()), ", clientType: ", Integer.valueOf(h.a()));
        return currentTimeMillis >= h.d() && currentTimeMillis <= h.c() && h.a() == 0;
    }

    private ntc.e h() {
        ntc d = d();
        if (d == null) {
            LogUtil.a("OneClickGrayingManager", "bean is null in getGrayingConfig");
            this.c = false;
            return null;
        }
        ntc.b a2 = d.a();
        if (a2 == null) {
            LogUtil.a("OneClickGrayingManager", "globalConfig is null in getGrayingConfig");
            return null;
        }
        ntc.e e2 = a2.e();
        if (e2 != null) {
            return e2;
        }
        LogUtil.a("OneClickGrayingManager", "grayingConfig is null in getGrayingConfig");
        return null;
    }

    private ntc d() {
        String b = SharedPreferenceManager.b(BaseApplication.e(), "ONE_CLICK_GRAYING", "ONE_CLICK_GRAYING");
        LogUtil.a("OneClickGrayingManager", "configStr: ", b);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            return (ntc) nrv.b(b, ntc.class);
        } catch (JsonSyntaxException | NumberFormatException unused) {
            LogUtil.a("OneClickGrayingManager", "get configs exception");
            return null;
        }
    }

    public void a() {
        String url = GRSManager.a(BaseApplication.e()).getUrl("marketingUrl");
        if (LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).isBrowseMode()) {
            e(url + "/positionservice/v1/globalConfigAnon");
            return;
        }
        e(url + "/positionservice/v1/globalConfig");
    }

    private void e(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(c()));
        lqi.d().c(str, e(), hashMap, String.class, new ResultCallback<String>() { // from class: ntd.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str2) {
                if (TextUtils.isEmpty(str2)) {
                    LogUtil.h("OneClickGrayingManager", "response is empty");
                    return;
                }
                LogUtil.a("OneClickGrayingManager", "healthCloudRequest onSuccess -> " + str2);
                SharedPreferenceManager.e(BaseApplication.e(), "ONE_CLICK_GRAYING", "ONE_CLICK_GRAYING", str2, new StorageParams());
                ntd.this.c = true;
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a("OneClickGrayingManager", "healthCloudRequest onFailure -> " + (th == null ? "request failed" : th.getMessage()));
            }
        });
    }

    private int c() {
        if (CommonUtil.bh()) {
            return 1;
        }
        if (CommonUtil.bf()) {
            return 2;
        }
        return nsn.ae(BaseApplication.e()) ? 5 : 3;
    }

    public static HashMap<String, String> e() {
        Context context = com.huawei.hwcommonmodel.application.BaseApplication.getContext();
        HashMap<String, String> hashMap = new HashMap<>(16);
        if (!LoginInit.getInstance(context).isBrowseMode()) {
            hashMap.put("x-huid", LoginInit.getInstance(context).getAccountInfo(1011));
            hashMap.put(CloudParamKeys.X_TOKEN_TYPE, String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            String accountInfo = LoginInit.getInstance(context).getAccountInfo(1008);
            if (!TextUtils.isEmpty(accountInfo)) {
                hashMap.put(CloudParamKeys.X_TOKEN, accountInfo);
            }
        }
        hashMap.put("Content-type", "application/json");
        hashMap.put(CloudParamKeys.X_CLIENT_VERSION, CommonUtil.c(context));
        hashMap.put(CloudParamKeys.X_SITE_ID, LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1009));
        hashMap.put(CloudParamKeys.X_APP_ID, com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage());
        String deviceId = LoginInit.getInstance(context).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put(CloudParamKeys.X_DEVICE_ID, deviceId);
        hashMap.put(CloudParamKeys.X_DEVICE_TYPE, LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getDeviceType());
        hashMap.put(CloudParamKeys.X_TS, String.valueOf(System.currentTimeMillis()));
        hashMap.put(CloudParamKeys.X_APP_TYPE, String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("x-caller-trace-id", String.valueOf(System.currentTimeMillis()) + Math.random());
        return hashMap;
    }
}
