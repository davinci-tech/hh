package com.huawei.hms.hihealth.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hms.health.aaba;
import com.huawei.hms.health.aabf;
import com.huawei.hms.health.aabg;
import com.huawei.hms.health.aabz;

/* loaded from: classes4.dex */
public class HealthKitMainActivity extends FragmentActivity {
    public static final String HEALTH_FRAGMENT_CODE = "FragmentName";
    public static final int HEALTH_TRANSPARENT_CODE = 1;
    public static final int PRIVACY_TRANS_PARENT_ACTIVITY_CODE = 2;
    private static final String TAG = "HealthKitMainActivity";

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        aabz.aabb(TAG, "HealthKitMainActivity onCreate");
        super.onCreate(bundle);
        try {
            setContentView(R.layout.activity_health_kit_main);
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().clearFlags(AppRouterExtras.COLDSTART);
            getWindow().setStatusBarColor(getResources().getColor(R.color._2131297881_res_0x7f090659));
            int intExtra = getIntent().getIntExtra(HEALTH_FRAGMENT_CODE, 0);
            if (intExtra == 1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.healthkit_main, new aabf()).addToBackStack(null).commit();
            } else if (intExtra == 2) {
                replaceWebviewFragment(new aabg(), getIntent());
            } else {
                replaceFragment(new aaba());
            }
        } catch (Throwable unused) {
            aabz.aab(TAG, "onCreate has exception");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        aabz.aabb(TAG, "onActivityResult requestCode:" + i);
    }

    @Override // android.app.Activity
    public void finish() {
        String str;
        try {
            aabz.aabb(TAG, "to finish HealthKitMainActivity");
            super.finish();
            overridePendingTransition(0, 0);
        } catch (IllegalArgumentException unused) {
            str = "finish has IllegalArgumentException";
            aabz.aab(TAG, str);
        } catch (Exception unused2) {
            str = "finish has exception";
            aabz.aab(TAG, str);
        }
    }

    private void replaceWebviewFragment(Fragment fragment, Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putString("authUrl", intent.getStringExtra("authUrl"));
        bundle.putInt("flag", intent.getIntExtra("flag", 0));
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        beginTransaction.replace(R.id.healthkit_main, fragment);
        beginTransaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.healthkit_main, fragment);
        beginTransaction.commit();
    }
}
