package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.main.api.CloudAuthApi;
import com.huawei.health.main.model.AppLangItem;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealthservice.HiHealthKitApi;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ezb {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static LinkedHashMap<String, Object> b(String[] strArr) {
        char c;
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(strArr.length);
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("SingleMotionDetectionUtil", "has empty name");
            } else {
                str.hashCode();
                switch (str.hashCode()) {
                    case -1079774562:
                        if (str.equals("detection_has_bind_app")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1057909642:
                        if (str.equals("detection_has_unsync_data")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 405579236:
                        if (str.equals("detection_sport_data_switch")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1664498026:
                        if (str.equals("detection_at_valid")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    ArrayList<String> c2 = c();
                    ArrayList<String> d = d();
                    JSONArray jSONArray = new JSONArray();
                    Iterator<String> it = c2.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(it.next());
                    }
                    Iterator<String> it2 = d.iterator();
                    while (it2.hasNext()) {
                        jSONArray.put(it2.next());
                    }
                    LogUtil.a("SingleMotionDetectionUtil", "apps:", jSONArray.toString());
                    linkedHashMap.put("detection_has_bind_app", jSONArray);
                } else if (c == 1) {
                    boolean hasUnSyncSportData = ((HiHealthKitApi) Services.c("HealthKit", HiHealthKitApi.class)).hasUnSyncSportData();
                    LogUtil.a("SingleMotionDetectionUtil", "hasUnSyncSportData = ", Boolean.valueOf(hasUnSyncSportData));
                    linkedHashMap.put("detection_has_unsync_data", Boolean.valueOf(hasUnSyncSportData));
                } else if (c == 2) {
                    gmz d2 = gmz.d();
                    String c3 = d2.c(2);
                    String c4 = d2.c(3);
                    String c5 = d2.c(7);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("person", "true".equals(c3) ? 1 : 0);
                        jSONObject.put("sport", "true".equals(c4) ? 1 : 0);
                        jSONObject.put("health", "true".equals(c5) ? 1 : 0);
                    } catch (JSONException unused) {
                        LogUtil.b("SingleMotionDetectionUtil", "jsonException");
                    }
                    LogUtil.a("SingleMotionDetectionUtil", "object:", jSONObject.toString());
                    linkedHashMap.put("detection_sport_data_switch", jSONObject);
                } else if (c == 3) {
                    boolean z = CommonUtil.g(LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1016)) - System.currentTimeMillis() > 0;
                    LogUtil.a("SingleMotionDetectionUtil", "isAtValid:", Boolean.valueOf(z));
                    linkedHashMap.put("detection_at_valid", Boolean.valueOf(z));
                }
            }
        }
        return linkedHashMap;
    }

    private static ArrayList<String> d() {
        CloudAuthApi cloudAuthApi = (CloudAuthApi) Services.c("Main", CloudAuthApi.class);
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1015);
        cloudAuthApi.constructInstance(BaseApplication.e(), accountInfo);
        LogUtil.c("SingleMotionDetectionUtil", "Get UserAt : ", accountInfo);
        String lang = cloudAuthApi.getLang();
        LogUtil.c("SingleMotionDetectionUtil", "getUserId: ", LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011));
        String appLangItemUrl = cloudAuthApi.getAppLangItemUrl(lang, "2");
        LogUtil.a("SingleMotionDetectionUtil", "Get appLangItemUrl : ", appLangItemUrl);
        List<AppLangItem> appLangItem = cloudAuthApi.getAppLangItem(accountInfo, "GET", appLangItemUrl);
        List<AppLangItem> appLangItem2 = cloudAuthApi.getAppLangItem(accountInfo, "GET", cloudAuthApi.getAppLangItemUrl(lang, "3"));
        if (appLangItem2 != null) {
            LogUtil.a("SingleMotionDetectionUtil", "healthAccountAppInfoList length: ", Integer.valueOf(appLangItem2.size()));
            appLangItem = b(appLangItem, appLangItem2);
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (koq.c(appLangItem)) {
            Iterator<AppLangItem> it = appLangItem.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getAppName());
            }
        }
        return arrayList;
    }

    private static List<AppLangItem> b(List<AppLangItem> list, List<AppLangItem> list2) {
        if (koq.b(list)) {
            return list2;
        }
        for (AppLangItem appLangItem : list2) {
            Iterator<AppLangItem> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getAppId().equals(appLangItem.getAppId())) {
                        break;
                    }
                } else {
                    list.add(appLangItem);
                    break;
                }
            }
        }
        return list;
    }

    private static ArrayList<String> c() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ArrayList<String> arrayList = new ArrayList<>();
        HiHealthNativeApi.a(BaseApplication.e()).queryKitAppInfo(new HiDataOperateListener() { // from class: eza
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                ezb.a(countDownLatch, arrayList, i, obj);
            }
        });
        try {
            LogUtil.a("SingleMotionDetectionUtil", "getLocalThirdPartyAppInfo countReachedZero: ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("SingleMotionDetectionUtil", "getLocalThirdPartyAppInfo interruptedException");
        }
        return arrayList;
    }

    static /* synthetic */ void a(CountDownLatch countDownLatch, ArrayList arrayList, int i, Object obj) {
        if (!(obj instanceof List)) {
            LogUtil.h("SingleMotionDetectionUtil", "getLocalThirdPartyAppInfo onResult data not list");
            countDownLatch.countDown();
            return;
        }
        List list = (List) obj;
        if (koq.b(list)) {
            LogUtil.h("SingleMotionDetectionUtil", "getLocalThirdPartyAppInfo onResult tempAppInfoList empty");
            countDownLatch.countDown();
            return;
        }
        boolean e = e(Constants.FAST_APP_PKG);
        for (Object obj2 : list) {
            if (obj2 instanceof HiAppInfo) {
                HiAppInfo hiAppInfo = (HiAppInfo) obj2;
                if (e(hiAppInfo.getPackageName()) && e(BaseApplication.e(), hiAppInfo)) {
                    arrayList.add(hiAppInfo.getAppName());
                }
                if (e && hiAppInfo.getAppName() != null && hiAppInfo.getAppName().startsWith("QuickApp_")) {
                    arrayList.add(hiAppInfo.getAppName());
                }
            }
        }
        countDownLatch.countDown();
    }

    private static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            BaseApplication.e().getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.a("SingleMotionDetectionUtil", "checkApkExist NameNotFoundException");
            return false;
        }
    }

    private static boolean e(Context context, HiAppInfo hiAppInfo) {
        if (hiAppInfo == null) {
            LogUtil.h("SingleMotionDetectionUtil", "checkApkValid() hiAppInfo is null");
            return false;
        }
        String packageName = hiAppInfo.getPackageName();
        String signature = hiAppInfo.getSignature();
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(signature)) {
            LogUtil.h("SingleMotionDetectionUtil", "checkApkValid() packageName or signature is null");
            return false;
        }
        try {
            return HsfSignValidator.e(context, packageName).equals(ivv.d(signature)) && ivv.b(signature) == context.getPackageManager().getApplicationInfo(packageName, 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("SingleMotionDetectionUtil", "checkApkValid() NameNotFoundException");
            return false;
        }
    }
}
