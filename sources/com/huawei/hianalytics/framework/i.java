package com.huawei.hianalytics.framework;

import android.text.TextUtils;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.core.transport.Utils;
import com.huawei.hianalytics.framework.config.ICallback;
import com.huawei.hianalytics.framework.config.ICollectorConfig;
import com.huawei.hianalytics.framework.policy.IStoragePolicy;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    public final String f3862a;
    public final String b;
    public final List<Event> c;
    public final ICollectorConfig d;
    public final ICallback e;
    public boolean f;
    public final String g;
    public final int h;
    public final int i;

    public void a() {
        Event event;
        String evtExHashCode;
        List list;
        HashMap hashMap = new HashMap();
        for (int i = 0; i < this.c.size(); i++) {
            if (TextUtils.isEmpty(this.c.get(i).getEvtExHashCode())) {
                event = this.c.get(i);
                evtExHashCode = "noExHashFlag";
                list = (List) hashMap.get("noExHashFlag");
                if (list == null) {
                    list = new ArrayList();
                }
            } else {
                event = this.c.get(i);
                evtExHashCode = this.c.get(i).getEvtExHashCode();
                list = (List) hashMap.get(evtExHashCode);
                if (list == null) {
                    list = new ArrayList();
                }
            }
            list.add(event);
            hashMap.put(evtExHashCode, list);
        }
        if (hashMap.size() <= 0) {
            return;
        }
        IStoragePolicy d = b.d(this.f3862a);
        Iterator it = hashMap.entrySet().iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (!a(d, (List) ((Map.Entry) it.next()).getValue())) {
                z = false;
            }
        }
        if (this.f) {
            return;
        }
        e a2 = b.a(this.f3862a);
        String str = this.b;
        if (z) {
            a2.b(str);
        } else {
            a2.a(str);
        }
    }

    public final boolean a(IStoragePolicy iStoragePolicy, List<Event> list) {
        IStorageHandler c;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int decryptMaxBatchSize = iStoragePolicy.getDecryptMaxBatchSize(this.b);
        boolean z = true;
        int i = 0;
        for (Event event : list) {
            if (iStoragePolicy.decide(IStoragePolicy.PolicyType.STORAGE_CYCLE, this.b, Utils.parseStringToLong(event.getEvttime()))) {
                arrayList.add(event);
            } else {
                if (event.getSubCount() + i > decryptMaxBatchSize) {
                    if (a(arrayList2, EncryptUtil.generateSecureRandomStr(16), this.f, i) == 0) {
                        z = false;
                    }
                    arrayList2 = new ArrayList();
                    i = 0;
                }
                arrayList2.add(event);
                i += event.getSubCount();
            }
        }
        boolean z2 = (arrayList2.size() <= 0 || a(arrayList2, EncryptUtil.generateSecureRandomStr(16), this.f, i) != 0) ? z : false;
        if (z2 && !"ha_default_collection".equals(this.f3862a)) {
            e a2 = b.a(this.f3862a);
            String str = this.b;
            a2.e.remove(str + "_send_retry_flag");
        }
        if (arrayList.size() > 0 && (c = b.c(this.f3862a)) != null) {
            c.deleteEvents(arrayList);
        }
        return z2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:0|1|(2:3|(3:7|8|(3:10|(3:14|(6:17|(1:19)(1:37)|20|(5:26|27|(1:29)(2:32|(1:36))|30|31)(3:22|23|24)|25|15)|38)|39)(7:41|42|43|(1:45)(1:109)|46|47|(2:49|50)(2:51|(2:53|54)(2:55|(2:57|58)(11:59|(1:61)|62|(1:64)(1:107)|65|(1:67)(1:106)|68|69|(2:71|(1:73)(4:76|(1:82)|83|84))(2:85|(2:87|88)(2:89|(1:91)(4:92|(4:95|(2:97|98)(2:100|101)|99|93)|102|84)))|74|75))))))|111|(1:113)(1:126)|114|115|116|117|118|119|120|8|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x00c1, code lost:
    
        r4 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(java.util.List<com.huawei.hianalytics.core.storage.Event> r24, java.lang.String r25, boolean r26, int r27) {
        /*
            Method dump skipped, instructions count: 1123
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.framework.i.a(java.util.List, java.lang.String, boolean, int):int");
    }

    public i(String str, String str2, List<Event> list, ICallback iCallback, String str3, int i, int i2) {
        this.f3862a = str;
        this.b = str2;
        this.c = list;
        this.e = iCallback;
        this.g = str3;
        this.d = b.b(str);
        this.h = i;
        this.i = i2;
    }
}
