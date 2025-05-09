package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.device.interactors.CompatibilityInteractor;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class oae {

    /* renamed from: a, reason: collision with root package name */
    private static oae f15577a;
    private static final Object c = new Object();
    private static jfq d;
    private Context b;
    private cwl e = new cwl();

    private oae(Context context) {
        this.b = context;
    }

    public static oae c(Context context) {
        oae oaeVar;
        synchronized (c) {
            if (f15577a == null) {
                f15577a = new oae(BaseApplication.getContext());
            }
            d = jfq.c();
            jiu.c();
            jgh.d(BaseApplication.getContext());
            jfy.b();
            jis.b();
            oaeVar = f15577a;
        }
        return oaeVar;
    }

    public DeviceInfo a() {
        return jpt.a("DeviceInteractors");
    }

    public List<DeviceInfo> c() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, "DeviceInteractors");
        if (deviceList == null || deviceList.size() == 0) {
            return deviceList;
        }
        ArrayList arrayList = new ArrayList(10);
        for (DeviceInfo deviceInfo : deviceList) {
            if ("main_relationship".equals(deviceInfo.getRelationship()) || cvt.c(deviceInfo.getProductType())) {
                arrayList.add(deviceInfo);
            }
        }
        return arrayList;
    }

    public List<DeviceInfo> j() {
        return cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DeviceInteractors");
    }

    public void e(List<String> list, boolean z) {
        try {
            d.c(list, z);
        } catch (RemoteException e) {
            LogUtil.b("DeviceInteractors", "unPair RemoteException = ", e.getMessage());
            jez.d(BaseApplication.getContext(), null);
        }
    }

    public boolean h() {
        try {
            return d.b();
        } catch (RemoteException e) {
            LogUtil.b("DeviceInteractors", "isPrompt RemoteException = ", e.getMessage());
            jez.a(BaseApplication.getContext());
            return true;
        }
    }

    public boolean g() {
        try {
            return d.a();
        } catch (RemoteException unused) {
            LogUtil.b("DeviceInteractors", "isOutgoingCall RemoteException");
            jez.a(BaseApplication.getContext());
            return false;
        }
    }

    public void c(List<DeviceInfo> list) {
        try {
            LogUtil.a("DeviceInteractors", "migrateUsedDeviceList = ");
            d.b(list);
        } catch (RemoteException e) {
            LogUtil.b("DeviceInteractors", "RemoteException = ", e.getMessage());
            jez.d(BaseApplication.getContext(), null);
        }
    }

    public static int d(int i) {
        int d2 = jfu.c(i).d();
        return (d2 != -1 || cup.a().get(Integer.valueOf(i)) == null) ? d2 : cup.a().get(Integer.valueOf(i)).intValue();
    }

    public static int c(String str) {
        return jfu.c(str);
    }

    public void d(DeviceParameter deviceParameter, String str, IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback) {
        try {
            jez.d(iAddDeviceStateAIDLCallback);
            d.b(deviceParameter, str, iAddDeviceStateAIDLCallback);
        } catch (RemoteException e) {
            LogUtil.b("DeviceInteractors", "RemoteException = ", e.getMessage());
            jez.d(BaseApplication.getContext(), null);
            if (iAddDeviceStateAIDLCallback != null) {
                try {
                    iAddDeviceStateAIDLCallback.onAddDeviceState(4);
                } catch (RemoteException e2) {
                    LogUtil.b("DeviceInteractors", " callback RemoteException = ", e2.getMessage());
                }
            }
        }
    }

    public String e() {
        DeviceInfo a2 = a();
        if (a2 == null) {
            LogUtil.a("DeviceInteractors", "getCurrentDeviceName() getCurrentDeviceInfo() == null");
            return "";
        }
        return a2.getDeviceName();
    }

    public List<String> b() {
        List<DeviceInfo> j = j();
        ArrayList arrayList = new ArrayList(16);
        if (j == null) {
            LogUtil.a("DeviceInteractors", "getCurrentUsedDeviceNameList() getCurrentDeviceInfo() == null");
            return arrayList;
        }
        LogUtil.a("DeviceInteractors", "deviceInfo.size() =", Integer.valueOf(j.size()));
        for (DeviceInfo deviceInfo : j) {
            LogUtil.a("DeviceInteractors", "getCurrentUsedDeviceNameList() name = ", deviceInfo.getDeviceName());
            arrayList.add(deviceInfo.getDeviceName());
        }
        return arrayList;
    }

    public int d() {
        DeviceInfo a2 = a();
        if (a2 == null) {
            LogUtil.a("DeviceInteractors", "getCurrentDeviceConnectState() getCurrentDeviceInfo() is null");
            return 0;
        }
        return a2.getDeviceConnectState();
    }

    public int e(String str) {
        DeviceInfo e = jpt.e(str, "DeviceInteractors");
        if (e == null) {
            LogUtil.a("DeviceInteractors", "getCurrentDeviceConnectState() getCurrentDeviceInfo() is null");
            return 0;
        }
        return e.getDeviceConnectState();
    }

    public String b(int i) {
        LogUtil.a("DeviceInteractors", "transDeviceProductTypeIntToStr: deviceProductType = ", Integer.valueOf(i));
        if (jpu.d("DeviceInteractors") != null && (i == 23 || i == 24)) {
            String f = jfu.c(i).f();
            LogUtil.a("DeviceInteractors", "transDeviceProductTypeIntToStr: mDeviceProductType = ", f);
            return f;
        }
        DeviceInfo a2 = a();
        if (a2 == null) {
            return jfu.c(i).f();
        }
        String str = "PORSCHE DESIGN";
        if ((TextUtils.isEmpty(a2.getDeviceName()) || !TextUtils.equals(a2.getDeviceName(), "PORSCHE DESIGN")) && (TextUtils.isEmpty(a2.getDeviceModel()) || !TextUtils.equals(a2.getDeviceModel(), "PORSCHE DESIGN"))) {
            str = jfu.c(i).f();
        }
        LogUtil.a("DeviceInteractors", "transDeviceProductTypeIntToStr: mDeviceProductType = ", str);
        return str;
    }

    public String c(int i) {
        LogUtil.a("DeviceInteractors", "transDeviceProductTypeIntToStrReadyDevice: deviceProductType ", Integer.valueOf(i));
        if (jpu.d("DeviceInteractors") != null && cvt.c(i)) {
            String f = jfu.c(i).f();
            LogUtil.a("DeviceInteractors", "mDeviceProductType  ", f);
            return f;
        }
        String f2 = jfu.c(i).f();
        LogUtil.a("DeviceInteractors", "mDeviceProductType ", f2);
        return f2;
    }

    public DeviceInfo f() {
        LogUtil.a("DeviceInteractors", "Enter initNpsRequestDeviceInfo !");
        if (d == null) {
            return null;
        }
        DeviceInfo a2 = jpt.a("DeviceInteractors");
        if (a2 == null) {
            a2 = jpu.d("DeviceInteractors");
        }
        if (a2 == null) {
            a2 = HwNpsManager.getInstance().getFollowedDeviceInfo();
        }
        if (a2 != null) {
            jcx.i(a2.getSoftVersion());
            jcx.f(a2.getDeviceModel());
            jcx.g(a2.getCertModel());
            HwNpsManager.getInstance().setNpsIdentify(a2);
            b(a2);
            jdx.b(new Runnable() { // from class: oae.1
                @Override // java.lang.Runnable
                public void run() {
                    jcx.d(GRSManager.a(oae.this.b).getCommonCountryCode());
                }
            });
            ReleaseLogUtil.d("Nps_DeviceInteractors", "init wear nps, delete weight nps");
            jcx.k("");
        }
        LogUtil.a("DeviceInteractors", "Leave initNpsRequestDeviceInfo !");
        return a2;
    }

    private void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        int productType = deviceInfo.getProductType();
        String c2 = jpp.c(deviceInfo);
        LogUtil.a("DeviceInteractors", "initNpsRequestDeviceInfo WearName : ", c2);
        jcx.j(c2);
        if (productType > 37 && productType != 54 && productType != 263) {
            jcx.j(deviceInfo.getDeviceModel());
            return;
        }
        if (productType == 18) {
            if ("CRIUS-B69".toUpperCase(Locale.ENGLISH).equals(deviceInfo.getDeviceModel())) {
                jcx.j(deviceInfo.getDeviceModel());
                return;
            }
            return;
        }
        if (productType != 19) {
            switch (productType) {
                case 34:
                case 35:
                    jcx.j(deviceInfo.getDeviceModel());
                    break;
                case 36:
                    String deviceModel = deviceInfo.getDeviceModel();
                    if (deviceModel != null && deviceModel.toUpperCase(Locale.ENGLISH).contains("B49")) {
                        jcx.j(deviceInfo.getDeviceModel());
                    }
                    LogUtil.a("DeviceInteractors", "setWearName:", 36, ", deviceModel:", deviceModel, ", wearName:", jcx.f());
                    break;
                default:
                    LogUtil.c("DeviceInteractors", "setWearName default branch.");
                    break;
            }
            return;
        }
        if ("TERRA-B69".toUpperCase(Locale.ENGLISH).equals(deviceInfo.getDeviceModel()) || "TERRA-B79".toUpperCase(Locale.ENGLISH).equals(deviceInfo.getDeviceModel())) {
            jcx.j(deviceInfo.getDeviceModel());
        }
    }

    public boolean a(String str) {
        LogUtil.a("DeviceInteractors", "isDeviceBinded(): ", str);
        if (str == null) {
            return false;
        }
        int i = 0;
        boolean z = false;
        while (i < str.length()) {
            try {
                int i2 = i + 2;
                String substring = str.substring(i, i2);
                String substring2 = str.substring(i2, (Integer.parseInt(substring, 16) * 2) + i + 2);
                i += substring.length() + (Integer.parseInt(substring, 16) * 2);
                if (h(substring2)) {
                    return true;
                }
                if (substring2.startsWith("FF7D02") && substring2.length() == 16) {
                    try {
                        for (cwd cwdVar : this.e.a(substring2.substring(substring2.length() - 10, substring2.length())).e()) {
                            if (jds.c(cwdVar.e(), 16) == 1) {
                                if (cwdVar.c().endsWith("FFFF")) {
                                    LogUtil.a("DeviceInteractors", "true");
                                    z = true;
                                }
                            } else {
                                LogUtil.b("DeviceInteractors", "isDeviceBinded() unknow TLV.tag() ");
                            }
                        }
                    } catch (cwg e) {
                        LogUtil.b("DeviceInteractors", "getResult() COMMAND_ID_MUNE_SET = ", e.getMessage());
                    }
                }
            } catch (IndexOutOfBoundsException unused) {
                LogUtil.b("DeviceInteractors", "isDeviceBinded IndexOutOfBoundsException");
            }
        }
        return z;
    }

    public String b(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        while (i < str.length()) {
            try {
                int i2 = i + 2;
                String substring = str.substring(i, i2);
                String substring2 = str.substring(i2, (CommonUtil.a(substring, 16) * 2) + i + 2);
                i += substring.length() + (CommonUtil.a(substring, 16) * 2);
                LogUtil.a("DeviceInteractors", "indexString:", substring, "position:", Integer.valueOf(i));
                if ("16".equals(substring2.substring(0, 2)) && substring2.length() > 6) {
                    String c2 = c(substring2, false);
                    String c3 = c(substring2, true);
                    LogUtil.a("DeviceInteractors", "getDeviceProductId productId:", c2, " subModelId: ", c3);
                    if (!TextUtils.isEmpty(c2) && !TextUtils.isEmpty(c3)) {
                        return c2 + Constants.LINK + c3;
                    }
                    if (TextUtils.isEmpty(c2)) {
                        return null;
                    }
                    return c2;
                }
            } catch (IndexOutOfBoundsException unused) {
                LogUtil.b("DeviceInteractors", "parseDeviceProductId IndexOutOfBoundsException");
            }
        }
        return null;
    }

    private String c(String str, boolean z) {
        LogUtil.a("DeviceInteractors", "valueString:", str);
        String substring = str.substring(2, 6);
        LogUtil.a("DeviceInteractors", "service:", substring);
        if (!"EEFD".equals(substring)) {
            return null;
        }
        String substring2 = str.substring(6, str.length());
        if (substring2.length() < 6) {
            return null;
        }
        int i = 0;
        String substring3 = substring2.substring(0, 6);
        LogUtil.a("DeviceInteractors", "autoHeadString:", substring3);
        if (!"010101".equals(substring3)) {
            return null;
        }
        String substring4 = substring2.substring(6, substring2.length());
        LogUtil.a("DeviceInteractors", "typeValueString:", substring4);
        while (i < substring4.length()) {
            String substring5 = substring4.substring(i, i + 2);
            int a2 = CommonUtil.a(substring5, 16);
            LogUtil.a("DeviceInteractors", "position:", Integer.valueOf(i), "indexString:", substring5, "typeValue:", Integer.valueOf(a2));
            int i2 = 4;
            if (a2 != 5 && a2 != 10 && a2 != 19) {
                if (a2 == 3) {
                    i2 = 6;
                } else if (a2 == 18) {
                    i2 = 8;
                    if (!z) {
                        String substring6 = substring4.substring(substring5.length() + i, i + substring5.length() + 8);
                        LogUtil.a("DeviceInteractors", "productString:", substring6);
                        return cvx.e(substring6);
                    }
                } else {
                    if (z && a2 == 4) {
                        return substring4.substring(substring5.length() + i, i + substring5.length() + 2);
                    }
                    i2 = 2;
                }
            }
            i += substring5.length() + i2;
        }
        return null;
    }

    private boolean h(String str) {
        String str2;
        ApplicationInfo applicationInfo;
        if (!str.startsWith("16EEFD")) {
            LogUtil.h("DeviceInteractors", "this value is not start with type hex value");
            return false;
        }
        String substring = str.substring(6);
        if (!substring.startsWith("010101") || substring.length() < 24) {
            LogUtil.h("DeviceInteractors", "this value is not start with find hex value or length is error");
            return false;
        }
        String c2 = c(str, false);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.a("DeviceInteractors", "productId is null");
            return false;
        }
        LogUtil.a("DeviceInteractors", "productId: ", c2);
        try {
            applicationInfo = BaseApplication.getContext().getPackageManager().getApplicationInfo(BaseApplication.getContext().getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("DeviceInteractors", "getFrontParam PackageManager.NameNotFoundException");
            str2 = null;
        }
        if (applicationInfo.metaData == null) {
            LogUtil.h("DeviceInteractors", "appInfo data is null");
            return false;
        }
        str2 = applicationInfo.metaData.getString("is-support-direct-connect");
        while (c2.length() < 6) {
            c2 = "0" + c2;
        }
        LogUtil.a("DeviceInteractors", "deviceModel: ", str2);
        if (!TextUtils.isEmpty(str2) && str2.contains(c2)) {
            LogUtil.a("DeviceInteractors", "support scan is true");
            return true;
        }
        LogUtil.a("DeviceInteractors", "value string is not contains device");
        return false;
    }

    public void a(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceInteractors", "migrateWearDeviceList enter");
        if (jdx.b(new Runnable() { // from class: oae.5
            @Override // java.lang.Runnable
            public void run() {
                List<DeviceInfo> j = oae.this.j();
                if (j != null && !j.isEmpty()) {
                    LogUtil.a("DeviceInteractors", "migrateWearDeviceList health has device");
                    iBaseResponseCallback.d(100001, null);
                } else {
                    oae.this.e(new IBaseResponseCallback() { // from class: oae.5.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (i == 0 && (obj instanceof List)) {
                                oae.this.c((List<DeviceInfo>) obj);
                                iBaseResponseCallback.d(0, null);
                                return;
                            }
                            iBaseResponseCallback.d(100001, null);
                        }
                    });
                }
            }
        }) == -1) {
            iBaseResponseCallback.d(100001, null);
        }
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        dul.c().e(new CompatibilityInteractor().d(), new IBaseResponseCallback() { // from class: oae.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DeviceInteractors", "getHuaweiWearDeviceListByTypes errorCode：", Integer.valueOf(i));
                if (i == 0 && (obj instanceof List)) {
                    List list = (List) obj;
                    if (list.size() > 0) {
                        iBaseResponseCallback.d(0, list);
                        return;
                    } else {
                        iBaseResponseCallback.d(100001, null);
                        return;
                    }
                }
                iBaseResponseCallback.d(100001, null);
            }
        });
    }

    public int d(String str) {
        LogUtil.a("DeviceInteractors", "enter getAllowDisturbListItem()");
        return joi.c().a(str);
    }
}
