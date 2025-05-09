package com.huawei.featureuserprofile.impl;

import com.huawei.featureuserprofile.target.cloud.ActiveTargetManager;
import com.huawei.hmf.services.ModuleProvider;

/* loaded from: classes3.dex */
public class UserProfileMgrModule extends ModuleProvider {
    @Override // com.huawei.hmf.services.ModuleProvider
    public void initialize() {
        super.initialize();
        ActiveTargetManager.e().a();
    }
}
