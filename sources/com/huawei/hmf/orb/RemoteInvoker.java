package com.huawei.hmf.orb;

import com.huawei.hmf.orb.aidl.client.impl.ResolvePendingResult;
import com.huawei.hmf.orb.aidl.request.InvokeService;
import com.huawei.hmf.orb.aidl.request.TypeKind;

/* loaded from: classes8.dex */
public interface RemoteInvoker {
    ResolvePendingResult<InvokeService.Response> post(String str, String str2, Object... objArr);

    InvokeService.Response send(String str, String str2, TypeKind typeKind, Object... objArr);
}
