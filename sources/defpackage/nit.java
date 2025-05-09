package defpackage;

import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nit {
    private static int b(int i) {
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 1;
        }
        if (i != 4) {
            return i != 8 ? 0 : 4;
        }
        return 3;
    }

    private static int d(int i) {
        return (i == 1 || i != 2) ? 3 : 4;
    }

    public static void d(MotionGoal motionGoal, final IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(b(motionGoal));
        HiHealthManager.d(BaseApplication.getContext()).setGoalInfo(0, arrayList, new HiCommonListener() { // from class: nit.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                IBaseResponseCallback.this.d(0, null);
                CommonUtil.am(BaseApplication.getContext());
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                IBaseResponseCallback.this.d(100001, null);
            }
        });
    }

    public static void b(final MotionGoal motionGoal, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).fetchGoalInfo(0, 0, new HiCommonListener() { // from class: nit.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (koq.e(obj, HiGoalInfo.class)) {
                    iBaseResponseCallback.d(0, nit.a(MotionGoal.this, (List<HiGoalInfo>) obj));
                    return;
                }
                iBaseResponseCallback.d(100001, null);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                iBaseResponseCallback.d(100001, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MotionGoal a(MotionGoal motionGoal, List<HiGoalInfo> list) {
        MotionGoal motionGoal2 = new MotionGoal();
        motionGoal2.setGoalType(motionGoal.getGoalType());
        for (HiGoalInfo hiGoalInfo : list) {
            if (hiGoalInfo.getGoalType() == 2) {
                int goalValue = (int) hiGoalInfo.getGoalValue();
                if (goalValue < 2000) {
                    motionGoal2.setStepGoal(2000);
                    a(motionGoal2);
                } else {
                    motionGoal2.setStepGoal(goalValue);
                }
            } else if (hiGoalInfo.getGoalType() == 1) {
                motionGoal2.setCalorieGoal((int) hiGoalInfo.getGoalValue());
            } else if (hiGoalInfo.getGoalType() == 3) {
                motionGoal2.setDistanceGoal((int) hiGoalInfo.getGoalValue());
            } else if (hiGoalInfo.getGoalType() == 4) {
                motionGoal2.setDutationGoal((int) hiGoalInfo.getGoalValue());
            }
        }
        return motionGoal2;
    }

    private static void a(MotionGoal motionGoal) {
        d(motionGoal, new IBaseResponseCallback() { // from class: nit.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
            }
        });
    }

    private static List<HiGoalInfo> b(MotionGoal motionGoal) {
        ArrayList arrayList = new ArrayList();
        if (motionGoal.getCurrValue() != -1) {
            arrayList.add(e(b(motionGoal.getDataType()), d(motionGoal.getGoalType()), motionGoal.getCurrValue()));
        } else {
            if (motionGoal.getStepGoal() > 0) {
                arrayList.add(e(2, d(motionGoal.getGoalType()), motionGoal.getStepGoal()));
            }
            if (motionGoal.getCalorieGoal() > 0) {
                arrayList.add(e(1, d(motionGoal.getGoalType()), motionGoal.getCalorieGoal()));
            }
            if (motionGoal.getDistanceGoal() > 0) {
                arrayList.add(e(3, d(motionGoal.getGoalType()), motionGoal.getDistanceGoal()));
            }
            if (motionGoal.getDutationGoal() > 0) {
                arrayList.add(e(4, d(motionGoal.getGoalType()), motionGoal.getDutationGoal()));
            }
        }
        return arrayList;
    }

    private static HiGoalInfo e(int i, int i2, int i3) {
        HiGoalInfo hiGoalInfo = new HiGoalInfo();
        hiGoalInfo.setGoalType(i);
        hiGoalInfo.setGoalUnit(i2);
        hiGoalInfo.setGoalValue(i3);
        return hiGoalInfo;
    }

    public static void a(int i, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("FitnessGoalUtil", "saveStepGoal2MotionGoal callback is null");
            return;
        }
        HiGoalInfo hiGoalInfo = new HiGoalInfo();
        hiGoalInfo.setGoalType(2);
        hiGoalInfo.setGoalUnit(3);
        hiGoalInfo.setGoalValue(i);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(hiGoalInfo);
        HiHealthManager.d(BaseApplication.getContext()).setGoalInfo(0, arrayList, new HiCommonListener() { // from class: nit.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i2, Object obj) {
                IBaseResponseCallback.this.d(0, null);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i2, Object obj) {
                IBaseResponseCallback.this.d(-1, null);
            }
        });
    }
}
