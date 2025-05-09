package com.huawei.health.sportservice.dataproducer;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import defpackage.cas;
import defpackage.ffd;
import defpackage.gwk;
import defpackage.gwo;
import health.compact.a.util.LogUtil;
import java.util.Iterator;

@SportComponentType(classify = 1, name = ComponentName.DATA_STORAGE_MANAGER)
/* loaded from: classes4.dex */
public class TargetSportDataStorageManager extends SportDataStorageManager implements ITargetUpdateListener {
    private static final String TAG = "TargetSportDataStorageManager";
    private static final String TARGET_STATUS = "TARGET_STATUS";
    private int mTargetType = -1;
    private float mTargetValue = 0.0f;

    public TargetSportDataStorageManager() {
        LogUtil.d(TAG, "TargetSportDataStorageManager()");
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        super.setParas(sportParamsType, obj);
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) && (obj instanceof SportLaunchParams)) {
            SportLaunchParams sportLaunchParams = (SportLaunchParams) obj;
            this.mTargetType = sportLaunchParams.getSportTarget();
            this.mTargetValue = sportLaunchParams.getTargetValue();
        }
        LogUtil.d(TAG, "setParas");
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        super.onStartSport();
        LogUtil.d(TAG, "onStartSport");
        BaseSportManager.getInstance().subscribeTargetStatus(TAG, this);
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.d(TAG, "onStopSport");
        this.mIsNeedSaveToFile = false;
        saveTargetDataToDatabase();
        unregBoltWearAbnormalStatus();
    }

    private void saveTargetDataToDatabase() {
        int targetSportStatus = BaseSportManager.getInstance().getTargetSportStatus();
        LogUtil.d(TAG, "targetSportStatus = ", Integer.valueOf(targetSportStatus));
        MotionPathSimplify savedSimplify = getSavedSimplify(targetSportStatus);
        MotionPath savedMotionPath = getSavedMotionPath(targetSportStatus);
        if (savedMotionPath == null && savedSimplify == null) {
            LogUtil.d(TAG, "motionPath == null && simplify == null");
            notifySportDataNotSaved();
        } else {
            saveDataToDatabase(savedSimplify, savedMotionPath);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        super.destroy();
        LogUtil.d(TAG, "destroy");
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
        LogUtil.d(TAG, "onTargetDataUpdate curProgress ", Integer.valueOf((int) (ffdVar.a() * 100.0f)));
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i, String str) {
        LogUtil.d(TAG, "type is ", Integer.valueOf(i), " command is ", str);
        if (i == 200) {
            targetFinishedSave();
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager
    public void stagingData(String str, Object obj) {
        super.stagingData(str, obj);
        if ("ONLY_STAGE_TARGET_SPORT_DATA".equals(str)) {
            Iterator<BaseProducer> it = this.mProducerMap.values().iterator();
            while (it.hasNext()) {
                it.next().onSaveData();
            }
            LogUtil.d(TAG, "stagingData, ONLY_STAGE_TARGET_SPORT_DATA ");
        }
    }

    private void targetFinishedSave() {
        int targetSportStatus = BaseSportManager.getInstance().getTargetSportStatus();
        if (targetSportStatus == 1 || targetSportStatus == 2) {
            return;
        }
        stagingData("ONLY_STAGE_TARGET_SPORT_DATA", null);
        correctSimplifyAndMotionPath();
        stagingTargetData();
        SportLaunchParams e = cas.e();
        if (e != null) {
            e.addExtra(TARGET_STATUS, 1);
            cas.b(e);
        }
        LogUtil.d(TAG, "targetFinishedSave");
    }

    private void correctSimplifyAndMotionPath() {
        LogUtil.d(TAG, "correctSimplifyAndMotionPath");
        correctTargetDate();
        stagingData("MOTION_PATH_SIMPLIFY_DATA", this.mMotionPathSimplify);
    }

    private void correctTargetDate() {
        int i = this.mTargetType;
        if (i == 0) {
            this.mMotionPathSimplify.saveTotalTime(((long) this.mTargetValue) * 1000);
            return;
        }
        if (i == 1) {
            this.mMotionPathSimplify.saveTotalDistance((int) this.mTargetValue);
        } else if (i == 2) {
            this.mMotionPathSimplify.saveTotalCalories(((int) this.mTargetValue) * 1000);
        } else {
            LogUtil.d(TAG, "no need to correct target data");
        }
    }

    public void stagingTargetData() {
        LogUtil.d(TAG, "stagingTargetData");
        gwo.e(BaseApplication.e(), "device_target_motion_simplify.txt");
        gwo.e(BaseApplication.e(), "device_target_motion_path.txt");
        if (!isToSave()) {
            LogUtil.d(TAG, "stagingTargetData: the storage condition is not met");
            return;
        }
        LogUtil.d(TAG, "saveSimpleSuccess is ", Boolean.valueOf(gwo.a(BaseApplication.e(), this.mMotionPathSimplify, "device_target_motion_simplify.txt")), " saveMotionSuccess is ", Boolean.valueOf(gwo.a(BaseApplication.e(), this.mMotionPath, "device_target_motion_path.txt")));
    }

    private MotionPathSimplify getSavedSimplify(int i) {
        if (i == 1) {
            return gwk.e(BaseApplication.e(), "device_target_motion_simplify.txt");
        }
        if (i == 2) {
            return null;
        }
        return this.mMotionPathSimplify;
    }

    private MotionPath getSavedMotionPath(int i) {
        if (i == 1) {
            return gwk.c(BaseApplication.e(), "device_target_motion_path.txt", this.mMotionPathSimplify.requestSportType());
        }
        if (i == 2) {
            return null;
        }
        return this.mMotionPath;
    }
}
