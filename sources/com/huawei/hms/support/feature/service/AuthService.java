package com.huawei.hms.support.feature.service;

import android.content.Intent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.HuaweiApiInterface;

/* loaded from: classes.dex */
public interface AuthService extends HuaweiApiInterface {
    Task<Void> cancelAuthorization();

    Intent getSignInIntent();

    Task<Void> signOut();
}
