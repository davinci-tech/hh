package com.huawei.health.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.koq;
import defpackage.nlk;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class PermissionDialogReceiver extends BroadcastReceiver {
    private nlk b;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.huawei.health.action.ACTION_PERMISSION_REQUEST".equals(action)) {
            LogUtil.a("PermissionDialogReceiver", "PermissionDialogReceiver detailDialog show");
            String[] stringArrayExtra = intent.getStringArrayExtra("Permissions");
            if (stringArrayExtra == null || BaseApplication.wa_() == null) {
                LogUtil.h("PermissionDialogReceiver", "permission is null or activity is null");
                return;
            }
            ArrayList arrayList = new ArrayList(Arrays.asList(stringArrayExtra));
            if (PermissionUtil.g()) {
                arrayList.remove(Constants.POST_NOTIFICATIONS);
            }
            if (koq.b(arrayList)) {
                LogUtil.h("PermissionDialogReceiver", "permissionList is empty");
                return;
            }
            nlk nlkVar = new nlk(BaseApplication.wa_(), arrayList);
            this.b = nlkVar;
            nlkVar.show();
            return;
        }
        if ("com.huawei.health.action.ACTION_PERMISSION_REQUEST_FINISH".equals(action)) {
            LogUtil.a("PermissionDialogReceiver", "PermissionDialogReceiver detailDialog dismiss");
            b();
        } else {
            LogUtil.a("PermissionDialogReceiver", "PermissionDialogReceiver receive unknown localBroadCast action = ", action);
        }
    }

    private void b() {
        nlk nlkVar = this.b;
        if (nlkVar == null) {
            LogUtil.h("PermissionDialogReceiver", "dismissDialog mDialog is null");
            return;
        }
        if (nlkVar.isShowing()) {
            Context baseContext = ((ContextWrapper) this.b.getContext()).getBaseContext();
            if (baseContext instanceof Activity) {
                Activity activity = (Activity) baseContext;
                if (!activity.isFinishing() && !activity.isDestroyed()) {
                    this.b.dismiss();
                }
            }
        }
        this.b = null;
    }
}
