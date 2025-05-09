package health.compact.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import defpackage.jah;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class ThirdMobileManager {
    private static final String[] e = {"com.tencent.qqpimsecure", "com.qihoo360.mobilesafe", "com.mpoyit.zawcgm", "com.zxly.assist", "com.anguanjia.safe", "com.fractalist.SystemOptimizer", "com.tencent.qlauncher.lite", "com.baoruan.launcher2", "com.hola.launcher", "com.dianxinos.dxhome", "com.nd.android.pandahome2", "com.mili.launcher", "com.tencent.launcher", "com.Dean.launcher", "com.gau.go.launcherex", "com.cleanmaster.mguard_cn", "com.isyezon.kbatterydoctor", "com.mdhlkj.batterysaver", "com.ijinshan.kbatterydoctor", "com.zlkj.cjszgj", "kaiqi.mobileguard"};

    private ThirdMobileManager() {
    }

    public static List<String> c(Context context) {
        PackageInfo packageInfo;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_ThirdMobileManager", "hasThirdMobileManager context is null,hasThirdMobileManager return false");
            return new ArrayList();
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_ThirdMobileManager", "PackageManager is null,hasThirdMobileManager return false");
            return new ArrayList();
        }
        List<String> c = c();
        ArrayList arrayList = new ArrayList();
        for (String str : c) {
            try {
                packageInfo = packageManager.getPackageInfo(str, 0);
            } catch (PackageManager.NameNotFoundException e2) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_ThirdMobileManager", "hasThirdMobileManager exception", e2);
                packageInfo = null;
            }
            if (packageInfo != null) {
                arrayList.add(packageInfo.packageName);
                com.huawei.hwlogsmodel.LogUtil.a("Step_ThirdMobileManager", "package : ", str);
            }
        }
        com.huawei.hwlogsmodel.LogUtil.a("Step_ThirdMobileManager", "hasThirdMobileManager return :", Boolean.valueOf(koq.b(arrayList)));
        return arrayList;
    }

    private static List<String> c() {
        String e2 = jah.c().e("thirdMobileManager_kill_app_live");
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_ThirdMobileManager", "getThirdAppPacksInfo: ", e2);
        if (TextUtils.isEmpty(e2)) {
            return Arrays.asList(e);
        }
        String[] split = e2.trim().split(";");
        if (split.length == 0) {
            return Arrays.asList(e);
        }
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        String[] strArr = e;
        ArrayList arrayList = new ArrayList(strArr.length + split.length);
        arrayList.addAll(Arrays.asList(strArr));
        arrayList.addAll(Arrays.asList(split));
        return arrayList;
    }
}
