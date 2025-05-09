package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.HorizontalDetailActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gwi;
import defpackage.gxs;
import defpackage.hgf;
import defpackage.hjc;
import defpackage.hjj;
import defpackage.koq;
import defpackage.nre;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class PaceHorizontalFrag extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private CustomTitleBar f3720a;
    private HealthColumnSystem b;
    private String c;
    private Context d;
    private HealthTableWidget e;
    private float[] f;
    private hgf h;
    private List<gxs> i;
    private View o;
    private nre<hjj, hjc, hjc, hjc, hjc, hjc> g = new nre<>();
    private int j = -1;

    private float[] a() {
        return new float[]{63.75f, 62.25f, 68.5f, 71.75f, 65.0f, 65.25f, 58.25f, 61.5f, 61.5f, 77.0f, 70.5f, 70.5f, 70.5f};
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.h("Track_PaceHorizontalFrag", "LayoutInflater not found.");
            return null;
        }
        LogUtil.a("Track_PaceHorizontalFrag", "onCreateView");
        FragmentActivity activity = getActivity();
        if (!(activity instanceof HorizontalDetailActivity)) {
            LogUtil.b("Track_PaceHorizontalFrag", "Object is not instanceof HorizontalDetailActivity");
            return null;
        }
        HorizontalDetailActivity horizontalDetailActivity = (HorizontalDetailActivity) activity;
        this.d = horizontalDetailActivity;
        if (!b(horizontalDetailActivity)) {
            LogUtil.b("Track_PaceHorizontalFrag", "getData failed");
            horizontalDetailActivity.finish();
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.track_horizontal_segment_fragment, viewGroup, false);
        this.f3720a = (CustomTitleBar) inflate.findViewById(R.id.titlebar);
        this.e = (HealthTableWidget) inflate.findViewById(R.id.table_layout);
        this.o = inflate.findViewById(R.id.tips_layout);
        LogUtil.a("Track_PaceHorizontalFrag", "mFragmentTag = ", this.c);
        this.f3720a.setTitleText(getResources().getString(R.string._2130843951_res_0x7f02192f));
        this.o.setVisibility(8);
        b();
        this.b = new HealthColumnSystem(this.d);
        this.f = a();
        c();
        BaseActivity.cancelLayoutById(inflate, this.e);
        return inflate;
    }

    private boolean b(HorizontalDetailActivity horizontalDetailActivity) {
        Intent intent = horizontalDetailActivity.getIntent();
        if (intent == null) {
            LogUtil.b("Track_PaceHorizontalFrag", "intent is null HorizontalDetailActivity");
            return false;
        }
        this.j = intent.getIntExtra(BleConstants.SPORT_TYPE, -1);
        String stringExtra = intent.getStringExtra("fragment_tag");
        this.c = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.h("Track_PaceHorizontalFrag", "intent fragment tag error: ");
            return false;
        }
        int i = this.j;
        if (i == -1) {
            LogUtil.h("Track_PaceHorizontalFrag", "intent sportType paras error: ", Integer.valueOf(i));
            return false;
        }
        if ("pace".equals(this.c)) {
            LogUtil.a("Track_PaceHorizontalFrag", "from PACE_TAG");
            List<gxs> a2 = HeartRateFrag.a();
            this.i = a2;
            if (!koq.b(a2)) {
                return true;
            }
            LogUtil.h("Track_PaceHorizontalFrag", " mSpeedRangList data list is empty");
            return false;
        }
        LogUtil.h("Track_PaceHorizontalFrag", "mFragmentTag exception", this.c);
        return false;
    }

    private void b() {
        e(this.i);
    }

    private void e(List<gxs> list) {
        if (!koq.e((Object) list, gxs.class)) {
            LogUtil.h("Track_PaceHorizontalFrag", "dataList is exception");
            return;
        }
        LogUtil.a("Track_PaceHorizontalFrag", "initRunningData");
        gwi.c(this.g, list);
        for (int i = 5; i >= 1; i--) {
            gwi.c(this.g, i, list);
        }
    }

    private void c() {
        hgf hgfVar = new hgf(this.d, this.g, false, LanguageUtil.bi(this.d) ? 38.5f : 0.0f) { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.PaceHorizontalFrag.4
            @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidthValueType() {
                return 1;
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidth(int i) {
                if (PaceHorizontalFrag.this.f != null && i >= 0 && i < PaceHorizontalFrag.this.f.length) {
                    return nsn.c(PaceHorizontalFrag.this.d, PaceHorizontalFrag.this.f[i]);
                }
                return super.getColumnWidth(i);
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnCount() {
                if (PaceHorizontalFrag.this.j == 258 && PaceHorizontalFrag.this.b.f() > 4) {
                    return Math.min(super.getColumnCount(), Math.min(11, PaceHorizontalFrag.this.i.size() + 1));
                }
                return super.getColumnCount();
            }

            @Override // defpackage.hgf
            public int a() {
                return super.a();
            }
        };
        this.h = hgfVar;
        this.e.setAdapter(hgfVar);
    }
}
