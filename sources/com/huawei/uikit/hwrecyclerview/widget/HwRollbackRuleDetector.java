package com.huawei.uikit.hwrecyclerview.widget;

import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class HwRollbackRuleDetector {
    private static final String e = "HwRollbackRuleDetector";

    /* renamed from: a, reason: collision with root package name */
    private int f10730a;
    private View b;
    private GestureDetector c;
    private int d;
    private RollBackScrollListener f;
    private Class<?> g;
    private Context h;
    private boolean i;
    private Object j;
    private Handler l = new a();
    private GestureDetector.OnGestureListener o = new d();

    public interface RollBackScrollListener {
        int getScrollYDistance();
    }

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            HwRollbackRuleDetector.this.f10730a = 0;
        }
    }

    class d implements GestureDetector.OnGestureListener {
        d() {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            HwRollbackRuleDetector.this.l.removeMessages(1);
            if (motionEvent == null || motionEvent2 == null || motionEvent.getY() >= motionEvent2.getY() || Math.abs(f2) <= Math.abs(f)) {
                HwRollbackRuleDetector.this.f10730a = 0;
            } else {
                HwRollbackRuleDetector.b(HwRollbackRuleDetector.this);
                HwRollbackRuleDetector.this.d();
                if (HwRollbackRuleDetector.this.c() && HwRollbackRuleDetector.this.a()) {
                    HwRollbackRuleDetector.this.f10730a = 0;
                    HwRollbackRuleDetector.this.b("com.huawei.control.intent.action.RollBackEvent");
                }
                HwRollbackRuleDetector.this.l.sendEmptyMessageDelayed(1, 1000L);
            }
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
    }

    public HwRollbackRuleDetector(RollBackScrollListener rollBackScrollListener) {
        this.f = rollBackScrollListener;
    }

    public void b(String str) {
        Class<?> cls = this.g;
        if (cls == null || this.j == null) {
            return;
        }
        try {
            cls.getDeclaredMethod("executeEvent", String.class).invoke(this.j, str);
        } catch (IllegalAccessException unused) {
            Log.w(e, "com.huawei.decision.DecisionHelper.executeEvent() IllegalAccessException!");
        } catch (NoSuchMethodException unused2) {
            Log.w(e, "com.huawei.decision.DecisionHelper no function executeEvent()!");
        } catch (InvocationTargetException unused3) {
            Log.w(e, "com.huawei.decision.DecisionHelper.executeEvent() InvocationTargetException!");
        }
    }

    public void e() {
        if (this.i) {
            if (this.h == null) {
                Log.w(e, "mServiceContext is null");
                return;
            }
            Class<?> cls = this.g;
            if (cls != null && this.j != null) {
                try {
                    cls.getDeclaredMethod("unbindService", Context.class).invoke(this.j, this.h);
                } catch (IllegalAccessException unused) {
                    Log.w(e, "com.huawei.decision.DecisionHelper.unbindService() Illegal Access");
                } catch (NoSuchMethodException unused2) {
                    Log.w(e, "com.huawei.decision.DecisionHelper no function unbindService()");
                } catch (InvocationTargetException unused3) {
                    Log.w(e, "com.huawei.decision.DecisionHelper.unbindService() Invocation Target");
                }
                this.g = null;
            }
            this.b = null;
            this.c = null;
            this.i = false;
        }
    }

    public void ego_(MotionEvent motionEvent) {
        GestureDetector gestureDetector;
        if (this.i && (gestureDetector = this.c) != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
    }

    public void egp_(View view) {
        if (view == null) {
            Log.w(e, "view is null!");
            return;
        }
        if (this.i) {
            return;
        }
        Context context = view.getContext();
        if (context == null) {
            Log.w(e, "context is null");
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.h = context;
        if (a(context)) {
            try {
                Class<?> cls = Class.forName("com.huawei.decision.DecisionHelper");
                Object newInstance = cls.newInstance();
                cls.getDeclaredMethod("bindService", Context.class).invoke(newInstance, this.h);
                this.c = new GestureDetector(this.h, this.o);
                this.g = cls;
                this.j = newInstance;
                this.b = view;
                this.i = true;
            } catch (ReceiverCallNotAllowedException unused) {
                Log.w(e, "There is a problem with the APP application scenario:BroadcastReceiver components are not allowed to register to receive intents");
            } catch (ClassNotFoundException unused2) {
                Log.w(e, "com.huawei.decision.DecisionHelper not found!");
            } catch (IllegalAccessException unused3) {
                Log.w(e, "com.huawei.decision.DecisionHelper.bindService() Illegal Access");
            } catch (InstantiationException unused4) {
                Log.w(e, "com.huawei.decision.DecisionHelper.bindService() InstantiationException!");
            } catch (NoSuchMethodException unused5) {
                Log.w(e, "com.huawei.decision.DecisionHelper no function bindService()");
            } catch (InvocationTargetException unused6) {
                Log.w(e, "com.huawei.decision.DecisionHelper.bindService() Invocation Target");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        RollBackScrollListener rollBackScrollListener = this.f;
        return rollBackScrollListener != null && rollBackScrollListener.getScrollYDistance() > this.d;
    }

    static /* synthetic */ int b(HwRollbackRuleDetector hwRollbackRuleDetector) {
        int i = hwRollbackRuleDetector.f10730a;
        hwRollbackRuleDetector.f10730a = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        return this.f10730a > 3;
    }

    private boolean a(Context context) {
        if (context != null) {
            return (Settings.Secure.getInt(context.getContentResolver(), "com.huawei.recsys.LMT_FeatureRecStatus", 0) & 1) != 1;
        }
        Log.w(e, "isRollbackUnused context is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        View view = this.b;
        if (view != null) {
            this.d = view.getHeight() * 3;
        }
    }
}
