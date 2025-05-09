package androidx.core.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.BuildCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;

/* loaded from: classes.dex */
public class ComponentActivity extends Activity implements LifecycleOwner, KeyEventDispatcher.Component {
    private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap<>();
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Deprecated
    /* loaded from: classes8.dex */
    public static class ExtraData {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReportFragment.injectIfNeededIn(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        this.mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }

    @Deprecated
    public <T extends ExtraData> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.core.view.KeyEventDispatcher.Component
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return KeyEventDispatcher.dispatchKeyEvent(this, decorView, this, keyEvent);
        }
        return true;
    }

    protected final boolean shouldDumpInternalState(String[] strArr) {
        return !shouldSkipDump(strArr);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static boolean shouldSkipDump(String[] strArr) {
        char c;
        if (strArr == null || strArr.length <= 0) {
            return false;
        }
        String str = strArr[0];
        str.hashCode();
        switch (str.hashCode()) {
            case -645125871:
                if (str.equals("--translation")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 100470631:
                if (str.equals("--dump-dumpable")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 472614934:
                if (str.equals("--list-dumpables")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1159329357:
                if (str.equals("--contentcapture")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1455016274:
                if (str.equals("--autofill")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return Build.VERSION.SDK_INT >= 31;
        }
        if (c == 1 || c == 2) {
            return BuildCompat.isAtLeastT();
        }
        return c != 3 ? c == 4 : Build.VERSION.SDK_INT >= 29;
    }
}
