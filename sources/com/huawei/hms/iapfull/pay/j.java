package com.huawei.hms.iapfull.pay;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.iapfull.a1;
import com.huawei.hms.iapfull.bean.PayRequest;
import com.huawei.hms.iapfull.bean.PayResult;
import com.huawei.hms.iapfull.e1;
import com.huawei.hms.iapfull.g0;
import com.huawei.hms.iapfull.network.model.AliPaySignResponse;
import com.huawei.hms.iapfull.network.model.DeveloperSignRequest;
import com.huawei.hms.iapfull.network.model.PaySignRequest;
import com.huawei.hms.iapfull.network.model.QueryOrderRequest;
import com.huawei.hms.iapfull.network.model.QueryOrderResponse;
import com.huawei.hms.iapfull.network.model.QueryWithholdResultRequest;
import com.huawei.hms.iapfull.network.model.QueryWithholdResultResponse;
import com.huawei.hms.iapfull.network.model.ReportEntity;
import com.huawei.hms.iapfull.network.model.ReportPayResultParams;
import com.huawei.hms.iapfull.network.model.WithholdQueryResultData;
import com.huawei.hms.iapfull.p0;
import com.huawei.hms.iapfull.pay.m;
import com.huawei.hms.iapfull.r0;
import com.huawei.hms.iapfull.s0;
import com.huawei.hms.iapfull.t0;
import com.huawei.hms.iapfull.u0;
import com.huawei.hms.iapfull.v0;
import com.huawei.hms.iapfull.w0;
import com.huawei.hms.iapfull.x0;
import com.huawei.hms.iapfull.y0;
import com.huawei.hms.iapfull.z0;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.secure.android.common.util.SafeString;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.open.utils.HttpUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
class j implements m.a {

    /* renamed from: a, reason: collision with root package name */
    private String f4753a;
    private PayRequest b;
    private m c;
    private p0 d;
    private String e;
    private String f;
    private String g;
    private ReportEntity h;

    static void a(j jVar) {
        DeveloperSignRequest developerSignRequest;
        String str;
        String str2;
        PayRequest payRequest = jVar.b;
        if (payRequest == null) {
            y0.a("PayPresenter", "getDevSignParams: request is null");
            jVar.a().a();
            developerSignRequest = null;
        } else {
            DeveloperSignRequest developerSignRequest2 = new DeveloperSignRequest(payRequest);
            developerSignRequest2.setDevelopUserSign(payRequest.getSign());
            developerSignRequest2.setPackageName(payRequest.getPackageName());
            developerSignRequest2.setExtUserName(payRequest.getMerchantName());
            developerSignRequest2.setVer("1");
            ArrayList arrayList = new ArrayList();
            arrayList.add("FP_PayTypePolices");
            arrayList.add("liteSdkWXNotSupportMerchants");
            developerSignRequest2.setDics(arrayList);
            String a2 = a1.a();
            developerSignRequest2.setNoiseTamp(a2);
            y0.b("PayPresenter", "requestDeveloperSign, noiseTamp: " + a2);
            developerSignRequest = developerSignRequest2;
        }
        PayActivity a3 = jVar.a();
        i iVar = new i(jVar);
        if (TextUtils.isEmpty(g0.a().f4711a)) {
            y0.a("PayRepository", "startReportPayResult: Grs is fail");
            return;
        }
        if (developerSignRequest == null || a3 == null) {
            str = ResultCode.CODE_NO_NEW_DATA;
            str2 = "param error";
        } else if (a1.b(a3)) {
            com.huawei.hms.iapfull.network.b.a(a3.getApplicationContext(), g0.a().f4711a).developerSign(developerSignRequest).enqueue(new com.huawei.hms.iapfull.network.a(new com.huawei.hms.iapfull.d(iVar)));
            return;
        } else {
            str = "-1";
            str2 = HttpUtils.NetworkUnavailableException.ERROR_INFO;
        }
        iVar.a(str, str2);
    }

