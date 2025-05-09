package com.huawei.ui.commonui.linechart;

import android.content.Context;
import com.github.mikephil.charting.data.ChartData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import defpackage.nrn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public abstract class HwHealthChartHolder<T extends HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry>, ChartT extends HwHealthBaseScrollBarLineChart> implements IChartLayerHolder<T, ChartT> {
    public static final String BRADYCARDIA_WARN = "BRADYCARDIA";
    public static final String HIGH_WARN = "HIGH_WARN";
    public static final String LAYER_ID_BRADYCARDIA = "bradycardia_hr";
    public static final String LAYER_ID_NORMAL_HR = "normal_hr";
    public static final String LAYER_ID_REST_HR = "rest_hr";
    public static final String LAYER_ID_WARNING_HR = "warning_hr";
    protected Context mContext;
    private List<Mode> mModes = new ArrayList();
    private Map<IChartLayerHolder.DataTypeFilter, String> mSpecifiedDataTypeUnit = new HashMap();

    public enum Mode {
        MODE_FIRST_AXIS,
        MODE_SECOND_AXIS,
        MODE_THIRD_PARTY_AXIS,
        MODE_NONE
    }

    protected abstract void initChartData(ChartT chartt);

    protected abstract T onCreateDataSet(ChartT chartt, DataInfos dataInfos, b bVar);

    protected void onCustomChartStyle(ChartT chartt, DataInfos dataInfos) {
    }

    protected abstract T onFakeDataSet(b bVar);

    public static class b {
        DataInfos c = DataInfos.NoDataPlaceHolder;

        /* renamed from: a, reason: collision with root package name */
        String f8851a = "default";
        int e = 0;

        public b e(DataInfos dataInfos) {
            this.c = dataInfos;
            return this;
        }

        public DataInfos d() {
            return this.c;
        }

        public b e(String str) {
            this.f8851a = str;
            return this;
        }

        public String e() {
            return this.f8851a;
        }
    }

    public HwHealthChartHolder(Context context) {
        this.mContext = context;
        initLanguageVaries();
    }

    private void initLanguageVaries() {
        this.mModes.clear();
        this.mModes.add(Mode.MODE_FIRST_AXIS);
        this.mModes.add(Mode.MODE_SECOND_AXIS);
        this.mModes.add(Mode.MODE_THIRD_PARTY_AXIS);
        this.mModes.add(Mode.MODE_NONE);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public T addDataLayer(ChartT chartt, DataInfos dataInfos) {
        return addDataLayer((HwHealthChartHolder<T, ChartT>) chartt, new b().e(dataInfos));
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public T addDataLayer(ChartT chartt, b bVar) {
        if (chartt == null) {
            return null;
        }
        T onDataLayerAdded = onDataLayerAdded(chartt, bVar.d(), bVar);
        chartt.refresh();
        return onDataLayerAdded;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public T addDataLayer(ChartT chartt, T t, b bVar) {
        addDataSet(chartt, t);
        chartt.refresh();
        return t;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public T fakeDataLayer(b bVar) {
        return onFakeDataSet(bVar);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public void rmDataLayer(ChartT chartt, T t) {
        ChartData data;
        List dataSets;
        if (chartt == null || (data = chartt.getData()) == null || (dataSets = data.getDataSets()) == null) {
            return;
        }
        dataSets.remove(t);
        chartt.refresh();
    }

    protected T onDataLayerAdded(ChartT chartt, DataInfos dataInfos, b bVar) {
        initChartData(chartt);
        T onCreateDataSet = onCreateDataSet(chartt, dataInfos, bVar);
        chartt.makeReverse(true);
        chartt.setGridColor(nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color), nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color));
        chartt.getXAxis().setAxisLineColor(nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color));
        onCustomChartStyle(chartt, dataInfos);
        addDataSet(chartt, onCreateDataSet);
        return onCreateDataSet;
    }

    protected String getChartUnit(DataInfos dataInfos) {
        for (Map.Entry<IChartLayerHolder.DataTypeFilter, String> entry : this.mSpecifiedDataTypeUnit.entrySet()) {
            if (entry != null) {
                IChartLayerHolder.DataTypeFilter key = entry.getKey();
                if (key.isAccept(dataInfos)) {
                    return this.mSpecifiedDataTypeUnit.get(key);
                }
            }
        }
        return "";
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public void spetifiyDataTypeUnit(IChartLayerHolder.DataTypeFilter dataTypeFilter, String str) {
        this.mSpecifiedDataTypeUnit.put(dataTypeFilter, str);
    }

    protected void addDataSet(ChartT chartt, T t) {
        if (chartt == null) {
            return;
        }
        ChartData data = chartt.getData();
        if (data == null) {
            throw new RuntimeException("addDataSet method found data null");
        }
        data.getDataSets().add(t);
    }

    protected String getChartLabel(DataInfos dataInfos) {
        return "";
    }

    protected String getChartBrief(DataInfos dataInfos) {
        return "";
    }
}
