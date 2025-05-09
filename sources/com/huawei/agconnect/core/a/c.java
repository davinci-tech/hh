package com.huawei.agconnect.core.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import com.huawei.agconnect.core.Service;
import com.huawei.agconnect.core.ServiceDiscovery;
import com.huawei.agconnect.core.ServiceRegistrar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1720a;

    public List<Service> a() {
        Log.i("AGC_Registrar", "getServices");
        List<String> b = b();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            ServiceRegistrar a2 = a(it.next());
            if (a2 != null) {
                a2.initialize(this.f1720a);
                List<Service> services = a2.getServices(this.f1720a);
                if (services != null) {
                    arrayList.addAll(services);
                }
            }
        }
        Log.i("AGC_Registrar", "services:" + arrayList.size());
        return arrayList;
    }

    static class a implements Serializable, Comparator<Map.Entry<String, Integer>> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
            return entry.getValue().intValue() - entry2.getValue().intValue();
        }

        private a() {
        }
    }

    private Bundle c() {
        ServiceInfo serviceInfo;
        PackageManager packageManager = this.f1720a.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            serviceInfo = packageManager.getServiceInfo(new ComponentName(this.f1720a, (Class<?>) ServiceDiscovery.class), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("AGC_Registrar", "get ServiceDiscovery exception." + e.getLocalizedMessage());
        }
        if (serviceInfo != null) {
            return serviceInfo.metaData;
        }
        Log.e("AGC_Registrar", "Can not found ServiceDiscovery service.");
        return null;
    }

    private List<String> b() {
        StringBuilder sb;
        ArrayList arrayList = new ArrayList();
        Bundle c = c();
        if (c == null) {
            return arrayList;
        }
        HashMap hashMap = new HashMap(10);
        for (String str : c.keySet()) {
            if ("com.huawei.agconnect.core.ServiceRegistrar".equals(c.getString(str))) {
                String[] split = str.split(":");
                if (split.length == 2) {
                    try {
                        hashMap.put(split[0], Integer.valueOf(split[1]));
                    } catch (NumberFormatException e) {
                        sb = new StringBuilder("registrar configuration format error:");
                        str = e.getMessage();
                    }
                } else if (split.length == 1) {
                    hashMap.put(split[0], 1000);
                } else {
                    sb = new StringBuilder("registrar configuration error, ");
                    sb.append(str);
                    Log.e("AGC_Registrar", sb.toString());
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList2, new a());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(((Map.Entry) it.next()).getKey());
        }
        return arrayList;
    }

    private <T extends ServiceRegistrar> T a(String str) {
        StringBuilder sb;
        String localizedMessage;
        try {
            Class<?> cls = Class.forName(str);
            if (ServiceRegistrar.class.isAssignableFrom(cls)) {
                return (T) Class.forName(str).newInstance();
            }
            Log.e("AGC_Registrar", cls + " must extends from ServiceRegistrar.");
            return null;
        } catch (ClassNotFoundException e) {
            sb = new StringBuilder("Can not found service class, ");
            localizedMessage = e.getMessage();
            sb.append(localizedMessage);
            Log.e("AGC_Registrar", sb.toString());
            return null;
        } catch (IllegalAccessException e2) {
            sb = new StringBuilder("instantiate service class exception ");
            localizedMessage = e2.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e("AGC_Registrar", sb.toString());
            return null;
        } catch (InstantiationException e3) {
            sb = new StringBuilder("instantiate service class exception ");
            localizedMessage = e3.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e("AGC_Registrar", sb.toString());
            return null;
        }
    }

    c(Context context) {
        this.f1720a = context;
    }
}
