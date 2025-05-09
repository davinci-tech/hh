package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.framework.servicemgr.HmfProviderService;
import com.huawei.framework.servicemgr.Lazy;
import com.huawei.framework.servicemgr.ServiceManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.NaviConsumer;
import com.huawei.hmf.repository.ComponentRepository;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.Module;
import health.compact.a.LogUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class bwb implements ServiceManager {
    private final Repository e;

    private bwb() {
        this.e = ComponentRepository.getRepository();
    }

    @Override // com.huawei.framework.servicemgr.ServiceManager
    public <T> Lazy<T> get(String str, Class<T> cls, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Missing required argument: provider is empty");
        }
        Module lookup = this.e.lookup(str);
        if (lookup == null) {
            LogUtil.a("ServiceManagerImpl", "The provider module is NOT loaded: ", str);
            return new c(this.e, str, cls, str2);
        }
        return new e(bwe.a(lookup, cls, str2));
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public static final ServiceManager f534a = new bwb();

        a() {
        }
    }

    static class e<T> implements Lazy<T> {
        private final T e;

        @Override // com.huawei.framework.servicemgr.Lazy
        public boolean isPresent() {
            return true;
        }

        private e(T t) {
            this.e = t;
        }

        @Override // com.huawei.framework.servicemgr.Lazy
        public T get() throws IllegalStateException {
            return this.e;
        }

        @Override // com.huawei.framework.servicemgr.Lazy
        public void load(Context context, Consumer<T> consumer, boolean z) throws IllegalStateException {
            if (consumer != null) {
                consumer.accept(get());
            }
        }
    }

    static class c<T> implements Lazy<T> {

        /* renamed from: a, reason: collision with root package name */
        private volatile Module f535a;
        private final String b;
        private final Class<T> c;
        private final Repository d;
        private final String e;

        private c(Repository repository, String str, Class<T> cls, String str2) {
            this.d = repository;
            this.b = str;
            this.c = cls;
            this.e = str2;
        }

        @Override // com.huawei.framework.servicemgr.Lazy
        public boolean isPresent() {
            if (this.f535a == null) {
                Context e = BaseApplication.e();
                AppRouter.c(e, this.b);
                this.f535a = this.d.lookup(this.b);
                if (this.f535a == null) {
                    HmfProviderService.b(e.getClassLoader(), this.b);
                    this.f535a = this.d.lookup(this.b);
                }
            }
            return this.f535a != null;
        }

        @Override // com.huawei.framework.servicemgr.Lazy
        public T get() throws IllegalStateException {
            if (!isPresent()) {
                LogUtil.a("ServiceManagerImpl", "The provider module=", this.b, " NOT loaded, please call the load() first");
                throw new IllegalStateException("Provider unavailable: " + this.b);
            }
            return (T) bwe.a(this.f535a, this.c, this.e);
        }

        @Override // com.huawei.framework.servicemgr.Lazy
        public void load(Context context, final Consumer<T> consumer, boolean z) throws IllegalStateException {
            if (isPresent()) {
                LogUtil.d("ServiceManagerImpl", "The provider module has been loaded, instantiate it as needed.");
                if (consumer != null) {
                    consumer.accept(get());
                    return;
                }
                return;
            }
            String str = this.b;
            if (!TextUtils.isEmpty(this.e)) {
                str = this.b + "/" + this.e;
            }
            LogUtil.c("ServiceManagerImpl", "Trying to download the AppBundle with name=", str);
            AppRouter.e(str, this.c, context, consumer != null ? new NaviConsumer<T>() { // from class: bwb.c.2
                @Override // com.huawei.haf.router.NaviConsumer
                public void accept(T t) {
                    consumer.accept(t);
                }
            } : null, !z);
        }
    }
}
