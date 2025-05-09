package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.TopBannerTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.suggestion.ui.fitness.module.SwitchView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.ui.commonui.woundplastadview.WoundPlast;
import defpackage.ash;
import defpackage.eil;
import defpackage.jcf;
import defpackage.mld;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.util.Map;

/* loaded from: classes4.dex */
public class CoachPauseRestView extends RelativeLayout implements HealthSeekBar.OnSeekBarChangeListener, SwitchView.OnSwitchStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3168a;
    private LinearLayout b;
    private HealthTextView c;
    private HealthTextView d;
    private ImageView e;
    private HealthSeekBar f;
    private ImageView g;
    private HealthSeekBar h;
    private HealthSeekBar i;
    private HealthTextView j;
    private ImageView k;
    private LinearLayout l;
    private ImageView m;
    private HealthTextView n;
    private RelativeLayout o;
    private ImageView p;
    private TimeProgressPlus q;
    private RelativeLayout r;
    private HealthTextView s;
    private ImageView t;
    private LinearLayout u;
    private RelativeLayout v;
    private SwitchView.OnSwitchStateChangeListener w;
    private long x;
    private HealthTextView y;

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
    }

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
    }

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
    }

    public CoachPauseRestView(Context context) {
        super(context);
    }

    public CoachPauseRestView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CoachPauseRestView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundColor(getResources().getColor(R$color.common_black_60alpha));
        setClickable(true);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sug_coach_view_setting, this);
        this.v = (RelativeLayout) inflate.findViewById(R.id.sug_coach_set_ll_volice_set);
        this.f = (HealthSeekBar) inflate.findViewById(R.id.sug_coach_set_pb_count);
        this.i = (HealthSeekBar) inflate.findViewById(R.id.sug_coach_set_pb_guide);
        this.h = (HealthSeekBar) inflate.findViewById(R.id.sug_coach_set_pb_bg);
        this.p = (ImageView) inflate.findViewById(R.id.coach_set_iv_bg_pre);
        this.t = (ImageView) inflate.findViewById(R.id.sug_coach_set_iv_stop);
        this.g = (ImageView) inflate.findViewById(R.id.sug_coach_set_iv_continue);
        this.m = (ImageView) inflate.findViewById(R.id.sug_coach_set_voice_ok);
        this.k = (ImageView) inflate.findViewById(R.id.coach_set_iv_bg_nex);
        this.n = (HealthTextView) inflate.findViewById(R.id.coach_set_tv_bg);
        this.j = (HealthTextView) inflate.findViewById(R.id.sug_coach_set_tv_motion);
        this.f3168a = (HealthTextView) inflate.findViewById(R.id.sug_coach_set_tv_motionkcal);
        this.d = (HealthTextView) inflate.findViewById(R.id.sug_coach_set_tv_motiontime);
        this.c = (HealthTextView) inflate.findViewById(R.id.sug_coach_set_tv_motionname);
        this.e = (ImageView) inflate.findViewById(R.id.sug_coach_set_iv_motion);
        this.q = (TimeProgressPlus) inflate.findViewById(R.id.sug_coach_set_tp_countdown);
        this.s = (HealthTextView) inflate.findViewById(R.id.sug_coach_set_tv_worn);
        this.u = (LinearLayout) inflate.findViewById(R.id.marketing_wound_plast_coach);
        this.o = (RelativeLayout) inflate.findViewById(R.id.sug_coach_set_ll_show_pause);
        this.r = (RelativeLayout) inflate.findViewById(R.id.sug_coach_set_rl_coach_rest);
        this.l = (LinearLayout) inflate.findViewById(R.id.sug_rl_coach_set_pause);
        this.y = (HealthTextView) inflate.findViewById(R.id.sug_coach_equipment_tip);
        this.b = (LinearLayout) inflate.findViewById(R.id.click_continue);
        j();
        jcf.bEA_(this.b, nsf.j(R.string._2130848394_res_0x7f022a8a), Button.class);
        jcf.bEA_((LinearLayout) inflate.findViewById(R.id.click_finish), nsf.j(R.string._2130848366_res_0x7f022a6e), Button.class);
        ((HealthTextView) inflate.findViewById(R.id.sug_coach_set_tv_continue)).setText(inflate.getResources().getString(R.string._2130848394_res_0x7f022a8a).toUpperCase());
        f();
        g();
        if (LanguageUtil.bc(getContext())) {
            h();
        }
    }

    private void j() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.sug_coach_view_pause_layout);
        if (frameLayout != null) {
            ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
            LogUtil.a("Suggestion_CoachPauseRestView", "onFinishInflate scaledDensity：", Float.valueOf(nsn.f()));
            if (nsn.a(2.1f) && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(0, 0, 0, mld.d(getContext(), 5.0f));
                frameLayout.setLayoutParams(layoutParams);
            }
        }
    }

    private void h() {
        d(R.id.sug_coach_volume_swh_count, R.drawable._2131431658_res_0x7f0b10ea);
        d(R.id.sug_coach_volume_swh_guide, R.drawable._2131431658_res_0x7f0b10ea);
        d(R.id.sug_coach_volume_swh_bg, R.drawable._2131431658_res_0x7f0b10ea);
    }

    private void d(int i, int i2) {
        ((ImageView) findViewById(i)).setImageDrawable(nrz.cKn_(getContext(), i2));
    }

    private void f() {
        this.f.setMax(1000);
        this.i.setMax(1000);
        this.h.setMax(1000);
        String b = ash.b("voicecoachviewcount");
        if (TextUtils.isEmpty(b)) {
            b = "1000";
        }
        String b2 = ash.b("voicecoachviewbg");
        if (TextUtils.isEmpty(b2)) {
            b2 = "1000";
        }
        String b3 = ash.b("voicecoachviewguide");
        String str = TextUtils.isEmpty(b3) ? "1000" : b3;
        try {
            this.f.setProgress(Integer.parseInt(b));
            this.f.setTouchable(true);
            this.i.setProgress(Integer.parseInt(str));
            this.i.setTouchable(true);
            this.h.setProgress(Integer.parseInt(b2));
            this.h.setTouchable(true);
        } catch (NumberFormatException e) {
            LogUtil.b("Suggestion_CoachPauseRestView", "NumberFormatException:", LogAnonymous.b((Throwable) e));
        }
    }

    private void g() {
        ((SwitchView) findViewById(R.id.sug_coach_set_swh_count)).a(true).setOnSwitchStateChangeListener(this);
        ((SwitchView) findViewById(R.id.sug_coach_set_swh_bg)).a(true).setOnSwitchStateChangeListener(this);
        ((SwitchView) findViewById(R.id.sug_coach_set_swh_guide)).a(true).setOnSwitchStateChangeListener(this);
    }

    private void c(final int i) {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.a("Suggestion_CoachPauseRestView", "marketingApi is null.");
        } else {
            LogUtil.a("Suggestion_CoachPauseRestView", "coach pause rest view start band play");
            marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachPauseRestView.4
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    ResourceResultInfo resourceResultInfo = marketingApi.filterMarketingRules(map).get(Integer.valueOf(i));
                    if (resourceResultInfo == null || resourceResultInfo.getTotalNum() == 0) {
                        LogUtil.h("Suggestion_CoachPauseRestView", "resourceResultInfo is null");
                        return;
                    }
                    CoachPauseRestView.this.u.addView(CoachPauseRestView.this.c(i, resourceResultInfo.getResources().get(0)));
                    CoachPauseRestView.this.e.setVisibility(8);
                    CoachPauseRestView.this.u.setVisibility(0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WoundPlast c(final int i, final ResourceBriefInfo resourceBriefInfo) {
        final TopBannerTemplate topBannerTemplate = (TopBannerTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), TopBannerTemplate.class);
        WoundPlast woundPlast = new WoundPlast(getContext());
        woundPlast.setAdImage(topBannerTemplate.getPicture());
        woundPlast.setImageSizeMode(WoundPlast.ImageSizeMode.NORMAL_IMAGE);
        this.x = System.currentTimeMillis();
        woundPlast.setAdClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachPauseRestView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MarketingBiUtils.b(i, resourceBriefInfo, CoachPauseRestView.this.x);
                eil.a(topBannerTemplate.getLinkValue(), i, resourceBriefInfo);
                CoachPauseRestView.this.x = System.currentTimeMillis();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        woundPlast.setCloseBtnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachPauseRestView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                eil.a(i, 1, resourceBriefInfo);
                CoachPauseRestView.this.u.setVisibility(8);
                CoachPauseRestView.this.e.setVisibility(0);
                MarketingBiUtils.c(i, resourceBriefInfo, CoachPauseRestView.this.x);
                CoachPauseRestView.this.x = System.currentTimeMillis();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return woundPlast;
    }

    public HealthSeekBar getCoachSetCountVoice() {
        return this.f;
    }

    public HealthSeekBar getCoachSetGuideVoice() {
        return this.i;
    }

    public HealthSeekBar getCoachSetBackgroundVoice() {
        return this.h;
    }

    public ImageView getCoachSetPreButton() {
        return this.p;
    }

    public HealthTextView getCoachSetMotionText() {
        return this.n;
    }

    public ImageView getCoachSetNextButton() {
        return this.k;
    }

    public RelativeLayout getCoachSetPausePage() {
        return this.o;
    }

    public ImageView getCoachSetStop() {
        return this.t;
    }

    public ImageView getCoachSetOk() {
        return this.m;
    }

    public ImageView getCoachSetContinue() {
        return this.g;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.module.SwitchView.OnSwitchStateChangeListener
    public void onSwitchStateChange(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.sug_coach_set_swh_bg) {
            ash.a("coachviewbg", String.valueOf(z));
            this.h.setEnabled(z);
            if (this.w != null) {
                view.setTag(2);
                this.w.onSwitchStateChange(view, z);
                return;
            }
            return;
        }
        if (id == R.id.sug_coach_set_swh_guide) {
            ash.a("coachviewguide", String.valueOf(z));
            this.i.setEnabled(z);
            if (this.w != null) {
                view.setTag(1);
                this.w.onSwitchStateChange(view, z);
                return;
            }
            return;
        }
        if (this.w != null) {
            view.setTag(0);
            this.w.onSwitchStateChange(view, z);
        }
        ash.a("coachviewcount", String.valueOf(z));
        this.f.setEnabled(z);
    }

    public void e(int i, boolean z, String str, String str2, String str3, String str4, String str5) {
        b();
        this.q.b(i);
        if (!z) {
            this.j.setText(getContext().getResources().getString(R.string._2130848383_res_0x7f022a7f));
        } else {
            this.j.setText(getContext().getResources().getString(R.string._2130848382_res_0x7f022a7e));
        }
        e(str, str2, str3, str4, str5);
    }

    public void b() {
        setVisibility(0);
        this.v.setVisibility(8);
        this.o.setVisibility(0);
        this.l.setVisibility(4);
        this.r.setVisibility(0);
    }

    public void c() {
        setVisibility(4);
        this.q.b();
    }

    public void d(String str, String str2, String str3, String str4, String str5) {
        setVisibility(0);
        this.v.setVisibility(8);
        this.o.setVisibility(0);
        this.r.setVisibility(4);
        this.l.setVisibility(0);
        this.j.setText(getContext().getString(R.string._2130848398_res_0x7f022a8e));
        e(str, str2, str3, str4, str5);
    }

    public void b(String str, String str2, String str3, String str4, String str5) {
        setVisibility(0);
        this.v.setVisibility(8);
        this.o.setVisibility(0);
        this.r.setVisibility(4);
        this.l.setVisibility(0);
        this.j.setText(getContext().getString(R.string._2130848776_res_0x7f022c08));
        e(str, str2, str3, str4, str5);
    }

    private void e(String str, String str2, String str3, String str4, String str5) {
        this.c.setText(str);
        nrf.cJA_(str5, this.e.getWidth(), this.e.getHeight(), this.e);
        this.s.setText(str4);
        this.f3168a.setText(str2);
        this.d.setText(str3);
        c(10002);
    }

    public void a() {
        setVisibility(0);
        this.o.setVisibility(4);
        this.v.setVisibility(0);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 4) {
            ash.a("voicecoachviewcount", String.valueOf(this.f.getProgress()));
            ash.a("voicecoachviewbg", String.valueOf(this.h.getProgress()));
            ash.a("voicecoachviewguide", String.valueOf(this.i.getProgress()));
        }
    }

    public void d(SwitchView.OnSwitchStateChangeListener onSwitchStateChangeListener) {
        this.w = onSwitchStateChangeListener;
    }

    public TimeProgressPlus d() {
        return this.q;
    }

    public void setEquipmentPauseTips(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_CoachPauseRestView", "pauseTips is isEmpty");
        } else {
            this.y.setText(str);
        }
    }

    public void e() {
        this.b.setVisibility(8);
    }
}
