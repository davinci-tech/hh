package com.huawei.health.ecologydevice.manager;

import android.os.Handler;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.ecologydevice.manager.MassageGunConfig;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import defpackage.jbj;
import defpackage.jdx;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class MassageGunConfig {
    private static final String e = File.separator + "third" + File.separator + "massage_gun_course_recommend.json";
    private String b;

    public interface MassageGunCallback {
        void onMassageGunCallback(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        String str;
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("getNewBatchPluginUrl", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
        if (TextUtils.isEmpty(noCheckUrl)) {
            LogUtil.h("MassageGunConfig", "updateMassageGunConfig url is empty");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(noCheckUrl);
        sb.append(CommonUtil.ag(BaseApplication.getContext()) ? "com.huawei.health_FasciaGun_deviceConfig" : "com.huawei.health_FasciaGun_deviceConfigBeta");
        String c = jbj.c(sb.toString(), "deviceType=courseRecommendMonitor");
        if (TextUtils.isEmpty(c) || Constants.NULL.equalsIgnoreCase(c)) {
            LogUtil.h("MassageGunConfig", "updateMassageGunConfig jsonResult is empty");
            return false;
        }
        String a2 = a(c);
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("MassageGunConfig", "updateMassageGunConfig downloadUrl is empty");
            return false;
        }
        try {
            str = BaseApplication.getContext().getFilesDir().getCanonicalPath() + e;
        } catch (IOException unused) {
            LogUtil.b("MassageGunConfig", "updateMassageGunConfig getCanonicalPath IOException");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("MassageGunConfig", "updateMassageGunConfig filePath is empty");
            return false;
        }
        boolean b = jbj.b(a2, str);
        LogUtil.a("MassageGunConfig", "updateMassageGunConfig isDownloadSuccess = ", Boolean.valueOf(b));
        if (!b) {
            return false;
        }
        boolean c2 = c(str);
        LogUtil.a("MassageGunConfig", "updateMassageGunConfig isParseSuccess = ", Boolean.valueOf(c2));
        if (c2) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_WIND), "massage_gun_config_last_update_time", Long.toString(System.currentTimeMillis()), new StorageParams());
        }
        return c2;
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if ("massage_gun_course_recommend".equals(jSONObject.getString(RecommendConstants.FILE_ID))) {
                    return jSONObject.getString(RecommendConstants.DOWNLOAD_URL);
                }
            }
            return "";
        } catch (JSONException unused) {
            LogUtil.b("MassageGunConfig", "parseJsonString JSONException");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("MassageGunConfig", "parseJsonFile filePath is empty");
            return false;
        }
        EzPluginManager.a();
        JSONObject a2 = EzPluginManager.a(new File(str));
        if (a2 != null) {
            this.b = a2.toString();
        } else {
            LogUtil.a("MassageGunConfig", "read file error, file content is empty");
        }
        return TextUtils.isEmpty(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        return System.currentTimeMillis() - CommonUtil.g(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_WIND), "massage_gun_config_last_update_time")) > 86400000;
    }

    void TG_(Handler handler, MassageGunCallback massageGunCallback) {
        jdx.b(new e(handler, massageGunCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() {
        LogUtil.a("MassageGunConfig", "getDefaultCountry");
        c(BaseApplication.getContext().getFilesDir().getAbsolutePath() + e);
        String str = this.b;
        return str == null ? "" : str;
    }

    public static final class e implements Runnable {
        private WeakReference<MassageGunConfig> b;
        private Handler c;
        private MassageGunCallback e;

        private e(MassageGunConfig massageGunConfig, Handler handler, MassageGunCallback massageGunCallback) {
            this.b = new WeakReference<>(massageGunConfig);
            this.c = handler;
            this.e = massageGunCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            MassageGunConfig massageGunConfig = this.b.get();
            if (massageGunConfig == null) {
                return;
            }
            if (massageGunConfig.e()) {
                massageGunConfig.b = massageGunConfig.a() ? massageGunConfig.b : massageGunConfig.d();
            } else {
                LogUtil.a("MassageGunConfig", "isNeedUpdateGrsConfig isNeedUpdate = false");
                if (massageGunConfig.b == null) {
                    massageGunConfig.c(BaseApplication.getContext().getFilesDir().getAbsolutePath() + MassageGunConfig.e);
                }
            }
            Handler handler = this.c;
            if (handler != null) {
                handler.post(new Runnable() { // from class: dci
                    @Override // java.lang.Runnable
                    public final void run() {
                        MassageGunConfig.e.this.e();
                    }
                });
            }
        }

        public /* synthetic */ void e() {
            if (this.b.get() != null) {
                this.e.onMassageGunCallback(this.b.get().b);
            }
        }
    }
}
