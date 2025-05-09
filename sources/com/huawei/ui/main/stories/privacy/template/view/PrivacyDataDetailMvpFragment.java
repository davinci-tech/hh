package com.huawei.ui.main.stories.privacy.template.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.spinner.HealthSpinner;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.contract.PrivacyDetailFragmentContract;
import com.huawei.ui.main.stories.privacy.template.model.adapter.SourceSpinnerAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.SourceInfoBean;
import com.huawei.ui.main.stories.privacy.template.model.config.PrivacyDataFragmentConfig;
import com.huawei.ui.main.stories.privacy.template.util.PrivacyToolBarManager;
import com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment;
import com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.template.health.HealthMvpFragment;
import com.huawei.ui.main.stories.template.health.config.HealthCommonExpandViewConfig;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.row;
import defpackage.rsk;
import defpackage.rso;
import defpackage.rsp;
import defpackage.rsr;
import defpackage.rtv;
import health.compact.a.LanguageUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public abstract class PrivacyDataDetailMvpFragment<P extends rso> extends HealthMvpFragment<P> implements PrivacyDetailFragmentContract.PrivacyFragmentView {
    private static final String TAG = "PrivacyDataDetailMvpFragment";
    protected int mClassType;
    protected BaseComponent mComponents;
    protected CustomTitleBar mCustomTitleBar;
    protected row mDataManager;
    protected int mDataSource;
    protected String mDeviceUuid;
    protected boolean mIsOtherManufacturer;
    protected int mModuleType;
    private ImageView mNoDataImage;
    protected HealthToolBar.OnSingleTapListener mOnSingleTapListener = new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment.5
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public void onSingleTap(int i) {
            if (i == 1) {
                PrivacyDataDetailMvpFragment.this.onToolBarLeftClicked();
            } else {
                if (i != 3) {
                    return;
                }
                PrivacyDataDetailMvpFragment.this.onToolBarRightClicked();
            }
        }
    };
    protected PrivacyToolBarManager.OnStatusChangedListener mOnStatusChangedListener = new PrivacyToolBarManager.OnStatusChangedListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment.3
        @Override // com.huawei.ui.main.stories.privacy.template.util.PrivacyToolBarManager.OnStatusChangedListener
        public void onStatusChanged(int i) {
            PrivacyDataDetailMvpFragment.this.onToolBarStatusChanged(i);
        }
    };
    protected String mPackageName;
    public PageModelArgs mPageModelArgs;
    public int mPageType;
    protected PrivacyDataModelActivity mPrivacyDataModelActivity;
    private PrivacyDataFragmentConfig mPrivacyFragmentConfig;
    protected SourceInfoBean mSourceInfoBean;
    protected CustomTitleBar mSpinnerCustomTitleBar;
    protected int mSubPageType;
    private HealthToolBar mToolBar;
    protected PrivacyToolBarManager mToolBarManager;

    protected abstract void clickSpinner(int i);

    protected abstract void deleteDate();

    protected abstract int getCheckedCount();

    protected abstract int getTotalCount();

    protected abstract boolean hasData();

    protected abstract boolean isShowDeleteBtn();

    protected abstract boolean isSupportLongClick();

    protected abstract void onDeleteBtnClicked();

    protected abstract void onToolBarStatusChanged(int i);

    protected abstract void setAllChecked(boolean z);

    public void setDeleteBtnVisibility(int i) {
        this.mCustomTitleBar.setRightButtonVisibility(i);
        this.mSpinnerCustomTitleBar.setRightButtonVisibility(i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initViews(View view) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.mPrivacyFragmentConfig = (PrivacyDataFragmentConfig) arguments.getParcelable("extra_config");
        this.mSourceInfoBean = (SourceInfoBean) arguments.getParcelable("extra_source_info");
        PageModelArgs pageModelArgs = (PageModelArgs) arguments.getParcelable("extra_page_model_args");
        this.mPageModelArgs = pageModelArgs;
        if (pageModelArgs == null) {
            LogUtil.b(TAG, "Privacy mPageModelArgs is null exception");
            return;
        }
        this.mIsOtherManufacturer = pageModelArgs.isOtherManufacturer();
        this.mDataSource = this.mPageModelArgs.getDataSource();
        this.mModuleType = this.mPageModelArgs.getModuleType();
        this.mPageType = this.mPageModelArgs.getPageType();
        this.mSubPageType = this.mPageModelArgs.getInt("Subpage");
        this.mPackageName = this.mPageModelArgs.getPackageName();
        this.mDeviceUuid = this.mPageModelArgs.getUuid();
        int classType = this.mPageModelArgs.getClassType();
        this.mClassType = classType;
        if (classType == 3) {
            this.mClassType = 2;
        }
        this.mDataManager = new row(this.mPageType);
        this.mNoDataImage = (ImageView) view.findViewById(R.id.no_data_image);
        HealthToolBar healthToolBar = (HealthToolBar) view.findViewById(R.id.privacy_detail_tool_bar);
        this.mToolBar = healthToolBar;
        PrivacyToolBarManager privacyToolBarManager = new PrivacyToolBarManager(healthToolBar, this.mOnSingleTapListener);
        this.mToolBarManager = privacyToolBarManager;
        privacyToolBarManager.c(this.mOnStatusChangedListener);
        initTitleBar();
        fixSpinnerPadding(this.mSpinnerCustomTitleBar);
    }

    private void initTitleBar() {
        if (getActivity() instanceof PrivacyDataModelActivity) {
            PrivacyDataModelActivity privacyDataModelActivity = (PrivacyDataModelActivity) getActivity();
            this.mPrivacyDataModelActivity = privacyDataModelActivity;
            CustomTitleBar customTitleBar = privacyDataModelActivity.getCustomTitleBar();
            this.mCustomTitleBar = customTitleBar;
            customTitleBar.setRightButtonDrawable(rsr.dQH_(R.drawable._2131430279_res_0x7f0b0b87), nsf.h(R$string.IDS_contact_delete));
            this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PrivacyDataDetailMvpFragment.this.onDeleteBtnClicked();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomTitleBar spinnerCustomTitleBar = this.mPrivacyDataModelActivity.getSpinnerCustomTitleBar();
            this.mSpinnerCustomTitleBar = spinnerCustomTitleBar;
            spinnerCustomTitleBar.setRightButtonDrawable(rsr.dQH_(R.drawable._2131430279_res_0x7f0b0b87), nsf.h(R$string.IDS_contact_delete));
            this.mSpinnerCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PrivacyDataDetailMvpFragment.this.onDeleteBtnClicked();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.template.BaseView
    public Context getViewContext() {
        return getActivity();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void initComponent(LinearLayout linearLayout) {
        addExpandedViewsIfNeeded(this.mPrivacyFragmentConfig.getLayoutConfig().getListView(), linearLayout);
        ((rso) getPresenter()).addComponents(this.mComponents);
    }

    private void addExpandedViewsIfNeeded(HealthCommonExpandViewConfig healthCommonExpandViewConfig, LinearLayout linearLayout) {
        if (healthCommonExpandViewConfig == null) {
            return;
        }
        String newComponent = healthCommonExpandViewConfig.getNewComponent();
        if (TextUtils.isEmpty(newComponent)) {
            return;
        }
        BaseComponent a2 = rsk.a(newComponent, getContext());
        this.mComponents = a2;
        if (a2 != null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            this.mComponents.setPresenter((PrivacyDetailFragmentContract.PrivacyFragmentPresenter) getPresenter());
            linearLayout.addView(this.mComponents.getView(getContext()), layoutParams);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initData() {
        if (this.mPageModelArgs == null) {
            LogUtil.h(TAG, "PageModelArgs null exception");
        } else {
            ((rso) getPresenter()).d(this.mPageModelArgs);
        }
    }

    public boolean onBackPressed() {
        if (this.mToolBarManager.e() == 1) {
            return false;
        }
        setUiState(1);
        return true;
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public P onCreatePresenter() {
        return (P) rsp.b(this.mPrivacyFragmentConfig.getFragmentPresenter(), this);
    }

    public void showDelConfirmDialog(final boolean z) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(requireContext()).e(requireContext().getString(R$string.IDS_hw_health_show_healthdata_delete)).czE_(requireContext().getString(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: rsq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyDataDetailMvpFragment.this.m854xb5d7173c(view);
            }
        }).czA_(requireContext().getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: rss
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyDataDetailMvpFragment.this.m855xbcfff97d(z, view);
            }
        }).e();
        e.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && keyEvent.getAction() == 1 && z) {
                    PrivacyDataDetailMvpFragment.this.setAllChecked(false);
                }
                return false;
            }
        });
        e.setCancelable(false);
        e.show();
    }

    /* renamed from: lambda$showDelConfirmDialog$0$com-huawei-ui-main-stories-privacy-template-view-PrivacyDataDetailMvpFragment, reason: not valid java name */
    public /* synthetic */ void m854xb5d7173c(View view) {
        if (rsr.e(this.mPageModelArgs) && !NetworkUtil.j()) {
            Toast.makeText(BaseApplication.getContext(), getResources().getString(R$string.IDS_privacy_network_error), 0).show();
        } else {
            deleteAllDataBI();
            deleteDate();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showDelConfirmDialog$1$com-huawei-ui-main-stories-privacy-template-view-PrivacyDataDetailMvpFragment, reason: not valid java name */
    public /* synthetic */ void m855xbcfff97d(boolean z, View view) {
        if (this.mPageType == 103) {
            rtv.e(3, getTotalCount() == getCheckedCount());
        }
        if (z) {
            setAllChecked(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void deleteAllDataBI() {
        int i = this.mPageType;
        if (i == 106 || i == 113) {
            if (this.mModuleType == 2) {
                rtv.b();
            } else {
                rtv.e();
            }
        }
        if (this.mPageType == 103) {
            rtv.e(2, getTotalCount() == getCheckedCount());
        }
        if (this.mPageType == 107) {
            rtv.d();
        }
    }

    public void setUiState(int i) {
        PrivacyToolBarManager privacyToolBarManager = this.mToolBarManager;
        if (privacyToolBarManager != null) {
            privacyToolBarManager.b(i);
            setTitleBarState(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onToolBarLeftClicked() {
        if (getCheckedCount() == 0) {
            return;
        }
        showDelConfirmDialog(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onToolBarRightClicked() {
        if (this.mToolBarManager.e() == 4) {
            setUiState(3);
        } else {
            setUiState(4);
        }
    }

    protected void setTitleBarState(int i) {
        if (i == 1) {
            setTitleBarNotEditStatus();
            return;
        }
        if (i == 2) {
            setTitleBarEditingStatus();
        } else if (i == 3) {
            setTitleBarCancelCheckedStatus();
        } else {
            if (i != 4) {
                return;
            }
            setTitleBarAllCheckedStatus();
        }
    }

    protected void setTitleBarNotEditStatus() {
        setDeleteBtnVisibility(isShowDeleteBtn() ? 0 : 8);
        if (rsr.h(this.mPageModelArgs)) {
            this.mCustomTitleBar.setAppBarVisible(false);
            this.mSpinnerCustomTitleBar.setAppBarVisible(true);
        } else {
            this.mCustomTitleBar.setLeftButtonDrawable(rsr.dQH_(R.mipmap._2131820919_res_0x7f110177), nsf.h(R$string.accessibility_go_back));
            this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: rst
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PrivacyDataDetailMvpFragment.this.m853x8a5ba5f6(view);
                }
            });
            this.mCustomTitleBar.setTitleText(this.mPrivacyDataModelActivity.getTitleBarContent());
        }
    }

    /* renamed from: lambda$setTitleBarNotEditStatus$2$com-huawei-ui-main-stories-privacy-template-view-PrivacyDataDetailMvpFragment, reason: not valid java name */
    public /* synthetic */ void m853x8a5ba5f6(View view) {
        PrivacyDataModelActivity privacyDataModelActivity = this.mPrivacyDataModelActivity;
        if (privacyDataModelActivity != null) {
            privacyDataModelActivity.onBackPressed();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void setTitleBarEditingStatus() {
        setDeleteBtnVisibility(8);
        this.mSpinnerCustomTitleBar.setAppBarVisible(false);
        this.mCustomTitleBar.setAppBarVisible(true);
        this.mCustomTitleBar.setLeftButtonDrawable(rsr.dQH_(R.drawable._2131430298_res_0x7f0b0b9a), nsf.h(R$string.accessibility_close));
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: rsv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyDataDetailMvpFragment.this.m852x254ce412(view);
            }
        });
        int checkedCount = getCheckedCount();
        if (checkedCount == 0) {
            this.mCustomTitleBar.setTitleText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_not_select));
        } else {
            this.mCustomTitleBar.setTitleText(getResources().getQuantityString(R.plurals._2130903064_res_0x7f030018, checkedCount, Integer.valueOf(checkedCount)));
        }
    }

    /* renamed from: lambda$setTitleBarEditingStatus$3$com-huawei-ui-main-stories-privacy-template-view-PrivacyDataDetailMvpFragment, reason: not valid java name */
    public /* synthetic */ void m852x254ce412(View view) {
        setUiState(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void setTitleBarCancelCheckedStatus() {
        this.mCustomTitleBar.setTitleText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_not_select));
    }

    protected void setTitleBarAllCheckedStatus() {
        int totalCount = getTotalCount();
        this.mCustomTitleBar.setTitleText(getResources().getQuantityString(R.plurals._2130903064_res_0x7f030018, totalCount, Integer.valueOf(totalCount)));
    }

    protected void showRtlLanguage() {
        if (LanguageUtil.bc(getViewContext())) {
            this.mNoDataImage.setBackground(nrz.cKn_(getViewContext(), R.drawable._2131430620_res_0x7f0b0cdc));
        } else {
            this.mNoDataImage.setBackgroundResource(R.drawable._2131430620_res_0x7f0b0cdc);
        }
    }

    protected void initSpinner(List<String> list, int i) {
        HealthSpinner titleSpinner = this.mSpinnerCustomTitleBar.getTitleSpinner();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.health_commonui_spinner_item_appbar, list);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_item);
        if (titleSpinner != null) {
            titleSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            titleSpinner.setSelection(i, true);
            titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment.7
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                    PrivacyDataDetailMvpFragment.this.clickSpinner(i2);
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                }
            });
        }
    }

    protected void initSpinnerWithIcon(List<Map<String, String>> list, int i) {
        HealthSpinner titleSpinner = this.mSpinnerCustomTitleBar.getTitleSpinner();
        SourceSpinnerAdapter sourceSpinnerAdapter = new SourceSpinnerAdapter(list);
        if (titleSpinner != null) {
            titleSpinner.setAdapter((SpinnerAdapter) sourceSpinnerAdapter);
            titleSpinner.setSelection(i, true);
            titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment.8
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                    PrivacyDataDetailMvpFragment.this.clickSpinner(i2);
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                }
            });
        }
    }

    protected void fixSpinnerPadding(CustomTitleBar customTitleBar) {
        HealthSpinner titleSpinner = customTitleBar.getTitleSpinner();
        if (titleSpinner == null) {
            return;
        }
        titleSpinner.setPadding(0, titleSpinner.getPaddingTop(), titleSpinner.getPaddingRight(), titleSpinner.getPaddingBottom());
    }
}
