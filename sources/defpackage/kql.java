package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hwidauth.api.ResultCallBack;
import com.huawei.hwidauth.c.j;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.hwidauth.ui.WebViewActivity;
import com.huawei.openalliance.ad.constant.VastAttribute;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class kql {

    /* renamed from: a, reason: collision with root package name */
    private Activity f14547a;
    private String b;
    private String c;
    private String d;
    private String e;
    private krf f;
    private String h;
    private ResultCallBack<kqo> i;

    private kql(Activity activity, String str, String str2, String str3, String str4, String str5, ResultCallBack<kqo> resultCallBack) {
        this.f14547a = activity;
        this.b = str;
        this.c = str2;
        this.e = str4;
        this.d = str3;
        this.h = str5;
        this.i = resultCallBack;
        ksi.c(resultCallBack);
    }

    public void e(String str, String str2, boolean z) throws kqk {
        Activity activity = this.f14547a;
        if (activity == null || activity.isFinishing()) {
            throw new kqk("Actvity status invalid");
        }
        if (TextUtils.isEmpty(str)) {
            throw new kqk("grsAppName is empty");
        }
        if (TextUtils.isEmpty(this.b)) {
            throw new kqk("Appid is null");
        }
        if (TextUtils.isEmpty(this.d)) {
            throw new kqk("RedirectUri is null");
        }
        if (this.i == null) {
            throw new kqk("ResultResultCallBack is null");
        }
        if (TextUtils.isEmpty(this.e)) {
            throw new kqk("deviceInfo is null");
        }
        ksy.b("HuaWeiIdSignInClient", "signIn", true);
        ksg.c(this.f14547a, 907115001, 0, "signOAuth start", ksg.a(), "accountPickerH5.signIn_v2", "api_entry");
        if (!TextUtils.isEmpty(str2)) {
            this.f = krf.c();
            d(str, str2, z, "");
        } else {
            d(str, null, "");
        }
    }

    public void e(String str) throws kqk {
        Activity activity = this.f14547a;
        if (activity == null || activity.isFinishing()) {
            throw new kqk("Actvity status invalid");
        }
        if (TextUtils.isEmpty(str)) {
            throw new kqk("grsAppName is empty");
        }
        if (TextUtils.isEmpty(this.b)) {
            throw new kqk("Appid is null");
        }
        if (TextUtils.isEmpty(this.d)) {
            throw new kqk("RedirectUri is null");
        }
        if (this.i == null) {
            throw new kqk("ResultResultCallBack is null");
        }
        if (TextUtils.isEmpty(this.e)) {
            throw new kqk("deviceInfo is null");
        }
        ksy.b("HuaWeiIdSignInClient", "signIn", true);
        ksg.c(this.f14547a, 907115001, 0, "signOAuth start", ksg.a(), "accountPickerH5.signIn_v2", "api_entry");
        d(str, null, "");
    }

    private void d(final String str, final String str2, final boolean z, final String str3) {
        ksy.b("HuaWeiIdSignInClient", "doRequest init.", true);
        this.f.c(this.f14547a.getApplicationContext(), str, new kqz(this.f14547a.getApplicationContext(), this.b, str2, this.c, this.d), new j() { // from class: kql.4
            @Override // com.huawei.hwidauth.c.j
            public void onSuccess(String str4) {
                ksy.b("HuaWeiIdSignInClient", "onSuccess response = " + str4, false);
                if (!TextUtils.isEmpty(str4)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str4);
                        if (jSONObject.has("code")) {
                            String string = jSONObject.getString("code");
                            kql kqlVar = kql.this;
                            kqlVar.b(200, "success", string, kqlVar.i, str3);
                        } else if (jSONObject.has(VastAttribute.ERROR) && "1301".equals(jSONObject.getString(VastAttribute.ERROR))) {
                            ksy.b("HuaWeiIdSignInClient", "unauthorized_code startWebViewActivity", true);
                            kql.this.d(str, str2, str3);
                        }
                        return;
                    } catch (JSONException unused) {
                        ksy.c("HuaWeiIdSignInClient", "JSONException", true);
                        kql kqlVar2 = kql.this;
                        kqlVar2.b(-1, "User cancel", "", kqlVar2.i, str3);
                        return;
                    }
                }
                ksy.b("HuaWeiIdSignInClient", "onSuccess response empty", true);
                kql kqlVar3 = kql.this;
                kqlVar3.b(-1, "User cancel", "", kqlVar3.i, str3);
            }

            @Override // com.huawei.hwidauth.c.j
            public void onFailure(int i, String str4) {
                ksy.b("HuaWeiIdSignInClient", "onFailure, errorCode is " + i, true);
                kql.this.c(str, i, str4, str2, z, str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i, String str2, String str3, boolean z, String str4) {
        ksy.b("HuaWeiIdSignInClient", "dealFailRequestResult errorCode = " + i, true);
        ksy.b("HuaWeiIdSignInClient", "dealFailRequestResult errorContent = " + str2, false);
        try {
            if (!TextUtils.isEmpty(str2)) {
                String string = new JSONObject(str2).getString(VastAttribute.ERROR);
                if ("1301".equals(string)) {
                    ksy.b("HuaWeiIdSignInClient", "unauthorized_code startWebViewActivity", true);
                    d(str, str3, str4);
                } else if ("1203".equals(string)) {
                    ksy.b("HuaWeiIdSignInClient", "token revoked startWebViewActivity", true);
                    if (z) {
                        d(str, "", str4);
                    } else {
                        ksg.c(this.f14547a, 907115001, 404, "Access Token is Invalid", str4, "accountPickerH5.signIn_v2", "api_ret");
                        this.i.onResult(new kqo("", new kqm(2008, "Access Token is Invalid")));
                    }
                } else {
                    ksy.b("HuaWeiIdSignInClient", "dealFailRequestResult other error", true);
                    ksg.c(this.f14547a, 907115001, 404, "signOAuth fail", str4, "accountPickerH5.signIn_v2", "api_ret");
                    this.i.onResult(new kqo("", new kqm(2003, AuthInternalPickerConstant.PARAM_ERROR)));
                }
            }
        } catch (JSONException unused) {
            ksy.c("HuaWeiIdSignInClient", "JSONException ", true);
            b(i, "User cancel", "", this.i, str4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2, String str3) {
        try {
            Intent intent = new Intent(this.f14547a, (Class<?>) WebViewActivity.class);
            intent.putExtra("key_redirecturi", this.d);
            intent.putExtra("key_app_id", this.b);
            intent.putExtra("key_scopes", this.c);
            intent.putExtra("key_transId", str3);
            intent.putExtra("key_oper", "from_signin");
            intent.putExtra("key_device_info", (Parcelable) DeviceInfo.a(this.f14547a, this.e));
            intent.putExtra("key_extends_param", this.h);
            intent.putExtra("grs_app_name", str);
            if (!TextUtils.isEmpty(str2)) {
                ksy.b("HuaWeiIdSignInClient", "accessToken not empty", true);
                intent.putExtra("key_access_token", str2);
            }
            this.f14547a.startActivity(intent);
        } catch (IllegalArgumentException e) {
            ksy.c("HuaWeiIdSignInClient", "IllegalArgumentException", true);
            ksg.c(this.f14547a, 907115001, 404, "IllegalArgumentException:" + e.getMessage(), str3, "accountPickerH5.signIn_v2", "api_ret");
        } catch (RuntimeException e2) {
            ksy.c("HuaWeiIdSignInClient", "RuntimeException", true);
            ksg.c(this.f14547a, 907115001, 404, "RuntimeException:" + e2.getMessage(), str3, "accountPickerH5.signIn_v2", "api_ret");
        } catch (Exception e3) {
            ksy.c("HuaWeiIdSignInClient", "Exception", true);
            ksg.c(this.f14547a, 907115001, 404, "Exception:" + e3.getMessage(), str3, "accountPickerH5.signIn_v2", "api_ret");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str, String str2, ResultCallBack<kqo> resultCallBack, String str3) {
        if (resultCallBack != null) {
            kqm kqmVar = new kqm(i, str);
            if (200 == i) {
                kqmVar.b(true);
                ksg.c(this.f14547a, 907115001, 200, "signOAuth ok", str3, "accountPickerH5.signIn_v2", "api_ret");
            } else {
                kqmVar.b(false);
                ksg.c(this.f14547a, 907115001, 404, "signOAuth fail", str3, "accountPickerH5.signIn_v2", "api_ret");
            }
            resultCallBack.onResult(new kqo(str2, kqmVar));
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f14549a;
        private Activity b;
        private String c;
        private String d;
        private String e;
        private String g;
        private ResultCallBack<kqo> j;

        public c(Activity activity) {
            this.b = activity;
        }

        public c a(String str) {
            this.f14549a = str;
            return this;
        }

        public c a(String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                this.d = "";
            } else {
                StringBuilder sb = new StringBuilder();
                ksy.b("HuaWeiIdSignInClient", "scopes length " + strArr.length, true);
                for (String str : strArr) {
                    sb.append(str);
                    sb.append(" ");
                }
                this.d = sb.toString();
            }
            return this;
        }

        public c c(String str) {
            this.e = str;
            return this;
        }

        public c d(String str) {
            this.c = str;
            return this;
        }

        public c b(String str) {
            this.g = str;
            return this;
        }

        public c a(ResultCallBack<kqo> resultCallBack) {
            this.j = resultCallBack;
            return this;
        }

        public kql c() {
            return new kql(this.b, this.f14549a, this.d, this.e, this.c, this.g, this.j);
        }
    }
}
