package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class gxo extends AbstractTrackSummaryData {
    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public int getSportTypeDrawableSource(boolean z) {
        return z ? R.drawable.ic_health_list_outdoor_colours_swim : R.drawable.ic_health_list_outdoor_swim;
    }

    public gxo(MotionPathSimplify motionPathSimplify) {
        super(motionPathSimplify);
    }

    public gxo(RelativeSportData relativeSportData) {
        super(relativeSportData);
    }

    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public String getChiefDataString() {
        if (this.mChiefDataValue < 1) {
            return gvv.e(BaseApplication.getContext());
        }
        return super.getChiefDataString();
    }

    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public String getChiefDataUnitString() {
        Context context = BaseApplication.getContext();
        if (context == null) {
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, this.mChiefDataValue);
        }
        return context.getString(R.string._2130841568_res_0x7f020fe0);
    }

    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public String getDistanceString() {
        if (UnitUtil.h()) {
            return getNumberFormat(UnitUtil.e(this.mChiefDataValue, 2), 0);
        }
        return getNumberFormat(this.mChiefDataValue, 0);
    }

    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public String getPaceString() {
        float f = this.mAlterData / 10.0f;
        double d = f;
        if (d > 360000.0d || d <= 3.6d) {
            return gvv.e(BaseApplication.getContext());
        }
        if (UnitUtil.h()) {
            f = (float) UnitUtil.d(d, 2);
        }
        return gvv.a(f);
    }

    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public String getPaceUnitString() {
        Context context = BaseApplication.getContext();
        if (context == null) {
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
        }
        return context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
    }
}
