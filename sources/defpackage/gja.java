package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.threeCircle.remind.model.OnRemindHandle;
import com.huawei.health.threeCircle.remind.model.ThreeCircleRemindData;
import com.huawei.health.threeCircle.remind.receiver.SportRemindReceiver;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class gja {

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f12821a;
    private DeviceInfo b;
    private Map<String, Integer> c;
    private gjs d;
    private ResponseCallback<ThreeCircleRemindData> e;
    private ConcurrentHashMap<String, OnRemindHandle> j;

    /* synthetic */ void e(int i, ThreeCircleRemindData threeCircleRemindData) {
        if (threeCircleRemindData == null) {
            ReleaseLogUtil.c("R_TC_SprtRmdDistCtr", "ThreeCircleRemindData is null.");
            return;
        }
        if ("TodayAchievement".equals(threeCircleRemindData.getRemindType()) || "OverGoal".equals(threeCircleRemindData.getRemindType())) {
            if ("0".equals(gjz.c("900200011"))) {
                LogUtil.h("TC_SprtRmdDistCtr", "switch is close.", threeCircleRemindData.getRemindType(), threeCircleRemindData.getSubRemindType());
                return;
            }
        } else if ("0".equals(gjz.c("900200010"))) {
            ReleaseLogUtil.d("R_TC_SprtRmdDistCtr", "switch is close.", threeCircleRemindData.getRemindType(), threeCircleRemindData.getSubRemindType());
            return;
        }
        ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "response remind data:", threeCircleRemindData.toString());
        if (!gjf.d()) {
            if (Utils.o()) {
                return;
            }
            ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "show notification.");
            gjc.b(threeCircleRemindData);
            return;
        }
        ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "send broad cast.");
        if (threeCircleRemindData.getDeviceEventData() == null) {
            ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "no device event data.");
            return;
        }
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo == null) {
            ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "no device connected.");
        } else {
            e(deviceInfo, threeCircleRemindData);
        }
    }

    private gja() {
        this.e = new ResponseCallback() { // from class: gjb
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                gja.this.e(i, (ThreeCircleRemindData) obj);
            }
        };
    }

    /* loaded from: classes4.dex */
    static class d {
        private static final gja e = new gja();
    }

    public static final gja a() {
        return d.e;
    }

    public void c() {
        ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "registerDeviceStatusReceiver");
        if (this.f12821a == null) {
            this.f12821a = new BroadcastReceiver() { // from class: gja.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    LogUtil.a("TC_SprtRmdDistCtr", "mDeviceConnectStatusReceiver onReceive");
                    if (intent == null) {
                        LogUtil.b("TC_SprtRmdDistCtr", "mDeviceConnectStatusReceiver intent is null");
                        return;
                    }
                    if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                        ReleaseLogUtil.d("R_TC_SprtRmdDistCtr", "registerDeviceStatusReceiver action is not ACTION_CONNECTION_STATE_CHANGED");
                        return;
                    }
                    Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                    if (!(parcelableExtra instanceof DeviceInfo)) {
                        ReleaseLogUtil.d("R_TC_SprtRmdDistCtr", "registerDeviceStatusReceiver object is not DeviceInfo");
                    } else {
                        gja.this.d((DeviceInfo) parcelableExtra);
                    }
                }
            };
        }
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.e(), this.f12821a, intentFilter, LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            boolean b = gjf.b(deviceInfo);
            ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "mConnectStateChangedReceiver() status: ", Integer.valueOf(deviceInfo.getDeviceConnectState()), deviceInfo.getDeviceName(), " isSupportCompleteThreeCircle:", Boolean.valueOf(b));
            if (b) {
                if (deviceInfo.getDeviceConnectState() == 2) {
                    this.b = deviceInfo;
                    e(deviceInfo);
                }
                if (deviceInfo.getDeviceConnectState() == 3) {
                    this.b = null;
                    a(deviceInfo);
                }
            }
        }
    }

    private void e(DeviceInfo deviceInfo, ThreeCircleRemindData threeCircleRemindData) {
        if (threeCircleRemindData == null) {
            LogUtil.b("TC_SprtRmdDistCtr", "remindData == null.");
        } else if (threeCircleRemindData.getDeviceEventData() == null) {
            LogUtil.b("TC_SprtRmdDistCtr", "DeviceEventData == null.");
        } else {
            gjf.c(deviceInfo, threeCircleRemindData.getDeviceEventData());
        }
    }

    private void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            ReleaseLogUtil.c("R_TC_SprtRmdDistCtr", "device info null or not connected.");
            return;
        }
        ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "onDeviceConnected:", deviceInfo.getDeviceName());
        gjf.e(deviceInfo);
        cuk.b().registerDeviceSampleListener("hw.sport.threecircle", new DataReceiveCallback() { // from class: gjg
            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public final void onDataReceived(int i, DeviceInfo deviceInfo2, cvr cvrVar) {
                gja.this.e(i, deviceInfo2, cvrVar);
            }
        });
    }

    /* synthetic */ void e(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "receive device event.", Integer.valueOf(i));
        if (!(cvrVar instanceof cvp)) {
            LogUtil.b("TC_SprtRmdDistCtr", "message not instanceof SampleEvent.");
        } else {
            a(gjf.b((cvp) cvrVar), true);
        }
    }

    private void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_TC_SprtRmdDistCtr", "onDeviceDisconnected: deviceInfo is null");
        } else {
            ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "onDeviceDisconnected:", deviceInfo.getDeviceName());
            cuk.b().unRegisterDeviceSampleListener("hw.sport.threecircle");
        }
    }

    private void a(String str, boolean z) {
        OnRemindHandle b = b(str);
        if (b == null) {
            return;
        }
        if (this.d == null) {
            this.d = new gjs();
        }
        b.onTrigger(g(), new gjr(this.d, str, z, this.e));
    }

    public void c(String str) {
        a(str, false);
    }

    public void aNf_(String str, Bundle bundle) {
        OnRemindHandle b = b(str);
        if (b == null) {
            return;
        }
        if (this.d == null) {
            this.d = new gjs();
        }
        b.onTrigger(bundle, g(), new gjr(this.d, str, this.e));
    }

    public void d() {
        String[] strArr = {"LagEncourage", "YesterdaySummary", "ActiveWeek", "PerfectMonth"};
        for (int i = 0; i < 4; i++) {
            String str = strArr[i];
            OnRemindHandle b = b(str);
            if (b != null && b.isActiveTrigger()) {
                ReleaseLogUtil.e("R_TC_SprtRmdDistCtr", "tryActiveTriggerRemind:", str);
                b.onTrigger(g(), new gjr(this.d, str, false, this.e));
            }
        }
    }

    public void b() {
        a(101010, SharedPerferenceUtils.w(BaseApplication.e()), "TodayAchievement");
        a(101011, SharedPerferenceUtils.ad(BaseApplication.e()), "YesterdaySummary");
        d("WeekSummary");
        d("ActiveWeek");
        d("PerfectMonth");
        d("MonthSummary");
    }

    private void a(int i, int i2, String str) {
        int b = DateFormatUtil.b(System.currentTimeMillis());
        if (i2 == -1 || (b != i2 && i2 != 0)) {
            sqa.ekn_(i, new Intent(BaseApplication.e(), (Class<?>) SportRemindReceiver.class), 335544320);
        }
        if (b == i2 && i2 != 0) {
            LogUtil.a("TC_SprtRmdDistCtr", "remind already");
        } else {
            d(str);
            ReleaseLogUtil.e("TC_SprtRmdDistCtr", "setEventRemind RemindAlarm", Integer.valueOf(b), "remindDay:", Integer.valueOf(i2));
        }
    }

    public void d(String str) {
        OnRemindHandle b = b(str);
        if (b == null) {
            return;
        }
        b.registerAlarm();
    }

    public void e() {
        ConcurrentHashMap<String, OnRemindHandle> concurrentHashMap = this.j;
        if (concurrentHashMap != null) {
            for (OnRemindHandle onRemindHandle : concurrentHashMap.values()) {
                if (onRemindHandle != null) {
                    onRemindHandle.destroy();
                }
            }
        }
        gjs gjsVar = this.d;
        if (gjsVar != null) {
            gjsVar.d();
            this.d = null;
        }
        if (this.f12821a != null) {
            BaseApplication.e().unregisterReceiver(this.f12821a);
            this.f12821a = null;
        }
    }

    private OnRemindHandle b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.j == null) {
            this.j = new ConcurrentHashMap<>();
        }
        OnRemindHandle onRemindHandle = this.j.get(str);
        if (onRemindHandle != null) {
            return onRemindHandle;
        }
        if ("TodayAchievement".equals(str) || "LagEncourage".equals(str) || "OverGoal".equals(str)) {
            gjj gjjVar = new gjj();
            this.j.put("TodayAchievement", gjjVar);
            this.j.put("LagEncourage", gjjVar);
            this.j.put("OverGoal", gjjVar);
            return gjjVar;
        }
        return e(str);
    }

    private OnRemindHandle e(String str) {
        if ("YesterdaySummary".equals(str)) {
            gjo gjoVar = new gjo();
            this.j.put("YesterdaySummary", gjoVar);
            return gjoVar;
        }
        if ("WeekSummary".equals(str)) {
            gjq gjqVar = new gjq();
            this.j.put("WeekSummary", gjqVar);
            return gjqVar;
        }
        if ("ActiveWeek".equals(str) || "PerfectWeek".equals(str)) {
            gjp gjpVar = new gjp();
            this.j.put("ActiveWeek", gjpVar);
            return gjpVar;
        }
        if ("PerfectMonth".equals(str)) {
            gje gjeVar = new gje();
            this.j.put("PerfectMonth", gjeVar);
            return gjeVar;
        }
        if ("MonthSummary".equals(str)) {
            gjk gjkVar = new gjk();
            this.j.put("MonthSummary", gjkVar);
            return gjkVar;
        }
        ReleaseLogUtil.d("R_TC_SprtRmdDistCtr", "not support this remind type.", str);
        return null;
    }

    private Map<String, Integer> g() {
        Map<String, Integer> map = this.c;
        if (map == null || map.size() == 0) {
            HashMap hashMap = new HashMap();
            this.c = hashMap;
            hashMap.put("OverGoal", 1000);
            this.c.put("TodayAchievement", 900);
            this.c.put("MonthSummary", 800);
            this.c.put("WeekSummary", 700);
            this.c.put("PerfectMonth", 600);
            this.c.put("PerfectWeek", 500);
            this.c.put("ActiveWeek", 400);
            this.c.put("YesterdaySummary", 300);
            this.c.put("LagEncourage", 200);
        }
        return this.c;
    }
}
