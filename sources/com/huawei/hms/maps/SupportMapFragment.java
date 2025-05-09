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
import androidx.fragment.app.Fragment;
import com.huawei.hms.common.Preconditions;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.feature.dynamic.OnDelegateCreatedListener;
import com.huawei.hms.maps.auth.AuthClient;
import com.huawei.hms.maps.internal.HmsUtil;
import com.huawei.hms.maps.internal.ICreator;
import com.huawei.hms.maps.internal.IHuaweiMapDelegate;
import com.huawei.hms.maps.internal.IMapFragmentDelegate;
import com.huawei.hms.maps.internal.IOnMapReadyCallback;
import com.huawei.hms.maps.model.RuntimeRemoteException;
import com.huawei.hms.maps.provider.inhuawei.MapFragmentDelegate;
import com.huawei.hms.maps.utils.LogM;
import com.huawei.hms.maps.utils.MapClientUtil;
import com.huawei.hms.maps.utils.MapsAdvUtil;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class SupportMapFragment extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f4943a = false;
    private final mac b;

    @Override // androidx.fragment.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        mac macVar = this.b;
        if (macVar != null) {
            macVar.onStop();
        }
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        mac macVar = this.b;
        if (macVar != null) {
            macVar.onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        LogM.d("SupportMapFragment", "onSaveInstanceState");
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
            super.onSaveInstanceState(bundle);
            mac macVar = this.b;
            if (macVar != null) {
                macVar.onSaveInstanceState(bundle);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        a(false);
        mac macVar = this.b;
        if (macVar != null) {
            macVar.onResume();
        }
    }

    static class mab extends com.huawei.hms.maps.mab implements mac {

        /* renamed from: a, reason: collision with root package name */
        private final Fragment f4945a;
        private IMapFragmentDelegate b;
        private Activity c;
        private boolean d;
        private boolean e;
        private HuaweiMapOptions f;
        private final List<OnMapReadyCallback> g;

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onStop() {
            try {
                this.b.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onStart() {
            try {
                this.b.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onSaveInstanceState(Bundle bundle) {
            try {
                this.b.onSaveInstanceState(com.huawei.hms.maps.internal.mac.a(bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onResume() {
            try {
                this.b.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onPause() {
            try {
                this.b.onPause();
                Activity activity = this.c;
                if (activity == null || !activity.isFinishing()) {
                    return;
                }
                LogM.d("SupportMapFragment", "clearResource in onPause method");
                b();
                this.d = true;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onLowMemory() {
            try {
                this.b.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                LogM.e("SupportMapFragment", "onInflate Bundle is null!");
                return;
            }
            this.f = (HuaweiMapOptions) bundle.getParcelable("HuaweiMapOptions");
            try {
                if (MapsAdvUtil.containsMapsBasic()) {
                    a();
                }
                this.b.onInflate(ObjectWrapper.wrap(activity), this.f, com.huawei.hms.maps.internal.mac.a(bundle2));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onDestroyView() {
            try {
                this.b.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onDestroy() {
            try {
                this.b.onDestroy();
                if (this.d) {
                    return;
                }
                Activity activity = this.c;
                if (activity == null || !activity.isFinishing()) {
                    LogM.d("SupportMapFragment", "onDestroy: execute clearResource in onDestroy method, but activity is  running");
                } else {
                    LogM.d("SupportMapFragment", "clearResource in onDestroy method");
                    b();
                }
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return mad.a(this.c, this.b, layoutInflater, viewGroup, bundle);
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public final void onCreate(Bundle bundle) {
            try {
                Bundle a2 = com.huawei.hms.maps.internal.mac.a(bundle);
                Bundle arguments = this.f4945a.getArguments();
                if (arguments != null && arguments.containsKey("HuaweiMapOptions")) {
                    HuaweiMapOptions huaweiMapOptions = (HuaweiMapOptions) arguments.getParcelable("HuaweiMapOptions");
                    this.f = huaweiMapOptions;
                    com.huawei.hms.maps.internal.mac.a(a2, "HuaweiMapOptions", huaweiMapOptions);
                }
                if (MapsAdvUtil.containsMapsBasic()) {
                    a();
                }
                this.b.onCreate(a2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.maps.mac
        public final void a(final OnMapReadyCallback onMapReadyCallback) {
            IMapFragmentDelegate iMapFragmentDelegate = this.b;
            if (iMapFragmentDelegate == null) {
                this.g.add(onMapReadyCallback);
                return;
            }
            try {
                iMapFragmentDelegate.getMapAsync(new IOnMapReadyCallback.Stub() { // from class: com.huawei.hms.maps.SupportMapFragment.mab.1
                    @Override // com.huawei.hms.maps.internal.IOnMapReadyCallback
                    public void onMapReady(IHuaweiMapDelegate iHuaweiMapDelegate) {
                        if (onMapReadyCallback != null) {
                            LogM.i("SupportMapFragment", "onMapReady: mapReadyCallback is not null");
                            if (MapsAdvUtil.containsMapsAdvance()) {
                                LogM.i("SupportMapFragment", "onMapReady: fullsdk ");
                                onMapReadyCallback.onMapReady(mab.this.a((com.huawei.hms.maps.provider.inhuawei.IHuaweiMapDelegate) iHuaweiMapDelegate));
                            } else {
                                onMapReadyCallback.onMapReady(new HuaweiMap(iHuaweiMapDelegate));
                            }
                        } else {
                            LogM.w("SupportMapFragment", "onMapReady: mapReadyCallback is null");
                        }
                        if (mab.this.f4945a.getView() != null) {
                            mab.this.f4945a.getView().setBackground(null);
                        }
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.huawei.hms.maps.mac
        public void a(Activity activity) {
            this.c = activity;
        }

        private void b() {
            MapsInitializer.initialize(null);
            MapClientIdentify.setAppContext(null);
            com.huawei.hms.maps.internal.mab.a();
        }

        private void a() {
            MapsInitializer.initialize(this.c);
            MapFragmentDelegate mapFragmentDelegate = new MapFragmentDelegate(this.c, this.f, this.e);
            this.b = mapFragmentDelegate;
            try {
                mapFragmentDelegate.onAttach(ObjectWrapper.wrap(this.c));
            } catch (RemoteException unused) {
                LogM.w("SupportMapFragment", TrackConstants$Events.EXCEPTION);
            }
            Iterator<OnMapReadyCallback> it = this.g.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            this.g.clear();
        }

        public mab(Fragment fragment, boolean z) {
            this.d = false;
            this.e = false;
            this.g = new ArrayList();
            this.e = z;
            this.f4945a = (Fragment) Preconditions.checkNotNull(fragment);
        }

        public mab(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate, Activity activity) {
            this.d = false;
            this.e = false;
            this.g = new ArrayList();
            this.b = (IMapFragmentDelegate) Preconditions.checkNotNull(iMapFragmentDelegate);
            this.f4945a = (Fragment) Preconditions.checkNotNull(fragment);
            this.c = activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        mac macVar = this.b;
        if (macVar != null) {
            macVar.onPause();
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        LogM.d("SupportMapFragment", "onLowMemory");
        mac macVar = this.b;
        if (macVar != null) {
            macVar.onLowMemory();
        }
        super.onLowMemory();
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        LogM.d("SupportMapFragment", "onInflate");
        if (this.b == null) {
            LogM.e("SupportMapFragment", "MapsInitializer is not initialized.");
            super.onInflate(activity, attributeSet, bundle);
            return;
        }
        MapClientIdentify.setMapAuthStartTime(System.currentTimeMillis());
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            LogM.d("SupportMapFragment", "onInflate");
            super.onInflate(activity, attributeSet, bundle);
            HuaweiMapOptions createFromAttributes = HuaweiMapOptions.createFromAttributes(activity, attributeSet);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("HuaweiMapOptions", createFromAttributes);
            if (getArguments() == null) {
                setArguments(bundle2);
            }
            this.b.a(activity);
            this.b.onInflate(activity, bundle2, bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public final void onExitAmbient() {
        LogM.d("SupportMapFragment", "onExitAmbient: ");
    }

    public final void onEnterAmbient(Bundle bundle) {
        LogM.d("SupportMapFragment", "onEnterAmbient: ");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogM.d("SupportMapFragment", "onDestroyView");
        mac macVar = this.b;
        if (macVar != null) {
            macVar.onDestroyView();
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogM.d("SupportMapFragment", "onDestroy");
        mac macVar = this.b;
        if (macVar != null) {
            macVar.onDestroy();
        }
        HmsUtil.setRepeatFlag(true);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogM.d("SupportMapFragment", "onCreateView: ");
        if (this.b == null) {
            LogM.e("SupportMapFragment", "MapsInitializer is not initialized.");
            return new View(getActivity());
        }
        if (MapClientIdentify.getMapAuthStartTime() == 0) {
            MapClientIdentify.setMapAuthStartTime(System.currentTimeMillis());
        }
        View onCreateView = this.b.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setBackground(new mae().a(onCreateView));
        onCreateView.setClickable(true);
        return onCreateView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.b == null) {
            LogM.e("SupportMapFragment", "MapsInitializer is not initialized.");
            return;
        }
        a(true);
        com.huawei.hms.maps.internal.mab.e(getActivity());
        com.huawei.hms.maps.internal.mab.b(true);
        if (MapsAdvUtil.containsMapsBasic()) {
            com.huawei.hms.maps.common.util.maa.a(getActivity());
            AuthClient.getInstance().startAuth(getActivity());
        }
        this.b.onCreate(bundle);
    }

    static class maa extends com.huawei.hms.maps.maa<mab> implements mac {

        /* renamed from: a, reason: collision with root package name */
        private final Fragment f4944a;
        private OnDelegateCreatedListener<mab> c;
        private Activity d;
        private HuaweiMapOptions e;
        private boolean f;
        private final List<OnMapReadyCallback> b = new ArrayList();
        private boolean g = false;

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper
        public final void createDelegate(OnDelegateCreatedListener<mab> onDelegateCreatedListener) {
            if (SupportMapFragment.b()) {
                return;
            }
            this.c = onDelegateCreatedListener;
            StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
            try {
                c();
            } finally {
                StrictMode.setThreadPolicy(threadPolicy);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hms.maps.maa
        protected void a(ICreator iCreator) {
            LogM.i("SupportMapFragment", "initDelegate: initDelegateFlag = " + this.g);
            if (this.g) {
                return;
            }
            this.g = true;
            try {
                MapClientIdentify mapClientIdentify = new MapClientIdentify();
                Context d = com.huawei.hms.maps.internal.mab.d(this.d);
                iCreator.initAppContext(ObjectWrapper.wrap(this.d));
                mapClientIdentify.regestIdentity(this.d, iCreator);
                IMapFragmentDelegate a2 = d != null ? a(iCreator, d, this.e) : null;
                if (a2 == null) {
                    LogM.w("SupportMapFragment", "innerCreateDelegate: supportMapFragmentDelegate is null");
                    this.g = false;
                    return;
                }
                a2.onAttach(ObjectWrapper.wrap(this.d));
                OnDelegateCreatedListener<mab> onDelegateCreatedListener = this.c;
                if (onDelegateCreatedListener != null) {
                    onDelegateCreatedListener.onDelegateCreated(new mab(this.f4944a, a2, this.d));
                }
                Iterator<OnMapReadyCallback> it = this.b.iterator();
                while (it.hasNext()) {
                    ((mab) getDelegate()).a(it.next());
                }
                this.b.clear();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hms.maps.mac
        public final void a(OnMapReadyCallback onMapReadyCallback) {
            if (getDelegate() != 0) {
                ((mab) getDelegate()).a(onMapReadyCallback);
            } else {
                this.b.add(onMapReadyCallback);
            }
        }

        @Override // com.huawei.hms.maps.mac
        public void a(Activity activity) {
            this.d = activity;
        }

        protected IMapFragmentDelegate a(ICreator iCreator, Context context, HuaweiMapOptions huaweiMapOptions) {
            try {
                return this.f ? iCreator.newTextureMapFragmentDelegate(ObjectWrapper.wrap(context), huaweiMapOptions) : iCreator.newMapFragmentDelegate(ObjectWrapper.wrap(context), huaweiMapOptions);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        private void c() {
            if (b()) {
                return;
            }
            com.huawei.hms.maps.common.util.maa.a(this.d.getApplicationContext());
            MapsInitializer.initialize(this.d);
            int isHmsAvailable = HmsUtil.isHmsAvailable(this.d);
            if (isHmsAvailable != 0) {
                LogM.e("SupportMapFragment", "not load map hmsState = " + isHmsAvailable);
                return;
            }
            MapClientIdentify.initApiKey(this.d.getApplicationContext());
            if (TextUtils.isEmpty(MapClientIdentify.getAppId())) {
                MapClientIdentify.setAppId(MapClientUtil.getAppId(this.d.getApplicationContext()));
            }
            Bundle arguments = this.f4944a.getArguments();
            if (arguments != null && arguments.containsKey("HuaweiMapOptions")) {
                this.e = (HuaweiMapOptions) arguments.getParcelable("HuaweiMapOptions");
            }
            ICreator a2 = com.huawei.hms.maps.internal.mab.a(this.d);
            if (a2 == null) {
                a((Context) this.d);
            } else {
                a(a2);
            }
        }

        private boolean b() {
            return this.d == null || this.c == null || getDelegate() != 0;
        }

        maa(Fragment fragment, boolean z) {
            this.f4944a = fragment;
            this.f = z;
        }

        maa(Fragment fragment) {
            this.f4944a = fragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        LogM.d("SupportMapFragment", "onAttach");
        super.onAttach(activity);
        mac macVar = this.b;
        if (macVar != null) {
            macVar.a(activity);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        LogM.d("SupportMapFragment", "getMapAsync: ");
        mac macVar = this.b;
        if (macVar != null) {
            macVar.a(onMapReadyCallback);
        }
    }

    public static SupportMapFragment newInstance(HuaweiMapOptions huaweiMapOptions) {
        LogM.i("SupportMapFragment", "SupportMapFragment construct");
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("HuaweiMapOptions", huaweiMapOptions);
        supportMapFragment.setArguments(bundle);
        return supportMapFragment;
    }

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b() {
        return f4943a;
    }

    private static void a(boolean z) {
        f4943a = z;
    }

    public SupportMapFragment() {
        if (MapsInitializer.a()) {
            this.b = MapsAdvUtil.containsMapsBasic() ? new mab(this, false) : new maa(this);
        } else {
            LogM.e("SupportMapFragment", "MapsInitializer is not initialized.");
            this.b = null;
        }
    }
}
