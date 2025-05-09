package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.health.healthcloudconfig.listener.featureconfig.ConfigFileCallback;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class drb {
    private static volatile drb e;
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, ConfigFileCallback> f11798a = new HashMap();

    private drb() {
    }

    public static drb b() {
        if (e == null) {
            synchronized (drb.class) {
                if (e == null) {
                    e = new drb();
                }
            }
        }
        return e;
    }

    public void e(String str, ConfigFileCallback configFileCallback) {
        LogUtil.a("FeatureConfigFileManager", "registerConfigFileInfoListener ConfigFileListener register start");
        if (configFileCallback == null || TextUtils.isEmpty(str)) {
            LogUtil.a("FeatureConfigFileManager", "registerConfigFileInfoListener DeviceIdentify or callback is null");
            return;
        }
        synchronized (b) {
            if (f11798a.containsKey(str)) {
                LogUtil.a("FeatureConfigFileManager", "registerConfigFileInfoListener Callback register failed: Device [", str, "] is already exist");
            } else {
                f11798a.put(str, configFileCallback);
                LogUtil.a("FeatureConfigFileManager", "registerConfigFileInfoListener Device [", str, "] has been registered, current map size: ", Integer.valueOf(f11798a.size()));
            }
        }
    }

    public void d(String str) {
        LogUtil.a("FeatureConfigFileManager", "unregisterConfigFileInfoListener ConfigFileListener unregistered start");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("FeatureConfigFileManager", "unregisterConfigFileInfoListener DeviceIdentify is null");
            return;
        }
        synchronized (b) {
            Map<String, ConfigFileCallback> map = f11798a;
            if (map == null) {
                LogUtil.b("FeatureConfigFileManager", "unregisterConfigFileInfoListener Callback map does not exist, unregister failed");
            } else {
                map.remove(str);
                LogUtil.a("FeatureConfigFileManager", "unregisterConfigFileInfoListener Device [", str, "] has been unregistered, current map size: ", Integer.valueOf(f11798a.size()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(String str) {
        try {
            return new JSONObject(CommonUtil.t(str)).getInt(RecommendConstants.VER);
        } catch (JSONException unused) {
            LogUtil.b("FeatureConfigFileManager", "getConfigFileVersion failed with JSONException");
            return -2;
        }
    }

    private ConfigFileCallback a(String str) {
        synchronized (b) {
            Map<String, ConfigFileCallback> map = f11798a;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }
    }

    private String d(dqs dqsVar) {
        if (dqsVar == null) {
            return "";
        }
        String c2 = dqsVar.c();
        return c2.endsWith(".txt") ? c2.substring(0, c2.lastIndexOf(".")) : c2;
    }

    public boolean e(String str) {
        if (str == null) {
            LogUtil.b("FeatureConfigFileManager", "isUpdate input hexString is null value");
            return false;
        }
        try {
            dqr dqrVar = (dqr) HiJsonUtil.e(HEXUtils.d(str), dqr.class);
            if (dqrVar == null) {
                LogUtil.b("FeatureConfigFileManager", "isUpdate input queryParams is null value");
                return false;
            }
            String e2 = dqrVar.e();
            LogUtil.a("FeatureConfigFileManager", "queryConfigFileInfo: configId: ", e2);
            return !TextUtils.isEmpty(e2) && c(a(dqrVar), e2) == 1;
        } catch (JsonSyntaxException e3) {
            LogUtil.b("FeatureConfigFileManager", "queryConfigFileInfo Exception: ", e3.getMessage());
            return false;
        }
    }

    private void c(ConfigFileCallback configFileCallback, List<String> list, String str, String str2, String str3) {
        LogUtil.b("FeatureConfigFileManager", str3);
        configFileCallback.onFileResponse(list, str, str2);
    }

    public void d(String str, String str2, ConfigFileCallback configFileCallback) {
        if (configFileCallback == null) {
            LogUtil.b("FeatureConfigFileManager", "queryConfigFileInfo FileCallback is null");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            c(configFileCallback, new ArrayList(), null, str2, "queryConfigFileInfo input params contains empty value");
            return;
        }
        String d = HEXUtils.d(str);
        try {
            dqr dqrVar = (dqr) HiJsonUtil.e(d, dqr.class);
            if (dqrVar == null) {
                c(configFileCallback, new ArrayList(), d, str2, "queryConfigFileInfo QueryParams is null");
                return;
            }
            String e2 = dqrVar.e();
            LogUtil.a("FeatureConfigFileManager", "queryConfigFileInfo: configId: ", e2);
            if (TextUtils.isEmpty(e2)) {
                c(configFileCallback, new ArrayList(), d, str2, "queryConfigFileInfo ConfigId is null");
                return;
            }
            List<dqs> a2 = a(dqrVar);
            dqrVar.a(c(dqrVar));
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            a(e2, a2, arrayList, arrayList2);
            if (arrayList.size() == 0) {
                LogUtil.a("FeatureConfigFileManager", "queryConfigFileInfo No file needs to update, directly receive");
                configFileCallback.onFileResponse(arrayList2, HiJsonUtil.e(dqrVar), str2);
                return;
            }
            LogUtil.a("FeatureConfigFileManager", "queryConfigFileInfo ", Integer.valueOf(arrayList.size()), "files need to update, downloading");
            dqrVar.b(a2);
            c cVar = new c(arrayList, dqrVar, arrayList2, str2, configFileCallback);
            HashMap hashMap = new HashMap();
            hashMap.put("configType", TrackConstants$Events.FEATURE);
            drd.d(new dql("com.huawei.health_deviceFeature_config", hashMap), "FeatureConfigFileManager_BatchUpdateRequirement", arrayList, cVar);
        } catch (JsonSyntaxException e3) {
            c(configFileCallback, new ArrayList(), d, str2, "queryConfigFileInfo Exception: " + e3.getMessage());
        }
    }

    private void a(String str, List<dqs> list, List<String> list2, List<String> list3) {
        for (dqs dqsVar : list) {
            String d = d(dqsVar);
            String d2 = drd.d(str, d, ".txt");
            int b2 = b(d2);
            int e2 = dqsVar.e();
            list3.add(d2);
            if (b2 < e2) {
                list2.add(d);
            } else {
                dqsVar.e(b2);
            }
        }
    }

    /* loaded from: classes8.dex */
    static class c implements DownloadCallback<List<File>> {

        /* renamed from: a, reason: collision with root package name */
        List<String> f11799a;
        dqr b;
        String c;
        List<String> d;
        ConfigFileCallback e;

        c(List<String> list, dqr dqrVar, List<String> list2, String str, ConfigFileCallback configFileCallback) {
            this.f11799a = list;
            this.b = dqrVar;
            this.d = list2;
            this.c = str;
            this.e = configFileCallback;
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onFinish(List<File> list) {
            LogUtil.a("FeatureConfigFileManager", "updateFeatureConfig onFinish, data is: ", HiJsonUtil.e(list));
            for (String str : this.f11799a) {
                String d = drd.d(this.b.e(), str, ".txt");
                d(str + ".txt", d, drb.b(d));
            }
            this.e.onFileResponse(this.d, HiJsonUtil.e(this.b), this.c);
        }

        private void d(String str, String str2, int i) {
            for (dqs dqsVar : this.b.d()) {
                if (dqsVar.c().equals(str)) {
                    this.d.add(str2);
                    dqsVar.e(i);
                }
            }
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
            LogUtil.a("FeatureConfigFileManager", "updateFeatureConfig onProgress, handleBytes: ", Long.valueOf(j), ", contentLength: ", Long.valueOf(j2), ", isDone: ", Boolean.valueOf(z), ", fileId: ", str);
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            LogUtil.a("FeatureConfigFileManager", "updateFeatureConfig on Fail: ", th.getMessage(), ", errCode: ", Integer.valueOf(i));
            this.e.onFileResponse(new ArrayList(), HiJsonUtil.e(this.b), this.c);
        }
    }

    private int c(List<dqs> list, String str) {
        for (dqs dqsVar : list) {
            if (b(drd.d(str, d(dqsVar), ".txt")) != dqsVar.e()) {
                return 1;
            }
        }
        return 0;
    }

    private List<dqv> c(dqr dqrVar) {
        List<dqv> c2 = dqrVar.c();
        Iterator<dqv> it = c2.iterator();
        while (it.hasNext()) {
            dqv next = it.next();
            if (next == null || TextUtils.isEmpty(next.c())) {
                it.remove();
            }
        }
        if (c2.size() == 0) {
            dqv dqvVar = new dqv();
            dqvVar.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
            c2.add(dqvVar);
        }
        dqrVar.a(c2);
        return c2;
    }

    private List<dqs> a(dqr dqrVar) {
        List<dqs> d = dqrVar.d();
        Iterator<dqs> it = d.iterator();
        while (it.hasNext()) {
            dqs next = it.next();
            if (next == null || TextUtils.isEmpty(next.c())) {
                it.remove();
            }
        }
        if (d.size() == 0) {
            dqs dqsVar = new dqs();
            dqsVar.a("feature_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010) + "_device.txt");
            dqsVar.e(-1);
            d.add(dqsVar);
        }
        dqrVar.b(d);
        return d;
    }

    public void d(String str, String str2, String str3) {
        LogUtil.a("FeatureConfigFileManager", "notifyUpdate start");
        String d = drd.d(str, str2, ".txt");
        ArrayList arrayList = new ArrayList();
        arrayList.add(d);
        dqr dqrVar = new dqr(str);
        try {
            JSONObject jSONObject = new JSONObject(CommonUtil.t(d));
            int i = jSONObject.getInt(RecommendConstants.VER);
            dqs dqsVar = new dqs();
            dqsVar.a(new File(d).getName());
            dqsVar.e(i);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(dqsVar);
            dqrVar.b(arrayList2);
            String string = jSONObject.getString("country");
            dqv dqvVar = new dqv();
            dqvVar.e(string);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(dqvVar);
            dqrVar.a(arrayList3);
            LogUtil.a("FeatureConfigFileManager", "notifyUpdate Return param generate success");
            if (!TextUtils.isEmpty(str3)) {
                ConfigFileCallback a2 = a(str3);
                if (a2 != null) {
                    a2.onFileResponse(arrayList, HiJsonUtil.e(dqrVar), str3);
                    return;
                } else {
                    LogUtil.b("FeatureConfigFileManager", "notifyUpdate Callback not registered");
                    return;
                }
            }
            for (String str4 : f11798a.keySet()) {
                ConfigFileCallback a3 = a(str4);
                if (a3 != null) {
                    a3.onFileResponse(arrayList, HiJsonUtil.e(dqrVar), str4);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("FeatureConfigFileManager", "notifyUpdate: JSONException");
        } catch (Exception unused2) {
            LogUtil.b("FeatureConfigFileManager", "notifyUpdate: Uncaught Exception occurs");
        }
    }
}
