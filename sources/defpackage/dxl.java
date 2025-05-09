package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.userlabelmgr.model.UpdateUserLabel;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Services;
import health.compact.a.Sha256;
import health.compact.a.SharedPreferenceManager;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class dxl {
    private static dxl g;
    private UpdateUserLabel h;
    private ArrayList<cpm> k;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final dyb[] f11885a = {new dyb(0.0d, 30.0d, "SportDeviceTime_12"), new dyb(30.0d, 90.0d, "SportDeviceTime_13"), new dyb(90.0d, 180.0d, "SportDeviceTime_14"), new dyb(180.0d, 2.147483647E9d, "SportDeviceTime_15")};
    private static final dyb[] f = {new dyb(0.0d, 30.0d, "SportDeviceTime_16"), new dyb(30.0d, 90.0d, "SportDeviceTime_17"), new dyb(90.0d, 180.0d, "SportDeviceTime_18"), new dyb(180.0d, 2.147483647E9d, "SportDeviceTime_19")};
    private static final dyb[] d = {new dyb(0.0d, 30.0d, "SportDeviceTime_4"), new dyb(30.0d, 90.0d, "SportDeviceTime_5"), new dyb(90.0d, 180.0d, "SportDeviceTime_6"), new dyb(180.0d, 2.147483647E9d, "SportDeviceTime_7")};
    private static final dyb[] b = {new dyb(0.0d, 30.0d, "SportDeviceTime_8"), new dyb(30.0d, 90.0d, "SportDeviceTime_9"), new dyb(90.0d, 180.0d, "SportDeviceTime_10"), new dyb(180.0d, 2.147483647E9d, "SportDeviceTime_11")};
    private static final ArrayList<dyb[]> c = new ArrayList(16) { // from class: dxl.3
        private static final long serialVersionUID = -743974295768716726L;

        {
            add(dxl.f11885a);
            add(dxl.f);
            add(dxl.d);
            add(dxl.b);
        }
    };
    private Context j = BaseApplication.getContext();
    private String i = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);

    private dxl() {
    }

    public static dxl d() {
        dxl dxlVar;
        LogUtil.a("BiDeviceLabelHelper", "enter BiDeviceLabelHelper getInstance");
        synchronized (e) {
            if (g == null) {
                g = new dxl();
            }
            dxlVar = g;
        }
        return dxlVar;
    }

    public void f() {
        LogUtil.a("BiDeviceLabelHelper", "registerCallback");
        this.h = new UpdateUserLabel() { // from class: dxl.2
            @Override // com.huawei.health.userlabelmgr.model.UpdateUserLabel
            public void onUpdate() {
                dxl.this.g();
            }
        };
        dxw.a(this.j).b(this.h);
    }

    public void i() {
        LogUtil.a("BiDeviceLabelHelper", "enter unRegisterCallback");
        if (this.h != null) {
            dxw.a(this.j).e(this.h);
        }
    }

    public void g() {
        this.k = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getWearInfo();
        l();
        n();
        h();
        j();
    }

    private void l() {
        ArrayList<cpm> arrayList = this.k;
        if (arrayList == null || arrayList.size() == 0) {
            LogUtil.h("BiDeviceLabelHelper", "generateDeviceTypeLabel no device.");
            dxw.a(this.j).b("health_sport_device_type_smart_wear", "SportDeviceModel_0", this.i);
        } else {
            LogUtil.a("BiDeviceLabelHelper", "generateDeviceTypeLabel has device.");
            dxw.a(this.j).b("health_sport_device_type_smart_wear", "SportDeviceModel_1", this.i);
        }
    }

    private void n() {
        ArrayList<cpm> arrayList = this.k;
        if (arrayList == null) {
            LogUtil.h("BiDeviceLabelHelper", "generateDeviceTypeNumLabel mUsedDeviceLists is null.");
            return;
        }
        Iterator<cpm> it = arrayList.iterator();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (it.hasNext()) {
            int e2 = dyh.e(it.next().i());
            if (e2 == 1) {
                z = true;
            } else if (e2 == 2) {
                z3 = true;
            } else if (e2 == 3) {
                z4 = true;
            } else if (e2 != 4) {
                LogUtil.h("BiDeviceLabelHelper", "generateDeviceTypeNumLabel classification is unKnow.");
            } else {
                z2 = true;
            }
        }
        dxw.a(this.j).b("health_sport_device_up_smart_wear", a(z, z2, z3, z4), this.i);
    }

    private String a(boolean z, boolean z2, boolean z3, boolean z4) {
        StringBuilder sb = new StringBuilder(16);
        if (z3) {
            sb.append("SportDeviceType_3");
        } else {
            sb.append("SportDeviceType_2");
        }
        if (z4) {
            sb.append(",SportDeviceType_5");
        } else {
            sb.append(",SportDeviceType_4");
        }
        if (z) {
            sb.append(",SportDeviceType_7");
        } else {
            sb.append(",SportDeviceType_6");
        }
        if (z2) {
            sb.append(",SportDeviceType_9");
        } else {
            sb.append(",SportDeviceType_8");
        }
        LogUtil.a("BiDeviceLabelHelper", "getDeviceTypeNumLabelValue labelValue:", sb.toString());
        return sb.toString();
    }

    private void h() {
        if (this.k == null) {
            LogUtil.h("BiDeviceLabelHelper", "generateDeviceActiveLabel mUsedDeviceLists is null.");
        } else {
            dxw.a(this.j).b("health_sport_device_time_smart_wear", a(k()), this.i);
        }
    }

    private long[] k() {
        dxl dxlVar = this;
        Iterator<cpm> it = dxlVar.k.iterator();
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        while (it.hasNext()) {
            cpm next = it.next();
            int e2 = dyh.e(next.i());
            String a2 = dxlVar.a(next);
            LogUtil.a("BiDeviceLabelHelper", "generateDeviceActiveLabel classification:", Integer.valueOf(e2), " value:", a2);
            if (!TextUtils.isEmpty(a2)) {
                try {
                    j5 = Long.parseLong(a2);
                } catch (NumberFormatException unused) {
                    LogUtil.b("BiDeviceLabelHelper", "generateDeviceActiveLabel exception");
                }
            }
            if (e2 != 1) {
                if (e2 != 2) {
                    if (e2 != 3) {
                        if (e2 != 4) {
                            LogUtil.h("BiDeviceLabelHelper", "generateDeviceActiveLabel classification is unKnow.");
                        } else if (j5 > j2) {
                            dxlVar = this;
                            j2 = j5;
                        }
                        dxlVar = this;
                    } else if (j5 > j4) {
                        dxlVar = this;
                        j4 = j5;
                    } else {
                        dxlVar = this;
                    }
                } else if (j5 > j3) {
                    dxlVar = this;
                    j3 = j5;
                } else {
                    dxlVar = this;
                }
            } else if (j5 > j) {
                dxlVar = this;
                j = j5;
            } else {
                dxlVar = this;
            }
        }
        return new long[]{j, j2, j3, j4};
    }

    private String a(cpm cpmVar) {
        String b2 = SharedPreferenceManager.b(this.j, String.valueOf(2000000), cpmVar.a());
        if (TextUtils.isEmpty(b2)) {
            try {
                b2 = SharedPreferenceManager.b(this.j, String.valueOf(2000000), b(Sha256.e(cpmVar.a().getBytes("UTF-8"))));
            } catch (UnsupportedEncodingException e2) {
                LogUtil.b("BiDeviceLabelHelper", "getDeviceActiveTime UnsupportedEncodingException:", e2.getMessage());
            } catch (Exception unused) {
                LogUtil.b("BiDeviceLabelHelper", "getDeviceActiveTime Exception");
            }
            LogUtil.a("BiDeviceLabelHelper", "getDeviceActiveTime value:", b2);
        }
        return b2;
    }

    private static String b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(128);
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private String a(long[] jArr) {
        double[] dArr = new double[jArr.length];
        String[] strArr = new String[jArr.length];
        StringBuilder sb = new StringBuilder(16);
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < jArr.length; i++) {
            dArr[i] = 0.0d;
            strArr[i] = "";
            if (jArr[i] != 0) {
                dArr[i] = (currentTimeMillis - r10) / 8.64E7d;
            }
            if (dArr[i] != 0.0d) {
                e(i, dArr, strArr);
            }
            if (!TextUtils.isEmpty(strArr[i])) {
                sb.append(strArr[i]);
                sb.append(",");
            }
        }
        if (sb.toString().endsWith(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        LogUtil.a("BiDeviceLabelHelper", "getActiveTimeLabelValue value:", sb.toString());
        return sb.toString();
    }

    private void e(int i, double[] dArr, String[] strArr) {
        if (i >= 0) {
            ArrayList<dyb[]> arrayList = c;
            if (i >= arrayList.size()) {
                return;
            }
            for (dyb dybVar : arrayList.get(i)) {
                String matchResult = dybVar.getMatchResult(dArr[i]);
                strArr[i] = matchResult;
                if (matchResult != null) {
                    return;
                }
            }
        }
    }

    private void j() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "BiDeviceLabelHelper");
        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceName())) {
            String deviceModel = deviceInfo.getDeviceModel();
            LogUtil.a("BiDeviceLabelHelper", "generateCurrentDeviceLabel deviceName is ", deviceModel);
            dxw.a(this.j).b("health_sport_current_device_name", deviceModel, this.i);
        } else {
            LogUtil.a("BiDeviceLabelHelper", "generateCurrentDeviceLabel deviceName is empty");
            dxw.a(this.j).b("health_sport_current_device_name", "", this.i);
        }
    }
}
