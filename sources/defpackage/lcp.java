package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;
import health.compact.a.UnitUtil;

/* loaded from: classes5.dex */
public class lcp extends BaseRealTimeDynamicChartViewModel {
    public lcp(RealTimeDynamicChartView realTimeDynamicChartView) {
        super(realTimeDynamicChartView);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void pushNewData(int i) {
        String e;
        float f = i;
        float d = UnitUtil.h() ? (int) UnitUtil.d(f / 5.0f, 2) : f;
        if (i > 0) {
            e = gvv.a(d);
        } else {
            e = gvv.e(BaseApplication.getContext());
        }
        this.mRealTimeDynamicChartView.setValue(e);
        super.pushNewData(i > 0 ? 5000.0f / f : 0.0f);
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void updateConfiguration(Context context) {
        if (this.mRealTimeDynamicChartView == null) {
            LogUtil.b("PaceViewModel", "updateConfiguration() ", "mRealTimeDynamicChartView is null");
            return;
        }
        super.updateConfiguration(context);
        this.mRealTimeDynamicChartView.setTitle(R.string._2130844083_res_0x7f0219b3);
        LogUtil.a("PaceViewModel", "Pace setUnit ");
        if (UnitUtil.h()) {
            LogUtil.a("PaceViewModel", "Pace is :100yards");
            setChartViewTitle(String.format(context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100), new Object[0]), context.getString(R.string._2130844083_res_0x7f0219b3));
        } else {
            setChartViewTitle(String.format(context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500), new Object[0]), context.getString(R.string._2130844083_res_0x7f0219b3));
        }
    }

    @Override // com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel
    public void setDefaultOrdinateY() {
        if (this.mRealTimeDynamicChartView != null) {
            this.mRealTimeDynamicChartView.setOrdinateY(0, 50);
        }
    }
}
