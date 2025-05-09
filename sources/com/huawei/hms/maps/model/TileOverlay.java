package com.huawei.hms.maps.model;

import android.os.RemoteException;
import com.huawei.hms.maps.model.internal.ITileOverlayDelegate;

/* loaded from: classes9.dex */
public final class TileOverlay {

    /* renamed from: a, reason: collision with root package name */
    private final ITileOverlayDelegate f5012a;

    public void setZIndex(float f) {
        try {
            this.f5012a.setZIndex(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f5012a.setVisible(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTransparency(float f) {
        try {
            this.f5012a.setTransparency(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFadeIn(boolean z) {
        try {
            this.f5012a.setFadeIn(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.f5012a.remove();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.f5012a.isVisible();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.f5012a.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        try {
            return this.f5012a.getZIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getTransparency() {
        try {
            return this.f5012a.getTransparency();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.f5012a.getId();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean getFadeIn() {
        try {
            return this.f5012a.getFadeIn();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TileOverlay)) {
            return false;
        }
        try {
            return this.f5012a.equalsRemote(((TileOverlay) obj).f5012a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void clearTileCache() {
        try {
            this.f5012a.clearTileCache();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public TileOverlay(ITileOverlayDelegate iTileOverlayDelegate) {
        this.f5012a = iTileOverlayDelegate;
    }
}
