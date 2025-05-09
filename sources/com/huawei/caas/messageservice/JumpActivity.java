package com.huawei.caas.messageservice;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import com.huawei.haf.language.LanguageInstallHelper;

/* loaded from: classes8.dex */
public class JumpActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Bundle bundleExtra;
        String str;
        super.onCreate(bundle);
        if (getIntent() == null || (bundleExtra = getIntent().getBundleExtra("bundle")) == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setPackage("com.huawei.meetime");
        intent.setAction("com.huawei.share.CaasShareService");
        int i = bundleExtra.getInt("ShareType");
        switch (i) {
            case 15:
                str = "share/product";
                intent.setType(str);
                break;
            case 16:
                str = "share/quickvideo";
                intent.setType(str);
                break;
            case 17:
                str = "share/web";
                intent.setType(str);
                break;
            case 18:
                str = "share/music";
                intent.setType(str);
                break;
            case 19:
                str = "share/longvideo";
                intent.setType(str);
                break;
            default:
                Log.e("JumpActivity", "Error type" + i);
                break;
        }
        intent.putExtras(bundleExtra);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Log.e("HwCaasShareManager", "Activity Not Found");
        }
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
