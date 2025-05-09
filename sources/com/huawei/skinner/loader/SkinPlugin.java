package com.huawei.skinner.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import defpackage.ncs;
import defpackage.ncx;
import defpackage.ndd;
import defpackage.ndf;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class SkinPlugin {
    public static final int PLUGIN_TYPE_DAY_NIGHT = 1;
    public static final int PLUGIN_TYPE_MULTI_PACKET_COMBINE = 2;
    public static final int PLUGIN_TYPE_SINGLE_PACKAGE = 0;
    Context mHostContext;
    String mLocation;
    PackageParser.Package mPackage;
    PackageInfo mPackageInfo;
    Context mPluginContext;
    ncs mPluginManager;
    Resources mResources;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PluginType {
    }

    public SkinPlugin getCurrentSkinPlugin() {
        return this;
    }

    public SkinPlugin getCurrentSkinPlugin(int i) {
        return this;
    }

    public SkinPlugin getCurrentSkinPlugin(String str, String str2) {
        return this;
    }

    public int getPluginType() {
        return 0;
    }

    SkinPlugin(ncs ncsVar, Context context, File file) throws Exception {
        this.mPluginManager = ncsVar;
        this.mHostContext = context;
        this.mLocation = file.getAbsolutePath();
    }

    public String getLocation() {
        return this.mLocation;
    }

    public ncs getPluginManager() {
        return this.mPluginManager;
    }

    public Context getHostContext() {
        return this.mHostContext;
    }

    public Context getPluginContext() {
        return this.mPluginContext;
    }

    public String getPackageName() {
        PackageParser.Package r0 = this.mPackage;
        return r0 != null ? r0.packageName : "";
    }

    public void updateResources(Resources resources) {
        this.mResources = resources;
    }

    public AssetManager getAssets() {
        Resources resources = this.mResources;
        return resources != null ? resources.getAssets() : this.mHostContext.getAssets();
    }

    public Resources getResources() {
        Resources resources = this.mResources;
        return resources != null ? resources : this.mHostContext.getResources();
    }

    public Resources.Theme getTheme() {
        Resources.Theme newTheme = this.mResources.newTheme();
        newTheme.applyStyle(ndf.e(this.mPackage.applicationInfo.theme, Build.VERSION.SDK_INT), false);
        return newTheme;
    }

    public void setTheme(int i) {
        ndd.a(Resources.class, this.mResources, "mThemeResId", Integer.valueOf(i));
    }

    static Resources createResources(Context context, File file) {
        if (Build.VERSION.SDK_INT > 28) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            applicationInfo.sourceDir = file.getAbsolutePath();
            applicationInfo.publicSourceDir = file.getAbsolutePath();
            try {
                return context.getPackageManager().getResourcesForApplication(applicationInfo);
            } catch (PackageManager.NameNotFoundException e) {
                ncx.e("SkinLoadedPlugin.createResources exception = " + e);
                return context.getResources();
            }
        }
        Resources resources = context.getResources();
        return new Resources(createAssetManager(file), resources.getDisplayMetrics(), resources.getConfiguration());
    }

    private static AssetManager createAssetManager(File file) {
        try {
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            ndd.c(AssetManager.class, assetManager, "addAssetPath", file.getAbsolutePath());
            return assetManager;
        } catch (Exception e) {
            ncx.e("SkinLoadedPlugin.createAssetManager exception: " + e.getMessage());
            return null;
        }
    }

    static String createLocation(int i, List<File> list) {
        int size = list.size();
        if (size == 1) {
            return list.get(0).getAbsolutePath();
        }
        String locationSeparator = getLocationSeparator(i);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(list.get(i2).getAbsolutePath());
            if (i2 != size - 1) {
                sb.append(locationSeparator);
            }
        }
        return sb.toString();
    }

    public static String getLocationSeparator(int i) {
        return i != 0 ? i != 1 ? "&" : "|" : "";
    }
}
