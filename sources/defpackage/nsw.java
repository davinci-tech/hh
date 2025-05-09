package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: classes.dex */
public class nsw {
    private static volatile ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Future<XmlResourceParser>>> b = new ConcurrentHashMap<>();

    public static void b() {
        ReleaseLogUtil.e("R_View_Load", "start release");
        if (b != null) {
            Iterator<Map.Entry<Integer, ConcurrentLinkedQueue<Future<XmlResourceParser>>>> it = b.entrySet().iterator();
            while (it.hasNext()) {
                a(it.next().getValue());
            }
            b.clear();
        }
        ReleaseLogUtil.e("R_View_Load", "end release");
    }

    public static void e(WeakReference<Context> weakReference, int i, int i2) {
        LogUtil.a("View_Load", "preInflate layout Id = ", Integer.valueOf(i));
        Context context = weakReference.get();
        if (context == null) {
            LogUtil.a("View_Load", "context is null");
        } else {
            a(context, Integer.valueOf(i), i2);
        }
    }

    public static XmlResourceParser cLT_(int i) {
        XmlResourceParser xmlResourceParser;
        LogUtil.a("View_Load", "getXmlParser id = ", Integer.valueOf(i));
        try {
            xmlResourceParser = cLU_(i);
        } catch (InterruptedException | ExecutionException e) {
            LogUtil.b("View_Load", e.getMessage());
            xmlResourceParser = null;
        }
        LogUtil.a("View_Load", "async inflate success, id: ", Integer.valueOf(i));
        if (xmlResourceParser != null) {
            return xmlResourceParser;
        }
        LogUtil.a("View_Load", "inflate in main thread, id: ", Integer.valueOf(i));
        try {
            return BaseApplication.getContext().getResources().getLayout(i);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("View_Load", "inflate failed in main thread");
            return xmlResourceParser;
        }
    }

    private static void a(Context context, final Integer num, int i) {
        try {
            LogUtil.a("View_Load", "preInflateInner");
            final Resources resources = context.getResources();
            for (int i2 = 0; i2 < i; i2++) {
                Future<XmlResourceParser> submit = ThreadPoolManager.d().submit(new Callable() { // from class: nta
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        XmlResourceParser layout;
                        layout = resources.getLayout(num.intValue());
                        return layout;
                    }
                });
                LogUtil.a("View_Load", "submitted, id: ", num);
                b.putIfAbsent(num, new ConcurrentLinkedQueue<>());
                b.get(num).add(submit);
            }
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("View_Load", "inflate failed!");
        }
    }

    private static XmlResourceParser cLU_(int i) throws ExecutionException, InterruptedException {
        Future<XmlResourceParser> c = c(Integer.valueOf(i));
        if (c == null) {
            LogUtil.b("View_Load", "task not exist, taskId: ", Integer.valueOf(i));
            return null;
        }
        if (c.isDone()) {
            return c.get();
        }
        c.cancel(true);
        return null;
    }

    private static Future<XmlResourceParser> c(Integer num) {
        ConcurrentLinkedQueue<Future<XmlResourceParser>> concurrentLinkedQueue;
        if (!b.containsKey(num) || (concurrentLinkedQueue = b.get(num)) == null || concurrentLinkedQueue.isEmpty()) {
            return null;
        }
        return concurrentLinkedQueue.poll();
    }

    private static void a(ConcurrentLinkedQueue<Future<XmlResourceParser>> concurrentLinkedQueue) {
        while (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
            Future<XmlResourceParser> poll = concurrentLinkedQueue.poll();
            if (poll == null) {
                return;
            }
            if (poll.isDone()) {
                try {
                    poll.get().close();
                } catch (InterruptedException | ExecutionException e) {
                    ReleaseLogUtil.c("R_View_Load", "get xml parser error:" + ExceptionUtils.d(e));
                }
            }
            poll.cancel(true);
        }
    }
}
