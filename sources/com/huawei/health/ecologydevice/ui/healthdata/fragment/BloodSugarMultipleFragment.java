package com.huawei.health.ecologydevice.ui.healthdata.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.healthdata.activity.BloodSugarHistoryActivity;
import com.huawei.health.ecologydevice.ui.healthdata.adapter.BloodSugarMultipleAdapter;
import com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarMultipleFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.koq;
import defpackage.nsj;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class BloodSugarMultipleFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private BloodSugarHistoryActivity f2325a;
    private NoTitleCustomAlertDialog b;
    private BloodSugarMultipleAdapter c;
    private HealthButton d;
    private CommonDialog21 e;
    private ExpandableListView g;
    private boolean i;

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof BloodSugarHistoryActivity) {
            this.f2325a = (BloodSugarHistoryActivity) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_blood_sugar_multiple, viewGroup, false);
        if (inflate == null) {
            LogUtil.c("BloodSugarMultipleFragment", "view is null");
            return inflate;
        }
        this.g = (ExpandableListView) inflate.findViewById(R.id.blood_sugar_multiple_expandable_lv);
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.blood_sugar_multiple_bt_batch_done);
        this.d = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: dgj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarMultipleFragment.this.Um_(view);
            }
        });
        this.f2325a.b(new BloodSugarHistoryActivity.DataConfirmRefreshListener() { // from class: dgl
            @Override // com.huawei.health.ecologydevice.ui.healthdata.activity.BloodSugarHistoryActivity.DataConfirmRefreshListener
            public final void refresh() {
                BloodSugarMultipleFragment.this.d();
            }
        });
        if (this.c == null) {
            this.c = new BloodSugarMultipleAdapter(this.f2325a);
        }
        this.g.setAdapter(this.c);
        this.g.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarMultipleFragment$$ExternalSyntheticLambda5
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public final boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                return BloodSugarMultipleFragment.this.Un_(expandableListView, view, i, i2, j);
            }
        });
        View inflate2 = layoutInflater.inflate(R.layout.item_blood_sugar_remind, (ViewGroup) null);
        inflate2.setOnClickListener(null);
        this.g.addFooterView(inflate2);
        return inflate;
    }

    public /* synthetic */ void Um_(View view) {
        this.d.setClickable(false);
        this.d.setBackgroundResource(R.drawable.button_background_emphasize_disable);
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ boolean Un_(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        this.f2325a.a(i, i2);
        ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i, i2);
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        g();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (isHidden()) {
            return;
        }
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        a();
        this.i = false;
        this.d.setClickable(true);
        this.d.setBackgroundResource(R.drawable.button_background_emphasize);
        if (this.f2325a.d() == 0) {
            this.f2325a.i();
        } else {
            g();
        }
    }

    private void g() {
        LogUtil.d("BloodSugarMultipleFragment", "updateView");
        List<List<HiHealthData>> e = this.f2325a.e();
        this.c.c(d(e), e);
        this.c.notifyDataSetChanged();
        this.g.expandGroup(0);
    }

    private List<String> d(List<List<HiHealthData>> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (List<HiHealthData> list2 : list) {
            if (koq.d(list2, 0)) {
                arrayList.add(nsj.g(list2.get(0).getEndTime()));
            }
        }
        return arrayList;
    }

    private void e() {
        int d = this.f2325a.d();
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f2325a).e(this.f2325a.getResources().getQuantityString(R.plurals._2130903346_res_0x7f030132, d, Integer.valueOf(d))).czE_(getString(R.string._2130845895_res_0x7f0220c7), new View.OnClickListener() { // from class: dgc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarMultipleFragment.this.Uo_(view);
            }
        }).czA_(getString(R.string._2130845098_res_0x7f021daa), new View.OnClickListener() { // from class: dge
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarMultipleFragment.this.Up_(view);
            }
        }).e();
        this.b = e;
        e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: dgh
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                BloodSugarMultipleFragment.this.Uq_(dialogInterface);
            }
        });
        this.b.show();
    }

    public /* synthetic */ void Uo_(View view) {
        b();
        this.i = true;
        this.f2325a.a();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Up_(View view) {
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Uq_(DialogInterface dialogInterface) {
        if (this.i) {
            return;
        }
        this.d.setClickable(true);
        this.d.setBackgroundResource(R.drawable.button_background_emphasize);
    }

    private void c() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.b;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.b = null;
        }
    }

    private void b() {
        if (this.e == null) {
            CommonDialog21 a2 = CommonDialog21.a(this.f2325a);
            this.e = a2;
            a2.e(getString(R.string.IDS_device_blood_sugar_confirming));
            this.e.a();
            this.e.setCancelable(false);
            return;
        }
        LogUtil.c("BloodSugarMultipleFragment", "showConfirmingDialog: mConfirmingDialog is not null");
    }

    private void a() {
        CommonDialog21 commonDialog21 = this.e;
        if (commonDialog21 != null) {
            commonDialog21.cancel();
            this.e = null;
        }
    }
}
