package defpackage;

import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class hkz extends hkw implements MapDataPreprocessor {

    /* renamed from: a, reason: collision with root package name */
    private static final String f13229a = "Track_MarkerPointPreprocessor";

    public hkz(List<MotionData> list, MotionPathSimplify motionPathSimplify) {
        super(list, motionPathSimplify);
    }

    @Override // defpackage.hkw, com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor
    public List<hkx> preprocess() {
        List<hkx> preprocess = super.preprocess();
        for (int i = 0; i < this.d.size(); i++) {
            preprocess.get(i).e(gwe.a((List<MarkPoint>) this.d.get(i).requestMarkPointList(), false));
        }
        LogUtil.a(f13229a, "checkOutput ", preprocess.toString());
        return preprocess;
    }
}
