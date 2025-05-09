package com.huawei.ui.main.stories.privacy.template.view.showdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.template.model.adapter.BloodOxygenDayAdapter;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.model.config.PrivacyDataFragmentConfig;
import com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment;
import com.huawei.ui.main.stories.privacy.template.view.component.PrivacyDayDataView;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDayDataFragment;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.rkb;
import defpackage.rsh;
import defpackage.rsr;
import defpackage.rtv;
import java.util.List;

/* loaded from: classes7.dex */
public class PrivacyDayDataFragment extends PrivacyDataDetailMvpFragment<rsh> {

    /* renamed from: a, reason: collision with root package name */
    private PrivacyDayDataView f10434a;
    private List<PrivacyDataModel> b;
    private LinearLayout c;
    private DayDataViewAdapter d;
    private RelativeLayout e;
    private TextView h;

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void clickSpinner(int i) {
    }

    public static PrivacyDayDataFragment dQL_(PrivacyDataFragmentConfig privacyDataFragmentConfig, PageModelArgs pageModelArgs, Parcelable parcelable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_config", privacyDataFragmentConfig);
        bundle.putParcelable("extra_page_model_args", pageModelArgs);
        bundle.putParcelable("extra_source_info", parcelable);
        PrivacyDayDataFragment privacyDayDataFragment = new PrivacyDayDataFragment();
        privacyDayDataFragment.setArguments(bundle);
        return privacyDayDataFragment;
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.privacy_fragment_data, viewGroup, false);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment, com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initViews(View view) {
        super.initViews(view);
        if (this.mPageType == 105 && this.mDataSource == 3) {
            this.d = new BloodOxygenDayAdapter();
        } else {
            this.d = new DayDataViewAdapter();
        }
        this.e = (RelativeLayout) view.findViewById(R.id.privacy_data_loading);
        this.c = (LinearLayout) view.findViewById(R.id.no_data_source);
        this.h = (TextView) view.findViewById(R.id.no_data_text);
        initComponent((LinearLayout) view.findViewById(R.id.privacy_data_fragment_container));
        if (this.mComponents instanceof PrivacyDayDataView) {
            this.f10434a = (PrivacyDayDataView) this.mComponents;
            if (this.mPageType == 105 && this.mDataSource == 3) {
                this.f10434a.setCardViewBackground(null);
            } else {
                this.f10434a.setCardViewBackground(getResources().getDrawable(R.drawable._2131431106_res_0x7f0b0ec2, null));
            }
        }
        this.f10434a.setAdapter(this.d);
        this.d.a(new DayDataViewAdapter.OnItemClickListener() { // from class: rtl
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter.OnItemClickListener
            public final void onItemClickListener(int i) {
                PrivacyDayDataFragment.this.b(i);
            }
        });
        this.d.d(new DayDataViewAdapter.OnLongClickListener() { // from class: rtn
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter.OnLongClickListener
            public final void onLongClickListener(int i) {
                PrivacyDayDataFragment.this.e(i);
            }
        });
    }

    public /* synthetic */ void e(int i) {
        if (isSupportLongClick()) {
            this.d.b(i);
            showDelConfirmDialog(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (this.mToolBarManager.e() != 1) {
            this.d.b(i);
            setTitleBarState(2);
            if (getCheckedCount() == getTotalCount()) {
                this.mToolBarManager.b(4);
                return;
            } else {
                this.mToolBarManager.b(2);
                return;
            }
        }
        if (nsn.a(500)) {
            return;
        }
        e(this.d.d(i));
    }

    private void e(PrivacyDataModel privacyDataModel) {
        int b;
        if (this.mPageType == 105 && this.mDataSource == 3 && (b = rtv.b(1002, privacyDataModel.getInt("bloodOxygenKey"))) != 0) {
            rtv.c(2, 2, b);
        }
        if (this.mPageType == 106 || this.mPageType == 113) {
            rtv.c();
        }
        Intent intent = new Intent();
        intent.putExtra("extra_source_info", this.mSourceInfoBean);
        intent.putExtra("extra_privacy_data_model", privacyDataModel);
        intent.putExtra("extra_page_model_args", this.mPageModelArgs);
        intent.setClass(getViewContext(), PrivacyDetailActivity.class);
        getViewContext().startActivity(intent);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public boolean isShowDeleteBtn() {
        return rsr.d(this.mPageModelArgs) && hasData();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public boolean isSupportLongClick() {
        return rsr.d(this.mPageModelArgs) && this.mToolBarManager.e() == 1;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public boolean hasData() {
        return !koq.b(this.b);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void onDeleteBtnClicked() {
        setUiState(2);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void setAllChecked(boolean z) {
        this.d.c(z);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void deleteDate() {
        rkb rkbVar = new rkb(this.mClassType, this.mDeviceUuid, this.mPackageName);
        rkbVar.e("categoryType", this.mPageModelArgs.getInt("categoryType"));
        this.mDataManager.b(this.d.b(), rkbVar, new DataSourceCallback() { // from class: rtk
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                PrivacyDayDataFragment.this.e(i, (Boolean) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void e(int i, Boolean bool) {
        LogUtil.a("PrivacyDayDataFragment", "deleteData callBack code : ", Integer.valueOf(i));
        if (getActivity() == null) {
            return;
        }
        nrh.b(BaseApplication.getContext(), i == 0 ? R.string.IDS_device_wifi_clear_user_success : R.string.IDS_device_wifi_delete_fail);
        ((rsh) getPresenter()).d(this.mPageModelArgs);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setResult(-1);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public int getCheckedCount() {
        return this.d.e();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public int getTotalCount() {
        return this.d.c();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void onToolBarStatusChanged(int i) {
        if (i == 1) {
            this.d.e(1);
            return;
        }
        if (i == 2) {
            this.d.e(2);
        } else if (i == 3) {
            setAllChecked(false);
        } else {
            if (i != 4) {
                return;
            }
            setAllChecked(true);
        }
    }

    public void e(int i, List<PrivacyDataModel> list) {
        Object[] objArr = new Object[4];
        objArr[0] = "addAllData resultCode = ";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = ", data size = ";
        objArr[3] = Integer.valueOf(list == null ? 0 : list.size());
        LogUtil.a("PrivacyDayDataFragment", objArr);
        this.b = list;
        this.e.setVisibility(8);
        List<PrivacyDataModel> list2 = this.b;
        if (list2 == null || list2.isEmpty()) {
            if (i == 0) {
                this.h.setText(getResources().getString(R$string.IDS_hwh_privacy_no_details));
            } else {
                this.h.setText(getResources().getString(R$string.IDS_setting_wearhome_obtain_failed));
            }
            this.c.setVisibility(0);
            showRtlLanguage();
        } else {
            this.d.d(this.b);
        }
        if (rsr.d(this.mPageModelArgs)) {
            setUiState(1);
        }
    }
}
