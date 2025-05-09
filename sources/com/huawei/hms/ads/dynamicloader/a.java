package com.huawei.hms.ads.dynamicloader;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.LayoutInflater;

/* loaded from: classes4.dex */
public class a extends ContextWrapper {

    /* renamed from: a, reason: collision with root package name */
    private int f4306a;
    private Resources.Theme b;
    private LayoutInflater c;

    @Override // android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        if (this.f4306a != i) {
            this.f4306a = i;
            a();
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.b;
        if (theme != null) {
            return theme;
        }
        int i = this.f4306a;
        int i2 = getApplicationInfo().targetSdkVersion;
        if (i == 0) {
            i = i2 < 11 ? R.style.Theme : i2 >= 14 ? i2 < 24 ? R.style.Theme.DeviceDefault : R.style.Theme.DeviceDefault.Light.DarkActionBar : R.style.Theme.Holo;
        }
        this.f4306a = i;
        a();
        return this.b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return super.getSystemService(str);
        }
        if (this.c == null) {
            this.c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    private void a() {
        if (this.b == null) {
            this.b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.b.setTo(theme);
            }
        }
        this.b.applyStyle(this.f4306a, true);
    }

    public a(Context context) {
        super(context);
    }
}
