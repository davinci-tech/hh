package com.huawei.ui.homewear21.home.legal;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.agreement.DeclarationAdapter;
import defpackage.jfq;
import defpackage.jfu;
import defpackage.jpt;
import defpackage.nsy;
import defpackage.nyu;
import defpackage.pen;
import health.compact.a.Services;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class WearHomeOpenBaseServiceActivity extends BaseActivity {
    private int c = -1;
    private List<nyu> d;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.open_service_statement_activity);
        c();
        d();
    }

    private void d() {
        ((CustomTitleBar) nsy.cMc_(this, R.id.wear_home_open_service_statement_title)).setTitleText(BaseApplication.getContext().getString(R.string._2130843850_res_0x7f0218ca));
        ListView listView = (ListView) nsy.cMc_(this, R.id.wear_home_open_service_statement_list_view);
        if (this.d == null) {
            LogUtil.a("WearHomeOpenBaseServiceActivity", "mConvertedLists is null");
        } else {
            listView.setAdapter((ListAdapter) new DeclarationAdapter(this, this.d, this.c));
        }
    }

    private void c() {
        int i;
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getIntExtra("device_version_type", -1);
        }
        DeviceInfo a2 = jpt.a("WearHomeOpenBaseServiceActivity");
        if (a2 == null) {
            LogUtil.h("WearHomeOpenBaseServiceActivity", "currentDeviceInfo is null");
            return;
        }
        String emuiVersion = a2.getEmuiVersion();
        if (!TextUtils.isEmpty(emuiVersion)) {
            Map<String, DeviceCapability> a3 = jfq.c().a(3, "", "WearHomeOpenBaseServiceActivity");
            if (a3 == null) {
                LogUtil.h("WearHomeOpenBaseServiceActivity", "filter deviceCapabilityMap is null");
                return;
            }
            DeviceCapability deviceCapability = a3.get(a2.getDeviceIdentify());
            if (deviceCapability != null) {
                i = deviceCapability.getSmartWatchVersionTypeValue();
                LogUtil.a("WearHomeOpenBaseServiceActivity", "smartWatchVersion:", Integer.valueOf(i));
            } else {
                i = 0;
            }
            this.d = pen.a(emuiVersion, i, ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginIndexOobe(jfu.d(a2.getProductType())));
            return;
        }
        LogUtil.h("WearHomeOpenBaseServiceActivity", "emuiVersion is null");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
