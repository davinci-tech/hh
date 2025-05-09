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
import defpackage.kwl;
import defpackage.nre;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class RowingMachineHolder {

    /* renamed from: a, reason: collision with root package name */
    private nre<hjj, hjc, hjc, hjc, hjc, hjc> f3819a = new nre<>();
    private hgf b;
    private HealthTableWidget c;
    private View d;
    private Context e;

    public RowingMachineHolder(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.e = context;
    }

    public void c(List<CommonSegment> list) {
        if (!koq.e((Object) list, kwl.class)) {
            LogUtil.c("Track_RowingMachineHolder", "segmentList is not instanceof List<RowingMachineSegment>");
            return;
        }
        gwr.c(this.f3819a);
        for (CommonSegment commonSegment : list) {
            if (commonSegment instanceof kwl) {
                gwr.d(this.f3819a, (kwl) commonSegment);
            }
        }
    }

    public View blc_() {
        this.d = LayoutInflater.from(this.e).inflate(R.layout.track_share_viewholder_rowmachine_segment, (ViewGroup) null);
        a();
        return this.d;
    }

    private void a() {
        this.c = (HealthTableWidget) this.d.findViewById(R.id.table_layout);
        this.b = new hgf(this.e, this.f3819a, !LanguageUtil.h(BaseApplication.e())) { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.RowingMachineHolder.3
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
        int c = nsn.c(this.e, 46.5f);
        int rowCount = this.b.getRowCount();
        int rowHeight = this.b.getRowHeight(1);
        int c2 = nsn.c(this.e, 4.0f);
        ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
        layoutParams.height = c + ((rowCount - 1) * (rowHeight + c2));
        this.c.setLayoutParams(layoutParams);
        this.c.setAdapter(this.b);
        this.b.notifyDataSetChanged();
    }
}
