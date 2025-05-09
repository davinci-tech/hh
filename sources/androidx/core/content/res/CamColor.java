package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;

/* loaded from: classes2.dex */
class CamColor {
    private static final float CHROMA_SEARCH_ENDPOINT = 0.4f;
    private static final float DE_MAX = 1.0f;
    private static final float DL_MAX = 0.2f;
    private static final float LIGHTNESS_SEARCH_ENDPOINT = 0.01f;
    private final float mAstar;
    private final float mBstar;
    private final float mChroma;
    private final float mHue;
    private final float mJ;
    private final float mJstar;
    private final float mM;
    private final float mQ;
    private final float mS;

    float getHue() {
        return this.mHue;
    }

    float getChroma() {
        return this.mChroma;
    }

    float getJ() {
        return this.mJ;
    }

    float getQ() {
        return this.mQ;
    }

    float getM() {
        return this.mM;
    }

    float getS() {
        return this.mS;
    }

    float getJStar() {
        return this.mJstar;
    }

    float getAStar() {
        return this.mAstar;
    }

    float getBStar() {
        return this.mBstar;
    }

    CamColor(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.mHue = f;
        this.mChroma = f2;
        this.mJ = f3;
        this.mQ = f4;
        this.mM = f5;
        this.mS = f6;
        this.mJstar = f7;
        this.mAstar = f8;
        this.mBstar = f9;
    }

    static int toColor(float f, float f2, float f3) {
        return toColor(f, f2, f3, ViewingConditions.DEFAULT);
    }

    static CamColor fromColor(int i) {
        return fromColorInViewingConditions(i, ViewingConditions.DEFAULT);
    }

    static CamColor fromColorInViewingConditions(int i, ViewingConditions viewingConditions) {
        float[] xyzFromInt = CamUtils.xyzFromInt(i);
        float[][] fArr = CamUtils.XYZ_TO_CAM16RGB;
        float f = xyzFromInt[0];
        float[] fArr2 = fArr[0];
        float f2 = fArr2[0];
        float f3 = xyzFromInt[1];
        float f4 = fArr2[1];
        float f5 = xyzFromInt[2];
        float f6 = fArr2[2];
        float[] fArr3 = fArr[1];
        float f7 = fArr3[0];
        float f8 = fArr3[1];
        float f9 = fArr3[2];
        float[] fArr4 = fArr[2];
        float f10 = fArr4[0];
        float f11 = fArr4[1];
        float f12 = fArr4[2];
        float f13 = viewingConditions.getRgbD()[0] * ((f2 * f) + (f4 * f3) + (f6 * f5));
        float f14 = viewingConditions.getRgbD()[1] * ((f7 * f) + (f8 * f3) + (f9 * f5));
        float f15 = viewingConditions.getRgbD()[2] * ((f * f10) + (f3 * f11) + (f5 * f12));
        float pow = (float) Math.pow((viewingConditions.getFl() * Math.abs(f13)) / 100.0d, 0.42d);
        float pow2 = (float) Math.pow((viewingConditions.getFl() * Math.abs(f14)) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow((viewingConditions.getFl() * Math.abs(f15)) / 100.0d, 0.42d);
        float signum = ((Math.signum(f13) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f14) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f15) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d = signum3;
        float f16 = ((float) (((signum * 11.0d) + (signum2 * (-12.0d))) + d)) / 11.0f;
        float f17 = ((float) ((signum + signum2) - (d * 2.0d))) / 9.0f;
        float f18 = signum2 * 20.0f;
        float f19 = (((signum * 20.0f) + f18) + (21.0f * signum3)) / 20.0f;
        float f20 = (((signum * 40.0f) + f18) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2(f17, f16)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f21 = atan2;
        float f22 = (3.1415927f * f21) / 180.0f;
        float pow4 = ((float) Math.pow((f20 * viewingConditions.getNbb()) / viewingConditions.getAw(), viewingConditions.getC() * viewingConditions.getZ())) * 100.0f;
        float c = 4.0f / viewingConditions.getC();
        float sqrt = (float) Math.sqrt(pow4 / 100.0f);
        float aw = viewingConditions.getAw();
        float flRoot = viewingConditions.getFlRoot();
        float pow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos((((((double) f21) < 20.14d ? 360.0f + f21 : f21) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * viewingConditions.getNc()) * viewingConditions.getNcb()) * ((float) Math.sqrt((f16 * f16) + (f17 * f17)))) / (f19 + 0.305f), 0.9d)) * ((float) Math.sqrt(pow4 / 100.0d));
        float flRoot2 = pow5 * viewingConditions.getFlRoot();
        float sqrt2 = (float) Math.sqrt((r3 * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f));
        float f23 = (1.7f * pow4) / ((0.007f * pow4) + 1.0f);
        float log = ((float) Math.log((0.0228f * flRoot2) + 1.0f)) * 43.85965f;
        double d2 = f22;
        return new CamColor(f21, pow5, pow4, flRoot * c * sqrt * (aw + 4.0f), flRoot2, sqrt2 * 50.0f, f23, log * ((float) Math.cos(d2)), log * ((float) Math.sin(d2)));
    }

