package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.HorizontalDetailActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.tablewidget.BaseViewHolder;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import defpackage.gwr;
import defpackage.hgf;
import defpackage.hjc;
import defpackage.hjj;
import defpackage.koq;
import defpackage.kwq;
import defpackage.kwu;
import defpackage.nre;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class SegmentHorizontalFrag extends BaseFragment {
    private HealthTableWidget b;
    private List<CommonSegment> c;
    private Context d;
    private hgf f;
    private CommonSegment g;
    private float[] j;
    private View k;
    private nre<hjj, hjc, hjc, hjc, hjc, hjc> n = new nre<>();
    private List<CommonSegment> i = new ArrayList();
    private int h = -1;
    private int e = 0;

    /* renamed from: a, reason: collision with root package name */
    boolean f3725a = false;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            throw new RuntimeException("LayoutInflater not found.");
        }
        LogUtil.a("Track_SegmentHorizontalFrag", "onCreateView");
        FragmentActivity activity = getActivity();
        if (!(activity instanceof HorizontalDetailActivity)) {
            LogUtil.b("Track_SegmentHorizontalFrag", "Object is not instanceof HorizontalDetailActivity");
            return null;
        }
        HorizontalDetailActivity horizontalDetailActivity = (HorizontalDetailActivity) activity;
        this.d = horizontalDetailActivity;
        if (!a(horizontalDetailActivity)) {
            LogUtil.b("Track_SegmentHorizontalFrag", "getData failed");
            horizontalDetailActivity.finish();
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.track_horizontal_segment_fragment, viewGroup, false);
        this.b = (HealthTableWidget) inflate.findViewById(R.id.table_layout);
        this.k = inflate.findViewById(R.id.tips_layout);
        a();
        this.j = e();
        d();
        BaseActivity.cancelLayoutById(inflate, this.b);
        return inflate;
    }

    private float[] e() {
        return gwr.d(this.h) ? new float[]{63.75f, 62.25f, 62.25f, 68.5f, 71.75f, 65.0f, 65.25f, 58.25f, 61.5f} : gwr.a(this.h) ? new float[]{63.75f, 62.25f, 62.25f, 68.5f, 71.75f, 65.0f, 65.25f, 58.25f, 61.5f, 61.5f, 77.0f, 70.5f, 70.5f, 70.5f} : new float[0];
    }

    private boolean a(HorizontalDetailActivity horizontalDetailActivity) {
        Intent intent = horizontalDetailActivity.getIntent();
        if (intent == null) {
            LogUtil.b("Track_SegmentHorizontalFrag", "intent is null HorizontalDetailActivity");
            return false;
        }
        int intExtra = intent.getIntExtra(BleConstants.SPORT_TYPE, -1);
        this.h = intExtra;
        if (intExtra == -1) {
            LogUtil.h("Track_SegmentHorizontalFrag", "intent sportType paras error: ", Integer.valueOf(intExtra));
            return false;
        }
        List<CommonSegment> b = SegmentFrag.b();
        this.c = b;
        if (!koq.b(b)) {
            return true;
        }
        LogUtil.h("Track_SegmentHorizontalFrag", "get segment data list is empty");
        return false;
    }

    private void a() {
        int i = this.h;
        if (i != 258) {
            if (i == 259) {
                d(SegmentFrag.b());
                return;
            } else if (i != 280) {
                return;
            }
        }
        b(SegmentFrag.b());
    }

    private void b(List<CommonSegment> list) {
        int fieldNum;
        if (koq.e((Object) list, kwu.class)) {
            List asList = Arrays.asList((kwu[]) list.toArray(new kwu[list.size()]));
            int c = gwr.c(list);
            gwr.a(this.n, c);
            CommonSegment e = SegmentFrag.e();
            this.g = e;
            this.f3725a = gwr.d((List<kwu>) asList, e);
            kwu kwuVar = null;
            boolean z = false;
            for (int i = 0; i < list.size(); i++) {
                kwuVar = (kwu) list.get(i);
                if (kwuVar.w()) {
                    z = true;
                }
                gwr.b(this.n, kwuVar, c, this.f3725a);
            }
            if (kwuVar != null) {
                if (z) {
                    fieldNum = kwuVar.getFieldNum();
                } else {
                    fieldNum = kwuVar.getFieldNum() - kwuVar.a();
                }
                this.e = fieldNum;
                if (c == 1) {
                    this.e = fieldNum + 1;
                }
            }
            CommonSegment commonSegment = this.g;
            if (commonSegment != null) {
                gwr.e(this.n, (kwu) commonSegment, this.d.getString(R.string._2130839908_res_0x7f020964), c, this.f3725a);
            } else {
                gwr.e(this.n, gwr.e((List<kwu>) Arrays.asList((kwu[]) list.toArray(new kwu[list.size()]))), this.d.getString(R.string._2130839908_res_0x7f020964), c, this.f3725a);
            }
            nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar = this.n;
            List<CommonSegment> list2 = this.i;
            gwr.e(nreVar, gwr.e((List<kwu>) Arrays.asList((kwu[]) list2.toArray(new kwu[list2.size()]))), this.d.getString(R.string._2130839909_res_0x7f020965), c, this.f3725a);
        }
    }

    private void d(List<CommonSegment> list) {
        if (koq.e((Object) list, kwq.class)) {
            gwr.d(this.n);
            kwq kwqVar = null;
            for (CommonSegment commonSegment : list) {
                if (commonSegment instanceof kwq) {
                    kwqVar = (kwq) commonSegment;
                    gwr.c(this.n, kwqVar);
                }
            }
            if (kwqVar != null) {
                this.e = kwqVar.getFieldNum();
            }
            CommonSegment e = SegmentFrag.e();
            this.g = e;
            kwq kwqVar2 = (kwq) e;
            if (e != null) {
                gwr.e(this.n, kwqVar2, this.d.getString(R.string._2130839908_res_0x7f020964));
            } else {
                gwr.e(this.n, gwr.a((List<kwq>) Arrays.asList((kwq[]) list.toArray(new kwq[list.size()]))), this.d.getString(R.string._2130839908_res_0x7f020964));
            }
            nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar = this.n;
            List<CommonSegment> list2 = this.i;
            gwr.e(nreVar, gwr.a((List<kwq>) Arrays.asList((kwq[]) list2.toArray(new kwq[list2.size()]))), this.d.getString(R.string._2130839909_res_0x7f020965));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        int c = gwr.c(this.i);
        if (gwr.a(this.h)) {
            if (this.c.size() == this.i.size()) {
                CommonSegment commonSegment = this.g;
                if (commonSegment instanceof kwu) {
                    kwu kwuVar = (kwu) commonSegment;
                    gwr.b(this.n, r0.getRowsCount() - 1, kwuVar, this.d.getString(R.string._2130839909_res_0x7f020965), c, this.f3725a);
                    return;
                }
            }
            nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar = this.n;
            int rowsCount = nreVar.getRowsCount();
            List<CommonSegment> list = this.i;
            gwr.b(nreVar, rowsCount - 1, gwr.e((List<kwu>) Arrays.asList((kwu[]) list.toArray(new kwu[list.size()]))), this.d.getString(R.string._2130839909_res_0x7f020965), c, this.f3725a);
            return;
        }
        if (gwr.d(this.h)) {
            if (this.c.size() == this.i.size()) {
                CommonSegment commonSegment2 = this.g;
                if (commonSegment2 instanceof kwq) {
                    gwr.e(this.n, r2.getRowsCount() - 1, (kwq) commonSegment2, this.d.getString(R.string._2130839909_res_0x7f020965));
                    return;
                }
            }
            nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar2 = this.n;
            int rowsCount2 = nreVar2.getRowsCount();
            List<CommonSegment> list2 = this.i;
            gwr.e(nreVar2, rowsCount2 - 1, gwr.a((List<kwq>) Arrays.asList((kwq[]) list2.toArray(new kwq[list2.size()]))), this.d.getString(R.string._2130839909_res_0x7f020965));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (koq.b(this.i)) {
            this.k.setVisibility(0);
        } else {
            this.k.setVisibility(8);
        }
    }

    private void d() {
        this.f = new hgf(getContext(), this.n, !LanguageUtil.h(BaseApplication.e())) { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SegmentHorizontalFrag.4
            @Override // defpackage.hgf
            public int a() {
                return nsn.c(SegmentHorizontalFrag.this.d, 12.0f);
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnCount() {
                return Math.min(super.getColumnCount(), SegmentHorizontalFrag.this.e);
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidth(int i) {
                int bfc_;
                int c = nsn.c(SegmentHorizontalFrag.this.d, 2.0f);
                if (i < getRowHeaderNum()) {
                    BaseViewHolder bdB_ = onCreateRowColumnHeaderViewHolder(null);
                    onBindRowColumnHeaderViewHolder(bdB_, 0, i);
                    bfc_ = SegmentHorizontalFrag.this.bfc_(bdB_.getItemView());
                } else {
                    BaseViewHolder bdz_ = onCreateColumnHeaderViewHolder(null);
                    onBindColumnHeaderViewHolder(bdz_, 0, i);
                    bfc_ = SegmentHorizontalFrag.this.bfc_(bdz_.getItemView());
                }
                int i2 = c + bfc_;
                return (SegmentHorizontalFrag.this.j == null || i < 0 || i >= SegmentHorizontalFrag.this.j.length) ? i2 : Math.max(nsn.c(SegmentHorizontalFrag.this.d, SegmentHorizontalFrag.this.j[i]), i2);
            }

            @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidthValueType() {
                if (SegmentHorizontalFrag.this.j != null && SegmentHorizontalFrag.this.j.length > 0) {
                    float f = 0.0f;
                    for (float f2 : SegmentHorizontalFrag.this.j) {
                        f += f2;
                    }
                    if (nsn.c(SegmentHorizontalFrag.this.d, f) < nsn.n()) {
                        return 1;
                    }
                }
                if (getColumnCount() > 9) {
                    return super.getColumnWidthValueType();
                }
                return 1;
            }
        };
        c();
        this.b.setAdapter(this.f);
    }

    private void c() {
        this.f.setOnItemClickListener(new HealthTableWidget.OnItemClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SegmentHorizontalFrag.1
            @Override // com.huawei.ui.commonui.tablewidget.HealthTableWidget.OnItemClickListener
            public void onItemClick(int i, int i2) {
                int rowHeaderNum = i - SegmentHorizontalFrag.this.f.getRowHeaderNum();
                if (koq.d(SegmentHorizontalFrag.this.c, rowHeaderNum)) {
                    CommonSegment commonSegment = (CommonSegment) SegmentHorizontalFrag.this.c.get(rowHeaderNum);
                    if (SegmentHorizontalFrag.this.i.contains(commonSegment)) {
                        SegmentHorizontalFrag.this.i.remove(commonSegment);
                        SegmentHorizontalFrag.this.f.c(i);
                    } else {
                        SegmentHorizontalFrag.this.i.add(commonSegment);
                        SegmentHorizontalFrag.this.f.a(i);
                    }
                    SegmentHorizontalFrag.this.f.notifyRowChanged(i);
                    SegmentHorizontalFrag.this.b();
                    SegmentHorizontalFrag.this.i();
                    SegmentHorizontalFrag.this.f.notifyRowChanged(SegmentHorizontalFrag.this.f.getRowCount() - 1);
                }
            }
        });
    }

    int bfc_(View view) {
        if (view == null) {
            LogUtil.h("Track_SegmentHorizontalFrag", "getViewWidth with null view, pls check");
            return 0;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredWidth();
    }
}
