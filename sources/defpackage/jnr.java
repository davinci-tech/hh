package defpackage;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jnr implements DataReceiveCallback {
    private static jnr b;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private jns f13972a = new jns();
    private IBaseResponseCallback c;
    private IBaseResponseCallback e;

    private jnr() {
    }

    public static jnr b() {
        jnr jnrVar;
        synchronized (d) {
            if (b == null) {
                LogUtil.a("HwWheelSizeManager", "getInstance");
                b = new jnr();
            }
            jnrVar = b;
        }
        return jnrVar;
    }

    public jns e() {
        return this.f13972a;
    }

    public void a() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.rimSizeSet", this);
    }

    private void j() {
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.rimSizeSet");
    }

    public void c() {
        j();
        this.c = null;
    }

    public void b(DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        this.e = iBaseResponseCallback;
        a();
        if (deviceInfo == null || !cwi.c(deviceInfo, 98) || deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwWheelSizeManager", "sendSettingDeviceCommand no send command");
            return;
        }
        boolean sendSampleConfigCommand = cuk.b().sendSampleConfigCommand(deviceInfo, b(1), "HwWheelSizeManager");
        LogUtil.a("HwWheelSizeManager", "constructSampleConfig: ", b(1));
        if (sendSampleConfigCommand) {
            return;
        }
        LogUtil.h("HwWheelSizeManager", "sendSettingDeviceCommand fail");
    }

    private String d() {
        StringBuilder sb = new StringBuilder();
        sb.append(HEXUtils.e(8) + HEXUtils.e(8) + String.format(Locale.ENGLISH, "%016x", Long.valueOf(this.f13972a.b())));
        sb.append(HEXUtils.e(9) + HEXUtils.e(2) + HEXUtils.d(this.f13972a.a()));
        sb.append(HEXUtils.e(10) + HEXUtils.e(1) + HEXUtils.e(this.f13972a.e()));
        sb.append(HEXUtils.e(11) + HEXUtils.e(1) + HEXUtils.e(this.f13972a.d()));
        LogUtil.a("HwWheelSizeManager", "constructSampleConfigInfo structStringBuilder: ", sb.toString());
        return sb.toString();
    }

    public void c(DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        this.c = iBaseResponseCallback;
        a();
        if (deviceInfo == null || !cwi.c(deviceInfo, 98) || deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwWheelSizeManager", "sendQueryDeviceCommand no send command");
        } else {
            if (cuk.b().sendSampleConfigCommand(deviceInfo, b(2), "HwWheelSizeManager")) {
                return;
            }
            LogUtil.h("HwWheelSizeManager", "sendQueryDeviceCommand fail");
        }
    }

    private cvq b(int i) {
        cvq cvqVar = new cvq();
        cvn cvnVar = new cvn();
        cvqVar.setSrcPkgName("hw.unitedevice.rimSizeSet");
        cvqVar.setWearPkgName("rimSizeSet");
        cvqVar.setCommandId(1);
        ArrayList arrayList = new ArrayList(5);
        if (i == 1) {
            cvnVar.e(900100006L);
            cvnVar.d(i);
            cvnVar.c(HEXUtils.c(d()));
            arrayList.add(cvnVar);
            cvqVar.setConfigInfoList(arrayList);
        } else if (i == 2) {
            cvnVar.e(900100006L);
            cvnVar.d(i);
            arrayList.add(cvnVar);
            cvqVar.setConfigInfoList(arrayList);
        } else {
            LogUtil.c("HwWheelSizeManager", "configAction is null");
        }
        return cvqVar;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (deviceInfo == null || d(cvrVar)) {
            LogUtil.h("HwWheelSizeManager", "device == null or isCheckSampleError");
            return;
        }
        if (cvrVar instanceof cvq) {
            LogUtil.a("HwWheelSizeManager", "onDataReceived");
            List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
            if (configInfoList == null || configInfoList.size() == 0) {
                LogUtil.h("HwWheelSizeManager", "onDataReceived sampleConfigInfoList empty");
                return;
            }
            cvn cvnVar = configInfoList.get(0);
            LogUtil.a("HwWheelSizeManager", "onDataReceived configAction", Integer.valueOf(cvnVar.e()));
            if (cvnVar.e() == 1) {
                this.e.d(i, cvnVar);
            }
            d(i, deviceInfo, configInfoList, cvnVar);
        }
    }

    private void d(int i, DeviceInfo deviceInfo, List<cvn> list, cvn cvnVar) {
        if (cvnVar.e() == 2) {
            jnt.a(deviceInfo.getDeviceIdentify(), HEXUtils.a(cvnVar.b()));
            IBaseResponseCallback iBaseResponseCallback = this.c;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i, list.get(0));
            }
            this.c = null;
        }
    }

    private boolean d(cvr cvrVar) {
        if (cvrVar == null) {
            LogUtil.a("HwWheelSizeManager", "sampleBase == null");
            return true;
        }
        if (cvrVar.getCommandId() == 1 && "rimSizeSet".equals(cvrVar.getSrcPkgName()) && "hw.unitedevice.rimSizeSet".equals(cvrVar.getWearPkgName())) {
            return false;
        }
        LogUtil.h("HwWheelSizeManager", "message: ", cvrVar.toString());
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(java.lang.String r7) {
        /*
            r6 = this;
            jqi r0 = defpackage.jqi.a()
            java.lang.String r7 = defpackage.jrh.b(r7)
            java.lang.String r7 = r0.getSwitchSettingFromDb(r7)
            java.lang.String r0 = "getValue is "
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r7}
            java.lang.String r1 = "HwWheelSizeManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            java.lang.String r2 = ""
            if (r0 == 0) goto L20
            return r2
        L20:
            java.lang.String r0 = "-1"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L43
            java.util.List r0 = defpackage.jnq.b()
            int r0 = r0.size()
            r3 = 9
            if (r0 <= r3) goto L43
            java.util.List r7 = defpackage.jnq.b()
            java.lang.Object r7 = r7.get(r3)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r7 = r6.d(r7)
            return r7
        L43:
            java.lang.String r0 = ","
            java.lang.String[] r7 = r7.split(r0)
            java.util.List r7 = java.util.Arrays.asList(r7)
            int r0 = r7.size()
            r3 = 3
            if (r0 <= r3) goto La1
            r0 = 2
            r4 = 0
            java.lang.Object r0 = r7.get(r0)     // Catch: java.lang.NumberFormatException -> L6b
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.NumberFormatException -> L6b
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L6b
            java.lang.Object r3 = r7.get(r3)     // Catch: java.lang.NumberFormatException -> L6c
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.NumberFormatException -> L6c
            int r4 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L6c
            goto L73
        L6b:
            r0 = r4
        L6c:
            java.lang.Object[] r3 = new java.lang.Object[r4]
            java.lang.String r5 = "getRimSize NumberFormatException"
            com.huawei.hwlogsmodel.LogUtil.b(r5, r3)
        L73:
            if (r0 != 0) goto L89
            java.util.List r3 = defpackage.jnq.b()
            int r3 = r3.size()
            if (r3 > r4) goto L89
            java.lang.String r7 = "getRimSize id is error."
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
            return r2
        L89:
            if (r0 != 0) goto L96
            java.util.List r7 = defpackage.jnq.b()
            java.lang.Object r7 = r7.get(r4)
            java.lang.String r7 = (java.lang.String) r7
            goto L9d
        L96:
            r2 = 1
            java.lang.Object r7 = r7.get(r2)
            java.lang.String r7 = (java.lang.String) r7
        L9d:
            java.lang.String r2 = r6.d(r0, r7)
        La1:
            java.lang.String r7 = "rim size : "
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r7)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jnr.a(java.lang.String):java.lang.String");
    }

    private String d(String str) {
        String[] split = str.split("  ");
        if (split.length <= 1) {
            return str;
        }
        return split[1].substring(0, r4.length() - 2);
    }

    private String d(int i, String str) {
        LogUtil.a("HwWheelSizeManager", "selectTag is : ", Integer.valueOf(i));
        if (i == 0) {
            str = d(str);
        }
        return (i == 1 && "3000".equals(str) && jnq.b().size() > 9) ? d(jnq.b().get(9)) : str;
    }

    public String c(DeviceInfo deviceInfo) {
        int e = b().e().e();
        int a2 = b().e().a();
        int d2 = b().e().d();
        long b2 = b().e().b();
        LogUtil.a("HwWheelSizeManager", "settingRimSize timeFromDevice = ", Long.valueOf(b2));
        String switchSettingFromDb = jqi.a().getSwitchSettingFromDb(jrh.b(deviceInfo.getDeviceIdentify()));
        if ((e == 1 && a2 == 3000) || "-1".equals(switchSettingFromDb)) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_bolt_not_set);
        }
        long j = 0;
        if (!TextUtils.isEmpty(switchSettingFromDb)) {
            try {
                j = Long.parseLong((String) Arrays.asList(switchSettingFromDb.split(",")).get(0));
            } catch (NumberFormatException unused) {
                LogUtil.b("HwWheelSizeManager", "settingRimSize NumberFormatException");
            }
            j = CommonUtil.n(BaseApplication.getContext(), String.format(Locale.ENGLISH, "%016d", Long.valueOf(j)));
            LogUtil.a("HwWheelSizeManager", "settingRimSize timeFromSetting = ", Long.valueOf(j));
        }
        String c = b().e().c();
        if (deviceInfo.getDeviceIdentify() != null && deviceInfo.getDeviceIdentify().equals(c) && (b2 > j || TextUtils.isEmpty(switchSettingFromDb))) {
            jqi.a().setSwitchSettingToDb(jrh.b(deviceInfo.getDeviceIdentify()), jrh.c(String.valueOf(b2), a2 + "," + e + "," + d2));
        }
        return b(jqi.a().getSwitchSettingFromDb(jrh.b(deviceInfo.getDeviceIdentify())), d2, e);
    }

    private String b(String str, int i, int i2) {
        String quantityString;
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_bolt_not_set);
        if (TextUtils.isEmpty(str)) {
            return string;
        }
        List asList = Arrays.asList(str.split(","));
        if (asList.size() > 3) {
            try {
                i = Integer.parseInt((String) asList.get(3));
                i2 = Integer.parseInt((String) asList.get(2));
            } catch (NumberFormatException unused) {
                LogUtil.b("settingRimSize NumberFormatException", new Object[0]);
            }
            if (i2 == 0 && jnq.b().size() <= i) {
                LogUtil.h("HwWheelSizeManager", "getRimFromDb id is error.");
                return string;
            }
            try {
                int parseInt = Integer.parseInt((String) asList.get(1));
                String e = UnitUtil.e(parseInt, 1, 0);
                if (i2 == 0) {
                    quantityString = jnq.b().get(i);
                } else {
                    quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903341_res_0x7f03012d, parseInt, e);
                }
                return quantityString;
            } catch (NumberFormatException unused2) {
                LogUtil.b("HwWheelSizeManager", "getRimFromDb NumberFormatException");
                return string;
            }
        }
        return (String) asList.get(1);
    }
}
