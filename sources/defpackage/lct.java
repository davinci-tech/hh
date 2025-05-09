package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;
import health.compact.a.UnitUtil;

/* loaded from: classes5.dex */
public class lct extends BaseRealTimeDynamicChartViewModel {
    public lct(RealTimeDynamicChartView realTimeDynamicChartView) {
        super(realTimeDynamicChartView);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void pushNewData(float f) {
        String e;
        float e2 = UnitUtil.h() ? (float) UnitUtil.e(f, 3) : f;
        if (f > 0.0f) {
            e = lbv.e(e2, 1, 1);
        } else {
            e = gvv.e(BaseApplication.getContext());
        }
        this.mRealTimeDynamicChartView.setValue(e);
        super.pushNewData(f);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void updateConfiguration(Context context) {
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("SpeedViewModel", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
            return;
        }
        super.updateConfiguration(context);
        if (UnitUtil.h()) {
            setChartViewTitle(context.getString(R.string._2130844079_res_0x7f0219af), context.getString(R.string._2130844076_res_0x7f0219ac));
        } else {
            setChartViewTitle(context.getString(R.string._2130844078_res_0x7f0219ae), context.getString(R.string._2130844076_res_0x7f0219ac));
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 12);
        }
    }
}
