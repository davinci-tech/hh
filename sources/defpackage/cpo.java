package defpackage;

import android.content.ContentValues;
import android.text.TextUtils;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.dep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class cpo {

    /* renamed from: a, reason: collision with root package name */
    private long f11404a = 0;
    private Map<String, a> d = new HashMap(1);

    public cpo() {
        a();
    }

    public void e(String str, String str2, String str3) {
        if (cpa.ae(str)) {
            LogUtil.h("HealthDeviceIntellLifeUtils", "uploadDeviceToCloud do not add huawei or honour scale");
            return;
        }
        dep b = b(str, str2, str3);
        if (b != null) {
            dew.a(b);
        }
    }

    public String c(String str, String str2) {
        if (cpa.ae(str)) {
            LogUtil.h("HealthDeviceIntellLifeUtils", "uploadDeviceToCloud do not add huawei or honour scale");
            return "";
        }
        return dew.d(str2);
    }

    public Map<String, String> b(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        if (list.size() != list2.size()) {
            return Collections.EMPTY_MAP;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!cpa.ae(list.get(i))) {
                LogUtil.h("HealthDeviceIntellLifeUtils", "uploadDeviceToCloud do not add huawei or honour scale");
                arrayList.add(list2.get(i));
            }
        }
        return dew.c(arrayList);
    }

    public void b() {
        if (System.currentTimeMillis() - this.f11404a < OpAnalyticsConstants.H5_LOADING_DELAY) {
            LogUtil.h("HealthDeviceIntellLifeUtils", "uploadDevicesToCloud too fast");
            return;
        }
        this.f11404a = System.currentTimeMillis();
        LogUtil.a("HealthDeviceIntellLifeUtils", " uploadDevicesToCloud start");
        HashMap hashMap = new HashMap(1);
        Iterator<ContentValues> it = ceo.d().f().iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            dep b = b(next.getAsString("productId"), next.getAsString("uniqueId"), next.getAsString("sn"));
            if (b != null) {
                hashMap.put(b.c().getId(), b);
            }
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator<a> it2 = this.d.values().iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().b);
        }
        arrayList.addAll(d());
        dew.b(hashMap, arrayList);
    }

    private List<String> d() {
        return Arrays.asList("007B", "N001");
    }

    public void e(String str) {
        HealthDevice c = cjx.e().c(str);
        if (c == null) {
            LogUtil.h("HealthDeviceIntellLifeUtils", " uploadDeviceToCloud device not exist");
        } else {
            dew.e(c.getAddress(), "local");
        }
    }

    private void a() {
        for (ProductMapInfo productMapInfo : ProductMap.d()) {
            String h = productMapInfo.h();
            if (!TextUtils.isEmpty(h)) {
                this.d.put(h, new a(productMapInfo.f(), productMapInfo.a(), productMapInfo.e(), productMapInfo.d()));
            }
        }
        c();
    }

    private void c() {
        this.d.put("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", new a("007B", "HAG-B19", "025", "001"));
        this.d.put("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", new a("M00F", "HAG-B19", "025", "001"));
        this.d.put("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", new a("N001", "AH111", "025", "002"));
        this.d.put("e835d102-af95-48a6-ae13-2983bc06f5c0", new a("M00D", "HEM-B19", "025", "001"));
        this.d.put("c943c933-442e-4c34-bcd0-66597f24aaed", new a("M0CJ", "DOBBY-B19", "A2B", "001"));
    }

    private static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthDeviceIntellLifeUtils", "can not substring an empty source");
            return "";
        }
        String replaceAll = str.replaceAll(":", "");
        int length = replaceAll.length();
        if (length > 3) {
            replaceAll = replaceAll.substring(length - 3, length);
        }
        return Constants.LINK + replaceAll.toUpperCase();
    }

    private dep b(String str, String str2, String str3) {
        String str4;
        dep.e eVar = new dep.e();
        a aVar = this.d.get(str);
        LogUtil.c("HealthDeviceIntellLifeUtils", " getCloudValidInformation deviceModeInfo productId:", str, " uniqueId:", cpw.d(str2), " sn:", cpw.d(str3));
        if (aVar != null) {
            LogUtil.a("HealthDeviceIntellLifeUtils", " getCloudValidInformation deviceModeInfo exist");
            eVar.a(aVar.b());
            eVar.e(aVar.a());
            eVar.g(aVar.c());
            eVar.f(aVar.e());
            if (cpa.ad(str)) {
                LogUtil.a("HealthDeviceIntellLifeUtils", "getCloudValidInformation from huawei or honour weight devcie");
                b(str, str2, eVar);
                return eVar.c();
            }
            HealthDevice c = cjx.e().c(str2);
            if (c != null) {
                LogUtil.a("HealthDeviceIntellLifeUtils", " getCloudValidInformation device exist");
                str4 = TextUtils.isEmpty(c.getAddress()) ? str2 : c.getAddress();
                eVar.b(str4);
                eVar.c(str4);
                eVar.j(str3);
                if (c.getUniqueId() != null) {
                    str2 = c.getUniqueId();
                }
                eVar.h(str2);
            } else {
                str4 = "";
            }
            dcz d = ResourceManager.e().d(str);
            if (d != null) {
                LogUtil.a("HealthDeviceIntellLifeUtils", " getCloudValidInformation productInfo exist");
                String d2 = dcx.d(str, d.n().b());
                if (d2 != null && !"".equals(d2) && c != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(d2);
                    if (TextUtils.isEmpty(str3)) {
                        str3 = str4;
                    }
                    sb.append(c(str3));
                    String sb2 = sb.toString();
                    eVar.i(sb2);
                    LogUtil.a("HealthDeviceIntellLifeUtils", " getCloudValidInformation device productId id: ", str, " name: ", sb2);
                }
            }
            eVar.d("1.0");
            eVar.d(4);
            eVar.l("local");
            eVar.a(0);
            eVar.b(System.currentTimeMillis());
            return eVar.c();
        }
        return eVar.c();
    }

    private void b(String str, String str2, dep.e eVar) {
        if (eVar == null) {
            return;
        }
        HealthDevice c = cjx.e().c(str2);
        if (c == null || TextUtils.isEmpty(c.getAddress()) || TextUtils.isEmpty(c.getUniqueId())) {
            LogUtil.h("HealthDeviceIntellLifeUtils", "buildHuaweiAndHonourWeightDevice device or device identify is null");
            return;
        }
        String l = cpa.l(str2);
        if (TextUtils.isEmpty(l)) {
            LogUtil.h("HealthDeviceIntellLifeUtils", "can not get serialNumber from sp");
            l = c.getAddress();
        }
        if (TextUtils.isEmpty(l)) {
            LogUtil.h("HealthDeviceIntellLifeUtils", "buildHuaweiAndHonourWeightDevice deviceid is null");
            return;
        }
        eVar.b(l);
        eVar.c(c.getAddress());
        eVar.h(l);
        dcz d = ResourceManager.e().d(str);
        if (d != null) {
            LogUtil.a("HealthDeviceIntellLifeUtils", " buildHuaweiAndHonourWeightDevice productInfo exist");
            String d2 = dcx.d(str, d.n().b());
            if (d2 != null && !"".equals(d2)) {
                String str3 = d2 + c(l);
                eVar.i(str3);
                LogUtil.a("HealthDeviceIntellLifeUtils", " buildHuaweiAndHonourWeightDevice device productId id: ", str, " name: ", str3);
            }
        }
        eVar.d("1.0");
        if (c instanceof ctk) {
            eVar.d(1);
            eVar.l("cloud");
        } else {
            eVar.d(4);
            eVar.l("local");
        }
        eVar.a(0);
        eVar.b(System.currentTimeMillis());
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f11405a;
        private String b;
        private String d;
        private String e;

        public a(String str, String str2, String str3, String str4) {
            this.d = str2;
            this.b = str;
            this.f11405a = str3;
            this.e = str4;
        }

        public String e() {
            return this.f11405a;
        }

        public String a() {
            return this.d;
        }

        public String b() {
            return this.e;
        }

        public String c() {
            return this.b;
        }

        public String toString() {
            return "DeviceModeInfo{mDeviceModel='" + this.d + ", mProdId='" + this.b + ", mDeviceType='" + this.f11405a + ", mDeviceManu='" + this.e + '}';
        }
    }
}
