package com.huawei.hwidauth.api;

import android.content.Intent;
import com.huawei.hmf.tasks.Task;

/* loaded from: classes9.dex */
public interface HuaweiIdAuthService {
    Intent getSignInIntent();

    Task<Void> signOut();
}
