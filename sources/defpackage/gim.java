package defpackage;

import android.content.Context;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.basesport.wearengine.DeviceStateListener;
import com.huawei.health.basesport.wearengine.SportHiWearBusinessType;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.suggestion.protobuf.PlanDataForDevice;
import com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.plandata.CourseDataBean;
import defpackage.cba;
import defpackage.gir;
import defpackage.spn;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gim implements DeviceStateListener {
    private static int b;
    private static final Object c = new Object();
    private static gim e;

    /* renamed from: a, reason: collision with root package name */
    private BaseCoursePlanDataCustomer f12817a;
    private Context d;
    private boolean g = false;
    private caw i = caw.d();

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onConnecting() {
    }

    private gim(Context context, int i) {
        this.d = context;
        b = i;
        e(this);
        b();
        a();
    }

    private void a() {
        if (b == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
            this.f12817a = new giq();
        } else {
            this.f12817a = new gip();
        }
    }

    public static gim c(int i) {
        gim gimVar;
        synchronized (c) {
            if (e == null) {
                LogUtil.a("Suggestion_CoursePlanDeviceManager", "getInstance");
                e = new gim(BaseApplication.getContext(), i);
            }
            if (i != IntPlan.PlanType.NA_PLAN.getType()) {
                b = i;
            }
            gimVar = e;
        }
        return gimVar;
    }

    public static boolean e(boolean z) {
        if (!z) {
            LogUtil.h("Suggestion_CoursePlanDeviceManager", "isConnectedWearDevice() bluetoothIsEnabled=false");
            return false;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_CoursePlanDeviceManager");
        if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
            return true;
        }
        LogUtil.h("Suggestion_CoursePlanDeviceManager", "isConnectedWearDevice() false, failed");
        return false;
    }

    public boolean e() {
        b();
        return this.g;
    }

    public void d(FitWorkout fitWorkout, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        spn.b bVar;
        if (fitWorkout == null) {
            ReleaseLogUtil.c("Suggestion_CoursePlanDeviceManager", "pushFitRunCourseData failed with null workout");
            iBaseResponseCallback.d(1, 1);
            return;
        }
        ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "pushFitRunCourseData enter. workout id is:", fitWorkout.acquireId());
        int b2 = cba.b();
        int typeValue = SportHiWearBusinessType.FITNESS_CUSTOM_COURSE_FILE.getTypeValue();
        String str = "pushFitRunCourseData_" + fitWorkout.acquireId() + "_" + typeValue + "_" + b2;
        try {
            bVar = c(fitWorkout, b2, typeValue);
        } catch (cbe e2) {
            LogUtil.h("Suggestion_CoursePlanDeviceManager", str, e2.getMessage());
            bVar = null;
        }
        if (bVar != null) {
            this.i.sendCommandToDevice(str, bVar.e(), z ? new cbc(this.i, "Suggestion_CoursePlanDeviceManager", typeValue, b2, iBaseResponseCallback) : null, iBaseResponseCallback);
        } else {
            ReleaseLogUtil.d("Suggestion_CoursePlanDeviceManager", str, "builder pushFitRunCourseData msg error");
            d(iBaseResponseCallback, 1, (Object) 1);
        }
    }

    private spn.b c(FitWorkout fitWorkout, int i, int i2) throws cbe {
        String str = i + "courseInfo.bin";
        byte[] courseData = this.f12817a.getCourseData(fitWorkout, 0);
        cba.b bVar = new cba.b();
        bVar.e(i2).a(courseData.length).b(1).c(i);
        b(str, bVar.a().e(), courseData);
        spn.b bVar2 = new spn.b();
        bVar2.a(new File(d(str)));
        return bVar2;
    }

    private spn.b b(Plan plan, int i, int i2, String str) {
        byte[] d = d(plan);
        cba.b bVar = new cba.b();
        bVar.e(i2).a(d.length).b(1).c(i);
        cba a2 = bVar.a();
        LogUtil.h("Suggestion_CoursePlanDeviceManager", str, a2.toString());
        ByteBuffer allocate = ByteBuffer.allocate(a2.j());
        allocate.put(a2.e());
        allocate.put(d);
        allocate.flip();
        spn.b bVar2 = new spn.b();
        bVar2.c(allocate.array());
        return bVar2;
    }

    private void d(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, obj);
        }
    }

    public void d(Plan plan, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "sendPlanHandleShake enter.");
        if (plan == null) {
            ReleaseLogUtil.c("Suggestion_CoursePlanDeviceManager", "sendPlanHandleShake failed with plan is null.");
            d(iBaseResponseCallback, 1, (Object) 1);
            return;
        }
        if (!e()) {
            ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "sendPlanHandleShake failed with device not support");
            d(iBaseResponseCallback, 1, (Object) 1);
            return;
        }
        if (ase.d(plan)) {
            ReleaseLogUtil.d("Suggestion_CoursePlanDeviceManager", "sendPlanHandleShake failed with plan has not update.");
            d(iBaseResponseCallback, 1, (Object) 1);
            return;
        }
        int b2 = cba.b();
        int handleShakeBusinessType = this.f12817a.handleShakeBusinessType();
        String str = "sendPlanHandleShake_" + plan.acquireId() + "_" + handleShakeBusinessType + "_" + b2 + ":";
        this.i.sendCommandToDevice(str, b(plan, b2, handleShakeBusinessType, str).e(), z ? new cbc(this.i, "Suggestion_CoursePlanDeviceManager", handleShakeBusinessType, b2, iBaseResponseCallback) : null, iBaseResponseCallback);
    }

    public void a(String str, int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "sendFinishPlan enter.");
        if (StringUtils.g(str)) {
            ReleaseLogUtil.c("Suggestion_CoursePlanDeviceManager", "sendFinishPlan failed with plan id is null.");
            d(iBaseResponseCallback, 1, (Object) 1);
            return;
        }
        if (!gij.c()) {
            ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "sendFinishPlan failed with device not support");
            d(iBaseResponseCallback, 1, (Object) 1);
            return;
        }
        int b2 = cba.b();
        int finishPlanBusinessType = BaseCoursePlanDataCustomer.getFinishPlanBusinessType(i);
        String str2 = "sendFinishPlan_" + str + "_" + finishPlanBusinessType + "_" + b2 + ":";
        this.i.sendCommandToDevice(str2, d(str, b2, finishPlanBusinessType, str2).e(), z ? new cbc(this.i, "Suggestion_CoursePlanDeviceManager", finishPlanBusinessType, b2, iBaseResponseCallback) : null, iBaseResponseCallback);
    }

    private spn.b d(String str, int i, int i2, String str2) {
        byte[] a2 = a(str);
        cba.b bVar = new cba.b();
        bVar.e(i2).a(a2.length).b(1).c(i);
        cba a3 = bVar.a();
        LogUtil.h("Suggestion_CoursePlanDeviceManager", str2, a3.toString());
        ByteBuffer allocate = ByteBuffer.allocate(a3.j());
        allocate.put(a3.e());
        allocate.put(a2);
        allocate.flip();
        spn.b bVar2 = new spn.b();
        bVar2.c(allocate.array());
        return bVar2;
    }

    private byte[] a(String str) {
        PlanDataForDevice.PlanShakeHandsInfo.Builder newBuilder = PlanDataForDevice.PlanShakeHandsInfo.newBuilder();
        newBuilder.setLanguage(CommonUtil.u());
        newBuilder.setPlanId(str);
        newBuilder.setPunchTimeStamp((int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        newBuilder.setWeekStartTime((int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        LogUtil.h("Suggestion_CoursePlanDeviceManager", "getFinishRunPlanData() ", newBuilder.build().toString());
        return newBuilder.build().toByteArray();
    }

    private byte[] d(Plan plan) {
        PlanDataForDevice.PlanShakeHandsInfo.Builder newBuilder = PlanDataForDevice.PlanShakeHandsInfo.newBuilder();
        newBuilder.setLanguage(CommonUtil.u());
        newBuilder.setPlanId(plan.acquireId());
        newBuilder.setPunchTimeStamp((int) (plan.getLatestClockInTime() / 1000));
        newBuilder.setWeekStartTime((int) ase.a(plan).a());
        LogUtil.h("Suggestion_CoursePlanDeviceManager", "getHandlerShakeData() ", newBuilder.build().toString());
        return newBuilder.build().toByteArray();
    }

    public void d(IntPlan intPlan, List<FitWorkout> list, int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        spn.b bVar;
        if (intPlan == null) {
            ReleaseLogUtil.c("Suggestion_CoursePlanDeviceManager", "pushPlanData failed with plan is null.");
            d(iBaseResponseCallback, 1, (Object) 1);
            return;
        }
        a();
        ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "pushPlanData enter. plan id is:", intPlan.getPlanId());
        if (!d(intPlan.getPlanType().getType())) {
            ReleaseLogUtil.c("Suggestion_CoursePlanDeviceManager", "pushPlanData failed with device not support", Integer.valueOf(intPlan.getPlanType().getType()));
            d(iBaseResponseCallback, 1, (Object) 1);
            return;
        }
        if (ase.n(intPlan)) {
            ReleaseLogUtil.d("Suggestion_CoursePlanDeviceManager", "pushPlanData failed with plan has not update.");
            d(iBaseResponseCallback, 1, (Object) 1);
            return;
        }
        String str = "pushPlanData_" + intPlan.getPlanId();
        int b2 = cba.b();
        int distributeBusinessType = this.f12817a.distributeBusinessType();
        try {
            bVar = d(intPlan, list, i, b2, str);
        } catch (cbe e2) {
            ReleaseLogUtil.c("Suggestion_CoursePlanDeviceManager", str, e2.getMessage());
            bVar = null;
        }
        SharedPreferenceManager.c("MMKV_SUGGEST_MODULE_TAG", "isSendCourseDevice", intPlan.getPlanId() + " true");
        if (bVar != null) {
            this.i.sendCommandToDevice(str, bVar.e(), z ? new cbc(this.i, "Suggestion_CoursePlanDeviceManager", distributeBusinessType, b2, iBaseResponseCallback) : null, iBaseResponseCallback);
        } else {
            ReleaseLogUtil.d("Suggestion_CoursePlanDeviceManager", str, "builder pushPlanData msg error");
            d(iBaseResponseCallback, 1, (Object) 1);
        }
    }

    private spn.b d(IntPlan intPlan, List<FitWorkout> list, int i, int i2, String str) throws cbe {
        Integer num;
        gir.d dVar = new gir.d();
        dVar.c(i2);
        dVar.e(this.f12817a.distributeBusinessType());
        HashMap hashMap = new HashMap(list.size());
        Map<String, Integer> c2 = c(intPlan.getWeekInfoByOrder(ase.g(intPlan)));
        ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", str, " can send courseIds is", c2);
        for (FitWorkout fitWorkout : list) {
            hashMap.put(fitWorkout.acquireId(), fitWorkout);
            if (i == 2 && (num = c2.get(fitWorkout.acquireId())) != null && num.intValue() == CourseDataBean.PlanCourseType.RUN_COURSE.getValue()) {
                dVar.c(this.f12817a.getCourseData(fitWorkout, 1), CourseDataBean.PlanCourseType.RUN_COURSE.getValue());
            }
        }
        Iterator<String> it = c2.keySet().iterator();
        while (it.hasNext()) {
            if (hashMap.get(it.next()) == null) {
                ReleaseLogUtil.c("Suggestion_CoursePlanDeviceManager", "relative courseId not has workout.");
                throw new cbe(-101, "relative courseId not has workout.");
            }
        }
        byte[] planBasicData = this.f12817a.getPlanBasicData(intPlan, hashMap);
        if (planBasicData == null) {
            ReleaseLogUtil.d("Suggestion_CoursePlanDeviceManager", "getPlanDataMsgBuilder planData null");
            return null;
        }
        dVar.e(planBasicData);
        ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "getPlanDataMsgBuilder logTag ", str);
        String str2 = i2 + this.f12817a.getPlanFileName();
        b(str2, dVar.c().a());
        spn.b bVar = new spn.b();
        bVar.a(new File(d(str2)));
        return bVar;
    }

    public final void e(DeviceStateListener deviceStateListener) {
        if (deviceStateListener == null) {
            ReleaseLogUtil.c("Suggestion_CoursePlanDeviceManager", "registerDeviceStateListener failed with null params");
        }
        this.i.registerDeviceStateListener(deviceStateListener);
    }

    private Map<String, Integer> c(IntWeekPlan intWeekPlan) {
        if (intWeekPlan == null) {
            return new LinkedHashMap();
        }
        int dayCount = intWeekPlan.getDayCount();
        LinkedHashMap linkedHashMap = new LinkedHashMap(dayCount);
        for (int i = 0; i < dayCount; i++) {
            IntDayPlan dayByIdx = intWeekPlan.getDayByIdx(i);
            if (dayByIdx != null) {
                for (int i2 = 0; i2 < dayByIdx.getInPlanActionCnt(); i2++) {
                    IntAction inPlanAction = dayByIdx.getInPlanAction(i2);
                    if (inPlanAction != null && !"Race".equals(inPlanAction.getActionId())) {
                        linkedHashMap.put(inPlanAction.getActionId(), Integer.valueOf(inPlanAction.getActionType().getType()));
                    }
                }
            }
        }
        return linkedHashMap;
    }

    private void b(String str, byte[]... bArr) throws cbe {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = this.d.openFileOutput(str, 0);
                for (byte[] bArr2 : bArr) {
                    fileOutputStream.write(bArr2);
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b("Suggestion_CoursePlanDeviceManager", "writefile finally failed IOException");
                    }
                }
            } catch (Exception e2) {
                LogUtil.b("Suggestion_CoursePlanDeviceManager", "writefile failed", ExceptionUtils.d(e2));
                throw new cbe(-102, "write file failed");
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused2) {
                    LogUtil.b("Suggestion_CoursePlanDeviceManager", "writefile finally failed IOException");
                }
            }
            throw th;
        }
    }

    private String d(String str) {
        return this.d.getFilesDir() + File.separator + str;
    }

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onDisconnected() {
        ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "the device disconnected.");
        this.g = false;
    }

    @Override // com.huawei.health.basesport.wearengine.DeviceStateListener
    public void onConnected() {
        b();
        if (this.g) {
            ReleaseLogUtil.e("Suggestion_CoursePlanDeviceManager", "the device support intelligent run plan.");
        } else {
            ReleaseLogUtil.d("Suggestion_CoursePlanDeviceManager", "the device not support intelligent run plan.");
        }
    }

    private void b() {
        this.g = gij.d();
    }

    public static boolean d(int i) {
        if (i == IntPlan.PlanType.AI_RUN_PLAN.getType() && gij.d()) {
            return true;
        }
        return i == IntPlan.PlanType.AI_FITNESS_PLAN.getType() && gij.e();
    }
}
