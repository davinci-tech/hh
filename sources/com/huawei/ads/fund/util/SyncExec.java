package com.huawei.ads.fund.util;

import com.huawei.ads.fund.util.AsyncExec;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes2.dex */
public abstract class SyncExec {
    public static <RESULT> Future<RESULT> callWithFuture(Callable<RESULT> callable) {
        if (callable == null) {
            return null;
        }
        return AsyncExec.a(callable, AsyncExec.a.SYNC_CALL);
    }
}
