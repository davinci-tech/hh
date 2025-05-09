package com.huawei.hms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hms.maps.MapFragment;
import com.huawei.hms.maps.auth.AuthClient;
import com.huawei.hms.maps.internal.HmsUtil;
import com.huawei.hms.maps.utils.LogM;
import com.huawei.hms.maps.utils.MapsAdvUtil;

/* loaded from: classes9.dex */
public class TextureMapFragment extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private final mac f4947a;

    @Override // android.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    @Override // android.app.Fragment
    public void onStop() {
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.onStop();
        }
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.onStart();
        }
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        LogM.d("MapFragment", "onSaveInstanceState: ");
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
            super.onSaveInstanceState(bundle);
            mac macVar = this.f4947a;
            if (macVar != null) {
                macVar.onSaveInstanceState(bundle);
            }
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.onResume();
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        LogM.d("MapFragment", "onPause: ");
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.onPause();
        }
        super.onPause();
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        LogM.d("MapFragment", "onLowMemory: ");
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.onLowMemory();
        }
        super.onLowMemory();
    }

    @Override // android.app.Fragment
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        LogM.d("MapFragment", "onInflate: ");
        if (this.f4947a == null) {
            LogM.e("MapFragment", "MapsInitializer is not initialized.");
            super.onInflate(activity, attributeSet, bundle);
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            HuaweiMapOptions createFromAttributes = HuaweiMapOptions.createFromAttributes(activity, attributeSet);
            super.onInflate(activity, attributeSet, bundle);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("HuaweiMapOptions", createFromAttributes);
            if (getArguments() == null) {
                setArguments(bundle2);
            }
            this.f4947a.a(activity);
            this.f4947a.onInflate(activity, bundle2, bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public final void onExitAmbient() {
        LogM.d("MapFragment", "onExitAmbient: ");
    }

    public final void onEnterAmbient(Bundle bundle) {
        LogM.d("MapFragment", "onEnterAmbient: ");
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.onDestroyView();
        }
        super.onDestroyView();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        LogM.d("MapFragment", "onDestroy: ");
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.onDestroy();
        }
        HmsUtil.setRepeatFlag(true);
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogM.d("MapFragment", "onCreateView: ");
        if (this.f4947a == null) {
            LogM.e("MapFragment", "MapsInitializer is not initialized.");
            return new View(getActivity());
        }
        if (MapClientIdentify.getMapAuthStartTime() == 0) {
            MapClientIdentify.setMapAuthStartTime(System.currentTimeMillis());
        }
        View onCreateView = this.f4947a.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setBackground(new mae().a(onCreateView));
        onCreateView.setClickable(true);
        return onCreateView;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        LogM.d("MapFragment", "onCreate: ");
        super.onCreate(bundle);
        if (this.f4947a == null) {
            LogM.e("MapFragment", "MapsInitializer is not initialized.");
            return;
        }
        if (MapsAdvUtil.containsMapsBasic()) {
            com.huawei.hms.maps.common.util.maa.a(getActivity());
            AuthClient.getInstance().startAuth(getActivity());
        }
        this.f4947a.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        LogM.d("MapFragment", "onAttach: ");
        super.onAttach(activity);
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.a(activity);
        }
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        LogM.d("MapFragment", "getMapAsync: ");
        mac macVar = this.f4947a;
        if (macVar != null) {
            macVar.a(onMapReadyCallback);
        }
    }

    public static TextureMapFragment newInstance(HuaweiMapOptions huaweiMapOptions) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("HuaweiMapOptions", huaweiMapOptions);
        TextureMapFragment textureMapFragment = new TextureMapFragment();
        textureMapFragment.setArguments(bundle);
        return textureMapFragment;
    }

    public static TextureMapFragment newInstance() {
        return new TextureMapFragment();
    }

    public TextureMapFragment() {
        if (MapsInitializer.a()) {
            this.f4947a = MapsAdvUtil.containsMapsBasic() ? new MapFragment.mab(this) : new MapFragment.maa(this, true);
        } else {
            LogM.e("MapFragment", "MapsInitializer is not initialized.");
            this.f4947a = null;
        }
    }
}
