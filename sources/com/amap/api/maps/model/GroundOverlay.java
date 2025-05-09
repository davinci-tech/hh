package com.amap.api.maps.model;

import android.text.TextUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class GroundOverlay extends BaseOverlay {
    private WeakReference<IGlOverlayLayer> glOverlayLayerRef;
    private float high;
    private GroundOverlayOptions options;
    private LatLng point;
    private float width;

    public GroundOverlay(IGlOverlayLayer iGlOverlayLayer, GroundOverlayOptions groundOverlayOptions, String str) {
        super(str);
        this.glOverlayLayerRef = new WeakReference<>(iGlOverlayLayer);
        this.options = groundOverlayOptions;
    }

    public final void remove() {
        try {
            IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
            if (iGlOverlayLayer != null) {
                iGlOverlayLayer.removeOverlay(this.overlayName);
            }
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions == null || groundOverlayOptions.getImage() == null) {
                return;
            }
            this.options.getImage().recycle();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final String getId() {
        try {
            return this.overlayName;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setPosition(LatLng latLng) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions == null || latLng == null) {
                return;
            }
            float f = this.width;
            if (f <= 0.0f) {
                f = groundOverlayOptions.getWidth();
            }
            float f2 = this.high;
            if (f2 <= 0.0f) {
                f2 = this.options.getHeight();
            }
            if (f == 0.0f) {
                this.point = latLng;
                return;
            }
            if (f2 == 0.0f) {
                this.options.position(latLng, f);
                a();
            } else if (f2 > 0.0f) {
                this.options.position(latLng, f, f2);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final LatLng getPosition() {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                return groundOverlayOptions.getLocation();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setDimensions(float f) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                LatLng latLng = this.point;
                if (latLng == null) {
                    latLng = groundOverlayOptions.getLocation();
                }
                if (latLng == null) {
                    this.width = f;
                } else {
                    this.options.position(latLng, f);
                    a();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setImage(BitmapDescriptor bitmapDescriptor) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                groundOverlayOptions.image(bitmapDescriptor);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setDimensions(float f, float f2) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                LatLng latLng = this.point;
                if (latLng == null) {
                    latLng = groundOverlayOptions.getLocation();
                }
                if (latLng == null) {
                    this.width = f;
                    this.high = f2;
                } else {
                    GroundOverlayOptions groundOverlayOptions2 = this.options;
                    groundOverlayOptions2.position(groundOverlayOptions2.getLocation(), f, f2);
                    a();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final float getWidth() {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                return groundOverlayOptions.getWidth();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public final float getHeight() {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                return groundOverlayOptions.getHeight();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public final void setPositionFromBounds(LatLngBounds latLngBounds) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions == null || latLngBounds == null) {
                return;
            }
            groundOverlayOptions.positionFromBounds(latLngBounds);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final LatLngBounds getBounds() {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                return groundOverlayOptions.getBounds();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setBearing(float f) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                groundOverlayOptions.bearing(f);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final float getBearing() {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                return groundOverlayOptions.getBearing();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public final void setZIndex(float f) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                groundOverlayOptions.zIndex(f);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final float getZIndex() {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                return groundOverlayOptions.getZIndex();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public final void setVisible(boolean z) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                groundOverlayOptions.visible(z);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final boolean isVisible() {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                return groundOverlayOptions.isVisible();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public final void setTransparency(float f) {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                groundOverlayOptions.transparency(f);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final float getTransparency() {
        try {
            GroundOverlayOptions groundOverlayOptions = this.options;
            if (groundOverlayOptions != null) {
                return groundOverlayOptions.getTransparency();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof GroundOverlay)) {
            try {
                if (super.equals(obj)) {
                    return true;
                }
                return ((GroundOverlay) obj).getId() == getId();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public final void destroy() {
        try {
            IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
            if (iGlOverlayLayer != null) {
                iGlOverlayLayer.removeOverlay(this.overlayName);
            }
        } catch (Throwable unused) {
        }
    }

    public final int hashCode() {
        return super.hashCode();
    }

    private void a() {
        IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
        if (TextUtils.isEmpty(this.overlayName) || iGlOverlayLayer == null) {
            return;
        }
        iGlOverlayLayer.updateOption(this.overlayName, this.options);
    }
}
