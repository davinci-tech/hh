package defpackage;

import com.huawei.health.R;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;

/* loaded from: classes4.dex */
public class gxm extends gxr {
    @Override // com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData
    public int getSportTypeDrawableSource(boolean z) {
        if (z) {
            return 0;
        }
        return R.drawable.ic_health_indoorrunning;
    }

    public gxm(MotionPathSimplify motionPathSimplify) {
        super(motionPathSimplify);
    }

    public gxm(RelativeSportData relativeSportData) {
        super(relativeSportData);
    }
}
