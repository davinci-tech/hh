package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ooo extends QrCodeDataBase {

    /* renamed from: a, reason: collision with root package name */
    private String f15825a;
    private String b;
    private String c;
    private String d;
    private String e;

    public ooo(String str) {
        super(str);
    }

    public String d() {
        return this.f15825a;
    }

    public void a(String str) {
        this.f15825a = str;
    }

    public String e() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public String b() {
        return this.c;
    }

    public void e(String str) {
        this.c = str;
    }

    public String a() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof String) {
                    byte[] decode = Base64.decode((String) obj, 0);
                    if (decode != null && decode.length > 0) {
                        String str = new String(decode, "UTF-8");
                        LogUtil.c("WifiQrCodeData", "parser result:", str);
                        JSONObject jSONObject = new JSONObject(str);
                        if (TextUtils.isEmpty(str)) {
                            LogUtil.b("R_QrCode_WifiQrCodeData", "parser result is null");
                            return -3;
                        }
                        if (jSONObject.has("nickName")) {
                            c(jSONObject.getString("nickName"));
                        }
                        if (jSONObject.has("devId")) {
                            a(jSONObject.getString("devId"));
                        }
                        if (jSONObject.has("mainHuid")) {
                            d(jSONObject.getString("mainHuid"));
                        }
                        if (jSONObject.has("verifyCode")) {
                            e(jSONObject.getString("verifyCode"));
                        }
                        if (jSONObject.has("proId")) {
                            b(jSONObject.getString("proId"));
                        }
                        return 0;
                    }
                    LogUtil.b("R_QrCode_WifiQrCodeData", "parser dataByte is null");
                    return -3;
                }
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("R_QrCode_WifiQrCodeData", "UnsupportedEncodingException");
                return -2;
            } catch (IllegalArgumentException unused2) {
                LogUtil.b("R_QrCode_WifiQrCodeData", "IllegalArgumentException");
                return -2;
            } catch (JSONException unused3) {
                LogUtil.b("R_QrCode_WifiQrCodeData", "JSONException");
                return -2;
            }
        }
        return -3;
    }

    public boolean c() {
        return TextUtils.isEmpty(this.f15825a) || TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c);
    }
}
