package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.kl;
import defpackage.lv;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class AlipayResultActivity extends Activity {
    public static final ConcurrentHashMap<String, a> e = new ConcurrentHashMap<>();

    public interface a {
        void a(int i, String str, String str2);
    }

    private void aK_(String str, Bundle bundle) {
        a remove = e.remove(str);
        if (remove == null) {
            return;
        }
        try {
            remove.a(bundle.getInt("endCode"), bundle.getString("memo"), bundle.getString("result"));
        } finally {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        Throwable th;
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            try {
                String stringExtra = intent.getStringExtra("session");
                Bundle bundleExtra = intent.getBundleExtra("result");
                String stringExtra2 = intent.getStringExtra("scene");
                lv b = lv.e.b(stringExtra);
                if (b == null) {
                    finish();
                    return;
                }
                kl.a(b, "biz", "BSPSession", stringExtra + "|" + SystemClock.elapsedRealtime());
                if (TextUtils.equals("mqpSchemePay", stringExtra2)) {
                    aK_(stringExtra, bundleExtra);
                    return;
                }
                if ((TextUtils.isEmpty(stringExtra) || bundleExtra == null) && intent.getData() != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(new String(Base64.decode(intent.getData().getQuery(), 2), "UTF-8"));
                        JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                        stringExtra = jSONObject.getString("session");
                        kl.a(b, "biz", "BSPUriSession", stringExtra);
                        Bundle bundle2 = new Bundle();
                        try {
                            Iterator<String> keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                bundle2.putString(next, jSONObject2.getString(next));
                            }
                            bundleExtra = bundle2;
                        } catch (Throwable th2) {
                            th = th2;
                            bundleExtra = bundle2;
                            kl.e(b, "biz", "BSPResEx", th);
                            kl.e(b, "biz", "ParseSchemeQueryError", th);
                            if (TextUtils.isEmpty(stringExtra)) {
                            }
                            kl.e(this, b, "", b.b);
                            finish();
                            return;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                if (!TextUtils.isEmpty(stringExtra) || bundleExtra == null) {
                    kl.e(this, b, "", b.b);
                    finish();
                    return;
                }
                try {
                    kl.a(b, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
                    kl.a(b, "biz", "PgReturnV", bundleExtra.getInt("endCode", -1) + "|" + bundleExtra.getString("memo", Constants.LINK));
                    OpenAuthTask.aN_(stringExtra, 9000, "OK", bundleExtra);
                    kl.e(this, b, "", b.b);
                    finish();
                } catch (Throwable th4) {
                    kl.e(this, b, "", b.b);
                    finish();
                    throw th4;
                }
            } catch (Throwable th5) {
                kl.e((lv) null, "biz", "BSPSerError", th5);
                kl.e((lv) null, "biz", "ParseBundleSerializableError", th5);
                finish();
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
