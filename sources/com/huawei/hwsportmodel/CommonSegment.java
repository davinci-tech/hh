package com.huawei.hwsportmodel;

import android.text.TextUtils;
import defpackage.kwl;
import defpackage.kwp;
import defpackage.kwq;
import defpackage.kws;
import defpackage.kwt;
import defpackage.kwu;
import defpackage.kwv;
import defpackage.kxb;
import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class CommonSegment implements Serializable {
    protected static final String SC = "sc";
    protected static final String SD = "sd";
    protected static final String SEE = "see";
    protected static final String SGCT = "sgct";
    protected static final String SGIA = "sgia";
    protected static final String SGTB = "sgtb";
    protected static final String SHR = "shr";
    protected static final String SHT = "sht";
    protected static final String SN = "sn";
    protected static final String SP = "sp";
    protected static final String SPI = "spi";
    protected static final String SPIR = "spir";
    protected static final String SSA = "ssa";
    protected static final String SSL = "ssl";
    protected static final String ST = "st";
    protected static final String STD = "std";
    protected static final String STP = "stp";
    protected static final String STR = "str";
    protected static final String SVO = "svo";
    protected static final String SVR = "svr";
    private static final long serialVersionUID = -8327203696100453982L;

    public abstract void fromTrackString(String[] strArr);

    public abstract int getFieldNum();

    public abstract int getSportSegmentMode();

    public abstract String toTrackString();

    public abstract void toTrackString(StringBuffer stringBuffer);

    public void fromTrackString(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        fromTrackString(str.split(";"));
    }

    public static <T extends CommonSegment> Class<T> getSegmentClass(int i) {
        if (kxb.a(i)) {
            return kwv.class;
        }
        if (i == 220) {
            return kws.class;
        }
        if (i == 279) {
            return kwp.class;
        }
        if (i == 259) {
            return kwq.class;
        }
        if (i == 287 || i == 288 || i == 291) {
            return TrackFreeDivingSegment.class;
        }
        if (i == 286) {
            return TrackGolfCourseSegment.class;
        }
        if (i == 274) {
            return kwl.class;
        }
        if (i == 283) {
            return kwt.class;
        }
        return kwu.class;
    }
}
