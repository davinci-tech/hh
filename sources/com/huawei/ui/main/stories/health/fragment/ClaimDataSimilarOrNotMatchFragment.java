package com.huawei.ui.main.stories.health.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity;
import com.huawei.ui.main.stories.health.adapter.ClaimMeasureDataAdapter;
import com.huawei.ui.main.stories.health.adapter.SelectUserAdapter;
import com.huawei.ui.main.stories.health.interactors.healthdata.SelectUserInterface;
import com.huawei.up.api.UpApi;
import defpackage.cfi;
import defpackage.csh;
import defpackage.csi;
import defpackage.dij;
import defpackage.koq;
import defpackage.nrh;
import defpackage.qrp;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class ClaimDataSimilarOrNotMatchFragment extends ClaimMeasureDataFragment implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f10169a;
    private View b;
    private HealthTextView c;
    private ClaimMeasureDataAdapter d;
    private LinearLayout e;
    private String f;
    private String g = "";
    private int h;
    private HealthRecycleView i;
    private ListView j;
    private List<cfi> k;
    private CustomViewDialog l;
    private cfi m;
    private LinearLayout n;
    private SelectUserAdapter o;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_claim_data_similar_or_notmatch, viewGroup, false);
        dEf_(inflate);
        e();
        return inflate;
    }

    private void e() {
        this.h = getArguments().getInt("pagetype");
        this.f = getArguments().getString("productId");
        if (this.h == 1) {
            this.c.setText(this.mActivity.getString(R$string.IDS_hw_weight_similar_hint));
        } else {
            this.c.setText(this.mActivity.getString(R$string.IDS_hw_claim_notmatch_hint, new Object[]{7}));
        }
        if (this.d == null) {
            ClaimMeasureDataAdapter claimMeasureDataAdapter = new ClaimMeasureDataAdapter(getActivity(), this.mSelectUserInterface);
            this.d = claimMeasureDataAdapter;
            this.i.setAdapter(claimMeasureDataAdapter);
        }
        setList();
    }

    private void dEf_(View view) {
        this.i = (HealthRecycleView) view.findViewById(R.id.claim_weight_data_recycler_view);
        this.c = (HealthTextView) view.findViewById(R.id.hw_health_data_claim_hint);
        this.f10169a = (LinearLayout) view.findViewById(R.id.hw_health_data_claim_layout);
        this.e = (LinearLayout) view.findViewById(R.id.hw_health_data_claim_empty_layout);
        this.i.a(false);
        this.i.d(false);
        this.i.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void setUserInterfaceAndHandler(SelectUserInterface selectUserInterface, ClaimMeasureDataActivity.b bVar) {
        this.mSelectUserInterface = selectUserInterface;
        this.mClaimHandler = bVar;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void setList() {
        ArrayList<csh> notMatchDataBean;
        ClaimMeasureDataAdapter claimMeasureDataAdapter = this.d;
        if (claimMeasureDataAdapter != null) {
            claimMeasureDataAdapter.e();
            if (this.h == 1) {
                notMatchDataBean = ClaimWeightDataManager.INSTANCE.getSimilarWeightDataBean();
            } else {
                notMatchDataBean = ClaimWeightDataManager.INSTANCE.getNotMatchDataBean();
            }
            if (koq.c(notMatchDataBean)) {
                LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "initData claimWeightDataBean not Empty size = ", Integer.valueOf(notMatchDataBean.size()));
                h();
                this.d.e(notMatchDataBean);
                return;
            }
            g();
            return;
        }
        g();
    }

    private void h() {
        this.f10169a.setVisibility(0);
        this.e.setVisibility(8);
    }

    private void g() {
        LinearLayout linearLayout = this.f10169a;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        LinearLayout linearLayout2 = this.e;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(0);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public int getListSize() {
        ClaimMeasureDataAdapter claimMeasureDataAdapter = this.d;
        if (claimMeasureDataAdapter != null) {
            return claimMeasureDataAdapter.a();
        }
        return 0;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void removeItem() {
        ClaimMeasureDataAdapter claimMeasureDataAdapter = this.d;
        if (claimMeasureDataAdapter != null) {
            claimMeasureDataAdapter.c();
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void setAllChooseItem(boolean z) {
        ClaimMeasureDataAdapter claimMeasureDataAdapter = this.d;
        if (claimMeasureDataAdapter != null) {
            claimMeasureDataAdapter.a(z);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void claimMeasureData() {
        this.g = "";
        d();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void sendStartClaimWeightData() {
        Message obtain = Message.obtain();
        obtain.what = 4;
        int c = this.o.c();
        if (koq.d(this.k, c)) {
            this.m = this.k.get(c);
            this.mClaimHandler.sendMessageDelayed(obtain, 200L);
        } else {
            LogUtil.b("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "sendClaimWeight out of userList bound:", Integer.valueOf(c));
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public boolean saveWeightData() {
        if (f()) {
            ReleaseLogUtil.c("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "unable to save weight data, return ");
            destroyLoadingDialog();
            return false;
        }
        ArrayList<csh> b = this.d.b();
        LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "saveWeightData mBean size,", Integer.valueOf(b.size()));
        b(this.m, b);
        saveSingleUserWeightData(this.m, b);
        return true;
    }

    private boolean f() {
        if (!super.saveWeightData()) {
            LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "saveWeightData : Network is not Connected!");
            return true;
        }
        ClaimMeasureDataAdapter claimMeasureDataAdapter = this.d;
        if (claimMeasureDataAdapter == null) {
            LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "saveWeightData mAdapter is null");
            return true;
        }
        if (!koq.b(claimMeasureDataAdapter.b())) {
            return false;
        }
        LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "selectedDatas is null or empty");
        return true;
    }

    private void b(cfi cfiVar, List<csh> list) {
        if (cfiVar == null || koq.b(list)) {
            return;
        }
        for (csh cshVar : list) {
            double min = Math.min(cshVar.d(), cfiVar.m());
            if (min != 0.0d && Math.abs(cshVar.d() - cfiVar.m()) / min >= 0.4d) {
                ReleaseLogUtil.d("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "claim weight data may be wrong, diff = ", Double.valueOf(cshVar.d() - cfiVar.m()));
            }
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void deleteWeightData() {
        ClaimMeasureDataAdapter claimMeasureDataAdapter = this.d;
        if (claimMeasureDataAdapter == null) {
            LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "deleteWeightData mAdapter is null");
            return;
        }
        ArrayList<csh> b = claimMeasureDataAdapter.b();
        if (koq.b(b)) {
            LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "deleteWeightData chooseDatas is null or empty");
        } else {
            deleteSelectedWeightData(b);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment
    public void claimFinishAndChangeUi(boolean z, String str) {
        super.claimFinishAndChangeUi(z, str);
        if (z) {
            removeItem();
            String str2 = this.g;
            if (str2 == null || str2.equals(str)) {
                return;
            }
            this.g = str;
            nrh.d(this.mActivity, this.mActivity.getString(R$string.IDS_hw_weight_claim_data_add_data, new Object[]{str}));
            return;
        }
        nrh.b(this.mActivity, R$string.IDS_update_server_bussy);
    }

    private void b() {
        CustomViewDialog customViewDialog = this.l;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
    }

    private void d() {
        a();
        this.b = View.inflate(getActivity(), R.layout.select_user, null);
        c();
        CustomViewDialog e = new CustomViewDialog.Builder(getActivity()).d(R$string.IDS_hw_weight_claim_data_select_user).czg_(this.b).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.fragment.ClaimDataSimilarOrNotMatchFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClaimDataSimilarOrNotMatchFragment.this.l.dismiss();
                int c = ClaimDataSimilarOrNotMatchFragment.this.o.c();
                if (koq.d(ClaimDataSimilarOrNotMatchFragment.this.k, c)) {
                    cfi cfiVar = (cfi) ClaimDataSimilarOrNotMatchFragment.this.k.get(c);
                    if (cfiVar != null) {
                        ClaimDataSimilarOrNotMatchFragment.this.d(cfiVar);
                    }
                } else {
                    LogUtil.b("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "initDialog out of userList bound:", Integer.valueOf(c));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.fragment.ClaimDataSimilarOrNotMatchFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClaimDataSimilarOrNotMatchFragment.this.l.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.l = e;
        e.setCancelable(false);
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(cfi cfiVar) {
        ArrayList<csh> b = this.d.b();
        if (koq.b(b)) {
            LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "deleteWeightData chooseDatas is null or empty");
            return;
        }
        String b2 = b(cfiVar);
        Iterator<csh> it = b.iterator();
        boolean z = true;
        while (it.hasNext()) {
            csh next = it.next();
            List<csi> b3 = next.b();
            if (koq.b(b3)) {
                LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "compareWeightDataUser conflictUserInfoList is empty");
            } else {
                LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "compareWeightDataUser conflictUserInfoList size is ", Integer.valueOf(b3.size()));
                for (csi csiVar : b3) {
                    if (TextUtils.equals(b2, csiVar.s())) {
                        saveConflictUserInfo(next, csiVar);
                        break;
                    }
                }
            }
            z = false;
        }
        if (z) {
            LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "compareWeightDataUser isAllMatchUser = true");
            sendClaimWeight();
        } else {
            e(cfiVar);
        }
    }

    private void e(final cfi cfiVar) {
        long currentTimeMillis = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(0L, currentTimeMillis);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "getChooseUserLastWeight userId:", CommonUtil.l(b(cfiVar)));
        hiAggregateOption.setFilter(b(cfiVar));
        hiAggregateOption.setCount(1);
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.ui.main.stories.health.fragment.ClaimDataSimilarOrNotMatchFragment.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "getChooseUserLastWeight onResult called");
                if (!koq.b(list)) {
                    ClaimDataSimilarOrNotMatchFragment.this.e(list, cfiVar);
                } else {
                    LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "getChooseUserLastWeight getWeight no data");
                    ClaimDataSimilarOrNotMatchFragment.this.e(null, cfiVar);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "getChooseUserLastWeight onResultIntent called");
            }
        });
    }

    private String b(cfi cfiVar) {
        return a(cfiVar) ? "0" : cfiVar.i();
    }

    private boolean a(cfi cfiVar) {
        String i = cfiVar.i();
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (i == null || "0".equals(i) || "".equals(i)) {
            LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "isMainUser");
            return true;
        }
        if (!Constants.NULL.equalsIgnoreCase(i) && !i.equals(accountInfo)) {
            return false;
        }
        LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "isMainUser");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list, cfi cfiVar) {
        if (koq.b(list)) {
            this.mClaimHandler.sendEmptyMessage(8);
            return;
        }
        double d = list.get(0).getDouble("bodyWeight");
        if (((int) d) == 0) {
            LogUtil.b("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "comparisonWeightClaim weight : 0");
            this.mClaimHandler.sendEmptyMessage(8);
            return;
        }
        ClaimMeasureDataAdapter claimMeasureDataAdapter = this.d;
        if (claimMeasureDataAdapter == null) {
            LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "compareWeightClaim mAdapter is null");
            return;
        }
        ArrayList<csh> b = claimMeasureDataAdapter.b();
        if (koq.b(b)) {
            LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "chooseDatas is null or empty");
            return;
        }
        Iterator<csh> it = b.iterator();
        while (it.hasNext()) {
            if (Math.abs(UnitUtil.a(d) - UnitUtil.a(it.next().d())) >= UnitUtil.a(5.0f)) {
                Message obtain = Message.obtain();
                obtain.what = 7;
                obtain.obj = cfiVar;
                this.mClaimHandler.sendMessage(obtain);
                return;
            }
        }
        this.mClaimHandler.sendEmptyMessage(8);
    }

    private void c() {
        HealthTextView healthTextView = (HealthTextView) this.b.findViewById(R.id.tv_claim_weight_data_creat_user_info);
        this.j = (ListView) this.b.findViewById(R.id.lv_claim_weight_data_select_user);
        this.n = (LinearLayout) this.b.findViewById(R.id.ll_claim_weight_data_select_user);
        this.j.setAdapter((ListAdapter) this.o);
        if (this.o.getCount() >= 10) {
            healthTextView.setVisibility(8);
        }
        if (b(this.f)) {
            healthTextView.setVisibility(8);
        }
        healthTextView.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tv_claim_weight_data_creat_user_info) {
            Intent intent = new Intent(getActivity(), (Class<?>) AddOrEditWeightUserActivity.class);
            intent.putExtra("weight_user_id_key", "");
            intent.putExtra("claimWeightData", true);
            startActivityForResult(intent, 0);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void j() {
        CustomViewDialog customViewDialog = this.l;
        if (customViewDialog == null || customViewDialog.isShowing()) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.n.getLayoutParams();
        layoutParams.height = dEe_(this.j, this.o);
        layoutParams.width = -1;
        this.n.setLayoutParams(layoutParams);
        this.l.show();
    }

    private int dEe_(ListView listView, BaseAdapter baseAdapter) {
        int Va_ = dij.Va_(listView);
        Object systemService = getActivity().getSystemService(com.huawei.openalliance.ad.constant.Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "calculateDialogHeight systemService is not WindowManager");
            return 0;
        }
        int height = (((WindowManager) systemService).getDefaultDisplay().getHeight() / 2) - qrp.a(getActivity(), 60.0f);
        if (Va_ >= height) {
            Va_ = height;
        }
        return Va_ + (baseAdapter.getCount() - 1);
    }

    private void a() {
        if (b(this.f)) {
            LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "herms only show main user");
            this.k = new ArrayList(16);
            cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
            if (mainUser == null) {
                LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "herms user is null");
                return;
            }
            if (TextUtils.isEmpty(mainUser.h())) {
                LogUtil.h("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "herms user name is null");
                mainUser.b(new UpApi(getActivity()).getAccountName());
            }
            this.k.add(mainUser);
        } else {
            this.k = MultiUsersManager.INSTANCE.getMainAllUser();
        }
        this.o = new SelectUserAdapter(getActivity(), this.k);
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser == null || TextUtils.isEmpty(currentUser.i())) {
            LogUtil.b("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "user.getUUIDOfUser() or user is null");
            return;
        }
        for (int i = 0; i < this.k.size(); i++) {
            if (currentUser.i().equals(this.k.get(i).i())) {
                this.o.c(i);
                return;
            }
        }
    }

    private boolean b(String str) {
        return !TextUtils.isEmpty(str) && "e835d102-af95-48a6-ae13-2983bc06f5c0".equals(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 1) {
            if (intent == null) {
                LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "onActivityResult mCurrentClaimMeasureData is null or data is null");
                return;
            }
            b();
            String stringExtra = intent.getStringExtra(HwPayConstant.KEY_USER_ID);
            if (TextUtils.isEmpty(stringExtra)) {
                LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "onActivityResult userId is null");
                return;
            }
            cfi singleUserById = MultiUsersManager.INSTANCE.getSingleUserById(stringExtra);
            if (singleUserById == null) {
                LogUtil.a("HealthWeight_ClaimDataSimilarOrNotMatchFragment", "onActivityResult user is null");
                return;
            }
            if (isConnectNetWork()) {
                this.m = singleUserById;
                Message obtain = Message.obtain();
                obtain.what = 5;
                obtain.arg1 = 0;
                this.mClaimHandler.sendMessage(obtain);
                Message obtain2 = Message.obtain();
                obtain2.what = 4;
                obtain2.obj = singleUserById;
                this.mClaimHandler.sendMessageDelayed(obtain2, 200L);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.d != null) {
            this.d = null;
        }
        if (this.k != null) {
            this.k = null;
        }
        if (this.o != null) {
            this.o = null;
        }
    }
}
