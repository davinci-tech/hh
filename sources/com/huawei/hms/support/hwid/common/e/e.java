package com.huawei.hms.support.hwid.common.e;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hms.support.hwid.bean.CheckPasswordByUserIdReq;
import com.huawei.hms.support.hwid.common.cloudservice.CloudRequestHandler;
import com.huawei.hwid.core.helper.handler.ErrorStatus;
import com.huawei.up.request.HttpRequestBase;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes9.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static final e f6008a = new e();
    private String b = "";
    private String c = "";
    private Context d;
    private CloudRequestHandler e;

    public static e a() {
        return f6008a;
    }

    public void a(Context context, CheckPasswordByUserIdReq checkPasswordByUserIdReq, String str, String str2, String str3, CloudRequestHandler cloudRequestHandler) {
        this.b = str3;
        this.c = checkPasswordByUserIdReq.getPackageName();
        this.d = context;
        this.e = cloudRequestHandler;
        final com.huawei.hms.support.hwid.common.c.a.b bVar = new com.huawei.hms.support.hwid.common.c.a.b(context, checkPasswordByUserIdReq, str, str2, str3);
        RestClientGlobalInstance.getInstance().init(this.d);
        RestClient build = new RestClient.Builder(this.d).build();
        if (build == null) {
            a(2003, "restClient is null");
            return;
        }
        a aVar = (a) build.create(a.class);
        try {
            aVar.a(bVar.d(), RequestBody.create(" text/html; charset=utf-8", bVar.e().getBytes("UTF-8"))).enqueue(new ResultCallback<ResponseBody>() { // from class: com.huawei.hms.support.hwid.common.e.e.1
                @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                public void onResponse(Response<ResponseBody> response) {
                    g.a("GetVerifyPwdUtil", "request getResource for siteDomain success");
                    e.this.a(bVar, response);
                }

                @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                public void onFailure(Throwable th) {
                    g.a("GetVerifyPwdUtil", "request getResource for siteDomain onFailure");
                    e.this.a(2016, "request getResource for siteDomain onFailure");
                }
            });
        } catch (IOException unused) {
            g.a("GetVerifyPwdUtil", "request getResource for siteDomain IOException");
            a(2016, "request getResource for siteDomain IOException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.huawei.hms.support.hwid.common.c.a.b bVar, Response<ResponseBody> response) {
        g.a("GetVerifyPwdUtil", "handleRequestNet");
        int code = response.getCode();
        g.a("GetVerifyPwdUtil", "code:" + code);
        if (code == 200) {
            g.a("GetVerifyPwdUtil", "GetResourceRequest success");
            try {
                bVar.a(new String(response.getBody().bytes(), "UTF-8"));
                Bundle b = bVar.b();
                String string = b.getString(CommonConstant.KEY_ID_TOKEN);
                if (!TextUtils.isEmpty(string)) {
                    g.a("GetVerifyPwdUtil", "idToken is:" + string, false);
                    Bundle bundle = new Bundle();
                    bundle.putString(CommonConstant.KEY_ID_TOKEN, string);
                    f.a(this.b, "hwid.checkPasswordByUserId", this.c);
                    this.e.onFinish(bundle);
                } else {
                    int i = b.getInt("errorCode");
                    g.a("GetVerifyPwdUtil", "handleRequestNet errorCode:" + i, true);
                    a(2016, i, b.getString(HttpRequestBase.TAG_ERROR_DESC));
                }
                return;
            } catch (IOException unused) {
                g.a("GetVerifyPwdUtil", "IOException");
                a(2015, "IOException");
                return;
            } catch (XmlPullParserException unused2) {
                g.a("GetVerifyPwdUtil", "XmlPullParserException");
                a(2015, "XmlPullParserException");
                return;
            }
        }
        a(2016, "request error.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        a(i, i, str);
    }

    private void a(int i, int i2, String str) {
        ErrorStatus errorStatus = new ErrorStatus(i, str);
        errorStatus.setSubErrCode(i2);
        this.e.onError(errorStatus);
        f.a(this.b, "hwid.checkPasswordByUserId", i, this.c);
    }
}
