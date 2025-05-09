package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.m;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes5.dex */
public abstract class dc {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7691a = "dc";

    public static <RESULT> RESULT c(Callable<RESULT> callable) {
        return (RESULT) a(new r(callable), null, 1L, TimeUnit.SECONDS);
    }

    public static <RESULT> RESULT b(Callable<RESULT> callable) {
        return (RESULT) a(callable, null, 1L, TimeUnit.SECONDS);
    }

    public static <RESULT> Future<RESULT> a(Callable<RESULT> callable) {
        if (callable == null) {
            return null;
        }
        return m.a(callable, m.a.SYNC_CALL);
    }

    public static <RESULT> RESULT a(Callable<RESULT> callable, RESULT result, long j, TimeUnit timeUnit) {
        String str;
        String str2;
        if (callable == null) {
            return result;
        }
        try {
            return (RESULT) m.a(callable, m.a.SYNC_CALL).get(j, timeUnit);
        } catch (InterruptedException unused) {
            str = f7691a;
            str2 = "call InterruptedException";
            ho.c(str, str2);
            return result;
        } catch (ExecutionException unused2) {
            str = f7691a;
            str2 = "call ExecutionException";
            ho.c(str, str2);
            return result;
        } catch (TimeoutException unused3) {
            str = f7691a;
            str2 = "call TimeoutException";
            ho.c(str, str2);
            return result;
        }
    }

    public static <RESULT> RESULT a(Callable<RESULT> callable, RESULT result) {
        return (RESULT) a(callable, result, 1L, TimeUnit.SECONDS);
    }

    public static <RESULT> RESULT a(Callable<RESULT> callable, long j, RESULT result) {
        return (RESULT) a(callable, result, j, TimeUnit.MILLISECONDS);
    }
}
