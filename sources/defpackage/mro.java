package defpackage;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.pluginmessagecenter.util.HttpResCallback;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mro {
    private static void b(HttpResCallback httpResCallback, String str, String str2, String str3) {
        if (httpResCallback != null) {
            httpResCallback.onSucceed(str, str2, str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Context context, String str, String str2, final HttpResCallback httpResCallback) {
        if (b(context, str)) {
            return;
        }
        lqi.d().b(str, b(context), str2, String.class, new ResultCallback<String>() { // from class: mro.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str3) {
                LogUtil.a("UIDV_MessagesHttpUtils", "doPost success");
                mro.b(str3, HttpResCallback.this);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("UIDV_MessagesHttpUtils", "doPost fail exception:", ExceptionUtils.d(th));
                mro.b(HttpResCallback.this, -2);
            }
        });
    }

    private static Map<String, String> b(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("accept", "*/*");
        hashMap.put(w9.h, "Keep-Alive");
        hashMap.put("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
        hashMap.put("charset", "utf-8");
        hashMap.put("x-huid", LoginInit.getInstance(context).getAccountInfo(1011));
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("Date", String.valueOf(SystemClock.currentThreadTimeMillis()));
        return hashMap;
    }

    private static boolean b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            return false;
        }
        LogUtil.a("UIDV_MessagesHttpUtils", "url is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, HttpResCallback httpResCallback) {
        b d = d(str);
        if (d != null) {
            if (d.b() == 0) {
                b(httpResCallback, d.d(), d.a(), d.c());
                return;
            } else {
                b(httpResCallback, d.b());
                return;
            }
        }
        LogUtil.b("UIDV_MessagesHttpUtils", "processResult resResult is null");
        b(httpResCallback, -1);
    }

    private static b d(String str) {
        b bVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            bVar = new b(Integer.parseInt(!jSONObject.isNull("resultCode") ? jSONObject.getString("resultCode") : ""), !jSONObject.isNull("resultDesc") ? jSONObject.getString("resultDesc") : "", !jSONObject.isNull("moreInfoUrl") ? jSONObject.getString("moreInfoUrl") : !jSONObject.isNull("messages") ? jSONObject.getString("messages") : "", !jSONObject.isNull("revokeMsgIds") ? jSONObject.getString("revokeMsgIds") : "", jSONObject.isNull("latestGetMsgTimestamp") ? "" : jSONObject.getString("latestGetMsgTimestamp"));
        } catch (JSONException unused) {
            bVar = null;
        }
        try {
            LogUtil.c("UIDV_MessagesHttpUtils", "resResult = ", bVar.toString());
            return bVar;
        } catch (JSONException unused2) {
            LogUtil.b("UIDV_MessagesHttpUtils", "parseResult() ==> JSONException");
            return bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(HttpResCallback httpResCallback, int i) {
        if (httpResCallback != null) {
            httpResCallback.onFailed(i);
        }
    }

    public static void e(final Context context, final String str, final String str2, final HttpResCallback httpResCallback) {
        if (TextUtils.isEmpty(str) || !Utils.i()) {
            LogUtil.a("UIDV_MessagesHttpUtils", "NoCloudVersion no doPostReq");
            return;
        }
        if (CommonUtil.bu()) {
            LogUtil.a("UIDV_MessagesHttpUtils", "storeDemo no message center");
            b(httpResCallback, -1);
        } else if (TextUtils.isEmpty(str2)) {
            LogUtil.b("UIDV_MessagesHttpUtils", "params is null");
            b(httpResCallback, -1);
        } else {
            jdx.b(new Runnable() { // from class: mro.5
                @Override // java.lang.Runnable
                public void run() {
                    Context context2 = context;
                    if (context2 != null) {
                        mro.a(context2, str, str2, httpResCallback);
                    }
                }
            });
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private int f15133a;
        private String b;
        private String c;
        private String d;
        private String e;

        b(int i, String str, String str2, String str3, String str4) {
            this.f15133a = i;
            this.d = str;
            this.e = str2;
            this.c = str3;
            this.b = str4;
        }

        public int b() {
            return this.f15133a;
        }

        public String d() {
            return this.e;
        }

        public String a() {
            return this.c;
        }

        public String c() {
            return this.b;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("HttpResResult{resultCode=");
            sb.append(this.f15133a);
            sb.append(", resultDesc=");
            sb.append(this.d);
            sb.append(", messages=");
            sb.append(this.e);
            sb.append(", revokeMsgIds=");
            sb.append(this.c);
            sb.append(", latestGetMsgTimestamp");
            sb.append(this.b);
            sb.append('}');
            return sb.toString();
        }
    }
}
