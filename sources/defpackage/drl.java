package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class drl {
    public static dqo a(String str, String str2, String str3) {
        dqm dqmVar;
        ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "getFeatureFileExtInfo start, configId: ", str, ", packageName: ", str3);
        try {
            dqmVar = (dqm) HiJsonUtil.e(JsonSanitizer.sanitize(CommonUtil.t(drd.d(str, "feature_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010) + "_app", str2))), dqm.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("R_CloudFeatureFileAnalysisUtil", "JsonSyntaxException in getFeatureFileExtInfo: ", LogAnonymous.b((Throwable) e));
        }
        if (dqmVar != null && dqmVar.a() != null) {
            ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "getFeatureFileExtInfo current local feature file version is ", Integer.valueOf(dqmVar.e()));
            for (dqo dqoVar : dqmVar.a()) {
                if (str3.equals(dqoVar.c())) {
                    ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "getFeatureFileExtInfo: ", str3, " found");
                    return dqoVar;
                }
            }
            ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "getFeatureFileExtInfo cannot found feature: ", str3);
            return null;
        }
        ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "getFeatureFileExtInfo: File content or feature list is missing");
        return null;
    }

    public static boolean c(String str, String str2, String str3) {
        String str4 = "isFeatureSupport, configId: " + str + ", packageName: " + str3 + ", ";
        dqo a2 = a(str, str2, str3);
        Integer num = 1;
        if (a2 == null) {
            ReleaseLogUtil.d("R_CloudFeatureFileAnalysisUtil", str4, "feature not found");
            return false;
        }
        Integer e = a2.e();
        dqp b = a2.b();
        String str5 = str4 + "support=" + e;
        if (e != null && e.intValue() != 1) {
            ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", str5);
            return false;
        }
        if (b == null) {
            ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", str5);
            return num.equals(e);
        }
        String b2 = b.b();
        if (!a(b2)) {
            ReleaseLogUtil.d("R_CloudFeatureFileAnalysisUtil", str5, ", invalid suppVer=", b2);
            return num.equals(e);
        }
        String e2 = CommonUtil.e((Context) null);
        if (e2.contains(Constants.LINK)) {
            e2 = e2.substring(0, e2.indexOf(45));
        }
        String str6 = e2;
        int d = CommonUtil.d(str6, b2);
        ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", str5, ", currVer=", str6, ", suppVer=", b2, ", compResult=", Integer.valueOf(d));
        return d >= 0;
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\.");
        if (split.length != 4) {
            return false;
        }
        for (String str2 : split) {
            if (!StringUtils.a(str2)) {
                return false;
            }
        }
        return true;
    }

    public static void e(DownloadCallback<File> downloadCallback) {
        String str = "feature_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        String str2 = str + "_app";
        String str3 = str + "_device";
        HashMap hashMap = new HashMap();
        hashMap.put("configType", TrackConstants$Events.FEATURE);
        DownloadCallback<File> a2 = a(str, "_app");
        if (downloadCallback == null) {
            downloadCallback = a(str, "_device");
        }
        drd.e(new dql("com.huawei.health_deviceFeature_config", hashMap), str2, 1, a2);
        drd.e(new dql("com.huawei.health_deviceFeature_config", hashMap), str3, 1, downloadCallback);
    }

    private static DownloadCallback<File> a(final String str, final String str2) {
        return new DownloadCallback<File>() { // from class: drl.2
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "updateFeatureConfig onFinish, data is: ", file.getName());
                if ("_device".equals(str2)) {
                    drb.b().d("com.huawei.health_deviceFeature_config", str + str2, (String) null);
                }
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str3) {
                LogUtil.a("CloudFeatureFileAnalysisUtil", "updateFeatureConfig onProgress, handleBytes: ", Long.valueOf(j), ", contentLength: ", Long.valueOf(j2), ", isDone: ", Boolean.valueOf(z), ", fileId: ", str3);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "updateFeatureConfig on Fail: ", LogAnonymous.b(th), ", errCode: ", Integer.valueOf(i));
            }
        };
    }

    public static JSONObject b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", -1);
            jSONObject.put("resultDesc", "Cloud config file caused JSONException");
            ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "getContextByFeature start");
            dqo a2 = a(str, "txt", str2);
            if (a2 == null) {
                ReleaseLogUtil.c("R_CloudFeatureFileAnalysisUtil", "getContextByFeature: Feature not found");
                jSONObject.put("resultDesc", "Feature not found");
                return jSONObject;
            }
            jSONObject.put("resultCode", 0);
            jSONObject.put("resultDesc", new JSONObject(HiJsonUtil.e(a2)));
            return jSONObject;
        } catch (JSONException e) {
            ReleaseLogUtil.c("R_CloudFeatureFileAnalysisUtil", "getContextByFeature JSONException: ", LogAnonymous.b((Throwable) e));
            return jSONObject;
        }
    }

    public static String c(String str, String str2) {
        ReleaseLogUtil.e("R_CloudFeatureFileAnalysisUtil", "getFeatureFileExtInfo start, configId: ", str);
        return CommonUtil.t(drd.d(str, "feature_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010) + "_app", str2));
    }
}
