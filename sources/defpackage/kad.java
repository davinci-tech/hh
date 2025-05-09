package defpackage;

import android.text.TextUtils;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.SyncHelper;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class kad {
    public static void a(List<Bean> list, String str, List<Bean> list2, List<String> list3) {
        LogUtil.a("ContactsCompareUtils", "compare: start. ");
        boolean isEmpty = TextUtils.isEmpty(str);
        List<jzt> b = jzy.b(str);
        boolean z = isEmpty || b.isEmpty();
        boolean z2 = list == null || list.isEmpty();
        if (z2 && z) {
            LogUtil.a("ContactsCompareUtils", "compare: SCENARIO 1.");
            return;
        }
        if (z) {
            list2.addAll(b(list));
            LogUtil.a("ContactsCompareUtils", "compare: SCENARIO 2. updated's size: ", Integer.valueOf(list2.size()));
            return;
        }
        if (z2) {
            LogUtil.a("ContactsCompareUtils", "compare: SCENARIO 3.1. ");
        } else {
            LogUtil.a("ContactsCompareUtils", "compare: SCENARIO 3.2. ");
        }
        List<String> d = d(b);
        kao.a(list);
        kao.a(b);
        kao.a(d);
        e(list, b, list2, list3);
    }

    private static Bean d(Bean bean) {
        bean.setUid(SyncHelper.a());
        return bean;
    }

    private static List<Bean> b(List<Bean> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("ContactsCompareUtils", "fillUid: list is null or empty.");
            return new ArrayList(0);
        }
        for (Bean bean : list) {
            if (TextUtils.isEmpty(bean.getUid())) {
                d(bean);
            }
        }
        return list;
    }

    private static List<String> d(List<jzt> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("ContactsCompareUtils", "getCachedUidList: cache list is null or empty.");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<jzt> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().c());
        }
        return arrayList;
    }

    private static void e(List<Bean> list, List<jzt> list2, List<Bean> list3, List<String> list4) {
        LogUtil.a("ContactsCompareUtils", "calculate: start.");
        HashMap hashMap = new HashMap(list2.size());
        for (jzt jztVar : list2) {
            hashMap.put(Integer.valueOf(jztVar.a()), jztVar);
        }
        Set keySet = hashMap.keySet();
        e(keySet, hashMap, list4);
        if (list != null && !list.isEmpty()) {
            d(keySet, hashMap, list, list3);
        }
        LogUtil.a("ContactsCompareUtils", "calculate: updated/deleted size: ", Integer.valueOf(list3.size()), Integer.valueOf(list4.size()));
    }

    private static void e(Set<Integer> set, Map<Integer, jzt> map, List<String> list) {
        ArrayList arrayList = new ArrayList(set);
        List<Bean> d = jzn.d();
        ArrayList arrayList2 = new ArrayList(d.size());
        if (!d.isEmpty()) {
            Iterator<Bean> it = d.iterator();
            while (it.hasNext()) {
                int e = CommonUtil.e(it.next().getId(), -1);
                if (e != -1) {
                    arrayList2.add(Integer.valueOf(e));
                }
            }
        }
        if (arrayList.removeAll(arrayList2)) {
            LogUtil.a("ContactsCompareUtils", "calculate: deletedRidList or updateContactRidList is empty.");
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            jzt jztVar = map.get((Integer) it2.next());
            if (jztVar != null) {
                list.add(jztVar.c());
            }
        }
    }

    private static void d(Set<Integer> set, Map<Integer, jzt> map, List<Bean> list, List<Bean> list2) {
        for (Bean bean : list) {
            if (!(bean instanceof kaa)) {
                LogUtil.a("ContactsCompareUtils", "calculateUpdatedContacts: bean is not instance of RawContactBean.");
            } else {
                int e = CommonUtil.e(bean.getId(), -1);
                if (e != -1) {
                    if (!set.contains(Integer.valueOf(e))) {
                        LogUtil.a("ContactsCompareUtils", "calculateUpdatedContacts: KeySet do not contains rid: ", Integer.valueOf(e));
                        list2.add(d(bean));
                    } else {
                        jzt jztVar = map.get(Integer.valueOf(e));
                        if (jztVar == null) {
                            LogUtil.a("ContactsCompareUtils", "calculateUpdatedContacts: cachedSimpleContactBean is null.");
                        } else {
                            kaa kaaVar = (kaa) bean;
                            kaaVar.setUid("0");
                            if (!kak.d(kaf.b(kaaVar)).equals(jztVar.e())) {
                                bean.setUid(jztVar.c());
                                list2.add(bean);
                            } else {
                                LogUtil.a("ContactsCompareUtils", "calculateUpdatedContacts: the rawContactFeature is same to cache's rawContactFeature ");
                            }
                        }
                    }
                }
            }
        }
    }
}
