package com.huawei.ui.homewear21.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cwi;
import defpackage.drl;
import defpackage.mxv;
import defpackage.ozh;
import defpackage.ozj;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class WearHomeAccessibilityActivity extends WearHomeBaseActivity {
    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("WearHomeAccessibilityActivity", "onCreate");
        this.mCustomTitleBar.setTitleText(BaseApplication.getContext().getString(R.string._2130845723_res_0x7f02201b));
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity
    protected void initGeneralList() {
        this.mGeneralList.clear();
        c();
    }

    private void c() {
        boolean isSupportEcgAuth = this.mDeviceCapability != null ? this.mDeviceCapability.isSupportEcgAuth() : false;
        boolean z = cwi.c(this.mCurrentDeviceInfo, 106) && drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce");
        boolean o = Utils.o();
        LogUtil.a("WearHomeAccessibilityActivity", "showEcgItem isSupportEcgAuth:", Boolean.valueOf(isSupportEcgAuth), ", isSupportEcgAnalysis:", Boolean.valueOf(z), ", isOversea:", Boolean.valueOf(o));
        if (z && isSupportEcgAuth && !o) {
            addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131821109_res_0x7f110235, 119, 3), new ozh(getString(R.string.IDS_quick_app_ecg), "", ""), new CompoundButton.OnCheckedChangeListener[0]);
        }
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity
    protected void setItemClick(int i) {
        LogUtil.a("WearHomeAccessibilityActivity", "setItemClick index = ", Integer.valueOf(i), ";size = ", Integer.valueOf(this.mGeneralList.size()));
        if (i >= this.mGeneralList.size()) {
            return;
        }
        if (this.mGeneralList.get(i).e() == 119) {
            mxv.b(BaseApplication.getContext(), "com.huawei.health.ecg.collection", 3, null);
        } else {
            LogUtil.h("WearHomeAccessibilityActivity", "setItemClick else branch");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
