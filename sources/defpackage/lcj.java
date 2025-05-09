package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;

/* loaded from: classes5.dex */
public class lcj extends BaseRealTimeDynamicChartViewModel {
    public lcj(RealTimeDynamicChartView realTimeDynamicChartView) {
        super(realTimeDynamicChartView);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void pushNewData(int i) {
        if (i > 30) {
            i = 30;
        }
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("Track_GroundImpactViewModel", "pushNewData() ", "mRealTimeDynamicChartView is null");
            return;
        }
        double d = i;
        this.mRealTimeDynamicChartView.setValue(lbv.e(d, 1, 0));
        LogUtil.a("Track_GroundImpactViewModel", "pushNewData", lbv.e(d, 1, 0));
        super.pushNewData(i);
        if (lbv.b("ground impact", i, this.mCurrentSpeed) == 2) {
            this.mRealTimeDynamicChartView.e();
        } else {
            this.mRealTimeDynamicChartView.a();
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void updateConfiguration(Context context) {
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("Track_GroundImpactViewModel", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
        } else {
            super.updateConfiguration(context);
            setChartViewTitle(context.getString(R.string._2130842716_res_0x7f02145c), context.getString(R.string._2130840277_res_0x7f020ad5));
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 30);
        }
    }
}
