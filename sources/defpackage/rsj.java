package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.template.contract.PrivacyDetailFragmentContract;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDoubleGroupDataFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class rsj extends rso {
    private static final String c = "DoubleGroupDataFragmentPresenter";

    @Override // defpackage.rso
    public void d(PageModelArgs pageModelArgs) {
        a(pageModelArgs);
    }

    private void a(PageModelArgs pageModelArgs) {
        if (pageModelArgs != null && pageModelArgs.getDataSource() == 3) {
            c(pageModelArgs);
        }
    }

    private void c(PageModelArgs pageModelArgs) {
        int pageType = pageModelArgs.getPageType();
        if (pageType != 107) {
            return;
        }
        long j = pageModelArgs.getLong("startTime");
        long j2 = pageModelArgs.getLong("endTime");
        if (j == Long.MIN_VALUE) {
            j = 0;
        }
        long j3 = j;
        if (j2 == Long.MIN_VALUE) {
            j2 = System.currentTimeMillis();
        }
        rkb rkbVar = new rkb(j3, j2, 0, "", "");
        rkbVar.d(3);
        rkbVar.e("Subpage", pageModelArgs.getInt("Subpage"));
        if (pageModelArgs.getInt("Subpage") == 3) {
            new row(pageType).d(rkbVar, new e(this));
        } else {
            new row(pageType).c(rkbVar, new d(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, List<rsb> list) {
        PrivacyDetailFragmentContract.PrivacyFragmentView e2 = e();
        if (e2 instanceof PrivacyDoubleGroupDataFragment) {
            PrivacyDoubleGroupDataFragment privacyDoubleGroupDataFragment = (PrivacyDoubleGroupDataFragment) e2;
            if (!isViewAttached()) {
                LogUtil.a(c, "notifyDoubleGroupDataChange isViewAttached false");
                return;
            }
            if (i != 0) {
                list = new ArrayList<>(10);
            }
            privacyDoubleGroupDataFragment.d(list);
        }
    }

    static class d implements DataSourceCallback<List<rsb>> {
        WeakReference<rsj> b;

        d(rsj rsjVar) {
            this.b = new WeakReference<>(rsjVar);
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<rsb> list) {
            rsj rsjVar;
            WeakReference<rsj> weakReference = this.b;
            if (weakReference == null || (rsjVar = weakReference.get()) == null) {
                return;
            }
            rsjVar.b(i, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Map<Integer, List<rsb>> map) {
        PrivacyDetailFragmentContract.PrivacyFragmentView e2 = e();
        if (e2 instanceof PrivacyDoubleGroupDataFragment) {
            PrivacyDoubleGroupDataFragment privacyDoubleGroupDataFragment = (PrivacyDoubleGroupDataFragment) e2;
            if (!isViewAttached()) {
                LogUtil.a(c, "notifyCategoryDoubleGroupData isViewAttached false");
                return;
            }
            if (i != 0) {
                map = new HashMap<>(10);
            }
            privacyDoubleGroupDataFragment.a(map);
        }
    }

    static class e implements DataSourceCallback<Map<Integer, List<rsb>>> {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<rsj> f16899a;

        e(rsj rsjVar) {
            this.f16899a = new WeakReference<>(rsjVar);
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, Map<Integer, List<rsb>> map) {
            rsj rsjVar;
            WeakReference<rsj> weakReference = this.f16899a;
            if (weakReference == null || (rsjVar = weakReference.get()) == null) {
                return;
            }
            rsjVar.c(i, map);
        }
    }
}
