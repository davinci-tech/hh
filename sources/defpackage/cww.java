package defpackage;

import android.content.ContentValues;
import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.ecologydevice.clouddevice.CloudSwatchDeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class cww {
    private static final Object c = new Object();

    public static boolean d() {
        boolean z = System.currentTimeMillis() - CommonUtil.g(SharedPreferenceManager.b(BaseApplication.getContext(), "cloudSwatchDevice", "cloud_swatch_device_last_update_time")) > 600000;
        LogUtil.a("SwatchDeviceUtils", "isNeedUpdateCloudSwatchDevice isNeedUpdate = ", Boolean.valueOf(z));
        return z;
    }

    public static void a() {
        LogUtil.a("SwatchDeviceUtils", "saveSwatchUpdateTime");
        SharedPreferenceManager.e(BaseApplication.getContext(), "cloudSwatchDevice", "cloud_swatch_device_last_update_time", Long.toString(System.currentTimeMillis()), new StorageParams());
    }

    private static List<CloudSwatchDeviceInfo> e() {
        synchronized (c) {
            ArrayList arrayList = new ArrayList(0);
            if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() && !Utils.o()) {
                Map<String, String> a2 = SharedPreferenceManager.a(BaseApplication.getContext(), c());
                if (a2 == null) {
                    return arrayList;
                }
                Iterator<Map.Entry<String, String>> it = a2.entrySet().iterator();
                while (it.hasNext()) {
                    try {
                        CloudSwatchDeviceInfo cloudSwatchDeviceInfo = (CloudSwatchDeviceInfo) new Gson().fromJson(it.next().getValue(), CloudSwatchDeviceInfo.class);
                        if (cloudSwatchDeviceInfo != null && !arrayList.contains(cloudSwatchDeviceInfo)) {
                            LogUtil.a("SwatchDeviceUtils", "getStorageCloudDevices deviceSn:", CommonUtil.l(cloudSwatchDeviceInfo.getDeviceSn()));
                            arrayList.add(cloudSwatchDeviceInfo);
                        }
                    } catch (JsonSyntaxException unused) {
                        LogUtil.b("SwatchDeviceUtils", "getStorageCloudDevices JsonSyntaxException");
                    }
                }
                LogUtil.a("SwatchDeviceUtils", "getStorageCloudDevices size:", Integer.valueOf(arrayList.size()));
                return arrayList;
            }
            return arrayList;
        }
    }

    private static String c() {
        return LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_cloudSwatchDevice";
    }

    public static void b(List<CloudSwatchDeviceInfo> list) {
        synchronized (c) {
            LogUtil.a("SwatchDeviceUtils", "Enter updateStorageDevices.");
            SharedPreferenceManager.j(BaseApplication.getContext(), c());
            if (koq.b(list)) {
                LogUtil.a("SwatchDeviceUtils", "updateStorageDevices cloudDeviceList is empty");
                return;
            }
            for (CloudSwatchDeviceInfo cloudSwatchDeviceInfo : list) {
                String l = CommonUtil.l(cloudSwatchDeviceInfo.getDeviceSn());
                LogUtil.a("SwatchDeviceUtils", "updateStorageDevices deviceSn: ", l);
                try {
                    SharedPreferenceManager.e(BaseApplication.getContext(), c(), l, new Gson().toJson(cloudSwatchDeviceInfo), new StorageParams());
                } catch (JsonSyntaxException unused) {
                    LogUtil.b("SwatchDeviceUtils", "updateStorageDevices JsonSyntaxException");
                }
            }
        }
    }

    public static ArrayList<cjv> b() {
        dcz d;
        LogUtil.a("SwatchDeviceUtils", "getCloudDevices");
        ArrayList<cjv> arrayList = new ArrayList<>(16);
        for (CloudSwatchDeviceInfo cloudSwatchDeviceInfo : e()) {
            if (!TextUtils.isEmpty(cloudSwatchDeviceInfo.getProductId()) && (d = ResourceManager.e().d(cloudSwatchDeviceInfo.getProductId())) != null && !d.n().d().trim().isEmpty()) {
                ContentValues contentValues = new ContentValues(16);
                contentValues.put("sn", cloudSwatchDeviceInfo.getDeviceSn());
                contentValues.put("deviceVersion", cloudSwatchDeviceInfo.getSoftVersion());
                contentValues.put("deviceManufacturer", cloudSwatchDeviceInfo.getManufacture());
                contentValues.put("deviceModel", cloudSwatchDeviceInfo.getDeviceModel());
                cjv cjvVar = new cjv();
                cjvVar.FU_(contentValues);
                cjvVar.c(d);
                cjvVar.a(3);
                arrayList.add(cjvVar);
            }
        }
        return arrayList;
    }

    public static Intent QP_(cjv cjvVar) {
        LogUtil.a("SwatchDeviceUtils", "getJumpH5Intent onItemClick swatch");
        Intent intent = new Intent();
        Object i = cjvVar.i();
        if (i instanceof dcz) {
            dcz dczVar = (dcz) i;
            String t = dczVar.t();
            StringBuilder d = d(d(new StringBuilder(1024), dcq.b().c(t), 5000), "#/tissot?", 5000);
            ContentValues FT_ = cjvVar.FT_();
            StringBuilder d2 = d(d(d, "deviceId=", 5000), FT_.getAsString("sn"), 5000);
            StringBuilder d3 = d(d(d2, "&deviceVersion=", 5000), FT_.getAsString("deviceVersion"), 5000);
            StringBuilder d4 = d(d(d3, "&deviceManufacturer=", 5000), FT_.getAsString("deviceManufacturer"), 5000);
            StringBuilder d5 = d(d(d4, "&deviceModel=", 5000), FT_.getAsString("deviceModel"), 5000);
            intent.setPackage(BaseApplication.getContext().getPackageName());
            intent.setClassName(BaseApplication.getContext().getPackageName(), "com.huawei.operation.activity.WebViewActivity");
            intent.putExtra("url", d5.toString());
            intent.putExtra("productId", t);
            if (dczVar.n() != null) {
                intent.putExtra("name", dcx.d(t, dczVar.n().b()));
            }
            if (dczVar.l() != null) {
                intent.putExtra("deviceType", dczVar.l().name());
            }
        }
        return intent;
    }

    private static StringBuilder d(StringBuilder sb, String str, int i) {
        if (sb != null && str != null && sb.length() + str.length() < i) {
            sb.append(str);
        }
        return sb;
    }
}
