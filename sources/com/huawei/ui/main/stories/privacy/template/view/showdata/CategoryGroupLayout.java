package com.huawei.ui.main.stories.privacy.template.view.showdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.component.PrivacyGroupDataView;
import com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryGroupLayout;
import defpackage.mlg;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.rkb;
import defpackage.rsg;
import defpackage.rsr;
import defpackage.rtv;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class CategoryGroupLayout extends CategoryCommonLayout {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10431a = "CategoryGroupLayout";
    private PrivacyGroupDataView b;
    private GroupDataAdapter c;
    private List<rsg> d;
    private PrivacyGroupDataFragment e;
    private LinearLayout h;
    private long j;

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected int getLayoutId() {
        return R.layout.privacy_fragment_data;
    }

    public CategoryGroupLayout(Context context, Activity activity, Fragment fragment, PageModelArgs pageModelArgs) {
        super(context, activity, pageModelArgs);
        this.j = Long.MIN_VALUE;
        if (fragment instanceof PrivacyGroupDataFragment) {
            this.e = (PrivacyGroupDataFragment) fragment;
        }
        initViews();
    }

    public void c(List<rsg> list) {
        this.d = list;
        b();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected void initViews() {
        super.initViews();
        ((RelativeLayout) findViewById(R.id.privacy_data_loading)).setVisibility(8);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.no_data_source);
        this.h = linearLayout;
        linearLayout.setVisibility(0);
        c();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected boolean hasData() {
        List<rsg> list = this.d;
        return (list == null || list.size() == 0) ? false : true;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected int getCheckedCount() {
        return this.c.e();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected int getTotalCount() {
        return this.c.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    public void onToolBarStatusChanged(int i) {
        if (i == 1) {
            this.c.d(1);
            this.e.c(true);
        } else if (i == 2) {
            this.c.d(2);
            this.e.c(false);
        } else if (i == 3) {
            setAllChecked(false);
        } else {
            if (i != 4) {
                return;
            }
            setAllChecked(true);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected void setAllChecked(boolean z) {
        this.c.d(z);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected void deleteDate() {
        if (this.mDataManager == null) {
            return;
        }
        int i = this.mClassType;
        String str = this.mDeviceUuid;
        String str2 = this.mPackageName;
        SourceInfo sourceInfo = this.mPageModelArgs.getSourceInfo();
        if (sourceInfo != null) {
            if (sourceInfo.getSourceType() == 1) {
                str2 = sourceInfo.getPackageName();
                i = 1;
            } else {
                str = sourceInfo.getDeviceUniqueCode();
                i = 2;
            }
        }
        rkb rkbVar = new rkb(i, str, str2);
        rkbVar.e("categoryType", this.mPageModelArgs.getInt("categoryType"));
        this.mDataManager.b(this.c.c(), rkbVar, new DataSourceCallback() { // from class: rtg
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                CategoryGroupLayout.this.a(i2, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ void a(int i, Boolean bool) {
        LogUtil.a(f10431a, "deleteData callBack code : " + i);
        nrh.b(getContext(), i == 0 ? R.string.IDS_device_wifi_clear_user_success : R.string.IDS_device_wifi_delete_fail);
        this.e.a();
    }

    private void b() {
        List<rsg> list = this.d;
        if (list == null || list.isEmpty()) {
            this.h.setVisibility(0);
            showRtlLanguage();
        } else {
            if (this.c == null) {
                return;
            }
            this.h.setVisibility(8);
            this.c.a(this.d);
            if (this.b != null) {
                int groupCount = this.c.getGroupCount();
                for (int i = 0; i < groupCount; i++) {
                    this.b.expandGroup(i);
                }
            }
            a();
        }
    }

    private void c() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.privacy_data_fragment_container);
        PrivacyGroupDataView privacyGroupDataView = new PrivacyGroupDataView(getContext());
        this.b = privacyGroupDataView;
        linearLayout.addView(privacyGroupDataView);
        GroupDataAdapter groupDataAdapter = new GroupDataAdapter();
        this.c = groupDataAdapter;
        groupDataAdapter.c(!this.mIsOtherManufacturer);
        this.c.b(new GroupDataAdapter.OnItemClickListener() { // from class: rth
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter.OnItemClickListener
            public final void onItemClickListener(int i, int i2) {
                CategoryGroupLayout.this.c(i, i2);
            }
        });
        this.c.c(new GroupDataAdapter.OnLongClickListener() { // from class: rti
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter.OnLongClickListener
            public final void onLongClickListener(int i, int i2) {
                CategoryGroupLayout.this.e(i, i2);
            }
        });
        this.b.setAdapter(this.c);
        this.b.setDividerHeight(0);
    }

    public /* synthetic */ void e(int i, int i2) {
        if (isSupportLongClick()) {
            this.c.a(i, i2);
            showDelConfirmDialog(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2) {
        if (this.mIsOtherManufacturer) {
            return;
        }
        if (this.mToolBarManager.e() != 1) {
            this.c.a(i, i2);
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
        a(this.c.d(i, i2));
    }

    private void a(PrivacyDataModel privacyDataModel) {
        if (this.mPageType != 106) {
            return;
        }
        rtv.a();
        PageModelArgs b = rsr.b(this.mPageModelArgs);
        if (this.mPageModelArgs.getInt("categoryType") == 2) {
            b.setPageType(113);
        }
        b.setTimestamp(privacyDataModel.getStartTime());
        b.setModuleType(2);
        Intent intent = new Intent(getContext(), (Class<?>) PrivacyDataModelActivity.class);
        intent.putExtra("extra_page_model_args", b);
        this.e.startActivityForResult(intent, 1);
    }

    private void a() {
        long j = this.j;
        if (j == Long.MIN_VALUE) {
            return;
        }
        String a2 = mlg.a(j, Integer.MAX_VALUE);
        Iterator<rsg> it = this.d.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            rsg next = it.next();
            i++;
            if (!a2.equals(next.e())) {
                i += next.d().size();
            } else {
                Iterator<PrivacyDataModel> it2 = next.d().iterator();
                while (it2.hasNext()) {
                    if (this.j == it2.next().getStartTime()) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        PrivacyGroupDataView privacyGroupDataView = this.b;
        if (privacyGroupDataView != null) {
            privacyGroupDataView.smoothScrollToPositionFromTop(i, (int) (((nsn.j() * 0.5f) - this.mSpinnerCustomTitleBar.getHeight()) - this.e.c()));
        }
        this.j = Long.MIN_VALUE;
    }
}
