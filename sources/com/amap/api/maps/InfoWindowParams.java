package com.amap.api.maps;

import android.view.View;

/* loaded from: classes2.dex */
public class InfoWindowParams {
    public static final int INFOWINDOW_TYPE_IMAGE = 1;
    public static final int INFOWINDOW_TYPE_VIEW = 2;
    private View infoContent;
    private View infoWindow;
    private int infoWindowType = 2;
    private int mInfoWindowUpdateTime;

    public void setInfoWindowUpdateTime(int i) {
        this.mInfoWindowUpdateTime = i;
    }

    public long getInfoWindowUpdateTime() {
        return this.mInfoWindowUpdateTime;
    }

    public void setInfoWindowType(int i) {
        this.infoWindowType = i;
    }

    public int getInfoWindowType() {
        return this.infoWindowType;
    }

    public View getInfoWindow() {
        return this.infoWindow;
    }

    public void setInfoContent(View view) {
        this.infoContent = view;
    }

    public void setInfoWindow(View view) {
        this.infoWindow = view;
    }

    public View getInfoContents() {
        return this.infoContent;
    }
}
