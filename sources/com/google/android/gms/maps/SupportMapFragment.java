package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class SupportMapFragment extends Fragment {
    private final zzb zzcg = new zzb(this);

    @Override // androidx.fragment.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        this.zzcg.onStop();
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.zzcg.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzcg.onSaveInstanceState(bundle);
    }

    static final class zza implements MapLifecycleDelegate {
        private final Fragment fragment;
        private final IMapFragmentDelegate zzba;

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStop() {
            try {
                this.zzba.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStart() {
            try {
                this.zzba.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzba.onSaveInstanceState(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onResume() {
            try {
                this.zzba.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onPause() {
            try {
                this.zzba.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onLowMemory() {
            try {
                this.zzba.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            GoogleMapOptions googleMapOptions = (GoogleMapOptions) bundle.getParcelable("MapOptions");
            try {
                Bundle bundle3 = new Bundle();
                zzby.zza(bundle2, bundle3);
                this.zzba.onInflate(ObjectWrapper.wrap(activity), googleMapOptions, bundle3);
                zzby.zza(bundle3, bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onExitAmbient() {
            try {
                this.zzba.onExitAmbient();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onEnterAmbient(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzba.onEnterAmbient(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroyView() {
            try {
                this.zzba.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroy() {
            try {
                this.zzba.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                IObjectWrapper onCreateView = this.zzba.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle2);
                zzby.zza(bundle2, bundle);
                return (View) ObjectWrapper.unwrap(onCreateView);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onCreate(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                Bundle arguments = this.fragment.getArguments();
                if (arguments != null && arguments.containsKey("MapOptions")) {
                    zzby.zza(bundle2, "MapOptions", arguments.getParcelable("MapOptions"));
                }
                this.zzba.onCreate(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.maps.internal.MapLifecycleDelegate
        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            try {
                this.zzba.getMapAsync(new zzak(this, onMapReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public zza(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.zzba = (IMapFragmentDelegate) Preconditions.checkNotNull(iMapFragmentDelegate);
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.zzcg.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.zzcg.onPause();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        this.zzcg.onLowMemory();
        super.onLowMemory();
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            super.onInflate(activity, attributeSet, bundle);
            this.zzcg.setActivity(activity);
            GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("MapOptions", createFromAttributes);
            this.zzcg.onInflate(activity, bundle2, bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient must be called on the main thread.");
        zzb zzbVar = this.zzcg;
        if (zzbVar.getDelegate() != null) {
            zzbVar.getDelegate().onExitAmbient();
        }
    }

    public final void onEnterAmbient(Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient must be called on the main thread.");
        zzb zzbVar = this.zzcg;
        if (zzbVar.getDelegate() != null) {
            zzbVar.getDelegate().onEnterAmbient(bundle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.zzcg.onDestroyView();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.zzcg.onDestroy();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = this.zzcg.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setClickable(true);
        return onCreateView;
    }

    static final class zzb extends DeferredLifecycleHelper<zza> {
        private final Fragment fragment;
        private OnDelegateCreatedListener<zza> zzbc;
        private Activity zzbd;
        private final List<OnMapReadyCallback> zzbe = new ArrayList();

        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            if (getDelegate() != null) {
                getDelegate().getMapAsync(onMapReadyCallback);
            } else {
                this.zzbe.add(onMapReadyCallback);
            }
        }

        @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper
        public final void createDelegate(OnDelegateCreatedListener<zza> onDelegateCreatedListener) {
            this.zzbc = onDelegateCreatedListener;
            zzc();
        }

        private final void zzc() {
            if (this.zzbd == null || this.zzbc == null || getDelegate() != null) {
                return;
            }
            try {
                MapsInitializer.initialize(this.zzbd);
                IMapFragmentDelegate zzc = zzbz.zza(this.zzbd).zzc(ObjectWrapper.wrap(this.zzbd));
                if (zzc == null) {
                    return;
                }
                this.zzbc.onDelegateCreated(new zza(this.fragment, zzc));
                Iterator<OnMapReadyCallback> it = this.zzbe.iterator();
                while (it.hasNext()) {
                    getDelegate().getMapAsync(it.next());
                }
                this.zzbe.clear();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            } catch (GooglePlayServicesNotAvailableException unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setActivity(Activity activity) {
            this.zzbd = activity;
            zzc();
        }

        zzb(Fragment fragment) {
            this.fragment = fragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzcg.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzcg.setActivity(activity);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync must be called on the main thread.");
        this.zzcg.getMapAsync(onMapReadyCallback);
    }

    public static SupportMapFragment newInstance(GoogleMapOptions googleMapOptions) {
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        supportMapFragment.setArguments(bundle);
        return supportMapFragment;
    }

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }
}
