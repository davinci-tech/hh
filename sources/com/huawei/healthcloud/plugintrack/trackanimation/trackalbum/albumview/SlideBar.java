package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.BidiFormatter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hcn;
import defpackage.hcr;
import defpackage.hji;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class SlideBar extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f3620a;
    private int aa;
    private int ab;
    private Drawable ac;
    private Drawable ad;
    private int ae;
    private int af;
    private int ag;
    private int ah;
    private int ai;
    private Drawable aj;
    private Scroller ak;
    private Drawable al;
    private Paint am;
    private Drawable an;
    private int ao;
    private int ap;
    private ReTrackSimplify aq;
    private VelocityTracker ar;
    private Drawable as;
    private String at;
    private int au;
    private int b;
    private Map<Integer, InterfaceUpdateReTrack.MarkerType> c;
    private int d;
    private int e;
    private Drawable f;
    private int g;
    private Drawable h;
    private int i;
    private Drawable j;
    private Map<Integer, String> k;
    private int l;
    private Drawable m;
    private int n;
    private Map<Integer, ArrayList<PhotoModel>> o;
    private Map<Integer, ArrayList<VideoModel>> p;
    private int q;
    private boolean r;
    private int s;
    private boolean t;
    private HashMap<Drawable, String> u;
    private Drawable v;
    private HashMap<Drawable, Integer> w;
    private Drawable x;
    private Drawable y;
    private Drawable z;

    public SlideBar(Context context) {
        this(context, null);
    }

    public SlideBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = new HashMap<>();
        this.u = new HashMap<>();
        this.k = new LinkedHashMap();
        this.p = new LinkedHashMap();
        this.o = new LinkedHashMap();
        this.c = new LinkedHashMap();
        this.r = false;
        this.t = false;
        this.aq = null;
        e(context);
        aZN_(context, attributeSet);
        d(context);
        setWillNotDraw(false);
        c(context);
        this.r = LanguageUtil.bc(context);
        if (nsn.ac(context)) {
            this.t = true;
        }
    }

    private void c(Context context) {
        if (nsn.v(context)) {
            this.e = 234881023;
        } else {
            this.e = 218103808;
        }
    }

    private void e(Context context) {
        VelocityTracker obtain = VelocityTracker.obtain();
        this.ar = obtain;
        if (obtain == null) {
            Log.e("SlideBar", "SlideBar:mVelocityTracker is null");
            return;
        }
        this.am = new Paint(1);
        this.ak = new Scroller(context);
        this.aa = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.af = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    private void aZN_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099717_res_0x7f060045, R.attr._2131099718_res_0x7f060046, R.attr._2131099719_res_0x7f060047, R.attr._2131099789_res_0x7f06008d, R.attr._2131100614_res_0x7f0603c6, R.attr._2131100845_res_0x7f0604ad, R.attr._2131100846_res_0x7f0604ae, R.attr._2131101310_res_0x7f06067e});
        int i = getResources().getDisplayMetrics().widthPixels;
        this.au = i;
        this.ab = i / 2;
        this.ap = obtainStyledAttributes.getLayoutDimension(7, hcn.a(context, 40.0f));
        this.g = obtainStyledAttributes.getLayoutDimension(3, hcn.a(context, 66.0f));
        this.ae = obtainStyledAttributes.getLayoutDimension(6, hcn.a(context, 1.0f));
        this.ag = obtainStyledAttributes.getLayoutDimension(5, hcn.a(context, 6.0f));
        this.b = obtainStyledAttributes.getLayoutDimension(0, hcn.a(context, 36.0f));
        this.f3620a = obtainStyledAttributes.getLayoutDimension(2, hcn.a(context, 0.0f));
        this.n = obtainStyledAttributes.getLayoutDimension(4, hcn.a(context, 12.0f));
        this.d = obtainStyledAttributes.getLayoutDimension(1, hcn.a(context, 18.0f));
        obtainStyledAttributes.recycle();
    }

    private void d(Context context) {
        this.ac = ContextCompat.getDrawable(context, R.drawable._2131431967_res_0x7f0b121f);
        this.an = ContextCompat.getDrawable(context, R.drawable._2131431969_res_0x7f0b1221);
        this.m = ContextCompat.getDrawable(context, R.drawable._2131431962_res_0x7f0b121a);
        this.v = ContextCompat.getDrawable(getContext(), R.drawable._2131431961_res_0x7f0b1219);
        this.x = ContextCompat.getDrawable(getContext(), R.drawable.trackalbum_slidebar_heartrate);
        this.ad = ContextCompat.getDrawable(getContext(), R.drawable._2131431968_res_0x7f0b1220);
        this.z = ContextCompat.getDrawable(getContext(), R.drawable._2131431964_res_0x7f0b121c);
        this.y = ContextCompat.getDrawable(getContext(), R.drawable._2131431960_res_0x7f0b1218);
        this.aj = ContextCompat.getDrawable(getContext(), R.drawable._2131431971_res_0x7f0b1223);
        this.al = ContextCompat.getDrawable(getContext(), R.drawable._2131431966_res_0x7f0b121e);
        this.as = ContextCompat.getDrawable(getContext(), R.drawable._2131431973_res_0x7f0b1225);
        this.f = ContextCompat.getDrawable(getContext(), R.drawable._2131431970_res_0x7f0b1222);
        this.h = ContextCompat.getDrawable(getContext(), R.drawable._2131431965_res_0x7f0b121d);
        this.j = ContextCompat.getDrawable(getContext(), R.drawable._2131431972_res_0x7f0b1224);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.i = (getScrollX() * (this.ah - 1)) / this.s;
        aZM_(canvas);
        aZI_(canvas);
        aZL_(canvas);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.ak.computeScrollOffset()) {
            scrollTo(this.ak.getCurrX(), this.ak.getCurrY());
            postInvalidate();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        if (i <= 0) {
            d();
            i = 0;
        } else if (i >= this.s) {
            d();
            i = this.s;
        }
        if (i != getScrollX()) {
            super.scrollTo(i, i2);
        }
    }

    private void d() {
        if (this.ak.isFinished()) {
            return;
        }
        this.ak.abortAnimation();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.ar == null) {
            this.ar = VelocityTracker.obtain();
        }
        this.ar.addMovement(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            int rawX = (int) motionEvent.getRawX();
            this.l = rawX;
            this.ao = rawX;
        } else if (action == 1) {
            this.ar.computeCurrentVelocity(1000, this.aa);
            int xVelocity = (int) this.ar.getXVelocity();
            if (Math.abs(xVelocity) > this.af) {
                b(-xVelocity);
            }
            if (this.l == this.ao) {
                e();
            } else {
                b();
            }
            h();
        } else if (action == 2) {
            int rawX2 = (int) motionEvent.getRawX();
            this.ai = rawX2;
            if (Math.abs(rawX2 - this.l) < 3) {
                this.ai = this.l;
            }
            int i = this.ao - this.ai;
            if (this.r) {
                i = -i;
            }
            scrollBy(i, 0);
            this.ao = this.ai;
        } else if (action == 3) {
            d();
            b();
            h();
        }
        return true;
    }

    private void h() {
        VelocityTracker velocityTracker = this.ar;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.ar = null;
        }
    }

    private void e() {
        int i = this.ao;
        if (this.t && !this.r) {
            i -= this.au;
        }
        if (this.r) {
            i = this.au - i;
        }
        Iterator<Map.Entry<Drawable, Integer>> it = this.w.entrySet().iterator();
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            Integer value = it.next().getValue();
            if (value != null && Math.abs((getScrollX() + i) - value.intValue()) < 40 && Math.abs(i2) > Math.abs((getScrollX() + i) - value.intValue())) {
                i3 = (value.intValue() - getScrollX()) - this.ab;
                i2 = Math.abs((getScrollX() + i) - value.intValue());
            }
        }
        if (Math.abs((getScrollX() + i) - this.ab) < 40 && Math.abs(i2) > Math.abs((getScrollX() + i) - this.ab)) {
            i3 = -getScrollX();
            i2 = Math.abs((getScrollX() + i) - this.ab);
        }
        if (Math.abs(((getScrollX() + i) - this.ab) - this.s) < 40 && Math.abs(i2) > Math.abs(((getScrollX() + i) - this.ab) - this.s)) {
            i3 = this.s - getScrollX();
            i2 = Math.abs(((getScrollX() + i) - this.ab) - this.s);
        }
        Iterator<Map.Entry<Integer, InterfaceUpdateReTrack.MarkerType>> it2 = this.c.entrySet().iterator();
        while (it2.hasNext()) {
            int intValue = (it2.next().getKey().intValue() * this.s) / (this.ah - 1);
            if (Math.abs(((getScrollX() + i) - intValue) - this.ab) < 40 && Math.abs(i2) > Math.abs(((getScrollX() + i) - intValue) - this.ab)) {
                i3 = intValue - getScrollX();
                i2 = Math.abs(((getScrollX() + i) - intValue) - this.ab);
            }
        }
        if (i3 != Integer.MAX_VALUE) {
            scrollBy(i3, 0);
            this.i = (getScrollX() * (this.ah - 1)) / this.s;
        }
    }

    private void b() {
        int i = this.ab;
        int scrollX = getScrollX();
        int i2 = this.q;
        int i3 = (i + scrollX) % i2;
        if (i3 > i2) {
            scrollBy(i2 - i3, 0);
            invalidate();
        } else {
            scrollBy(-i3, 0);
        }
    }

    private void aZL_(Canvas canvas) {
        this.at = "";
        Drawable drawable = this.an;
        int i = this.ab;
        int i2 = this.n;
        int i3 = (this.ap + this.g) / 2;
        drawable.setBounds(i - i2, i3 - i2, i + i2, i3 + i2);
        this.an.draw(canvas);
        Drawable drawable2 = this.m;
        int i4 = this.ab;
        int i5 = this.s;
        int i6 = this.n;
        int i7 = i4 + i5;
        int i8 = (this.ap + this.g) / 2;
        drawable2.setBounds(i7 - i6, i8 - i6, i7 + i6, i8 + i6);
        this.m.draw(canvas);
        for (Map.Entry<Drawable, Integer> entry : this.w.entrySet()) {
            Integer value = entry.getValue();
            if (value != null) {
                int intValue = value.intValue();
                Drawable key = entry.getKey();
                int i9 = this.n;
                int i10 = (this.ap + this.g) / 2;
                key.setBounds(intValue - i9, i10 - i9, intValue + i9, i10 + i9);
                entry.getKey().draw(canvas);
                if (value.intValue() == getScrollX() + this.ab) {
                    this.at = this.u.get(entry.getKey());
                }
            }
        }
    }

    public void c() {
        if (((Integer) this.aq.getMaxAltitude().first).intValue() != -1) {
            this.w.put(this.v, Integer.valueOf(c(((Integer) this.aq.getMaxAltitude().first).intValue())));
            this.u.put(this.v, getResources().getString(R.string._2130839953_res_0x7f020991, getResources().getQuantityString(UnitUtil.h() ? R.plurals._2130903306_res_0x7f03010a : R.plurals._2130903307_res_0x7f03010b, (int) Math.ceil(((Double) this.aq.getMaxAltitude().second).doubleValue()), hcr.a(((Double) this.aq.getMaxAltitude().second).doubleValue()))));
        }
        if (((Integer) this.aq.getMaxHeartRate().first).intValue() != -1) {
            this.w.put(this.x, Integer.valueOf(c(((Integer) this.aq.getMaxHeartRate().first).intValue())));
            this.u.put(this.x, getResources().getString(R.string._2130839954_res_0x7f020992, BidiFormatter.getInstance().unicodeWrap(getResources().getQuantityString(R.plurals._2130903131_res_0x7f03005b, (int) Math.ceil(((Double) this.aq.getMaxHeartRate().second).doubleValue()), hcr.d(Math.ceil(((Double) this.aq.getMaxHeartRate().second).doubleValue()))))));
        }
        if (((Integer) this.aq.getMaxSpeed().first).intValue() != -1) {
            if (this.aq.getMaxSpeedType() == 18) {
                this.w.put(this.y, Integer.valueOf(c(((Integer) this.aq.getMaxSpeed().first).intValue())));
                this.u.put(this.y, getResources().getString(R.string._2130839955_res_0x7f020993, getResources().getQuantityString(R.plurals._2130903308_res_0x7f03010c, (int) Math.ceil(((Double) this.aq.getMaxSpeed().second).doubleValue()), hcr.e(((Double) this.aq.getMaxSpeed().second).doubleValue()))));
            } else if (this.aq.getMaxSpeedType() == 17) {
                this.w.put(this.ad, Integer.valueOf(c(((Integer) this.aq.getMaxSpeed().first).intValue())));
                this.u.put(this.ad, getResources().getString(R.string._2130839956_res_0x7f020994, getResources().getQuantityString(UnitUtil.h() ? R.plurals._2130903130_res_0x7f03005a : R.plurals._2130903309_res_0x7f03010d, (int) Math.ceil(((Double) this.aq.getMaxSpeed().second).doubleValue()), hcr.c(((Double) this.aq.getMaxSpeed().second).doubleValue()))));
            } else {
                this.w.put(this.z, Integer.valueOf(c(((Integer) this.aq.getMaxSpeed().first).intValue())));
                double doubleValue = ((Double) this.aq.getMaxSpeed().second).doubleValue();
                this.u.put(this.z, getResources().getString(R.string._2130839957_res_0x7f020995, getResources().getQuantityString(UnitUtil.h() ? R.plurals._2130903281_res_0x7f0300f1 : R.plurals._2130903280_res_0x7f0300f0, (int) Math.ceil(doubleValue), hji.c((float) doubleValue))));
            }
        }
    }

    private int c(int i) {
        return ((this.s * i) / (this.ah - 1)) + this.ab;
    }

    public void a() {
        int i = this.ah;
        if (i <= 450) {
            this.s = (i - 1) * 4;
            this.q = 4;
        } else if (i <= 650) {
            this.s = (i - 1) * 3;
            this.q = 3;
        } else {
            this.s = (i - 1) * 2;
            this.q = 2;
        }
        LogUtil.a("SlideBar", "mLengthOfSlideBar : ", Integer.valueOf(this.s), "mLineDegreeSpace : ", Integer.valueOf(this.q));
    }

    private void aZM_(Canvas canvas) {
        this.am.setAntiAlias(true);
        int i = (this.g - this.ap) / 2;
        this.am.setStyle(Paint.Style.FILL);
        this.am.setColor(this.e);
        int i2 = this.ab + this.s;
        canvas.drawArc(new RectF(i2 - i, this.ap, i2 + i, this.g), 270.0f, 180.0f, false, this.am);
        canvas.drawRect(this.ab, this.ap, r1 + this.s, this.g, this.am);
        this.am.setStyle(Paint.Style.FILL);
        this.am.setColor(-15978);
        int i3 = this.ab;
        canvas.drawArc(new RectF(i3 - i, this.ap, i3 + i, this.g), 90.0f, 180.0f, false, this.am);
        canvas.drawRect(this.ab, this.ap, r0 + getScrollX(), this.g, this.am);
        Drawable drawable = this.ac;
        int i4 = this.ab;
        int i5 = this.ae;
        drawable.setBounds((i4 - i5) + getScrollX(), this.ap - this.ag, this.ab + this.ae + getScrollX(), this.g + this.ag);
        this.ac.draw(canvas);
    }

    private void aZI_(Canvas canvas) {
        this.at = "";
        InterfaceUpdateReTrack.MarkerType markerType = null;
        for (Map.Entry<Integer, InterfaceUpdateReTrack.MarkerType> entry : this.c.entrySet()) {
            if (entry.getKey().intValue() == this.i) {
                markerType = entry.getValue();
            } else {
                aZK_(canvas, entry);
            }
        }
        if (markerType != null) {
            aZJ_(canvas, markerType);
        }
    }

    private void aZJ_(Canvas canvas, InterfaceUpdateReTrack.MarkerType markerType) {
        if (markerType == InterfaceUpdateReTrack.MarkerType.Image_type) {
            Drawable drawable = this.h;
            int scrollX = getScrollX();
            int i = this.ab;
            int i2 = this.d;
            int i3 = this.f3620a;
            int scrollX2 = getScrollX();
            int i4 = this.ab;
            drawable.setBounds((scrollX + i) - i2, i3, scrollX2 + i4 + this.d, this.b);
            this.h.draw(canvas);
            return;
        }
        if (markerType == InterfaceUpdateReTrack.MarkerType.Text_type) {
            Drawable drawable2 = this.f;
            int scrollX3 = getScrollX();
            int i5 = this.ab;
            int i6 = this.d;
            int i7 = this.f3620a;
            int scrollX4 = getScrollX();
            int i8 = this.ab;
            drawable2.setBounds((scrollX3 + i5) - i6, i7, scrollX4 + i8 + this.d, this.b);
            this.f.draw(canvas);
            return;
        }
        Drawable drawable3 = this.j;
        int scrollX5 = getScrollX();
        int i9 = this.ab;
        int i10 = this.d;
        int i11 = this.f3620a;
        int scrollX6 = getScrollX();
        int i12 = this.ab;
        drawable3.setBounds((scrollX5 + i9) - i10, i11, scrollX6 + i12 + this.d, this.b);
        this.j.draw(canvas);
    }

    private void aZK_(Canvas canvas, Map.Entry<Integer, InterfaceUpdateReTrack.MarkerType> entry) {
        int intValue = (entry.getKey().intValue() * this.s) / (this.ah - 1);
        if (entry.getValue() == InterfaceUpdateReTrack.MarkerType.Image_type) {
            Drawable drawable = this.al;
            int i = this.ab;
            int i2 = this.d;
            int i3 = intValue + i;
            drawable.setBounds(i3 - i2, this.f3620a, i3 + i2, this.b);
            this.al.draw(canvas);
            return;
        }
        if (entry.getValue() == InterfaceUpdateReTrack.MarkerType.Text_type) {
            Drawable drawable2 = this.aj;
            int i4 = this.ab;
            int i5 = this.d;
            int i6 = intValue + i4;
            drawable2.setBounds(i6 - i5, this.f3620a, i6 + i5, this.b);
            this.aj.draw(canvas);
            return;
        }
        Drawable drawable3 = this.as;
        int i7 = this.ab;
        int i8 = this.d;
        int i9 = intValue + i7;
        drawable3.setBounds(i9 - i8, this.f3620a, i9 + i8, this.b);
        this.as.draw(canvas);
    }

    private void b(int i) {
        Scroller scroller = this.ak;
        int scrollX = getScrollX();
        int i2 = this.s;
        scroller.fling(scrollX, 0, i, 0, -i2, i2, 0, 0);
    }

    public void setNumberPoints(int i) {
        this.ah = i;
    }

    public int getCurrentPointIndex() {
        return this.i;
    }

    public void setTrackSimplify(ReTrackSimplify reTrackSimplify) {
        this.aq = reTrackSimplify;
    }

    public String getWonderfulText() {
        return this.at;
    }

    public void setIndexOfText(Map<Integer, String> map) {
        this.k = map;
        setAddIconType(this.i);
        invalidate();
    }

    private void setAddIconType(int i) {
        if (this.p.containsKey(Integer.valueOf(i))) {
            if (this.c.get(Integer.valueOf(i)) != InterfaceUpdateReTrack.MarkerType.Video_type) {
                this.c.put(Integer.valueOf(i), InterfaceUpdateReTrack.MarkerType.Video_type);
            }
        } else if (this.o.containsKey(Integer.valueOf(i))) {
            if (this.c.get(Integer.valueOf(i)) != InterfaceUpdateReTrack.MarkerType.Image_type) {
                this.c.put(Integer.valueOf(i), InterfaceUpdateReTrack.MarkerType.Image_type);
            }
        } else {
            if (this.k.containsKey(Integer.valueOf(i))) {
                if (this.c.get(Integer.valueOf(i)) != InterfaceUpdateReTrack.MarkerType.Text_type) {
                    this.c.put(Integer.valueOf(i), InterfaceUpdateReTrack.MarkerType.Text_type);
                    return;
                }
                return;
            }
            this.c.remove(Integer.valueOf(i));
        }
    }

    public void setIndexOfVideo(Map<Integer, ArrayList<VideoModel>> map, int i) {
        this.p = map;
        if (i != this.i) {
            a(i);
        }
        setAddIconType(i);
        invalidate();
    }

    public void setIndexOfPhoto(Map<Integer, ArrayList<PhotoModel>> map, int i) {
        this.o = map;
        if (i != this.i) {
            a(i);
        }
        setAddIconType(i);
        invalidate();
    }

    public void setAutoMatchPhoto(int i, Map<Integer, ArrayList<PhotoModel>> map) {
        this.o = map;
        this.c.put(Integer.valueOf(i), InterfaceUpdateReTrack.MarkerType.Image_type);
    }

    public void a(int i) {
        scrollTo(i * this.q, 0);
        this.i = (getScrollX() * (this.ah - 1)) / this.s;
    }
}
