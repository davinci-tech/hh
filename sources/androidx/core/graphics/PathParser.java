package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PathParser {
    private static final String LOGTAG = "PathParser";

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        int min = Math.min(i3, length - i);
        float[] fArr2 = new float[i3];
        System.arraycopy(fArr, i, fArr2, 0, min);
        return fArr2;
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(createNodesFromPathData, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error in parsing " + str, e);
        }
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 1;
        while (i2 < str.length()) {
            int nextStart = nextStart(str, i2);
            String trim = str.substring(i, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i = nextStart;
            i2 = nextStart + 1;
        }
        if (i2 - i == 1 && i < str.length()) {
            addNode(arrayList, str.charAt(i), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr2[i] = new PathDataNode(pathDataNodeArr[i]);
        }
        return pathDataNodeArr2;
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            if (pathDataNodeArr[i].mType != pathDataNodeArr2[i].mType || pathDataNodeArr[i].mParams.length != pathDataNodeArr2[i].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i = 0; i < pathDataNodeArr2.length; i++) {
            pathDataNodeArr[i].mType = pathDataNodeArr2[i].mType;
            for (int i2 = 0; i2 < pathDataNodeArr2[i].mParams.length; i2++) {
                pathDataNodeArr[i].mParams[i2] = pathDataNodeArr2[i].mParams[i2];
            }
        }
    }

    private static int nextStart(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return i;
            }
            i++;
        }
        return i;
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c, float[] fArr) {
        arrayList.add(new PathDataNode(c, fArr));
    }

    static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i = 1;
            int i2 = 0;
            while (i < length) {
                extract(str, i, extractFloatResult);
                int i3 = extractFloatResult.mEndPosition;
                if (i < i3) {
                    fArr[i2] = Float.parseFloat(str.substring(i, i3));
                    i2++;
                }
                i = extractFloatResult.mEndWithNegOrDot ? i3 : i3 + 1;
            }
            return copyOfRange(fArr, 0, i2);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a A[LOOP:0: B:2:0x0007->B:14:0x003a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void extract(java.lang.String r8, int r9, androidx.core.graphics.PathParser.ExtractFloatResult r10) {
        /*
            r0 = 0
            r10.mEndWithNegOrDot = r0
            r1 = r9
            r2 = r0
            r3 = r2
            r4 = r3
        L7:
            int r5 = r8.length()
            if (r1 >= r5) goto L3d
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L35
            r6 = 69
            if (r5 == r6) goto L33
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L33
            switch(r5) {
                case 44: goto L35;
                case 45: goto L2a;
                case 46: goto L22;
                default: goto L21;
            }
        L21:
            goto L31
        L22:
            if (r2 != 0) goto L27
            r3 = r0
            r2 = r7
            goto L37
        L27:
            r10.mEndWithNegOrDot = r7
            goto L35
        L2a:
            if (r1 == r9) goto L31
            if (r3 != 0) goto L31
            r10.mEndWithNegOrDot = r7
            goto L35
        L31:
            r3 = r0
            goto L37
        L33:
            r3 = r7
            goto L37
        L35:
            r3 = r0
            r4 = r7
        L37:
            if (r4 == 0) goto L3a
            goto L3d
        L3a:
            int r1 = r1 + 1
            goto L7
        L3d:
            r10.mEndPosition = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.extract(java.lang.String, int, androidx.core.graphics.PathParser$ExtractFloatResult):void");
    }

    public static boolean interpolatePathDataNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2, PathDataNode[] pathDataNodeArr3, float f) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr3 == null) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes cannot be null");
        }
        if (pathDataNodeArr.length != pathDataNodeArr2.length || pathDataNodeArr2.length != pathDataNodeArr3.length) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes must have the same length");
        }
        if (!canMorph(pathDataNodeArr2, pathDataNodeArr3)) {
            return false;
        }
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr[i].interpolatePathDataNode(pathDataNodeArr2[i], pathDataNodeArr3[i], f);
        }
        return true;
    }

    public static class PathDataNode {
        public float[] mParams;
        public char mType;

        PathDataNode(char c, float[] fArr) {
            this.mType = c;
            this.mParams = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            float[] fArr = pathDataNode.mParams;
            this.mParams = PathParser.copyOfRange(fArr, 0, fArr.length);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c = 'm';
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                PathDataNode pathDataNode = pathDataNodeArr[i];
                addCommand(path, fArr, c, pathDataNode.mType, pathDataNode.mParams);
                c = pathDataNodeArr[i].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            this.mType = pathDataNode.mType;
            int i = 0;
            while (true) {
                float[] fArr = pathDataNode.mParams;
                if (i >= fArr.length) {
                    return;
                }
                this.mParams[i] = (fArr[i] * (1.0f - f)) + (pathDataNode2.mParams[i] * f);
                i++;
            }
        }

        private static void addCommand(Path path, float[] fArr, char c, char c2, float[] fArr2) {
            int i;
            int i2;
            int i3;
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            float f10;
            float f11;
            char c3 = c2;
            boolean z = false;
            float f12 = fArr[0];
            float f13 = fArr[1];
            float f14 = fArr[2];
            float f15 = fArr[3];
            float f16 = fArr[4];
            float f17 = fArr[5];
            switch (c3) {
                case 'A':
                case 'a':
                    i2 = 7;
                    i = i2;
                    break;
                case 'C':
                case 'c':
                    i2 = 6;
                    i = i2;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f16, f17);
                    f12 = f16;
                    f14 = f12;
                    f13 = f17;
                    f15 = f13;
                default:
                    i = 2;
                    break;
            }
            float f18 = f12;
            float f19 = f13;
            float f20 = f16;
            float f21 = f17;
            int i4 = 0;
            char c4 = c;
            while (i4 < fArr2.length) {
                if (c3 != 'A') {
                    if (c3 == 'C') {
                        i3 = i4;
                        int i5 = i3 + 2;
                        int i6 = i3 + 3;
                        int i7 = i3 + 4;
                        int i8 = i3 + 5;
                        path.cubicTo(fArr2[i3], fArr2[i3 + 1], fArr2[i5], fArr2[i6], fArr2[i7], fArr2[i8]);
                        f18 = fArr2[i7];
                        float f22 = fArr2[i8];
                        float f23 = fArr2[i5];
                        float f24 = fArr2[i6];
                        f19 = f22;
                        f15 = f24;
                        f14 = f23;
                    } else if (c3 == 'H') {
                        i3 = i4;
                        path.lineTo(fArr2[i3], f19);
                        f18 = fArr2[i3];
                    } else if (c3 == 'Q') {
                        i3 = i4;
                        int i9 = i3 + 1;
                        int i10 = i3 + 2;
                        int i11 = i3 + 3;
                        path.quadTo(fArr2[i3], fArr2[i9], fArr2[i10], fArr2[i11]);
                        float f25 = fArr2[i3];
                        float f26 = fArr2[i9];
                        f18 = fArr2[i10];
                        f19 = fArr2[i11];
                        f14 = f25;
                        f15 = f26;
                    } else if (c3 == 'V') {
                        i3 = i4;
                        path.lineTo(f18, fArr2[i3]);
                        f19 = fArr2[i3];
                    } else if (c3 != 'a') {
                        if (c3 != 'c') {
                            if (c3 == 'h') {
                                path.rLineTo(fArr2[i4], 0.0f);
                                f18 += fArr2[i4];
                            } else if (c3 != 'q') {
                                if (c3 != 'v') {
                                    if (c3 != 'L') {
                                        if (c3 == 'M') {
                                            f6 = fArr2[i4];
                                            f7 = fArr2[i4 + 1];
                                            if (i4 > 0) {
                                                path.lineTo(f6, f7);
                                            } else {
                                                path.moveTo(f6, f7);
                                                f18 = f6;
                                                f19 = f7;
                                            }
                                        } else if (c3 == 'S') {
                                            if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                                f18 = (f18 * 2.0f) - f14;
                                                f19 = (f19 * 2.0f) - f15;
                                            }
                                            float f27 = f18;
                                            int i12 = i4 + 1;
                                            int i13 = i4 + 2;
                                            int i14 = i4 + 3;
                                            path.cubicTo(f27, f19, fArr2[i4], fArr2[i12], fArr2[i13], fArr2[i14]);
                                            f = fArr2[i4];
                                            f2 = fArr2[i12];
                                            float f28 = fArr2[i13];
                                            f4 = fArr2[i14];
                                            f18 = f28;
                                            f14 = f;
                                            float f29 = f4;
                                            f15 = f2;
                                            f19 = f29;
                                        } else if (c3 == 'T') {
                                            if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                                f18 = (f18 * 2.0f) - f14;
                                                f19 = (f19 * 2.0f) - f15;
                                            }
                                            int i15 = i4 + 1;
                                            path.quadTo(f18, f19, fArr2[i4], fArr2[i15]);
                                            i3 = i4;
                                            f15 = f19;
                                            f14 = f18;
                                            f18 = fArr2[i4];
                                            f19 = fArr2[i15];
                                        } else if (c3 == 'l') {
                                            int i16 = i4 + 1;
                                            path.rLineTo(fArr2[i4], fArr2[i16]);
                                            f18 += fArr2[i4];
                                            f5 = fArr2[i16];
                                        } else if (c3 == 'm') {
                                            float f30 = fArr2[i4];
                                            f18 += f30;
                                            float f31 = fArr2[i4 + 1];
                                            f19 += f31;
                                            if (i4 > 0) {
                                                path.rLineTo(f30, f31);
                                            } else {
                                                path.rMoveTo(f30, f31);
                                            }
                                        } else if (c3 == 's') {
                                            if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                                f8 = f18 - f14;
                                                f9 = f19 - f15;
                                            } else {
                                                f8 = 0.0f;
                                                f9 = 0.0f;
                                            }
                                            int i17 = i4 + 1;
                                            int i18 = i4 + 2;
                                            int i19 = i4 + 3;
                                            path.rCubicTo(f8, f9, fArr2[i4], fArr2[i17], fArr2[i18], fArr2[i19]);
                                            f = fArr2[i4] + f18;
                                            f2 = fArr2[i17] + f19;
                                            f18 += fArr2[i18];
                                            f3 = fArr2[i19];
                                        } else if (c3 == 't') {
                                            if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                                f10 = f18 - f14;
                                                f11 = f19 - f15;
                                            } else {
                                                f11 = 0.0f;
                                                f10 = 0.0f;
                                            }
                                            int i20 = i4 + 1;
                                            path.rQuadTo(f10, f11, fArr2[i4], fArr2[i20]);
                                            f15 = f11 + f19;
                                            float f32 = f10 + f18;
                                            f18 = fArr2[i4] + f18;
                                            f19 = fArr2[i20] + f19;
                                            f14 = f32;
                                        }
                                        i3 = i4;
                                        f21 = f19;
                                        f20 = f18;
                                    } else {
                                        int i21 = i4 + 1;
                                        path.lineTo(fArr2[i4], fArr2[i21]);
                                        f6 = fArr2[i4];
                                        f7 = fArr2[i21];
                                    }
                                    f18 = f6;
                                    f19 = f7;
                                } else {
                                    path.rLineTo(0.0f, fArr2[i4]);
                                    f5 = fArr2[i4];
                                }
                                f19 += f5;
                            } else {
                                int i22 = i4 + 1;
                                int i23 = i4 + 2;
                                int i24 = i4 + 3;
                                path.rQuadTo(fArr2[i4], fArr2[i22], fArr2[i23], fArr2[i24]);
                                f = fArr2[i4] + f18;
                                f2 = fArr2[i22] + f19;
                                f18 += fArr2[i23];
                                f3 = fArr2[i24];
                            }
                            i3 = i4;
                        } else {
                            int i25 = i4 + 2;
                            int i26 = i4 + 3;
                            int i27 = i4 + 4;
                            int i28 = i4 + 5;
                            path.rCubicTo(fArr2[i4], fArr2[i4 + 1], fArr2[i25], fArr2[i26], fArr2[i27], fArr2[i28]);
                            f = fArr2[i25] + f18;
                            f2 = fArr2[i26] + f19;
                            f18 += fArr2[i27];
                            f3 = fArr2[i28];
                        }
                        f4 = f19 + f3;
                        f14 = f;
                        float f292 = f4;
                        f15 = f2;
                        f19 = f292;
                        i3 = i4;
                    } else {
                        int i29 = i4 + 5;
                        int i30 = i4 + 6;
                        i3 = i4;
                        drawArc(path, f18, f19, fArr2[i29] + f18, fArr2[i30] + f19, fArr2[i4], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3] != 0.0f, fArr2[i4 + 4] != 0.0f);
                        f18 += fArr2[i29];
                        f19 += fArr2[i30];
                    }
                    i4 = i3 + i;
                    c4 = c2;
                    c3 = c4;
                    z = false;
                } else {
                    i3 = i4;
                    int i31 = i3 + 5;
                    int i32 = i3 + 6;
                    drawArc(path, f18, f19, fArr2[i31], fArr2[i32], fArr2[i3], fArr2[i3 + 1], fArr2[i3 + 2], fArr2[i3 + 3] != 0.0f, fArr2[i3 + 4] != 0.0f);
                    f18 = fArr2[i31];
                    f19 = fArr2[i32];
                }
                f15 = f19;
                f14 = f18;
                i4 = i3 + i;
                c4 = c2;
                c3 = c4;
                z = false;
            }
            fArr[z ? 1 : 0] = f18;
            fArr[1] = f19;
            fArr[2] = f14;
            fArr[3] = f15;
            fArr[4] = f20;
            fArr[5] = f21;
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            double radians = Math.toRadians(f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = f;
            double d4 = f2;
            double d5 = f5;
            double d6 = ((d3 * cos) + (d4 * sin)) / d5;
            double d7 = f6;
            double d8 = (((-f) * sin) + (d4 * cos)) / d7;
            double d9 = f4;
            double d10 = ((f3 * cos) + (d9 * sin)) / d5;
            double d11 = (((-f3) * sin) + (d9 * cos)) / d7;
            double d12 = d6 - d10;
            double d13 = d8 - d11;
            double d14 = (d6 + d10) / 2.0d;
            double d15 = (d8 + d11) / 2.0d;
            double d16 = (d12 * d12) + (d13 * d13);
            if (d16 == 0.0d) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d17 = (1.0d / d16) - 0.25d;
            if (d17 < 0.0d) {
                Log.w(PathParser.LOGTAG, "Points are too far apart " + d16);
                float sqrt = (float) (Math.sqrt(d16) / 1.99999d);
                drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d17);
            double d18 = d12 * sqrt2;
            double d19 = sqrt2 * d13;
            if (z == z2) {
                d = d14 - d19;
                d2 = d15 + d18;
            } else {
                d = d14 + d19;
                d2 = d15 - d18;
            }
            double atan2 = Math.atan2(d8 - d2, d6 - d);
            double atan22 = Math.atan2(d11 - d2, d10 - d) - atan2;
            if (z2 != (atan22 >= 0.0d)) {
                atan22 = atan22 > 0.0d ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d20 = d * d5;
            double d21 = d2 * d7;
            arcToBezier(path, (d20 * cos) - (d21 * sin), (d20 * sin) + (d21 * cos), d5, d7, d3, d4, radians, atan2, atan22);
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            double d10 = d3;
            int ceil = (int) Math.ceil(Math.abs((d9 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d11 = -d10;
            double d12 = d11 * cos;
            double d13 = d4 * sin;
            double d14 = d11 * sin;
            double d15 = d4 * cos;
            double d16 = d9 / ceil;
            double d17 = d6;
            double d18 = (sin2 * d12) - (cos2 * d13);
            double d19 = (sin2 * d14) + (cos2 * d15);
            double d20 = d8;
            int i = 0;
            double d21 = d5;
            while (i < ceil) {
                double d22 = d20 + d16;
                double sin3 = Math.sin(d22);
                double cos3 = Math.cos(d22);
                double d23 = d16;
                double d24 = (d + ((d10 * cos) * cos3)) - (d13 * sin3);
                double d25 = d2 + (d10 * sin * cos3) + (d15 * sin3);
                double d26 = (d12 * sin3) - (d13 * cos3);
                double d27 = (sin3 * d14) + (cos3 * d15);
                double d28 = d22 - d20;
                double tan = Math.tan(d28 / 2.0d);
                double sin4 = (Math.sin(d28) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) (d21 + (d18 * sin4)), (float) (d17 + (d19 * sin4)), (float) (d24 - (sin4 * d26)), (float) (d25 - (sin4 * d27)), (float) d24, (float) d25);
                i++;
                d17 = d25;
                d21 = d24;
                sin = sin;
                d20 = d22;
                d19 = d27;
                ceil = ceil;
                d18 = d26;
                cos = cos;
                d10 = d3;
                d16 = d23;
            }
        }
    }

    private PathParser() {
    }
}
