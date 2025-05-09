package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;

/* loaded from: classes5.dex */
public class lcs extends BaseRealTimeDynamicChartViewModel {
    private final String b;

    public lcs(RealTimeDynamicChartView realTimeDynamicChartView, String str) {
        super(realTimeDynamicChartView);
        this.b = str;
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void pushNewData(float f) {
        String e;
        if (f > 0.0f) {
            e = lbv.e(f, 1, 0);
        } else {
            e = gvv.e(BaseApplication.getContext());
        }
        this.mRealTimeDynamicChartView.setValue(e);
        super.pushNewData(f);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void updateConfiguration(Context context) {
        String string;
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("PaddleFrequencyViewModle", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
            return;
        }
        super.updateConfiguration(context);
        String string2 = context.getString(R.string._2130843497_res_0x7f021769);
        if (this.b.equals("291")) {
            string = context.getString(R.string._2130845951_res_0x7f0220ff);
        } else {
            string = context.getString(R.string._2130843496_res_0x7f021768);
        }
        setChartViewTitle(string2, string);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 60);
        }
    }
}
