package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;

/* loaded from: classes5.dex */
public class lcr extends BaseRealTimeDynamicChartViewModel {
    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setOrdinateY(int i, int i2) {
    }

    public lcr(RealTimeDynamicChartView realTimeDynamicChartView) {
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
            LogUtil.b("PowerViewModel", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
        } else {
            super.updateConfiguration(context);
            setChartViewTitle(context.getString(R.string._2130843492_res_0x7f021764), context.getString(R.string._2130843491_res_0x7f021763));
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 250);
        }
    }
}
