package com.huawei.operation.h5pro.versionmanage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.h5pro.ext.version.H5ProVersionUpgradeExtApi;
import com.huawei.health.h5pro.vengine.H5ProVersionMeta;
import health.compact.a.CommonUtil;

/* loaded from: classes.dex */
public class H5ProVersionUpgradeExitImpl implements H5ProVersionUpgradeExtApi {
    @Override // com.huawei.health.h5pro.ext.version.H5ProVersionUpgradeExtApi
    public boolean isSupportUpgrade(H5ProVersionMeta h5ProVersionMeta) {
        return h5ProVersionMeta == null || TextUtils.isEmpty(h5ProVersionMeta.getMinAppVersion()) || CommonUtil.d(CommonUtil.e(BaseApplication.e()), h5ProVersionMeta.getMinAppVersion()) >= 0;
    }
}
