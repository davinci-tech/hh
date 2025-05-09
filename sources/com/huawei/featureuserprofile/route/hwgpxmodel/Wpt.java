package com.huawei.featureuserprofile.route.hwgpxmodel;

import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Attribute;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.tencent.open.SocialConstants;
import defpackage.buc;
import java.util.Objects;

@Tag("wpt")
/* loaded from: classes3.dex */
public class Wpt {

    @Ignore
    public static final String COLOR = "color";

    @Ignore
    private static final double DOUBLE_ZERO = 0.0d;

    @Ignore
    public static final String MODE = "mode";

    @Tag(SocialConstants.PARAM_APP_DESC)
    private String mDesc;

    @Tag("ele")
    private double mElevation;

    @Tag(order = 1, value = "extensions")
    private Extensions mExtensions;

    @Ignore
    private int mIconColor;

    @Attribute("lat")
    private double mLatitude;

    @Attribute("lon")
    private double mLongitude;

    @Ignore
    private int mMode;

    @Tag("name")
    private String mName;

    @Tag("time")
    private String mStringTime;

    @Tag("sym")
    private int mSym;

    @Ignore
    private long mTime;

    public Wpt(double d, double d2, double d3, long j) {
        this.mElevation = Double.MIN_VALUE;
        this.mSym = MarkPoint.MarkType.COMMON.type();
        this.mIconColor = MarkPoint.PointColor.AUTO.color();
        this.mMode = 0;
        this.mLatitude = d;
        this.mLongitude = d2;
        this.mElevation = d3;
        this.mTime = j;
    }

    public Wpt() {
        this(0.0d, 0.0d, 0.0d, 0L);
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(double d) {
        this.mLatitude = d;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(double d) {
        this.mLongitude = d;
    }

    public double getElevation() {
        return this.mElevation;
    }

    public void setElevation(double d) {
        this.mElevation = d;
    }

    public long getTime() {
        return this.mTime;
    }

    public String getStringTime() {
        return this.mStringTime;
    }

    public void setStringTime(String str) {
        this.mStringTime = str;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public void setTime(long j) {
        this.mTime = j;
        this.mStringTime = longTimeToString(j);
    }

    public void setSym(int i) {
        this.mSym = i;
    }

    public int getSym() {
        return this.mSym;
    }

    public int getIconColor() {
        Extensions extensions = this.mExtensions;
        if (extensions == null) {
            return this.mIconColor;
        }
        int i = Extensions.getInt(extensions.getExtensionsMap(), "color", this.mIconColor);
        this.mIconColor = i;
        return i;
    }

    public int getMode() {
        Extensions extensions = this.mExtensions;
        if (extensions == null) {
            return this.mMode;
        }
        int i = Extensions.getInt(extensions.getExtensionsMap(), MODE, this.mMode);
        this.mMode = i;
        return i;
    }

    private String longTimeToString(long j) {
        return buc.a(j);
    }

    public Extensions getExtensions() {
        return this.mExtensions;
    }

    public void setExtensions(Extensions extensions) {
        this.mExtensions = extensions;
    }

    public <T> void addExtension(String str, T t) {
        if (this.mExtensions == null) {
            this.mExtensions = new Extensions();
        }
        this.mExtensions.addExtension(str, t);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Wpt wpt = (Wpt) obj;
        return Double.compare(wpt.mLatitude, this.mLatitude) == 0 && Double.compare(wpt.mLongitude, this.mLongitude) == 0 && Double.compare(wpt.mElevation, this.mElevation) == 0 && this.mTime == wpt.mTime;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.mLatitude), Double.valueOf(this.mLongitude), Double.valueOf(this.mElevation), Long.valueOf(this.mTime));
    }
}
