package com.huawei.uikit.hwunifiedinteract.widget;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

/* loaded from: classes7.dex */
public class HwKeyEventDetector {

    /* renamed from: a, reason: collision with root package name */
    private View f10772a = null;
    private OnEditEventListener c = null;
    private OnSearchEventListener d = null;
    private OnNextTabEventListener e = null;
    private OnGlobalNextTabEventListener b = null;
    private View.OnUnhandledKeyEventListener j = null;

    public interface OnEditEventListener {
        boolean onCopy(int i, KeyEvent keyEvent);

        boolean onCut(int i, KeyEvent keyEvent);

        boolean onDelete(int i, KeyEvent keyEvent);

        boolean onPaste(int i, KeyEvent keyEvent);

        boolean onSelectAll(int i, KeyEvent keyEvent);

        boolean onUndo(int i, KeyEvent keyEvent);
    }

    public interface OnGlobalNextTabEventListener {
        boolean onGlobalNextTab(int i, KeyEvent keyEvent);
    }

    public interface OnNextTabEventListener {
        boolean onNextTab(int i, KeyEvent keyEvent);
    }

    public interface OnSearchEventListener {
        boolean onSearch(int i, KeyEvent keyEvent);
    }

    class e implements View.OnUnhandledKeyEventListener {
        e() {
        }

        @Override // android.view.View.OnUnhandledKeyEventListener
        public boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent) {
            return HwKeyEventDetector.this.a(view, keyEvent);
        }
    }

    public HwKeyEventDetector(Context context) {
    }

    private boolean eiw_(int i, int i2, KeyEvent keyEvent) {
        OnEditEventListener onEditEventListener;
        if (i != 112 || (onEditEventListener = this.c) == null) {
            return false;
        }
        return onEditEventListener.onDelete(i2, keyEvent);
    }

    public void a(OnNextTabEventListener onNextTabEventListener) {
        this.e = onNextTabEventListener;
    }

    public void a(OnSearchEventListener onSearchEventListener) {
        this.d = onSearchEventListener;
    }

    public OnEditEventListener b() {
        return this.c;
    }

    public void c(OnEditEventListener onEditEventListener) {
        this.c = onEditEventListener;
    }

    public OnSearchEventListener d() {
        return this.d;
    }

    public void e() {
        b(false);
    }

    public boolean eix_(int i, KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        if (!keyEvent.isCtrlPressed()) {
            return eiw_(i, action, keyEvent);
        }
        if (eiu_(i, action, keyEvent)) {
            return true;
        }
        OnNextTabEventListener onNextTabEventListener = this.e;
        if (onNextTabEventListener != null && i == 61 && onNextTabEventListener.onNextTab(action, keyEvent)) {
            return true;
        }
        OnSearchEventListener onSearchEventListener = this.d;
        return onSearchEventListener != null && i == 34 && onSearchEventListener.onSearch(action, keyEvent);
    }

    public void eiy_(View view, OnGlobalNextTabEventListener onGlobalNextTabEventListener) {
        this.f10772a = view;
        this.b = onGlobalNextTabEventListener;
        b(onGlobalNextTabEventListener != null);
    }

    private void b(boolean z) {
        if (Build.VERSION.SDK_INT < 28) {
            Log.w("HwKeyEventDetector", "unhandledKeyEventListenerProc: need minimum sdk version 28.");
            return;
        }
        View view = this.f10772a;
        if (view == null) {
            return;
        }
        if (z) {
            if (this.j == null) {
                e eVar = new e();
                this.j = eVar;
                this.f10772a.addOnUnhandledKeyEventListener(eVar);
                return;
            }
            return;
        }
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener = this.j;
        if (onUnhandledKeyEventListener != null) {
            view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
            this.j = null;
        }
    }

    private boolean eiu_(int i, int i2, KeyEvent keyEvent) {
        OnEditEventListener onEditEventListener = this.c;
        if (onEditEventListener == null) {
            return false;
        }
        if (i != 29) {
            if (i != 31) {
                if (i != 50) {
                    if (i != 52) {
                        if (i == 54 && onEditEventListener.onUndo(i2, keyEvent)) {
                            return true;
                        }
                    } else if (onEditEventListener.onCut(i2, keyEvent)) {
                        return true;
                    }
                } else if (onEditEventListener.onPaste(i2, keyEvent)) {
                    return true;
                }
            } else if (onEditEventListener.onCopy(i2, keyEvent)) {
                return true;
            }
        } else if (onEditEventListener.onSelectAll(i2, keyEvent)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(View view, KeyEvent keyEvent) {
        return this.b != null && keyEvent.getKeyCode() == 61 && keyEvent.isCtrlPressed() && this.b.onGlobalNextTab(keyEvent.getAction(), keyEvent);
    }
}
