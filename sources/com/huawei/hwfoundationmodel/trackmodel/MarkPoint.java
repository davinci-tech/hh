package com.huawei.hwfoundationmodel.trackmodel;

import com.google.gson.annotations.SerializedName;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class MarkPoint implements Serializable, TimeSequence, Cloneable {
    private static final long serialVersionUID = 8974499289536629196L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("lat")
    private double f6362a;

    @SerializedName("lng")
    private double b;

    @SerializedName("color")
    private int c;

    @SerializedName(Wpt.MODE)
    private int d;

    @SerializedName("order")
    private int e;

    @SerializedName("type")
    private int f;

    @SerializedName("time")
    private long g;

    public void e(int i) {
        this.e = i;
    }

    public void c(int i) {
        this.f = i;
    }

    public void a(long j) {
        this.g = j;
    }

    public void a(double d) {
        this.f6362a = d;
    }

    public void c(double d) {
        this.b = d;
    }

    public void d(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.g;
    }

    public int b() {
        return this.e;
    }

    public int j() {
        return this.f;
    }

    public double d() {
        return this.f6362a;
    }

    public double a() {
        return this.b;
    }

    public int e() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public MarkPoint clone() throws CloneNotSupportedException {
        return (MarkPoint) super.clone();
    }

    public enum MarkType {
        COMMON(0),
        START(1),
        CAMPSITE(2),
        LEFT(3),
        RIGHT(4),
        STRAIGHT(5),
        DANGER(6),
        BEAST(7),
        HEALTHCARE(8),
        WATER(9),
        PROVIDE(10),
        BRIDGE(11),
        RISK_AVERSION(12),
        TOP_MOUNTAIN(13),
        HOUSE(14),
        END(HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE),
        AUTO_MARK(255);

        private final int mType;

        MarkType(int i) {
            this.mType = i;
        }

        public int type() {
            return this.mType;
        }

        public static MarkType getType(int i) {
            if (i >= 0 && i < values().length) {
                return values()[i];
            }
            return values()[0];
        }
    }

    public enum PointColor {
        ORANGE(0),
        RED(1),
        BLUE(2),
        YELLOW(3),
        GRAY(4),
        GREEN(5),
        AUTO(255);

        private final int mColor;

        PointColor(int i) {
            this.mColor = i;
        }

        public int color() {
            return this.mColor;
        }

        public static PointColor getColor(int i) {
            if (i >= 0 && i < values().length) {
                return values()[i];
            }
            return values()[0];
        }
    }
}
