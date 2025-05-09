package com.huawei.ui.main.stories.privacy.template.view.showdata;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.util.PrivacyToolBarManager;
import com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.row;
import defpackage.rsr;
import defpackage.rtv;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public abstract class CategoryCommonLayout extends RelativeLayout {
    protected int mClassType;
    protected CustomTitleBar mCustomTitleBar;
    protected row mDataManager;
    protected PrivacyDataModelActivity mDataModelActivity;
    protected String mDeviceUuid;
    protected boolean mIsOtherManufacturer;
    private ImageView mNoDataImage;
    protected HealthToolBar.OnSingleTapListener mOnSingleTapListener;
    protected PrivacyToolBarManager.OnStatusChangedListener mOnStatusChangedListener;
    protected String mPackageName;
    protected PageModelArgs mPageModelArgs;
    protected int mPageType;
    protected CustomTitleBar mSpinnerCustomTitleBar;
    protected PrivacyToolBarManager mToolBarManager;

    protected abstract void deleteDate();

    protected abstract int getCheckedCount();

    protected abstract int getLayoutId();

    protected abstract int getTotalCount();

    protected abstract boolean hasData();

    public abstract void onToolBarStatusChanged(int i);

    protected abstract void setAllChecked(boolean z);

    /* renamed from: lambda$new$0$com-huawei-ui-main-stories-privacy-template-view-showdata-CategoryCommonLayout, reason: not valid java name */
    public /* synthetic */ void m856xe9d7cc05(int i) {
        if (i == 1) {
            onToolBarLeftClicked();
        } else {
            if (i != 3) {
                return;
            }
            onToolBarRightClicked();
        }
    }

    public CategoryCommonLayout(Context context, Activity activity, PageModelArgs pageModelArgs) {
        super(context);
        this.mOnSingleTapListener = new HealthToolBar.OnSingleTapListener() { // from class: rtb
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i) {
                CategoryCommonLayout.this.m856xe9d7cc05(i);
            }
        };
        this.mOnStatusChangedListener = new PrivacyToolBarManager.OnStatusChangedListener() { // from class: rsz
            @Override // com.huawei.ui.main.stories.privacy.template.util.PrivacyToolBarManager.OnStatusChangedListener
            public final void onStatusChanged(int i) {
                CategoryCommonLayout.this.onToolBarStatusChanged(i);
            }
        };
        if (activity instanceof PrivacyDataModelActivity) {
            this.mDataModelActivity = (PrivacyDataModelActivity) activity;
        }
        this.mPageModelArgs = pageModelArgs;
        if (pageModelArgs != null) {
            this.mIsOtherManufacturer = pageModelArgs.isOtherManufacturer();
            this.mPageType = this.mPageModelArgs.getPageType();
            this.mPackageName = this.mPageModelArgs.getPackageName();
            this.mDeviceUuid = this.mPageModelArgs.getUuid();
            int classType = this.mPageModelArgs.getClassType();
            this.mClassType = classType;
            if (classType == 3) {
                this.mClassType = 2;
            }
            this.mDataManager = new row(this.mPageType);
        }
    }

    public boolean isShowDeleteBtn() {
        return rsr.a(this.mPageModelArgs) && hasData();
    }

    public void setDeleteBtnVisibility(int i) {
        this.mCustomTitleBar.setRightButtonVisibility(i);
        this.mSpinnerCustomTitleBar.setRightButtonVisibility(i);
    }

    public boolean onBackPressed() {
        if (this.mToolBarManager.e() == 1) {
            return false;
        }
        setUiState(1);
        return true;
    }

    protected void initViews() {
        LayoutInflater.from(getContext()).inflate(getLayoutId(), this);
        this.mNoDataImage = (ImageView) findViewById(R.id.no_data_image);
        PrivacyToolBarManager privacyToolBarManager = new PrivacyToolBarManager((HealthToolBar) findViewById(R.id.privacy_detail_tool_bar), this.mOnSingleTapListener);
        this.mToolBarManager = privacyToolBarManager;
        privacyToolBarManager.c(this.mOnStatusChangedListener);
        PrivacyDataModelActivity privacyDataModelActivity = this.mDataModelActivity;
        if (privacyDataModelActivity != null) {
            this.mCustomTitleBar = privacyDataModelActivity.getCustomTitleBar();
            this.mSpinnerCustomTitleBar = this.mDataModelActivity.getSpinnerCustomTitleBar();
        }
    }

    protected boolean isSupportLongClick() {
        return rsr.a(this.mPageModelArgs) && this.mToolBarManager.e() == 1;
    }

    protected void showDelConfirmDialog(final boolean z) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(getContext()).e(getContext().getString(R$string.IDS_hw_health_show_healthdata_delete)).czE_(getContext().getString(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: rsy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CategoryCommonLayout.this.m859xf65027f0(view);
            }
        }).czA_(getContext().getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: rsw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CategoryCommonLayout.this.m860xd4438dcf(z, view);
            }
        }).e();
        e.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: rsx
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return CategoryCommonLayout.this.m861xb236f3ae(z, dialogInterface, i, keyEvent);
            }
        });
        e.show();
    }

    /* renamed from: lambda$showDelConfirmDialog$1$com-huawei-ui-main-stories-privacy-template-view-showdata-CategoryCommonLayout, reason: not valid java name */
    public /* synthetic */ void m859xf65027f0(View view) {
        if (this.mPageType == 106) {
            rtv.e();
        }
        deleteDate();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showDelConfirmDialog$2$com-huawei-ui-main-stories-privacy-template-view-showdata-CategoryCommonLayout, reason: not valid java name */
    public /* synthetic */ void m860xd4438dcf(boolean z, View view) {
        if (z) {
            setAllChecked(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showDelConfirmDialog$3$com-huawei-ui-main-stories-privacy-template-view-showdata-CategoryCommonLayout, reason: not valid java name */
    public /* synthetic */ boolean m861xb236f3ae(boolean z, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 1 && z) {
            setAllChecked(false);
        }
        return false;
    }

    protected void setUiState(int i) {
        PrivacyToolBarManager privacyToolBarManager = this.mToolBarManager;
        if (privacyToolBarManager != null) {
            privacyToolBarManager.b(i);
            setTitleBarState(i);
        }
    }

    private void onToolBarLeftClicked() {
        if (getCheckedCount() == 0) {
            return;
        }
        showDelConfirmDialog(false);
    }

    private void onToolBarRightClicked() {
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
        if (rsr.h(this.mPageModelArgs) && this.mPageModelArgs.getLong("startTime") == Long.MIN_VALUE) {
            this.mCustomTitleBar.setAppBarVisible(false);
            this.mSpinnerCustomTitleBar.setAppBarVisible(true);
            return;
        }
        this.mCustomTitleBar.setLeftButtonDrawable(rsr.dQH_(R.mipmap._2131820919_res_0x7f110177), nsf.h(R$string.accessibility_go_back));
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: rsu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CategoryCommonLayout.this.m858x9050d655(view);
            }
        });
        PrivacyDataModelActivity privacyDataModelActivity = this.mDataModelActivity;
        if (privacyDataModelActivity != null) {
            this.mCustomTitleBar.setTitleText(privacyDataModelActivity.getTitleBarContent());
        }
    }

    /* renamed from: lambda$setTitleBarNotEditStatus$4$com-huawei-ui-main-stories-privacy-template-view-showdata-CategoryCommonLayout, reason: not valid java name */
    public /* synthetic */ void m858x9050d655(View view) {
        PrivacyDataModelActivity privacyDataModelActivity = this.mDataModelActivity;
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
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: rta
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CategoryCommonLayout.this.m857x53875bb9(view);
            }
        });
        int checkedCount = getCheckedCount();
        if (checkedCount == 0) {
            this.mCustomTitleBar.setTitleText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_not_select));
        } else {
            this.mCustomTitleBar.setTitleText(getResources().getQuantityString(R.plurals._2130903064_res_0x7f030018, checkedCount, Integer.valueOf(checkedCount)));
        }
    }

    /* renamed from: lambda$setTitleBarEditingStatus$5$com-huawei-ui-main-stories-privacy-template-view-showdata-CategoryCommonLayout, reason: not valid java name */
    public /* synthetic */ void m857x53875bb9(View view) {
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
        if (LanguageUtil.bc(getContext())) {
            this.mNoDataImage.setBackground(nrz.cKn_(getContext(), R.drawable._2131430620_res_0x7f0b0cdc));
        } else {
            this.mNoDataImage.setBackgroundResource(R.drawable._2131430620_res_0x7f0b0cdc);
        }
    }
}
