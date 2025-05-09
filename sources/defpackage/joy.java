package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/* loaded from: classes5.dex */
public class joy {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13998a = new Object();
    private static joy c;
    private boolean d = false;
    private jqi b = jqi.a();

    private joy() {
    }

    public static joy d() {
        joy joyVar;
        synchronized (f13998a) {
            if (c == null) {
                c = new joy();
            }
            joyVar = c;
        }
        return joyVar;
    }

    public void b(int i, String str) {
        DeviceInfo b = jpt.b(str, "HwHeartRateHelper");
        if ("M0A7".equals(b.getHiLinkDeviceId()) || "0A8".equals(b.getHiLinkDeviceId())) {
            LogUtil.a("HwHeartRateHelper", "deviceInfo is molly, send intelligent mode");
            c(i);
        } else if ((i & 2) == 2) {
            this.b.setSwitchSetting("heart_rate_mode", Integer.toString(0), null);
            this.b.setSwitchSetting("continue_heart_rate", "1", null);
            LogUtil.a("HwHeartRateHelper", "send continue heart rate ", 1);
        } else if ((i & 8) == 8) {
            c(i);
        }
    }

    private void c(int i) {
        if (jhg.c(BaseApplication.getContext()).d()) {
            LogUtil.a("HwHeartRateHelper", "send cycle heart rate isFamilyMode result is:", Integer.valueOf(i));
            jhg.c(BaseApplication.getContext()).d(CommonUtil.m(BaseApplication.getContext(), "1"));
        } else {
            this.b.setSwitchSetting("heart_rate_mode", Integer.toString(1), null);
            this.b.setSwitchSetting("heart_rate_button", "1", null);
            this.b.setSwitchSetting("continue_heart_rate", "1", null);
            LogUtil.a("HwHeartRateHelper", "send cycle heart rate ", 1);
        }
    }

    public void d(int i) {
        if ((i & 2) == 2) {
            jho.b(1);
        } else if ((i & 8) == 8) {
            jho.a(1);
        }
        e(i);
    }

    public void a(int i) {
        if ((i & 1) == 1) {
            jho.e(1);
        }
    }

