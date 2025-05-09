package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.callback.HwOtaResponseCallback;
import com.huawei.hwdevice.outofprocess.datatype.DataOtaParametersV2;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.hwservicesmgr.ISilenceOTAAIDLCallback;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jkv extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static jkv b;

    /* renamed from: a, reason: collision with root package name */
    private Context f13922a;
    private HwOtaResponseCallback e;
    private IBaseResponseCallback f;
    private boolean g;
    private IBaseResponseCallback h;
    private String i;
    private IBaseResponseCallback j;
    private IOTAResultAIDLCallback k;
    private String l;
    private IBaseResponseCallback m;
    private HwOtaResponseCallback n;
    private int o;
    private ISilenceOTAAIDLCallback p;
    private int q;
    private IBaseResponseCallback s;
    private static final Object d = new Object();
    private static final Object c = new Object();

    public void d(boolean z) {
        this.g = z;
    }

    public static boolean a(String str) {
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        if (e == null) {
            LogUtil.h("HwOtaV2Mgr", "isSupportChangeLog deviceInfo is null");
            return false;
        }
        return cwi.c(e, 52);
    }

    public static boolean b(String str) {
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        if (e == null) {
            LogUtil.h("HwOtaV2Mgr", "getIsSupportPackageTypeRequest deviceInfo is null");
            return false;
        }
        return cwi.c(e, 204);
    }

    private jkv(Context context) {
        super(context);
        this.k = null;
        this.p = null;
        this.o = -1;
        this.g = false;
        this.n = new HwOtaResponseCallback() { // from class: jkv.2
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwotamanager.callback.HwOtaResponseCallback
            public void onResponse(int i, DeviceInfo deviceInfo, Object obj) {
                byte[] bArr = obj instanceof byte[] ? (byte[]) obj : null;
                if (deviceInfo == null || bArr == null || bArr.length < 2) {
                    LogUtil.h("HwOtaV2Mgr", "onResponse(), error data, return");
                    return;
                }
                LogUtil.a("HwOtaV2Mgr", "Enter mOtaV2ResponseCallback onResponse(),data ", cvx.d(bArr));
                byte b2 = bArr[1];
                LogUtil.a("HwOtaV2Mgr", "mOtaV2ResponseCallback command id ", Integer.valueOf(b2));
                try {
                    if (b2 == 1) {
                        jkv.this.a(jkw.j(bArr), deviceInfo);
                    } else if (b2 == 2) {
                        jkv.this.c(jkw.a(bArr), deviceInfo);
                    } else if (b2 == 11) {
                        LogUtil.a("HwOtaV2Mgr", "onResponse errorCode ", Integer.valueOf(i), ", and objectData ", cvx.d(bArr));
                        jkv.this.f.d(0, obj);
                    } else if (b2 == 12) {
                        jkv.this.b(bArr);
                    } else if (b2 == Byte.MAX_VALUE) {
                        jkv.this.e(jkw.c(bArr));
                    } else {
                        LogUtil.h("HwOtaV2Mgr", "command is null");
                    }
                } catch (Exception unused) {
                    LogUtil.b("HwOtaV2Mgr", "Exception e");
                }
            }
        };
        this.f13922a = context;
        jfq c2 = jfq.c();
        c(3);
        c2.e(100009, this);
        c2.e(9, this);
    }

    public static jkv b() {
        jkv jkvVar;
        synchronized (d) {
            if (b == null) {
                b = new jkv(BaseApplication.getContext());
            }
            jkvVar = b;
        }
        return jkvVar;
    }

    private void c(int i) {
        this.o = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void e(HwOtaResponseCallback hwOtaResponseCallback) {
        synchronized (c) {
            if (this.e == null) {
                this.e = hwOtaResponseCallback;
            }
        }
    }

    public void e() {
        synchronized (c) {
            this.e = null;
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 9;
    }

    public void b(final String str, String str2, final int i, String str3, IOTAResultAIDLCallback iOTAResultAIDLCallback) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || iOTAResultAIDLCallback == null) {
            LogUtil.h("HwOtaV2Mgr", "OTA V2 transferOtaFile ,parameter is error!");
            if (iOTAResultAIDLCallback != null) {
                try {
                    iOTAResultAIDLCallback.onUpgradeFailed(109001, "OTA V2 transferOtaFile ,parameter is error!");
                    return;
                } catch (RemoteException unused) {
                    LogUtil.b("HwOtaV2Mgr", "OTA V2 transferOtaFile ,parameter is error");
                    return;
                }
            }
            return;
        }
        this.i = str3;
        this.l = str2;
        this.k = iOTAResultAIDLCallback;
        this.q = i;
        if (!CommonUtil.ag(this.f13922a) && knx.e()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jkv.5
                @Override // java.lang.Runnable
                public void run() {
                    if (!jsd.h(str) || !jsd.d(str)) {
                        jsd.e(str);
                    }
                    DeviceInfo f = jsd.f(str);
                    SharedPreferenceManager.d(jkv.this.f13922a, System.currentTimeMillis(), "ota_datatime");
                    jsc.e(jkv.this.f13922a, i);
                    if (f != null && f.getSoftVersion() != null) {
                        jsc.a(jkv.this.f13922a, f.getSoftVersion());
                    }
                    jsc.e(jkv.this.f13922a, jkv.this.l);
                }
            });
        }
        c();
        a(str, str2, i, str3, this.k);
        e(this.n);
    }

    public void d(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwOtaV2Mgr", "checkDeviceReadyForUpdate enter");
        e(this.n);
        this.f = iBaseResponseCallback;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(11);
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        deviceCommand.setmIdentify(str);
        jfq.c().b(deviceCommand);
    }

    private void c() {
        LogUtil.a("HwOtaV2Mgr", "otaSwitchAnalytics enter");
        final DeviceInfo d2 = jpt.d("HwOtaV2Mgr");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add("wlan_auto_update");
        arrayList.add("auto_update_status");
        arrayList.add("auto_open_wlan_status");
        jqi.a().getSwitchSetting(arrayList, new IBaseResponseCallback() { // from class: jkv.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                String str;
                LogUtil.a("HwOtaV2Mgr", "otaSwitchAnalytics list errCode is:", Integer.valueOf(i));
                if (i == 0 && (obj instanceof List)) {
                    List list = (List) obj;
                    LogUtil.a("HwOtaV2Mgr", "getSwitchSetting userPreferenceList is:", list.toString());
                    str = jkv.this.e(d2, (List<HiUserPreference>) list);
                } else {
                    str = "";
                }
                jrd.b(d2, "090000", "1", String.valueOf(i), str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(DeviceInfo deviceInfo, List<HiUserPreference> list) {
        char c2;
        String str;
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            HiUserPreference hiUserPreference = list.get(i);
            if (hiUserPreference != null) {
                String key = hiUserPreference.getKey();
                key.hashCode();
                int hashCode = key.hashCode();
                if (hashCode == 105734552) {
                    if (key.equals("auto_update_status")) {
                        c2 = 0;
                    }
                    c2 = 65535;
                } else if (hashCode != 312087338) {
                    if (hashCode == 1760488028 && key.equals("wlan_auto_update")) {
                        c2 = 2;
                    }
                    c2 = 65535;
                } else {
                    if (key.equals("auto_open_wlan_status")) {
                        c2 = 1;
                    }
                    c2 = 65535;
                }
                if (c2 == 0) {
                    str = "0x02";
                } else if (c2 == 1) {
                    str = "0x03";
                } else if (c2 != 2) {
                    LogUtil.h("HwOtaV2Mgr", "getOtaSwitchEventInfo hiUserPreference getKey is error");
                    str = "";
                } else {
                    str = "0x01";
                }
                try {
                    String e = jkw.e(deviceInfo, hiUserPreference.getValue());
                    if (e.equals("true") || e.equals("false")) {
                        e = e.equals("true") ? "1" : "2";
                    }
                    if (!TextUtils.isEmpty(e)) {
                        jSONObject.put(str, e);
                    }
                } catch (JSONException unused) {
                    LogUtil.b("HwOtaV2Mgr", "getOtaSwitchInfo JSONException");
                }
            }
        }
        LogUtil.a("HwOtaV2Mgr", "userPreferenceList.info = ", jSONObject.toString());
        return jSONObject.toString();
    }

    private void a(String str, String str2, int i, String str3, IOTAResultAIDLCallback iOTAResultAIDLCallback) {
        int i2;
        LogUtil.a("HwOtaV2Mgr", "Enter getIsUpdate() updateMode ", Integer.valueOf(i));
        try {
            if (!TextUtils.isEmpty(str3)) {
                if (str3.equals("is_package_already_exists")) {
                    i2 = 512;
                } else if (!new File(str3).exists()) {
                    LogUtil.h("HwOtaV2Mgr", "file is not exist");
                    iOTAResultAIDLCallback.onUpgradeFailed(104003, "升级文件不存在 ");
                    return;
                } else {
                    LogUtil.a("HwOtaV2Mgr", "Enter getIsUpdate()");
                    i2 = (int) jkw.a(str, str3);
                }
                int i3 = i2;
                LogUtil.a("HwOtaV2Mgr", "getIsUpdate(),componentSize ", Integer.valueOf(i3));
                String[] split = str3.split(File.separatorChar == '\\' ? "\\" : File.separator);
                if (split != null && split.length != 0) {
                    String str4 = split[split.length - 1];
                    LogUtil.a("HwOtaV2Mgr", "sendOTAFileData tmpFilePath ", str4);
                    boolean startsWith = str4.toUpperCase(Locale.getDefault()).startsWith("AW70");
                    this.p = null;
                    d(str, str2, i3, i, startsWith);
                    return;
                }
                LogUtil.h("HwOtaV2Mgr", "getIsUpdate tempArray is invalid");
                return;
            }
            LogUtil.h("HwOtaV2Mgr", "transferOtaFile V2, filePath is null");
            iOTAResultAIDLCallback.onUpgradeFailed(104003, "升级文件不存在 ");
        } catch (Exception unused) {
            LogUtil.b("HwOtaV2Mgr", "getIsUpdate TLVException e");
        }
    }

    private void d(String str, String str2, int i, int i2, boolean z) {
        String str3;
        jrb.b("HwOtaV2Mgr", 9, 1);
        String c2 = cvx.c(str2);
        String str4 = cvx.e(1) + cvx.e(c2.length() / 2) + c2;
        short s = (short) i;
        String str5 = cvx.e(2) + cvx.e(2) + cvx.a((int) s);
        String str6 = cvx.e(3) + cvx.e(1) + cvx.e(i2);
        String str7 = cvx.e(5) + cvx.e(1) + cvx.e(5);
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        if (e == null) {
            str3 = "";
        } else if (e.getProductType() == 11 || e.getProductType() == 12) {
            str3 = str4 + str5 + str6;
        } else {
            str3 = str4 + str5 + str6 + str7;
        }
        if (z) {
            str3 = str4 + str5 + str6;
        }
        LogUtil.a("HwOtaV2Mgr", "5.9.1 queryOtaAllow : ", str3);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(str3));
        deviceCommand.setDataLen(cvx.a(str3).length);
        deviceCommand.setmIdentify(str);
        if (i == 256) {
            deviceCommand.setNeedAck(false);
        } else {
            deviceCommand.setNeedAck(true);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("0x01", str2);
                jSONObject.put("0x02", (int) s);
                jSONObject.put("0x03", i2);
            } catch (JSONException unused) {
                LogUtil.b("HwOtaV2Mgr", "queryOtaAllow JSONException");
            }
            jrd.b(e, "050901", "1", "", jSONObject.toString());
        }
        jfq.c().b(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(jkp jkpVar, DeviceInfo deviceInfo) {
        try {
            if (jkpVar == null) {
                LogUtil.h("HwOtaV2Mgr", "dataOtaQueryAllow is null");
                return;
            }
            if (this.p != null) {
                int d2 = jkpVar.d();
                LogUtil.a("HwOtaV2Mgr", "Response: errorCode ", Integer.valueOf(d2));
                if (d2 != 100000 && d2 != 109025) {
                    this.p.onResponse(d2, "设备不允许升级");
                    this.p = null;
                    return;
                }
                this.p.onResponse(100000, "success");
                this.p = null;
                return;
            }
            SharedPreferenceManager.d(BaseApplication.getContext(), System.currentTimeMillis(), "EXCE_OTA_APP_UPG_TRANS_START");
            final int d3 = jkpVar.d();
            jrd.b(deviceInfo, "050901", "0", String.valueOf(d3), "");
            int a2 = jkpVar.a();
            if (!CommonUtil.ag(this.f13922a)) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: jkv.1
                    @Override // java.lang.Runnable
                    public void run() {
                        jsc.c(jkv.this.f13922a, String.valueOf(d3));
                        SharedPreferenceManager.d(jkv.this.f13922a, System.currentTimeMillis(), "EXCE_OTA_APP_UPG_TRANS_START");
                    }
                });
            }
            LogUtil.a("HwOtaV2Mgr", "Response: errorCode ", Integer.valueOf(d3), ", batteryThreshold ", Integer.valueOf(a2));
            if (d3 != 100000 && (!this.g || d3 != 109025)) {
                if (a2 != -1) {
                    this.k.onUpgradeFailed(d3, String.valueOf(a2));
                    return;
                } else {
                    this.k.onUpgradeFailed(d3, "");
                    return;
                }
            }
            e(deviceInfo);
        } catch (Exception unused) {
            LogUtil.b("HwOtaV2Mgr", "queryOtaAllowHandle Exception e");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int[] iArr) {
        IOTAResultAIDLCallback iOTAResultAIDLCallback;
        try {
            if (iArr.length == 0) {
                LogUtil.h("HwOtaV2Mgr", "dataInfos.length is 0");
                return;
            }
            int i = iArr[0];
            if (i == 100000 || (iOTAResultAIDLCallback = this.k) == null) {
                return;
            }
            iOTAResultAIDLCallback.onUpgradeFailed(i, jcv.c(i));
        } catch (Exception unused) {
            LogUtil.b("HwOtaV2Mgr", "errorCodeHandle Exception e");
        }
    }

    private void e(DeviceInfo deviceInfo) {
        jrb.b("HwOtaV2Mgr", 9, 2);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(null);
        deviceCommand.setDataLen(0);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        deviceCommand.setNeedAck(true);
        jrd.b(deviceInfo, "050902", "1", "", "");
        jfq.c().b(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DataOtaParametersV2 dataOtaParametersV2, DeviceInfo deviceInfo) {
        try {
            if (dataOtaParametersV2 == null) {
                LogUtil.h("HwOtaV2Mgr", "dataOtaParametersV2 is null");
                return;
            }
            e();
            jfq.c().c(deviceInfo, this.i + "@" + this.l, new Gson().toJson(dataOtaParametersV2), this.q, this.k);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("0x01", dataOtaParametersV2.getAppWaitTimeout());
                jSONObject.put("0x02", dataOtaParametersV2.getDeviceRestartTimeout());
            } catch (JSONException unused) {
                LogUtil.b("HwOtaV2Mgr", "getOtaParametersV2handle JSONException");
            }
            jrd.b(deviceInfo, "050902", "0", "", jSONObject.toString());
        } catch (Exception unused2) {
            LogUtil.b("HwOtaV2Mgr", "getOtaParametersV2handle() TLV error");
        }
    }

    public void b(String str, String str2, ISilenceOTAAIDLCallback iSilenceOTAAIDLCallback) {
        this.p = iSilenceOTAAIDLCallback;
        d(str, str2, 256, 2, true);
        e(this.n);
    }

    public void d(String str, boolean z) {
        String str2;
        if (z) {
            str2 = cvx.e(1) + cvx.e(1) + cvx.e(1);
        } else {
            str2 = cvx.e(1) + cvx.e(1) + cvx.e(0);
        }
        byte[] a2 = cvx.a(str2);
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", z ? 1 : 0);
        } catch (JSONException unused) {
            LogUtil.b("HwOtaV2Mgr", "sendNightValue JSONException");
        }
        jrd.b(e, "050912", "1", "", jSONObject.toString());
        jqi.a().sendSetSwitchSettingCmd(a2, str, 9, 12);
    }

    public void a(String str, int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(13);
        String str2 = cvx.e(1) + cvx.e(1) + cvx.e(i);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setmIdentify(str);
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", i);
        } catch (JSONException unused) {
            LogUtil.b("HwOtaV2Mgr", "sendUpdateChange JSONException");
        }
        jrd.b(e, "050913", "1", "", jSONObject.toString());
        LogUtil.a("HwOtaV2Mgr", "sendUpdateChange, deviceCommand :", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    public void a(jko jkoVar) {
        e(jkoVar, "", (IBaseResponseCallback) null);
    }

    public void e(jko jkoVar, String str, IBaseResponseCallback iBaseResponseCallback) {
        if (jkoVar == null) {
            LogUtil.h("HwOtaV2Mgr", "sendUpdateChange, updateMessageBean == null");
            return;
        }
        this.h = iBaseResponseCallback;
        jrb.b("HwOtaV2Mgr", 9, 14);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(14);
        StringBuffer stringBuffer = new StringBuffer(16);
        if (jkoVar.d() == 1) {
            String c2 = cvx.c(jkoVar.h());
            String str2 = cvx.e(1) + cvx.e(c2.length() / 2) + c2;
            String c3 = cvx.c(jkoVar.c());
            String str3 = cvx.e(2) + cvx.e(c3.length() / 2) + c3;
            stringBuffer.append(str2);
            stringBuffer.append(str3);
        }
        String b2 = cvx.b(jkoVar.a());
        String str4 = cvx.e(3) + cvx.e(b2.length() / 2) + b2;
        String str5 = cvx.e(4) + cvx.e(1) + cvx.e(jkoVar.d());
        String str6 = cvx.e(5) + cvx.e(1) + cvx.e(jkoVar.b());
        String str7 = cvx.e(6) + cvx.e(1) + cvx.e(jkoVar.g());
        stringBuffer.append(str4);
        stringBuffer.append(str5);
        stringBuffer.append(str6);
        if (!TextUtils.isEmpty(str)) {
            stringBuffer.append(str7);
        }
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        deviceCommand.setmIdentify(jkoVar.e());
        DeviceInfo e = jpt.e(jkoVar.e(), "HwOtaV2Mgr");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", jkoVar.h());
            jSONObject.put("0x04", jkoVar.d());
            jSONObject.put("0x05", jkoVar.b());
            jSONObject.put("0x06", jkoVar.g());
        } catch (JSONException unused) {
            LogUtil.b("HwOtaV2Mgr", "sendUpdateMessage JSONException");
        }
        jrd.b(e, "050914", "1", "", jSONObject.toString());
        LogUtil.a("HwOtaV2Mgr", "sendUpdateMessage, deviceCommand :", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte[] bArr) {
        String d2 = cvx.d(bArr);
        LogUtil.a("HwOtaV2Mgr", "handleNightUpdate ", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.h("HwOtaV2Mgr", "handleNightUpdate dataInfos is error");
            return;
        }
        int i = 0;
        try {
            i = CommonUtil.w(new cwl().a(d2.substring(4)).e().get(0).e());
        } catch (cwg unused) {
            LogUtil.b("HwOtaV2Mgr", "handleNightUpdate error");
        }
        LogUtil.a("HwOtaV2Mgr", "handleNightUpdate :", Integer.valueOf(i));
    }

    private void a(byte[] bArr) {
        if (bArr.length < 2) {
            LogUtil.h("HwOtaV2Mgr", "dealDeviceRequestCallback dataInfos is invalid");
            return;
        }
        LogUtil.c("HwOtaV2Mgr", "dealDeviceRequestCallback dataInfos: ", cvx.d(bArr));
        byte b2 = bArr[1];
        if (b2 == 19) {
            e(bArr);
            return;
        }
        if (b2 == 14) {
            c(bArr);
        } else if (b2 == 22) {
            d(bArr);
        } else {
            LogUtil.c("HwOtaV2Mgr", "dealDeviceRequestCallback default");
        }
    }

    public boolean e(String str) {
        try {
            int c2 = jkj.d().c(str);
            if (c2 == 3) {
                a(str, 109021L);
                return false;
            }
            if (c2 != 4 && c2 != 6) {
                a(str, 100000L);
                return true;
            }
            a(str, 109022L);
            return false;
        } catch (IllegalStateException unused) {
            LogUtil.b("parserDeviceRequestCheck startService IllegalStateException", new Object[0]);
            return false;
        }
    }

    public boolean b(String str, int i) {
        DeviceInfo d2 = jpt.d("HwOtaV2Mgr");
        if (d2 == null) {
            LogUtil.h("HwOtaV2Mgr", "deviceRequestDownloadOta connectDeviceInfo is null");
            return false;
        }
        if (i == 0 || i == 1) {
            String str2 = i == 0 ? "false" : "true";
            jqi.a().setSwitchSetting("auto_update_status", str2, d2.getDeviceUdid(), null);
            d(str, Boolean.parseBoolean(str2));
            return true;
        }
        if (i == 2) {
            c(str);
            return false;
        }
        if (i == 3) {
            jqi.a().setSwitchSetting("auto_update_status", "true", d2.getDeviceUdid(), null);
        }
        return false;
    }

    private void c(byte[] bArr) {
        try {
            int d2 = jkw.d(bArr);
            IBaseResponseCallback iBaseResponseCallback = this.h;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(d2, null);
            }
        } catch (cwg unused) {
            LogUtil.b("HwOtaV2Mgr", "deviceUpdateMessage TlvException");
        }
    }

    private void e(byte[] bArr) {
        try {
            int b2 = jkw.b(bArr);
            IBaseResponseCallback iBaseResponseCallback = this.m;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, Integer.valueOf(b2));
            }
        } catch (cwg unused) {
            LogUtil.b("HwOtaV2Mgr", "deviceTransmitMode TlvException");
        }
    }

    private void d(byte[] bArr) {
        try {
            int b2 = jkw.b(bArr);
            IBaseResponseCallback iBaseResponseCallback = this.s;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, Integer.valueOf(b2));
            }
        } catch (cwg unused) {
            LogUtil.b("HwOtaV2Mgr", "deviceTransmitMode TlvException");
        }
    }

    private void a(String str, long j) {
        jrb.b("HwOtaV2Mgr", 9, 15);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(15);
        byte[] d2 = new bms().i(127, (int) j).d();
        deviceCommand.setDataContent(d2);
        deviceCommand.setDataLen(d2.length);
        deviceCommand.setmIdentify(str);
        jrd.b(jpt.e(str, "HwOtaV2Mgr"), "050915", "1", String.valueOf(j), "");
        LogUtil.a("HwOtaV2Mgr", "sendDeviceRequestCheckAnswer deviceCommand:", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    private void c(String str) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(16);
        String str2 = cvx.e(1) + cvx.e(1) + cvx.e(2);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setmIdentify(str);
        LogUtil.a("HwOtaV2Mgr", "answerDeviceRequestStatus deviceCommand:", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    public void b(String str, int i, int i2) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(16);
        StringBuffer stringBuffer = new StringBuffer(16);
        String str2 = cvx.e(2) + cvx.e(1) + cvx.e(i);
        String str3 = cvx.e(3) + cvx.e(1) + cvx.e(i2);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        deviceCommand.setmIdentify(str);
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x02", i);
            jSONObject.put("0x03", i2);
        } catch (JSONException unused) {
            LogUtil.b("HwOtaV2Mgr", "answerDeviceRequestDownloadOta JSONException");
        }
        jrd.b(e, "050916", "1", "", jSONObject.toString());
        LogUtil.a("HwOtaV2Mgr", "answerDeviceRequestDownloadOta deviceCommand:", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    public void d(String str, int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(17);
        String str2 = cvx.e(2) + cvx.e(1) + cvx.e(i);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setmIdentify(str);
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x02", i);
        } catch (JSONException unused) {
            LogUtil.b("HwOtaV2Mgr", "answerUserIsAgreeDownload JSONException");
        }
        jrd.b(e, "050917", "1", "", jSONObject.toString());
        LogUtil.a("HwOtaV2Mgr", "answerUserIsAgreeDownload deviceCommand:", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    public void c(String str, int i, int i2, int i3) {
        LogUtil.c("HwOtaV2Mgr", "sendOtaDownloadPercent percent:", Integer.valueOf(i), " downloadMode:", Integer.valueOf(i2), " downloadState:", Integer.valueOf(i3));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(18);
        StringBuffer stringBuffer = new StringBuffer(16);
        String str2 = cvx.e(1) + cvx.e(1) + cvx.e(i);
        String str3 = cvx.e(2) + cvx.e(1) + cvx.e(i3);
        String str4 = cvx.e(3) + cvx.e(1) + cvx.e(i2);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        stringBuffer.append(str4);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        deviceCommand.setmIdentify(str);
        if (i == 100 || i3 == 0 || i3 == 2 || i3 == 255) {
            DeviceInfo b2 = jpt.b(str, "HwOtaV2Mgr");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("0x01", i);
                jSONObject.put("0x02", i3);
                jSONObject.put("0x03", i2);
            } catch (JSONException unused) {
                LogUtil.b("HwOtaV2Mgr", "sendOtaDownloadPercent JSONException");
            }
            jrd.b(b2, "050918", "1", "", jSONObject.toString());
        }
        jfq.c().b(deviceCommand);
    }

    public void e(String str, IBaseResponseCallback iBaseResponseCallback) {
        this.s = iBaseResponseCallback;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(22);
        String str2 = cvx.e(1) + cvx.e(1);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setmIdentify(str);
        LogUtil.a("HwOtaV2Mgr", "queryDevicePackageType deviceCommand:", deviceCommand.toString());
        jrd.b(jpt.e(str, "HwOtaV2Mgr"), "050922", "1", "", "");
        jfq.c().b(deviceCommand);
    }

    public void b(String str, IBaseResponseCallback iBaseResponseCallback) {
        this.m = iBaseResponseCallback;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(19);
        String str2 = cvx.e(1) + cvx.e(1);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setmIdentify(str);
        LogUtil.a("HwOtaV2Mgr", "queryDeviceTransmitMode deviceCommand:", deviceCommand.toString());
        jrd.b(jpt.e(str, "HwOtaV2Mgr"), "050919", "1", "", "");
        jfq.c().b(deviceCommand);
    }

    public void a() {
        LogUtil.a("HwOtaV2Mgr", "removeQueryDeviceTransmitCallback enter");
        this.m = null;
    }

    public void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwOtaV2Mgr", "deviceInfo is null or aw70");
        } else {
            kxz.e(this.f13922a, deviceInfo.getDeviceIdentify(), "device_is_transmission", false);
        }
    }

    public void d(String str, jkr jkrVar) {
        LogUtil.a("HwOtaV2Mgr", "sendChangelogToDevice packageVersion:", jkrVar.b(), " appResult:", Integer.valueOf(jkrVar.d()));
        String b2 = jkrVar.b();
        if (StringUtils.g(b2)) {
            return;
        }
        int d2 = jkrVar.d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(20);
        StringBuffer stringBuffer = new StringBuffer(16);
        String c2 = cvx.c(b2);
        stringBuffer.append(cvx.e(1) + cvx.e(c2.length() / 2) + c2);
        stringBuffer.append(cvx.e(2) + cvx.e(1) + cvx.e(d2));
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", b2);
            jSONObject.put("0x02", d2);
            jSONObject.put("0x05", jkrVar.c());
        } catch (JSONException unused) {
            LogUtil.b("HwOtaV2Mgr", "sendChangelogToDevice JSONException");
        }
        jrd.b(e, "050920", "1", "", jSONObject.toString());
        if (d2 == 0) {
            deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
            deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
            LogUtil.a("HwOtaV2Mgr", "sendChangelogToDevice, deviceCommand :", deviceCommand.toString());
            jfq.c().b(deviceCommand);
            return;
        }
        if (d2 == 1) {
            String c3 = jkrVar.c();
            String f = jkrVar.f();
            String a2 = jkrVar.a();
            LogUtil.a("HwOtaV2Mgr", "sendChangelogToDevice, versionChangeLog :", a2);
            String c4 = cvx.c(a2);
            stringBuffer.append(cvx.e(3) + cvx.d(c4.length() / 2) + c4);
            String c5 = cvx.c(f);
            stringBuffer.append(cvx.e(4) + cvx.d(c5.length() / 2) + c5);
            String c6 = cvx.c(c3);
            stringBuffer.append(cvx.e(5) + cvx.e(c6.length() / 2) + c6);
        }
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        deviceCommand.setmIdentify(str);
        LogUtil.a("HwOtaV2Mgr", "sendChangelogToDevice, deviceCommand :", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    private void c(int i, byte[] bArr) {
        LogUtil.h("HwOtaV2Mgr", "deviceChangeLogMessage errorCode=", Integer.valueOf(i));
        try {
            jkr e = jkw.e(bArr);
            IBaseResponseCallback iBaseResponseCallback = this.j;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i, e);
            }
        } catch (cwg unused) {
            LogUtil.b("HwOtaV2Mgr", "deviceChangeLogCallback TlvException");
        }
    }

    private void b(int i, byte[] bArr) {
        LogUtil.h("HwOtaV2Mgr", "dealDeviceRequestCallback errorCode=", Integer.valueOf(i));
        if (bArr.length < 2) {
            LogUtil.h("HwOtaV2Mgr", "dealDeviceRequestCallback dataInfos is invalid");
            return;
        }
        LogUtil.a("HwOtaV2Mgr", "dealDeviceRequestCallback dataInfos: ", cvx.d(bArr));
        if (bArr[1] == 21) {
            c(i, bArr);
        }
    }

    public void b(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwOtaV2Mgr", "queryChangelogFromDevice packageVersion:", str2, " versionCountry:", str3);
        this.j = iBaseResponseCallback;
        if (StringUtils.g(str2) || StringUtils.g(str3)) {
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(21);
        StringBuffer stringBuffer = new StringBuffer(16);
        String c2 = cvx.c(str2);
        stringBuffer.append(cvx.e(1) + cvx.e(c2.length() / 2) + c2);
        String c3 = cvx.c(str3);
        stringBuffer.append(cvx.e(2) + cvx.e(c3.length() / 2) + c3);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        deviceCommand.setmIdentify(str);
        DeviceInfo e = jpt.e(str, "HwOtaV2Mgr");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", str2);
            jSONObject.put("0x02", str3);
        } catch (JSONException unused) {
            LogUtil.b("HwOtaV2Mgr", "queryChangelogFromDevice JSONException");
        }
        jrd.b(e, "050921", "1", "", jSONObject.toString());
        LogUtil.a("HwOtaV2Mgr", "sendUpdateMessage, deviceCommand :", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        HwOtaResponseCallback hwOtaResponseCallback = this.e;
        if (hwOtaResponseCallback != null) {
            hwOtaResponseCallback.onResponse(i, deviceInfo, bArr);
        }
        DeviceCapability e = cvs.e(deviceInfo.getDeviceIdentify());
        if (e != null && e.getIsSupportDeviceRequestCheck()) {
            a(bArr);
        }
        if (a(deviceInfo.getDeviceIdentify())) {
            b(i, bArr);
        }
    }
}
