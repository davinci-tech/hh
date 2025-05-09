package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthMark;
import com.huawei.ui.commonui.calendarview.listener.OnDayMarkShowListener;
import health.compact.a.LanguageUtil;

/* loaded from: classes9.dex */
public class nkv {
    public static void cxd_(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, nky nkyVar, int i3, Paint paint, Paint paint2) {
        for (HealthMark healthMark : healthCalendar.getMarks()) {
            int c = healthMark.c();
            int b = healthMark.b();
            int i4 = AnonymousClass2.c[healthMark.f().ordinal()];
            if (i4 == 1) {
                Drawable cxx_ = healthMark.cxx_();
                if (cxx_ == null) {
                    cxx_ = nkyVar.i().cxx_();
                    c = nkyVar.i().c();
                }
                if (cxx_ != null) {
                    cxb_(canvas, cxx_, i + b, i2 + c);
                }
            } else if (i4 == 2) {
                cxa_(canvas, i, i2, nkyVar, i3, paint, healthMark);
            } else if (i4 == 3) {
                String e = healthMark.e();
                Paint.FontMetrics fontMetrics = paint2.getFontMetrics();
                float f = i;
                float f2 = (i2 - fontMetrics.ascent) + c;
                canvas.drawText(e, f, f2, paint2);
                Drawable cxx_2 = healthMark.cxx_();
                if (cxx_2 != null) {
                    float measureText = ((paint2.measureText(e) + cxx_2.getIntrinsicWidth()) / 2.0f) + b;
                    if (LanguageUtil.bc(BaseApplication.e())) {
                        measureText = -measureText;
                    }
                    cxb_(canvas, cxx_2, f + measureText, (f2 + fontMetrics.descent) - ((fontMetrics.descent - fontMetrics.ascent) / 2.0f));
                }
            } else if (i4 == 4) {
                cxc_(canvas, healthCalendar, i, i2, nkyVar.j, healthMark, c, b);
            }
        }
    }

    /* renamed from: nkv$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[HealthMark.MarkType.values().length];
            c = iArr;
            try {
                iArr[HealthMark.MarkType.DRAWABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[HealthMark.MarkType.COLOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[HealthMark.MarkType.TEXT_DRAWABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[HealthMark.MarkType.VIEW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static void cxa_(Canvas canvas, int i, int i2, nky nkyVar, int i3, Paint paint, HealthMark healthMark) {
        int d;
        if (healthMark.j()) {
            d = healthMark.d();
        } else {
            d = nkyVar.i().d();
        }
        paint.setColor(d);
        canvas.drawCircle(i, i2 - i3, i3, paint);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void cxc_(Canvas canvas, HealthCalendar healthCalendar, int i, int i2, int i3, HealthMark healthMark, int i4, int i5) {
        View cxy_ = healthMark.cxy_();
        if (cxy_ == 0) {
            return;
        }
        healthMark.e(i3);
        int save = canvas.save();
        canvas.translate((i - (cxy_.getWidth() / 2)) + i5, i2 + i4);
        if (cxy_ instanceof OnDayMarkShowListener) {
            ((OnDayMarkShowListener) cxy_).onDayMarkShow(cxy_, healthCalendar);
        }
        cxy_.draw(canvas);
        canvas.restoreToCount(save);
    }

    private static void cxb_(Canvas canvas, Drawable drawable, float f, float f2) {
        int save = canvas.save();
        canvas.translate(f, f2);
        int intrinsicHeight = drawable.getIntrinsicHeight() / 2;
        int intrinsicWidth = drawable.getIntrinsicWidth() / 2;
        drawable.setBounds(-intrinsicWidth, -intrinsicHeight, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }
}
