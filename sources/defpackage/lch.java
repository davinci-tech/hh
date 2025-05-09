package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;

/* loaded from: classes5.dex */
public class lch extends BaseRealTimeDynamicChartViewModel {
    public lch(RealTimeDynamicChartView realTimeDynamicChartView) {
        super(realTimeDynamicChartView);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void pushNewData(float f) {
        if (f > 30.0f) {
            f = 30.0f;
        }
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("GroundShockPeakViewModel", "pushNewData() ", "mRealTimeDynamicChartView is null");
            return;
        }
        this.mRealTimeDynamicChartView.setValue(lbv.e(f, 1, 1));
        super.pushNewData(f);
        if (lbv.b("ground shock peak", (int) f, this.mCurrentSpeed) == 2) {
            this.mRealTimeDynamicChartView.e();
        } else {
            this.mRealTimeDynamicChartView.a();
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void updateConfiguration(Context context) {
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("GroundShockPeakViewModel", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
            return;
        }
        super.updateConfiguration(context);
        this.mRealTimeDynamicChartView.setValue(lbv.e(0.0d, 1, 1));
        setChartViewTitle(context.getString(R.string._2130845180_res_0x7f021dfc), context.getString(R.string._2130845176_res_0x7f021df8));
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 30);
        }
    }
}
