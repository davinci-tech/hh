package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import com.huawei.ui.main.stories.privacy.template.contract.PrivacyDetailFragmentContract;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDayDataFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class rsh extends rso {
    @Override // defpackage.rso
    public void d(PageModelArgs pageModelArgs) {
        int dataSource = pageModelArgs.getDataSource();
        if (dataSource == 1) {
            c(pageModelArgs);
        } else if (dataSource == 2) {
            b(pageModelArgs.getTimestamp());
        } else {
            if (dataSource != 3) {
                return;
            }
            e(pageModelArgs);
        }
    }

    private void e(PageModelArgs pageModelArgs) {
        long c;
        long e2;
        int pageType = pageModelArgs.getPageType();
        SourceInfo sourceInfo = pageModelArgs.getSourceInfo();
        if (pageType == 103) {
            c = rrb.b(pageModelArgs.getTimestamp());
        } else {
            c = rrb.c(pageModelArgs.getTimestamp());
        }
        long j = c;
        if (pageType == 103) {
            e2 = rrb.a(pageModelArgs.getTimestamp());
        } else {
            e2 = rrb.e(pageModelArgs.getTimestamp());
        }
        rkb rkbVar = new rkb(j, e2, sourceInfo == null ? 0 : rsr.e(sourceInfo.getSourceType()), sourceInfo == null ? "" : sourceInfo.getDeviceUniqueCode(), sourceInfo != null ? sourceInfo.getPackageName() : "");
        rkbVar.e("categoryType", pageModelArgs.getInt("categoryType"));
        rkbVar.b(rsr.c(pageModelArgs));
        new row(pageModelArgs.getPageType()).b(rkbVar, new e(this));
    }

    private void c(PageModelArgs pageModelArgs) {
        if (pageModelArgs.getPageType() == 103) {
            new row(pageModelArgs.getPageType()).b(new rkb(rrb.b(pageModelArgs.getTimestamp()), rrb.a(pageModelArgs.getTimestamp()), pageModelArgs.getClassType(), pageModelArgs.getUuid(), pageModelArgs.getPackageName()), new e(this));
        } else {
            new row(pageModelArgs.getPageType()).a(pageModelArgs.getClassType(), pageModelArgs.getTimestamp(), pageModelArgs.getUuid(), pageModelArgs.getPackageName(), new e(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, List<PrivacyDataModel> list) {
        PrivacyDetailFragmentContract.PrivacyFragmentView e2 = e();
        if (e2 instanceof PrivacyDayDataFragment) {
            PrivacyDayDataFragment privacyDayDataFragment = (PrivacyDayDataFragment) e2;
            if (!isViewAttached()) {
                LogUtil.a("DayDataFragmentPresenter", "notifyDayDataChange isViewAttached false");
            } else {
                privacyDayDataFragment.e(i, list);
            }
        }
    }

    private void b(long j) {
        List<PrivacyDataModel> e2 = rru.d().e(rsr.i(j));
        PrivacyDetailFragmentContract.PrivacyFragmentView e3 = e();
        if (e3 instanceof PrivacyDayDataFragment) {
            PrivacyDayDataFragment privacyDayDataFragment = (PrivacyDayDataFragment) e3;
            if (koq.b(e2)) {
                LogUtil.a("DayDataFragmentPresenter", "getDayDataFromKit has no data");
                privacyDayDataFragment.e(0, new ArrayList());
            } else {
                privacyDayDataFragment.e(0, e2);
            }
        }
    }

    static class e implements DataSourceCallback<List<PrivacyDataModel>> {
        WeakReference<rsh> c;

        e(rsh rshVar) {
            this.c = new WeakReference<>(rshVar);
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<PrivacyDataModel> list) {
            rsh rshVar;
            WeakReference<rsh> weakReference = this.c;
            if (weakReference == null || (rshVar = weakReference.get()) == null) {
                return;
            }
            rshVar.c(i, list);
        }
    }
}
