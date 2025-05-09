package androidx.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.alipay.sdk.m.p.e;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.uhy;
import defpackage.uib;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0019\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u000fJ\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0017J\u0012\u0010\u001d\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0015J\b\u0010 \u001a\u00020\u0013H\u0015J\b\u0010!\u001a\u00020\u0013H\u0015J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0007H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0011¨\u0006$"}, d2 = {"Landroidx/activity/ComponentDialog;", "Landroid/app/Dialog;", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/activity/OnBackPressedDispatcherOwner;", ParamConstants.Param.CONTEXT, "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "_lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "lifecycleRegistry", "getLifecycleRegistry", "()Landroidx/lifecycle/LifecycleRegistry;", "onBackPressedDispatcher", "Landroidx/activity/OnBackPressedDispatcher;", "getOnBackPressedDispatcher$annotations", "()V", "addContentView", "", "view", "Landroid/view/View;", e.n, "Landroid/view/ViewGroup$LayoutParams;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "getOnBackPressedDispatcher", "initViewTreeOwners", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onStop", "setContentView", "layoutResID", "activity_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public class ComponentDialog extends Dialog implements LifecycleOwner, OnBackPressedDispatcherOwner {
    private LifecycleRegistry _lifecycleRegistry;
    private final OnBackPressedDispatcher onBackPressedDispatcher;

    private static /* synthetic */ void getOnBackPressedDispatcher$annotations() {
    }

    public /* synthetic */ ComponentDialog(Context context, int i, int i2, uib uibVar) {
        this(context, (i2 & 2) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComponentDialog(Context context, int i) {
        super(context, i);
        uhy.e((Object) context, "");
        this.onBackPressedDispatcher = new OnBackPressedDispatcher(new Runnable() { // from class: androidx.activity.ComponentDialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ComponentDialog.m2onBackPressedDispatcher$lambda1(ComponentDialog.this);
            }
        });
    }

    private final LifecycleRegistry getLifecycleRegistry() {
        LifecycleRegistry lifecycleRegistry = this._lifecycleRegistry;
        if (lifecycleRegistry != null) {
            return lifecycleRegistry;
        }
        LifecycleRegistry lifecycleRegistry2 = new LifecycleRegistry(this);
        this._lifecycleRegistry = lifecycleRegistry2;
        return lifecycleRegistry2;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return getLifecycleRegistry();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 33) {
            this.onBackPressedDispatcher.setOnBackInvokedDispatcher(getOnBackInvokedDispatcher());
        }
        getLifecycleRegistry().handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        getLifecycleRegistry().handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Dialog
    public void onStop() {
        getLifecycleRegistry().handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        this._lifecycleRegistry = null;
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBackPressedDispatcher$lambda-1, reason: not valid java name */
    public static final void m2onBackPressedDispatcher$lambda1(ComponentDialog componentDialog) {
        uhy.e((Object) componentDialog, "");
        super.onBackPressed();
    }

    @Override // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.onBackPressedDispatcher;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        this.onBackPressedDispatcher.onBackPressed();
    }

    @Override // android.app.Dialog
    public void setContentView(int layoutResID) {
        initViewTreeOwners();
        super.setContentView(layoutResID);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        uhy.e((Object) view, "");
        initViewTreeOwners();
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        uhy.e((Object) view, "");
        initViewTreeOwners();
        super.setContentView(view, params);
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        uhy.e((Object) view, "");
        initViewTreeOwners();
        super.addContentView(view, params);
    }

    private final void initViewTreeOwners() {
        Window window = getWindow();
        uhy.d(window);
        ViewTreeLifecycleOwner.set(window.getDecorView(), this);
        Window window2 = getWindow();
        uhy.d(window2);
        View decorView = window2.getDecorView();
        uhy.a(decorView, "");
        ViewTreeOnBackPressedDispatcherOwner.set(decorView, this);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ComponentDialog(Context context) {
        this(context, 0, 2, null);
        uhy.e((Object) context, "");
    }
}
