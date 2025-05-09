package defpackage;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public final class ncy {
    private static final ThreadLocal<WeakReference<LayoutInflater>> b;
    private static final String c = "LayoutInflaterUtils";
    private static boolean d;
    private static final AtomicInteger e;

    static {
        d = Build.VERSION.SDK_INT >= 29;
        b = new ThreadLocal<>();
        e = new AtomicInteger();
    }

    public static LayoutInflater ctf_(Context context) {
        LayoutInflater layoutInflater;
        ThreadLocal<WeakReference<LayoutInflater>> threadLocal = b;
        WeakReference<LayoutInflater> weakReference = threadLocal.get();
        if (weakReference != null) {
            layoutInflater = weakReference.get();
            if (ncs.d.isShowLog()) {
                ncs.d.debug(c, "getLayoutInflater(), get cachedInflater:" + layoutInflater + ";threadId:" + Thread.currentThread().getId() + ";time:" + System.currentTimeMillis());
            }
        } else {
            layoutInflater = null;
        }
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context).cloneInContext(context);
            threadLocal.set(new WeakReference<>(layoutInflater));
            if (ncs.d.isShowLog()) {
                int incrementAndGet = e.incrementAndGet();
                ncs.d.debug(c, "getLayoutInflater(), set cachedInflater:" + layoutInflater + ";threadId:" + Thread.currentThread().getId() + ";time:" + System.currentTimeMillis() + ";create count:" + incrementAndGet);
            }
        }
        return layoutInflater;
    }

    public static View cte_(LayoutInflater layoutInflater, Context context, String str, String str2, AttributeSet attributeSet) throws ClassNotFoundException {
        View createView;
        if (ncs.d.isShowLog()) {
            ncs.d.debug(c, "createView(), start, inflater:" + layoutInflater + ";viewContext:" + context + ";name:" + str + ";prefix:" + str2 + ";attrs:" + attributeSet + ";threadId:" + Thread.currentThread().getId() + ";time:" + System.currentTimeMillis());
        }
        if (d) {
            createView = layoutInflater.createView(context, str, str2, attributeSet);
        } else {
            createView = layoutInflater.createView(str, str2, attributeSet);
        }
        if (ncs.d.isShowLog()) {
            ncs.d.debug(c, "createView(), end, inflater:" + layoutInflater + ";viewContext:" + context + ";name:" + str + ";prefix:" + str2 + ";attrs:" + attributeSet + ";threadId:" + Thread.currentThread().getId() + ";time:" + System.currentTimeMillis() + ";view:" + createView);
        }
        return createView;
    }
}
