package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.huawei.appgallery.marketinstallerservice.a.c;
import com.huawei.appgallery.marketinstallerservice.a.e.a;
import com.huawei.appgallery.marketinstallerservice.api.BaseParamSpec;
import com.huawei.appgallery.marketinstallerservice.api.FailResultParam;
import com.huawei.appgallery.marketinstallerservice.api.InstallCallback;
import com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec;
import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.RequestBean;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.ResponseBean;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.b;
import com.huawei.appgallery.marketinstallerservice.ui.MarketDownloadActivity;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class agi implements c {

    /* loaded from: classes8.dex */
    static class d implements b {
        private InstallCallback c;

        @Override // com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.b
        public void a(RequestBean requestBean, ResponseBean responseBean) {
        }

        @Override // com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.b
        public void b(RequestBean requestBean, ResponseBean responseBean) {
            if (this.c == null) {
                agr.e("MarketInstallApiImpl", "callback is null!");
                return;
            }
            if (!(responseBean instanceof agk)) {
                agr.e("MarketInstallApiImpl", "get Market info error!");
                return;
            }
            if (responseBean.getResponseCode() == 0 && responseBean.getRtnCode() == 0) {
                this.c.onSuccess(((agk) responseBean).getHiAppInfo());
                return;
            }
            agr.e("MarketInstallApiImpl", "get Market info error: responseCode:" + responseBean.getResponseCode() + ", rtnCode:" + responseBean.getRtnCode());
            FailResultParam failResultParam = new FailResultParam();
            failResultParam.setResult(-4);
            failResultParam.setResponseCode(responseBean.getResponseCode());
            failResultParam.setRtnCode(responseBean.getRtnCode());
            this.c.onFailed(failResultParam);
        }

        d(InstallCallback installCallback) {
            this.c = installCallback;
        }
    }

    @Override // com.huawei.appgallery.marketinstallerservice.a.c
    public void a(Context context, BaseParamSpec baseParamSpec, InstallCallback installCallback) {
        a newInstance = a.newInstance(context);
        newInstance.setServiceUrl(baseParamSpec.getServerUrl());
        newInstance.setSubsystem(baseParamSpec.getSubsystem());
        newInstance.setMarketPkg(baseParamSpec.getMarketPkg());
        agp.a(newInstance, new d(installCallback));
    }

    @Override // com.huawei.appgallery.marketinstallerservice.a.c
    public void a(Activity activity, InstallParamSpec installParamSpec, InstallCallback installCallback) {
        if (installParamSpec.isSilentDownload()) {
            agr.c("MarketInstallApiImpl", "start silent download market!");
            new agy(activity, installParamSpec, installCallback).c();
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) MarketDownloadActivity.class);
        intent.putExtra("callback_key", agh.a(installCallback));
        MarketInfo marketInfo = installParamSpec.getMarketInfo();
        if (marketInfo != null) {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            arrayList.add(marketInfo);
            intent.putParcelableArrayListExtra("market_info_key", arrayList);
        } else {
            String serverUrl = installParamSpec.getServerUrl();
            String subsystem = installParamSpec.getSubsystem();
            String marketPkg = installParamSpec.getMarketPkg();
            boolean isUpdate = installParamSpec.isUpdate();
            intent.putExtra("service_url_key", serverUrl);
            intent.putExtra("sub_system_key", subsystem);
            intent.putExtra("market_pkg_key", marketPkg);
            intent.putExtra("is_update_key", isUpdate);
        }
        intent.putExtra("fail_result_type_key", installParamSpec.getFailResultPromptType());
        activity.startActivity(intent);
    }
}
