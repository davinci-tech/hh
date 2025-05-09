package com.huawei.ui.commonui.healthtextview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.uikit.phone.hwtextview.widget.HwTextView;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class HealthTextView extends HwTextView implements AutoRiseInterface {
    private static final String c = System.lineSeparator();
    private static final int e = nsn.c(BaseApplication.e(), 30.0f);

    /* renamed from: a, reason: collision with root package name */
    private AlphaAnimation f8837a;
    private String aa;
    private float ab;
    private int ac;
    private int ad;
    private ScaleAnimation ae;
    private Paint af;
    private Paint ag;
    private Paint ah;
    private Rect ai;
    private float aj;
    private int ak;
    private AnimationSet al;
    private int am;
    private TextPaint an;
    private String ao;
    private int ap;
    private int aq;
    private Timer ar;
    private TimerTask as;
    private int ax;
    private Context b;
    private int d;
    private Matrix f;
    private int g;
    private long h;
    private float i;
    private String j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private TextLineListener u;
    private boolean v;
    private boolean w;
    private boolean x;
    private LinearGradient y;
    private float z;

    public interface TextLineListener {
        void onCallback();
    }

    static /* synthetic */ int b(HealthTextView healthTextView, int i) {
        int i2 = healthTextView.ac + i;
        healthTextView.ac = i2;
        return i2;
    }

    static /* synthetic */ int e(HealthTextView healthTextView, int i) {
        int i2 = healthTextView.ad + i;
        healthTextView.ad = i2;
        return i2;
    }

    public HealthTextView(Context context) {
        this(context, null);
        this.b = context;
    }

    public HealthTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.b = context;
    }

    public HealthTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.v = false;
        this.q = true;
        this.aj = 0.0f;
        this.i = 0.0f;
        this.s = false;
        this.aq = 0;
        this.ap = 0;
        this.l = true;
        this.an = new TextPaint();
        this.af = new Paint();
        this.ac = 0;
        this.ad = 0;
        this.t = true;
        this.j = "";
        this.b = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.healthTextView);
        this.n = obtainStyledAttributes.getBoolean(R$styleable.healthTextView_control_zoom, false);
        this.k = obtainStyledAttributes.getBoolean(R$styleable.healthTextView_auto_scale, false);
        this.m = obtainStyledAttributes.getBoolean(R$styleable.healthTextView_auto_text, false);
        this.x = obtainStyledAttributes.getBoolean(R$styleable.healthTextView_share_text, false);
        this.r = obtainStyledAttributes.getBoolean(R$styleable.healthTextView_scroll_text, false);
        obtainStyledAttributes.recycle();
        if (this.n) {
            Paint paint = new Paint();
            this.ah = paint;
            paint.set(getPaint());
            float textSize = getTextSize();
            this.z = textSize;
            if (textSize <= b(15.0f)) {
                this.z = b(15.0f);
            }
            this.ab = b(7.0f);
        }
        if (this.r) {
            this.ai = new Rect();
            this.ar = new Timer("LastBottomBaseLineTextView");
            a aVar = new a();
            this.as = aVar;
            this.ar.schedule(aVar, 0L, 33L);
        }
        this.p = "V12".equals(CommonUtil.ab());
        this.o = Build.VERSION.SDK_INT == 29;
    }

    public void setSplitable(boolean z, int i, String str) {
        this.v = z;
        this.ax = i;
        if (str != null) {
            this.j = str;
        }
    }

    public void setSplittable(boolean z) {
        this.v = z;
    }

    public void setConnectionSymbol(boolean z) {
        this.q = z;
    }

    @Override // com.huawei.uikit.hwtextview.widget.HwTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.v && getMeasuredWidth() > 0 && getMeasuredHeight() > 0) {
            CharSequence f = f();
            if (!TextUtils.isEmpty(f)) {
                setText(f);
            }
        }
        if (this.x) {
            d(i, i2);
        }
    }

    private void d(int i, int i2) {
        int makeMeasureSpec;
        this.an.setTextSize(getResources().getDisplayMetrics().scaledDensity * getTextSize());
        this.af.setTypeface(getTypeface());
        this.af.setTextSize(getTextSize());
        if (nsn.a(this.b, getTextSize()) < 20) {
            this.g = 4;
        } else if (nsn.a(this.b, getTextSize()) < 40) {
            this.g = 6;
        } else {
            this.g = 8;
        }
        if (this.p && this.o) {
            this.g *= 2;
            if (nsn.a(this.b, getTextSize()) > 20) {
                this.g += 4;
            }
        }
        float measureText = this.af.measureText(getText().toString());
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            int c2 = (int) (nsn.c(this.b, this.g) + measureText);
            if (size > c2) {
                size = c2;
            }
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, mode);
        } else if (mode == 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (nsn.c(this.b, this.g) + measureText), mode);
        } else {
            makeMeasureSpec = mode != 1073741824 ? 0 : View.MeasureSpec.makeMeasureSpec(size, mode);
        }
        setMeasuredDimension(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), View.MeasureSpec.getMode(i2)));
    }

    @Override // com.huawei.uikit.hwtextview.widget.HwTextView
    public void setAutoTextInfo(int i, int i2, int i3) {
        super.setAutoTextInfo(i, i2, i3);
    }

    private CharSequence f() {
        float f;
        String str;
        int i;
        int i2;
        CharSequence text = getText();
        String obj = text.toString();
        String[] split = obj.split(c);
        TextPaint paint = getPaint();
        StringBuilder sb = new StringBuilder();
        float measureWidth = (getMeasureWidth() - getPaddingLeft()) - getPaddingRight();
        int length = split.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            String str2 = split[i3];
            if (i4 != 0 || (i2 = this.ax) == 0) {
                f = measureWidth;
                str = str2;
            } else {
                f = measureWidth - i2;
                str = str2.replaceAll(this.j, "");
            }
            if (paint.measureText(str) <= f) {
                sb.append(str2);
            } else {
                if (!Pattern.compile(".*[a-zA-Z]+.*").matcher(str).matches()) {
                    return null;
                }
                if (i4 != 0 || this.ax == 0) {
                    i = 0;
                } else {
                    sb.append(this.j);
                    i = this.ax;
                }
                cAM_(paint, f, i, str, sb);
            }
            sb.append(c);
            i4++;
            i3++;
            measureWidth = f;
        }
        if (!obj.endsWith(c)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        if (text instanceof Spanned) {
            TextUtils.copySpansFrom((Spanned) text, 0, text.length(), null, spannableString, 0);
        }
        return spannableString;
    }

    private void cAM_(Paint paint, float f, int i, String str, StringBuilder sb) {
        int i2 = 0;
        if (paint.measureText(String.valueOf(str.charAt(0))) > f) {
            LogUtil.c("LastBottomBaseLineTextView", "a single character has no display space,return");
            return;
        }
        float f2 = f;
        float f3 = 0.0f;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            f3 += paint.measureText(String.valueOf(charAt));
            if (f3 <= f2) {
                sb.append(charAt);
            } else {
                if (i != 0) {
                    f2 = f + i;
                }
                int i3 = i2 - 1;
                i2 -= 2;
                if (d(str, i3, i2)) {
                    sb.deleteCharAt(sb.length() - 1);
                    e(sb);
                } else {
                    sb.append(c);
                    i2 = i3;
                }
                f3 = 0.0f;
            }
            i2++;
        }
    }

    private void e(StringBuilder sb) {
        if (this.q) {
            sb.append(Constants.LINK);
            sb.append(c);
        } else {
            sb.append(c);
            sb.append(Constants.LINK);
        }
    }

    private boolean d(String str, int i, int i2) {
        boolean z;
        boolean z2;
        if (i2 >= 0) {
            z2 = str.charAt(i2) == ' ' || str.charAt(i) == ' ';
            z = (str.charAt(i2) == '(' || str.charAt(i) == '(') || (str.charAt(i2) == ')' || str.charAt(i) == ')') || (str.charAt(i2) == 65288 || str.charAt(i) == 65288) || (str.charAt(i2) == 65289 || str.charAt(i) == 65289);
        } else {
            z = false;
            z2 = false;
        }
        return this.ax != 0 ? a(Character.valueOf(str.charAt(i))) && a(Character.valueOf(str.charAt(i + 1))) : (z2 || z) ? false : true;
    }

    private boolean a(Character ch) {
        return Character.isUpperCase(ch.charValue()) || Character.isLowerCase(ch.charValue());
    }

    @Override // android.widget.TextView, android.view.View
    public int getBaseline() {
        Layout layout = getLayout();
        if (layout == null) {
            return super.getBaseline();
        }
        if (layout.getLineCount() > 0) {
            return (super.getBaseline() - layout.getLineBaseline(0)) + layout.getLineBaseline(layout.getLineCount() - 1);
        }
        return 0;
    }

    private void g() {
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(this.aj, this.i);
        ofFloat.setDuration(this.h);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.commonui.healthtextview.HealthTextView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (LanguageUtil.m(HealthTextView.this.getContext())) {
                    HealthTextView.this.setText(new DecimalFormat("##0.00").format(CommonUtil.j(ofFloat.getAnimatedValue().toString())));
                } else {
                    HealthTextView.this.setText(UnitUtil.e(CommonUtil.j(ofFloat.getAnimatedValue().toString()), 1, 2));
                }
            }
        });
        ofFloat.start();
    }

    @Override // com.huawei.ui.commonui.healthtextview.AutoRiseInterface
    public void start() {
        g();
    }

    @Override // com.huawei.ui.commonui.healthtextview.AutoRiseInterface
    public void setNumber(float f, float f2) {
        this.aj = f;
        this.i = f2;
    }

    @Override // com.huawei.ui.commonui.healthtextview.AutoRiseInterface
    public void setDuration(long j) {
        this.h = j;
    }

    private void a(String str, int i) {
        if (i > 0) {
            int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
            if (paddingLeft == 0) {
                LogUtil.h("LastBottomBaseLineTextView", "refitText actualTextWidth is zero");
                return;
            }
            if (!this.k) {
                this.ah.setTextSize(getTextSize());
                this.ak = (int) Math.ceil(this.ah.measureText(str) / paddingLeft);
                d();
                return;
            }
            float f = paddingLeft;
            float f2 = this.z;
            this.ah.setTextSize(f2);
            float measureText = this.ah.measureText(str);
            while (true) {
                float f3 = this.ab;
                if (f2 <= f3 || measureText <= 2.8f * f) {
                    break;
                }
                f2 -= 1.0f;
                if (f2 <= f3) {
                    f2 = f3;
                    break;
                } else {
                    this.ah.setTextSize(f2);
                    measureText = this.ah.measureText(str);
                }
            }
            this.ak = (int) Math.ceil(measureText / f);
            d();
            setTextSize(0, f2);
        }
    }

    private void d() {
        TextLineListener textLineListener;
        if (!this.w || (textLineListener = this.u) == null) {
            return;
        }
        this.w = false;
        textLineListener.onCallback();
    }

    public void a(TextLineListener textLineListener) {
        this.u = textLineListener;
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.n) {
            this.w = true;
            a(getText().toString(), getWidth());
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.n && i != i3) {
            a(getText().toString(), i);
            return;
        }
        if (this.s && this.aq == 0) {
            int measuredWidth = getMeasuredWidth();
            this.aq = measuredWidth;
            if (measuredWidth > 0) {
                this.ag = getPaint();
                LinearGradient linearGradient = new LinearGradient(-this.aq, 0.0f, 0.0f, 0.0f, new int[]{Integer.MIN_VALUE, -1, Integer.MIN_VALUE}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
                this.y = linearGradient;
                this.ag.setShader(linearGradient);
                this.f = new Matrix();
            }
        }
    }

    private int b(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        if (canvas == null) {
            LogUtil.b("LastBottomBaseLineTextView", "onDraw canvas == null");
            return;
        }
        if (this.s) {
            super.onDraw(canvas);
            h();
        } else if (this.m) {
            cAK_(canvas);
        } else if (this.r) {
            cAL_(canvas);
        } else {
            super.onDraw(canvas);
        }
    }

    private void cAL_(Canvas canvas) {
        this.ao = getText().toString();
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        String str = this.ao;
        paint.getTextBounds(str, 0, str.length(), this.ai);
        float descent = (((-paint.ascent()) + paint.descent()) / 2.0f) - paint.descent();
        if (this.ai.width() < getWidth()) {
            canvas.drawText(this.ao, 0.0f, (getHeight() / 2.0f) + descent, paint);
        } else {
            canvas.drawText(this.ao, this.ac, (getHeight() / 2.0f) + descent, paint);
            canvas.drawText(this.ao, this.ad, (getHeight() / 2.0f) + descent, paint);
        }
    }

    private void cAK_(Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f = fontMetrics.descent - fontMetrics.ascent;
        float f2 = f;
        for (String str : cAJ_(getText().toString(), paint, getWidth() - (paint.getTextSize() / 2.0f))) {
            canvas.drawText(str, 0.0f, f2, paint);
            f2 += fontMetrics.leading + f;
        }
    }

    private void h() {
        Matrix matrix;
        if (!this.l || (matrix = this.f) == null) {
            return;
        }
        int i = this.ap;
        int i2 = this.aq;
        int i3 = i + (i2 / 10);
        this.ap = i3;
        if (i3 > i2 * 2) {
            this.ap = -i2;
        }
        matrix.setTranslate(this.ap, 0.0f);
        this.y.setLocalMatrix(this.f);
        postInvalidateDelayed(50L);
    }

    private String[] cAJ_(String str, Paint paint, float f) {
        int length = str.length();
        if (paint.measureText(str) <= f) {
            return new String[]{str};
        }
        int ceil = (int) Math.ceil(r1 / f);
        String[] strArr = new String[ceil];
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length || i3 >= ceil) {
                break;
            }
            if (paint.measureText(str, i2, i) > f) {
                strArr[i3] = (String) str.subSequence(i2, i);
                i3++;
                i2 = i;
            }
            if (i != length) {
                i++;
            } else if (str.subSequence(i2, i) instanceof String) {
                strArr[i3] = (String) str.subSequence(i2, i);
            }
        }
        return strArr;
    }

    public void a(int i) {
        this.d = i;
        b(i);
        this.f8837a = new AlphaAnimation(0.0f, 1.0f);
        this.ae = new ScaleAnimation(10.0f, 1.0f, 10.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        b(300, 0, 400);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, final int i2, final int i3) {
        setAlpha(1.0f);
        AnimationSet animationSet = new AnimationSet(true);
        this.al = animationSet;
        animationSet.addAnimation(this.f8837a);
        this.al.addAnimation(this.ae);
        this.al.setDuration(i);
        this.al.setFillAfter(true);
        this.al.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.ui.commonui.healthtextview.HealthTextView.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                HealthTextView.this.postDelayed(new Runnable() { // from class: com.huawei.ui.commonui.healthtextview.HealthTextView.4.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (i2 == 0) {
                            if (HealthTextView.this.d == 0) {
                                HealthTextView.this.setAlpha(0.0f);
                                return;
                            }
                            HealthTextView.this.f8837a = new AlphaAnimation(1.0f, 0.0f);
                            HealthTextView.this.ae = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                            HealthTextView.this.b(292, 1, 0);
                        }
                    }
                }, i3);
            }
        });
        startAnimation(this.al);
    }

    private void b(int i) {
        setAlpha(0.0f);
        this.aa = UnitUtil.e(i, 1, 0);
        if (i == 0) {
            this.aa = "GO";
            setTextSize(2, 150.0f);
        } else {
            setTextSize(2, 200.0f);
        }
        setTypeface(Typeface.createFromAsset(this.b.getAssets(), "fonts/HarmonyOS_SansCondensedItalic_Bold.ttf"));
        setText(this.aa);
        setGravity(17);
        setTextColor(getResources().getColor(R$color.common_color_white));
        setLayerType(1, null);
        this.ae = new ScaleAnimation(1.0f, 10.0f, 1.0f, 10.0f, 1, 0.5f, 1, 0.5f);
        this.f8837a = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(true);
        this.al = animationSet;
        animationSet.addAnimation(this.f8837a);
        this.al.addAnimation(this.ae);
        this.al.setDuration(1L);
        this.al.setFillAfter(true);
        startAnimation(this.al);
    }

    public void setLockText(boolean z) {
        this.s = z;
    }

    public void c() {
        Timer timer = this.ar;
        if (timer != null) {
            timer.cancel();
        }
    }

    public void e() {
        this.as = new a();
        Timer timer = new Timer("LastBottomBaseLineTextView");
        this.ar = timer;
        timer.schedule(this.as, 0L, 33L);
    }

    public void b() {
        this.ac = 0;
        this.ad = 0;
        this.t = true;
    }

    public void setSpeed(int i) {
        this.am = i;
    }

    class a extends TimerTask {
        private a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (HealthTextView.this.ai.width() < HealthTextView.this.getWidth()) {
                return;
            }
            if (HealthTextView.this.am < 0) {
                a();
            } else {
                b();
            }
            HealthTextView.this.postInvalidate();
        }

        private void b() {
            if (HealthTextView.this.ac > HealthTextView.this.ai.width() + HealthTextView.this.getPaddingStart()) {
                HealthTextView healthTextView = HealthTextView.this;
                healthTextView.ac = (-healthTextView.ai.width()) - HealthTextView.e;
            }
            if (HealthTextView.this.ad > HealthTextView.this.ai.width() + HealthTextView.this.getPaddingStart()) {
                HealthTextView healthTextView2 = HealthTextView.this;
                healthTextView2.ad = (-healthTextView2.ai.width()) - HealthTextView.e;
            }
            if (HealthTextView.this.ac > HealthTextView.this.ad) {
                HealthTextView.this.t = true;
            } else {
                HealthTextView.this.t = false;
            }
            if (HealthTextView.this.t) {
                HealthTextView healthTextView3 = HealthTextView.this;
                HealthTextView.b(healthTextView3, healthTextView3.am);
                HealthTextView healthTextView4 = HealthTextView.this;
                healthTextView4.ad = (healthTextView4.ac - HealthTextView.e) - HealthTextView.this.ai.width();
                return;
            }
            HealthTextView healthTextView5 = HealthTextView.this;
            HealthTextView.e(healthTextView5, healthTextView5.am);
            HealthTextView healthTextView6 = HealthTextView.this;
            healthTextView6.ac = (healthTextView6.ad - HealthTextView.e) - HealthTextView.this.ai.width();
        }

        private void a() {
            if (HealthTextView.this.ac < (-HealthTextView.this.ai.width()) - HealthTextView.this.getPaddingEnd()) {
                HealthTextView healthTextView = HealthTextView.this;
                healthTextView.ac = healthTextView.ai.width() + HealthTextView.this.getPaddingEnd();
            }
            if (HealthTextView.this.ad < (-HealthTextView.this.ai.width()) - HealthTextView.this.getPaddingEnd()) {
                HealthTextView healthTextView2 = HealthTextView.this;
                healthTextView2.ad = healthTextView2.ai.width() + HealthTextView.this.getPaddingEnd();
            }
            if (HealthTextView.this.ac < HealthTextView.this.ad) {
                HealthTextView.this.t = true;
            } else {
                HealthTextView.this.t = false;
            }
            if (HealthTextView.this.t) {
                HealthTextView healthTextView3 = HealthTextView.this;
                HealthTextView.b(healthTextView3, healthTextView3.am);
                HealthTextView healthTextView4 = HealthTextView.this;
                healthTextView4.ad = healthTextView4.ac + HealthTextView.e + HealthTextView.this.ai.width();
                return;
            }
            HealthTextView healthTextView5 = HealthTextView.this;
            HealthTextView.e(healthTextView5, healthTextView5.am);
            HealthTextView healthTextView6 = HealthTextView.this;
            healthTextView6.ac = healthTextView6.ad + HealthTextView.e + HealthTextView.this.ai.width();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        TimerTask timerTask = this.as;
        if (timerTask != null) {
            timerTask.cancel();
            this.as = null;
        }
        Timer timer = this.ar;
        if (timer != null) {
            timer.cancel();
            this.ar = null;
        }
    }

    public int getTextLine() {
        return this.ak;
    }

    public float getTextWidth() {
        TextPaint paint = getPaint();
        if (paint == null) {
            LogUtil.h("LastBottomBaseLineTextView", "getTextWidth textPaint is null");
            return 0.0f;
        }
        return paint.measureText(String.valueOf(getText()));
    }

    public float getMeasureWidth() {
        TextPaint paint = getPaint();
        if (paint == null) {
            LogUtil.h("LastBottomBaseLineTextView", "getMeasureWidth textPaint is null");
            return 0.0f;
        }
        return paint.measureText(String.valueOf(getText()));
    }

    public void setZoomSize(int i, float f) {
        if (f <= 0.0f) {
            ReleaseLogUtil.a("LastBottomBaseLineTextView", "setZoomSize zoomFactor ", Float.valueOf(f), " dimensionResourcesId ", Integer.valueOf(i));
            return;
        }
        int b = nsf.b(i);
        if (b <= 0) {
            ReleaseLogUtil.a("LastBottomBaseLineTextView", "setZoomSize dimensionPixelSize ", Integer.valueOf(b), " dimensionResourcesId ", Integer.valueOf(i), " zoomFactor ", Float.valueOf(f));
        } else {
            setTextSize(0, Math.min(getTextSize(), b * f));
        }
    }
}
