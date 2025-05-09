package com.huawei.haf.common.log;

import java.util.ArrayDeque;

/* loaded from: classes.dex */
public final class LogBuilder {

    /* renamed from: a, reason: collision with root package name */
    private static final ArrayDeque<StringBuilder> f2094a = new ArrayDeque<>(3);

    private LogBuilder() {
    }

    public static void d() {
        ArrayDeque<StringBuilder> arrayDeque = f2094a;
        synchronized (arrayDeque) {
            arrayDeque.clear();
        }
    }

    public static String e(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        if (objArr.length == 1) {
            return String.valueOf(objArr[0]);
        }
        StringBuilder e = e();
        for (Object obj : objArr) {
            e.append(obj);
        }
        String sb = e.toString();
        c(e);
        return sb;
    }

    private static StringBuilder e() {
        StringBuilder pollLast;
        ArrayDeque<StringBuilder> arrayDeque = f2094a;
        synchronized (arrayDeque) {
            pollLast = arrayDeque.pollLast();
        }
        if (pollLast == null) {
            return new StringBuilder(256);
        }
        pollLast.delete(0, pollLast.length());
        return pollLast;
    }

    private static void c(StringBuilder sb) {
        if (sb.capacity() > 256) {
            return;
        }
        ArrayDeque<StringBuilder> arrayDeque = f2094a;
        synchronized (arrayDeque) {
            if (arrayDeque.size() < 3) {
                arrayDeque.addLast(sb);
            }
        }
    }
}
