package com.huawei.featureuserprofile.route.hwkmlmodel;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class LineString {
    private static final int ABNORMAL_POINT_LENGTH = 1;
    private static final int POINT_WITH_TWO_DATA_LENGTH = 2;
    private static final String TAG = "KML_LineString";
    private String mCoordinates;
    private List<LineStringPoint> mLineStringPointList = new ArrayList();

    public static class LineStringPoint {
        private double mEle = Double.MIN_VALUE;
        private double mLat;
        private double mLon;

        public double getLon() {
            return this.mLon;
        }

        public void setLon(double d) {
            this.mLon = d;
        }

        public double getLat() {
            return this.mLat;
        }

        public void setLat(double d) {
            this.mLat = d;
        }

        public double getEle() {
            return this.mEle;
        }

        public void setEle(double d) {
            this.mEle = d;
        }
    }

    public String getCoordinates() {
        makePointList(this.mCoordinates);
        return this.mCoordinates;
    }

    public void setCoordinates(String str) {
        this.mCoordinates = str;
    }

    public List<LineStringPoint> getLineStringPointList() {
        return this.mLineStringPointList;
    }

    public void setLineStringPointList(List<LineStringPoint> list) {
        this.mLineStringPointList = list;
    }

    private void makePointList(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split(" ")) {
            LineStringPoint lineStringPoint = new LineStringPoint();
            String[] split = str2.split(",");
            if (split.length > 1 && (split.length != 2 || (!TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])))) {
                try {
                    lineStringPoint.mLon = Double.parseDouble(split[0]);
                } catch (NumberFormatException unused) {
                    lineStringPoint.mLon = 0.0d;
                }
                try {
                    lineStringPoint.mLat = Double.parseDouble(split[1]);
                } catch (NumberFormatException unused2) {
                    lineStringPoint.mLat = 0.0d;
                }
                if (split.length > 2) {
                    try {
                        lineStringPoint.mEle = Double.parseDouble(split[2]);
                    } catch (NumberFormatException unused3) {
                        lineStringPoint.mEle = 0.0d;
                    }
                }
                this.mLineStringPointList.add(lineStringPoint);
            } else {
                LogUtil.h(TAG, "longitude or latitude of point is missing");
            }
        }
    }
}
