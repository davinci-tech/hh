package com.huawei.agconnect.abtest.a;

import android.content.Context;
import android.os.Bundle;
import com.huawei.agconnect.abtest.AGConnectABTesting;
import com.huawei.agconnect.common.api.HaBridge;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class c extends AGConnectABTesting {

    /* renamed from: a, reason: collision with root package name */
    private final String f1696a;
    private HaBridge b = new HaBridge(HaBridge.HA_SERVICE_TAG_ABTEST);

    @Override // com.huawei.agconnect.abtest.AGConnectABTesting
    public void replaceAllExperiments(List<Map<String, String>> list) {
        if (list == null) {
            throw new IllegalArgumentException("The replacementExperiments list is null.");
        }
        if (list.isEmpty()) {
            removeAllExperiments();
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Iterator<Map<String, String>> it = list.iterator();
        while (it.hasNext()) {
            a a2 = a.a(it.next());
            arrayList.add(a2);
            hashSet.add(a2.a());
        }
        List<a> a3 = d.a(this.f1696a);
        HashSet hashSet2 = new HashSet();
        Iterator<a> it2 = a3.iterator();
        while (it2.hasNext()) {
            hashSet2.add(it2.next().a());
        }
        b(a(a3, hashSet));
        a(b(arrayList, hashSet2));
        d.b();
    }

    @Override // com.huawei.agconnect.abtest.AGConnectABTesting
    public void removeAllExperiments() {
        b(d.a(this.f1696a));
        d.b();
    }

    private void b(List<a> list) {
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            d.a(it.next());
        }
    }

    private ArrayList<a> b(List<a> list, Set<String> set) {
        ArrayList<a> arrayList = new ArrayList<>();
        for (a aVar : list) {
            if (!set.contains(aVar.a())) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private void a(List<a> list) {
        ArrayDeque arrayDeque = new ArrayDeque(d.a(this.f1696a));
        for (a aVar : list) {
            while (arrayDeque.size() >= 25) {
                a aVar2 = (a) arrayDeque.pollFirst();
                if (aVar2 != null) {
                    d.a(aVar2);
                }
            }
            a(aVar);
            arrayDeque.offer(aVar);
        }
    }

    private void a(a aVar) {
        if (aVar.c() == null || aVar.c().length() == 0) {
            Bundle bundle = new Bundle();
            bundle.putString("$ABTaskId", aVar.a());
            bundle.putString("$ABVarId", aVar.b());
            bundle.putString("$ABChannel", this.f1696a);
            Logger.d("ABTest", "report ab test event");
            this.b.onEvent("$JoinABTask", bundle);
            aVar.a(true);
        }
        d.a(aVar, this.f1696a);
    }

    private ArrayList<a> a(List<a> list, Set<String> set) {
        ArrayList<a> arrayList = new ArrayList<>();
        for (a aVar : list) {
            if (!set.contains(aVar.a())) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public c(Context context, String str) {
        this.f1696a = str;
        SharedPrefUtil.init(context);
        d.a();
    }
}