    static void b(j jVar, AliPaySignResponse aliPaySignResponse) {
        if (jVar.f()) {
            jVar.c.a(aliPaySignResponse);
            return;
        }
        aliPaySignResponse.parseJson();
        m mVar = jVar.c;
        String aliPayOrderInfo = aliPaySignResponse.getAliPayOrderInfo();
        mVar.getClass();
        if (TextUtils.isEmpty(aliPayOrderInfo)) {
            y0.b("ThirdPayHelper", "callAliPaySDK,but strOrderInfo is empty");
        } else {
            new Thread(new l(mVar, aliPayOrderInfo)).start();
        }
    }

    private ReportPayResultParams c() throws JSONException {
        String str;
        SimpleDateFormat simpleDateFormat;
        StringBuilder sb;
        String str2;
        if (this.b == null) {
            str2 = "getReportPayResultParams payRequest is null";
        } else {
            if (this.h != null) {
                ReportPayResultParams reportPayResultParams = new ReportPayResultParams();
                reportPayResultParams.setMerchantID(this.b.getMerchantId());
                reportPayResultParams.setRequestId(this.b.getRequestId());
                reportPayResultParams.setApplicationID(this.b.getApplicationID());
                reportPayResultParams.setOrderNo(this.h.getOrderNo());
                reportPayResultParams.setPkgName(this.b.getPackageName());
                reportPayResultParams.setData(d());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("merchantID", this.b.getMerchantId());
                jSONObject.put("requestId", this.b.getRequestId());
                jSONObject.put(HwPayConstant.KEY_APPLICATIONID, this.b.getApplicationID());
                jSONObject.put("orderNo", this.h.getOrderNo());
                jSONObject.put(SdkCfgSha256Record.PKGNAME, this.b.getPackageName());
                jSONObject.put("at", this.f4753a);
                jSONObject.put("scene", "fullSDK");
                jSONObject.put("data", d());
                String a2 = a1.a(jSONObject);
                String str3 = this.e;
                reportPayResultParams.setSign(a1.a(str3, a2, a1.a(str3, HwPayConstant.SIGNTYPE_RSA256PSS)));
                reportPayResultParams.setSignType(a1.a(this.e, HwPayConstant.SIGNTYPE_RSA256PSS));
                reportPayResultParams.setScene("fullSDK");
                reportPayResultParams.setUserName(this.b.getMerchantName());
                reportPayResultParams.setAt(this.f4753a);
                reportPayResultParams.setOrderID(this.h.getPayOrderId());
                reportPayResultParams.setAmount(this.h.getAmount());
                if (!TextUtils.isEmpty(this.h.getReturnCode())) {
                    reportPayResultParams.setReturnCode(this.h.getReturnCode());
                }
                if (!TextUtils.isEmpty(this.h.getTradeTime())) {
                    String tradeTime = this.h.getTradeTime();
                    try {
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                    } catch (ParseException unused) {
                        str = System.currentTimeMillis() + "";
                    }
                    if (TextUtils.isEmpty(tradeTime)) {
                        sb = new StringBuilder();
                    } else {
                        Date parse = simpleDateFormat.parse(tradeTime);
                        if (parse == null) {
                            sb = new StringBuilder();
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(parse.getTime());
                            sb = sb2;
                            sb.append("");
                            str = sb.toString();
                            reportPayResultParams.setTradeTime(str);
                        }
                    }
                    sb.append(System.currentTimeMillis());
                    sb.append("");
                    str = sb.toString();
                    reportPayResultParams.setTradeTime(str);
                }
                if (!TextUtils.isEmpty(this.b.getCurrency())) {
                    reportPayResultParams.setCurrency(this.b.getCurrency());
                }
                if (!TextUtils.isEmpty(this.b.getCountry())) {
                    reportPayResultParams.setCountry(this.b.getCountry());
                }
                return reportPayResultParams;
            }
            str2 = "getReportPayResultParams reportEntity is null";
        }
        y0.a("PayPresenter", str2);
        return null;
    }

