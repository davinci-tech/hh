package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.coresleepresult.HwCoreSleepDataProvider;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.algorithm.callback.IBluetoothResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwcoresleepmgr.SyncBaseFunction;
import com.huawei.hwcoresleepmgr.datatype.NotificationData;
import com.huawei.hwcoresleepmgr.datatype.TruSleepLastSyncTime;
import com.huawei.hwcoresleepmgr.datatype.TruSleepUpgTime;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.syncmgr.SyncMode;
import com.huawei.syncmgr.SyncOption;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jen extends HwBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13765a = new Object();
    private static jen b;
    private final Object aa;
    private ThreadPoolManager ab;
    private IBaseResponseCallback ac;
    private List<IBaseResponseCallback> c;
    private final Object d;
    private IBaseResponseCallback e;
    private Context f;
    private long g;
    private int h;
    private final BroadcastReceiver i;
    private jek j;
    private ExtendHandler k;
    private boolean l;
    private int m;
    private int n;
    private Map<String, Boolean> o;
    private List<bfk> p;
    private List<bfj> q;
    private long r;
    private List<bfl> s;
    private long t;
    private long u;
    private final Object v;
    private Timer w;
    private d x;
    private SyncBaseFunction y;

    private jen(Context context, SyncBaseFunction syncBaseFunction) {
        super(context);
        this.ac = null;
        this.e = null;
        this.aa = new Object();
        this.l = false;
        this.m = 0;
        this.g = 0L;
        this.n = 0;
        this.w = null;
        this.x = null;
        this.c = new ArrayList(0);
        this.d = new Object();
        this.t = 0L;
        this.r = 0L;
        this.o = new HashMap(10);
        this.v = new Object();
        this.u = 0L;
        this.i = new BroadcastReceiver() { // from class: jen.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                LogUtil.a("HwCoreSleepManager", "mConnectStateChangedReceiver(): intent is ", intent.getAction());
                if (context2 == null) {
                    LogUtil.h("HwCoreSleepManager", "mConnectStateChangedReceiver context is null.");
                    return;
                }
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                    if (!(parcelableExtra instanceof DeviceInfo)) {
                        LogUtil.h("HwCoreSleepManager", "mConnectStateChangedReceiver(): cast error");
                        return;
                    }
                    DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                    if (deviceInfo.getRelationship() != null && "followed_relationship".equals(deviceInfo.getRelationship())) {
                        LogUtil.h("HwCoreSleepManager", "This device does not have the correspond capability.");
                        return;
                    }
                    int deviceConnectState = deviceInfo.getDeviceConnectState();
                    LogUtil.a("HwCoreSleepManager", "mConnectStateChangedReceiver(): ", deviceInfo.getDeviceName(), ",state is ", Integer.valueOf(deviceConnectState));
                    if (deviceConnectState == 3) {
                        LogUtil.a("HwCoreSleepManager", "bt disconnect, mCoreSleepSyncFlag is ", Integer.valueOf(jen.this.m));
                        jen.this.e(21000, deviceConnectState);
                        jen.this.a(30004, AwarenessRequest.MessageType.DISCONNECT);
                        if (jen.this.m == 2) {
                            jen.this.b(deviceInfo, false);
                            jen.this.l = false;
                            jen.this.f();
                            jen.this.b(-1);
                            jen.this.b(21000, "");
                        }
                    }
                }
            }
        };
        this.f = BaseApplication.getContext();
        jek e2 = jek.e();
        this.j = e2;
        this.y = syncBaseFunction;
        e2.b(syncBaseFunction);
        this.ab = ThreadPoolManager.a(1, 2);
        this.k = HandlerCenter.yt_(new b(), "HwCoreSleepManager");
        h();
    }

    public static jen e(SyncBaseFunction syncBaseFunction) {
        jen jenVar;
        synchronized (f13765a) {
            if (b == null) {
                b = new jen(BaseApplication.getContext(), syncBaseFunction);
            }
            jenVar = b;
        }
        return jenVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 30;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str) {
        IBaseResponseCallback d2 = d();
        if (d2 != null) {
            ReleaseLogUtil.e("HwCoreSleepManager", "setCoreSleepSyncRate sProgressListener is responding errCode=", Integer.valueOf(i));
            d2.d(i, "");
        }
        e(i, str);
        if (c() != null) {
            ReleaseLogUtil.e("HwCoreSleepManager", "setCoreSleepSyncRate sCoreSleepCallback is responding errCode=", Integer.valueOf(i));
            c().d(i, "");
        } else {
            ReleaseLogUtil.d("HwCoreSleepManager", "setCoreSleepSyncRate sCoreSleepCallback is null");
            a(i);
            c(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z, DeviceInfo deviceInfo) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HwCoreSleepManager", "isNormalSync ", Boolean.valueOf(z), ", currTime ", Long.valueOf(currentTimeMillis), ", mBeginSyncTime ", Long.valueOf(this.g), ",totalTime: ", 1200000L, " mCoreSleepSyncFlag: ", Integer.valueOf(this.m));
        if (currentTimeMillis - this.g > 1200000) {
            b(z, deviceInfo);
            return;
        }
        int i = this.m;
        if (i == 1) {
            c(z, deviceInfo);
            return;
        }
        if (i == -1) {
            f();
            if (z) {
                a(21004);
            } else {
                b(21004, VastAttribute.ERROR);
            }
            a(21004, "save error.");
            b(deviceInfo, false);
            return;
        }
        if (i == 3) {
            f();
            if (z) {
                a(21006);
            } else {
                b(21006, VastAttribute.ERROR);
            }
            a(21006, "cpc error");
            b(deviceInfo, false);
            return;
        }
        if (i == 4) {
            int i2 = this.n + 2;
            this.n = i2;
            LogUtil.a("HwCoreSleepManager", "cpc progress :", Integer.valueOf(i2));
            if (c() != null && this.n > 100) {
                c().d(20000, Integer.valueOf(this.n));
                c(this.n);
                return;
            }
            int i3 = this.n;
            if (i3 > 100) {
                c(i3);
                return;
            } else {
                LogUtil.h("HwCoreSleepManager", "process error.");
                return;
            }
        }
        LogUtil.h("HwCoreSleepManager", "Enter else");
    }

    private void b(boolean z, DeviceInfo deviceInfo) {
        LogUtil.a("HwCoreSleepManager", "setCoreSleepSyncRate() timeout");
        IBaseResponseCallback d2 = d();
        if (d2 != null) {
            d2.d(21003, "");
        }
        f();
        this.m = -1;
        if (z) {
            a(21003);
        } else {
            b(21003, "timeout");
        }
        a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "time out");
        b(deviceInfo, false);
        LogUtil.a("HwCoreSleepManager", "setCoreSleepSyncRate sync timeout");
    }

    private void c(boolean z, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "rate is 100, isNormalSync is", Boolean.valueOf(z));
        f();
        if (z) {
            n();
            c(-1);
        } else {
            b(0, "success");
        }
        a(0, "success");
        b(deviceInfo, false);
    }

    class b implements Handler.Callback {
        private b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            DeviceInfo deviceInfo = message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null;
            int i = message.what;
            if (i == 0) {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "sync core sleep timeout");
                jen.this.a(deviceInfo);
                jen.this.e(0, message.what);
                return true;
            }
            if (i == 1) {
                jel.a();
                ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "sync core sleep timeout : cpc timeout out.");
                jen.this.a(deviceInfo);
                return true;
            }
            if (i == 2) {
                LogUtil.a("HwCoreSleepManager", "process update sync flag");
                jen.this.m = 1;
                jen.this.n = 0;
                if (jem.bGA_(message, jen.this.t, jen.this.r) && (message.obj instanceof NotificationData)) {
                    jen.this.a((NotificationData) message.obj);
                }
                return true;
            }
            if (i == 3) {
                LogUtil.a("HwCoreSleepManager", "sync from device success");
                if (jen.this.m != 3) {
                    jen.this.n = 100;
                    jen.this.m = 4;
                    synchronized (jen.this.aa) {
                        if (jen.this.x == null) {
                            LogUtil.h("HwCoreSleepManager", "have no time task");
                            jen.this.a(message.getData().getBoolean("isNormalSync"), deviceInfo);
                        }
                    }
                    IBaseResponseCallback c = jen.this.c();
                    if (c != null) {
                        LogUtil.a("HwCoreSleepManager", "report progress 100");
                        c.d(20000, Integer.valueOf(jen.this.n));
                    }
                } else {
                    ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "cpc error，do nothing");
                }
                return true;
            }
            LogUtil.h("HwCoreSleepManager", "no support message : ", Integer.valueOf(message.what));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(NotificationData notificationData) {
        int i;
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter sendSleepBroadcast");
        String description = notificationData.getDescription();
        if (TextUtils.isEmpty(description)) {
            i = 0;
        } else {
            Matcher matcher = Pattern.compile("\\d+").matcher(description);
            StringBuffer stringBuffer = new StringBuffer(16);
            while (matcher.find()) {
                stringBuffer.append(matcher.group());
            }
            i = CommonUtil.h(stringBuffer.toString());
        }
        LogUtil.a("HwCoreSleepManager", "sendSleepBroadcast score is:", Integer.valueOf(i));
        long startTime = notificationData.getStartTime();
        long endTime = notificationData.getEndTime();
        Intent intent = new Intent("com.huawei.health.track.broadcast");
        intent.putExtra("fallAsleepTime", startTime);
        intent.putExtra("wakeTime", endTime);
        intent.putExtra(JsUtil.SCORE, i);
        intent.putExtra("command_type", "com.huawei.health.sync.coresleep");
        BroadcastManagerUtil.bFG_(this.f, intent);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "sendSleepBroadcast end");
    }

    private void a(List<bfk> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        long j = 0;
        for (bfk bfkVar : list) {
            if (bfkVar != null && bfkVar.b() > j) {
                j = bfkVar.b();
            }
        }
        if (j > 0) {
            LogUtil.a("HwCoreSleepManager", "checkFailLastTime success, update time.");
            e(j);
        } else {
            LogUtil.h("HwCoreSleepManager", "wrong situation, please check json and code.");
        }
    }

    private void a(long j, int i) {
        if (i == 0 || i == 100) {
            return;
        }
        LogUtil.a("HwCoreSleepManager", "addCoreSleepToUpgrage enter, endTime=", Long.valueOf(j), ", errorCode=", Integer.valueOf(i));
        if (!jet.b(j, System.currentTimeMillis())) {
            LogUtil.h("HwCoreSleepManager", "addCoreSleepToUpgrage is not today.");
            return;
        }
        if (d(j)) {
            LogUtil.h("HwCoreSleepManager", "addCoreSleepToUpgrage has the endTime.");
            return;
        }
        jel.b(i);
        SyncBaseFunction syncBaseFunction = this.y;
        if (syncBaseFunction != null) {
            syncBaseFunction.addOtaCoreSleepLog(i, j);
        }
        c(j);
    }

    private void c(long j) {
        List list;
        LogUtil.a("HwCoreSleepManager", "enter saveUpgradeEndTime, endTime is ", Long.valueOf(j));
        String l = Long.toString(j);
        StorageParams storageParams = new StorageParams(1);
        DeviceInfo deviceInfo = this.y.getDeviceInfo();
        String securityDeviceId = deviceInfo != null ? deviceInfo.getSecurityDeviceId() : "";
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        Gson gson = new Gson();
        String sharedPreference = getSharedPreference("kStorage_CoreSleepMgr_Long_UpgTimes");
        LogUtil.c("HwCoreSleepManager", "saveUpgradeEndTime - get time is: ", sharedPreference);
        try {
            list = (List) gson.fromJson(sharedPreference, new TypeToken<List<TruSleepUpgTime>>() { // from class: jen.4
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HwCoreSleepManager", "saveUpgradeEndTime fromJson parse fail.");
            list = null;
        }
        if (list == null || list.isEmpty()) {
            c(e2, securityDeviceId, l, storageParams);
            return;
        }
        if (!d(list.iterator(), e2 + securityDeviceId, l)) {
            TruSleepUpgTime truSleepUpgTime = new TruSleepUpgTime();
            truSleepUpgTime.setDeviceMac(e2 + securityDeviceId);
            truSleepUpgTime.setLastUpgEndTime(l);
            list.add(truSleepUpgTime);
        }
        setSharedPreference("kStorage_CoreSleepMgr_Long_UpgTimes", new Gson().toJson(list), storageParams);
    }

    private void c(String str, String str2, String str3, StorageParams storageParams) {
        LogUtil.a("HwCoreSleepManager", "updateUpg sleepUpgTimeList is null.");
        ArrayList arrayList = new ArrayList(16);
        TruSleepUpgTime truSleepUpgTime = new TruSleepUpgTime();
        truSleepUpgTime.setDeviceMac(str + str2);
        truSleepUpgTime.setLastUpgEndTime(str3);
        arrayList.add(truSleepUpgTime);
        setSharedPreference("kStorage_CoreSleepMgr_Long_UpgTimes", new Gson().toJson(arrayList), storageParams);
    }

    private boolean d(Iterator<TruSleepUpgTime> it, String str, String str2) {
        boolean z = false;
        while (it.hasNext()) {
            TruSleepUpgTime next = it.next();
            if (str.equalsIgnoreCase(next.getDeviceMac())) {
                next.setLastUpgEndTime(str2);
                z = true;
            } else {
                LogUtil.h("HwCoreSleepManager", "no condition");
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    private boolean d(long j) {
        List<TruSleepUpgTime> list;
        LogUtil.a("HwCoreSleepManager", "enter isUpgradeEndTimeExist() endTime =", Long.valueOf(j));
        DeviceInfo deviceInfo = this.y.getDeviceInfo();
        String securityDeviceId = deviceInfo != null ? deviceInfo.getSecurityDeviceId() : "";
        LogUtil.c("HwCoreSleepManager", "isUpgradeEndTimeExist currentDeviceMac is: ", iyl.d().e(securityDeviceId));
        Gson gson = new Gson();
        String sharedPreference = getSharedPreference("kStorage_CoreSleepMgr_Long_UpgTimes");
        if (TextUtils.isEmpty(sharedPreference)) {
            LogUtil.a("HwCoreSleepManager", "isUpgradeEndTimeExist timeString is empty.");
            return false;
        }
        try {
            list = (List) gson.fromJson(sharedPreference, new TypeToken<List<TruSleepUpgTime>>() { // from class: jen.1
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HwCoreSleepManager", "upgTimeList fromJson parse fail.");
            list = null;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwCoreSleepManager", "upgTimeList is empty!");
            return false;
        }
        LogUtil.a("HwCoreSleepManager", "upgTimeList: ", list.toString());
        String str = KeyValDbManager.b(BaseApplication.getContext()).e("user_id") + securityDeviceId;
        for (TruSleepUpgTime truSleepUpgTime : list) {
            if (str.equalsIgnoreCase(truSleepUpgTime.getDeviceMac()) && truSleepUpgTime.getLastUpgEndTime().equalsIgnoreCase(Long.toString(j))) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        this.m = -1;
    }

    public void bGz_(Message message) {
        message.what = 2;
        this.k.sendMessage(message, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        LogUtil.h("HwCoreSleepManager", "handle sync error:", Integer.valueOf(i));
        this.m = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.h("HwCoreSleepManager", "enter mSyncTimer");
        synchronized (this.aa) {
            d dVar = this.x;
            if (dVar != null) {
                dVar.cancel();
                this.x = null;
                LogUtil.h("HwCoreSleepManager", "sync timeout,task cancel");
            }
            Timer timer = this.w;
            if (timer != null) {
                timer.cancel();
                this.w = null;
                LogUtil.h("HwCoreSleepManager", "sync timeout,mSyncTimer cancel");
            }
        }
    }

    public void e(long j) {
        List list;
        LogUtil.a("HwCoreSleepManager", "enter updateLastSyncTime,time is ", Long.valueOf(j));
        StorageParams storageParams = new StorageParams();
        String l = Long.toString(j);
        DeviceInfo deviceInfo = this.y.getDeviceInfo();
        String securityDeviceId = deviceInfo != null ? deviceInfo.getSecurityDeviceId() : "";
        LogUtil.c("HwCoreSleepManager", "currentDeviceMac is:", iyl.d().e(securityDeviceId));
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        Gson gson = new Gson();
        String sharedPreference = getSharedPreference("kStorage_CoreSleepMgr_Long_LastSyncTime");
        LogUtil.c("HwCoreSleepManager", "get timeStringFromSharedPreference is: ", sharedPreference);
        try {
            list = (List) gson.fromJson(sharedPreference, new TypeToken<List<TruSleepLastSyncTime>>() { // from class: jen.5
            }.getType());
        } catch (JsonSyntaxException e3) {
            LogUtil.b("HwCoreSleepManager", "fromJson parse fail.", e3.getMessage());
            list = null;
        }
        if (list != null) {
            if (list.size() != 0) {
                LogUtil.c("HwCoreSleepManager", "lastSyncTimeList: ", list.toString());
                boolean c2 = c(list.iterator(), e2 + securityDeviceId, l);
                LogUtil.a("HwCoreSleepManager", "lastSyncTime isExist:", Boolean.valueOf(c2));
                if (!c2) {
                    TruSleepLastSyncTime truSleepLastSyncTime = new TruSleepLastSyncTime();
                    truSleepLastSyncTime.setDeviceMac(e2 + securityDeviceId);
                    truSleepLastSyncTime.setLastSyncTime(l);
                    list.add(truSleepLastSyncTime);
                }
                setSharedPreference("kStorage_CoreSleepMgr_Long_LastSyncTime", new Gson().toJson(list), storageParams);
                return;
            }
            return;
        }
        d(e2, securityDeviceId, l, storageParams);
    }

    private boolean c(Iterator<TruSleepLastSyncTime> it, String str, String str2) {
        boolean z = false;
        while (it.hasNext()) {
            TruSleepLastSyncTime next = it.next();
            if (str.equalsIgnoreCase(next.getDeviceMac())) {
                next.setLastSyncTime(str2);
                z = true;
            } else {
                LogUtil.h("HwCoreSleepManager", "no condition");
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    private void d(String str, String str2, String str3, StorageParams storageParams) {
        LogUtil.a("HwCoreSleepManager", "lastSyncTimeList is null.");
        ArrayList arrayList = new ArrayList(16);
        TruSleepLastSyncTime truSleepLastSyncTime = new TruSleepLastSyncTime();
        truSleepLastSyncTime.setDeviceMac(str + str2);
        truSleepLastSyncTime.setLastSyncTime(str3);
        arrayList.add(truSleepLastSyncTime);
        setSharedPreference("kStorage_CoreSleepMgr_Long_LastSyncTime", new Gson().toJson(arrayList), storageParams);
    }

    private long i() {
        List<TruSleepLastSyncTime> list;
        LogUtil.a("HwCoreSleepManager", "enter getLastSyncTime()");
        DeviceInfo deviceInfo = this.y.getDeviceInfo();
        String securityDeviceId = deviceInfo != null ? deviceInfo.getSecurityDeviceId() : "";
        LogUtil.c("HwCoreSleepManager", "currentDeviceMac is: ", iyl.d().e(securityDeviceId));
        Gson gson = new Gson();
        String sharedPreference = getSharedPreference("kStorage_CoreSleepMgr_Long_LastSyncTime");
        long j = 0;
        if (TextUtils.isEmpty(sharedPreference)) {
            LogUtil.a("HwCoreSleepManager", "timeStringFromSharedPreference is empty.");
            return 0L;
        }
        try {
            list = (List) gson.fromJson(sharedPreference, new TypeToken<List<TruSleepLastSyncTime>>() { // from class: jen.7
            }.getType());
        } catch (JsonSyntaxException e2) {
            LogUtil.b("HwCoreSleepManager", "fromJson parse fail : ", e2.getMessage());
            list = null;
        }
        if (list != null && list.size() != 0) {
            LogUtil.a("HwCoreSleepManager", "lastSyncTimeList: ", list.toString());
            String str = KeyValDbManager.b(BaseApplication.getContext()).e("user_id") + securityDeviceId;
            for (TruSleepLastSyncTime truSleepLastSyncTime : list) {
                if (str.equalsIgnoreCase(truSleepLastSyncTime.getDeviceMac())) {
                    String lastSyncTime = truSleepLastSyncTime.getLastSyncTime();
                    LogUtil.a("HwCoreSleepManager", "getLastCoreSleepSyncTime timeString is ", lastSyncTime);
                    if (lastSyncTime != null && !lastSyncTime.isEmpty()) {
                        try {
                            j = Long.parseLong(lastSyncTime);
                            break;
                        } catch (NumberFormatException unused) {
                            LogUtil.b("HwCoreSleepManager", "getLastCoreSleepSyncTime exception");
                        }
                    }
                }
            }
            LogUtil.a("HwCoreSleepManager", "getLastCoreSleepSyncTime lastTimeStamp is ", Long.valueOf(j));
            return j;
        }
        LogUtil.h("HwCoreSleepManager", "lastSyncTimeList is empty!");
        return 0L;
    }

    private void a(SyncOption syncOption, IBaseResponseCallback iBaseResponseCallback, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter startSyncCoreSleepAuto");
        if (!d(syncOption, deviceInfo)) {
            ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "startSyncCoreSleepAuto false");
            b(deviceInfo, false);
            b(21005, "isSupportSyncAuto false");
            return;
        }
        boolean c2 = cwi.c(deviceInfo, 143);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "isSupportAlgorithmDown ", Boolean.valueOf(c2), Boolean.valueOf(e(syncOption)), "hasUpgradeTime ", Boolean.valueOf(e(syncOption)));
        if (c2 && !e(syncOption)) {
            c(deviceInfo, syncOption, iBaseResponseCallback);
        } else {
            d(iBaseResponseCallback);
            b(deviceInfo, syncOption, true, iBaseResponseCallback);
        }
    }

    private void a(SyncOption syncOption) {
        DeviceCapability deviceCapability = this.y.getDeviceCapability(this.y.getDeviceInfo());
        if (deviceCapability == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "wakeUpDevice deviceCapability is null.");
            return;
        }
        if (!deviceCapability.isSupportSendCoreSleepOutState()) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "not support wake up.");
            return;
        }
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "syncCoreSleepData isManualSync is ", Boolean.valueOf(syncOption.isManualSync()));
        if (syncOption.isManualSync()) {
            jeo.b(this.y);
        }
    }

    private long e(long j, long j2) {
        if (j >= j2 && j - j2 <= 300) {
            LogUtil.a("HwCoreSleepManager", "future time : ", Long.valueOf(j));
            return j2 - 61;
        }
        if (j - j2 <= 300) {
            return j;
        }
        LogUtil.a("HwCoreSleepManager", "future time : ", Long.valueOf(j));
        long j3 = j2 - 61;
        e(j3);
        return j3;
    }

    class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        IBaseResponseCallback f13771a;
        long b;
        boolean c;
        DeviceInfo d;
        long e;

        e(long j, long j2, IBaseResponseCallback iBaseResponseCallback, boolean z, DeviceInfo deviceInfo) {
            this.f13771a = iBaseResponseCallback;
            this.e = j;
            this.b = j2;
            this.c = z;
            this.d = deviceInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            jen.this.e(this.e, this.b, this.f13771a, this.c, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, DeviceInfo deviceInfo) {
        synchronized (this.aa) {
            d dVar = this.x;
            if (dVar != null) {
                dVar.cancel();
            }
            this.x = new d(z, deviceInfo);
            Timer timer = new Timer("HwCoreSleepManager");
            this.w = timer;
            timer.schedule(this.x, 0L, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, long j2, IBaseResponseCallback iBaseResponseCallback, boolean z, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter syncCoreSleepDetailDataRun, isNormalSync:", Boolean.valueOf(z));
        this.m = 2;
        this.g = System.currentTimeMillis();
        synchronized (this.aa) {
            d dVar = this.x;
            if (dVar != null) {
                dVar.cancel();
            }
            this.x = new d(z, deviceInfo);
            Timer timer = new Timer("HwCoreSleepManager");
            this.w = timer;
            timer.schedule(this.x, 0L, 2000L);
        }
        d(j, j2, iBaseResponseCallback, z, deviceInfo);
        this.k.removeMessages(0);
        Message obtain = Message.obtain();
        obtain.obj = deviceInfo;
        obtain.what = 0;
        this.k.sendMessage(obtain, 1200000L);
    }

    private void d(long j, long j2, final IBaseResponseCallback iBaseResponseCallback, final boolean z, final DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter sendCommand, startTime : ", Long.valueOf(j), ",endTime : ", Long.valueOf(j2));
        o();
        SyncBaseFunction syncBaseFunction = this.y;
        if (syncBaseFunction != null) {
            syncBaseFunction.getSleepDataFromDevice(false, z, (int) j, (int) j2, new IBluetoothResponseCallback() { // from class: jen.10
                @Override // com.huawei.health.algorithm.callback.IBluetoothResponseCallback
                public void onSuccess(int i, String str) {
                    ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "getSleepDataFromDevice onSuccess successCode: ", Integer.valueOf(i));
                    LogUtil.a("HwCoreSleepManager", "getSleepDataFromDevice onSuccess successMessage=", str);
                    if (CommonUtil.as()) {
                        sqo.d("core sleep file sync is completed, sync duration: " + (System.currentTimeMillis() - jen.this.u));
                    }
                    jen.this.e(str, i, iBaseResponseCallback, z, deviceInfo);
                }

                @Override // com.huawei.health.algorithm.callback.IBluetoothResponseCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "getSleepDataFromDevice onFailure errorCode: ", Integer.valueOf(i));
                    jen.this.e(i, z, iBaseResponseCallback, deviceInfo);
                }

                @Override // com.huawei.health.algorithm.callback.IBluetoothResponseCallback
                public void onProgress(int i, String str) {
                    if (iBaseResponseCallback != null) {
                        LogUtil.a("HwCoreSleepManager", "getSleepDataFromDevice onProgress() progress: ", Integer.valueOf(i));
                        iBaseResponseCallback.d(20000, Integer.valueOf(i));
                        jen.this.e(20000, Integer.valueOf(i));
                    }
                    jen.this.c(i);
                }
            });
        } else {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "getSleepDataFromDevice mSyncBaseFunction is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, boolean z, IBaseResponseCallback iBaseResponseCallback, DeviceInfo deviceInfo) {
        if (i == 10002) {
            jel.b();
        }
        ExtendHandler extendHandler = this.k;
        if (extendHandler != null) {
            extendHandler.removeMessages(0);
            LogUtil.a("HwCoreSleepManager", "onFailure remove sync detail timeout.");
        }
        f();
        b(-1);
        if (z) {
            a(i);
        }
        e(-1, i);
        a(i, "device error");
        b(deviceInfo, false);
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, null);
            e(i, (Object) null);
            g();
        }
    }

    private void c(int i, IBaseResponseCallback iBaseResponseCallback, boolean z, DeviceInfo deviceInfo) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, null);
            e(0, (Object) null);
            g();
        }
        if (z && this.k != null) {
            Message obtain = Message.obtain();
            obtain.obj = deviceInfo;
            Bundle bundle = new Bundle();
            bundle.putBoolean("isNormalSync", true);
            obtain.setData(bundle);
            obtain.what = 3;
            this.k.sendMessage(obtain);
        }
        e(3, i);
    }

    private void c(ArrayList<byte[]> arrayList, ArrayList<byte[]> arrayList2, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter processCpcData");
        this.ab.d("HwCoreSleepManager", new c(arrayList, arrayList2, iBaseResponseCallback));
    }

    class c implements Runnable {
        IBaseResponseCallback d;
        ArrayList<byte[]> c = new ArrayList<>(16);
        ArrayList<byte[]> e = new ArrayList<>(16);

        c(ArrayList<byte[]> arrayList, ArrayList<byte[]> arrayList2, IBaseResponseCallback iBaseResponseCallback) {
            this.d = iBaseResponseCallback;
            if (arrayList.size() > 0) {
                this.c.addAll(arrayList);
            }
            if (arrayList2.size() > 0) {
                this.e.addAll(arrayList2);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            jen.this.a(this.c, this.e, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ArrayList<byte[]> arrayList, ArrayList<byte[]> arrayList2, IBaseResponseCallback iBaseResponseCallback) {
        Thread.currentThread().setPriority(10);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter processCpcDataRun.");
        new HwCoreSleepDataProvider().c(arrayList, arrayList2, iBaseResponseCallback);
    }

    class d extends TimerTask {
        boolean d;
        DeviceInfo e;

        d(boolean z, DeviceInfo deviceInfo) {
            this.d = false;
            LogUtil.a("HwCoreSleepManager", "SyncTimerTask:", Boolean.valueOf(z));
            this.d = z;
            this.e = deviceInfo;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            LogUtil.a("HwCoreSleepManager", "run.");
            jen.this.e(this.d, this.e);
        }
    }

    private void k() {
        LogUtil.a("HwCoreSleepManager", "registerDeviceToHiHealth");
        DeviceInfo deviceInfo = this.y.getDeviceInfo();
        if (deviceInfo != null) {
            this.y.registerHiHealthDataClient(deviceInfo);
        } else {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_HwCoreSleepManager", "registerDeviceToHiHealth deviceInfo is null");
        }
    }

    public long e() {
        int a2;
        long i = i();
        List<bfk> list = this.p;
        if (list == null || list.size() == 0) {
            LogUtil.h("HwCoreSleepManager", "mSleepErrorFramesList size is 0,Can not update SyncTime");
        } else {
            for (bfk bfkVar : this.p) {
                if (bfkVar.b() > i && ((a2 = bfkVar.a()) == 104 || a2 == 215 || a2 == 217 || a2 == 237 || a2 == 252)) {
                    i = bfkVar.b();
                }
            }
            List<bfl> list2 = this.s;
            if (list2 == null || list2.size() == 0) {
                LogUtil.h("HwCoreSleepManager", "mSleepStatusFramesList size is 0,Can not update SyncTime");
            } else {
                List<bfj> list3 = this.q;
                if (list3 == null || list3.size() == 0) {
                    if (d(this.s)) {
                        LogUtil.h("HwCoreSleepManager", "mSleepCalcFramesList size is 0, Has Night Sleep, Can not update SyncTime");
                    } else {
                        for (bfl bflVar : this.s) {
                            if (bflVar.b() > i) {
                                i = bflVar.b();
                            }
                        }
                        LogUtil.h("HwCoreSleepManager", "mSleepCalcFramesList size is 0,Can Only update noonSleep Time");
                    }
                } else {
                    for (bfl bflVar2 : this.s) {
                        if (bflVar2.b() > i) {
                            i = bflVar2.b();
                        }
                    }
                    return i + 60000;
                }
            }
        }
        return i + 60000;
    }

    private boolean d(List<bfl> list) {
        Iterator<bfl> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().e().get(0).intValue() != 5) {
                return true;
            }
        }
        return false;
    }

    private void o() {
        LogUtil.a("HwCoreSleepManager", "sendSyncStartBroadcast.");
        Intent intent = new Intent("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_START");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.f.sendBroadcast(intent, LocalBroadcast.c);
    }

    private void n() {
        LogUtil.a("HwCoreSleepManager", "sendSyncSuccessBroadcast.");
        Intent intent = new Intent("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_SUCCESS");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.f.sendBroadcast(intent, LocalBroadcast.c);
    }

    private void a(int i) {
        LogUtil.a("HwCoreSleepManager", "sendSyncFailBroadcast.error:", Integer.valueOf(i));
        Intent intent = new Intent("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_FAILED");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.putExtra("errorCode", i);
        this.f.sendBroadcast(intent, LocalBroadcast.c);
        c(-1);
    }

    public boolean b() {
        return j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i, final int i2) {
        LogUtil.a("HwCoreSleepManager", "enter sendCoreSleepSyncEvent.state is ", Integer.valueOf(i), ",errorMessage is ", Integer.valueOf(i2));
        final DeviceInfo deviceInfo = this.y.getDeviceInfo();
        ThreadPoolManager.d().execute(new Runnable() { // from class: jen.9
            @Override // java.lang.Runnable
            public void run() {
                jel.c(i, i2, jen.this.g, jen.this.h, deviceInfo);
            }
        });
    }

    private void c(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (this.d) {
            this.c.add(iBaseResponseCallback);
        }
    }

    private void g() {
        synchronized (this.d) {
            this.c.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, Object obj) {
        synchronized (this.d) {
            if (koq.b(this.c)) {
                Iterator<IBaseResponseCallback> it = this.c.iterator();
                while (it.hasNext()) {
                    it.next().d(i, obj);
                }
            }
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        f();
        if (j()) {
            b(-1);
            b(21005, "");
        }
        a(-1, "object destroy");
        this.o.clear();
        m();
        this.k.quit(false);
    }

    private void m() {
        try {
            this.f.unregisterReceiver(this.i);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("HwCoreSleepManager", e2.getMessage());
        }
    }

    private void h() {
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.f, this.i, intentFilter, LocalBroadcast.c, null);
    }

    public IBaseResponseCallback c() {
        return this.e;
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        this.e = iBaseResponseCallback;
    }

    private void d(SyncOption syncOption, IBaseResponseCallback iBaseResponseCallback, final DeviceInfo deviceInfo) {
        synchronized (this) {
            ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter startSyncCoreSleepByUi");
            if (((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).isDeviceOtaUpdating(deviceInfo.getDeviceIdentify())) {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "startSyncCoreSleepByUi device is Otaing.");
                b(deviceInfo, false);
                iBaseResponseCallback.d(22000, "");
            } else if (cwi.c(deviceInfo, 143)) {
                c(deviceInfo, syncOption, iBaseResponseCallback);
            } else {
                d(iBaseResponseCallback);
                b(deviceInfo, syncOption, false, new IBaseResponseCallback() { // from class: jen.8
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        jen.this.a(i, obj, deviceInfo);
                    }
                });
            }
        }
    }

    private void a(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.h("HwCoreSleepManager", "busy,return");
        iBaseResponseCallback.d(30002, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj, DeviceInfo deviceInfo) {
        LogUtil.a("HwCoreSleepManager", "reportProcessForUi has been done errorCode: ", Integer.valueOf(i));
        if (i == 20000) {
            if (c() != null) {
                c().d(20000, obj);
            }
        } else {
            if (i != 0) {
                b(i, "");
                return;
            }
            if (this.k != null) {
                Message obtain = Message.obtain();
                obtain.obj = deviceInfo;
                Bundle bundle = new Bundle();
                bundle.putBoolean("isNormalSync", false);
                obtain.setData(bundle);
                obtain.what = 3;
                this.k.sendMessage(obtain);
            }
        }
    }

    public IBaseResponseCallback d() {
        return this.ac;
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        this.ac = iBaseResponseCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("HwCoreSleepManager", "reportProgress original progress ", Integer.valueOf(i));
        int i2 = 100;
        if (i <= 100 && i >= 0) {
            i2 = (i * 7) / 9;
        } else if (i < 160 && i > 100) {
            i2 = (((i - 100) * 600) / 2160) + 77;
        } else if (i < 280 && i >= 160) {
            i2 = (((i - 160) * 200) / 4320) + 94;
        } else if (i != -1) {
            i2 = 99;
        }
        LogUtil.a("HwCoreSleepManager", "reportProgress finish progress ", Integer.valueOf(i2));
        IBaseResponseCallback d2 = d();
        if (d2 != null) {
            d2.d(20000, Integer.valueOf(i2));
        } else {
            LogUtil.h("HwCoreSleepManager", "reportProgress callback is null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        Intent intent = new Intent("com.huawei.health.action.ACTION_CORE_SLEEP_ERROR_CODE");
        intent.putExtra("errorCode", i);
        intent.putExtra("message", str);
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        f();
        b(-1);
        b(21005, "");
        a(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "timeout");
        b(deviceInfo, false);
        this.n = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x015c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(java.lang.String r18, int r19, com.huawei.hwbasemgr.IBaseResponseCallback r20, boolean r21, com.huawei.health.devicemgr.business.entity.DeviceInfo r22) {
        /*
            Method dump skipped, instructions count: 397
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jen.e(java.lang.String, int, com.huawei.hwbasemgr.IBaseResponseCallback, boolean, com.huawei.health.devicemgr.business.entity.DeviceInfo):void");
    }

    private void b(FileInputStream fileInputStream, FileInputStream fileInputStream2, ObjectInputStream objectInputStream, ObjectInputStream objectInputStream2) {
        IoUtils.e(fileInputStream);
        IoUtils.e(fileInputStream2);
        IoUtils.e(objectInputStream);
        IoUtils.e(objectInputStream2);
    }

    private void b(ArrayList<byte[]> arrayList, ArrayList<byte[]> arrayList2, int i, IBaseResponseCallback iBaseResponseCallback, boolean z, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter reportDataContent");
        LogUtil.a("HwCoreSleepManager", "reportDataContent(), ", ", coreSleepStatus: ", arrayList, ", coreSleepData: ", arrayList2, ", sucCode = ", Integer.valueOf(i), " coreSleepDatas size: ", Integer.valueOf(arrayList2.size()), ", coreSleepStatus size: ", Integer.valueOf(arrayList.size()), ", sucCode: ", Integer.valueOf(i), ", isNormalSync: ", Boolean.valueOf(z));
        if (arrayList.size() == 0) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "reportDataContent, sucCode is ", Integer.valueOf(i), "status file size is 0.");
            e(i, z, iBaseResponseCallback, deviceInfo);
        } else if (arrayList2.size() == 0) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "reportDataContent, sucCode is ", Integer.valueOf(i), "data file size is 0.");
            c(i, iBaseResponseCallback, z, deviceInfo);
            d(arrayList2, arrayList, deviceInfo);
        } else {
            c(i, iBaseResponseCallback, z, deviceInfo);
            d(arrayList2, arrayList, deviceInfo);
        }
    }

    private void d(ArrayList<byte[]> arrayList, ArrayList<byte[]> arrayList2, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter processCoreSleepData");
        if (this.l) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "processCoreSleepData is running");
            return;
        }
        this.l = true;
        ExtendHandler extendHandler = this.k;
        if (extendHandler != null) {
            extendHandler.removeMessages(0);
        }
        this.h = arrayList2.size();
        if (arrayList2.size() == 0) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "processCoreSleepData data is null");
            this.m = -1;
            this.l = false;
            return;
        }
        LogUtil.a("HwCoreSleepManager", "CoreSleepData from BT is as follow:");
        ArrayList<byte[]> arrayList3 = new ArrayList<>(16);
        ArrayList<byte[]> arrayList4 = new ArrayList<>(16);
        if (arrayList.size() > 0) {
            LogUtil.a("HwCoreSleepManager", "dataContents size is ", Integer.valueOf(arrayList.size()));
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList3.add(i, arrayList.get(i));
            }
        }
        if (arrayList2.size() > 0) {
            LogUtil.a("HwCoreSleepManager", "statusContents size is ", Integer.valueOf(arrayList2.size()));
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                arrayList4.add(i2, arrayList2.get(i2));
            }
        }
        LogUtil.a("HwCoreSleepManager", "CoreSleepData before getCpcResult(stringDataContents, stringStatusContents) is as follow");
        b(arrayList3, arrayList4, deviceInfo);
    }

    private void b(ArrayList<byte[]> arrayList, ArrayList<byte[]> arrayList2, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter getCpcResult");
        this.q = new ArrayList(16);
        this.s = new ArrayList(16);
        this.p = new ArrayList(16);
        Message obtain = Message.obtain();
        obtain.obj = deviceInfo;
        obtain.what = 1;
        this.k.sendMessage(obtain, 180000L);
        c(arrayList, arrayList2, new IBaseResponseCallback() { // from class: jen.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                jen.this.c(i, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Object obj) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter cpcResponseTask, errorCode=", Integer.valueOf(i));
        this.k.removeMessages(1);
        if (i == 0 && (obj instanceof bfd)) {
            bfd bfdVar = (bfd) obj;
            if (bfdVar.b().size() > 0) {
                this.q = bfdVar.b();
            }
            if (bfdVar.a().size() > 0) {
                this.s = bfdVar.a();
            }
            if (bfdVar.e().size() > 0) {
                this.p = bfdVar.e();
                bfk bfkVar = bfdVar.e().get(bfdVar.e().size() - 1);
                a(bfkVar.b(), bfkVar.a());
                if (this.q.size() == 0 && this.s.size() == 0) {
                    ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "SleepCalcFramesList.size() is 0, SleepStatusFramesList.size() is 0");
                    a(bfdVar.e());
                    this.m = 3;
                    this.l = false;
                    return;
                }
            } else {
                LogUtil.a("HwCoreSleepManager", "get cpc result no error");
            }
            k();
            this.j.c(this, this.q, this.s);
        } else {
            LogUtil.h("HwCoreSleepManager", "get cpc result error");
            this.m = 3;
        }
        this.l = false;
    }

    public ArrayList<byte[]> b(String str) {
        long length;
        ArrayList<byte[]> arrayList = new ArrayList<>(16);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwCoreSleepManager", "getFileByte filePath is empty.");
            return arrayList;
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                File file = FileUtils.getFile(str);
                fileInputStream = FileUtils.openInputStream(file);
                length = file.length();
            } catch (IOException e2) {
                LogUtil.b("HwCoreSleepManager", "getFileByte : ", e2.getMessage());
            }
            if (length > 20971520) {
                LogUtil.h("HwCoreSleepManager", "wrong file. file.length is too large.");
                return arrayList;
            }
            byte[] bArr = new byte[(int) length];
            if (fileInputStream.read(bArr) == length) {
                LogUtil.a("HwCoreSleepManager", "read inputStream success.");
            }
            arrayList.add(bArr);
            return arrayList;
        } finally {
            IoUtils.e((Closeable) null);
        }
    }

    public void a(SyncOption syncOption, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "enter startSynCoreSleep, isManualSync: ", Boolean.valueOf(syncOption.isManualSync()), ", isNightCheck: ", Boolean.valueOf(syncOption.isNightCheck()), ", isTemperatureCheck: ", Boolean.valueOf(syncOption.isTemperatureCheck()), ", mode:", Integer.valueOf(syncOption.getMode(SyncMode.INCREMENTAL.getValue())), ", interfaceType: ", syncOption.getCustomParameter("isUiInterface"));
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "startSynCoreSleep callback is null");
            return;
        }
        DeviceInfo e2 = jeo.e(this.y);
        if (e(iBaseResponseCallback, e2)) {
            boolean equals = "uiInterface".equals(syncOption.getCustomParameter("isUiInterface"));
            synchronized (this.v) {
                boolean b2 = b(e2);
                ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "startSynCoreSleep isSyncing: ", Boolean.valueOf(b2));
                if (b2) {
                    if (equals) {
                        a(iBaseResponseCallback);
                    } else {
                        c(iBaseResponseCallback);
                    }
                } else {
                    b(e2, true);
                    this.u = System.currentTimeMillis();
                    if (equals) {
                        d(syncOption, iBaseResponseCallback, e2);
                    } else {
                        a(syncOption, iBaseResponseCallback, e2);
                    }
                }
            }
        }
    }

    private boolean d(SyncOption syncOption, DeviceInfo deviceInfo) {
        int i = Calendar.getInstance().get(11);
        LogUtil.a("HwCoreSleepManager", "localHour=", Integer.valueOf(i));
        if (syncOption.isNightCheck() && i >= 0 && i < 6) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "startSyncCoreSleepAuto isPullDownRefresh and localHour false");
            return false;
        }
        if (!syncOption.isTemperatureCheck() || ThermalCallback.getInstance().isTriggerTask()) {
            return true;
        }
        ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "startSyncCoreSleepAuto does not have the conditions to trigger the task.");
        return false;
    }

    private long c(SyncOption syncOption) {
        int mode = syncOption.getMode(SyncMode.INCREMENTAL.getValue());
        if (mode == SyncMode.CUSTOM.getValue()) {
            return syncOption.getStartTime() / 1000;
        }
        if (mode == SyncMode.FULL.getValue()) {
            return 0L;
        }
        return i() / 1000;
    }

    private long b(SyncOption syncOption) {
        int mode = syncOption.getMode(SyncMode.INCREMENTAL.getValue());
        if (mode == SyncMode.CUSTOM.getValue()) {
            return syncOption.getEndTime() / 1000;
        }
        if (mode == SyncMode.FULL.getValue()) {
            return System.currentTimeMillis() / 1000;
        }
        if (e(syncOption)) {
            return Long.parseLong(syncOption.getCustomParameter("hasUpgradeTime")) / 1000;
        }
        return System.currentTimeMillis() / 1000;
    }

    private boolean e(SyncOption syncOption) {
        return syncOption.getCustomParameter("hasUpgradeTime") != null;
    }

    private void c(final DeviceInfo deviceInfo, SyncOption syncOption, final IBaseResponseCallback iBaseResponseCallback) {
        a(syncOption);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "startSyncCoreSleepByUi dicSyncCoreSleep start");
        this.y.dicSyncCoreSleep(deviceInfo.getDeviceIdentify(), new IBaseResponseCallback() { // from class: jen.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "startDicSync errorCode:", Integer.valueOf(i));
                jen.this.b(deviceInfo, false);
                iBaseResponseCallback.d(i, obj);
                jen.this.a(i, "dicSyncCoreSleep");
            }
        });
    }

    private boolean j() {
        return b(jeo.e(this.y));
    }

    private boolean b(DeviceInfo deviceInfo) {
        Boolean bool;
        if (deviceInfo == null || this.o == null) {
            return false;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        return this.o.containsKey(deviceIdentify) && (bool = this.o.get(deviceIdentify)) != null && bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null || this.o == null) {
            return;
        }
        ReleaseLogUtil.e("BTSYNC_CoreSleep_HwCoreSleepManager", "update deviceSync: ", Boolean.valueOf(z), ", deviceName: ", deviceInfo.getDeviceName());
        this.o.put(deviceInfo.getDeviceIdentify(), Boolean.valueOf(z));
    }

    private boolean e(IBaseResponseCallback iBaseResponseCallback, DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "startSynCoreSleep deviceInfo null or DEVICE_DISCONNECTED return!");
            iBaseResponseCallback.d(21000, "");
            return false;
        }
        DeviceCapability e2 = jeo.e(deviceInfo, this.y);
        if (e2 == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "startSynCoreSleep capability is null.");
            iBaseResponseCallback.d(21001, "capability is null");
            return false;
        }
        LogUtil.a("HwCoreSleepManager", "isSupportQueryDeviceCoreSleepSwitch(): ", Boolean.valueOf(e2.isSupportQueryDeviceCoreSleepSwitch()), ", isSupportCoreSleep() : ", Boolean.valueOf(e2.isSupportCoreSleep()));
        if (e2.isSupportCoreSleep() || e2.isSupportQueryDeviceCoreSleepSwitch()) {
            return true;
        }
        ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "startSynCoreSleep don't satisfy coreSleep sync condition!");
        iBaseResponseCallback.d(21002, "don't satisfy coreSleep sync condition");
        return false;
    }

    private void b(DeviceInfo deviceInfo, SyncOption syncOption, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        a(syncOption);
        long c2 = c(syncOption);
        long b2 = b(syncOption);
        if (z && c2 >= b2 && e(syncOption)) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_HwCoreSleepManager", "startTime >= endTime", "startime: ", Long.valueOf(c2), "endTime: ", Long.valueOf(b2));
            b(deviceInfo, false);
            b(21005, "startTime >= endTime");
        } else {
            long e2 = e(c2, b2);
            this.t = e2;
            this.r = b2;
            this.ab.d("HwCoreSleepManager", new e(e2, b2, iBaseResponseCallback, z, deviceInfo));
        }
    }
}
