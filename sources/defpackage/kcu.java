package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class kcu implements ParserInterface {
    private static kcu c;
    private static final Object e = new Object();
    private List<HiHealthData> j = new ArrayList(10);
    private int h = 0;
    private int i = 0;
    private int g = 0;
    private boolean f = false;

    /* renamed from: a, reason: collision with root package name */
    private CopyOnWriteArrayList<HiHealthData> f14291a = new CopyOnWriteArrayList<>();
    private cwl o = new cwl();
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: kcu.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HwMenstrualManager", "mConnectStateChangedReceiver intent is null");
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    LogUtil.h("HwMenstrualManager", "mConnectStateChangedReceiver, info is null");
                    return;
                }
                if (deviceInfo.getRelationship() != null && "followed_relationship".equals(deviceInfo.getRelationship())) {
                    LogUtil.a("HwMenstrualManager", "This device does not have the correspond capability.");
                    return;
                }
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "mConnectStateChangedReceiver, deviceConnectState:", Integer.valueOf(deviceConnectState));
                if (deviceConnectState == 3) {
                    kcu.this.l();
                }
            }
        }
    };
    private ExtendHandler b = HandlerCenter.yt_(new b(), "HwMenstrualManager");

    private int e(int i) {
        return 1 << i;
    }

    private boolean i() {
        return false;
    }

    private kcu() {
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.d, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public static kcu d() {
        kcu kcuVar;
        synchronized (e) {
            if (c == null) {
                c = new kcu();
            }
            kcuVar = c;
        }
        return kcuVar;
    }

    private boolean n() {
        boolean c2 = cwi.c(kcl.a().c("HwMenstrualManager"), 174);
        ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "isSupportMenstrualSynchronize is :", Boolean.valueOf(c2));
        return c2;
    }

    public void d(String str) {
        if (i()) {
            LogUtil.h("HwMenstrualManager", "sendMenstrualUpdateNoticeFromKit, this app is gp version");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "sendMenstrualUpdateNoticeFromKit null");
            return;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", "HwMenstrualManager");
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        if (deviceCapability != null && deviceCapability.isSupportMenstrual()) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                MenstrualSwitchStatus menstrualSwitchStatus = new MenstrualSwitchStatus();
                menstrualSwitchStatus.setMasterSwitch(jSONObject.optInt("masterSwitch"));
                menstrualSwitchStatus.setMenstruationStartRemindSwitch(jSONObject.optInt("menstrualStartSwitch"));
                menstrualSwitchStatus.setMenstruationEndRemindSwitch(jSONObject.optInt("menstrualEndSwitch"));
                menstrualSwitchStatus.setEasyPregnancyStartSwitch(jSONObject.optInt("easyPregnancyStartSwitch"));
                menstrualSwitchStatus.setEasyPregnancyEndSwitch(jSONObject.optInt("easyPregnancyEndSwitch"));
                a(menstrualSwitchStatus);
                return;
            } catch (JSONException unused) {
                LogUtil.b("HwMenstrualManager", "sendMenstrualStatusForKit JSONException");
                return;
            }
        }
        LogUtil.h("HwMenstrualManager", "sendMenstrualUpdateNoticeFromKit capability is error");
    }

    public void a(MenstrualSwitchStatus menstrualSwitchStatus) {
        ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "sendMenstrualStatus start");
        if (i()) {
            LogUtil.h("HwMenstrualManager", "sendMenstrualSwitch, this app is gp version");
            return;
        }
        if (menstrualSwitchStatus == null) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "sendMenstrualSwitch menstrualSwitchStatus null");
            return;
        }
        int masterSwitch = menstrualSwitchStatus.getMasterSwitch();
        int menstruationStartRemindSwitch = menstrualSwitchStatus.getMenstruationStartRemindSwitch();
        int menstruationEndRemindSwitch = menstrualSwitchStatus.getMenstruationEndRemindSwitch();
        int easyPregnancyStartSwitch = menstrualSwitchStatus.getEasyPregnancyStartSwitch();
        int easyPregnancyEndSwitch = menstrualSwitchStatus.getEasyPregnancyEndSwitch();
        String str = cvx.e(1) + cvx.e(1) + cvx.e((masterSwitch * e(0)) + (menstruationStartRemindSwitch * e(1)) + (menstruationEndRemindSwitch * e(2)) + (easyPregnancyStartSwitch * e(3)) + (easyPregnancyEndSwitch * e(4)));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwMenstrualManager", "sendMenstrualStatus, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj) {
        StringBuffer stringBuffer = new StringBuffer(16);
        if (i == 0 && (obj instanceof Long)) {
            long longValue = ((Long) obj).longValue();
            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "modifyTime is:", Long.valueOf(longValue));
            stringBuffer.append(cvx.e(1)).append(cvx.e(cvx.b(longValue).length() / 2)).append(cvx.b(longValue));
        } else {
            String b2 = cvx.b(150001L);
            stringBuffer.append(cvx.e(127)).append(cvx.e(b2.length() / 2)).append(b2);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        LogUtil.a("HwMenstrualManager", "sendModifyTime, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj, boolean[] zArr, Object obj2, boolean z) {
        ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "sendMenstrualStatusData");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(3);
        StringBuffer stringBuffer = new StringBuffer(16);
        if (i == 0 && (obj instanceof kcs)) {
            kcs kcsVar = (kcs) obj;
            List<kcr> b2 = kcsVar.b();
            String c2 = kcl.a().c(b2);
            stringBuffer.append(cvx.e(1)).append(cvx.e(c2.length() / 2)).append(c2);
            stringBuffer.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS));
            StringBuffer stringBuffer2 = new StringBuffer(16);
            Iterator<kcr> it = b2.iterator();
            while (it.hasNext()) {
                d(stringBuffer2, it.next(), z);
            }
            stringBuffer.append(cvx.d(stringBuffer2.toString().length() / 2)).append(stringBuffer2.toString());
            long d = kcsVar.d();
            stringBuffer.append(cvx.e(9)).append(cvx.e(cvx.b(d).length() / 2)).append(cvx.b(d));
            d(zArr, obj2, stringBuffer);
        } else {
            String b3 = cvx.b(150001L);
            stringBuffer.append(cvx.e(127)).append(cvx.e(b3.length() / 2)).append(b3);
        }
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        LogUtil.a("HwMenstrualManager", "sendMenstrualStatusData, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void d(boolean[] zArr, Object obj, StringBuffer stringBuffer) {
        if ((obj instanceof int[]) && zArr != null && zArr.length == 2) {
            int[] iArr = (int[]) obj;
            if (zArr[0] && iArr.length > 0) {
                stringBuffer.append(cvx.e(10)).append(cvx.e(1)).append(cvx.e(iArr[0]));
            }
            if (!zArr[1] || iArr.length <= 1) {
                return;
            }
            stringBuffer.append(cvx.e(11)).append(cvx.e(1)).append(cvx.e(iArr[1]));
        }
    }

    private void d(StringBuffer stringBuffer, kcr kcrVar, boolean z) {
        StringBuffer stringBuffer2 = new StringBuffer(16);
        stringBuffer2.append(cvx.e(4)).append(cvx.e(cvx.b(kcrVar.h()).length() / 2)).append(cvx.b(kcrVar.h()));
        stringBuffer2.append(cvx.e(5)).append(cvx.e(cvx.b(kcrVar.c()).length() / 2)).append(cvx.b(kcrVar.c()));
        stringBuffer2.append(cvx.e(6)).append(cvx.e(cvx.b(kcrVar.d()).length() / 2)).append(cvx.b(kcrVar.d()));
        stringBuffer2.append(cvx.e(7)).append(cvx.e(cvx.b(kcrVar.b()).length() / 2)).append(cvx.b(kcrVar.b()));
        stringBuffer2.append(cvx.e(8)).append(cvx.e(1)).append(cvx.e(kcrVar.e()));
        if (z) {
            boolean a2 = kcrVar.a();
            stringBuffer2.append(cvx.e(12)).append(cvx.e(cvx.e(a2 ? 1 : 0).length() / 2)).append(cvx.e(a2 ? 1 : 0));
        }
        stringBuffer.append(cvx.e(131)).append(cvx.e(stringBuffer2.toString().length() / 2)).append(stringBuffer2);
    }

    public void e() {
        if (i()) {
            LogUtil.h("HwMenstrualManager", "sendUpdateNotice, this app is gp version");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(4);
        LogUtil.a("HwMenstrualManager", "sendUpdateNotice, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        if (!n()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - 5184000000L;
        while (true) {
            long j = currentTimeMillis;
            if (j >= System.currentTimeMillis()) {
                return;
            }
            kco e2 = kco.e();
            currentTimeMillis = HwExerciseConstants.TEN_DAY_SECOND + j;
            e2.c(j, currentTimeMillis, 0L);
        }
    }

    public void b() {
        if (i()) {
            LogUtil.h("HwMenstrualManager", "sendMenstrualCapability, this app is gp version");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(5);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1)).append(cvx.e(1)).append(cvx.e(n() ? 3 : 2));
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        LogUtil.a("HwMenstrualManager", "sendMenstrualCapability, command:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(6);
        StringBuffer stringBuffer = new StringBuffer(16);
        if (i == 0) {
            String b2 = cvx.b(150001L);
            stringBuffer.append(cvx.e(127)).append(cvx.e(b2.length() / 2)).append(b2);
        } else {
            stringBuffer.append(cvx.e(4)).append(cvx.e(cvx.e(i).length() / 2)).append(cvx.e(i));
        }
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLength(cvx.a(stringBuffer.toString()).length);
        LogUtil.a("HwMenstrualManager", "sendFrameNumResponse, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void d(int i) {
        List<HiHealthData> list = this.j;
        if (list == null || list.isEmpty()) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "sendBodyStatusDataResponse data null");
            return;
        }
        int size = this.j.size();
        int i2 = this.h;
        int i3 = i * i2;
        if (i3 < 0) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "sendBodyStatusDataResponse no data");
            return;
        }
        int i4 = (i + 1) * i2;
        if (i4 >= size) {
            i4 = size;
        }
        if (size <= i3) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "sendBodyStatusDataResponse no data");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1)).append(cvx.e(cvx.e(i).length() / 2)).append(cvx.e(i));
        int i5 = i4 - i3;
        stringBuffer.append(cvx.e(2)).append(cvx.e(cvx.e(i5).length() / 2)).append(cvx.e(i5));
        StringBuffer stringBuffer2 = new StringBuffer(16);
        while (i3 < i4) {
            b(stringBuffer2, i3);
            i3++;
        }
        stringBuffer.append(cvx.e(131)).append(cvx.d(stringBuffer2.toString().length() / 2)).append(stringBuffer2.toString());
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(7);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLength(cvx.a(stringBuffer.toString()).length);
        LogUtil.a("HwMenstrualManager", "sendBodyStatusDataResponse, deviceCommand:", deviceCommand.toString());
        ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "sendBodyStatusDataResponse, index", Integer.valueOf(i));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void b(StringBuffer stringBuffer, int i) {
        if (this.j.size() >= i) {
            HiHealthData hiHealthData = this.j.get(i);
            long startTime = hiHealthData.getStartTime() / 1000;
            int i2 = (int) hiHealthData.getDouble("menstrual_status");
            int i3 = (int) hiHealthData.getDouble("menstrual_physical");
            int i4 = (int) hiHealthData.getDouble("menstrual_sub_status");
            StringBuffer stringBuffer2 = new StringBuffer(16);
            stringBuffer2.append(cvx.e(5)).append(cvx.e(cvx.b(startTime).length() / 2)).append(cvx.b(startTime));
            stringBuffer2.append(cvx.e(6)).append(cvx.e(cvx.a(i2).length() / 2)).append(cvx.a(i2));
            if (!n()) {
                int i5 = (int) hiHealthData.getDouble("menstrual_quantity");
                int i6 = (int) hiHealthData.getDouble("menstrual_dysmenorrhea");
                if (i5 > 3) {
                    sqo.y("getDailyData quantity is" + i5);
                    i5 = 3;
                }
                if (i6 > 3) {
                    sqo.y("getDailyData dysmenorrhea is" + i6);
                    i6 = 3;
                }
                stringBuffer2.append(cvx.e(7)).append(cvx.e(1)).append(cvx.e(i5 + (i6 * e(4))));
            }
            stringBuffer2.append(cvx.e(8)).append(cvx.e(cvx.a(i3).length() / 2)).append(cvx.a(i3));
            stringBuffer2.append(cvx.e(9)).append(cvx.e(cvx.a(i4).length() / 2)).append(cvx.a(i4));
            stringBuffer.append(cvx.e(UserInfomation.WEIGHT_DEFAULT_ENGLISH)).append(cvx.e(stringBuffer2.toString().length() / 2)).append(stringBuffer2.toString());
            return;
        }
        LogUtil.b("HwMenstrualManager", "getDailyDataFromHiHealthData ArrayIndexOutOfBoundsException.");
    }

    private void k() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(127)).append(cvx.e(cvx.b(100000L).length() / 2)).append(cvx.b(100000L));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(50);
        deviceCommand.setCommandID(8);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setDataLength(cvx.a(stringBuffer.toString()).length);
        LogUtil.a("HwMenstrualManager", "sendFrameNumReceived, deviceCommand:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i >= this.i) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "sendBodyStatusRequest frameIndex error");
            return;
        }
        if (this.g < 3) {
            Message obtain = Message.obtain();
            obtain.what = i;
            this.b.sendMessage(obtain, 5000L);
            this.g++;
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(cvx.e(1)).append(cvx.e(cvx.e(i).length() / 2)).append(cvx.e(i));
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(50);
            deviceCommand.setCommandID(9);
            deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
            deviceCommand.setDataLength(cvx.a(stringBuffer.toString()).length);
            LogUtil.a("HwMenstrualManager", "sendBodyStatusRequest, deviceCommand:", deviceCommand.toString());
            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "sendBodyStatusRequest, frameIndex:", Integer.valueOf(i));
            jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
            return;
        }
        ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "Have Already tried three times!");
        l();
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a("HwMenstrualManager", "getResult:", d);
        if (i()) {
            LogUtil.h("HwMenstrualManager", "this app is gp version");
        }
        if (bArr == null || bArr.length <= 1) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "data illegal");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "getResult commandId is:", Byte.valueOf(bArr[1]));
        if ("7F04".equalsIgnoreCase(d.length() >= 8 ? d.substring(4, 8) : null)) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "getResult error 7F04");
            return;
        }
        byte b2 = bArr[1];
        if (b2 == 2) {
            f();
            return;
        }
        if (b2 == 3) {
            b(bArr);
            return;
        }
        switch (b2) {
            case 6:
                e(bArr);
                break;
            case 7:
                a(bArr);
                break;
            case 8:
                c(bArr);
                break;
            case 9:
                d(bArr);
                break;
            default:
                LogUtil.h("HwMenstrualManager", "getResult nothing to do");
                break;
        }
    }

    private void f() {
        ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "handleModifyTimeResult");
        kcl.a().e(new IBaseResponseCallback() { // from class: kcu.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                kcu.this.d(i, obj);
            }
        });
    }

    private void b(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a("HwMenstrualManager", "handleSendMenstrualDataResult,", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "handleSendMenstrualDataResult data is error");
            return;
        }
        try {
            int i = 0;
            int i2 = 0;
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            for (cwd cwdVar : this.o.a(d.substring(4)).e()) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 1) {
                    String c2 = cwdVar.c();
                    if (!TextUtils.isEmpty(c2) && c2.length() >= 4) {
                        int w2 = CommonUtil.w(c2.substring(0, 2));
                        int w3 = CommonUtil.w(c2.substring(2));
                        ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "historyCycle:", Integer.valueOf(w2), "futureCycle:", Integer.valueOf(w3));
                        i = w2;
                        i2 = w3;
                    }
                } else {
                    switch (w) {
                        case 10:
                            z2 = true;
                            break;
                        case 11:
                            z = true;
                            break;
                        case 12:
                            z3 = true;
                            break;
                        default:
                            Object[] objArr = new Object[1];
                            objArr[0] = "handleSendMenstrualDataResult default";
                            LogUtil.h("HwMenstrualManager", objArr);
                            break;
                    }
                }
            }
            e(i, i2, z, z2, z3);
        } catch (cwg unused) {
            LogUtil.b("HwMenstrualManager", "handleSendMenstrualDataResult TLVException");
        }
    }

    private void e(int i, int i2, final boolean z, final boolean z2, final boolean z3) {
        kcl.a().b(i, i2, new IBaseResponseCallback() { // from class: kcu.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(final int i3, final Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "handleSendMenstrualDataResult getData errorCode:", Integer.valueOf(i3));
                if (!z && !z2) {
                    kcu.this.d(i3, obj, null, null, z3);
                } else {
                    kcl.a().c(new IBaseResponseCallback() { // from class: kcu.3.5
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i4, Object obj2) {
                            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "prepareMenstrualData manualErrorCode:", Integer.valueOf(i4));
                            kcu.this.d(i3, obj, new boolean[]{z, z2}, obj2, z3);
                        }
                    });
                }
            }
        });
    }

    private void e(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a("HwMenstrualManager", "handleFrameNumRequest,", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "handleFrameNumRequest data is error");
            return;
        }
        try {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (cwd cwdVar : this.o.a(d.substring(4)).e()) {
                int a2 = CommonUtil.a(cwdVar.e(), 16);
                if (a2 == 1) {
                    i = CommonUtil.a(cwdVar.c(), 16);
                } else if (a2 == 2) {
                    i2 = CommonUtil.a(cwdVar.c(), 16);
                } else if (a2 == 3) {
                    i3 = CommonUtil.a(cwdVar.c(), 16);
                } else {
                    LogUtil.h("HwMenstrualManager", "handleFrameNumRequest default");
                }
            }
            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "handleFrameNumRequest historyDay:", Integer.valueOf(i), ",futureDay:", Integer.valueOf(i2), ",frameSize:", Integer.valueOf(i3));
            if (i3 != 0) {
                d(i, i2, i3);
            }
        } catch (cwg unused) {
            LogUtil.b("HwMenstrualManager", "handleFrameNumRequest TlvException");
        }
    }

    private void d(int i, int i2, int i3) {
        this.h = i3;
        kcl.a().c(i, i2, new IBaseResponseCallback() { // from class: kcu.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i4, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "handleFrameNumRequest getBodyStatus errorCode:", Integer.valueOf(i4));
                if (i4 != 0 || !(obj instanceof List)) {
                    kcu.this.b(0);
                    return;
                }
                kcu.this.j = (List) obj;
                kcu.this.b((int) Math.ceil((r4.size() * 1.0d) / kcu.this.h));
            }
        });
    }

    private void a(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a("HwMenstrualManager", "handleBodyStatusRequest,", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "handleBodyStatusRequest data is error");
            return;
        }
        try {
            List<cwd> e2 = this.o.a(d.substring(4)).e();
            if (e2 == null || e2.isEmpty()) {
                return;
            }
            int a2 = CommonUtil.a(e2.get(0).c(), 16);
            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "handleBodyStatusRequest frameIndex is,", Integer.valueOf(a2));
            d(a2);
        } catch (cwg unused) {
            LogUtil.b("HwMenstrualManager", "handleBodyStatusRequest TlvException");
        }
    }

    private void c(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a("HwMenstrualManager", "handleFrameNumReceive,", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "handleFrameNumReceive data is error");
            return;
        }
        if (this.f) {
            LogUtil.h("HwMenstrualManager", "Processing, wait!");
            return;
        }
        try {
            List<cwd> e2 = this.o.a(d.substring(4)).e();
            if (e2 == null || e2.isEmpty()) {
                return;
            }
            this.i = CommonUtil.a(e2.get(0).c(), 16);
            k();
            if (this.i > 0) {
                this.f = true;
                c(0);
            }
        } catch (cwg unused) {
            LogUtil.b("HwMenstrualManager", "handleFrameNumReceive TlvException");
        }
    }

    private void d(byte[] bArr) {
        int i;
        String d = cvx.d(bArr);
        LogUtil.a("HwMenstrualManager", "handleFrameNumReceive,", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "handleFrameNumReceive data is error");
            return;
        }
        this.g = 0;
        try {
            cwe a2 = this.o.a(d.substring(4));
            Iterator<cwd> it = a2.e().iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = 0;
                    break;
                }
                cwd next = it.next();
                if (CommonUtil.w(next.e()) == 1) {
                    i = CommonUtil.w(next.c());
                    ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "handleBodyStatusReceive device response frame:", Integer.valueOf(i));
                    this.b.removeMessages(i);
                    c(i + 1);
                    break;
                }
            }
            List<cwe> a3 = a2.a();
            if (a3 != null && !a3.isEmpty()) {
                cwe cweVar = a3.get(0);
                ArrayList arrayList = new ArrayList(16);
                Iterator<cwe> it2 = cweVar.a().iterator();
                while (it2.hasNext()) {
                    d(arrayList, it2.next());
                }
                this.f14291a.addAll(arrayList);
                if (i >= this.i - 1) {
                    m();
                    kcl.a().d(this.f14291a, new IBaseResponseCallback() { // from class: kcu.4
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj) {
                            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "insert finish, reset:", Integer.valueOf(i2));
                            kcu.this.l();
                        }
                    });
                    if (CommonUtil.as()) {
                        g();
                        return;
                    }
                    return;
                }
                return;
            }
            LogUtil.h("HwMenstrualManager", "handleBodyStatusReceive device response no daily data");
        } catch (cwg unused) {
            LogUtil.b("HwMenstrualManager", "handleBodyStatusReceive TlvException");
        }
    }

    private void g() {
        CopyOnWriteArrayList<HiHealthData> copyOnWriteArrayList = this.f14291a;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() == 0) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualManager", "insertMenstrualDirtyData healthDataList empty");
            return;
        }
        kcl.a().a(this.f14291a.get(r1.size() - 1).getEndTime(), new IBaseResponseCallback() { // from class: kcu.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "getMenstrualEndData finish, errorCode:", Integer.valueOf(i));
                if (i == 0 && (obj instanceof List)) {
                    kcl.a().d((List) obj, new IBaseResponseCallback() { // from class: kcu.6.4
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj2) {
                            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "insertMenstrualDirtyData insert finish, errorCode:", Integer.valueOf(i2));
                            sqo.y("insertMenstrual has end dirty data");
                        }
                    });
                }
            }
        });
    }

    private void m() {
        if (koq.b(this.f14291a)) {
            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "dataInsertList is null");
            return;
        }
        Iterator<HiHealthData> it = this.f14291a.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (it.hasNext()) {
            HiHealthData next = it.next();
            if (next == null) {
                ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "hiHealthData is null, continue");
            } else {
                double d = next.getDouble("menstrual_status");
                if (Double.compare(d, 101.0d) == 0) {
                    i++;
                } else if (Double.compare(d, 102.0d) == 0) {
                    i2++;
                } else if (Double.compare(d, 301.0d) == 0) {
                    i3++;
                } else if (Double.compare(d, 302.0d) == 0) {
                    i4++;
                } else if (Double.compare(d, 401.0d) == 0) {
                    i5++;
                } else if (Double.compare(d, 402.0d) == 0) {
                    i6++;
                }
            }
        }
        if (i != i2) {
            sqo.y("periodSyncError device put 102 is " + i2 + "device put 101 is " + i);
            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "periodSyncError, device put 102 is,", Integer.valueOf(i2), "device put 101 is,", Integer.valueOf(i));
        }
        if (i3 != i4) {
            sqo.y("predictSyncError device put 302 is " + i4 + "device put 301 is" + i3);
            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "periodSyncError, device put 302 is,", Integer.valueOf(i4), "device put 301 is,", Integer.valueOf(i3));
        }
        if (i5 != i6) {
            sqo.y("pregnantSyncError device put 402 is " + i6 + "device put 401 is " + i5);
            ReleaseLogUtil.e("DEVMGR_HwMenstrualManager", "periodSyncError, device put 402 is,", Integer.valueOf(i6), "device put 401 is,", Integer.valueOf(i5));
        }
    }

    private void d(List<HiHealthData> list, cwe cweVar) {
        HiHealthData hiHealthData = new HiHealthData(10010);
        hiHealthData.setDeviceUuid("-1");
        for (cwd cwdVar : cweVar.e()) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 5:
                    hiHealthData.setTimeInterval(CommonUtil.y(cwdVar.c()) * 1000, CommonUtil.y(cwdVar.c()) * 1000);
                    break;
                case 6:
                    hiHealthData.putDouble("menstrual_status", CommonUtil.w(cwdVar.c()));
                    break;
                case 7:
                    if (!n()) {
                        int w = CommonUtil.w(cwdVar.c()) & 15;
                        int w2 = CommonUtil.w(cwdVar.c()) & GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
                        LogUtil.a("HwMenstrualManager", "getHiHealthDataFromCommand quantity is,", Integer.valueOf(w), "and dysmenorrhea is,", Integer.valueOf(w2));
                        if (w > 3) {
                            sqo.y("quantity is" + w);
                            w = 3;
                        }
                        if (w2 >= 16) {
                            w2 /= e(4);
                        } else if (w2 > 3) {
                            sqo.y("dysmenorrhea is over 3" + w2);
                            w2 = 3;
                        } else if (w2 != 0) {
                            sqo.y("dysmenorrhea is" + w2);
                        } else {
                            LogUtil.a("HwMenstrualManager", "dysmenorrhea is Correct");
                        }
                        hiHealthData.putDouble("menstrual_quantity", w);
                        hiHealthData.putDouble("menstrual_dysmenorrhea", w2);
                        break;
                    } else {
                        LogUtil.a("HwMenstrualManager", "flow synchronization data dictionary.");
                        break;
                    }
                case 8:
                    hiHealthData.putDouble("menstrual_physical", 2.147483647E9d);
                    break;
                case 9:
                    hiHealthData.putDouble("menstrual_sub_status", CommonUtil.w(cwdVar.c()));
                    break;
                default:
                    LogUtil.h("HwMenstrualManager", "handleBodyStatusReceive default");
                    break;
            }
        }
        list.add(hiHealthData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.f = false;
        this.i = 0;
        this.g = 0;
        this.f14291a.clear();
    }

    public void a() {
        j();
        h();
        try {
            BaseApplication.getContext().unregisterReceiver(this.d);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HwMenstrualManager", "receiver unregistered IllegalArgumentException");
        }
    }

    private static void j() {
        synchronized (e) {
            c = null;
        }
    }

    private void h() {
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.quit(false);
            this.b = null;
        }
    }

    public static boolean c() {
        boolean z;
        synchronized (e) {
            z = c == null;
        }
        return z;
    }

    /* loaded from: classes5.dex */
    class b implements Handler.Callback {
        private b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            kcu.this.c(message.what);
            return true;
        }
    }
}
