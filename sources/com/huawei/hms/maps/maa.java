package com.huawei.hms.maps;

import android.content.Context;
import com.huawei.hms.feature.dynamic.DeferredLifecycleHelper;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.LifecycleDelegate;
import com.huawei.hms.maps.internal.ICoordinateConverterDelegate;
import com.huawei.hms.maps.internal.ICreator;
import com.huawei.hms.maps.internal.IDistanceCalculatorDelegate;
import com.huawei.hms.maps.internal.IMapAuthToProvider;
import com.huawei.hms.maps.internal.IMapClientIdentity;
import com.huawei.hms.maps.internal.IMapFragmentDelegate;
import com.huawei.hms.maps.internal.IMapViewDelegate;
import com.huawei.hms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.huawei.hms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.huawei.hms.maps.utils.DynamicUtil;
import com.huawei.hms.maps.utils.LogM;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
abstract class maa<T extends LifecycleDelegate> extends DeferredLifecycleHelper<T> {
    private Disposable b;

    /* renamed from: a, reason: collision with root package name */
    private boolean f4969a = false;
    private ICreator c = new ICreator.Stub() { // from class: com.huawei.hms.maps.maa.1
        @Override // com.huawei.hms.maps.internal.ICreator
        public void init(IObjectWrapper iObjectWrapper, int i) {
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public void initAppContext(IObjectWrapper iObjectWrapper) {
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public ICoordinateConverterDelegate newCoordinateConverterDelegate(IObjectWrapper iObjectWrapper) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IDistanceCalculatorDelegate newDistanceCalculatorDelegate(IObjectWrapper iObjectWrapper) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IMapAuthToProvider newMapAuthToProvider(IObjectWrapper iObjectWrapper) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IMapClientIdentity newMapClientIdentity(IObjectWrapper iObjectWrapper) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IMapFragmentDelegate newMapFragmentDelegate(IObjectWrapper iObjectWrapper, HuaweiMapOptions huaweiMapOptions) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IMapViewDelegate newMapViewDelegate(IObjectWrapper iObjectWrapper, HuaweiMapOptions huaweiMapOptions) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public maf newPlaceDelegate(IObjectWrapper iObjectWrapper) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IStreetViewPanoramaFragmentDelegate newStreetViewPanoramaFragmentDelegate(IObjectWrapper iObjectWrapper) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IStreetViewPanoramaViewDelegate newStreetViewPanoramaViewDelegate(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IMapFragmentDelegate newTextureMapFragmentDelegate(IObjectWrapper iObjectWrapper, HuaweiMapOptions huaweiMapOptions) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IMapViewDelegate newTextureMapViewDelegate(IObjectWrapper iObjectWrapper, HuaweiMapOptions huaweiMapOptions) {
            return null;
        }

        @Override // com.huawei.hms.maps.internal.ICreator
        public IStreetViewPanoramaFragmentDelegate optStreetViewPanoramaFragmentDelegate(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            return null;
        }
    };

    protected abstract void a(ICreator iCreator);

    protected void a(final Context context) {
        if (DynamicUtil.isAndroidN()) {
            return;
        }
        LogM.i("AbsDeferredLifecycleHelper", "getCreator: createdFlag = " + this.f4969a);
        if (this.f4969a) {
            return;
        }
        this.f4969a = true;
        b();
        this.b = Observable.fromCallable(new Callable<ICreator>() { // from class: com.huawei.hms.maps.maa.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public ICreator call() {
                ICreator c = com.huawei.hms.maps.internal.mab.c(context);
                return c == null ? maa.this.c : c;
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).retryWhen(new mac()).subscribe(new C0132maa(), new mab());
        LogM.i("AbsDeferredLifecycleHelper", "getCreator: execute");
    }

    static class mac implements Function<Observable<? extends Throwable>, Observable<?>> {

        /* renamed from: a, reason: collision with root package name */
        private int f4974a;

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Observable<?> apply(Observable<? extends Throwable> observable) {
            return observable.flatMap(new Function<Throwable, ObservableSource<?>>() { // from class: com.huawei.hms.maps.maa.mac.1
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public ObservableSource<?> apply(Throwable th) {
                    if (mac.a(mac.this) > 3) {
                        return Observable.error(th);
                    }
                    LogM.w("AbsDeferredLifecycleHelper", "get MapCreator failed, retry counter :" + mac.this.f4974a);
                    return Observable.timer(500L, TimeUnit.MILLISECONDS);
                }
            });
        }

        static /* synthetic */ int a(mac macVar) {
            int i = macVar.f4974a + 1;
            macVar.f4974a = i;
            return i;
        }

        private mac() {
            this.f4974a = 0;
        }
    }

    /* renamed from: com.huawei.hms.maps.maa$maa, reason: collision with other inner class name */
    class C0132maa implements Consumer<ICreator> {
        @Override // io.reactivex.rxjava3.functions.Consumer
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(ICreator iCreator) {
            maa.this.b();
            if (iCreator == null || iCreator == maa.this.c) {
                maa.this.f4969a = false;
                LogM.e("AbsDeferredLifecycleHelper", "getCreator: creator == null, createdFlag = false");
            } else {
                LogM.i("AbsDeferredLifecycleHelper", "realCreateDelegate: start");
                maa.this.a(iCreator);
            }
        }

        private C0132maa() {
        }
    }

    class mab implements Consumer<Throwable> {
        @Override // io.reactivex.rxjava3.functions.Consumer
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            maa.this.b();
            maa.this.f4969a = false;
            LogM.e("AbsDeferredLifecycleHelper", "getCreator: throwable = " + th.getMessage() + "; createdFlag = " + maa.this.f4969a);
        }

        private mab() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Disposable disposable = this.b;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.b.dispose();
        this.b = null;
        LogM.d("AbsDeferredLifecycleHelper", "getCreator: disposable");
    }

    maa() {
    }
}
