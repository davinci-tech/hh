package com.huawei.phoneservice.feedback.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.common.Logger;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.phoneservice.faq.base.network.SdkAppInfo;
import com.huawei.phoneservice.faq.base.tracker.d;
import com.huawei.phoneservice.faq.base.util.FaqBaseCallback;
import com.huawei.phoneservice.faq.base.util.FaqHwFrameworkUtil;
import com.huawei.phoneservice.faq.base.util.b;
import com.huawei.phoneservice.faq.base.util.g;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.o;
import com.huawei.phoneservice.faq.base.util.t;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: classes5.dex */
public abstract class FeedbackAbstractBaseActivity extends FragmentActivity {

    /* renamed from: a, reason: collision with root package name */
    public boolean f8283a;
    private boolean b;

    protected abstract int j();

    protected abstract void l();

    protected abstract void m();

    protected abstract void n();

    protected abstract int p();

    protected abstract int r();

    @Override // android.app.Activity
    public void setTitle(int i) {
        setTitle(getString(i));
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        if (Build.VERSION.SDK_INT == 26 && o()) {
            return;
        }
        super.setRequestedOrientation(i);
    }

    protected void q() {
        t.cdq_(this, i());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        d(TrackConstants$Opers.STOPPED);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        d(TrackConstants$Opers.STARTED);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return true;
        }
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
        return onOptionsItemSelected;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        g.b(this);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        FaqBaseCallback callback = j.e().getCallback();
        if (callback != null) {
            i.d("FeedbackAbstractActivity", " baseCallback ");
            View onCreateView = callback.onCreateView(getWindow(), str, context, attributeSet);
            if (onCreateView != null) {
                i.d("FeedbackAbstractActivity", "return view");
                return onCreateView;
            }
        }
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        SdkAppInfo.initAppInfo(getApplication());
        int identifier = getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
        if (identifier == 0) {
            this.f8283a = true;
            identifier = R.style.FeedbackTheme;
        }
        super.setTheme(identifier);
        int i = Build.VERSION.SDK_INT;
        if (i == 26 && o()) {
            i.b("FeedbackAbstractActivity", "onCreate fixOrientation when Oreo, result = " + h());
        }
        FaqHwFrameworkUtil.cdf_(this, 1);
        com.huawei.phoneservice.faq.base.util.d.cdc_(this, new int[]{android.R.id.content}, p());
        if (i >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
        t.cds_(this, this.f8283a, getResources().getColor(R.color.faq_emui_white_bg));
        g();
        super.onCreate(bundle);
        try {
            setContentView(j());
            t.cdr_(this);
            n();
            m();
            l();
            if (b.c()) {
                q();
            }
        } catch (RuntimeException e) {
            Log.e("FeedbackAbstractActivity", "RuntimeException:" + e.getMessage());
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        q();
        boolean e = o.e(this);
        i.b("FeedbackAbstractActivity", "onConfigurationChanged newConfig.orientation:%s", Integer.valueOf(configuration.orientation));
        o.cdl_(this, k(), r());
        if (e != this.b) {
            this.b = e;
            setRequestedOrientation((e || b.c()) ? -1 : 1);
        }
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }

    protected int[] k() {
        return new int[]{0};
    }

    public int[] i() {
        return new int[]{android.R.id.content};
    }

    private boolean o() {
        boolean z;
        Exception e;
        Method method;
        try {
            TypedArray obtainStyledAttributes = obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null));
            method = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            AccessController.doPrivileged(new c(method));
            z = ((Boolean) method.invoke(null, obtainStyledAttributes)).booleanValue();
        } catch (Exception e2) {
            z = false;
            e = e2;
        }
        try {
            method.setAccessible(false);
        } catch (Exception e3) {
            e = e3;
            i.c("FeedbackAbstractActivity", e.getMessage());
            return z;
        }
        return z;
    }

    private boolean h() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mActivityInfo");
            AccessController.doPrivileged(new d(declaredField));
            ActivityInfo activityInfo = (ActivityInfo) declaredField.get(this);
            if (activityInfo != null) {
                activityInfo.screenOrientation = -1;
            }
            declaredField.setAccessible(false);
            return true;
        } catch (Exception e) {
            i.c("FeedbackAbstractActivity", e.getMessage());
            return false;
        }
    }

    class c implements PrivilegedAction<Object> {
        final /* synthetic */ Method e;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.e.setAccessible(true);
            return null;
        }

        c(Method method) {
            this.e = method;
        }
    }

    class d implements PrivilegedAction<Object> {
        final /* synthetic */ Field d;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.d.setAccessible(true);
            return null;
        }

        d(Field field) {
            this.d = field;
        }
    }

    private void g() {
        try {
            this.b = o.e(this);
            if (o.c(this)) {
                setRequestedOrientation(-1);
            } else {
                setRequestedOrientation(1);
            }
        } catch (IllegalStateException e) {
            Logger.d("FeedbackAbstractActivity", "e: " + e.getMessage());
        }
    }

    private void d(String str) {
        String simpleName = getClass().getSimpleName();
        CharSequence title = getTitle();
        com.huawei.phoneservice.faq.base.tracker.d.d("activity", new d.e().c(str).b(title == null ? "" : title.toString()).a(simpleName).e().b());
    }
}
