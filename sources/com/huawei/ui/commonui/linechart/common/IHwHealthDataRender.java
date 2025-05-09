package com.huawei.ui.commonui.linechart.common;

import defpackage.nnl;
import java.util.List;

/* loaded from: classes6.dex */
public interface IHwHealthDataRender {
    void disableFocusArea();

    void focusArea(List<nnl> list);

    boolean hasData();

    boolean isUsePaintAsBackground();

    void usePaintAsBackground(boolean z);
}
