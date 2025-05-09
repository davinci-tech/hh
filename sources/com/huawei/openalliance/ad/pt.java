package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.ContentExt;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.inter.data.IPreCheckInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class pt {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Integer> f7459a;

    private static void b(List<IPreCheckInfo> list, Ad30 ad30, qa qaVar) {
        List<Content> c = ad30.c();
        if (com.huawei.openalliance.ad.utils.bg.a(c)) {
            return;
        }
        for (Content content : c) {
            if (content != null) {
                list.add(a(ad30, false, qaVar, content));
            }
        }
    }

    private static void a(List<IPreCheckInfo> list, Ad30 ad30, qa qaVar) {
        List<Content> h = ad30.h();
        if (com.huawei.openalliance.ad.utils.bg.a(h)) {
            return;
        }
        for (Content content : h) {
            if (content != null) {
                list.add(a(ad30, true, qaVar, content));
            }
        }
    }

    private static void a(int i) {
        if (i != -1) {
            String a2 = com.huawei.openalliance.ad.utils.cz.a(Integer.valueOf(i));
            if (!f7459a.containsKey(a2)) {
                f7459a.put(a2, 1);
            } else {
                f7459a.put(a2, Integer.valueOf(f7459a.get(a2).intValue() + 1));
            }
        }
    }

    private static Map<String, String> a(Content content) {
        HashMap hashMap = new HashMap();
        List<ContentExt> Q = content.Q();
        if (!com.huawei.openalliance.ad.utils.bg.a(Q)) {
            for (ContentExt contentExt : Q) {
                if (contentExt != null) {
                    hashMap.put(contentExt.a(), com.huawei.openalliance.ad.utils.cz.c(contentExt.b()));
                }
            }
        }
        List<ImpEX> P = content.P();
        if (!com.huawei.openalliance.ad.utils.bg.a(P)) {
            for (ImpEX impEX : P) {
                if (impEX != null) {
                    hashMap.put(impEX.a(), com.huawei.openalliance.ad.utils.cz.c(impEX.b()));
                }
            }
        }
        return hashMap;
    }

    private static List<ImpEX> a(List<ImpEX> list, List<ImpEX> list2) {
        ArrayList arrayList = new ArrayList();
        if (!com.huawei.openalliance.ad.utils.bg.a(list2)) {
            arrayList.addAll(list2);
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(list)) {
            arrayList.addAll(list);
        }
        if (com.huawei.openalliance.ad.utils.bg.a(arrayList)) {
            return null;
        }
        return arrayList;
    }

    private static List<pz> a(Context context, List<Integer> list) {
        Object puVar;
        if (list == null || context == null) {
            if (list == null) {
                ho.c("AdFilterManager", "createFilters filterList is null");
            }
            if (context != null) {
                return null;
            }
            ho.c("AdFilterManager", "createFilters context is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : list) {
            if (num != null) {
                int intValue = num.intValue();
                if (intValue == 1) {
                    puVar = new pu(context);
                } else if (intValue == 2) {
                    puVar = new pv(context);
                } else if (intValue == 3) {
                    puVar = new qa(context);
                } else if (intValue == 4) {
                    puVar = new pw(context);
                } else if (intValue == 99) {
                    puVar = new py(context);
                }
                arrayList.add(puVar);
            }
        }
        return arrayList;
    }

    public static List<IPreCheckInfo> a(Context context, AdContentRsp adContentRsp) {
        ArrayList arrayList = new ArrayList();
        if (context != null && adContentRsp != null) {
            List<Ad30> c = adContentRsp.c();
            if (com.huawei.openalliance.ad.utils.bg.a(adContentRsp.c())) {
                return arrayList;
            }
            for (Ad30 ad30 : c) {
                if (ad30 != null) {
                    qa qaVar = new qa(context);
                    b(arrayList, ad30, qaVar);
                    a(arrayList, ad30, qaVar);
                }
            }
        }
        return arrayList;
    }

    private static com.huawei.openalliance.ad.inter.data.f a(Ad30 ad30, boolean z, qa qaVar, Content content) {
        com.huawei.openalliance.ad.inter.data.f fVar = new com.huawei.openalliance.ad.inter.data.f();
        fVar.b(content.g());
        fVar.a(ad30.a());
        fVar.a(z);
        fVar.a(a(content));
        fVar.a(qaVar.a(content.c()));
        return fVar;
    }

    public static AdContentRsp a(Context context, AdContentRsp adContentRsp, int i) {
        long c = com.huawei.openalliance.ad.utils.ao.c();
        f7459a = new HashMap();
        if (adContentRsp == null) {
            return null;
        }
        List<Ad30> c2 = adContentRsp.c();
        if (com.huawei.openalliance.ad.utils.bg.a(c2)) {
            return adContentRsp;
        }
        for (Ad30 ad30 : c2) {
            List<Content> c3 = ad30.c();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            List<ImpEX> f = ad30.f();
            if (!com.huawei.openalliance.ad.utils.bg.a(c3)) {
                for (Content content : c3) {
                    if (content != null) {
                        content.a(adContentRsp.h(), i);
                        content.f(adContentRsp.n());
                        content.g(a(f, content.P()));
                        List<pz> a2 = a(context, content.y());
                        int a3 = a(ad30.a(), a2, content, i, adContentRsp.n());
                        if (a3 == -1) {
                            arrayList.add(content);
                            com.huawei.openalliance.ad.utils.cl.a(context, content, a(a2), i);
                        } else {
                            a(a3);
                            arrayList2.add(content);
                        }
                    }
                }
            }
            ad30.a(arrayList);
            ad30.c(arrayList2);
        }
        if (!com.huawei.openalliance.ad.utils.bl.a(f7459a)) {
            adContentRsp.a(f7459a);
        }
        long c4 = com.huawei.openalliance.ad.utils.ao.c() - c;
        adContentRsp.a(c4);
        if (ho.a()) {
            ho.a("AdFilterManager", "filter ad contents, duration: %s ms", Long.valueOf(c4));
        }
        return adContentRsp;
    }

    private static int a(List<pz> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return 0;
        }
        Iterator<pz> it = list.iterator();
        while (it.hasNext()) {
            int b = it.next().b();
            if (b == 3) {
                return b;
            }
        }
        return 0;
    }

    private static int a(String str, List<pz> list, Content content, int i, int i2) {
        if (list == null) {
            return -1;
        }
        boolean z = false;
        int i3 = -1;
        for (pz pzVar : list) {
            z |= pzVar.a(str, i, i2, content);
            if (z && i3 == -1) {
                i3 = pzVar.b();
            }
        }
        return i3;
    }
}
