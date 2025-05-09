package com.huawei.healthcloud.plugintrack.ui.map;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.TextureMapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.map.HiMapHolder;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.HuaweiMapOptions;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import defpackage.gwe;
import defpackage.hke;
import defpackage.hki;
import defpackage.hkk;
import defpackage.hkm;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class HiMapHolder extends HealthCardView {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f3770a;
    private LinearLayout b;
    private InterfaceHiMap c;
    private SupportMapFragment d;
    private MapView e;
    private View h;
    private TextureMapView j;

    public HiMapHolder(Context context) {
        super(context);
        this.j = null;
        this.d = null;
        this.e = null;
        this.f3770a = null;
        c(context);
    }

    public HiMapHolder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = null;
        this.d = null;
        this.e = null;
        this.f3770a = null;
        c(context);
    }

    public HiMapHolder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = null;
        this.d = null;
        this.e = null;
        this.f3770a = null;
        c(context);
    }

    private void c(Context context) {
        this.h = View.inflate(context, R.layout.layout_map_holder, this);
    }

    public InterfaceHiMap getHiMap() {
        return this.c;
    }

    public InterfaceHiMap bhK_(Bundle bundle, Context context) {
        LogUtil.a("Track_HiMapHolder", "GAODE_MAP_TYPE");
        if (this.b != null && this.j != null && (this.c instanceof hkk)) {
            LogUtil.a("Track_HiMapHolder", "gaode map exist");
            return this.c;
        }
        MapsInitializer.updatePrivacyShow(context, true, true);
        MapsInitializer.updatePrivacyAgree(context, true);
        MapsInitializer.setSupportRecycleView(false);
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.map_container);
        this.b = linearLayout;
        linearLayout.removeAllViews();
        this.j = new TextureMapView(getContext());
        this.b.addView(this.j, new LinearLayout.LayoutParams(-1, -1));
        this.j.onCreate(bundle);
        MapsInitializer.setProtocol(2);
        hkk hkkVar = new hkk(context, this.j, true);
        this.c = hkkVar;
        return hkkVar;
    }

    public InterfaceHiMap bhJ_(Bundle bundle, Context context) {
        LogUtil.a("Track_HiMapHolder", "EMPTY_MAP_TYPE");
        if (this.b != null && this.f3770a != null && (this.c instanceof hke)) {
            LogUtil.a("Track_HiMapHolder", "empty map exist");
            return this.c;
        }
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.map_container);
        this.b = linearLayout;
        linearLayout.removeAllViews();
        this.f3770a = new LinearLayout(getContext());
        this.b.addView(this.f3770a, new LinearLayout.LayoutParams(-1, -1));
        hke hkeVar = new hke(context, this.f3770a, true);
        this.c = hkeVar;
        return hkeVar;
    }

    public void a(final Context context, Fragment fragment, final SyncMapCallback syncMapCallback) {
        FragmentManager fragmentManager;
        if (this.b != null && this.d != null && (this.c instanceof hki)) {
            LogUtil.a("Track_HiMapHolder", "google map exist");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.map_container);
        this.b = linearLayout;
        linearLayout.removeAllViews();
        if (fragment != null) {
            fragmentManager = fragment.getChildFragmentManager();
        } else if (context instanceof FragmentActivity) {
            fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        } else {
            LogUtil.h("Track_HiMapHolder", "fragmentManager is null");
            fragmentManager = null;
        }
        if (fragmentManager != null) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(SupportMapFragment.class.getName());
            if (findFragmentByTag != null) {
                fragmentManager.beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
            }
            this.d = SupportMapFragment.newInstance();
            try {
                fragmentManager.beginTransaction().add(R.id.map_container, this.d, SupportMapFragment.class.getName()).commitAllowingStateLoss();
                this.d.getMapAsync(new OnMapReadyCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.map.HiMapHolder.2
                    @Override // com.google.android.gms.maps.OnMapReadyCallback
                    public void onMapReady(GoogleMap googleMap) {
                        LogUtil.a("Track_HiMapHolder", "GoogleMap onMapReady", Integer.valueOf(googleMap.getMapType()));
                        HiMapHolder hiMapHolder = HiMapHolder.this;
                        hiMapHolder.c = new hki(context, hiMapHolder.d, googleMap);
                        SyncMapCallback syncMapCallback2 = syncMapCallback;
                        if (syncMapCallback2 != null) {
                            syncMapCallback2.onMapReady(HiMapHolder.this.c);
                        }
                    }
                });
            } catch (IllegalArgumentException unused) {
                ReleaseLogUtil.c("Track_HiMapHolder", "commitAllowingStateLoss illegalArgumentException");
            }
        }
    }

    public void b(final Context context, final SyncMapCallback syncMapCallback) {
        LogUtil.a("Track_HiMapHolder", "HMS_MAP_TYPE");
        if (this.b != null && this.e != null && (this.c instanceof hkm)) {
            LogUtil.a("Track_HiMapHolder", "hms map exist");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.map_container);
        this.b = linearLayout;
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        com.huawei.hms.maps.MapsInitializer.initialize(context);
        HuaweiMapOptions huaweiMapOptions = new HuaweiMapOptions();
        Location aUH_ = gwe.aUH_(BaseApplication.getContext());
        if (aUH_ != null) {
            double[] aUD_ = gwe.aUD_(BaseApplication.getContext(), aUH_);
            huaweiMapOptions.camera(new CameraPosition(new LatLng(aUD_[0], aUD_[1]), 16.5f, 0.0f, 0.0f));
        }
        huaweiMapOptions.compassEnabled(false);
        MapView mapView = new MapView(context, huaweiMapOptions);
        this.e = mapView;
        mapView.onCreate(null);
        this.e.onStart();
        this.e.onResume();
        this.e.getMapAsync(new com.huawei.hms.maps.OnMapReadyCallback() { // from class: hkl
            @Override // com.huawei.hms.maps.OnMapReadyCallback
            public final void onMapReady(HuaweiMap huaweiMap) {
                HiMapHolder.this.d(context, syncMapCallback, huaweiMap);
            }
        });
        this.b.addView(this.e, layoutParams);
    }

    public /* synthetic */ void d(Context context, SyncMapCallback syncMapCallback, HuaweiMap huaweiMap) {
        LogUtil.c("Track_HiMapHolder", "hms onMapReady");
        hkm hkmVar = new hkm(context, huaweiMap, this.e);
        this.c = hkmVar;
        if (syncMapCallback != null) {
            syncMapCallback.onMapReady(hkmVar);
        }
    }

    public void b() {
        InterfaceHiMap interfaceHiMap = this.c;
        if (interfaceHiMap != null) {
            interfaceHiMap.onDestroy();
            this.c = null;
        }
        TextureMapView textureMapView = this.j;
        if (textureMapView != null) {
            textureMapView.onDestroy();
        }
        LinearLayout linearLayout = this.b;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        this.b = null;
        this.j = null;
        this.e = null;
        this.f3770a = null;
    }
}
