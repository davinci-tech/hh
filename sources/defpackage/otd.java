package defpackage;

import android.content.ContentValues;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.utlis.SearchDeviceThreadManager;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes9.dex */
public class otd {
    private CountDownLatch b;
    private List<nyq> e = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<nyq> f15938a = new ArrayList();
    private List<cjv> d = new ArrayList();
    private SearchDeviceThreadManager f = SearchDeviceThreadManager.b();
    private List<cjv> c = new ArrayList(16);
    private HashMap<String, Integer> g = new HashMap<>(16);

    private void e() {
        this.e.clear();
        oba obaVar = new oba(null);
        if (koq.b(obaVar.b())) {
            LogUtil.h("DeviceSearchUtil", "alldevices is null");
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexAll();
        }
        this.e.addAll(obaVar.b());
        LogUtil.a("DeviceSearchUtil", "allDevices size: ", Integer.valueOf(this.e.size()));
    }

    public Map<String, Object> d(String str) {
        if (koq.b(this.e)) {
            e();
        }
        this.f15938a.clear();
        this.d.clear();
        this.b = new CountDownLatch(2);
        this.f.d(new SearchDeviceThreadManager.SearchCallback() { // from class: otd.4
            @Override // com.huawei.ui.device.utlis.SearchDeviceThreadManager.SearchCallback
            public void onSearchResult(List<nyq> list) {
                otd.this.f15938a = list;
                otd.this.b.countDown();
            }
        });
        a();
        this.f.e(str, this.e);
        try {
            this.b.await();
        } catch (InterruptedException e) {
            LogUtil.b("DeviceSearchUtil", "loadData failed, cause InterruptedException happened: " + e.getCause());
        }
        HashMap hashMap = new HashMap();
        if (koq.b(this.f15938a)) {
            return hashMap;
        }
        hashMap.put("ALL_DEVICE_KEY", this.f15938a);
        hashMap.put("CONNECTED_DEVICE_KEY", this.d);
        return hashMap;
    }

    private void a() {
        LogUtil.a("DeviceSearchUtil", "enter initList");
        this.c.clear();
        ThreadPoolManager.d().execute(new Runnable() { // from class: otd.2
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList(16);
                ArrayList arrayList2 = new ArrayList(16);
                otd.this.e(arrayList, arrayList2);
                ArrayList arrayList3 = new ArrayList(16);
                Iterator<ContentValues> it = ceo.d().f().iterator();
                while (it.hasNext()) {
                    ContentValues next = it.next();
                    String asString = next.getAsString("productId");
                    String asString2 = next.getAsString("uniqueId");
                    if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2)) {
                        LogUtil.h("DeviceSearchUtil", "initDeviceList : productId or deviceIdentify is empty");
                    } else {
                        dcz d = ResourceManager.e().d(asString);
                        if (d != null && !d.n().d().trim().isEmpty()) {
                            cjv b = dbp.b(d);
                            b.FU_(next);
                            b.a(new cke("deviceUsedTime").b(cpp.a(), next.getAsString("uniqueId")));
                            arrayList3.add(b);
                        }
                    }
                }
                if (koq.c(arrayList3)) {
                    arrayList.addAll(arrayList3);
                }
                Collections.sort(arrayList2);
                Collections.sort(arrayList);
                arrayList.addAll(0, arrayList2);
                otd.this.d = arrayList;
                LogUtil.a("DeviceSearchUtil", "productInfoList size: ", Integer.valueOf(arrayList.size()));
                otd.this.b.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<cjv> list, List<cjv> list2) {
        LogUtil.a("DeviceSearchUtil", "enter setList");
        ArrayList<cpm> a2 = jfv.a();
        int i = a2.size() > 1 ? 2 : 1;
        LogUtil.a("DeviceSearchUtil", "enter setList displayType：", Integer.valueOf(i));
        int a3 = a(a2);
        Iterator<cpm> it = a2.iterator();
        while (it.hasNext()) {
            cpm next = it.next();
            cjv cjvVar = new cjv();
            cjvVar.a(1);
            cjvVar.e(i);
            cjvVar.c(next);
            if (next.b() == 1 && next.e() == 2) {
                if (a3 > 1) {
                    cjvVar.a(this.g.get(next.a()).intValue() + System.currentTimeMillis());
                } else {
                    cjvVar.a(System.currentTimeMillis());
                }
                LogUtil.a("DeviceSearchUtil", "deviceInfoForWear name: ", next.d(), "deviceInfoForWear current connected time:", Long.valueOf(System.currentTimeMillis()), ",compareTime:", Long.valueOf(cjvVar.d()));
                c(list2, cjvVar);
            } else {
                cjvVar.a(next.h());
                LogUtil.a("DeviceSearchUtil", "deviceInfoForWear name: ", next.d(), "deviceInfoForWear last connected time:", Long.valueOf(next.h()));
                list.add(cjvVar);
            }
        }
        LogUtil.a("DeviceSearchUtil", "has wear deviceInfoForWears : ", a2.toArray());
    }

    private void c(List<cjv> list, cjv cjvVar) {
        if (!list.isEmpty()) {
            Object i = list.get(0).i();
            if (i instanceof cpm) {
                if (((cpm) i).e() == 2) {
                    LogUtil.a("DeviceSearchUtil", "add to normal");
                    a(list, cjvVar);
                    return;
                } else {
                    LogUtil.a("DeviceSearchUtil", "add to first");
                    list.add(0, cjvVar);
                    return;
                }
            }
            return;
        }
        list.add(cjvVar);
    }

    private void a(List<cjv> list, cjv cjvVar) {
        if (list.size() > 1) {
            list.add(1, cjvVar);
        } else {
            list.add(cjvVar);
        }
    }

    private int a(ArrayList<cpm> arrayList) {
        ArrayList arrayList2 = new ArrayList(16);
        Iterator<cpm> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            cpm next = it.next();
            if (next.b() == 1 && next.e() == 2) {
                i++;
                cjv cjvVar = new cjv();
                cjvVar.c(next);
                cjvVar.a(next.h());
                arrayList2.add(cjvVar);
            }
        }
        if (i > 1) {
            Collections.sort(arrayList2);
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                cpm cpmVar = (cpm) ((cjv) arrayList2.get(i2)).i();
                LogUtil.c("DeviceSearchUtil", "getConnectedDevices connectedDevice name:", cpmVar.d());
                this.g.put(cpmVar.a(), Integer.valueOf((size - i2) * 10));
            }
        }
        LogUtil.a("DeviceSearchUtil", "getConnectedDevices connectedCount:", Integer.valueOf(i));
        return i;
    }
}
