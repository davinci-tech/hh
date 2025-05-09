package com.huawei.health.marketing.views.audition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.views.audition.AuditionActivity;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.trade.TradeViewApi;
import com.huawei.trade.datatype.TradeViewInfo;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import com.huawei.ui.commonui.flowlayout.textviewbuilder.SearchTagTextViewBuilder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import defpackage.ele;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nmk;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.Services;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class AuditionActivity extends Activity implements View.OnClickListener, AuditionPlayerListener, HealthSeekBar.OnSeekBarChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2899a;
    private HealthTextView b;
    private ImageView c;
    private ImageView d;
    private HealthTextView e;
    private String f;
    private HealthTextView g;
    private Map<String, String> h;
    private HealthBottomSheet i;
    private HealthTextView j;
    private HealthSeekBar l;
    private HealthFlowLayout m;
    private Context n;
    private View p;
    private FrameLayout q;
    private SingleGridContent r;
    private boolean o = false;
    private d k = new d(this);

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
    }

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
    }

    public static class d extends BaseHandler<AuditionActivity> {
        d(AuditionActivity auditionActivity) {
            super(auditionActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aqq_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AuditionActivity auditionActivity, Message message) {
            if (message.what == 1) {
                int e = ele.c().e();
                auditionActivity.l.setProgress(e);
                auditionActivity.e.setText(auditionActivity.a(e));
                sendEmptyMessageDelayed(1, 1000L);
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.audio_preview_layout);
        this.n = this;
        j();
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("AuditionActivity", "intent is null, finish AuditionLayout page");
            finish();
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("resource_bi_map");
        if (serializableExtra instanceof Map) {
            this.h = (Map) serializableExtra;
        }
        this.o = intent.getBooleanExtra("is_vip_expired", false);
        SingleGridContent singleGridContent = (SingleGridContent) intent.getParcelableExtra("audition_content");
        this.r = singleGridContent;
        if (singleGridContent == null) {
            LogUtil.h("AuditionActivity", "mSingleGridContent is null");
            finish();
        }
    }

    private void g() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(this.r.getAuditionLink());
        ele.c().d(this);
        ele.c().c(this.r.getDynamicDataId(), arrayList);
    }

    private void j() {
        HealthBottomSheet healthBottomSheet = (HealthBottomSheet) findViewById(R.id.sliding_layout);
        this.i = healthBottomSheet;
        healthBottomSheet.setIndicateSafeInsetsEnabled(true);
        this.i.setForceShowIndicateEnabled(false);
        this.i.setSheetHeight(0);
        this.c = (ImageView) findViewById(R.id.audition_icon);
        this.d = (ImageView) findViewById(R.id.audition_play_control_btn);
        this.j = (HealthTextView) findViewById(R.id.audition_title);
        this.f2899a = (HealthTextView) findViewById(R.id.audition_flag);
        this.b = (HealthTextView) findViewById(R.id.audition_detail);
        this.m = (HealthFlowLayout) findViewById(R.id.audition_description);
        this.e = (HealthTextView) findViewById(R.id.audition_played_time);
        this.g = (HealthTextView) findViewById(R.id.audition_total_time);
        this.l = (HealthSeekBar) findViewById(R.id.audition_seek_bar);
        this.q = (FrameLayout) findViewById(R.id.audition_vip_layout);
        this.l.setTouchable(true);
        this.l.setOnSeekBarChangeListener(this);
        this.d.setOnClickListener(this);
        this.i.d(new HwBottomSheet.SheetSlideListener() { // from class: com.huawei.health.marketing.views.audition.AuditionActivity.2
            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetSlide(View view, float f) {
            }

            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetStateChanged(View view, HwBottomSheet.SheetState sheetState, HwBottomSheet.SheetState sheetState2) {
                if (sheetState2 == HwBottomSheet.SheetState.COLLAPSED || sheetState2 == HwBottomSheet.SheetState.HIDDEN) {
                    AuditionActivity.this.c(2);
                    AuditionActivity.this.finish();
                }
            }
        });
        findViewById(R.id.content).setOnClickListener(this);
        findViewById(R.id.drag_content).setOnClickListener(this);
    }

    private void c() {
        int i;
        int i2;
        i();
        if (!TextUtils.isEmpty(this.r.getAuditionDetailUrl())) {
            nrf.cIU_(this.r.getAuditionDetailUrl(), this.c, this.n.getResources().getDimensionPixelOffset(R.dimen._2131364527_res_0x7f0a0aaf));
        }
        this.j.setText(this.r.getTheme());
        if (!TextUtils.isEmpty(this.r.getAuditionDetail())) {
            this.b.setText(this.r.getAuditionDetail());
            this.b.setVisibility(0);
        }
        f();
        this.q.removeAllViews();
        if (this.r.getVip() == 1) {
            this.q.setMinimumHeight(getResources().getDimensionPixelOffset(R.dimen._2131363120_res_0x7f0a0530));
            StringBuilder sb = new StringBuilder("#/PayPopup?payResourceType=");
            sb.append(TextUtils.equals(this.f, "SleepingSeries") ? 7 : 4);
            sb.append("&benefitName=");
            sb.append(this.r.getTheme());
            this.p = ((VipApi) Services.c("vip", VipApi.class)).getVipPayView(this, "", sb.toString(), b());
            i = R.color._2131299320_res_0x7f090bf8;
            i2 = R.drawable._2131427560_res_0x7f0b00e8;
        } else {
            this.q.setMinimumHeight(getResources().getDimensionPixelOffset(R.dimen._2131363076_res_0x7f0a0504));
            TradeViewInfo tradeViewInfo = new TradeViewInfo(this.r.getDynamicDataId(), 12, "");
            tradeViewInfo.setTrigResName(this.r.getTheme());
            tradeViewInfo.setBiParams(this.h);
            this.p = ((TradeViewApi) Services.c("TradeService", TradeViewApi.class)).getTradeView(this, tradeViewInfo);
            i = R.color._2131296643_res_0x7f090183;
            i2 = R.drawable._2131427559_res_0x7f0b00e7;
        }
        this.q.addView(this.p);
        b(i);
        this.l.setProgressDrawable(this.n.getResources().getDrawable(i2));
        this.d.setBackground(nrf.cJH_(this.n.getDrawable(R.drawable._2131431552_res_0x7f0b1080), this.n.getColor(i)));
        this.d.setImageDrawable(nrf.cJH_(this.n.getDrawable(R.mipmap._2131820564_res_0x7f110014), this.n.getColor(R.color._2131297782_res_0x7f0905f6)));
    }

    private void f() {
        SearchTagTextViewBuilder searchTagTextViewBuilder = new SearchTagTextViewBuilder(BaseApplication.getContext());
        if (nsn.r()) {
            this.m.setTagHeight(nsn.c(this.n, 30.0f));
        } else {
            this.m.setTagHeight(nsn.c(this.n, 16.0f));
        }
        searchTagTextViewBuilder.b(R.dimen._2131365067_res_0x7f0a0ccb).a(R.color._2131299241_res_0x7f090ba9).c(R$string.textFontFamilyRegular).d(R.drawable._2131430760_res_0x7f0b0d68);
        this.m.setTextViewBuilder(searchTagTextViewBuilder);
        if (!koq.b(this.r.getAuditionLabel())) {
            this.m.e(c(this.r.getAuditionLabel()), false);
            this.m.setVisibility(0);
        } else {
            this.m.setVisibility(8);
        }
    }

    private void i() {
        String extend = this.r.getExtend();
        if (TextUtils.isEmpty(extend) || !extend.contains("category")) {
            LogUtil.h("AuditionActivity", "parseCategory extentStr is empty");
        } else {
            this.f = extend.substring(8);
        }
    }

    private void b(int i) {
        if (TextUtils.isEmpty(this.r.getCorner())) {
            LogUtil.h("AuditionActivity", "showFlagBtn mSingleGridContent corner is empty");
            this.f2899a.setVisibility(8);
            return;
        }
        int color = ContextCompat.getColor(BaseApplication.getContext(), i);
        try {
            float c2 = nsn.c(BaseApplication.getContext(), 4.0f);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(c2);
            gradientDrawable.setColor(color);
            this.f2899a.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("AuditionActivity", "IllegalArgumentException");
        }
        this.f2899a.setVisibility(0);
        this.f2899a.setText(this.r.getCorner());
    }

    private Map<String, Object> b() {
        HashMap hashMap = new HashMap(16);
        Map<String, String> map = this.h;
        if (map == null) {
            return hashMap;
        }
        hashMap.putAll(map);
        return hashMap;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        LogUtil.a("AuditionActivity", "onResume");
        if (this.p == null) {
            c();
            ThreadPoolManager.d().execute(new Runnable() { // from class: ekw
                @Override // java.lang.Runnable
                public final void run() {
                    AuditionActivity.this.a();
                }
            });
        } else {
            n();
        }
    }

    public /* synthetic */ void a() {
        g();
        c(0);
        e();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.i.getSheetState() == HwBottomSheet.SheetState.EXPANDED) {
            h();
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            LogUtil.h("AuditionActivity", "onClick() view click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.audition_play_control_btn) {
            ele.c().a();
        } else if (id == R.id.content) {
            c(3);
            h();
        } else if (id == R.id.drag_content) {
            LogUtil.a("AuditionActivity", "drag_content clicked");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ObserverManagerUtil.c("MARKETING_AUDITION_BTN_REFRESH", Integer.valueOf(R.drawable._2131430254_res_0x7f0b0b6e), Integer.valueOf(this.r.getIsPay()));
        ele.c().f();
        ele.c().d((AuditionPlayerListener) null);
        this.k.removeMessages(1);
        ObserverManagerUtil.e("MARKETING_AUDITION_BTN_REFRESH");
        finish();
    }

    private ArrayList<nmk> c(List<String> list) {
        ArrayList<nmk> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new nmk(i, list.get(i), 1));
        }
        return arrayList;
    }

    @Override // com.huawei.health.marketing.views.audition.AuditionPlayerListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.l.setProgress(0);
        int duration = mediaPlayer.getDuration();
        this.l.setMax(duration);
        this.g.setText(a(duration));
        this.k.sendEmptyMessageDelayed(1, 1000L);
    }

    @Override // com.huawei.health.marketing.views.audition.AuditionPlayerListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.d.setImageDrawable(nrf.cJH_(this.n.getDrawable(R.mipmap._2131820563_res_0x7f110013), this.n.getColor(R.color._2131297782_res_0x7f0905f6)));
        ObserverManagerUtil.c("MARKETING_AUDITION_BTN_REFRESH", Integer.valueOf(R.drawable._2131430254_res_0x7f0b0b6e));
        this.k.removeMessages(1);
    }

    @Override // com.huawei.health.marketing.views.audition.AuditionPlayerListener
    public void onPlayerPause() {
        this.d.setImageDrawable(nrf.cJH_(this.n.getDrawable(R.mipmap._2131820563_res_0x7f110013), this.n.getColor(R.color._2131297782_res_0x7f0905f6)));
        ObserverManagerUtil.c("MARKETING_AUDITION_BTN_REFRESH", Integer.valueOf(R.drawable._2131430254_res_0x7f0b0b6e));
        this.k.removeMessages(1);
        e();
    }

    @Override // com.huawei.health.marketing.views.audition.AuditionPlayerListener
    public void onPlayerStart() {
        this.d.setImageDrawable(nrf.cJH_(this.n.getDrawable(R.mipmap._2131820564_res_0x7f110014), this.n.getColor(R.color._2131297782_res_0x7f0905f6)));
        ObserverManagerUtil.c("MARKETING_AUDITION_BTN_REFRESH", Integer.valueOf(R.drawable._2131428570_res_0x7f0b04da));
        this.k.sendEmptyMessage(1);
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i) {
        return new SimpleDateFormat("mm:ss").format(new Date(i));
    }

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
        ele.c().d(healthSeekBar.getProgress());
        c(1);
    }

    private void n() {
        if (this.r.getVip() == 2) {
            LogUtil.a("AuditionActivity", "refreshView tradeview");
            ((TradeViewApi) Services.c("TradeService", TradeViewApi.class)).refreshView(this.p);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: eky
                @Override // java.lang.Runnable
                public final void run() {
                    AuditionActivity.this.d();
                }
            });
        }
    }

    public /* synthetic */ void d() {
        LogUtil.a("AuditionActivity", "updateVipStatus");
        ((VipApi) Services.c("vip", VipApi.class)).getVipInfo(new c(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("audioId", this.r.getDynamicDataId());
        hashMap.put("audioName", this.r.getTheme());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MARKETING_AUDITION.value(), hashMap, 0);
    }

    private void e() {
        String str = this.f;
        if (str != null && !TextUtils.equals(str, "SleepAudio")) {
            LogUtil.h("AuditionActivity", "audioPlayBiEvent not sleep audio");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.r.getTheme());
        hashMap.put("click", 1);
        hashMap.put("from", "4");
        hashMap.put("audioId", this.r.getDynamicDataId());
        hashMap.put("BIType", 2);
        hashMap.put("status", Integer.valueOf(ele.c().d() ? 1 : 0));
        hashMap.put("vipMode", Integer.valueOf(this.r.getVip()));
        hashMap.put("resourceLabel", this.r.getAuditionLabel());
        hashMap.put("isSeries", true);
        hashMap.put("isAuditoryClip", true);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SLEEP_RECORD_1090095.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", 4);
        hashMap.put("from", 5);
        hashMap.put("name", this.r.getTheme());
        hashMap.put("payResourceType", Integer.valueOf(TextUtils.equals(this.f, "SleepingSeries") ? 7 : 4));
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            hashMap.putAll(marketRouterApi.getLastMarketSource());
        }
        Map<String, String> map = this.h;
        if (map != null) {
            hashMap.put("resourcePositionId", map.get(WebViewHelp.BI_KEY_PULL_FROM));
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("AuditionActivity", "onActivityResult requestCode=", Integer.valueOf(i), " resultCode=", Integer.valueOf(i2));
        if (i != 0) {
            LogUtil.h("AuditionActivity", "onActivityResult return");
            return;
        }
        int intExtra = intent != null ? intent.getIntExtra("shoppingResult", -1) : -1;
        LogUtil.a("AuditionActivity", "shoppingResult:", Integer.valueOf(intExtra));
        if (intExtra == 0) {
            LogUtil.a("AuditionActivity", "shoppingResult success.");
            this.r.setIsPay(1);
            h();
            return;
        }
        LogUtil.h("AuditionActivity", "shoppingResult fail. resultCode = ", Integer.valueOf(intExtra));
    }

    static class c implements VipCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<AuditionActivity> f2900a;

        c(AuditionActivity auditionActivity) {
            this.f2900a = new WeakReference<>(auditionActivity);
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onSuccess(Object obj) {
            String value;
            if (obj == null) {
                LogUtil.h("AuditionActivity", "getVipInfo onSuccess result is null");
                return;
            }
            AuditionActivity auditionActivity = this.f2900a.get();
            if (auditionActivity == null || auditionActivity.isFinishing() || auditionActivity.isDestroyed()) {
                LogUtil.h("AuditionActivity", "AuditionActivity is null");
                return;
            }
            if (obj instanceof UserMemberInfo) {
                UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
                LogUtil.c("AuditionActivity", "getVipInfo mUserMemberInfo = ", userMemberInfo.toString());
                if (userMemberInfo.getMemberFlag() != 1 || gpn.d(userMemberInfo)) {
                    return;
                }
                if (auditionActivity.o) {
                    value = AnalyticsValue.VIP_RENEWAL_PAY_SUCCESS_EVENT.value();
                } else {
                    value = AnalyticsValue.VIP_PAY_SUCCESS_EVENT.value();
                }
                auditionActivity.e(value);
                LogUtil.a("AuditionActivity", "vip course, your are vip, close audition page");
                auditionActivity.h();
            }
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onFailure(int i, String str) {
            LogUtil.h("AuditionActivity", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
