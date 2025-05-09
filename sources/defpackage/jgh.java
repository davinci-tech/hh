package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.callback.FitnessCloudCallBack;
import com.huawei.datatype.DeviceCommand;
import com.huawei.datatype.EventAlarmInfo;
import com.huawei.datatype.SmartAlarmInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataResponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.videoedit.utils.ListUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LocalBroadcast;
import health.compact.a.MagicBuild;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jgh extends HwBaseManager implements BluetoothDataReceiveCallback, DataReceiveCallback {
    private static jgh h;
    private int g;
    private IBaseResponseCallback j;
    private Context k;
    private String l;
    private jgb m;
    private int n;
    private BroadcastReceiver o;
    private ExtendHandler p;
    private jqi q;
    private jfq r;
    private int s;
    private cwl t;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13809a = new Object();
    private static final Object b = new Object();
    private static List<IBaseResponseCallback> f = new ArrayList(16);
    private static List<IBaseResponseCallback> i = new ArrayList(16);
    private static List<IBaseResponseCallback> e = new ArrayList(16);
    private static List<IBaseResponseCallback> d = new ArrayList(16);

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public boolean onDataMigrate() {
        return true;
    }

    static /* synthetic */ int b(jgh jghVar) {
        int i2 = jghVar.n;
        jghVar.n = i2 + 1;
        return i2;
    }

    private jgh(Context context) {
        super(context);
        this.n = 0;
        this.p = null;
        this.s = 0;
        this.l = "";
        this.t = new cwl();
        this.g = 5;
        this.o = new BroadcastReceiver() { // from class: jgh.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                DeviceInfo deviceInfo;
                if (context2 == null || intent == null) {
                    LogUtil.h("HwAlarmManager", "mConnectStateChangedReceiver() context or intent is null ");
                    return;
                }
                LogUtil.a("HwAlarmManager", "mConnectStateChangedReceiver() context ", context2, " intent ", intent.getAction());
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    try {
                        deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                    } catch (BadParcelableException unused) {
                        LogUtil.b("HwAlarmManager", "fuzzy test exception");
                        deviceInfo = null;
                    }
                    if (deviceInfo != null) {
                        if (jgh.this.e(deviceInfo)) {
                            jgh.this.c(deviceInfo);
                            return;
                        } else {
                            LogUtil.h("HwAlarmManager", "This device does not have the correspond capability.");
                            return;
                        }
                    }
                    LogUtil.b("HwAlarmManager", "mConnectStateChangedReceiver() deviceInfo is null ");
                }
            }
        };
        this.j = new IBaseResponseCallback() { // from class: jgh.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.a("HwAlarmManager", "mAutoSendResponseCallback() is comeback, errCode ", Integer.valueOf(i2));
            }
        };
        this.k = context;
        this.m = new jgb(context);
        jfq c2 = jfq.c();
        this.r = c2;
        if (c2 != null) {
            BroadcastManagerUtil.bFC_(this.k, this.o, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
            this.r.e(8, this);
        } else {
            LogUtil.b("HwAlarmManager", "HwAlarmManager mDeviceConfigManager is null");
        }
        this.q = jqi.a();
        this.p = HandlerCenter.yt_(new a(), "HwAlarmManager");
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo) {
        int autoDetectSwitchStatus = deviceInfo.getAutoDetectSwitchStatus();
        LogUtil.a("HwAlarmManager", "autoDetectSwitchStatus ", Integer.valueOf(autoDetectSwitchStatus));
        if (autoDetectSwitchStatus != 1) {
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            this.s = deviceConnectState;
            LogUtil.a("HwAlarmManager", "mDeviceConnect ", Integer.valueOf(deviceConnectState));
            if (deviceInfo.getDeviceConnectState() == 2) {
                String securityDeviceId = deviceInfo.getSecurityDeviceId();
                a(deviceInfo);
                a(securityDeviceId);
                return;
            }
            LogUtil.a("HwAlarmManager", "setSwitchDeviceInfo unKnow type");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "HwAlarmManager");
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("HwAlarmManager", "enter mConnectStateChangedReceiver, deviceCapabilityHashMaps is null or empty");
            return false;
        }
        DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability != null && (deviceCapability.isEventAlarm() || deviceCapability.isSmartAlarm())) {
            return true;
        }
        LogUtil.h("HwAlarmManager", "device not support event alarm or smart alarm.");
        return false;
    }

    public static jgh d(Context context) {
        jgh jghVar;
        synchronized (b) {
            if (h == null && context != null) {
                LogUtil.a("HwAlarmManager", "getInstance() context ", context);
                h = new jgh(BaseApplication.getContext());
            }
            jghVar = h;
        }
        return jghVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 8;
    }

    public void c(final List<EventAlarmInfo> list, final IBaseResponseCallback iBaseResponseCallback, final boolean z) {
        ReleaseLogUtil.e("R_HwAlarmManager", " setEventAlarm begin ");
        synchronized (c) {
            d(new IBaseResponseCallback() { // from class: jgh.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    DeviceCommand deviceCommand = new DeviceCommand();
                    deviceCommand.setServiceID(8);
                    deviceCommand.setCommandID(1);
                    deviceCommand.setmIdentify(jgh.this.l);
                    LogUtil.a("HwAlarmManager", "setEventAlarm mCurrentDeviceId: ", iyl.d().e(jgh.this.l));
                    ByteBuffer e2 = jgh.this.m.e(list, true);
                    deviceCommand.setDataLen(e2.array().length);
                    deviceCommand.setDataContent(e2.array());
                    jgh.this.r.b(deviceCommand);
                    LogUtil.a("HwAlarmManager", "isNeedRespond ", Boolean.valueOf(z));
                    if (z) {
                        synchronized (jgh.l()) {
                            jgh.f.add(iBaseResponseCallback);
                        }
                        jgh.this.e((List<EventAlarmInfo>) list, (IBaseResponseCallback) null);
                        return;
                    }
                    jgh.this.e((List<EventAlarmInfo>) list, iBaseResponseCallback);
                }
            });
        }
    }

    private int d(SmartAlarmInfo smartAlarmInfo) {
        int smartAlarmEnable = smartAlarmInfo.getSmartAlarmEnable();
        String b2 = SharedPreferenceManager.b(this.k, String.valueOf(10022), "ONCE_SMART_ALARM_INFO");
        LogUtil.a("HwAlarmManager", "once onceSmartAlarmIsOver json ", b2);
        if (!TextUtils.isEmpty(b2)) {
            smartAlarmEnable = d(smartAlarmInfo, (List<SmartAlarmInfo>) new Gson().fromJson(b2, new TypeToken<List<SmartAlarmInfo>>() { // from class: jgh.6
            }.getType()));
        } else {
            LogUtil.h("HwAlarmManager", "once onceSmartAlarmIsOver json is null");
        }
        ReleaseLogUtil.e("R_HwAlarmManager", "once onceSmartAlarmIsOver smartAlarmEnable ,", Integer.valueOf(smartAlarmEnable));
        return smartAlarmEnable;
    }

    private int d(SmartAlarmInfo smartAlarmInfo, List<SmartAlarmInfo> list) {
        int smartAlarmEnable = smartAlarmInfo.getSmartAlarmEnable();
        if (list != null && !list.isEmpty()) {
            for (SmartAlarmInfo smartAlarmInfo2 : list) {
                if (smartAlarmInfo2.getSmartAlarmIndex() == smartAlarmInfo.getSmartAlarmIndex()) {
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    LogUtil.a("HwAlarmManager", "once curTime ", Long.valueOf(currentTimeMillis));
                    if (currentTimeMillis >= smartAlarmInfo2.getSmartAlarmTime()) {
                        smartAlarmEnable = 0;
                    }
                }
            }
        }
        return smartAlarmEnable;
    }

    public void b(List<EventAlarmInfo> list) {
        ReleaseLogUtil.e("R_HwAlarmManager", "once eventAlarmInfoList ", list.toString());
        ArrayList arrayList = new ArrayList(16);
        for (EventAlarmInfo eventAlarmInfo : list) {
            if (eventAlarmInfo.getEventAlarmRepeat() == 0 && eventAlarmInfo.getEventAlarmEnable() == 1) {
                arrayList.add(eventAlarmInfo);
            }
        }
        ReleaseLogUtil.e("R_HwAlarmManager", "once eventAlarm saveAlarmInfoList.size() ", Integer.valueOf(arrayList.size()));
        if (arrayList.isEmpty()) {
            return;
        }
        StorageParams storageParams = new StorageParams(0);
        String json = new Gson().toJson(arrayList);
        LogUtil.a("HwAlarmManager", "once eventAlarm saveOnceAlarm() json ", json);
        SharedPreferenceManager.e(this.k, String.valueOf(10022), "ONCE_EVENT_ALARM_INFO", json, storageParams);
    }

    public void b(String str, List<EventAlarmInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(8);
        deviceCommand.setCommandID(1);
        deviceCommand.setmIdentify(str);
        ByteBuffer e2 = this.m.e(list, false);
        deviceCommand.setDataLen(e2.array().length);
        deviceCommand.setDataContent(e2.array());
        this.r.b(deviceCommand);
        ReleaseLogUtil.e("R_HwAlarmManager", "setDeviceEventAlarm is: ", e2.array());
        synchronized (l()) {
            f.clear();
            f.add(iBaseResponseCallback);
        }
    }

    public void b(final List<SmartAlarmInfo> list, final IBaseResponseCallback iBaseResponseCallback, final boolean z) {
        ReleaseLogUtil.e("R_HwAlarmManager", " setSmartAlarm()");
        synchronized (f13809a) {
            a(new IBaseResponseCallback() { // from class: jgh.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    ReleaseLogUtil.e("R_HwAlarmManager", "setSmartAlarm() before smartAlarmInfoList.size() ", Integer.valueOf(list.size()));
                    if (list.size() > 1) {
                        SmartAlarmInfo smartAlarmInfo = (SmartAlarmInfo) list.get(0);
                        list.clear();
                        list.add(smartAlarmInfo);
                    }
                    ReleaseLogUtil.e("R_HwAlarmManager", "setSmartAlarm() after smartAlarmList.size() ", Integer.valueOf(list.size()));
                    DeviceCommand deviceCommand = new DeviceCommand();
                    deviceCommand.setServiceID(8);
                    deviceCommand.setCommandID(2);
                    deviceCommand.setmIdentify(jgh.this.l);
                    LogUtil.a("HwAlarmManager", "setSmartAlarm mCurrentDeviceId");
                    ByteBuffer c2 = jgh.this.c((List<SmartAlarmInfo>) list);
                    deviceCommand.setDataLen(c2.array().length);
                    deviceCommand.setDataContent(c2.array());
                    jgh.this.r.b(deviceCommand);
                    if (z) {
                        synchronized (jgh.k()) {
                            jgh.i.add(iBaseResponseCallback);
                        }
                        jgh.this.d((List<SmartAlarmInfo>) list, (IBaseResponseCallback) null);
                        return;
                    }
                    jgh.this.d((List<SmartAlarmInfo>) list, iBaseResponseCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ByteBuffer c(List<SmartAlarmInfo> list) {
        ByteBuffer allocate;
        byte[] a2;
        int d2;
        int size = list.size() * 18;
        if (size > 255) {
            allocate = ByteBuffer.allocate(size + 3);
            a2 = cvx.a(cvx.a(size));
        } else {
            allocate = ByteBuffer.allocate(size + 2);
            a2 = cvx.a(cvx.e(size));
        }
        allocate.put((byte) -127);
        allocate.put(a2);
        for (SmartAlarmInfo smartAlarmInfo : list) {
            allocate.put((byte) -126);
            allocate.put(cvx.a(cvx.e(16)));
            allocate.put((byte) 3);
            allocate.put((byte) 1);
            allocate.put(cvx.a(cvx.e(smartAlarmInfo.getSmartAlarmIndex())));
            allocate.put((byte) 4);
            allocate.put((byte) 1);
            if (smartAlarmInfo.getSmartAlarmRepeat() == 0 && smartAlarmInfo.getSmartAlarmEnable() == 1 && (d2 = d(smartAlarmInfo)) == 0) {
                smartAlarmInfo.setSmartAlarmEnable(d2);
            }
            allocate.put(cvx.a(cvx.e(smartAlarmInfo.getSmartAlarmEnable())));
            allocate.put((byte) 5);
            allocate.put((byte) 2);
            allocate.put(cvx.a(cvx.a(cvx.j((smartAlarmInfo.getSmartAlarmStartTimeHour() * 100) + smartAlarmInfo.getSmartAlarmStartTimeMins()))));
            allocate.put((byte) 6);
            allocate.put((byte) 1);
            allocate.put(cvx.a(cvx.e(smartAlarmInfo.getSmartAlarmRepeat())));
            allocate.put((byte) 7);
            allocate.put((byte) 1);
            allocate.put(cvx.a(cvx.e(smartAlarmInfo.getSmartAlarmAheadTime())));
        }
        return allocate;
    }

    public void d(String str, List<SmartAlarmInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("R_HwAlarmManager", " setDeviceSmartAlarm()");
        LogUtil.a("HwAlarmManager", "setSmartAlarm() before smartAlarmInfoList.size() ", Integer.valueOf(list.size()));
        if (list.size() > 1) {
            SmartAlarmInfo smartAlarmInfo = list.get(0);
            list.clear();
            list.add(smartAlarmInfo);
        }
        LogUtil.a("HwAlarmManager", "setSmartAlarm() after smartAlarmInfoList.size() ", Integer.valueOf(list.size()));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(8);
        deviceCommand.setCommandID(2);
        deviceCommand.setmIdentify(str);
        e(list, deviceCommand);
        ReleaseLogUtil.e("R_HwAlarmManager", "setDeviceSmartAlarm is: ", list);
        synchronized (k()) {
            i.clear();
            i.add(iBaseResponseCallback);
        }
    }

    private void e(List<SmartAlarmInfo> list, DeviceCommand deviceCommand) {
        ByteBuffer allocate;
        byte[] a2;
        int size = list.size() * 18;
        if (size > 255) {
            allocate = ByteBuffer.allocate(size + 3);
            a2 = cvx.a(cvx.a(size));
        } else {
            allocate = ByteBuffer.allocate(size + 2);
            a2 = cvx.a(cvx.e(size));
        }
        allocate.put((byte) -127);
        allocate.put(a2);
        c(list, deviceCommand, allocate);
    }

    private void c(List<SmartAlarmInfo> list, DeviceCommand deviceCommand, ByteBuffer byteBuffer) {
        for (SmartAlarmInfo smartAlarmInfo : list) {
            byteBuffer.put((byte) -126);
            byteBuffer.put(cvx.a(cvx.e(16)));
            byteBuffer.put((byte) 3);
            byteBuffer.put((byte) 1);
            byteBuffer.put(cvx.a(cvx.e(smartAlarmInfo.getSmartAlarmIndex())));
            byteBuffer.put((byte) 4);
            byteBuffer.put((byte) 1);
            byteBuffer.put(cvx.a(cvx.e(smartAlarmInfo.getSmartAlarmEnable())));
            byteBuffer.put((byte) 5);
            byteBuffer.put((byte) 2);
            byteBuffer.put(cvx.a(cvx.a(cvx.j((smartAlarmInfo.getSmartAlarmStartTimeHour() * 100) + smartAlarmInfo.getSmartAlarmStartTimeMins()))));
            byteBuffer.put((byte) 6);
            byteBuffer.put((byte) 1);
            byteBuffer.put(cvx.a(cvx.e(smartAlarmInfo.getSmartAlarmRepeat())));
            byteBuffer.put((byte) 7);
            byteBuffer.put((byte) 1);
            byteBuffer.put(cvx.a(cvx.e(smartAlarmInfo.getSmartAlarmAheadTime())));
            deviceCommand.setDataLen(byteBuffer.array().length);
            deviceCommand.setDataContent(byteBuffer.array());
            this.r.b(deviceCommand);
        }
    }

    private void d(byte[] bArr) {
        int a2;
        LogUtil.a("HwAlarmManager", "Alarm manager getResult ", cvx.d(bArr));
        byte b2 = bArr[1];
        int i2 = 0;
        if (b2 == 1 || b2 == 2) {
            a2 = a(bArr);
            ReleaseLogUtil.e("R_HwAlarmManager", "Alarm manager info ", Integer.valueOf(a2), "message[1] ", Byte.valueOf(bArr[1]));
            if (a2 != 100000) {
                i2 = -1;
            }
        } else {
            a2 = 0;
        }
        b(bArr, i2, a2);
        if (i2 != 0) {
            a("");
            LogUtil.a("HwAlarmManager", "bluetooth send error, clear mac");
        }
    }

    private void b(byte[] bArr, int i2, int i3) {
        ReleaseLogUtil.e("R_HwAlarmManager", "commandId Received is ", Byte.valueOf(bArr[1]));
        byte b2 = bArr[1];
        if (b2 == 1) {
            synchronized (l()) {
                if (!f.isEmpty()) {
                    f.get(0).d(i2, Integer.valueOf(i3));
                    f.remove(0);
                }
            }
            return;
        }
        if (b2 == 2) {
            synchronized (k()) {
                if (!i.isEmpty()) {
                    i.get(0).d(i2, Integer.valueOf(i3));
                    i.remove(0);
                }
            }
            return;
        }
        if (b2 == 3) {
            synchronized (m()) {
                if (!e.isEmpty()) {
                    e.get(0).d(i2, cvx.d(bArr));
                    e.remove(0);
                }
            }
            return;
        }
        if (b2 == 4) {
            synchronized (o()) {
                if (!d.isEmpty()) {
                    d.get(0).d(i2, cvx.d(bArr));
                    d.remove(0);
                }
            }
            return;
        }
        if (b2 == 5) {
            c(cvx.d(bArr));
        } else if (b2 == 8) {
            b(cvx.d(bArr));
        } else {
            LogUtil.a("HwAlarmManager", "switchMessage unKnow type");
        }
    }

    private int a(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (d2 != null && d2.length() > 8) {
            try {
                return Integer.parseInt(d2.substring(8, d2.length()), 16);
            } catch (NumberFormatException e2) {
                LogUtil.a("HwAlarmManager", e2.getMessage());
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object l() {
        List<IBaseResponseCallback> list;
        synchronized (jgh.class) {
            list = f;
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object k() {
        List<IBaseResponseCallback> list;
        synchronized (jgh.class) {
            list = i;
        }
        return list;
    }

    private static Object m() {
        List<IBaseResponseCallback> list;
        synchronized (jgh.class) {
            list = e;
        }
        return list;
    }

    private static Object o() {
        List<IBaseResponseCallback> list;
        synchronized (jgh.class) {
            list = d;
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<EventAlarmInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("R_HwAlarmManager", "eventAlarmInfoList=" + list + ";mResponseCallback=" + iBaseResponseCallback);
        jgg jggVar = new jgg();
        jggVar.e(iBaseResponseCallback);
        jggVar.c(list);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = jggVar;
        this.p.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<SmartAlarmInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("R_HwAlarmManager", "smartAlarmInfoList ", list, " ; baseResponseCallback ", iBaseResponseCallback);
        jgg jggVar = new jgg();
        jggVar.e(iBaseResponseCallback);
        jggVar.c(list);
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = jggVar;
        this.p.sendMessage(obtain);
    }

    private void f() {
        DeviceInfo deviceInfo;
        LogUtil.a("HwAlarmManager", "autoSendCommend() enter.");
        if (this.s != 2) {
            LogUtil.a("HwAlarmManager", "autoSendCommend device is not mConnected, return");
            return;
        }
        List<DeviceInfo> b2 = jpt.b("HwAlarmManager");
        if (b2 != null) {
            Iterator<DeviceInfo> it = b2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    deviceInfo = null;
                    break;
                }
                deviceInfo = it.next();
                if (deviceInfo.getAutoDetectSwitchStatus() != 1) {
                    LogUtil.a("HwAlarmManager", "autoSendCommend autoDetectSwitchStatus not equal 1");
                    break;
                }
            }
            a(deviceInfo);
            return;
        }
        LogUtil.a("HwAlarmManager", "autoSendCommend getConnectDeviceList is null");
    }

    private void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwAlarmManager", "autoSendCommend info is null");
            return;
        }
        if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
            LogUtil.a("HwAlarmManager", "autoSendCommend info is RUN_WORK_MODE");
            return;
        }
        DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
        if (e2 != null) {
            this.l = deviceInfo.getDeviceIdentify();
            boolean isEventAlarm = e2.isEventAlarm();
            boolean isSmartAlarm = e2.isSmartAlarm();
            boolean isSupportChangeAlarm = e2.isSupportChangeAlarm();
            ReleaseLogUtil.e("R_HwAlarmManager", "autoSendCommend isSupportChangeAlarm: ", Boolean.valueOf(isSupportChangeAlarm), " isSupportEventAlarm ", Boolean.valueOf(isEventAlarm), ", isSupportSmartAlarm ", Boolean.valueOf(isSmartAlarm));
            if (isSupportChangeAlarm) {
                return;
            }
            b(isEventAlarm, isSmartAlarm);
            return;
        }
        ReleaseLogUtil.d("R_HwAlarmManager", "autoSendCommend CapabilityUtils.getDeviceCapability() is null!!");
    }

    private void b(boolean z, boolean z2) {
        if (z) {
            d(new IBaseResponseCallback() { // from class: jgh.11
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    synchronized (jgh.c) {
                        List<EventAlarmInfo> arrayList = new ArrayList<>(16);
                        if (i2 == 0 && (obj instanceof List)) {
                            arrayList = (List) obj;
                        }
                        if (TextUtils.isEmpty(SharedPreferenceManager.b(jgh.this.k, String.valueOf(10022), "ONCE_EVENT_ALARM_INFO"))) {
                            jgh.this.b(arrayList);
                        }
                        jgh jghVar = jgh.this;
                        jghVar.c(arrayList, jghVar.j, true);
                        ReleaseLogUtil.e("R_HwAlarmManager", "autoSendCommend() setEventAlarm finish.");
                    }
                }
            });
        }
        if (z2) {
            a(new IBaseResponseCallback() { // from class: jgh.13
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    List<SmartAlarmInfo> arrayList = new ArrayList<>(16);
                    if (i2 == 0 && (obj instanceof List)) {
                        arrayList = (List) obj;
                    }
                    LogUtil.a("HwAlarmManager", "autoSendCommend() smartAlarmInfoList.size() ", Integer.valueOf(arrayList.size()));
                    if (arrayList.isEmpty()) {
                        arrayList.add(new SmartAlarmInfo());
                    }
                    jgh jghVar = jgh.this;
                    jghVar.b(arrayList, jghVar.j, true);
                    ReleaseLogUtil.e("R_HwAlarmManager", "autoSendCommend() setSmartAlarm finish.");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(File file) {
        jgd jgdVar = new jgd();
        String a2 = msb.a(file);
        ReleaseLogUtil.e("R_HwAlarmManager", "parseLegalHolidayInfo jsonResult is,", a2);
        if (TextUtils.isEmpty(a2) || Constants.NULL.equalsIgnoreCase(a2)) {
            ReleaseLogUtil.e("R_HwAlarmManager", "parseLegalHolidayInfo jsonResult is empty");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(a2);
            JSONArray jSONArray = jSONObject.getJSONArray("belowVersion");
            JSONObject jSONObject2 = jSONObject.getJSONObject("normalVersion");
            boolean z = true;
            if (jSONArray != null && jSONArray.length() >= 1 && jSONObject2 != null) {
                c(jSONObject2, jgdVar);
                DeviceInfo a3 = jpt.a("HwAlarmManager");
                if (a3 == null) {
                    ReleaseLogUtil.e("R_HwAlarmManager", "curDeviceInfo is null");
                    return;
                }
                String deviceModel = a3.getDeviceModel();
                String softVersion = a3.getSoftVersion();
                if (deviceModel != null && softVersion != null) {
                    String str = deviceModel.split(com.huawei.openalliance.ad.constant.Constants.LINK)[0];
                    String str2 = softVersion.split("\\(")[0];
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        ReleaseLogUtil.e("R_HwAlarmManager", "deviceModel is:", str, "deviceSoftVersion is:", str2);
                        int i2 = 0;
                        while (true) {
                            if (i2 >= jSONArray.length()) {
                                break;
                            }
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                            if (jSONObject3 != null && !jSONObject3.isNull("deviceVersion")) {
                                String string = jSONObject3.getString("deviceVersion");
                                if (str.equals(jSONObject3.getString("product")) && b(str2, string)) {
                                    c(jSONObject3, jgdVar);
                                    break;
                                }
                            }
                            i2++;
                        }
                        ReleaseLogUtil.e("R_HwAlarmManager", "year is:", jgdVar.a() + "version is:", jgdVar.b() + "freeDay is:", jgdVar.d() + "workDay is:", jgdVar.c());
                        b(jgdVar.a(), jgdVar.b(), jgdVar.c(), jgdVar.d());
                        return;
                    }
                    ReleaseLogUtil.d("R_HwAlarmManager", "deviceModel is null or deviceSoftVersion is null.");
                    b(jgdVar.a(), jgdVar.b(), jgdVar.c(), jgdVar.d());
                    return;
                }
                ReleaseLogUtil.e("R_HwAlarmManager", "deviceModel is null or deviceSoftVersion is null");
                b(jgdVar.a(), jgdVar.b(), jgdVar.c(), jgdVar.d());
                return;
            }
            Object[] objArr = new Object[4];
            objArr[0] = "belowVersion is,";
            objArr[1] = Boolean.valueOf(jSONArray == null);
            objArr[2] = "normalVersionObject is,";
            if (jSONObject2 != null) {
                z = false;
            }
            objArr[3] = Boolean.valueOf(z);
            ReleaseLogUtil.e("R_HwAlarmManager", objArr);
        } catch (JSONException e2) {
            ReleaseLogUtil.d("R_HwAlarmManager", "parseLegalHolidayInfo e: ", ExceptionUtils.d(e2));
        }
    }

    private boolean b(String str, String str2) {
        if (str == null || str2 == null) {
            ReleaseLogUtil.e("R_HwAlarmManager", "deviceVersionStr is null or configVersionStr is null");
            return false;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int length = split.length;
        int[] iArr = new int[length];
        int length2 = split2.length;
        int[] iArr2 = new int[length2];
        for (int i2 = 0; i2 < length; i2++) {
            try {
                iArr[i2] = Integer.parseInt(split[i2]);
            } catch (NumberFormatException e2) {
                ReleaseLogUtil.d("R_HwAlarmManager", "compareVersion e: ", ExceptionUtils.d(e2));
                return false;
            }
        }
        for (int i3 = 0; i3 < length2; i3++) {
            iArr2[i3] = Integer.parseInt(split2[i3]);
        }
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = iArr[i5];
            int i7 = iArr2[i5];
            if (i6 > i7) {
                return false;
            }
            if (i6 < i7 || (i4 = i4 + 1) == length) {
                return true;
            }
        }
        return false;
    }

    private void c(JSONObject jSONObject, jgd jgdVar) throws JSONException {
        if (jSONObject.isNull("year") || jSONObject.isNull("version") || jSONObject.isNull("workday") || jSONObject.isNull("freeday")) {
            return;
        }
        jgdVar.b(jSONObject.getInt("year"));
        jgdVar.e(jSONObject.getString("version"));
        jgdVar.b(e(jSONObject.getJSONArray("workday")));
        jgdVar.d(e(jSONObject.getJSONArray("freeday")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i2) {
        ReleaseLogUtil.e("R_HwAlarmManager", "enter downloadSkipHolidayConfig retryCount = ", Integer.valueOf(i2));
        if (i2 < 0) {
            ReleaseLogUtil.d("R_HwAlarmManager", "downloadSkipHolidayConfig retryCount < 0");
            n();
        } else {
            drd.e(new dql("com.huawei.health_skip_holiday_config", new HashMap()), "skipHolidayConfig", 1, new DownloadCallback<File>() { // from class: jgh.14
                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onFinish(File file) {
                    LogUtil.a("HwAlarmManager", "downloadSkipHolidayConfig success, result: ", file.toString());
                    jgh.this.c(file);
                }

                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onProgress(long j, long j2, boolean z, String str) {
                    LogUtil.a("HwAlarmManager", "downloadSkipHolidayConfig onProgress, already: ", Long.valueOf(j), ", total: " + j2, ", fileId: ", str);
                }

                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onFail(int i3, Throwable th) {
                    ReleaseLogUtil.c("R_HwAlarmManager", "downloadSkipHolidayConfig failed,", th.getMessage());
                    jgh.this.c(i2 - 1);
                }
            });
        }
    }

    private void n() {
        LogUtil.a("HwAlarmManager", "getDefaultPath");
        String d2 = drd.d("com.huawei.health_skip_holiday_config", "skipHolidayConfig", "json");
        if (TextUtils.isEmpty(d2)) {
            ReleaseLogUtil.d("R_HwAlarmManager", "DefaultFilePath is null");
        } else {
            LogUtil.a("HwAlarmManager", "DefaultPath :", d2);
            c(new File(d2));
        }
    }

    private void b(int i2, String str, String str2, String str3) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(8);
        deviceCommand.setCommandID(8);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(2));
        sb.append(cvx.e(2));
        sb.append(cvx.e(i2));
        sb.append(cvx.e(3));
        sb.append(cvx.e(str.length()));
        sb.append(cvx.c(str));
        sb.append(cvx.e(4));
        sb.append(cvx.d(str2.length()));
        sb.append(cvx.c(str2));
        sb.append(cvx.e(5));
        sb.append(cvx.d(str3.length()));
        sb.append(cvx.c(str3));
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setDataLength(cvx.a(sb.toString()).length);
        LogUtil.a("HwAlarmManager", "sendHoliday, deviceCommand:", deviceCommand.toString());
        this.r.b(deviceCommand);
    }

    private String e(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() < 1) {
            ReleaseLogUtil.e("R_HwAlarmManager", "array is null");
            return "";
        }
        return jSONArray.toString().replace("[", "").replace("]", "");
    }

    public void b(String str) {
        ReleaseLogUtil.e("HwAlarmManager", "handleLegalHolidayDataCommand");
        if (str == null || str.length() < 8) {
            return;
        }
        if ("7F04".equalsIgnoreCase(str.substring(4, 8))) {
            LogUtil.h("HwAlarmManager", "getResult error 7F04");
        } else {
            c(1);
        }
    }

    public void a(String str, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(8);
        deviceCommand.setCommandID(3);
        deviceCommand.setmIdentify(str);
        String str2 = cvx.e(1) + cvx.e(0);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        this.r.b(deviceCommand);
        ReleaseLogUtil.e("R_HwAlarmManager", "getDeviceEventAlarm finish.");
        synchronized (m()) {
            e.clear();
            e.add(iBaseResponseCallback);
        }
    }

    public void c(String str, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(8);
        deviceCommand.setCommandID(4);
        deviceCommand.setmIdentify(str);
        String str2 = cvx.e(1) + cvx.e(0);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        this.r.b(deviceCommand);
        ReleaseLogUtil.e("R_HwAlarmManager", "getDeviceSmartAlarm finish.");
        synchronized (o()) {
            d.clear();
            d.add(iBaseResponseCallback);
        }
    }

    public List<EventAlarmInfo> d(Object obj) {
        ArrayList arrayList = new ArrayList(16);
        if (!(obj instanceof String)) {
            return arrayList;
        }
        String str = (String) obj;
        if (str.length() <= 4) {
            return arrayList;
        }
        String substring = str.substring(4, str.length());
        ReleaseLogUtil.e("R_HwAlarmManager", "commandHex , ", substring);
        try {
            for (cwe cweVar : this.t.a(substring).a()) {
                LogUtil.a("HwAlarmManager", "tlvFatherInfos ", Integer.valueOf(cweVar.a().size()));
                b(arrayList, cweVar);
            }
        } catch (cwg e2) {
            LogUtil.b("HwAlarmManager", "COMMAND_ID_GET_DATE error tlvException ", e2);
        }
        return arrayList;
    }

    private void b(List<EventAlarmInfo> list, cwe cweVar) {
        for (cwe cweVar2 : cweVar.a()) {
            EventAlarmInfo eventAlarmInfo = new EventAlarmInfo();
            for (cwd cwdVar : cweVar2.e()) {
                switch (CommonUtil.a(cwdVar.e(), 16)) {
                    case 3:
                        eventAlarmInfo.setEventAlarmIndex(d(cwdVar.c()));
                        break;
                    case 4:
                        eventAlarmInfo.setEventAlarmEnable(d(cwdVar.c()));
                        break;
                    case 5:
                        String c2 = cwdVar.c();
                        if (c2.length() >= 0 && c2.length() >= 2 && c2.length() >= 4) {
                            eventAlarmInfo.setEventAlarmStartTimeHour(d(c2.substring(0, 2)));
                            eventAlarmInfo.setEventAlarmStartTimeMins(d(c2.substring(2, 4)));
                            break;
                        } else {
                            LogUtil.h("HwAlarmManager", "setEventTlvFatherInfo valueString timeIndex outOfBounds");
                            break;
                        }
                    case 6:
                        eventAlarmInfo.setEventAlarmRepeat(d(cwdVar.c()));
                        break;
                    case 7:
                        eventAlarmInfo.setEventAlarmName(cvx.e(cwdVar.c()));
                        break;
                    case 8:
                        eventAlarmInfo.setEventAlarmLegalHolidayEnable(d(cwdVar.c()));
                        break;
                    case 9:
                        eventAlarmInfo.setCreateTimestamp(CommonUtil.y(cwdVar.c()));
                        break;
                    case 10:
                        eventAlarmInfo.setModifiedTimestamp(CommonUtil.y(cwdVar.c()));
                        break;
                    default:
                        LogUtil.a("HwAlarmManager", "setEventTlvFatherInfo unKnow type");
                        break;
                }
            }
            if (cweVar2.e().size() >= 2) {
                list.add(eventAlarmInfo);
                ReleaseLogUtil.e("R_HwAlarmManager", "eventAlarmInfo ", eventAlarmInfo.toString());
            } else {
                LogUtil.a("HwAlarmManager", "add eventAlarmInfo failed");
            }
        }
        LogUtil.a("HwAlarmManager", "eventAlarmInfos ", Integer.valueOf(list.size()));
    }

    private int d(String str) {
        try {
            return Integer.parseInt(str, 16);
        } catch (NumberFormatException e2) {
            LogUtil.a("HwAlarmManager", e2.getMessage());
            return 0;
        }
    }

    public List<SmartAlarmInfo> b(Object obj) {
        ArrayList arrayList = new ArrayList(16);
        String str = obj instanceof String ? (String) obj : null;
        if (str != null && str.length() > 4) {
            String substring = str.substring(4, str.length());
            ReleaseLogUtil.e("R_HwAlarmManager", "commandHex ", substring);
            try {
                for (cwe cweVar : this.t.a(substring).a()) {
                    LogUtil.a("HwAlarmManager", "tlvFatherInfos ", Integer.valueOf(cweVar.a().size()));
                    c(arrayList, cweVar);
                }
            } catch (cwg e2) {
                LogUtil.b("HwAlarmManager", "COMMAND_ID_GET_DATE error tlvException ", e2);
            }
        }
        return arrayList;
    }

    private void c(List<SmartAlarmInfo> list, cwe cweVar) {
        char c2;
        Iterator<cwe> it = cweVar.a().iterator();
        while (it.hasNext()) {
            char c3 = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (cwd cwdVar : it.next().e()) {
                try {
                    int parseInt = Integer.parseInt(cwdVar.e(), 16);
                    if (parseInt == 3) {
                        c2 = c3;
                        i3 = Integer.parseInt(cwdVar.c(), 16);
                    } else if (parseInt == 4) {
                        c2 = c3;
                        i4 = Integer.parseInt(cwdVar.c(), 16);
                    } else if (parseInt != 5) {
                        if (parseInt == 6) {
                            i6 = Integer.parseInt(cwdVar.c(), 16);
                        } else if (parseInt == 7) {
                            try {
                                SmartAlarmInfo e2 = e(i3, i4, i2, i5, i6);
                                e2.setSmartAlarmAheadTime(Integer.parseInt(cwdVar.c(), 16));
                                try {
                                    list.add(e2);
                                    LogUtil.a("HwAlarmManager", "smartAlarmInfo ", e2.toString());
                                } catch (NumberFormatException e3) {
                                    e = e3;
                                    c2 = 0;
                                    LogUtil.a("HwAlarmManager", "setSmartAlarmTlvFatherInfo NumberFormatException", e.getMessage());
                                    c3 = c2;
                                }
                            } catch (NumberFormatException e4) {
                                e = e4;
                            }
                        } else {
                            Object[] objArr = new Object[1];
                            objArr[c3] = "setSmartAlarmTlvFatherInfo unKnow type";
                            LogUtil.a("HwAlarmManager", objArr);
                            c2 = c3;
                        }
                        c2 = 0;
                    } else {
                        String c4 = cwdVar.c();
                        if (c4.length() >= 0 && c4.length() >= 2 && c4.length() >= 4) {
                            i2 = Integer.parseInt(c4.substring(0, 2), 16);
                            i5 = Integer.parseInt(c4.substring(2, 4), 16);
                            c2 = 0;
                        } else {
                            Object[] objArr2 = new Object[1];
                            c2 = 0;
                            try {
                                objArr2[0] = "setSmartAlarmTlvFatherInfo valueString timeIndex OutOfBounds";
                                LogUtil.h("HwAlarmManager", objArr2);
                            } catch (NumberFormatException e5) {
                                e = e5;
                                LogUtil.a("HwAlarmManager", "setSmartAlarmTlvFatherInfo NumberFormatException", e.getMessage());
                                c3 = c2;
                            }
                        }
                    }
                } catch (NumberFormatException e6) {
                    e = e6;
                    c2 = c3;
                }
                c3 = c2;
            }
        }
        LogUtil.a("HwAlarmManager", "msmartAlarmInfoList ", Integer.valueOf(list.size()));
    }

    public void d(int i2) {
        this.g = i2;
    }

    public int i() {
        return this.g;
    }

    private void e(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwAlarmManager", "parseAlarmNumberData id is empty.");
            return;
        }
        if (str2 == null) {
            this.g = 5;
            LogUtil.h("HwAlarmManager", "alarmNumber is default value.");
            return;
        }
        LogUtil.a("HwAlarmManager", "configData: ", str2);
        try {
            List<cwd> e2 = new cwl().a(str2).e();
            if (ListUtil.isEmpty(e2)) {
                LogUtil.h("HwAlarmManager", "parseAlarmNumberData tlvList is empty.");
                return;
            }
            for (cwd cwdVar : e2) {
                if (CommonUtil.w(cwdVar.e()) == 1) {
                    int w = CommonUtil.w(cwdVar.c());
                    this.g = w;
                    ReleaseLogUtil.e("R_HwAlarmManager", "parseAlarmNumberData AlarmMaxNumber is ", Integer.valueOf(w));
                } else {
                    LogUtil.h("HwAlarmManager", "parseAlarmNumberData default branches.");
                }
            }
        } catch (cwg e3) {
            LogUtil.b("HwAlarmManager", "parseAlarmNumberData tlvException: ", ExceptionUtils.d(e3));
        } catch (NumberFormatException e4) {
            LogUtil.b("HwAlarmManager", "parseAlarmNumberData numberFormatException: ", ExceptionUtils.d(e4));
        }
    }

    private SmartAlarmInfo e(int i2, int i3, int i4, int i5, int i6) {
        SmartAlarmInfo smartAlarmInfo = new SmartAlarmInfo();
        smartAlarmInfo.setSmartAlarmIndex(i2);
        smartAlarmInfo.setSmartAlarmEnable(i3);
        smartAlarmInfo.setSmartAlarmStartTimeHour(i4);
        smartAlarmInfo.setSmartAlarmStartTimeMins(i5);
        smartAlarmInfo.setSmartAlarmRepeat(i6);
        return smartAlarmInfo;
    }

    public void c(String str) {
        LogUtil.a("HwAlarmManager", "commandHex ", str);
        if (str == null || str.length() < 8) {
            LogUtil.h("HwAlarmManager", "hex is null or hex.length less than eight.");
            return;
        }
        int d2 = d(str.substring(8, str.length()));
        if (d2 == 1) {
            this.n = 0;
            j();
        } else {
            if (d2 == 2 || d2 == 3) {
                return;
            }
            LogUtil.a("HwAlarmManager", "smartAlarmPut unKnow Type");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        DeviceInfo a2 = jpt.a("HwAlarmManager");
        if (a2 != null) {
            this.q.getSwitchSetting("intelligent_home_linkage", a2.getDeviceIdentify(), new IBaseResponseCallback() { // from class: jgh.15
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("HwAlarmManager", "activeTransDeviceData errorCode = ", Integer.valueOf(i2));
                    if (i2 == 0 && (obj instanceof String)) {
                        jgh.this.e((String) obj);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (str.contains("&&")) {
            String[] split = str.split("&&");
            LogUtil.a("HwAlarmManager", "INTELLIGENT_HOME_LINKAGE new splits ", Integer.valueOf(split.length));
            if (split.length == 5) {
                String str2 = split[0];
                String str3 = split[1];
                String str4 = split[2];
                String str5 = split[3];
                String str6 = split[4];
                LogUtil.a("HwAlarmManager", "checkDevidIsPermanent devId: ", str2, " expiresIn: ", str3, " proId: ", str4, " enable: ", str5, " isClick: ", str6);
                if (Boolean.parseBoolean(str5) && Boolean.parseBoolean(str6)) {
                    h(str2);
                }
            }
        }
    }

    private void h(String str) {
        Message obtain = Message.obtain();
        obtain.what = 7;
        this.p.sendMessage(obtain, 60000L);
        jgy.e(this.k).c("sleep", "2", str, new FitnessCloudCallBack() { // from class: jgh.1
            @Override // com.huawei.callback.FitnessCloudCallBack
            public void onResponce(Object obj) {
                if (obj != null) {
                    if (((TransferDeviceDataResponse) obj).getResultCode().intValue() == 0) {
                        LogUtil.a("HwAlarmManager", "transDeviceData is success");
                        jgh.this.p.removeMessages(7);
                    } else {
                        LogUtil.a("HwAlarmManager", "transDeviceData is fail");
                    }
                }
            }
        });
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090019.value(), new HashMap(16), 0);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        try {
            h();
            this.k.unregisterReceiver(this.o);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HwAlarmManager", "mConnectStateChangedReceiver is not registered");
        }
        ExtendHandler extendHandler = this.p;
        if (extendHandler != null) {
            extendHandler.quit(false);
            this.p = null;
        }
        r();
        LogUtil.a("HwAlarmManager", "onDestroy()");
    }

    private static void h() {
        synchronized (b) {
            h = null;
        }
        synchronized (l()) {
            f.clear();
        }
        synchronized (k()) {
            i.clear();
        }
        synchronized (m()) {
            e.clear();
        }
        synchronized (o()) {
            d.clear();
        }
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i2, DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("HwAlarmManager", "Alarm manager receive data ", cvx.d(bArr));
        d(bArr);
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i2, DeviceInfo deviceInfo, cvr cvrVar) {
        ReleaseLogUtil.e("R_HwAlarmManager", "onDataReceived: ", Integer.valueOf(i2));
        if (deviceInfo == null || b(cvrVar)) {
            LogUtil.h("HwAlarmManager", "device is null or isCheckSampleError.");
            return;
        }
        if (cvrVar instanceof cvq) {
            List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
            if (configInfoList == null || configInfoList.size() == 0) {
                LogUtil.h("HwAlarmManager", "onDataReceived sampleConfigInfoList empty.");
                return;
            }
            cvn cvnVar = configInfoList.get(0);
            LogUtil.a("HwAlarmManager", "onDataReceived configAction", Integer.valueOf(cvnVar.e()));
            if (cvnVar.e() == 2) {
                e(deviceInfo.getDeviceIdentify(), cvx.d(cvnVar.b()));
            }
        }
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                LogUtil.a("HwAlarmManager", "INSERT_EVENT_ALARM");
                final jgg jggVar = (jgg) message.obj;
                synchronized (jgh.c) {
                    List<EventAlarmInfo> list = (List) jggVar.a();
                    Iterator<EventAlarmInfo> it = list.iterator();
                    while (it.hasNext()) {
                        LogUtil.a("HwAlarmManager", "INSERT_EVENT_ALARM eventAlarmInfos ", it.next().toString());
                    }
                    jgh.this.c(list, new IBaseResponseCallback() { // from class: jgh.a.5
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            LogUtil.a("HwAlarmManager", "migrateEventAlarm1 errorCode ", Integer.valueOf(i));
                            if (jggVar.d() == null) {
                                LogUtil.a("HwAlarmManager", "migrateEventAlarm1 callback is null ");
                                return;
                            }
                            LogUtil.a("HwAlarmManager", "alarmObject ", jggVar);
                            if (i == 0) {
                                jggVar.d().d(0, 100000);
                            } else {
                                jggVar.d().d(-1, 108001);
                            }
                        }
                    });
                }
                return true;
            }
            return bGZ_(message);
        }

        private boolean bGZ_(Message message) {
            int i = message.what;
            if (i == 2) {
                LogUtil.a("HwAlarmManager", "DELETE_EVENT_ALARM");
                List<EventAlarmInfo> list = (List) message.obj;
                Iterator<EventAlarmInfo> it = list.iterator();
                while (it.hasNext()) {
                    LogUtil.a("HwAlarmManager", "DELETE_EVENT_ALARM eventAlarmInfos ", it.next().toString());
                }
                jgh.this.c(list, (IBaseResponseCallback) null);
                return true;
            }
            if (i == 3) {
                LogUtil.a("HwAlarmManager", "GET_EVENT_ALARM");
                if (message.obj instanceof IBaseResponseCallback) {
                    final IBaseResponseCallback iBaseResponseCallback = (IBaseResponseCallback) message.obj;
                    synchronized (jgh.c) {
                        jgh.this.d(new IBaseResponseCallback() { // from class: jgh.a.3
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i2, Object obj) {
                                LogUtil.a("HwAlarmManager", "GET_EVENT_ALARM getEventAlarm errCode ", Integer.valueOf(i2));
                                Object arrayList = new ArrayList(16);
                                if (i2 == 0 && (obj instanceof List)) {
                                    arrayList = (List) obj;
                                }
                                iBaseResponseCallback.d(0, arrayList);
                            }
                        });
                    }
                } else {
                    LogUtil.h("HwAlarmManager", "message.obj not instanceof IBaseResponseCallback");
                }
                return true;
            }
            return bHa_(message);
        }

        private boolean bHa_(Message message) {
            int i = message.what;
            if (i == 4) {
                LogUtil.a("HwAlarmManager", "INSERT_SMART_ALARM");
                bGY_(message);
                return true;
            }
            if (i == 7) {
                LogUtil.a("HwAlarmManager", "GET_SMART_TIMEOUT");
                if (jgh.this.p != null) {
                    jgh.this.p.removeMessages(7);
                }
                jgh.b(jgh.this);
                LogUtil.a("HwAlarmManager", "mConnected ", Integer.valueOf(jgh.this.n));
                if (jgh.this.n < 3) {
                    jgh.this.j();
                }
                return true;
            }
            return bHb_(message);
        }

        private void bGY_(Message message) {
            if (message.obj instanceof jgg) {
                final jgg jggVar = (jgg) message.obj;
                if (jggVar.a() instanceof List) {
                    List<SmartAlarmInfo> list = (List) jggVar.a();
                    Iterator<SmartAlarmInfo> it = list.iterator();
                    while (it.hasNext()) {
                        LogUtil.a("HwAlarmManager", "INSERT_SMART_ALARM smartAlarmInfoList ", it.next().toString());
                    }
                    jgh.this.a(list, new IBaseResponseCallback() { // from class: jgh.a.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            LogUtil.a("HwAlarmManager", "migrateSmartAlarm1 errorCode ", Integer.valueOf(i));
                            if (jggVar.d() == null) {
                                LogUtil.h("HwAlarmManager", "migrateSmartAlarm1 callback is null ");
                                return;
                            }
                            LogUtil.a("HwAlarmManager", "alarmObject ", jggVar);
                            if (i == 0) {
                                jggVar.d().d(0, 100000);
                            } else {
                                jggVar.d().d(-1, 108001);
                            }
                        }
                    });
                    return;
                }
                LogUtil.h("HwAlarmManager", "alarmObject.getObject() not instanceof List<?>");
                return;
            }
            LogUtil.h("HwAlarmManager", "message.obj not instanceof AlarmObject");
        }

        private boolean bHb_(Message message) {
            int i = message.what;
            if (i == 5) {
                LogUtil.a("HwAlarmManager", "DELETE_SMART_ALARM");
                List<SmartAlarmInfo> list = (List) message.obj;
                Iterator<SmartAlarmInfo> it = list.iterator();
                while (it.hasNext()) {
                    LogUtil.a("HwAlarmManager", "DELETE_SMART_ALARM eventAlarmInfos ", it.next().toString());
                }
                jgh.this.a(list, (IBaseResponseCallback) null);
                return true;
            }
            if (i == 6) {
                LogUtil.a("HwAlarmManager", "GET_SMART_ALARM");
                if (message.obj instanceof IBaseResponseCallback) {
                    final IBaseResponseCallback iBaseResponseCallback = (IBaseResponseCallback) message.obj;
                    jgh.this.a(new IBaseResponseCallback() { // from class: jgh.a.1
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj) {
                            LogUtil.a("HwAlarmManager", "getSmartAlarm errorCode = ", Integer.valueOf(i2));
                            List arrayList = new ArrayList(16);
                            if (i2 == 0 && (obj instanceof List)) {
                                arrayList = (List) obj;
                            }
                            if (arrayList.size() == 0) {
                                LogUtil.a("HwAlarmManager", "get smartAlart cursor is null");
                                iBaseResponseCallback.d(0, arrayList);
                            }
                        }
                    });
                } else {
                    LogUtil.h("HwAlarmManager", "message.obj not instanceof IBaseResponseCallback");
                }
                return true;
            }
            LogUtil.a("HwAlarmManager", "processingThirdMessage unKnow type");
            return false;
        }
    }

    public void d(final IBaseResponseCallback iBaseResponseCallback) {
        Object[] objArr = new Object[2];
        objArr[0] = "getEventAlarmInfos callback isNull:";
        objArr[1] = Boolean.valueOf(iBaseResponseCallback == null);
        LogUtil.a("HwAlarmManager", objArr);
        if (iBaseResponseCallback == null) {
            return;
        }
        jqi.a().getSwitchSetting("custom.wear_event_alarm", new IBaseResponseCallback() { // from class: jgh.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                List list;
                List arrayList = new ArrayList(16);
                if (i2 == 0 && (obj instanceof String)) {
                    try {
                        list = (List) new Gson().fromJson((String) obj, new TypeToken<List<EventAlarmInfo>>() { // from class: jgh.2.1
                        }.getType());
                    } catch (Exception unused) {
                    }
                    try {
                        for (int size = list.size() - 1; size >= 0; size--) {
                            if (list.get(size) == null) {
                                list.remove(size);
                            }
                        }
                        iBaseResponseCallback.d(0, list);
                        return;
                    } catch (Exception unused2) {
                        arrayList = list;
                        iBaseResponseCallback.d(100001, arrayList);
                        jgh.this.c((List<EventAlarmInfo>) arrayList, (IBaseResponseCallback) null, false);
                        LogUtil.b("HwAlarmManager", "getEventAlarmInfos JsonSyntaxException");
                        return;
                    }
                }
                iBaseResponseCallback.d(100001, arrayList);
            }
        });
    }

    public void a(String str) {
        SharedPreferenceManager.e(this.k, String.valueOf(10022), "kStorage_AlarmMgr_MAC", str, new StorageParams());
    }

    public void c(final List<EventAlarmInfo> list, final IBaseResponseCallback iBaseResponseCallback) {
        Object[] objArr = new Object[2];
        objArr[0] = "setEventAlarmInfos eventAlarmInfoList isNull:";
        objArr[1] = Boolean.valueOf(list == null);
        ReleaseLogUtil.d("R_HwAlarmManager", objArr);
        if (list == null) {
            return;
        }
        jqi.a().setSwitchSetting("custom.wear_event_alarm", new Gson().toJson(list), new IBaseResponseCallback() { // from class: jgh.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(i2, list);
                }
            }
        });
    }

    public void a(final IBaseResponseCallback iBaseResponseCallback) {
        Object[] objArr = new Object[2];
        objArr[0] = "getSmartAlarmInfos callback isNull:";
        objArr[1] = Boolean.valueOf(iBaseResponseCallback == null);
        LogUtil.a("HwAlarmManager", objArr);
        if (iBaseResponseCallback == null) {
            return;
        }
        jqi.a().getSwitchSetting("custom.wear_smart_alarm", new IBaseResponseCallback() { // from class: jgh.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                Collection arrayList = new ArrayList(16);
                if (i2 == 0 && (obj instanceof String)) {
                    try {
                        arrayList = (List) new Gson().fromJson((String) obj, new TypeToken<List<SmartAlarmInfo>>() { // from class: jgh.3.1
                        }.getType());
                    } catch (JsonSyntaxException unused) {
                        iBaseResponseCallback.d(100001, arrayList);
                        LogUtil.b("HwAlarmManager", "getSmartAlarmInfos JsonSyntaxException");
                        return;
                    } catch (NullPointerException e2) {
                        LogUtil.b("HwAlarmManager", "getSmartAlarmInfos fromJson NullPointerException: ", e2.getMessage());
                    }
                    iBaseResponseCallback.d(0, arrayList);
                    return;
                }
                iBaseResponseCallback.d(100001, arrayList);
            }
        });
    }

    public void a(List<SmartAlarmInfo> list, final IBaseResponseCallback iBaseResponseCallback) {
        Object[] objArr = new Object[2];
        objArr[0] = "setSmartAlarmInfos smartAlarmInfoList isNull:";
        objArr[1] = Boolean.valueOf(list == null);
        LogUtil.h("HwAlarmManager", objArr);
        if (list == null) {
            return;
        }
        final String json = new Gson().toJson(list);
        jqi.a().setSwitchSetting("custom.wear_smart_alarm", json, new IBaseResponseCallback() { // from class: jgh.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(i2, json);
                }
            }
        });
    }

    private void t() {
        cuk.b().registerDeviceSampleListener("hw.uniedevice.getAlarmsNumber", this);
    }

    private void r() {
        cuk.b().unRegisterDeviceSampleListener("hw.uniedevice.getAlarmsNumber");
    }

    private cvq q() {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.uniedevice.getAlarmsNumber");
        cvqVar.setWearPkgName("getAlarmsNumber");
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900100015L);
        cvnVar.d(2);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    public void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwAlarmManager", "sendAlarmNumberCommand, deviceInfo is null.");
        } else if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwAlarmManager", "sendAlarmNumberCommand, device is not connected.");
        } else {
            t();
            LogUtil.h("HwAlarmManager", "isSendSuccess is: ", Boolean.valueOf(cuk.b().sendSampleConfigCommand(deviceInfo, q(), "HwAlarmManager")));
        }
    }

    private boolean b(cvr cvrVar) {
        if (cvrVar != null) {
            return (cvrVar.getCommandId() == 1 && "getAlarmsNumber".equals(cvrVar.getSrcPkgName()) && "hw.uniedevice.getAlarmsNumber".equals(cvrVar.getWearPkgName())) ? false : true;
        }
        LogUtil.a("HwAlarmManager", "sampleBase is null.");
        return true;
    }

    public void d(Context context, boolean z) {
        Intent intent = new Intent(context, (Class<?>) HandleIntentService.class);
        intent.setAction("com.huawei.health.ACTION_NOTIFICATION_CLOCK_STATE");
        Bundle bundle = new Bundle();
        bundle.putString("clockStateType", "0");
        bundle.putBoolean("clockStateValue", z);
        intent.putExtras(bundle);
        context.startService(intent);
        ReleaseLogUtil.e("R_HwAlarmManager", "sendSyncRemindChangeBroadcast success.");
    }

    public boolean g() {
        PackageInfo packageInfo;
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        try {
            if (MagicBuild.f13130a) {
                packageInfo = packageManager.getPackageInfo("com.hihonor.synergy", 128);
            } else {
                packageInfo = packageManager.getPackageInfo("com.huawei.synergy", 128);
            }
            if (packageInfo == null) {
                ReleaseLogUtil.d("R_HwAlarmManager", "isMiddleWearSupportHealth packageInfo is null");
                return false;
            }
            String str = packageInfo.versionName;
            ReleaseLogUtil.e("R_HwAlarmManager", "deviceVersion is,", str);
            String[] split = str.split("\\.");
            if (split.length < 4) {
                return false;
            }
            return CommonUtils.h(split[0]) == 14 && CommonUtils.h(split[1]) == 0 ? CommonUtils.h(split[3]) >= 11 : CommonUtils.h(split[0]) == 14 && CommonUtils.h(split[1]) == 1 ? CommonUtils.h(split[3]) >= 2 : (CommonUtils.h(split[0]) == 14 && CommonUtils.h(split[1]) >= 2) && CommonUtils.h(split[3]) >= 1;
        } catch (PackageManager.NameNotFoundException unused) {
            ReleaseLogUtil.c("R_HwAlarmManager", "isMiddleWearSupportHealth not exist");
            return false;
        }
    }
}
