package com.huawei.hms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.hms.common.Preconditions;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.feature.dynamic.OnDelegateCreatedListener;
import com.huawei.hms.maps.auth.AuthClient;
import com.huawei.hms.maps.internal.HmsUtil;
import com.huawei.hms.maps.internal.ICreator;
import com.huawei.hms.maps.internal.IHuaweiMapDelegate;
import com.huawei.hms.maps.internal.IMapViewDelegate;
import com.huawei.hms.maps.internal.IOnMapReadyCallback;
import com.huawei.hms.maps.model.RuntimeRemoteException;
import com.huawei.hms.maps.provider.inhuawei.MapViewDelegate;
import com.huawei.hms.maps.utils.LogM;
import com.huawei.hms.maps.utils.MapClientUtil;
import com.huawei.hms.maps.utils.MapsAdvUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class MapView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    protected MapLifecycleDelegate f4936a;
    private final boolean b;

    public void onStop() {
        MapLifecycleDelegate mapLifecycleDelegate = this.f4936a;
        if (mapLifecycleDelegate != null) {
            mapLifecycleDelegate.onStop();
        }
    }

    public void onStart() {
        MapLifecycleDelegate mapLifecycleDelegate = this.f4936a;
        if (mapLifecycleDelegate != null) {
            mapLifecycleDelegate.onStart();
        }
    }

    static class mab extends com.huawei.hms.maps.mab implements MapLifecycleDelegate {

        /* renamed from: a, reason: collision with root package name */
        private ViewGroup f4938a;
        private IMapViewDelegate b;
        private View c;
        private boolean d = false;
        private Context e;

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onStop() {
            try {
                this.b.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onStart() {
            try {
                this.b.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.b.onSaveInstanceState(com.huawei.hms.maps.internal.mac.a(bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onResume() {
            try {
                LogM.d("MapView", "MapView:onResume");
                this.b.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onPause() {
            try {
                LogM.d("MapView", "MapView:onResume");
                this.b.onPause();
                if (MapClientIdentify.getAppContext() instanceof Activity) {
                    a();
                    if (this.d) {
                        LogM.d("MapView", "clearResource in onPause method");
                    }
                }
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onLowMemory() {
            try {
                this.b.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate prohibited on MapViewDelegate");
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView prohibited on MapViewDelegate");
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onDestroy() {
            try {
                this.b.onDestroy();
                if (this.d) {
                    return;
                }
                a();
                LogM.d("MapView", this.d ? "clearResource in onDestroy method" : "onDestroy: execute clearResource in onDestroy method, but activity is  running");
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView prohibited on MapViewDelegate");
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onCreate(Bundle bundle) {
            try {
                this.b.onCreate(com.huawei.hms.maps.internal.mac.a(bundle));
                this.b.setActivity(ObjectWrapper.wrap(this.e));
                this.c = !MapsAdvUtil.containsMapsBasic() ? (View) ObjectWrapper.unwrap(this.b.getView()) : this.b.getMapView();
                this.f4938a.removeAllViews();
                this.f4938a.addView(this.c);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.maps.MapLifecycleDelegate
        public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
            try {
                this.b.getMapAsync(new IOnMapReadyCallback.Stub() { // from class: com.huawei.hms.maps.MapView.mab.1
                    @Override // com.huawei.hms.maps.internal.IOnMapReadyCallback
                    public void onMapReady(IHuaweiMapDelegate iHuaweiMapDelegate) {
                        if (onMapReadyCallback != null) {
                            LogM.i("MapView", "onMapReady: mapReadyCallback is not null");
                            if (MapsAdvUtil.containsMapsAdvance()) {
                                LogM.i("MapView", "onMapReady: fullsdk ");
                                onMapReadyCallback.onMapReady(mab.this.a((com.huawei.hms.maps.provider.inhuawei.IHuaweiMapDelegate) iHuaweiMapDelegate));
                            } else {
                                onMapReadyCallback.onMapReady(new HuaweiMap(iHuaweiMapDelegate));
                            }
                        } else {
                            LogM.w("MapView", "onMapReady: mapReadyCallback is null");
                        }
                        if (mab.this.f4938a != null) {
                            mab.this.f4938a.setBackground(null);
                        }
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        private void a() {
            Activity findActivity = HmsUtil.findActivity(MapClientIdentify.getAppContext());
            if (findActivity == null || !findActivity.isFinishing()) {
                return;
            }
            MapsInitializer.initialize(null);
            MapClientIdentify.setAppContext(null);
            com.huawei.hms.maps.internal.mab.a();
            this.d = true;
        }

        public mab(ViewGroup viewGroup, Context context, IMapViewDelegate iMapViewDelegate) {
            this.f4938a = (ViewGroup) Preconditions.checkNotNull(viewGroup);
            this.b = (IMapViewDelegate) Preconditions.checkNotNull(iMapViewDelegate);
            this.e = context;
            MapsInitializer.initialize(context);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        MapLifecycleDelegate mapLifecycleDelegate = this.f4936a;
        if (mapLifecycleDelegate != null) {
            mapLifecycleDelegate.onSaveInstanceState(bundle);
        }
    }

    public void onResume() {
        MapLifecycleDelegate mapLifecycleDelegate = this.f4936a;
        if (mapLifecycleDelegate != null) {
            mapLifecycleDelegate.onResume();
        }
    }

    public void onPause() {
        LogM.d("MapView", "onPause");
        MapLifecycleDelegate mapLifecycleDelegate = this.f4936a;
        if (mapLifecycleDelegate != null) {
            mapLifecycleDelegate.onPause();
        }
    }

    public final void onLowMemory() {
        MapLifecycleDelegate mapLifecycleDelegate = this.f4936a;
        if (mapLifecycleDelegate != null) {
            mapLifecycleDelegate.onLowMemory();
        }
    }

    public final void onExitAmbient() {
        LogM.d("MapView", "onExitAmbient");
    }

    public final void onEnterAmbient(Bundle bundle) {
        LogM.d("MapView", "onEnterAmbient");
    }

    public void onDestroy() {
        MapLifecycleDelegate mapLifecycleDelegate = this.f4936a;
        if (mapLifecycleDelegate != null) {
            mapLifecycleDelegate.onDestroy();
        }
        HmsUtil.setRepeatFlag(true);
    }

    public void onCreate(Bundle bundle) {
        if (this.f4936a == null) {
            LogM.e("MapView", "MapsInitializer is not initialized.");
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        MapClientIdentify.setMapAuthStartTime(System.currentTimeMillis());
        com.huawei.hms.maps.internal.mab.e(getContext());
        com.huawei.hms.maps.internal.mab.b(true);
        try {
            this.f4936a.onCreate(bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        LogM.d("MapView", "getMapAsync: ");
        MapLifecycleDelegate mapLifecycleDelegate = this.f4936a;
        if (mapLifecycleDelegate != null) {
            mapLifecycleDelegate.getMapAsync(onMapReadyCallback);
        }
    }

    private void a(Context context, HuaweiMapOptions huaweiMapOptions) {
        MapLifecycleDelegate maaVar;
        LogM.d("MapView", "initMapView: ");
        if (!MapsInitializer.a()) {
            LogM.e("MapView", "MapsInitializer is not initialized.");
            this.f4936a = null;
            return;
        }
        if (MapClientIdentify.getMapAuthStartTime() == 0) {
            MapClientIdentify.setMapAuthStartTime(System.currentTimeMillis());
        }
        if (!this.b) {
            if (MapsAdvUtil.containsMapsBasic()) {
                com.huawei.hms.maps.common.util.maa.a(context);
                AuthClient.getInstance().startAuth(context);
                maaVar = new mab(this, context, new MapViewDelegate(context, huaweiMapOptions));
            } else {
                maaVar = new maa(this, context, huaweiMapOptions);
            }
            this.f4936a = maaVar;
        }
        setClickable(true);
        setBackground(new mae().a(this));
    }

    static class maa extends com.huawei.hms.maps.maa<mab> implements MapLifecycleDelegate {

        /* renamed from: a, reason: collision with root package name */
        private final ViewGroup f4937a;
        private final Context b;
        private OnDelegateCreatedListener<mab> c;
        private final HuaweiMapOptions d;
        private final List<OnMapReadyCallback> e = new ArrayList();
        private boolean f = false;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hms.maps.MapLifecycleDelegate
        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            LogM.i("MapView", "getMapAsync: getDelegate = " + getDelegate());
            if (getDelegate() != 0) {
                ((mab) getDelegate()).getMapAsync(onMapReadyCallback);
                return;
            }
            this.e.add(onMapReadyCallback);
            LogM.i("MapView", "getMapAsync: mapReadyCallbacks.size = " + this.e.size());
        }

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper
        public void createDelegate(OnDelegateCreatedListener<mab> onDelegateCreatedListener) {
            StringBuilder sb;
            com.huawei.hms.maps.common.util.maa.a(this.b);
            this.c = onDelegateCreatedListener;
            if (onDelegateCreatedListener == null || getDelegate() != 0) {
                sb = new StringBuilder("createDelegate: onDelegateCreatedListener = ");
                sb.append(onDelegateCreatedListener);
                sb.append("; getDelegate = ");
                sb.append(getDelegate());
            } else {
                MapsInitializer.initialize(this.b);
                int isHmsAvailable = HmsUtil.isHmsAvailable(this.b);
                if (isHmsAvailable == 0) {
                    MapClientIdentify.initApiKey(this.b);
                    if (TextUtils.isEmpty(MapClientIdentify.getAppId())) {
                        MapClientIdentify.setAppId(MapClientUtil.getAppId(this.b));
                    }
                    ICreator a2 = com.huawei.hms.maps.internal.mab.a(this.b);
                    if (a2 == null) {
                        LogM.e("MapView", "createDelegate: creator == null");
                        a(this.b);
                        return;
                    } else {
                        LogM.e("MapView", "createDelegate: innerCreateDelegate creator = " + a2);
                        a(a2);
                        return;
                    }
                }
                sb = new StringBuilder("not load map hmsState = ");
                sb.append(isHmsAvailable);
            }
            LogM.e("MapView", sb.toString());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hms.maps.maa
        protected void a(ICreator iCreator) {
            LogM.i("MapView", "createDelegate: initDelegateFlag = " + this.f);
            if (this.f) {
                return;
            }
            this.f = true;
            try {
                MapClientIdentify mapClientIdentify = new MapClientIdentify();
                Context d = com.huawei.hms.maps.internal.mab.d(this.b);
                iCreator.initAppContext(ObjectWrapper.wrap(this.b));
                mapClientIdentify.regestIdentity(this.b, iCreator);
                IMapViewDelegate a2 = d != null ? a(iCreator, d, this.d) : null;
                if (a2 == null) {
                    LogM.w("MapView", "createDelegate: mapReadyCallbacks: mapViewDelegate is null");
                    this.f = false;
                    return;
                }
                LogM.i("MapView", "createDelegate: sdk MapView constructor mIMapViewDelegate:" + a2);
                a2.setActivity(ObjectWrapper.wrap(this.b));
                OnDelegateCreatedListener<mab> onDelegateCreatedListener = this.c;
                if (onDelegateCreatedListener != null) {
                    onDelegateCreatedListener.onDelegateCreated(new mab(this.f4937a, this.b, a2));
                }
                LogM.i("MapView", "createDelegate: mapReadyCallbacks.size = " + this.e.size());
                Iterator<OnMapReadyCallback> it = this.e.iterator();
                while (it.hasNext()) {
                    ((mab) getDelegate()).getMapAsync(it.next());
                }
                this.e.clear();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        protected IMapViewDelegate a(ICreator iCreator, Context context, HuaweiMapOptions huaweiMapOptions) {
            if (MapsAdvUtil.containsMapsBasic()) {
                return new MapViewDelegate(context, huaweiMapOptions);
            }
            try {
                LogM.i("MapView", "createDelegate: createMapViewDelegateRemote");
                return iCreator.newMapViewDelegate(ObjectWrapper.wrap(context), huaweiMapOptions);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        maa(ViewGroup viewGroup, Context context, HuaweiMapOptions huaweiMapOptions) {
            this.f4937a = viewGroup;
            this.b = context;
            this.d = huaweiMapOptions;
        }
    }

    protected MapView(Context context, HuaweiMapOptions huaweiMapOptions, boolean z) {
        super(context);
        this.b = z;
        a(context, huaweiMapOptions);
    }

    public MapView(Context context, HuaweiMapOptions huaweiMapOptions) {
        this(context, huaweiMapOptions, false);
    }

    protected MapView(Context context, AttributeSet attributeSet, int i, boolean z) {
        super(context, attributeSet, i);
        this.b = z;
        a(context, attributeSet != null ? HuaweiMapOptions.createFromAttributes(context, attributeSet) : null);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, false);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MapView(Context context) {
        this(context, (AttributeSet) null, 0);
    }
}
