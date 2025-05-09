package com.huawei.ui.commonui.marqueetext;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;
import defpackage.nps;
import java.lang.reflect.Field;

/* loaded from: classes6.dex */
public class HealthHeadLineMarqueeTextView extends TextView {
    private final float b;
    private long c;
    private final boolean e;

    public HealthHeadLineMarqueeTextView(Context context) {
        this(context, null);
    }

    public HealthHeadLineMarqueeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthHeadLineMarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthHeadLineMarqueeTextView);
        this.b = obtainStyledAttributes.getDimension(R$styleable.HealthHeadLineMarqueeTextView_headToTailSpace, 10.0f);
        this.e = obtainStyledAttributes.getBoolean(R$styleable.HealthHeadLineMarqueeTextView_isUseHeadToTailSpace, false);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public void invalidate() {
        c();
        super.invalidate();
    }

    private void c() {
        if (this.e && System.currentTimeMillis() - this.c >= 1000) {
            a();
        }
    }

    private void a() {
        Field d;
        Object obj;
        try {
            Class<?> cls = null;
            for (Class<?> cls2 : TextView.class.getDeclaredClasses()) {
                if ("Marquee".equals(cls2.getSimpleName())) {
                    cls = cls2;
                }
            }
            if (cls == null || (d = nps.d(cls, "mGhostStart", true)) == null) {
                return;
            }
            int width = getWidth();
            int compoundPaddingLeft = getCompoundPaddingLeft();
            int compoundPaddingRight = getCompoundPaddingRight();
            Layout layout = getLayout();
            if (layout == null) {
                return;
            }
            float lineWidth = layout.getLineWidth(0);
            float f = this.b;
            float f2 = (width - compoundPaddingLeft) - compoundPaddingRight;
            float f3 = (lineWidth - f2) + f;
            Field d2 = nps.d(TextView.class, "mMarquee", true);
            if (d2 == null || (obj = d2.get(this)) == null) {
                return;
            }
            this.c = System.currentTimeMillis();
            if (Math.abs(((Float) d.get(obj)).floatValue() - f3) > 1.0E-6f) {
                Field d3 = nps.d(cls, "mMaxScroll", true);
                Field d4 = nps.d(cls, "mGhostOffset", true);
                Field d5 = nps.d(cls, "mMaxFadeScroll", true);
                if (d3 != null && d4 != null && d5 != null) {
                    d.set(obj, Float.valueOf(f3));
                    d3.set(obj, Float.valueOf(f2 + f3));
                    d4.set(obj, Float.valueOf(f + lineWidth));
                    d5.set(obj, Float.valueOf(f3 + lineWidth + lineWidth));
                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.b("HealthHeadLineMarqueeTextView", "IllegalAccessException: ", ExceptionUtils.d(e));
        }
    }
}
