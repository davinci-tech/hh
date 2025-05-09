package com.huawei.updatesdk.b.d;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.appgallery.marketinstallerservice.api.Constant;
import com.huawei.updatesdk.support.pm.PackageInstallerActivity;
import java.io.File;

/* loaded from: classes7.dex */
public class e {
    public static void a(Context context, String str, String str2, String str3) {
        a(3, 1);
        File file = new File(str);
        if (!file.exists() || !file.isFile() || file.length() <= 0) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("InstallProcess", "system install failed,file not existed filePath:" + str);
            a(4, -10003);
            return;
        }
        Intent intent = new Intent(context, (Class<?>) PackageInstallerActivity.class);
        intent.putExtra("install_path", str);
        intent.putExtra("install_packagename", str2);
        intent.putExtra("apk_sha256", str3);
        if (!(context instanceof Activity)) {
            intent.setFlags(402653184);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            a(4, Constant.INSTALL_FAILED_SHA256_EEROR);
            com.huawei.updatesdk.a.a.c.a.a.a.a("InstallProcess", " can not start install !", e);
        }
    }

    public static void a(int i, int i2) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("INSTALL_STATE", i);
        bundle.putInt("INSTALL_TYPE", i2);
        intent.putExtras(bundle);
        com.huawei.updatesdk.b.f.c.b().c(com.huawei.updatesdk.a.b.b.b.a(intent));
    }
}
