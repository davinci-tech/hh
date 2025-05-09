package defpackage;

import android.os.Bundle;
import com.huawei.health.ui.notification.common.IReporter;
import health.compact.a.LogAnonymous;
import health.compact.a.StepsRecord;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class gsn extends IReporter {
    private gsj c;

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void languageChanged() {
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStart(Bundle bundle) {
        super.onStart(bundle);
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void refresh(StepsRecord stepsRecord) {
        if (stepsRecord != null && stepsRecord.g == 0) {
            gsj gsjVar = this.c;
            if (gsjVar != null) {
                gsjVar.b(0);
                return;
            }
            return;
        }
        try {
            if (this.c == null) {
                gsj gsjVar2 = new gsj();
                this.c = gsjVar2;
                gsjVar2.e();
            }
            gsj gsjVar3 = this.c;
            if (gsjVar3 == null || stepsRecord == null) {
                return;
            }
            gsjVar3.b(stepsRecord.g);
        } catch (Exception e) {
            ReleaseLogUtil.c("R_StepForMmReporter", "refresh exception,", LogAnonymous.b((Throwable) e));
        }
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStop() {
        super.onStop();
        try {
            gsj gsjVar = this.c;
            if (gsjVar != null) {
                gsjVar.a();
            }
        } catch (Exception e) {
            ReleaseLogUtil.c("R_StepForMmReporter", "onStop exception,", LogAnonymous.b((Throwable) e));
        }
    }
}
