package com.huawei.health.ecologydevice.manager;

import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.EventBusApi;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.MassageGunConfig;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.cev;
import defpackage.cez;
import defpackage.cos;
import defpackage.cpp;
import defpackage.cpt;
import defpackage.dbt;
import defpackage.dch;
import defpackage.dcq;
import defpackage.dcr;
import defpackage.dcs;
import defpackage.dcu;
import defpackage.dcz;
import defpackage.ddh;
import defpackage.ddo;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HEXUtils;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class ResourceManager {
    private static final Map<String, dcz> d = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    private ResourceFileListener f2302a;
    private dch b;
    private final Object c;
    private ArrayList<String> e;

    static class b {
        private static final ResourceManager d = new ResourceManager();
    }

    private ResourceManager() {
        this.c = new Object();
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager getInstance()");
        this.b = new dch();
    }

    public static ResourceManager e() {
        return b.d;
    }

    public void c() {
        m();
        g();
    }

    public void i() {
        if (b() || d()) {
            LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager updateResource is isToUpdate");
            j();
        }
    }

    public void h() {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime");
        String b2 = deviceCloudSharePreferencesManager.b("currentAppVersion");
        if ("chinaVersion".equals(b2) || "overseaVersion".equals(b2)) {
            String str = Utils.o() ? "overseaVersion" : "chinaVersion";
            if (b2.equals(str)) {
                return;
            }
            LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager updateGroupList version changed");
            deviceCloudSharePreferencesManager.a("currentAppVersion", str);
            this.b.e().clear();
            this.b.e();
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager updateGroupList first enter");
        if (!Utils.o()) {
            deviceCloudSharePreferencesManager.a("currentAppVersion", "chinaVersion");
        } else {
            deviceCloudSharePreferencesManager.a("currentAppVersion", "overseaVersion");
        }
    }

    public void e(ResourceFileListener resourceFileListener) {
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager =====registerResultCallback");
        synchronized (this.c) {
            this.f2302a = resourceFileListener;
        }
    }

    public void e(int i, String str) {
        synchronized (this.c) {
            ResourceFileListener resourceFileListener = this.f2302a;
            if (resourceFileListener != null) {
                resourceFileListener.onResult(i, str);
            } else {
                LogUtil.h("EcologyDevice_ResourceManager", "ResourceManager sendResultCallBack resultCallBack is null");
            }
        }
    }

    public void f() {
        synchronized (this.c) {
            this.f2302a = null;
        }
    }

    public dch a() {
        return (dch) cpt.d(this.b);
    }

    public String c(String str) {
        return dcq.b().e(str);
    }

    public dcz d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("EcologyDevice_ResourceManager", "ResourceManager getProductInfo paramProductId is empty");
            return null;
        }
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
        LogUtil.c("EcologyDevice_ResourceManager", "ResourceManager getProductInfo ID:", normalize);
        dcz b2 = b(normalize);
        return b2 == null ? f(normalize) : b2;
    }

    public dcz b(String str) {
        Map<String, dcz> map = d;
        if (map.size() > 0 && map.get(str) != null) {
            return map.get(str);
        }
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager getProductInfoFromProductFile");
        dcz b2 = dcs.b(dcq.b().b(str));
        if (b2 != null && b2.e().size() > 0) {
            String c = CommonUtil.c(dcq.b().a(b2.t(), b2.e().get(0).e()));
            if (c == null) {
                LogUtil.h("EcologyDevice_ResourceManager", "ResourceManager getProductInfoFromProductFile path is not exists ID:", str);
                return b2;
            }
            if (!new File(c).exists()) {
                LogUtil.h("EcologyDevice_ResourceManager", "ResourceManager getProductInfoFromProductFile file is not exists ID:", str);
                return b2;
            }
            map.put(str, b2);
            LogUtil.a("EcologyDevice_ResourceManager", "cache from product file, ID: ", str);
        }
        return b2;
    }

    private dcz f(String str) {
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager getProductInfoFromBondedDevice");
        HealthDevice bondedDevice = ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).getBondedDevice(str);
        MeasurableDevice measurableDevice = bondedDevice instanceof MeasurableDevice ? (MeasurableDevice) bondedDevice : null;
        if (measurableDevice == null) {
            LogUtil.h("EcologyDevice_ResourceManager", "ResourceManager getProductInfoFromBondedDevice the device is null! ID:", str);
            return null;
        }
        if (!"0".equals(measurableDevice.getMeasureKitUuid()) && !str.contains(Constants.LINK)) {
            return d("ic_heartrate_devices", str, measurableDevice.getDeviceName(), measurableDevice.getAddress(), measurableDevice);
        }
        if (!"0".equals(measurableDevice.getMeasureKitUuid()) && str.contains(Constants.LINK)) {
            return d("", str, "", "", measurableDevice);
        }
        LogUtil.h("EcologyDevice_ResourceManager", "ResourceManager getProductInfoFromBondedDevice the productInfo is null! ID:", str);
        return null;
    }

    private dcz d(String str, String str2, String str3, String str4, MeasurableDevice measurableDevice) {
        dcz dczVar = new dcz();
        dczVar.n(str2);
        dczVar.a(str, str3, str4);
        dczVar.b("1");
        cev.b bVar = new cev.b();
        bVar.a(1);
        bVar.c(10L, TimeUnit.SECONDS);
        dczVar.b(bVar.c());
        dczVar.g(measurableDevice.getMeasureKitUuid());
        dczVar.b(measurableDevice.getKind());
        if (!"".equals(measurableDevice.getDeviceName())) {
            d.put(str2, dczVar);
            LogUtil.a("EcologyDevice_ResourceManager", "cache from device db, productId: ", str2);
        }
        return dczVar;
    }

    public void m() {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime");
        if (deviceCloudSharePreferencesManager.e("userchange") == Utils.o()) {
            this.b.c().clear();
            if (!Utils.o()) {
                deviceCloudSharePreferencesManager.b("userchange", true);
            } else {
                deviceCloudSharePreferencesManager.b("userchange", false);
            }
        }
    }

    public boolean b() {
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager isToUpdate");
        return ddh.c().b();
    }

    public boolean d() {
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager isFirstToDownload");
        return ddh.c().e();
    }

    public boolean a(String str) {
        return ddh.c().a(str);
    }

    public boolean g(String str) {
        return ddh.c().f(str);
    }

    public void j() {
        String a2;
        final String str;
        LogUtil.a("EcologyDevice_ResourceManager", "DeviceCloudUtil toDownloadSingleXmlFile()");
        if (!Utils.o()) {
            LogUtil.a("EcologyDevice_ResourceManager", "toDownloadSingleXmlFile to download groups.xml");
            a2 = dbt.a("devicekindgroups_new");
            str = cos.b + "groups.xml";
        } else {
            LogUtil.a("EcologyDevice_ResourceManager", "toDownloadSingleXmlFile to download groups_abroad.xml");
            if (CommonUtil.cg()) {
                a2 = dbt.a("devicekindgroups_new");
                str = cos.b + "groups.xml";
            } else if (!TextUtils.equals(n(), "true")) {
                LogUtil.a("EcologyDevice_ResourceManager", "toDownloadSingleXmlFile user disagree download resoure");
                ((EventBusApi) Services.c("PluginDevice", EventBusApi.class)).publishDeviceDownMsg();
                return;
            } else {
                a2 = dbt.a("devicekindgroupsabroad_new");
                str = cos.b + "groups_abroad.xml";
            }
        }
        d(GRSManager.a(BaseApplication.getContext()).getUrl("getLatestVersion"), a2, new ResourceFileListener() { // from class: ddi
            @Override // com.huawei.health.device.manager.ResourceFileListener
            public final void onResult(int i, String str2) {
                ResourceManager.this.b(str, i, str2);
            }
        });
    }

    public /* synthetic */ void b(String str, int i, String str2) {
        if (i == 200) {
            LogUtil.a("EcologyDevice_ResourceManager", "DeviceCloudUtil toDownloadSingleXmlFile() resCode =", Integer.valueOf(i));
            d(dbt.b(str2), str);
        }
    }

    public void e(ArrayList<dcu> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h("EcologyDevice_ResourceManager", "toDownloadZipFilesFromKind fileInfo is empty");
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<dcu> it = arrayList.iterator();
        while (it.hasNext()) {
            dcu next = it.next();
            LogUtil.a("EcologyDevice_ResourceManager", "download id:", next.d());
            arrayList2.add(next.d());
        }
        int size = arrayList2.size() % 20;
        int size2 = arrayList2.size() / 20;
        if (size > 0) {
            size2++;
        }
        for (int i = 1; i <= size2; i++) {
            ArrayList<String> arrayList3 = new ArrayList<>();
            if (arrayList2.size() % 20 > 0 && i == size2) {
                int i2 = (i - 1) * 20;
                arrayList3.addAll(arrayList2.subList(i2, i2 + size));
            } else {
                arrayList3.addAll(arrayList2.subList((i - 1) * 20, i * 20));
            }
            d(arrayList3);
        }
    }

    public void d(ArrayList<String> arrayList) {
        if (koq.b(arrayList)) {
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "DeviceCloudUtil toDownloadZipFile fileIds size is ", Integer.valueOf(arrayList.size()));
        d(GRSManager.a(BaseApplication.getContext()).getUrl("getLatestVersion"), dbt.c(arrayList), new ResourceFileListener() { // from class: ddb
            @Override // com.huawei.health.device.manager.ResourceFileListener
            public final void onResult(int i, String str) {
                ResourceManager.this.b(i, str);
            }
        });
    }

    public /* synthetic */ void b(int i, String str) {
        List<ddo> c;
        if (i == 200 && (c = dbt.c(str)) != null) {
            LogUtil.a("EcologyDevice_ResourceManager", "DeviceCloudUtil toDownloadZipFile resultObjects is not null");
            b(c, false);
        }
    }

    public void c(ArrayList<String> arrayList, final boolean z) {
        LogUtil.a("EcologyDevice_ResourceManager", "toDownResZipGetSize download resource enter");
        if (!koq.b(arrayList)) {
            this.e = arrayList;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "toDownResZipGetSize mFileIds.size > 0");
        if (Utils.o() && !TextUtils.equals(n(), "true")) {
            LogUtil.a("EcologyDevice_ResourceManager", "toDownResZipGetSize download resource");
            ((EventBusApi) Services.c("PluginDevice", EventBusApi.class)).publishSingleDeviceDownMsg();
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "toDownResZipGetSize china network");
        if (!koq.b(this.e)) {
            LogUtil.a("EcologyDevice_ResourceManager", "toDownResZipGetSize fileIds size is ", Integer.valueOf(this.e.size()));
            d(GRSManager.a(BaseApplication.getContext()).getUrl("getLatestVersion"), dbt.c(this.e), new ResourceFileListener() { // from class: ddc
                @Override // com.huawei.health.device.manager.ResourceFileListener
                public final void onResult(int i, String str) {
                    ResourceManager.this.c(z, i, str);
                }
            });
        }
        LogUtil.a("EcologyDevice_ResourceManager", "toDownResZipGetSize end");
    }

    public /* synthetic */ void c(boolean z, int i, String str) {
        List<ddo> c;
        this.e.clear();
        if (i != 200 || (c = dbt.c(str)) == null) {
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "toDownResZipGetSize is not null");
        b(c, z);
    }

    public void c(ArrayList<String> arrayList) {
        if (!koq.b(arrayList)) {
            this.e = arrayList;
        }
        if (Utils.o() && !TextUtils.equals(n(), "true")) {
            LogUtil.a("EcologyDevice_ResourceManager", "toDownResZip user disagree download resource");
            ((EventBusApi) Services.c("PluginDevice", EventBusApi.class)).publishSingleDeviceDownMsg();
        } else {
            if (koq.b(this.e)) {
                return;
            }
            LogUtil.a("EcologyDevice_ResourceManager", "DeviceCloudUtil toDownResZip fileIds size is ", Integer.valueOf(this.e.size()));
            d(GRSManager.a(BaseApplication.getContext()).getUrl("getLatestVersion"), dbt.c(this.e), new ResourceFileListener() { // from class: dda
                @Override // com.huawei.health.device.manager.ResourceFileListener
                public final void onResult(int i, String str) {
                    ResourceManager.this.c(i, str);
                }
            });
        }
    }

    public /* synthetic */ void c(int i, String str) {
        List<ddo> c;
        this.e.clear();
        if (i != 200 || (c = dbt.c(str)) == null) {
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "DeviceCloudUtil toDownResZip is not null");
        b(c, false);
    }

    private String n() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "oversea_user_agreed");
        LogUtil.a("EcologyDevice_ResourceManager", "getUserAgreeStatus onclick userAgreed :", b2);
        return b2;
    }

    private void m(String str) {
        if (str == null) {
            return;
        }
        int lastIndexOf = str.lastIndexOf("_new");
        String substring = (lastIndexOf == -1 || str.length() < lastIndexOf) ? "" : str.substring(0, lastIndexOf);
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager setProductsCatch productID ", substring);
        String b2 = dcq.b().b(substring);
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager setProductsCatch filePath ", b2);
        dcz b3 = dcs.b(b2);
        if (b3 != null) {
            Map<String, dcz> map = d;
            map.remove(b3.t());
            map.put(b3.t(), b3);
        }
    }

    private void b(List<ddo> list, final boolean z) {
        if (list == null) {
            return;
        }
        AtomicInteger atomicInteger = new AtomicInteger(1);
        LogUtil.a("EcologyDevice_ResourceManager", "DeviceCloudUtil toDownloadFileList is not null");
        for (final ddo ddoVar : list) {
            cpp.b(new PriorityRunnable(atomicInteger.getAndIncrement()) { // from class: com.huawei.health.ecologydevice.manager.ResourceManager.3
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("EcologyDevice_ResourceManager", "FileManager toDownloadFileList the file is not exists");
                    String str = cos.f11394a + ddoVar.e() + ".zip";
                    if (!z) {
                        ResourceManager.this.b(str, ddoVar);
                        return;
                    }
                    double e2 = ResourceManager.e(ddoVar.d());
                    LogUtil.a("EcologyDevice_ResourceManager", "File size ", Double.valueOf(e2));
                    if (e2 <= 0.0d) {
                        return;
                    }
                    ResourceManager.this.e(300, String.valueOf(e2));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, final ddo ddoVar) {
        final String e2 = ddoVar.e();
        ddh.c().e(this.b, ddoVar.d(), str, new ResourceFileListener() { // from class: ddd
            @Override // com.huawei.health.device.manager.ResourceFileListener
            public final void onResult(int i, String str2) {
                ResourceManager.this.e(e2, ddoVar, i, str2);
            }
        });
    }

    public /* synthetic */ void e(String str, ddo ddoVar, int i, String str2) {
        if (i != 200000) {
            e(-1, str);
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager downLoadResourceFiled resultCode ", Integer.valueOf(i));
        String str3 = cos.f11394a + str + ".zip";
        String str4 = cos.e;
        if (!TextUtils.isEmpty(str) && str.contains("6d5416d9-2167-41df-ab10-c492e152b44f")) {
            str4 = cos.d;
        }
        if (e(str3, ddoVar)) {
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager downLoadResourceFiled resultObject.getFileId():", str);
        if (str.startsWith("6d5416d9-2167-41df-ab10-c492e152b44f")) {
            d(str3, str4, str);
        } else {
            ddh.c().a(str3, (InputStream) null, str4, new e(this));
            m(str);
        }
    }

    private void d(String str, String str2, String str3) {
        if (!new File(str2 + "6d5416d9-2167-41df-ab10-c492e152b44f" + File.separator + "libs" + File.separator + "am16.dex").exists()) {
            ddh.c().a(str, (InputStream) null, str2, new e(this));
            m(str3);
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager downLoadResourceFiled DeviceCloudConstants DEVICE_GOODIX Resource exit");
        k();
        try {
            CommonUtil.e(str, cez.d, true);
        } catch (Exception unused) {
            LogUtil.b("EcologyDevice_ResourceManager", "ResourceManager downLoadResourceFiled copy fail");
        }
    }

    private void k() {
        File file = new File(cez.b);
        if (file.exists()) {
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "initDest DeviceCloudConstants DEVICE_GOODIX Cache Path Make", Boolean.valueOf(file.mkdirs()));
    }

    private boolean e(String str, ddo ddoVar) {
        String c = this.b.c(ddoVar.e());
        String e2 = ddh.c().e(str, "SHA-256");
        if (c.equals(e2)) {
            return false;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "checkZipHashCode the download check hash is fail, hash_value=", c, " hash_value_zip=", e2);
        File file = new File(str);
        if (!file.exists()) {
            return true;
        }
        if (file.delete()) {
            LogUtil.a("EcologyDevice_ResourceManager", "checkZipHashCode the zip file hash value is wrong, so delete zhe zip file success");
            return true;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "checkZipHashCode the zip file hash value is wrong, so delete zhe zip file fail");
        return true;
    }

    public String d(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str2);
                messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
                return HEXUtils.a(messageDigest.digest());
            } catch (NoSuchAlgorithmException e2) {
                LogUtil.b("EcologyDevice_ResourceManager", "ResourceManager getHashCodeForString e=", e2.getMessage());
            }
        }
        return "";
    }

    public void e(String str, InputStream inputStream, ResourceFileListener resourceFileListener) {
        c(str, inputStream, resourceFileListener);
    }

    private void c(final String str, final InputStream inputStream, final ResourceFileListener resourceFileListener) {
        if (resourceFileListener == null) {
            LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager executorServiceRunnable resourceFileListener == null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ddf
                @Override // java.lang.Runnable
                public final void run() {
                    ResourceManager.this.a(str, inputStream, resourceFileListener);
                }
            });
        }
    }

    public /* synthetic */ void a(String str, InputStream inputStream, final ResourceFileListener resourceFileListener) {
        ddh.c().a(str, inputStream, cos.e, new ResourceFileListener() { // from class: dcy
            @Override // com.huawei.health.device.manager.ResourceFileListener
            public final void onResult(int i, String str2) {
                ResourceManager.this.b(resourceFileListener, i, str2);
            }
        });
    }

    public /* synthetic */ void b(ResourceFileListener resourceFileListener, int i, String str) {
        LogUtil.a("EcologyDevice_ResourceManager", "onResult, resultCode : ", Integer.valueOf(i), "resultValue : ", str);
        if (i != 200) {
            resourceFileListener.onResult(-1, "unzip failed");
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "resultCode : ", Integer.valueOf(i), "UNZIP file productId : ", str);
        if (!dcq.b().d(str)) {
            dcq.b().a(str);
            resourceFileListener.onResult(40004, "zip is error");
            return;
        }
        LogUtil.a("EcologyDevice_ResourceManager", "the file is right");
        i(str);
        LogUtil.a("EcologyDevice_ResourceManager", "CommonUtil.isTestThirdDeviceVersion:", Boolean.valueOf(CommonUtil.cg()));
        if (CommonUtil.cg()) {
            l(str);
        }
        resourceFileListener.onResult(200, "unzip finished");
    }

    private void i(String str) {
        dcz d2 = e().d(str);
        if (d2 == null) {
            LogUtil.a("EcologyDevice_ResourceManager", "productInfo is null");
            return;
        }
        if ("NULL".equals(d2.k()) || d2.k() == null) {
            LogUtil.h("EcologyDevice_ResourceManager", "productInfo.getMeasureEntry() is null");
        } else {
            a(str, d2);
        }
        LogUtil.a("EcologyDevice_ResourceManager", "===productInfo is not", ",===Entry:", d2.k());
        e().a().e();
        LogUtil.a("EcologyDevice_ResourceManager", "productGroups size =", Integer.valueOf(e().a().c().size()));
        e().a().b(d2.l(), "home", new dcu(d2.t(), d2.ac(), "", ""));
    }

    private void l(String str) {
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("data", 0).edit();
        edit.putString("productId", str);
        edit.apply();
    }

    private void a(String str, dcz dczVar) {
        try {
            File file = new File(cos.e + str + File.separator + str + File.separator + (dczVar.k().substring(0, dczVar.k().indexOf(":") - 3) + "zip"));
            String canonicalPath = file.getCanonicalPath();
            LogUtil.a("EcologyDevice_ResourceManager", "unZipLib jniLibs");
            int lastIndexOf = canonicalPath.lastIndexOf("/");
            if (file.exists() && lastIndexOf != -1) {
                ddh.c().a(canonicalPath, (InputStream) null, canonicalPath.substring(0, lastIndexOf), new ResourceFileListener() { // from class: dde
                    @Override // com.huawei.health.device.manager.ResourceFileListener
                    public final void onResult(int i, String str2) {
                        LogUtil.a("EcologyDevice_ResourceManager", "unZip jniLibs resultCode ", Integer.valueOf(i));
                    }
                });
            } else {
                LogUtil.a("EcologyDevice_ResourceManager", "not exists jnilibs");
            }
        } catch (IOException e2) {
            LogUtil.b("EcologyDevice_ResourceManager", "unZipLib", e2.getMessage());
        }
    }

    private static void d(final String str, final String str2, final ResourceFileListener resourceFileListener) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: ddg
            @Override // java.lang.Runnable
            public final void run() {
                ddh.c().a(str, str2, resourceFileListener);
            }
        });
    }

    private void d(ddo ddoVar, final String str) {
        if (ddoVar == null) {
            LogUtil.h("EcologyDevice_ResourceManager", "ResourceManager toDownloadGroupsFile resultObject is null");
            return;
        }
        final String d2 = ddoVar.d();
        if (TextUtils.isEmpty(d2)) {
            LogUtil.h("EcologyDevice_ResourceManager", "ResourceManager toDownloadGroupsFile url is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dcv
                @Override // java.lang.Runnable
                public final void run() {
                    ResourceManager.this.c(d2, str);
                }
            });
        }
    }

    public /* synthetic */ void c(String str, String str2) {
        ddh.c().e(this.b, str, str2, new e(this));
    }

    static class e implements ResourceFileListener {
        private WeakReference<ResourceManager> c;

        e(ResourceManager resourceManager) {
            this.c = new WeakReference<>(resourceManager);
        }

        @Override // com.huawei.health.device.manager.ResourceFileListener
        public void onResult(int i, String str) {
            ResourceManager resourceManager;
            WeakReference<ResourceManager> weakReference = this.c;
            if (weakReference == null || (resourceManager = weakReference.get()) == null) {
                return;
            }
            resourceManager.e(i, str);
        }
    }

    public void g() {
        LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager unzipForRes");
        ArrayList<dcr> e2 = this.b.e();
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime");
        if ("NoFirst".equals(deviceCloudSharePreferencesManager.b("firstUnzipRes"))) {
            LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager unzipForRes() firstUnzipRes is NoFirst");
        } else if (Utils.o()) {
            d(e2, deviceCloudSharePreferencesManager, "abroadFirstUnzipResource");
        } else {
            d(e2, deviceCloudSharePreferencesManager, "firstUnzipResource");
        }
    }

    private void d(final ArrayList<dcr> arrayList, DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager, String str) {
        if ("0".equals(deviceCloudSharePreferencesManager.b(str))) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ddj
                @Override // java.lang.Runnable
                public final void run() {
                    ResourceManager.this.a(arrayList);
                }
            });
            deviceCloudSharePreferencesManager.a(str, "NoFirst");
        }
    }

    public /* synthetic */ void a(ArrayList arrayList) {
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b(((dcr) it.next()).d());
            }
            e(200, MessageConstant.GROUP_MEDAL_TYPE);
        } catch (Exception unused) {
            LogUtil.b("EcologyDevice_ResourceManager", "ResourceManager unzip Exception");
        }
    }

    private void b(ArrayList<dcu> arrayList) {
        Iterator<dcu> it = arrayList.iterator();
        while (it.hasNext()) {
            String e2 = it.next().e();
            if (!"6d5416d9-2167-41df-ab10-c492e152b44f".equals(e2)) {
                ddh.c().a(e2, (InputStream) null, cos.e, new e(this));
            } else {
                ddh.c().a(e2, (InputStream) null, cos.d, new e(this));
            }
        }
    }

    abstract class PriorityRunnable implements Runnable, Comparable<PriorityRunnable> {
        private int mPriority;

        PriorityRunnable(int i) {
            if (i < 0) {
                throw new IllegalArgumentException();
            }
            this.mPriority = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(PriorityRunnable priorityRunnable) {
            if (priorityRunnable == null) {
                return 0;
            }
            return Integer.compare(priorityRunnable.getPriority(), getPriority());
        }

        public int getPriority() {
            return this.mPriority;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
    
        if (r2 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
    
        r2.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003b, code lost:
    
        if (r2 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static double e(java.lang.String r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L5
            return r0
        L5:
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            java.net.URLConnection r6 = r3.openConnection()     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            java.net.URLConnection r6 = com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation.openConnection(r6)     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            boolean r3 = r6 instanceof java.net.HttpURLConnection     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            if (r3 == 0) goto L1a
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            r2 = r6
        L1a:
            if (r2 == 0) goto L21
            int r6 = r2.getContentLength()     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            double r0 = (double) r6
        L21:
            if (r2 == 0) goto L40
            goto L3d
        L24:
            r6 = move-exception
            goto L41
        L26:
            r6 = move-exception
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L24
            java.lang.String r4 = "ResourceManager getDownloadFileSize IOException e="
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L24
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L24
            r4 = 1
            r3[r4] = r6     // Catch: java.lang.Throwable -> L24
            java.lang.String r6 = "EcologyDevice_ResourceManager"
            com.huawei.hwlogsmodel.LogUtil.b(r6, r3)     // Catch: java.lang.Throwable -> L24
            if (r2 == 0) goto L40
        L3d:
            r2.disconnect()
        L40:
            return r0
        L41:
            if (r2 == 0) goto L46
            r2.disconnect()
        L46:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ecologydevice.manager.ResourceManager.e(java.lang.String):double");
    }

    public void j(String str) {
        ddh.c().a(str, (InputStream) null, cos.e, new ResourceFileListener() { // from class: dcw
            @Override // com.huawei.health.device.manager.ResourceFileListener
            public final void onResult(int i, String str2) {
                LogUtil.a("EcologyDevice_ResourceManager", "ResourceManager unZipWiFiDevice resultCode=", Integer.valueOf(i), " resultValue:", str2);
            }
        });
    }

    public void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Map<String, dcz> map = d;
        if (map.containsKey(str)) {
            LogUtil.c("EcologyDevice_ResourceManager", "removeCacheMapByProductId productId = ", str);
            map.remove(str);
        }
    }

    public void TL_(Handler handler, MassageGunConfig.MassageGunCallback massageGunCallback) {
        new MassageGunConfig().TG_(handler, massageGunCallback);
    }
}
