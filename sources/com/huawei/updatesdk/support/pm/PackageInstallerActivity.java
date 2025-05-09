package com.huawei.updatesdk.support.pm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.updatesdk.a.a.c.a.a.a;
import com.huawei.updatesdk.a.a.d.d;
import com.huawei.updatesdk.a.a.d.i.c;
import com.huawei.updatesdk.a.b.b.b;
import com.huawei.updatesdk.b.d.e;
import com.huawei.updatesdk.fileprovider.UpdateSdkFileProvider;
import java.io.File;

/* loaded from: classes7.dex */
public class PackageInstallerActivity extends Activity {
    @Override // android.app.Activity
    protected void onDestroy() {
        finishActivity(1000);
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        String str;
        requestWindowFeature(1);
        c.f().a(getWindow());
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            str = "PackageInstallerActivity error intent";
        } else {
            b a2 = b.a(intent);
            String a3 = a2.a("install_path");
            String a4 = a2.a("install_packagename");
            if (!a(a3, a2.a("apk_sha256"))) {
                try {
                    Intent a5 = a(this, a3);
                    a5.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
                    a5.putExtra("android.intent.extra.RETURN_RESULT", true);
                    a.c("PackageInstallerActivity", " onCreate filePath:" + a3 + ",packageName:" + a4 + ",taskId:" + getTaskId());
                    startActivityForResult(a5, 1000);
                    return;
                } catch (Exception unused) {
                    a.b("PackageInstallerActivity", "can not start install action");
                    e.a(4, -2);
                    finish();
                    return;
                }
            }
            e.a(4, -3);
            finish();
            str = "PackageInstallerActivity can not find filePath.";
        }
        a.b("PackageInstallerActivity", str);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (1000 == i) {
            if (i2 == 0) {
                e.a(7, 0);
            } else {
                int a2 = intent != null ? b.a(intent).a("android.intent.extra.INSTALL_RESULT", -10004) : -10004;
                if (a2 != 0 && a2 != 1) {
                    e.a(4, a2);
                }
            }
        }
        finish();
    }

    private boolean a(String str, String str2) {
        return d.d(str) || TextUtils.isEmpty(str2) || !str2.equalsIgnoreCase(d.a(str, "SHA-256"));
    }

    private Intent a(Context context, String str) {
        if (d.d(str)) {
            throw new IllegalArgumentException("getNormalInstallIntent: Not a standard path");
        }
        File file = new File(str);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.INSTALL_PACKAGE");
        intent.addFlags(1);
        String str2 = context.getApplicationContext().getPackageName() + UpdateSdkFileProvider.AUTHORITIES_SUFFIX;
        if (!d.d(str2)) {
            intent.setData(UpdateSdkFileProvider.getUriForFile(context, str2, file));
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        return intent;
    }
}
