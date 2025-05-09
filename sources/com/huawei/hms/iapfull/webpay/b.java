package com.huawei.hms.iapfull.webpay;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hms.iapfull.bean.PayRequest;
import com.huawei.hms.iapfull.bean.PayResult;
import com.huawei.hms.iapfull.bean.WebProductPayRequest;
import com.huawei.hms.iapfull.g0;
import com.huawei.hms.iapfull.n1;
import com.huawei.hms.iapfull.network.model.WebPayPayResponse;
import com.huawei.hms.iapfull.network.model.WebPayRequest;
import com.huawei.hms.iapfull.o1;
import com.huawei.hms.iapfull.q1;
import com.huawei.hms.iapfull.y0;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private PayRequest f4777a;
    private WebProductPayRequest b;
    private q1 c;
    private boolean d;
    private n1 e = new a();

    static void a(b bVar) {
        WebPayRequest webPayRequest;
        if (bVar.d) {
            bVar.b.setReturnUrl("https://webpay.cloud.huawei.com");
            bVar.b.setTerminalType("WAP");
            if (bVar.b.isWebPayPmsVer4()) {
                o1.a().b(bVar.a(), bVar.b, bVar.e);
                return;
            } else {
                o1.a().a(bVar.a(), bVar.b, bVar.e);
                return;
            }
        }
        PayRequest payRequest = bVar.f4777a;
        if (payRequest == null) {
            y0.a("WebPayPresenter", "getWebPayRequest: request is null");
            bVar.a().a();
            webPayRequest = null;
        } else {
            WebPayRequest webPayRequest2 = new WebPayRequest(payRequest);
            webPayRequest2.setReturnUrl("https://webpay.cloud.huawei.com");
            webPayRequest2.setTerminalType("WAP");
            webPayRequest = webPayRequest2;
        }
        if (webPayRequest != null) {
            o1.a().a(bVar.a(), webPayRequest, bVar.e);
            return;
        }
        ((WebViewActivity) bVar.c).a(new PayResult(-1, "pay fail, payRequest is null"));
    }

    public void b() {
        if (this.c == null) {
            y0.a("WebPayPresenter", "view is null");
            return;
        }
        if (!this.d ? this.f4777a == null : this.b == null) {
            a().a(a().getString(R.string._2130851270_res_0x7f0235c6));
            g0.a().a(a(), y0.a(this.d ? this.b.getReservedInfor() : this.f4777a.getReservedInfor()), new com.huawei.hms.iapfull.webpay.a(this));
        } else {
            y0.a("WebPayPresenter", "payRequest is null");
            a().finish();
        }
    }

    public LinkedHashMap<String, String> a(PayRequest payRequest) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("packageName", payRequest.getPackageName());
        linkedHashMap.put("appId", payRequest.getApplicationID());
        linkedHashMap.put(HwPayConstant.KEY_PRODUCTNAME, payRequest.getProductName());
        linkedHashMap.put(ParsedFieldTag.PRICE, payRequest.getAmount());
        String country = payRequest.getCountry();
        if (!TextUtils.isEmpty(country)) {
            String requestId = payRequest.getRequestId();
            if (!"CN".equals(country)) {
                requestId = "";
            }
            linkedHashMap.put("requestId", requestId);
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, String> a(WebProductPayRequest webProductPayRequest) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("packageName", webProductPayRequest.getPackageName());
        linkedHashMap.put("appId", webProductPayRequest.getApplicationID());
        linkedHashMap.put(HwPayConstant.KEY_PRODUCT_NO, webProductPayRequest.getProductNo());
        return linkedHashMap;
    }

    class a implements n1 {
        @Override // com.huawei.hms.iapfull.n1
        public void a(WebPayPayResponse webPayPayResponse) {
            LinkedHashMap<String, String> a2;
            b.this.a().a();
            String cashierUrl = webPayPayResponse.getCashierUrl();
            if (TextUtils.isEmpty(cashierUrl)) {
                ((WebViewActivity) b.this.c).a(new PayResult(-1, "webpay fail, url is null"));
                return;
            }
            WebViewActivity webViewActivity = (WebViewActivity) b.this.c;
            webViewActivity.getClass();
            y0.b("WebViewActivity", "loadUrl");
            webViewActivity.c.loadUrl(cashierUrl);
            if (b.this.d) {
                b bVar = b.this;
                a2 = bVar.a(bVar.b);
            } else {
                b bVar2 = b.this;
                a2 = bVar2.a(bVar2.f4777a);
            }
            com.huawei.hms.iapfull.b.a(b.this.a(), "iap_full_web_pay_launch", a2);
        }

        @Override // com.huawei.hms.iapfull.n1
        public void a(int i, String str) {
            b.this.a().a();
            y0.b("WebPayPresenter", "startWebpay failed, errorCode is: " + i);
            PayResult payResult = new PayResult();
            payResult.setReturnCode(i);
            payResult.setErrMsg(str);
            ((WebViewActivity) b.this.c).a(payResult);
        }

        a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebViewActivity a() {
        q1 q1Var = this.c;
        return q1Var instanceof WebViewActivity ? (WebViewActivity) q1Var : new WebViewActivity();
    }

    public b(WebProductPayRequest webProductPayRequest, q1 q1Var) {
        this.d = false;
        this.b = webProductPayRequest;
        this.c = q1Var;
        this.d = true;
    }

    public b(PayRequest payRequest, q1 q1Var) {
        this.d = false;
        this.f4777a = payRequest;
        this.c = q1Var;
        this.d = false;
    }
}
