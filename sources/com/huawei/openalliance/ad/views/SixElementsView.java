package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.bx;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes5.dex */
public class SixElementsView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    protected int f8017a;
    protected Context b;
    protected View c;
    protected TextView d;
    protected TextView e;
    protected TextView f;
    protected TextView g;
    protected TextView h;
    protected TextView i;
    protected TextView j;
    protected TextView k;
    protected TextView l;
    protected TextView m;
    protected AdLandingPageData n;
    protected ContentRecord o;
    protected MaterialClickInfo p;
    private int q;
    private float r;
    private AppInfo s;

    enum a {
        DESC,
        PRIVACY,
        PERMISSION
    }

    public void setTitleTextVisibility(int i) {
        this.d.setVisibility(i);
    }

    public void setOrgClickInfo(MaterialClickInfo materialClickInfo) {
        this.p = materialClickInfo;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.six_elements_privacy_policy || id == R.id.endcard_six_elements_privacy_policy) {
            e();
        } else if (id == R.id.six_elements_permission || id == R.id.endcard_six_elements_permission) {
            f();
        } else if (id == R.id.six_elements_desc || id == R.id.endcard_six_elements_desc) {
            g();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public MaterialClickInfo getOrgClickInfo() {
        return this.p;
    }

    protected boolean b() {
        return this.q == 0 && ao.n(this.b);
    }

    protected void b(Context context, AttributeSet attributeSet) {
        String str;
        if (attributeSet == null) {
            str = "attrs is null..";
        } else {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100128_res_0x7f0601e0, R.attr._2131101178_res_0x7f0605fa, R.attr._2131101302_res_0x7f060676});
            if (obtainStyledAttributes != null) {
                try {
                    this.f8017a = obtainStyledAttributes.getInt(0, 0);
                    int i = obtainStyledAttributes.getInt(1, 0);
                    this.q = i;
                    this.r = obtainStyledAttributes.getDimension(2, i == 1 ? ao.a(context, 16.0f) : ao.c(context, 16.0f));
                    return;
                } finally {
                    obtainStyledAttributes.recycle();
                }
            }
            str = "typedArray null..";
        }
        ho.a("SixElementsView", str);
    }

    public void a(boolean z) {
        if (b()) {
            return;
        }
        a(this.c, z);
    }

    public void a(AdLandingPageData adLandingPageData) {
        String str;
        if (adLandingPageData == null) {
            str = "landingPageData is null.";
        } else {
            this.n = adLandingPageData;
            this.s = adLandingPageData.getAppInfo();
            this.o = adLandingPageData.x();
            AppInfo appInfo = this.s;
            if (appInfo != null) {
                this.d.setText(!TextUtils.isEmpty(appInfo.getAppName()) ? this.s.getAppName() : this.s.getAppDesc());
                this.e.setText(this.s.getDeveloperName());
                if (b()) {
                    d();
                    return;
                } else {
                    c();
                    return;
                }
            }
            str = "appInfo is null.";
        }
        ho.c("SixElementsView", str);
    }

    protected void a(final View view, final boolean z) {
        if (view == null) {
            ho.b("SixElementsView", "rootView is null..");
        } else {
            view.postDelayed(new Runnable() { // from class: com.huawei.openalliance.ad.views.SixElementsView.1
                @Override // java.lang.Runnable
                public void run() {
                    int width = view.getWidth();
                    if (width == 0) {
                        ho.b("SixElementsView", "do not get screen width.");
                        return;
                    }
                    double d = width;
                    int i = (int) (0.35d * d);
                    ho.b("SixElementsView", "larger detail width is %d", Integer.valueOf(i));
                    int i2 = (int) (0.18d * d);
                    ho.b("SixElementsView", "small detail width is %d", Integer.valueOf(i2));
                    if (z) {
                        SixElementsView.this.f.setMaxWidth((int) (d * 0.2d));
                    } else {
                        SixElementsView.this.f.setMaxWidth(i);
                    }
                    SixElementsView.this.g.setMaxWidth(i2);
                    SixElementsView.this.h.setMaxWidth(i2);
                    SixElementsView.this.i.setMaxWidth(i2);
                    String lowerCase = dd.u(SixElementsView.this.b).toLowerCase(Locale.getDefault());
                    ho.a("SixElementsView", " languageCode=%s", lowerCase);
                    if ("bo-cn".equals(lowerCase)) {
                        SixElementsView.this.f.setIncludeFontPadding(true);
                        SixElementsView.this.g.setIncludeFontPadding(true);
                        SixElementsView.this.h.setIncludeFontPadding(true);
                        SixElementsView.this.i.setIncludeFontPadding(true);
                    }
                }
            }, 200L);
        }
    }

    protected void a(Context context, AttributeSet attributeSet) {
        b(context, attributeSet);
        this.b = context;
        if (b()) {
            View inflate = inflate(context, R.layout.six_elements_elderly_layout, this);
            this.c = inflate;
            this.m = (TextView) inflate.findViewById(R.id.six_elements_splicing);
        } else {
            View inflate2 = inflate(context, this.f8017a == 1 ? R.layout.six_elements_center_layout : R.layout.six_elements_layout, this);
            this.c = inflate2;
            this.f = (TextView) inflate2.findViewById(R.id.six_elements_version);
            TextView textView = (TextView) this.c.findViewById(R.id.six_elements_desc);
            this.g = textView;
            textView.setOnClickListener(this);
            TextView textView2 = (TextView) this.c.findViewById(R.id.six_elements_privacy_policy);
            this.h = textView2;
            textView2.setOnClickListener(this);
            TextView textView3 = (TextView) this.c.findViewById(R.id.six_elements_permission);
            this.i = textView3;
            textView3.setOnClickListener(this);
            this.j = (TextView) this.c.findViewById(R.id.version_line);
            this.k = (TextView) this.c.findViewById(R.id.privacy_line);
            this.l = (TextView) this.c.findViewById(R.id.permission_line);
            a(this.c, false);
        }
        this.d = (TextView) this.c.findViewById(R.id.six_elements_name);
        this.e = (TextView) this.c.findViewById(R.id.six_elements_develop_name);
        a();
    }

    protected void a() {
        TextView textView;
        if (Float.compare(this.r, 0.0f) != 0) {
            this.d.setTextSize(0, this.r);
        }
        if (1 != this.q) {
            ho.b("SixElementsView", "supportElderly is not 0, do not adaptation.");
            return;
        }
        if (b()) {
            textView = this.m;
        } else {
            a(this.e, R.dimen._2131363250_res_0x7f0a05b2);
            a(this.f, R.dimen._2131363250_res_0x7f0a05b2);
            a(this.g, R.dimen._2131363250_res_0x7f0a05b2);
            a(this.h, R.dimen._2131363250_res_0x7f0a05b2);
            a(this.i, R.dimen._2131363250_res_0x7f0a05b2);
            a(this.j, R.dimen._2131363250_res_0x7f0a05b2);
            a(this.k, R.dimen._2131363250_res_0x7f0a05b2);
            textView = this.l;
        }
        a(textView, R.dimen._2131363250_res_0x7f0a05b2);
    }

    private boolean j() {
        AppInfo appInfo;
        AdLandingPageData adLandingPageData = this.n;
        return (adLandingPageData == null || !adLandingPageData.m() || (appInfo = this.s) == null || TextUtils.isEmpty(appInfo.getPackageName()) || TextUtils.isEmpty(this.s.getPrivacyLink())) ? false : true;
    }

    private boolean i() {
        AppInfo appInfo = this.s;
        return appInfo != null && (!TextUtils.isEmpty(appInfo.getPermissionUrl()) || this.s.u());
    }

    private boolean h() {
        AppInfo appInfo = this.s;
        return (appInfo == null || TextUtils.isEmpty(appInfo.getAppDetailUrl())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        bx.a(this.b, this.n, getOrgClickInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (TextUtils.isEmpty(this.s.getPermissionUrl())) {
            com.huawei.openalliance.ad.download.app.h.a(this.b, this.s);
        } else {
            this.s.showPermissionPage(this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (TextUtils.isEmpty(this.s.getPrivacyLink())) {
            ho.c("SixElementsView", "privacyUrl is empty.");
        } else {
            this.s.showPrivacyPolicy(this.b);
        }
    }

    private void d() {
        boolean z;
        String string = this.b.getResources().getString(R.string._2130851007_res_0x7f0234bf, this.s.getVersionName());
        String string2 = this.b.getResources().getString(R.string._2130851101_res_0x7f02351d);
        String string3 = this.b.getResources().getString(R.string._2130851139_res_0x7f023543);
        String string4 = this.b.getResources().getString(R.string._2130851008_res_0x7f0234c0);
        StringBuilder sb = new StringBuilder();
        boolean z2 = true;
        if (TextUtils.isEmpty(this.s.getVersionName())) {
            z = false;
        } else {
            sb.append(string);
            z = true;
        }
        boolean h = h();
        if (h) {
            if (z) {
                sb.append("｜");
            }
            sb.append(string2);
            z = true;
        }
        boolean j = j();
        if (j) {
            if (z) {
                sb.append("｜");
            }
            sb.append(string3);
        } else {
            z2 = z;
        }
        boolean i = i();
        if (i) {
            if (z2) {
                sb.append("｜");
            }
            sb.append(string4);
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        if (h) {
            a(spannableString, sb.toString(), string2, a.DESC);
        }
        if (j) {
            a(spannableString, sb.toString(), string3, a.PRIVACY);
        }
        if (i) {
            a(spannableString, sb.toString(), string4, a.PERMISSION);
        }
        a(spannableString, sb.toString());
        this.m.setText(spannableString);
        this.m.setMovementMethod(LinkMovementMethod.getInstance());
        this.m.setHighlightColor(this.b.getResources().getColor(R.color._2131298002_res_0x7f0906d2));
    }

    private void c(Context context, AttributeSet attributeSet) {
        a(context, attributeSet);
    }

    private void c() {
        if (TextUtils.isEmpty(this.s.getVersionName())) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
            this.f.setText(this.b.getResources().getString(R.string._2130851007_res_0x7f0234bf, this.s.getVersionName()));
        }
        boolean z = true;
        boolean z2 = this.f.getVisibility() == 0;
        boolean h = h();
        this.j.setVisibility(h & z2 ? 0 : 8);
        this.g.setVisibility(h ? 0 : 8);
        boolean z3 = h || z2;
        boolean j = j();
        this.k.setVisibility(j & z3 ? 0 : 8);
        this.h.setVisibility(j ? 0 : 8);
        if (!j && !z3) {
            z = false;
        }
        boolean i = i();
        this.i.setVisibility(i ? 0 : 8);
        this.l.setVisibility((i && z) ? 0 : 8);
    }

    private void a(TextView textView, int i) {
        if (textView == null) {
            return;
        }
        textView.setTextSize(0, this.b.getResources().getDimensionPixelSize(i));
    }

    private void a(SpannableString spannableString, String str, String str2, final a aVar) {
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.huawei.openalliance.ad.views.SixElementsView.3
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(SixElementsView.this.b.getResources().getColor(R.color._2131297894_res_0x7f090666));
                textPaint.setUnderlineText(false);
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                int i = AnonymousClass4.f8021a[aVar.ordinal()];
                if (i == 1) {
                    SixElementsView.this.g();
                } else if (i == 2) {
                    SixElementsView.this.f();
                } else if (i == 3) {
                    SixElementsView.this.e();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        int indexOf = str.indexOf(str2);
        spannableString.setSpan(clickableSpan, indexOf, str2.length() + indexOf, 33);
    }

    private void a(SpannableString spannableString, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 65372) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            spannableString.setSpan(new AbsoluteSizeSpan(this.b.getResources().getDimensionPixelSize(R.dimen._2131363390_res_0x7f0a063e)) { // from class: com.huawei.openalliance.ad.views.SixElementsView.2
                @Override // android.text.style.AbsoluteSizeSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(SixElementsView.this.b.getResources().getColor(R.color._2131297890_res_0x7f090662));
                    textPaint.setUnderlineText(false);
                }
            }, ((Integer) arrayList.get(i2)).intValue(), ((Integer) arrayList.get(i2)).intValue() + 1, 33);
        }
    }

    public SixElementsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = 0;
        this.f8017a = 0;
        this.r = 0.0f;
        c(context, attributeSet);
    }

    /* renamed from: com.huawei.openalliance.ad.views.SixElementsView$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8021a;

        static {
            int[] iArr = new int[a.values().length];
            f8021a = iArr;
            try {
                iArr[a.DESC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8021a[a.PERMISSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8021a[a.PRIVACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public SixElementsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = 0;
        this.f8017a = 0;
        this.r = 0.0f;
        c(context, attributeSet);
    }

    public SixElementsView(Context context) {
        super(context);
        this.q = 0;
        this.f8017a = 0;
        this.r = 0.0f;
        c(context, null);
    }
}
