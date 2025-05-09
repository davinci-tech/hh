package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity;
import com.huawei.ui.main.stories.health.adapter.BloodSugarMeasureMutipleAdapter;
import com.huawei.ui.main.stories.health.fragment.BloodSugarMeasureMutipleFragment;
import defpackage.koq;
import defpackage.qkg;
import health.compact.a.util.LogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class BloodSugarMeasureMutipleFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f10166a;
    private BloodSugarMeasureMutipleAdapter b;
    private boolean c;
    private ExpandableListView d;
    private BloodSugarDeviceMeasureActivity e;

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof BloodSugarDeviceMeasureActivity) {
            this.e = (BloodSugarDeviceMeasureActivity) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_blood_sugar_measure_mutiple, viewGroup, false);
        this.d = (ExpandableListView) inflate.findViewById(R.id.hw_show_blood_sugar_measure_mutiple_expandable_lv);
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.hw_show_blood_sugar_measure_mutiple_bt_batch_done);
        this.f10166a = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: qin
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarMeasureMutipleFragment.this.dDZ_(view);
            }
        });
        this.e.c(new BloodSugarDeviceMeasureActivity.DataConfirmRefreshListener() { // from class: qim
            @Override // com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity.DataConfirmRefreshListener
            public final void refresh() {
                BloodSugarMeasureMutipleFragment.this.c();
            }
        });
        BloodSugarMeasureMutipleAdapter bloodSugarMeasureMutipleAdapter = new BloodSugarMeasureMutipleAdapter(getContext());
        this.b = bloodSugarMeasureMutipleAdapter;
        this.d.setAdapter(bloodSugarMeasureMutipleAdapter);
        this.d.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.huawei.ui.main.stories.health.fragment.BloodSugarMeasureMutipleFragment$$ExternalSyntheticLambda5
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public final boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                return BloodSugarMeasureMutipleFragment.this.dEa_(expandableListView, view, i, i2, j);
            }
        });
        View inflate2 = layoutInflater.inflate(R.layout.item_blood_sugar_remind, (ViewGroup) null);
        inflate2.setOnClickListener(null);
        this.d.addFooterView(inflate2);
        return inflate;
    }

    public /* synthetic */ void dDZ_(View view) {
        this.f10166a.setClickable(false);
        this.f10166a.setBackgroundResource(R.drawable.button_background_emphasize_disable);
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void c() {
        this.c = false;
        this.f10166a.setClickable(true);
        this.f10166a.setBackgroundResource(R.drawable.button_background_emphasize);
        d();
    }

    /* synthetic */ boolean dEa_(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        this.e.c(i, i2);
        ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i, i2);
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        d();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (isHidden()) {
            return;
        }
        d();
    }

    private void d() {
        LogUtil.d("BloodSugarMeasureMutipleFragment", "update");
        List<List<qkg>> g = this.e.g();
        List<String> e = e(g);
        this.b.b(e, g);
        this.b.notifyDataSetChanged();
        for (int i = 0; i < e.size(); i++) {
            this.d.expandGroup(i);
        }
    }

    private List<String> e(List<List<qkg>> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/d");
        ArrayList arrayList = new ArrayList();
        for (List<qkg> list2 : list) {
            if (koq.d(list2, 0)) {
                arrayList.add(simpleDateFormat.format(Long.valueOf(list2.get(0).h())));
            }
        }
        return arrayList;
    }

    private void a() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
        int a2 = this.e.a();
        builder.e(this.e.getResources().getQuantityString(R.plurals._2130903346_res_0x7f030132, a2, Integer.valueOf(a2))).czE_(getString(R$string.IDS_hw_show_healthdata_bloodsugar_certain), new View.OnClickListener() { // from class: qij
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarMeasureMutipleFragment.this.dEb_(view);
            }
        }).czA_(getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: qil
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: qip
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                BloodSugarMeasureMutipleFragment.this.dEc_(dialogInterface);
            }
        });
        e.show();
    }

    public /* synthetic */ void dEb_(View view) {
        this.c = true;
        this.e.c();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dEc_(DialogInterface dialogInterface) {
        if (this.c) {
            return;
        }
        this.f10166a.setClickable(true);
        this.f10166a.setBackgroundResource(R.drawable.button_background_emphasize);
    }
}
