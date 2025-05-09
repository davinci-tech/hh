package com.huawei.health.knit.section.view;

import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import java.util.List;

/* loaded from: classes8.dex */
public interface ISectionCallBack {

    public interface OnMarkViewTextChangeListener {
        void onMarkViewTextChanged(String str, List<HwHealthMarkerView.a> list);
    }

    public interface OnXRangeChangeListener {
        void onXRangeChanged(int i, String str);
    }
}
