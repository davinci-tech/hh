package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;

/* loaded from: classes5.dex */
public class lci extends BaseRealTimeDynamicChartViewModel {
    public lci(RealTimeDynamicChartView realTimeDynamicChartView) {
        super(realTimeDynamicChartView);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void pushNewData(int i) {
        int i2 = i <= 1200 ? i : 1200;
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("GroundContactTimeViewModel", "pushNewData() ", "mRealTimeDynamicChartView is null");
            return;
        }
        double d = i2;
        this.mRealTimeDynamicChartView.setValue(lbv.e(d, 1, 0));
        LogUtil.a("GroundContactTimeViewModel", "pushNewData", lbv.e(d, 1, 0));
        super.pushNewData(i);
        if (lbv.b("touch time", i2, this.mCurrentSpeed) == 2) {
            this.mRealTimeDynamicChartView.e();
        } else {
            this.mRealTimeDynamicChartView.a();
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void updateConfiguration(Context context) {
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("GroundContactTimeViewModel", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
        } else {
            super.updateConfiguration(context);
            setChartViewTitle(context.getResources().getQuantityString(R.plurals._2130903252_res_0x7f0300d4, 2, ""), context.getString(R.string._2130840278_res_0x7f020ad6));
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 1200);
        }
    }
}
