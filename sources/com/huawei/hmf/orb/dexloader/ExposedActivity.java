package com.huawei.hmf.orb.dexloader;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

/* loaded from: classes9.dex */
public class ExposedActivity extends Activity implements ExposedUIModule {
    ExposedActivityDelegate mExposedDelegate = ExposedActivityDelegate.create(this);

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mExposedDelegate.onCreate(bundle);
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.mExposedDelegate.getResources(super.getResources());
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.mExposedDelegate.getAssets(super.getAssets());
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        return this.mExposedDelegate.getTheme(super.getTheme());
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        this.mExposedDelegate.setTheme(i);
    }

    @Override // android.content.ContextWrapper
    public Context getBaseContext() {
        return this.mExposedDelegate.getBaseContext(super.getBaseContext());
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        return this.mExposedDelegate.getSystemService(str, super.getSystemService(str));
    }
}
