package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.IoUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kyk implements Runnable {
    private static boolean b = false;
    private static DeviceInfo c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f14698a;
    private boolean d;
    private kxi e;

    public kyk(kxi kxiVar, boolean z, boolean z2) {
        this.e = kxiVar;
        this.d = z;
        this.f14698a = z2;
    }

    public static void d(DeviceInfo deviceInfo) {
        c = deviceInfo;
    }

    public static void b(boolean z) {
        b = z;
    }

    private static String a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("updateLog")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("updateLog");
                if (jSONObject2.has("IMEI")) {
                    jSONObject2.put("IMEI", CommonUtil.l(jSONObject2.getString("IMEI")));
                }
                if (jSONObject2.has("udid")) {
                    jSONObject2.put("udid", CommonUtil.l(jSONObject2.getString("udid")));
                }
                if (jSONObject2.has("deviceId")) {
                    jSONObject2.put("deviceId", CommonUtil.l(jSONObject2.getString("deviceId")));
                }
                jSONObject.put("updateLog", jSONObject2);
                return jSONObject.toString();
            }
            LogUtil.a("AppStatusReportThread", "getUpdateLogContent not have updateLog");
            return null;
        } catch (JSONException unused) {
            LogUtil.b("AppStatusReportThread", "getUpdateLogContent JSONException");
            return null;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        int i;
        OutputStream outputStream = null;
        try {
            try {
                try {
                    outputStream = b();
                    if (outputStream != null) {
                        LogUtil.a("AppStatusReportThread", "app status : ", System.lineSeparator(), a(outputStream.toString()));
                        LogUtil.a("AppStatusReportThread", "isHonorDevice: ", Boolean.valueOf(cvz.c(c)));
                        if ((b && this.f14698a && !this.d) || (cvz.c(c) && !this.d && !this.f14698a)) {
                            str = kxz.e() + "/Ring2/v2/UpdateReport.action";
                        } else if (!CommonUtil.as() || this.d) {
                            str = GRSManager.a(BaseApplication.getContext()).getUrl("domainQueryHicloud") + "/ring/v2/UpdateReport.action";
                        } else {
                            str = "";
                        }
                        if (TextUtils.isEmpty(str)) {
                            i = 0;
                        } else if (kxz.e(str)) {
                            i = jeb.b(str, outputStream);
                        } else {
                            i = jee.a(str, outputStream);
                        }
                        LogUtil.a("AppStatusReportThread", "run statusCode = ", Integer.valueOf(i));
                    }
                } catch (RuntimeException unused) {
                    LogUtil.b("AppStatusReportThread", "run RuntimeException");
                }
            } catch (IOException unused2) {
                LogUtil.b("AppStatusReportThread", "run IOException ");
            } catch (JSONException unused3) {
                LogUtil.b("AppStatusReportThread", "run JSONException");
            }
        } finally {
            IoUtils.e(outputStream);
        }
    }

    public OutputStream b() throws IOException, JSONException {
        LogUtil.c("AppStatusReportThread", "convertAppStatusReportInfoToStream JSON begin");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        kxi kxiVar = this.e;
        if (kxiVar == null) {
            LogUtil.b("AppStatusReportThread", "appStatusReportInfo is null");
            return null;
        }
        jSONObject.put("operateType", String.valueOf(kxiVar.e()));
        if (this.d) {
            if (CommonUtil.br()) {
                jSONObject2.put("udid", CommonUtil.h());
                jSONObject2.put("deviceId", CommonUtil.g());
            } else if (CommonUtil.bh()) {
                jSONObject2.put("IMEI", this.e.b());
            } else {
                jSONObject2.put("udid", CommonUtil.an());
                jSONObject2.put("deviceId", CommonUtil.g());
            }
        } else if (CommonUtil.r(this.e.b())) {
            jSONObject2.put("deviceId", this.e.b());
        } else {
            jSONObject2.put("IMEI", this.e.b());
        }
        jSONObject2.put("versionID", this.e.a());
        jSONObject2.put("clientversion", this.e.c());
        jSONObject2.put(BaseEvent.KEY_DESCINFO, this.e.d());
        jSONObject.putOpt("updateLog", jSONObject2);
        byte[] bytes = jSONObject.toString().getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(bytes);
        byteArrayOutputStream.flush();
        return byteArrayOutputStream;
    }

    public void e() {
        Thread thread = new Thread(this);
        thread.setName("Ver-statusReport");
        thread.start();
    }
}
