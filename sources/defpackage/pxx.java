package defpackage;

import com.huawei.ui.commonui.linechart.common.DataLayerType;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.model.MultiDataBarModel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class pxx implements IStorageModel, MultiDataBarModel {

    /* renamed from: a, reason: collision with root package name */
    private Map<DataLayerType, Float> f16339a = new HashMap();
    private pya e;

    @Override // com.huawei.ui.commonui.linechart.model.MultiDataBarModel
    public void put(DataLayerType dataLayerType, float f) {
        this.f16339a.put(dataLayerType, Float.valueOf(f));
    }

    @Override // com.huawei.ui.commonui.linechart.model.MultiDataBarModel
    public float getCeil(DataLayerType dataLayerType) {
        if (this.f16339a.get(dataLayerType) != null) {
            return this.f16339a.get(dataLayerType).floatValue();
        }
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.model.MultiDataBarModel
    public float getCeil() {
        pya pyaVar = this.e;
        if (pyaVar != null) {
            return getCeil(pyaVar.d());
        }
        return 0.0f;
    }

    public void d(pya pyaVar) {
        this.e = pyaVar;
    }
}
