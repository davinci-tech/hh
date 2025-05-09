package defpackage;

import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;

/* loaded from: classes4.dex */
public class gxl extends AbstractTrackSummaryData {
    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public int getSportTypeDrawableSource(boolean z) {
        return z ? R.drawable.ic_health_list_colours_bike : R.drawable.ic_health_list_bike;
    }

    public gxl(MotionPathSimplify motionPathSimplify) {
        super(motionPathSimplify);
    }

    public gxl(RelativeSportData relativeSportData) {
        super(relativeSportData);
    }

    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public String getAlterDataString() {
        return getSpeedString();
    }

    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public String getAlterUnitString() {
        return getSpeedUnitString();
    }
}
