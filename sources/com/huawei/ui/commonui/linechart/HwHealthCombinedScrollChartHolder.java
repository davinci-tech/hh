package com.huawei.ui.commonui.linechart;

import android.content.Context;
import android.graphics.Color;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import defpackage.nmy;
import defpackage.nmz;
import defpackage.nnd;
import defpackage.now;
import defpackage.noz;
import defpackage.npc;
import defpackage.nsn;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public abstract class HwHealthCombinedScrollChartHolder extends HwHealthScrollChartHolder<HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry>, HwHealthCombinedChart> {
    private static final String TAG = "HealthChart_HwHealthCombinedScrollChartHolder";

    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    protected /* bridge */ /* synthetic */ void addDataSet(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        addDataSet((HwHealthCombinedChart) hwHealthBaseScrollBarLineChart, (HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry>) hwHealthBaseBarLineDataSet);
    }

    public HwHealthCombinedScrollChartHolder(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry> onCreateDataSet(HwHealthCombinedChart hwHealthCombinedChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        return createData(hwHealthCombinedChart, bVar);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry> onFakeDataSet(HwHealthChartHolder.b bVar) {
        return createData(null, bVar);
    }

    public HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry> createData(HwHealthCombinedChart hwHealthCombinedChart, HwHealthChartHolder.b bVar) {
        DataInfos d = bVar.d();
        if (HwHealthChartHolder.LAYER_ID_NORMAL_HR.equals(bVar.e())) {
            nmy nmyVar = new nmy(new ArrayList(), getChartBrief(d), getChartLabel(d), getChartUnit(d), d);
            nmyVar.setColor(Color.argb(255, 253, 152, 172));
            nmyVar.e(Color.argb(255, 252, 49, 89));
            nmyVar.setLabelCount(5, true);
            if (hwHealthCombinedChart != null) {
                nmz barData = hwHealthCombinedChart.getBarData();
                if (barData == null) {
                    LogUtil.b(TAG, "build bar data,find null");
                    return null;
                }
                barData.d(d);
            }
            return nmyVar;
        }
        if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(bVar.e())) {
            return getRestDataSet(d);
        }
        if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(bVar.e())) {
            return getWaringDataSet(d, hwHealthCombinedChart);
        }
        if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(bVar.e())) {
            return getBarDataSet(d, hwHealthCombinedChart);
        }
        LogUtil.a(TAG, "showModeArg.dataLayerId: ", bVar.e());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public void initChartData(HwHealthCombinedChart hwHealthCombinedChart) {
        if (((nnd) hwHealthCombinedChart.getData()) == null) {
            nnd nndVar = new nnd();
            nndVar.c(new nmz(new ArrayList()));
            nndVar.d(new now(new ArrayList()));
            nndVar.b(new noz(new ArrayList()));
            hwHealthCombinedChart.setData(nndVar);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void addDataSet(HwHealthCombinedChart hwHealthCombinedChart, HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry> hwHealthBaseBarLineDataSet) {
        if (hwHealthCombinedChart == null) {
            return;
        }
        nnd nndVar = (nnd) hwHealthCombinedChart.getData();
        if (nndVar == null) {
            LogUtil.b(TAG, "addDataSet method found data null");
            return;
        }
        if (hwHealthBaseBarLineDataSet instanceof HwHealthBarDataSet) {
            nndVar.j().addDataSet((IHwHealthBarDataSet) hwHealthBaseBarLineDataSet);
        } else if (hwHealthBaseBarLineDataSet instanceof npc) {
            nndVar.h().addDataSet((IHwHealthLineDataSet) hwHealthBaseBarLineDataSet);
        } else if (hwHealthBaseBarLineDataSet instanceof HwHealthLineDataSet) {
            nndVar.f().addDataSet((IHwHealthLineDataSet) hwHealthBaseBarLineDataSet);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder, com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder
    public void rmDataLayer(HwHealthCombinedChart hwHealthCombinedChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        if (hwHealthCombinedChart == null || hwHealthCombinedChart.getData() == null) {
            return;
        }
        if (hwHealthBaseBarLineDataSet instanceof HwHealthBarDataSet) {
            hwHealthCombinedChart.e((HwHealthBarDataSet) hwHealthBaseBarLineDataSet);
            hwHealthCombinedChart.acquireScrollAdapter().reverseInjectStorage(hwHealthBaseBarLineDataSet);
            hwHealthCombinedChart.refresh();
        } else if (hwHealthBaseBarLineDataSet instanceof npc) {
            hwHealthCombinedChart.d();
            hwHealthCombinedChart.acquireScrollAdapter().reverseInjectStorage(hwHealthBaseBarLineDataSet);
            hwHealthCombinedChart.refresh();
        } else {
            if (hwHealthBaseBarLineDataSet instanceof HwHealthLineDataSet) {
                hwHealthCombinedChart.e();
                hwHealthCombinedChart.acquireScrollAdapter().reverseInjectStorage(hwHealthBaseBarLineDataSet);
                hwHealthCombinedChart.refresh();
                return;
            }
            LogUtil.b(TAG, "rmDataLayer unrecognized dataSet,logic error!!!");
        }
    }

    private HwHealthBarDataSet getBarDataSet(DataInfos dataInfos, HwHealthCombinedChart hwHealthCombinedChart) {
        nmy nmyVar = new nmy(new ArrayList(16), "bradycardia", getChartLabel(dataInfos), getChartUnit(dataInfos), dataInfos);
        nmyVar.setColor(Color.argb(255, 253, 152, 172));
        nmyVar.e(Color.argb(255, 252, 49, 89));
        nmyVar.setLabelCount(5, true);
        if (hwHealthCombinedChart != null) {
            nmz barData = hwHealthCombinedChart.getBarData();
            if (barData == null) {
                LogUtil.b(TAG, "build bar data,find null 3");
                return null;
            }
            barData.d(dataInfos);
        }
        return nmyVar;
    }

    private HwHealthBarDataSet getWaringDataSet(DataInfos dataInfos, HwHealthCombinedChart hwHealthCombinedChart) {
        ArrayList arrayList = new ArrayList();
        getChartBrief(dataInfos);
        nmy nmyVar = new nmy(arrayList, HwHealthChartHolder.LAYER_ID_WARNING_HR, getChartLabel(dataInfos), getChartUnit(dataInfos), dataInfos);
        nmyVar.setColor(Color.argb(255, 253, 152, 172));
        nmyVar.e(Color.argb(255, 252, 49, 89));
        nmyVar.setLabelCount(5, true);
        if (hwHealthCombinedChart != null) {
            nmz barData = hwHealthCombinedChart.getBarData();
            if (barData == null) {
                LogUtil.b(TAG, "build bar data,find null 2");
                return null;
            }
            barData.d(dataInfos);
        }
        return nmyVar;
    }

    private HwHealthLineDataSet getRestDataSet(DataInfos dataInfos) {
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, new ArrayList(), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos));
        hwHealthLineDataSet.setLabelCount(5, true);
        hwHealthLineDataSet.setColor(Color.argb(255, 252, 49, 89));
        hwHealthLineDataSet.b(new HwHealthLineDataSet.NodeDrawStyle() { // from class: com.huawei.ui.commonui.linechart.HwHealthCombinedScrollChartHolder.3
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i) {
                return 1.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return true;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return nsn.c(BaseApplication.getContext(), 2.0f);
            }
        });
        return hwHealthLineDataSet;
    }
}
