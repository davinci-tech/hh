package com.huawei.health.ecologydevice.manager;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.common.util.Utils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.fitness.datastruct.SettingStatusData;
import com.huawei.health.ecologydevice.manager.RopeCloudAuthManager;
import com.huawei.health.ecologydevice.networkclient.AuthorizationData;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.networkclient.OnRequestCallBack;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.h5pro.webkit.QuickPackageManager;
import com.huawei.health.main.api.CloudAuthApi;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hms.hihealth.data.Scopes;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.AchieveDataApi;
import defpackage.cez;
import defpackage.dcz;
import defpackage.dds;
import defpackage.dei;
import defpackage.dij;
import defpackage.dks;
import defpackage.ehp;
import defpackage.gnm;
import defpackage.koq;
import defpackage.mdd;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class RopeCloudAuthManager {
    private DonwHkpListen b;
    private Context c;
    private int[] d;
    private String f;
    private HashMap<Integer, e> g;
    private String h;
    private int[] j;

    /* renamed from: a, reason: collision with root package name */
    private String f2304a = "";
    private boolean e = false;

    public interface DonwHkpListen {
        void downHpkSuccessReSend();
    }

    public RopeCloudAuthManager(Context context, String str, String str2) {
        HashMap<Integer, e> hashMap = new HashMap<>();
        this.g = hashMap;
        this.c = context;
        this.h = str;
        this.f = str2;
        hashMap.put(1, new e(1));
        this.g.put(2, new e(2));
    }

    public void c() {
        this.f2304a = "";
        this.b = null;
    }

    public String d(String str, DonwHkpListen donwHkpListen) {
        LogUtil.a("RopeCloudAuthManager", "downLoadHpk");
        if (!"true".equals(dij.c("isSupportWeight", str))) {
            LogUtil.a("RopeCloudAuthManager", "device isSupportWeight is false");
            return this.f2304a;
        }
        if (!TextUtils.isEmpty(this.f2304a)) {
            this.e = true;
            return this.f2304a;
        }
        this.b = donwHkpListen;
        final QuickPackageManager quickPackageManager = new QuickPackageManager(this.c);
        final String str2 = "com.huawei.health.device." + str;
        quickPackageManager.installApp(str2, new H5ProAppLoader.H5ProPreloadCbk() { // from class: com.huawei.health.ecologydevice.manager.RopeCloudAuthManager.3
            @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
            public void onSuccess() {
                LogUtil.a("RopeCloudAuthManager", "download hpk success");
                RopeCloudAuthManager.this.e(quickPackageManager.getAppInfo(str2));
            }

            @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
            public void onFailure(int i, String str3) {
                LogUtil.b("RopeCloudAuthManager", "download hpk fail ", str3);
            }
        });
        return this.f2304a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(H5ProAppInfo h5ProAppInfo) {
        DonwHkpListen donwHkpListen;
        if (h5ProAppInfo == null) {
            LogUtil.h("RopeCloudAuthManager", "downHpkSuccess h5ProAppInfo is null");
            return;
        }
        this.f2304a = h5ProAppInfo.getAppId();
        if (!this.e || (donwHkpListen = this.b) == null) {
            return;
        }
        this.e = false;
        donwHkpListen.downHpkSuccessReSend();
    }

    public void d() {
        b(false, true);
    }

    public void d(boolean z) {
        if (TextUtils.isEmpty(this.f2304a)) {
            LogUtil.h("RopeCloudAuthManager", "getUserInfoFromRope mAppId is null");
            this.e = true;
            return;
        }
        boolean e2 = e(this.f2304a);
        LogUtil.a("RopeCloudAuthManager", "onDeviceStateChanged isConnected:", Boolean.valueOf(z), " mIsUseDenyAuthorization:", Boolean.valueOf(e2));
        if (z && e2) {
            a();
        }
    }

    private void a() {
        if (dij.g(this.h)) {
            dds.c().d(2, 0, new int[]{GlMapUtil.DEVICE_DISPLAY_DPI_HIGH});
        } else {
            dds.c().d(2, 0, new int[]{256});
        }
    }

    public void c(boolean z, SettingStatusData settingStatusData) {
        if (settingStatusData == null) {
            LogUtil.a("RopeCloudAuthManager", "settingStatusData is null; isConnect = ", Boolean.valueOf(z));
            if (z) {
                a();
                return;
            } else {
                b(false, false);
                return;
            }
        }
        e(settingStatusData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final boolean z, final boolean z2) {
        if (TextUtils.isEmpty(d(this.h, this.b))) {
            LogUtil.a("RopeCloudAuthManager", "appId is null");
            this.e = true;
            return;
        }
        final CloudAuthApi cloudAuthApi = (CloudAuthApi) Services.c("Main", CloudAuthApi.class);
        final String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1015);
        cloudAuthApi.constructInstance(this.c, accountInfo);
        final String scopeLangItemUrl = cloudAuthApi.getScopeLangItemUrl(this.f2304a, cloudAuthApi.getLang());
        LogUtil.a("RopeCloudAuthManager", "Get cloudAuthApi in ThirdPartServiceActivity: ", scopeLangItemUrl);
        ThreadPoolManager.d().execute(new Runnable() { // from class: ddl
            @Override // java.lang.Runnable
            public final void run() {
                RopeCloudAuthManager.this.e(cloudAuthApi, accountInfo, scopeLangItemUrl, z, z2);
            }
        });
    }

    public /* synthetic */ void e(CloudAuthApi cloudAuthApi, String str, String str2, boolean z, boolean z2) {
        ehp scopeLangItem = cloudAuthApi.getScopeLangItem(str, "GET", str2);
        boolean z3 = true;
        if (scopeLangItem == null || scopeLangItem.a() == null) {
            LogUtil.h("RopeCloudAuthManager", "scopeLangItem get null.");
            b(z, z2, false);
            return;
        }
        Map<String, String> a2 = scopeLangItem.a();
        LogUtil.a("RopeCloudAuthManager", "getScopeLangItem scopeLangItem:", scopeLangItem);
        Set<String> keySet = a2.keySet();
        this.g.get(1).b(keySet.contains(Scopes.HEALTHKIT_HEIGHTWEIGHT_READ));
        LogUtil.a("RopeCloudAuthManager", "HEIGHT_WEIGHT_READ_SEND_TYPE HasPermission: ", Boolean.valueOf(keySet.contains(Scopes.HEALTHKIT_HEIGHTWEIGHT_READ)));
        ((e) Objects.requireNonNull(this.g.get(2))).b(keySet.contains(Scopes.HEALTHKIT_EXTEND_SPORT_READ));
        LogUtil.a("RopeCloudAuthManager", "HEALTHKIT_EXTEND_SPORT_READ HasPermission: ", Boolean.valueOf(keySet.contains(Scopes.HEALTHKIT_EXTEND_SPORT_READ)));
        if (!this.g.get(1).a() && !this.g.get(2).a()) {
            z3 = false;
        }
        b(z, z2, z3);
    }

    private void b(final boolean z, final boolean z2) {
        ReleaseLogUtil.e("DEVMGR_RopeCloudAuthManager", "checkAuthorization");
        if (TextUtils.isEmpty(d(this.h, this.b))) {
            ReleaseLogUtil.e("DEVMGR_RopeCloudAuthManager", "appId is null");
            this.e = true;
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("nsp_svc", "nsp.scope.app.get");
        hashMap.put("nsp_fmt", "json");
        hashMap.put("appid", this.f2304a);
        hashMap.put("type", "2");
        String url = Utils.getUrl("com.huawei.health", "domainScopeOauth");
        ReleaseLogUtil.e("DEVMGR_RopeCloudAuthManager", "appId:", this.f2304a);
        new HealthEngineRequestManager.RequestParamsBuilder().setTag("RopeCloudAuthManager").setUrl(url).setQueryParams(hashMap).setHeaderParam(new HashMap(10)).setMethod("GET").setRequestCallBack(new OnRequestCallBack<AuthorizationData>() { // from class: com.huawei.health.ecologydevice.manager.RopeCloudAuthManager.1
            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(AuthorizationData authorizationData) {
                if (RopeCloudAuthManager.this.a(authorizationData.getScopes())) {
                    RopeCloudAuthManager.this.c(z, z2);
                } else {
                    LogUtil.a("RopeCloudAuthManager", "The user does not apply for permission.");
                }
            }

            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c("DEVMGR_RopeCloudAuthManager", "checkAuthorization fail:", Integer.valueOf(i));
            }
        }).create().request();
    }

    public void e(SettingStatusData settingStatusData) {
        if (dij.g(this.h)) {
            d(settingStatusData);
        }
        b(settingStatusData);
    }

    public void b(final SettingStatusData settingStatusData) {
        LogUtil.a("RopeCloudAuthManager", "getUserInfoFromApp");
        if (this.c == null || settingStatusData == null) {
            LogUtil.h("RopeCloudAuthManager", "contrastCalorieInfoForApp mainActivity or mRopeDeviceDataManager is null");
        } else {
            dei.c().a(this.c, new CommonUiResponse() { // from class: ddn
                @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
                public final void onResponse(int i, Object obj) {
                    RopeCloudAuthManager.this.b(settingStatusData, i, (HiUserInfo) obj);
                }
            });
        }
    }

    public /* synthetic */ void b(SettingStatusData settingStatusData, int i, HiUserInfo hiUserInfo) {
        int weight = (int) (hiUserInfo.getWeight() * 10.0f);
        int height = hiUserInfo.getHeight();
        Object[] objArr = new Object[4];
        objArr[0] = "weight is same ";
        objArr[1] = Boolean.valueOf(weight == settingStatusData.getWeight());
        objArr[2] = " height is same ";
        objArr[3] = Boolean.valueOf(height == settingStatusData.getHeight());
        LogUtil.a("RopeCloudAuthManager", objArr);
        boolean z = weight == settingStatusData.getWeight() && height == settingStatusData.getHeight();
        boolean z2 = settingStatusData.getHeight() == 255 || settingStatusData.getWeight() == 65535;
        boolean z3 = (z2 || z) ? false : true;
        boolean f = f(this.h);
        LogUtil.a("RopeCloudAuthManager", "isSame = ", Boolean.valueOf(z), " isErrorValue ", Boolean.valueOf(z2), " isFirst ", Boolean.valueOf(f));
        this.j = new int[]{weight, height, 255};
        this.g.get(1).e(z3);
        LogUtil.a("RopeCloudAuthManager", "isSend HEIGHT_WEIGHT_READ_SEND_TYPE: ", Boolean.valueOf(this.g.get(1).e()), " isSend SPORT_READ_SEND_TYPE: ", Boolean.valueOf(this.g.get(2).e()));
        if (f) {
            b(true, false);
            return;
        }
        if (!z2) {
            if (this.g.get(1).e() || this.g.get(2).e()) {
                b(true, false);
                return;
            }
            return;
        }
        LogUtil.a("RopeCloudAuthManager", "Data exception");
    }

    public static void c(String str) {
        b(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(List<AuthorizationData.Scopes> list) {
        boolean z = false;
        if (koq.b(list)) {
            return false;
        }
        this.g.get(1).b(false);
        this.g.get(2).c(false);
        for (AuthorizationData.Scopes scopes : list) {
            if (Scopes.HEALTHKIT_HEIGHTWEIGHT_READ.equals(scopes.getUri())) {
                LogUtil.a("RopeCloudAuthManager", "checkScopeUrl Has HEALTH_KIT_HEIGHT_WEIGHT_READ_SCOPE_URL");
                this.g.get(1).c(true);
                z = true;
            }
            if (Scopes.HEALTHKIT_EXTEND_SPORT_READ.equals(scopes.getUri())) {
                LogUtil.a("RopeCloudAuthManager", "checkScopeUrl Has HEALTHKIT_EXTEND_SPORT_READ");
                this.g.get(2).c(true);
                z = true;
            }
        }
        return z;
    }

    private void b(boolean z, boolean z2, boolean z3) {
        LogUtil.a("RopeCloudAuthManager", "isNeedUpdate = ", Boolean.valueOf(z), " isAuthorization = ", Boolean.valueOf(z3), " getRejectSignInStatus() = ", Boolean.valueOf(e(this.f2304a)), " isSelectFunction = ", Boolean.valueOf(z2));
        a(this.h);
        if (z2) {
            d(z3, this.h, this.f);
            return;
        }
        if (!e(this.f2304a) && !z3) {
            LogUtil.a("RopeCloudAuthManager", "User Deny Authorization");
            return;
        }
        if (!z3) {
            dks.a(this.c, this.h, this.f, "#/?type=signInPage");
            b(this.f2304a);
            return;
        }
        g(this.f2304a);
        if (z) {
            if (a(1)) {
                LogUtil.a("RopeCloudAuthManager", "SendDevice UserInfo");
                a(9, e());
            }
            if (a(2)) {
                LogUtil.a("RopeCloudAuthManager", "SendDevice Achievements");
                a(7, this.d);
            }
        }
    }

    private boolean a(int i) {
        LogUtil.a("RopeCloudAuthManager", "isSendDeviceData TYPE:", Integer.valueOf(i), " isNeedSend: ", Boolean.valueOf(((e) Objects.requireNonNull(this.g.get(Integer.valueOf(i)))).e()), " isHasPermission:", Boolean.valueOf(((e) Objects.requireNonNull(this.g.get(Integer.valueOf(i)))).a()), " isHasScopeUrl:", Boolean.valueOf(((e) Objects.requireNonNull(this.g.get(Integer.valueOf(i)))).b()));
        if (dij.g(this.h) && this.g.get(2).a() && this.g.get(2).b()) {
            return this.g.get(Integer.valueOf(i)).e();
        }
        return this.g.get(Integer.valueOf(i)).e() && this.g.get(Integer.valueOf(i)).a() && this.g.get(Integer.valueOf(i)).b();
    }

    private void d(boolean z, String str, String str2) {
        if (z) {
            c(this.c);
        } else {
            dks.a(this.c, str, str2, "#/?type=signInPage");
        }
    }

    private void c(Context context) {
        if (TextUtils.isEmpty(this.f2304a) || context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAccountAuthActivity");
        intent.putExtra("app_id", this.f2304a);
        gnm.aPB_(context, intent);
    }

    private void a(int i, int[] iArr) {
        dds.c().d(2, i, iArr);
    }

    public static void b(String str) {
        a(str, "1");
    }

    private static void g(String str) {
        a(str, "");
    }

    public static boolean e(String str) {
        String j = j(str);
        LogUtil.a("RopeCloudAuthManager", "getRejectSignInStatus ", j);
        return TextUtils.isEmpty(j);
    }

    public static void d(String str) {
        String e2 = dij.e(str);
        dcz d = ResourceManager.e().d(str);
        Object[] objArr = new Object[4];
        objArr[0] = "smartProId = ";
        objArr[1] = e2;
        objArr[2] = " productInfo is null? ";
        objArr[3] = Boolean.valueOf(d == null);
        LogUtil.c("RopeCloudAuthManager", objArr);
        if (d == null && cez.ac.contains(e2)) {
            a("download_" + str, "1");
        }
    }

    public static void a(String str) {
        a("download_" + str, "");
    }

    private static boolean f(String str) {
        LogUtil.a("RopeCloudAuthManager", "download flag = ", j("download_" + str));
        return !TextUtils.isEmpty(r2);
    }

    private int[] e() {
        return this.j;
    }

    private static void a(String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.e(), String.valueOf(10000), str, str2, new StorageParams(0));
    }

    private static String j(String str) {
        return SharedPreferenceManager.b(BaseApplication.e(), String.valueOf(10000), str);
    }

    public void d(SettingStatusData settingStatusData) {
        LogUtil.a("RopeCloudAuthManager", "getRopeAchievementsFromApp");
        if (this.c == null || settingStatusData == null) {
            LogUtil.h("RopeCloudAuthManager", "getRopeAchievementsFromApp mContext or settingStatusData is null");
            return;
        }
        AchieveDataApi achieveDataApi = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
        if (achieveDataApi == null) {
            LogUtil.h("RopeCloudAuthManager", "getRopeAchievementsFromApp achieveDataApi is null.");
            return;
        }
        LogUtil.a("RopeCloudAuthManager", "settingStatusData BestNumberSingleJumps: ", Integer.valueOf(settingStatusData.getSettingBestNumberSingleJumps()), " BestRopeContinuousCount: ", Integer.valueOf(settingStatusData.getSettingBestNumberConsecutiveJumps()), " BestRopeSpeed: ", Integer.valueOf(settingStatusData.getSettingBestSkippingSpeed()), " BestRopeDuration: ", Integer.valueOf(settingStatusData.getSettingBestSkippingDuration()));
        mdd singleDayData = achieveDataApi.getSingleDayData();
        if (!d(singleDayData)) {
            LogUtil.h("RopeCloudAuthManager", "getRopeAchievementsFromApp checkSingleDayData is false.");
            return;
        }
        LogUtil.a("RopeCloudAuthManager", "getBestRopeSingCount: ", singleDayData.f(), " getBestRopeContinuousCount: ", singleDayData.i(), " getBestRopeSpeed: ", singleDayData.j(), " getBestRopeDuration: ", singleDayData.g());
        int parseInt = Integer.parseInt(singleDayData.f());
        int parseInt2 = Integer.parseInt(singleDayData.i());
        int parseInt3 = Integer.parseInt(singleDayData.j());
        int parseInt4 = Integer.parseInt(singleDayData.g());
        boolean z = false;
        boolean z2 = settingStatusData.getSettingBestNumberSingleJumps() == parseInt && settingStatusData.getSettingBestNumberConsecutiveJumps() == parseInt2;
        boolean z3 = settingStatusData.getSettingBestSkippingDuration() == parseInt4 && settingStatusData.getSettingBestSkippingSpeed() == parseInt3;
        this.d = new int[]{parseInt, parseInt2, parseInt4, parseInt3};
        this.g.get(2).e((z2 && z3) ? false : true);
        Object[] objArr = new Object[2];
        objArr[0] = "RopeAchievements is same:";
        if (z2 && z3) {
            z = true;
        }
        objArr[1] = Boolean.valueOf(z);
        LogUtil.a("RopeCloudAuthManager", objArr);
    }

    private boolean d(mdd mddVar) {
        return (mddVar == null || TextUtils.isEmpty(mddVar.f()) || TextUtils.isEmpty(mddVar.i()) || TextUtils.isEmpty(mddVar.j()) || TextUtils.isEmpty(mddVar.g())) ? false : true;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2306a;
        private int b;
        private boolean c;
        private boolean d;

        e(int i) {
            this.b = i;
        }

        public boolean a() {
            return this.c;
        }

        public void b(boolean z) {
            this.c = z;
        }

        public boolean b() {
            return this.f2306a;
        }

        public void c(boolean z) {
            this.f2306a = z;
        }

        public boolean e() {
            return this.d;
        }

        public void e(boolean z) {
            this.d = z;
        }
    }
}
