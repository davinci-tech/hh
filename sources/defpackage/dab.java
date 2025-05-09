package defpackage;

import android.os.Bundle;
import android.util.SparseArray;
import com.huawei.dnurse.sdk.DnurseDeviceTest;
import com.huawei.dnurse.sdk.IMeasureDataResultCallback;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class dab extends MeasureController {

    /* renamed from: a, reason: collision with root package name */
    private DnurseDeviceTest f11565a;
    private HealthDevice b;
    private int c = -1;
    private IMeasureDataResultCallback d = new IMeasureDataResultCallback() { // from class: dab.5
        @Override // com.huawei.dnurse.sdk.IMeasureDataResultCallback
        public void onSuccess(SparseArray sparseArray) {
            float floatValue = ((Float) sparseArray.get(1)).floatValue();
            ReleaseLogUtil.e("DEVMGR_EcologyDevice_DnurseMeasureController", "mMeasureDataResultCallback onSuccess, result = ", Float.valueOf(floatValue));
            deb debVar = new deb();
            debVar.setBloodSugar(floatValue);
            Calendar calendar = sparseArray.get(2) instanceof Calendar ? (Calendar) sparseArray.get(2) : null;
            if (calendar != null) {
                LogUtil.c("DnurseMeasureController", "onSuccess mTime = ", Long.valueOf(calendar.getTimeInMillis()));
                debVar.setStartTime(calendar.getTimeInMillis());
                debVar.setEndTime(calendar.getTimeInMillis());
            }
            debVar.setSequenceNumber(0);
            if (dab.this.e != null) {
                dab.this.e.onDataChanged(dab.this.b, debVar);
            }
            dab.this.c = 8;
        }

        @Override // com.huawei.dnurse.sdk.IMeasureDataResultCallback
        public void onMeasuring(int i, int i2) {
            LogUtil.a("DnurseMeasureController", "Enter onMeasuring, status=", Integer.valueOf(i));
            if (dab.this.c != i) {
                dab.this.c = i;
                if (dab.this.b instanceof cfa) {
                    ((cfa) dab.this.b).d(dab.this.f11565a.getDeviceSn());
                }
                if (dab.this.e == null || i == 8) {
                    return;
                }
                dab.this.e.onStatusChanged(dab.this.b, i);
            }
        }
    };
    private IHealthDeviceCallback e;

    public dab() {
        LogUtil.a("DnurseMeasureController", "Enter constructor");
        this.f11565a = DnurseDeviceTest.getInstance(cpp.a());
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("DnurseMeasureController", "Enter prepare");
        this.b = healthDevice;
        this.e = iHealthDeviceCallback;
        return true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        ReleaseLogUtil.e("DEVMGR_EcologyDevice_DnurseMeasureController", "Enter start, mStatus=", Integer.valueOf(this.c));
        int i = this.c;
        if (i == 9) {
            this.f11565a.wakeupDevice();
        } else if (i < 0) {
            this.f11565a.startTest(this.d);
        } else {
            this.e.onStatusChanged(this.b, i);
        }
        dew.b(this.b.getAddress(), 1);
        return true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void ending() {
        LogUtil.a("DnurseMeasureController", "Enter ending");
        this.c = -1;
        this.f11565a.stopTest();
        dew.b(this.b.getAddress(), 0);
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        LogUtil.a("DnurseMeasureController", "Enter cleanup");
        this.e = null;
        this.b = null;
        this.f11565a = null;
    }
}
