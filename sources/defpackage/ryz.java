package defpackage;

import com.huawei.ui.main.stories.template.BaseView;
import com.huawei.ui.main.stories.template.data.PageDataObserver;
import com.huawei.ui.main.stories.template.health.module.nodevice.HealthNoDeviceContract;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public class ryz implements HealthNoDeviceContract.Presenter, PageDataObserver {
    private WeakReference<BaseView> c;

    public HealthNoDeviceContract.View b() {
        WeakReference<BaseView> weakReference = this.c;
        if (weakReference == null) {
            return null;
        }
        BaseView baseView = weakReference.get();
        if (baseView instanceof HealthNoDeviceContract.View) {
            return (HealthNoDeviceContract.View) baseView;
        }
        return null;
    }

    @Override // com.huawei.ui.main.stories.template.health.module.nodevice.HealthNoDeviceContract.Presenter
    public void requestActivityInfo(int i) {
        ryg.b().e(i, this);
        ryn.d().a(i);
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public void attachView(BaseView baseView) {
        this.c = new WeakReference<>(baseView);
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public boolean isViewAttached() {
        WeakReference<BaseView> weakReference = this.c;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public void detachView() {
        WeakReference<BaseView> weakReference = this.c;
        if (weakReference != null) {
            weakReference.clear();
            this.c = null;
        }
        ryg.b().e(this);
    }

    @Override // com.huawei.ui.main.stories.template.data.PageDataObserver
    public void update(ryg rygVar, List<cdy> list) {
        if (b() != null) {
            b().onResponsePageModule(list);
        }
    }
}