    private void e() {
        if (this.d == null) {
            y0.a("PayPresenter", "view is null");
            return;
        }
        if (this.b == null) {
            y0.a("PayPresenter", "payRequest is null");
            a().finish();
            return;
        }
        m mVar = new m(a(), this.b.getAmount());
        this.c = mVar;
        mVar.a(this);
        a().a(a().getString(R.string._2130851270_res_0x7f0235c6));
        g0.a().a(a(), y0.a(this.b.getReservedInfor()), new h(this));
    }

    protected void c(int i) {
        if (i == 17) {
            try {
                if (!a(a(), "com.tencent.mm")) {
                    y0.b("PayPresenter", "try to pay or subscribe but not install wechat");
                    a1.a(a(), R.string._2130851294_res_0x7f0235de, 0);
                    y0.b("PayPresenter", "not install wechat");
                    return;
                }
            } catch (JSONException unused) {
                y0.b("PayPresenter", "params error");
                return;
            }
        }
        y0.b("PayPresenter", "wechat installed");
        if (f() && i == 5 && !a(a(), "com.eg.android.AlipayGphone")) {
            y0.b("PayPresenter", "try to use alipay withhold but not install alipay");
            a1.a(a(), R.string._2130851254_res_0x7f0235b6, 0);
            return;
        }
        a().a(a().getString(R.string._2130851269_res_0x7f0235c5));
        PaySignRequest a2 = a(i);
        if (a2 == null) {
            y0.b("PayPresenter", "params is null");
        } else {
            y0.b("PayPresenter", "params is in requestPaySign");
            t0.a().a(a(), a2, new a(i));
        }
    }

    static void a(j jVar, AliPaySignResponse aliPaySignResponse) {
        jVar.getClass();
        y0.b("PayPresenter", "callWetchatPayChannel start");
        if (jVar.f()) {
            aliPaySignResponse.parseWeChatJson();
            m mVar = jVar.c;
            PayActivity a2 = jVar.a();
            mVar.getClass();
            if (a2 != null) {
                String wechatPaySignAppid = aliPaySignResponse.getWeChatPayInfo().getWechatPaySignAppid();
                String preEntrustWebID = aliPaySignResponse.getWeChatPayInfo().getPreEntrustWebID();
                if (TextUtils.isEmpty(preEntrustWebID) || TextUtils.isEmpty(wechatPaySignAppid)) {
                    y0.b("ThirdPayHelper", "callWechatWithhold pre_entrustweb_id or appid is null");
                } else if (m.a(a2, wechatPaySignAppid, preEntrustWebID)) {
                    y0.b("PayPresenter", "callWechatWithhold success");
                    return;
                }
            } else {
                y0.a("ThirdPayHelper", "callWechatWithhold, but paramters are empty");
            }
            y0.a("PayPresenter", "callWechatWithhold fail");
            ((PayActivity) jVar.d).a(new PayResult(30001, "params is error"));
            return;
        }
        aliPaySignResponse.parseWeChatJson();
        m mVar2 = jVar.c;
        PayActivity a3 = jVar.a();
        mVar2.getClass();
        if (a3 != null) {
            String wechatPaySignAppid2 = aliPaySignResponse.getWeChatPayInfo().getWechatPaySignAppid();
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(a3, wechatPaySignAppid2);
            createWXAPI.registerApp(wechatPaySignAppid2);
            PayReq payReq = new PayReq();
            payReq.appId = wechatPaySignAppid2;
            payReq.partnerId = aliPaySignResponse.getPartnerId();
            payReq.prepayId = aliPaySignResponse.getWeChatPayInfo().getPrepayId();
            payReq.packageValue = aliPaySignResponse.getWeChatPayInfo().getPackageValue();
            payReq.nonceStr = aliPaySignResponse.getWeChatPayInfo().getNonceStr();
            payReq.timeStamp = aliPaySignResponse.getWeChatPayInfo().getTimeStamp();
            payReq.sign = aliPaySignResponse.getWeChatPayInfo().getWeChatSign();
            y0.b("ThirdPayHelper", "callWeChatPay, req construction is complete");
            if (createWXAPI.sendReq(payReq)) {
                y0.b("PayPresenter", "callWeChatPay success");
                return;
            }
        } else {
            y0.a("ThirdPayHelper", "callWeChatPay, but paramters are empty");
        }
        y0.a("PayPresenter", "callWeChatPay fail");
        ((PayActivity) jVar.d).a(new PayResult(30001, "params is error"));
    }

