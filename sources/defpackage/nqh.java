package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* loaded from: classes6.dex */
public class nqh {

    /* renamed from: a, reason: collision with root package name */
    private float f15441a;
    private float b;
    private boolean c;
    private final Handler d;
    private MotionEvent e;
    private int g;
    private final GestureDetector.OnGestureListener h;
    private boolean i;

    class a extends Handler {
        a() {
        }

        a(Handler handler) {
            super(handler.getLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 2) {
                return;
            }
            nqh.this.b();
        }
    }

    public nqh(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public nqh(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        if (handler != null) {
            this.d = new a(handler);
        } else {
            this.d = new a();
        }
        this.h = onGestureListener;
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.g = scaledTouchSlop * scaledTouchSlop;
    }

    public void cFF_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return;
        }
        cFE_(motionEvent, motionEvent.getAction(), motionEvent.getX(), motionEvent.getY());
    }

    private void cFE_(MotionEvent motionEvent, int i, float f, float f2) {
        int i2 = i & 255;
        if (i2 == 0) {
            this.b = f;
            this.f15441a = f2;
            MotionEvent motionEvent2 = this.e;
            if (motionEvent2 != null) {
                motionEvent2.recycle();
            }
            this.e = MotionEvent.obtain(motionEvent);
            this.c = true;
            this.i = false;
            this.d.removeMessages(2);
            this.d.sendEmptyMessageAtTime(2, this.e.getDownTime() + 50);
            return;
        }
        if (i2 == 1) {
            if (this.c) {
                this.h.onSingleTapUp(motionEvent);
            }
            this.d.removeMessages(2);
            this.c = false;
            this.i = false;
            return;
        }
        if (i2 != 2) {
            if (i2 == 3 || i2 == 5) {
                this.d.removeMessages(2);
                this.c = false;
                this.i = false;
                return;
            }
            return;
        }
        if (!this.i && this.c) {
            int i3 = (int) (f - this.b);
            int i4 = (int) (f2 - this.f15441a);
            if ((i3 * i3) + (i4 * i4) > this.g) {
                this.c = false;
                this.d.removeMessages(2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.i = true;
        this.h.onLongPress(this.e);
    }
}
