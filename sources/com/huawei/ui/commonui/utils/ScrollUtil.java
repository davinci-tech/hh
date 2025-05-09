package com.huawei.ui.commonui.utils;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.core.util.Consumer;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.floatview.HealthFloatingView;
import defpackage.koq;
import defpackage.nmh;
import java.util.List;

/* loaded from: classes6.dex */
public class ScrollUtil {
    private static int b = 0;
    private static final String d = "ScrollUtil";

    /* loaded from: classes9.dex */
    public interface ScrollStateListener {
        void stopScroll();

        void swipDown();

        void swipUp();
    }

    public static int c(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static boolean cKv_(View view, View view2) {
        if (view == null || view2 == null) {
            LogUtil.h(d, "isScrollChildViewVisible view ", view, " childView ", view2);
            return false;
        }
        Rect rect = new Rect();
        view.getHitRect(rect);
        return view2.getLocalVisibleRect(rect);
    }

    public static boolean cKw_(View view, View view2, int i) {
        if (view == null || view2 == null) {
            LogUtil.h(d, "isScrollChildViewVisible view ", view, " childView ", view2, " pixel ", Integer.valueOf(i));
            return false;
        }
        Rect rect = new Rect();
        view.getHitRect(rect);
        return view2.getLocalVisibleRect(rect) && rect.bottom >= i;
    }

    public static boolean c(float f, float f2) {
        return Float.compare(f, f2) == 0;
    }

    public static void cKy_(View view, final View view2, final int i, final Consumer<Boolean> consumer) {
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.commonui.utils.ScrollUtil.5
            private boolean b = false;
            private float d;
            private float j;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view3, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.d = motionEvent.getRawX();
                    this.j = motionEvent.getRawY();
                    this.b = true;
                    Consumer consumer2 = Consumer.this;
                    if (consumer2 != null) {
                        consumer2.accept(true);
                    }
                } else if (action == 1) {
                    view3.performClick();
                    this.b = false;
                    float rawX = motionEvent.getRawX() - this.d;
                    float rawY = motionEvent.getRawY() - this.j;
                    LogUtil.a(ScrollUtil.d, "MotionEvent.ACTION_UP, offsetX: ", Float.valueOf(rawX), " offsetY: ", Float.valueOf(rawY));
                    if (Math.abs(rawX) / 2.0f < Math.abs(rawY)) {
                        ScrollUtil.cKA_(rawY, view2, i);
                    }
                    Consumer consumer3 = Consumer.this;
                    if (consumer3 != null) {
                        consumer3.accept(false);
                    }
                } else if (action == 2) {
                    if (!this.b) {
                        this.d = motionEvent.getRawX();
                        this.j = motionEvent.getRawY();
                        this.b = true;
                    }
                    Consumer consumer4 = Consumer.this;
                    if (consumer4 != null) {
                        consumer4.accept(true);
                    }
                }
                return false;
            }
        });
    }

    public static void cKx_(View view, View view2, int i) {
        cKy_(view, view2, i, null);
    }

    public static void cKz_(View view, final ScrollStateListener scrollStateListener) {
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.commonui.utils.ScrollUtil.1

            /* renamed from: a, reason: collision with root package name */
            private boolean f8970a = false;
            private float d;
            private float e;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.d = motionEvent.getRawX();
                    this.e = motionEvent.getRawY();
                    this.f8970a = true;
                } else if (action != 1) {
                    if (action == 2 && !this.f8970a) {
                        this.d = motionEvent.getRawX();
                        this.e = motionEvent.getRawY();
                        this.f8970a = true;
                    }
                } else {
                    view2.performClick();
                    this.f8970a = false;
                    float rawX = motionEvent.getRawX() - this.d;
                    float rawY = motionEvent.getRawY() - this.e;
                    LogUtil.a(ScrollUtil.d, "MotionEvent.ACTION_UP, offsetX: ", Float.valueOf(rawX), " offsetY: ", Float.valueOf(rawY));
                    if (Math.abs(rawX) / 2.0f < Math.abs(rawY)) {
                        ScrollUtil.b(rawY, ScrollStateListener.this);
                    }
                }
                return false;
            }
        });
    }

    private static void cKB_(View view, int i) {
        String str = d;
        LogUtil.a(str, "swipeDown");
        int i2 = b;
        if (i2 == 0 || i2 == 1) {
            b = -1;
            LogUtil.c(str, "setScreenTouchListening Fling swipeDown!");
            if (view == null) {
                return;
            }
            LogUtil.a(str, "floatintView show positionId: ", Integer.valueOf(i));
            HealthFloatingView cKu_ = cKu_(view, i);
            if (cKu_ != null) {
                LogUtil.a(str, "floatintView show exist.");
                cKu_.j();
            } else {
                LogUtil.a(str, "floatintView show didn't exist.");
            }
        }
    }

    private static void cKC_(View view, int i) {
        int i2 = b;
        if (i2 == 0 || i2 == -1) {
            b = 1;
            String str = d;
            LogUtil.c(str, "setScreenTouchListening Fling swipeUp!");
            if (view == null) {
                return;
            }
            LogUtil.a(str, "floatintView show positionId: ", Integer.valueOf(i));
            HealthFloatingView cKu_ = cKu_(view, i);
            if (cKu_ != null) {
                LogUtil.a(str, "floatintView hide exist.");
                cKu_.c();
            } else {
                LogUtil.a(str, "floatintView hide didn't exist.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cKA_(float f, View view, int i) {
        String str = d;
        LogUtil.a(str, "setSwipeUP, positionId: ", Integer.valueOf(i), " offsetY: ", Float.valueOf(f));
        if (f < -5.0f) {
            cKC_(view, i);
        } else if (f > 5.0f) {
            cKB_(view, i);
        } else {
            LogUtil.c(str, "setScreenTouchListening no branch!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(float f, ScrollStateListener scrollStateListener) {
        if (f < -5.0f) {
            scrollStateListener.swipUp();
        } else if (f > 5.0f) {
            scrollStateListener.swipDown();
        } else {
            LogUtil.c(d, "setScreenTouchListening no branch!");
        }
    }

    public static HealthFloatingView cKu_(View view, int i) {
        List<Integer> d2 = nmh.d();
        if (!koq.b(d2) && d2.contains(Integer.valueOf(i))) {
            View findViewById = view.findViewById(3044);
            if (findViewById instanceof HealthFloatingView) {
                return (HealthFloatingView) findViewById;
            }
        }
        View findViewById2 = view.findViewById(i);
        if (findViewById2 instanceof HealthFloatingView) {
            return (HealthFloatingView) findViewById2;
        }
        return null;
    }
}