    static void a(j jVar, QueryOrderResponse queryOrderResponse) {
        jVar.getClass();
        if (1 != queryOrderResponse.getStatus()) {
            jVar.a().a();
            y0.b("PayPresenter", "queryOrder failed, status code is :" + queryOrderResponse.getStatus());
            e1.a().a(jVar.a(), queryOrderResponse.getStatus());
            return;
        }
        y0.b("PayPresenter", "pay success!");
        x0 x0Var = new x0();
        x0Var.d(jVar.b.getProductName());
        x0Var.e(jVar.b.getAmount());
        x0Var.c(queryOrderResponse.getPayType());
        queryOrderResponse.getPayType().equals(String.valueOf(5));
        if (jVar.h == null) {
            jVar.h = new ReportEntity();
        }
        jVar.h.setReturnCode(queryOrderResponse.getReturnCode());
        jVar.h.setReturnDesc(queryOrderResponse.getReturnDesc());
        jVar.h.setStatus(String.valueOf(queryOrderResponse.getStatus()));
        jVar.h.setErrMsg(queryOrderResponse.getReturnDesc());
        jVar.h.setAmount(jVar.b.getAmount());
        jVar.h.setOrderNo(queryOrderResponse.getOrderId());
        jVar.h.setTradeNo(queryOrderResponse.getTradeNo());
        jVar.h.setTradeTime(queryOrderResponse.getTradeTime());
        jVar.h.setOrderTime(queryOrderResponse.getOrderTime());
        x0Var.f(jVar.h.getTradeTime());
        x0Var.b(jVar.h.getOrderNo());
        x0Var.a(jVar.h.getErrMsg());
        ((PayActivity) jVar.d).b(x0Var);
    }

    static boolean a(j jVar, QueryWithholdResultResponse queryWithholdResultResponse) {
        jVar.getClass();
        if (queryWithholdResultResponse.getData() != null && !queryWithholdResultResponse.getData().isEmpty()) {
            return true;
        }
        y0.b("PayPresenter", "isWithholdSuccess fail response.getData() is null");
        return false;
    }

    static void a(j jVar, QueryWithholdResultResponse queryWithholdResultResponse, String str) {
        jVar.getClass();
        x0 x0Var = new x0();
        List<WithholdQueryResultData> data = queryWithholdResultResponse.getData();
        if (data != null && data.size() > 0) {
            x0Var.b(data.get(0).getPactNo());
            x0Var.f(data.get(0).getPactTime());
            x0Var.c(data.get(0).getChannel());
        }
        x0Var.e(jVar.b.getAmount());
        x0Var.d(jVar.b.getProductName());
        x0Var.b(jVar.f);
        if (!TextUtils.isEmpty(str)) {
            x0Var.g(str);
        }
        if (jVar.h == null) {
            jVar.h = new ReportEntity();
        }
        List<WithholdQueryResultData> data2 = queryWithholdResultResponse.getData();
        if (data2 != null && data2.size() > 0) {
            jVar.h.setWithHoldId(data2.get(0).getPactNo());
            jVar.h.setTradeTime(data2.get(0).getPactTime());
            jVar.h.setPayType(data2.get(0).getChannel());
        }
        jVar.h.setReturnCode(queryWithholdResultResponse.getReturnCode());
        jVar.h.setReturnDesc(queryWithholdResultResponse.getReturnDesc());
        jVar.h.setStatus("1");
        jVar.h.setAmount(jVar.b.getAmount());
        jVar.h.setErrMsg(queryWithholdResultResponse.getReturnDesc());
        jVar.h.setOrderNo(jVar.f);
        x0Var.a(jVar.h.getErrMsg());
        ((PayActivity) jVar.d).c(x0Var);
    }

