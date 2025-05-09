package net.openid.appauth;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.huawei.haf.language.LanguageInstallHelper;

/* loaded from: classes10.dex */
public class RedirectUriReceiverActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            if (intent != null) {
                startActivity(AuthorizationManagementActivity.ffY_(this, intent.getData()));
            }
            finish();
        } catch (RuntimeException e) {
            Log.e("RedirectUriReceiverActivity", "onCreate->RuntimeException: " + e.toString());
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
