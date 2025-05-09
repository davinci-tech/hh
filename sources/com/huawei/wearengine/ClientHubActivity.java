package com.huawei.wearengine;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import defpackage.tov;
import defpackage.trr;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ClientHubActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        tov.a("ClientHubActivity", "ClientHubActivity onCreate");
        super.onCreate(bundle);
        trr.e(this);
        Intent intent = getIntent();
        if (!fca_(intent)) {
            e(5, "invalid intent");
            return;
        }
        String stringExtra = intent.getStringExtra("target_json");
        String stringExtra2 = intent.getStringExtra("start_request_json");
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
            e(5, "ClientHubActivity start authNameJson is empty or requestDataJsonString is empty");
        } else {
            b(stringExtra, stringExtra2);
        }
    }

    private void e(int i, String str) {
        tov.c("ClientHubActivity", "errorReturn:" + str + ", resultCode:" + i);
        setResult(i);
        finish();
    }

    private boolean fca_(Intent intent) {
        if (intent != null) {
            return true;
        }
        tov.c("ClientHubActivity", "checkIntent intent is null");
        return false;
    }

    private void b(String str, String str2) {
        tov.a("ClientHubActivity", "startJumpActivity");
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("target_package_name");
            String string2 = jSONObject.getString("target_activity_name");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                e(12, "targetPackageName or targetActivityName is empty");
                return;
            }
            Intent intent = new Intent();
            intent.setPackage(string);
            intent.setClassName(string, string2);
            intent.putExtra("start_request_json", str2);
            Intent ffe_ = trr.ffe_(intent);
            if (ffe_ == null) {
                e(12, "createExplicitActivityIntent, intent is null.");
                return;
            }
            try {
                startActivityForResult(ffe_, 19900);
            } catch (ActivityNotFoundException unused) {
                e(12, "startJumpActivity not find JumpActivity");
            }
        } catch (JSONException unused2) {
            e(12, "startJumpActivity JSONException");
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        tov.a("ClientHubActivity", "onActivityResult requestCode:" + i + ", resultCode:" + i2);
        if (i == 19900) {
            tov.a("ClientHubActivity", "onActivityResult finish");
            setResult(i2);
            finish();
        }
    }
}