    public void a(String str, String str2, String str3, String str4) {
        ReportEntity reportEntity = new ReportEntity();
        this.h = reportEntity;
        reportEntity.setReturnCode("0");
        this.h.setReturnDesc(str);
        this.h.setStatus("1");
        this.h.setErrMsg(str);
        this.h.setAmount(this.b.getAmount());
        this.h.setOrderNo(str4);
        this.h.setTradeNo(null);
        this.h.setTradeTime();
        ReportEntity reportEntity2 = this.h;
        reportEntity2.setOrderTime(reportEntity2.getTradeTime());
        this.h.setYeeOrAliPaySign(str3);
        this.h.setYeeOrAliPaySignContent(str2);
        b(5);
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

    private PaySignRequest a(int i) throws JSONException {
        PayRequest payRequest = this.b;
        if (payRequest == null) {
            y0.a("PayPresenter", "getDevSignParams: payRequest is null");
            return null;
        }
        PaySignRequest paySignRequest = new PaySignRequest(payRequest);
        paySignRequest.checkCountryAndCurrency();
        paySignRequest.setPackageName(this.b.getPackageName());
        paySignRequest.setMerName(this.b.getMerchantName());
        int i2 = z0.f4791a;
        paySignRequest.setManufacturer(Build.MANUFACTURER);
        paySignRequest.setDeviceBrand(Build.BRAND);
        paySignRequest.setAccessMode("0");
        if (!TextUtils.isEmpty(this.b.getMerchantId())) {
            paySignRequest.setUserID(this.b.getMerchantId());
        } else if (!TextUtils.isEmpty(this.b.getMerchantName())) {
            paySignRequest.setUserName(this.b.getMerchantName());
        }
        if (i == 5) {
            paySignRequest.setChannel("AliPay");
            if (TextUtils.isEmpty(paySignRequest.getTradeType())) {
                paySignRequest.setTradeType("App4");
            }
        } else if (i == 17) {
            paySignRequest.setChannel("TenPay");
            if (TextUtils.isEmpty(paySignRequest.getReservedInfor())) {
                paySignRequest.setReservedInfor("17");
            }
            if (f()) {
                paySignRequest.setTradeType("toSign4app");
            } else {
                paySignRequest.setTradeType(null);
            }
            paySignRequest.setWalletAppId(this.b.getPackageName());
        }
        String str = System.currentTimeMillis() + "";
        paySignRequest.setTime(str);
        paySignRequest.setSerialNo(SafeString.substring(UUID.randomUUID().toString().replace(Constants.LINK, ""), 0, 16) + str + "_" + this.b.getRequestId());
        String a2 = a1.a();
        paySignRequest.setNoiseTamp(a2);
        StringBuilder sb = new StringBuilder("paySign , noiseTamp is: ");
        sb.append(a2);
        y0.b("PayPresenter", sb.toString());
        String reservedInfor = this.b.getReservedInfor();
        if (TextUtils.isEmpty(reservedInfor)) {
            y0.b("PayPresenter", "reservedInfor is null!");
        } else {
            try {
                JSONObject jSONObject = new JSONObject(new JSONObject(reservedInfor).optString("accountInfo"));
                String optString = jSONObject.optString("accessToken");
                this.f4753a = optString;
                paySignRequest.setAt(optString);
                paySignRequest.setCountry(jSONObject.optString("countryCode"));
            } catch (JSONException unused) {
                y0.a("PayPresenter", "setAccountInfo: JSONException");
            }
        }
        if (f()) {
            paySignRequest.setWeburl("hwid://" + this.b.getPackageName() + ".iapfull/result");
        }
        paySignRequest.setScene("fullSDK");
        String a3 = a1.a(new JSONObject(new Gson().toJson(paySignRequest)));
        String a4 = a1.a(this.e, HwPayConstant.SIGNTYPE_RSA256PSS);
        paySignRequest.setSign(a1.a(this.e, a3, a4));
        paySignRequest.setSignType(a4);
        return paySignRequest;
    }

    public boolean a(Context context, String str) {
        String str2;
        PackageManager packageManager;
        String str3;
        if (context == null) {
            str2 = "getVersion context is null.";
        } else {
            if (!TextUtils.isEmpty(str)) {
                try {
                    packageManager = context.getPackageManager();
                } catch (PackageManager.NameNotFoundException unused) {
                    str2 = "getVersion, get the app version fail, NameNotFoundException";
                } catch (RuntimeException unused2) {
                    str2 = "getVersion, get the app version fail, RuntimeException";
                }
                if (packageManager != null) {
                    if (TextUtils.isEmpty(str)) {
                        str = context.getPackageName();
                    }
                    PackageInfo packageInfo = packageManager.getPackageInfo(str, 16384);
                    if (packageInfo != null) {
                        str3 = packageInfo.versionName;
                        return !TextUtils.isEmpty(str3);
                    }
                }
                str3 = null;
                return !TextUtils.isEmpty(str3);
            }
            str2 = "getVersion packageName is null.";
        }
        y0.a("PayPresenter", str2);
        str3 = null;
        return !TextUtils.isEmpty(str3);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            y0.b("PayPresenter", "processWithholdResult result is null");
            return;
        }
        Map<String, String> a2 = a1.a(str, "&", false);
        String a3 = a1.a(a2);
        HashMap hashMap = (HashMap) a2;
        String str2 = (String) hashMap.get(HwPayConstant.KEY_SIGN);
        String str3 = (String) hashMap.get("is_success");
        String str4 = (String) hashMap.get("external_sign_no");
        if (ExifInterface.GPS_DIRECTION_TRUE.equals(str3)) {
            ReportEntity reportEntity = new ReportEntity();
            this.h = reportEntity;
            reportEntity.setReturnCode("0");
            this.h.setReturnDesc("success");
            this.h.setStatus("1");
            this.h.setErrMsg("success");
            this.h.setOrderNo(this.f);
            this.h.setYeeOrAliPaySign(str2);
            this.h.setYeeOrAliPaySignContent(a3);
            this.h.setWithHoldId(str4);
            a(str4, 5);
        }
    }

