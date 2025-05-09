package com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppJs;
import java.util.List;

@H5ProService(name = WalletAppJs.TAG, users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class WalletAppJs {
    private static final String TAG = "WalletManager";

    @H5ProCallback
    interface IH5ProCallback<T> {
        void onFailure(int i, String str);

        void onSuccess(T t);
    }

    @H5ProMethod(name = "getWalletApplicationList")
    public static void getWalletApplicationList(final IH5ProCallback<List<WalletApplication>> iH5ProCallback) {
        LogUtil.a(TAG, "getWalletApplicationList enter");
        WalletAppManager.getInstance().getWalletApplicationList(new ResponseCallback() { // from class: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppJs$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WalletAppJs.IH5ProCallback.this.onSuccess((List) obj);
            }
        });
    }

    @H5ProMethod(name = "jumpToHuaweiWallet")
    public static void jumpToHuaweiWallet(IH5ProCallback<Void> iH5ProCallback) {
        LogUtil.a(TAG, "jumpToHuaweiWallet enter");
        WalletAppManager.getInstance().startHuaweiWalletActivity();
    }
}
