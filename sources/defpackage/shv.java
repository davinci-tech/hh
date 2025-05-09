package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.apacheclient.ApacheClientInstrumentation;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class shv {

    /* renamed from: a, reason: collision with root package name */
    private String f17064a;
    private String b;
    private String c;
    private Context d;
    private String e;

    public shv() {
        Context context = BaseApplication.getContext();
        this.d = context;
        this.f17064a = CommonUtil.k(context);
        this.e = LoginInit.getInstance(this.d).getAccountInfo(1008);
        this.c = LoginInit.getInstance(this.d).getAccountInfo(1011);
        this.b = GRSManager.a(this.d).getUrl("healthCloudUrl") + "/dataOpen/weixin/createQrcodeTicketsNew";
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("WechatRankingCloudInteractor", "getQrCodeTicket");
        if (iBaseResponseCallback == null) {
            LogUtil.b("WechatRankingCloudInteractor", "callback is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: shv.4
                @Override // java.lang.Runnable
                public void run() {
                    String d = shv.this.d();
                    LogUtil.c("WechatRankingCloudInteractor", "getQrCodeTicket ticketStr is ", d);
                    shv.this.a(d, iBaseResponseCallback);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v23 */
    /* JADX WARN: Type inference failed for: r13v30, types: [java.io.Closeable, java.io.InputStream] */
    private String c(String str) {
        Throwable th;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        InputStreamReader inputStreamReader3;
        ?? r13;
        HttpResponse execute;
        int statusCode;
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.e)) {
            LogUtil.b("WechatRankingCloudInteractor", "mCreateQrCodeUrl or param is null");
            return "";
        }
        InputStreamReader inputStreamReader4 = null;
        try {
            HttpPost httpPost = new HttpPost(this.b);
            httpPost.setHeader("x-huid", this.c);
            httpPost.setHeader("x-version", CommonUtil.c(this.d));
            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
            httpPost.setEntity(new StringEntity(str, "utf-8"));
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            defaultHttpClient.getParams().setParameter("http.connection.timeout", 10000);
            defaultHttpClient.getParams().setParameter("http.socket.timeout", 10000);
            execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost) : ApacheClientInstrumentation.execute(defaultHttpClient, httpPost);
            statusCode = execute.getStatusLine().getStatusCode();
            LogUtil.a("WechatRankingCloudInteractor", "getQrCodeTicket: response = ", Integer.valueOf(statusCode));
        } catch (IllegalArgumentException e) {
            e = e;
            inputStreamReader3 = null;
            try {
                LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
                inputStreamReader2 = inputStreamReader3;
                FileUtils.d(inputStreamReader4);
                FileUtils.d(inputStreamReader2);
                return "";
            } catch (Throwable th2) {
                th = th2;
                r13 = inputStreamReader4;
                inputStreamReader4 = inputStreamReader3;
                InputStreamReader inputStreamReader5 = inputStreamReader4;
                inputStreamReader4 = r13;
                inputStreamReader = inputStreamReader5;
                FileUtils.d(inputStreamReader4);
                FileUtils.d(inputStreamReader);
                throw th;
            }
        } catch (IllegalStateException e2) {
            e = e2;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (UnknownHostException e3) {
            e = e3;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (ClientProtocolException e4) {
            e = e4;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (HttpHostConnectException e5) {
            e = e5;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (IOException unused) {
            inputStreamReader2 = null;
        } catch (Throwable th3) {
            th = th3;
            inputStreamReader = null;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader);
            throw th;
        }
        if (statusCode != 200) {
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket Network error!");
            FileUtils.d((Closeable) null);
            FileUtils.d((Closeable) null);
            return "";
        }
        r13 = execute.getEntity().getContent();
        try {
            inputStreamReader3 = new InputStreamReader((InputStream) r13, "UTF-8");
            try {
                String a2 = a(inputStreamReader3);
                FileUtils.d((Closeable) r13);
                FileUtils.d(inputStreamReader3);
                return a2;
            } catch (IOException unused2) {
                inputStreamReader4 = inputStreamReader3;
                InputStreamReader inputStreamReader6 = inputStreamReader4;
                inputStreamReader4 = r13;
                inputStreamReader2 = inputStreamReader6;
                try {
                    LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket IOException");
                    FileUtils.d(inputStreamReader4);
                    FileUtils.d(inputStreamReader2);
                    return "";
                } catch (Throwable th4) {
                    th = th4;
                    InputStreamReader inputStreamReader7 = inputStreamReader4;
                    inputStreamReader4 = inputStreamReader2;
                    r13 = inputStreamReader7;
                    InputStreamReader inputStreamReader52 = inputStreamReader4;
                    inputStreamReader4 = r13;
                    inputStreamReader = inputStreamReader52;
                    FileUtils.d(inputStreamReader4);
                    FileUtils.d(inputStreamReader);
                    throw th;
                }
            } catch (IllegalArgumentException e6) {
                e = e6;
                Exception exc = e;
                inputStreamReader4 = r13;
                e = exc;
                LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
                inputStreamReader2 = inputStreamReader3;
                FileUtils.d(inputStreamReader4);
                FileUtils.d(inputStreamReader2);
                return "";
            } catch (IllegalStateException e7) {
                e = e7;
                Exception exc2 = e;
                inputStreamReader4 = r13;
                e = exc2;
                LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
                inputStreamReader2 = inputStreamReader3;
                FileUtils.d(inputStreamReader4);
                FileUtils.d(inputStreamReader2);
                return "";
            } catch (UnknownHostException e8) {
                e = e8;
                Exception exc22 = e;
                inputStreamReader4 = r13;
                e = exc22;
                LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
                inputStreamReader2 = inputStreamReader3;
                FileUtils.d(inputStreamReader4);
                FileUtils.d(inputStreamReader2);
                return "";
            } catch (ClientProtocolException e9) {
                e = e9;
                Exception exc222 = e;
                inputStreamReader4 = r13;
                e = exc222;
                LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
                inputStreamReader2 = inputStreamReader3;
                FileUtils.d(inputStreamReader4);
                FileUtils.d(inputStreamReader2);
                return "";
            } catch (HttpHostConnectException e10) {
                e = e10;
                Exception exc2222 = e;
                inputStreamReader4 = r13;
                e = exc2222;
                LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
                inputStreamReader2 = inputStreamReader3;
                FileUtils.d(inputStreamReader4);
                FileUtils.d(inputStreamReader2);
                return "";
            } catch (Throwable th5) {
                th = th5;
                inputStreamReader4 = inputStreamReader3;
                InputStreamReader inputStreamReader522 = inputStreamReader4;
                inputStreamReader4 = r13;
                inputStreamReader = inputStreamReader522;
                FileUtils.d(inputStreamReader4);
                FileUtils.d(inputStreamReader);
                throw th;
            }
        } catch (IllegalArgumentException e11) {
            e = e11;
            inputStreamReader4 = r13;
            e = e;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (IllegalStateException e12) {
            e = e12;
            inputStreamReader4 = r13;
            e = e;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (UnknownHostException e13) {
            e = e13;
            inputStreamReader4 = r13;
            e = e;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (ClientProtocolException e14) {
            e = e14;
            inputStreamReader4 = r13;
            e = e;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (HttpHostConnectException e15) {
            e = e15;
            inputStreamReader4 = r13;
            e = e;
            inputStreamReader3 = null;
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket error,", e.getMessage());
            inputStreamReader2 = inputStreamReader3;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader2);
            return "";
        } catch (IOException unused3) {
        } catch (Throwable th6) {
            th = th6;
            InputStreamReader inputStreamReader5222 = inputStreamReader4;
            inputStreamReader4 = r13;
            inputStreamReader = inputStreamReader5222;
            FileUtils.d(inputStreamReader4);
            FileUtils.d(inputStreamReader);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("WechatRankingCloudInteractor", "qrCodeInfo is null");
            iBaseResponseCallback.d(-1, null);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("ticket");
            String optString2 = jSONObject.optString("verifyCode");
            String optString3 = jSONObject.optString("expireTime");
            String optString4 = jSONObject.optString("resultCode");
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3)) {
                shy shyVar = new shy(a(optString, optString2), CommonUtil.n(this.d, optString3), 5);
                if ("601010".equals(optString4)) {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_BIND_WECHAT_85070019.value(), CommonUtil.m(this.d, optString4));
                    iBaseResponseCallback.d(2, shyVar);
                    return;
                } else {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_BIND_WECHAT_85070019.value(), CommonUtil.m(this.d, "0"));
                    iBaseResponseCallback.d(0, shyVar);
                    return;
                }
            }
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket ticket/verifyCode/expireTime is empty");
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_BIND_WECHAT_85070019.value(), CommonUtil.m(this.d, optString4));
            iBaseResponseCallback.d(-1, null);
        } catch (JSONException e) {
            LogUtil.b("WechatRankingCloudInteractor", "getQrCodeTicket JSONException,e is ", e.getMessage());
            iBaseResponseCallback.d(-1, null);
        }
    }

    private String a(String str, String str2) {
        return str + "#" + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() {
        LogUtil.a("WechatRankingCloudInteractor", "getTicketByRequestUrl");
        String c = c(d(1));
        if (e(c)) {
            return c;
        }
        LogUtil.h("WechatRankingCloudInteractor", "getTicketByRequestUrl not bound");
        return c(d(0));
    }

    private boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WechatRankingCloudInteractor", "isTicketValid: ticket is empty");
            return false;
        }
        String e = jah.c().e("domain_we_qq");
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("WechatRankingCloudInteractor", "isTicketValid: get tencentDomainName fail");
            return false;
        }
        return str.contains(e);
    }

    private String d(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mac", "");
            jSONObject.put("opType", i);
            jSONObject.put("productId", "");
            jSONObject.put("devType", "classical");
            jSONObject.put("source", 1);
            jSONObject.put("token", this.e);
            jSONObject.put("tokenType", ThirdLoginDataStorageUtil.getTokenTypeValue());
            jSONObject.put("appId", BaseApplication.getAppPackage());
            jSONObject.put("deviceId", this.f17064a);
            jSONObject.put("upDeviceType", 9);
            jSONObject.put("environment", 1);
            jSONObject.put("ts", System.currentTimeMillis());
            LogUtil.a("WechatRankingCloudInteractor", "ts is", Long.valueOf(System.currentTimeMillis()));
        } catch (JSONException unused) {
            LogUtil.a("WechatRankingCloudInteractor", "JSONException e");
        }
        return jSONObject.toString();
    }

    private String a(InputStreamReader inputStreamReader) {
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder(1024);
        int i = 0;
        do {
            try {
                int read = inputStreamReader.read(cArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                sb.append(cArr, 0, read);
                i += read;
            } catch (IOException unused) {
                LogUtil.b("WechatRankingCloudInteractor", "inputStreamReader2String IOException");
            }
        } while (i <= 10485760);
        return sb.toString();
    }
}
