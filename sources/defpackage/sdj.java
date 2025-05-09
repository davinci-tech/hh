package defpackage;

import com.huawei.haf.common.os.FileUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class sdj {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f17032a = new Object();
    private static volatile sdj e;

    private sdj() {
    }

    public static sdj e() {
        sdj sdjVar;
        synchronized (f17032a) {
            if (e == null) {
                e = new sdj();
            }
            sdjVar = e;
        }
        return sdjVar;
    }

    private ArrayList<cpm> g() {
        ArrayList<cpm> a2 = jfv.a();
        return koq.b(a2) ? new ArrayList<>(0) : a2;
    }

    private HashMap<String, Integer> c() {
        HashMap<String, Integer> b = cup.b();
        return b.isEmpty() ? new HashMap<>(0) : b;
    }

    private List<String> b() {
        ArrayList<cpm> g = g();
        LogUtil.a("RemoveNoUsePluginUtils", "localDeviceInfo size: ", Integer.valueOf(g.size()));
        HashMap<String, Integer> c = c();
        LogUtil.a("RemoveNoUsePluginUtils", "cloudDeviceInfo size: ", Integer.valueOf(c.size()));
        ArrayList arrayList = new ArrayList(16);
        Iterator<cpm> it = g.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().i()));
        }
        ArrayList arrayList2 = new ArrayList(16);
        for (Map.Entry<String, Integer> entry : c.entrySet()) {
            String key = entry.getKey();
            if (!arrayList.contains(Integer.valueOf(entry.getValue().intValue()))) {
                arrayList2.add(key);
                LogUtil.c("RemoveNoUsePluginUtils", "not used deviceInfo size: ", Integer.valueOf(arrayList2.size()));
            }
        }
        return arrayList2;
    }

    private List<String> f() {
        List<String> b = b();
        ArrayList arrayList = new ArrayList(16);
        for (String str : b) {
            arrayList.add(msj.a().d(str) + File.separator + str + File.separator + "img");
        }
        return arrayList;
    }

    public void d() {
        for (String str : f()) {
            LogUtil.c("RemoveNoUsePluginUtils", "getPathPath: ", str, "isSuccess: ", Boolean.valueOf(moh.b(str)));
        }
    }

    public long a() {
        Iterator<String> it = f().iterator();
        long j = 0;
        while (it.hasNext()) {
            j += FileUtils.j(new File(it.next()));
        }
        LogUtil.a("RemoveNoUsePluginUtils", "totalSize: ", Long.valueOf(j));
        return j;
    }
}
