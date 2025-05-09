package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.SectionMessageWindow;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.activetips.ActiveTipsView;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.utils.ScrollUtil;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionMessageWindow extends BaseSection {
    private static final int b = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2702a;
    private HealthTextView aa;
    private String ab;
    private ImageView ac;
    private HealthTextView ad;
    private HealthSubHeader ae;
    private HealthTextView af;
    private HealthTextView ag;
    private ImageView ah;
    private ImageView ai;
    private HealthTextView aj;
    private HealthCardView am;
    private HealthTextView c;
    private HashMap<String, Object> d;
    private boolean e;
    private HealthScrollView f;
    private boolean g;
    private HealthCardView h;
    private ActiveTipsView i;
    private Context j;
    private boolean k;
    private HealthTextView l;
    private LinearLayout m;
    private HealthCardView n;
    private boolean o;
    private ImageView p;
    private HealthTextView q;
    private ImageView r;
    private HealthTextView s;
    private HealthTextView t;
    private View u;
    private HealthCardView v;
    private OnClickSectionListener w;
    private d x;
    private LinearLayout y;
    private HealthTextView z;

    public SectionMessageWindow(Context context) {
        super(context);
        this.ab = "";
        this.e = false;
        this.g = false;
        this.d = new HashMap<>();
    }

    public SectionMessageWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ab = "";
        this.e = false;
        this.g = false;
        this.d = new HashMap<>();
    }

    public SectionMessageWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ab = "";
        this.e = false;
        this.g = false;
        this.d = new HashMap<>();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionMessageWindow", "onCreateView");
        this.j = context;
        e();
        return this.u;
    }

    private void e() {
        View inflate = LayoutInflater.from(this.j).inflate(R.layout.section_message_window, (ViewGroup) this, false);
        this.u = inflate;
        this.am = (HealthCardView) inflate.findViewById(R.id.section_window_root);
        this.z = (HealthTextView) this.u.findViewById(R.id.doctor_icon);
        this.af = (HealthTextView) this.u.findViewById(R.id.doctor_name);
        this.f2702a = (HealthTextView) this.u.findViewById(R.id.doctor_describe);
        this.ad = (HealthTextView) this.u.findViewById(R.id.doctor_message);
        this.c = (HealthTextView) this.u.findViewById(R.id.doctor_button);
        this.ac = (ImageView) this.u.findViewById(R.id.doctor_avatar);
        this.ai = (ImageView) this.u.findViewById(R.id.message_picture);
        this.m = (LinearLayout) this.u.findViewById(R.id.health_model_not_sign_doctor_container);
        this.r = (ImageView) this.u.findViewById(R.id.not_sign_doctor);
        this.q = (HealthTextView) this.u.findViewById(R.id.no_doctor_message);
        if (nsn.p()) {
            this.q.setTextSize(1, 14.0f);
        }
        this.l = (HealthTextView) this.u.findViewById(R.id.no_doctor_button);
        this.v = (HealthCardView) this.u.findViewById(R.id.section_window_not_sign_doctor);
        this.i = (ActiveTipsView) this.u.findViewById(R.id.active_tips_view);
        this.f = getKnitFragment().getHealthScrollView();
        HealthCardView healthCardView = (HealthCardView) this.u.findViewById(R.id.section_challenge_plan_view);
        this.h = healthCardView;
        healthCardView.post(new Runnable() { // from class: eha
            @Override // java.lang.Runnable
            public final void run() {
                SectionMessageWindow.this.b();
            }
        });
        this.n = (HealthCardView) this.u.findViewById(R.id.section_measure_plan_view);
        this.s = (HealthTextView) this.u.findViewById(R.id.section_right_button_description_top);
        this.t = (HealthTextView) this.u.findViewById(R.id.section_right_button_description_bottom);
        this.p = (ImageView) this.u.findViewById(R.id.section_right_button_icon);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.u.findViewById(R.id.section_sub_header);
        this.ae = healthSubHeader;
        healthSubHeader.setGuideLine();
        this.ah = (ImageView) this.u.findViewById(R.id.section_icon);
        this.ag = (HealthTextView) this.u.findViewById(R.id.section_description_top);
        this.aa = (HealthTextView) this.u.findViewById(R.id.section_description_bottom);
        this.y = (LinearLayout) this.u.findViewById(R.id.weekly_report_entrance);
        this.aj = (HealthTextView) this.u.findViewById(R.id.weekly_report_text);
        this.v.setVisibility(8);
    }

    public /* synthetic */ void b() {
        this.h.setMinimumWidth(nsn.h(this.j));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionMessageWindow", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.b("SectionMessageWindow", "no need to bind");
            return;
        }
        nsy.cMr_(this.z, nru.b(hashMap, "RIGHT_ICON_TEXT", ""));
        nsy.cMj_(this.z, (Drawable) nru.c(hashMap, "RIGHT_ICON_IMAGE", Drawable.class, null));
        nsy.cMj_(this.ai, (Drawable) nru.c(hashMap, "MIDDLE_ICON_IMAGE", Drawable.class, null));
        nsy.cMr_(this.ad, nru.b(hashMap, "GUIDE_BUBBLE_TEXT", ""));
        nsy.cMr_(this.c, nru.b(hashMap, "ITEM_BUTTON_TEXT", ""));
        if (!LanguageUtil.m(this.j) || !LanguageUtil.p(this.j)) {
            this.c.setAllCaps(true);
        }
        nsy.cMr_(this.s, nru.b(hashMap, "RIGHT_BUTTON_TOP_DES", ""));
        nsy.cMr_(this.t, nru.b(hashMap, "RIGHT_BUTTON_BOTTOM_DES", ""));
        nsy.cMm_(this.p, nru.cKj_(hashMap, "RIGHT_BUTTON_IMAGE", null));
        nsy.cMA_(this.h, nru.d((Map) hashMap, "LEFT_BUTTON_VISIBILITY", true) ? 0 : 8);
        d dVar = (d) nru.c(hashMap, "TOP_CARD_BEAN", d.class, null);
        this.x = dVar;
        if (dVar != null) {
            nsy.cMr_(this.af, dVar.d());
            nsy.cMr_(this.f2702a, this.x.e());
            this.k = this.x.c();
            this.o = this.x.a();
            LogUtil.a("SectionMessageWindow", "mIsJoindPrivilege is ：", Boolean.valueOf(this.k), "mIsShowHasDoctorCardType is ：", Boolean.valueOf(this.o));
        }
        a();
        this.w = (OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null);
        d();
        setDoctorCardType(hashMap);
        this.g = !eet.c(hashMap, this.d);
        this.d.putAll(hashMap);
        if (this.g) {
            setViewData(hashMap);
        }
        a(hashMap);
    }

    private void a(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionMessageWindow", "bindBannerView");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionMessageWindow", "no need to bind");
            return;
        }
        boolean d2 = nru.d((Map) hashMap, "IS_REMIND_SHOW", false);
        LogUtil.a("SectionMessageWindow", "isShowBanner: " + d2 + ", mActiveTipsView visibility: " + this.i.getVisibility());
        if (d2) {
            this.i.setVisibility(0);
            this.i.setTipsText(nru.b(hashMap, "BANNER_CARD_CONTENT", getResources().getString(R$string.IDS_user_service_has_pick_tip)));
            this.i.setActiveBtText(nru.b(hashMap, "BANNER_CARD_SUBMIT", ""));
            setActiveTipsViewVisibleFocusListener();
            return;
        }
        this.i.setVisibility(8);
    }

    public void setActiveTipsViewVisibleFocusListener() {
        HealthScrollView healthScrollView = this.f;
        if (healthScrollView != null) {
            healthScrollView.setScrollChangeOtherListener(new HealthScrollView.ScrollChangeOtherListener() { // from class: com.huawei.health.knit.section.view.SectionMessageWindow.1
                @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeOtherListener
                public void scrollChangeOtherListener() {
                    boolean cKv_ = ScrollUtil.cKv_(SectionMessageWindow.this.f, SectionMessageWindow.this.i);
                    LogUtil.a("SectionMessageWindow", "setActiveTipsViewVisibleFocusListener isVisibleArea = ", Boolean.valueOf(cKv_));
                    SectionMessageWindow.this.i.setSelected(cKv_);
                }
            });
        } else {
            LogUtil.b("SectionMessageWindow", "setActiveTipsViewVisibleFocusListener mHealthScrollView is null");
        }
    }

    private void a() {
        d dVar = this.x;
        if (dVar == null) {
            LogUtil.a("SectionMessageWindow", "setDoctorImg mTopCardBean is null");
            return;
        }
        String b2 = dVar.b();
        if (b2.length() <= 0 || b2.equals(this.ab)) {
            return;
        }
        nrf.cIS_(this.ac, b2, b, 0, 0);
        this.ab = b2;
    }

    private void setDoctorCardType(HashMap<String, Object> hashMap) {
        this.z.setVisibility(4);
        if (this.k) {
            LogUtil.a("SectionMessageWindow", "has bind doctor");
            nsy.cMA_(this.ae, 0);
            if (this.o) {
                this.v.setVisibility(8);
                this.am.setVisibility(0);
                return;
            }
            this.v.setVisibility(0);
            this.am.setVisibility(8);
            this.r.setImageResource(R.drawable.health_no_doctor_picture);
            nsy.cMr_(this.l, nru.b(hashMap, "ITEM_BUTTON_TEXT", ""));
            nsy.cMr_(this.q, nru.b(hashMap, "GUIDE_BUBBLE_TEXT", ""));
            return;
        }
        LogUtil.a("SectionMessageWindow", "has not bind doctor");
        nsy.cMA_(this.ae, 8);
        this.am.setVisibility(8);
        this.v.setVisibility(8);
    }

    private boolean b(HashMap<String, Object> hashMap) {
        return hashMap.containsKey("IS_LOAD_DEFAULT_VIEW") && (hashMap.get("IS_LOAD_DEFAULT_VIEW") instanceof Boolean) && ((Boolean) hashMap.get("IS_LOAD_DEFAULT_VIEW")).booleanValue();
    }

    private void setViewData(HashMap<String, Object> hashMap) {
        nsy.cMA_(this.ae, 0);
        nsy.d(this.ae, 0, nru.b(hashMap, "TITLE", ""), 8, 8);
        nsy.cMm_(this.ah, (Drawable) nru.c(hashMap, "LEFT_ICON_IMAGE", Drawable.class, null));
        nsy.cMr_(this.aj, nru.b(hashMap, "BUTTON_TEXT", ""));
        setWeeklyReport(hashMap);
        if (TextUtils.isEmpty(nru.b(hashMap, "BUTTON_TEXT", ""))) {
            nsy.cMA_(this.y, 8);
            nsy.cMA_(this.aj, 8);
            nsy.cMA_(this.aa, 0);
        } else {
            nsy.cMA_(this.y, 0);
            nsy.cMA_(this.aj, 0);
            nsy.cMA_(this.aa, 8);
        }
    }

    private void setWeeklyReport(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionMessageWindow", "setSectionButton");
        if (b(hashMap)) {
            nsy.cMr_(this.ag, nru.b(hashMap, "ITEM_DESCRIPTION", ""));
        } else {
            Object d2 = nru.d(hashMap, "ITEM_DESCRIPTION", (Object) null);
            if (d2 instanceof SpannableString) {
                SpannableString spannableString = (SpannableString) d2;
                if (spannableString.length() != 0) {
                    nsy.cMr_(this.ag, spannableString);
                } else {
                    nsy.cMr_(this.ag, "");
                }
            } else if (d2 instanceof String) {
                nsy.cMr_(this.ag, (String) d2);
            } else {
                nsy.cMr_(this.ag, "");
            }
        }
        nsy.cMr_(this.aa, nru.b(hashMap, "ITEM_DESCRIPTION_BOTTOM", ""));
    }

    private void d() {
        HealthCardView healthCardView = this.h;
        if (healthCardView != null) {
            healthCardView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionMessageWindow.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("SectionMessageWindow", "joined or has doctor plan");
                    SectionMessageWindow.this.w.onClick("ALL_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        LinearLayout linearLayout = this.y;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionMessageWindow.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("SectionMessageWindow", "set button weekly report");
                    SectionMessageWindow.this.w.onClick("BUTTON_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        HealthCardView healthCardView2 = this.n;
        if (healthCardView2 != null) {
            healthCardView2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionMessageWindow.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionMessageWindow.this.w.onClick("MEASURE_PLAN_BUTTON_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        HealthCardView healthCardView3 = this.am;
        if (healthCardView3 != null) {
            healthCardView3.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionMessageWindow.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionMessageWindow.this.w.onClick("DOCTOR_BUTTON_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        ActiveTipsView activeTipsView = this.i;
        if (activeTipsView != null) {
            activeTipsView.setClickBannerListener(new View.OnClickListener() { // from class: ehh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SectionMessageWindow.this.ajl_(view);
                }
            });
        }
        HealthCardView healthCardView4 = this.v;
        if (healthCardView4 != null) {
            healthCardView4.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionMessageWindow.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionMessageWindow.this.w.onClick("RIGHT_BOTTOM_TEXT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    public /* synthetic */ void ajl_(View view) {
        this.w.onClick("BANNER_CARD_SUBMIT_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private String f2703a;
        private boolean b;
        private boolean c;
        private String d;
        private String e;

        public boolean a(d dVar) {
            if (dVar != null) {
                return this.f2703a.equals(dVar.f2703a) && this.d.equals(dVar.d) && this.e.equals(dVar.e) && this.b == dVar.b && this.c == dVar.c;
            }
            LogUtil.a("SectionMessageWindow", "topCardBean is null");
            return false;
        }

        public String d() {
            return this.f2703a;
        }

        public void d(String str) {
            this.f2703a = str;
        }

        public String e() {
            return this.d;
        }

        public void e(String str) {
            this.d = str;
        }

        public String b() {
            return this.e;
        }

        public void b(String str) {
            this.e = str;
        }

        public boolean c() {
            return this.c;
        }

        public void e(boolean z) {
            this.c = z;
        }

        public boolean a() {
            return this.b;
        }

        public void c(boolean z) {
            this.b = z;
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionMessageWindow";
    }
}
