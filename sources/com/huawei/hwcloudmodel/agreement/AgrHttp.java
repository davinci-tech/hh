package com.huawei.hwcloudmodel.agreement;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.hwcloudmodel.https.HttpResCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.jbj;
import defpackage.jdx;
import health.compact.a.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes5.dex */
public class AgrHttp {
    private static final String QUERY_NSP = "as.user.query";
    private static final String SIGN_NSP = "as.user.sign";
    private static final String TAG = "SignAgrHttp";

    public String signHttpReq(String str, String str2, boolean z, List<Integer> list, String str3, String str4, HttpResCallBack httpResCallBack) {
        LogUtil.a(TAG, "Show_signAgrReqBean CountryCode ", str3);
        String json = new Gson().toJson(composeSignAgrReqBean(str3, str4, z, list));
        LogUtil.a(TAG, "Show_signAgrReqBean ", json);
        String obtainBody = obtainBody(SIGN_NSP, str, json);
        LogUtil.a(TAG, "Show_signAgrReqBean reqBody ", obtainBody);
        try {
            postReq(str2, obtainBody, obtainHeader(new URL(str2).getHost()), httpResCallBack);
        } catch (IOException unused) {
            LogUtil.b(TAG, "agr_sign_response data exception");
        }
        return json;
    }

    public String queryHttpReq(String str, String str2, boolean z, HttpResCallBack httpResCallBack) {
        String json = new Gson().toJson(composeQueryAgrReqBean(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010), z));
        LogUtil.a(TAG, "Show_queryAgrReqBean ", json);
        try {
            postReq(str2, obtainBody(QUERY_NSP, str, json), obtainHeader(new URL(str2).getHost()), httpResCallBack);
            return "";
        } catch (IOException unused) {
            LogUtil.b(TAG, "queryHttpReq data IOException");
            return "";
        }
    }

    public String queryHttpBetaReq(String str, String str2, HttpResCallBack httpResCallBack) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(new QueryAgreementRequestInfo(AgrConstant.BETA_USER_AGREEMENT_CODE, accountInfo));
        String json = new Gson().toJson(new QueryAgreementRequestBean(arrayList));
        LogUtil.a(TAG, "Show_queryHttpBetaReq ", json);
        try {
            postReq(str2, obtainBody(QUERY_NSP, str, json), obtainHeader(new URL(str2).getHost()), httpResCallBack);
            return "";
        } catch (IOException unused) {
            LogUtil.b(TAG, "queryHttpBetaReq data IOException");
            httpResCallBack.onFinished(-1, null);
            return "";
        }
    }

    public static int postReq(String str, String str2, HashMap<String, String> hashMap, HttpResCallBack httpResCallBack) {
        LogUtil.a(TAG, "entry Https.postReq Url:", str, ", Param:", str2);
        HttpsURLConnection b = jbj.b(str);
        if (b == null) {
            if (httpResCallBack != null) {
                httpResCallBack.onFinished(-1, null);
            }
            return -1;
        }
        jbj.c(b);
        b.setDoOutput(true);
        b.setDoInput(true);
        b.setUseCaches(false);
        try {
            b.setRequestMethod("POST");
            if (hashMap != null) {
                jbj.b(b, hashMap);
            }
            LogUtil.c(TAG, "postReq-->stringBody:", str2);
            jdx.b(startRunnable(str2, b, httpResCallBack));
            LogUtil.a(TAG, "exit AchieveHttps.postReq");
            return 0;
        } catch (ProtocolException unused) {
            LogUtil.b(TAG, "postReq ProtocolException");
            return -1;
        }
    }

    private static Runnable startRunnable(final String str, final HttpsURLConnection httpsURLConnection, final HttpResCallBack httpResCallBack) {
        return new Runnable() { // from class: com.huawei.hwcloudmodel.agreement.AgrHttp.1
            /* JADX WARN: Removed duplicated region for block: B:22:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 250
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwcloudmodel.agreement.AgrHttp.AnonymousClass1.run():void");
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dealFinallyStream(OutputStream outputStream, InputStream inputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
                LogUtil.b(TAG, "dealFinallyStream Exception");
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused2) {
                LogUtil.b(TAG, "dealFinallyStream Exception");
            }
        }
    }

    private String obtainBody(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        String str4 = "nsp_svc=" + str;
        try {
            return (str4 + "&access_token=" + URLEncoder.encode(str2, "UTF-8")) + "&request=" + URLEncoder.encode(str3, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b(TAG, "obtainBody UnsupportedEncodingException");
            return str4;
        }
    }

    private SignAgreementRequestBean composeSignAgrReqBean(String str, String str2, boolean z, List<Integer> list) {
        ArrayList arrayList = new ArrayList(10);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new SignAgreementRequestInfo(it.next().intValue(), z, str, str2));
        }
        return new SignAgreementRequestBean(arrayList);
    }

    private QueryAgreementRequestBean composeQueryAgrReqBean(String str, boolean z) {
        ArrayList arrayList = new ArrayList(10);
        if (z && !Utils.o()) {
            arrayList.add(new QueryAgreementRequestInfo(134, str));
            arrayList.add(new QueryAgreementRequestInfo(10023, str));
        }
        arrayList.add(new QueryAgreementRequestInfo(118, str));
        arrayList.add(new QueryAgreementRequestInfo(10009, str));
        return new QueryAgreementRequestBean(arrayList);
    }

    private HashMap<String, String> obtainHeader(String str) {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("Content-Type", "text/plain; charset=UTF-8");
        hashMap.put("Content-Encoding", Constants.GZIP);
        hashMap.put("Connection", "Keep-Alive");
        hashMap.put("User-Agent", getUserAgent());
        hashMap.put("Host", str);
        return hashMap;
    }

    protected String getUserAgent() {
        return "Android/1.0";
    }
}
