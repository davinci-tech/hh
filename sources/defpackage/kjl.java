package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.openalliance.ad.constant.ParamConstants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes.dex */
public class kjl implements ParserInterface {
    private static kjl b;
    private PendingIntent ac;
    private ThreadPoolManager ae;
    private ExtendHandler ah;
    private int g;
    private byte h;
    private String i;
    private AlarmManager j;
    private DeviceInfo k;
    private Context n;
    private long x;
    private List<String> z;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14415a = new Object();
    private Set<Integer> d = new HashSet();
    private int ag = 0;
    private Location s = null;
    private int ad = 0;
    private boolean v = true;
    private jbz o = null;
    private boolean w = false;
    private b t = new b();
    private byte[] af = new byte[6];
    private String y = "";
    private boolean u = false;
    private boolean p = false;
    private long ab = 0;
    private String l = "";
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: kjl.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("HwWeatherManager", "mAlarmClockReceiver receiver");
            kjl.this.r();
        }
    };
    private BroadcastReceiver m = new BroadcastReceiver() { // from class: kjl.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HwWeatherManager", "mFirstConnectReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("HwWeatherManager", "mFirstConnectReceiver action:", action);
            if ("com.huawei.bone.action.REQUEST_BIND_DEVICE".equals(action) && TextUtils.isEmpty(intent.getStringExtra("connect_status"))) {
                kjl.this.p = true;
                return;
            }
            if ("weather_location_change".equals(action)) {
                jbo.d(intent.getStringExtra("location"));
            } else if ("weather_http_change".equalsIgnoreCase(action)) {
                jbo.e(intent.getIntExtra("code", 0));
            } else {
                LogUtil.c("HwWeatherManager", "mFirstConnectReceiver else");
            }
        }
    };
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: kjl.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HwWeatherManager", "mConnectStateChangedReceiver intent is null");
                return;
            }
            LogUtil.a("HwWeatherManager", "mConnectStateChangedReceiver context: ", context, " intent:", intent.getAction());
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    LogUtil.h("HwWeatherManager", "mConnectStateChangedReceiver deviceInfo is null");
                    return;
                }
                LogUtil.a("HwWeatherManager", "mConnectStateChangedReceiver DeviceConnectState: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                if (deviceInfo.getDeviceConnectState() != 2) {
                    return;
                }
                DeviceCapability e = kjn.e(deviceInfo);
                if (e == null || !e.isWeatherPush()) {
                    LogUtil.h("HwWeatherManager", "mConnectStateChangedReceiver DeviceCapability is null!");
                    return;
                }
                DeviceInfo deviceInfo2 = null;
                Iterator<DeviceInfo> it = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwWeatherManager").iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DeviceInfo next = it.next();
                    if (!cvt.c(next.getProductType())) {
                        deviceInfo2 = next;
                        break;
                    }
                }
                if (deviceInfo2 == null) {
                    kjl.this.g = -1;
                } else {
                    kjl.this.g = deviceInfo2.getDeviceBluetoothType();
                }
                if (kjl.this.l.equals(deviceInfo.getSecurityDeviceId()) && kjl.this.g != 1) {
                    kjl.this.k = deviceInfo;
                    LogUtil.a("HwWeatherManager", "mConnectStateChangedReceiver the same device");
                } else {
                    LogUtil.a("HwWeatherManager", "mConnectStateChangedReceiver not the same device or BR type");
                    kjl.this.l = deviceInfo.getSecurityDeviceId();
                    kjl.this.k = deviceInfo2;
                    kjl.this.ab = 0L;
                    kjl.this.u = true;
                }
                kjl.this.a();
            }
        }
    };
    private BroadcastReceiver aa = new BroadcastReceiver() { // from class: kjl.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HwWeatherManager", "mNetworkChangedReceiver intent is null");
                return;
            }
            if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                LogUtil.h("HwWeatherManager", "mNetworkChangedReceiver not CONNECTIVITY_ACTION");
                return;
            }
            int b2 = kjn.b(context);
            if (b2 == -1) {
                LogUtil.a("HwWeatherManager", "mNetworkChangedReceiver net disconnect");
                return;
            }
            if (b2 == 0 || b2 == 1) {
                LogUtil.a("HwWeatherManager", "mNetworkChangedReceiver network connected: ", Integer.valueOf(b2));
                if (SystemClock.elapsedRealtime() - kjl.this.x < 60000) {
                    LogUtil.a("HwWeatherManager", "mNetworkChangedReceiver network connected within 1 minute");
                    return;
                }
                DeviceCapability e = kjn.e();
                if (e == null || !e.isWeatherPush()) {
                    return;
                }
                LogUtil.a("HwWeatherManager", "mNetworkChangedReceiver support weather push");
                kjl.this.x = SystemClock.elapsedRealtime();
                kjl.this.a();
                return;
            }
            LogUtil.h("HwWeatherManager", "mNetworkChangedReceiver unknown type");
        }
    };
    private ILoactionCallback q = new ILoactionCallback() { // from class: kjl.6
        @Override // com.huawei.hwlocationmgr.model.ILoactionCallback
        public void dispatchLocationChanged(Location location) {
            synchronized (kjl.c) {
                Object[] objArr = new Object[4];
                objArr[0] = "ILocationResultCallback get gps location:";
                objArr[1] = Boolean.valueOf(location != null);
                objArr[2] = " locationTasks:";
                objArr[3] = kjl.this.d.toString();
                jrb.d("HwWeatherManager", objArr);
            }
            ExtendHandler extendHandler = kjl.this.ah;
            if (extendHandler != null) {
                extendHandler.removeMessages(200);
            }
            kjl.this.u();
            if (location != null) {
                kjl.this.s = location;
                String bOM_ = kjn.bOM_(location);
                kjn.bON_(location);
                synchronized (kjl.c) {
                    if (!kjl.this.d.isEmpty()) {
                        Iterator it = kjl.this.d.iterator();
                        while (it.hasNext()) {
                            int intValue = ((Integer) it.next()).intValue();
                            if (intValue == 1) {
                                kjl.this.bOI_(location);
                            } else if (intValue == 2) {
                                kjl.this.k();
                            } else if (intValue == 3) {
                                kjm.d(true, bOM_);
                            } else {
                                LogUtil.h("HwWeatherManager", "ILocationResultCallback get default");
                            }
                        }
                    }
                }
            } else {
                kjl.this.q();
            }
            synchronized (kjl.c) {
                kjl.this.d.clear();
            }
        }
    };
    private jqi r = jqi.a();
    private ktg ai = ktg.c();

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        synchronized (c) {
            if (!this.d.isEmpty()) {
                Iterator<Integer> it = this.d.iterator();
                while (it.hasNext()) {
                    int intValue = it.next().intValue();
                    if (intValue == 1) {
                        s();
                    } else if (intValue == 2) {
                        kjm.c(1, -1);
                    } else if (intValue == 3) {
                        kjm.e(24, 6, 100001);
                    } else {
                        LogUtil.h("HwWeatherManager", "sendErrorCode default");
                    }
                }
            }
        }
        kjn.e(1);
    }

    private kjl(Context context) {
        this.n = context;
        if (this.r == null) {
            LogUtil.b("HwWeatherManager", "mHwSwitchSettingManager is null");
        }
        this.ah = HandlerCenter.yt_(this.t, "HwWeatherManager");
        o();
        LogUtil.a("HwWeatherManager", "HwWeatherManager init");
    }

    public static kjl b() {
        kjl kjlVar;
        synchronized (f14415a) {
            if (b == null) {
                b = new kjl(BaseApplication.getContext());
            }
            kjlVar = b;
        }
        return kjlVar;
    }

    private void o() {
        BroadcastManagerUtil.bFC_(this.n, this.f, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
        this.n.registerReceiver(this.aa, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"), LocalBroadcast.c, null);
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.REQUEST_BIND_DEVICE");
        if (jbo.h()) {
            intentFilter.addAction("weather_location_change");
            intentFilter.addAction("weather_http_change");
        }
        BroadcastManagerUtil.bFA_(this.n, this.m, intentFilter, LocalBroadcast.c, null);
        ArrayList arrayList = new ArrayList(0);
        this.z = arrayList;
        arrayList.add(HAWebViewInterface.NETWORK);
        this.z.add(GeocodeSearch.GPS);
        if (EnvironmentInfo.r()) {
            Object systemService = this.n.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (systemService instanceof AlarmManager) {
                this.j = (AlarmManager) systemService;
            } else {
                LogUtil.a("HwWeatherManager", "alarmManager is null");
            }
            BroadcastManagerUtil.bFC_(this.n, this.e, new IntentFilter("action_broadcast_alarm_clock"), LocalBroadcast.c, null);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        d(bArr, deviceInfo);
        e(bArr);
    }

    public void d(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null || bArr.length < 5) {
            LogUtil.h("HwWeatherManager", "handleDataFromDevice, length < 5, return");
            return;
        }
        if (bArr[0] != 15) {
        }
        String d = cvx.d(bArr);
        LogUtil.a("HwWeatherManager", "handleDataFromDevice", d);
        switch (bArr[1]) {
            case 1:
                if (!TextUtils.isEmpty(d) && d.length() >= 16 && "000186A0".equals(d.substring(8, 16))) {
                    long currentTimeMillis = System.currentTimeMillis();
                    this.ab = currentTimeMillis;
                    this.p = false;
                    LogUtil.a("HwWeatherManager", "success send data,mLastSendTime is :", Long.valueOf(currentTimeMillis));
                    break;
                }
                break;
            case 2:
                a(bArr[4], deviceInfo);
                break;
            case 3:
                a(bArr);
                break;
            case 4:
                b(bArr);
                break;
            case 5:
                LogUtil.a("HwWeatherManager", "COMMAND_ID_UNIT_WEATHER!");
                n();
                break;
            case 6:
                e(bArr, deviceInfo);
                break;
            case 8:
                if (!TextUtils.isEmpty(d) && d.length() >= 16 && "000186A0".equals(d.substring(8, 16))) {
                    LogUtil.a("HwWeatherManager", "COMMAND_ID_FUTURE_WEATHER :", d.substring(8, 16));
                    break;
                }
                break;
            case 10:
                b(bArr, deviceInfo);
                break;
        }
    }

    public void a() {
        this.r.getSwitchSetting("weather_switch_status", new IBaseResponseCallback() { // from class: kjl.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HwWeatherManager", "checkIsNeedPush errorCode: ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String) && "false".equals((String) obj)) {
                    kjl.this.c(false);
                    LogUtil.h("HwWeatherManager", "checkIsNeedPush weather switch is false so return");
                } else {
                    kjl.this.d(false);
                    if (kjl.this.w) {
                        return;
                    }
                    kjl.this.c(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(double d, double d2) {
        if (this.o == null) {
            LogUtil.h("HwWeatherManager", "isPushCondition mDataWeather is null");
            return true;
        }
        if (System.currentTimeMillis() - this.ab > 3540000) {
            LogUtil.a("HwWeatherManager", "isPushCondition over 1 hour");
            return true;
        }
        try {
        } catch (Exception e) {
            LogUtil.b("HwWeatherManager", "isPushCondition exception ", ExceptionUtils.d(e));
        }
        if (Locale.getDefault() == null) {
            LogUtil.h("HwWeatherManager", "isPushCondition Locale.getDefault is null");
            return true;
        }
        List<Address> fromLocation = new Geocoder(this.n, Locale.getDefault()).getFromLocation(d, d2, 1);
        if (fromLocation != null && !fromLocation.isEmpty()) {
            Address address = fromLocation.get(0);
            if (address != null && address.getSubLocality() != null && this.y != null) {
                String subLocality = address.getSubLocality();
                LogUtil.a("HwWeatherManager", "isPushCondition SubLocality:", subLocality, " mLastArea:", this.y);
                if (!this.y.equals(subLocality)) {
                    this.y = subLocality;
                    return true;
                }
                return false;
            }
            LogUtil.h("HwWeatherManager", "isPushCondition address is null or empty");
            return true;
        }
        LogUtil.h("HwWeatherManager", "isPushCondition resultList is null");
        return true;
    }

    public void c(boolean z) {
        LogUtil.a("HwWeatherManager", "setWeatherSwitchStatus enable: ", Boolean.valueOf(z));
        if (!z) {
            kjm.c(2, -1);
        }
        this.v = z;
        if (this.w && !z) {
            this.w = false;
            this.u = false;
            g();
            LogUtil.a("HwWeatherManager", "setWeatherSwitchStatus stop two hour handler");
            return;
        }
        if (z) {
            LogUtil.a("HwWeatherManager", "setWeatherSwitchStatus start two hour handler");
            this.w = true;
            r();
            return;
        }
        LogUtil.h("HwWeatherManager", "setWeatherSwitchStatus mIsPolling is false do nothing");
    }

    public void d(boolean z) {
        this.u = z;
        DeviceCapability e = kjn.e();
        if (e == null) {
            LogUtil.h("HwWeatherManager", "pushWeatherData DeviceCapability is null");
        } else if (!e.isSupportUnitWeather()) {
            LogUtil.a("HwWeatherManager", "pushWeatherData not is SupportUnitWeather");
            n();
        } else {
            this.r.getSwitchSetting("weather_switch_unit_status", new IBaseResponseCallback() { // from class: kjl.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    boolean z2;
                    LogUtil.a("HwWeatherManager", "pushWeatherData errorCode: ", Integer.valueOf(i));
                    if (i == 0 && (obj instanceof String)) {
                        z2 = !"false".equals((String) obj);
                    } else {
                        z2 = !UnitUtil.c();
                    }
                    LogUtil.a("HwWeatherManager", "pushWeatherData weather_unit_status isMetric: ", Boolean.valueOf(z2));
                    if (z2) {
                        kjm.b(0);
                    } else {
                        kjm.b(1);
                    }
                }
            });
        }
    }

    /* loaded from: classes5.dex */
    class b implements Handler.Callback {
        private b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                return false;
            }
            int i = message.what;
            if (i == 100) {
                LogUtil.a("HwWeatherManager", "TwoHoursHandler MSG_ACTION_WEATHER_REPORT_DELAY");
                kjl.this.r();
                return true;
            }
            if (i == 200) {
                kjl.this.m();
                return true;
            }
            if (i == 400) {
                LogUtil.a("HwWeatherManager", "TwoHoursHandler MSG_GET_WEATHER_FAILED_RETRY mRetryNetworkNum: ", Integer.valueOf(kjl.this.ag));
                if (kjl.this.ag < 3 && kjl.this.s != null) {
                    kjl.this.k();
                    kjn.e(3);
                } else {
                    LogUtil.a("HwWeatherManager", "TwoHoursHandler MSG_GET_WEATHER_FAILED_RETRY mRetryNetworkNum > 3");
                    kjl.this.ag = 0;
                    kjn.e(4);
                }
                return true;
            }
            if (i == 401) {
                LogUtil.a("HwWeatherManager", "TwoHoursHandler MSG_GET_FUTURE_WEATHER_FAILED_RETRY mRetryFutureNetworkNumber:", Integer.valueOf(kjl.this.ad));
                if (kjl.this.ad < 3 && kjl.this.s != null) {
                    kjl.this.k();
                    kjn.e(3);
                } else {
                    LogUtil.a("HwWeatherManager", "TwoHoursHandler MSG_GET_FUTURE_WEATHER_FAILED_RETRY mRetryFutureNetworkNumber > 3");
                    kjl.this.ad = 0;
                    kjn.e(4);
                }
                return true;
            }
            LogUtil.h("HwWeatherManager", "TwoHoursHandler default");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        u();
        Location bOJ_ = bOJ_();
        Object obj = c;
        synchronized (obj) {
            Object[] objArr = new Object[4];
            objArr[0] = "getLocationTimeOut locationTasks:";
            objArr[1] = this.d.toString();
            objArr[2] = " location:";
            objArr[3] = Boolean.valueOf(bOJ_ != null);
            jrb.d("HwWeatherManager", objArr);
        }
        if (bOJ_ != null) {
            String bOM_ = kjn.bOM_(bOJ_);
            kjn.bON_(bOJ_);
            synchronized (obj) {
                if (!this.d.isEmpty()) {
                    Iterator<Integer> it = this.d.iterator();
                    while (it.hasNext()) {
                        int intValue = it.next().intValue();
                        if (intValue == 1) {
                            this.s = bOJ_;
                            bOI_(bOJ_);
                        } else if (intValue == 2) {
                            this.s = bOH_(bOJ_);
                            k();
                        } else if (intValue == 3) {
                            kjm.d(true, bOM_);
                        } else {
                            LogUtil.h("HwWeatherManager", "getLocationTimeOut default");
                        }
                    }
                }
            }
        } else {
            q();
        }
        synchronized (c) {
            this.d.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (this.w) {
            DeviceInfo deviceInfo = this.k;
            if (deviceInfo != null && deviceInfo.getProductType() >= 34) {
                this.w = false;
                g();
                LogUtil.h("HwWeatherManager", "processPolling new device, do not poll");
                return;
            }
            if (EnvironmentInfo.r()) {
                i();
                w();
            } else {
                ExtendHandler extendHandler = this.ah;
                if (extendHandler != null) {
                    extendHandler.removeMessages(100);
                    extendHandler.sendEmptyMessage(100, AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL);
                }
            }
            boolean b2 = PowerKitManager.e().b();
            LogUtil.h("HwWeatherManager", "processPolling isSleeping: ", Boolean.valueOf(b2));
            if (b2) {
                return;
            }
            n();
            return;
        }
        LogUtil.a("HwWeatherManager", "processPolling can not pushWeatherToDevice");
        g();
    }

    private void g() {
        if (EnvironmentInfo.r()) {
            i();
            return;
        }
        ExtendHandler extendHandler = this.ah;
        if (extendHandler != null) {
            extendHandler.removeMessages(100);
        }
    }

    private void w() {
        if (this.j == null) {
            LogUtil.h("HwWeatherManager", "startAlarm mAlarmManager is null");
            return;
        }
        Intent intent = new Intent("action_broadcast_alarm_clock");
        if (this.ac == null) {
            this.ac = PendingIntent.getBroadcast(this.n, 0, intent, 201326592);
        }
        try {
            this.j.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL, this.ac);
        } catch (SecurityException e) {
            jrb.d("HwWeatherManager", "Weather push startAlarm exception ", e.getMessage());
        }
    }

    private void i() {
        PendingIntent pendingIntent;
        AlarmManager alarmManager = this.j;
        if (alarmManager == null || (pendingIntent = this.ac) == null) {
            LogUtil.h("HwWeatherManager", "startAlarm mAlarmManager or mPendingIntent is null");
        } else {
            alarmManager.cancel(pendingIntent);
        }
    }

    private void n() {
        if (h()) {
            return;
        }
        if ((this.u || jbo.a()) && !kjn.d()) {
            d(2);
            return;
        }
        Location bOJ_ = bOJ_();
        if (bOJ_ != null) {
            this.s = bOH_(bOJ_);
            k();
            return;
        }
        Location bOL_ = kjn.bOL_();
        Object[] objArr = new Object[2];
        objArr[0] = "getLocationForWeatherInquire locationWeather valid is ";
        objArr[1] = Boolean.valueOf(bOL_ != null);
        jrb.d("HwWeatherManager", objArr);
        if (bOL_ != null && System.currentTimeMillis() - bOL_.getTime() < 3600000) {
            LogUtil.a("HwWeatherManager", "getLocationForWeatherInquire locationWeather less 1 hour");
            this.s = bOH_(bOL_);
            k();
            return;
        }
        LogUtil.a("HwWeatherManager", "getLocationForWeatherInquire locationWeather over 1 hour");
        if (kjn.d()) {
            Location bOK_ = kjn.bOK_();
            if (bOK_ != null) {
                this.s = bOK_;
                k();
                return;
            } else {
                kjm.c(1, -1);
                return;
            }
        }
        d(2);
    }

    private boolean h() {
        boolean f = kjn.f();
        jrb.d("HwWeatherManager", "checkPushWeatherConditions() isHasGpsSwitchAndPermissions:", Boolean.valueOf(f));
        if (!f) {
            Location bOK_ = kjn.bOK_();
            if (bOK_ != null) {
                this.s = bOK_;
                k();
                return true;
            }
            kjm.c(1, -1);
            return true;
        }
        if (!CommonUtil.aa(this.n)) {
            jrb.d("HwWeatherManager", "checkPushWeatherConditions network connect false");
            kjm.c(0, -1);
            return true;
        }
        jrb.d("HwWeatherManager", "checkPushWeatherConditions query weather now, mIsWeatherNeededNow: ", Boolean.valueOf(this.u), " isBackgroundAndNoPermission:", Boolean.valueOf(kjn.d()));
        return false;
    }

    private Location bOH_(Location location) {
        Location bOK_ = kjn.bOK_();
        if (bOK_ == null) {
            return location;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "queryLocationByPhoneCache location.getTime() > deviceLocation.getTime() ";
        objArr[1] = Boolean.valueOf(location.getTime() > bOK_.getTime());
        jrb.d("HwWeatherManager", objArr);
        return location.getTime() > bOK_.getTime() ? location : bOK_;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.location.Location bOJ_() {
        /*
            r10 = this;
            java.lang.String r0 = "HwWeatherManager"
            r1 = 0
            ktg r2 = r10.ai     // Catch: android.os.BadParcelableException -> L54
            java.util.List<java.lang.String> r3 = r10.z     // Catch: android.os.BadParcelableException -> L54
            android.location.Location r2 = r2.bQQ_(r3)     // Catch: android.os.BadParcelableException -> L54
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L32
            ktg r5 = r10.ai     // Catch: android.os.BadParcelableException -> L52
            android.location.Location r2 = r5.bQR_()     // Catch: android.os.BadParcelableException -> L52
            if (r2 == 0) goto L3c
            long r5 = java.lang.System.currentTimeMillis()     // Catch: android.os.BadParcelableException -> L52
            long r7 = r2.getTime()     // Catch: android.os.BadParcelableException -> L52
            long r5 = r5 - r7
            r7 = 3600000(0x36ee80, double:1.7786363E-317)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 < 0) goto L3c
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch: android.os.BadParcelableException -> L54
            java.lang.String r5 = "queryLocationByPhoneCache location over 1 hour, set location is null"
            r2[r3] = r5     // Catch: android.os.BadParcelableException -> L54
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)     // Catch: android.os.BadParcelableException -> L54
            goto L3d
        L32:
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: android.os.BadParcelableException -> L52
            java.lang.String r5 = "queryLocationByPhoneCache lastKnown location is true"
            r1[r3] = r5     // Catch: android.os.BadParcelableException -> L52
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)     // Catch: android.os.BadParcelableException -> L52
        L3c:
            r1 = r2
        L3d:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: android.os.BadParcelableException -> L54
            java.lang.String r5 = "queryLocationByPhoneCache location is "
            r2[r3] = r5     // Catch: android.os.BadParcelableException -> L54
            if (r1 == 0) goto L48
            r3 = r4
        L48:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch: android.os.BadParcelableException -> L54
            r2[r4] = r3     // Catch: android.os.BadParcelableException -> L54
            defpackage.jrb.d(r0, r2)     // Catch: android.os.BadParcelableException -> L54
            goto L67
        L52:
            r1 = move-exception
            goto L58
        L54:
            r2 = move-exception
            r9 = r2
            r2 = r1
            r1 = r9
        L58:
            java.lang.String r3 = "queryLocationByPhoneCache badParcelableException:  "
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r1}
            defpackage.jrb.d(r0, r1)
            r1 = r2
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kjl.bOJ_():android.location.Location");
    }

    private void d(int i) {
        synchronized (c) {
            if (!this.d.isEmpty()) {
                this.d.add(Integer.valueOf(i));
                LogUtil.h("HwWeatherManager", "queryLocationByGps already regLocationListener, so return");
                return;
            }
            this.d.add(Integer.valueOf(i));
            LogUtil.a("HwWeatherManager", "queryLocationByGps register LocationListener");
            u();
            this.ai.e(this.q, "wearWeatherLocation");
            ExtendHandler extendHandler = this.ah;
            if (extendHandler != null) {
                if (EnvironmentInfo.r()) {
                    extendHandler.sendEmptyMessage(200, 8000L);
                } else {
                    extendHandler.sendEmptyMessage(200, PreConnectManager.CONNECT_INTERNAL);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        this.ai.a("wearWeatherLocation");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        DeviceCapability e = kjn.e();
        if (e == null || !e.isWeatherPush()) {
            LogUtil.h("HwWeatherManager", "processLocationForQuery this device not support weather push");
            return;
        }
        double latitude = this.s.getLatitude();
        double longitude = this.s.getLongitude();
        if (jbo.a()) {
            latitude = jbo.b();
            longitude = jbo.d();
            LogUtil.a("HwWeatherManager", "processLocationForQuery is developer mode, latitudeDev:", Double.valueOf(latitude), " longitudeDev:", Double.valueOf(longitude));
        }
        if (!Utils.o()) {
            BigDecimal a2 = kjn.a(latitude);
            if (a2 != null) {
                latitude = a2.setScale(3, 4).doubleValue();
            }
            BigDecimal a3 = kjn.a(longitude);
            if (a3 != null) {
                longitude = a3.setScale(3, 4).doubleValue();
            }
        } else {
            BigDecimal a4 = kjn.a(latitude);
            if (a4 != null) {
                latitude = a4.setScale(2, 4).doubleValue();
            }
            BigDecimal a5 = kjn.a(longitude);
            if (a5 != null) {
                longitude = a5.setScale(2, 4).doubleValue();
            }
        }
        double d = latitude;
        double d2 = longitude;
        boolean z = this.u;
        if (z) {
            this.ag = 0;
            this.ad = 0;
        }
        boolean z2 = (z || this.p) ? false : true;
        LogUtil.a("HwWeatherManager", "processLocationForQuery isCheckPushCondition:", Boolean.valueOf(z2));
        c(z2, d, d2);
    }

    private void c(final boolean z, final double d, final double d2) {
        if (this.ae == null) {
            this.ae = ThreadPoolManager.a(2, 4);
        }
        this.ae.d("hagWeather", new Runnable() { // from class: kjl.10
            @Override // java.lang.Runnable
            public void run() {
                if (!z || kjl.this.b(d, d2)) {
                    kjl.this.u = false;
                    if (Locale.getDefault() != null) {
                        try {
                            List<Address> fromLocation = new Geocoder(kjl.this.n, Locale.getDefault()).getFromLocation(d, d2, 1);
                            if (fromLocation != null && !fromLocation.isEmpty()) {
                                Address address = fromLocation.get(0);
                                if (address.getSubLocality() != null) {
                                    String subLocality = address.getSubLocality();
                                    LogUtil.a("HwWeatherManager", "asyncSendWeatherData SubLocality:", subLocality);
                                    kjl.this.y = subLocality;
                                }
                            } else {
                                LogUtil.h("HwWeatherManager", "asyncSendWeatherData location result is null");
                            }
                        } catch (Exception e) {
                            LogUtil.b("HwWeatherManager", "asyncSendWeatherData Exception ", ExceptionUtils.d(e));
                        }
                    }
                    kjl.this.a(d, d2);
                    return;
                }
                LogUtil.h("HwWeatherManager", "asyncSendWeatherData don't need push weather");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(double d, double d2) {
        boolean c2 = kjf.c();
        LogUtil.a("HwWeatherManager", "getWeather isSupportTideCapability: ", Boolean.valueOf(c2));
        jbz c3 = jbr.e().c(c2, d, d2);
        if (c3 != null) {
            boolean z = (c3.k() == -99 || c3.n() == -99 || c3.s() == -99) ? false : true;
            Object[] objArr = new Object[2];
            objArr[0] = "getWeather getWeatherInfo is ";
            objArr[1] = z ? "success" : ParamConstants.CallbackMethod.ON_FAIL;
            jrb.d("HwWeatherManager", objArr);
            if (c3.c() != null && c3.c().size() > 0) {
                String value = AnalyticsValue.HAG_WEATHER_PUSH.value();
                ixx.d().d(BaseApplication.getContext(), value, c3.c(), 0);
                b(c2, c3, value);
            }
            if (z) {
                b(c3);
                if (kjn.i()) {
                    kjn.a(c3);
                }
                this.ag = 0;
                if (kjn.d(c3)) {
                    this.ad++;
                    ExtendHandler extendHandler = this.ah;
                    if (extendHandler != null) {
                        extendHandler.sendEmptyMessage(401, 120000L);
                        return;
                    }
                    return;
                }
                this.ad = 0;
                return;
            }
        }
        c(c3);
    }

    private void b(boolean z, jbz jbzVar, String str) {
        if (!z || jbzVar.b() == null) {
            return;
        }
        kjf.e(jbzVar.b().getHagReportBiMap(), str);
    }

    private void c(jbz jbzVar) {
        if (kjn.e(jbzVar)) {
            kjm.c(0, 0);
            return;
        }
        if (this.ag == 0) {
            if (kjn.b(jbzVar)) {
                kjm.c(0, 0);
            } else {
                kjm.c(0, -1);
            }
        }
        this.ag++;
        ExtendHandler extendHandler = this.ah;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(400, 120000L);
        }
    }

    private void b(jbz jbzVar) {
        if (jbzVar == null) {
            LogUtil.h("HwWeatherManager", "setWeatherData data is null");
            return;
        }
        DeviceCapability e = kjn.e();
        if (e == null || !e.isWeatherPush()) {
            LogUtil.h("HwWeatherManager", "setWeatherData not support weather push");
        } else if (this.v) {
            this.o = jbzVar;
            kjm.c();
        }
    }

    private void a(byte b2, DeviceInfo deviceInfo) {
        DeviceCapability e = kjn.e(deviceInfo);
        if (e == null) {
            LogUtil.h("HwWeatherManager", "DeviceCapability is null.");
            return;
        }
        this.h = b2;
        if (!e.isAtmosphereSupportExpand()) {
            if (kjk.c(deviceInfo)) {
                kjk.a(deviceInfo);
                return;
            } else {
                LogUtil.a("HwWeatherManager", "atmosphere support, push weather now");
                e((kjg) null, deviceInfo);
                return;
            }
        }
        LogUtil.a("HwWeatherManager", "atmosphere support expand,later send cmd.");
        kjm.e();
    }

    private void e(byte[] bArr, DeviceInfo deviceInfo) {
        if (this.h == 0) {
            LogUtil.h("HwWeatherManager", "handleDeviceSupportDataExpand atmosphereSupport get fail");
            return;
        }
        boolean c2 = kjk.c(deviceInfo);
        LogUtil.a("HwWeatherManager", "handleDeviceSupportDataExpand isSupportFutureWeatherDetailCapability:", Boolean.valueOf(c2));
        this.af = bArr;
        if (c2) {
            kjk.a(deviceInfo);
        } else {
            e((kjg) null, deviceInfo);
        }
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        e(kjn.a(bArr), deviceInfo);
    }

    private void e(kjg kjgVar, DeviceInfo deviceInfo) {
        String str;
        kji kjiVar;
        if (this.o == null || this.h == 0) {
            LogUtil.h("HwWeatherManager", "pushWeather mDataWeather is null or mAtmosphereSupport is 0");
            return;
        }
        DeviceCapability e = kjn.e(deviceInfo);
        if (e != null && !e.isAtmosphereSupportExpand()) {
            kjiVar = kjn.b(this.h);
            str = kjm.e(this.o, kjiVar);
        } else {
            byte[] bArr = this.af;
            if (bArr == null || bArr.length < 6) {
                LogUtil.h("HwWeatherManager", "pushWeather mSupportWeatherFutureByte is invalid");
                return;
            }
            kji c2 = kjn.c(bArr[4], bArr[5]);
            kji b2 = kjn.b(this.h);
            b2.a(c2.e());
            b2.c(c2.o());
            String e2 = kjm.e(this.o, b2);
            String b3 = kjm.b(this.o, c2);
            LogUtil.a("HwWeatherManager", "pushWeather COMMAND_ID_ATMOSPHERE_SUPPORT_EXPAND, tlv: ", e2, b3);
            str = e2 + b3;
            kjiVar = b2;
        }
        LogUtil.a("HwWeatherManager", "pushWeather command:", str);
        boolean k = kjn.k();
        kjiVar.e(k);
        LogUtil.a("HwWeatherManager", "pushWeather isSupportAlert :", Boolean.valueOf(k));
        if (!TextUtils.isEmpty(str)) {
            kjm.d(str, this.o, k);
        }
        kjk.d(this.o, kjgVar, kjiVar, deviceInfo);
        kjf.e(this.o, deviceInfo);
        v();
    }

    private void v() {
        if (kjn.l()) {
            if (kjn.f()) {
                if (PermissionUtil.c() && kjn.d()) {
                    kjm.c(1, -1);
                    return;
                }
                return;
            }
            kjm.c(1, -1);
        }
    }

    private void b(final byte[] bArr) {
        byte b2 = bArr[4];
        if (b2 == 1) {
            LogUtil.a("HwWeatherManager", "device request weather, push weather need check.");
        } else if (b2 == 2) {
            LogUtil.a("HwWeatherManager", "device request weather, push weather now");
            this.u = true;
        } else {
            LogUtil.h("HwWeatherManager", "handleDeviceRequestWeatherInfo unknown");
            return;
        }
        if (bArr.length == 5 && kjn.i()) {
            LogUtil.a("HwWeatherManager", "need to check the cache validity");
            jbz a2 = kjn.a();
            if (a2 == null) {
                LogUtil.a("HwWeatherManager", "invalid cache.");
            } else {
                jrb.d("HwWeatherManager", "cache valid, push the cache weather data.");
                b(a2);
                return;
            }
        }
        kjn.b(bArr);
        ThreadPoolManager.d().execute(new Runnable() { // from class: kjl.8
            @Override // java.lang.Runnable
            public void run() {
                if (EnvironmentInfo.r()) {
                    LogUtil.a("HwWeatherManager", "PowerKitManager weather request");
                    byte[] bArr2 = bArr;
                    if (bArr2.length > 7 && bArr2[7] == 1) {
                        LogUtil.a("HwWeatherManager", "handleDeviceRequestWeatherInfo auto request powerKit.");
                        PowerKitManager.e().d("HwWeatherManager", 65535, PreConnectManager.CONNECT_INTERNAL, "user-weatherRequest");
                    } else {
                        LogUtil.a("HwWeatherManager", "handleDeviceRequestWeatherInfo user request powerKit.");
                        PowerKitManager.e().d("HwWeatherManager", 65535, PreConnectManager.CONNECT_INTERNAL, "user-weatherRequest");
                    }
                    LogUtil.a("HwWeatherManager", "PowerKitManager weather request end");
                }
            }
        });
        jrb.b("HwWeatherManager", 15, 4);
        kjm.e(15, 4, 100000);
        LogUtil.a("HwWeatherManager", "handleDataFromDevice() mIsWeatherSwitchOn:", Boolean.valueOf(this.v));
        if (!this.v) {
            kjm.c(2, -1);
        } else {
            n();
        }
    }

    private void e(byte[] bArr) {
        if (bArr == null || bArr.length < 5) {
            LogUtil.h("HwWeatherManager", "dataContents is null or length < 5");
            return;
        }
        if (bArr[0] != 24) {
            return;
        }
        LogUtil.a("HwWeatherManager", "device request location, dataContents[1]:", Byte.valueOf(bArr[1]));
        if (bArr[1] != 6) {
            return;
        }
        if (!kjn.f()) {
            LogUtil.h("HwWeatherManager", "device request location, GpsPermissions is false");
            kjm.e(24, 6, 100001);
        } else {
            boolean z = bArr[4] == 2;
            LogUtil.a("HwWeatherManager", "device request location, isUserActive:", Boolean.valueOf(z));
            e(z);
        }
    }

    private void e(boolean z) {
        jrb.b("HwWeatherManager", 24, 6);
        if (z && !kjn.d()) {
            d(3);
            return;
        }
        Location bOJ_ = bOJ_();
        if (bOJ_ != null) {
            this.s = bOJ_;
            LogUtil.a("HwWeatherManager", "device request location, location is valid, send gps to device");
            kjm.d(true, kjn.bOM_(bOJ_));
        } else {
            LogUtil.h("HwWeatherManager", "device request location, phone cache is null, isBackgroundAndNoPermission: ", Boolean.valueOf(kjn.d()));
            if (kjn.d()) {
                kjm.e(24, 6, 100001);
            } else {
                d(3);
            }
        }
    }

    public void d() {
        try {
            this.n.unregisterReceiver(this.f);
            this.n.unregisterReceiver(this.aa);
            this.n.unregisterReceiver(this.m);
            if (EnvironmentInfo.r()) {
                this.n.unregisterReceiver(this.e);
            }
        } catch (Exception unused) {
            LogUtil.b("HwWeatherManager", "Exception: unregisterReceiver");
        }
        u();
        j();
        ThreadPoolManager threadPoolManager = this.ae;
        if (threadPoolManager != null) {
            threadPoolManager.shutdown();
        }
        ExtendHandler extendHandler = this.ah;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.ah = null;
        }
        LogUtil.a("HwWeatherManager", "onDestroy");
    }

    private static void j() {
        synchronized (f14415a) {
            b = null;
        }
    }

    private void a(byte[] bArr) {
        String d = cvx.d(bArr);
        if (d != null && d.length() > 4) {
            try {
                for (cwd cwdVar : new cwl().a(d.substring(4)).e()) {
                    if (CommonUtil.w(cwdVar.e()) != 1) {
                        LogUtil.h("HwWeatherManager", "deviceRequestAtmosphere other type: ", cwdVar.e());
                    } else {
                        LogUtil.a("HwWeatherManager", "deviceRequestAtmosphere value: ", cwdVar.c());
                        b(cwdVar.c());
                    }
                }
                return;
            } catch (cwg unused) {
                LogUtil.b("HwWeatherManager", "deviceRequestAtmosphere TlvException");
                s();
                return;
            }
        }
        LogUtil.b("HwWeatherManager", "deviceRequestAtmosphere get error data");
        s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        p();
        StringBuilder sb = new StringBuilder(0);
        sb.append(cvx.e(127));
        sb.append(cvx.e(4));
        sb.append(cvx.b(100001L));
        kjm.e(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        this.i = null;
    }

    private void b(String str) {
        this.i = str;
        if (TextUtils.isEmpty(str)) {
            s();
            return;
        }
        jca c2 = c(str);
        if (c2 != null) {
            e(c2);
        } else {
            f();
        }
    }

    private void f() {
        boolean f = kjn.f();
        jrb.d("HwWeatherManager", "getAtmosphereFromNet() isHasGpsSwitchAndPermissions:", Boolean.valueOf(f));
        if (!f) {
            s();
            return;
        }
        boolean aa = CommonUtil.aa(this.n);
        LogUtil.a("HwWeatherManager", "getAtmosphereFromNet atmosphere, netWork state: ", Boolean.valueOf(aa));
        if (!aa) {
            s();
            return;
        }
        Location bOJ_ = bOJ_();
        if (bOJ_ != null) {
            this.s = bOJ_;
            LogUtil.a("HwWeatherManager", "getAtmosphereFromNet atmosphere, location is valid");
            l();
        } else {
            boolean d = kjn.d();
            jrb.d("HwWeatherManager", "getAtmosphereFromNet atmosphere, phone cache is null, isBackgroundAndNoPermission: ", Boolean.valueOf(d));
            if (d) {
                s();
            } else {
                d(1);
            }
        }
    }

    private jca c(String str) {
        int i;
        int i2 = 0;
        try {
            i = Integer.parseInt(str, 16);
        } catch (NumberFormatException unused) {
            LogUtil.b("HwWeatherManager", "getAtmosphereFromServiceProvider NumberFormatException");
            i = 0;
        }
        kjh c2 = kjn.c(i);
        LogUtil.a("HwWeatherManager", "getAtmosphereFromServiceProvider atmosphereSupport: ", c2.toString());
        if (!c2.e()) {
            return null;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(15), "get_atmosphere_auto_press");
        LogUtil.a("HwWeatherManager", "getAtmosphereFromServiceProvider last info is: ", b2);
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        String[] split = b2.split("_");
        if (split.length != 3) {
            return null;
        }
        long j = 0;
        double d = 0.0d;
        try {
            j = Long.parseLong(split[0]);
            d = Double.parseDouble(split[1]);
            i2 = Integer.parseInt(split[2]);
        } catch (NumberFormatException unused2) {
            LogUtil.b("HwWeatherManager", "getAtmosphereFromServiceProvider exception split is not number");
        }
        if (!kjn.a(10, j)) {
            jca jcaVar = new jca();
            jcaVar.d(d);
            jcaVar.b(i2);
            LogUtil.a("HwWeatherManager", jcaVar.toString());
            return jcaVar;
        }
        LogUtil.h("HwWeatherManager", "getAtmosphereFromServiceProvider gap time is rather than 10 minutes");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(jca jcaVar) {
        if (TextUtils.isEmpty(this.i)) {
            StringBuilder sb = new StringBuilder(0);
            sb.append(a(jcaVar));
            kjm.e(sb.toString());
        } else {
            try {
                kjm.e(d(jcaVar, kjn.c(Integer.parseInt(this.i, 16))));
            } catch (NumberFormatException unused) {
                LogUtil.b("HwWeatherManager", "pushAtmosphere NumberFormatException");
            }
        }
    }

    private String d(jca jcaVar, kjh kjhVar) {
        if (jcaVar == null) {
            LogUtil.h("HwWeatherManager", "AtmosphereInfo is null");
            return null;
        }
        StringBuilder sb = new StringBuilder(0);
        if (kjhVar.e()) {
            sb.append(a(jcaVar));
        }
        return sb.toString();
    }

    private String a(jca jcaVar) {
        int[] c2 = kjn.c(jcaVar.b());
        StringBuilder sb = new StringBuilder(0);
        sb.append(cvx.e(2));
        sb.append(cvx.e(2));
        sb.append(cvx.a(c2[0]));
        StringBuilder sb2 = new StringBuilder(0);
        sb2.append(cvx.e(3));
        sb2.append(cvx.e(1));
        sb2.append(cvx.e(jcaVar.d()));
        StringBuilder sb3 = new StringBuilder(0);
        sb3.append(cvx.e(4));
        sb3.append(cvx.e(2));
        sb3.append(cvx.a(c2[1]));
        LogUtil.a("HwWeatherManager", "pressureTLV : ", sb.toString(), "pressureUnitTLV : ", sb2.toString(), "pressureDecimalTLV : ", sb3.toString());
        return sb.toString() + sb2.toString() + sb3.toString();
    }

    private void l() {
        LogUtil.a("HwWeatherManager", "pushAtmosphereToDevice() ENTER");
        if (this.ae == null) {
            this.ae = ThreadPoolManager.a(2, 4);
        }
        this.ae.d("hagWeather", new Runnable() { // from class: kjl.1
            @Override // java.lang.Runnable
            public void run() {
                jbs.a(kjl.this.n).a(CommonUtil.c(kjl.this.s.getLatitude(), 1), CommonUtil.c(kjl.this.s.getLongitude(), 1), new ICloudOperationResult<jca>() { // from class: kjl.1.4
                    @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void operationResult(jca jcaVar, String str, boolean z) {
                        if (jcaVar != null && jcaVar.e() != null && !jcaVar.e().isEmpty()) {
                            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HAG_WEATHER_PUSH.value(), jcaVar.e(), 0);
                        }
                        if (z && jcaVar != null) {
                            kjl.this.e(jcaVar);
                            kjl.this.b(jcaVar);
                            kjl.this.p();
                        } else {
                            LogUtil.h("HwWeatherManager", "pushAtmosphereToDevice() AtmosphereInfo ERROR!");
                            kjl.this.s();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(jca jcaVar) {
        LogUtil.a("HwWeatherManager", "enter saveInfoOnServiceProvider, mAtmosphereSupportValue is: ", this.i);
        if (!TextUtils.isEmpty(this.i)) {
            try {
                if (kjn.c(Integer.parseInt(this.i, 16)).e()) {
                    kjn.a(jcaVar);
                    return;
                }
                return;
            } catch (NumberFormatException unused) {
                LogUtil.b("HwWeatherManager", "valve is not number");
                return;
            }
        }
        kjn.a(jcaVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bOI_(Location location) {
        Object[] objArr = new Object[2];
        objArr[0] = "handleWhenGetLocationSuccess hwLocation: ";
        objArr[1] = Boolean.valueOf(location != null);
        LogUtil.a("HwWeatherManager", objArr);
        if (location == null) {
            return;
        }
        this.s = location;
        l();
    }

    public void e() {
        LogUtil.a("HwWeatherManager", "pushLocalPressure enter");
        f();
    }
}
