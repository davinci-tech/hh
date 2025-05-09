package com.huawei.healthcloud.plugintrack.model;

import android.content.Context;
import android.text.format.DateFormat;
import com.huawei.health.R;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import defpackage.gvv;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public abstract class AbstractTrackSummaryData {
    protected static final double DISTANCE_DIGITAL = 0.005d;
    protected static final String EMPTY_STRING = "";
    protected static final double MAX_PACE = 360000.0d;
    protected static final double MIN_PACE = 3.6d;
    protected int mAbnormalTrack;
    protected float mAlterData;
    protected float mCalories;
    protected int mChiefDataValue;
    protected int mChiefSportDataType;
    protected int mDuplicated;
    protected long mDuration;
    protected long mEndTime;
    protected int mSportType;
    protected long mStartTime;

    public int getSportDurationDrawableSource() {
        return R.drawable._2131430060_res_0x7f0b0aac;
    }

    public int getSportTypeDrawableSource(boolean z) {
        return z ? R.drawable.ic_health_list_colours_run : R.drawable.ic_health_list_run;
    }

    public AbstractTrackSummaryData() {
        this.mChiefSportDataType = 0;
        this.mAlterData = 0.0f;
        this.mAbnormalTrack = 0;
        this.mDuplicated = 0;
    }

    public AbstractTrackSummaryData(MotionPathSimplify motionPathSimplify) {
        this.mChiefSportDataType = 0;
        this.mAlterData = 0.0f;
        this.mAbnormalTrack = 0;
        this.mDuplicated = 0;
        if (motionPathSimplify != null) {
            this.mChiefDataValue = motionPathSimplify.requestTotalDistance();
            this.mChiefSportDataType = motionPathSimplify.requestChiefSportDataType();
            this.mAlterData = motionPathSimplify.requestAvgPace();
            this.mDuration = motionPathSimplify.requestTotalTime();
            this.mEndTime = motionPathSimplify.requestEndTime();
            this.mStartTime = motionPathSimplify.requestStartTime();
            this.mSportType = motionPathSimplify.requestSportType();
            this.mCalories = motionPathSimplify.requestTotalCalories();
            this.mAbnormalTrack = motionPathSimplify.requestAbnormalTrack();
            this.mDuplicated = motionPathSimplify.requestDuplicated();
        }
    }

    public AbstractTrackSummaryData(RelativeSportData relativeSportData) {
        this.mChiefSportDataType = 0;
        this.mAlterData = 0.0f;
        this.mAbnormalTrack = 0;
        this.mDuplicated = 0;
        if (relativeSportData != null) {
            this.mChiefDataValue = relativeSportData.getDistance();
            if (relativeSportData.getDistance() != 0) {
                this.mAlterData = (float) ((relativeSportData.getDuration() * 1.0d) / relativeSportData.getDistance());
            }
            this.mDuration = relativeSportData.getDuration();
            this.mEndTime = relativeSportData.getEndTime();
            this.mStartTime = relativeSportData.getStartTime();
            this.mSportType = relativeSportData.getSportType();
            this.mCalories = relativeSportData.getCalories();
        }
    }

    public int getChiefDataValue() {
        return this.mChiefDataValue;
    }

    public int getChiefSportDataType() {
        return this.mChiefSportDataType;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public float getAlterData() {
        return this.mAlterData;
    }

    public int getSportType() {
        return this.mSportType;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public int getDuplicated() {
        return this.mDuplicated;
    }

    public int getAbnormalType() {
        return this.mAbnormalTrack;
    }

    public float getCalorie() {
        return this.mCalories;
    }

    public void setChiefDataValueAndType(int i, int i2) {
        this.mChiefDataValue = i;
        this.mChiefSportDataType = i2;
    }

    public void setDuration(long j) {
        this.mDuration = j;
    }

    public void saveAlterData(float f) {
        this.mAlterData = f;
    }

    public void setSportType(int i) {
        this.mSportType = i;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public void setAbnormalType(int i) {
        this.mAbnormalTrack = i;
    }

    public void setCalories(float f) {
        this.mCalories = f;
    }

    public void setDuplicated(int i) {
        this.mDuplicated = i;
    }

    public String getChiefDataString() {
        return getDistanceString();
    }

    public String getChiefDataUnitString() {
        return getDistanceUnitString();
    }

    public String getAlterDataString() {
        return getPaceString();
    }

    public String getAlterUnitString() {
        return getPaceUnitString();
    }

    public String getDistanceString() {
        String e = gvv.e(BaseApplication.getContext());
        if (UnitUtil.h()) {
            double e2 = UnitUtil.e((this.mChiefDataValue * 1.0d) / 1000.0d, 3);
            return e2 >= DISTANCE_DIGITAL ? getNumberFormat(e2, 2) : e;
        }
        double d = (this.mChiefDataValue * 1.0d) / 1000.0d;
        return d >= DISTANCE_DIGITAL ? getNumberFormat(d, 2) : e;
    }

    public String getDistanceUnitString() {
        Context context = BaseApplication.getContext();
        if (context == null) {
            return "";
        }
        if (UnitUtil.h()) {
            return context.getString(R.string._2130841383_res_0x7f020f27);
        }
        return context.getString(R.string._2130841382_res_0x7f020f26);
    }

    public String getPaceString() {
        double d = this.mAlterData;
        if (d > MAX_PACE || d <= MIN_PACE) {
            return gvv.e(BaseApplication.getContext());
        }
        return gvv.a(UnitUtil.h() ? (float) UnitUtil.d(this.mAlterData, 3) : this.mAlterData);
    }

    public String getPaceUnitString() {
        Context context = BaseApplication.getContext();
        if (context == null) {
            return "";
        }
        if (UnitUtil.h()) {
            return "/" + context.getResources().getString(R.string._2130841383_res_0x7f020f27);
        }
        return "/" + context.getResources().getString(R.string._2130841382_res_0x7f020f26);
    }

    public String getSpeedString() {
        double seconds;
        if (Math.abs(this.mAlterData) < 1.0E-4f) {
            return gvv.e(BaseApplication.getContext());
        }
        if (UnitUtil.h()) {
            seconds = UnitUtil.e(TimeUnit.HOURS.toSeconds(1L) / this.mAlterData, 3);
        } else {
            seconds = TimeUnit.HOURS.toSeconds(1L) / this.mAlterData;
        }
        return UnitUtil.e(seconds, 1, 2);
    }

    public String getSpeedUnitString() {
        Context context = BaseApplication.getContext();
        if (context == null) {
            return "";
        }
        if (UnitUtil.h()) {
            return context.getString(R.string._2130844079_res_0x7f0219af);
        }
        return context.getString(R.string._2130844078_res_0x7f0219ae);
    }

    public String getItemDataString() {
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "Md")).format(Long.valueOf(this.mStartTime));
    }

    public String getItemDurationString() {
        return UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(this.mDuration));
    }

    protected String getNumberFormat(double d, int i) {
        return UnitUtil.e(d, 1, i);
    }
}
