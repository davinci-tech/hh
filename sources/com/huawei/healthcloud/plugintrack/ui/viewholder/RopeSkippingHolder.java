package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwsportmodel.CommonSegment;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import defpackage.gwr;
import defpackage.hgf;
import defpackage.hjc;
import defpackage.hjj;
import defpackage.koq;
import defpackage.kwt;
import defpackage.nre;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class RopeSkippingHolder {
    private View b;
    private Context c;
    private nre<hjj, hjc, hjc, hjc, hjc, hjc> e = new nre<>();

    public RopeSkippingHolder(Context context) {
        this.c = context;
    }

    public void d(List<CommonSegment> list) {
        if (!koq.e((Object) list, kwt.class)) {
            LogUtil.c("RopeSkippingHolder", "segmentList is not instanceof List<RowingMachineSegment>");
            return;
        }
        boolean d = gwr.d(list);
        gwr.b(this.e, d);
        for (CommonSegment commonSegment : list) {
            if (commonSegment instanceof kwt) {
                gwr.e(this.e, (kwt) commonSegment, d);
            }
        }
    }

    public View bkW_() {
        this.b = LayoutInflater.from(this.c).inflate(R.layout.track_share_viewholder_rowmachine_segment, (ViewGroup) null);
        d();
        return this.b;
    }

    private void d() {
        HealthTableWidget healthTableWidget = (HealthTableWidget) this.b.findViewById(R.id.table_layout);
        hgf hgfVar = new hgf(this.c, this.e, !LanguageUtil.h(BaseApplication.e())) { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.RopeSkippingHolder.2
            @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidthValueType() {
                return 1;
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidth(int i) {
                return super.getColumnWidth(i);
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnCount() {
                return super.getColumnCount();
            }

            @Override // defpackage.hgf
            public int a() {
                return super.a();
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getRowHeight(int i) {
                return super.getRowHeight(i);
            }
        };
        int c = nsn.c(this.c, 46.5f);
        int rowCount = hgfVar.getRowCount();
        int rowHeight = hgfVar.getRowHeight(1);
        int c2 = nsn.c(this.c, 4.0f);
        ViewGroup.LayoutParams layoutParams = healthTableWidget.getLayoutParams();
        layoutParams.height = c + ((rowCount - 1) * (rowHeight + c2));
        healthTableWidget.setLayoutParams(layoutParams);
        healthTableWidget.setAdapter(hgfVar);
        hgfVar.notifyDataSetChanged();
    }
}
