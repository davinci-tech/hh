package defpackage;

import com.huawei.ui.main.stories.privacy.template.contract.PrivacyDetailFragmentContract;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class rso implements PrivacyDetailFragmentContract.PrivacyFragmentPresenter<PrivacyDetailFragmentContract.PrivacyFragmentView> {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<PrivacyDetailFragmentContract.PrivacyFragmentView> f16901a;

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyDetailFragmentContract.PrivacyFragmentPresenter
    public void addComponents(BaseComponent baseComponent) {
    }

    public void d(PageModelArgs pageModelArgs) {
    }

    public PrivacyDetailFragmentContract.PrivacyFragmentView e() {
        WeakReference<PrivacyDetailFragmentContract.PrivacyFragmentView> weakReference = this.f16901a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void attachView(PrivacyDetailFragmentContract.PrivacyFragmentView privacyFragmentView) {
        this.f16901a = new WeakReference<>(privacyFragmentView);
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public boolean isViewAttached() {
        WeakReference<PrivacyDetailFragmentContract.PrivacyFragmentView> weakReference = this.f16901a;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public void detachView() {
        WeakReference<PrivacyDetailFragmentContract.PrivacyFragmentView> weakReference = this.f16901a;
        if (weakReference != null) {
            weakReference.clear();
            this.f16901a = null;
        }
    }
}
