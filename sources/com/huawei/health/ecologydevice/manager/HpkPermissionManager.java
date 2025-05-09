package com.huawei.health.ecologydevice.manager;

import android.text.TextUtils;
import com.huawei.common.util.Utils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.manager.HpkPermissionManager;
import com.huawei.health.ecologydevice.networkclient.AuthorizationData;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.networkclient.OnRequestCallBack;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.h5pro.webkit.QuickPackageManager;
import com.huawei.health.main.api.CloudAuthApi;
import com.huawei.hms.hihealth.data.Scopes;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.ehp;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class HpkPermissionManager {
    private String b = "";
    private String d;

    public interface PermissionAuthenticationListener {
        void onResult(boolean z);
    }

    public HpkPermissionManager(String str) {
        this.d = str;
    }

    public void b(String str, PermissionAuthenticationListener permissionAuthenticationListener) {
        LogUtil.a("FittingsPermissionManager", "permissionAuthentication");
        QuickPackageManager quickPackageManager = new QuickPackageManager(BaseApplication.e());
        String str2 = "com.huawei.health.device." + this.d;
        H5ProAppInfo appInfo = quickPackageManager.getAppInfo(str2);
        Object[] objArr = new Object[2];
        objArr[0] = "h5ProAppInfo is null? ";
        objArr[1] = Boolean.valueOf(appInfo == null);
        LogUtil.a("FittingsPermissionManager", objArr);
        if (appInfo != null) {
            String appId = appInfo.getAppId();
            this.b = appId;
            ReleaseLogUtil.e("DEVMGR_FittingsPermissionManager", "appId:", appId);
            if (TextUtils.isEmpty(this.b)) {
                permissionAuthenticationListener.onResult(d(this.d));
                return;
            } else {
                d(str, permissionAuthenticationListener);
                return;
            }
        }
        c(quickPackageManager, str2, str, permissionAuthenticationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str) {
        return SharedPreferenceManager.a("ecology_device_module", str, false);
    }

    private void c(final QuickPackageManager quickPackageManager, final String str, final String str2, final PermissionAuthenticationListener permissionAuthenticationListener) {
        LogUtil.a("FittingsPermissionManager", "downLoadHpk");
        quickPackageManager.installApp(str2, new H5ProAppLoader.H5ProPreloadCbk() { // from class: com.huawei.health.ecologydevice.manager.HpkPermissionManager.3
            @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
            public void onSuccess() {
                LogUtil.a("FittingsPermissionManager", "getAppIdWithDownloadHpk download hpk success");
                H5ProAppInfo appInfo = quickPackageManager.getAppInfo(str2);
                if (appInfo == null) {
                    LogUtil.h("FittingsPermissionManager", "getAppIdWithDownloadHpk downSuccess but h5ProAppInfo is null");
                    PermissionAuthenticationListener permissionAuthenticationListener2 = permissionAuthenticationListener;
                    HpkPermissionManager hpkPermissionManager = HpkPermissionManager.this;
                    permissionAuthenticationListener2.onResult(hpkPermissionManager.d(hpkPermissionManager.d));
                    return;
                }
                HpkPermissionManager.this.b = appInfo.getAppId();
                ReleaseLogUtil.e("DEVMGR_FittingsPermissionManager", "appId:", HpkPermissionManager.this.b);
                if (!TextUtils.isEmpty(HpkPermissionManager.this.b)) {
                    HpkPermissionManager.this.d(str, permissionAuthenticationListener);
                    return;
                }
                PermissionAuthenticationListener permissionAuthenticationListener3 = permissionAuthenticationListener;
                HpkPermissionManager hpkPermissionManager2 = HpkPermissionManager.this;
                permissionAuthenticationListener3.onResult(hpkPermissionManager2.d(hpkPermissionManager2.d));
            }

            @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
            public void onFailure(int i, String str3) {
                LogUtil.b("FittingsPermissionManager", "getAppIdWithDownloadHpk download hpk fail ", str3);
                PermissionAuthenticationListener permissionAuthenticationListener2 = permissionAuthenticationListener;
                HpkPermissionManager hpkPermissionManager = HpkPermissionManager.this;
                permissionAuthenticationListener2.onResult(hpkPermissionManager.d(hpkPermissionManager.d));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str, final PermissionAuthenticationListener permissionAuthenticationListener) {
        ReleaseLogUtil.e("DEVMGR_FittingsPermissionManager", "checkPlatformPermission");
        HashMap hashMap = new HashMap(10);
        hashMap.put("nsp_svc", "nsp.scope.app.get");
        hashMap.put("nsp_fmt", "json");
        hashMap.put("appid", this.b);
        hashMap.put("type", "2");
        new HealthEngineRequestManager.RequestParamsBuilder().setTag("FittingsPermissionManager").setUrl(Utils.getUrl("com.huawei.health", "domainScopeOauth")).setQueryParams(hashMap).setHeaderParam(new HashMap(10)).setMethod("GET").setRequestCallBack(new OnRequestCallBack<AuthorizationData>() { // from class: com.huawei.health.ecologydevice.manager.HpkPermissionManager.1
            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(AuthorizationData authorizationData) {
                LogUtil.a("FittingsPermissionManager", "checkPlatformPermission success");
                if (HpkPermissionManager.this.d(authorizationData.getScopes(), str)) {
                    HpkPermissionManager.this.d(permissionAuthenticationListener);
                    return;
                }
                LogUtil.a("FittingsPermissionManager", "The user does not apply for permission.");
                PermissionAuthenticationListener permissionAuthenticationListener2 = permissionAuthenticationListener;
                HpkPermissionManager hpkPermissionManager = HpkPermissionManager.this;
                permissionAuthenticationListener2.onResult(hpkPermissionManager.d(hpkPermissionManager.d));
            }

            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c("DEVMGR_FittingsPermissionManager", "checkPlatformPermission fail:", Integer.valueOf(i));
                PermissionAuthenticationListener permissionAuthenticationListener2 = permissionAuthenticationListener;
                HpkPermissionManager hpkPermissionManager = HpkPermissionManager.this;
                permissionAuthenticationListener2.onResult(hpkPermissionManager.d(hpkPermissionManager.d));
            }
        }).create().request();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(List<AuthorizationData.Scopes> list, String str) {
        LogUtil.a("FittingsPermissionManager", "checkScopeUrl");
        if (koq.b(list)) {
            return false;
        }
        Iterator<AuthorizationData.Scopes> it = list.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().getUri())) {
                LogUtil.a("FittingsPermissionManager", "checkScopeUrl Has HEALTH_KIT_EXTEND_SPORT_READ_SCOPE_URL");
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final PermissionAuthenticationListener permissionAuthenticationListener) {
        LogUtil.a("DEVMGR_FittingsPermissionManager", "checkAuthorizationStatus");
        final CloudAuthApi cloudAuthApi = (CloudAuthApi) Services.c("Main", CloudAuthApi.class);
        final String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1015);
        cloudAuthApi.constructInstance(BaseApplication.e(), accountInfo);
        final String scopeLangItemUrl = cloudAuthApi.getScopeLangItemUrl(this.b, cloudAuthApi.getLang());
        LogUtil.a("FittingsPermissionManager", "Get cloudAuthApi in ThirdPartServiceActivity: ", scopeLangItemUrl);
        ThreadPoolManager.d().execute(new Runnable() { // from class: dck
            @Override // java.lang.Runnable
            public final void run() {
                HpkPermissionManager.this.a(cloudAuthApi, accountInfo, scopeLangItemUrl, permissionAuthenticationListener);
            }
        });
    }

    public /* synthetic */ void a(CloudAuthApi cloudAuthApi, String str, String str2, PermissionAuthenticationListener permissionAuthenticationListener) {
        ehp scopeLangItem = cloudAuthApi.getScopeLangItem(str, "GET", str2);
        if (scopeLangItem == null || scopeLangItem.a() == null) {
            LogUtil.h("FittingsPermissionManager", "scopeLangItem get null.");
            permissionAuthenticationListener.onResult(d(this.d));
            return;
        }
        Map<String, String> a2 = scopeLangItem.a();
        LogUtil.a("FittingsPermissionManager", "getScopeLangItem scopeLangItem:", scopeLangItem);
        boolean contains = a2.keySet().contains(Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        LogUtil.a("FittingsPermissionManager", "HEALTH_KIT_EXTEND_SPORT_READ_SCOPE_URL HasPermission: ", Boolean.valueOf(contains));
        permissionAuthenticationListener.onResult(contains);
    }
}
