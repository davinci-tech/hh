package defpackage;

import android.os.Bundle;
import android.os.Looper;
import com.google.gson.JsonSyntaxException;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.request.InvoiceApplicationApi;
import com.huawei.ui.main.stories.health.request.InvoiceInfoFactory;
import com.huawei.ui.main.stories.health.request.InvoiceRequestInfo;
import com.huawei.ui.main.stories.health.request.InvoiceResponse;
import com.huawei.ui.main.stories.health.request.RespDownloadInvoice;
import com.huawei.ui.main.stories.health.request.RespQueryResult;
import health.compact.a.ReleaseLogUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* loaded from: classes8.dex */
public class qrg {
    private static void dHD_(qmr qmrVar, Bundle bundle) {
        int i = bundle.getInt("invoiceType");
        String string = bundle.getString("content");
        int i2 = bundle.getInt("customerType");
        String string2 = bundle.getString("customerName");
        String string3 = bundle.getString("taxNo");
        String string4 = bundle.getString("email");
        String string5 = bundle.getString("orderCode");
        double d = bundle.getDouble(HwPayConstant.KEY_AMOUNT);
        String string6 = bundle.getString("locale");
        qmrVar.e(String.valueOf(i));
        qmrVar.b(string);
        qmrVar.a(String.valueOf(i2));
        qmrVar.c(string2);
        qmrVar.j(string3);
        qmrVar.d(string4);
        qmrVar.h(string5);
        qmrVar.e(Double.valueOf(d));
        qmrVar.f(string6);
    }

