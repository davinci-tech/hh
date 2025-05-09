package com.huawei.indoorequip.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.lbh;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes5.dex */
public class SportEquipItemDetail extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f6437a;
    private ImageView b;
    private LinearLayout.LayoutParams c;
    private LinearLayout.LayoutParams d;
    private HealthTextView e;
    private LinearLayout f;
    private LinearLayout.LayoutParams g;
    private int h;
    private LinearLayout.LayoutParams i;
    private int j;
    private LinearLayout.LayoutParams k;
    private LinearLayout l;
    private Typeface m;
    private HealthTextView n;
    private int o;

    private boolean d(int i) {
        return i == 283;
    }

    public SportEquipItemDetail(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = 0;
        this.d = new LinearLayout.LayoutParams(-2, -2);
        this.g = new LinearLayout.LayoutParams(-2, -2);
        this.k = new LinearLayout.LayoutParams(-2, -2);
        this.c = new LinearLayout.LayoutParams(-2, -1);
        this.i = new LinearLayout.LayoutParams(-2, -2);
    }

    public SportEquipItemDetail(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = 0;
        this.d = new LinearLayout.LayoutParams(-2, -2);
        this.g = new LinearLayout.LayoutParams(-2, -2);
        this.k = new LinearLayout.LayoutParams(-2, -2);
        this.c = new LinearLayout.LayoutParams(-2, -1);
        this.i = new LinearLayout.LayoutParams(-2, -2);
    }

    public SportEquipItemDetail(Context context, int i, int i2, String str) {
        super(context);
        this.o = 0;
        this.d = new LinearLayout.LayoutParams(-2, -2);
        this.g = new LinearLayout.LayoutParams(-2, -2);
        this.k = new LinearLayout.LayoutParams(-2, -2);
        this.c = new LinearLayout.LayoutParams(-2, -1);
        this.i = new LinearLayout.LayoutParams(-2, -2);
        this.b = new ImageView(context);
        this.e = new HealthTextView(context);
        this.n = new HealthTextView(context);
        this.f6437a = new ImageView(context);
        this.l = new LinearLayout(context);
        this.f = new LinearLayout(context);
        this.o = i;
        Resources resources = context.getResources();
        this.e.setTextSize(0, nsn.c(context, 16.0f));
        this.e.setTextColor(resources.getColor(R.color._2131298664_res_0x7f090968));
        this.j = nsn.c(context, 16.0f);
        this.e.setMaxLines(LanguageUtil.h(context) || LanguageUtil.p(context) ? 2 : 1);
        this.e.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        linearLayout.setWeightSum(5.0f);
        Object systemService = getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.e("Track_IDEQ_SportEquipItemDetail", "object is invalid type");
            return;
        }
        WindowManager windowManager = (WindowManager) systemService;
        int bVH_ = bVH_(windowManager);
        bVK_(context, resources, linearLayout, bVH_, i2);
        bVI_(context, resources, linearLayout, bVH_, i2);
        addView(linearLayout);
        if (d(this.o)) {
            this.i.height = 500;
        } else {
            this.i.height = 400;
        }
        bVJ_(context, str, resources, bVH_);
        this.i.width = bVH_(windowManager);
        setLayoutParams(this.i);
    }

    private void bVJ_(Context context, String str, Resources resources, int i) {
        this.n.setMaxLines(2);
        this.n.setEllipsize(TextUtils.TruncateAt.END);
        Typeface createFromAsset = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.m = createFromAsset;
        this.n.setTypeface(createFromAsset);
        c(context, str, i);
        this.n.setTextColor(resources.getColor(R.color._2131299238_res_0x7f090ba6));
    }

    private void c(Context context, String str, int i) {
        if (!TextUtils.isEmpty(str) && str.length() > 5) {
            Paint paint = new Paint();
            paint.setTypeface(this.m);
            for (int i2 = 48; i2 >= 0; i2--) {
                float f = i2;
                paint.setTextSize(nsn.c(context, f));
                if (paint.measureText(str) < i - nsn.c(context, 24.0f)) {
                    this.n.setTextSize(0, nsn.c(context, f));
                    return;
                }
            }
            return;
        }
        this.n.setTextSize(0, nsn.c(context, 48.0f));
    }

    private int bVH_(WindowManager windowManager) {
        return windowManager.getDefaultDisplay().getWidth() / 2;
    }

    private void bVK_(Context context, Resources resources, LinearLayout linearLayout, int i, int i2) {
        LinearLayout.LayoutParams layoutParams;
        this.l.setOrientation(0);
        this.l.setGravity(81);
        if (d(this.o)) {
            layoutParams = new LinearLayout.LayoutParams(i, 0, 3.0f);
            layoutParams.bottomMargin = resources.getDimensionPixelOffset(R.dimen._2131363122_res_0x7f0a0532);
        } else {
            layoutParams = new LinearLayout.LayoutParams(i2, 0, 3.0f);
            this.c.gravity = 80;
            layoutParams.topMargin = 0;
        }
        this.l.addView(this.n, this.k);
        this.l.addView(this.f6437a, this.c);
        linearLayout.addView(this.l, layoutParams);
    }

    private void bVI_(Context context, Resources resources, LinearLayout linearLayout, int i, int i2) {
        LinearLayout.LayoutParams layoutParams;
        this.f.setOrientation(0);
        this.f.setGravity(49);
        if (d(this.o)) {
            this.h = i;
            layoutParams = new LinearLayout.LayoutParams(this.h, 0, 2.0f);
        } else {
            this.h = i2;
            layoutParams = new LinearLayout.LayoutParams(this.h, 0, 2.0f);
        }
        this.f.addView(this.e, this.g);
        this.f.addView(this.b, this.d);
        linearLayout.addView(this.f, layoutParams);
    }

    public void setTextColor(int i) {
        this.e.setTextColor(i);
        this.n.setTextColor(i);
        this.e.setAlpha(0.5f);
    }

    public void setValueTextSize(float f) {
        this.n.setTextSize(0, f);
    }

    public void setNameTextSize(float f) {
        this.e.setTextSize(0, f);
        e((int) f);
    }

    public HealthTextView getName() {
        return this.e;
    }

    public ImageView getNameImage() {
        return this.b;
    }

    public HealthTextView getValue() {
        return this.n;
    }

    public void c(int i) {
        this.e.setAutoTextSize(0, i);
        this.e.setAutoTextInfo(10, 1, 1);
        e(i);
    }

    public void a(int i) {
        this.n.setTextSize(0, i);
    }

    public void setGroupSize(int i, int i2) {
        this.i.width = i;
        this.i.height = i2;
        setLayoutParams(this.i);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(a(i, true), a(i2, false));
    }

    private int a(int i, boolean z) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int i2 = z ? 500 : 400;
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    public void setItemView(e eVar) {
        if (eVar == null) {
            LogUtil.c("Track_IDEQ_SportEquipItemDetail", "setItemView data is null");
            return;
        }
        if (eVar.b != null) {
            int i = this.j;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
            layoutParams.setMargins(0, (int) (this.j * 0.2f), 0, 0);
            this.e.setMaxWidth(this.h - this.j);
            this.b.setLayoutParams(layoutParams);
            this.b.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.b.setImageDrawable(eVar.b);
        }
        this.e.setText(eVar.c);
        if (eVar.f6438a != null) {
            this.n.setText(eVar.f6438a);
        }
        if (eVar.d != null) {
            b(eVar.d);
        }
    }

    public void e(int i) {
        this.j = i;
    }

    private void b(lbh lbhVar) {
        if (lbhVar.bVB_() != null) {
            this.f6437a.setImageDrawable(lbhVar.bVB_());
        }
        if (lbhVar.e() != 0) {
            this.e.setTextColor(lbhVar.e());
        }
        if (lbhVar.a() != 0) {
            this.e.setTextSize(lbhVar.a());
        }
        if (lbhVar.c() != 0) {
            this.n.setTextColor(lbhVar.c());
        }
        if (lbhVar.b() != 0) {
            this.n.setTextSize(lbhVar.b());
        }
    }

    public void d(Context context, float f) {
        if (context == null) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart((int) f);
        this.e.setLayoutParams(layoutParams);
        this.n.setLayoutParams(layoutParams);
    }

    public void d(int i, int i2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.h, 0, 0.0f);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.h, 0, 5.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(i2, i2);
        this.l.setLayoutParams(layoutParams);
        this.f.setGravity(17);
        this.f.setLayoutParams(layoutParams2);
        layoutParams3.setMarginStart(i);
        this.e.setVisibility(8);
        this.n.setVisibility(8);
        this.b.setLayoutParams(layoutParams3);
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private String f6438a;
        private Drawable b;
        private String c;
        private lbh d;

        public e(Drawable drawable, String str, String str2) {
            if (drawable != null) {
                this.b = drawable;
            }
            if (str != null) {
                this.c = str;
            }
            if (str2 != null) {
                this.f6438a = str2;
            }
        }

        public e(Drawable drawable, String str, String str2, lbh lbhVar) {
            if (drawable != null) {
                this.b = drawable;
            }
            if (str != null) {
                this.c = str;
            }
            if (str2 != null) {
                this.f6438a = str2;
            }
            if (lbhVar != null) {
                this.d = lbhVar;
            }
        }

        public String a() {
            return this.c;
        }

        public Drawable bVM_() {
            return this.b;
        }

        public String b() {
            return this.f6438a;
        }
    }
}
