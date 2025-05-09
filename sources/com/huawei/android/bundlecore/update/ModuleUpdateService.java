package com.huawei.android.bundlecore.update;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.common.utils.CollectionUtils;
import defpackage.yc;
import defpackage.yg;
import health.compact.a.LogUtil;
import java.io.File;

/* loaded from: classes8.dex */
public class ModuleUpdateService extends IntentService {
    public ModuleUpdateService() {
        super("bundle_module_update");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            LogUtil.a("Bundle_UpdateService", "intent == null || intent.getAction() is empty");
            return;
        }
        String action = intent.getAction();
        action.hashCode();
        if (action.equals("ModuleUpdateService")) {
            eI_(intent);
        }
    }

    private void eI_(Intent intent) {
        yg a2 = yg.a();
        if (a2.a(this).isEmpty()) {
            LogUtil.a("Bundle_UpdateService", "Failed to get modules info of current module-info version!");
            return;
        }
        String stringExtra = intent.getStringExtra("new_module_info_version");
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.a("Bundle_UpdateService", "New module-info version null");
            return;
        }
        String stringExtra2 = intent.getStringExtra("new_module_info_path");
        if (TextUtils.isEmpty(stringExtra2)) {
            LogUtil.a("Bundle_UpdateService", "New module-info path null");
        } else {
            e(a2, stringExtra, stringExtra2);
        }
    }

    private void e(yg ygVar, String str, String str2) {
        File file = new File(str2);
        if (!file.exists() || !file.canWrite()) {
            LogUtil.a("Bundle_UpdateService", "New module-info file ", str2, " is invalid");
            return;
        }
        if (str.equals(ygVar.e())) {
            LogUtil.a("Bundle_UpdateService", "New module-info version ", str, " is equals to current mVersion!");
            return;
        }
        yc d = ygVar.d(str2);
        if (d == null || !d.c()) {
            LogUtil.a("Bundle_UpdateService", "Failed to parse ModuleDetails for new module info file!");
            return;
        }
        String d2 = d.d();
        if (TextUtils.isEmpty(d2) || !d2.equals(AppBundleBuildConfig.a())) {
            LogUtil.a("Bundle_UpdateService", "New bundle-id is not equal to current app, so we could't update modules!");
            return;
        }
        if (CollectionUtils.d(d.b())) {
            LogUtil.a("Bundle_UpdateService", "There are no modules need to be updated!");
            return;
        }
        LogUtil.c("Bundle_UpdateService", "Success to check update request, updatedModuleInfoPath:", str2, ", updatedModuleInfoVersion:", str);
        if (ygVar.c(str, file)) {
            LogUtil.a("Bundle_UpdateService", "updateModuleInfoVersion update ok");
        } else {
            LogUtil.a("Bundle_UpdateService", "updateModuleInfoVersion update error");
        }
    }
}
