package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.Section4_5Card_01;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eew;
import defpackage.efb;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nru;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section4_5Card_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private String f2657a;
    private int aa;
    private View ab;
    private Context ac;
    private ImageView ad;
    private View ae;
    private final ViewOutlineProvider af;
    private final b ag;
    private View ah;
    private ImageView ai;
    private RelativeLayout aj;
    private ImageView ak;
    private ImageView al;
    private List<Object> am;
    private RelativeLayout an;
    private ImageView ao;
    private View ap;
    private List<Object> aq;
    private String ar;
    private boolean as;
    private View at;
    private ImageView au;
    private HealthTextView av;
    private LinearLayout aw;
    private LinearLayout ax;
    private LinearLayout ay;
    private HealthTextView az;
    private HealthImageView b;
    private HealthTextView ba;
    private LinearLayout bb;
    private ImageView bc;
    private View bd;
    private SpannableStringBuilder be;
    private StringBuffer bf;
    private HealthTextView bg;
    private HealthTextView bh;
    private LinearLayout bi;
    private HealthTextView bj;
    private View bk;
    private HealthTextView bl;
    private int c;
    private int d;
    private int e;
    private HealthButton f;
    private View g;
    private List<Object> h;
    private HealthImageView i;
    private HealthTextView j;
    private HealthTextView k;
    private ImageView l;
    private HealthColumnSystem m;
    private ImageView n;
    private HealthButton o;
    private ImageView p;
    private boolean q;
    private boolean r;
    private List<Object> s;
    private boolean t;
    private HealthTextView u;
    private LinearLayout v;
    private List<Object> w;
    private HealthTextView x;
    private HealthTextView y;
    private RelativeLayout z;

    public Section4_5Card_01(Context context) {
        this(context, null);
        this.ac = context;
    }

    public Section4_5Card_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section4_5Card_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ag = new b(this);
        this.t = false;
        this.q = false;
        this.ar = "";
        this.f2657a = "";
        this.d = 6;
        this.e = 2;
        this.c = 16;
        this.aa = -1;
        this.af = new ViewOutlineProvider() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (outline == null || view == null) {
                    return;
                }
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), nrf.d);
            }
        };
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void overrideParamsByOnlineData(SectionBean sectionBean, HashMap<String, Object> hashMap) {
        List<String> d = sectionBean.d();
        if (koq.b(d)) {
            return;
        }
        hashMap.put("BACKGROUND", d);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("Section_Section4_5Card_01", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_Section4_5Card_01", "no need to bind");
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "default gone");
        Object d = nru.d(hashMap, "SECTION_STYLE", (Object) null);
        boolean d2 = nru.d((Map) hashMap, "RIGHT_TOP_ICON_SHOW", false);
        if (d instanceof String) {
            String str = (String) d;
            e(str, d2);
            u(str);
        }
        h(nru.d(hashMap, "BACKGROUND", Object.class, new ArrayList()));
        c(nru.d(hashMap, "MARKER", (Object) null));
        n(nru.d(hashMap, "MARKER", (Object) null));
        d(nru.d(hashMap, "RIGHT_TOP_IMAGE", (Object) null), nru.d(hashMap, "RIGHT_TOP_IMAGE_DESC", (Object) null));
        p(nru.d(hashMap, "RIGHT_TOP_IMAGE_SECOND", (Object) null));
        q(nru.d(hashMap, "RIGHT_TOP_SECOND_BUBBLE", (Object) null));
        y(nru.d(hashMap, "RIGHT_TOP_TITLE", (Object) null));
        w(nru.d(hashMap, "RIGHT_TOP_TITLE_SECOND", (Object) null));
        a();
        b(nru.d(hashMap, "ACCUMULATED_DURATION", (Object) null));
        g(nru.d(hashMap, "ACCUMULATED_DURATION", (Object) null));
        i(nru.d(hashMap, "ACCUMULATED_DURATION_TEXT", (Object) null));
        e(nru.d(hashMap, "ACCUMULATED_DURATION_TEXT", (Object) null));
        l(nru.d(hashMap, "GUIDE_BUBBLE_TEXT", Object.class, new ArrayList()));
        f(nru.d(hashMap, "CANCEL_GUIDE_BUBBLE_TEXT", Object.class, new ArrayList()));
        m(Boolean.valueOf(nru.d((Map) hashMap, "IS_GOAL_BUTTON_SHOW", true)));
        o(nru.d(hashMap, "GOAL_BUTTON", (Object) null));
        j(nru.a(hashMap, "CLICK_EVENT_LISTENER", null));
        d(nru.b(hashMap, "ADVERTISEMENT_TEXT", ""));
        if (this.an != null && this.j != null) {
            d(hashMap);
        }
        if (this.bd != null) {
            s(nru.d(hashMap, "LEFT_IMAGEVIEW", Object.class, new ArrayList()));
            c(nru.d(hashMap, "MIDDLE_IMAGEVIEW", Object.class, new ArrayList()), nru.d(hashMap, "MIDDLE_IMAGEVIEW_DESC", (Object) null));
            r(nru.d(hashMap, "RIGHT_IMAGEVIEW", Object.class, new ArrayList()));
            t(nru.d(hashMap, "RIGHT_LAYOUT_VISIBLE", (Object) null));
        }
        setContentDescription(jcf.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentDescription(boolean z) {
        HealthTextView healthTextView;
        String str;
        View view = this.ah;
        if (view == null || (healthTextView = this.bl) == null) {
            ReleaseLogUtil.a("Section_Section4_5Card_01", "setContentDescription mSubtitleClickEventFitnessAccessibility ", view, " titleFitness ", this.bl);
            return;
        }
        if (!z) {
            view.setVisibility(8);
            return;
        }
        int i = this.aa;
        if (i >= 0) {
            str = nsf.a(R.plurals._2130903527_res_0x7f0301e7, i, UnitUtil.e(i, 1, 0));
        } else {
            CharSequence text = healthTextView.getText();
            if (TextUtils.isEmpty(text)) {
                ReleaseLogUtil.a("Section_Section4_5Card_01", "setContentDescription durationMinuteText ", text);
                return;
            }
            HealthTextView healthTextView2 = this.bg;
            str = text.toString() + ((Object) (healthTextView2 != null ? healthTextView2.getText() : ""));
        }
        this.ah.setVisibility(0);
        jcf.bEz_(this.ah, str);
    }

    private void d(HashMap<String, Object> hashMap) {
        String a2 = a(nru.d(hashMap, "FITNESS_DURATION", (Object) null));
        String d = d(nru.d(hashMap, "FITNESS_DURATION_UNIT", (Object) null));
        String d2 = d(nru.d(hashMap, "FITNESS_DURATION_TEXT", (Object) null));
        String a3 = a(nru.d(hashMap, "ACCUMULATED_DAYS", (Object) null));
        String d3 = d(nru.d(hashMap, "ACCUMULATED_DAYS_UNIT", (Object) null));
        String d4 = d(nru.d(hashMap, "ACCUMULATED_DAYS_TEXT", (Object) null));
        String a4 = a(nru.d(hashMap, "ACCUMULATED_TIMES", (Object) null));
        String d5 = d(nru.d(hashMap, "ACCUMULATED_TIMES_UNIT", (Object) null));
        e(a2, d, d2, d4);
        d(a3, d3, a4, d5);
        this.j.setText(this.be);
        k(nru.d(hashMap, "BUTTON_TEXT", (Object) null));
    }

    private void d(String str, String str2, String str3, String str4) {
        this.bf.append(str);
        this.be.append((CharSequence) str);
        String stringBuffer = this.bf.toString();
        this.ar = stringBuffer;
        int indexOf = this.ar.indexOf(str, stringBuffer.indexOf(this.f2657a));
        if (indexOf >= 0) {
            this.be.setSpan(new TextAppearanceSpan(this.ac, R.style.fitness_basic_textview), indexOf, str.length() + indexOf, 17);
            this.be.setSpan(new eew(nsn.c(this.ac, this.e)), indexOf, str.length() + indexOf, 17);
        }
        this.bf.append(str2);
        this.be.append((CharSequence) str2);
        String stringBuffer2 = this.bf.toString();
        this.ar = stringBuffer2;
        int indexOf2 = stringBuffer2.indexOf(str2);
        if (indexOf2 >= 0) {
            this.be.setSpan(new eew(nsn.c(this.ac, this.d)), indexOf2, str2.length() + indexOf2, 17);
        }
        this.bf.append(str3);
        this.be.append((CharSequence) str3);
        String stringBuffer3 = this.bf.toString();
        this.ar = stringBuffer3;
        int lastIndexOf = stringBuffer3.lastIndexOf(str3);
        if (lastIndexOf >= 0) {
            this.be.setSpan(new TextAppearanceSpan(this.ac, R.style.fitness_basic_textview), lastIndexOf, str3.length() + lastIndexOf, 17);
            this.be.setSpan(new eew(nsn.c(this.ac, this.e)), lastIndexOf, str3.length() + lastIndexOf, 17);
        }
        this.bf.append(str4);
        this.be.append((CharSequence) str4);
    }

    private void e(String str, String str2, String str3, String str4) {
        HealthTextView healthTextView = this.j;
        if (healthTextView != null && !TextUtils.isEmpty(healthTextView.getText())) {
            StringBuffer stringBuffer = this.bf;
            stringBuffer.delete(0, stringBuffer.length());
            SpannableStringBuilder spannableStringBuilder = this.be;
            spannableStringBuilder.delete(0, spannableStringBuilder.length());
        }
        if (this.bf == null) {
            this.bf = new StringBuffer();
        }
        if (this.be == null) {
            this.be = new SpannableStringBuilder(this.ar);
        }
        b(str, str2, str3);
        this.f2657a = str4;
        this.bf.append(str4);
        this.be.append((CharSequence) str4);
        String stringBuffer2 = this.bf.toString();
        this.ar = stringBuffer2;
        int indexOf = stringBuffer2.indexOf(str4);
        if (indexOf >= 0) {
            this.be.setSpan(new eew(nsn.c(this.ac, this.d)), indexOf, str4.length() + indexOf, 17);
        }
    }

    private void b(String str, String str2, String str3) {
        this.bf.append(str3);
        this.be.append((CharSequence) str3);
        String stringBuffer = this.bf.toString();
        this.ar = stringBuffer;
        int indexOf = stringBuffer.indexOf(str3);
        if (indexOf >= 0) {
            this.be.setSpan(new eew(nsn.c(this.ac, this.d)), indexOf, str3.length() + indexOf, 17);
        }
        this.bf.append(str);
        this.be.append((CharSequence) str);
        String stringBuffer2 = this.bf.toString();
        this.ar = stringBuffer2;
        int indexOf2 = stringBuffer2.indexOf(str);
        if (indexOf2 >= 0) {
            this.be.setSpan(new TextAppearanceSpan(this.ac, R.style.fitness_basic_textview), indexOf2, str.length() + indexOf2, 17);
            this.be.setSpan(new eew(nsn.c(this.ac, this.e)), indexOf2, str.length() + indexOf2, 17);
        }
        this.bf.append(str2);
        this.be.append((CharSequence) str2);
        String stringBuffer3 = this.bf.toString();
        this.ar = stringBuffer3;
        int indexOf3 = stringBuffer3.indexOf(str2);
        if (indexOf3 >= 0) {
            this.be.setSpan(new eew(nsn.c(this.ac, this.c)), indexOf3, str2.length() + indexOf3, 17);
        }
    }

    private void b() {
        BitmapDrawable cKn_;
        RelativeLayout relativeLayout = this.an;
        if (relativeLayout == null || this.aj == null) {
            return;
        }
        relativeLayout.setVisibility(0);
        this.aj.setVisibility(8);
        this.f = (HealthButton) this.ae.findViewById(R.id.common_card_button_fitness);
        if (LanguageUtil.bc(getContext()) && (cKn_ = nrz.cKn_(getContext(), R.drawable._2131430137_res_0x7f0b0af9)) != null && this.f != null) {
            cKn_.setBounds(0, 0, cKn_.getMinimumWidth(), cKn_.getMinimumHeight());
            this.f.setCompoundDrawablesRelative(cKn_, null, null, null);
        }
        this.j = (HealthTextView) this.ae.findViewById(R.id.common_courses_data_unit);
    }

    private void e(String str, boolean z) {
        if (BaseSection.OUTDOOR_RUN_STYLE.equals(str) && z) {
            if (this.ap != null) {
                return;
            }
            View inflate = ((ViewStub) this.ae.findViewById(R.id.outdoor_right_top_icon_layout_area)).inflate();
            this.ap = inflate;
            this.bb = (LinearLayout) inflate.findViewById(R.id.right_top_layout);
            this.aw = (LinearLayout) this.ap.findViewById(R.id.right_top_image_layout);
            this.ay = (LinearLayout) this.ap.findViewById(R.id.right_top_image_layout_second);
            this.au = (ImageView) this.ap.findViewById(R.id.right_top_image);
            this.az = (HealthTextView) this.ap.findViewById(R.id.right_top_title);
            this.bc = (ImageView) this.ap.findViewById(R.id.right_top_image_second);
            this.ba = (HealthTextView) this.ap.findViewById(R.id.right_top_title_second);
            this.ab = ((ViewStub) this.ae.findViewById(R.id.outdoor_right_top_bubble)).inflate();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(3, R.id.right_top_layout);
            layoutParams.addRule(21);
            layoutParams.setMargins(0, (int) getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532), 0, 0);
            this.ab.setLayoutParams(layoutParams);
            this.ab.bringToFront();
        } else {
            if (this.at != null) {
                return;
            }
            View inflate2 = ((ViewStub) this.ae.findViewById(R.id.right_top_icon_layout_area)).inflate();
            this.at = inflate2;
            this.bb = (LinearLayout) inflate2.findViewById(R.id.right_top_layout);
            this.aw = (LinearLayout) this.at.findViewById(R.id.right_top_image_layout);
            this.ay = (LinearLayout) this.at.findViewById(R.id.right_top_image_layout_second);
            this.au = (ImageView) this.at.findViewById(R.id.right_top_image);
            this.az = (HealthTextView) this.at.findViewById(R.id.right_top_title);
            this.bc = (ImageView) this.at.findViewById(R.id.right_top_image_second);
            this.ba = (HealthTextView) this.at.findViewById(R.id.right_top_title_second);
            this.ab = ((ViewStub) this.ae.findViewById(R.id.right_top_bubble)).inflate();
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(3, R.id.right_top_layout);
            layoutParams2.setMargins(0, (int) getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532), (int) getResources().getDimension(R.dimen._2131363063_res_0x7f0a04f7), 0);
            layoutParams2.addRule(21);
            this.ab.setLayoutParams(layoutParams2);
            this.ab.bringToFront();
        }
        this.au.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Section4_5Card_01.this.au.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int[] iArr = new int[2];
                Section4_5Card_01.this.getKnitFragment().getHealthScrollView().getLocationOnScreen(iArr);
                LogUtil.a("Section_Section4_5Card_01", "scrollView x =", Integer.valueOf(iArr[0]));
                LogUtil.a("Section_Section4_5Card_01", "scrollView y =", Integer.valueOf(iArr[1]));
                int[] iArr2 = new int[2];
                Section4_5Card_01.this.ae.getLocationOnScreen(iArr2);
                LogUtil.a("Section_Section4_5Card_01", "mainView x =", Integer.valueOf(iArr2[0]));
                LogUtil.a("Section_Section4_5Card_01", "mainView y =", Integer.valueOf(iArr2[1]));
                int[] iArr3 = {iArr2[0], (iArr2[1] + iArr[1]) - Section4_5Card_01.this.au.getHeight()};
                LogUtil.a("Section_Section4_5Card_01", "newPoint x =", Integer.valueOf(iArr3[0]));
                LogUtil.a("Section_Section4_5Card_01", "newPoint y =", Integer.valueOf(iArr3[1]));
                Section4_5Card_01.this.getKnitFragment().setPuppetsView(iArr3);
            }
        });
    }

    private void c() {
        if (this.bd != null) {
            return;
        }
        RelativeLayout relativeLayout = this.aj;
        if (relativeLayout != null && this.an != null) {
            relativeLayout.setVisibility(0);
            this.an.setVisibility(8);
        }
        View inflate = ((ViewStub) this.ae.findViewById(R.id.round_btn_area)).inflate();
        this.bd = inflate;
        this.v = (LinearLayout) inflate.findViewById(R.id.section_img_sport_entrance_warmup);
        this.p = (ImageView) this.bd.findViewById(R.id.section_img_sport_entrance_warmup_icon);
        this.u = (HealthTextView) this.bd.findViewById(R.id.section_img_sport_entrance_warmup_text);
        this.ak = (ImageView) this.bd.findViewById(R.id.section_img_sport_entrance_begin);
        if (LanguageUtil.bc(getContext())) {
            this.ak.setRotationY(180.0f);
        }
        this.ax = (LinearLayout) this.bd.findViewById(R.id.section_img_sport_entrance_music);
        this.ao = (ImageView) this.bd.findViewById(R.id.section_img_sport_entrance_music_icon);
        this.av = (HealthTextView) this.bd.findViewById(R.id.section_img_sport_entrance_music_text);
        this.g = this.bd.findViewById(R.id.goal_card_area);
        this.o = (HealthButton) this.bd.findViewById(R.id.goal_card_button);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void u(Object obj) {
        char c;
        LogUtil.a("Section_Section4_5Card_01", "start to set section style, current is " + obj);
        String str = (String) obj;
        str.hashCode();
        switch (str.hashCode()) {
            case -1822123262:
                if (str.equals(BaseSection.YOGA_STYLE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1706133581:
                if (str.equals(BaseSection.BIKE_STYLE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1485318245:
                if (str.equals(BaseSection.WALK_STYLE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -976583727:
                if (str.equals(BaseSection.INDOOR_RUN_STYLE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 617956442:
                if (str.equals(BaseSection.FITNESS_STYLE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1310151527:
                if (str.equals(BaseSection.CLIMB_STYLE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1335403056:
                if (str.equals(BaseSection.ROPE_SKIPPING_STYLE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1409752685:
                if (str.equals(BaseSection.HIKE_STYLE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1479600666:
                if (str.equals(BaseSection.OUTDOOR_RUN_STYLE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                LogUtil.a("Section_Section4_5Card_01", "current style is yoga");
                b();
                setDarkModeColor(false);
                break;
            case 1:
                LogUtil.a("Section_Section4_5Card_01", "current style is bike");
                c();
                setDarkModeColor(true);
                break;
            case 2:
                LogUtil.a("Section_Section4_5Card_01", "current style is walk");
                c();
                setDarkModeColor(true);
                break;
            case 3:
                LogUtil.a("Section_Section4_5Card_01", "current style is indoor run");
                c();
                setDarkModeColor(false);
                break;
            case 4:
                LogUtil.a("Section_Section4_5Card_01", "current style is fitness");
                b();
                setDarkModeColor(false);
                break;
            case 5:
                LogUtil.a("Section_Section4_5Card_01", "current style is climb");
                c();
                setDarkModeColor(true);
                nsy.cMA_(this.v, 8);
                break;
            case 6:
                LogUtil.a("Section_Section4_5Card_01", "current style is rope skipping");
                c();
                setDarkModeColor(false);
                break;
            case 7:
                LogUtil.a("Section_Section4_5Card_01", "current style is hike");
                c();
                setDarkModeColor(true);
                nsy.cMA_(this.v, 8);
                break;
            case '\b':
                LogUtil.a("Section_Section4_5Card_01", "current style is outdoor run");
                c();
                setDarkModeColor(true);
                break;
            default:
                LogUtil.a("Section_Section4_5Card_01", "unknown sport style");
                break;
        }
    }

    private void h(Object obj) {
        if (obj instanceof List) {
            List<Object> list = (List) obj;
            this.h = list;
            if (list.size() == 2) {
                if (nsn.ag(this.ac)) {
                    LogUtil.a("Section_Section4_5Card_01", "start to loadRoundRectangle tahitiBackground");
                    ac(this.h.get(1));
                } else {
                    LogUtil.a("Section_Section4_5Card_01", "start to loadRoundRectangle background");
                    ac(this.h.get(0));
                }
            }
        }
    }

    private void ac(Object obj) {
        RelativeLayout relativeLayout = this.aj;
        if (relativeLayout == null || this.an == null || obj == null) {
            return;
        }
        if (relativeLayout.getVisibility() == 0) {
            x(obj);
        }
        if (this.an.getVisibility() == 0) {
            v(obj);
        }
    }

    public void c(Object obj) {
        if (this.ai == null || !(obj instanceof Integer)) {
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "start to set marker");
        this.ai.setImageResource(((Integer) obj).intValue());
    }

    private void n(Object obj) {
        if (this.al == null || !(obj instanceof Integer)) {
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "start to set marker");
        this.al.setImageResource(((Integer) obj).intValue());
    }

    private void d(Object obj, Object obj2) {
        ImageView imageView = this.au;
        if (imageView != null && (obj instanceof Integer) && obj != null) {
            LogUtil.a("Section_Section4_5Card_01", "start to set right top image");
            this.au.setImageResource(((Integer) obj).intValue());
            nsy.cMA_(this.au, 0);
            this.t = true;
        } else {
            nsy.cMA_(imageView, 8);
        }
        if (obj2 instanceof String) {
            jcf.bEz_(this.au, (String) obj2);
        }
    }

    private void p(Object obj) {
        ImageView imageView = this.bc;
        if (imageView != null && (obj instanceof Integer) && obj != null) {
            LogUtil.a("Section_Section4_5Card_01", "start to set right top imageSecond");
            this.bc.setImageResource(((Integer) obj).intValue());
            nsy.cMA_(this.bc, 0);
            this.q = true;
            return;
        }
        this.q = false;
        nsy.cMA_(imageView, 8);
    }

    private void q(Object obj) {
        final int a2 = SharedPreferenceManager.a(Integer.toString(10000), "is_not_avail_bolt_tag", 0);
        LogUtil.a("Section_Section4_5Card_01", "start to set right top bubble，", Integer.valueOf(a2));
        View view = this.ab;
        if (view != null && (obj instanceof String) && a2 < 1) {
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.tip_content);
            HealthTextView healthTextView2 = (HealthTextView) this.ab.findViewById(R.id.ok_text);
            if (nsn.p()) {
                healthTextView.setTextSize(2, 10.0f);
                healthTextView2.setTextSize(2, 10.0f);
            }
            healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: efr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    Section4_5Card_01.this.air_(a2, view2);
                }
            });
            healthTextView.setText((String) obj);
            nsy.cMA_(this.ab, 0);
            return;
        }
        nsy.cMA_(view, 8);
    }

    public /* synthetic */ void air_(int i, View view) {
        View view2 = this.ab;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        SharedPreferenceManager.b(Integer.toString(10000), "is_not_avail_bolt_tag", i + 1);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        if (this.t) {
            nsy.cMA_(this.aw, 0);
        } else {
            nsy.cMA_(this.aw, 8);
        }
        if (this.q) {
            nsy.cMA_(this.ay, 0);
        } else {
            nsy.cMA_(this.ay, 8);
        }
        if (this.t || this.q) {
            nsy.cMA_(this.bb, 0);
        } else {
            nsy.cMA_(this.bb, 8);
        }
    }

    private void y(Object obj) {
        HealthTextView healthTextView = this.az;
        if (healthTextView != null && (obj instanceof String) && obj != null) {
            LogUtil.a("Section_Section4_5Card_01", "start to set right top title");
            this.az.setText(String.valueOf(obj));
            this.az.setVisibility(0);
        } else if (healthTextView == null) {
            LogUtil.h("Section_Section4_5Card_01", "rightTopTitle==null");
        } else {
            healthTextView.setVisibility(8);
        }
    }

    private void w(Object obj) {
        HealthTextView healthTextView = this.ba;
        if (healthTextView == null) {
            return;
        }
        if ((obj instanceof String) && obj != null) {
            LogUtil.a("Section_Section4_5Card_01", "start to set right top titleSecond");
            this.ba.setText(String.valueOf(obj));
            this.ba.setVisibility(0);
            return;
        }
        healthTextView.setVisibility(8);
    }

    private void g(Object obj) {
        LogUtil.a("Section_Section4_5Card_01", "handleFitnessAccumulatedDuration subViewTypeValue ", obj, " titleFitness ", this.bl);
        if (this.bl != null && (obj instanceof Integer)) {
            int intValue = ((Integer) obj).intValue();
            this.aa = intValue;
            this.bl.setText(UnitUtil.e(intValue, 1, 0));
        }
        HealthTextView healthTextView = this.bl;
        if (healthTextView == null || !(obj instanceof String)) {
            return;
        }
        healthTextView.setText(String.valueOf(obj));
    }

    private void b(Object obj) {
        if (this.bj != null && (obj instanceof Integer)) {
            LogUtil.a("Section_Section4_5Card_01", "start to set button title");
            this.bj.setText(UnitUtil.e(((Integer) obj).intValue(), 1, 0));
        }
        if (this.bj == null || !(obj instanceof String)) {
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "start to set button title");
        this.bj.setText(String.valueOf(obj));
    }

    private void i(Object obj) {
        if (this.bg == null || !(obj instanceof String)) {
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "start to set button subtitleFitness");
        this.bg.setText(String.valueOf(obj));
    }

    private void e(Object obj) {
        if (this.bh == null || !(obj instanceof String)) {
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "start to set button subtitle");
        this.bh.setText(String.valueOf(obj));
    }

    private String a(Object obj) {
        return (!(obj instanceof Integer) || obj == null) ? "" : UnitUtil.e(((Integer) obj).intValue(), 1, 0);
    }

    private String d(Object obj) {
        return (!(obj instanceof String) || obj == null) ? "" : (String) obj;
    }

    private void l(Object obj) {
        if (this.k == null || !(obj instanceof List)) {
            return;
        }
        List list = (List) obj;
        if (list.size() == 2) {
            LogUtil.a("Section_Section4_5Card_01", "start to set text guide bubble");
            Object obj2 = list.get(0);
            Object obj3 = list.get(1);
            if ((obj2 instanceof String) && TextUtils.equals("true", (String) obj2) && (obj3 instanceof String)) {
                this.k.setText(String.valueOf(obj3));
                this.k.setVisibility(0);
            } else {
                this.k.setVisibility(8);
            }
        }
    }

    private void f(Object obj) {
        if (this.z == null || !(obj instanceof List)) {
            return;
        }
        List<Object> list = (List) obj;
        if (list.size() == 4) {
            LogUtil.a("Section_Section4_5Card_01", "start to set cancel guide bubble");
            this.w = list;
            Object obj2 = list.get(0);
            Object obj3 = this.w.get(1);
            if ((obj2 instanceof String) && TextUtils.equals("true", (String) obj2) && (obj3 instanceof String)) {
                LogUtil.a("Section_Section4_5Card_01", "isFirstLoading");
                this.y.setText(String.valueOf(obj3));
                StateListDrawable aip_ = aip_(((Integer) this.w.get(2)).intValue(), ((Integer) this.w.get(3)).intValue());
                if (aip_ != null) {
                    this.ad.setBackground(aip_);
                }
                this.z.setVisibility(0);
                return;
            }
            this.z.setVisibility(8);
        }
    }

    private void m(Object obj) {
        if (this.g == null || !(obj instanceof Boolean)) {
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "start to set goal button visiblity");
        this.g.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
    }

    private void o(Object obj) {
        if (this.o == null || !(obj instanceof String)) {
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "start to set button goalButton");
        this.o.setText(String.valueOf(obj));
        if (nsn.r()) {
            ViewGroup.LayoutParams layoutParams = this.o.getLayoutParams();
            layoutParams.height = -2;
            this.o.setLayoutParams(layoutParams);
        }
    }

    private void k(Object obj) {
        if (this.f == null || !(obj instanceof String)) {
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "start to set buttonFitness text");
        this.f.setText(String.valueOf(obj));
    }

    private void s(Object obj) {
        if (obj instanceof List) {
            List<Object> list = (List) obj;
            this.s = list;
            if (list.size() > 0 && (this.s.get(0) instanceof Integer)) {
                LogUtil.a("Section_Section4_5Card_01", "start to set left imageview");
                if (LanguageUtil.m(this.ac)) {
                    this.u.setVisibility(0);
                    this.p.setVisibility(0);
                    i();
                    StateListDrawable aip_ = aip_(((Integer) this.s.get(0)).intValue(), ((Integer) this.s.get(1)).intValue());
                    if (aip_ != null) {
                        this.v.setBackground(aip_);
                    }
                    if (this.s.get(2) instanceof Integer) {
                        this.p.setImageDrawable(this.ac.getDrawable(((Integer) this.s.get(2)).intValue()));
                    }
                    if (this.s.get(3) instanceof Integer) {
                        this.u.setText(this.ac.getResources().getString(((Integer) this.s.get(3)).intValue()));
                        return;
                    }
                    return;
                }
                this.u.setVisibility(8);
                this.v.setVisibility(8);
                this.u.setVisibility(8);
                return;
            }
            this.v.setVisibility(8);
            this.p.setVisibility(8);
            this.u.setVisibility(8);
        }
    }

    private void c(Object obj, Object obj2) {
        if (obj instanceof List) {
            List<Object> list = (List) obj;
            this.am = list;
            if (list.size() == 2 && (this.am.get(0) instanceof Integer)) {
                LogUtil.a("Section_Section4_5Card_01", "start to set middle button");
                StateListDrawable aip_ = aip_(((Integer) this.am.get(0)).intValue(), ((Integer) this.am.get(1)).intValue());
                if (aip_ != null) {
                    this.ak.setBackground(aip_);
                }
            } else if (this.am.size() == 1 && (this.am.get(0) instanceof String)) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ak.getLayoutParams();
                layoutParams.width = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362871_res_0x7f0a0437);
                layoutParams.height = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362863_res_0x7f0a042f);
                this.ak.setLayoutParams(layoutParams);
                nrf.cHI_((String) this.am.get(0), this.ak, 0);
            }
        }
        if (obj2 instanceof String) {
            this.ak.setContentDescription((String) obj2);
        }
    }

    private void r(Object obj) {
        if (obj instanceof List) {
            List<Object> list = (List) obj;
            this.aq = list;
            if (list.size() > 0 && (this.aq.get(0) instanceof Integer)) {
                LogUtil.a("Section_Section4_5Card_01", "start to set right imageview");
                this.ax.setVisibility(0);
                this.ao.setVisibility(0);
                if (LanguageUtil.m(this.ac)) {
                    this.av.setVisibility(0);
                } else {
                    this.av.setVisibility(8);
                }
                j();
                StateListDrawable aip_ = aip_(((Integer) this.aq.get(0)).intValue(), ((Integer) this.aq.get(1)).intValue());
                if (aip_ != null) {
                    this.ax.setBackground(aip_);
                }
                if (this.aq.get(2) instanceof Integer) {
                    this.ao.setImageDrawable(this.ac.getDrawable(((Integer) this.aq.get(2)).intValue()));
                }
                if (this.aq.get(3) instanceof Integer) {
                    this.av.setText(this.ac.getResources().getString(((Integer) this.aq.get(3)).intValue()));
                    return;
                }
                return;
            }
            if (this.aq.size() > 0 && (this.aq.get(0) instanceof Bitmap)) {
                this.ax.setVisibility(0);
                this.ao.setVisibility(8);
                this.av.setVisibility(8);
                h();
                this.ax.setBackground(new BitmapDrawable(this.ac.getResources(), (Bitmap) this.aq.get(0)));
                return;
            }
            this.ax.setVisibility(8);
            this.ao.setVisibility(8);
            this.av.setVisibility(8);
        }
    }

    private void d(String str) {
        nsy.cMs_(this.x, str, true);
    }

    private void t(Object obj) {
        if (obj instanceof Integer) {
            LogUtil.a("Section_Section4_5Card_01", "start to set rightLayout visibility");
            this.ax.setVisibility(((Integer) obj).intValue());
        }
    }

    private void j(Object obj) {
        LogUtil.a("Section_Section4_5Card_01", "start to set click event");
        setClickListenerEvent(obj);
    }

    private void v(Object obj) {
        HealthImageView healthImageView = this.i;
        if (healthImageView == null) {
            return;
        }
        if (obj instanceof Integer) {
            healthImageView.setImageResource(((Integer) obj).intValue());
        }
        if (obj instanceof String) {
            nrf.c(this.i, (String) obj, nrf.d, 0, 0);
        }
    }

    private void x(Object obj) {
        HealthImageView healthImageView = this.b;
        if (healthImageView == null) {
            return;
        }
        if (obj instanceof Integer) {
            healthImageView.setImageResource(((Integer) obj).intValue());
        }
        if (obj instanceof String) {
            nrf.c(this.b, (String) obj, nrf.d, 0, 0);
        }
    }

    private void setClickListenerEvent(Object obj) {
        if (obj instanceof OnClickSectionListener) {
            final OnClickSectionListener onClickSectionListener = (OnClickSectionListener) obj;
            LogUtil.a("Section_Section4_5Card_01", "start to set button click event");
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("Section_Section4_5Card_01", "set subtitleClickEvent onClick");
                    onClickSectionListener.onClick("ACCUMULATED_DURATION_CLICK_VIEW");
                    ViewClickInstrumentation.clickOnView(view);
                }
            };
            nsy.cMn_(this.bk, onClickListener);
            nsy.cMn_(this.bl, onClickListener);
            nsy.cMn_(this.bi, onClickListener);
            nsy.cMn_(this.ah, onClickListener);
            setFitnessButtonClick(onClickSectionListener);
            LinearLayout linearLayout = this.aw;
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.10
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("Section_Section4_5Card_01", "set rightTopImageLayoutFirst onClick");
                        onClickSectionListener.onClick("RIGHT_TOP_IMAGE");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            ImageView imageView = this.bc;
            if (imageView != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("Section_Section4_5Card_01", "set rightTopImageSecond onClick");
                        onClickSectionListener.onClick("RIGHT_TOP_IMAGE_SECOND");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            HealthTextView healthTextView = this.k;
            if (healthTextView != null) {
                healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("Section_Section4_5Card_01", "set guideBubbleText onClick");
                        onClickSectionListener.onClick("GUIDE_BUBBLE_TEXT");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            ImageView imageView2 = this.ad;
            if (imageView2 != null) {
                imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("Section_Section4_5Card_01", "set cancelBubbleText onClick");
                        onClickSectionListener.onClick("CANCEL_GUIDE_BUBBLE_TEXT");
                        if (Section4_5Card_01.this.z != null) {
                            Section4_5Card_01.this.z.setVisibility(8);
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            setLayoutClickListener(onClickSectionListener);
        }
    }

    private StateListDrawable aip_(int i, int i2) {
        LogUtil.a("Section_Section4_5Card_01", "addStateDrawable");
        Resources resources = this.ac.getResources();
        if (resources != null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable drawable = i == -1 ? null : resources.getDrawable(i, null);
            Drawable drawable2 = i2 != -1 ? resources.getDrawable(i2, null) : null;
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, drawable2);
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, drawable2);
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, drawable);
            stateListDrawable.addState(new int[0], drawable);
            return stateListDrawable;
        }
        LogUtil.a("Section_Section4_5Card_01", "get StateListDrawable fail");
        return null;
    }

    private void setDarkModeColor(boolean z) {
        if (z) {
            int i = nrt.a(this.ac) ? R.drawable._2131431570_res_0x7f0b1092 : R.drawable._2131431569_res_0x7f0b1091;
            this.l.setImageResource(i);
            this.n.setImageResource(i);
        } else {
            int color = this.ac.getResources().getColor(R.color._2131299239_res_0x7f090ba7);
            this.bj.setTextColor(color);
            this.bl.setTextColor(color);
            this.bh.setTextColor(color);
            this.bg.setTextColor(color);
        }
    }

    private void d() {
        if (this.ae == null) {
            LogUtil.h("Section_Section4_5Card_01", "initView mainView is null, start to inflate");
            this.ae = LayoutInflater.from(this.ac).inflate(R.layout.section4_5card_layout, (ViewGroup) this, false);
        }
        if (this.m == null) {
            this.m = new HealthColumnSystem(this.ac, 1);
        }
        aiq_(this.ae);
        g();
        e();
        Typeface createFromAsset = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.bj.setTypeface(createFromAsset);
        this.bl.setTypeface(createFromAsset);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_Section4_5Card_01", "loadView");
        this.ac = context;
        d();
        if (efb.g()) {
            getKnitFragment().setRecyclerViewDescendantFocusability(393216);
        }
        return this.ae;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.common_middle_click_view || id == R.id.common_middle_click_view_fitness || id == R.id.common_middle_fitness_accessibility) {
            onClick("ACCUMULATED_DURATION_CLICK_VIEW");
        } else if (id == R.id.common_card_button || id == R.id.common_card_button_fitness) {
            onClick("BUTTON_TEXT");
        } else if (id == R.id.auxiliary_card_button) {
            onClick("AUXILIARY_BUTTON_TEXT");
        } else {
            LogUtil.a("Section_Section4_5Card_01", "onClick viewId ", Integer.valueOf(id));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        List<Object> list = this.h;
        if (list == null || list.size() != 2) {
            return;
        }
        if (nsn.ag(this.ac)) {
            LogUtil.a("Section_Section4_5Card_01", "start to loadRoundRectangle tahitiBackground");
            ac(this.h.get(1));
        } else {
            LogUtil.a("Section_Section4_5Card_01", "start to loadRoundRectangle background");
            ac(this.h.get(0));
        }
    }

    private void i() {
        float dimension;
        if (this.v.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
            layoutParams.height = nsn.c(this.ac, 80.0f);
            layoutParams.width = nsn.c(this.ac, 80.0f);
            if (nsn.ag(this.ac)) {
                dimension = nsn.c(this.ac, 56.0f);
            } else {
                dimension = getResources().getDimension(R.dimen._2131362566_res_0x7f0a0306);
            }
            layoutParams.setMarginEnd((int) dimension);
            layoutParams.bottomMargin = nsn.c(this.ac, 14.0f);
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "rightLayout type error");
    }

    private void j() {
        float dimension;
        if (this.ax.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ax.getLayoutParams();
            layoutParams.height = nsn.c(this.ac, 80.0f);
            layoutParams.width = nsn.c(this.ac, 80.0f);
            if (nsn.ag(this.ac)) {
                dimension = nsn.c(this.ac, 56.0f);
            } else {
                dimension = getResources().getDimension(R.dimen._2131362566_res_0x7f0a0306);
            }
            layoutParams.setMarginStart((int) dimension);
            layoutParams.bottomMargin = nsn.c(this.ac, 14.0f);
            return;
        }
        LogUtil.a("Section_Section4_5Card_01", "rightLayout type error");
    }

    private void h() {
        float dimension;
        if (this.ax.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ax.getLayoutParams();
            layoutParams.width = nsn.c(this.ac, 56.0f);
            layoutParams.height = nsn.c(this.ac, 56.0f);
            if (nsn.ag(this.ac)) {
                dimension = nsn.c(this.ac, 56.0f);
            } else {
                dimension = getResources().getDimension(R.dimen._2131362566_res_0x7f0a0306);
            }
            layoutParams.setMarginStart(((int) dimension) + nsn.c(this.ac, 12.0f));
            layoutParams.bottomMargin = nsn.c(this.ac, 28.0f);
            this.ax.setLayoutParams(layoutParams);
        }
    }

    private void e() {
        if (LanguageUtil.v(this.ac)) {
            this.bh.setTextSize(0, nsn.c(this.ac, 12.0f));
            this.bg.setTextSize(0, nsn.c(this.ac, 12.0f));
        }
    }

    private void setLayoutClickListener(final OnClickSectionListener onClickSectionListener) {
        HealthButton healthButton = this.o;
        if (healthButton != null) {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("Section_Section4_5Card_01", "set goalButton onClick");
                    onClickSectionListener.onClick("GOAL_BUTTON");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        LinearLayout linearLayout = this.v;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onClickSectionListener.onClick("LEFT_IMAGEVIEW");
                    if (Section4_5Card_01.this.v != null && Section4_5Card_01.this.s != null && Section4_5Card_01.this.s.size() >= 2) {
                        LogUtil.a("Section_Section4_5Card_01", "start to change left button image");
                        if (Section4_5Card_01.this.r || Section4_5Card_01.this.s.get(1) == null || !(Section4_5Card_01.this.s.get(1) instanceof Integer)) {
                            if (Section4_5Card_01.this.r && Section4_5Card_01.this.s.get(0) != null && (Section4_5Card_01.this.s.get(0) instanceof Integer)) {
                                Section4_5Card_01.this.r = false;
                            } else {
                                LogUtil.a("Section_Section4_5Card_01", "left button setImageResource fail");
                            }
                        } else {
                            Section4_5Card_01.this.r = true;
                        }
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        ImageView imageView = this.ak;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.15
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("Section_Section4_5Card_01", "set middleButton onClick");
                    onClickSectionListener.onClick("MIDDLE_IMAGEVIEW");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        setRightClick(onClickSectionListener);
    }

    private void setRightClick(final OnClickSectionListener onClickSectionListener) {
        LinearLayout linearLayout = this.ax;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onClickSectionListener.onClick("RIGHT_IMAGEVIEW");
                    if (Section4_5Card_01.this.ax != null && Section4_5Card_01.this.aq != null && Section4_5Card_01.this.aq.size() >= 2) {
                        LogUtil.a("Section_Section4_5Card_01", "start to change right button image");
                        if (Section4_5Card_01.this.as || Section4_5Card_01.this.aq.get(1) == null || !(Section4_5Card_01.this.aq.get(1) instanceof Integer)) {
                            if (Section4_5Card_01.this.as && Section4_5Card_01.this.aq.get(0) != null && (Section4_5Card_01.this.aq.get(0) instanceof Integer)) {
                                Section4_5Card_01.this.as = false;
                            } else {
                                LogUtil.a("Section_Section4_5Card_01", "right button setImageResource fail");
                            }
                        } else {
                            Section4_5Card_01.this.as = true;
                        }
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void setFitnessButtonClick(final OnClickSectionListener onClickSectionListener) {
        HealthButton healthButton = this.f;
        if (healthButton != null) {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section4_5Card_01.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("Section_Section4_5Card_01", "set buttonFitness onClick");
                    onClickSectionListener.onClick("BUTTON_TEXT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void aiq_(View view) {
        this.aj = (RelativeLayout) view.findViewById(R.id.normal_view);
        this.an = (RelativeLayout) view.findViewById(R.id.normal_view_fitness);
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.common_background);
        this.b = healthImageView;
        healthImageView.setOutlineProvider(this.af);
        this.b.setClipToOutline(true);
        HealthImageView healthImageView2 = (HealthImageView) view.findViewById(R.id.common_background_fitness);
        this.i = healthImageView2;
        healthImageView2.setOutlineProvider(this.af);
        this.i.setClipToOutline(true);
        if (LanguageUtil.bc(getContext())) {
            this.b.setRotationY(180.0f);
            this.i.setRotationY(180.0f);
        }
        this.bj = (HealthTextView) view.findViewById(R.id.common_card_title);
        this.bl = (HealthTextView) view.findViewById(R.id.common_card_title_fitness);
        this.bh = (HealthTextView) view.findViewById(R.id.common_card_subtitle);
        this.bg = (HealthTextView) view.findViewById(R.id.common_card_subtitle_fitness);
        this.l = (ImageView) view.findViewById(R.id.common_accumulated_duration_arrow);
        this.n = (ImageView) view.findViewById(R.id.common_accumulated_duration_arrow_fitness);
        if (LanguageUtil.bc(getContext())) {
            this.l.setRotationY(180.0f);
            this.n.setRotationY(180.0f);
        }
        this.k = (HealthTextView) view.findViewById(R.id.guide_bubble_textview);
        this.z = (RelativeLayout) view.findViewById(R.id.cancel_guide_bubble);
        this.ad = (ImageView) view.findViewById(R.id.never_tips);
        this.y = (HealthTextView) view.findViewById(R.id.cancel_guide_bubble_text);
        this.bk = view.findViewById(R.id.common_middle_click_view);
        this.ah = view.findViewById(R.id.common_middle_fitness_accessibility);
        this.bi = (LinearLayout) view.findViewById(R.id.subtitle_arrow_area_fitness);
        this.ai = (ImageView) view.findViewById(R.id.marker_image);
        this.al = (ImageView) view.findViewById(R.id.marker_image_fitness);
        this.x = (HealthTextView) view.findViewById(R.id.advertisement);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ReleaseLogUtil.b("Section_Section4_5Card_01", "onAttachedToWindow");
        jcf.bEj_(this.ag);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ReleaseLogUtil.b("Section_Section4_5Card_01", "onDetachedFromWindow");
        jcf.bEu_(this.ag);
    }

    private void g() {
        if (nsn.r()) {
            nsy.cMw_(this.bj, 56.0f);
            nsy.cMw_(this.bl, 56.0f);
            nsy.cMw_(this.bh, 28.0f);
            nsy.cMw_(this.bg, 28.0f);
            HealthButton healthButton = this.o;
            if (healthButton != null) {
                healthButton.setAutoTextInfo(17, 1, 1);
                if (this.o.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.o.getLayoutParams();
                    layoutParams.height = -2;
                    this.o.setLayoutParams(layoutParams);
                }
            }
            HealthButton healthButton2 = this.f;
            if (healthButton2 != null) {
                healthButton2.setAutoTextInfo(17, 1, 1);
                this.f.setPadding(0, nsn.c(BaseApplication.getContext(), -3.0f), 0, 0);
            }
        }
    }

    static class b implements AccessibilityManager.TouchExplorationStateChangeListener {
        private final WeakReference<Section4_5Card_01> b;

        b(Section4_5Card_01 section4_5Card_01) {
            this.b = new WeakReference<>(section4_5Card_01);
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            Section4_5Card_01 section4_5Card_01 = this.b.get();
            if (section4_5Card_01 == null) {
                ReleaseLogUtil.a("Section_Section4_5Card_01", "TouchExplorationStateChangeListener onTouchExplorationStateChanged section is null");
            } else {
                LogUtil.a("Section_Section4_5Card_01", "TouchExplorationStateChangeListener onTouchExplorationStateChanged enabled ", Boolean.valueOf(z));
                section4_5Card_01.setContentDescription(z);
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Section4_5Card_01";
    }
}
