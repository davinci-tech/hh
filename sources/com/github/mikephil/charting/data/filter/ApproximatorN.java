package com.github.mikephil.charting.data.filter;

import java.util.ArrayList;

/* loaded from: classes8.dex */
public class ApproximatorN {
    public float[] reduceWithDouglasPeucker(float[] fArr, float f) {
        int i = 2;
        int length = fArr.length / 2;
        if (f <= 2.0f || f >= length) {
            return fArr;
        }
        boolean[] zArr = new boolean[length];
        int i2 = 0;
        zArr[0] = true;
        int i3 = length - 1;
        zArr[i3] = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Line(0, i3, fArr));
        do {
            Line line = (Line) arrayList.remove(arrayList.size() - 1);
            zArr[line.index] = true;
            i++;
            if (i == f) {
                break;
            }
            Line line2 = new Line(line.start, line.index, fArr);
            if (line2.index > 0) {
                arrayList.add(insertionIndex(line2, arrayList), line2);
            }
            Line line3 = new Line(line.index, line.end, fArr);
            if (line3.index > 0) {
                arrayList.add(insertionIndex(line3, arrayList), line3);
            }
        } while (arrayList.isEmpty());
        float[] fArr2 = new float[i * 2];
        int i4 = 0;
        int i5 = 0;
        while (i2 < i) {
            if (zArr[i2]) {
                int i6 = i4 + 1;
                fArr2[i4] = fArr[i5];
                i4 += 2;
                fArr2[i6] = fArr[i5 + 1];
            }
            i2++;
            i5 += 2;
        }
        return fArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float distanceToLine(float f, float f2, float[] fArr, float[] fArr2) {
        float f3 = fArr2[0] - fArr[0];
        float f4 = fArr2[1] - fArr[1];
        return (float) (Math.abs((((f * f4) - (f2 * f3)) - (r0 * r7)) + (r1 * r6)) / Math.sqrt((f3 * f3) + (f4 * f4)));
    }

    static class Line {
        float distance;
        int end;
        int index;
        int start;

        Line(int i, int i2, float[] fArr) {
            this.distance = 0.0f;
            this.index = 0;
            this.start = i;
            this.end = i2;
            int i3 = i * 2;
            float[] fArr2 = {fArr[i3], fArr[i3 + 1]};
            int i4 = i2 * 2;
            float[] fArr3 = {fArr[i4], fArr[i4 + 1]};
            int i5 = i + 1;
            if (i2 <= i5) {
                return;
            }
            int i6 = i5 * 2;
            while (i5 < i2) {
                float distanceToLine = ApproximatorN.distanceToLine(fArr[i6], fArr[i6 + 1], fArr2, fArr3);
                if (distanceToLine > this.distance) {
                    this.index = i5;
                    this.distance = distanceToLine;
                }
                i5++;
                i6 += 2;
            }
        }

        boolean equals(Line line) {
            return this.start == line.start && this.end == line.end && this.index == line.index;
        }

        boolean lessThan(Line line) {
            return this.distance < line.distance;
        }
    }

    private static int insertionIndex(Line line, ArrayList<Line> arrayList) {
        int size = arrayList.size();
        int i = 0;
        while (!arrayList.isEmpty()) {
            int i2 = ((size - i) / 2) + i;
            Line line2 = arrayList.get(i2);
            if (line2.equals(line)) {
                return i2;
            }
            if (line.lessThan(line2)) {
                size = i2;
            } else {
                i = i2 + 1;
            }
        }
        return i;
    }
}
