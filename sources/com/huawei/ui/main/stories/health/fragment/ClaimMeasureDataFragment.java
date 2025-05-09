package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity;
import com.huawei.ui.main.stories.health.interactors.healthdata.SelectUserInterface;
import defpackage.cfi;
import defpackage.csh;
import defpackage.csi;
import defpackage.ctv;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public abstract class ClaimMeasureDataFragment extends BaseFragment {
    public static final String CLAIM_DATA_FINISH_RESULT = "claimresult";
    public static final String CLAIM_DATA_FINISH_USER_ID = "claimUserId";
    public static final String CLAIM_DATA_FINISH_USER_NAME = "claimusername";
    private static final int RESISTANCE_DIVIDER = 10;
    private static final String TAG = "ClaimMeasureDataFragment";
    protected FragmentActivity mActivity;
    protected ClaimMeasureDataActivity.b mClaimHandler;
    private CommonDialog21 mLoadingDialog;
    protected SelectUserInterface mSelectUserInterface;

    public abstract void claimMeasureData();

    public abstract void deleteWeightData();

    public abstract int getListSize();

    public abstract void removeItem();

    public abstract void sendStartClaimWeightData();

    public abstract void setAllChooseItem(boolean z);

    public abstract void setList();

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            setAllChooseItem(false);
        }
    }

    public void setUserInterfaceAndHandler(SelectUserInterface selectUserInterface, ClaimMeasureDataActivity.b bVar) {
        this.mSelectUserInterface = selectUserInterface;
        this.mClaimHandler = bVar;
    }

    public void sendClaimWeight() {
        if (isConnectNetWork()) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.arg1 = 0;
            this.mClaimHandler.sendMessage(obtain);
            sendStartClaimWeightData();
        }
    }

    public boolean saveWeightData() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            return true;
        }
        LogUtil.h(TAG, "saveWeightData : Network is not Connected!");
        nrh.e(this.mActivity, R$string.IDS_confirm_network_whether_connected);
        return false;
    }

    protected void saveSingleUserWeightData(final cfi cfiVar, ArrayList<csh> arrayList) {
        if (cfiVar == null || koq.b(arrayList)) {
            LogUtil.h(TAG, "saveSingleUserWeightData currentUser is null or selectedDatas is empty");
            return;
        }
        String i = MultiUsersManager.INSTANCE.getCurrentUser().i();
        String i2 = cfiVar.i();
        final boolean equals = TextUtils.equals(i2, MultiUsersManager.INSTANCE.getMainUser().i());
        final boolean z = !TextUtils.equals(i, i2);
        LogUtil.a(TAG, "currentUser has been changed ? isCurrentUserChanged = ", Boolean.valueOf(z), ", currentUserIsMain = ", Boolean.valueOf(equals));
        this.mClaimHandler.removeMessages(6);
        ClaimWeightDataManager.INSTANCE.claimWeightData(arrayList, cfiVar, LoginInit.getInstance(this.mActivity).getAccountInfo(1011), new WeightInsertStatusCallback() { // from class: com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment.3
            @Override // com.huawei.health.device.callback.WeightInsertStatusCallback
            public void isSuccess(boolean z2) {
                ReleaseLogUtil.e(ClaimMeasureDataFragment.TAG, "claim weight data isSuccess ", Boolean.valueOf(z2));
                if (z2) {
                    ClaimMeasureDataFragment.this.mClaimHandler.sendEmptyMessageDelayed(6, 15000L);
                    ClaimWeightDataManager.INSTANCE.startSync();
                    if (z) {
                        LogUtil.a(ClaimMeasureDataFragment.TAG, "refresh the BaseHealthDataActivity");
                        ClaimWeightDataManager.INSTANCE.onCurrentUserChanged(equals);
                    }
                }
                Message obtain = Message.obtain();
                obtain.what = 0;
                Bundle bundle = new Bundle();
                bundle.putBoolean(ClaimMeasureDataFragment.CLAIM_DATA_FINISH_RESULT, z2);
                bundle.putString(ClaimMeasureDataFragment.CLAIM_DATA_FINISH_USER_NAME, cfiVar.h());
                bundle.putString(ClaimMeasureDataFragment.CLAIM_DATA_FINISH_USER_ID, cfiVar.i());
                obtain.setData(bundle);
                ClaimMeasureDataFragment.this.mClaimHandler.sendMessage(obtain);
            }
        });
    }

    protected void deleteSelectedWeightData(ArrayList<csh> arrayList) {
        ClaimWeightDataManager.INSTANCE.deleteWeightData(arrayList, this.mActivity, new HiDataOperateListener() { // from class: com.huawei.ui.main.stories.health.fragment.ClaimMeasureDataFragment.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                    ClaimMeasureDataFragment.this.mClaimHandler.sendEmptyMessage(3);
                    LogUtil.a(ClaimMeasureDataFragment.TAG, "deleteCheckData ErrorConstants.SUCCESS");
                } else {
                    LogUtil.h(ClaimMeasureDataFragment.TAG, "deleteCheckData ErrorConstants.ERR_NONE");
                }
            }
        });
    }

    protected void saveConflictUserInfo(csh cshVar, csi csiVar) {
        LogUtil.a(TAG, "saveConflictUserInfo userInfo to dataBean");
        HiHealthData c = cshVar.c();
        c.putDouble(BleConstants.BODY_FAT_RATE, csiVar.e() / 10.0d);
        c.putDouble(IndoorEquipManagerApi.KEY_HEART_RATE, csiVar.h());
        c.putDouble("age", csiVar.c());
        c.putInt("height", csiVar.d());
        c.putInt(CommonConstant.KEY_GENDER, csiVar.b());
        c.putDouble("lfrfHfImpedance", csiVar.f() / 10.0d);
        c.putDouble("lhrhHfImpedance", csiVar.k() / 10.0d);
        c.putDouble("lhlfHfImpedance", csiVar.g() / 10.0d);
        c.putDouble("lhrfHfImpedance", csiVar.m() / 10.0d);
        c.putDouble("rhlfHfImpedance", csiVar.q() / 10.0d);
        c.putDouble("rhrfHfImpedance", csiVar.r() / 10.0d);
        c.putDouble("lfrfImpedance", csiVar.j() / 10.0d);
        c.putDouble("lhrhImpedance", csiVar.o() / 10.0d);
        c.putDouble("lhlfImpedance", csiVar.i() / 10.0d);
        c.putDouble("lhrfImpedance", csiVar.l() / 10.0d);
        c.putDouble("rhlfImpedance", csiVar.n() / 10.0d);
        c.putDouble("rhrfImpedance", csiVar.t() / 10.0d);
    }

    protected boolean isConnectNetWork() {
        Context context = BaseApplication.getContext();
        if (ctv.d(context)) {
            return true;
        }
        nrh.b(context, R$string.IDS_device_wifi_not_network);
        return false;
    }

    public void claimFinishAndChangeUi(boolean z, String str) {
        destroyLoadingDialog();
    }

    public void showDialog() {
        if (this.mLoadingDialog == null) {
            new CommonDialog21(this.mActivity, R.style.app_update_dialogActivity);
            this.mLoadingDialog = CommonDialog21.a(this.mActivity);
        }
        this.mLoadingDialog.setCancelable(false);
        this.mLoadingDialog.e(getString(R$string.IDS_hw_weight_claim_data_claiming));
        this.mLoadingDialog.a();
    }

    public void destroyLoadingDialog() {
        CommonDialog21 commonDialog21 = this.mLoadingDialog;
        if (commonDialog21 == null || !commonDialog21.isShowing() || this.mActivity.isFinishing()) {
            return;
        }
        this.mLoadingDialog.dismiss();
        this.mLoadingDialog = null;
    }
}
