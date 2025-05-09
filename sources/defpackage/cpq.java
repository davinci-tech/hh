package defpackage;

import android.text.TextUtils;
import com.huawei.hms.network.embedded.j;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cpq {
    public static void e(int i, String str, msq msqVar) {
        if (msqVar == null) {
            return;
        }
        ReleaseLogUtil.e("ServicesApi_EzPluginHealthBiUtils", "entry biGetConfigCalling!");
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(msqVar.l())) {
                JSONObject jSONObject2 = new JSONObject(msqVar.l());
                jSONObject.put("startTime", jSONObject2.getString("ts"));
                jSONObject.put("configureId", jSONObject2.getString("configId"));
            }
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("resultCode", new JSONObject(str).getString("resultCode"));
            }
            jSONObject.put("endTime", String.valueOf(System.currentTimeMillis()));
            jSONObject.put("domain", GRSManager.a(BaseApplication.getContext()).getUrl("domainHealthCloudCommon"));
            jSONObject.put("methodType", "POST");
            jSONObject.put(OpAnalyticsConstants.REQUEST_INTERFACE, "/commonAbility/configCenter/");
            jSONObject.put("dnsIp", "1.1.1.1");
            jSONObject.put("httpCode", String.valueOf(i));
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
            linkedHashMap.put(j.m, jSONObject.toString());
            OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.HEALTH_CLOUD_DOWN_RESOURCE_85040100.value(), linkedHashMap);
        } catch (JSONException unused) {
            ReleaseLogUtil.c("ServicesApi_EzPluginHealthBiUtils", "biGetConfigCalling exception");
        }
    }

    public static void a(DeviceDownloadSourceInfo deviceDownloadSourceInfo, String str, String str2, mso msoVar, msq msqVar) {
        if (deviceDownloadSourceInfo == null || deviceDownloadSourceInfo.getSite() != 1 || msqVar == null || msoVar == null || msoVar.i() == 0) {
            return;
        }
        ReleaseLogUtil.e("ServicesApi_EzPluginHealthBiUtils", "entry biDownloadCalling!");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("localVersion", str);
            jSONObject.put("newVersion", str2);
            jSONObject.put("endTime", String.valueOf(System.currentTimeMillis()));
            jSONObject.put(RecommendConstants.DOWNLOAD_URL, msqVar.j());
            jSONObject.put(RecommendConstants.FILE_ID, msqVar.c());
            jSONObject.put("cdnFacilitator", "hwcdn");
            if (!TextUtils.isEmpty(msqVar.l())) {
                jSONObject.put("startTime", new JSONObject(msqVar.l()).getString("ts"));
            }
            jSONObject.put("dnsIp", "2.2.2.2");
            jSONObject.put("httpCode", String.valueOf(msoVar.i()));
            jSONObject.put("cdnIp", "1.1.1.1");
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
            linkedHashMap.put("downloads", jSONObject.toString());
            OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.HEALTH_CLOUD_DOWN_RESOURCE_85040100.value(), linkedHashMap);
        } catch (JSONException unused) {
            ReleaseLogUtil.c("ServicesApi_EzPluginHealthBiUtils", "biDownloadCalling exception");
        }
    }
}
