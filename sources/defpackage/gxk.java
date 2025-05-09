package defpackage;

import com.huawei.health.R;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;

/* loaded from: classes4.dex */
public class gxk extends gxl {
    @Override // defpackage.gxl, com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public int getSportTypeDrawableSource(boolean z) {
        if (z) {
            return 0;
        }
        return R.drawable.ic_health_list_indoor_bike;
    }

    public gxk(MotionPathSimplify motionPathSimplify) {
        super(motionPathSimplify);
    }

    public gxk(RelativeSportData relativeSportData) {
        super(relativeSportData);
    }
}
