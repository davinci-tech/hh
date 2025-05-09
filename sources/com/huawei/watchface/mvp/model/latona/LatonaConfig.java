package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.jsoperation.JsUtil;

/* loaded from: classes9.dex */
public class LatonaConfig {

    @SerializedName("backgrounds")
    private Backgrounds mBackgrounds;

    @SerializedName("times")
    private HandStyles mHandStyles;

    @SerializedName(JsUtil.DATA_LIST)
    private LatonaDatas mLatonaDatas;

    @SerializedName("positions")
    private LatonaPositions mLatonaPositions;

    @SerializedName("styles")
    private LatonaStyles mLatonaStyles;

    public LatonaPositions getLatonaPositions() {
        return this.mLatonaPositions;
    }

    public void setLatonaPositions(LatonaPositions latonaPositions) {
        this.mLatonaPositions = latonaPositions;
    }

    public LatonaStyles getLatonaStyles() {
        return this.mLatonaStyles;
    }

    public void setLatonaStyles(LatonaStyles latonaStyles) {
        this.mLatonaStyles = latonaStyles;
    }

    public LatonaDatas getLatonaDatas() {
        return this.mLatonaDatas;
    }

    public void setLatonaDatas(LatonaDatas latonaDatas) {
        this.mLatonaDatas = latonaDatas;
    }

    public Backgrounds getBackgrounds() {
        return this.mBackgrounds;
    }

    public void setBackgrounds(Backgrounds backgrounds) {
        this.mBackgrounds = backgrounds;
    }

    public HandStyles getHandStyles() {
        return this.mHandStyles;
    }

    public void setHandStyles(HandStyles handStyles) {
        this.mHandStyles = handStyles;
    }

    public String toString() {
        return "LatonaConfig{mLatonaPositions=" + this.mLatonaPositions + ", mLatonaStyles=" + this.mLatonaStyles + ", mLatonaDatas=" + this.mLatonaDatas + ", mBackgrounds=" + this.mBackgrounds + ", mHandStyles=" + this.mHandStyles + '}';
    }
}
