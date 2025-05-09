package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jbe {
    public static void a(final String str, final List<jbd> list, final ResultCallback<jbk> resultCallback) {
        LogUtil.a("HonorPrivacyManager", "enter queryHonorPrivacy");
        ThreadPoolManager.d().execute(new Runnable() { // from class: jbe.1
            @Override // java.lang.Runnable
            public void run() {
                jbi jbiVar = new jbi();
                jbiVar.d(str);
                jbiVar.a(list);
                jbiVar.e(false);
                lqi.d().b(jbiVar.getUrl(), jbe.c(), lql.b(jbiVar), jbk.class, resultCallback);
            }
        });
    }

    public static void c(final String str, final List<jbm> list, final ResultCallback<jbk> resultCallback) {
        LogUtil.a("HonorPrivacyManager", "enter queryHonorPrivacy");
        ThreadPoolManager.d().execute(new Runnable() { // from class: jbe.3
            @Override // java.lang.Runnable
            public void run() {
                jbl jblVar = new jbl();
                jblVar.e(str);
                jblVar.e(list);
                lqi.d().b(jblVar.getUrl(), jbe.c(), lql.b(jblVar), jbk.class, resultCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/json");
        hashMap.put("X-HW-ID", "3576ad5484514af79a9b6f77ebd48090");
        hashMap.put("X-HW-APPKEY", "04X/NQfuHWMV4wWKfdj9og==");
        hashMap.put("projectAppId", "3576ad5484514af79a9b6f77ebd48090");
        return hashMap;
    }
}
