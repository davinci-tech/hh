package com.huawei.hms.iapfull.webpay.thirdpay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hms.iapfull.a1;
import com.huawei.hms.iapfull.n0;
import com.huawei.hms.iapfull.pay.m;
import com.huawei.hms.iapfull.y0;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class ThirdPayActivity extends n0 {
    private AlertDialog b;
    private int c;

    @Override // com.huawei.hms.iapfull.n0, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        y0.b("ThirdPayActivity", "onCreate");
        SafeIntent safeIntent = new SafeIntent(getIntent());
        String stringExtra = safeIntent.getStringExtra("alipay_sign_url");
        if (!TextUtils.isEmpty(stringExtra) && m.a(this, stringExtra)) {
            y0.b("ThirdPayActivity", "call alipay sign success");
            this.c = 5;
            b();
            return;
        }
        String stringExtra2 = safeIntent.getStringExtra("wechat_sign_appId");
        String stringExtra3 = safeIntent.getStringExtra("wechat_sign_preEntrustwebId");
        if (TextUtils.isEmpty(stringExtra2) || TextUtils.isEmpty(stringExtra3) || !m.a(this, stringExtra2, stringExtra3)) {
            y0.a("ThirdPayActivity", "onCreate illegal params");
            finish();
        } else {
            y0.b("ThirdPayActivity", "call wechat sign success");
            this.c = 17;
            b();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        String str;
        super.onNewIntent(intent);
        y0.b("ThirdPayActivity", "onNewIntent");
        SafeIntent safeIntent = new SafeIntent(intent);
        if (safeIntent.getBooleanExtra("wechat_sign_result", false)) {
            a(-1);
            return;
        }
        if ("iapfull".equals(safeIntent.getScheme())) {
            Uri data = safeIntent.getData();
            if (data != null) {
                if ("/alipaysignresult".equals(data.getPath())) {
                    y0.b("ThirdPayActivity", "get alipay sign result");
                    String encodedQuery = data.getEncodedQuery();
                    if (!TextUtils.isEmpty(encodedQuery)) {
                        String a2 = a1.a(encodedQuery);
                        if (TextUtils.isEmpty(a2)) {
                            y0.a("ThirdPayHelper", "parseAlipayResult result is null");
                        } else {
                            String str2 = (String) ((HashMap) a1.a(a2, "&", false)).get("is_success");
                            y0.b("ThirdPayHelper", "parseAlipayResult flag " + str2);
                            if (ExifInterface.GPS_DIRECTION_TRUE.equals(str2)) {
                                y0.b("ThirdPayActivity", "alipay sign success");
                                a(-1);
                                return;
                            }
                        }
                    }
                }
                y0.a("ThirdPayActivity", "onNewIntentDispatch illegal.");
                a(0);
                return;
            }
            str = "scheme getData isEmpty.";
        } else {
            str = "scheme illegal.";
        }
        y0.a("ThirdPayActivity", str);
        a(0);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        y0.b("ThirdPayActivity", "onStart");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        y0.b("ThirdPayActivity", "onPause");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    private void b() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string._2130851258_res_0x7f0235ba);
        builder.setPositiveButton(R.string._2130851266_res_0x7f0235c2, new a());
        builder.setNegativeButton(R.string._2130851267_res_0x7f0235c3, new b());
        AlertDialog create = builder.create();
        this.b = create;
        create.show();
    }

    class a implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            y0.b("ThirdPayActivity", "user click finish");
            ThirdPayActivity.this.a(-1);
        }

        a() {
        }
    }

    class b implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            y0.b("ThirdPayActivity", "user click cancel");
            ThirdPayActivity.this.a(0);
        }

        b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        y0.b("ThirdPayActivity", "finishCallback result " + i + " pay type " + this.c);
        AlertDialog alertDialog = this.b;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.b.dismiss();
        }
        setResult(i);
        finish();
    }
}
