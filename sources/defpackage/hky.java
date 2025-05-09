package defpackage;

import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class hky {
    private static final String d = "Track_PreprocessorPicker";

    public static MapDataPreprocessor c(List<MotionData> list, MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify.requestSportType() == 512) {
            return new hkw(list, motionPathSimplify);
        }
        for (MotionData motionData : list) {
            if (koq.c(motionData.requestMarkPointList())) {
                LogUtil.a(d, "pick MarkerPointPreprocessor");
                return new hkz(list, motionPathSimplify);
            }
            if (motionData.getPaceMap() != null && motionData.getPaceMap().size() > 0) {
                LogUtil.a(d, "pick PaceMapLineDataPreprocessor");
                return new hkv(list, motionPathSimplify);
            }
            if (!koq.b(motionData.requestSegmentList())) {
                LogUtil.a(d, "pick SegmentLineDataPreprocessor");
                return new hlb(list, motionPathSimplify);
            }
        }
        LogUtil.a(d, "pick LineDataPreprocessor");
        return new hkw(list, motionPathSimplify);
    }
}
