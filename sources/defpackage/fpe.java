package defpackage;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.suggestion.ui.fitness.mvp.PreViewStatusContract;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.trade.datatype.ProductSummary;
import com.huawei.trade.datatype.TradeViewInfo;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.Services;
import java.util.Map;

/* loaded from: classes4.dex */
public class fpe implements PreViewStatusContract, View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f12592a;
    private HealthTextView b;
    private HealthButton c;
    private HealthTextView d;
    private Context e;
    private FrameLayout f;
    private LinearLayout g;
    private HealthTextView h;
    private FrameLayout i;
    private HealthTextView j;
    private fmq k;
    private LinearLayout n;

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.PreViewStatusContract
    public void normalStarPlayView() {
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.PreViewStatusContract
    public void payCourseHasVipPrice() {
    }

    public fpe(Context context, FrameLayout frameLayout, FrameLayout frameLayout2, fmq fmqVar) {
        LogUtil.a("Suggestion_PreviewHelper", "PreviewStatusHelper init");
        if (frameLayout == null || frameLayout2 == null || context == null) {
            LogUtil.h("Suggestion_PreviewHelper", "PreviewStatusHelper view is null or context == null");
            return;
        }
        this.i = frameLayout;
        this.f = frameLayout2;
        this.e = context;
        this.k = fmqVar;
        b();
        c();
    }

    private void c() {
        this.j.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.f12592a.setOnClickListener(this);
    }

    public void b() {
        this.c = (HealthButton) this.i.findViewById(R.id.enable_member_preview_end);
        this.n = (LinearLayout) this.i.findViewById(R.id.has_vip_price_layout);
        this.j = (HealthTextView) this.i.findViewById(R.id.pay_course_original);
        this.h = (HealthTextView) this.i.findViewById(R.id.enable_member_vip_end);
        this.g = (LinearLayout) this.f.findViewById(R.id.full_has_vip_price_layout);
        this.d = (HealthTextView) this.f.findViewById(R.id.full_pay_course_original);
        this.b = (HealthTextView) this.f.findViewById(R.id.full_enable_member_vip_end);
        this.f12592a = (HealthButton) this.f.findViewById(R.id.full_enable_member_preview_end);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.PreViewStatusContract
    public void hideOpenView(View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setVisibility(8);
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.PreViewStatusContract
    public void openVipView() {
        if (a()) {
            return;
        }
        this.n.setVisibility(8);
        this.g.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setText(ffy.d(this.e, R.string._2130848773_res_0x7f022c05, new Object[0]));
        this.f12592a.setVisibility(0);
        this.f12592a.setText(ffy.d(this.e, R.string._2130848773_res_0x7f022c05, new Object[0]));
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.PreViewStatusContract
    public void payCourseNotVipPriceView() {
        if (a()) {
            return;
        }
        this.n.setVisibility(8);
        this.g.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setText(ffy.d(this.e, R.string._2130843201_res_0x7f021641, new Object[0]));
        this.f12592a.setVisibility(0);
        this.f12592a.setText(ffy.d(this.e, R.string._2130843201_res_0x7f021641, new Object[0]));
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.PreViewStatusContract
    public void payCourseHasVipPriceNotOpenVip() {
        if (a()) {
            return;
        }
        this.n.setVisibility(0);
        this.g.setVisibility(0);
        this.c.setVisibility(8);
        this.f12592a.setVisibility(8);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.PreViewStatusContract
    public void payCourseHasVipPriceByOpenVip() {
        if (a()) {
            return;
        }
        this.n.setVisibility(8);
        this.g.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setText(ffy.d(this.e, R.string._2130843201_res_0x7f021641, new Object[0]));
        this.f12592a.setVisibility(0);
        this.f12592a.setText(ffy.d(this.e, R.string._2130843201_res_0x7f021641, new Object[0]));
    }

    private boolean a() {
        if (this.n != null && this.g != null && this.c != null && this.f12592a != null) {
            return false;
        }
        LogUtil.h("Suggestion_PreviewHelper", "openVipView view null");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void aCy_(java.lang.String r10, com.huawei.ui.commonui.healthtextview.HealthTextView r11, android.widget.LinearLayout r12) {
        /*
            r9 = this;
            java.lang.String r0 = "Suggestion_PreviewHelper"
            if (r11 == 0) goto La7
            if (r12 != 0) goto L8
            goto La7
        L8:
            fmq r1 = r9.k
            fpy r1 = r1.a()
            boolean r2 = r1.l()
            r3 = 2130848771(0x7f022c03, float:1.7302816E38)
            if (r2 == 0) goto L2b
            android.content.Context r12 = r9.e
            r2 = 2130845705(0x7f022009, float:1.7296598E38)
            java.lang.String r12 = r12.getString(r2)
            android.content.Context r2 = r9.e
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r12}
            java.lang.String r10 = defpackage.ffy.d(r2, r3, r10)
            goto L44
        L2b:
            boolean r2 = r1.o()
            if (r2 == 0) goto L48
            android.content.Context r12 = r9.e
            r2 = 2130843201(0x7f021641, float:1.7291519E38)
            java.lang.String r12 = r12.getString(r2)
            android.content.Context r2 = r9.e
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r12}
            java.lang.String r10 = defpackage.ffy.d(r2, r3, r10)
        L44:
            r8 = r12
            r12 = r10
            r10 = r8
            goto L72
        L48:
            boolean r2 = r1.g()
            if (r2 == 0) goto L5f
            android.content.Context r12 = r9.e
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            r0 = 2130848772(0x7f022c04, float:1.7302818E38)
            java.lang.String r10 = defpackage.ffy.d(r12, r0, r10)
            r11.setText(r10)
            return
        L5f:
            boolean r10 = r1.i()
            if (r10 == 0) goto L6f
            r10 = 1
            android.view.View[] r10 = new android.view.View[r10]
            r11 = 0
            r10[r11] = r12
            r9.hideOpenView(r10)
            return
        L6f:
            java.lang.String r10 = ""
            r12 = r10
        L72:
            boolean r2 = android.text.TextUtils.isEmpty(r12)
            if (r2 == 0) goto L79
            return
        L79:
            java.lang.String r2 = "initPlayingTips auditionCourseData:"
            java.lang.String r4 = "tips:"
            java.lang.String r6 = "targetStr:"
            r3 = r1
            r5 = r12
            r7 = r10
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3, r4, r5, r6, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            int r7 = r12.indexOf(r10)
            r2 = -1
            if (r7 > r2) goto L9e
            java.lang.String r10 = "setPreviewTime index "
            java.lang.Integer r11 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r11}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r10)
            return
        L9e:
            r2 = r9
            r3 = r11
            r4 = r10
            r5 = r12
            r6 = r1
            r2.b(r3, r4, r5, r6, r7)
            return
        La7:
            java.lang.String r10 = "initPlayingTips watchingGoToVipTips == null"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fpe.aCy_(java.lang.String, com.huawei.ui.commonui.healthtextview.HealthTextView, android.widget.LinearLayout):void");
    }

    private void b(HealthTextView healthTextView, String str, String str2, final fpy fpyVar, int i) {
        SpannableString spannableString = new SpannableString(str2);
        spannableString.setSpan(new ClickableSpan() { // from class: fpe.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                LogUtil.a("Suggestion_PreviewHelper", "initPlayingTips click");
                if (!nsn.o()) {
                    fpe.this.k.c(2);
                    if (fpyVar.l()) {
                        fpe.this.d();
                    }
                    if (fpyVar.o()) {
                        fpe.this.d(fpyVar.d());
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(ContextCompat.getColor(fpe.this.e, R.color._2131299320_res_0x7f090bf8));
            }
        }, i, str.length() + i, 33);
        healthTextView.setText(spannableString);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ProductSummary productSummary) {
        if (productSummary == null) {
            LogUtil.h("Suggestion_PreviewHelper", "jumpPay productSummary = null");
            return;
        }
        fpy a2 = this.k.a();
        ggr.a(a2);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productSummary.getProductId());
        productInfo.setProductName(productSummary.getProductName());
        productInfo.setPurchasePolicy(productSummary.getPurchasePolicy());
        TradeViewInfo e = a2.e();
        if (e != null) {
            productInfo.setResourceType(e.getResType());
            Map<String, String> biParams = e.getBiParams();
            if (biParams != null) {
                biParams.put("buyStage", "1");
                productInfo.setBiParams(biParams);
            }
        }
        productInfo.setLinkType(productSummary.getLinkType());
        productInfo.setLinkValue(productSummary.getLinkValue());
        String json = new Gson().toJson(productInfo);
        LogUtil.a("Suggestion_PreviewHelper", "jumpPay productInfoString = ", json, "giveaway:", Integer.valueOf(productSummary.getGiveawaysExist()));
        a(json, productSummary.getGiveawaysExist() == 1, productSummary.getProductId());
    }

    private void a(String str, boolean z, String str2) {
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h("Suggestion_PreviewHelper", "pullPayByResourceId activity = null");
            return;
        }
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h("Suggestion_PreviewHelper", "pullPayByResourceId payApi = null");
        } else if (z) {
            payApi.startTradeSureOrderActivity(this.e, str, str2);
        } else {
            payApi.buyByProductId(activity, 100001, str);
        }
    }

    public void d() {
        Map<String, String> map;
        int i;
        String str;
        fpy a2 = this.k.a();
        TradeViewInfo e = a2.e();
        if (e != null) {
            map = e.getBiParams();
            str = TextUtils.isEmpty(e.getTrigResName()) ? e.getTrigResName() : "";
            i = e.getResType();
        } else {
            map = null;
            i = 1;
            str = "";
        }
        if (gpo.b()) {
            e(map, str, i, a2);
            return;
        }
        H5ProLaunchOption.Builder addPath = new H5ProLaunchOption.Builder().addPath(gpn.c("from=1&trigResMemberPrice=1&trigResType=" + i + "&trigResName=" + str + "&payResourceType=" + (map != null ? map.get("payResourceType") : "")));
        if (map != null) {
            addPath.addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, map.get(WebViewHelp.BI_KEY_PULL_FROM)).addGlobalBiParam("resourceId", map.get("resourceId")).addGlobalBiParam("resourceName", map.get("resourceName")).addGlobalBiParam("pullOrder", map.get("pullOrder"));
        }
        bzs.e().loadH5ProApp(this.e, "com.huawei.health.h5.vip", addPath);
    }

    private void e(Map<String, String> map, String str, int i, fpy fpyVar) {
        String str2;
        if (map != null) {
            str2 = "huaweischeme://healthapp/router/openwith?address=com.huawei.health.h5.vip?h5pro=true&urlType=4&pName=com.huawei.health&path=MemberCenter&currentBenefit=1&benefitList=%5B1%5D&pullfrom=" + map.get(WebViewHelp.BI_KEY_PULL_FROM) + "&resourceId=" + map.get("resourceId") + "&resourceName=" + map.get("resourceName") + "&pullOrder=" + map.get("pullOrder") + "&payResourceType=" + map.get("payResourceType");
        } else {
            str2 = "huaweischeme://healthapp/router/openwith?address=com.huawei.health.h5.vip?h5pro=true&urlType=4&pName=com.huawei.health&path=MemberCenter&currentBenefit=1&benefitList=%5B1%5D";
        }
        AppRouter.zi_(Uri.parse((str2 + "&popUpTypeSelect=true") + "&trigResMemberPrice=1&trigResType=" + i + "&trigResName=" + str + "&vipOpenFrom=" + ((fpyVar == null || !fpyVar.f()) ? "VipPreview" : "VipRatePreview"))).c(this.e);
    }

    private void e() {
        fpy a2 = this.k.a();
        if (a2.l()) {
            d();
        } else if (a2.f()) {
            d(a2.b());
        } else {
            d(a2.d());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        fmq fmqVar = this.k;
        if (fmqVar == null) {
            LogUtil.h("Suggestion_PreviewHelper", "onClick mVipView == null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.c || view == this.f12592a) {
            fmqVar.c(3);
            e();
        } else if (view == this.j || view == this.d) {
            fmqVar.c(3);
            d(this.k.a().d());
        } else if (view == this.h || view == this.b) {
            fmqVar.c(3);
            d();
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
