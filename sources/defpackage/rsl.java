package defpackage;

import android.content.res.Resources;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes7.dex */
public class rsl implements PrivacyActivityContract.PrivacyActivityPresenter<PrivacyActivityContract.PrivacyActivityView> {
    private WeakReference<PrivacyActivityContract.PrivacyActivityView> b;

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract.PrivacyActivityPresenter
    public void initPage(int i, String str, long j) {
        if (this.b == null || c() == null) {
            return;
        }
        PrivacyActivityContract.PrivacyActivityView privacyActivityView = this.b.get();
        Resources resources = c().getViewContext().getResources();
        if (i == 1) {
            dQE_(resources, 1, j);
            privacyActivityView.showAllDataView();
        } else if (i == 2) {
            dQE_(resources, 2, j);
            privacyActivityView.showSegmentDataView();
        } else {
            if (i != 3) {
                return;
            }
            dQE_(resources, 3, j);
            privacyActivityView.showDoubleGroupDataView();
        }
    }

    private void dQE_(Resources resources, int i, long j) {
        if (i == 1 || i == 3) {
            c().setTitle(resources.getString(R$string.IDS_privacy_all_record_data));
        } else {
            c().setTitle(String.format(Locale.ROOT, resources.getString(R$string.IDS_privacy_record_date), rsr.i(j)));
        }
    }

    private PrivacyActivityContract.PrivacyActivityView c() {
        return this.b.get();
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void attachView(PrivacyActivityContract.PrivacyActivityView privacyActivityView) {
        this.b = new WeakReference<>(privacyActivityView);
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public boolean isViewAttached() {
        WeakReference<PrivacyActivityContract.PrivacyActivityView> weakReference = this.b;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public void detachView() {
        WeakReference<PrivacyActivityContract.PrivacyActivityView> weakReference = this.b;
        if (weakReference != null) {
            weakReference.clear();
            this.b = null;
        }
    }
}