    public static void dHE_(final Bundle bundle, final UiCallback<Integer> uiCallback) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qrg.3
                @Override // java.lang.Runnable
                public void run() {
                    qrg.dHE_(bundle, uiCallback);
                }
            });
            return;
        }
        qmr qmrVar = new qmr();
        dHD_(qmrVar, bundle);
        InvoiceApplicationApi invoiceApplicationApi = (InvoiceApplicationApi) lqi.d().b(InvoiceApplicationApi.class);
        InvoiceInfoFactory invoiceInfoFactory = new InvoiceInfoFactory(BaseApplication.getContext());
        String b = lql.b(invoiceInfoFactory.getBody(qmrVar));
        LogUtil.a("InvoiceRequestUtil", "invoice apply url: ", qmrVar.getUrl());
        LogUtil.a("InvoiceRequestUtil", "invoice apply headers:", invoiceInfoFactory.getHeaders());
        LogUtil.a("InvoiceRequestUtil", "invoice apply body: ", b);
        try {
            Response<InvoiceResponse> execute = invoiceApplicationApi.getInvoiceApply(qmrVar.getUrl(), invoiceInfoFactory.getHeaders(), b).execute();
            if (execute.isOK()) {
                LogUtil.a("InvoiceRequestUtil", "requestInvoiceApply response is OK.");
                InvoiceResponse body = execute.getBody();
                if (body == null) {
                    uiCallback.onFailure(1, "requestInvoiceApply result is null");
                    return;
                }
                LogUtil.a("InvoiceRequestUtil", "invoice apply result code: ", Integer.valueOf(body.getResultCode()));
                LogUtil.a("InvoiceRequestUtil", "invoice apply result desc: ", body.getResultDesc());
                if (body.getResultCode() != 0) {
                    ReleaseLogUtil.c("InvoiceRequestUtil", "invoice apply result code: ", Integer.valueOf(body.getResultCode()));
                    ReleaseLogUtil.c("InvoiceRequestUtil", "invoice apply result desc: ", body.getResultDesc());
                    uiCallback.onFailure(1, "requestInvoiceApply result is error");
                    return;
                }
                uiCallback.onSuccess(Integer.valueOf(body.getResultCode()));
                return;
            }
            uiCallback.onFailure(1, "requestInvoiceApply response is not ok, response code: " + execute.getCode());
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            LogUtil.b("InvoiceRequestUtil", "requestInvoiceApply exception");
        }
    }

    public static void a(final String str, final UiCallback<List<InvoiceRequestInfo>> uiCallback) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qrg.1
                @Override // java.lang.Runnable
                public void run() {
                    qrg.a(str, uiCallback);
                }
            });
            return;
        }
        qmt qmtVar = new qmt();
        qmtVar.a(c(str));
        InvoiceApplicationApi invoiceApplicationApi = (InvoiceApplicationApi) lqi.d().b(InvoiceApplicationApi.class);
        InvoiceInfoFactory invoiceInfoFactory = new InvoiceInfoFactory(BaseApplication.getContext());
        LogUtil.a("InvoiceRequestUtil", "query result url: ", qmtVar.getUrl());
        LogUtil.a("InvoiceRequestUtil", "query result headers:", invoiceInfoFactory.getHeaders());
        try {
            Response<RespQueryResult> execute = invoiceApplicationApi.getQueryResult(qmtVar.getUrl(), invoiceInfoFactory.getHeaders()).execute();
            if (execute.isOK()) {
                LogUtil.a("InvoiceRequestUtil", "requestQueryResult response is OK.");
                RespQueryResult body = execute.getBody();
                if (body == null) {
                    uiCallback.onFailure(1, "requestQueryResult result is null");
                    return;
                }
                if (body.getResultCode() != 0) {
                    ReleaseLogUtil.c("InvoiceRequestUtil", "query result result code: ", Integer.valueOf(body.getResultCode()));
                    ReleaseLogUtil.c("InvoiceRequestUtil", "query result result desc: ", body.getResultDesc());
                    uiCallback.onFailure(1, "requestQueryResult result is error");
                    return;
                } else {
                    List<InvoiceRequestInfo> invoiceRequestInfos = body.getInvoiceRequestInfos();
                    ReleaseLogUtil.b("InvoiceRequestUtil", "results: ", body.getInvoiceRequestInfos());
                    if (koq.b(invoiceRequestInfos)) {
                        uiCallback.onFailure(1, "requestQueryResult result is empty");
                        return;
                    } else {
                        uiCallback.onSuccess(invoiceRequestInfos);
                        return;
                    }
                }
            }
            uiCallback.onFailure(1, "requestQueryResult response is not ok, response code: " + execute.getCode());
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            LogUtil.b("InvoiceRequestUtil", "requestQueryResult exception");
        }
    }

    public static void c(final String str, final UiCallback<String> uiCallback) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qrg.2
                @Override // java.lang.Runnable
                public void run() {
                    qrg.c(str, uiCallback);
                }
            });
            return;
        }
        qmv qmvVar = new qmv();
        qmvVar.c(c(str));
        InvoiceApplicationApi invoiceApplicationApi = (InvoiceApplicationApi) lqi.d().b(InvoiceApplicationApi.class);
        InvoiceInfoFactory invoiceInfoFactory = new InvoiceInfoFactory(BaseApplication.getContext());
        LogUtil.a("InvoiceRequestUtil", "download invoice url: ", qmvVar.getUrl());
        LogUtil.a("InvoiceRequestUtil", "download invoice headers:", invoiceInfoFactory.getHeaders());
        try {
            Response<RespDownloadInvoice> execute = invoiceApplicationApi.getDownloadInvoice(qmvVar.getUrl(), invoiceInfoFactory.getHeaders()).execute();
            if (execute.isOK()) {
                LogUtil.a("InvoiceRequestUtil", "requestDownloadInvoice response is OK.");
                RespDownloadInvoice body = execute.getBody();
                if (body == null) {
                    uiCallback.onFailure(1, "requestDownloadInvoice result is null");
                    return;
                }
                if (body.getResultCode() != 0) {
                    ReleaseLogUtil.c("InvoiceRequestUtil", "download invoice result code: ", Integer.valueOf(body.getResultCode()));
                    uiCallback.onFailure(1, "requestDownloadInvoice response is error");
                    return;
                } else {
                    String message = body.getMessage();
                    ReleaseLogUtil.b("InvoiceRequestUtil", "download invoice file type: ", body.getFileType());
                    ReleaseLogUtil.b("InvoiceRequestUtil", "download invoice message: ", message);
                    uiCallback.onSuccess(message);
                    return;
                }
            }
            uiCallback.onFailure(1, "requestDownloadInvoice response is not ok, response code: " + execute.getCode());
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            LogUtil.b("InvoiceRequestUtil", "requestDownloadInvoice exception");
        }
    }

    private static String c(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("InvoiceRequestUtil", "getEncodedOrderCode encode for orderCode = ", str, " failed, ", "using orderCode! exception = ", ExceptionUtils.d(e));
            return str;
        }
    }
}
