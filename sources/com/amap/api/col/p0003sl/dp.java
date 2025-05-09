package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;

/* loaded from: classes2.dex */
public final class dp {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f975a = new File("/system/framework/amap.jar").exists();

    public static AssetManager a(Context context) {
        if (context == null) {
            return null;
        }
        AssetManager assets = context.getAssets();
        if (f975a) {
            try {
                assets.getClass().getDeclaredMethod("addAssetPath", String.class).invoke(assets, "/system/framework/amap.jar");
            } catch (Throwable th) {
                iv.c(th, "ResourcesUtil", "getSelfAssets");
            }
        }
        return assets;
    }

    public static int b(Context context) {
        return (int) ((context.getResources().getDisplayMetrics().density * 1.0f) + 0.5f);
    }
}
