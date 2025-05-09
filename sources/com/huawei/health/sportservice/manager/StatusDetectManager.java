package com.huawei.health.sportservice.manager;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fgm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.STATUS_DETECT_MANAGER)
/* loaded from: classes8.dex */
public class StatusDetectManager implements ManagerComponent {
    private static final String TAG = "SportService_StatusDetectManager";
    private int mMachineStatus;
    private int mOpCode;
    private fgm mSportCallbackOption = new fgm();
    private int mTrainingStatus;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("OP_CODE_DATA");
        arrayList.add("MACHINE_STATUS_DATA");
        arrayList.add("TRAINING_STATUS_DATA");
        if (BaseSportManager.getInstance().getSportType() == 283) {
            arrayList.add("ROPE_TRAINING_STATUS_DATA");
        }
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.StatusDetectManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                StatusDetectManager.this.m473x7cd1354e(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-StatusDetectManager, reason: not valid java name */
    /* synthetic */ void m473x7cd1354e(List list, Map map) {
        int status = BaseSportManager.getInstance().getStatus();
        if (list.contains("ROPE_TRAINING_STATUS_DATA")) {
            getSkipStatus(status, ((Integer) map.get("ROPE_TRAINING_STATUS_DATA")).intValue());
        }
        if (list.contains("OP_CODE_DATA")) {
            this.mOpCode = ((Integer) map.get("OP_CODE_DATA")).intValue();
        } else if (list.contains("MACHINE_STATUS_DATA")) {
            this.mMachineStatus = ((Integer) map.get("MACHINE_STATUS_DATA")).intValue();
        } else if (list.contains("TRAINING_STATUS_DATA")) {
            this.mTrainingStatus = ((Integer) map.get("TRAINING_STATUS_DATA")).intValue();
        }
        getStatus(status);
    }

    private void getSkipStatus(int i, int i2) {
        if (i == 1) {
            if (i2 == 2) {
                BaseSportManager.getInstance().onPauseSport();
                LogUtil.a(TAG, "skip onPauseSport()");
                return;
            } else {
                if (i2 == 3) {
                    BaseSportManager.getInstance().onStopSport();
                    LogUtil.a(TAG, "getSkipStatus onPauseSport()");
                    return;
                }
                return;
            }
        }
        if (i == 2) {
            if (i2 == 1) {
                BaseSportManager.getInstance().onResumeSport();
                LogUtil.a(TAG, "skip onResumeSport()");
            } else if (i2 == 3) {
                BaseSportManager.getInstance().onStopSport();
                LogUtil.a(TAG, "skip onResumeSport()");
            }
        }
    }

    private void getStatus(int i) {
        LogUtil.a(TAG, "getStatus() sportStatus: ", Integer.valueOf(i), ", mOpCode: ", Integer.valueOf(this.mOpCode), ", mMachineStatus: ", Integer.valueOf(this.mMachineStatus), ", mTrainingStatus: ", Integer.valueOf(this.mTrainingStatus));
        int i2 = this.mOpCode;
        if (i2 == 2 && this.mMachineStatus == 2) {
            if (i == 1) {
                BaseSportManager.getInstance().onPauseSport();
                LogUtil.a(TAG, "invoke BaseSportManager.getInstance().onPauseSport()");
                return;
            }
            return;
        }
        if (i2 == 4 && this.mTrainingStatus == 13) {
            if (i == 7 || i == 0) {
                BaseSportManager.getInstance().onStartSport();
                LogUtil.a(TAG, "invoke BaseSportManager.getInstance().onStartSport()");
            } else if (i == 2) {
                BaseSportManager.getInstance().onResumeSport();
                LogUtil.a(TAG, "invoke BaseSportManager.getInstance().onResumeSport()");
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }
}
