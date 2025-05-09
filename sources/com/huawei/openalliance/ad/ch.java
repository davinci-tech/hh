package com.huawei.openalliance.ad;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.hihonor.android.app.ActivityManagerEx;
import com.hihonor.android.app.HwMultiWindowEx;
import com.hihonor.android.app.PackageManagerEx;
import com.hihonor.android.content.pm.ApplicationInfoEx;
import com.hihonor.android.fsm.HwFoldScreenManagerEx;
import com.hihonor.android.provider.SettingsEx;
import com.hihonor.android.view.DisplaySideRegionEx;
import com.hihonor.android.view.WindowManagerEx;
import com.hihonor.android.widget.ActionBarEx;

/* loaded from: classes5.dex */
public class ch extends ce {

    /* renamed from: a, reason: collision with root package name */
    private static cq f6680a;
    private static final byte[] b = new byte[0];

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public boolean g() {
        return PackageManagerEx.hasHwSystemFeature("com.huawei.hardware.screen.type.eink");
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public String f() {
        return "com.hihonor.android.os.Build";
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public String e() {
        return "com.hihonor.android.os.Build$VERSION";
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public boolean d() {
        return HwFoldScreenManagerEx.isFoldable();
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public int c() {
        return HwFoldScreenManagerEx.getDisplayMode();
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public boolean b() {
        return HwMultiWindowEx.isInMultiWindowMode();
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public boolean a(Activity activity) {
        if (activity == null) {
            return false;
        }
        try {
            return ActivityManagerEx.getActivityWindowMode(activity) == 102;
        } catch (Throwable unused) {
            ho.c("HnSysApiImpl", "isFreedomWindowMode error");
            return false;
        }
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public void a(WindowManager.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return;
        }
        new WindowManagerEx.LayoutParamsEx(layoutParams).setDisplaySideMode(1);
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public void a(ActionBar actionBar, boolean z, Drawable drawable, View.OnClickListener onClickListener) {
        ActionBarEx.setStartIcon(actionBar, true, (Drawable) null, onClickListener);
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public com.huawei.openalliance.ad.utils.bp a() {
        return new com.huawei.openalliance.ad.utils.br();
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public Rect a(WindowInsets windowInsets) {
        DisplaySideRegionEx displaySideRegion = WindowManagerEx.LayoutParamsEx.getDisplaySideRegion(windowInsets);
        if (displaySideRegion != null) {
            return displaySideRegion.getSafeInsets();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public int a(ApplicationInfo applicationInfo) {
        return new ApplicationInfoEx(applicationInfo).getHwFlags();
    }

    @Override // com.huawei.openalliance.ad.ce, com.huawei.openalliance.ad.cq
    public int a(Context context) {
        return SettingsEx.getIntForUser(context.getContentResolver(), "accessibility_screenreader_enabled", -1, ActivityManagerEx.getCurrentUser());
    }

    private static cq c(Context context) {
        cq cqVar;
        synchronized (b) {
            if (f6680a == null) {
                f6680a = new ch(context);
            }
            cqVar = f6680a;
        }
        return cqVar;
    }

    public static cq b(Context context) {
        return c(context);
    }

    private ch(Context context) {
    }
}
