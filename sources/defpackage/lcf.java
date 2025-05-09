package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;

/* loaded from: classes5.dex */
public class lcf extends BaseRealTimeDynamicChartViewModel {
    public lcf(RealTimeDynamicChartView realTimeDynamicChartView) {
        super(realTimeDynamicChartView);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void pushNewData(int i) {
        String e;
        if (i > 0) {
            e = lbv.e(i, 1, 0);
        } else {
            e = gvv.e(BaseApplication.getContext());
        }
        this.mRealTimeDynamicChartView.setValue(e);
        super.pushNewData(i);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void updateConfiguration(Context context) {
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("CadenceViewModel", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
        } else {
            super.updateConfiguration(context);
            setChartViewTitle(context.getString(R.string._2130843487_res_0x7f02175f), context.getString(R.string._2130843486_res_0x7f02175e));
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 200);
        }
    }
}
