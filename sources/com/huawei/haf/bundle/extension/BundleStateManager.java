package com.huawei.haf.bundle.extension;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleSetting;
import com.huawei.haf.store.ExtendSharedPreferences;
import com.huawei.haf.store.SharedStoreManager;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class BundleStateManager implements AppBundleSetting {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2065a = false;
    private volatile boolean b;
    private boolean c;
    private String d;
    private String e;

    private ExtendSharedPreferences h() {
        ExtendSharedPreferences c = SharedStoreManager.c("app_bundle_info");
        if (!this.b) {
            c.edit().remove("user_agreed_module_set").apply();
            this.b = true;
        }
        return c;
    }

    private void b(String str, boolean z) {
        ExtendSharedPreferences h = h();
        if (z) {
            h.addStringSet("installed_module_set", str);
        } else {
            h.removeStringSet("installed_module_set", str);
        }
    }

    private void d(String str, boolean z) {
        ExtendSharedPreferences h = h();
        if (z) {
            h.addStringSet("force_agreed_module_set", str);
        } else {
            h.removeStringSet("force_agreed_module_set", str);
        }
    }

    public Set<String> c() {
        ExtendSharedPreferences h = h();
        HashSet hashSet = new HashSet();
        Set<String> stringSet = h.getStringSet("installed_module_set", null);
        if (stringSet != null) {
            hashSet.addAll(stringSet);
        }
        Set<String> stringSet2 = h.getStringSet("force_agreed_module_set", null);
        if (stringSet2 != null) {
            hashSet.addAll(stringSet2);
        }
        return hashSet;
    }

    @Override // com.huawei.haf.bundle.AppBundleSetting
    public void setAllowAutoUpdateModule(boolean z) {
        h().edit().putBoolean("auto_update_enable", z).apply();
    }

    @Override // com.huawei.haf.bundle.AppBundleSetting
    public boolean isAllowAutoUpdateModule() {
        return h().getBoolean("auto_update_enable", true);
    }

    @Override // com.huawei.haf.bundle.AppBundleSetting
    public void setAllowUsingMobileUpdateModule(boolean z) {
        h().edit().putBoolean("use_mobile_update_enable", z).apply();
    }

    @Override // com.huawei.haf.bundle.AppBundleSetting
    public boolean isAllowUsingMobileUpdateModule() {
        return h().getBoolean("use_mobile_update_enable", false);
    }

    @Override // com.huawei.haf.bundle.AppBundleSetting
    public void setAllowDownloadModule(Context context, String str, boolean z) {
        if (!z) {
            d(str, z);
        } else {
            if (!AppBundle.c().isBundleModule(str) || AppBundle.c().isBuiltInModule(context, str)) {
                return;
            }
            d(str, z);
        }
    }

    @Override // com.huawei.haf.bundle.AppBundleSetting
    public void updateModuleState(Context context, String str, boolean z) {
        if (!z) {
            b(str, z);
        } else if (AppBundle.c().isBundleModule(str)) {
            b(str, z);
        }
    }

    @Override // com.huawei.haf.bundle.AppBundleSetting
    public boolean isAllowDownloadModule(Context context, String str) {
        if (!AppBundle.c().isBundleModule(str) || AppBundle.c().isBuiltInModule(context, str)) {
            return true;
        }
        return h().containsStringSet("force_agreed_module_set", str);
    }

    @Override // com.huawei.haf.bundle.AppBundleSetting
    public void setTestEnvironment(String str, boolean z, String str2) {
        this.e = str;
        this.f2065a = z;
        this.d = str2;
        this.c = (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) ? false : true;
    }

    public boolean e() {
        return this.c;
    }

    public boolean b() {
        if (e()) {
            return this.f2065a;
        }
        return true;
    }

    public String d() {
        if (e()) {
            return this.e;
        }
        return null;
    }

    public String a() {
        if (e()) {
            return this.d;
        }
        return null;
    }
}
