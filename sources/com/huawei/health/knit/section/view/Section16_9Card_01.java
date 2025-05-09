package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ccq;
import defpackage.eas;
import defpackage.eet;
import defpackage.eie;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nru;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class Section16_9Card_01 extends BaseSection {
    private static int b = 100;
    private static int c = 130;
    private static int e = 300;

    /* renamed from: a, reason: collision with root package name */
    private HealthImageView f2638a;
    private HealthColumnSystem d;
    private HealthTextView f;
    private ImageView g;
    private ConstraintLayout h;
    private HealthTextView i;
    private eas j;
    private HealthDotsPageIndicator k;
    private ConstraintLayout l;
    private eas m;
    private HealthViewPager n;
    private Context o;
    private Pair<Integer, Integer> p;
    private List<Object> q;
    private FrameLayout r;
    private View s;
    private HealthViewPager t;
    private HealthButton u;
    private LinearLayout x;
    private HealthTextView y;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void overrideParamsByOnlineData(SectionBean sectionBean, HashMap<String, Object> hashMap) {
    }

    public Section16_9Card_01(Context context) {
        this(context, null);
    }

    public Section16_9Card_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section16_9Card_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = new ArrayList(10);
        this.p = BaseActivity.getSafeRegionWidth();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section16_9Card_01", "loadView");
        this.o = context;
        e();
        return this.s;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("Section16_9Card_01", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section16_9Card_01", "no need to bind");
            return;
        }
        f();
        setBackGround(nru.d(hashMap, "BACKGROUND", (Object) null));
        setRTLLanguage(nru.d(hashMap, "LEFT_TOP_VALUE", (Object) null));
        setLeftBottomText(nru.d(hashMap, "LEFT_BOTTOM_TEXT", (Object) null));
        setRightCombingText(nru.d(hashMap, "RIGHT_COMBINE_TEXT", (Object) null));
        setRightTopButton(nru.d(hashMap, "RIGHT_TOP_BUTTON", (Object) null));
        setBackgroundColor(nru.d(hashMap, "BACKGROUNDCOLOR", (Object) null));
        this.q = nru.d(hashMap, "SECTION16_9CARD_01_BANNER_DATA", Object.class, new ArrayList());
        setClickListenerEvent(nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null));
        setRightTopButtonDrawable(nru.d(hashMap, "RIGHT_TOP_BUTTON_ICON", (Object) null));
        d();
    }

    private void f() {
        int color;
        this.g.setImageDrawable(nrz.cKl_(this.o, nrt.a(this.o) ? R.drawable._2131431570_res_0x7f0b1092 : R.drawable._2131431569_res_0x7f0b1091));
        if (nrt.a(this.o)) {
            color = this.o.getResources().getColor(R.color._2131299238_res_0x7f090ba6);
        } else {
            color = this.o.getResources().getColor(R.color._2131299236_res_0x7f090ba4);
        }
        this.i.setTextColor(color);
        this.f.setTextColor(color);
    }

    private void setBackGround(Object obj) {
        if (eet.e(obj)) {
            List list = (List) obj;
            if (list.size() == 2) {
                if (nsn.ag(this.o)) {
                    LogUtil.a("Section16_9Card_01", "start to loadRoundRectangle tahitiBackground");
                    e(list.get(1));
                } else {
                    LogUtil.a("Section16_9Card_01", "start to loadRoundRectangle background");
                    e(list.get(0));
                }
            }
        }
    }

    private void setRightTopButtonDrawable(Object obj) {
        if (this.u == null || !eet.c(obj)) {
            return;
        }
        this.u.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(((Integer) obj).intValue(), null), (Drawable) null, (Drawable) null, (Drawable) null);
    }

    private void setRTLLanguage(Object obj) {
        if (this.i == null || !eet.d(obj)) {
            return;
        }
        this.i.setText(UnitUtil.e(((Long) obj).longValue(), 1, 0));
    }

    private void setLeftBottomText(Object obj) {
        if (this.f == null || !eet.f(obj)) {
            return;
        }
        LogUtil.a("Section16_9Card_01", "start to set button leftBottomText");
        this.f.setText(String.valueOf(obj));
    }

    private void setRightCombingText(Object obj) {
        if (this.y == null || !(obj instanceof CharSequence)) {
            return;
        }
        LogUtil.a("Section16_9Card_01", "start to set button rightCombineText");
        this.y.setText((CharSequence) obj);
    }

    private void setRightTopButton(Object obj) {
        if (this.u == null || !eet.f(obj)) {
            return;
        }
        LogUtil.a("Section16_9Card_01", "start to set rightTopButton");
        this.u.setText(String.valueOf(obj));
    }

    private void setBackgroundColor(Object obj) {
        if (this.f2638a == null || !eet.c(obj)) {
            return;
        }
        LogUtil.a("Section16_9Card_01", "start to set backgroundColor");
        this.f2638a.setBackgroundColor(((Integer) obj).intValue());
    }

    private void e(Object obj) {
        HealthImageView healthImageView = this.f2638a;
        if (healthImageView == null) {
            return;
        }
        if (obj instanceof Integer) {
            nrf.d(healthImageView, ((Integer) obj).intValue(), nrf.d, 0, 0);
        }
        if (obj instanceof String) {
            nrf.c(this.f2638a, (String) obj, nrf.d, 0, 0);
        }
    }

    private void setClickListenerEvent(Object obj) {
        if (eet.a(obj)) {
            final OnClickSectionListener onClickSectionListener = (OnClickSectionListener) obj;
            LogUtil.a("Section16_9Card_01", "start to set button click event");
            ConstraintLayout constraintLayout = this.h;
            if (constraintLayout != null) {
                constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section16_9Card_01.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("Section16_9Card_01", "set subtitleClickEvent onClick");
                        onClickSectionListener.onClick("ACCUMULATED_DURATION_CLICK_VIEW");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            HealthButton healthButton = this.u;
            if (healthButton != null) {
                healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section16_9Card_01.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("Section16_9Card_01", "set button onClick");
                        onClickSectionListener.onClick("ITEM_BUTTON_TEXT");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
    }

    private void e() {
        if (this.s == null) {
            LogUtil.h("Section16_9Card_01", "initView mainView is null, start to inflate");
            this.s = LayoutInflater.from(this.o).inflate(R.layout.section16_9card_layout, (ViewGroup) this, false);
        }
        if (this.d == null) {
            this.d = new HealthColumnSystem(this.o, 1);
        }
        this.f2638a = (HealthImageView) this.s.findViewById(R.id.common_background);
        this.i = (HealthTextView) this.s.findViewById(R.id.left_top_value);
        this.f = (HealthTextView) this.s.findViewById(R.id.left_bottom_text);
        this.y = (HealthTextView) this.s.findViewById(R.id.right_combine_text);
        this.u = (HealthButton) this.s.findViewById(R.id.right_top_button);
        this.h = (ConstraintLayout) this.s.findViewById(R.id.left_click_area);
        this.x = (LinearLayout) this.s.findViewById(R.id.subtitle_arrow_area);
        this.g = (ImageView) this.s.findViewById(R.id.left_bottom_text_arrow);
        if (this.y.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            this.y.setLayoutParams((ConstraintLayout.LayoutParams) this.y.getLayoutParams());
        }
        if (LanguageUtil.bc(this.o)) {
            this.x.setLayoutDirection(1);
            this.g.setImageResource(R.drawable._2131429720_res_0x7f0b0958);
        }
        h();
        this.l = (ConstraintLayout) this.s.findViewById(R.id.normal_view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.common_middle_click_view) {
            onClick("ACCUMULATED_DURATION_CLICK_VIEW");
        } else if (view.getId() == R.id.common_card_button) {
            onClick("ITEM_BUTTON_TEXT");
        } else {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        this.i.setTypeface(Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
    }

    private void d() {
        this.r = (FrameLayout) this.s.findViewById(R.id.banner_root);
        this.t = (HealthViewPager) this.s.findViewById(R.id.view_pager_banner);
        this.n = (HealthViewPager) this.s.findViewById(R.id.view_pager_banner_cant_loop);
        this.k = (HealthDotsPageIndicator) this.s.findViewById(R.id.indicator);
        a();
    }

    private void a() {
        b();
        if (this.t == null || this.n == null || koq.b(this.q)) {
            return;
        }
        e(this.q.size());
        m();
    }

    private void b() {
        if (this.r == null || this.l == null) {
            LogUtil.h("Section16_9Card_01", "changLayoutHeight mRootView == null || mContentLayout == null");
            return;
        }
        boolean b2 = koq.b(this.q);
        ReleaseLogUtil.e("Section16_9Card_01", "changLayoutHeight", Boolean.valueOf(b2));
        if (b2) {
            this.r.setVisibility(8);
            ViewGroup.LayoutParams layoutParams = this.l.getLayoutParams();
            if (nsn.e(1.1f)) {
                layoutParams.height = nrr.e(this.o, c);
            } else {
                layoutParams.height = nrr.e(this.o, b);
            }
            this.l.setLayoutParams(layoutParams);
            return;
        }
        this.r.setVisibility(0);
        ViewGroup.LayoutParams layoutParams2 = this.l.getLayoutParams();
        layoutParams2.height = nrr.e(this.o, e);
        this.l.setLayoutParams(layoutParams2);
    }

    private void e(int i) {
        if (i == 1) {
            i();
            return;
        }
        if (i == 2) {
            if (nsn.ag(this.o)) {
                i();
                return;
            } else {
                g();
                return;
            }
        }
        g();
    }

    private void g() {
        this.n.setVisibility(8);
        this.t.setVisibility(0);
        this.k.setVisibility(0);
        eas easVar = new eas(this.t, this.o);
        this.j = easVar;
        easVar.c(this.q);
        this.t.setOffscreenPageLimit(this.q.size());
        this.t.setAdapter(this.j);
        this.k.setViewPager(this.t);
        d(this.t);
        j();
        this.t.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.knit.section.view.Section16_9Card_01.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Section16_9Card_01.this.aib_(motionEvent);
                return false;
            }
        });
    }

    private void i() {
        this.n.setVisibility(0);
        this.t.setVisibility(8);
        this.k.setVisibility(8);
        eas easVar = new eas(this.n, this.o);
        this.m = easVar;
        easVar.c(this.q);
        this.n.setAdapter(this.m);
        d(this.n);
        j();
        this.n.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.knit.section.view.Section16_9Card_01.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Section16_9Card_01.this.aib_(motionEvent);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aib_(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            m();
        } else if (action == 0) {
            c();
        } else {
            LogUtil.a("Section16_9Card_01", "not need deal");
        }
    }

    private void j() {
        ViewGroup.LayoutParams layoutParams = this.r.getLayoutParams();
        int c2 = eie.c();
        LogUtil.a("Section16_9Card_01", "width = ", Integer.valueOf(c2));
        int dimension = (int) this.o.getResources().getDimension(R.dimen._2131362948_res_0x7f0a0484);
        if (nsn.t()) {
            dimension = (int) this.o.getResources().getDimension(R.dimen._2131362920_res_0x7f0a0468);
            LogUtil.a("Section16_9Card_01", "isMoreThanOneFontScale, maxHeight = ", Integer.valueOf(dimension));
        }
        int i = (c2 * 9) / 16;
        if (i <= dimension) {
            dimension = i;
        }
        if (nsn.ag(this.o) && nsn.l()) {
            dimension = (int) this.o.getResources().getDimension(R.dimen._2131362933_res_0x7f0a0475);
            if (nsn.t()) {
                dimension = (int) this.o.getResources().getDimension(R.dimen._2131362920_res_0x7f0a0468);
                LogUtil.a("Section16_9Card_01", "isMoreThanOneFontScale, height = ", Integer.valueOf(dimension));
            }
        }
        layoutParams.height = dimension;
        LogUtil.a("Section16_9Card_01", "result width = ", Integer.valueOf(c2), ", height=", Integer.valueOf(dimension));
        this.r.setLayoutParams(layoutParams);
    }

    private void d(HealthViewPager healthViewPager) {
        int i;
        int i2;
        int i3;
        int i4;
        if (koq.b(this.q)) {
            LogUtil.h("Section16_9Card_01", "resizeViewPager() mADImageShowList is empty.");
            return;
        }
        int n = nsn.n();
        if (nsn.ag(this.o)) {
            int c2 = eie.c();
            i = nrr.b(this.o);
            int size = this.q.size();
            if (size == 1) {
                healthViewPager.setIsScroll(false);
            } else {
                if (size == 2) {
                    int h = (int) ((nsn.h(this.o) - BaseApplication.getContext().getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e)) - BaseApplication.getContext().getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d));
                    int i5 = ((h - (c2 * 2)) - i) / 2;
                    i4 = (h - c2) - i5;
                    healthViewPager.setIsScroll(false);
                    r4 = i5;
                } else if (size >= 3) {
                    r4 = (n - c2) / 2;
                    healthViewPager.setIsScroll(true);
                    i4 = r4;
                } else {
                    LogUtil.h("Section16_9Card_01", "resizeViewPager() imageListSize <= 0.");
                }
                i3 = i4;
                i2 = r4;
            }
            i4 = 0;
            i = 0;
            i3 = i4;
            i2 = r4;
        } else {
            int size2 = this.q.size();
            int dimension = (int) this.o.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
            int intValue = ((Integer) this.p.first).intValue();
            int intValue2 = ((Integer) this.p.second).intValue();
            r4 = size2 > 1 ? ((Integer) this.p.first).intValue() : 0;
            ccq.a(size2, healthViewPager, 1);
            i = intValue2 + dimension + intValue;
            i2 = r4;
            i3 = i2;
        }
        int i6 = i;
        LogUtil.a("Section16_9Card_01", "resizeViewPager() pageMargin = ", Integer.valueOf(i6), ", startPadding = ", Integer.valueOf(i2), ", endPadding = ", Integer.valueOf(i3));
        ccq.d(healthViewPager, i6, "Section16_9Card_01", i2, i3, 0);
    }

    private void m() {
        HealthDotsPageIndicator healthDotsPageIndicator = this.k;
        if (healthDotsPageIndicator != null) {
            healthDotsPageIndicator.a(4000);
        }
    }

    private void c() {
        HealthDotsPageIndicator healthDotsPageIndicator = this.k;
        if (healthDotsPageIndicator != null) {
            healthDotsPageIndicator.c();
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section16_9Card_01";
    }
}