    protected void a(String str, int i) {
        Submit<QueryWithholdResultResponse> queryWithhold;
        com.huawei.hms.iapfull.network.a aVar;
        String str2;
        String str3;
        a().a(a().getString(R.string._2130851270_res_0x7f0235c6));
        String str4 = TextUtils.isEmpty(str) ? this.g : str;
        QueryWithholdResultRequest queryWithholdResultRequest = new QueryWithholdResultRequest();
        queryWithholdResultRequest.setRequestId(this.b.getRequestId());
        queryWithholdResultRequest.setPactNo(str4);
        queryWithholdResultRequest.setSourceID("fullSDK");
        queryWithholdResultRequest.setApplicationID(this.b.getApplicationID());
        queryWithholdResultRequest.setAt(this.f4753a);
        queryWithholdResultRequest.setSrvCountry(this.b.getCountry());
        String a2 = a1.a();
        queryWithholdResultRequest.setNoiseTamp(a2);
        y0.b("PayPresenter", "queryWithholdResult , noiseTamp is: " + a2);
        PayActivity a3 = a();
        c cVar = new c(str);
        if (a3 == null) {
            str2 = ResultCode.CODE_NO_NEW_DATA;
            str3 = "param error";
        } else {
            if (a1.b(a3)) {
                com.huawei.hms.iapfull.j jVar = new com.huawei.hms.iapfull.j(cVar);
                if (5 == i) {
                    y0.b("QueryRepository", "Query order poll start");
                    queryWithhold = com.huawei.hms.iapfull.network.d.b(a3.getApplicationContext(), g0.a().f4711a).pollQueryWithhold(queryWithholdResultRequest);
                    aVar = new com.huawei.hms.iapfull.network.a(jVar);
                } else {
                    queryWithhold = com.huawei.hms.iapfull.network.d.d(a3.getApplicationContext(), g0.a().f4711a).queryWithhold(queryWithholdResultRequest);
                    aVar = new com.huawei.hms.iapfull.network.a(jVar);
                }
                queryWithhold.enqueue(aVar);
                return;
            }
            str2 = "-1";
            str3 = HttpUtils.NetworkUnavailableException.ERROR_INFO;
        }
        cVar.a(str2, str3);
    }

