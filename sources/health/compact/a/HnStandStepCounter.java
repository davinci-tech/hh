package health.compact.a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.hihonor.health.fw.IHwStepCounterManager;
import com.huawei.health.fw.IReportCallback;
import com.huawei.health.fwk.BaseStepCounter;

/* loaded from: classes.dex */
public class HnStandStepCounter extends BaseStepCounter {
    private IHwStepCounterManager c;

    @Override // com.huawei.health.fwk.BaseStepCounter
    public boolean getHistoryData(long j, long j2, IReportCallback iReportCallback) {
        return false;
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public void registerDataCallback(IReportCallback iReportCallback) {
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public void registerLogCallback(IReportCallback iReportCallback) {
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public void unRegisterDataCallback(IReportCallback iReportCallback) {
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public void unRegisterLogCallback(IReportCallback iReportCallback) {
    }

    public HnStandStepCounter(IBinder iBinder) {
        this.c = IHwStepCounterManager.Stub.asInterface(iBinder);
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public String getVersion() {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                return iHwStepCounterManager.getVersion();
            }
            return null;
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "getVersion RemoteException");
            return null;
        }
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public int[] getAbility() {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                return iHwStepCounterManager.getAbility();
            }
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "getAbility RemoteException");
        }
        return new int[0];
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public int openStepCounter() {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                return iHwStepCounterManager.openStepCounter();
            }
            return 0;
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "openStepCounter RemoteException");
            return 0;
        }
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public int closeStepCounter() {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                return iHwStepCounterManager.closeStepCounter();
            }
            return 0;
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "closeStepCounter RemoteException");
            return 0;
        }
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public void unRegisterDataCallback(com.hihonor.health.fw.IReportCallback iReportCallback) {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                iHwStepCounterManager.unRegisterDataCallback(iReportCallback);
            }
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "unRegisterDataCallback RemoteException");
        }
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public void registerDataCallback(com.hihonor.health.fw.IReportCallback iReportCallback) {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                iHwStepCounterManager.registerDataCallback(iReportCallback);
            }
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "registerDataCallback RemoteException");
        }
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public boolean insertDiffStep(Bundle bundle) {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                return iHwStepCounterManager.insertDiffStep(bundle);
            }
            return false;
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "insertDiffStep RemoteException");
            return false;
        }
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public boolean getHistoryData(long j, long j2, com.hihonor.health.fw.IReportCallback iReportCallback) {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                return iHwStepCounterManager.getHistoryData(j, j2, iReportCallback);
            }
            return false;
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "getHistoryData RemoteException");
            return false;
        }
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public void registerLogCallback(com.hihonor.health.fw.IReportCallback iReportCallback) {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                iHwStepCounterManager.registerLogCallback(iReportCallback);
                LogUtil.c("Step_HwStandStepCounter", "registerLogCallback end ");
            }
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "registerLogCallback RemoteException");
        }
    }

    @Override // com.huawei.health.fwk.BaseStepCounter
    public void unRegisterLogCallback(com.hihonor.health.fw.IReportCallback iReportCallback) {
        try {
            IHwStepCounterManager iHwStepCounterManager = this.c;
            if (iHwStepCounterManager != null) {
                iHwStepCounterManager.unRegisterLogCallback(iReportCallback);
            }
        } catch (RemoteException unused) {
            LogUtil.e("Step_HwStandStepCounter", "unRegisterLogCallback RemoteException");
        }
    }
}
