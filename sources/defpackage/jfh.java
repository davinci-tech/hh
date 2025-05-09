package defpackage;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jfh {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, List<IBaseResponseCallback>> f13783a = new HashMap(16);
    private static final List<Integer> e = new ArrayList(Arrays.asList(2, 3, 4, 6, 5, 7, 8, 9, 10, 11, 12, 13, 16, 20, 21, 23, 24, 25, 27, 26, 29, 34, 35, 36));

    public static void d() {
        synchronized (d) {
            if (f13783a.isEmpty()) {
                Iterator<Integer> it = e.iterator();
                while (it.hasNext()) {
                    f13783a.put(it.next(), new ArrayList(10));
                }
            }
        }
    }

    public static void b(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("CommandCallback", "Enter addToCommandCallbackMap method. commandId: ", Integer.valueOf(i));
        synchronized (d) {
            if (iBaseResponseCallback != null) {
                f13783a.get(Integer.valueOf(i)).add(iBaseResponseCallback);
            }
        }
    }

    public static List<IBaseResponseCallback> a(int i) {
        LogUtil.a("CommandCallback", "Enter getCommandCallback method");
        synchronized (d) {
            if (f13783a.containsKey(Integer.valueOf(i))) {
                return f13783a.get(Integer.valueOf(i));
            }
            return new ArrayList(10);
        }
    }

    public static void e() {
        LogUtil.a("CommandCallback", "Enter releaseAllCommandCallbackMap method");
        synchronized (d) {
            Iterator<Integer> it = e.iterator();
            while (it.hasNext()) {
                b(it.next().intValue());
            }
        }
    }

    public static void b() {
        LogUtil.a("CommandCallback", "Enter resetCommandCallbackMap method");
        synchronized (d) {
            Iterator<Integer> it = e.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                if (intValue != 13) {
                    b(intValue);
                }
            }
        }
    }

    private static void b(int i) {
        LogUtil.a("CommandCallback", "Enter removeCommandCallback method");
        synchronized (d) {
            if (f13783a.get(Integer.valueOf(i)) != null) {
                f13783a.get(Integer.valueOf(i)).clear();
            }
        }
    }
}
