package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.hwsmartinteractmgr.smarter.AbnormalSmarter;
import com.huawei.hwsmartinteractmgr.smarter.BloodPressureSmarter;
import com.huawei.hwsmartinteractmgr.smarter.HealthcareSmarter;
import com.huawei.hwsmartinteractmgr.smarter.MsgCenterSmarter;
import com.huawei.hwsmartinteractmgr.smarter.ParticipatedActivitySmarter;
import com.huawei.hwsmartinteractmgr.userlabel.LabelObserver;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class kvt {
    private static final Object b = new Object();
    private static volatile kvt d;

    /* renamed from: a, reason: collision with root package name */
    private BloodPressureSmarter f14636a;
    private kwc ad;
    private AbnormalSmarter c;
    private Context e;
    private kwf f;
    private HealthcareSmarter h;
    private long m;
    private long n;
    private long o;
    private ParticipatedActivitySmarter p;
    private List<Integer> q;
    private MsgCenterSmarter r;
    private List<Integer> s;
    private List<Integer> t;
    private List<Integer> u;
    private List<Integer> v;
    private kvx w;
    private List<Integer> x;
    private MessageObserver l = new MessageObserver() { // from class: kvt.2
        @Override // com.huawei.pluginmessagecenter.service.MessageObserver
        public void onChange(int i, MessageChangeEvent messageChangeEvent) {
            LogUtil.a("SMART_HwSmartInteractManager", "MessageObserver onChange start flag = ", Integer.valueOf(i));
            if (messageChangeEvent == null) {
                return;
            }
            List<String> modifyMessageObjectIds = messageChangeEvent.getModifyMessageObjectIds();
            List<String> removeMessageObjectIds = messageChangeEvent.getRemoveMessageObjectIds();
            boolean z = (modifyMessageObjectIds == null || modifyMessageObjectIds.isEmpty()) ? false : true;
            boolean z2 = (removeMessageObjectIds == null || removeMessageObjectIds.isEmpty()) ? false : true;
            if (i == 0 && z) {
                LogUtil.a("SMART_HwSmartInteractManager", "Message Change");
                kvt.this.f.e();
            } else if (i == 0 && z2) {
                LogUtil.a("SMART_HwSmartInteractManager", "Message Change");
                kvt.this.f.e();
            } else {
                LogUtil.b("SMART_HwSmartInteractManager", "Message Change is error");
            }
        }
    };
    private LabelObserver j = new LabelObserver() { // from class: kvt.9
        @Override // com.huawei.hwsmartinteractmgr.userlabel.LabelObserver
        public void onChange(Map<Integer, List<String>> map) {
            kvt.this.h.onChange(map);
            kvt.this.ad.onChange(map);
            kvt.this.f.b();
        }
    };
    private HiSubscribeListener i = new HiSubscribeListener() { // from class: kvt.11
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            LogUtil.a("SMART_HwSmartInteractManager", "subscribeWeightGoalInfo onResult");
            kvt.this.t = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            LogUtil.a("SMART_HwSmartInteractManager", "subscribeWeightGoalInfo onChange");
            kwn.a(kvt.this.e, new IBaseResponseCallback() { // from class: kvt.11.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 == 0) {
                        kvt.this.ad.d(kvt.this.e, new IBaseResponseCallback() { // from class: kvt.11.3.3
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i3, Object obj2) {
                                if (obj2 instanceof List) {
                                    List list = (List) obj2;
                                    if (list.isEmpty()) {
                                        return;
                                    }
                                    double d2 = ((HiHealthData) list.get(0)).getDouble("bodyWeight");
                                    String metaData = ((HiHealthData) list.get(0)).getMetaData();
                                    if (TextUtils.isEmpty(metaData)) {
                                        LogUtil.a("SMART_HwSmartInteractManager", "This is a mainUser.");
                                        return;
                                    }
                                    if (Constants.NULL.equalsIgnoreCase(metaData) || "0".equals(metaData)) {
                                        LogUtil.a("SMART_HwSmartInteractManager", "This is a mainUser.");
                                        return;
                                    }
                                    cfi singleUserById = MultiUsersManager.INSTANCE.getSingleUserById(metaData);
                                    if (singleUserById == null) {
                                        return;
                                    }
                                    singleUserById.b((float) d2);
                                }
                            }
                        });
                    }
                }
            });
            if (i == 101) {
                kvt.this.ad.b();
            }
        }
    };
    private Handler g = new Handler(Looper.getMainLooper());
    private MessageCenterApi k = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
    private kwg y = kwg.c();

    private kvt(Context context) {
        this.e = context.getApplicationContext();
        this.ad = kwc.e(this.e);
        this.f14636a = new BloodPressureSmarter(this.e);
        this.r = new MsgCenterSmarter(this.e);
        this.w = kvx.c(this.e);
        this.c = new AbnormalSmarter(this.e);
        this.f = kwf.d(this.e);
        this.h = new HealthcareSmarter(this.e);
        this.p = new ParticipatedActivitySmarter(this.e);
        if (Utils.g()) {
            return;
        }
        o();
        g();
        n();
        m();
        p();
        k();
    }

    public static kvt e(Context context) {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new kvt(context);
                }
            }
        }
        return d;
    }

    public void b(final int i) {
        jdx.b(new Runnable() { // from class: kvt.15
            @Override // java.lang.Runnable
            public void run() {
                kvs.b(kvt.this.e).d(i);
            }
        });
    }

    public void a(final int i, final int i2, final IBaseResponseCallback iBaseResponseCallback) {
        jdx.b(new Runnable() { // from class: kvt.12
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList(5);
                SmartMsgDbObject d2 = kvw.c(kvt.this.e).d(kvs.b(kvt.this.e).c(LoginInit.getInstance(kvt.this.e).getAccountInfo(1011), i), i, i2);
                if (d2 != null) {
                    LogUtil.a("SMART_HwSmartInteractManager", "Smart_card_msg", Integer.valueOf(d2.getId()));
                    arrayList.add(d2);
                }
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, arrayList);
                }
            }
        });
    }

    public void a() {
        c(new IBaseResponseCallback() { // from class: kvt.14
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("SMART_HwSmartInteractManager", "errCode=", Integer.valueOf(i));
            }
        });
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SMART_HwSmartInteractManager", "startTimerCheck");
        if (iBaseResponseCallback == null) {
            LogUtil.a("SMART_HwSmartInteractManager", "startTimerCheck callback == null");
            return;
        }
        if (Utils.g()) {
            LogUtil.a("SMART_HwSmartInteractManager", "startTimerCheck, isNoCloudVersion, return");
            iBaseResponseCallback.d(100001, null);
        } else if (e()) {
            jdx.b(new Runnable() { // from class: kvt.13
                @Override // java.lang.Runnable
                public void run() {
                    int i;
                    try {
                        kvt.this.ad.d();
                        kvt.this.f14636a.d();
                        kwe.e().d();
                        kvt.this.c.d();
                        kvt.this.h.d();
                        kvt.this.f.d();
                        Context context = kvt.this.e;
                        String num = Integer.toString(20009);
                        long currentTimeMillis = System.currentTimeMillis();
                        SharedPreferenceManager.e(context, num, "last_timercheck_time", String.valueOf(currentTimeMillis), new StorageParams());
                        i = 0;
                    } catch (UnsupportedOperationException e) {
                        LogUtil.b("SMART_HwSmartInteractManager", "startTimerCheck error UnsupportedOperationException：", e.getMessage());
                        i = 100001;
                    }
                    iBaseResponseCallback.d(i, Integer.valueOf(i));
                }
            });
        } else {
            iBaseResponseCallback.d(100001, 100001);
            LogUtil.a("SMART_HwSmartInteractManager", "startTimingCheck, interval not enough");
        }
    }

    private boolean e() {
        String b2 = SharedPreferenceManager.b(this.e, Integer.toString(20009), "last_timercheck_time");
        if (!TextUtils.isEmpty(b2)) {
            try {
            } catch (NumberFormatException e) {
                LogUtil.b("SMART_HwSmartInteractManager", "checkInterval numberFormatException = ", e.getMessage());
            }
            if (Math.abs(System.currentTimeMillis() - Long.parseLong(b2)) <= AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL) {
                return false;
            }
        }
        return true;
    }

    public void d() {
        if (Utils.g() || CommonUtil.bu()) {
            LogUtil.b("SMART_HwSmartInteractManager", "init, isNoCloudVersion or storeDemo, return");
            return;
        }
        l();
        MessageObserver messageObserver = this.l;
        if (messageObserver != null) {
            this.k.registerMessageObserver(messageObserver);
        }
        this.r.b();
        h();
        b();
        a();
    }

    public void b() {
        jdx.b(new Runnable() { // from class: kvt.19
            @Override // java.lang.Runnable
            public void run() {
                kvt.this.h.b();
                kvt.this.p.b();
                kvt.this.r.a();
            }
        });
    }

    public void c() {
        if (Utils.g() || CommonUtil.bu()) {
            LogUtil.a("SMART_HwSmartInteractManager", "onDestroy, isNoCloudVersion, storeDemo, return");
            return;
        }
        MessageObserver messageObserver = this.l;
        if (messageObserver != null) {
            this.k.unregisterMessageObserver(messageObserver);
        }
        this.r.e();
        this.y.b(this.j);
        kwb.d().c();
        e(this.u);
        e(this.q);
        e(this.s);
        e(this.x);
        e(this.t);
        e(this.v);
    }

    private void e(List<Integer> list) {
        if (koq.c(list)) {
            HiHealthNativeApi.a(this.e).unSubscribeHiHealthData(list, new HiUnSubscribeListener() { // from class: kvt.18
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public void onResult(boolean z) {
                    LogUtil.a("SMART_HwSmartInteractManager", "onDestroy unSubscribeHiHealthData isSuccess=", Boolean.valueOf(z));
                }
            });
        }
    }

    private void o() {
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(HiSubscribeType.c, new HiSubscribeListener() { // from class: kvt.1
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                kvt.this.u = list;
                LogUtil.a("SMART_HwSmartInteractManager", "subscribeWeightData, onResult");
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                LogUtil.a("SMART_HwSmartInteractManager", "onChange, type=", Integer.valueOf(i));
                if (i == HiSubscribeType.c) {
                    if (System.currentTimeMillis() - kvt.this.m > 1000) {
                        LogUtil.a("SMART_HwSmartInteractManager", "onChange Weight execute");
                        kvt.this.g.postDelayed(new Runnable() { // from class: kvt.1.5
                            @Override // java.lang.Runnable
                            public void run() {
                                kvt.this.j();
                            }
                        }, 1000L);
                    }
                    kvt.this.m = System.currentTimeMillis();
                }
            }
        });
    }

    private void g() {
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(HiSubscribeType.f4119a, new HiSubscribeListener() { // from class: kvt.3
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                kvt.this.q = list;
                LogUtil.a("SMART_HwSmartInteractManager", "subscribeBloodPressData, onResult");
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                LogUtil.a("SMART_HwSmartInteractManager", "subscribeBloodPressData onChange, type=", Integer.valueOf(i));
                if (i == HiSubscribeType.f4119a) {
                    if (System.currentTimeMillis() - kvt.this.o > 1000) {
                        LogUtil.a("SMART_HwSmartInteractManager", "onChange BloodPressure execute");
                        kvt.this.g.postDelayed(new Runnable() { // from class: kvt.3.3
                            @Override // java.lang.Runnable
                            public void run() {
                                kvt.this.f();
                            }
                        }, 1000L);
                    }
                    kvt.this.o = System.currentTimeMillis();
                }
            }
        });
    }

    private void n() {
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(10, new HiSubscribeListener() { // from class: kvt.4
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                kvt.this.s = list;
                LogUtil.a("SMART_HwSmartInteractManager", "subscribeBloodSugarData, onResult");
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                LogUtil.a("SMART_HwSmartInteractManager", "subscribeBloodSugarData onChange");
                if (i == 10) {
                    if (System.currentTimeMillis() - kvt.this.n > 1000) {
                        LogUtil.a("SMART_HwSmartInteractManager", "onChange BloodSugar execute");
                        kvt.this.g.postDelayed(new Runnable() { // from class: kvt.4.4
                            @Override // java.lang.Runnable
                            public void run() {
                                kvt.this.f.a();
                                kvt.this.i();
                            }
                        }, 1000L);
                    }
                    kvt.this.n = System.currentTimeMillis();
                }
            }
        });
    }

    private void p() {
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(101, this.i);
    }

    private void m() {
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(102, new HiSubscribeListener() { // from class: kvt.5
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                LogUtil.a("SMART_HwSmartInteractManager", "subscribeUserPreference, onResult");
                kvt.this.v = list;
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                LogUtil.a("SMART_HwSmartInteractManager", "subscribeUserPreference onchange");
                if (i == 102) {
                    jdx.b(new Runnable() { // from class: kvt.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            kvt.this.w.c();
                            kvt.this.f.h();
                            kvt.this.ad.b();
                            kvt.this.ad.e();
                            kvt.this.ad.b(true);
                        }
                    });
                }
            }
        });
    }

    private void k() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(2);
        arrayList.add(3);
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(arrayList, new HiSubscribeListener() { // from class: kvt.8
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                LogUtil.a("SMART_HwSmartInteractManager", "subscribeSleepData, onResult");
                kvt.this.x = list;
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                if (i == 2 || i == 3) {
                    kvt.this.f.f();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("SMART_HwSmartInteractManager", "doWeightChange");
        this.f.g();
        jdx.b(new Runnable() { // from class: kvt.10
            @Override // java.lang.Runnable
            public void run() {
                kvt.this.ad.e(kvt.this.e, new IBaseResponseCallback() { // from class: kvt.10.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (obj instanceof List) {
                            List list = (List) obj;
                            if (!list.isEmpty()) {
                                double d2 = ((HiHealthData) list.get(0)).getDouble("bodyWeight");
                                long startTime = ((HiHealthData) list.get(0)).getStartTime();
                                LogUtil.a("SMART_HwSmartInteractManager", "doWeightChange startTime = ", Long.valueOf(startTime));
                                kvt.this.ad.d(d2, startTime, false);
                            }
                            kvt.this.ad.b();
                            kvt.this.ad.e();
                            kvt.this.ad.a();
                            kvt.this.ad.b(true);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("SMART_HwSmartInteractManager", "doBloodPressChange");
        this.f.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("SMART_HwSmartInteractManager", "doBloodPressChange");
        jdx.b(new Runnable() { // from class: kvt.7
            @Override // java.lang.Runnable
            public void run() {
                kwe.e().b();
            }
        });
    }

    public void c(Context context, double d2) {
        this.ad.c(context, d2);
    }

    private void l() {
        this.y.d(this.j);
    }

    private void h() {
        jdx.b(new Runnable() { // from class: kvt.6
            @Override // java.lang.Runnable
            public void run() {
                kvt.this.y.d();
            }
        });
    }
}
