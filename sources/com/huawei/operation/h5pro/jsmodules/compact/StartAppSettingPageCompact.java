package com.huawei.operation.h5pro.jsmodules.compact;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.StartAppSettingPage;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class StartAppSettingPageCompact implements StartAppSettingPage {
    private static final String TAG = "StartAppSettingPageCompact";

    @Override // com.huawei.operation.adapter.StartAppSettingPage
    public void onStartAppSettingPage() {
        try {
            Intent intent = new Intent();
            intent.setPackage(Constants.APP_PACKAGE);
            intent.setClassName(Constants.APP_PACKAGE, Constants.APP_SETTING_ACTIVITY);
            intent.addFlags(268435456);
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.h(TAG, "onStartAppSettingPage err: activity not found.");
        }
    }
}
