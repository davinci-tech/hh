package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.up.utils.ErrorCode;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class jgw extends HwBaseManager implements BluetoothDataReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13833a = new Object();
    private static jgw b;
    private BroadcastReceiver c;
    private IBaseResponseCallback d;
    private Context e;
    private jfq f;

    private int b(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 0;
            case 7:
            case 8:
            case 9:
                return 1;
            default:
                return -1;
        }
    }

    private int e(int i) {
        return (i <= 127 || i > 16383) ? i : (i & 127) + ((i & 16256) << 1) + 32768;
    }

    private jgw(Context context) {
        super(context);
        this.c = new BroadcastReceiver() { // from class: jgw.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                DeviceInfo deviceInfo;
                if (context2 == null || intent == null) {
                    return;
                }
                LogUtil.a("HwExerciseAdviceManager", "mConnectStateChangedReceiver() action : ", intent.getAction());
                if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null) {
                    return;
                }
                Object[] objArr = new Object[4];
                objArr[0] = "mConnectStateChangedReceiver() status : ";
                objArr[1] = Integer.valueOf(deviceInfo.getDeviceConnectState());
                objArr[2] = " is callback null: ";
                objArr[3] = Boolean.valueOf(jgw.this.d == null);
                LogUtil.a("HwExerciseAdviceManager", objArr);
                if (jgw.this.d == null) {
                    return;
                }
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                if (deviceConnectState == 2) {
                    jgw.this.d.d(100000, 100001);
                } else if (deviceConnectState == 3) {
                    jgw.this.d.d(100000, Integer.valueOf(ErrorCode.HWID_IS_STOPED));
                } else {
                    LogUtil.h("HwExerciseAdviceManager", "Invalid device connect state.");
                }
            }
        };
        this.e = context;
        jfq c = jfq.c();
        this.f = c;
        if (c != null) {
            c.e(22, this);
        } else {
            LogUtil.b("HwExerciseAdviceManager", "mDeviceConfigManager is null");
        }
        BroadcastManagerUtil.bFC_(this.e, this.c, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public static jgw d() {
        jgw jgwVar;
        synchronized (f13833a) {
            if (b == null) {
                LogUtil.a("HwExerciseAdviceManager", "getInstance");
                b = new jgw(BaseApplication.getContext());
            }
            jgwVar = b;
        }
        return jgwVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 22;
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwExerciseAdviceManager", "registerDataCallback");
        this.d = iBaseResponseCallback;
    }

    public void c() {
        LogUtil.a("HwExerciseAdviceManager", "unRegisterDataCallback");
        this.d = null;
    }

    public void a() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(22);
        deviceCommand.setCommandID(12);
        String d = cvx.d(0);
        String e = cvx.e(129);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("HwExerciseAdviceManager", "getFitRunCourseInfo deviceCommand : ", deviceCommand.toString());
        this.f.b(deviceCommand);
    }

    private int d(byte[] bArr) {
        LogUtil.a("HwExerciseAdviceManager", "Enter parseFitRunCourseInfo");
        cwe e = e(bArr);
        int i = -1;
        if (e == null) {
            return -1;
        }
        for (cwd cwdVar : e.e()) {
            try {
                LogUtil.a("HwExerciseAdviceManager", "the case is ", Integer.valueOf(Integer.parseInt(cwdVar.e(), 16)));
                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                if (parseInt == 1) {
                    LogUtil.a("HwExerciseAdviceManager", "RUNNING_COURSE_STURCT value ", cwdVar.c());
                } else if (parseInt == 2) {
                    LogUtil.a("HwExerciseAdviceManager", "DEVICE_FRAME_ABILITY value ", cwdVar.c());
                } else if (parseInt == 3) {
                    LogUtil.a("HwExerciseAdviceManager", "DEVICE_FRAME_VERSION value ", cwdVar.c());
                    i = Integer.parseInt(cwdVar.c());
                } else if (parseInt == 4) {
                    LogUtil.a("HwExerciseAdviceManager", "EXERCISE_ID_LIST value ", cwdVar.c());
                } else if (parseInt == 5) {
                    LogUtil.a("HwExerciseAdviceManager", "EXERCISE_VERSION_LIST value ", cwdVar.c());
                } else {
                    LogUtil.h("HwExerciseAdviceManager", "parseFitRunCourseInfo switch defalt");
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("HwExerciseAdviceManager", "parseFitRunCourseInfo NumberFormatException");
            }
        }
        return i;
    }

    public void d(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.h("HwExerciseAdviceManager", "map is null,can not push to watch!");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(22);
        deviceCommand.setCommandID(13);
        StringBuffer stringBuffer = new StringBuffer(16);
        b(fitWorkout, stringBuffer);
        stringBuffer.append(d(3, fitWorkout.acquireMeasurementType(), 0));
        h(fitWorkout, stringBuffer);
        a(fitWorkout, stringBuffer);
        e(fitWorkout, stringBuffer);
        stringBuffer.append(d(14, 1, 0));
        stringBuffer.append(d(15, fitWorkout.acquireRunActionNum(), 0));
        d(fitWorkout, stringBuffer);
        c(fitWorkout, stringBuffer);
        deviceCommand.setDataLen(stringBuffer.length() / 2);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        LogUtil.a("HwExerciseAdviceManager", "pushFitRunCourseData deviceCommand : ", deviceCommand.toString());
        this.f.b(deviceCommand);
    }

    private void h(FitWorkout fitWorkout, StringBuffer stringBuffer) {
        if (fitWorkout == null || stringBuffer == null) {
            return;
        }
        stringBuffer.append(d(4, (int) fitWorkout.acquireDistance(), 2));
        stringBuffer.append(d(5, fitWorkout.acquireDuration(), 2));
    }

    private void a(FitWorkout fitWorkout, StringBuffer stringBuffer) {
        if (fitWorkout == null || stringBuffer == null) {
            return;
        }
        stringBuffer.append(d(7, fitWorkout.acquireDifficulty(), 0));
        String accquireVersion = fitWorkout.accquireVersion();
        String e = cvx.e(9);
        String c = cvx.c(accquireVersion);
        stringBuffer.append(e);
        stringBuffer.append(cvx.e(c.length() / 2));
        stringBuffer.append(c);
    }

    private void b(FitWorkout fitWorkout, StringBuffer stringBuffer) {
        if (fitWorkout == null || stringBuffer == null) {
            return;
        }
        String acquireId = fitWorkout.acquireId();
        String e = cvx.e(1);
        String c = cvx.c(acquireId);
        stringBuffer.append(e);
        stringBuffer.append(cvx.e(c.length() / 2));
        stringBuffer.append(c);
        String acquireName = fitWorkout.acquireName();
        String e2 = cvx.e(2);
        String g = cvx.g(acquireName);
        stringBuffer.append(e2);
        stringBuffer.append(cvx.e(g.length() / 2));
        stringBuffer.append(g);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(com.huawei.pluginfitnessadvice.FitWorkout r4, java.lang.StringBuffer r5) {
        /*
            r3 = this;
            java.lang.String r0 = "课程说明"
            java.lang.String r1 = "extendMap"
            if (r4 == 0) goto L65
            if (r5 != 0) goto L9
            goto L65
        L9:
            java.lang.String r4 = r4.acquireNarrowDesc()
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L36
            r2.<init>(r4)     // Catch: org.json.JSONException -> L36
            boolean r4 = r2.has(r1)     // Catch: org.json.JSONException -> L36
            if (r4 == 0) goto L41
            java.lang.String r4 = r2.optString(r1)     // Catch: org.json.JSONException -> L36
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch: org.json.JSONException -> L36
            if (r4 != 0) goto L41
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L36
            java.lang.String r1 = r2.optString(r1)     // Catch: org.json.JSONException -> L36
            r4.<init>(r1)     // Catch: org.json.JSONException -> L36
            boolean r1 = r4.isNull(r0)     // Catch: org.json.JSONException -> L36
            if (r1 != 0) goto L41
            java.lang.String r4 = r4.optString(r0)     // Catch: org.json.JSONException -> L36
            goto L42
        L36:
            java.lang.String r4 = "detailInfo exception"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r0 = "HwExerciseAdviceManager"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)
        L41:
            r4 = 0
        L42:
            if (r4 == 0) goto L65
            java.lang.String r4 = defpackage.cvx.g(r4)
            r0 = 12
            java.lang.String r0 = defpackage.cvx.e(r0)
            r5.append(r0)
            int r0 = r4.length()
            int r0 = r0 / 2
            int r0 = r3.e(r0)
            java.lang.String r0 = defpackage.cvx.e(r0)
            r5.append(r0)
            r5.append(r4)
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jgw.e(com.huawei.pluginfitnessadvice.FitWorkout, java.lang.StringBuffer):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(com.huawei.pluginfitnessadvice.FitWorkout r5, java.lang.StringBuffer r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L5b
            if (r6 != 0) goto L5
            goto L5b
        L5:
            java.lang.String r5 = r5.acquireExtendSeaMap()
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r1 = -1
            if (r0 != 0) goto L4e
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L43
            r0.<init>(r5)     // Catch: org.json.JSONException -> L43
            java.lang.String r5 = "workoutType"
            org.json.JSONObject r5 = r0.optJSONObject(r5)     // Catch: org.json.JSONException -> L43
            if (r5 == 0) goto L4e
            mmz r0 = new mmz     // Catch: org.json.JSONException -> L43
            java.lang.String r2 = "title"
            java.lang.String r2 = r5.optString(r2)     // Catch: org.json.JSONException -> L43
            java.lang.String r3 = "context"
            java.lang.String r5 = r5.optString(r3)     // Catch: org.json.JSONException -> L43
            r0.<init>(r2, r5)     // Catch: org.json.JSONException -> L43
            java.lang.String r5 = r0.a()     // Catch: org.json.JSONException -> L43
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: org.json.JSONException -> L43
            if (r5 != 0) goto L4e
            java.lang.String r5 = r0.a()     // Catch: org.json.JSONException -> L43
            r0 = 10
            int r5 = health.compact.a.CommonUtil.a(r5, r0)     // Catch: org.json.JSONException -> L43
            goto L4f
        L43:
            java.lang.String r5 = "workoutTypeObject exception"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r0 = "HwExerciseAdviceManager"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)
        L4e:
            r5 = r1
        L4f:
            if (r5 == r1) goto L5b
            r0 = 31
            r1 = 0
            java.lang.String r5 = r4.d(r0, r5, r1)
            r6.append(r5)
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jgw.d(com.huawei.pluginfitnessadvice.FitWorkout, java.lang.StringBuffer):void");
    }

    private void c(FitWorkout fitWorkout, StringBuffer stringBuffer) {
        if (fitWorkout == null || stringBuffer == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(d(fitWorkout.acquireWarmUpRunAction(), 0));
        Iterator<WorkoutAction> it = fitWorkout.acquireWorkoutActions().iterator();
        while (it.hasNext()) {
            sb.append(d(it.next(), 1));
        }
        sb.append(d(fitWorkout.acquireCoolDownRunAction(), 3));
        stringBuffer.append(cvx.e(16));
        stringBuffer.append(cvx.e(e(sb.length() / 2)));
        stringBuffer.append(sb.toString());
    }

    private int b(byte[] bArr) {
        LogUtil.a("HwExerciseAdviceManager", "Enter parseFitRunCourseResponseData");
        cwe e = e(bArr);
        if (e != null) {
            try {
                return Integer.parseInt(e.e().get(0).c(), 16);
            } catch (NumberFormatException unused) {
                LogUtil.b("HwExerciseAdviceManager", "parseFitRunCourseResponseData NumberFormatException");
            }
        }
        return -1;
    }

    private cwe e(byte[] bArr) {
        Object[] objArr = new Object[2];
        objArr[0] = "Enter getTlvList is dataContent null ";
        objArr[1] = Boolean.valueOf(bArr == null);
        LogUtil.a("HwExerciseAdviceManager", objArr);
        if (bArr == null) {
            return null;
        }
        String d = cvx.d(bArr);
        if (d.length() <= 4) {
            LogUtil.h("HwExerciseAdviceManager", "getTlvList dataStrInfo.length() is invalid");
            return null;
        }
        try {
            return new cwl().a(d.substring(4, d.length()));
        } catch (cwg unused) {
            LogUtil.b("HwExerciseAdviceManager", "resloveWatchStatus TlvException.");
            return null;
        }
    }

    private String d(int i, int i2, int i3) {
        String e;
        int i4 = 1;
        if (i3 == 0) {
            e = cvx.e(i2);
        } else if (i3 == 1) {
            e = cvx.a(i2);
            i4 = 2;
        } else if (i3 == 2) {
            e = cvx.b(i2);
            i4 = 4;
        } else {
            LogUtil.h("HwExerciseAdviceManager", "formTlvForIntType unknown valueType : ", Integer.valueOf(i3));
            e = "";
            i4 = 0;
        }
        String str = cvx.e(i) + cvx.e(i4) + e;
        LogUtil.c("HwExerciseAdviceManager", "formTlvForIntType() result : ", str);
        return str;
    }

    private String d(WorkoutAction workoutAction, int i) {
        if (workoutAction == null) {
            LogUtil.h("HwExerciseAdviceManager", "workoutAction is null");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        b(workoutAction, stringBuffer);
        int acquireIntensityType = workoutAction.acquireIntensityType();
        int b2 = b(acquireIntensityType);
        if (b2 != -1) {
            stringBuffer.append(d(25, b2, 0));
        }
        stringBuffer.append(d(26, acquireIntensityType, 0));
        String c = c(workoutAction, acquireIntensityType);
        String e = cvx.e(27);
        String c2 = cvx.c(c);
        stringBuffer.append(e);
        stringBuffer.append(cvx.e(c2.length() / 2));
        stringBuffer.append(c2);
        stringBuffer.append(d(28, i, 0));
        String e2 = cvx.e(145);
        stringBuffer.insert(0, cvx.e(e(stringBuffer.length() / 2)));
        stringBuffer.insert(0, e2);
        return stringBuffer.toString();
    }

    private void b(WorkoutAction workoutAction, StringBuffer stringBuffer) {
        if (workoutAction == null || stringBuffer == null) {
            return;
        }
        String actionId = workoutAction.getActionId();
        String e = cvx.e(18);
        String c = cvx.c(actionId);
        stringBuffer.append(e);
        stringBuffer.append(cvx.e(c.length() / 2));
        stringBuffer.append(c);
        AtomicAction action = workoutAction.getAction();
        if (action == null) {
            return;
        }
        String name = action.getName();
        String e2 = cvx.e(19);
        String g = cvx.g(name);
        stringBuffer.append(e2);
        stringBuffer.append(cvx.e(g.length() / 2));
        stringBuffer.append(g);
        stringBuffer.append(d(21, workoutAction.acquireMeasurementValue(), 2));
        stringBuffer.append(d(22, workoutAction.acquireMeasurementType(), 0));
    }

    private String c(WorkoutAction workoutAction, int i) {
        if (workoutAction == null) {
            return null;
        }
        if (i == 0) {
            return String.valueOf((int) (workoutAction.acquireSpeedL() * 1000.0f)) + "," + String.valueOf((int) (workoutAction.acquireSpeedH() * 1000.0f));
        }
        if (i == 1) {
            return String.valueOf(workoutAction.acquireRelativeHeartRatePercentL()) + "," + String.valueOf(workoutAction.acquireRelativeHeartRatePercentH());
        }
        if (i == 2) {
            return workoutAction.acquireAbsoluteHeartRateL() + "," + workoutAction.acquireAbsoluteHeartRateH();
        }
        if (i == 3) {
            int acquireSpeedL = (int) (workoutAction.acquireSpeedL() * 1000.0f);
            int acquireSpeedH = (int) (workoutAction.acquireSpeedH() * 1000.0f);
            return String.valueOf(workoutAction.acquireRelativeHeartRatePercentL()) + "," + String.valueOf(workoutAction.acquireRelativeHeartRatePercentH()) + "|" + String.valueOf(acquireSpeedL) + "," + String.valueOf(acquireSpeedH);
        }
        if (i == 4) {
            return String.valueOf(workoutAction.acquireRelativeHeartRateRangeL()) + "," + String.valueOf(workoutAction.acquireRelativeHeartRateRangeH());
        }
        if (i == 5) {
            int acquireSpeedL2 = (int) (workoutAction.acquireSpeedL() * 1000.0f);
            int acquireSpeedH2 = (int) (workoutAction.acquireSpeedH() * 1000.0f);
            return String.valueOf(workoutAction.acquireAbsoluteHeartRateL()) + "," + String.valueOf(workoutAction.acquireAbsoluteHeartRateH()) + "|" + String.valueOf(acquireSpeedL2) + "," + String.valueOf(acquireSpeedH2);
        }
        if (i == 6) {
            int acquireSpeedL3 = (int) (workoutAction.acquireSpeedL() * 1000.0f);
            int acquireSpeedH3 = (int) (workoutAction.acquireSpeedH() * 1000.0f);
            return String.valueOf(workoutAction.acquireRelativeHeartRateRangeL()) + "," + String.valueOf(workoutAction.acquireRelativeHeartRateRangeH()) + "|" + String.valueOf(acquireSpeedL3) + "," + String.valueOf(acquireSpeedH3);
        }
        return e(workoutAction, i);
    }

    private String e(WorkoutAction workoutAction, int i) {
        if (workoutAction == null) {
            return null;
        }
        if (i == 7) {
            return String.valueOf(workoutAction.acquireReserveHeartRatePercentL()) + "," + String.valueOf(workoutAction.acquireReserveHeartRatePercentH());
        }
        if (i == 8) {
            return String.valueOf(workoutAction.acquireReserveHeartRateRangeL()) + "," + String.valueOf(workoutAction.acquireReserveHeartRateRangeH());
        }
        if (i == 9) {
            return String.valueOf(workoutAction.acquireMaf180HeartRateBase()) + "," + String.valueOf(workoutAction.acquireMaf180HeartRateRange());
        }
        LogUtil.h("HwExerciseAdviceManager", "Invalid type");
        return null;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (i != 0 || bArr == null) {
            return;
        }
        Object[] objArr = new Object[4];
        objArr[0] = "onDataReceived bluetooth data";
        objArr[1] = cvx.d(bArr);
        objArr[2] = " is callback null: ";
        objArr[3] = Boolean.valueOf(this.d == null);
        LogUtil.a("HwExerciseAdviceManager", objArr);
        IBaseResponseCallback iBaseResponseCallback = this.d;
        if (iBaseResponseCallback == null) {
            return;
        }
        byte b2 = bArr[1];
        if (b2 == 12) {
            iBaseResponseCallback.d(12, Integer.valueOf(d(bArr)));
        } else if (b2 == 13) {
            iBaseResponseCallback.d(13, Integer.valueOf(b(bArr)));
        } else {
            LogUtil.h("HwExerciseAdviceManager", "Invalid course information type.");
        }
    }
}
