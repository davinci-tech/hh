package com.huawei.hms.support.hwid.service;

import android.content.Intent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.feature.service.AuthService;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;

/* loaded from: classes4.dex */
public interface HuaweiIdAuthService extends AuthService {
    Intent getSignInIntent(String str);

    Task<AuthHuaweiId> silentSignIn();

    Task<AuthHuaweiId> silentSignIn(String str);
}
