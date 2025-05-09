package com.huawei.ui.device.utlis.clouddevice;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import defpackage.cvc;
import defpackage.cve;
import defpackage.cvj;
import defpackage.cvk;
import defpackage.jfv;
import defpackage.jnw;
import defpackage.jqf;
import defpackage.koq;
import defpackage.msa;
import defpackage.ntt;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class DownloadCloudDeviceResource implements DownloadResultCallBack {
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private DeviceResourceCallback f9304a;
    private boolean c = false;
    private List<DeviceProfile> d;
    private DeviceResourceCallback e;
    private ExecutorService f;

    public interface DeviceResourceCallback {
        void deviceResourceCallbackResult(boolean z);

        void deviceResourceCallbackResultDeviceList(List<CloudDeviceInfo> list);
    }

    public void c() {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() || Utils.o()) {
            return;
        }
        jfv.b(-1, "default", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        synchronized (b) {
            DeviceResourceCallback deviceResourceCallback = this.f9304a;
            if (deviceResourceCallback != null) {
                deviceResourceCallback.deviceResourceCallbackResult(z);
            }
        }
    }

    public void d(DeviceResourceCallback deviceResourceCallback) {
        synchronized (b) {
            if (deviceResourceCallback == null) {
                return;
            }
            this.f9304a = deviceResourceCallback;
            if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() || Utils.o() || CommonUtil.bu()) {
                ReleaseLogUtil.d("R_DownloadCloudDeviceResource", "isStoreDemoVersion:", Boolean.valueOf(CommonUtil.bu()));
                a(false);
            } else {
                a();
            }
        }
    }

    private void a() {
        final AtomicBoolean d = jqf.d();
        ReleaseLogUtil.e("R_DownloadCloudDeviceResource", "getCloudDeviceList start isRemoveDevice: ", Boolean.valueOf(d.get()));
        jnw.e().d("DownloadCloudDeviceResource", new IBaseResponseCallback() { // from class: com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && koq.e(obj, DeviceProfile.class)) {
                    List list = (List) obj;
                    LogUtil.a("DownloadCloudDeviceResource", "deviceCloudProfiles:", Integer.valueOf(list.size()));
                    DownloadCloudDeviceResource.this.e((List<DeviceProfile>) list, new DeviceResourceCallback() { // from class: com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.5.2
                        @Override // com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.DeviceResourceCallback
                        public void deviceResourceCallbackResultDeviceList(List<CloudDeviceInfo> list2) {
                            jqf.a(d);
                            if (!CommonUtil.bv() && d.get()) {
                                ReleaseLogUtil.e("R_DownloadCloudDeviceResource", "getCloudDeviceList configDeviceProfiles hasRemove");
                            } else {
                                if (list2 == null || list2.size() <= 0) {
                                    return;
                                }
                                jfv.a(list2);
                                DownloadCloudDeviceResource.this.a(true);
                            }
                        }

                        @Override // com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.DeviceResourceCallback
                        public void deviceResourceCallbackResult(boolean z) {
                            LogUtil.c("DownloadCloudDeviceResource", "deviceResourceCallbackResult");
                        }
                    });
                } else {
                    jqf.a(d);
                    LogUtil.h("DownloadCloudDeviceResource", "getCloudDeviceList errorCode:", Integer.valueOf(i));
                }
            }
        });
    }

    public void d() {
        ExecutorService executorService = this.f;
        if (executorService != null) {
            if (!executorService.isShutdown()) {
                this.f.shutdown();
            }
            this.f = null;
        }
        synchronized (b) {
            this.f9304a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<DeviceProfile> list, DeviceResourceCallback deviceResourceCallback) {
        String str;
        this.e = deviceResourceCallback;
        if (this.c || list.size() == 0) {
            this.e.deviceResourceCallbackResultDeviceList(null);
            return;
        }
        ArrayList arrayList = new ArrayList(0);
        for (DeviceProfile deviceProfile : list) {
            Map<String, Object> profile = deviceProfile.getProfile();
            CloudDeviceInfo cloudDeviceInfo = new CloudDeviceInfo();
            try {
                if (!e(profile, cloudDeviceInfo)) {
                    if (!(profile.get("sn") instanceof String)) {
                        str = "";
                    } else {
                        str = (String) profile.get("sn");
                        LogUtil.a("DownloadCloudDeviceResource", "serial string:", CommonUtil.l(str));
                    }
                    final String id = deviceProfile.getId();
                    LogUtil.a("DownloadCloudDeviceResource", "deviceProfileId string:", CommonUtil.l(id));
                    if (!TextUtils.isEmpty(id) && (id.contains("ANDROID21") || str.contains("ANDROID21"))) {
                        FutureTask<Object> futureTask = new FutureTask<>(new Callable<Object>() { // from class: com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.3
                            @Override // java.util.concurrent.Callable
                            public Object call() throws Exception {
                                return DownloadCloudDeviceResource.this.h(id);
                            }
                        });
                        try {
                            ExecutorService executorService = this.f;
                            if (executorService == null || executorService.isShutdown()) {
                                this.f = Executors.newCachedThreadPool();
                            }
                            this.f.execute(futureTask);
                        } catch (RejectedExecutionException unused) {
                            LogUtil.b("DownloadCloudDeviceResource", "configDeviceProfiles RejectedExecutionException!");
                        }
                        c(cloudDeviceInfo, id, futureTask, arrayList);
                    }
                }
            } catch (ClassCastException unused2) {
                LogUtil.b("DownloadCloudDeviceResource", "ClassCastException");
            } catch (NumberFormatException unused3) {
                LogUtil.b("DownloadCloudDeviceResource", "NumberFormatException");
            }
        }
        d(arrayList);
    }

    private boolean e(Map<String, Object> map, CloudDeviceInfo cloudDeviceInfo) {
        String str;
        String str2 = map.get("devType") instanceof String ? (String) map.get("devType") : null;
        if (str2 == null) {
            LogUtil.h("DownloadCloudDeviceResource", "cloudDevice type is null");
            return true;
        }
        LogUtil.h("DownloadCloudDeviceResource", "configDeviceProfiles type:", str2);
        if (!str2.equals("06E") && !str2.equals("06D") && !str2.equals("A12")) {
            LogUtil.h("DownloadCloudDeviceResource", "cloudDevice type not is watch or band");
            return true;
        }
        if (map.get("prodId") instanceof String) {
            str = (String) map.get("prodId");
            if ("004Q".equals(str) || "004U".equals(str)) {
                LogUtil.h("DownloadCloudDeviceResource", "leo is not support");
                return true;
            }
        } else {
            str = "";
        }
        LogUtil.h("DownloadCloudDeviceResource", "smartProductId:", str);
        cloudDeviceInfo.setHiLinkDeviceId(str);
        if (!TextUtils.isEmpty(str)) {
            return b(map, cloudDeviceInfo);
        }
        LogUtil.h("DownloadCloudDeviceResource", "smartProductId or subMac or deviceName or deviceUdid is null.");
        return true;
    }

    private boolean b(Map<String, Object> map, CloudDeviceInfo cloudDeviceInfo) {
        String str;
        String str2;
        String str3;
        String str4 = "";
        if (map.get("deviceName") instanceof String) {
            str = (String) map.get("deviceName");
            LogUtil.a("DownloadCloudDeviceResource", "deviceName:", str);
        } else {
            str = "";
        }
        cloudDeviceInfo.setDeviceName(str);
        if (map.get("mac") instanceof String) {
            str2 = (String) map.get("mac");
            LogUtil.a("DownloadCloudDeviceResource", "subMac:", str2);
        } else {
            str2 = "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = c(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            LogUtil.a("DownloadCloudDeviceResource", "end subMac:", str2);
            cloudDeviceInfo.setDeviceIdentify(str2);
        }
        if (map.get("udid") instanceof String) {
            str3 = (String) map.get("udid");
            LogUtil.a("DownloadCloudDeviceResource", "device id string:", CommonUtil.l(str3));
        } else {
            str3 = "";
        }
        cloudDeviceInfo.setSmartDeviceUdid(str3);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            LogUtil.h("DownloadCloudDeviceResource", "deviceName or deviceUdid is null.");
            return true;
        }
        if (map.get("model") instanceof String) {
            str4 = (String) map.get("model");
            LogUtil.a("DownloadCloudDeviceResource", "device model:", str4);
        }
        cloudDeviceInfo.setDeviceModel(str4);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String h(String str) {
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        List<ServiceProfile> servicesOfDevice = clientAgent.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        String str2 = "";
        if (servicesOfDevice == null) {
            return "";
        }
        Iterator<ServiceProfile> it = servicesOfDevice.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            LogUtil.a("DownloadCloudDeviceResource", "ServiceProfile serviceId:", id);
            if (!TextUtils.isEmpty(id) && w9.h.equals(id)) {
                ServiceCharacteristicProfile serviceCharacteristics = clientAgent.getServiceCharacteristics(str, id, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
                if (serviceCharacteristics == null) {
                    LogUtil.h("DownloadCloudDeviceResource", "characteristicProfile is null");
                } else {
                    Iterator<Map.Entry<String, Object>> it2 = serviceCharacteristics.getProfile().entrySet().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Map.Entry<String, Object> next = it2.next();
                            String key = next.getKey();
                            String obj = next.getValue().toString();
                            LogUtil.a("DownloadCloudDeviceResource", "profileMap character key:", key, "characterValue:", obj);
                            if ("timestamp".equals(key)) {
                                str2 = obj;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return str2;
    }

    private void d(List<CloudDeviceInfo> list) {
        if (list.size() > 0) {
            List<cve> deviceList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList();
            if (deviceList != null && deviceList.size() > 0) {
                LogUtil.a("DownloadCloudDeviceResource", "getAllDeviceList have data");
                a(list);
                return;
            } else {
                LogUtil.a("DownloadCloudDeviceResource", "getAllDeviceList null");
                this.c = true;
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).addDownloadIndexAllCallBack(this);
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexAll();
                return;
            }
        }
        LogUtil.h("DownloadCloudDeviceResource", "mDeviceInfoList size = 0");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo r6, java.lang.String r7, java.util.concurrent.FutureTask<java.lang.Object> r8, java.util.List<com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo> r9) {
        /*
            r5 = this;
            java.lang.String r0 = "DownloadCloudDeviceResource"
            r1 = 1
            r2 = 0
            java.lang.Object r8 = r8.get()     // Catch: java.util.concurrent.ExecutionException -> L21 java.lang.InterruptedException -> L2b
            boolean r3 = r8 instanceof java.lang.String     // Catch: java.util.concurrent.ExecutionException -> L21 java.lang.InterruptedException -> L2b
            if (r3 == 0) goto L34
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.util.concurrent.ExecutionException -> L21 java.lang.InterruptedException -> L2b
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.util.concurrent.ExecutionException -> L1d java.lang.InterruptedException -> L1f
            java.lang.String r3 = "timestamp"
            r4 = 0
            r2[r4] = r3     // Catch: java.util.concurrent.ExecutionException -> L1d java.lang.InterruptedException -> L1f
            r2[r1] = r8     // Catch: java.util.concurrent.ExecutionException -> L1d java.lang.InterruptedException -> L1f
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)     // Catch: java.util.concurrent.ExecutionException -> L1d java.lang.InterruptedException -> L1f
            r2 = r8
            goto L34
        L1d:
            r2 = r8
            goto L21
        L1f:
            r2 = r8
            goto L2b
        L21:
            java.lang.String r8 = "futureTask ExecutionException!"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)
            goto L34
        L2b:
            java.lang.String r8 = "futureTask InterruptedException!"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)
        L34:
            boolean r8 = android.text.TextUtils.isEmpty(r2)
            if (r8 != 0) goto L41
            long r2 = health.compact.a.CommonUtil.g(r2)
            r6.setLastConnectedTime(r2)
        L41:
            r6.setDeviceProfileId(r7)
            r6.setIsCloudDevice(r1)
            r9.add(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.c(com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo, java.lang.String, java.util.concurrent.FutureTask, java.util.List):void");
    }

    private String c(String str) {
        int length = str.length();
        String substring = length > 3 ? str.substring(length - 3, length) : null;
        LogUtil.a("DownloadCloudDeviceResource", "getSubMac：", substring);
        return substring;
    }

    private void a(List<CloudDeviceInfo> list) {
        ArrayList arrayList = new ArrayList(0);
        for (CloudDeviceInfo cloudDeviceInfo : list) {
            Iterator<cve> it = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceInfoByBluetooth(cloudDeviceInfo.getDeviceName()).iterator();
            while (it.hasNext()) {
                a(it.next(), arrayList);
            }
            LogUtil.a("DownloadCloudDeviceResource", "configDownloadDeviceUuid deviceInfo getDeviceName:", CommonUtil.l(cloudDeviceInfo.getDeviceName()));
        }
        if (arrayList.size() > 0) {
            LogUtil.a("DownloadCloudDeviceResource", "uuidList.size():", Integer.valueOf(arrayList.size()));
            a(arrayList, list);
        } else {
            c(list);
            LogUtil.a("DownloadCloudDeviceResource", "uuidList size is 0");
        }
    }

    private void a(cve cveVar, List<String> list) {
        if (cveVar == null || cveVar.ac().size() <= 0) {
            return;
        }
        LogUtil.a("DownloadCloudDeviceResource", "configDownloadDeviceUuid pluginInfoBean.getUuidList().size():", Integer.valueOf(cveVar.ac().size()));
        for (int i = 0; i < cveVar.ac().size(); i++) {
            String str = cveVar.ac().get(i);
            if (!((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(str) && !list.contains(str)) {
                list.add(str);
            }
        }
    }

    private void a(List<String> list, final List<CloudDeviceInfo> list2) {
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(list, new DownloadDeviceInfoCallBack() { // from class: com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.1
            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onSuccess() {
                LogUtil.a("DownloadCloudDeviceResource", "onSuccess");
                DownloadCloudDeviceResource.this.c((List<CloudDeviceInfo>) list2);
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onFailure(int i) {
                DownloadCloudDeviceResource.this.e.deviceResourceCallbackResultDeviceList(null);
                LogUtil.a("DownloadCloudDeviceResource", "onFailure");
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void netWorkError() {
                DownloadCloudDeviceResource.this.e.deviceResourceCallbackResultDeviceList(null);
                LogUtil.a("DownloadCloudDeviceResource", "netWorkError");
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onDownload(int i) {
                LogUtil.c("DownloadCloudDeviceResource", "progressBar:", Integer.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final List<CloudDeviceInfo> list) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.2
            @Override // java.lang.Runnable
            public void run() {
                DownloadCloudDeviceResource.this.b((List<CloudDeviceInfo>) list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<CloudDeviceInfo> list) {
        ArrayList arrayList = new ArrayList(0);
        for (CloudDeviceInfo cloudDeviceInfo : list) {
            int b2 = b(cloudDeviceInfo.getHiLinkDeviceId());
            LogUtil.a("DownloadCloudDeviceResource", "getDeviceType:", Integer.valueOf(b2), "deviceName:", CommonUtil.l(cloudDeviceInfo.getDeviceName()));
            if (b2 != -1) {
                cloudDeviceInfo.setProductType(b2);
                arrayList.add(cloudDeviceInfo);
            }
        }
        LogUtil.a("DownloadCloudDeviceResource", "deviceList:", Integer.valueOf(arrayList.size()));
        this.e.deviceResourceCallbackResultDeviceList(arrayList);
    }

    @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
    public void setDownloadStatus(int i, int i2) {
        this.c = false;
        if (i != 0) {
            LogUtil.h("DownloadCloudDeviceResource", "setDownloadStatus:", Integer.valueOf(i));
        }
        if (i == 1) {
            a(false);
            a();
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        } else if (i == -1 || i == -2) {
            this.e.deviceResourceCallbackResultDeviceList(null);
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        String str2;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        jnw.e().d("DownloadCloudDeviceResource", new IBaseResponseCallback() { // from class: com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && koq.e(obj, DeviceProfile.class)) {
                    DownloadCloudDeviceResource.this.d = (List) obj;
                } else {
                    LogUtil.h("DownloadCloudDeviceResource", "getDeviceProfileId errorCode:", Integer.valueOf(i));
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(3000L, TimeUnit.MILLISECONDS);
            List<DeviceProfile> list = this.d;
            if (list == null) {
                LogUtil.h("DownloadCloudDeviceResource", "getDeviceProfileId deviceProfiles is null");
                return "";
            }
            for (DeviceProfile deviceProfile : list) {
                String id = deviceProfile.getId();
                Map<String, Object> profile = deviceProfile.getProfile();
                if (profile.get("sn") instanceof String) {
                    str2 = (String) profile.get("sn");
                    LogUtil.a("DownloadCloudDeviceResource", "getDeviceProfileId serial string:", CommonUtil.l(str2));
                } else {
                    str2 = null;
                }
                if (str.equals(id) || (!TextUtils.isEmpty(str2) && str.equals(str2))) {
                    return id;
                }
            }
            return str;
        } catch (InterruptedException unused) {
            LogUtil.b("DownloadCloudDeviceResource", "getDeviceProfileId InterruptedException");
            return "";
        }
    }

    public boolean a(String str, boolean z) {
        FutureTask futureTask = new FutureTask(d(str, z));
        boolean z2 = false;
        try {
            ExecutorService executorService = this.f;
            if (executorService == null || executorService.isShutdown()) {
                this.f = Executors.newCachedThreadPool();
            }
            this.f.execute(futureTask);
            try {
                Object obj = futureTask.get();
                if (!(obj instanceof Boolean)) {
                    return false;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                try {
                    LogUtil.a("DownloadCloudDeviceResource", "isSupportPush", Boolean.valueOf(booleanValue));
                    return booleanValue;
                } catch (InterruptedException unused) {
                    z2 = booleanValue;
                    LogUtil.b("DownloadCloudDeviceResource", "futureTask InterruptedException!");
                    return z2;
                } catch (ExecutionException unused2) {
                    z2 = booleanValue;
                    LogUtil.b("DownloadCloudDeviceResource", "futureTask ExecutionException!");
                    return z2;
                }
            } catch (InterruptedException unused3) {
            } catch (ExecutionException unused4) {
            }
        } catch (RejectedExecutionException unused5) {
            LogUtil.b("DownloadCloudDeviceResource", "getDeviceCapDeviceIsSupport RejectedExecutionException!");
            return false;
        }
    }

    private Callable<Object> d(final String str, final boolean z) {
        return new Callable<Object>() { // from class: com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource.8
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                List<ServiceProfile> servicesOfDevice;
                ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
                String a2 = DownloadCloudDeviceResource.this.a(str);
                if (TextUtils.isEmpty(a2) || (servicesOfDevice = clientAgent.getServicesOfDevice(a2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN))) == null) {
                    return false;
                }
                Iterator<ServiceProfile> it = servicesOfDevice.iterator();
                while (it.hasNext()) {
                    String id = it.next().getId();
                    LogUtil.a("DownloadCloudDeviceResource", "ServiceProfile serviceId:", id);
                    if (!TextUtils.isEmpty(id) && ("WEARisSupportMultiLink".equals(id) || "WEARisSupportSingleLink".equals(id))) {
                        ServiceCharacteristicProfile serviceCharacteristics = clientAgent.getServiceCharacteristics(a2, id, z, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
                        if (serviceCharacteristics == null) {
                            LogUtil.h("DownloadCloudDeviceResource", "getDeviceCapDeviceIsSupport characteristicProfile is null");
                        } else {
                            for (Map.Entry<String, Object> entry : serviceCharacteristics.getProfile().entrySet()) {
                                String key = entry.getKey();
                                String obj = entry.getValue().toString();
                                LogUtil.a("DownloadCloudDeviceResource", "profileMap character key:", key, "characterValue:", obj);
                                if ("ability".equals(key) && CommonUtil.h(obj) == 1) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                return false;
            }
        };
    }

    private int b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DownloadCloudDeviceResource", "getDeviceInfoBySubMac() subMac or smartProductId is isEmpty");
            return -1;
        }
        LogUtil.a("DownloadCloudDeviceResource", "getDeviceInfoBySubMac smartProductId is ", str);
        int d = d(str);
        if (d != -1) {
            LogUtil.a("DownloadCloudDeviceResource", "getDeviceInfoBySubMac localDeviceType is ", Integer.valueOf(d));
            return d;
        }
        cvc e = e(str);
        if (e == null) {
            return -1;
        }
        LogUtil.a("DownloadCloudDeviceResource", "getDeviceInfoBySubMac() ezPluginInfo not null");
        return ntt.a(e);
    }

    private int d(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("004Q", 10);
        hashMap.put("004U", 10);
        hashMap.put("004W", 8);
        hashMap.put("0050", 14);
        hashMap.put("0051", 15);
        hashMap.put("004Z", 7);
        hashMap.put("0054", 13);
        hashMap.put("0056", 12);
        if (hashMap.containsKey(str)) {
            return ((Integer) hashMap.get(str)).intValue();
        }
        return -1;
    }

    private cvc e(String str) {
        List<msa> d = EzPluginManager.a().d();
        if (d == null || d.isEmpty()) {
            LogUtil.h("DownloadCloudDeviceResource", "bindWearDevice hiLinkDeviceList is null or hiLinkDeviceList is empty");
            return g(str);
        }
        Iterator<msa> it = d.iterator();
        cvc cvcVar = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String b2 = it.next().b();
            if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(b2) && (cvcVar = b(b2, str)) != null) {
                LogUtil.a("DownloadCloudDeviceResource", "bindWearDevice ezPluginInfo not null");
                break;
            }
        }
        return cvcVar != null ? cvcVar : g(str);
    }

    private cvc g(String str) {
        List<cvk> indexList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexList();
        cvc cvcVar = null;
        if (indexList == null || indexList.isEmpty()) {
            LogUtil.h("DownloadCloudDeviceResource", "bindWearDevice hiLinkDeviceList is null or hiLinkDeviceList is empty");
            return null;
        }
        Iterator<cvk> it = indexList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String e = it.next().e();
            if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(e) && (cvcVar = b(e, str)) != null) {
                LogUtil.a("DownloadCloudDeviceResource", "bindWearDevice ezPluginInfo not null");
                break;
            }
        }
        return cvcVar;
    }

    private cvc b(String str, String str2) {
        LogUtil.a("DownloadCloudDeviceResource", "gePluginInfo product is ", str, " productId is ", str2);
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
        if (pluginInfoByUuid == null) {
            LogUtil.h("DownloadCloudDeviceResource", "gePluginInfo pluginInfo is null");
            return null;
        }
        cvj f = pluginInfoByUuid.f();
        if (f == null) {
            LogUtil.h("DownloadCloudDeviceResource", "gePluginInfo pluginInfoForWear is empty");
            return null;
        }
        if (!TextUtils.isEmpty(f.b())) {
            LogUtil.c("DownloadCloudDeviceResource", "gePluginInfo AiTipsProduct is", f.b());
            try {
            } catch (JSONException unused) {
                LogUtil.b("DownloadCloudDeviceResource", "getPluginInfo JSONException");
            }
            if (new JSONObject(f.b()).has("ai_tips_product_" + str2)) {
                return pluginInfoByUuid;
            }
            return null;
        }
        LogUtil.h("DownloadCloudDeviceResource", "gePluginInfo AiTipsProduct is empty");
        return null;
    }
}
