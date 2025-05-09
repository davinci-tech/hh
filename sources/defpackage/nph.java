package defpackage;

import android.graphics.Canvas;
import com.github.mikephil.charting.utils.Transformer;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;
import java.util.List;

/* loaded from: classes6.dex */
public class nph extends npj {
    public nph(HwHealthRenderMode hwHealthRenderMode, DataInfos dataInfos) {
        super(hwHealthRenderMode);
        hwHealthRenderMode.c = true;
        hwHealthRenderMode.b = dataInfos;
    }

    @Override // defpackage.npj
    public void cDs_(Canvas canvas, IHwHealthLineDatasContainer iHwHealthLineDatasContainer, Transformer transformer) {
        super.cDs_(canvas, iHwHealthLineDatasContainer, transformer);
    }

    @Override // defpackage.npj
    protected List<npf> e(IHwHealthLineDatasContainer iHwHealthLineDatasContainer) {
        return iHwHealthLineDatasContainer.queryNewNodes();
    }
}
