package com.huawei.hwdevice.phoneprocess.mgr.hwdevicescreenshotmgr;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.jrm;
import defpackage.kba;
import defpackage.spn;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DeviceScreenshotPretreatment implements PretreatmentService {
    private static final String[] e = {"android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        LogUtil.a("DeviceScreenshotPretreatment", "enter deviceScreenshot pretreatment.");
        kba.a().c(new spn.b().c(b(context).getBytes(StandardCharsets.UTF_8)).e(), new SendCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwdevicescreenshotmgr.DeviceScreenshotPretreatment.2
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("DeviceScreenshotPretreatment", "send msg code: ", Integer.valueOf(i), " in DeviceScreenshotPretreatment");
            }
        });
        d(guidepost, context);
        return false;
    }

    private void d(Guidepost guidepost, Context context) {
        Uri zN_ = guidepost.zN_();
        String uri = zN_.toString();
        LogUtil.a("DeviceScreenshotPretreatment", "screenshot uri is ", uri);
        if (!uri.contains("screenshot")) {
            LogUtil.a("DeviceScreenshotPretreatment", " uri do not contains screenshot");
        } else if (Objects.equals(zN_.getQueryParameter("type"), "manual")) {
            if (c(context) == 2) {
                jrm.b();
            }
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("DeviceScreenshotPretreatment", "screenshot type is manual");
        }
    }

    private boolean b(String str) {
        if (CommonUtil.bv()) {
            return false;
        }
        return SharedPreferenceManager.a(Integer.toString(1000), str, false);
    }

    private String b(Context context) {
        JSONObject jSONObject = new JSONObject();
        boolean b = b("screenshot_init_screenshot_fail_switch");
        int c = b("screenshot_no_storage_permission_switch") ? 2 : c(context);
        try {
            jSONObject.put("operateType", 1);
            jSONObject.put("statusCode", b ^ true ? 1 : 2);
            jSONObject.put("hasPermission", c);
        } catch (JSONException unused) {
            LogUtil.b("DeviceScreenshotPretreatment", "createResponseForStartService has an JSONException");
        }
        LogUtil.a("DeviceScreenshotPretreatment", "createResponseForStartService json:", jSONObject.toString());
        return jSONObject.toString();
    }

    private int c(Context context) {
        return (Build.VERSION.SDK_INT < 29 && !PermissionUtil.e(context, e)) ? 2 : 1;
    }
}
