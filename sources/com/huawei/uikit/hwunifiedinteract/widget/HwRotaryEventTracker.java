package com.huawei.uikit.hwunifiedinteract.widget;

import android.content.Context;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes9.dex */
public class HwRotaryEventTracker {

    /* renamed from: a, reason: collision with root package name */
    private MotionEvent f10773a;
    private MotionEvent d;
    private View e;
    private long b = -1;
    private c c = new c();
    private float f = 0.0f;

    public interface OnRotaryListener {
        boolean onBeginScroll(MotionEvent motionEvent);

        boolean onEndScroll(MotionEvent motionEvent);

        boolean onMiddleScroll(MotionEvent motionEvent);
    }

    class c implements Runnable {
        private OnRotaryListener c;

        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwRotaryEventTracker.this.eiB_(null, this.c);
        }
    }

    public HwRotaryEventTracker(Context context) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean eiB_(android.view.MotionEvent r7, com.huawei.uikit.hwunifiedinteract.widget.HwRotaryEventTracker.OnRotaryListener r8) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwunifiedinteract.widget.HwRotaryEventTracker.eiB_(android.view.MotionEvent, com.huawei.uikit.hwunifiedinteract.widget.HwRotaryEventTracker$OnRotaryListener):boolean");
    }

    private MotionEvent eiz_(float f) {
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.x = 0.0f;
        pointerCoords.y = 0.0f;
        pointerCoords.setAxisValue(26, f);
        MotionEvent.PointerCoords[] pointerCoordsArr = {pointerCoords};
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = 0;
        long uptimeMillis = SystemClock.uptimeMillis();
        return MotionEvent.obtain(uptimeMillis, uptimeMillis, 8, 1, new MotionEvent.PointerProperties[]{pointerProperties}, pointerCoordsArr, 0, 0, 1.0f, 1.0f, 0, 0, 4194304, 0);
    }
}
