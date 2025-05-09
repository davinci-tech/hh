package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hms.framework.common.PackageUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.dep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes5.dex */
public class lbd {
    private ArrayList<ProductMapInfo> c;
    private Context e;

    private lbd(Context context) {
        this.e = context;
    }

    public static lbd d(Context context) {
        return new lbd(context);
    }

    public void b() {
        ArrayList<ProductMapInfo> d = ProductMap.d();
        this.c = d;
        if (d.isEmpty()) {
            LogUtil.a("SportBiReportController", "sport products is empty, start load product map.");
            ThreadPoolManager.d().execute(new Runnable() { // from class: lbd.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!jbw.d(lbd.this.e, 2)) {
                        LogUtil.a("SportBiReportController", "updateProductMapConfig failed");
                        return;
                    }
                    lbd.this.c = ProductMap.d();
                    LogUtil.a("SportBiReportController", "load product map success. sport product size is ", Integer.valueOf(lbd.this.c.size()));
                }
            });
        } else {
            LogUtil.a("SportBiReportController", "get product map success. sport product size is ", Integer.valueOf(this.c.size()));
        }
    }

    public void b(DeviceInformation deviceInformation, String str) {
        if (deviceInformation == null) {
            LogUtil.a("SportBiReportController", "device information is null in reportDeviceInfo method.");
            return;
        }
        ProductMapInfo e = e(deviceInformation.getModelString(), deviceInformation.getDeviceType());
        if (e != null) {
            a(e, str);
        } else {
            a(d(), str);
        }
    }

    public void e(String str) {
        dew.b(str, 0);
    }

    private ProductMapInfo d() {
        return new ProductMapInfo("08F", "2BYB", "41e57179-c0c1-49d1-9ea6-c317689ffda8", "A5H", "252", null, 309);
    }

    public ProductMapInfo e(String str, int i) {
        ArrayList<ProductMapInfo> arrayList = this.c;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        String d = lbv.d(i);
        ArrayList arrayList2 = new ArrayList();
        String upperCase = TextUtils.isEmpty(str) ? "" : str.toUpperCase(Locale.ENGLISH);
        Iterator<ProductMapInfo> it = this.c.iterator();
        while (it.hasNext()) {
            ProductMapInfo next = it.next();
            if (!TextUtils.isEmpty(next.a()) && next.a().toUpperCase(Locale.ENGLISH).equals(upperCase) && next.e().equals(d)) {
                arrayList2.add(next);
            }
        }
        LogUtil.a("SportBiReportController", "target sport product size :", Integer.valueOf(arrayList2.size()));
        if (arrayList2.isEmpty()) {
            return null;
        }
        return (ProductMapInfo) arrayList2.get(0);
    }

    private void a(ProductMapInfo productMapInfo, String str) {
        LogUtil.h("SportBiReportController", "uploadDeviceToCloud enter");
        if (cpa.ae(productMapInfo.f())) {
            LogUtil.h("SportBiReportController", "uploadDeviceToCloud do not add huawei or honour scale");
            return;
        }
        dep d = d(productMapInfo, str, str);
        if (d != null) {
            dew.e(d, 1);
        } else {
            LogUtil.h("SportBiReportController", "uploadDeviceToCloud CloudValidInformation is null");
        }
    }

    private dep d(ProductMapInfo productMapInfo, String str, String str2) {
        String a2 = productMapInfo.a();
        dep.e eVar = new dep.e();
        LogUtil.a("SportBiReportController", " getCloudValidInformation deviceModeInfo exist");
        eVar.a(productMapInfo.d());
        eVar.e(a2);
        String f = productMapInfo.f();
        eVar.g(f);
        eVar.f(productMapInfo.e());
        if (!TextUtils.isEmpty(str2)) {
            eVar.b(str2);
            eVar.c(a(str2));
            eVar.h(str);
        }
        dcz d = ResourceManager.e().d(f);
        if (d != null) {
            LogUtil.a("SportBiReportController", " getCloudValidInformation productInfo exist");
            String d2 = dcx.d(f, d.n().b());
            if (!TextUtils.isEmpty(d2)) {
                eVar.i(d2 + Constants.LINK + a(str2));
                LogUtil.a("SportBiReportController", " getCloudValidInformation device resource");
            }
        }
        eVar.d("1.0");
        eVar.d(4);
        eVar.l("local");
        eVar.a(0);
        String versionName = PackageUtils.getVersionName(this.e);
        eVar.m(versionName);
        LogUtil.c("SportBiReportController", "app version:", versionName);
        eVar.b(System.currentTimeMillis());
        return eVar.c();
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SportBiReportController", "can not substring an empty mac");
            return "";
        }
        String replaceAll = str.replaceAll(":", "");
        int length = replaceAll.length();
        if (length > 3) {
            return replaceAll.substring(length - 3, length);
        }
        return null;
    }
}
