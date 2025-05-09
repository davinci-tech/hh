package com.huawei.healthcloud.plugintrack.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.basesport.controls.ControlButtonLayout;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hjx;
import defpackage.jcf;
import defpackage.nrs;
import defpackage.nsf;

/* loaded from: classes4.dex */
public class ToolsLayout extends FrameLayout implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    /* renamed from: a, reason: collision with root package name */
    private CircleProgressButton f3802a;
    private int b;
    private ConstraintLayout c;
    private ControlButtonLayout d;
    private ConstraintLayout e;
    private IBaseResponseCallback f;
    private Handler g;
    private View h;
    private GestureDetector i;

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public ToolsLayout(Context context) {
        this(context, null);
    }

    public ToolsLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ToolsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 8;
        this.f = new IBaseResponseCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ToolsLayout.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.a("Track_ToolsLayout", "mResetAnimateCallback = ", Integer.valueOf(i2));
                ToolsLayout.this.g.removeMessages(3);
                if (i2 == 100) {
                    ToolsLayout.this.g.sendMessageDelayed(ToolsLayout.this.g.obtainMessage(3), 2000L);
                } else if (i2 == 101) {
                    ToolsLayout.this.g.sendEmptyMessage(4);
                } else if (i2 == 102) {
                    ToolsLayout.this.g.sendEmptyMessage(4);
                } else {
                    LogUtil.h("Track_ToolsLayout", "error code ", Integer.valueOf(i2));
                }
            }
        };
        this.g = new c(Looper.getMainLooper(), this);
    }

    public void setShowOrHideView(View view) {
        this.h = view;
    }

    public ControlButtonLayout getControlButtonLayout() {
        if (this.d == null) {
            this.d = (ControlButtonLayout) findViewById(R.id.control_button);
        }
        return this.d;
    }

    public ConstraintLayout getEcologyRopeControlPanelLayout() {
        if (this.c == null) {
            this.c = (ConstraintLayout) findViewById(R.id.ecology_rope_control_panel);
        }
        return this.c;
    }

    public ConstraintLayout getBottomPanel() {
        if (this.e == null) {
            this.e = (ConstraintLayout) findViewById(R.id.bottom_panel);
        }
        return this.e;
    }

    public CircleProgressButton getCircleProgressButton() {
        if (this.f3802a == null) {
            CircleProgressButton circleProgressButton = (CircleProgressButton) findViewById(R.id.sport_control_button);
            this.f3802a = circleProgressButton;
            jcf.bEG_(circleProgressButton, nsf.j(R.string._2130839724_res_0x7f0208ac));
        }
        return this.f3802a;
    }

    public void c() {
        GestureDetector gestureDetector = new GestureDetector(getContext(), this);
        gestureDetector.setOnDoubleTapListener(this);
        setGestureDetector(gestureDetector);
        a();
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        a();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        a();
        return true;
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.i = gestureDetector;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        GestureDetector gestureDetector = this.i;
        if (gestureDetector == null) {
            return true;
        }
        return gestureDetector.onTouchEvent(motionEvent);
    }

    private void a() {
        a(this.b == 0 ? 4 : 0);
        if (jcf.c()) {
            LogUtil.a("Track_ToolsLayout", "toggleToolView isTouchExplorationEnabled");
        } else {
            this.g.removeMessages(3);
            this.g.sendMessageDelayed(this.g.obtainMessage(3), 2000L);
        }
    }

    public IBaseResponseCallback getAnimateCallback() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        this.b = i;
        View view = this.h;
        if (view == null) {
            return;
        }
        view.animate().translationY(b(i)).setInterpolator(hjx.bhd_()).setDuration(200L).setListener(new AnimatorListenerAdapter() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ToolsLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (i == 0) {
                    ToolsLayout.this.h.setVisibility(i);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (i != 0) {
                    ToolsLayout.this.h.setVisibility(i);
                }
            }
        }).start();
    }

    private int b(int i) {
        int[] iArr = new int[2];
        this.h.getLocationInWindow(iArr);
        if (i == 0) {
            return 0;
        }
        return (Math.max(nrs.d(getContext()), getHeight()) + 20) - iArr[1];
    }

    /* loaded from: classes8.dex */
    static class c extends BaseHandler<ToolsLayout> {
        c(Looper looper, ToolsLayout toolsLayout) {
            super(looper, toolsLayout);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bjD_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ToolsLayout toolsLayout, Message message) {
            if (message == null) {
                LogUtil.h("Track_ToolsLayout", "handleMessage message is null");
                return;
            }
            int i = message.what;
            if (i != 3) {
                if (i == 4 && toolsLayout.b == 8) {
                    toolsLayout.a(0);
                    return;
                }
                return;
            }
            if (toolsLayout.b == 0) {
                toolsLayout.a(8);
            }
        }
    }
}
