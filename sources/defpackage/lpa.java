package defpackage;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lpa {
    private static final String b = "AkaAuth";
    private final AuthParam c;
    private final Message d;
    private final Context e;

    /* renamed from: a, reason: collision with root package name */
    private final ResponseHandler f14822a = new ResponseHandler() { // from class: lpa.2
        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(String str) {
            if (lop.c(str)) {
                lpa.this.c(str);
                return;
            }
            if (lpa.this.c == null) {
                return;
            }
            loq.a(lpa.b, "AkaFirstAuthResult = " + str);
            loq.c(lpa.b, "AkaFirstAuthResult response begin to parse");
            String a2 = lpa.this.a(str);
            if (TextUtils.isEmpty(a2)) {
                loq.c(lpa.b, "entitleResponse.getEapAkaInfo() is null");
                lop.bYp_(lpa.this.d, 1101);
                return;
            }
            loq.a(lpa.b, "AkaFirstAuthResponsePayload =" + a2);
            String d = new lpc(lpa.this.e, lpa.this.c.getImsi()).d(a2, lpa.this.c.getSlotId());
            loq.a(lpa.b, "start second response payload = " + d);
            if (TextUtils.isEmpty(d)) {
                loq.b(lpa.b, "fail to get second response payload.");
                lop.bYp_(lpa.this.d, 1102);
                return;
            }
            String a3 = lop.a(lpa.this.e, lpa.this.c.getImsi(), "cookie");
            loq.a(lpa.b, "payload:" + d + " cookie:" + a3);
            lpa.this.e(d, a3);
        }
    };
    private final ResponseHandler j = new ResponseHandler() { // from class: lpa.1
        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(String str) {
            lpa.this.d(str);
            if (lpa.this.c != null) {
                lop.b(lpa.this.e, lpa.this.c.getImsi(), "cookie");
            }
        }
    };

    public lpa(Context context, AuthParam authParam, Message message) {
        this.e = context;
        this.d = message;
        this.c = authParam;
    }

    public void a(AbsPrimaryDevice absPrimaryDevice) {
        String b2;
        if (this.c == null) {
            return;
        }
        String a2 = lop.a("ODSA_ESURL", "");
        if (TextUtils.isEmpty(a2)) {
            loq.b(b, "get first aka url fail.");
            lop.bYp_(this.d, 1103);
            return;
        }
        if (lop.e("ODSA_SUPPORT_POST_METHOD", (Boolean) false).booleanValue()) {
            b2 = e(absPrimaryDevice);
        } else {
            b2 = b(absPrimaryDevice);
        }
        if (TextUtils.isEmpty(b2)) {
            loq.b(b, "get first aka param fail.");
            lop.bYp_(this.d, ExceptionCode.CHECK_FILE_HASH_FAILED);
        } else {
            loi d = loi.d();
            lop.b(this.e, this.c.getImsi(), "cookie");
            d.e(a2, b2, this.c.getImsi(), this.f14822a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        int indexOf = str.indexOf(":");
        int lastIndexOf = str.lastIndexOf("\"");
        int i = indexOf + 2;
        if (lastIndexOf <= i) {
            loq.d(b, "invalid response.");
            return null;
        }
        String substring = str.substring(i, lastIndexOf);
        loq.d(b, "get eap-relay-packet = " + substring);
        return substring;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        String a2 = lop.a("ODSA_ESURL", "");
        if (TextUtils.isEmpty(a2)) {
            loq.b(b, "get second aka url fail.");
            lop.bYp_(this.d, 1103);
            return;
        }
        String b2 = b(str);
        if (TextUtils.isEmpty(b2)) {
            loq.b(b, "get second aka param fail.");
            lop.bYp_(this.d, ExceptionCode.CHECK_FILE_HASH_FAILED);
        } else {
            loi.d().c(a2, b2, str2, this.j);
        }
    }

    private String b(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("eap-relay-packet", str);
            return jSONObject.toString();
        } catch (JSONException unused) {
            loq.b(b, "generateJsonStr error");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        String str2 = b;
        loq.a(str2, "aka second authentication response = " + str);
        if (lop.c(str)) {
            c(str);
            return;
        }
        int lastIndexOf = str.lastIndexOf("&");
        String substring = str.substring(lastIndexOf + 1);
        if (lastIndexOf >= 0) {
            str = str.substring(0, lastIndexOf);
        }
        lpt lptVar = new lpt();
        if (substring.contains("json")) {
            lptVar.e(str);
        } else {
            lptVar.d(str);
        }
        if (lptVar.a() != null) {
            loq.a(str2, "AkaAuth token:" + lptVar.a().b());
            lpu.a(lptVar.a().b());
            lop.bYp_(this.d, 1100);
            return;
        }
        loq.c(str2, ".getTokenInfo()=null");
        lop.bYp_(this.d, 1111);
    }

    private String b(AbsPrimaryDevice absPrimaryDevice) {
        AuthParam authParam = this.c;
        if (authParam == null || absPrimaryDevice == null) {
            return "";
        }
        int slotId = authParam.getSlotId();
        String str = b;
        loq.d(str, "getFirstAkaRequestParam = " + slotId);
        String terminalId = absPrimaryDevice.getTerminalId();
        String e = new lpc(this.e, this.c.getImsi()).e(slotId);
        String a2 = lop.a(this.e, this.c.getImsi(), "entitlement_version");
        if (TextUtils.isEmpty(a2)) {
            a2 = "0";
        }
        StringBuilder sb = new StringBuilder("?terminal_id=");
        sb.append(terminalId);
        sb.append("&EAP_ID=");
        sb.append(e);
        sb.append("&app=ap2006&vers=");
        sb.append(a2);
        sb.append("&app_name=Health&terminal_model=");
        sb.append(absPrimaryDevice.getTerminalModel());
        sb.append("&terminal_iccid=");
        sb.append(lop.d());
        sb.append("&terminal_vendor=");
        sb.append(absPrimaryDevice.getTerminalVendor());
        sb.append("&terminal_sw_version=");
        sb.append(absPrimaryDevice.getTerminalSwVersion());
        sb.append("&entitlement_version=");
        sb.append(lop.a("ODSA_ES_VERSION", "1.0"));
        if (lop.e("ODSA_SUPPORT_NOTIFICATION_SERVICE", (Boolean) true).booleanValue()) {
            String a3 = lop.a(this.e, this.c.getImsi(), "notif_token");
            sb.append("&notif_token=");
            sb.append(a3);
            sb.append("&notif_action=1");
        }
        loq.c(str, "getFirstAkaRequestParam slot:" + slotId);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        String str2 = b;
        loq.c(str2, "AkaAuthFailCode =" + str);
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt == 403) {
                loq.c(str2, "Response result is 403 Forbidden");
            } else if (parseInt == 409) {
                loq.c(str2, "Response result is 409 Conflict a duplicate value was provided for the friendly_device_name");
            } else if (parseInt == 500) {
                loq.c(str2, "Response result is 500 Internal Server error");
            } else if (parseInt == 503) {
                loq.c(str2, "Response result is 503 Retry after / Service Unavailable");
            } else if (parseInt == 511) {
                loq.c(str2, "Response result is 511 Network Authentication Required");
            } else if (parseInt == 1112) {
                loq.c(str2, "x509 error.");
            } else if (parseInt == 1113) {
                loq.c(str2, "Net is error");
            } else {
                loq.c(str2, "undefine error. resultCode = " + parseInt);
            }
            loq.c(str2, "AKA Auth Fail.");
            lop.bYp_(this.d, parseInt);
        } catch (NumberFormatException unused) {
            loq.c(b, "Response result code error");
            lop.bYp_(this.d, ExceptionCode.FILE_IO_EXCEPTION);
        }
    }

    private String e(AbsPrimaryDevice absPrimaryDevice) {
        AuthParam authParam = this.c;
        if (authParam == null || absPrimaryDevice == null) {
            return "";
        }
        int slotId = authParam.getSlotId();
        loq.d(b, "getFirstAkaPostRequestParam = " + slotId);
        String terminalId = absPrimaryDevice.getTerminalId();
        String e = new lpc(this.e, this.c.getImsi()).e(slotId);
        String a2 = lop.a(this.e, this.c.getImsi(), "entitlement_version");
        if (TextUtils.isEmpty(a2)) {
            a2 = "0";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("terminal_id", terminalId);
            jSONObject.put("EAP_ID", e);
            jSONObject.put("app", "ap2006");
            jSONObject.put("vers", a2);
            jSONObject.put("terminal_iccid", lop.d());
            jSONObject.put("app_name", "Health");
            jSONObject.put("terminal_model", absPrimaryDevice.getTerminalModel());
            jSONObject.put("terminal_vendor", absPrimaryDevice.getTerminalVendor());
            jSONObject.put("terminal_sw_version", absPrimaryDevice.getTerminalSwVersion());
            jSONObject.put("entitlement_version", lop.a("ODSA_ES_VERSION", "1.0"));
            if (lop.e("ODSA_SUPPORT_NOTIFICATION_SERVICE", (Boolean) true).booleanValue()) {
                jSONObject.put("notif_token", lop.a(this.e, this.c.getImsi(), "notif_token"));
                jSONObject.put("notif_action", 1);
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            loq.b(b, "generateJsonStr error");
            return "";
        }
    }
}
