package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.amap.api.services.district.DistrictSearchQuery;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.vip.datatypes.PromotionPolicyCacheInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.trade.datatype.SubscriptionPromotion;
import com.huawei.trade.datatype.TimeLimitedPromotion;
import com.huawei.ui.commonui.R$anim;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.dpx;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dpx {
    private static boolean e(int i, int i2, int i3) {
        return i2 >= i && i <= i3 && i2 <= i3 && i >= 0 && i2 >= 0;
    }

    public static void d(final Context context, Product product, final ProductInfo productInfo) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null || wa_.isFinishing() || wa_.isDestroyed()) {
            LogUtil.b("MemberCommonUtil", "activity is wrong or destroyed");
            return;
        }
        if (product == null) {
            LogUtil.b("MemberCommonUtil", "showAgreementDialog selected product is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(wa_);
        if (product != null && product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
            builder.e(nsf.h(R$string.IDS_vip_subscribe_agree_text));
        } else {
            builder.e(nsf.h(R$string.IDS_vip_solid_agree_dialog));
        }
        builder.czC_(R$string.IDS_vip_continue_open, new View.OnClickListener() { // from class: dqa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dpx.Zg_(context, wa_, productInfo, view);
            }
        });
        if (CommonUtil.h(LoginInit.getInstance(context).getAccountInfo(1009)) == 7) {
            builder.czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.health.health.utils.vippage.MemberCommonUtil$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    dpx.Zh_(context, view);
                }
            });
        } else {
            builder.czy_(R$string.IDS_settings_button_cancal, R.color._2131299241_res_0x7f090ba9, new View.OnClickListener() { // from class: com.huawei.health.health.utils.vippage.MemberCommonUtil$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    dpx.Zi_(context, view);
                }
            });
        }
        builder.e(true);
        NoTitleCustomAlertDialog e = builder.e();
        e.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.health.health.utils.vippage.MemberCommonUtil$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                dpx.Zj_(context, dialogInterface);
            }
        });
        e.show();
    }

    static /* synthetic */ void Zg_(Context context, Activity activity, ProductInfo productInfo, View view) {
        LogUtil.a("MemberCommonUtil", "agreed confirmed");
        dqj.b(context, 1);
        Zf_(activity, productInfo);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void Zh_(Context context, View view) {
        LogUtil.a("MemberCommonUtil", "agree canceled");
        dqj.b(context, 2);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void Zi_(Context context, View view) {
        LogUtil.a("MemberCommonUtil", "agree canceled");
        dqj.b(context, 2);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void Zj_(Context context, DialogInterface dialogInterface) {
        LogUtil.a("MemberCommonUtil", "dialog canceled");
        dqj.b(context, 3);
    }

    public static boolean a(Product product) {
        TimeLimitedPromotion promotion;
        if (product == null) {
            return false;
        }
        long nowTime = product.getNowTime();
        if (product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
            SubscriptionPromotion subscriptionPromotion = product.getSubscriptionPromotion();
            if (subscriptionPromotion == null) {
                return false;
            }
            if (nowTime >= Long.parseLong(subscriptionPromotion.getStartTime()) && nowTime <= Long.parseLong(subscriptionPromotion.getEndTime())) {
                return product.getMicroPrice() != subscriptionPromotion.getMicroOriginalPrice();
            }
            LogUtil.a("MemberCommonUtil", "expired subscription");
            return false;
        }
        if (product.getPurchasePolicy() != Product.REPEAT_PURCHASE_FLAG || (promotion = product.getPromotion()) == null) {
            return false;
        }
        if (nowTime <= promotion.getPromotionEndTime()) {
            return product.getMicroPrice() != promotion.getMicroOriginPrice();
        }
        LogUtil.a("MemberCommonUtil", "expired promotion");
        return false;
    }

    public static String d(Product product) {
        return CommonUtil.p(String.valueOf(product.getMicroPrice() / 1000000.0f));
    }

    public static String b(long j, String str) {
        return new DecimalFormat(str).format(j / 1000000.0f);
    }

    public static void c(Context context, int i) {
        AppRouter.b("/OperationBundle/MemberTypeSelectActivity").c("MEMBER_TYPE_SELECT_INDEX", i).b(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031).c(context);
    }

    public static void a(Context context) {
        if (!(context instanceof Activity)) {
            LogUtil.a("MemberCommonUtil", "context is not Activity");
        } else {
            ((Activity) context).overridePendingTransition(R$anim.fade_in, R$anim.fade_out);
        }
    }

    public static String e() {
        if (TextUtils.isEmpty(dqj.a()) || TextUtils.isEmpty(dqj.e())) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DistrictSearchQuery.KEYWORDS_PROVINCE, dqj.a());
            jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, dqj.e());
            return jSONObject.toString();
        } catch (JSONException e) {
            LogUtil.b("MemberCommonUtil", "getAreaInoStr exception ", e.getMessage());
            return "";
        }
    }

    public static ProductInfo e(Product product) {
        ProductInfo productInfo = new ProductInfo();
        if (product == null) {
            return productInfo;
        }
        productInfo.setProductId(product.getProductId());
        productInfo.setPurchasePolicy(product.getPurchasePolicy());
        if (product.getSubscriptionPromotion() != null) {
            if (!TextUtils.isEmpty(product.getSubscriptionPromotion().getPromotionId())) {
                productInfo.setPromotionId(product.getSubscriptionPromotion().getPromotionId());
            }
            productInfo.setPromotionPolicyId(product.getSubscriptionPromotion().getPromotionPolicyId());
        }
        productInfo.setLinkType(product.getLinkType());
        productInfo.setLinkValue(product.getLinkValue());
        productInfo.setClientVersion(eaa.c(BaseApplication.e()));
        return productInfo;
    }

    public static void Zf_(Activity activity, ProductInfo productInfo) {
        if (productInfo == null || activity == null) {
            return;
        }
        productInfo.setAreaInfo(e());
        productInfo.setBiParams(dqj.b());
        String a2 = nrv.a(productInfo);
        LogUtil.a("MemberCommonUtil", "jumpOpenPage: ", a2);
        ((PayApi) Services.c("TradeService", PayApi.class)).buyByProductId(activity, 10000, a2);
    }

    public static CharSequence b(boolean z) {
        String b = nsf.b(R$string.IDS_huawei_member_protocol, nsf.h(R$string.IDS_vip_agreement));
        String b2 = nsf.b(R$string.IDS_huawei_member_protocol, nsf.h(R$string.IDS_vip_renewal_agreement));
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.e(), R.color._2131299317_res_0x7f090bf5));
        a aVar = new a(false);
        a aVar2 = new a(true);
        if (z) {
            String b3 = nsf.b(R$string.IDS_huawei_member_and_renew_protocol, b, b2);
            int indexOf = b3.indexOf(b);
            int length = b.length() + indexOf;
            int indexOf2 = b3.indexOf(b2);
            int length2 = b2.length() + indexOf2;
            LogUtil.a("MemberCommonUtil", "getAgreementText: ", Integer.valueOf(indexOf), " ", Integer.valueOf(length), " ", Integer.valueOf(indexOf2), " ", Integer.valueOf(length2));
            SpannableString spannableString = new SpannableString(b3);
            if (e(indexOf, length, b3.length()) && e(indexOf2, length2, b3.length())) {
                spannableString.setSpan(aVar, indexOf, length, 33);
                spannableString.setSpan(aVar2, indexOf2, length2, 33);
                spannableString.setSpan(CharacterStyle.wrap(foregroundColorSpan), indexOf, length, 33);
                spannableString.setSpan(CharacterStyle.wrap(foregroundColorSpan), indexOf2, length2, 33);
            }
            return spannableString;
        }
        String b4 = nsf.b(R$string.IDS_huawei_member_agree_protocol, b);
        int indexOf3 = b4.indexOf(b);
        int length3 = b.length() + indexOf3;
        SpannableString spannableString2 = new SpannableString(b4);
        spannableString2.setSpan(aVar, indexOf3, length3, 33);
        spannableString2.setSpan(CharacterStyle.wrap(foregroundColorSpan), indexOf3, length3, 33);
        return spannableString2;
    }

    public static String c() {
        return GRSManager.a(BaseApplication.e()).getUrl("userAgreementDomain") + a("/terms/scope/huawei/health/member-terms.htm?");
    }

    public static String d() {
        return GRSManager.a(BaseApplication.e()).getUrl("userAgreementDomain") + a("/terms/scope/huawei/health/monservice-terms.htm?");
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MemberCommonUtil", "input url is empty. return empty string.");
            return "";
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010);
        String languageTag = BaseApplication.e().getResources().getConfiguration().locale.toLanguageTag();
        LogUtil.a("MemberCommonUtil", "getUrlWithLangAndCountry countryCode = ", accountInfo, "; language = ", languageTag);
        return str + "code=" + accountInfo + Constants.LANGUAGE + languageTag;
    }

    static class a extends ClickableSpan {
        private boolean c;

        public a(boolean z) {
            this.c = z;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            jdx.b(new Runnable() { // from class: dpx.a.4
                @Override // java.lang.Runnable
                public void run() {
                    String d = a.this.c ? dpx.d() : dpx.c();
                    a aVar = a.this;
                    aVar.a(aVar.c, d);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(final boolean z, final String str) {
            if (!HandlerExecutor.b()) {
                HandlerExecutor.a(new Runnable() { // from class: dpx.a.5
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.a(z, str);
                    }
                });
                return;
            }
            Object[] objArr = new Object[4];
            objArr[0] = "jump ";
            objArr[1] = z ? "renew" : MessageConstant.MEMBER_TYPE;
            objArr[2] = " url: ";
            objArr[3] = str;
            LogUtil.a("MemberCommonUtil", objArr);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (z) {
                dqj.f();
            } else {
                dqj.h();
            }
            ((MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class)).router(str);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
        }
    }

    public static PromotionPolicyCacheInfo b() {
        PromotionPolicyCacheInfo promotionPolicyCacheInfo;
        String e = SharedPreferenceManager.e(Integer.toString(10000), "SHOW_PROMOTION_DIALOG", "");
        LogUtil.a("MemberCommonUtil", "getPromotionPolicyInfoFromSp cacheInfoStr ： ", e);
        try {
            promotionPolicyCacheInfo = (PromotionPolicyCacheInfo) new Gson().fromJson(e, PromotionPolicyCacheInfo.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("MemberCommonUtil", "parse (JsonSyntaxException");
            promotionPolicyCacheInfo = null;
        }
        return promotionPolicyCacheInfo == null ? new PromotionPolicyCacheInfo() : promotionPolicyCacheInfo;
    }

    public static boolean d(boolean z, String str) {
        StringBuilder sb = z ? new StringBuilder("inbox_dialog_show_times_") : new StringBuilder("purchase_dialog_show_times_");
        sb.append(str);
        String sb2 = sb.toString();
        int e = jfa.e(Integer.toString(10000), sb2, 0);
        LogUtil.a("MemberCommonUtil", "member vip show spKey:", sb2, "  times: ", Integer.valueOf(e));
        return e >= 3;
    }

    public static void b(boolean z, String str) {
        StringBuilder sb = z ? new StringBuilder("inbox_dialog_show_times_") : new StringBuilder("purchase_dialog_show_times_");
        sb.append(str);
        String sb2 = sb.toString();
        int e = jfa.e(Integer.toString(10000), sb2, 0) + 1;
        LogUtil.a("MemberCommonUtil", "member vip showed times: ", Integer.valueOf(e));
        jfa.c(Integer.toString(10000), sb2, e);
    }

    public static boolean a() {
        long lastShowTime = b().getLastShowTime();
        if (lastShowTime == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long b = jdl.b(jdl.u(currentTimeMillis), 14);
        long b2 = jdl.b(jdl.s(currentTimeMillis), 14);
        if (currentTimeMillis < b2 || lastShowTime >= b2) {
            return currentTimeMillis < b2 && lastShowTime < b;
        }
        return true;
    }
}