    public void g() {
        y0.b("PayPresenter", "start query result");
        if (f()) {
            y0.a("PayPresenter", "activityFocusChanged withhold back");
            if (TextUtils.isEmpty(this.g)) {
                return;
            }
            a(this.g, 17);
            return;
        }
        y0.a("PayPresenter", "activityFocusChanged pay back");
        if (TextUtils.isEmpty(this.f)) {
            return;
        }
        b(17);
    }

    protected void b(int i) {
        try {
            a().a(a().getString(R.string._2130851270_res_0x7f0235c6));
            QueryOrderRequest b2 = b();
            y0.b("PayPresenter", "queryOrder, noiseTamp is: " + b2.getNoiseTamp());
            w0.a().a(a(), i, b2, new b());
        } catch (JSONException unused) {
            y0.a("PayPresenter", "queryOrder, param error");
        }
    }

    public void a(s0 s0Var) {
        try {
            ReportPayResultParams c2 = c();
            if (c2 != null) {
                t0.a().a(a(), c2, s0Var);
            } else {
                y0.b("PayPresenter", "params is null");
                s0Var.a(ResultCode.CODE_NO_NEW_DATA, "param error");
            }
        } catch (Exception unused) {
            y0.a("PayPresenter", "report fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        PayRequest payRequest = this.b;
        if (payRequest != null) {
            return "toSign".equals(payRequest.getTradeType());
        }
        return false;
    }

    private String d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(HwPayConstant.KEY_USER_NAME, this.b.getMerchantName());
        jSONObject.put(HwPayConstant.KEY_AMOUNT, this.h.getAmount());
        if (!TextUtils.isEmpty(this.h.getPayOrderId())) {
            jSONObject.put("orderID", this.h.getPayOrderId());
        }
        if (!TextUtils.isEmpty(this.h.getErrMsg())) {
            jSONObject.put("errMsg", this.h.getErrMsg());
        }
        if (!TextUtils.isEmpty(this.h.getTradeTime())) {
            jSONObject.put("time", this.h.getTradeTime());
        }
        if (!TextUtils.isEmpty(this.h.getReturnCode())) {
            jSONObject.put("returnCode", this.h.getReturnCode());
        }
        if (!TextUtils.isEmpty(this.h.getWithHoldId())) {
            jSONObject.put("withholdID", this.h.getWithHoldId());
        }
        jSONObject.put("requestId", this.b.getRequestId());
        jSONObject.put(HwPayConstant.KEY_SIGN_TYPE, this.b.getUsedSignType());
        return jSONObject.toString();
    }

    class a implements r0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f4754a;

        @Override // com.huawei.hms.iapfull.r0
        public void a(String str, String str2) {
            j.this.a().a();
            y0.b("PayPresenter", "requestPaySign failed, errorCode is: " + str + " desc is: " + str2);
            if (j.this.d == null) {
                y0.b("PayPresenter", "view is null");
                return;
            }
            ((PayActivity) j.this.d).a(new PayResult(-1, "pay fail"));
        }

        @Override // com.huawei.hms.iapfull.r0
        public void a(AliPaySignResponse aliPaySignResponse) {
            j.this.a().a();
            if (aliPaySignResponse == null) {
                new AliPaySignResponse().setReturnCode("-1");
                return;
            }
            y0.b("PayPresenter", "errorCode: " + aliPaySignResponse.getReturnCode());
            if (!"0".equals(aliPaySignResponse.getReturnCode())) {
                ((PayActivity) j.this.d).a(aliPaySignResponse.getReturnDesc(), aliPaySignResponse.getReturnCode());
                return;
            }
            y0.b("PayPresenter", "paySign success!");
            j.this.f = aliPaySignResponse.getOrderID();
            int i = this.f4754a;
            if (i == 5) {
                j.this.g = aliPaySignResponse.getPactNo();
                j.b(j.this, aliPaySignResponse);
            } else if (i == 17) {
                j.this.g = aliPaySignResponse.getPactNo();
                j.a(j.this, aliPaySignResponse);
            }
            j jVar = j.this;
            com.huawei.hms.iapfull.b.a(j.this.a(), "iap_payment_cashier_click_confirm", jVar.a(jVar.b));
        }

        a(int i) {
            this.f4754a = i;
        }
    }