    private static CamColor fromJch(float f, float f2, float f3) {
        return fromJchInFrame(f, f2, f3, ViewingConditions.DEFAULT);
    }

    private static CamColor fromJchInFrame(float f, float f2, float f3, ViewingConditions viewingConditions) {
        float c = 4.0f / viewingConditions.getC();
        float sqrt = (float) Math.sqrt(f / 100.0d);
        float aw = viewingConditions.getAw();
        float flRoot = viewingConditions.getFlRoot();
        float flRoot2 = viewingConditions.getFlRoot() * f2;
        float sqrt2 = (float) Math.sqrt(((f2 / ((float) Math.sqrt(r4))) * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f));
        float f4 = (1.7f * f) / ((0.007f * f) + 1.0f);
        float log = ((float) Math.log((flRoot2 * 0.0228d) + 1.0d)) * 43.85965f;
        double d = (3.1415927f * f3) / 180.0f;
        return new CamColor(f3, f2, f, c * sqrt * (aw + 4.0f) * flRoot, flRoot2, sqrt2 * 50.0f, f4, log * ((float) Math.cos(d)), log * ((float) Math.sin(d)));
    }

    float distance(CamColor camColor) {
        float jStar = getJStar() - camColor.getJStar();
        float aStar = getAStar() - camColor.getAStar();
        float bStar = getBStar() - camColor.getBStar();
        return (float) (Math.pow(Math.sqrt((jStar * jStar) + (aStar * aStar) + (bStar * bStar)), 0.63d) * 1.41d);
    }

    int viewedInSrgb() {
        return viewed(ViewingConditions.DEFAULT);
    }

