package com.huawei.health.manager.util;

import android.util.Log;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;

/* loaded from: classes.dex */
public class HwCfgFilePolicy {

    /* renamed from: a, reason: collision with root package name */
    private static String[] f2807a;
    private static String[] c;

    static {
        String str = System.getenv("CUST_POLICY_DIRS");
        LogUtil.a("Step_CfgFilePolicy", "policy=", str);
        if (str == null || str.length() == 0) {
            Log.i("Step_CfgFilePolicy", "ERROR: env CUST_POLICY_DIRS not set, use default");
            str = "/system/emui:/system/global:/system/etc:/oem:/data/cust:/cust_spec";
        }
        String[] split = str.split(":");
        c = split;
        f2807a = (String[]) split.clone();
        int i = 0;
        while (true) {
            String[] strArr = f2807a;
            if (i >= strArr.length) {
                return;
            }
            if (strArr[i].endsWith("/etc") && !f2807a[i].equals("/etc")) {
                String[] strArr2 = f2807a;
                strArr2[i] = strArr2[i].replace("/etc", "");
            }
            i++;
        }
    }

    private HwCfgFilePolicy() {
    }

    public static File b(String str, int i) throws NoClassDefFoundError {
        LogUtil.a("Step_CfgFilePolicy", "getCfgFile enter...");
        String[] strArr = i == 1 ? f2807a : c;
        for (int length = strArr.length - 1; length >= 0; length--) {
            File file = new File(strArr[length], str);
            if (file.exists()) {
                return file;
            }
        }
        LogUtil.c("Step_CfgFilePolicy", "mCfgDirs length : ", Integer.valueOf(c.length));
        return null;
    }
}
