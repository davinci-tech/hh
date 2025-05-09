package com.huawei.ui.main.stories.health.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.adapter.ClaimDataExpandableListViewAdapter;
import com.huawei.ui.main.stories.health.fragment.ClaimDataFatRateFluctuationFragment;
import defpackage.cfi;
import defpackage.csh;
import defpackage.csi;
import defpackage.gnm;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class ClaimDataFatRateFluctuationFragment extends ClaimMeasureDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f10168a;
    private ClaimDataExpandableListViewAdapter b;
    private ExpandableListView c;
    private int d;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_claim_data_fatrate_fluctuation, viewGroup, false);
        dEd_(inflate);
        a();
        return inflate;
    }

    private void a() {
        ClaimDataExpandableListViewAdapter claimDataExpandableListViewAdapter = new ClaimDataExpandableListViewAdapter(this.mSelectUserInterface);
        this.b = claimDataExpandableListViewAdapter;
        this.c.setAdapter(claimDataExpandableListViewAdapter);
        setList();
    }

    private void dEd_(View view) {
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.hw_claim_data_expand_listview);
        this.c = expandableListView;
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: com.huawei.ui.main.stories.health.fragment.ClaimDataFatRateFluctuationFragment.5
            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public boolean onGroupClick(ExpandableListView expandableListView2, View view2, int i, long j) {
                ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView2, view2, i);
                return true;
            }
        });
        view.findViewById(R.id.hw_right_measure_method).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.fragment.ClaimDataFatRateFluctuationFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ClaimDataFatRateFluctuationFragment.this.e();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qis
            @Override // java.lang.Runnable
            public final void run() {
                ClaimDataFatRateFluctuationFragment.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        final String b = b();
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "can not get CorrectMeasureMethod help url");
        } else if (d()) {
            this.mActivity.runOnUiThread(new Runnable() { // from class: qio
                @Override // java.lang.Runnable
                public final void run() {
                    ClaimDataFatRateFluctuationFragment.this.a(b);
                }
            });
        }
    }

    private String b() {
        return GRSManager.a(BaseApplication.e()).getNoCheckUrl("domainTipsResDbankcdn", "SG") + "/handbook/SmartWear/Scale-eight/EMUI8.0/C001B001/zh-CN/index.html";
    }

    private boolean d() {
        return (this.mActivity == null || this.mActivity.isDestroyed() || this.mActivity.isFinishing()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "startWebViewActivity url is empty");
            return false;
        }
        Intent intent = new Intent(this.mActivity, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        gnm.aPB_(this.mActivity, intent);
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void setList() {
        ClaimDataExpandableListViewAdapter claimDataExpandableListViewAdapter = this.b;
        if (claimDataExpandableListViewAdapter == null) {
            LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "setList mAdapter is null");
            return;
        }
        claimDataExpandableListViewAdapter.b();
        Map<cfi, ArrayList<csh>> fatRateFluctuationDataBean = ClaimWeightDataManager.INSTANCE.getFatRateFluctuationDataBean();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<cfi, ArrayList<csh>> entry : fatRateFluctuationDataBean.entrySet()) {
            cfi key = entry.getKey();
            if (key == null) {
                LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "setList user is null");
            } else {
                arrayList.add(key);
                ArrayList<csh> value = entry.getValue();
                b(value);
                arrayList2.add(value);
            }
        }
        if (koq.c(arrayList) && koq.c(arrayList2)) {
            LogUtil.a("HealthWeight_ClaimDataFatRateBigChangeFragment", "initData claimWeightDataBean not Empty size = ", Integer.valueOf(arrayList2.size()));
            this.b.a(arrayList, arrayList2);
            for (int i = 0; i < arrayList.size(); i++) {
                this.c.expandGroup(i);
            }
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public int getListSize() {
        ClaimDataExpandableListViewAdapter claimDataExpandableListViewAdapter = this.b;
        if (claimDataExpandableListViewAdapter != null) {
            return claimDataExpandableListViewAdapter.c();
        }
        return 0;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void removeItem() {
        ClaimDataExpandableListViewAdapter claimDataExpandableListViewAdapter = this.b;
        if (claimDataExpandableListViewAdapter != null) {
            claimDataExpandableListViewAdapter.a();
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void setAllChooseItem(boolean z) {
        ClaimDataExpandableListViewAdapter claimDataExpandableListViewAdapter = this.b;
        if (claimDataExpandableListViewAdapter != null) {
            claimDataExpandableListViewAdapter.c(z);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void claimMeasureData() {
        if (this.b == null) {
            LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "claimMeasureData mAdapter is null");
        } else {
            sendClaimWeight();
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void sendStartClaimWeightData() {
        Message obtain = Message.obtain();
        obtain.what = 4;
        this.mClaimHandler.sendMessageDelayed(obtain, 200L);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void deleteWeightData() {
        ClaimDataExpandableListViewAdapter claimDataExpandableListViewAdapter = this.b;
        if (claimDataExpandableListViewAdapter == null) {
            LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "deleteWeightData mAdapter is null");
            return;
        }
        ArrayList<csh> d = claimDataExpandableListViewAdapter.d();
        if (koq.b(d)) {
            LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "deleteWeightData chooseClaimWeightDatas is empty");
        } else {
            deleteSelectedWeightData(d);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public boolean saveWeightData() {
        if (g()) {
            ReleaseLogUtil.c("HealthWeight_ClaimDataFatRateBigChangeFragment", "unable to save weight data, return");
            destroyLoadingDialog();
            return false;
        }
        Map<cfi, ArrayList<csh>> e = this.b.e();
        this.d = e.size();
        for (Map.Entry<cfi, ArrayList<csh>> entry : e.entrySet()) {
            saveSingleUserWeightData(entry.getKey(), entry.getValue());
        }
        LogUtil.a("HealthWeight_ClaimDataFatRateBigChangeFragment", "saveWeightData chooseMapData size,", Integer.valueOf(e.size()));
        return true;
    }

    private boolean g() {
        if (!super.saveWeightData()) {
            LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "saveWeightData : Network is not Connected!");
            return true;
        }
        ClaimDataExpandableListViewAdapter claimDataExpandableListViewAdapter = this.b;
        if (claimDataExpandableListViewAdapter == null) {
            LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "saveWeightData mAdapter is null");
            return true;
        }
        if (claimDataExpandableListViewAdapter.e().size() > 0) {
            return false;
        }
        LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "saveWeightData chooseMapData is empty");
        return true;
    }

    private void b(ArrayList<csh> arrayList) {
        Iterator<csh> it = arrayList.iterator();
        while (it.hasNext()) {
            csh next = it.next();
            List<csi> b = next.b();
            if (koq.b(b)) {
                LogUtil.h("HealthWeight_ClaimDataFatRateBigChangeFragment", "setClaimDataConflictUserInfo conflictUserInfoList is null or empty");
            } else {
                saveConflictUserInfo(next, b.get(0));
            }
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void claimFinishAndChangeUi(boolean z, String str) {
        int i = this.f10168a + 1;
        this.f10168a = i;
        if (i == this.d) {
            super.claimFinishAndChangeUi(z, str);
            if (z) {
                this.f10168a = 0;
                removeItem();
                nrh.d(this.mActivity, this.mActivity.getString(R$string.IDS_hw_claim_save_success));
            }
        }
        if (z) {
            return;
        }
        this.f10168a = 0;
        super.claimFinishAndChangeUi(z, str);
        nrh.b(this.mActivity, R$string.IDS_update_server_bussy);
    }
}