    class b implements u0 {
        @Override // com.huawei.hms.iapfull.u0
        public void a(String str, String str2) {
            j.this.a().a();
            y0.b("queryPayResult", "errorCode = " + str + ", desc = " + str2);
            if ("30005".equals(str)) {
                return;
            }
            e1.a().a(j.this.a(), R.string._2130851285_res_0x7f0235d5);
        }

        @Override // com.huawei.hms.iapfull.u0
        public void a(QueryOrderResponse queryOrderResponse) {
            y0.b("queryPayResult", "returnCode = " + queryOrderResponse.getReturnCode() + ", status = " + queryOrderResponse.getStatus());
            if ("0".equals(queryOrderResponse.getReturnCode()) && (queryOrderResponse.getStatus() == 0 || 1 == queryOrderResponse.getStatus())) {
                j.a(j.this, queryOrderResponse);
                return;
            }
            j.this.a().a();
            if (!TextUtils.isEmpty(queryOrderResponse.getErrMsg())) {
                e1.a().a(j.this.a(), queryOrderResponse.getErrMsg());
            }
            y0.b("queryPayResult", "status is fail");
        }

        b() {
        }
    }

    class c implements v0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f4756a;

        @Override // com.huawei.hms.iapfull.v0
        public void a(String str, String str2) {
            j.this.a().a();
            y0.a("PayPresenter", "queryWithholdResult fail, errorCode:" + str + ", desc: " + str2);
            e1.a().a(j.this.a(), R.string._2130851285_res_0x7f0235d5);
        }

        @Override // com.huawei.hms.iapfull.v0
        public void a(QueryWithholdResultResponse queryWithholdResultResponse) {
            if (queryWithholdResultResponse != null) {
                if ("0".equals(queryWithholdResultResponse.getReturnCode()) && j.a(j.this, queryWithholdResultResponse)) {
                    y0.b("PayPresenter", "queryWithholdResult success!");
                    j.a(j.this, queryWithholdResultResponse, this.f4756a);
                    return;
                } else if (!"0".equals(queryWithholdResultResponse.getReturnCode())) {
                    e1.a().a(j.this.a(), queryWithholdResultResponse.getReturnDesc());
                }
            }
            j.this.a().a();
            y0.a("PayPresenter", "queryWithholdResult fail! data is null");
        }

        c(String str) {
            this.f4756a = str;
        }
    }

    private QueryOrderRequest b() throws JSONException {
        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setUserID(this.b.getMerchantId());
        queryOrderRequest.setRequestId(this.b.getRequestId());
        queryOrderRequest.setTime(System.currentTimeMillis());
        queryOrderRequest.setOrderNo(this.f);
        queryOrderRequest.setNoiseTamp(a1.a());
        String a2 = a1.a(new JSONObject(new Gson().toJson(queryOrderRequest)));
        String a3 = a1.a(this.e, HwPayConstant.SIGNTYPE_RSA256PSS);
        queryOrderRequest.setSign(a1.a(this.e, a2, a3));
        queryOrderRequest.setSignType(a3);
        return queryOrderRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PayActivity a() {
        p0 p0Var = this.d;
        return p0Var instanceof PayActivity ? (PayActivity) p0Var : new PayActivity();
    }

    public j(PayRequest payRequest, p0 p0Var) {
        this.b = payRequest;
        this.d = p0Var;
        e();
    }
}
