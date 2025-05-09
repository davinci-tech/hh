package defpackage;

import com.huawei.appgallery.marketinstallerservice.b.a.b.b;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.RequestBean;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.ResponseBean;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class agp {
    private static final Map<String, Class> b;
    public static final ThreadPoolExecutor d;

    public static ResponseBean a(RequestBean requestBean) {
        return new b(requestBean, null).c(requestBean.getContext());
    }

    public static b a(RequestBean requestBean, com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.b bVar) {
        b bVar2 = new b(requestBean, bVar);
        bVar2.e(d);
        return bVar2;
    }

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        if (hashMap.size() <= 0) {
            hashMap.put("client.getMarketInfo", agk.class);
        }
        d = new ThreadPoolExecutor(6, 6, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        Executors.newFixedThreadPool(1);
    }
}
