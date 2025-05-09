package defpackage;

import com.huawei.ui.main.stories.template.BaseComponent;
import com.huawei.ui.main.stories.template.health.contract.DataDetailFragmentContract;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public class ryq implements DataDetailFragmentContract.DetailFragmentPresenter<DataDetailFragmentContract.DetailFragmentView> {

    /* renamed from: a, reason: collision with root package name */
    protected List<BaseComponent> f16970a;
    protected WeakReference<DataDetailFragmentContract.DetailFragmentView> d;

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void attachView(DataDetailFragmentContract.DetailFragmentView detailFragmentView) {
        this.d = new WeakReference<>(detailFragmentView);
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public boolean isViewAttached() {
        WeakReference<DataDetailFragmentContract.DetailFragmentView> weakReference = this.d;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public void detachView() {
        WeakReference<DataDetailFragmentContract.DetailFragmentView> weakReference = this.d;
        if (weakReference != null) {
            weakReference.clear();
            this.d = null;
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailFragmentContract.DetailFragmentPresenter
    public void setComponents(List<BaseComponent> list) {
        this.f16970a = list;
    }
}
