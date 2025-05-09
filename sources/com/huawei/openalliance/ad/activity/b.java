package com.huawei.openalliance.ad.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public abstract class b extends e {
    protected abstract void b();

    public class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            StringBuilder sb;
            try {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action) || !action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                    return;
                }
                String stringExtra = intent.getStringExtra("reason");
                ho.a("BaseHomeActivity", "systemReason:%s", stringExtra);
                if (TextUtils.isEmpty(stringExtra)) {
                    return;
                }
                if (stringExtra.equalsIgnoreCase("homekey")) {
                    ho.a("BaseHomeActivity", "closedialog SYSTEM_HOME_KEY");
                } else if (!stringExtra.equalsIgnoreCase("recentapps")) {
                    return;
                } else {
                    ho.b("BaseHomeActivity", "closedialog SYSTEM_RECENT_APPS");
                }
                b.this.b();
            } catch (RuntimeException e) {
                e = e;
                sb = new StringBuilder("onReceive:");
                sb.append(e.getClass().getSimpleName());
                ho.c("BaseHomeActivity", sb.toString());
            } catch (Throwable th) {
                e = th;
                sb = new StringBuilder("onReceive ex: ");
                sb.append(e.getClass().getSimpleName());
                ho.c("BaseHomeActivity", sb.toString());
            }
        }

        public a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
