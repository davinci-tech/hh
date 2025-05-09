package com.amap.api.maps.model;

import android.graphics.Typeface;
import com.amap.api.col.p0003sl.cm;

/* loaded from: classes8.dex */
public final class Text {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;
    private Marker marker;
    private TextOptions textOptions;

    public Text(Marker marker, TextOptions textOptions) {
        this.marker = marker;
        this.textOptions = textOptions;
    }

    public final void remove() {
        try {
            this.marker.remove();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void destroy() {
        try {
            this.marker.destroy();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final String getId() {
        try {
            return this.marker.getId();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setPosition(LatLng latLng) {
        try {
            this.marker.setPosition(latLng);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final LatLng getPosition() {
        try {
            return this.marker.getPosition();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setText(String str) {
        try {
            this.textOptions.text(str);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a() {
        this.marker.setIcon(cm.b(this.textOptions));
    }

    public final String getText() {
        try {
            return this.textOptions.getText();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setBackgroundColor(int i) {
        try {
            this.textOptions.backgroundColor(i);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final int getBackgroundColor() {
        try {
            return this.textOptions.getBackgroundColor();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public final void setFontColor(int i) {
        try {
            this.textOptions.fontColor(i);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final int getFontColor() {
        try {
            return this.textOptions.getFontColor();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public final void setFontSize(int i) {
        try {
            this.textOptions.fontSize(i);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final int getFontSize() {
        try {
            return this.textOptions.getFontSize();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public final void setTypeface(Typeface typeface) {
        try {
            this.textOptions.typeface(typeface);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final Typeface getTypeface() {
        try {
            return this.textOptions.getTypeface();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setAlign(int i, int i2) {
        try {
            this.textOptions.align(i, i2);
            this.marker.setAnchor(cm.a(this.textOptions.getAlignX(), true), cm.a(this.textOptions.getAlignY(), false));
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final int getAlignX() {
        try {
            return this.textOptions.getAlignX();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public final int getAlignY() {
        try {
            return this.textOptions.getAlignY();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public final void setVisible(boolean z) {
        try {
            this.marker.setVisible(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final boolean isVisible() {
        try {
            return this.marker.isVisible();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Text)) {
            return false;
        }
        try {
            return this.marker.equals(((Text) obj).marker);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public final int hashCode() {
        return this.marker.hashCode();
    }

    public final void setObject(Object obj) {
        this.marker.setObject(obj);
    }

    public final Object getObject() {
        return this.marker.getObject();
    }

    public final void setRotate(float f) {
        try {
            this.marker.setRotateAngle(f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final float getRotate() {
        return this.marker.getRotateAngle();
    }

    public final void setZIndex(float f) {
        this.marker.setZIndex(f);
    }

    public final float getZIndex() {
        return this.marker.getZIndex();
    }
}
