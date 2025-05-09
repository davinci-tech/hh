package com.huawei.phoneservice.feedback.widget;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.common.Logger;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.n;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.photolibrary.internal.utils.e;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class FeedbackNoticeView extends FrameLayout implements View.OnClickListener {
    private static Map<CharSequence, Integer> g = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    protected View f8301a;
    private int aa;
    private Runnable ab;
    private boolean ac;
    private int[] ad;
    private int ae;
    protected TextView b;
    protected ImageView c;
    protected c d;
    protected ProgressBar e;
    private int f;
    protected View h;
    private int i;
    protected Button j;
    private int k;
    private int l;
    private boolean m;
    private FaqConstants.FaqErrorCode n;
    private String o;
    private float p;
    private int q;
    private int r;
    private int s;
    private float t;
    private final Map<FaqConstants.FaqErrorCode, Integer> u;
    private final Map<FaqConstants.FaqErrorCode, Integer> v;
    private final Map<FaqConstants.FaqErrorCode, Integer> w;
    private int x;
    private final Map<FaqConstants.FaqErrorCode, Integer> y;
    private b z;

    public interface b {
        void a();
    }

    public enum c {
        ERROR,
        PROGRESS
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 8) {
            this.n = FaqConstants.FaqErrorCode.DEFAULT;
        }
    }

    public void setShouldHideContactUsButton(boolean z) {
        a(this.d);
    }

    public void setProgressMarginTop(int i) {
        this.aa = i;
        a(this.d);
    }

    public void setNoticeLoadingText(String str) {
        this.o = str;
    }

    public void setNoticeImageViewSize(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.c.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LinearLayout.LayoutParams(i, i);
        } else {
            layoutParams.height = i;
            layoutParams.width = i;
        }
        this.c.setLayoutParams(layoutParams);
    }

    public void setNoticeImageResource(int i) {
        this.c.setImageResource(i);
    }

    public void setCallback(b bVar) {
        this.z = bVar;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        i.d("FeedbackNoticeView", "onWindowFocusChanged");
        super.onWindowFocusChanged(z);
        getLocationOnScreen(this.ad);
        i.d("FeedbackNoticeView", "onWindowFocusChanged location 0:" + this.ad[0] + "   location 1:" + this.ad[1]);
        setContentMarginTop(this.d == c.ERROR ? this.y.get(this.n).intValue() : this.aa);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
    
        if (r0 != 3) goto L24;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            if (r0 == 0) goto L4c
            r1 = 1
            r2 = -1
            if (r0 == r1) goto L49
            r1 = 2
            r3 = 3
            if (r0 == r1) goto L13
            if (r0 == r3) goto L49
            goto L5f
        L13:
            int r0 = r4.s
            if (r0 != r2) goto L18
            goto L5f
        L18:
            int r0 = r5.findPointerIndex(r0)
            if (r0 != r2) goto L1f
            goto L5f
        L1f:
            float r1 = r5.getY(r0)
            int r1 = (int) r1
            float r0 = r5.getX(r0)
            int r0 = (int) r0
            float r2 = r4.p
            int r2 = (int) r2
            int r1 = r1 - r2
            int r1 = java.lang.Math.abs(r1)
            float r2 = r4.t
            int r2 = (int) r2
            int r0 = r0 - r2
            int r0 = java.lang.Math.abs(r0)
            int r2 = r4.x
            if (r1 > r2) goto L45
            if (r0 > r2) goto L45
            com.huawei.phoneservice.feedback.widget.FeedbackNoticeView$c r0 = r4.d
            com.huawei.phoneservice.feedback.widget.FeedbackNoticeView$c r1 = com.huawei.phoneservice.feedback.widget.FeedbackNoticeView.c.PROGRESS
            if (r0 != r1) goto L5f
        L45:
            r5.setAction(r3)
            goto L5f
        L49:
            r4.s = r2
            goto L5f
        L4c:
            float r0 = r5.getY()
            r4.p = r0
            float r0 = r5.getX()
            r4.t = r0
            r0 = 0
            int r0 = r5.getPointerId(r0)
            r4.s = r0
        L5f:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedback.widget.FeedbackNoticeView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        i.d("FeedbackNoticeView", "onSizeChanged");
        super.onSizeChanged(i, i2, i3, i4);
        getLocationOnScreen(this.ad);
        i.d("FeedbackNoticeView", "onSizeChanged location 0:" + this.ad[0] + "   location 1:" + this.ad[1]);
        boolean z = !(i4 == 0 || i2 == 0 || i2 - i4 <= this.q) || i4 == 0 || i2 == 0 || i4 - i2 <= this.q;
        i.d("FeedbackNoticeView", "onSizeChanged sizeEnough:" + z + "   contentSizeEnough:" + this.ac);
        if (z != this.ac) {
            removeCallbacks(this.ab);
            if (isAttachedToWindow()) {
                post(this.ab);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        i.d("FeedbackNoticeView", "onMeasure");
        super.onMeasure(i, i2);
        if (this.e.getMeasuredHeight() > 0 && this.k == 0) {
            this.k = this.e.getMeasuredWidth();
            int measuredHeight = this.e.getMeasuredHeight();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.e.getLayoutParams();
            int max = Math.max(measuredHeight, this.k);
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(max, max);
            } else {
                layoutParams.height = max;
                layoutParams.width = max;
            }
            i.d("FeedbackNoticeView", "onMeasure noticeProgressBar height:" + layoutParams.height + "   width:" + layoutParams.width);
            this.e.setLayoutParams(layoutParams);
        }
        if (this.f8301a.getMeasuredHeight() <= 0 || this.r != 0) {
            return;
        }
        this.r = this.h.getMeasuredHeight();
        i.d("FeedbackNoticeView", "onMeasure mBtnHeight:" + this.r);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        i.d("FeedbackNoticeView", "onLayout changed:" + z);
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            removeCallbacks(this.ab);
            boolean d = d();
            i.d("FeedbackNoticeView", "onLayout sizeEnough:" + d + "   contentSizeEnough:" + this.ac);
            if (d != this.ac) {
                this.ac = d;
            }
            setContentMarginTop(this.d == c.ERROR ? this.y.get(this.n).intValue() : this.aa);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.ab);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b bVar;
        if (s.cdx_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        FaqConstants.FaqErrorCode faqErrorCode = this.n;
        if (faqErrorCode == FaqConstants.FaqErrorCode.INTERNET_ERROR) {
            b(getContext());
        } else if (faqErrorCode == FaqConstants.FaqErrorCode.DIFFERENT_SITE && (bVar = this.z) != null) {
            bVar.a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public TextView getNoticeTextView() {
        return this.b;
    }

    public int getMinContentMarginTop() {
        float dimension = getResources().getDimension(R.dimen._2131362750_res_0x7f0a03be);
        if (this.h.getVisibility() == 0) {
            dimension = this.r + (getResources().getDimension(R.dimen._2131362750_res_0x7f0a03be) * 2.0f);
        }
        c cVar = this.d;
        c cVar2 = c.ERROR;
        float intValue = cVar == cVar2 ? this.w.get(this.n).intValue() : this.e.getHeight();
        this.i = (int) ((((getMeasuredHeight() - dimension) - a(this.b)) - getResources().getDimensionPixelSize(R.dimen._2131362796_res_0x7f0a03ec)) - intValue);
        i.d("FeedbackNoticeView", "getMinContentMarginTop noticeType：".concat(this.d == cVar2 ? Constants.LOG_ERROR : "PROGRESS"));
        i.d("FeedbackNoticeView", "getMinContentMarginTop contentMarginTop：" + Math.max(this.ae, this.i));
        i.d("FeedbackNoticeView", "getMinContentMarginTop contentImageOffset：" + intValue);
        i.d("FeedbackNoticeView", "getMinContentMarginTop getMeasuredHeight：" + getMeasuredHeight());
        i.d("FeedbackNoticeView", "getMinContentMarginTop buttonOffset：" + dimension + "    measureTextViewHeight:" + a(this.b));
        return Math.max(this.ae, this.i);
    }

    public FaqConstants.FaqErrorCode getFaqErrorCode() {
        return this.n;
    }

    public int getColorPrimary() {
        Resources resources;
        int i;
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
        i.d("getColorPrimary", "colorPrimary : " + typedValue.data + "  strColor: " + String.format("#%06X", Integer.valueOf(typedValue.data & ViewCompat.MEASURED_SIZE_MASK)));
        if (Color.parseColor("#f0f0f0") == typedValue.data) {
            resources = getResources();
            i = R.color.feedback_sdk_notice_view_pad_background;
        } else {
            resources = getResources();
            i = R.color.feedback_sdk_notice_view_background;
        }
        typedValue.data = resources.getColor(i);
        return typedValue.data;
    }

    public void c(FaqConstants.FaqErrorCode faqErrorCode, int i) {
        this.w.put(faqErrorCode, Integer.valueOf(i));
        a(this.d);
    }

    public void c(int i) {
        if (i == 0) {
            this.b.setVisibility(4);
        } else {
            this.b.setVisibility(0);
            this.b.setText(i);
        }
    }

    public void a(Throwable th) {
        Logger.d("FeedbackNoticeView", "dealWithHttpError error:%s", th);
        if (th != null) {
            if ((th instanceof FaqWebServiceException) && ((FaqWebServiceException) th).errorCode == 500002) {
                c(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR);
            } else {
                c();
            }
        }
    }

    public void a(c cVar) {
        i.d("FeedbackNoticeView", "showNoticeType type:" + cVar);
        this.d = cVar;
        if (cVar == c.ERROR) {
            c(this.n);
            return;
        }
        this.c.setVisibility(8);
        this.e.setVisibility(0);
        this.h.setVisibility(4);
        this.b.setText(this.o);
        this.ac = d();
        setContentMarginTop(this.aa);
        setVisibility(0);
    }

    public void e(FaqConstants.FaqErrorCode faqErrorCode, int i) {
        this.u.put(faqErrorCode, Integer.valueOf(i));
        a(this.d);
    }

    public void c(FaqConstants.FaqErrorCode faqErrorCode) {
        i.d("FeedbackNoticeView", "showErrorCode errorCode:" + faqErrorCode);
        this.c.setVisibility(0);
        this.e.setVisibility(8);
        int intValue = this.u.get(faqErrorCode).intValue();
        int intValue2 = this.v.get(faqErrorCode).intValue();
        setNoticeImageViewSize(this.w.get(faqErrorCode).intValue());
        setNoticeImageResource(intValue);
        c(intValue2);
        this.h.setVisibility(4);
        e(faqErrorCode, intValue, intValue2);
        if (faqErrorCode != FaqConstants.FaqErrorCode.DEFAULT) {
            setVisibility(0);
        }
        this.n = faqErrorCode;
        this.d = c.ERROR;
        this.ac = d();
        setContentMarginTop(this.y.get(faqErrorCode).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentMarginTop(int i) {
        int i2;
        i.d("FeedbackNoticeView", "setContentMarginTop param contentMarginTop:" + i);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f8301a.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        }
        if (!this.ac) {
            i = getMinContentMarginTop();
            i.d("FeedbackNoticeView", "setContentMarginTop getMinContentMarginTop:" + i);
        }
        e();
        b();
        if (getResources().getConfiguration().orientation == 2) {
            if (!this.ac) {
                this.f = Math.min(i, this.f);
            }
            i2 = this.f;
            if (i2 <= 0) {
                i2 = 0;
            }
        } else {
            i2 = this.l;
        }
        layoutParams.topMargin = i2;
        if (this.ac || this.i >= this.ae || this.h.getVisibility() != 0) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(4);
        }
        i.d("FeedbackNoticeView", "setContentMarginTop topMargin:" + layoutParams.topMargin);
        this.f8301a.setLayoutParams(layoutParams);
    }

    private int getAutoFitContentSize() {
        int intValue = this.d == c.ERROR ? this.w.get(this.n).intValue() : this.e.getHeight();
        float a2 = a(this.b) + getResources().getDimensionPixelSize(R.dimen._2131362796_res_0x7f0a03ec);
        i.d("FeedbackNoticeView", "getAutoFitContentSize:   contentImageOffset:" + intValue + "   measureTextViewHeight(noticeTextView):" + a(this.b) + "   DimensionPixelSize" + getResources().getDimensionPixelSize(R.dimen._2131362796_res_0x7f0a03ec) + "   textView Height :" + this.b.getMeasuredHeight());
        return (int) (intValue + a2 + 0.5d);
    }

    private void c() {
        c(!com.huawei.phoneservice.faq.base.util.b.b(getContext()) ? FaqConstants.FaqErrorCode.INTERNET_ERROR : FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        i.d("FeedbackNoticeView", "checkContentSize mBtnHeight:" + this.r);
        if (this.r > 0) {
            c cVar = this.d;
            c cVar2 = c.ERROR;
            float measuredHeight = (((getMeasuredHeight() - a(this.b)) - getResources().getDimensionPixelSize(R.dimen._2131362796_res_0x7f0a03ec)) - (this.d == cVar2 ? this.w.get(this.n).intValue() : this.e.getHeight())) - (cVar == cVar2 ? this.y.get(this.n).intValue() : this.aa);
            float dimension = this.h.getVisibility() == 0 ? this.r + (getResources().getDimension(R.dimen._2131362750_res_0x7f0a03be) * 2.0f) : getResources().getDimension(R.dimen._2131362750_res_0x7f0a03be);
            i.d("FeedbackNoticeView", "checkContentSize leftHeight:" + measuredHeight + "   btnHeight:" + dimension);
            if (measuredHeight < dimension) {
                return false;
            }
        }
        return true;
    }

    private void d(Context context) {
        this.j.measure(0, 0);
        if (this.j.getMeasuredWidth() < com.huawei.phoneservice.faq.base.util.b.d(context)) {
            e.a(context, this.j);
        }
    }

    private void cfd_(Context context, AttributeSet attributeSet) {
        Drawable drawable;
        int i;
        int i2;
        Drawable drawable2;
        String str;
        int i3;
        int dimension = (int) context.getResources().getDimension(R.dimen._2131362797_res_0x7f0a03ed);
        int color = context.getResources().getColor(R.color.feedback_sdk_notice_view_default_text_color);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen._2131362796_res_0x7f0a03ec);
        int i4 = -1;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100073_res_0x7f0601a9, R.attr._2131100074_res_0x7f0601aa, R.attr._2131100075_res_0x7f0601ab, R.attr._2131100076_res_0x7f0601ac, R.attr._2131100077_res_0x7f0601ad, R.attr._2131100078_res_0x7f0601ae, R.attr._2131100079_res_0x7f0601af, R.attr._2131100080_res_0x7f0601b0, R.attr._2131100081_res_0x7f0601b1, R.attr._2131100082_res_0x7f0601b2, R.attr._2131100083_res_0x7f0601b3});
            drawable2 = obtainStyledAttributes.getDrawable(9);
            this.d = d(obtainStyledAttributes.getInt(10, 0));
            str = obtainStyledAttributes.getString(5);
            i = obtainStyledAttributes.getDimensionPixelSize(8, (int) context.getResources().getDimension(R.dimen._2131362797_res_0x7f0a03ed));
            i2 = obtainStyledAttributes.getColor(6, context.getResources().getColor(R.color.feedback_sdk_notice_view_default_text_color));
            dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelSize(7, context.getResources().getDimensionPixelOffset(R.dimen._2131362796_res_0x7f0a03ec));
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, getResources().getDimensionPixelOffset(R.dimen._2131362746_res_0x7f0a03ba));
            drawable = obtainStyledAttributes.getDrawable(3);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(4, -1);
            this.m = obtainStyledAttributes.getBoolean(0, false);
            obtainStyledAttributes.recycle();
            i4 = dimensionPixelSize;
            i3 = dimensionPixelSize2;
        } else {
            drawable = null;
            i = dimension;
            i2 = color;
            drawable2 = null;
            str = null;
            i3 = -1;
        }
        if (drawable2 != null) {
            setBackground(drawable2);
        }
        if (TextUtils.isEmpty(str)) {
            this.o = getResources().getString(R.string._2130850857_res_0x7f023429);
        } else {
            this.o = str;
        }
        if (this.d == c.ERROR) {
            c(this.n);
        } else {
            this.aa = i4;
            this.c.setVisibility(8);
            this.e.setVisibility(0);
            this.h.setVisibility(4);
            this.b.setText(this.o);
        }
        this.b.setTextSize(0, i);
        this.b.setTextColor(i2);
        cfc_(i4, dimensionPixelOffset, i3, drawable);
    }

    public static void b(Context context) {
        try {
            Intent intent = new Intent();
            if (com.huawei.phoneservice.faq.base.util.b.a()) {
                intent.setAction("android.settings.WIRELESS_SETTINGS");
                intent.putExtra("use_emui_ui", true);
            } else {
                intent.setAction("android.settings.SETTINGS");
            }
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            context.startActivity(new Intent("android.settings.SETTINGS"));
            i.c("FeedbackNoticeView", "gotoNetworkSettingView ActivityNotFoundException...");
        }
    }

    private void b() {
        Point point = new Point();
        ((WindowManager) getContext().getSystemService(com.huawei.openalliance.ad.constant.Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getRealSize(point);
        int max = Math.max(point.x, point.y);
        i.d("FeedbackNoticeView", "calculatePortMarginTop location 0:" + this.ad[0] + "   location 1:" + this.ad[1]);
        int i = ((int) (((double) max) * 0.3d)) - this.ad[1];
        int autoFitContentSize = getAutoFitContentSize();
        if (this.m || i < 0) {
            i = (getHeight() - autoFitContentSize) / 2;
        }
        i.d("FeedbackNoticeView", "calculatePortMarginTop getHeight:" + getHeight() + "   getAutoFitContentSize:" + autoFitContentSize + "   newCenterMarginTop:" + i);
        if (this.l != i) {
            i.d("FeedbackNoticeView", "calculatePortMarginTop portMarginTop30 != newPortMarginTop");
            this.l = i;
        }
    }

    private void e(FaqConstants.FaqErrorCode faqErrorCode, int i, int i2) {
        Context context;
        Resources resources;
        int i3;
        if (faqErrorCode == FaqConstants.FaqErrorCode.INTERNET_ERROR) {
            if (i != 0 || i2 != 0) {
                this.h.setVisibility(0);
                this.j.setText(getResources().getString(R.string._2130850862_res_0x7f02342e));
            }
            if (this.n != faqErrorCode && (i != 0 || i2 != 0)) {
                return;
            }
            context = getContext();
            resources = getResources();
            i3 = R.string._2130850892_res_0x7f02344c;
        } else {
            if (faqErrorCode != FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR) {
                if (faqErrorCode == FaqConstants.FaqErrorCode.DIFFERENT_SITE) {
                    if (i == 0 && i2 == 0) {
                        return;
                    }
                    this.h.setVisibility(0);
                    this.j.setText(getResources().getString(R.string._2130850849_res_0x7f023421));
                    return;
                }
                return;
            }
            if (this.n != faqErrorCode && (i != 0 || i2 != 0)) {
                return;
            }
            context = getContext();
            resources = getResources();
            i3 = R.string._2130850861_res_0x7f02342d;
        }
        n.a(context, resources.getString(i3));
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.y.put(FaqConstants.FaqErrorCode.DEFAULT, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362755_res_0x7f0a03c3)));
        this.y.put(FaqConstants.FaqErrorCode.INTERNET_ERROR, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362769_res_0x7f0a03d1)));
        this.y.put(FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362746_res_0x7f0a03ba)));
        this.y.put(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362746_res_0x7f0a03ba)));
        this.y.put(FaqConstants.FaqErrorCode.DIFFERENT_SITE, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362746_res_0x7f0a03ba)));
        this.w.put(FaqConstants.FaqErrorCode.DEFAULT, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362785_res_0x7f0a03e1)));
        this.w.put(FaqConstants.FaqErrorCode.INTERNET_ERROR, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362785_res_0x7f0a03e1)));
        this.w.put(FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362785_res_0x7f0a03e1)));
        this.w.put(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362784_res_0x7f0a03e0)));
        this.w.put(FaqConstants.FaqErrorCode.DIFFERENT_SITE, Integer.valueOf(getResources().getDimensionPixelOffset(R.dimen._2131362784_res_0x7f0a03e0)));
        this.u.put(FaqConstants.FaqErrorCode.DEFAULT, Integer.valueOf(R.drawable.feedback_sdk_ic_no_pic_disable));
        Map<FaqConstants.FaqErrorCode, Integer> map = this.u;
        FaqConstants.FaqErrorCode faqErrorCode = FaqConstants.FaqErrorCode.INTERNET_ERROR;
        Integer valueOf = Integer.valueOf(R.drawable.feedback_sdk_ic_no_wifi_disable);
        map.put(faqErrorCode, valueOf);
        this.u.put(FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR, valueOf);
        this.u.put(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, Integer.valueOf(R.drawable.feedback_sdk_ic_no_search_result));
        this.u.put(FaqConstants.FaqErrorCode.DIFFERENT_SITE, Integer.valueOf(R.drawable._2131430129_res_0x7f0b0af1));
        this.v.put(FaqConstants.FaqErrorCode.INTERNET_ERROR, Integer.valueOf(R.string._2130850858_res_0x7f02342a));
        this.v.put(FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR, Integer.valueOf(R.string._2130850843_res_0x7f02341b));
        this.v.put(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, Integer.valueOf(R.string._2130850919_res_0x7f023467));
        this.v.put(FaqConstants.FaqErrorCode.DIFFERENT_SITE, Integer.valueOf(R.string._2130850884_res_0x7f023444));
        this.ae = getResources().getDimensionPixelOffset(R.dimen._2131362750_res_0x7f0a03be);
        this.q = com.huawei.phoneservice.faq.base.util.b.c(context) / 3;
        this.x = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        LayoutInflater.from(context).inflate(R.layout.feedback_sdk_widget_notice_view, this);
        this.c = (ImageView) findViewById(R.id.notice_image_view);
        this.b = (TextView) findViewById(R.id.notice_text_view);
        this.e = (ProgressBar) findViewById(R.id.notice_progress_view);
        this.f8301a = findViewById(R.id.notice_view_container);
        this.h = findViewById(R.id.button_container);
        Button button = (Button) findViewById(R.id.error_button);
        this.j = button;
        button.setOnClickListener(this);
        d(context);
        setBackgroundColor(c(context).intValue());
        cfd_(context, attributeSet);
    }

    private void cfc_(int i, int i2, int i3, Drawable drawable) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
        }
        layoutParams.topMargin = i2;
        this.b.setLayoutParams(layoutParams);
        if (drawable != null) {
            this.c.setImageDrawable(drawable);
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.c.getLayoutParams();
        if (i3 <= 0) {
            i3 = -2;
        }
        if (layoutParams2 == null) {
            layoutParams2 = new LinearLayout.LayoutParams(i3, i3);
        } else {
            layoutParams2.height = i3;
            layoutParams2.width = i3;
        }
        this.c.setLayoutParams(layoutParams2);
        setContentMarginTop(i);
    }

    private void e() {
        Point point = new Point();
        ((WindowManager) getContext().getSystemService(com.huawei.openalliance.ad.constant.Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getRealSize(point);
        int min = Math.min(point.x, point.y);
        i.d("FeedbackNoticeView", "calculateCenterMarginTop location 0:" + this.ad[0] + "   location 1:" + this.ad[1]);
        int autoFitContentSize = getAutoFitContentSize();
        int height = this.m ? (getHeight() - autoFitContentSize) / 2 : ((min / 2) - this.ad[1]) - (autoFitContentSize / 2);
        i.d("FeedbackNoticeView", "calculateCenterMarginTop getHeight:" + getHeight() + "   getAutoFitContentSize:" + autoFitContentSize + "   newCenterMarginTop:" + height);
        if (this.f != height) {
            i.d("FeedbackNoticeView", "calculateCenterMarginTop centerMarginTop != newCenterMarginTop");
            this.f = height;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x005c, code lost:
    
        if (r3 == r0.intValue()) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Integer c(android.content.Context r6) {
        /*
            android.content.res.Resources r0 = android.content.res.Resources.getSystem()
            java.lang.String r1 = "color"
            java.lang.String r2 = "androidhwext"
            java.lang.String r3 = "navigationbar_emui_light"
            int r0 = r0.getIdentifier(r3, r1, r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "navigationbar_emui_light : "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "getColorPrimary"
            com.huawei.phoneservice.faq.base.util.i.d(r2, r1)
            if (r0 == 0) goto L5e
            android.content.res.Resources r1 = r6.getResources()
            int r0 = r1.getColor(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r1 = r0.intValue()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r3 = "#%08X"
            java.lang.String r1 = java.lang.String.format(r3, r1)
            java.lang.String r3 = "#00000000"
            int r3 = android.graphics.Color.parseColor(r3)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "navigationbarEmuiLight : "
            r4.<init>(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.huawei.phoneservice.faq.base.util.i.d(r2, r1)
            int r1 = r0.intValue()
            if (r3 != r1) goto L5f
        L5e:
            r0 = 0
        L5f:
            if (r0 != 0) goto Lc2
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            android.content.res.Resources$Theme r1 = r6.getTheme()
            r3 = 16843827(0x1010433, float:2.369657E-38)
            r4 = 1
            r1.resolveAttribute(r3, r0, r4)
            int r1 = r0.data
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r1 = r1 & r3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r3 = "#%06X"
            java.lang.String r1 = java.lang.String.format(r3, r1)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "colorPrimary : "
            r3.<init>(r4)
            int r4 = r0.data
            r3.append(r4)
            java.lang.String r4 = "  strColor: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.huawei.phoneservice.faq.base.util.i.d(r2, r1)
            java.lang.String r1 = "#f0f0f0"
            int r1 = android.graphics.Color.parseColor(r1)
            int r2 = r0.data
            android.content.res.Resources r6 = r6.getResources()
            if (r1 != r2) goto Lb2
            r1 = 2131297678(0x7f09058e, float:1.8213308E38)
            goto Lb5
        Lb2:
            r1 = 2131297676(0x7f09058c, float:1.8213304E38)
        Lb5:
            int r6 = r6.getColor(r1)
            r0.data = r6
            int r6 = r0.data
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            return r6
        Lc2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedback.widget.FeedbackNoticeView.c(android.content.Context):java.lang.Integer");
    }

    private c d(int i) {
        return i == 0 ? c.ERROR : c.PROGRESS;
    }

    public static int a(TextView textView) {
        if (textView.getMeasuredWidth() == 0) {
            return 0;
        }
        String obj = textView.getText().toString();
        if (g.containsKey(obj)) {
            return g.get(obj).intValue();
        }
        TextPaint paint = textView.getPaint();
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f = (fontMetrics.bottom - fontMetrics.top) - (fontMetrics.descent - fontMetrics.ascent);
        int i = (int) f;
        if (f > i) {
            i++;
        }
        int i2 = i;
        StaticLayout staticLayout = new StaticLayout(obj, paint, textView.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        int[] iArr = new int[2];
        int maxLines = textView.getMaxLines();
        if (staticLayout.getLineCount() <= maxLines) {
            iArr[0] = -1;
            int height = staticLayout.getHeight();
            iArr[1] = height;
            g.put(obj, Integer.valueOf(height + i2));
            return iArr[1] + i2;
        }
        int lineStart = staticLayout.getLineStart(maxLines) - 1;
        iArr[0] = lineStart;
        int height2 = new StaticLayout(obj.substring(0, lineStart), paint, textView.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false).getHeight();
        iArr[1] = height2;
        g.put(obj, Integer.valueOf(height2 + i2));
        return iArr[1] + i2;
    }

    public FeedbackNoticeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            int i;
            FeedbackNoticeView feedbackNoticeView = FeedbackNoticeView.this;
            feedbackNoticeView.ac = feedbackNoticeView.d();
            i.d("FeedbackNoticeView", "refreshAction contentSizeEnough:" + FeedbackNoticeView.this.ac);
            FeedbackNoticeView feedbackNoticeView2 = FeedbackNoticeView.this;
            if (feedbackNoticeView2.d != c.ERROR) {
                i = feedbackNoticeView2.aa;
            } else {
                i = ((Integer) feedbackNoticeView2.y.get(FeedbackNoticeView.this.n)).intValue();
            }
            feedbackNoticeView2.setContentMarginTop(i);
        }

        a() {
        }
    }

    public FeedbackNoticeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = 0;
        this.q = 0;
        this.s = -1;
        this.y = new HashMap();
        this.w = new HashMap();
        this.u = new HashMap();
        this.v = new HashMap();
        this.aa = 0;
        this.ac = true;
        this.ad = new int[2];
        this.ab = new a();
        a(context, attributeSet);
    }

    public FeedbackNoticeView(Context context) {
        super(context);
        this.r = 0;
        this.q = 0;
        this.s = -1;
        this.y = new HashMap();
        this.w = new HashMap();
        this.u = new HashMap();
        this.v = new HashMap();
        this.aa = 0;
        this.ac = true;
        this.ad = new int[2];
        this.ab = new a();
        a(context, (AttributeSet) null);
    }
}
