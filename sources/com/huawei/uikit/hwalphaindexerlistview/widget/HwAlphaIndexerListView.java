package com.huawei.uikit.hwalphaindexerlistview.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.huawei.health.R;
import defpackage.sjx;
import defpackage.sko;
import defpackage.skp;
import defpackage.slb;
import defpackage.slq;
import defpackage.smr;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class HwAlphaIndexerListView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f10593a;
    private Drawable aa;
    private Drawable ab;
    private boolean ac;
    private int ad;
    private int ae;
    private int af;
    private boolean ag;
    private int ah;
    private boolean ai;
    private String[] aj;
    private int ak;
    private String[] al;
    private int am;
    private d[] an;
    private Paint ao;
    private int ap;
    private String aq;
    private int ar;
    private String as;
    private int at;
    private String au;
    private String av;
    private String aw;
    private boolean ax;
    private int ay;
    private ValueAnimator az;
    private int b;
    private List<String> ba;
    private List<String> bb;
    private int bc;
    private int bd;
    private int bh;
    private int c;
    Runnable d;
    private int e;
    private int f;
    private int g;
    private float h;
    private float i;
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private Context p;
    private ListView q;
    private OnItemClickListener r;
    private PopupWindow s;
    private boolean t;
    private Handler u;
    private TextView v;
    private Drawable w;
    private Paint x;
    private Map<String, String> y;
    private Drawable z;

    public interface OnItemClickListener {
        void onItemClick(String str, int i);
    }

    class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f10594a;

        a(int i) {
            this.f10594a = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            HwAlphaIndexerListView.this.an[this.f10594a].c = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            HwAlphaIndexerListView.this.invalidate();
        }
    }

    class b extends View.AccessibilityDelegate {
        private b() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (view == null || accessibilityEvent == null) {
                return;
            }
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            if (HwAlphaIndexerListView.this.aw == null) {
                return;
            }
            String str = (String) HwAlphaIndexerListView.this.y.get(HwAlphaIndexerListView.this.aw);
            accessibilityEvent.getText().add(HwAlphaIndexerListView.this.aw);
            accessibilityEvent.setContentDescription(str);
        }

        /* synthetic */ b(HwAlphaIndexerListView hwAlphaIndexerListView, e eVar) {
            this();
        }
    }

    class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            HwAlphaIndexerListView.this.at = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            HwAlphaIndexerListView.this.invalidate();
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (HwAlphaIndexerListView.this.s != null) {
                HwAlphaIndexerListView.this.s.dismiss();
            }
        }
    }

    public HwAlphaIndexerListView(Context context) {
        this(context, null);
    }

    private int getHighlightPos() {
        if (this.aw == null) {
            return -1;
        }
        int size = this.ba.size();
        for (int i = 0; i < size; i++) {
            if (b(this.ba.get(i), this.aw, i)) {
                return i;
            }
        }
        return -1;
    }

    private int getSizeNum() {
        if (this.j == 0) {
            return 0;
        }
        int paddingBottom = (((this.bd - (this.c * 2)) - getPaddingBottom()) - getPaddingTop()) / this.j;
        int floor = (int) Math.floor(this.m ? paddingBottom - 2 : paddingBottom - 1);
        int[] iArr = {26, 18, 14, 10, 6};
        for (int i = 1; i < 5; i++) {
            int i2 = iArr[0];
            if (floor >= i2 || (floor < iArr[i - 1] && floor >= (i2 = iArr[i]))) {
                floor = i2;
                break;
            }
        }
        if (floor > 6) {
            return floor;
        }
        return 6;
    }

    public boolean b() {
        return this.l;
    }

    public void c(boolean z, boolean z2) {
        String[] strArr = (String[]) skp.d().h().toArray(new String[0]);
        String[] a2 = a(strArr);
        String[] strArr2 = (String[]) skp.d().j().toArray(new String[0]);
        int sizeNum = getSizeNum();
        if (this.m) {
            e(z, z2, sizeNum, strArr, strArr2);
        } else {
            if (sizeNum == 26) {
                if (z && !this.ac) {
                    a2 = strArr2;
                }
            } else if (a2.length > sizeNum || !this.ac) {
                skp.d();
                a2 = (String[]) c(skp.b(sizeNum, Arrays.asList(a2))).toArray(new String[0]);
            }
            int length = a2.length;
            String[] strArr3 = new String[length + 1];
            this.al = strArr3;
            if (z2) {
                strArr3[length] = "#";
                System.arraycopy(a2, 0, strArr3, 0, a2.length);
            } else {
                strArr3[0] = "#";
                System.arraycopy(a2, 0, strArr3, 1, a2.length);
            }
        }
        if (!this.m || this.l) {
            this.ba = new ArrayList(Arrays.asList(this.al));
        } else {
            this.ba = new ArrayList(Arrays.asList(this.aj));
        }
        this.k = z;
        a();
        invalidate();
    }

    public boolean c(int i) {
        if (this.m) {
            if (i == this.ay && !this.l) {
                return true;
            }
            if (i == (this.n ? this.ba.size() - 2 : this.ba.size() - 1) && this.l) {
                return true;
            }
        }
        return false;
    }

    public void d() {
        if (this.t) {
            this.u.postDelayed(this.d, 800L);
        }
    }

    public void d(String str) {
        this.au = this.av;
        this.av = str;
        if (this.t) {
            this.u.removeCallbacks(this.d);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131363890_res_0x7f0a0832);
            if (this.s == null) {
                TextView textView = new TextView(getContext());
                this.v = textView;
                textView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen._2131363892_res_0x7f0a0834));
                Drawable drawable = this.w;
                if (drawable != null) {
                    this.v.setBackground(drawable);
                }
                this.v.setTextColor(this.f);
                this.v.setTypeface(Typeface.create(getResources().getString(R.string._2130851233_res_0x7f0235a1), 0));
                this.v.setGravity(17);
                PopupWindow popupWindow = new PopupWindow(this.v, dimensionPixelSize, dimensionPixelSize);
                this.s = popupWindow;
                popupWindow.setAnimationStyle(R.style.Animation_Emui_HwAlphaIndexerListView_PopupWindow);
                if (Build.VERSION.SDK_INT >= 29) {
                    slq slqVar = new slq(this.p, this.v, this.ah, this.ae);
                    slqVar.a(this.ai);
                    this.v.setOutlineSpotShadowColor(this.ak);
                    slqVar.d(this.ag);
                }
            }
            int i = this.bc;
            boolean z = i == -1 && str != null;
            boolean z2 = i != -1 && i < this.ba.size() && b(this.ba.get(this.bc), str, this.bc);
            if (z || z2) {
                this.v.setText(str);
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen._2131363891_res_0x7f0a0833);
                int[] iArr = new int[2];
                getLocationInWindow(iArr);
                int i2 = iArr[0];
                this.s.showAtLocation(getRootView(), 8388627, getParent().getLayoutDirection() == 1 ? this.e + i2 + dimensionPixelSize2 : (i2 - dimensionPixelSize2) - dimensionPixelSize, 0);
            }
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        boolean z = ((float) this.f10593a) > y || ((float) (getHeight() - this.bh)) < y;
        if ((action == 0 || action == 2) && z) {
            return true;
        }
        int d2 = d(y);
        if (action == 0) {
            if (d2 >= 0 && d2 < this.ba.size()) {
                a(d2);
            }
            if (isHapticFeedbackEnabled()) {
                this.h = motionEvent.getY();
            }
        } else if (action == 1) {
            d();
            this.bc = -1;
            invalidate();
        } else if (action == 2) {
            a(motionEvent, d2);
        } else if (action == 3) {
            d();
            return false;
        }
        return true;
    }

    public boolean e(String str, String str2) {
        if (str == null || str2 == null) {
            Log.w("HwAlphaIndexerListView", "equalsChar: indexer or section is null!");
            return false;
        }
        if (str.length() != str2.length()) {
            return false;
        }
        Collator collator = Collator.getInstance();
        collator.setStrength(0);
        return collator.equals(str, str2);
    }

    public int getGravity() {
        return this.af;
    }

    public boolean getLabelShadowClip() {
        return this.ag;
    }

    public int getLabelShadowColor() {
        return this.ak;
    }

    public int getLabelShadowSize() {
        return this.ah;
    }

    public int getLabelShadowStyle() {
        return this.ae;
    }

    public Drawable getPopupWindowBgDrawable() {
        return this.w;
    }

    public Object[] getSections() {
        ListView listView = this.q;
        if (listView != null) {
            ListAdapter adapter = listView.getAdapter();
            if (adapter instanceof HwSortedTextListAdapter) {
                return ((HwSortedTextListAdapter) adapter).getSections();
            }
        }
        return new String[0];
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        PopupWindow popupWindow = this.s;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            Log.w("HwAlphaIndexerListView", "onDraw: canvas is null!");
            return;
        }
        super.onDraw(canvas);
        e();
        int size = this.ba.size();
        int i = this.bc;
        if (i == -1) {
            i = getHighlightPos();
        }
        int width = (int) ((getWidth() - this.j) / 2.0f);
        dZp_(canvas, size, width);
        for (int i2 = 0; i2 < size; i2++) {
            this.x.setColor(this.b);
            this.x.setTypeface(Typeface.create(getResources().getString(R.string._2130851234_res_0x7f0235a2), 0));
            int i3 = (this.j * i2) + this.f10593a;
            if (i2 == i) {
                this.x.setColor(this.g);
                this.x.setTypeface(Typeface.create(getResources().getString(R.string._2130851233_res_0x7f0235a1), 0));
                if (isFocused() && hasWindowFocus()) {
                    dZq_(canvas, this.aa, width, i3);
                } else {
                    dZq_(canvas, this.z, width, i3);
                }
            }
            int i4 = this.ad;
            if (i4 == i2 && i4 != i) {
                this.x.setTypeface(Typeface.create(getResources().getString(R.string._2130851233_res_0x7f0235a1), 0));
            }
            if (i2 != i) {
                dZn_(i2, canvas, width, i3);
            }
            String replace = this.ba.get(i2).replace("劃", "");
            float measureText = (this.j - this.x.measureText(replace)) / 2.0f;
            Paint.FontMetrics fontMetrics = this.x.getFontMetrics();
            canvas.drawText(replace, width + measureText, (i3 + (this.j / 2.0f)) - ((fontMetrics.ascent + fontMetrics.descent) / 2.0f), this.x);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            b(d(motionEvent.getY()));
        } else if (action == 10) {
            b(-1);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent == null || this.ba == null || this.q == null) {
            return false;
        }
        Object[] sections = getSections();
        if (!(sections instanceof String[])) {
            return false;
        }
        String[] strArr = (String[]) sections;
        int i2 = this.bc;
        if (i2 == -1) {
            i2 = getHighlightPos();
        }
        if (i == 19) {
            a(b(strArr, i2));
        } else if (i == 20) {
            a(e(strArr, i2));
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 20 || i == 19) {
            d();
            this.bc = -1;
            invalidate();
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            size = this.e;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.bd = getMeasuredHeight();
        c(this.k, this.n);
    }

    public void setGravity(int i) {
        this.af = i;
    }

    public void setInactiveAlphaColor(int i) {
        this.b = i;
    }

    public void setLabelShadowClip(boolean z) {
        this.ag = z;
    }

    public void setLabelShadowColor(int i) {
        this.ak = i;
    }

    public void setLabelShadowEnabled(boolean z) {
        this.ai = z;
    }

    public void setLabelShadowSize(int i) {
        this.ah = i;
    }

    public void setLabelShadowStyle(int i) {
        this.ae = i;
    }

    public void setListViewAttachTo(ListView listView) {
        this.q = listView;
        if (listView == null || this.o) {
            return;
        }
        ListAdapter adapter = listView.getAdapter();
        if (adapter instanceof HwSortedTextListAdapter) {
            this.n = ((HwSortedTextListAdapter) adapter).isDigitLast();
        }
        c(this.k, this.n);
        this.o = true;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.r = onItemClickListener;
    }

    public void setOverLayInfo(String str) {
        if (str == null) {
            Log.w("HwAlphaIndexerListView", "setOverLayInfo: sectionName is null!");
            return;
        }
        if ("".equals(str)) {
            this.aw = "@";
            return;
        }
        if (str.equals(this.aw)) {
            this.aw = str;
        } else {
            this.aw = str;
            sendAccessibilityEvent(16384);
        }
        h();
    }

    public void setPopupTextColor(int i) {
        this.f = i;
    }

    public void setPopupWindowBgDrawable(Drawable drawable) {
        this.w = drawable;
    }

    public void setSelectedAlphaColor(int i) {
        this.g = i;
    }

    public void setShowPopup(boolean z) {
        this.t = z;
    }

    public void setSupportCompactMode(boolean z) {
        this.ac = z;
    }

    class d {
        int c;
        ValueAnimator e;

        private d() {
            this.c = 0;
        }

        /* synthetic */ d(HwAlphaIndexerListView hwAlphaIndexerListView, e eVar) {
            this();
        }
    }

    public HwAlphaIndexerListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100180_res_0x7f060214);
    }

    private void a() {
        int size = this.ba.size();
        this.an = new d[size];
        for (int i = 0; i < size; i++) {
            this.an[i] = new d(this, null);
        }
    }

    private void e(int i) {
        List<String> list;
        if (this.ab == null || (list = this.ba) == null) {
            return;
        }
        boolean z = i >= 0 && i < list.size();
        if (z != this.ax) {
            ValueAnimator valueAnimator = this.az;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.az.cancel();
            }
            this.ax = z;
            ValueAnimator dYk_ = sjx.dYk_(this.at, !z);
            this.az = dYk_;
            dYk_.addUpdateListener(new c());
            this.az.start();
        }
    }

    private void h() {
        if (this.m) {
            if (f()) {
                if (this.l) {
                    return;
                }
                a(false);
            } else if (this.l) {
                a(true);
            }
        }
    }

    public HwAlphaIndexerListView(Context context, AttributeSet attributeSet, int i) {
        super(a(context, i), attributeSet, i);
        this.d = new e();
        this.as = "A";
        this.aq = "Z";
        this.bb = new ArrayList(10);
        this.ba = new ArrayList(10);
        this.ay = -1;
        this.bc = -1;
        this.f = -1;
        this.h = 0.0f;
        this.k = false;
        this.o = false;
        this.n = false;
        this.t = true;
        this.x = new Paint();
        this.u = new Handler();
        this.y = new HashMap();
        this.ad = -1;
        this.ap = 0;
        this.ar = 0;
        this.ao = new Paint();
        this.at = 0;
        this.ax = false;
        dZo_(super.getContext(), attributeSet, i);
        setDefaultFocusHighlightEnabled(false);
    }

    private static Context a(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwAlphaIndexerListView);
    }

    private void dZo_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.gravity, R.attr._2131100334_res_0x7f0602ae, R.attr._2131100358_res_0x7f0602c6, R.attr._2131100361_res_0x7f0602c9, R.attr._2131100372_res_0x7f0602d4, R.attr._2131100391_res_0x7f0602e7, R.attr._2131100392_res_0x7f0602e8, R.attr._2131100393_res_0x7f0602e9, R.attr._2131100427_res_0x7f06030b, R.attr._2131100429_res_0x7f06030d, R.attr._2131100511_res_0x7f06035f, R.attr._2131100513_res_0x7f060361, R.attr._2131100558_res_0x7f06038e, R.attr._2131100606_res_0x7f0603be}, i, R.style.Widget_Emui_HwAlphaIndexerListView);
        this.f = obtainStyledAttributes.getColor(9, -16777216);
        this.w = obtainStyledAttributes.getDrawable(8);
        this.b = obtainStyledAttributes.getColor(4, -16777216);
        this.g = obtainStyledAttributes.getColor(10, -16776961);
        this.ab = obtainStyledAttributes.getDrawable(2);
        this.aa = obtainStyledAttributes.getDrawable(1);
        this.z = obtainStyledAttributes.getDrawable(11);
        this.ac = obtainStyledAttributes.getBoolean(12, false);
        this.af = obtainStyledAttributes.getInt(0, 17);
        this.ai = obtainStyledAttributes.getBoolean(6, false);
        this.ae = obtainStyledAttributes.getInt(13, 0);
        this.ah = obtainStyledAttributes.getInt(7, 3);
        this.ak = obtainStyledAttributes.getColor(5, -16777216);
        int color = obtainStyledAttributes.getColor(3, 0);
        obtainStyledAttributes.recycle();
        this.ao.setColor(color);
        this.ap = Color.alpha(color);
        try {
            this.am = getResources().getDimensionPixelSize(R.dimen._2131363893_res_0x7f0a0835);
        } catch (Resources.NotFoundException unused) {
            Log.d("HwAlphaIndexerListView", "initHoverStatus: resource radius not found");
        }
        this.p = context;
        Resources resources = context.getResources();
        this.m = sko.e().a(this.as) != 1;
        this.k = resources.getConfiguration().orientation == 2;
        this.c = resources.getDimensionPixelSize(R.dimen._2131363889_res_0x7f0a0831);
        this.e = resources.getDimensionPixelSize(R.dimen._2131363897_res_0x7f0a0839);
        this.j = resources.getDimensionPixelSize(R.dimen._2131363895_res_0x7f0a0837);
        this.i = resources.getDimensionPixelSize(R.dimen._2131363896_res_0x7f0a0838);
        this.x.setAntiAlias(true);
        this.x.setTextSize(this.i);
        setContentDescription(getContext().getResources().getString(R.string._2130851232_res_0x7f0235a0));
        setAccessibilityDelegate(new b(this, null));
        for (String str : skp.b()) {
            this.y.put(str, str.toLowerCase(Locale.ENGLISH));
        }
    }

    private int b(String[] strArr, int i) {
        int i2;
        if (i == -1) {
            return -1;
        }
        for (int i3 = 1; i3 <= i; i3++) {
            for (int length = strArr.length - 1; length >= 0; length--) {
                int i4 = i - i3;
                if (this.ba.get(i4).equals(strArr[length])) {
                    return i4;
                }
                if ("•".equals(this.ba.get(i4)) && i4 - 1 >= 0 && this.ba.get(i2).compareTo(strArr[length]) < 0 && this.ba.get(i4 + 1).compareTo(strArr[length]) > 0) {
                    return i4;
                }
            }
        }
        return -1;
    }

    public boolean b(String str, String str2, int i) {
        boolean z = str == null || str2 == null;
        boolean z2 = i < 0 || i >= this.ba.size();
        if (!z && !z2) {
            if (!str.equals("•")) {
                return e(str, str2);
            }
            this.bb.clear();
            if (!this.n) {
                this.bb.add("#");
            }
            int sizeNum = getSizeNum();
            List<String> i2 = skp.d().i();
            skp.d();
            List<String> b2 = skp.b();
            List<String> a2 = skp.d().a();
            if (this.m) {
                a(sizeNum, i2, b2, a2);
            } else if (sizeNum == 26) {
                if (this.k) {
                    this.bb.addAll(a2);
                } else {
                    this.bb.addAll(i2);
                }
            } else if (sizeNum == 18) {
                this.bb.addAll(a2);
            } else {
                List<String> list = this.bb;
                skp.d();
                list.addAll(skp.b(sizeNum, i2));
            }
            if (this.n) {
                this.bb.add("#");
            }
            for (String str3 : this.bb.get(i).split(" ")) {
                if (e(str3, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setOverLayInfo(int i, String str) {
        this.bc = i;
        setOverLayInfo(str);
    }

    private void a(int i) {
        List<String> list;
        if (this.r == null || (list = this.ba) == null || i < 0 || i >= list.size()) {
            return;
        }
        this.r.onItemClick(this.ba.get(i), i);
        invalidate();
    }

    private boolean f() {
        if (this.aw == null) {
            return false;
        }
        Object[] sections = getSections();
        String str = this.aw;
        if (sections != null && "#".equals(str)) {
            if (this.n) {
                return false;
            }
            if (sections.length > 1) {
                Object obj = sections[1];
                if (obj instanceof String) {
                    str = (String) obj;
                }
            }
        }
        return Collator.getInstance().compare(str, this.as) < 0;
    }

    public void c() {
        d(this.aw);
    }

    private void e(boolean z, boolean z2, int i, String[] strArr, String[] strArr2) {
        String[] strArr3 = (String[]) skp.e().toArray(new String[0]);
        String[] strArr4 = (String[]) skp.b().toArray(new String[0]);
        String[] a2 = a(strArr);
        String[] a3 = a(strArr4);
        if (i == 26) {
            if (z) {
                boolean z3 = this.ac;
                if (z3) {
                    strArr2 = a2;
                }
                if (z3) {
                    a2 = strArr2;
                }
                a3 = strArr3;
            }
            strArr3 = a3;
            strArr2 = a2;
            a3 = strArr3;
        } else {
            skp.d();
            List<String> b2 = skp.b(i, Arrays.asList(a2));
            skp.d();
            List<String> b3 = skp.b(i, Arrays.asList(a3));
            String[] strArr5 = (String[]) c(b2).toArray(new String[0]);
            String[] strArr6 = (String[]) c(b3).toArray(new String[0]);
            if (i < a2.length || !this.ac) {
                a2 = strArr5;
            }
            if (i < a3.length || !this.ac) {
                a3 = strArr6;
            }
            strArr2 = a2;
        }
        c(z2, strArr, strArr2, a3);
    }

    private void c(boolean z, String[] strArr, String[] strArr2, String[] strArr3) {
        int length = strArr2.length;
        String[] strArr4 = new String[length + 2];
        this.al = strArr4;
        int length2 = strArr3.length;
        String[] strArr5 = new String[length2 + 2];
        this.aj = strArr5;
        if (z) {
            strArr4[length + 1] = "#";
            strArr5[length2 + 1] = "#";
            System.arraycopy(strArr2, 0, strArr4, 0, strArr2.length);
            this.ay = 0;
            String[] strArr6 = this.aj;
            strArr6[0] = strArr[0];
            System.arraycopy(strArr3, 0, strArr6, 1, strArr3.length);
            String[] strArr7 = this.al;
            int length3 = strArr7.length;
            String[] strArr8 = this.aj;
            strArr7[length3 - 2] = strArr8[strArr8.length - 2];
            return;
        }
        strArr4[0] = "#";
        strArr5[0] = "#";
        System.arraycopy(strArr2, 0, strArr4, 1, strArr2.length);
        this.ay = 1;
        String[] strArr9 = this.aj;
        strArr9[1] = strArr[0];
        System.arraycopy(strArr3, 0, strArr9, 2, strArr3.length);
        String[] strArr10 = this.al;
        int length4 = strArr10.length;
        String[] strArr11 = this.aj;
        strArr10[length4 - 1] = strArr11[strArr11.length - 1];
    }

    private String[] a(String[] strArr) {
        Object[] sections = getSections();
        if (!this.ac || !(sections instanceof String[])) {
            return strArr;
        }
        String[] strArr2 = (String[]) sections;
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < strArr.length; i++) {
            for (String str : strArr2) {
                if (strArr[i].equals(str)) {
                    arrayList.add(strArr[i]);
                }
            }
        }
        return arrayList.size() > 0 ? (String[]) arrayList.toArray(new String[0]) : strArr;
    }

    private List<String> c(List<String> list) {
        ArrayList arrayList = new ArrayList(10);
        for (String str : list) {
            if (str.split(" ").length > 1) {
                arrayList.add("•");
            } else {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private void dZp_(Canvas canvas, int i, int i2) {
        Drawable drawable = this.ab;
        if (drawable == null || this.at == 0) {
            return;
        }
        int i3 = this.c;
        int i4 = this.f10593a;
        int i5 = this.j;
        drawable.setBounds(i2 - i3, i4 - i3, i2 + i5 + i3, i4 + (i * i5) + i3);
        this.ab.setAlpha(this.at);
        this.ab.draw(canvas);
    }

    private void dZn_(int i, Canvas canvas, int i2, int i3) {
        int i4;
        d[] dVarArr = this.an;
        if (dVarArr == null || i >= dVarArr.length || (i4 = dVarArr[i].c) == 0) {
            return;
        }
        this.ao.setAlpha(i4);
        int i5 = this.j;
        RectF rectF = new RectF(i2, i3, i2 + i5, i3 + i5);
        float f = this.am;
        canvas.drawRoundRect(rectF, f, f, this.ao);
    }

    private void dZq_(Canvas canvas, Drawable drawable, int i, int i2) {
        if (drawable != null) {
            int i3 = this.j;
            drawable.setBounds(i, i2, i + i3, i3 + i2);
            drawable.draw(canvas);
        }
    }

    private void e() {
        int size = this.ba.size();
        int i = this.bd;
        int i2 = size * this.j;
        if (i <= i2) {
            int i3 = this.c;
            this.f10593a = i3;
            this.bh = i3;
            return;
        }
        int i4 = this.af;
        if (i4 == 48) {
            int i5 = this.c;
            this.f10593a = i5;
            this.bh = (i - i2) - i5;
        } else if (i4 != 80) {
            int i6 = (int) ((i - i2) / 2.0f);
            this.bh = i6;
            this.f10593a = i6;
        } else {
            int i7 = this.c;
            this.bh = i7;
            this.f10593a = (i - i2) - i7;
        }
    }

    private int e(String[] strArr, int i) {
        int i2;
        if (i == -1) {
            return -1;
        }
        for (int i3 = 1; i3 < this.ba.size() - i; i3++) {
            for (int i4 = 0; i4 < strArr.length; i4++) {
                int i5 = i + i3;
                if (this.ba.get(i5).equals(strArr[i4])) {
                    return i5;
                }
                if ("•".equals(this.ba.get(i5)) && (i2 = i5 + 1) < this.ba.size() && this.ba.get(i5 - 1).compareTo(strArr[i4]) < 0 && this.ba.get(i2).compareTo(strArr[i4]) > 0) {
                    return i5;
                }
            }
        }
        return -1;
    }

    private void b(int i) {
        if (i != this.ad) {
            a(i, this.ap);
            a(this.ad, this.ar);
            this.ad = i;
            e(i);
        }
    }

    private void a(int i, int i2) {
        d[] dVarArr;
        if (i < 0 || i >= this.ba.size() || (dVarArr = this.an) == null || i >= dVarArr.length) {
            return;
        }
        ValueAnimator valueAnimator = dVarArr[i].e;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        ValueAnimator dYj_ = sjx.dYj_(this.an[i].c, i2);
        dYj_.addUpdateListener(new a(i));
        this.an[i].e = dYj_;
        dYj_.start();
    }

    private int d(float f) {
        int height = getHeight();
        int i = this.f10593a;
        int i2 = (height - i) - this.bh;
        if (i2 == 0) {
            return 0;
        }
        return (int) (((f - i) * this.ba.size()) / i2);
    }

    private void a(MotionEvent motionEvent, int i) {
        if (i < 0 || i >= this.ba.size()) {
            return;
        }
        a(i);
        if (isHapticFeedbackEnabled()) {
            String str = this.au;
            if (str == null || !str.equals(this.av)) {
                slb.ebC_(this, 7, 0);
                this.au = this.av;
            }
        }
    }

    private void a(boolean z) {
        Animation loadAnimation;
        this.ba.clear();
        this.ba = z ? new ArrayList(Arrays.asList(this.aj)) : new ArrayList(Arrays.asList(this.al));
        this.l = !z;
        if (z) {
            loadAnimation = AnimationUtils.loadAnimation(this.p, R.anim._2130772033_res_0x7f010041);
        } else {
            loadAnimation = AnimationUtils.loadAnimation(this.p, R.anim._2130772034_res_0x7f010042);
        }
        setAnimation(loadAnimation);
        e();
        startAnimation(loadAnimation);
    }

    private void a(int i, List<String> list, List<String> list2, List<String> list3) {
        if (this.l) {
            if (i == 26) {
                if (this.k) {
                    this.bb.addAll(list3);
                } else {
                    this.bb.addAll(list);
                }
            } else if (i == 18) {
                this.bb.addAll(list3);
            } else {
                List<String> list4 = this.bb;
                skp.d();
                list4.addAll(skp.b(i, list));
            }
            this.bb.add(this.aq);
            return;
        }
        int i2 = this.ay;
        if (i2 >= 0) {
            String[] strArr = this.al;
            if (i2 < strArr.length) {
                this.bb.add(strArr[i2]);
            }
        }
        if (i == 26) {
            if (this.k) {
                this.bb.addAll(skp.c());
                return;
            } else {
                this.bb.addAll(list2);
                return;
            }
        }
        if (i == 18) {
            this.bb.addAll(skp.c());
            return;
        }
        List<String> list5 = this.bb;
        skp.d();
        list5.addAll(skp.b(i, list2));
    }
}
