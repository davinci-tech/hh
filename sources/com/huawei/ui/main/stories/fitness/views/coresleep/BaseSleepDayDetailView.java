package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.R$string;
import defpackage.jec;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.pvz;
import defpackage.pxb;
import defpackage.pyf;
import defpackage.scn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public abstract class BaseSleepDayDetailView extends View {
    private static final int ARRAY_COLLECTION_FOUR_INDEX = 2;
    private static final int ARRAY_COLLECTION_TWO_INDEX = 2;
    private static final String COLON = ":";
    public static final int FITNESS_SLEEP_TYPE_AWAKE_SLEEP = 67;
    public static final int FITNESS_SLEEP_TYPE_AWAKE_SLEEP_CONNECTION = 700;
    public static final int FITNESS_SLEEP_TYPE_DEEP_SLEEP = 65;
    public static final int FITNESS_SLEEP_TYPE_NOON_SLEEP = 69;
    public static final int FITNESS_SLEEP_TYPE_NOON_SLEEP_CONNECTION = 710;
    private static final int FITNESS_SLEEP_TYPE_NO_SLEEP = 64;
    public static final int FITNESS_SLEEP_TYPE_SHALLOW_SLEEP = 66;
    public static final int FITNESS_SLEEP_TYPE_SLUMBER_SLEEP = 68;
    private static final float HALF_FLOAT = 2.0f;
    private static final int HALF_INT = 2;
    public static final int HOUR_IN_DAY = 24;
    public static final float MINS_IN_DAY = 1440.0f;
    public static final int MINS_IN_HOUR = 60;
    private static final float PRECISION = 1.0E-7f;
    public static final int SLEEP_CHART_COLOR_TYPE = 1;
    public static final int SLEEP_START_END_HOUR = 20;
    private static final String TAG = "BaseSleepDayDetailView";
    private static final String TAG_RELEASE = "R_Sleep_BaseSleepDayDetailView";
    private static final int THREE_TIMES = 3;
    private static final int TWO_TIMES_INT = 2;
    protected Canvas mCanvas;
    private float mChartBorderWidth;
    private List<String> mChartLabels;
    private int mColorType;
    private Context mContext;
    private List<pvz> mCopyOfFitnessDataList;
    private float mCoreSleepEndX;
    private float mCoreSleepStartX;
    private float mCurrentBottom;
    private pvz mCurrentData;
    private Date mCurrentDay;
    private float mCursorCenter;
    private float mCursorHeight;
    private ArrayList<pyf> mCursorList;
    private float mCursorTextHeight;
    private String[] mDate;
    private float mDiagramHeight;
    private float mDiagramWidth;
    private int mEndConnectionColor;
    private int mEndCyColor;
    private pvz mEndData;
    private String mEndDate;
    private int mEndHour;
    private int mEndLineRectColor;
    private int mEndMin;
    private String mEndText;
    private List<pvz> mFitnessDataList;
    private boolean mIsDayDetailView;
    private boolean mIsDefault;
    private boolean mIsMove;
    private boolean mIsOnlyNoonSleepType;
    private boolean mIsOtherType;
    private boolean mIsScienceSleep;
    private pvz mLastData;
    private int mLastNightEndPoint;
    private int mNightEndPoint;
    private int mNightStartPoint;
    private int mRemSleepTimes;
    private int mStartConnectionColor;
    private int mStartCyColor;
    private String mStartDate;
    private int mStartHour;
    private int mStartLineRectColor;
    private int mStartMin;
    private int mStartSleepPoint;
    private String mStartText;
    private String[] mTime;
    private float mTimeStrY;
    private float mTimeStringMargin;
    private float mTransparentHeight;
    private float mVerticalLineStartY;
    private float mViewHeight;
    private float mViewWidth;
    private static final float XAXIS_TEXT_OFFSET_DEFAULT = scn.b(8.0f);
    private static final float XAXIS_TEXT_OFFSET_TAHITI = scn.b(16.0f);
    private static final float CURSOR_TEXT_MINIMUM_HEIGHT = scn.b(4.0f);
    private static final float CURSOR_TEXT_HIGHEST_HEIGHT = scn.b(11.5f);
    private static final float CURSOR_TEXT_FLOATING_OFFSET = scn.b(19.0f);
    private static final float CURSOR_WIDTH_OFFSET = scn.b(36.0f);

    public BaseSleepDayDetailView(Context context) {
        super(context);
        this.mRemSleepTimes = 0;
        this.mNightStartPoint = 0;
        this.mNightEndPoint = 0;
        this.mLastNightEndPoint = 0;
        this.mStartSleepPoint = 0;
        this.mCoreSleepStartX = 0.0f;
        this.mCoreSleepEndX = 0.0f;
        this.mStartText = "";
        this.mEndText = "";
        this.mTimeStringMargin = scn.b(2.0f);
        this.mChartBorderWidth = scn.b(40.0f);
        this.mTimeStrY = 45.0f;
        this.mVerticalLineStartY = 20.0f;
        this.mColorType = 0;
        this.mIsDayDetailView = true;
        this.mIsScienceSleep = false;
        this.mIsDefault = true;
        this.mIsMove = false;
        this.mFitnessDataList = new ArrayList(16);
        this.mCopyOfFitnessDataList = new ArrayList(16);
        this.mCursorList = new ArrayList<>(16);
        this.mChartLabels = new ArrayList(16);
        this.mIsOtherType = false;
        this.mCurrentBottom = 0.0f;
        this.mContext = context;
    }

    public BaseSleepDayDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRemSleepTimes = 0;
        this.mNightStartPoint = 0;
        this.mNightEndPoint = 0;
        this.mLastNightEndPoint = 0;
        this.mStartSleepPoint = 0;
        this.mCoreSleepStartX = 0.0f;
        this.mCoreSleepEndX = 0.0f;
        this.mStartText = "";
        this.mEndText = "";
        this.mTimeStringMargin = scn.b(2.0f);
        this.mChartBorderWidth = scn.b(40.0f);
        this.mTimeStrY = 45.0f;
        this.mVerticalLineStartY = 20.0f;
        this.mColorType = 0;
        this.mIsDayDetailView = true;
        this.mIsScienceSleep = false;
        this.mIsDefault = true;
        this.mIsMove = false;
        this.mFitnessDataList = new ArrayList(16);
        this.mCopyOfFitnessDataList = new ArrayList(16);
        this.mCursorList = new ArrayList<>(16);
        this.mChartLabels = new ArrayList(16);
        this.mIsOtherType = false;
        this.mCurrentBottom = 0.0f;
    }

    public void setDayDetailView(boolean z) {
        this.mIsDayDetailView = z;
    }

    public Context getBaseContext() {
        return this.mContext;
    }

    public void setBaseContext(Context context) {
        this.mContext = context;
    }

    public void setColorType(int i) {
        this.mColorType = i;
    }

    public int getColorType() {
        return this.mColorType;
    }

    public int getNightStartPoint() {
        return this.mNightStartPoint;
    }

    public int getNightEndPoint() {
        return this.mNightEndPoint;
    }

    public float getChartBorderWidth() {
        return this.mChartBorderWidth;
    }

    public boolean getIsScienceSleep() {
        return this.mIsScienceSleep;
    }

    public boolean getIsOnlyNoonSleepType() {
        return this.mIsOnlyNoonSleepType;
    }

    public boolean getIsDefault() {
        return this.mIsDefault;
    }

    public void setIsDefault(boolean z) {
        this.mIsDefault = z;
    }

    public boolean getIsMove() {
        return this.mIsMove;
    }

    public void setIsMove(boolean z) {
        this.mIsMove = z;
    }

    public List<pvz> getFitnessDataList() {
        return this.mFitnessDataList;
    }

    public ArrayList<pyf> getCursorList() {
        return this.mCursorList;
    }

    public float getViewWidth() {
        return this.mViewWidth;
    }

    public void setViewWidth(float f) {
        this.mViewWidth = f;
    }

    public float getViewHeight() {
        return this.mViewHeight;
    }

    public void setViewHeight(float f) {
        this.mViewHeight = f;
    }

    public pvz getLastData() {
        return this.mLastData;
    }

    public float getDiagramWidth() {
        return this.mDiagramWidth;
    }

    public void setDiagramWidth(float f) {
        this.mDiagramWidth = f;
    }

    public float getDiagramHeight() {
        return this.mDiagramHeight;
    }

    public void setDiagramHeight(float f) {
        this.mDiagramHeight = f;
    }

    public void setIsOtherType(boolean z) {
        this.mIsOtherType = z;
    }

    public float getTransparentHeight() {
        return this.mTransparentHeight;
    }

    public void setTransparentHeight(float f) {
        this.mTransparentHeight = f;
    }

    public float getCursorHeight() {
        return this.mCursorHeight;
    }

    public void setCursorHeight(float f) {
        this.mCursorHeight = f;
    }

    public float getCursorCenter() {
        return this.mCursorCenter;
    }

    public void setCursorCenter(float f) {
        this.mCursorCenter = f;
    }

    public static class c {
        private float c = 0.0f;
        private float b = 0.0f;
        private float e = 0.0f;

        /* renamed from: a, reason: collision with root package name */
        private float f9967a = 0.0f;

        public float d() {
            return this.c;
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private float f9968a;
        private float b;
        private float c;
        private float d;
        private float e;
        private float h;

        private d() {
            this.d = 0.0f;
            this.e = 0.0f;
            this.h = 0.0f;
            this.b = 0.0f;
            this.c = 0.0f;
            this.f9968a = 0.0f;
        }
    }

    public void refreshFitnessDataList(List<pvz> list, boolean z, double d2, boolean z2, Date date) {
        LogUtil.a(TAG, "Enter refreshCoreFitness validData:", Double.valueOf(d2));
        if (this.mIsDayDetailView) {
            this.mChartBorderWidth = scn.b(40.0f);
        } else {
            this.mChartBorderWidth = scn.b(36.0f);
        }
        if (date != null) {
            this.mCurrentDay = (Date) date.clone();
        } else {
            LogUtil.b(TAG_RELEASE, "refreshFitnessDataList mCurrentDay is null ");
            this.mCurrentDay = jec.e();
        }
        this.mIsScienceSleep = z;
        if (list == null || list.size() == 0) {
            this.mIsScienceSleep = false;
        }
        this.mIsOnlyNoonSleepType = z2;
        initData();
        if (list != null) {
            LogUtil.a(TAG, "refreshCoreFitnessDataList ", list.toString());
        }
        if (list != null && list.size() > 0) {
            this.mFitnessDataList.addAll(list);
            copyOfFitnessDataList();
        }
        invalidate();
        LogUtil.a(TAG, "Leave refreshCoreFitnessDataList");
    }

    public void onConfigurationChanged() {
        drawCursorText(this.mCanvas);
    }

    private void initData() {
        this.mIsDefault = true;
        this.mIsMove = false;
        this.mFitnessDataList.clear();
        this.mCopyOfFitnessDataList.clear();
        this.mCursorList.clear();
        this.mChartLabels.clear();
        this.mRemSleepTimes = 0;
        this.mNightStartPoint = 0;
        this.mNightEndPoint = 0;
    }

    private void copyOfFitnessDataList() {
        resetCopyData();
        List<pvz> list = this.mFitnessDataList;
        if (list == null) {
            return;
        }
        this.mCopyOfFitnessDataList.addAll(list);
        Iterator<pvz> it = this.mFitnessDataList.iterator();
        while (it.hasNext()) {
            getSleepDataForUi(it.next());
        }
        Map<String, Integer> b = pxb.b(this.mFitnessDataList);
        if (b == null) {
            LogUtil.b(TAG_RELEASE, "timeMap is null!");
            return;
        }
        this.mStartSleepPoint = b.get("core_sleep_start_time_key").intValue();
        this.mLastNightEndPoint = b.get("core_sleep_end_time_key").intValue();
        LogUtil.a(TAG, " copyOfFitnessDataList mStartSleepPoint = ", Integer.valueOf(this.mStartSleepPoint));
        LogUtil.a(TAG, " copyOfFitnessDataList mNightEndPoint = ", Integer.valueOf(this.mLastNightEndPoint));
    }

    private void getSleepDataForUi(pvz pvzVar) {
        if (pvzVar == null) {
            LogUtil.b(TAG, "getSleepDataForUi data is null");
            return;
        }
        if (pvzVar.b() == 68) {
            this.mRemSleepTimes += pvzVar.b - pvzVar.o;
        }
        if (pvzVar.b() != 69) {
            if (this.mNightStartPoint == 0 && this.mNightEndPoint == 0) {
                if (pvzVar.b() != 67) {
                    this.mNightStartPoint = pvzVar.e();
                } else if (this.mFitnessDataList.size() > 1) {
                    this.mNightStartPoint = this.mFitnessDataList.get(1).e();
                }
            }
            this.mNightEndPoint = pvzVar.c();
        }
    }

    private void resetCopyData() {
        this.mCopyOfFitnessDataList.clear();
        this.mRemSleepTimes = 0;
        this.mStartSleepPoint = 0;
        this.mLastNightEndPoint = 0;
        this.mNightStartPoint = 0;
        this.mNightEndPoint = 0;
    }

    public void getTimeLabel() {
        String str;
        Paint paint = new Paint();
        paint.setTextSize(scn.b(10.0f));
        paint.setAntiAlias(true);
        this.mDate = new String[2];
        if (this.mCurrentDay == null) {
            LogUtil.b(TAG_RELEASE, "getTimeLabel mCurrentDay is null ");
            this.mCurrentDay = jec.e();
        }
        if (this.mIsScienceSleep && !this.mIsOnlyNoonSleepType) {
            getCoreSleepTimeLabel(paint);
            return;
        }
        List<String> chartLabels = getChartLabels();
        this.mChartLabels = chartLabels;
        this.mTime = new String[chartLabels.size()];
        for (int i = 0; i < this.mChartLabels.size(); i++) {
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                List<String> list = this.mChartLabels;
                str = list.get((list.size() - i) - 1);
            } else {
                str = this.mChartLabels.get(i);
            }
            this.mTime[i] = str;
        }
        this.mStartDate = dateToString(jec.s(this.mCurrentDay));
        String dateToString = dateToString(this.mCurrentDay);
        this.mEndDate = dateToString;
        String[] strArr = this.mDate;
        strArr[0] = this.mStartDate;
        strArr[1] = dateToString;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            String[] strArr2 = this.mDate;
            strArr2[0] = this.mEndDate;
            strArr2[1] = this.mStartDate;
        }
    }

    private void getCoreSleepTimeLabel(Paint paint) {
        float b;
        float b2;
        LogUtil.a(TAG, "mStartSleepPoint: ", Integer.valueOf(this.mStartSleepPoint), " mLastNightEndPoint:", Integer.valueOf(this.mLastNightEndPoint), " mStartPoint:", Integer.valueOf(this.mNightStartPoint), " mEndPoint:", Integer.valueOf(this.mNightEndPoint));
        this.mTime = new String[2];
        setStartSleepText();
        String str = this.mStartText;
        float measureText = (int) paint.measureText(str, 0, str.length());
        float f = 0.0f;
        if (this.mNightEndPoint != this.mNightStartPoint) {
            b = ((((this.mStartSleepPoint - r3) / (r1 - r3)) * (this.mViewWidth - scn.b(80.0f))) + scn.b(40.0f)) - (measureText / 2.0f);
            if (b < 0.0f) {
                b = scn.b(16.0f);
            }
        } else {
            b = scn.b(40.0f) - (measureText / 2.0f);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            b = (this.mViewWidth - measureText) - b;
            float b3 = scn.b(16.0f);
            float f2 = this.mViewWidth;
            if (b + measureText + b3 > f2) {
                b = (f2 - measureText) - scn.b(16.0f);
            }
        }
        this.mCoreSleepStartX = b;
        setEndSleepText();
        String str2 = this.mEndText;
        float measureText2 = (int) paint.measureText(str2, 0, str2.length());
        if (this.mNightEndPoint != this.mNightStartPoint) {
            b2 = ((((this.mLastNightEndPoint - r1) / (r0 - r1)) * (this.mViewWidth - scn.b(80.0f))) + scn.b(40.0f)) - (measureText2 / 2.0f);
            float b4 = scn.b(16.0f);
            float f3 = this.mViewWidth;
            if (b2 + measureText2 + b4 > f3) {
                b2 = (f3 - measureText2) - scn.b(16.0f);
            }
        } else {
            b2 = (this.mViewWidth - measureText2) - scn.b(16.0f);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f4 = (this.mViewWidth - b2) - measureText2;
            if (f4 >= 0.0f) {
                f = f4;
            }
        } else {
            f = b2;
        }
        this.mCoreSleepEndX = f;
        dealEndTimeIfStartGreater();
        replaceTextAndDate();
    }

    private void dealEndTimeIfStartGreater() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
            Date parse = simpleDateFormat.parse(this.mStartDate);
            Date parse2 = simpleDateFormat.parse(this.mEndDate);
            if (parse == null || parse2 == null || parse.getTime() <= parse2.getTime()) {
                return;
            }
            this.mEndDate = this.mStartDate;
            LogUtil.h(TAG, "startTime = ", Long.valueOf(parse.getTime()), " endTime = ", Long.valueOf(parse2.getTime()));
        } catch (ParseException unused) {
            LogUtil.b(TAG_RELEASE, "simpleDateFormat ParseException ");
        }
    }

    private void replaceTextAndDate() {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            String[] strArr = this.mTime;
            strArr[0] = this.mEndText;
            strArr[1] = this.mStartText;
            String[] strArr2 = this.mDate;
            strArr2[0] = this.mEndDate;
            strArr2[1] = this.mStartDate;
            return;
        }
        String[] strArr3 = this.mTime;
        strArr3[0] = this.mStartText;
        strArr3[1] = this.mEndText;
        String[] strArr4 = this.mDate;
        strArr4[0] = this.mStartDate;
        strArr4[1] = this.mEndDate;
    }

    private void setStartSleepText() {
        int i = this.mStartSleepPoint;
        int i2 = i / 60;
        int i3 = i2 + 20;
        if (i3 >= 24) {
            i3 = i2 - 4;
        }
        if (i3 >= 20 && i3 < 24) {
            this.mStartDate = dateToString(jec.s(this.mCurrentDay));
        } else {
            this.mStartDate = dateToString(this.mCurrentDay);
        }
        this.mStartText = formatTime(getTimeStr(i3) + ":" + getTimeStr(i % 60));
        if (!LanguageUtil.j(BaseApplication.getContext())) {
            this.mStartText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_start_sleep) + " " + this.mStartText;
            return;
        }
        this.mStartText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_start_sleep) + this.mStartText;
    }

    private void setEndSleepText() {
        int i = this.mLastNightEndPoint;
        int i2 = i / 60;
        int i3 = i2 + 20;
        if (i3 >= 24) {
            i3 = i2 - 4;
        }
        if (i3 > 20 && i3 < 24) {
            this.mEndDate = dateToString(jec.s(this.mCurrentDay));
        } else {
            this.mEndDate = dateToString(this.mCurrentDay);
        }
        this.mEndText = formatTime(getTimeStr(i3) + ":" + getTimeStr(i % 60));
        if (!LanguageUtil.j(BaseApplication.getContext())) {
            this.mEndText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_end_sleep) + " " + this.mEndText;
            return;
        }
        this.mEndText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_end_sleep) + this.mEndText;
    }

    public void drawSleepRect(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        List<pvz> list = this.mFitnessDataList;
        if (list == null || list.size() == 0) {
            this.mLastData = null;
            return;
        }
        this.mCurrentBottom = this.mDiagramHeight;
        int size = this.mFitnessDataList.size();
        for (int i = 0; i < size; i++) {
            pvz pvzVar = this.mFitnessDataList.get(i);
            this.mCurrentData = pvzVar;
            int e = pvzVar.e();
            int c2 = this.mCurrentData.c();
            if (this.mIsOnlyNoonSleepType) {
                drawOnlyNoonSleepRect(canvas, e, c2, size, i);
            } else if (this.mIsScienceSleep) {
                if (this.mCurrentData.b() != 69) {
                    drawCoreSleepRect(canvas, size, i);
                }
            } else if (this.mCurrentData.b() != 69) {
                drawCommonSleepRect(canvas, e, c2, size, i);
            }
        }
    }

    private void drawOnlyNoonSleepRect(Canvas canvas, int i, int i2, int i3, int i4) {
        if (canvas == null) {
            return;
        }
        c cVar = new c();
        c cVar2 = new c();
        setCurrentValueData(cVar, getOnlyNoonRectSetting(this.mCurrentData.b(), this.mCurrentBottom), i, i2);
        setRtlPosition(cVar);
        getTheLastData(this.mCurrentData);
        boolean z = true;
        int i5 = i4 + 1;
        if (i5 < i3) {
            this.mEndData = this.mFitnessDataList.get(i5);
            cVar2.e = ((i / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
            cVar2.f9967a = ((i2 / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
            boolean z2 = Math.abs(cVar.f9967a - cVar2.e) < PRECISION;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                float f = cVar2.e;
                cVar2.e = this.mViewWidth - cVar2.f9967a;
                cVar2.f9967a = this.mViewWidth - f;
                if (Math.abs(cVar.e - cVar2.f9967a) >= PRECISION) {
                    z = false;
                }
            } else {
                z = z2;
            }
            c onlyNoonRectSetting = getOnlyNoonRectSetting(this.mEndData.b(), this.mCurrentBottom);
            cVar2.c = onlyNoonRectSetting.c;
            cVar2.b = onlyNoonRectSetting.b;
            if (!this.mIsOtherType && z) {
                setCurrentRectColor(this.mCurrentData.b());
                setConnectionBlock(canvas, this.mCurrentData, cVar, this.mEndData, cVar2);
            } else if (this.mCurrentData.b() == 69) {
                setCurrentRectColor(710);
            } else {
                setCurrentRectColor(700);
            }
        } else if (this.mCurrentData.b() == 69) {
            setCurrentRectColor(710);
        } else {
            setCurrentRectColor(700);
        }
        drawCyRect(canvas, cVar);
    }

    private void setCurrentValueData(c cVar, c cVar2, int i, int i2) {
        cVar.c = cVar2.c;
        cVar.b = cVar2.b;
        cVar.e = ((i / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
        cVar.f9967a = ((i2 / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
    }

    private void drawCoreSleepRect(Canvas canvas, int i, int i2) {
        c cVar = new c();
        c cVar2 = new c();
        int e = this.mCurrentData.e();
        int i3 = this.mNightStartPoint;
        cVar.e = (((e - i3) / (this.mNightEndPoint - i3)) * this.mDiagramWidth) + this.mChartBorderWidth;
        int c2 = this.mCurrentData.c();
        int i4 = this.mNightStartPoint;
        cVar.f9967a = (((c2 - i4) / (this.mNightEndPoint - i4)) * this.mDiagramWidth) + this.mChartBorderWidth;
        boolean z = true;
        if (i2 == 1) {
            LogUtil.a(TAG, "mCurrentData.getStartPoint() = ", Integer.valueOf(this.mCurrentData.e()), "; mNightStartPoint = ", Integer.valueOf(this.mNightStartPoint), "; mNightEndPoint = ", Integer.valueOf(this.mNightEndPoint), "; mCurrentLeft = ", Float.valueOf(cVar.e), "", "; mViewWidth = ", Float.valueOf(this.mViewWidth), "; mDiagramWidth = ", Float.valueOf(this.mDiagramWidth), "mChartBorderWidth = ", Float.valueOf(this.mChartBorderWidth));
        }
        c currentRectSetting = getCurrentRectSetting(this.mCurrentData.b(), this.mCurrentBottom);
        cVar.c = currentRectSetting.c;
        cVar.b = currentRectSetting.b;
        setCurrentRectColor(this.mCurrentData.b());
        setRtlPosition(cVar);
        getTheLastData(this.mCurrentData);
        int i5 = i2 + 1;
        if (i5 < i) {
            pvz pvzVar = this.mFitnessDataList.get(i5);
            this.mEndData = pvzVar;
            int e2 = pvzVar.e();
            int i6 = this.mNightStartPoint;
            cVar2.e = (((e2 - i6) / (this.mNightEndPoint - i6)) * this.mDiagramWidth) + this.mChartBorderWidth;
            int c3 = this.mEndData.c();
            int i7 = this.mNightStartPoint;
            cVar2.f9967a = (((c3 - i7) / (this.mNightEndPoint - i7)) * this.mDiagramWidth) + this.mChartBorderWidth;
            boolean z2 = Math.abs(cVar.f9967a - cVar2.e) < PRECISION;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                float f = cVar2.e;
                cVar2.e = this.mViewWidth - cVar2.f9967a;
                cVar2.f9967a = this.mViewWidth - f;
                if (Math.abs(cVar.e - cVar2.f9967a) >= PRECISION) {
                    z = false;
                }
            } else {
                z = z2;
            }
            c currentRectSetting2 = getCurrentRectSetting(this.mEndData.b(), this.mCurrentBottom);
            cVar2.c = currentRectSetting2.c;
            cVar2.b = currentRectSetting2.b;
            if (!this.mIsOtherType && z) {
                setConnectionBlock(canvas, this.mCurrentData, cVar, this.mEndData, cVar2);
            }
        }
        drawCyRect(canvas, cVar);
    }

    private void drawCommonSleepRect(Canvas canvas, int i, int i2, int i3, int i4) {
        c cVar = new c();
        c cVar2 = new c();
        cVar.e = ((i / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
        cVar.f9967a = ((i2 / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
        c ordinaryRectSetting = getOrdinaryRectSetting(this.mCurrentData.b(), this.mCurrentBottom);
        cVar.c = ordinaryRectSetting.c;
        cVar.b = ordinaryRectSetting.b;
        setCurrentRectColor(this.mCurrentData.b());
        setRtlPosition(cVar);
        getTheLastData(this.mCurrentData);
        boolean z = true;
        int i5 = i4 + 1;
        if (i5 < i3) {
            this.mEndData = this.mFitnessDataList.get(i5);
            cVar2.e = ((r10.e() / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
            cVar2.f9967a = ((this.mEndData.c() / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
            boolean z2 = Math.abs(cVar.f9967a - cVar2.e) < PRECISION;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                float f = cVar2.e;
                cVar2.e = this.mViewWidth - cVar2.f9967a;
                cVar2.f9967a = this.mViewWidth - f;
                if (Math.abs(cVar.e - cVar2.f9967a) >= PRECISION) {
                    z = false;
                }
            } else {
                z = z2;
            }
            c ordinaryRectSetting2 = getOrdinaryRectSetting(this.mEndData.b(), this.mCurrentBottom);
            cVar2.c = ordinaryRectSetting2.c;
            cVar2.b = ordinaryRectSetting2.b;
            if (!this.mIsOtherType && z) {
                setConnectionBlock(canvas, this.mCurrentData, cVar, this.mEndData, cVar2);
            }
        }
        drawCyRect(canvas, cVar);
    }

    private void setRtlPosition(c cVar) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f = cVar.e;
            cVar.e = this.mViewWidth - cVar.f9967a;
            cVar.f9967a = this.mViewWidth - f;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void drawMaxSleepVerticalLine(android.graphics.Canvas r14) {
        /*
            r13 = this;
            if (r14 != 0) goto L3
            return
        L3:
            java.util.List<pvz> r0 = r13.mCopyOfFitnessDataList
            if (r0 == 0) goto La2
            int r0 = r0.size()
            if (r0 != 0) goto Lf
            goto La2
        Lf:
            int r0 = r13.mRemSleepTimes
            if (r0 > 0) goto La2
            boolean r0 = r13.mIsScienceSleep
            if (r0 == 0) goto L1d
            boolean r0 = r13.mIsOnlyNoonSleepType
            if (r0 != 0) goto L1d
            goto La2
        L1d:
            boolean r0 = r13.mIsOnlyNoonSleepType
            if (r0 == 0) goto L48
            java.util.ArrayList r0 = r13.getSleepSumList()
            java.lang.String r1 = "sumSleepList = "
            java.lang.String r2 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
            java.lang.String r2 = "BaseSleepDayDetailView"
            com.huawei.hwlogsmodel.LogUtil.c(r2, r1)
            pvz r0 = r13.getMaxSleepData(r0)
            if (r0 != 0) goto L3b
            return
        L3b:
            int r1 = r0.o
            int r2 = r0.b
            long r3 = r0.a()
            long r5 = r0.d()
            goto L73
        L48:
            int r1 = r13.mStartSleepPoint
            int r2 = r13.mLastNightEndPoint
            java.util.List<pvz> r0 = r13.mFitnessDataList
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L76
            java.util.List<pvz> r0 = r13.mFitnessDataList
            r3 = 0
            java.lang.Object r0 = r0.get(r3)
            pvz r0 = (defpackage.pvz) r0
            long r3 = r0.a()
            java.util.List<pvz> r0 = r13.mFitnessDataList
            int r5 = r0.size()
            int r5 = r5 + (-1)
            java.lang.Object r0 = r0.get(r5)
            pvz r0 = (defpackage.pvz) r0
            long r5 = r0.d()
        L73:
            r9 = r3
            r11 = r5
            goto L7a
        L76:
            r3 = 0
            r9 = r3
            r11 = r9
        L7a:
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            boolean r0 = health.compact.a.LanguageUtil.bc(r0)
            if (r0 == 0) goto L8b
            int r0 = 1440 - r1
            int r1 = 1440 - r2
            r6 = r0
            r5 = r1
            goto L8d
        L8b:
            r5 = r1
            r6 = r2
        L8d:
            float r0 = (float) r5
            r1 = 1152647168(0x44b40000, float:1440.0)
            float r0 = r0 / r1
            float r2 = r13.mDiagramWidth
            float r3 = r13.mChartBorderWidth
            float r4 = (float) r6
            float r4 = r4 / r1
            float r0 = r0 * r2
            float r7 = r0 + r3
            float r4 = r4 * r2
            float r8 = r4 + r3
            r3 = r13
            r4 = r14
            r3.drawStartEndTimeStr(r4, r5, r6, r7, r8, r9, r11)
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.views.coresleep.BaseSleepDayDetailView.drawMaxSleepVerticalLine(android.graphics.Canvas):void");
    }

    public void drawCursorText(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        Paint paint = new Paint();
        paint.setStrokeWidth(1.0f);
        paint.setTextSize(scn.b(10.0f));
        paint.setAntiAlias(true);
        int length = this.mTime.length;
        float[] fArr = new float[length];
        this.mCursorTextHeight = this.mCursorHeight - scn.b(4.0f);
        for (int i = 0; i < length; i++) {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            paint.setTypeface(Typeface.create(this.mContext.getResources().getString(R$string.textFontFamilyRegular), 0));
            int i2 = length - 1;
            String str = this.mTime[i2];
            float measureText = (int) paint.measureText(str, 0, str.length());
            setTextTransverseWidth(fArr, measureText, length, i);
            setFloatText(fArr, measureText, i);
            float f = this.mCursorCenter;
            float b = scn.b(36.0f);
            float f2 = fArr[i];
            if (f - b < (measureText / 2.0f) + f2 && f2 < this.mCursorCenter + scn.b(36.0f) && this.mIsDayDetailView) {
                paint.setColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                paint.setTypeface(Typeface.create(this.mContext.getResources().getString(R$string.textFontFamilyMedium), 0));
            }
            drawCursorTextExtra(i, fArr, canvas, paint, length);
            String str2 = this.mStartDate;
            float measureText2 = (int) paint.measureText(str2, 0, str2.length());
            String str3 = this.mEndDate;
            float f3 = (measureText - measureText2) / 2.0f;
            float measureText3 = (measureText - ((int) paint.measureText(str3, 0, str3.length()))) / 2.0f;
            if (i == 0) {
                canvas.drawText(this.mDate[0], fArr[i] + f3 + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue(), this.mCursorTextHeight - scn.b(10.0f), paint);
            } else if (i == i2) {
                canvas.drawText(this.mDate[1], (fArr[i] + measureText3) - ((Integer) BaseActivity.getSafeRegionWidth().second).intValue(), this.mCursorTextHeight - scn.b(10.0f), paint);
            } else {
                LogUtil.a(TAG, "i is not start and end");
            }
        }
    }

    private void drawCursorTextExtra(int i, float[] fArr, Canvas canvas, Paint paint, int i2) {
        if (i == 0) {
            canvas.drawText(this.mTime[i], fArr[i] + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue(), this.mCursorTextHeight, paint);
        } else if (i == i2 - 1) {
            canvas.drawText(this.mTime[i], fArr[i] - ((Integer) BaseActivity.getSafeRegionWidth().second).intValue(), this.mCursorTextHeight, paint);
        } else {
            canvas.drawText(this.mTime[i], fArr[i], this.mCursorTextHeight, paint);
        }
    }

    private void setFloatText(float[] fArr, float f, int i) {
        if (i < 0) {
            return;
        }
        float f2 = fArr[i];
        float f3 = CURSOR_WIDTH_OFFSET;
        float f4 = this.mCursorCenter;
        float f5 = f2 - f3;
        if (f5 <= f4) {
            float f6 = f + f2;
            float f7 = f6 + f3;
            if (f4 <= f7 && this.mIsDayDetailView) {
                if (f6 < f4 && f4 <= f7) {
                    float f8 = CURSOR_TEXT_MINIMUM_HEIGHT + (((f7 - f4) / f3) * CURSOR_TEXT_FLOATING_OFFSET);
                    float f9 = CURSOR_TEXT_HIGHEST_HEIGHT * 2.0f;
                    if (f8 > f9) {
                        f8 = f9;
                    }
                    this.mCursorTextHeight = this.mCursorHeight - f8;
                    return;
                }
                if (f2 <= f4 && f4 <= f6) {
                    this.mCursorTextHeight = this.mCursorHeight - (CURSOR_TEXT_HIGHEST_HEIGHT * 2.0f);
                    return;
                }
                if (f5 <= f4 && f4 < f2) {
                    float f10 = CURSOR_TEXT_MINIMUM_HEIGHT + (((f4 - f5) / f3) * CURSOR_TEXT_FLOATING_OFFSET);
                    float f11 = CURSOR_TEXT_HIGHEST_HEIGHT * 2.0f;
                    if (f10 > f11) {
                        f10 = f11;
                    }
                    this.mCursorTextHeight = this.mCursorHeight - f10;
                    return;
                }
                this.mCursorTextHeight = this.mCursorHeight - CURSOR_TEXT_MINIMUM_HEIGHT;
                return;
            }
        }
        this.mCursorTextHeight = this.mCursorHeight - CURSOR_TEXT_MINIMUM_HEIGHT;
    }

    private void setTextTransverseWidth(float[] fArr, float f, int i, int i2) {
        float f2 = nsn.ag(this.mContext) ? XAXIS_TEXT_OFFSET_TAHITI : XAXIS_TEXT_OFFSET_DEFAULT;
        if (i2 == 0) {
            if (this.mIsScienceSleep && !this.mIsOnlyNoonSleepType) {
                fArr[i2] = this.mCoreSleepStartX + f2;
                return;
            } else {
                fArr[i2] = scn.b(16.0f) + f2;
                return;
            }
        }
        int i3 = i - 1;
        if (i2 == i3) {
            if (this.mIsScienceSleep && !this.mIsOnlyNoonSleepType) {
                fArr[i2] = this.mCoreSleepEndX - f2;
                return;
            } else {
                fArr[i2] = ((this.mViewWidth - scn.b(16.0f)) - f) - f2;
                return;
            }
        }
        fArr[i2] = ((((this.mViewWidth - (scn.b(16.0f) * 2.0f)) - f) / i3) * i2) + scn.b(16.0f);
    }

    private void drawStartEndTimeStr(Canvas canvas, int i, int i2, float f, float f2, long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        this.mStartHour = calendar.get(11);
        this.mStartMin = calendar.get(12);
        String formatTime = formatTime(getTimeStr(this.mStartHour) + ":" + getTimeStr(this.mStartMin));
        calendar.setTimeInMillis(j2);
        this.mEndHour = calendar.get(11);
        this.mEndMin = calendar.get(12);
        String formatTime2 = formatTime(getTimeStr(this.mEndHour) + ":" + getTimeStr(this.mEndMin));
        Paint paint = new Paint();
        if (this.mColorType == 1) {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        } else {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        }
        paint.setTextSize(nrf.e(0, this.mContext.getResources().getDimension(R.dimen._2131362708_res_0x7f0a0394)));
        paint.setAntiAlias(true);
        if (this.mIsScienceSleep && !this.mIsOnlyNoonSleepType) {
            canvas.drawText(formatTime, (f - ((int) paint.measureText(formatTime, 0, formatTime.length()))) - this.mTimeStringMargin, this.mTimeStrY, paint);
            canvas.drawText(formatTime2, f2 + this.mTimeStringMargin, this.mTimeStrY, paint);
        } else {
            drawCommonSleepStartEndTimeStr(canvas, paint, f, f2);
        }
    }

    private void drawCommonSleepStartEndTimeStr(Canvas canvas, Paint paint, float f, float f2) {
        String startEndTimeFormat;
        String startEndTimeFormat2;
        boolean z;
        boolean z2;
        float f3;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            startEndTimeFormat2 = getStartEndTimeFormat(this.mStartHour, this.mStartMin);
            startEndTimeFormat = getStartEndTimeFormat(this.mEndHour, this.mEndMin);
        } else {
            startEndTimeFormat = getStartEndTimeFormat(this.mStartHour, this.mStartMin);
            startEndTimeFormat2 = getStartEndTimeFormat(this.mEndHour, this.mEndMin);
        }
        float measureText = (int) paint.measureText(startEndTimeFormat, 0, startEndTimeFormat.length());
        float f4 = (f - measureText) - this.mTimeStringMargin;
        if (0.0f >= f4 - scn.b(16.0f)) {
            f4 = scn.b(16.0f);
            z = true;
        } else {
            z = false;
        }
        float f5 = this.mTimeStringMargin + f2 + measureText;
        if (scn.b(16.0f) + f5 > this.mViewWidth) {
            z2 = true;
        } else {
            f5 = f2 + this.mTimeStringMargin;
            z2 = false;
        }
        LogUtil.a(TAG, " tipEndX = ", Float.valueOf(f5), " tipStartX = ", Float.valueOf(f4), " startTextWidth = ", Float.valueOf(measureText), " mDiagramWidth = ", Float.valueOf(this.mDiagramWidth));
        if (!z && !z2) {
            drawVerticalLine(canvas, f, 0.0f);
            drawVerticalLine(canvas, f2, 0.0f);
            canvas.drawText(startEndTimeFormat, f4, this.mTimeStrY, paint);
            canvas.drawText(startEndTimeFormat2, f5, this.mTimeStrY, paint);
        }
        String str = startEndTimeFormat + Constants.LINK + startEndTimeFormat2;
        if (z2) {
            float f6 = this.mViewWidth;
            float f7 = this.mTimeStringMargin;
            float measureText2 = (int) paint.measureText(str, 0, str.length());
            canvas.drawText(startEndTimeFormat + Constants.LINK + startEndTimeFormat2, ((f6 - f7) - measureText2) - scn.b(16.0f), this.mTimeStrY, paint);
            f3 = 12.0f;
            drawVerticalLine(canvas, f, 12.0f);
            drawVerticalLine(canvas, f2, 12.0f);
        } else {
            f3 = 12.0f;
        }
        if (z) {
            drawVerticalLine(canvas, f, f3);
            drawVerticalLine(canvas, f2, f3);
            canvas.drawText(startEndTimeFormat + Constants.LINK + startEndTimeFormat2, f4, this.mTimeStrY, paint);
        }
    }

    private void drawVerticalLine(Canvas canvas, float f, float f2) {
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1.0f);
        if (this.mColorType == 1) {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131296741_res_0x7f0901e5));
        } else {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131296834_res_0x7f090242));
        }
        paint.setPathEffect(new DashPathEffect(new float[]{2.0f, 2.0f}, 0.0f));
        Path path = new Path();
        path.moveTo(f, this.mVerticalLineStartY + scn.b(f2));
        path.lineTo(f, this.mTransparentHeight);
        canvas.drawPath(path, paint);
    }

    private String getStartEndTimeFormat(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(jec.d(), jec.a(), 1, i, i2);
        return UnitUtil.a(calendar.getTime(), 1);
    }

    private ArrayList<pvz> getSleepSumList() {
        ArrayList<pvz> arrayList = new ArrayList<>(16);
        for (pvz pvzVar : this.mCopyOfFitnessDataList) {
            if (pvzVar.b() != 64) {
                if (arrayList.size() == 0) {
                    arrayList.add(pvzVar);
                } else {
                    pvz pvzVar2 = arrayList.get(arrayList.size() - 1);
                    if (pvzVar2.b == pvzVar.o) {
                        pvzVar2.b = pvzVar.b;
                    } else {
                        arrayList.add(pvzVar);
                    }
                }
            }
        }
        return arrayList;
    }

    private void setBottomToTopLineEndColor(int i) {
        switch (i) {
            case 65:
                if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296738_res_0x7f0901e2);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296830_res_0x7f09023e);
                    break;
                }
            case 66:
                if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296746_res_0x7f0901ea);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296843_res_0x7f09024b);
                    break;
                }
            case 67:
                if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296755_res_0x7f0901f3);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296862_res_0x7f09025e);
                    break;
                }
            case 68:
                if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296752_res_0x7f0901f0);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296856_res_0x7f090258);
                    break;
                }
            case 69:
                setNoonSleepEndConnectionLineColor();
                break;
        }
    }

    private void setBottomToTopLineStartColor(int i) {
        switch (i) {
            case 65:
                if (this.mColorType == 1) {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296738_res_0x7f0901e2);
                    break;
                } else {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296830_res_0x7f09023e);
                    break;
                }
            case 66:
                if (this.mColorType == 1) {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296745_res_0x7f0901e9);
                    break;
                } else {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296841_res_0x7f090249);
                    break;
                }
            case 67:
                setAwakeSleepStartConnectionLineColor();
                break;
            case 68:
                if (this.mColorType == 1) {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296751_res_0x7f0901ef);
                    break;
                } else {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296854_res_0x7f090256);
                    break;
                }
            case 69:
                setNoonSleepStartConnectionLineColor();
                break;
        }
    }

    private void setAwakeSleepStartConnectionLineColor() {
        if (this.mColorType == 1) {
            this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296755_res_0x7f0901f3);
        } else {
            this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296862_res_0x7f09025e);
        }
    }

    private void setNoonSleepStartConnectionLineColor() {
        if (this.mColorType == 1) {
            this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296737_res_0x7f0901e1);
        } else {
            this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296827_res_0x7f09023b);
        }
    }

    private void setBottomToTopEndColor(int i) {
        switch (i) {
            case 65:
                setDeepSleepGradientColor();
                break;
            case 66:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296718_res_0x7f0901ce);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296720_res_0x7f0901d0);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296839_res_0x7f090247);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298960_res_0x7f090a90);
                    break;
                }
            case 67:
                setAwakeSleepGradientColor();
                break;
            case 68:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296725_res_0x7f0901d5);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296726_res_0x7f0901d6);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296851_res_0x7f090253);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296854_res_0x7f090256);
                    break;
                }
            case 69:
                setNoonSleepGradientColor();
                break;
            default:
                setDefaultLineRectColor();
                break;
        }
    }

    private void setDefaultLineRectColor() {
        this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296971_res_0x7f0902cb);
        this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296971_res_0x7f0902cb);
    }

    private void setAwakeSleepGradientColor() {
        if (this.mColorType == 1) {
            this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296730_res_0x7f0901da);
            this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296731_res_0x7f0901db);
        } else {
            this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296859_res_0x7f09025b);
            this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296861_res_0x7f09025d);
        }
    }

    private void drawConnectionLine(Canvas canvas, float f, float f2, float f3, float f4) {
        Paint paint = new Paint();
        LinearGradient linearGradient = new LinearGradient(f, f2, f, f4, this.mStartConnectionColor, this.mEndConnectionColor, Shader.TileMode.MIRROR);
        paint.setStrokeWidth(1.0f);
        paint.setShader(linearGradient);
        paint.setAntiAlias(true);
        canvas.drawLine(f, f2, f3, f4, paint);
    }

    private void drawLineArc(Canvas canvas, d dVar) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color._2131296657_res_0x7f090191));
        paint.setStrokeWidth(1.0f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(new RectF(dVar.d, dVar.e, dVar.h, dVar.b), dVar.c, dVar.f9968a, true, paint);
    }

    private void setBottomToTopStartColor(int i) {
        switch (i) {
            case 65:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296714_res_0x7f0901ca);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296715_res_0x7f0901cb);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296831_res_0x7f09023f);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296833_res_0x7f090241);
                    break;
                }
            case 66:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296721_res_0x7f0901d1);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296724_res_0x7f0901d4);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296843_res_0x7f09024b);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296849_res_0x7f090251);
                    break;
                }
            case 67:
                setAwakeSleepRoundGradientColor();
                break;
            case 68:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296728_res_0x7f0901d8);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296729_res_0x7f0901d9);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296856_res_0x7f090258);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296857_res_0x7f090259);
                    break;
                }
            case 69:
                setNoonSleepGradientColor();
                break;
            default:
                setDefaultLineRectColor();
                break;
        }
    }

    private void setAwakeSleepRoundGradientColor() {
        if (this.mColorType == 1) {
            this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296731_res_0x7f0901db);
            this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296730_res_0x7f0901da);
        } else {
            this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296861_res_0x7f09025d);
            this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296859_res_0x7f09025b);
        }
    }

    private void setTopToBottomLineEndColor(int i) {
        switch (i) {
            case 65:
                if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296740_res_0x7f0901e4);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296833_res_0x7f090241);
                    break;
                }
            case 66:
                if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296745_res_0x7f0901e9);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296841_res_0x7f090249);
                    break;
                }
            case 67:
                if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296754_res_0x7f0901f2);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296860_res_0x7f09025c);
                    break;
                }
            case 68:
                if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296751_res_0x7f0901ef);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296854_res_0x7f090256);
                    break;
                }
            case 69:
                setNoonSleepEndConnectionLineColor();
                break;
        }
    }

    private void setNoonSleepEndConnectionLineColor() {
        if (this.mColorType == 1) {
            this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296736_res_0x7f0901e0);
        } else {
            this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296823_res_0x7f090237);
        }
    }

    private void setTopToBottomLineStartColor(int i) {
        switch (i) {
            case 65:
                if (this.mColorType == 1) {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296739_res_0x7f0901e3);
                    break;
                } else {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296832_res_0x7f090240);
                    break;
                }
            case 66:
                if (this.mColorType == 1) {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296746_res_0x7f0901ea);
                    break;
                } else {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296843_res_0x7f09024b);
                    break;
                }
            case 67:
                setAwakeSleepStartConnectionLineColor();
                break;
            case 68:
                if (this.mColorType == 1) {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296752_res_0x7f0901f0);
                    break;
                } else {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296856_res_0x7f090258);
                    break;
                }
            case 69:
                setNoonSleepStartConnectionLineColor();
                break;
        }
    }

    private void setTopToBottomEndColor(int i) {
        switch (i) {
            case 65:
                setDeepSleepGradientColor();
                break;
            case 66:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296724_res_0x7f0901d4);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296721_res_0x7f0901d1);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296849_res_0x7f090251);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296843_res_0x7f09024b);
                    break;
                }
            case 67:
                setAwakeSleepGradientColor();
                break;
            case 68:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296729_res_0x7f0901d9);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296728_res_0x7f0901d8);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296857_res_0x7f090259);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296856_res_0x7f090258);
                    break;
                }
            case 69:
                setNoonSleepGradientColor();
                break;
            default:
                setDefaultLineRectColor();
                break;
        }
    }

    private void setNoonSleepGradientColor() {
        if (this.mColorType == 1) {
            this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296712_res_0x7f0901c8);
            this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296711_res_0x7f0901c7);
        } else {
            this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296827_res_0x7f09023b);
            this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296823_res_0x7f090237);
        }
    }

    private void setDeepSleepGradientColor() {
        if (this.mColorType == 1) {
            this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296715_res_0x7f0901cb);
            this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296714_res_0x7f0901ca);
        } else {
            this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296833_res_0x7f090241);
            this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296831_res_0x7f09023f);
        }
    }

    private void drawLineRect(Canvas canvas, float f, float f2, float f3, float f4) {
        Paint paint = new Paint();
        LinearGradient linearGradient = new LinearGradient(f, f3, f, f4, this.mStartLineRectColor, this.mEndLineRectColor, Shader.TileMode.MIRROR);
        paint.setStrokeWidth(1.0f);
        paint.setAntiAlias(true);
        paint.setShader(linearGradient);
        canvas.drawRect(f, f2, f3, f4, paint);
    }

    private void setTopToBottomStartColor(int i) {
        switch (i) {
            case 65:
                setDeepSleepGradientColor();
                break;
            case 66:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296720_res_0x7f0901d0);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296718_res_0x7f0901ce);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296841_res_0x7f090249);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298960_res_0x7f090a90);
                    break;
                }
            case 67:
                setAwakeSleepRoundGradientColor();
                break;
            case 68:
                if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296726_res_0x7f0901d6);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296725_res_0x7f0901d5);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296854_res_0x7f090256);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296851_res_0x7f090253);
                    break;
                }
            case 69:
                setNoonSleepGradientColor();
                break;
            default:
                setDefaultLineRectColor();
                break;
        }
    }

    public c getOnlyNoonRectSetting(int i, float f) {
        float b = scn.b(20.0f);
        float b2 = this.mDiagramHeight - scn.b(20.0f);
        this.mIsOtherType = false;
        c cVar = new c();
        if (i == 67) {
            cVar.c = b;
            cVar.b = b + (b2 * 0.19f);
        } else if (i == 69) {
            cVar.c = scn.b(20.0f) + (b2 * 0.38f);
            cVar.b = (scn.b(20.0f) * 3.0f) + ((f - b) * 0.62f);
        } else {
            this.mIsOtherType = true;
        }
        return cVar;
    }

    private c getCurrentRectSetting(int i, float f) {
        float b = scn.b(6.8f);
        this.mIsOtherType = false;
        c cVar = new c();
        switch (i) {
            case 65:
                float f2 = b + ((f - b) * 0.75f);
                cVar.c = f2;
                cVar.b = f2 + scn.b(25.5f);
                return cVar;
            case 66:
                float f3 = b + ((f - b) * 0.5f);
                cVar.c = f3;
                cVar.b = f3 + scn.b(25.5f);
                return cVar;
            case 67:
                cVar.c = b;
                cVar.b = b + scn.b(25.5f);
                return cVar;
            case 68:
                float f4 = b + ((f - b) * 0.25f);
                cVar.c = f4;
                cVar.b = f4 + scn.b(25.5f);
                return cVar;
            default:
                this.mIsOtherType = true;
                return cVar;
        }
    }

    private c getOrdinaryRectSetting(int i, float f) {
        float b = scn.b(20.0f);
        this.mIsOtherType = false;
        c cVar = new c();
        switch (i) {
            case 65:
                float f2 = b + ((f - b) * 0.66f);
                cVar.c = f2;
                cVar.b = f2 + scn.b(32.0f);
                return cVar;
            case 66:
                float f3 = b + ((f - b) * 0.33f);
                cVar.c = f3;
                cVar.b = f3 + scn.b(32.0f);
                return cVar;
            case 67:
                cVar.c = b;
                cVar.b = b + scn.b(32.0f);
                return cVar;
            default:
                this.mIsOtherType = true;
                return cVar;
        }
    }

    private void setConnectionBlock(Canvas canvas, pvz pvzVar, c cVar, pvz pvzVar2, c cVar2) {
        if (cVar.b - cVar2.b < 0.0f) {
            setTopToBottomConnectionBlock(canvas, pvzVar, cVar, pvzVar2, cVar2);
        } else {
            setBottomToTopConnectionBlock(canvas, pvzVar, cVar, pvzVar2, cVar2);
        }
    }

    private void setBottomToTopConnectionBlock(Canvas canvas, pvz pvzVar, c cVar, pvz pvzVar2, c cVar2) {
        float b;
        float b2;
        float b3 = scn.b(6.0f);
        float b4 = scn.b(1.0f);
        if (cVar.f9967a - cVar.e < b3) {
            b = (cVar.f9967a - cVar.e) / 2.0f;
        } else {
            b = scn.b(3.0f);
        }
        float f = b;
        setBottomToTopStartColor(pvzVar.b());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            drawLineRect(canvas, cVar.e, (cVar.c - f) + b4, cVar.e + f, cVar.c + f);
            drawArc5(canvas, cVar, b4, f);
        } else {
            float f2 = cVar.f9967a;
            drawLineRect(canvas, f2 - f, (cVar.c - f) + b4, cVar.f9967a, cVar.c + f);
            drawArc6(canvas, cVar, b4, f);
        }
        if (cVar2.f9967a - cVar2.e < b3) {
            b2 = (cVar2.f9967a - cVar2.e) / 2.0f;
        } else {
            b2 = scn.b(3.0f);
        }
        float f3 = b2;
        setBottomToTopEndColor(pvzVar2.b());
        setBottomToTopLineStartColor(pvzVar.b());
        setBottomToTopLineEndColor(pvzVar2.b());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f4 = cVar2.f9967a;
            drawLineRect(canvas, f4 - f3, cVar2.b - f3, cVar2.f9967a, cVar2.b + f3);
            drawArc7(canvas, cVar2, b4, f3);
            drawConnectionLine(canvas, cVar.e, (cVar.b - f3) - b4, cVar.e, cVar2.c + f3 + b4);
            return;
        }
        drawLineRect(canvas, cVar2.e, cVar2.b - f3, cVar2.e + f3, (cVar2.b + f3) - b4);
        drawArc8(canvas, cVar2, b4, f3);
        drawConnectionLine(canvas, cVar.f9967a, (cVar.b - f3) - b4, cVar.f9967a, cVar2.c + f3 + b4);
    }

    private void drawArc5(Canvas canvas, c cVar, float f, float f2) {
        d dVar = new d();
        dVar.d = cVar.e;
        float f3 = f2 * 2.0f;
        dVar.e = cVar.c - f3;
        dVar.h = cVar.e + f3 + f;
        dVar.b = cVar.c;
        dVar.c = 180.0f;
        dVar.f9968a = -90.0f;
        drawLineArc(canvas, dVar);
    }

    private void drawArc6(Canvas canvas, c cVar, float f, float f2) {
        d dVar = new d();
        float f3 = f2 * 2.0f;
        dVar.d = cVar.f9967a - (f + f3);
        dVar.e = cVar.c - f3;
        dVar.h = cVar.f9967a;
        dVar.b = cVar.c;
        dVar.c = 0.0f;
        dVar.f9968a = 90.0f;
        drawLineArc(canvas, dVar);
    }

    private void drawArc7(Canvas canvas, c cVar, float f, float f2) {
        d dVar = new d();
        float f3 = f2 * 2.0f;
        dVar.d = (cVar.f9967a - f3) + f;
        dVar.e = cVar.b;
        dVar.h = cVar.f9967a;
        dVar.b = cVar.b + f3;
        dVar.c = 0.0f;
        dVar.f9968a = -90.0f;
        drawLineArc(canvas, dVar);
    }

    private void drawArc8(Canvas canvas, c cVar, float f, float f2) {
        d dVar = new d();
        dVar.d = cVar.e;
        dVar.e = cVar.b;
        float f3 = f2 * 2.0f;
        dVar.h = cVar.e + f + f3;
        dVar.b = cVar.b + f3;
        dVar.c = 180.0f;
        dVar.f9968a = 90.0f;
        drawLineArc(canvas, dVar);
    }

    private void setTopToBottomConnectionBlock(Canvas canvas, pvz pvzVar, c cVar, pvz pvzVar2, c cVar2) {
        float b;
        float b2;
        float b3 = scn.b(6.0f);
        float b4 = scn.b(1.0f);
        if (cVar.f9967a - cVar.e < b3) {
            b = (cVar.f9967a - cVar.e) / 2.0f;
        } else {
            b = scn.b(3.0f);
        }
        float f = b;
        setTopToBottomStartColor(pvzVar.b());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            drawLineRect(canvas, cVar.e, cVar.b - f, cVar.e + f, cVar.b + f);
            drawArc1(canvas, cVar, b4, f);
        } else {
            float f2 = cVar.f9967a;
            drawLineRect(canvas, f2 - f, cVar.b - f, cVar.f9967a, (cVar.b + f) - b4);
            drawArc2(canvas, cVar, b4, f);
        }
        if (cVar2.f9967a - cVar2.e < b3) {
            b2 = (cVar2.f9967a - cVar2.e) / 2.0f;
        } else {
            b2 = scn.b(3.0f);
        }
        float f3 = b2;
        setTopToBottomEndColor(pvzVar2.b());
        setTopToBottomLineStartColor(pvzVar.b());
        setTopToBottomLineEndColor(pvzVar2.b());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f4 = cVar2.f9967a;
            drawLineRect(canvas, f4 - f3, (cVar2.c - f3) + b4, cVar2.f9967a, cVar2.c + f3);
            drawArc3(canvas, cVar2, b4, f3);
            drawConnectionLine(canvas, cVar.e, cVar.c + f3 + b4, cVar.e, (cVar2.b - f3) - b4);
            return;
        }
        drawLineRect(canvas, cVar2.e, (cVar2.c - f3) + b4, cVar2.e + f3, cVar2.c + f3);
        drawArc4(canvas, cVar2, b4, f3);
        drawConnectionLine(canvas, cVar.f9967a, cVar.c + f3 + b4, cVar.f9967a, (cVar2.b - f3) - b4);
    }

    private void drawArc1(Canvas canvas, c cVar, float f, float f2) {
        d dVar = new d();
        dVar.d = cVar.e;
        dVar.e = cVar.b;
        float f3 = f2 * 2.0f;
        dVar.h = cVar.e + f + f3;
        dVar.b = cVar.b + f3;
        dVar.c = 180.0f;
        dVar.f9968a = 90.0f;
        drawLineArc(canvas, dVar);
    }

    private void drawArc2(Canvas canvas, c cVar, float f, float f2) {
        d dVar = new d();
        float f3 = f2 * 2.0f;
        dVar.d = cVar.f9967a - (f + f3);
        dVar.e = cVar.b;
        dVar.h = cVar.f9967a;
        dVar.b = cVar.b + f3;
        dVar.c = 0.0f;
        dVar.f9968a = -90.0f;
        drawLineArc(canvas, dVar);
    }

    private void drawArc3(Canvas canvas, c cVar, float f, float f2) {
        d dVar = new d();
        float f3 = f2 * 2.0f;
        dVar.d = cVar.f9967a - (f + f3);
        dVar.e = cVar.c - f3;
        dVar.h = cVar.f9967a;
        dVar.b = cVar.c;
        dVar.c = 0.0f;
        dVar.f9968a = 90.0f;
        drawLineArc(canvas, dVar);
    }

    private void drawArc4(Canvas canvas, c cVar, float f, float f2) {
        d dVar = new d();
        dVar.d = cVar.e;
        float f3 = f2 * 2.0f;
        dVar.e = cVar.c - f3;
        dVar.h = cVar.e + f3 + f;
        dVar.b = cVar.c;
        dVar.c = 180.0f;
        dVar.f9968a = -90.0f;
        drawLineArc(canvas, dVar);
    }

    private void setCurrentRectColor(int i) {
        if (i == 700) {
            if (this.mColorType == 1) {
                this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296755_res_0x7f0901f3);
                this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296755_res_0x7f0901f3);
                return;
            } else {
                this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296862_res_0x7f09025e);
                this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296862_res_0x7f09025e);
                return;
            }
        }
        if (i != 710) {
            switch (i) {
                case 65:
                    setCurrentRectDeepColor();
                    break;
                case 66:
                    setCurrentRectLightColor();
                    break;
                case 67:
                    setCurrentRectAwakeColor();
                    break;
                case 68:
                    setCurrentRectRemColor();
                    break;
                case 69:
                    if (this.mColorType == 1) {
                        this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296737_res_0x7f0901e1);
                        this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296736_res_0x7f0901e0);
                        break;
                    } else {
                        this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296827_res_0x7f09023b);
                        this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296823_res_0x7f090237);
                        break;
                    }
                default:
                    this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296971_res_0x7f0902cb);
                    this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296971_res_0x7f0902cb);
                    break;
            }
            return;
        }
        if (this.mColorType == 1) {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296736_res_0x7f0901e0);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296736_res_0x7f0901e0);
        } else {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296823_res_0x7f090237);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296823_res_0x7f090237);
        }
    }

    private void setCurrentRectDeepColor() {
        if (this.mColorType == 1) {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296739_res_0x7f0901e3);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296738_res_0x7f0901e2);
        } else {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296832_res_0x7f090240);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296830_res_0x7f09023e);
        }
    }

    private void setCurrentRectLightColor() {
        if (this.mColorType == 1) {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296747_res_0x7f0901eb);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296744_res_0x7f0901e8);
        } else {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296845_res_0x7f09024d);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131298960_res_0x7f090a90);
        }
    }

    private void setCurrentRectRemColor() {
        if (this.mColorType == 1) {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296753_res_0x7f0901f1);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296750_res_0x7f0901ee);
        } else {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296858_res_0x7f09025a);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296852_res_0x7f090254);
        }
    }

    private void setCurrentRectAwakeColor() {
        if (this.mColorType == 1) {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296755_res_0x7f0901f3);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296754_res_0x7f0901f2);
        } else {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296862_res_0x7f09025e);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296860_res_0x7f09025c);
        }
    }

    private void drawCyRect(Canvas canvas, c cVar) {
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(cVar.e, cVar.c, cVar.e, cVar.b, this.mStartCyColor, this.mEndCyColor, Shader.TileMode.MIRROR));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1.0f);
        canvas.drawRoundRect(new RectF(cVar.e, cVar.c, cVar.f9967a, cVar.b), scn.b(3.0f), scn.b(3.0f), paint);
    }

    private pvz getMaxSleepData(ArrayList<pvz> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        pvz pvzVar = arrayList.get(0);
        Iterator<pvz> it = arrayList.iterator();
        while (it.hasNext()) {
            pvz next = it.next();
            if (next.b - next.o > pvzVar.b - pvzVar.o) {
                pvzVar = next;
            }
        }
        return pvzVar;
    }

    private void getTheLastData(pvz pvzVar) {
        boolean z = pvzVar.b() == 67 || pvzVar.b() == 68;
        boolean z2 = pvzVar.b() == 66 || pvzVar.b() == 69 || pvzVar.b() == 65;
        if (z || z2) {
            this.mLastData = pvzVar;
        } else {
            this.mLastData = null;
        }
    }

    private String dateToString(Date date) {
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "MM/dd")).format(date);
    }

    public String formatTime(String str) {
        try {
            return UnitUtil.a(new SimpleDateFormat("HH:mm").parse(str), 129);
        } catch (ParseException unused) {
            LogUtil.a(TAG, "formatTime.ParseException");
            return str;
        }
    }

    public String getTimeStr(int i) {
        if (i >= 10) {
            return Integer.toString(i);
        }
        return "0" + Integer.toString(i);
    }

    private List<String> getChartLabels() {
        ArrayList arrayList = new ArrayList(5);
        Calendar calendar = Calendar.getInstance();
        calendar.set(jec.d(), jec.a(), 1, 20, 0);
        arrayList.add(UnitUtil.a(calendar.getTime(), 129));
        calendar.set(jec.d(), jec.a(), 1, 2, 0);
        arrayList.add(UnitUtil.a(calendar.getTime(), 129));
        calendar.set(jec.d(), jec.a(), 1, 8, 0);
        arrayList.add(UnitUtil.a(calendar.getTime(), 129));
        calendar.set(jec.d(), jec.a(), 1, 14, 0);
        arrayList.add(UnitUtil.a(calendar.getTime(), 129));
        calendar.set(jec.d(), jec.a(), 1, 20, 0);
        arrayList.add(UnitUtil.a(calendar.getTime(), 129));
        return arrayList;
    }
}
