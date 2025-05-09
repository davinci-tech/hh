package androidx.constraintlayout.motion.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes8.dex */
public class KeyAttributes extends Key {
    private static final boolean DEBUG = false;
    public static final int KEY_TYPE = 1;
    static final String NAME = "KeyAttribute";
    private static final String TAG = "KeyAttributes";
    private String mTransitionEasing;
    private int mCurveFit = -1;
    private boolean mVisibility = false;
    private float mAlpha = Float.NaN;
    private float mElevation = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;
    private float mProgress = Float.NaN;

    public KeyAttributes() {
        this.mType = 1;
        this.mCustomConstraints = new HashMap<>();
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, new int[]{R.attr.alpha, R.attr.transformPivotX, R.attr.transformPivotY, R.attr.translationX, R.attr.translationY, R.attr.scaleX, R.attr.scaleY, R.attr.rotation, R.attr.rotationX, R.attr.rotationY, R.attr.translationZ, R.attr.elevation, com.huawei.health.R.attr._2131099971_res_0x7f060143, com.huawei.health.R.attr._2131100124_res_0x7f0601dc, com.huawei.health.R.attr._2131100879_res_0x7f0604cf, com.huawei.health.R.attr._2131100881_res_0x7f0604d1, com.huawei.health.R.attr._2131101319_res_0x7f060687, com.huawei.health.R.attr._2131101321_res_0x7f060689, com.huawei.health.R.attr._2131101323_res_0x7f06068b}));
    }

    int getCurveFit() {
        return this.mCurveFit;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mPivotX)) {
            hashSet.add(Key.PIVOT_X);
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashSet.add(Key.PIVOT_Y);
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            hashSet.add("progress");
        }
        if (this.mCustomConstraints.size() > 0) {
            Iterator<String> it = this.mCustomConstraints.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (this.mCurveFit == -1) {
            return;
        }
        if (!Float.isNaN(this.mAlpha)) {
            hashMap.put("alpha", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mElevation)) {
            hashMap.put("elevation", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotation)) {
            hashMap.put(Key.ROTATION, Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashMap.put("rotationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashMap.put("rotationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotX)) {
            hashMap.put(Key.PIVOT_X, Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashMap.put(Key.PIVOT_Y, Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashMap.put("translationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashMap.put("translationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashMap.put("translationZ", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashMap.put("transitionPathRotate", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashMap.put("scaleX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashMap.put("scaleY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mProgress)) {
            hashMap.put("progress", Integer.valueOf(this.mCurveFit));
        }
        if (this.mCustomConstraints.size() > 0) {
            Iterator<String> it = this.mCustomConstraints.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put("CUSTOM," + it.next(), Integer.valueOf(this.mCurveFit));
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x00a2, code lost:
    
        if (r1.equals("scaleY") == false) goto L71;
     */
    @Override // androidx.constraintlayout.motion.widget.Key
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r6) {
        /*
            Method dump skipped, instructions count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyAttributes.addValues(java.util.HashMap):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1913008125:
                if (str.equals(Key.MOTIONPROGRESS)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1249320806:
                if (str.equals("rotationX")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -760884510:
                if (str.equals(Key.PIVOT_X)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -760884509:
                if (str.equals(Key.PIVOT_Y)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -40300674:
                if (str.equals(Key.ROTATION)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 579057826:
                if (str.equals("curveFit")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1941332754:
                if (str.equals("visibility")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.mProgress = toFloat(obj);
                break;
            case 1:
                this.mTransitionEasing = obj.toString();
                break;
            case 2:
                this.mRotationX = toFloat(obj);
                break;
            case 3:
                this.mRotationY = toFloat(obj);
                break;
            case 4:
                this.mTranslationX = toFloat(obj);
                break;
            case 5:
                this.mTranslationY = toFloat(obj);
                break;
            case 6:
                this.mTranslationZ = toFloat(obj);
                break;
            case 7:
                this.mScaleX = toFloat(obj);
                break;
            case '\b':
                this.mScaleY = toFloat(obj);
                break;
            case '\t':
                this.mPivotX = toFloat(obj);
                break;
            case '\n':
                this.mPivotY = toFloat(obj);
                break;
            case 11:
                this.mRotation = toFloat(obj);
                break;
            case '\f':
                this.mElevation = toFloat(obj);
                break;
            case '\r':
                this.mTransitionPathRotate = toFloat(obj);
                break;
            case 14:
                this.mAlpha = toFloat(obj);
                break;
            case 15:
                this.mCurveFit = toInt(obj);
                break;
            case 16:
                this.mVisibility = toBoolean(obj);
                break;
        }
    }

    static class Loader {
        private static final int ANDROID_ALPHA = 1;
        private static final int ANDROID_ELEVATION = 2;
        private static final int ANDROID_PIVOT_X = 19;
        private static final int ANDROID_PIVOT_Y = 20;
        private static final int ANDROID_ROTATION = 4;
        private static final int ANDROID_ROTATION_X = 5;
        private static final int ANDROID_ROTATION_Y = 6;
        private static final int ANDROID_SCALE_X = 7;
        private static final int ANDROID_SCALE_Y = 14;
        private static final int ANDROID_TRANSLATION_X = 15;
        private static final int ANDROID_TRANSLATION_Y = 16;
        private static final int ANDROID_TRANSLATION_Z = 17;
        private static final int CURVE_FIT = 13;
        private static final int FRAME_POSITION = 12;
        private static final int PROGRESS = 18;
        private static final int TARGET_ID = 10;
        private static final int TRANSITION_EASING = 9;
        private static final int TRANSITION_PATH_ROTATE = 8;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(0, 1);
            mAttrMap.append(11, 2);
            mAttrMap.append(7, 4);
            mAttrMap.append(8, 5);
            mAttrMap.append(9, 6);
            mAttrMap.append(1, 19);
            mAttrMap.append(2, 20);
            mAttrMap.append(5, 7);
            mAttrMap.append(18, 8);
            mAttrMap.append(17, 9);
            mAttrMap.append(15, 10);
            mAttrMap.append(13, 12);
            mAttrMap.append(12, 13);
            mAttrMap.append(6, 14);
            mAttrMap.append(3, 15);
            mAttrMap.append(4, 16);
            mAttrMap.append(10, 17);
            mAttrMap.append(14, 18);
        }

        public static void read(KeyAttributes keyAttributes, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        keyAttributes.mAlpha = typedArray.getFloat(index, keyAttributes.mAlpha);
                        break;
                    case 2:
                        keyAttributes.mElevation = typedArray.getDimension(index, keyAttributes.mElevation);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e(KeyAttributes.NAME, "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        break;
                    case 4:
                        keyAttributes.mRotation = typedArray.getFloat(index, keyAttributes.mRotation);
                        break;
                    case 5:
                        keyAttributes.mRotationX = typedArray.getFloat(index, keyAttributes.mRotationX);
                        break;
                    case 6:
                        keyAttributes.mRotationY = typedArray.getFloat(index, keyAttributes.mRotationY);
                        break;
                    case 7:
                        keyAttributes.mScaleX = typedArray.getFloat(index, keyAttributes.mScaleX);
                        break;
                    case 8:
                        keyAttributes.mTransitionPathRotate = typedArray.getFloat(index, keyAttributes.mTransitionPathRotate);
                        break;
                    case 9:
                        keyAttributes.mTransitionEasing = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            keyAttributes.mTargetId = typedArray.getResourceId(index, keyAttributes.mTargetId);
                            if (keyAttributes.mTargetId == -1) {
                                keyAttributes.mTargetString = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyAttributes.mTargetString = typedArray.getString(index);
                            break;
                        } else {
                            keyAttributes.mTargetId = typedArray.getResourceId(index, keyAttributes.mTargetId);
                            break;
                        }
                    case 12:
                        keyAttributes.mFramePosition = typedArray.getInt(index, keyAttributes.mFramePosition);
                        break;
                    case 13:
                        keyAttributes.mCurveFit = typedArray.getInteger(index, keyAttributes.mCurveFit);
                        break;
                    case 14:
                        keyAttributes.mScaleY = typedArray.getFloat(index, keyAttributes.mScaleY);
                        break;
                    case 15:
                        keyAttributes.mTranslationX = typedArray.getDimension(index, keyAttributes.mTranslationX);
                        break;
                    case 16:
                        keyAttributes.mTranslationY = typedArray.getDimension(index, keyAttributes.mTranslationY);
                        break;
                    case 17:
                        keyAttributes.mTranslationZ = typedArray.getDimension(index, keyAttributes.mTranslationZ);
                        break;
                    case 18:
                        keyAttributes.mProgress = typedArray.getFloat(index, keyAttributes.mProgress);
                        break;
                    case 19:
                        keyAttributes.mPivotX = typedArray.getDimension(index, keyAttributes.mPivotX);
                        break;
                    case 20:
                        keyAttributes.mPivotY = typedArray.getDimension(index, keyAttributes.mPivotY);
                        break;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key) {
        super.copy(key);
        KeyAttributes keyAttributes = (KeyAttributes) key;
        this.mCurveFit = keyAttributes.mCurveFit;
        this.mVisibility = keyAttributes.mVisibility;
        this.mAlpha = keyAttributes.mAlpha;
        this.mElevation = keyAttributes.mElevation;
        this.mRotation = keyAttributes.mRotation;
        this.mRotationX = keyAttributes.mRotationX;
        this.mRotationY = keyAttributes.mRotationY;
        this.mPivotX = keyAttributes.mPivotX;
        this.mPivotY = keyAttributes.mPivotY;
        this.mTransitionPathRotate = keyAttributes.mTransitionPathRotate;
        this.mScaleX = keyAttributes.mScaleX;
        this.mScaleY = keyAttributes.mScaleY;
        this.mTranslationX = keyAttributes.mTranslationX;
        this.mTranslationY = keyAttributes.mTranslationY;
        this.mTranslationZ = keyAttributes.mTranslationZ;
        this.mProgress = keyAttributes.mProgress;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone */
    public Key mo10clone() {
        return new KeyAttributes().copy(this);
    }
}
