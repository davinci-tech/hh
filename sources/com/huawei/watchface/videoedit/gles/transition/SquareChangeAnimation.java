package com.huawei.watchface.videoedit.gles.transition;

import android.text.TextUtils;
import com.huawei.watchface.videoedit.gles.animations.MeshChanger;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class SquareChangeAnimation implements MeshChanger {
    public static final String BIG = "big";
    private static final float DELTA = 0.05f;
    public static final String DOWN = "down";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    private static final float SCALE_EXTRA = 1.5f;
    public static final String SMALL = "small";
    public static final String UP = "up";
    private float mDelta = 0.05f;
    private final String mType;

    public SquareChangeAnimation(String str) {
        this.mType = str;
    }

    public MeshChanger setDelta(float f) {
        this.mDelta = f;
        return this;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.watchface.videoedit.gles.animations.MeshChanger
    public void changeMesh(float[] fArr, float f, int i) {
        char c;
        if (TextUtils.isEmpty(this.mType)) {
            return;
        }
        String str = this.mType;
        str.hashCode();
        switch (str.hashCode()) {
            case 3739:
                if (str.equals(UP)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 97536:
                if (str.equals(BIG)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3089570:
                if (str.equals(DOWN)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3317767:
                if (str.equals(LEFT)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 108511772:
                if (str.equals(RIGHT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 109548807:
                if (str.equals(SMALL)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            up(fArr, f, this.mDelta);
        } else if (c == 1) {
            big(fArr, f, this.mDelta * SCALE_EXTRA);
        } else if (c == 2) {
            down(fArr, f, this.mDelta);
        } else if (c == 3) {
            left(fArr, f, this.mDelta);
        } else if (c == 4) {
            right(fArr, f, this.mDelta);
        } else if (c != 5) {
            return;
        } else {
            small(fArr, f, this.mDelta * SCALE_EXTRA);
        }
        Utils.vboBindData(i, fArr);
    }

    private void up(float[] fArr, float f, float f2) {
        float f3 = 2.0f * f2;
        setCoordinateUv(fArr, 1.0f - ((1.0f - f) * f3), f3 * f, f2, 1.0f - f2);
    }

    private void down(float[] fArr, float f, float f2) {
        float f3 = 2.0f * f2;
        setCoordinateUv(fArr, 1.0f - (f3 * f), f3 * (1.0f - f), f2, 1.0f - f2);
    }

    private void left(float[] fArr, float f, float f2) {
        float f3 = 2.0f * f2;
        setCoordinateUv(fArr, 1.0f - f2, f2, f3 * f, 1.0f - (f3 * (1.0f - f)));
    }

    private void right(float[] fArr, float f, float f2) {
        float f3 = 2.0f * f2;
        setCoordinateUv(fArr, 1.0f - f2, f2, f3 * (1.0f - f), 1.0f - (f3 * f));
    }

    private void big(float[] fArr, float f, float f2) {
        float f3 = f2 * f;
        float f4 = 1.0f - f3;
        setCoordinateUv(fArr, f4, f3, f3, f4);
    }

    private void small(float[] fArr, float f, float f2) {
        float f3 = f2 * (1.0f - f);
        float f4 = 1.0f - f3;
        setCoordinateUv(fArr, f4, f3, f3, f4);
    }

    private void setCoordinateUv(float[] fArr, float f, float f2, float f3, float f4) {
        fArr[11] = f;
        fArr[15] = f;
        fArr[3] = f2;
        fArr[7] = f2;
        fArr[2] = f3;
        fArr[10] = f3;
        fArr[6] = f4;
        fArr[14] = f4;
    }
}
