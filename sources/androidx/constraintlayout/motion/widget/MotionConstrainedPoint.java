package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* loaded from: classes8.dex */
class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    static final int PERPENDICULAR = 1;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    private float height;
    private Easing mKeyFrameEasing;
    private float position;
    int visibility;
    private float width;
    private float x;
    private float y;
    private float alpha = 1.0f;
    int mVisibilityMode = 0;
    private boolean applyElevation = false;
    private float elevation = 0.0f;
    private float rotation = 0.0f;
    private float rotationX = 0.0f;
    public float rotationY = 0.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float translationX = 0.0f;
    private float translationY = 0.0f;
    private float translationZ = 0.0f;
    private int mDrawPath = 0;
    private float mPathRotate = Float.NaN;
    private float mProgress = Float.NaN;
    private int mAnimateRelativeTo = -1;
    LinkedHashMap<String, ConstraintAttribute> attributes = new LinkedHashMap<>();
    int mMode = 0;
    double[] mTempValue = new double[18];
    double[] mTempDelta = new double[18];

    private boolean diff(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    void different(MotionConstrainedPoint motionConstrainedPoint, HashSet<String> hashSet) {
        if (diff(this.alpha, motionConstrainedPoint.alpha)) {
            hashSet.add("alpha");
        }
        if (diff(this.elevation, motionConstrainedPoint.elevation)) {
            hashSet.add("elevation");
        }
        int i = this.visibility;
        int i2 = motionConstrainedPoint.visibility;
        if (i != i2 && this.mVisibilityMode == 0 && (i == 0 || i2 == 0)) {
            hashSet.add("alpha");
        }
        if (diff(this.rotation, motionConstrainedPoint.rotation)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint.mPathRotate)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mProgress) || !Float.isNaN(motionConstrainedPoint.mProgress)) {
            hashSet.add("progress");
        }
        if (diff(this.rotationX, motionConstrainedPoint.rotationX)) {
            hashSet.add("rotationX");
        }
        if (diff(this.rotationY, motionConstrainedPoint.rotationY)) {
            hashSet.add("rotationY");
        }
        if (diff(this.mPivotX, motionConstrainedPoint.mPivotX)) {
            hashSet.add(Key.PIVOT_X);
        }
        if (diff(this.mPivotY, motionConstrainedPoint.mPivotY)) {
            hashSet.add(Key.PIVOT_Y);
        }
        if (diff(this.scaleX, motionConstrainedPoint.scaleX)) {
            hashSet.add("scaleX");
        }
        if (diff(this.scaleY, motionConstrainedPoint.scaleY)) {
            hashSet.add("scaleY");
        }
        if (diff(this.translationX, motionConstrainedPoint.translationX)) {
            hashSet.add("translationX");
        }
        if (diff(this.translationY, motionConstrainedPoint.translationY)) {
            hashSet.add("translationY");
        }
        if (diff(this.translationZ, motionConstrainedPoint.translationZ)) {
            hashSet.add("translationZ");
        }
    }

    void different(MotionConstrainedPoint motionConstrainedPoint, boolean[] zArr, String[] strArr) {
        zArr[0] = zArr[0] | diff(this.position, motionConstrainedPoint.position);
        zArr[1] = zArr[1] | diff(this.x, motionConstrainedPoint.x);
        zArr[2] = zArr[2] | diff(this.y, motionConstrainedPoint.y);
        zArr[3] = zArr[3] | diff(this.width, motionConstrainedPoint.width);
        zArr[4] = diff(this.height, motionConstrainedPoint.height) | zArr[4];
    }

    void fillStandard(double[] dArr, int[] iArr) {
        int i = 0;
        float[] fArr = {this.position, this.x, this.y, this.width, this.height, this.alpha, this.elevation, this.rotation, this.rotationX, this.rotationY, this.scaleX, this.scaleY, this.mPivotX, this.mPivotY, this.translationX, this.translationY, this.translationZ, this.mPathRotate};
        for (int i2 : iArr) {
            if (i2 < 18) {
                dArr[i] = fArr[r4];
                i++;
            }
        }
    }

    boolean hasCustomData(String str) {
        return this.attributes.containsKey(str);
    }

    int getCustomDataCount(String str) {
        return this.attributes.get(str).numberOfInterpolatedValues();
    }

    int getCustomData(String str, double[] dArr, int i) {
        ConstraintAttribute constraintAttribute = this.attributes.get(str);
        if (constraintAttribute.numberOfInterpolatedValues() == 1) {
            dArr[i] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int numberOfInterpolatedValues = constraintAttribute.numberOfInterpolatedValues();
        constraintAttribute.getValuesToInterpolate(new float[numberOfInterpolatedValues]);
        int i2 = 0;
        while (i2 < numberOfInterpolatedValues) {
            dArr[i] = r2[i2];
            i2++;
            i++;
        }
        return numberOfInterpolatedValues;
    }

    void setBounds(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.position, motionConstrainedPoint.position);
    }

    public void applyParameters(View view) {
        this.visibility = view.getVisibility();
        this.alpha = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.applyElevation = false;
        this.elevation = view.getElevation();
        this.rotation = view.getRotation();
        this.rotationX = view.getRotationX();
        this.rotationY = view.getRotationY();
        this.scaleX = view.getScaleX();
        this.scaleY = view.getScaleY();
        this.mPivotX = view.getPivotX();
        this.mPivotY = view.getPivotY();
        this.translationX = view.getTranslationX();
        this.translationY = view.getTranslationY();
        this.translationZ = view.getTranslationZ();
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        this.mVisibilityMode = constraint.propertySet.mVisibilityMode;
        this.visibility = constraint.propertySet.visibility;
        this.alpha = (constraint.propertySet.visibility == 0 || this.mVisibilityMode != 0) ? constraint.propertySet.alpha : 0.0f;
        this.applyElevation = constraint.transform.applyElevation;
        this.elevation = constraint.transform.elevation;
        this.rotation = constraint.transform.rotation;
        this.rotationX = constraint.transform.rotationX;
        this.rotationY = constraint.transform.rotationY;
        this.scaleX = constraint.transform.scaleX;
        this.scaleY = constraint.transform.scaleY;
        this.mPivotX = constraint.transform.transformPivotX;
        this.mPivotY = constraint.transform.transformPivotY;
        this.translationX = constraint.transform.translationX;
        this.translationY = constraint.transform.translationY;
        this.translationZ = constraint.transform.translationZ;
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        this.mPathRotate = constraint.motion.mPathRotate;
        this.mDrawPath = constraint.motion.mDrawPath;
        this.mAnimateRelativeTo = constraint.motion.mAnimateRelativeTo;
        this.mProgress = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute.isContinuous()) {
                this.attributes.put(str, constraintAttribute);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void addValues(HashMap<String, ViewSpline> hashMap, int i) {
        char c;
        for (String str : hashMap.keySet()) {
            ViewSpline viewSpline = hashMap.get(str);
            str.hashCode();
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -760884510:
                    if (str.equals(Key.PIVOT_X)) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case -760884509:
                    if (str.equals(Key.PIVOT_Y)) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case -40300674:
                    if (str.equals(Key.ROTATION)) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case -4379043:
                    if (str.equals("elevation")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        c = '\r';
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
                    viewSpline.setPoint(i, Float.isNaN(this.rotationX) ? 0.0f : this.rotationX);
                    break;
                case 1:
                    viewSpline.setPoint(i, Float.isNaN(this.rotationY) ? 0.0f : this.rotationY);
                    break;
                case 2:
                    viewSpline.setPoint(i, Float.isNaN(this.translationX) ? 0.0f : this.translationX);
                    break;
                case 3:
                    viewSpline.setPoint(i, Float.isNaN(this.translationY) ? 0.0f : this.translationY);
                    break;
                case 4:
                    viewSpline.setPoint(i, Float.isNaN(this.translationZ) ? 0.0f : this.translationZ);
                    break;
                case 5:
                    viewSpline.setPoint(i, Float.isNaN(this.mProgress) ? 0.0f : this.mProgress);
                    break;
                case 6:
                    viewSpline.setPoint(i, Float.isNaN(this.scaleX) ? 1.0f : this.scaleX);
                    break;
                case 7:
                    viewSpline.setPoint(i, Float.isNaN(this.scaleY) ? 1.0f : this.scaleY);
                    break;
                case '\b':
                    viewSpline.setPoint(i, Float.isNaN(this.mPivotX) ? 0.0f : this.mPivotX);
                    break;
                case '\t':
                    viewSpline.setPoint(i, Float.isNaN(this.mPivotY) ? 0.0f : this.mPivotY);
                    break;
                case '\n':
                    viewSpline.setPoint(i, Float.isNaN(this.rotation) ? 0.0f : this.rotation);
                    break;
                case 11:
                    viewSpline.setPoint(i, Float.isNaN(this.elevation) ? 0.0f : this.elevation);
                    break;
                case '\f':
                    viewSpline.setPoint(i, Float.isNaN(this.mPathRotate) ? 0.0f : this.mPathRotate);
                    break;
                case '\r':
                    viewSpline.setPoint(i, Float.isNaN(this.alpha) ? 1.0f : this.alpha);
                    break;
                default:
                    if (!str.startsWith("CUSTOM")) {
                        Log.e("MotionPaths", "UNKNOWN spline " + str);
                        break;
                    } else {
                        String str2 = str.split(",")[1];
                        if (this.attributes.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.attributes.get(str2);
                            if (viewSpline instanceof ViewSpline.CustomSet) {
                                ((ViewSpline.CustomSet) viewSpline).setPoint(i, constraintAttribute);
                                break;
                            } else {
                                Log.e("MotionPaths", str + " ViewSpline not a CustomSet frame = " + i + ", value" + constraintAttribute.getValueToInterpolate() + viewSpline);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
            }
        }
    }

    public void setState(View view) {
        setBounds(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        applyParameters(view);
    }

    public void setState(Rect rect, View view, int i, float f) {
        setBounds(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(view);
        this.mPivotX = Float.NaN;
        this.mPivotY = Float.NaN;
        if (i == 1) {
            this.rotation = f - 90.0f;
        } else {
            if (i != 2) {
                return;
            }
            this.rotation = f + 90.0f;
        }
    }

    public void setState(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        setBounds(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(constraintSet.getParameters(i2));
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return;
                    }
                }
            }
            float f = this.rotation + 90.0f;
            this.rotation = f;
            if (f > 180.0f) {
                this.rotation = f - 360.0f;
                return;
            }
            return;
        }
        this.rotation -= 90.0f;
    }
}
