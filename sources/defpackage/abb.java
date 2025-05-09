package defpackage;

import android.app.Activity;
import com.huawei.android.hicloud.sync.update.UpdateCallbackInterface;
import com.huawei.android.hicloud.sync.update.UpdateManager;

/* loaded from: classes8.dex */
public class abb {

    static final class c implements UpdateManager.UpdateCheckCallback {
        final /* synthetic */ UpdateCallbackInterface c;

        c(UpdateCallbackInterface updateCallbackInterface) {
            this.c = updateCallbackInterface;
        }

        @Override // com.huawei.android.hicloud.sync.update.UpdateManager.UpdateCheckCallback
        public void onCheckHiCloudResult(int i) {
            if (this.c != null) {
                abd.c("UpdateApi", "Call App: checkHicloudNewVersion resultCode = " + i);
                this.c.onCheckHiCloudResult(i);
            }
        }
    }

    public static void fk_(Activity activity, UpdateCallbackInterface updateCallbackInterface) {
        abd.c("UpdateApi", "checkHicloudNewVersion");
        if (activity == null) {
            abd.d("UpdateApi", "checkHicloudNewVersion error: activity is null");
        } else {
            UpdateManager.a().b(activity, new c(updateCallbackInterface));
            UpdateManager.a().c();
        }
    }
}
