package com.autonavi.base.amap.mapcore;

import android.opengl.Matrix;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMapConfig;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class MapConfig implements IMapConfig, Cloneable {
    public static final int DEFAULT_RATIO = 1;
    private static final int GEO_POINT_ZOOM = 20;
    public static final float MAX_ZOOM = 20.0f;
    public static final float MAX_ZOOM_INDOOR = 20.0f;
    public static final float MIN_ZOOM = 3.0f;
    public static final int MSG_ACTION_ONBASEPOICLICK = 20;
    public static final int MSG_ACTION_ONMAPCLICK = 19;
    public static final int MSG_AUTH_FAILURE = 2;
    public static final int MSG_CALLBACK_MAPLOADED = 16;
    public static final int MSG_CALLBACK_ONTOUCHEVENT = 14;
    public static final int MSG_CALLBACK_SCREENSHOT = 15;
    public static final int MSG_CAMERAUPDATE_CHANGE = 10;
    public static final int MSG_CAMERAUPDATE_FINISH = 11;
    public static final int MSG_COMPASSVIEW_CHANGESTATE = 13;
    public static final int MSG_INFOWINDOW_UPDATE = 18;
    public static final int MSG_TILEOVERLAY_REFRESH = 17;
    public static final int MSG_ZOOMVIEW_CHANGESTATE = 12;
    private static final int TILE_SIZE_POW = 8;
    private String customTextureResourcePath;
    private boolean isSetLimitZoomLevel;
    MapConfig lastMapconfig;
    private IPoint[] limitIPoints;
    private LatLngBounds limitLatLngBounds;
    private String mCustomStyleID;
    private String mCustomStylePath;
    private int mapHeight;
    private float mapPerPixelUnitLength;
    private int mapWidth;
    private float skyHeight;
    public float maxZoomLevel = 20.0f;
    public float minZoomLevel = 3.0f;
    private FPoint[] mapRect = null;
    private Rectangle geoRectangle = new Rectangle();
    private boolean isIndoorEnable = false;
    private boolean isBuildingEnable = true;
    private boolean isMapTextEnable = true;
    private boolean isTrafficEnabled = false;
    private boolean isCustomStyleEnabled = false;
    private double sX = 2.21010267E8d;
    private double sY = 1.01697799E8d;
    private DPoint mapGeoCenter = new DPoint(this.sX, this.sY);
    private float sZ = 10.0f;
    private float sC = 0.0f;
    private float sR = 0.0f;
    private boolean isCenterChanged = false;
    private boolean isZoomChanged = false;
    private boolean isTiltChanged = false;
    private boolean isBearingChanged = false;
    private boolean isNeedUpdateZoomControllerState = false;
    private boolean isNeedUpdateMapRectNextFrame = false;
    private int mMapStyleMode = 0;
    private int mMapStyleTime = 0;
    private int mMapStyleState = 0;
    private int anchorX = 0;
    private String mMapLanguage = "zh_cn";
    private boolean isHideLogoEnable = false;
    private boolean isWorldMapEnable = false;
    private boolean isTouchPoiEnable = true;
    private int abroadState = 1;
    private boolean isAbroadEnable = false;
    private boolean isTerrainEnable = false;
    float[] viewMatrix = new float[16];
    float[] projectionMatrix = new float[16];
    float[] mvpMatrix = new float[16];
    private int[] tilsIDs = new int[100];
    private boolean mapEnable = true;
    private int anchorY = 0;
    private boolean isProFunctionAuthEnable = true;
    private boolean isUseProFunction = false;
    private int customBackgroundColor = -1;
    private float mapZoomScale = 1.0f;
    private AtomicInteger changedCounter = new AtomicInteger(0);
    private volatile double changeRatio = 1.0d;
    private volatile double changeGridRatio = 1.0d;
    private int gridX = 0;
    private int gridY = 0;

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getAnchorY() {
        return this.anchorY;
    }

    public void setAnchorY(int i) {
        this.anchorY = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getAnchorX() {
        return this.anchorX;
    }

    public void setAnchorX(int i) {
        this.anchorX = i;
    }

    public MapConfig(boolean z) {
        this.lastMapconfig = null;
        if (z) {
            MapConfig mapConfig = new MapConfig(false);
            this.lastMapconfig = mapConfig;
            mapConfig.setGridXY(0, 0);
            this.lastMapconfig.setSX(0.0d);
            this.lastMapconfig.setSY(0.0d);
            this.lastMapconfig.setSZ(0.0f);
            this.lastMapconfig.setSC(0.0f);
            this.lastMapconfig.setSR(0.0f);
        }
    }

    public int getChangedCounter() {
        return this.changedCounter.get();
    }

    public void resetChangedCounter() {
        this.changedCounter.set(0);
    }

    public void addChangedCounter() {
        this.changedCounter.incrementAndGet();
    }

    public boolean isMapStateChange() {
        MapConfig mapConfig = this.lastMapconfig;
        boolean z = false;
        if (mapConfig != null) {
            double sx = mapConfig.getSX();
            double sy = this.lastMapconfig.getSY();
            float sz = this.lastMapconfig.getSZ();
            float sc = this.lastMapconfig.getSC();
            float sr = this.lastMapconfig.getSR();
            double d = this.sX;
            boolean z2 = sx != d;
            this.isCenterChanged = z2;
            double d2 = this.sY;
            if (sy != d2) {
                z2 = true;
            }
            this.isCenterChanged = z2;
            float f = this.sZ;
            boolean z3 = sz != f;
            this.isZoomChanged = z3;
            if (z3) {
                float f2 = this.minZoomLevel;
                if (sz > f2 && f > f2) {
                    float f3 = this.maxZoomLevel;
                    if (sz < f3 && f < f3) {
                        this.isNeedUpdateZoomControllerState = false;
                    }
                }
                this.isNeedUpdateZoomControllerState = true;
            }
            boolean z4 = sc != this.sC;
            this.isTiltChanged = z4;
            boolean z5 = sr != this.sR;
            this.isBearingChanged = z5;
            boolean z6 = z2 || z3 || z4 || z5 || this.isNeedUpdateMapRectNextFrame;
            if (z6) {
                this.isNeedUpdateMapRectNextFrame = false;
                int i = 28 - ((int) f);
                setGridXY(((int) d) >> i, ((int) d2) >> i);
                changeRatio();
            }
            z = z6;
        }
        if (this.sC < 45.0f || this.skyHeight != 0.0f) {
            return z;
        }
        return true;
    }

    protected void setGridXY(int i, int i2) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setGridXY(this.gridX, this.gridY);
        }
        this.gridX = i;
        this.gridY = i2;
    }

    protected int getGridX() {
        return this.gridX;
    }

    protected int getGridY() {
        return this.gridY;
    }

    public double getChangeRatio() {
        return this.changeRatio;
    }

    public double getChangeGridRatio() {
        return this.changeGridRatio;
    }

    private void changeRatio() {
        double sx = this.lastMapconfig.getSX();
        double sy = this.lastMapconfig.getSY();
        float sz = this.lastMapconfig.getSZ();
        float sc = this.lastMapconfig.getSC();
        float sr = this.lastMapconfig.getSR();
        this.changeRatio = Math.abs(this.sX - sx) + Math.abs(this.sY - sy);
        this.changeRatio = this.changeRatio == 0.0d ? 1.0d : this.changeRatio * 2.0d;
        this.changeRatio = this.changeRatio * (sz == this.sZ ? 1.0d : Math.abs(sz - r11));
        float f = this.sC;
        float abs = sc == f ? 1.0f : Math.abs(sc - f);
        float f2 = this.sR;
        float abs2 = sr != f2 ? Math.abs(sr - f2) : 1.0f;
        double d = abs;
        this.changeRatio *= d;
        double d2 = abs2;
        this.changeRatio *= d2;
        this.changeGridRatio = Math.abs(this.lastMapconfig.getGridX() - this.gridX) + (this.lastMapconfig.getGridY() - this.gridY);
        this.changeGridRatio = this.changeGridRatio != 0.0d ? this.changeGridRatio * 2.0d : 1.0d;
        this.changeGridRatio *= d;
        this.changeGridRatio *= d2;
    }

    public String toString() {
        return " sX: " + this.sX + " sY: " + this.sY + " sZ: " + this.sZ + " sC: " + this.sC + " sR: " + this.sR + " skyHeight: " + this.skyHeight;
    }

    public boolean isZoomChanged() {
        return this.isZoomChanged;
    }

    public boolean isTiltChanged() {
        return this.isTiltChanged;
    }

    public boolean isBearingChanged() {
        return this.isBearingChanged;
    }

    public boolean isIndoorEnable() {
        return this.isIndoorEnable;
    }

    public void setIndoorEnable(boolean z) {
        this.isIndoorEnable = z;
    }

    public boolean isBuildingEnable() {
        return this.isBuildingEnable;
    }

    public void setBuildingEnable(boolean z) {
        this.isBuildingEnable = z;
    }

    public boolean isMapTextEnable() {
        return this.isMapTextEnable;
    }

    public void setMapTextEnable(boolean z) {
        this.isMapTextEnable = z;
    }

    public boolean isTrafficEnabled() {
        return this.isTrafficEnabled;
    }

    public void setTrafficEnabled(boolean z) {
        this.isTrafficEnabled = z;
    }

    public boolean isNeedUpdateZoomControllerState() {
        return this.isNeedUpdateZoomControllerState;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public double getSX() {
        return this.sX;
    }

    public void setSX(double d) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSX(this.sX);
        }
        this.sX = d;
        this.mapGeoCenter.x = d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public double getSY() {
        return this.sY;
    }

    public void setSY(double d) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSY(this.sY);
        }
        this.sY = d;
        this.mapGeoCenter.x = d;
    }

    public DPoint getMapGeoCenter() {
        return this.mapGeoCenter;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public float getSZ() {
        return this.sZ;
    }

    public void setSZ(float f) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSZ(this.sZ);
        }
        this.sZ = f;
    }

    public float getSC() {
        return this.sC;
    }

    public void setSC(float f) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSC(this.sC);
        }
        this.sC = f;
    }

    public float getSR() {
        return this.sR;
    }

    public void setSR(float f) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSR(this.sR);
        }
        this.sR = f;
    }

    public Rectangle getGeoRectangle() {
        return this.geoRectangle;
    }

    public void setMaxZoomLevel(float f) {
        if (f > 20.0f) {
            f = 20.0f;
        }
        if (f < 3.0f) {
            f = 3.0f;
        }
        if (f < getMinZoomLevel()) {
            f = getMinZoomLevel();
        }
        this.isSetLimitZoomLevel = true;
        this.maxZoomLevel = f;
    }

    public void setMinZoomLevel(float f) {
        if (f < 3.0f) {
            f = 3.0f;
        }
        if (f > 20.0f) {
            f = 20.0f;
        }
        if (f > getMaxZoomLevel()) {
            f = getMaxZoomLevel();
        }
        this.isSetLimitZoomLevel = true;
        this.minZoomLevel = f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public float getMaxZoomLevel() {
        return this.maxZoomLevel;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public float getMinZoomLevel() {
        return this.minZoomLevel;
    }

    public IPoint[] getLimitIPoints() {
        return this.limitIPoints;
    }

    public void setLimitIPoints(IPoint[] iPointArr) {
        this.limitIPoints = iPointArr;
    }

    public boolean isSetLimitZoomLevel() {
        return this.isSetLimitZoomLevel;
    }

    public LatLngBounds getLimitLatLngBounds() {
        return this.limitLatLngBounds;
    }

    public void setLimitLatLngBounds(LatLngBounds latLngBounds) {
        this.limitLatLngBounds = latLngBounds;
        if (latLngBounds == null) {
            resetMinMaxZoomPreference();
        }
    }

    public void resetMinMaxZoomPreference() {
        this.minZoomLevel = 3.0f;
        this.maxZoomLevel = 20.0f;
        this.isSetLimitZoomLevel = false;
    }

    public void updateMapRectNextFrame(boolean z) {
        this.isNeedUpdateMapRectNextFrame = z;
    }

    public void setCustomStylePath(String str) {
        this.mCustomStylePath = str;
    }

    public String getCustomStylePath() {
        return this.mCustomStylePath;
    }

    public String getCustomStyleID() {
        return this.mCustomStyleID;
    }

    public void setCustomStyleID(String str) {
        this.mCustomStyleID = str;
    }

    public void setCustomStyleEnable(boolean z) {
        this.isCustomStyleEnabled = z;
    }

    public boolean isCustomStyleEnable() {
        return this.isCustomStyleEnabled;
    }

    public int getMapStyleTime() {
        return this.mMapStyleTime;
    }

    public void setMapStyleTime(int i) {
        this.mMapStyleTime = i;
    }

    public int getMapStyleMode() {
        return this.mMapStyleMode;
    }

    public void setMapStyleMode(int i) {
        this.mMapStyleMode = i;
    }

    public int getMapStyleState() {
        return this.mMapStyleState;
    }

    public void setMapStyleState(int i) {
        this.mMapStyleState = i;
    }

    public void setCustomTextureResourcePath(String str) {
        this.customTextureResourcePath = str;
    }

    public String getCustomTextureResourcePath() {
        return this.customTextureResourcePath;
    }

    public boolean isProFunctionAuthEnable() {
        return this.isProFunctionAuthEnable;
    }

    public void setProFunctionAuthEnable(boolean z) {
        this.isProFunctionAuthEnable = z;
    }

    public boolean isUseProFunction() {
        return this.isUseProFunction;
    }

    public void setUseProFunction(boolean z) {
        this.isUseProFunction = z;
    }

    public void setCustomBackgroundColor(int i) {
        this.customBackgroundColor = i;
    }

    public int getCustomBackgroundColor() {
        return this.customBackgroundColor;
    }

    public void setMapZoomScale(float f) {
        this.mapZoomScale = f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public float getMapZoomScale() {
        return this.mapZoomScale;
    }

    public void setMapWidth(int i) {
        this.mapWidth = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getMapWidth() {
        return this.mapWidth;
    }

    public void setMapHeight(int i) {
        this.mapHeight = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getMapHeight() {
        return this.mapHeight;
    }

    public void setMapLanguage(String str) {
        this.mMapLanguage = str;
    }

    public String getMapLanguage() {
        return this.mMapLanguage;
    }

    public void setHideLogoEnble(boolean z) {
        this.isHideLogoEnable = z;
    }

    public boolean isHideLogoEnable() {
        return this.isHideLogoEnable;
    }

    public void setWorldMapEnable(boolean z) {
        this.isWorldMapEnable = z;
    }

    public boolean isWorldMapEnable() {
        return this.isWorldMapEnable;
    }

    public float getSkyHeight() {
        return this.skyHeight;
    }

    public void setSkyHeight(float f) {
        this.skyHeight = f;
    }

    public float[] getViewMatrix() {
        return this.viewMatrix;
    }

    public float[] getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public float[] getMvpMatrix() {
        return this.mvpMatrix;
    }

    public void updateFinalMatrix() {
        Matrix.multiplyMM(this.mvpMatrix, 0, this.projectionMatrix, 0, this.viewMatrix, 0);
    }

    public boolean isTouchPoiEnable() {
        return this.isTouchPoiEnable;
    }

    public void setTouchPoiEnable(boolean z) {
        this.isTouchPoiEnable = z;
    }

    public boolean isMapEnable() {
        return this.mapEnable;
    }

    public void setMapEnable(boolean z) {
        this.mapEnable = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getAbroadState() {
        return this.abroadState;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public void setAbroadState(int i) {
        this.abroadState = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public boolean isAbroadEnable() {
        return this.isAbroadEnable;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public void setAbroadEnable(boolean z) {
        this.isAbroadEnable = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public boolean isTerrainEnable() {
        return this.isTerrainEnable;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public void setTerrainEnable(boolean z) {
        this.isTerrainEnable = z;
    }
}
