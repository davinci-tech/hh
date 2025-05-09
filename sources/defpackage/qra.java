package defpackage;

import android.text.TextUtils;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.util.BloodSugarCallback;
import com.huawei.ui.main.stories.health.util.UpDataFileListener;
import health.compact.a.Utils;
import java.io.File;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class qra {
    public void b(UpDataFileListener upDataFileListener) {
        LogUtil.a("Health_BloodSugar_DownloadUtils", "startDownload");
        HashMap hashMap = new HashMap();
        hashMap.put("configType", "bloodsugar");
        drd.e(new dql("com.huawei.health_Sport_Common", hashMap), "blood_sugar_suggestion", 7, new c(upDataFileListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d() {
        return drd.d("com.huawei.health_Sport_Common", "blood_sugar_suggestion", "zip");
    }

    public String b() {
        String d = d();
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        File file = new File(d);
        if (!file.exists()) {
            return "";
        }
        return file.getParent() + File.separator + "blood_sugar_suggestions" + File.separator;
    }

    public void a(final BloodSugarCallback bloodSugarCallback) {
        jdx.b(new Runnable() { // from class: qrb
            @Override // java.lang.Runnable
            public final void run() {
                qra.this.e(bloodSugarCallback);
            }
        });
    }

    /* synthetic */ void e(BloodSugarCallback bloodSugarCallback) {
        String c2 = Utils.c(b() + "sugar_food_config.json");
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("Health_BloodSugar_DownloadUtils", "getSugarFoodList json is null");
            bloodSugarCallback.result(1, null);
        } else {
            try {
                bloodSugarCallback.result(0, new JSONObject(c2));
            } catch (JSONException unused) {
                LogUtil.b("Health_BloodSugar_DownloadUtils", "getTaskCardPathList JSONException");
                bloodSugarCallback.result(1, null);
            }
        }
    }

    public void c(final BloodSugarCallback bloodSugarCallback) {
        jdx.b(new Runnable() { // from class: qqz
            @Override // java.lang.Runnable
            public final void run() {
                qra.this.b(bloodSugarCallback);
            }
        });
    }

    /* synthetic */ void b(BloodSugarCallback bloodSugarCallback) {
        String c2 = Utils.c(b() + "sugar_suggestion_config.json");
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("Health_BloodSugar_DownloadUtils", "getBloodSuggestionStr read json is null");
            bloodSugarCallback.result(1, null);
        } else {
            try {
                bloodSugarCallback.result(0, new JSONObject(c2));
            } catch (JSONException unused) {
                LogUtil.b("Health_BloodSugar_DownloadUtils", "getBloodSuggestionStr JSONException");
                bloodSugarCallback.result(1, null);
            }
        }
    }

    public File e() {
        File file = new File(b(), "BloodSugarAnalyze.js");
        if (file.exists()) {
            return file;
        }
        LogUtil.h("Health_BloodSugar_DownloadUtils", "BloodSugarAnalyze is not exists");
        return null;
    }

    static class c implements DownloadCallback<File> {
        private final UpDataFileListener b;

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
        }

        c(UpDataFileListener upDataFileListener) {
            this.b = upDataFileListener;
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onFinish(File file) {
            if (file == null || !file.exists()) {
                LogUtil.h("Health_BloodSugar_DownloadUtils", "updateIndexFile onFinish data is: file is null");
                return;
            }
            LogUtil.a("Health_BloodSugar_DownloadUtils", "download success");
            msp.c(qra.d(), file.getParent());
            UpDataFileListener upDataFileListener = this.b;
            if (upDataFileListener == null) {
                LogUtil.h("Health_BloodSugar_DownloadUtils", "upDataFileListener is null");
            } else {
                upDataFileListener.onFinish();
            }
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            Object[] objArr = new Object[2];
            objArr[0] = "updateIndexFile onFail: ";
            objArr[1] = th == null ? "throwable is null" : th.getMessage();
            LogUtil.b("Health_BloodSugar_DownloadUtils", objArr);
        }
    }
}
