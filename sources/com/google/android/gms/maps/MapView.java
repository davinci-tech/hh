package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class MapView extends FrameLayout {
    private final zzb zzbf;

    static final class zza implements MapLifecycleDelegate {
        private final ViewGroup parent;
        private final IMapViewDelegate zzbg;
        private View zzbh;

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStop() {
            try {
                this.zzbg.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStart() {
            try {
                this.zzbg.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzbg.onSaveInstanceState(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onResume() {
            try {
                this.zzbg.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onPause() {
            try {
                this.zzbg.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onLowMemory() {
            try {
                this.zzbg.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }

        public final void onExitAmbient() {
            try {
                this.zzbg.onExitAmbient();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onEnterAmbient(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzbg.onEnterAmbient(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroy() {
            try {
                this.zzbg.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onCreate(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzbg.onCreate(bundle2);
                zzby.zza(bundle2, bundle);
                this.zzbh = (View) ObjectWrapper.unwrap(this.zzbg.getView());
                this.parent.removeAllViews();
                this.parent.addView(this.zzbh);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.maps.internal.MapLifecycleDelegate
        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            try {
                this.zzbg.getMapAsync(new zzac(this, onMapReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public zza(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.zzbg = (IMapViewDelegate) Preconditions.checkNotNull(iMapViewDelegate);
            this.parent = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        }
    }

    public final void onStop() {
        this.zzbf.onStop();
    }

    public final void onStart() {
        this.zzbf.onStart();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzbf.onSaveInstanceState(bundle);
    }

    public final void onResume() {
        this.zzbf.onResume();
    }

    public final void onPause() {
        this.zzbf.onPause();
    }

    public final void onLowMemory() {
        this.zzbf.onLowMemory();
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
        zzb zzbVar = this.zzbf;
        if (zzbVar.getDelegate() != null) {
            zzbVar.getDelegate().onExitAmbient();
        }
    }

    public final void onEnterAmbient(Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
        zzb zzbVar = this.zzbf;
        if (zzbVar.getDelegate() != null) {
            zzbVar.getDelegate().onEnterAmbient(bundle);
        }
    }

    public final void onDestroy() {
        this.zzbf.onDestroy();
    }

    public final void onCreate(Bundle bundle) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            this.zzbf.onCreate(bundle);
            if (this.zzbf.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
        this.zzbf.getMapAsync(onMapReadyCallback);
    }

    static final class zzb extends DeferredLifecycleHelper<zza> {
        private OnDelegateCreatedListener<zza> zzbc;
        private final List<OnMapReadyCallback> zzbe = new ArrayList();
        private final ViewGroup zzbi;
        private final Context zzbj;
        private final GoogleMapOptions zzbk;

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
            if (onDelegateCreatedListener == null || getDelegate() != null) {
                return;
            }
            try {
                MapsInitializer.initialize(this.zzbj);
                IMapViewDelegate zza = zzbz.zza(this.zzbj).zza(ObjectWrapper.wrap(this.zzbj), this.zzbk);
                if (zza == null) {
                    return;
                }
                this.zzbc.onDelegateCreated(new zza(this.zzbi, zza));
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

        zzb(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.zzbi = viewGroup;
            this.zzbj = context;
            this.zzbk = googleMapOptions;
        }
    }

    public MapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context);
        this.zzbf = new zzb(this, context, googleMapOptions);
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzbf = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzbf = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context) {
        super(context);
        this.zzbf = new zzb(this, context, null);
        setClickable(true);
    }
}
