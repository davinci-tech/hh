package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;

/* loaded from: classes5.dex */
public class lcw extends BaseRealTimeDynamicChartViewModel {
    public lcw(RealTimeDynamicChartView realTimeDynamicChartView) {
        super(realTimeDynamicChartView);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void pushNewData(int i) {
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("StepFrequencyViewModel", "pushNewData() ", "mRealTimeDynamicChartView is null");
            return;
        }
        int i2 = i <= 270 ? i : 270;
        if (i2 <= 0) {
            this.mRealTimeDynamicChartView.setValue(BaseApplication.getContext().getString(R.string._2130851304_res_0x7f0235e8));
        } else {
            this.mRealTimeDynamicChartView.setValue(lbv.e(i2, 1, 0));
        }
        super.pushNewData(i);
        if (lbv.b("step frequency", i2, this.mCurrentSpeed) == 1) {
            this.mRealTimeDynamicChartView.d();
        } else {
            this.mRealTimeDynamicChartView.a();
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void updateConfiguration(Context context) {
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("StepFrequencyViewModel", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
        } else {
            super.updateConfiguration(context);
            this.mRealTimeDynamicChartView.setTitle(R.string._2130845058_res_0x7f021d82);
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 270);
        }
    }
}
