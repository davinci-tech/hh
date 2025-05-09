package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.tradeservice.pay.IPayEnvCheckCallback;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.iap.Iap;
import com.huawei.hms.iap.IapApiException;
import com.huawei.hms.iap.entity.IsEnvReadyReq;
import com.huawei.hms.iap.entity.IsEnvReadyResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class gla {
    private static String d;

    public static String a(String str, double d2) {
        String e = e(d2);
        if (LanguageUtil.as(BaseApplication.e())) {
            e = njn.b(e);
        }
        return com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130844959_res_0x7f021d1f, b(str), e);
    }

    public static String e(double d2) {
        return d(a(d2, 1, 2));
    }

    private static String d(String str) {
        try {
            return new BigDecimal(str).stripTrailingZeros().toPlainString();
        } catch (NumberFormatException unused) {
            LogUtil.e("TradeServiceCommonUtils", "NumberFormatException");
            return str;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("TradeServiceCommonUtils", "currency is empty");
            return "";
        }
        if ("CNY".equals(str)) {
            return "¥";
        }
        try {
            return Currency.getInstance(str).getSymbol();
        } catch (IllegalArgumentException unused) {
            LogUtil.a("TradeServiceCommonUtils", "Currency exception");
            return "";
        }
    }

    public static void aNL_(String str, String str2, TextView textView) {
        if (TextUtils.isEmpty(str) || textView == null) {
            return;
        }
        Context e = BaseApplication.e();
        SpannableString spannableString = new SpannableString(str);
        int indexOf = spannableString.toString().indexOf(str2);
        if (indexOf != -1) {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(e, R.color.emui_accent)), indexOf, str2.length() + indexOf, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(e.getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6)), indexOf, str2.length() + indexOf, 33);
        }
        textView.setText(spannableString);
    }

    public static void aNK_(String str, String str2, TextView textView, int i) {
        if (TextUtils.isEmpty(str) || textView == null) {
            return;
        }
        Context e = BaseApplication.e();
        SpannableString spannableString = new SpannableString(str);
        int indexOf = spannableString.toString().indexOf(str2);
        if (indexOf != -1) {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(e, i)), indexOf, str2.length() + indexOf, 33);
        }
        textView.setText(spannableString);
    }

    public static double d(double d2, int i) {
        return UnitUtil.a(d2, c(d2, i));
    }

    public static String a(double d2, int i, int i2) {
        return UnitUtil.e(d(d2, i2), i, c(d2, i2));
    }

    private static int c(double d2, int i) {
        if (i > -1) {
            return i;
        }
        double a2 = UnitUtil.a(d2, 2);
        int i2 = 0;
        if (a2 - UnitUtil.a(d2, 0) != 0.0d) {
            i2 = 1;
            if (a2 - UnitUtil.a(d2, 1) != 0.0d) {
                return 2;
            }
        }
        return i2;
    }

    public static int d() {
        return CommonUtil.m(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), eil.a());
    }

    public static String e(String str) {
        LogUtil.c("TradeServiceCommonUtils", "getPayType", str);
        Map<String, String> b = b();
        return (TextUtils.isEmpty(str) || !b.containsKey(str)) ? "" : b.get(str);
    }

    private static Map<String, String> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("4", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130844961_res_0x7f021d21));
        hashMap.put("17", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130844962_res_0x7f021d22));
        hashMap.put("31", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130844963_res_0x7f021d23));
        hashMap.put("3", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130844964_res_0x7f021d24));
        hashMap.put("16", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130844965_res_0x7f021d25));
        hashMap.put(HealthZonePushReceiver.PRESSURE_NOTIFY, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845019_res_0x7f021d5b));
        hashMap.put("44", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845019_res_0x7f021d5b));
        hashMap.put("6", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845976_res_0x7f022118));
        hashMap.put("0", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845977_res_0x7f022119));
        hashMap.put("21", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845978_res_0x7f02211a));
        hashMap.put(HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845980_res_0x7f02211c));
        hashMap.put("19", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845979_res_0x7f02211b));
        hashMap.put("13", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845981_res_0x7f02211d));
        hashMap.put("69", com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845982_res_0x7f02211e));
        return hashMap;
    }

    public static String c() {
        return CommonUtil.cc() ? "10135307" : "10414141";
    }

    public static HashMap<String, String> c(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap<>();
        if (hashMap == null) {
            LogUtil.a("TradeServiceCommonUtils", "encodeRequestParams params = null");
            return hashMap2;
        }
        for (String str : hashMap.keySet()) {
            try {
                hashMap2.put(str, URLEncoder.encode(hashMap.get(str), StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException | IllegalStateException unused) {
                LogUtil.e("TradeServiceCommonUtils", "encodeRequestParams exception");
            }
        }
        return hashMap2;
    }

    public static void e(int i, Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(context, AnalyticsValue.VIP_RETAIN_POP.value(), hashMap, 0);
    }

    public static void aNJ_(Activity activity, boolean z, final IPayEnvCheckCallback iPayEnvCheckCallback) {
        Task<IsEnvReadyResult> isEnvReady;
        if (iPayEnvCheckCallback == null) {
            LogUtil.a("TradeServiceCommonUtils", "checkPayEnv failed, payCallback is null...");
            return;
        }
        if (CommonUtil.bb()) {
            LogUtil.c("TradeServiceCommonUtils", "current version is GP-version, won`t guide to install hms");
            if (z) {
                isEnvReady = Iap.getIapClient(activity.getApplicationContext(), c()).isEnvReady(f());
            } else {
                isEnvReady = Iap.getIapClient(activity.getApplicationContext(), c()).isEnvReady();
            }
        } else if (z) {
            isEnvReady = Iap.getIapClient(activity, c()).isEnvReady(f());
        } else {
            isEnvReady = Iap.getIapClient(activity, c()).isEnvReady();
        }
        if (isEnvReady == null) {
            LogUtil.e("TradeServiceCommonUtils", "checkPayEnv failed, create iap-task failed");
            iPayEnvCheckCallback.onResponse(1003);
        } else {
            isEnvReady.addOnSuccessListener(new OnSuccessListener<IsEnvReadyResult>() { // from class: gla.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(IsEnvReadyResult isEnvReadyResult) {
                    IPayEnvCheckCallback.this.onResponse(0);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: gla.4
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    if (exc instanceof IapApiException) {
                        Status status = ((IapApiException) exc).getStatus();
                        if (status != null) {
                            int statusCode = status.getStatusCode();
                            if (statusCode == 60050) {
                                IPayEnvCheckCallback.this.onResponse(1001);
                            } else if (statusCode == 60054) {
                                IPayEnvCheckCallback.this.onResponse(1002);
                            } else {
                                IPayEnvCheckCallback.this.onResponse(1003);
                            }
                            LogUtil.e("TradeServiceCommonUtils", "IAP-SDK return failed, sdk status code: " + statusCode);
                            return;
                        }
                        IPayEnvCheckCallback.this.onResponse(1003);
                        LogUtil.e("TradeServiceCommonUtils", "IAP-SDK return failed, but sdk-status is null");
                        return;
                    }
                    if (exc instanceof ResolvableApiException) {
                        IPayEnvCheckCallback.this.onResponse(1015);
                        LogUtil.e("TradeServiceCommonUtils", "IAP-SDK return failed, user canceled");
                    } else {
                        IPayEnvCheckCallback.this.onResponse(1003);
                        LogUtil.e("TradeServiceCommonUtils", "IAP-SDK return failed, unknown exception, type: " + exc.getClass().getSimpleName());
                    }
                }
            });
        }
    }

    private static IsEnvReadyReq f() {
        IsEnvReadyReq isEnvReadyReq = new IsEnvReadyReq();
        String e = e();
        if (!TextUtils.isEmpty(e)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("accountInfo", e);
                isEnvReadyReq.setReservedInfor(jSONObject.toString());
            } catch (JSONException e2) {
                LogUtil.e("TradeServiceCommonUtils", "jsonException:", ExceptionUtils.d(e2));
            }
        }
        return isEnvReadyReq;
    }

    public static String e() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("applicationId", c());
            jSONObject.put("routeMode", "fullsdk");
            String accountInfo = LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1014);
            if (TextUtils.isEmpty(accountInfo)) {
                LogUtil.a("TradeServiceCommonUtils", "getAccountInfo countryCode isEmpty");
                return "";
            }
            jSONObject.put("countryCode", accountInfo);
            String accountInfo2 = LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1015);
            if (TextUtils.isEmpty(accountInfo2)) {
                LogUtil.a("TradeServiceCommonUtils", "getAccountInfo accessToken isEmpty");
                return "";
            }
            jSONObject.put("accessToken", accountInfo2);
            String accountInfo3 = LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1011);
            if (TextUtils.isEmpty(accountInfo3)) {
                LogUtil.a("TradeServiceCommonUtils", "getAccountInfo huid isEmpty");
                return "";
            }
            jSONObject.put("clientID", accountInfo3);
            String jSONObject2 = jSONObject.toString();
            LogUtil.d("TradeServiceCommonUtils", "getAccountInfo accountInfo : ", jSONObject2);
            return jSONObject2;
        } catch (JSONException e) {
            ReleaseLogUtil.c("TradeServiceCommonUtils", "getAccountInfo jsonException: ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static void c(String str) {
        d = str;
    }

    public static String a() {
        return d;
    }
}
