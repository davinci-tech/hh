package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import java.util.HashSet;

/* loaded from: classes5.dex */
public class w {

    /* renamed from: a, reason: collision with root package name */
    private static w f7721a;
    private static final byte[] b = new byte[0];
    private Context c;
    private String d = "0";

    public int c() {
        if ("4".equalsIgnoreCase(this.d)) {
            return 8;
        }
        return "1".equalsIgnoreCase(this.d) ? 5 : 4;
    }

    public boolean b() {
        return "4".equalsIgnoreCase(a(this.c).a());
    }

    public String a() {
        return this.d;
    }

    private void d() {
        HashSet hashSet;
        String str;
        PackageManager packageManager = this.c.getPackageManager();
        if (packageManager == null) {
            ho.d("DeviceTypeUtil", "packageManager is null.");
            return;
        }
        try {
            FeatureInfo[] systemAvailableFeatures = packageManager.getSystemAvailableFeatures();
            hashSet = new HashSet();
            if (systemAvailableFeatures != null) {
                for (FeatureInfo featureInfo : systemAvailableFeatures) {
                    if (!TextUtils.isEmpty(featureInfo.name)) {
                        ho.a("DeviceTypeUtil", "add feature:" + featureInfo.name);
                        hashSet.add(featureInfo.name);
                    }
                }
            }
        } catch (Throwable th) {
            ho.c("DeviceTypeUtil", "get device type error:" + th.getClass().getSimpleName());
        }
        if (!hashSet.contains("com.huawei.software.features.handset") && !hashSet.contains("com.hihonor.software.features.handset")) {
            if (!hashSet.contains("com.huawei.software.features.pad") && !hashSet.contains("com.hihonor.software.features.pad")) {
                if (!hashSet.contains("com.huawei.software.features.mobiletv") && !hashSet.contains("com.hihonor.software.features.mobiletv")) {
                    if (!hashSet.contains("com.huawei.software.features.tv") && !hashSet.contains("com.hihonor.software.features.tv")) {
                        if (!hashSet.contains("com.huawei.software.features.kidwatch") && !hashSet.contains("com.hihonor.software.features.kidwatch")) {
                            if (!hashSet.contains("com.huawei.software.features.watch") && !hashSet.contains("com.hihonor.software.features.watch")) {
                                String a2 = dd.a("ro.build.characteristics");
                                ho.b("DeviceTypeUtil", "characteristics:" + a2);
                                a(a2);
                                ho.b("DeviceTypeUtil", "type is:" + this.d);
                            }
                            str = "2";
                            this.d = str;
                            ho.b("DeviceTypeUtil", "type is:" + this.d);
                        }
                        str = "3";
                        this.d = str;
                        ho.b("DeviceTypeUtil", "type is:" + this.d);
                    }
                    str = "4";
                    this.d = str;
                    ho.b("DeviceTypeUtil", "type is:" + this.d);
                }
                str = "5";
                this.d = str;
                ho.b("DeviceTypeUtil", "type is:" + this.d);
            }
            str = "1";
            this.d = str;
            ho.b("DeviceTypeUtil", "type is:" + this.d);
        }
        str = "0";
        this.d = str;
        ho.b("DeviceTypeUtil", "type is:" + this.d);
    }

    private static w b(Context context) {
        w wVar;
        synchronized (b) {
            if (f7721a == null) {
                f7721a = new w(context);
            }
            wVar = f7721a;
        }
        return wVar;
    }

    private void a(String str) {
        String str2;
        if (str.equals("default")) {
            str2 = "0";
        } else if (str.equals("tablet")) {
            str2 = "1";
        } else if (!str.equals("tv")) {
            return;
        } else {
            str2 = "4";
        }
        this.d = str2;
    }

    public static w a(Context context) {
        return b(context);
    }

    private w(Context context) {
        this.c = context.getApplicationContext();
        d();
    }
}