    private void e(final int i) {
        c(new IBaseResponseCallback() { // from class: joy.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 != -1) {
                    joy.this.j();
                    return;
                }
                int i3 = i;
                int i4 = ((i3 & 2) == 2 || (i3 & 8) == 8) ? 1 : 0;
                DeviceCapability d = cvs.d();
                Semaphore semaphore = new Semaphore(1);
                try {
                    if (d.isSupportHeartRateRaiseAlarm()) {
                        semaphore.acquire();
                        joy.this.e(semaphore, 2, i4);
                    }
                    if (d.isSupportHeartRateDownAlarm()) {
                        semaphore.acquire();
                        joy.this.a(semaphore, 2, i4);
                    }
                } catch (InterruptedException unused) {
                    LogUtil.b("HwHeartRateHelper", "reportHeartRateRemindHandle InterruptedException");
                }
            }
        });
        Intent intent = new Intent();
        intent.setAction("com.huawei.bone.action.CONTINUE_HEART_RATE_SWITCH");
        intent.putExtra("continue_heart_rate", true);
        BroadcastManagerUtil.bFI_(BaseApplication.getContext(), intent);
    }

    private void c(final IBaseResponseCallback iBaseResponseCallback) {
        if (jhg.c(BaseApplication.getContext()).d()) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), "104", "custom.heart_rate_raise_remind");
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "105", "custom.heart_rate_down_remind");
            if (b == null || b2 == null) {
                ReleaseLogUtil.e("DEVMGR_HwHeartRateHelper", "neverDefaultOpenHeartRateRemind raiseValue or downValue is null");
                iBaseResponseCallback.d(-1, null);
                return;
            } else {
                iBaseResponseCallback.d(1, null);
                return;
            }
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add("custom.heart_rate_raise_remind");
        arrayList.add("custom.heart_rate_down_remind");
        this.b.getSwitchSetting(arrayList, new IBaseResponseCallback() { // from class: joy.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj == null) {
                    ReleaseLogUtil.e("DEVMGR_HwHeartRateHelper", "neverDefaultOpenHeartRateRemind objectData is null");
                    iBaseResponseCallback.d(-1, null);
                    return;
                }
                List list = (List) obj;
                String a2 = joy.a((List<HiUserPreference>) list, "custom.heart_rate_raise_remind");
                String a3 = joy.a((List<HiUserPreference>) list, "custom.heart_rate_down_remind");
                if (a2 == null || a3 == null) {
                    ReleaseLogUtil.e("DEVMGR_HwHeartRateHelper", "neverDefaultOpenHeartRateRemind raiseNumber and downNumber is null");
                    iBaseResponseCallback.d(-1, null);
                } else {
                    iBaseResponseCallback.d(1, null);
                }
            }
        });
    }

    public static String a(List<HiUserPreference> list, String str) {
        LogUtil.a("HwHeartRateHelper", "getSettingItem enter");
        if (list == null) {
            LogUtil.h("HwHeartRateHelper", "getSettingItem userPreferenceList is null");
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            HiUserPreference hiUserPreference = list.get(i);
            if (hiUserPreference == null) {
                LogUtil.h("HwHeartRateHelper", "getSettingItem userPreference error");
            } else if (str.equals(hiUserPreference.getKey())) {
                LogUtil.a("HwHeartRateHelper", "userPreference is:", hiUserPreference.toString());
                return hiUserPreference.getValue();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (jhg.c(BaseApplication.getContext()).d()) {
            g();
            h();
        } else {
            m();
            n();
        }
    }

    private void h() {
        jhg c2 = jhg.c(BaseApplication.getContext());
        int m = CommonUtil.m(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), "105", "custom.heart_rate_down_remind"));
        if (m == 0) {
            c2.e(0, 0, false);
        } else {
            c2.e(1, m, false);
        }
    }

    private void g() {
        jhg c2 = jhg.c(BaseApplication.getContext());
        int m = CommonUtil.m(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), "104", "custom.heart_rate_raise_remind"));
        if (m == 0) {
            c2.a(0, 0, false);
        } else {
            c2.a(1, m, false);
        }
    }

    private void m() {
        this.b.getSwitchSetting("custom.heart_rate_raise_remind", new IBaseResponseCallback() { // from class: joy.8
            /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
            /* JADX WARN: Removed duplicated region for block: B:9:0x0031  */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void d(int r4, java.lang.Object r5) {
                /*
                    r3 = this;
                    java.lang.String r0 = "reportHeartRateRaiseRemind err_code = "
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
                    java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                    java.lang.String r1 = "HwHeartRateHelper"
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                    android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
                    jhg r0 = defpackage.jhg.c(r0)
                    r2 = 0
                    if (r4 != 0) goto L2e
                    boolean r4 = r5 instanceof java.lang.String
                    if (r4 == 0) goto L2e
                    java.lang.String r5 = (java.lang.String) r5
                    int r4 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.NumberFormatException -> L25
                    goto L2f
                L25:
                    java.lang.String r4 = "reportHeartRateRaiseRemind NumberFormatException"
                    java.lang.Object[] r4 = new java.lang.Object[]{r4}
                    com.huawei.hwlogsmodel.LogUtil.b(r1, r4)
                L2e:
                    r4 = r2
                L2f:
                    if (r4 != 0) goto L35
                    r0.a(r2, r2, r2)
                    goto L39
                L35:
                    r5 = 1
                    r0.a(r5, r4, r2)
                L39:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.joy.AnonymousClass8.d(int, java.lang.Object):void");
            }
        });
    }

    private void n() {
        this.b.getSwitchSetting("custom.heart_rate_down_remind", new IBaseResponseCallback() { // from class: joy.13
            /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
            /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void d(int r4, java.lang.Object r5) {
                /*
                    r3 = this;
                    java.lang.String r0 = "result watch auto open continue heart rate getHeartRateDownRemindNumber err_code = "
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
                    java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                    java.lang.String r1 = "HwHeartRateHelper"
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                    android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
                    jhg r0 = defpackage.jhg.c(r0)
                    r2 = 0
                    if (r4 != 0) goto L2f
                    boolean r4 = r5 instanceof java.lang.String
                    if (r4 == 0) goto L2f
                    java.lang.String r5 = (java.lang.String) r5
                    int r4 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.NumberFormatException -> L26
                    goto L30
                L26:
                    java.lang.String r4 = "reportHeartRateDownRemind NumberFormatException"
                    java.lang.Object[] r4 = new java.lang.Object[]{r4}
                    com.huawei.hwlogsmodel.LogUtil.b(r1, r4)
                L2f:
                    r4 = r2
                L30:
                    if (r4 != 0) goto L36
                    r0.e(r2, r2, r2)
                    goto L3a
                L36:
                    r5 = 1
                    r0.e(r5, r4, r2)
                L3a:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.joy.AnonymousClass13.d(int, java.lang.Object):void");
            }
        });
    }

    public void d(int i, Object obj, int i2) {
        LogUtil.a("HwHeartRateHelper", "HEART_RATE_MODE err_code = ", Integer.valueOf(i));
        int m = (i == 0 && (obj instanceof String)) ? CommonUtil.m(BaseApplication.getContext(), (String) obj) : 1;
        LogUtil.a("HwHeartRateHelper", "HEART_RATE_MODE status : " + m);
        if (m == 0) {
            LogUtil.a("HwHeartRateHelper", "init click continue click");
            jho.b(i2);
        } else if (m == 1) {
            LogUtil.a("HwHeartRateHelper", "init cycle continue click");
            jho.a(i2);
        } else {
            LogUtil.a("HwHeartRateHelper", "not get heart rate from cloud");
        }
    }

    public void d(final int[] iArr, final Semaphore semaphore) {
        LogUtil.a("HwHeartRateHelper", "reLink send continue cycle heart rate");
        this.b.getSwitchSetting("continue_heart_rate", new IBaseResponseCallback() { // from class: joy.14
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                final int i2;
                LogUtil.a("HwHeartRateHelper", "CONTINUE_MEASURE_HEART_RATE err_code = ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    try {
                        i2 = Integer.parseInt((String) obj);
                    } catch (NumberFormatException e) {
                        LogUtil.b("HwHeartRateHelper", "store format cannot to number", e.getMessage());
                    }
                    iArr[0] = i2;
                    LogUtil.a("HwHeartRateHelper", "CONTINUE_MEASURE_HEART_RATE status : " + i2);
                    joy.this.b.getSwitchSetting("heart_rate_mode", new IBaseResponseCallback() { // from class: joy.14.3
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i3, Object obj2) {
                            joy.this.d(i3, obj2, i2);
                        }
                    });
                    semaphore.release();
                }
                i2 = 0;
                iArr[0] = i2;
                LogUtil.a("HwHeartRateHelper", "CONTINUE_MEASURE_HEART_RATE status : " + i2);
                joy.this.b.getSwitchSetting("heart_rate_mode", new IBaseResponseCallback() { // from class: joy.14.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i3, Object obj2) {
                        joy.this.d(i3, obj2, i2);
                    }
                });
                semaphore.release();
            }
        });
    }

    public void e(final int[] iArr, final Semaphore semaphore) {
        LogUtil.a("HwHeartRateHelper", "getWearCommonSetting enter");
        this.b.getSwitchSetting("continue_heart_rate", new IBaseResponseCallback() { // from class: joy.15
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                int i2;
                LogUtil.a("HwHeartRateHelper", "getWearCommonSetting err_code = ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    try {
                        i2 = Integer.parseInt((String) obj);
                    } catch (NumberFormatException e) {
                        LogUtil.b("HwHeartRateHelper", e.getMessage());
                    }
                    iArr[0] = i2;
                    LogUtil.a("HwHeartRateHelper", "getWearCommonSetting status : " + i2);
                    jho.b(i2);
                    semaphore.release();
                }
                i2 = 0;
                iArr[0] = i2;
                LogUtil.a("HwHeartRateHelper", "getWearCommonSetting status : " + i2);
                jho.b(i2);
                semaphore.release();
            }
        });
    }

    public void b(final int[] iArr, final Semaphore semaphore) {
        this.b.getSwitchSetting("custom.heart_rate_raise_remind", new IBaseResponseCallback() { // from class: joy.11
            /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x0075  */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void d(int r8, java.lang.Object r9) {
                /*
                    r7 = this;
                    java.lang.String r0 = "supportHeartRateRaise errorCode = "
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
                    java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                    java.lang.String r1 = "HwHeartRateHelper"
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                    r0 = 1
                    r2 = 0
                    java.util.concurrent.Semaphore r3 = r2     // Catch: java.lang.InterruptedException -> L34
                    java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.InterruptedException -> L34
                    r5 = 3
                    boolean r3 = r3.tryAcquire(r5, r4)     // Catch: java.lang.InterruptedException -> L34
                    if (r3 == 0) goto L29
                    java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.InterruptedException -> L34
                    java.lang.String r4 = "semaphore tryAcquire()"
                    r3[r2] = r4     // Catch: java.lang.InterruptedException -> L34
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r3)     // Catch: java.lang.InterruptedException -> L34
                    goto L40
                L29:
                    java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.InterruptedException -> L34
                    java.lang.String r4 = "semaphore tryAcquire() fail"
                    r3[r2] = r4     // Catch: java.lang.InterruptedException -> L34
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r3)     // Catch: java.lang.InterruptedException -> L34
                    goto L40
                L34:
                    r3 = move-exception
                    java.lang.String r3 = r3.getMessage()
                    java.lang.Object[] r3 = new java.lang.Object[]{r3}
                    com.huawei.hwlogsmodel.LogUtil.b(r1, r3)
                L40:
                    android.content.Context r3 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
                    jhg r3 = defpackage.jhg.c(r3)
                    int[] r4 = r3
                    r4 = r4[r2]
                    if (r4 != r0) goto L8e
                    if (r8 != 0) goto L65
                    boolean r8 = r9 instanceof java.lang.String
                    if (r8 == 0) goto L65
                    java.lang.String r9 = (java.lang.String) r9
                    int r8 = java.lang.Integer.parseInt(r9)     // Catch: java.lang.NumberFormatException -> L5b
                    goto L66
                L5b:
                    java.lang.String r8 = "supportHeartRateRaise NumberFormatException"
                    java.lang.Object[] r8 = new java.lang.Object[]{r8}
                    com.huawei.hwlogsmodel.LogUtil.b(r1, r8)
                L65:
                    r8 = r2
                L66:
                    if (r8 != 0) goto L75
                    java.lang.String r8 = "close heart rate raise remind."
                    java.lang.Object[] r8 = new java.lang.Object[]{r8}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
                    r3.a(r2, r2, r2)
                    goto La6
                L75:
                    java.lang.StringBuilder r9 = new java.lang.StringBuilder
                    java.lang.String r4 = "open heart rate heart rate number is "
                    r9.<init>(r4)
                    r9.append(r8)
                    java.lang.String r9 = r9.toString()
                    java.lang.Object[] r9 = new java.lang.Object[]{r9}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r9)
                    r3.a(r0, r8, r2)
                    goto La6
                L8e:
                    if (r4 != 0) goto L9d
                    r3.a(r2, r2, r2)
                    java.lang.String r8 = "continueHeartRateEnable[0] == 0"
                    java.lang.Object[] r8 = new java.lang.Object[]{r8}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
                    goto La6
                L9d:
                    java.lang.String r8 = "continueHeartRateEnable[0] != 0 & != 1"
                    java.lang.Object[] r8 = new java.lang.Object[]{r8}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
                La6:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.joy.AnonymousClass11.d(int, java.lang.Object):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final Semaphore semaphore, final int i, final int i2) {
        if (jhg.c(BaseApplication.getContext()).d()) {
            b(i2, SharedPreferenceManager.b(BaseApplication.getContext(), "104", "custom.heart_rate_raise_remind"), i);
            semaphore.release();
        } else {
            this.b.getSwitchSetting("custom.heart_rate_raise_remind", new IBaseResponseCallback() { // from class: joy.12
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i3, Object obj) {
                    LogUtil.a("HwHeartRateHelper", "heartRateRiseDefaultOpen errorCode ", Integer.valueOf(i3));
                    joy.this.b(i2, obj, i);
                    semaphore.release();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Object obj, int i2) {
        jhg c2 = jhg.c(BaseApplication.getContext());
        int m = obj instanceof String ? CommonUtil.m(BaseApplication.getContext(), (String) obj) : 0;
        if (i == 1) {
            if (obj == null) {
                m = 120;
            }
            if (m == 0) {
                c2.a(0, 0, true);
                return;
            } else {
                c2.a(1, m, true);
                return;
            }
        }
        if (i2 == 1) {
            c2.a(0, 0, false);
        } else if (m == 0) {
            c2.a(0, 0, false);
        } else {
            c2.a(1, m, false);
        }
        LogUtil.a("HwHeartRateHelper", "heartRateRiseDefaultOpen heartRate is closed");
    }

    private void b(int i) {
        if ((i & 1) == 1) {
            LogUtil.a("HwHeartRateHelper", "set CoreSleep switch open");
            this.b.setSwitchSettingToLocal("core_sleep_Default", "0", PrebakedEffectId.RT_COIN_DROP);
            Intent intent = new Intent();
            intent.setAction("com.huawei.bone.action.CORE_SLEEP_DEFAULT_SWITCH");
            intent.putExtra("coreSleep_value", true);
            BroadcastManagerUtil.bFI_(BaseApplication.getContext(), intent);
            this.b.setSwitchSetting("core_sleep_button", "1", null);
            jqp.a("1");
            return;
        }
        this.b.setSwitchSettingToLocal("core_sleep_Default", "1", PrebakedEffectId.RT_COIN_DROP);
    }

    public void e(int i, String str) {
        ReleaseLogUtil.e("DEVMGR_HwHeartRateHelper", " enter handleDefaultSwitch result :", Integer.valueOf(i));
        DeviceInfo d = jpt.d("HwHeartRateHelper");
        if (d == null) {
            ReleaseLogUtil.d("DEVMGR_HwHeartRateHelper", "handleDefaultSwitch deviceInfo is null");
            return;
        }
        f(i);
        String d2 = knl.d(d.getDeviceIdentify());
        LogUtil.a("HwHeartRateHelper", "encryptedDeviceIndetify is ", d2);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), d2);
        LogUtil.a("HwHeartRateHelper", "sharedPreferenceResult is ", b);
        if ("".equals(b)) {
            ReleaseLogUtil.e("DEVMGR_HwHeartRateHelper", " enter handleDefaultSwitch sharedPreferenceResult is empty");
            b(i);
            b(i, str);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), d2, "0", new StorageParams(0));
            jpe.b().c(new jpa("heart_rate_switch", 2, i, c));
            jpe.b().c(new jpb("core_sleep_switch", 2, i, c));
            jpe.b().e();
        }
        if ((i & 4) == 4) {
            LogUtil.a("HwHeartRateHelper", "support run course true");
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "new_run_course", "0", new StorageParams(0));
        } else {
            LogUtil.a("HwHeartRateHelper", "support run course false");
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "new_run_course", "1", new StorageParams(0));
        }
        DeviceCapability d3 = cvs.d();
        if (d3 == null || !d3.isSupportCycleBloodOxygenSwitch()) {
            return;
        }
        i();
    }

    private void i() {
        DeviceInfo a2 = jpt.a("HwHeartRateHelper");
        if (a2 == null) {
            LogUtil.h("HwHeartRateHelper", "info is null");
        } else if (jpo.c(a2.getDeviceIdentify()) == 2) {
            f();
        } else {
            b();
        }
    }

    private void f() {
        int m = CommonUtil.m(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), "100", "custom.blood.oxygen.switch"));
        int m2 = CommonUtil.m(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), "103", "custom.blood.oxygen.remind"));
        jhg c2 = jhg.c(BaseApplication.getContext());
        if (m == 0) {
            LogUtil.a("HwHeartRateHelper", "handleFamilyBloodOxygen blood switch status is closed");
            c2.b(0, m2, false);
            return;
        }
        LogUtil.a("HwHeartRateHelper", "handleFamilyBloodOxygen blood switch status is open");
        if (m2 == 0) {
            c2.b(0, m2, true);
        } else {
            c2.b(1, m2, true);
        }
    }

    private void f(int i) {
        if ((i & 16) == 16) {
            jow.e().d(true);
            LogUtil.a("HwHeartRateHelper", "setIsOpenRaiseAndDownHeartRate is true");
        } else {
            jow.e().d(false);
            LogUtil.h("HwHeartRateHelper", "setIsOpenRaiseAndDownHeartRate is false");
        }
    }

    public void a(final int[] iArr, final Semaphore semaphore) {
        this.b.getSwitchSetting("custom.heart_rate_down_remind", new IBaseResponseCallback() { // from class: joy.17
            /* JADX WARN: Removed duplicated region for block: B:13:0x0060  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x006d  */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void d(int r8, java.lang.Object r9) {
                /*
                    r7 = this;
                    java.lang.String r0 = "getHeartRateDownRemindNumber err_code = "
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
                    java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                    java.lang.String r1 = "HwHeartRateHelper"
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                    r0 = 1
                    r2 = 0
                    java.util.concurrent.Semaphore r3 = r2     // Catch: java.lang.InterruptedException -> L2c
                    java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.InterruptedException -> L2c
                    r5 = 3
                    boolean r3 = r3.tryAcquire(r5, r4)     // Catch: java.lang.InterruptedException -> L2c
                    r4 = 2
                    java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.InterruptedException -> L2c
                    java.lang.String r5 = "getHeartRateDownRemindNumber semaphore tryAcquire() is success:"
                    r4[r2] = r5     // Catch: java.lang.InterruptedException -> L2c
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.InterruptedException -> L2c
                    r4[r0] = r3     // Catch: java.lang.InterruptedException -> L2c
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r4)     // Catch: java.lang.InterruptedException -> L2c
                    goto L38
                L2c:
                    r3 = move-exception
                    java.lang.String r3 = r3.getMessage()
                    java.lang.Object[] r3 = new java.lang.Object[]{r3}
                    com.huawei.hwlogsmodel.LogUtil.b(r1, r3)
                L38:
                    android.content.Context r3 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
                    jhg r3 = defpackage.jhg.c(r3)
                    int[] r4 = r3
                    r4 = r4[r2]
                    if (r4 != r0) goto L7e
                    if (r8 != 0) goto L5d
                    boolean r8 = r9 instanceof java.lang.String
                    if (r8 == 0) goto L5d
                    java.lang.String r9 = (java.lang.String) r9
                    int r8 = java.lang.Integer.parseInt(r9)     // Catch: java.lang.NumberFormatException -> L53
                    goto L5e
                L53:
                    java.lang.String r8 = "supportHeartRateDown NumberFormatException"
                    java.lang.Object[] r8 = new java.lang.Object[]{r8}
                    com.huawei.hwlogsmodel.LogUtil.b(r1, r8)
                L5d:
                    r8 = r2
                L5e:
                    if (r8 != 0) goto L6d
                    java.lang.String r8 = "close heart rate down remind."
                    java.lang.Object[] r8 = new java.lang.Object[]{r8}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
                    r3.e(r2, r2, r2)
                    goto L96
                L6d:
                    java.lang.String r9 = "open heart rate raise remind. heart rate number is "
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
                    java.lang.Object[] r9 = new java.lang.Object[]{r9, r4}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r9)
                    r3.e(r0, r8, r2)
                    goto L96
                L7e:
                    if (r4 != 0) goto L8d
                    r3.e(r2, r2, r2)
                    java.lang.String r8 = "continueHeartrateEnable[0] is 0"
                    java.lang.Object[] r8 = new java.lang.Object[]{r8}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
                    goto L96
                L8d:
                    java.lang.String r8 = "continueHeartrateEnable[0] != 0 & != 1"
                    java.lang.Object[] r8 = new java.lang.Object[]{r8}
                    com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
                L96:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.joy.AnonymousClass17.d(int, java.lang.Object):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Semaphore semaphore, final int i, final int i2) {
        if (jhg.c(BaseApplication.getContext()).d()) {
            c(i2, SharedPreferenceManager.b(BaseApplication.getContext(), "105", "custom.heart_rate_down_remind"), i);
            semaphore.release();
        } else {
            this.b.getSwitchSetting("custom.heart_rate_down_remind", new IBaseResponseCallback() { // from class: joy.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i3, Object obj) {
                    LogUtil.a("HwHeartRateHelper", "heartRateDownDefaultOpen err_code ", Integer.valueOf(i3));
                    joy.this.c(i2, obj, i);
                    semaphore.release();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Object obj, int i2) {
        jhg c2 = jhg.c(BaseApplication.getContext());
        int m = obj instanceof String ? CommonUtil.m(BaseApplication.getContext(), (String) obj) : 0;
        if (i == 1) {
            if (obj == null) {
                m = 40;
            }
            if (m == 0) {
                c2.e(0, 0, true);
                return;
            } else {
                c2.e(1, m, true);
                return;
            }
        }
        if (i2 == 1) {
            c2.e(0, 0, false);
        } else if (m == 0) {
            c2.e(0, 0, false);
        } else {
            c2.e(1, m, false);
        }
        LogUtil.a("HwHeartRateHelper", "heartRateDownDefaultOpen heartRate is closed");
    }

    public void e() {
        LogUtil.a("HwHeartRateHelper", "syncCoreSleepButtonEnable");
        DeviceCapability d = cvs.d();
        if (d == null) {
            LogUtil.h("HwFitnessManager", "syncCoreSleepButtonEnable deviceCapability is null");
        } else if (!d.isSupportCoreSleep()) {
            LogUtil.h("HwFitnessManager", "syncCoreSleepButtonEnable is not support");
        } else {
            this.b.getSwitchSetting("core_sleep_button", new IBaseResponseCallback() { // from class: joy.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    int i2;
                    LogUtil.a("HwFitnessManager", "CORE_SLEEP_BUTTON errorCode:", Integer.valueOf(i));
                    if (i == 0 && obj != null && (obj instanceof String)) {
                        try {
                            i2 = Integer.parseInt((String) obj);
                        } catch (NumberFormatException unused) {
                            LogUtil.b("HwFitnessManager", "NumberFormatException");
                        }
                        LogUtil.a("HwFitnessManager", "syncCoreSleepButtonEnable status:", Integer.valueOf(i2));
                        jho.e(i2);
                    }
                    i2 = 0;
                    LogUtil.a("HwFitnessManager", "syncCoreSleepButtonEnable status:", Integer.valueOf(i2));
                    jho.e(i2);
                }
            });
        }
    }

    public void a() {
        LogUtil.a("HwHeartRateHelper", "syncHeartRateButtonEnable enter.");
        DeviceCapability d = cvs.d();
        if (d == null) {
            LogUtil.h("HwFitnessManager", "syncHeartRateButtonEnable deviceCapability is null");
            return;
        }
        if (!d.isSupportHeartRateEnable()) {
            LogUtil.h("HwFitnessManager", "syncHeartRateButtonEnable is not support");
            return;
        }
        if (!d.isSupportHeartRateEnable() || d.isSupportContinueHeartRate()) {
            return;
        }
        if (jhg.c(BaseApplication.getContext()).d()) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), "102", "heart_rate_button");
            LogUtil.a("HwHeartRateHelper", "syncHeartRateButtonEnable familyStatus is:", b);
            jho.a(CommonUtil.h(b));
            return;
        }
        this.b.getSwitchSetting("heart_rate_button", new IBaseResponseCallback() { // from class: joy.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                int i2;
                LogUtil.a("HwFitnessManager", "HEART_RATE_BUTTON errorCode :", Integer.valueOf(i));
                if (obj instanceof String) {
                    i2 = jds.c((String) obj, 10);
                    LogUtil.a("05", 1, "HwFitnessManager", "syncHeartRateButtonEnable btValue:", Integer.valueOf(i2));
                } else {
                    i2 = 0;
                }
                jho.a(i2);
            }
        });
    }

    public void d(final DeviceCapability deviceCapability) {
        LogUtil.a("HwHeartRateHelper", "enter autoSendHeartRateCommand");
        if (deviceCapability == null) {
            return;
        }
        LogUtil.a("HwHeartRateHelper", "heart rate remind default open flag ", Integer.valueOf(jow.e().c("heartRateRemindKey")));
        final int[] iArr = new int[1];
        c(new IBaseResponseCallback() { // from class: joy.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == -1) {
                    joy.this.c(deviceCapability, iArr);
                } else {
                    joy.this.d(deviceCapability, iArr);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceCapability deviceCapability, int[] iArr) {
        LogUtil.a("HwHeartRateHelper", "enter handleHeartRateRemindDefaultOpen");
        Semaphore semaphore = new Semaphore(1);
        try {
            semaphore.acquire();
            if (deviceCapability.isSupportContinueHeartRate()) {
                if (deviceCapability.isSupportHeartRateEnable()) {
                    d(iArr, semaphore);
                } else {
                    LogUtil.a("HwHeartRateHelper", "handleHeartRateRemindDefaultOpen reLink send continue heart rate");
                    e(iArr, semaphore);
                }
            } else {
                c(iArr, semaphore);
            }
            jhg c2 = jhg.c(BaseApplication.getContext());
            if (deviceCapability.isSupportHeartRateRaiseAlarm()) {
                LogUtil.a("HwHeartRateHelper", "handleHeartRateRemindDefaultOpen 5.7.28 is open. check heart rate raise remind number.");
                if (jhg.c(BaseApplication.getContext()).d()) {
                    b(c2);
                } else {
                    semaphore.acquire();
                    e(semaphore, 1, iArr[0]);
                }
            }
            if (deviceCapability.isSupportHeartRateDownAlarm()) {
                LogUtil.a("HwHeartRateHelper", "handleHeartRateRemindDefaultOpen 5.7.34 is open. check heart rate down remind number.");
                if (jhg.c(BaseApplication.getContext()).d()) {
                    a(c2);
                } else {
                    semaphore.acquire();
                    a(semaphore, 1, iArr[0]);
                }
            }
        } catch (InterruptedException unused) {
            LogUtil.b("HwHeartRateHelper", "handleHeartRateRemindDefaultOpen InterruptedException ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceCapability deviceCapability, int[] iArr) {
        LogUtil.a("HwHeartRateHelper", "enter handleOriginHeartRate");
        Semaphore semaphore = new Semaphore(0);
        if (deviceCapability.isSupportContinueHeartRate()) {
            if (deviceCapability.isSupportHeartRateEnable()) {
                d(iArr, semaphore);
            } else {
                LogUtil.a("HwHeartRateHelper", "reLink send continue heart rate");
                e(iArr, semaphore);
            }
        } else {
            c(iArr, semaphore);
        }
        jhg c2 = jhg.c(BaseApplication.getContext());
        if (deviceCapability.isSupportHeartRateRaiseAlarm()) {
            LogUtil.a("HwHeartRateHelper", "5.7.28 is open. check heart rate raise remind number.");
            if (jhg.c(BaseApplication.getContext()).d()) {
                b(c2);
            } else {
                b(iArr, semaphore);
            }
        }
        if (deviceCapability.isSupportHeartRateDownAlarm()) {
            LogUtil.a("HwHeartRateHelper", "5.7.34 is open. check heart rate down remind number.");
            if (jhg.c(BaseApplication.getContext()).d()) {
                a(c2);
            } else {
                a(iArr, semaphore);
            }
        }
    }

    private void b(jhg jhgVar) {
        int m = CommonUtil.m(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), "104", "custom.heart_rate_raise_remind"));
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "102", "heart_rate_button");
        LogUtil.a("HwHeartRateHelper", "setFamilyHeartRateRaise status : ", b, ",heartRateRaiseNumber : ", Integer.valueOf(m));
        if (CommonUtil.h(b) == 0) {
            jhgVar.a(0, 0, false);
            LogUtil.a("HwHeartRateHelper", "setFamilyHeartRateDown is close");
        } else if (m != 0) {
            LogUtil.a("HwHeartRateHelper", "open heart rate raise remind. heart rate number is ", Integer.valueOf(m));
            jhgVar.a(1, m, false);
        } else {
            LogUtil.a("HwHeartRateHelper", "setFamilyHeartRateDown close heart rate raise remind.");
            jhgVar.a(0, 0, false);
        }
    }

    private void a(jhg jhgVar) {
        int m = CommonUtil.m(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), "105", "custom.heart_rate_down_remind"));
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "102", "heart_rate_button");
        LogUtil.a("HwHeartRateHelper", "setFamilyHeartRateDown status : ", b, ",heartRateDownNumber : ", Integer.valueOf(m));
        if (CommonUtil.h(b) == 0) {
            jhgVar.e(0, 0, false);
            LogUtil.a("HwHeartRateHelper", "setFamilyHeartRateDown is close");
        } else if (m != 0) {
            LogUtil.a("HwHeartRateHelper", "open heart rate down remind. heart rate number is ", Integer.valueOf(m));
            jhgVar.e(1, m, false);
        } else {
            LogUtil.a("HwHeartRateHelper", "setFamilyHeartRateDown close heart rate raise remind.");
            jhgVar.e(0, 0, false);
        }
    }

    private void b() {
        LogUtil.a("HwHeartRateHelper", "enter handleBloodOxygen");
        jhg c2 = jhg.c(BaseApplication.getContext());
        final int[] iArr = new int[1];
        final Semaphore semaphore = new Semaphore(1);
        try {
            semaphore.acquire();
            c(c2, iArr, semaphore);
            semaphore.acquire();
            this.b.getSwitchSetting("custom.blood.oxygen.remind", new IBaseResponseCallback() { // from class: joy.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HwHeartRateHelper", "handleBloodOxygen errorCode = ", Integer.valueOf(i));
                    int i2 = 0;
                    int min = (i == 0 && (obj instanceof String)) ? Math.min(CommonUtil.b(BaseApplication.getContext(), (String) obj, 0), 90) : 0;
                    DeviceCapability d = cvs.d();
                    if (d == null || !d.isSupportBloodOxygenDownRemind()) {
                        LogUtil.h("HwHeartRateHelper", "handleBloodOxygen deviceCapability is null or deviceCapability is not support");
                        semaphore.release();
                        return;
                    }
                    if (iArr[0] != 0 && min != 0) {
                        LogUtil.a("HwHeartRateHelper", "handleBloodOxygen blood switch status is open");
                        i2 = 1;
                    }
                    jho.c(i2, min);
                    semaphore.release();
                }
            });
        } catch (InterruptedException unused) {
            LogUtil.b("HwHeartRateHelper", "handleBloodOxygen InterruptedException");
        }
    }

    private void c(final jhg jhgVar, final int[] iArr, final Semaphore semaphore) {
        this.b.getSwitchSetting("custom.blood.oxygen.switch", new IBaseResponseCallback() { // from class: joy.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HwHeartRateHelper", "getBloodOxygenSwitch errorCode = ", Integer.valueOf(i));
                int b = (i == 0 && (obj instanceof String)) ? CommonUtil.b(BaseApplication.getContext(), (String) obj, 0) : 0;
                iArr[0] = b;
                jhgVar.b(b);
                semaphore.release();
            }
        });
    }

    private void c(final int[] iArr, final Semaphore semaphore) {
        LogUtil.a("HwHeartRateHelper", "syncHeartRateButtonEnableHandleLock enter.");
        DeviceCapability d = cvs.d();
        if (d == null) {
            LogUtil.h("HwFitnessManager", "syncHeartRateButtonEnableHandleLock deviceCapability is null");
            semaphore.release();
            return;
        }
        if (!d.isSupportHeartRateEnable()) {
            LogUtil.h("HwFitnessManager", "syncHeartRateButtonEnableHandleLock is not support");
            semaphore.release();
        } else {
            if (!d.isSupportHeartRateEnable() || d.isSupportContinueHeartRate()) {
                return;
            }
            if (jhg.c(BaseApplication.getContext()).d()) {
                jho.a(CommonUtil.h(SharedPreferenceManager.b(BaseApplication.getContext(), "102", "heart_rate_button")));
            } else {
                this.b.getSwitchSetting("heart_rate_button", new IBaseResponseCallback() { // from class: joy.9
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        int i2;
                        LogUtil.a("HwFitnessManager", "HEART_RATE_BUTTON errorCode: ", Integer.valueOf(i));
                        if (obj instanceof String) {
                            i2 = jds.c((String) obj, 10);
                            LogUtil.a("05", 1, "HwFitnessManager", "syncHeartRateButtonEnableHandleLock btValue:", Integer.valueOf(i2));
                        } else {
                            i2 = 0;
                        }
                        iArr[0] = i2;
                        jho.a(i2);
                        semaphore.release();
                    }
                });
            }
        }
    }

    public void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            return;
        }
        jpe.b().c(new jpf("heart_rate_switch", 1, d(), cvs.e(deviceInfo.getDeviceIdentify())));
        jpe.b().c(new jpc("core_sleep_switch", 1, d()));
        jpe.b().e(1);
    }

    public void c() {
        LogUtil.a("HwHeartRateHelper", "syncToSendHeartSwitch enter");
        DeviceInfo d = jpt.d("HwHeartRateHelper");
        if (d == null || TextUtils.isEmpty(d.getDeviceIdentify())) {
            LogUtil.a("HwHeartRateHelper", "syncToSendHeartSwitch deviceInfo error");
            return;
        }
        jpe.b().c(new jpf("heart_rate_switch", 1, d(), cvs.e(d.getDeviceIdentify())));
        jpe.b().e(1);
    }
}
