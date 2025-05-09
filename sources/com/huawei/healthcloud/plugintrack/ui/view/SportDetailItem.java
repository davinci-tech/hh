package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.view.SportDetailItem;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.caj;
import defpackage.nsn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class SportDetailItem extends LinearLayout {
    private static boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3798a;
    private LinearLayout.LayoutParams c;
    private HealthTextView d;
    private ImageView e;
    private LinearLayout.LayoutParams f;
    private LinearLayout.LayoutParams g;
    private LinearLayout.LayoutParams h;
    private LinearLayout i;
    private HealthTextView j;
    private RelativeLayout k;
    private LinearLayout.LayoutParams l;
    private LinearLayout.LayoutParams m;
    private HealthTextView n;
    private RelativeLayout.LayoutParams o;

    public SportDetailItem(Context context) {
        super(context);
        this.c = new LinearLayout.LayoutParams(-2, -2);
        this.g = new LinearLayout.LayoutParams(-2, -2);
        this.l = new LinearLayout.LayoutParams(-2, -2);
        this.m = new LinearLayout.LayoutParams(-2, -2);
        this.h = new LinearLayout.LayoutParams(-2, -2);
        this.o = new RelativeLayout.LayoutParams(-2, -2);
        this.f = new LinearLayout.LayoutParams(-2, -2);
        this.e = new ImageView(context);
        this.f3798a = new ImageView(context);
        this.d = new HealthTextView(context);
        this.n = new HealthTextView(context);
        this.j = new HealthTextView(context);
        Resources resources = context.getResources();
        setGravity(16);
        setPadding(0, resources.getDimensionPixelOffset(R.dimen._2131363691_res_0x7f0a076b), 0, resources.getDimensionPixelOffset(R.dimen._2131363691_res_0x7f0a076b));
        bjv_(resources);
        this.i = bju_(context, resources);
        bjw_(resources);
        settingUnitTextView(resources);
        this.e.setBackground(resources.getDrawable(R.drawable._2131430061_res_0x7f0b0aad));
        this.c.setMarginEnd((int) resources.getDimension(R.dimen._2131363628_res_0x7f0a072c));
        addView(this.e, this.c);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = 0;
        this.m.bottomMargin = resources.getDimensionPixelOffset(R.dimen._2131363702_res_0x7f0a0776);
        this.m.setMarginStart(resources.getDimensionPixelOffset(R.dimen._2131363702_res_0x7f0a0776));
        linearLayout2.addView(this.n, this.l);
        linearLayout2.addView(this.j, this.m);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.k = relativeLayout;
        relativeLayout.addView(linearLayout2, layoutParams);
        linearLayout.addView(this.i, this.h);
        linearLayout.addView(this.k, this.o);
        addView(linearLayout);
        setLayoutParams(this.f);
    }

    private void bjw_(Resources resources) {
        this.n.setTypeface(Typeface.create(BaseApplication.getContext().getResources().getString(R.string._2130851581_res_0x7f0236fd), 0));
        this.n.setMaxLines(2);
        this.n.setEllipsize(TextUtils.TruncateAt.END);
        this.n.setTextSize(0, resources.getDimension(R.dimen._2131362955_res_0x7f0a048b));
        this.n.setTextColor(resources.getColor(R.color._2131296685_res_0x7f0901ad));
    }

    private LinearLayout bju_(Context context, Resources resources) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.addView(this.d, this.g);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart(resources.getDimensionPixelOffset(R.dimen._2131363702_res_0x7f0a0776));
        linearLayout.addView(this.f3798a, layoutParams);
        return linearLayout;
    }

    private void bjv_(Resources resources) {
        this.d.setTextSize(0, resources.getDimension(R.dimen._2131365062_res_0x7f0a0cc6));
        this.d.setTextColor(resources.getColor(R.color._2131296687_res_0x7f0901af));
        this.d.setMaxLines(3);
        this.d.setEllipsize(TextUtils.TruncateAt.END);
    }

    public SportDetailItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new LinearLayout.LayoutParams(-2, -2);
        this.g = new LinearLayout.LayoutParams(-2, -2);
        this.l = new LinearLayout.LayoutParams(-2, -2);
        this.m = new LinearLayout.LayoutParams(-2, -2);
        this.h = new LinearLayout.LayoutParams(-2, -2);
        this.o = new RelativeLayout.LayoutParams(-2, -2);
        this.f = new LinearLayout.LayoutParams(-2, -2);
    }

    public SportDetailItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new LinearLayout.LayoutParams(-2, -2);
        this.g = new LinearLayout.LayoutParams(-2, -2);
        this.l = new LinearLayout.LayoutParams(-2, -2);
        this.m = new LinearLayout.LayoutParams(-2, -2);
        this.h = new LinearLayout.LayoutParams(-2, -2);
        this.o = new RelativeLayout.LayoutParams(-2, -2);
        this.f = new LinearLayout.LayoutParams(-2, -2);
    }

    private void settingUnitTextView(Resources resources) {
        if (nsn.c() > 1.0f) {
            this.j.setMaxLines(2);
        } else {
            this.j.setSingleLine(true);
        }
        this.j.setEllipsize(TextUtils.TruncateAt.END);
        this.j.setTextSize(0, resources.getDimension(R.dimen._2131365062_res_0x7f0a0cc6));
        this.j.setAutoTextInfo(9, 1, 2);
        this.j.setTextColor(resources.getColor(R.color._2131296687_res_0x7f0901af));
    }

    public void setTextColor(int i) {
        this.d.setTextColor(i);
        this.n.setTextColor(i);
        this.j.setTextColor(i);
        this.d.setAlpha(0.5f);
        this.j.setAlpha(0.5f);
    }

    public void setValueTextSize(float f) {
        this.n.setTextSize(0, f);
    }

    public void a() {
        this.n.getPaint().setFakeBoldText(true);
    }

    public void setGroupSize(int i, int i2) {
        this.f.width = i;
        setMinimumHeight(i2);
        setLayoutParams(this.f);
    }

    public void setGroupSize(int i) {
        this.f.width = i;
        setLayoutParams(this.f);
    }

    public void setGroupSizeHeight(int i) {
        this.f.height = i;
        setLayoutParams(this.f);
    }

    public void setItemView(final b bVar) {
        if (bVar == null) {
            LogUtil.h("Track_SportDetailItem", "setItemView data is null");
            return;
        }
        this.e.setBackground(bVar.e);
        this.d.setText(bVar.d);
        this.n.setText(bVar.c);
        this.j.setText(bVar.f);
        if (bVar.f3800a != null) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable._2131430493_res_0x7f0b0c5d);
            if (drawable == null) {
                ReleaseLogUtil.c("Track_SportDetailItem", "explainIcon is null");
                return;
            }
            this.f3798a.setBackground(drawable);
            this.f3798a.setVisibility(0);
            this.f3798a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.view.SportDetailItem.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nsn.o()) {
                        LogUtil.h("Track_SportDetailItem", "downRemindClick is fast click");
                        ViewClickInstrumentation.clickOnView(view);
                    } else {
                        if (bVar.b) {
                            AppRouter.b(bVar.f3800a).b(268435456).c(BaseApplication.getContext());
                        } else {
                            caj.a().a(bVar.f3800a);
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
            });
            a(drawable.getIntrinsicWidth());
            return;
        }
        this.f3798a.setVisibility(8);
    }

    private void a(final int i) {
        this.d.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hlq
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                SportDetailItem.this.e(i);
            }
        });
    }

    public /* synthetic */ void e(int i) {
        int i2 = this.f.width;
        int width = this.e.getWidth();
        if (this.d.getWidth() > ((i2 - width) - i) - nsn.c(getContext(), 12.0f)) {
            this.d.setTextSize(0, this.d.getTextSize() - 1.0f);
        }
    }

    public static void c(boolean z) {
        b = z;
    }

    public void c(Context context) {
        if (context == null) {
            return;
        }
        this.h.gravity = 1;
        this.i.setLayoutParams(this.h);
        if (b) {
            this.d.setGravity(17);
            this.d.setAutoTextInfo(9, 1, 1);
            LogUtil.a("Track_SportDetailItem", "mName mUnit setAutoTextInfo 9");
            this.j.setAutoTextInfo(9, 1, 1);
            this.d.setMaxLines(2);
        }
        this.l.gravity = 1;
        this.k.setLayoutParams(this.l);
        this.e.setVisibility(8);
        this.m.setMargins(nsn.c(context, 2.0f), nsn.c(context, 6.0f), 0, 0);
        this.j.setLayoutParams(this.m);
        this.j.setSingleLine(false);
        this.j.setMaxLines(2);
    }

    public void e(Context context) {
        if (context == null) {
            return;
        }
        Resources resources = context.getResources();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart(resources.getDimensionPixelSize(R.dimen._2131362573_res_0x7f0a030d));
        layoutParams.setMarginEnd((int) resources.getDimension(R.dimen._2131362575_res_0x7f0a030f));
        this.e.setLayoutParams(layoutParams);
    }

    public void a(int i, int i2, int i3) {
        if (i != 0) {
            this.d.setTextSize(0, i);
        }
        if (i2 != 0) {
            this.n.setTextSize(0, i2);
        }
        if (i3 != 0) {
            this.j.setTextSize(0, i3);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f3800a;
        private boolean b;
        private String c;
        private String d;
        private Drawable e;
        private String f;

        public b(Drawable drawable, String str, String str2, String str3) {
            this.e = drawable;
            this.d = str;
            this.c = str2;
            this.f = str3;
        }

        public void a(String str) {
            this.f3800a = str;
        }

        public String d() {
            return this.d;
        }

        public String e() {
            return this.c;
        }

        public void d(String str) {
            this.c = str;
        }

        public String c() {
            return this.f;
        }

        public void e(String str) {
            this.f = str;
        }
    }
}
