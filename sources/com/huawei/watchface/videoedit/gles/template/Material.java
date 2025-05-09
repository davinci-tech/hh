package com.huawei.watchface.videoedit.gles.template;

import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.gles.CurvedAnimation;
import com.huawei.watchface.videoedit.gles.glutils.TimeRange;
import java.util.Objects;

/* loaded from: classes9.dex */
public class Material {
    public static final int GAUSS_BACKGROUND = 1;
    private static final int RENDER_INDEX = -1;
    private static final int RIGHT_ANGLE = 90;
    private CurvedAnimation[] mCurvedAnimations;
    private String mId;
    private String mMaterialId;
    private String mMaterialType;
    private String mMeshChangerType;
    private TimeRange mTimeRange;
    private int mWidth = 0;
    private int mHeight = 0;
    private int mRotation = 0;
    private int mAnimType = 0;
    private int mRenderIndex = 1;
    private int mBackgroundType = 0;

    public Material(String str, String str2, TimeRange timeRange) {
        this.mId = str;
        this.mMaterialId = str2;
        this.mTimeRange = timeRange;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Material)) {
            return false;
        }
        Material material = (Material) obj;
        return this.mWidth == material.mWidth && this.mHeight == material.mHeight && this.mRotation == material.mRotation && Objects.equals(this.mMaterialId, material.mMaterialId) && Objects.equals(this.mId, material.mId) && Objects.equals(this.mMaterialType, material.mMaterialType) && Objects.equals(Integer.valueOf(this.mBackgroundType), Integer.valueOf(material.mBackgroundType)) && Objects.equals(this.mMeshChangerType, material.mMeshChangerType) && Objects.equals(this.mTimeRange, material.getTimeRange());
    }

    public int hashCode() {
        return Objects.hash(this.mMaterialId, this.mId, this.mMaterialType, Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), Integer.valueOf(this.mRotation), this.mTimeRange, Integer.valueOf(this.mBackgroundType), this.mMeshChangerType);
    }

    public Material setMeshChangerType(String str) {
        this.mMeshChangerType = str;
        return this;
    }

    public String getMeshChangerType() {
        return this.mMeshChangerType;
    }

    public Material setAnimType(int i) {
        this.mAnimType = i;
        return this;
    }

    public int getAnimType() {
        return this.mAnimType;
    }

    public boolean isVideo() {
        return "video".equals(this.mMaterialType) || "video_musk".equals(this.mMaterialType);
    }

    public Material setMaterialType(String str) {
        this.mMaterialType = str;
        return this;
    }

    public String getMaterialId() {
        return this.mMaterialId;
    }

    public String getId() {
        return this.mId;
    }

    public int getRotation() {
        return this.mRotation;
    }

    public Material setRotation(int i) {
        this.mRotation = i;
        return this;
    }

    public Material setWidth(int i) {
        this.mWidth = i;
        return this;
    }

    public Material setHeight(int i) {
        this.mHeight = i;
        return this;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public Material setBackgroundType(int i) {
        this.mBackgroundType = i;
        return this;
    }

    public int getBackgroundType() {
        return this.mBackgroundType;
    }

    public Material setCurvedAnimation(CurvedAnimation curvedAnimation) {
        this.mCurvedAnimations = new CurvedAnimation[]{curvedAnimation};
        return this;
    }

    public Material setVpMatrix(float[] fArr) {
        CurvedAnimation[] curvedAnimationArr = this.mCurvedAnimations;
        if (curvedAnimationArr == null) {
            return this;
        }
        for (CurvedAnimation curvedAnimation : curvedAnimationArr) {
            curvedAnimation.setVpMatrix(fArr);
        }
        return this;
    }

    public boolean contains(long j) {
        return this.mTimeRange.contains(j);
    }

    public void draw(long j, int i, int i2) {
        CurvedAnimation[] curvedAnimationArr = this.mCurvedAnimations;
        if (curvedAnimationArr == null) {
            return;
        }
        for (CurvedAnimation curvedAnimation : curvedAnimationArr) {
            if (curvedAnimation.contains(j)) {
                curvedAnimation.draw(j, i, i2);
                return;
            }
        }
    }

    public void draw(long j, int i) {
        CurvedAnimation[] curvedAnimationArr = this.mCurvedAnimations;
        if (curvedAnimationArr == null) {
            return;
        }
        for (CurvedAnimation curvedAnimation : curvedAnimationArr) {
            if (curvedAnimation.contains(j)) {
                curvedAnimation.draw(j, i);
                return;
            }
        }
    }

    public TimeRange getTimeRange() {
        return this.mTimeRange;
    }

    public Material updateAnimVertex(float f) {
        if (this.mCurvedAnimations != null) {
            int i = this.mWidth;
            int i2 = this.mHeight;
            if (i * i2 != 0) {
                float f2 = i / i2;
                if ((this.mRotation / 90) % 2 != 0) {
                    f2 = 1.0f / f2;
                    HwLog.d(HwLog.TAG, "updateAnimVertex, change ratio.");
                }
                getRenderIndex();
                for (CurvedAnimation curvedAnimation : this.mCurvedAnimations) {
                    curvedAnimation.updateAnimVertex(f2, f, true);
                }
            }
        }
        return this;
    }

    public void release() {
        CurvedAnimation[] curvedAnimationArr = this.mCurvedAnimations;
        if (curvedAnimationArr == null) {
            return;
        }
        for (CurvedAnimation curvedAnimation : curvedAnimationArr) {
            curvedAnimation.clean();
        }
        this.mCurvedAnimations = null;
    }

    public String toString() {
        return "id: " + this.mId + System.lineSeparator() + "type: " + this.mMaterialType + System.lineSeparator() + "materailId: " + this.mMaterialId + System.lineSeparator() + "timeRange: " + this.mTimeRange.toString() + "width: " + this.mWidth + System.lineSeparator() + "height: " + this.mHeight + System.lineSeparator() + "rotation: " + this.mRotation + System.lineSeparator() + "renderIndex: " + this.mRenderIndex + System.lineSeparator() + "backgroundType: " + this.mBackgroundType + System.lineSeparator() + "meshChangerType: " + this.mMeshChangerType + System.lineSeparator();
    }

    public int getRenderIndex() {
        return this.mRenderIndex;
    }

    public void setRenderIndex(int i) {
        this.mRenderIndex = i;
    }
}
