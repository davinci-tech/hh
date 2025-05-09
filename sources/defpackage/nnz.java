package defpackage;

import com.huawei.ui.commonui.linechart.common.MotionType;
import com.huawei.ui.commonui.linechart.common.MultiMotionBarModel;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class nnz implements IStorageModel, MultiMotionBarModel {
    private Map<MotionType, Float> d = new HashMap();

    @Override // com.huawei.ui.commonui.linechart.common.MultiMotionBarModel
    public void put(MotionType motionType, float f) {
        this.d.put(motionType, Float.valueOf(f));
    }

    @Override // com.huawei.ui.commonui.linechart.common.MultiMotionBarModel
    public float getMotionType(MotionType motionType) {
        if (this.d.get(motionType) != null) {
            return this.d.get(motionType).floatValue();
        }
        return 0.0f;
    }
}
