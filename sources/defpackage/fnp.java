package defpackage;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.huawei.health.suggestion.ui.fitness.module.SugChart;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class fnp {
    public static void aBs_(Canvas canvas, fqv fqvVar, c cVar) {
        if (canvas == null || fqvVar == null || cVar.d == null || cVar.f12574a == null) {
            LogUtil.b("Suggestion_CalloutHelper", "canvas == null || excel == null || calloutPaint == null || backgroundPaint == null");
            return;
        }
        float a2 = a(2.0f) + (cVar.c / 2.0f);
        if (fqvVar.n() <= 0.0f) {
            return;
        }
        PointF aEj_ = fqvVar.aEj_();
        Path path = new Path();
        float a3 = a(8.0f);
        float a4 = a(4.0f);
        path.moveTo(aEj_.x, aEj_.y - a2);
        float f = a3 / 2.0f;
        path.lineTo(aEj_.x - f, ((aEj_.y - a2) - a4) - 1.0f);
        path.lineTo(aEj_.x + f, ((aEj_.y - a2) - a4) - 1.0f);
        path.close();
        canvas.drawPath(path, cVar.f12574a);
        String valueOf = String.valueOf(fqvVar.j());
        float measureText = cVar.d.measureText(valueOf);
        float a5 = a(21.0f);
        float a6 = a(6.0f);
        float f2 = measureText / 2.0f;
        RectF rectF = new RectF((aEj_.x - f2) - a6, ((aEj_.y - a2) - a4) - a5, aEj_.x + f2 + a6, (aEj_.y - a2) - a4);
        float f3 = rectF.right - cVar.e;
        float f4 = aEj_.x;
        if (f3 > 0.0f) {
            rectF.right = (rectF.right - f3) - 1.0f;
            rectF.left = (rectF.left - f3) - 1.0f;
            f4 = (aEj_.x - f3) - 1.0f;
        } else if (rectF.left < 0.0f) {
            rectF.right = (rectF.right - rectF.left) + 1.0f;
            f4 = (aEj_.x - rectF.left) + 1.0f;
            rectF.left = 1.0f;
        }
        canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, cVar.f12574a);
        canvas.drawText(valueOf, f4, (rectF.bottom - (rectF.height() / 2.0f)) + (aBt_(cVar.d) / 2.0f), cVar.d);
    }

    private static int a(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    private static float aBt_(Paint paint) {
        return (-paint.ascent()) - paint.descent();
    }

    public static float c() {
        return a(21.0f) + a(4.0f) + a(4.0f);
    }

    public static fqv c(fqv fqvVar, fqv fqvVar2) {
        if (fqvVar != null && fqvVar2 != null) {
            return fqvVar2.f() > fqvVar.f() ? fqvVar2 : fqvVar;
        }
        LogUtil.b("Suggestion_CalloutHelper", "sugExcel == null || standardHeightExcel == null");
        return null;
    }

    public static fqv a(fqv fqvVar, fqv fqvVar2) {
        if (fqvVar != null && fqvVar2 != null) {
            return fqvVar2.q() > fqvVar.q() ? fqvVar2 : fqvVar;
        }
        LogUtil.b("Suggestion_CalloutHelper", "sugExcel == null || highestExcel == null");
        return null;
    }

    public static void aBv_(MotionEvent motionEvent, PointF pointF, float f, SugChart.OnHelperGestureListener onHelperGestureListener) {
        if (motionEvent == null || pointF == null) {
            LogUtil.b("Suggestion_CalloutHelper", "event == null || downPoint == null");
            return;
        }
        float x = motionEvent.getX() - pointF.x;
        float f2 = 3.0f * f;
        if (Math.abs(motionEvent.getY() - pointF.y) < f2) {
            if (Math.abs(x) <= f * 4.0f || onHelperGestureListener == null) {
                return;
            }
            onHelperGestureListener.scrollX(x > 0.0f);
            return;
        }
        if (Math.abs(x) >= f2 || onHelperGestureListener == null) {
            return;
        }
        onHelperGestureListener.scrollX(motionEvent.getY() - pointF.y > 0.0f);
    }

    public static int a(List<fqv> list) {
        if (koq.b(list)) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i).n() < list.get(i2).n()) {
                i = i2;
            }
        }
        return i;
    }

    public static boolean aBu_(PointF pointF, fqv fqvVar, PointF pointF2) {
        return pointF != null && pointF2 != null && fqvVar != null && pointF2.x <= pointF.x && pointF2.x + fqvVar.x() > pointF.x && pointF2.y > pointF.y && (pointF2.y - fqvVar.q()) - 1.0f < pointF.y;
    }

    public static boolean c(fqv fqvVar) {
        if (fqvVar == null) {
            return false;
        }
        return fqvVar.h() == 0 || fqvVar.h() == 3 || fqvVar.h() == 2;
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private Paint f12574a;
        private float c;
        private Paint d;
        private float e;

        public c(float f, float f2, Paint paint, Paint paint2) {
            this.c = f;
            this.e = f2;
            this.f12574a = paint;
            this.d = paint2;
        }
    }
}
