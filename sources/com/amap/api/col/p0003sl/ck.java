package com.amap.api.col.p0003sl;

import android.os.RemoteException;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* loaded from: classes2.dex */
public final class ck {

    /* renamed from: a, reason: collision with root package name */
    private final IAMapDelegate f940a;
    private TileOverlay b;
    private TileOverlay c;
    private boolean d = false;
    private boolean e = false;

    public ck(IAMapDelegate iAMapDelegate) {
        this.f940a = iAMapDelegate;
    }

    private void b() {
        if (this.b == null) {
            TileOverlayOptions tileProvider = new TileOverlayOptions().tileProvider(new dc(this.f940a.getMapConfig()));
            tileProvider.memCacheSize(10485760);
            tileProvider.diskCacheSize(20480);
            tileProvider.visible(this.d);
            try {
                this.b = this.f940a.addTileOverlay(tileProvider);
                this.c = this.f940a.addTileOverlay(tileProvider);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public final void a() {
        c();
        d();
    }

    private void c() {
        boolean e = e();
        if (e) {
            b();
        }
        if (this.d != e) {
            this.d = e;
            TileOverlay tileOverlay = this.b;
            if (tileOverlay != null) {
                tileOverlay.setVisible(e);
            }
        }
    }

    private void d() {
        boolean f = f();
        if (f) {
            b();
        }
        if (this.e != f) {
            this.e = f;
            TileOverlay tileOverlay = this.c;
            if (tileOverlay != null) {
                tileOverlay.setVisible(f);
            }
        }
    }

    private boolean e() {
        IAMapDelegate iAMapDelegate = this.f940a;
        if (iAMapDelegate == null) {
            return false;
        }
        return iAMapDelegate.getMapConfig().getMapLanguage().equals("en");
    }

    private static boolean f() {
        return MapsInitializer.isLoadWorldGridMap();
    }
}
