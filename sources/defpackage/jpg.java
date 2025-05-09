package defpackage;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class jpg {
    public static b d(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        b bVar = new b();
        bVar.e = str;
        if (z) {
            bVar.d = 452984831;
        } else {
            bVar.d = 251658240;
        }
        bVar.c = 12.0f;
        bVar.b = -25.0f;
        return bVar;
    }

    public static void bIG_(b bVar, Activity activity, int i) {
        if (activity == null || bVar == null) {
            LogUtil.h("HwSecureWaterMark", "activity or watermarkDrawable is null");
            return;
        }
        if (i == -1) {
            View findViewById = activity.findViewById(1000);
            View decorView = activity.getWindow().getDecorView();
            if (findViewById != null && (findViewById instanceof FrameLayout) && (decorView instanceof FrameLayout)) {
                ((FrameLayout) decorView).removeView((FrameLayout) findViewById);
            }
        } else {
            View findViewById2 = activity.findViewById(1000);
            if (findViewById2 != null && (findViewById2 instanceof FrameLayout)) {
                FrameLayout frameLayout = (FrameLayout) findViewById2;
                ViewGroup viewGroup = (ViewGroup) activity.findViewById(i);
                if (viewGroup != null) {
                    viewGroup.removeView(frameLayout);
                }
            }
        }
        FrameLayout frameLayout2 = new FrameLayout(activity);
        if (bIF_(activity)) {
            frameLayout2.setAlpha(0.1f);
        }
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout2.setBackground(bVar);
        frameLayout2.setId(1000);
        if (i == -1) {
            View decorView2 = activity.getWindow().getDecorView();
            if (decorView2 instanceof FrameLayout) {
                ((FrameLayout) decorView2).addView(frameLayout2);
                return;
            }
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) activity.findViewById(i);
        if (viewGroup2 != null) {
            viewGroup2.addView(frameLayout2);
        }
    }

    public static void bIH_(b bVar, Activity activity, ViewGroup viewGroup) {
        if (activity == null || bVar == null || viewGroup == null) {
            LogUtil.h("HwSecureWaterMark", "activity or watermarkDrawable is null");
            return;
        }
        View findViewById = viewGroup.findViewById(1000);
        if (findViewById != null && (findViewById instanceof FrameLayout)) {
            viewGroup.removeView((FrameLayout) findViewById);
        }
        FrameLayout frameLayout = new FrameLayout(activity);
        if (bIF_(activity)) {
            frameLayout.setAlpha(0.1f);
        }
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setBackground(bVar);
        frameLayout.setId(1000);
        viewGroup.addView(frameLayout);
    }

    private static boolean bIF_(Activity activity) {
        AccessibilityManager accessibilityManager;
        if (activity == null || !(activity.getSystemService("accessibility") instanceof AccessibilityManager) || (accessibilityManager = (AccessibilityManager) activity.getSystemService("accessibility")) == null) {
            return false;
        }
        try {
            Method method = accessibilityManager.getClass().getMethod("isHighTextContrastEnabled", null);
            if (method == null) {
                return false;
            }
            Object invoke = method.invoke(accessibilityManager, null);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (NoSuchMethodException unused) {
            LogUtil.b("HwSecureWaterMark", "NoSuchMethodException");
            return false;
        } catch (Exception unused2) {
            LogUtil.b("HwSecureWaterMark", "Exception");
            return false;
        }
    }

    public static class b extends Drawable {

        /* renamed from: a, reason: collision with root package name */
        private Paint f14013a;
        private float b;
        private float c;
        private int d;
        private String e;

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        private b() {
            this.f14013a = new Paint();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (canvas == null) {
                return;
            }
            this.f14013a.reset();
            this.f14013a.setTextSize(BaseApplication.getContext().getResources().getDisplayMetrics().density * this.c);
            this.f14013a.setAntiAlias(true);
            int i = 0;
            this.f14013a.setTypeface(Typeface.create(Constants.FONT, 0));
            this.f14013a.setColor(this.d);
            canvas.drawColor(0);
            canvas.rotate(this.b);
            int i2 = getBounds().right;
            int i3 = getBounds().bottom;
            int sqrt = (int) Math.sqrt((i2 * i2) + (i3 * i3));
            if (sqrt == 0) {
                return;
            }
            float measureText = this.f14013a.measureText(this.e);
            LogUtil.a("HwSecureWaterMark", " drawText with:", this.e);
            if (TextUtils.isEmpty(this.e)) {
                return;
            }
            LogUtil.a("HwSecureWaterMark", "start drawText");
            int i4 = sqrt / 10;
            int i5 = i4;
            while (i5 <= sqrt) {
                float f = -i2;
                float f2 = i % 2;
                while (true) {
                    int i6 = (int) (f + (f2 * measureText));
                    if (i6 < i2) {
                        f = i6;
                        canvas.drawText(this.e, f, i5, this.f14013a);
                        f2 = 2.0f;
                    }
                }
                i5 += i4;
                i++;
            }
            LogUtil.a("HwSecureWaterMark", "end drawText");
            canvas.save();
            canvas.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            LogUtil.c("HwSecureWaterMark", "setAlpha");
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            LogUtil.c("HwSecureWaterMark", "setColorFilter");
        }
    }
}
