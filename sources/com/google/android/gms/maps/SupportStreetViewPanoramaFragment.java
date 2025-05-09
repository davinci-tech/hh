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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class SupportStreetViewPanoramaFragment extends Fragment {
    private final zzb zzch = new zzb(this);

    @Override // androidx.fragment.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        this.zzch.onStop();
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.zzch.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzch.onSaveInstanceState(bundle);
    }

    static final class zza implements StreetViewLifecycleDelegate {
        private final Fragment fragment;
        private final IStreetViewPanoramaFragmentDelegate zzbt;

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStop() {
            try {
                this.zzbt.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStart() {
            try {
                this.zzbt.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzbt.onSaveInstanceState(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onResume() {
            try {
                this.zzbt.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onPause() {
            try {
                this.zzbt.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onLowMemory() {
            try {
                this.zzbt.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                Bundle bundle3 = new Bundle();
                zzby.zza(bundle2, bundle3);
                this.zzbt.onInflate(ObjectWrapper.wrap(activity), null, bundle3);
                zzby.zza(bundle3, bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroyView() {
            try {
                this.zzbt.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroy() {
            try {
                this.zzbt.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                IObjectWrapper onCreateView = this.zzbt.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle2);
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
                if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
                    zzby.zza(bundle2, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
                }
                this.zzbt.onCreate(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.maps.internal.StreetViewLifecycleDelegate
        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            try {
                this.zzbt.getStreetViewPanoramaAsync(new zzal(this, onStreetViewPanoramaReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public zza(Fragment fragment, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
            this.zzbt = (IStreetViewPanoramaFragmentDelegate) Preconditions.checkNotNull(iStreetViewPanoramaFragmentDelegate);
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.zzch.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.zzch.onPause();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        this.zzch.onLowMemory();
        super.onLowMemory();
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            super.onInflate(activity, attributeSet, bundle);
            this.zzch.setActivity(activity);
            this.zzch.onInflate(activity, new Bundle(), bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.zzch.onDestroyView();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.zzch.onDestroy();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzch.onCreateView(layoutInflater, viewGroup, bundle);
    }

    static final class zzb extends DeferredLifecycleHelper<zza> {
        private final Fragment fragment;
        private OnDelegateCreatedListener<zza> zzbc;
        private Activity zzbd;
        private final List<OnStreetViewPanoramaReadyCallback> zzbv = new ArrayList();

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            if (getDelegate() != null) {
                getDelegate().getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            } else {
                this.zzbv.add(onStreetViewPanoramaReadyCallback);
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
                this.zzbc.onDelegateCreated(new zza(this.fragment, zzbz.zza(this.zzbd).zzd(ObjectWrapper.wrap(this.zzbd))));
                Iterator<OnStreetViewPanoramaReadyCallback> it = this.zzbv.iterator();
                while (it.hasNext()) {
                    getDelegate().getStreetViewPanoramaAsync(it.next());
                }
                this.zzbv.clear();
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
        this.zzch.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzch.setActivity(activity);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzch.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }

    public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        supportStreetViewPanoramaFragment.setArguments(bundle);
        return supportStreetViewPanoramaFragment;
    }

    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }
}
