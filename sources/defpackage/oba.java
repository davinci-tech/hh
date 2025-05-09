package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class oba {

    /* renamed from: a, reason: collision with root package name */
    private String f15590a;
    private cuy c;
    private JSONObject d;
    private JSONObject f;
    private List<nym> e = new ArrayList(16);
    private List<nyq> b = new ArrayList(16);
    private List<Map<String, List<cve>>> i = new ArrayList(16);

    public oba(List<String> list) {
        this.e.clear();
        this.b.clear();
        this.i.clear();
        d();
        g();
        c();
        e(list);
        e();
    }

    private void d() {
        cuy indexBean = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexBean();
        this.c = indexBean;
        if (indexBean != null) {
            this.f15590a = indexBean.d();
        }
    }

    private void g() {
        if (TextUtils.isEmpty(this.f15590a)) {
            return;
        }
        File e = cvy.e(cvy.b() + this.f15590a);
        if (e == null) {
            return;
        }
        this.f = cvy.b(e);
    }

    private void c() {
        if (TextUtils.isEmpty(this.f15590a)) {
            return;
        }
        File c = cvy.c(cvy.b() + this.f15590a);
        if (c == null) {
            return;
        }
        this.d = cvy.b(c);
    }

    private void e(List<String> list) {
        cuy cuyVar = this.c;
        if (cuyVar == null) {
            LogUtil.h("GetDeviceListUtils", "setDeviceList, indexallBean is null");
            return;
        }
        List<cva> e = cuyVar.e();
        if (e == null || e.isEmpty()) {
            LogUtil.h("GetDeviceListUtils", "setDeviceList, deviceKindBeans is empty");
            return;
        }
        for (cva cvaVar : e) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(16);
            List<cve> c = cvaVar.c();
            if (c == null) {
                LogUtil.h("GetDeviceListUtils", "setDeviceList, devicePluginInfos is empty");
            } else if (list == null || list.contains(cvaVar.e())) {
                Iterator<cve> it = c.iterator();
                while (it.hasNext()) {
                    b(linkedHashMap, it.next());
                }
                if (!linkedHashMap.isEmpty()) {
                    nym nymVar = new nym();
                    nymVar.c(e(cvaVar.a()));
                    nymVar.a(cvaVar.e());
                    this.e.add(nymVar);
                    this.i.add(linkedHashMap);
                }
            }
        }
    }

    private void b(Map<String, List<cve>> map, cve cveVar) {
        if (!cwc.e(cveVar.a())) {
            LogUtil.h("GetDeviceListUtils", "app version is not supported");
            return;
        }
        if (koq.b(cveVar.ac()) || !PermissionDialogHelper.b(cveVar.ac().get(0))) {
            String v = cveVar.v();
            if (Utils.o()) {
                if (TextUtils.equals(v, "2") || TextUtils.equals(v, "3")) {
                    b(cveVar, map);
                    return;
                }
                return;
            }
            if (TextUtils.equals(v, "1") || TextUtils.equals(v, "3")) {
                d(map, cveVar);
            }
        }
    }

    private void b(cve cveVar, Map<String, List<cve>> map) {
        if (!cpa.f()) {
            List<String> ac = cveVar.ac();
            if (ac.contains("b29df4e3-b1f7-4e40-960d-4cfb63ccca05") || ac.contains("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4")) {
                return;
            }
            d(map, cveVar);
            return;
        }
        d(map, cveVar);
    }

    private void d(Map<String, List<cve>> map, cve cveVar) {
        if (!b(cveVar)) {
            LogUtil.h("GetDeviceListUtils", "setDeviceMap,uuid list is empty");
            return;
        }
        String i = cveVar.i();
        if (map.containsKey(i)) {
            List<cve> list = map.get(i);
            if (list == null) {
                list = new ArrayList<>(16);
            }
            list.add(cveVar);
            map.put(i, list);
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(cveVar);
        map.put(i, arrayList);
    }

    private boolean b(cve cveVar) {
        List<cvd> h = cveVar.h();
        if (bkz.e(h)) {
            LogUtil.a("GetDeviceListUtils", "this device not support different brand");
            return true;
        }
        List<String> ac = cveVar.ac();
        if (bkz.e(ac)) {
            LogUtil.h("GetDeviceListUtils", "setOtherBrandInfo, uuidList is null");
            return false;
        }
        if (ac.contains("a4da0665-b209-4dfe-8617-cad54daf1b1f")) {
            cvd cvdVar = h.get(0);
            if (cvdVar.b().contains(BaseApplication.getContext().getResources().getConfiguration().locale.getCountry())) {
                cveVar.c(cvdVar.e());
                cveVar.b(e(cvdVar.e()));
            }
        }
        if (!ac.contains("f98754bd-bc30-4c72-8d25-a1d17e13671b")) {
            return true;
        }
        cvd cvdVar2 = h.get(0);
        if (!cvdVar2.b().contains(BaseApplication.getContext().getResources().getConfiguration().locale.getCountry())) {
            return true;
        }
        cveVar.c(cvdVar2.e());
        cveVar.b(e(cvdVar2.e()));
        return true;
    }

    public String e(String str) {
        List<String> c = cvy.c(str, this.f, this.d);
        if (c.size() > 0) {
            return c.get(0);
        }
        LogUtil.h("GetDeviceListUtils", "getDeviceContext,value is empty");
        return "";
    }

    private void e() {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            Map<String, List<cve>> map = this.i.get(i);
            Iterator<Map.Entry<String, List<cve>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                List<cve> list = map.get(it.next().getKey());
                if (list == null || list.isEmpty()) {
                    LogUtil.h("GetDeviceListUtils", "mapDeviceInfo, allDeviceItems is empty");
                } else {
                    nyq nyqVar = new nyq();
                    nyqVar.b(e(list.get(0).g()));
                    nyqVar.c(i);
                    nyqVar.e(i);
                    nyqVar.j(1);
                    this.b.add(nyqVar);
                    Iterator<cve> it2 = list.iterator();
                    while (it2.hasNext()) {
                        a(it2.next());
                    }
                }
            }
        }
    }

    private void a(cve cveVar) {
        String k;
        nyq nyqVar = new nyq();
        nyqVar.a(cveVar.f());
        nyqVar.e(cveVar.ac());
        nyqVar.b(cveVar.g());
        nyqVar.f(cveVar.t());
        nyqVar.a(cveVar.w());
        nyqVar.e(cveVar.aa());
        nyqVar.e(cveVar.r());
        nyqVar.j(3);
        nyqVar.h(cveVar.u());
        nyqVar.b(cveVar.e());
        nyqVar.d(cveVar.d());
        nyqVar.a(cveVar.s());
        if (cveVar.f() == 1) {
            List<String> ac = cveVar.ac();
            if (bkz.e(ac)) {
                LogUtil.h("GetDeviceListUtils", "setDeviceInfo, uuidList is empty");
                return;
            }
            cuw c = jfu.c(ac.get(0), false);
            if (c == null) {
                LogUtil.h("GetDeviceListUtils", "deviceInfoNew is null");
                return;
            } else {
                nyqVar.a(c.f());
                nyqVar.c(c.h());
                nyqVar.b(c.v());
            }
        } else {
            nyqVar.a(e(cveVar.n()));
            nyqVar.c(e(cveVar.o()));
            if (!cpa.f()) {
                k = cveVar.c();
                if (TextUtils.isEmpty(k)) {
                    k = cveVar.k();
                }
            } else {
                k = cveVar.k();
            }
            nyqVar.d(k);
        }
        this.b.add(nyqVar);
    }

    public List<nym> a() {
        return this.e;
    }

    public List<nyq> b() {
        return this.b;
    }
}