    int viewed(ViewingConditions viewingConditions) {
        float pow = (float) Math.pow(((((double) getChroma()) == 0.0d || ((double) getJ()) == 0.0d) ? 0.0f : getChroma() / ((float) Math.sqrt(getJ() / 100.0d))) / Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d), 1.1111111111111112d);
        double hue = (getHue() * 3.1415927f) / 180.0f;
        float cos = (float) (Math.cos(2.0d + hue) + 3.8d);
        float aw = viewingConditions.getAw();
        float pow2 = (float) Math.pow(getJ() / 100.0d, (1.0d / viewingConditions.getC()) / viewingConditions.getZ());
        float nc = viewingConditions.getNc();
        float ncb = viewingConditions.getNcb();
        float nbb = (aw * pow2) / viewingConditions.getNbb();
        float sin = (float) Math.sin(hue);
        float cos2 = (float) Math.cos(hue);
        float f = (((0.305f + nbb) * 23.0f) * pow) / (((((((cos * 0.25f) * 3846.1538f) * nc) * ncb) * 23.0f) + ((11.0f * pow) * cos2)) + ((pow * 108.0f) * sin));
        float f2 = cos2 * f;
        float f3 = f * sin;
        float f4 = nbb * 460.0f;
        float f5 = (((451.0f * f2) + f4) + (288.0f * f3)) / 1403.0f;
        float f6 = ((f4 - (891.0f * f2)) - (261.0f * f3)) / 1403.0f;
        float f7 = ((f4 - (f2 * 220.0f)) - (f3 * 6300.0f)) / 1403.0f;
        float max = (float) Math.max(0.0d, (Math.abs(f5) * 27.13d) / (400.0d - Math.abs(f5)));
        float signum = Math.signum(f5);
        float fl = 100.0f / viewingConditions.getFl();
        float pow3 = (float) Math.pow(max, 2.380952380952381d);
        float max2 = (float) Math.max(0.0d, (Math.abs(f6) * 27.13d) / (400.0d - Math.abs(f6)));
        float signum2 = Math.signum(f6);
        float fl2 = 100.0f / viewingConditions.getFl();
        float pow4 = (float) Math.pow(max2, 2.380952380952381d);
        float max3 = (float) Math.max(0.0d, (Math.abs(f7) * 27.13d) / (400.0d - Math.abs(f7)));
        float signum3 = Math.signum(f7);
        float fl3 = 100.0f / viewingConditions.getFl();
        float pow5 = (float) Math.pow(max3, 2.380952380952381d);
        float f8 = ((signum * fl) * pow3) / viewingConditions.getRgbD()[0];
        float f9 = ((signum2 * fl2) * pow4) / viewingConditions.getRgbD()[1];
        float f10 = ((signum3 * fl3) * pow5) / viewingConditions.getRgbD()[2];
        float[][] fArr = CamUtils.CAM16RGB_TO_XYZ;
        float[] fArr2 = fArr[0];
        float f11 = fArr2[0];
        float f12 = fArr2[1];
        float f13 = fArr2[2];
        float[] fArr3 = fArr[1];
        float f14 = fArr3[0];
        float f15 = fArr3[1];
        float f16 = fArr3[2];
        float[] fArr4 = fArr[2];
        return ColorUtils.XYZToColor((f11 * f8) + (f12 * f9) + (f13 * f10), (f14 * f8) + (f15 * f9) + (f16 * f10), (f8 * fArr4[0]) + (f9 * fArr4[1]) + (f10 * fArr4[2]));
    }

    static int toColor(float f, float f2, float f3, ViewingConditions viewingConditions) {
        if (f2 < 1.0d || Math.round(f3) <= 0.0d || Math.round(f3) >= 100.0d) {
            return CamUtils.intFromLStar(f3);
        }
        float min = f < 0.0f ? 0.0f : Math.min(360.0f, f);
        boolean z = true;
        CamColor camColor = null;
        float f4 = 0.0f;
        float f5 = f2;
        while (Math.abs(f4 - f2) >= CHROMA_SEARCH_ENDPOINT) {
            CamColor findCamByJ = findCamByJ(min, f5, f3);
            if (!z) {
                if (findCamByJ == null) {
                    f2 = f5;
                } else {
                    f4 = f5;
                    camColor = findCamByJ;
                }
                f5 = ((f2 - f4) / 2.0f) + f4;
            } else {
                if (findCamByJ != null) {
                    return findCamByJ.viewed(viewingConditions);
                }
                f5 = ((f2 - f4) / 2.0f) + f4;
                z = false;
            }
        }
        if (camColor == null) {
            return CamUtils.intFromLStar(f3);
        }
        return camColor.viewed(viewingConditions);
    }

    private static CamColor findCamByJ(float f, float f2, float f3) {
        float f4 = 100.0f;
        float f5 = 1000.0f;
        float f6 = 0.0f;
        CamColor camColor = null;
        float f7 = 1000.0f;
        while (Math.abs(f6 - f4) > 0.01f) {
            float f8 = ((f4 - f6) / 2.0f) + f6;
            int viewedInSrgb = fromJch(f8, f2, f).viewedInSrgb();
            float lStarFromInt = CamUtils.lStarFromInt(viewedInSrgb);
            float abs = Math.abs(f3 - lStarFromInt);
            if (abs < 0.2f) {
                CamColor fromColor = fromColor(viewedInSrgb);
                float distance = fromColor.distance(fromJch(fromColor.getJ(), fromColor.getChroma(), f));
                if (distance <= 1.0f) {
                    camColor = fromColor;
                    f5 = abs;
                    f7 = distance;
                }
            }
            if (f5 == 0.0f && f7 == 0.0f) {
                break;
            }
            if (lStarFromInt < f3) {
                f6 = f8;
            } else {
                f4 = f8;
            }
        }
        return camColor;
    }
}
