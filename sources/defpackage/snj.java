package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class snj {
    private static final Method b;
    private static final Method d;
    private static final Method e;

    /* renamed from: a, reason: collision with root package name */
    private final View f17137a;
    private Context m;
    private int c = 0;
    private final Rect f = new Rect();
    private final Rect i = new Rect();
    private int h = 0;
    private boolean g = false;
    private boolean j = false;
    private boolean k = false;
    private boolean o = false;
    private boolean n = false;
    private boolean l = true;
    private final Runnable s = new c();

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            snj.this.f17137a.requestLayout();
        }
    }

    /* JADX WARN: Finally extract failed */
    static {
        if (Build.VERSION.SDK_INT < 28) {
            e = null;
            b = null;
            return;
        }
        try {
            try {
                try {
                    b = Class.forName("huawei.android.widget.RadiusSizeUtils").getDeclaredMethod("getRadiusSize", Context.class);
                } catch (ClassNotFoundException unused) {
                    Log.w("HwWidgetSafeInsets", "getRadiusSize ClassNotFoundException");
                    b = null;
                    try {
                        try {
                            try {
                                try {
                                    e = Class.forName("com.huawei.android.view.WindowManagerEx$LayoutParamsEx").getDeclaredMethod("getDisplaySafeInsets", WindowInsets.class);
                                } catch (ClassNotFoundException unused2) {
                                    Log.w("HwWidgetSafeInsets", "getDisplaySafeInsets ClassNotFoundException");
                                    d = Class.forName("com.huawei.android.app.WindowManagerEx").getDeclaredMethod("getDisplaySideSafeInsets", new Class[0]);
                                }
                            } catch (NoSuchMethodException unused3) {
                                Log.w("HwWidgetSafeInsets", "getDisplaySafeInsets NoSuchMethodException");
                                d = Class.forName("com.huawei.android.app.WindowManagerEx").getDeclaredMethod("getDisplaySideSafeInsets", new Class[0]);
                            }
                            d = Class.forName("com.huawei.android.app.WindowManagerEx").getDeclaredMethod("getDisplaySideSafeInsets", new Class[0]);
                        } catch (ClassNotFoundException unused4) {
                            Log.w("HwWidgetSafeInsets", "getDisplaySideSafeInsets ClassNotFoundException");
                        }
                    } finally {
                        d = null;
                    }
                }
            } catch (NoSuchMethodException unused5) {
                Log.w("HwWidgetSafeInsets", "getRadiusSize NoSuchMethodException");
                b = null;
                e = Class.forName("com.huawei.android.view.WindowManagerEx$LayoutParamsEx").getDeclaredMethod("getDisplaySafeInsets", WindowInsets.class);
                d = Class.forName("com.huawei.android.app.WindowManagerEx").getDeclaredMethod("getDisplaySideSafeInsets", new Class[0]);
            }
            try {
                e = Class.forName("com.huawei.android.view.WindowManagerEx$LayoutParamsEx").getDeclaredMethod("getDisplaySafeInsets", WindowInsets.class);
                try {
                    d = Class.forName("com.huawei.android.app.WindowManagerEx").getDeclaredMethod("getDisplaySideSafeInsets", new Class[0]);
                } catch (NoSuchMethodException unused6) {
                    Log.w("HwWidgetSafeInsets", "getDisplaySideSafeInsets NoSuchMethodException");
                }
            } finally {
                e = null;
            }
        } catch (Throwable th) {
            b = null;
            throw th;
        }
    }

    public snj(View view) {
        this.m = null;
        this.f17137a = view;
        this.m = view.getContext();
    }

    private boolean d() {
        Activity eiT_ = eiT_(this.m);
        if (eiT_ == null) {
            return false;
        }
        try {
            return ((Integer) Class.forName("com.huawei.android.app.ActivityManagerEx").getMethod("getActivityWindowMode", Activity.class).invoke(null, eiT_)).intValue() == 102;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            Log.e("HwWidgetSafeInsets", "Exception occurred in isInMultiWindowMode.");
            return false;
        }
    }

    private Activity eiT_(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return eiT_(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public boolean b() {
        return this.c == 2;
    }

    public void c() {
        View view = this.f17137a;
        if (view != null) {
            view.removeCallbacks(this.s);
            this.f17137a.post(this.s);
        }
    }

    public void c(boolean z) {
        this.j = z;
    }

    public void d(int i, int i2, int i3, int i4) {
        eja_(new Rect(i, i2, i3, i4));
    }

    public void d(boolean z) {
        this.k = z;
    }

    public void eiV_(View view, Rect rect, boolean z) {
        if (view == null || rect == null || b()) {
            return;
        }
        int i = this.h;
        if ((i & 2) == 0) {
            return;
        }
        this.h = i | 1;
        view.setPadding(rect.left, rect.top, rect.right, rect.bottom);
        this.h &= -2;
        if (z) {
            c();
            this.h &= -3;
        }
    }

    public Rect eiW_(View view) {
        return eiX_(view, this.i);
    }

    public Rect eiY_() {
        return this.f;
    }

    public void eiZ_(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            Log.w("HwWidgetSafeInsets", "parse cutout mode error");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100270_res_0x7f06026e});
        this.c = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
    }

    public void ejb_(WindowInsets windowInsets) {
        if (!d() && Build.VERSION.SDK_INT >= 28) {
            Rect eiR_ = e != null ? eiR_(windowInsets) : eiO_(windowInsets);
            if (eiR_ == null) {
                eiR_ = new Rect();
            }
            boolean eiU_ = eiU_(this.f17137a);
            this.f17137a.getLocationInWindow(new int[2]);
            if (this.l) {
                if (this.f.equals(eiR_) && this.g == eiU_) {
                    return;
                }
                this.f.set(eiR_);
                this.h |= 2;
                this.g = eiU_;
                c();
            }
        }
    }

    private void eiQ_(Rect rect, Rect rect2, Rect rect3, Rect rect4) {
        if (this.c == 1) {
            Rect rect5 = this.f;
            int i = rect5.left;
            if (i > 0) {
                rect2.left = rect.left + i;
            }
            int i2 = rect5.right;
            if (i2 > 0) {
                rect2.right = rect.right + i2;
                return;
            }
            return;
        }
        Rect rect6 = this.f;
        int i3 = rect6.left;
        if (i3 > 0 && rect3.left < i3) {
            rect2.left = rect.left + i3;
        }
        int i4 = rect6.right;
        if (i4 <= 0 || rect4.right - i4 >= rect3.right) {
            return;
        }
        rect2.right = rect.right + i4;
    }

    public Rect eiX_(View view, Rect rect) {
        int eiN_;
        int i;
        Rect rect2 = new Rect();
        if (view != null && rect != null) {
            rect2.set(rect);
            boolean z = (this.k || this.j || a()) ? false : true;
            if (!b() && !z) {
                Rect rect3 = new Rect();
                Rect rect4 = new Rect();
                int[] iArr = new int[2];
                view.getLocationInWindow(iArr);
                int i2 = iArr[0];
                rect3.set(i2, iArr[1], view.getWidth() + i2, iArr[1] + view.getHeight());
                View rootView = view.getRootView();
                if (rootView != null) {
                    rootView.getLocationInWindow(iArr);
                    int i3 = iArr[0];
                    rect4.set(i3, iArr[1], rootView.getWidth() + i3, iArr[1] + rootView.getHeight());
                    eiQ_(rect, rect2, rect3, rect4);
                    boolean c2 = c(view.getContext());
                    if (this.j) {
                        if (c2 && (i = this.f.top) > 0 && rect3.top < i) {
                            int i4 = rect3.top;
                            int paddingTop = view.getPaddingTop();
                            int i5 = this.f.top;
                            if (i4 + paddingTop >= i5) {
                                i5 = view.getPaddingTop();
                            }
                            rect2.top = i5;
                        } else if (this.o) {
                            rect2.top = view.getPaddingTop();
                        }
                    }
                    if (this.k && c2 && (eiN_ = eiN_(view)) > 0) {
                        rect2.bottom = rect.bottom + eiN_;
                    }
                }
            }
        }
        return rect2;
    }

    public void eja_(Rect rect) {
        if (eiS_(this.f17137a) && (this.h & 1) == 0) {
            if (this.n) {
                this.o = true;
                return;
            }
            this.i.set(rect);
            this.n = true;
            this.h |= 2;
        }
    }

    private boolean eiU_(View view) {
        return view != null && Settings.Global.getInt(view.getContext().getContentResolver(), Constants.NAVIGATIONBAR_IS_MIN, 0) == 0;
    }

    private boolean c(Context context) {
        WindowManager windowManager;
        int rotation = (context == null || (windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)) == null) ? 0 : windowManager.getDefaultDisplay().getRotation();
        return rotation == 0 || rotation == 2;
    }

    public void e(boolean z) {
        if ((this.h & 2) == 0) {
            return;
        }
        View view = this.f17137a;
        eiV_(view, eiW_(view), z);
    }

    private static Rect eiR_(WindowInsets windowInsets) {
        int i;
        Rect rect = new Rect();
        if (windowInsets != null && Build.VERSION.SDK_INT >= 28) {
            try {
                Method method = e;
                if (method != null) {
                    Object invoke = method.invoke(null, windowInsets);
                    if (invoke instanceof Rect) {
                        rect = (Rect) invoke;
                    }
                }
                Method method2 = d;
                if (method2 != null) {
                    Object invoke2 = method2.invoke(null, new Object[0]);
                    if (!(invoke2 instanceof Rect)) {
                        return rect;
                    }
                    Rect rect2 = (Rect) invoke2;
                    int i2 = rect2.left;
                    if (i2 != 0 && (i = rect2.right) != 0) {
                        rect.left = i2;
                        rect.right = i;
                    }
                }
            } catch (IllegalAccessException unused) {
                Log.e("HwWidgetSafeInsets", "getWindowDisplaySafeInsets IllegalAccessException");
            } catch (InvocationTargetException unused2) {
                Log.e("HwWidgetSafeInsets", "getWindowDisplaySafeInsets InvocationTargetException");
            }
        }
        return rect;
    }

    private int eiN_(View view) {
        Context context;
        int e2;
        if (view == null || (context = view.getContext()) == null || (e2 = e(context)) <= 0) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return ((iArr[1] + view.getMeasuredHeight()) + e2) - displayMetrics.heightPixels;
    }

    private static Rect eiO_(WindowInsets windowInsets) {
        DisplayCutout displayCutout;
        Rect rect = new Rect();
        if (windowInsets != null && Build.VERSION.SDK_INT == 28 && (displayCutout = windowInsets.getDisplayCutout()) != null) {
            rect.set(displayCutout.getSafeInsetLeft(), 0, displayCutout.getSafeInsetRight(), 0);
        }
        return rect;
    }

    private static int e(Context context) {
        try {
            Method method = b;
            if (method != null) {
                return ((Integer) method.invoke(null, context)).intValue();
            }
        } catch (IllegalAccessException unused) {
            Log.w("HwWidgetSafeInsets", "getRadiusSize IllegalAccessException");
        } catch (InvocationTargetException unused2) {
            Log.w("HwWidgetSafeInsets", "getRadiusSize InvocationTargetException");
        }
        return 0;
    }

    private boolean eiS_(View view) {
        if (view == null) {
            return false;
        }
        return view.isAttachedToWindow();
    }

    private boolean a() {
        Rect rect = this.f;
        if (rect == null) {
            return false;
        }
        return rect.left > 0 || rect.right > 0;
    }
}
