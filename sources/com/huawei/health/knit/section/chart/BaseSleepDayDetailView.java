package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.ViewCompat;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.accessibility.AbstractTouchNode;
import com.huawei.hwcommonmodel.accessibility.ChartTouchHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.ecc;
import defpackage.ecu;
import defpackage.een;
import defpackage.jcf;
import defpackage.jco;
import defpackage.jdl;
import defpackage.jec;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class BaseSleepDayDetailView extends View {
    private static final int ARRAY_COLLECTION_FOUR_INDEX = 2;
    private static final int ARRAY_COLLECTION_TWO_INDEX = 2;
    private static final String COLON = ":";
    public static final int DATA_TOP = 24;
    private static final int DEFALUT_TIME_VALUE = -1;
    private static final int END_POINT = 1440;
    public static final int FIRST_BED_AND_SLEEP = 2;
    public static final int FIRST_BED_AND_SLEEP_AND_BED = 3;
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
    private static final float HALF_PAST_ONE = 1.5f;
    public static final int HOUR_IN_DAY = 24;
    public static final int LAST_BED_AND_SLEEP_AND_BED = 5;
    public static final int LAST_SLEEP_AND_BED = 4;
    public static final float MINS_IN_DAY = 1440.0f;
    public static final int MINS_IN_HOUR = 60;
    private static final float PRECISION = 1.0E-7f;
    private static final float RTL_EXTRA_BOTTOM_TO_TOP = 2.0f;
    private static final float RTL_EXTRA_LEFT = 4.2f;
    private static final float RTL_EXTRA_TOP_TO_BOTTOM = 2.0f;
    public static final int SLEEP_CHART_COLOR_TYPE = 1;
    public static final int SLEEP_START_END_HOUR = 20;
    private static final String TAG = "BaseSleepDayDetailView";
    private static final String TAG_RELEASE = "R_Sleep_BaseSleepDayDetailView";
    private static final int THREE_TIMES = 3;
    private static final int TWO_TIMES_INT = 2;
    private static final int ZERO_POINT = 0;
    private final c mAccessibilityListerner;
    private String mBedDate;
    private float mBedSleepStartX;
    private String mBedText;
    protected Canvas mCanvas;
    private float mChartBorderWidth;
    private List<String> mChartLabels;
    private int mColorType;
    private Context mContext;
    private List<ecu> mCopyOfFitnessDataList;
    private float mCoreSleepEndX;
    private float mCoreSleepStartX;
    private float mCurrentBottom;
    private ecu mCurrentData;
    private Date mCurrentDay;
    private float mCursorCenter;
    private float mCursorHeight;
    protected int mCursorInBedState;
    private ArrayList<ecc> mCursorList;
    private int mCursorSleep;
    private long mCursorSleepTime;
    private float mCursorTextHeight;
    private int mCursorWake;
    private long mCursorWakeTime;
    private String[] mDate;
    private float mDiagramHeight;
    private float mDiagramWidth;
    private int mEndConnectionColor;
    private int mEndCyColor;
    private ecu mEndData;
    private String mEndDate;
    private int mEndHour;
    private int mEndLineRectColor;
    private int mEndMin;
    private String mEndText;
    private long mFallAsleepTime;
    private List<ecu> mFitnessDataList;
    private long mGoBedTime;
    private ChartTouchHelper mHelper;
    private boolean mIsCommonSleep;
    private boolean mIsDayDetailView;
    private boolean mIsDefault;
    protected boolean mIsManual;
    private boolean mIsManualAndIncomplete;
    protected boolean mIsManualAndOnlyBed;
    private boolean mIsMove;
    private boolean mIsMultiSleep;
    private boolean mIsOnlyNoonSleepType;
    private boolean mIsOtherType;
    protected boolean mIsPhone;
    private boolean mIsScienceSleep;
    private boolean mIsSupportBedTime;
    private ecu mLastData;
    private int mLastNightEndPoint;
    private ArrayList<ecu> mManualCursorList;
    private int mNightEndPoint;
    private int mNightStartPoint;
    private long mOffBedTime;
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
    private String mUpDate;
    private float mUpSleepEndX;
    private String mUpText;
    private float mVerticalLineStartY;
    private float mViewHeight;
    private float mViewWidth;
    private long mWakeUpTime;
    private static final float XAXIS_TEXT_OFFSET_DEFAULT = een.e(8.0f);
    private static final float XAXIS_TEXT_OFFSET_TAHITI = een.e(16.0f);
    private static final float CURSOR_TEXT_MINIMUM_HEIGHT = een.e(4.0f);
    private static final float CURSOR_TEXT_HIGHEST_HEIGHT = een.e(11.5f);
    private static final float CURSOR_TEXT_FLOATING_OFFSET = een.e(19.0f);
    private static final float CURSOR_WIDTH_OFFSET = een.e(36.0f);

    public BaseSleepDayDetailView(Context context) {
        super(context);
        this.mRemSleepTimes = 0;
        this.mNightStartPoint = 0;
        this.mNightEndPoint = 0;
        this.mLastNightEndPoint = 0;
        this.mStartSleepPoint = 0;
        this.mWakeUpTime = 0L;
        this.mFallAsleepTime = 0L;
        this.mGoBedTime = 0L;
        this.mOffBedTime = 0L;
        this.mCoreSleepStartX = 0.0f;
        this.mCoreSleepEndX = 0.0f;
        this.mBedSleepStartX = 0.0f;
        this.mUpSleepEndX = 0.0f;
        this.mStartText = "";
        this.mBedText = "";
        this.mEndText = "";
        this.mUpText = "";
        this.mTimeStringMargin = een.e(2.0f);
        this.mChartBorderWidth = een.e(24.0f);
        this.mTimeStrY = 45.0f;
        this.mVerticalLineStartY = 20.0f;
        this.mColorType = 0;
        this.mIsDayDetailView = true;
        this.mIsScienceSleep = false;
        this.mIsCommonSleep = false;
        this.mIsDefault = true;
        this.mIsMove = false;
        this.mIsSupportBedTime = false;
        this.mIsMultiSleep = false;
        this.mFitnessDataList = new ArrayList(16);
        this.mCopyOfFitnessDataList = new ArrayList(16);
        this.mCursorList = new ArrayList<>(16);
        this.mManualCursorList = new ArrayList<>(16);
        this.mChartLabels = new ArrayList(16);
        this.mIsOtherType = false;
        this.mCurrentBottom = 0.0f;
        this.mCursorSleep = -1;
        this.mCursorWake = -1;
        this.mCursorSleepTime = -1L;
        this.mCursorWakeTime = -1L;
        this.mCursorInBedState = -1;
        this.mAccessibilityListerner = new c(this);
        this.mContext = context;
    }

    public BaseSleepDayDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRemSleepTimes = 0;
        this.mNightStartPoint = 0;
        this.mNightEndPoint = 0;
        this.mLastNightEndPoint = 0;
        this.mStartSleepPoint = 0;
        this.mWakeUpTime = 0L;
        this.mFallAsleepTime = 0L;
        this.mGoBedTime = 0L;
        this.mOffBedTime = 0L;
        this.mCoreSleepStartX = 0.0f;
        this.mCoreSleepEndX = 0.0f;
        this.mBedSleepStartX = 0.0f;
        this.mUpSleepEndX = 0.0f;
        this.mStartText = "";
        this.mBedText = "";
        this.mEndText = "";
        this.mUpText = "";
        this.mTimeStringMargin = een.e(2.0f);
        this.mChartBorderWidth = een.e(24.0f);
        this.mTimeStrY = 45.0f;
        this.mVerticalLineStartY = 20.0f;
        this.mColorType = 0;
        this.mIsDayDetailView = true;
        this.mIsScienceSleep = false;
        this.mIsCommonSleep = false;
        this.mIsDefault = true;
        this.mIsMove = false;
        this.mIsSupportBedTime = false;
        this.mIsMultiSleep = false;
        this.mFitnessDataList = new ArrayList(16);
        this.mCopyOfFitnessDataList = new ArrayList(16);
        this.mCursorList = new ArrayList<>(16);
        this.mManualCursorList = new ArrayList<>(16);
        this.mChartLabels = new ArrayList(16);
        this.mIsOtherType = false;
        this.mCurrentBottom = 0.0f;
        this.mCursorSleep = -1;
        this.mCursorWake = -1;
        this.mCursorSleepTime = -1L;
        this.mCursorWakeTime = -1L;
        this.mCursorInBedState = -1;
        this.mAccessibilityListerner = new c(this);
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

    public List<ecu> getFitnessDataList() {
        return this.mFitnessDataList;
    }

    public ArrayList<ecc> getCursorList() {
        return this.mCursorList;
    }

    public ArrayList<ecu> getManualCursorList() {
        return this.mManualCursorList;
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

    public ecu getLastData() {
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

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private float f2598a = 0.0f;
        private float c = 0.0f;
        private float e = 0.0f;
        private float d = 0.0f;

        public float a() {
            return this.f2598a;
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private float f2596a;
        private float b;
        private float c;
        private float d;
        private float e;
        private float f;

        private a() {
            this.f2596a = 0.0f;
            this.c = 0.0f;
            this.f = 0.0f;
            this.e = 0.0f;
            this.b = 0.0f;
            this.d = 0.0f;
        }
    }

    public void isSupportBedTime(boolean z) {
        this.mIsSupportBedTime = z;
    }

    public void refreshFitnessDataList(List<ecu> list, boolean z, double d, boolean z2, Date date, boolean z3, boolean z4, boolean z5) {
        LogUtil.a(TAG, "Enter refreshCoreFitness validData:", Double.valueOf(d));
        LogUtil.a(TAG, "refreshFitnessDataList" + list);
        this.mIsPhone = z3;
        this.mIsManual = z4;
        this.mIsCommonSleep = false;
        this.mIsManualAndOnlyBed = true;
        this.mIsManualAndIncomplete = false;
        if (z4) {
            Iterator<ecu> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().d() == 66) {
                    this.mIsManualAndOnlyBed = false;
                    break;
                }
            }
            if (z5) {
                Iterator<ecu> it2 = list.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (it2.next().a() == 1440) {
                        this.mIsManualAndIncomplete = true;
                        break;
                    }
                }
            }
        } else {
            this.mIsManualAndOnlyBed = false;
        }
        if (this.mIsDayDetailView) {
            this.mChartBorderWidth = een.e(24.0f);
        } else {
            this.mChartBorderWidth = een.e(36.0f);
        }
        if (date != null) {
            this.mCurrentDay = (Date) date.clone();
        } else {
            LogUtil.b(TAG_RELEASE, "refreshFitnessDataList mCurrentDay is null ");
            this.mCurrentDay = new Date(System.currentTimeMillis());
        }
        this.mIsScienceSleep = z;
        if (list == null || list.size() == 0) {
            this.mIsScienceSleep = false;
        }
        this.mIsOnlyNoonSleepType = z2;
        initData();
        if (list != null) {
            LogUtil.b(TAG, "refreshCoreFitnessDataList ", list.toString());
        }
        if (list != null && list.size() > 0) {
            this.mFitnessDataList.clear();
            this.mFitnessDataList.addAll(list);
            copyOfFitnessDataList();
            if (!this.mIsScienceSleep && !this.mIsManual && !this.mIsPhone) {
                this.mIsCommonSleep = true;
            }
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
        List<ecu> list = this.mFitnessDataList;
        if (list == null) {
            return;
        }
        this.mCopyOfFitnessDataList.addAll(list);
        Iterator<ecu> it = this.mFitnessDataList.iterator();
        while (it.hasNext()) {
            getSleepDataForUi(it.next());
        }
        Map<String, Integer> sleepSegmentedMaps = getSleepSegmentedMaps(this.mFitnessDataList, this.mIsPhone);
        if (sleepSegmentedMaps == null) {
            LogUtil.b(TAG_RELEASE, "timeMap is null!");
            return;
        }
        this.mStartSleepPoint = sleepSegmentedMaps.get("core_sleep_start_time_key").intValue();
        this.mLastNightEndPoint = sleepSegmentedMaps.get("core_sleep_end_time_key").intValue();
        if (this.mFitnessDataList.size() == 0) {
            return;
        }
        calStartPoint();
        calEndPoint();
    }

    private void calStartPoint() {
        this.mGoBedTime = -1L;
        this.mStartSleepPoint = -1;
        for (ecu ecuVar : this.mFitnessDataList) {
            if (ecuVar.g != 69 && this.mGoBedTime == -1) {
                this.mGoBedTime = ecuVar.e();
            }
            if (ecuVar.g != 69 && ecuVar.g != 67 && this.mStartSleepPoint == -1) {
                this.mStartSleepPoint = ecuVar.h;
                this.mFallAsleepTime = ecuVar.e();
            }
            if (this.mGoBedTime != -1 && this.mStartSleepPoint != -1) {
                break;
            }
        }
        LogUtil.a(TAG, " copyOfFitnessDataList mStartSleepPoint = ", Integer.valueOf(this.mStartSleepPoint));
    }

    private void calEndPoint() {
        this.mLastNightEndPoint = -1;
        this.mOffBedTime = -1L;
        for (int size = this.mFitnessDataList.size() - 1; size >= 0; size--) {
            ecu ecuVar = this.mFitnessDataList.get(size);
            if (ecuVar.g != 69 && this.mOffBedTime == -1) {
                this.mOffBedTime = ecuVar.b();
            }
            if (ecuVar.g != 69 && ecuVar.g != 67 && this.mLastNightEndPoint == -1) {
                this.mWakeUpTime = ecuVar.b();
                this.mLastNightEndPoint = ecuVar.f11956a;
            }
            if (this.mOffBedTime != -1 && this.mLastNightEndPoint != -1) {
                break;
            }
        }
        LogUtil.a(TAG, " copyOfFitnessDataList mNightEndPoint = ", Integer.valueOf(this.mLastNightEndPoint));
    }

    public static Map<String, Integer> getSleepSegmentedMaps(List<ecu> list, boolean z) {
        ecu fitnessKnitHistogramData;
        if (list == null) {
            return new HashMap(16);
        }
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(16);
        Map hashMap = new HashMap(16);
        for (int i = 0; i < arrayList.size() && (fitnessKnitHistogramData = getFitnessKnitHistogramData(arrayList, arrayList2, hashMap, i)) != null; i++) {
            hashMap = getStringIntegerMap(arrayList, fitnessKnitHistogramData, arrayList2, hashMap, i);
        }
        if (z) {
            return getPhoneMaxSleepData(arrayList2);
        }
        return getMaxSleepData((List<Map<String, Integer>>) arrayList2);
    }

    private static ecu getFitnessKnitHistogramData(List<ecu> list, List<Map<String, Integer>> list2, Map<String, Integer> map, int i) {
        ecu ecuVar = list.get(i);
        if (ecuVar == null) {
            return null;
        }
        if (map.get("core_sleep_start_time_key") == null && ecuVar.d() != 69 && ecuVar.d() != 67) {
            map.put("core_sleep_start_time_key", Integer.valueOf(ecuVar.c()));
        }
        if (i == list.size() - 1) {
            if (list.size() == 1) {
                map.put("core_sleep_end_time_key", Integer.valueOf(ecuVar.a()));
            }
            if (map.get("core_sleep_start_time_key") != null && map.get("core_sleep_end_time_key") != null) {
                list2.add(map);
            }
        }
        if (list.size() <= i + 1) {
            return null;
        }
        return ecuVar;
    }

    private static Map<String, Integer> getStringIntegerMap(List<ecu> list, ecu ecuVar, List<Map<String, Integer>> list2, Map<String, Integer> map, int i) {
        int i2 = i + 1;
        ecu ecuVar2 = !koq.b(list, i2) ? list.get(i2) : null;
        if (ecuVar2 == null) {
            return map;
        }
        if (ecuVar2.c() - ecuVar.a() > 30 && ecuVar.d() != 69) {
            map.put("core_sleep_end_time_key", Integer.valueOf(ecuVar.a()));
            if (map.get("core_sleep_start_time_key") == null || map.get("core_sleep_end_time_key") == null) {
                return map;
            }
            list2.add(map);
            return new HashMap(16);
        }
        if (ecuVar2.d() != 69) {
            map.put("core_sleep_end_time_key", Integer.valueOf(ecuVar2.a()));
            return map;
        }
        LogUtil.h(TAG, "getSleepSegmentedMaps takeHistogramHeight = 69, i = ", Integer.valueOf(i));
        return map;
    }

    private static Map<String, Integer> getPhoneMaxSleepData(List<Map<String, Integer>> list) {
        HashMap hashMap = new HashMap(16);
        if (koq.b(list)) {
            return null;
        }
        int i = 0;
        Map<String, Integer> map = list.get(0);
        Map<String, Integer> map2 = list.get(list.size() - 1);
        int i2 = Integer.MAX_VALUE;
        for (Map<String, Integer> map3 : list) {
            if (map3.get("core_sleep_end_time_key").intValue() > i) {
                i = map3.get("core_sleep_end_time_key").intValue();
            }
            if (map3.get("core_sleep_start_time_key").intValue() < i2) {
                i2 = map3.get("core_sleep_start_time_key").intValue();
            }
        }
        hashMap.put("core_sleep_start_time_key", map.get("core_sleep_start_time_key"));
        hashMap.put("core_sleep_end_time_key", map2.get("core_sleep_end_time_key"));
        return hashMap;
    }

    private static Map<String, Integer> getMaxSleepData(List<Map<String, Integer>> list) {
        HashMap hashMap = new HashMap(16);
        if (koq.b(list)) {
            return null;
        }
        Map<String, Integer> map = list.get(0);
        for (Map<String, Integer> map2 : list) {
            if (map2.get("core_sleep_end_time_key").intValue() - map2.get("core_sleep_start_time_key").intValue() > map.get("core_sleep_end_time_key").intValue() - map.get("core_sleep_start_time_key").intValue()) {
                map = map2;
            }
        }
        hashMap.put("core_sleep_start_time_key", map.get("core_sleep_start_time_key"));
        hashMap.put("core_sleep_end_time_key", map.get("core_sleep_end_time_key"));
        return hashMap;
    }

    private void getSleepDataForUi(ecu ecuVar) {
        if (ecuVar == null) {
            LogUtil.b(TAG, "getSleepDataForUi data is null");
            return;
        }
        if (ecuVar.d() == 68) {
            this.mRemSleepTimes += ecuVar.f11956a - ecuVar.h;
        }
        if (ecuVar.d() != 69) {
            if (this.mNightStartPoint == 0 && this.mNightEndPoint == 0) {
                this.mNightStartPoint = ecuVar.c();
            }
            this.mNightEndPoint = ecuVar.a();
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
        paint.setTextSize(een.e(10.0f));
        paint.setAntiAlias(true);
        this.mDate = new String[2];
        if (this.mCurrentDay == null) {
            LogUtil.b(TAG_RELEASE, "getTimeLabel mCurrentDay is null ");
            this.mCurrentDay = new Date(System.currentTimeMillis());
        }
        if ((this.mIsScienceSleep && !this.mIsOnlyNoonSleepType) || ((this.mIsPhone && !this.mIsOnlyNoonSleepType) || this.mIsCommonSleep)) {
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
        this.mStartDate = dateToString(new Date(jdl.v(this.mCurrentDay.getTime())));
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
        float e2;
        float e3;
        if (this.mIsManual) {
            this.mStartSleepPoint = this.mNightStartPoint;
            this.mLastNightEndPoint = this.mNightEndPoint;
            Iterator<ecu> it = this.mFitnessDataList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ecu next = it.next();
                if (next.d() == 66) {
                    this.mCursorSleep = next.c();
                    this.mCursorWake = next.a();
                    this.mCursorSleepTime = next.e();
                    this.mCursorWakeTime = next.b();
                    break;
                }
            }
            getManualBedUpLabel(paint);
        }
        LogUtil.a(TAG, "mStartSleepPoint: ", Integer.valueOf(this.mStartSleepPoint), " mLastNightEndPoint:", Integer.valueOf(this.mLastNightEndPoint), " mStartPoint:", Integer.valueOf(this.mNightStartPoint), " mEndPoint:", Integer.valueOf(this.mNightEndPoint));
        this.mTime = new String[2];
        setStartSleepText();
        String str = this.mStartText;
        float measureText = (int) paint.measureText(str, 0, str.length());
        float f = 0.0f;
        if (this.mNightEndPoint != this.mNightStartPoint) {
            e2 = ((((this.mStartSleepPoint - r3) / (r1 - r3)) * (this.mViewWidth - een.e(48.0f))) + een.e(24.0f)) - (measureText / 2.0f);
            if (e2 < 0.0f) {
                e2 = een.e(16.0f);
            }
        } else {
            e2 = een.e(24.0f) - (measureText / 2.0f);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            e2 = (this.mViewWidth - measureText) - e2;
            float e4 = een.e(16.0f);
            float f2 = this.mViewWidth;
            if (e2 + measureText + e4 > f2) {
                e2 = (f2 - measureText) - een.e(16.0f);
            }
        }
        this.mCoreSleepStartX = e2;
        setEndSleepText();
        String str2 = this.mEndText;
        float measureText2 = (int) paint.measureText(str2, 0, str2.length());
        if (this.mNightEndPoint != this.mNightStartPoint) {
            e3 = ((((this.mLastNightEndPoint - r2) / (r1 - r2)) * (this.mViewWidth - een.e(48.0f))) + een.e(24.0f)) - (measureText2 / 2.0f);
            float e5 = een.e(16.0f);
            float f3 = this.mViewWidth;
            if (e3 + measureText2 + e5 > f3) {
                e3 = (f3 - measureText2) - een.e(16.0f);
            }
        } else {
            e3 = (this.mViewWidth - measureText2) - een.e(16.0f);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f4 = (this.mViewWidth - e3) - measureText2;
            if (f4 >= 0.0f) {
                f = f4;
            }
        } else {
            f = e3;
        }
        this.mCoreSleepEndX = f;
        float e6 = ((measureText + measureText2) / 2.0f) + een.e(16.0f);
        if (Math.abs(this.mCoreSleepEndX - this.mCoreSleepStartX) <= e6) {
            if (this.mCoreSleepEndX + measureText2 + een.e(16.0f) > this.mViewWidth) {
                float f5 = this.mCoreSleepStartX;
                this.mCoreSleepStartX = f5 - (e6 - (this.mCoreSleepEndX - f5));
            } else {
                float f6 = this.mCoreSleepEndX;
                this.mCoreSleepEndX = f6 + (e6 - (f6 - this.mCoreSleepStartX));
            }
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f7 = this.mCoreSleepEndX;
            this.mCoreSleepEndX = this.mCoreSleepStartX;
            this.mCoreSleepStartX = f7;
        }
        dealEndTimeIfStartGreater();
        replaceTextAndDate();
    }

    private void getManualBedUpLabel(Paint paint) {
        float e2;
        float e3;
        LogUtil.a(TAG, "mCursorSleep: ", Integer.valueOf(this.mCursorSleep), " mCursorWake:", Integer.valueOf(this.mCursorWake));
        setBedText();
        String str = this.mBedText;
        float measureText = (int) paint.measureText(str, 0, str.length());
        float f = 0.0f;
        if (this.mNightEndPoint != this.mNightStartPoint) {
            e2 = ((((this.mCursorSleep - r3) / (r1 - r3)) * (this.mViewWidth - een.e(48.0f))) + een.e(24.0f)) - (measureText / 2.0f);
            if (e2 < 0.0f) {
                e2 = een.e(16.0f);
            }
        } else {
            e2 = een.e(24.0f) - (measureText / 2.0f);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            e2 = (this.mViewWidth - measureText) - e2;
            float e4 = een.e(16.0f);
            float f2 = this.mViewWidth;
            if (e2 + measureText + e4 > f2) {
                e2 = (f2 - measureText) - een.e(16.0f);
            }
        }
        this.mBedSleepStartX = e2;
        setUpText();
        String str2 = this.mUpText;
        float measureText2 = (int) paint.measureText(str2, 0, str2.length());
        if (this.mNightEndPoint != this.mNightStartPoint) {
            e3 = ((((this.mCursorWake - r2) / (r1 - r2)) * (this.mViewWidth - een.e(48.0f))) + een.e(24.0f)) - (measureText2 / 2.0f);
            float e5 = een.e(16.0f);
            float f3 = this.mViewWidth;
            if (e3 + measureText2 + e5 > f3) {
                e3 = (f3 - measureText2) - een.e(16.0f);
            }
        } else {
            e3 = (this.mViewWidth - measureText2) - een.e(16.0f);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f4 = (this.mViewWidth - e3) - measureText2;
            if (f4 >= 0.0f) {
                f = f4;
            }
        } else {
            f = e3;
        }
        this.mUpSleepEndX = f;
        float e6 = ((measureText + measureText2) / 2.0f) + een.e(16.0f);
        if (Math.abs(this.mUpSleepEndX - this.mBedSleepStartX) <= e6) {
            if (this.mUpSleepEndX + measureText2 + een.e(16.0f) > this.mViewWidth) {
                float f5 = this.mBedSleepStartX;
                this.mBedSleepStartX = f5 - ((e6 - Math.abs(this.mUpSleepEndX - f5)) + een.e(16.0f));
            } else {
                float f6 = this.mUpSleepEndX;
                this.mUpSleepEndX = f6 + (e6 - Math.abs(f6 - this.mBedSleepStartX)) + een.e(16.0f);
            }
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f7 = this.mUpSleepEndX;
            this.mUpSleepEndX = this.mBedSleepStartX;
            this.mBedSleepStartX = f7;
        }
        dealManualEndTimeIfStartGreater();
    }

    private void dealManualEndTimeIfStartGreater() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
            Date parse = simpleDateFormat.parse(this.mBedDate);
            Date parse2 = simpleDateFormat.parse(this.mUpDate);
            if (parse == null || parse2 == null || parse.getTime() <= parse2.getTime()) {
                return;
            }
            this.mUpDate = this.mBedDate;
            LogUtil.h(TAG, "startTime = ", Long.valueOf(parse.getTime()), " endTime = ", Long.valueOf(parse2.getTime()));
        } catch (ParseException unused) {
            LogUtil.b(TAG_RELEASE, "simpleDateFormat ParseException ");
        }
    }

    private void setUpText() {
        int[] hourAndMin = getHourAndMin(this.mCursorWakeTime);
        int i = hourAndMin[0];
        int i2 = hourAndMin[1];
        if (i > 20 && i < 24) {
            this.mUpDate = dateToString(new Date(jdl.v(this.mCurrentDay.getTime())));
        } else {
            this.mUpDate = dateToString(this.mCurrentDay);
        }
        this.mUpText = formatTime(getTimeStr(i) + ":" + getTimeStr(i2));
        if (!LanguageUtil.j(BaseApplication.getContext())) {
            this.mUpText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_end_sleep) + " " + this.mUpText;
            return;
        }
        this.mUpText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_end_sleep) + this.mUpText;
    }

    private void setBedText() {
        int[] hourAndMin = getHourAndMin(this.mCursorSleepTime);
        int i = hourAndMin[0];
        int i2 = hourAndMin[1];
        if (i >= 20 && i < 24) {
            this.mBedDate = dateToString(new Date(jdl.v(this.mCurrentDay.getTime())));
        } else {
            this.mBedDate = dateToString(this.mCurrentDay);
        }
        this.mBedText = formatTime(getTimeStr(i) + ":" + getTimeStr(i2));
        if (!LanguageUtil.j(BaseApplication.getContext())) {
            this.mBedText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_start_sleep) + " " + this.mBedText;
            return;
        }
        this.mBedText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_start_sleep) + this.mBedText;
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
        int[] hourAndMin = getHourAndMin(this.mFallAsleepTime);
        int i = hourAndMin[0];
        int i2 = hourAndMin[1];
        if (i2 == 0 && i == 20) {
            this.mStartDate = "";
            this.mStartText = "";
            return;
        }
        if (i >= 20 && i < 24) {
            this.mStartDate = dateToString(new Date(jdl.v(this.mCurrentDay.getTime())));
        } else {
            this.mStartDate = dateToString(this.mCurrentDay);
        }
        this.mStartText = formatTime(getTimeStr(i) + ":" + getTimeStr(i2));
        if (!LanguageUtil.j(BaseApplication.getContext())) {
            this.mStartText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_start_sleep) + " " + this.mStartText;
            return;
        }
        this.mStartText = this.mContext.getResources().getString(R$string.IDS_fitness_core_sleep_start_sleep) + this.mStartText;
    }

    private void setEndSleepText() {
        int[] hourAndMin = getHourAndMin(this.mWakeUpTime);
        int i = hourAndMin[0];
        int i2 = hourAndMin[1];
        if (i2 == 0 && i == 20) {
            this.mEndDate = "";
            this.mEndText = "";
            return;
        }
        if (i == 20 && i2 == 0) {
            this.mEndDate = dateToString(this.mCurrentDay);
        } else if (i >= 20 && i < 24) {
            this.mEndDate = dateToString(new Date(jdl.v(this.mCurrentDay.getTime())));
        } else {
            this.mEndDate = dateToString(this.mCurrentDay);
        }
        this.mEndText = formatTime(getTimeStr(i) + ":" + getTimeStr(i2));
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
        List<ecu> list = this.mFitnessDataList;
        if (list == null || list.size() == 0) {
            this.mLastData = null;
            return;
        }
        ChartTouchHelper chartTouchHelper = this.mHelper;
        if (chartTouchHelper != null) {
            chartTouchHelper.e().d();
        }
        this.mCurrentBottom = this.mDiagramHeight;
        int size = this.mFitnessDataList.size();
        this.mIsMultiSleep = false;
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            ecu ecuVar = this.mFitnessDataList.get(i2);
            this.mCurrentData = ecuVar;
            int c2 = ecuVar.c();
            int a2 = this.mCurrentData.a();
            if (this.mIsOnlyNoonSleepType) {
                drawOnlyNoonSleepRect(canvas, c2, a2, size, i2);
            } else if (this.mCurrentData.d() != 69) {
                if (i != -1 && i2 - i != 1) {
                    this.mIsMultiSleep = true;
                }
                drawCoreSleepRect(canvas, i2);
                i = i2;
            }
        }
    }

    private void setAccsibilityVirtualNode(ecu ecuVar, e eVar, AbstractTouchNode abstractTouchNode) {
        abstractTouchNode.setDescription(getAccessibilityDesription(ecuVar));
        abstractTouchNode.setRect(getAccessibilityRect(eVar));
    }

    private String getAccessibilityDesription(ecu ecuVar) {
        String b;
        String b2 = nsf.b(R$string.IDS_hw_health_coresleep_standard_range_1, getDigitTimeStr(ecuVar.c()), getDigitTimeStr(ecuVar.a()));
        String sleepTypeString = getSleepTypeString(ecuVar.d());
        int a2 = ecuVar.a() - ecuVar.c();
        int i = a2 / 60;
        int i2 = a2 % 60;
        if (i == 0) {
            b = nsf.b(R$string.IDS_hw_show_set_target_sport_time_unit, Integer.valueOf(i2));
        } else {
            b = nsf.b(R$string.IDS_h_min_unit, Integer.valueOf(i), Integer.valueOf(i2));
        }
        return nsf.b(R$string.IDS_two_parts, nsf.b(R$string.IDS_two_parts, b2, sleepTypeString), b);
    }

    private Rect getAccessibilityRect(e eVar) {
        return new Rect((int) eVar.e, (int) eVar.f2598a, (int) eVar.d, (int) eVar.c);
    }

    protected String getSleepTypeString(int i) {
        switch (i) {
            case 65:
                return getBaseContext().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_deepsleep);
            case 66:
                return getBaseContext().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep);
            case 67:
                return getBaseContext().getString(R$string.IDS_details_sleep_sleep_latency);
            case 68:
                return getBaseContext().getString(R$string.IDS_fitness_core_sleep_rem_sleep);
            case 69:
                return getBaseContext().getString(R$string.IDS_fitness_core_sleep_noontime_sleep);
            default:
                return getBaseContext().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep);
        }
    }

    protected String getDigitTimeStr(int i) {
        int i2 = i / 60;
        int i3 = i2 + 20;
        if (i3 >= 24) {
            i3 = i2 - 4;
        }
        return formatTime(getTimeStr(i3) + ":" + getTimeStr(i % 60));
    }

    private void drawOnlyNoonSleepRect(Canvas canvas, int i, int i2, int i3, int i4) {
        if (canvas == null) {
            return;
        }
        e eVar = new e();
        e eVar2 = new e();
        setCurrentValueData(eVar, getOnlyNoonRectSetting(this.mCurrentData.d(), this.mCurrentBottom), i, i2);
        setRtlPosition(eVar);
        getTheLastData(this.mCurrentData);
        int i5 = i4 + 1;
        if (i5 < i3) {
            this.mEndData = this.mFitnessDataList.get(i5);
            eVar2.e = ((i / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
            eVar2.d = ((i2 / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
            boolean z = false;
            boolean z2 = Math.abs(eVar.d - eVar2.e) < PRECISION;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                float f = eVar2.e;
                eVar2.e = this.mViewWidth - eVar2.d;
                eVar2.d = this.mViewWidth - f;
                if (Math.abs(eVar.e - eVar2.d) < PRECISION) {
                    z = true;
                }
            } else {
                z = z2;
            }
            e onlyNoonRectSetting = getOnlyNoonRectSetting(this.mEndData.d(), this.mCurrentBottom);
            eVar2.f2598a = onlyNoonRectSetting.f2598a;
            eVar2.c = onlyNoonRectSetting.c;
            if (!this.mIsOtherType && z) {
                setCurrentRectColor(this.mCurrentData.d());
                setConnectionBlock(canvas, this.mCurrentData, eVar, this.mEndData, eVar2);
            } else if (this.mCurrentData.d() == 69) {
                setCurrentRectColor(710);
            }
        } else if (this.mCurrentData.d() == 69) {
            setCurrentRectColor(710);
        }
        ChartTouchHelper chartTouchHelper = this.mHelper;
        if (chartTouchHelper != null) {
            setAccsibilityVirtualNode(this.mFitnessDataList.get(i4), eVar, chartTouchHelper.e().a(i4));
        }
        drawCyRect(canvas, eVar);
    }

    private void setCurrentValueData(e eVar, e eVar2, int i, int i2) {
        eVar.f2598a = eVar2.f2598a;
        eVar.c = eVar2.c;
        eVar.e = ((i / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
        eVar.d = ((i2 / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
    }

    private void drawCoreSleepRect(Canvas canvas, int i) {
        boolean z;
        e eVar = new e();
        e eVar2 = new e();
        int c2 = this.mCurrentData.c();
        int i2 = this.mNightStartPoint;
        eVar.e = (((c2 - i2) / (this.mNightEndPoint - i2)) * this.mDiagramWidth) + this.mChartBorderWidth;
        int a2 = this.mCurrentData.a();
        int i3 = this.mNightStartPoint;
        eVar.d = (((a2 - i3) / (this.mNightEndPoint - i3)) * this.mDiagramWidth) + this.mChartBorderWidth;
        if (i == 1) {
            LogUtil.a(TAG, "mCurrentData.getStartPoint() = ", Integer.valueOf(this.mCurrentData.c()), "; mNightStartPoint = ", Integer.valueOf(this.mNightStartPoint), "; mNightEndPoint = ", Integer.valueOf(this.mNightEndPoint), "; mCurrentLeft = ", Float.valueOf(eVar.e), "", "; mViewWidth = ", Float.valueOf(this.mViewWidth), "; mDiagramWidth = ", Float.valueOf(this.mDiagramWidth), "mChartBorderWidth = ", Float.valueOf(this.mChartBorderWidth));
        }
        e currentRectSetting = getCurrentRectSetting(this.mCurrentData.d(), this.mCurrentBottom);
        eVar.f2598a = currentRectSetting.f2598a;
        eVar.c = currentRectSetting.c;
        setCurrentRectColor(this.mCurrentData.d());
        setRtlPosition(eVar);
        getTheLastData(this.mCurrentData);
        int i4 = i + 1;
        if (i4 < this.mFitnessDataList.size()) {
            ecu ecuVar = this.mFitnessDataList.get(i4);
            this.mEndData = ecuVar;
            int c3 = ecuVar.c();
            int i5 = this.mNightStartPoint;
            eVar2.e = (((c3 - i5) / (this.mNightEndPoint - i5)) * this.mDiagramWidth) + this.mChartBorderWidth;
            int a3 = this.mEndData.a();
            int i6 = this.mNightStartPoint;
            eVar2.d = (((a3 - i6) / (this.mNightEndPoint - i6)) * this.mDiagramWidth) + this.mChartBorderWidth;
            boolean z2 = Math.abs(eVar.d - eVar2.e) < PRECISION;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                float f = eVar2.e;
                eVar2.e = this.mViewWidth - eVar2.d;
                eVar2.d = this.mViewWidth - f;
                z = Math.abs(eVar.e - eVar2.d) < PRECISION;
            } else {
                z = z2;
            }
            e currentRectSetting2 = getCurrentRectSetting(this.mEndData.d(), this.mCurrentBottom);
            eVar2.f2598a = currentRectSetting2.f2598a;
            eVar2.c = currentRectSetting2.c;
            if (!this.mIsOtherType && z) {
                setConnectionBlock(canvas, this.mCurrentData, eVar, this.mEndData, eVar2);
            }
            setMultiSleep(z);
        }
        ChartTouchHelper chartTouchHelper = this.mHelper;
        if (chartTouchHelper != null) {
            setAccsibilityVirtualNode(this.mFitnessDataList.get(i), eVar, chartTouchHelper.e().a(i));
        }
        drawCyRect(canvas, eVar);
    }

    private void drawCommonSleepRect(Canvas canvas, int i, int i2, int i3) {
        LogUtil.b(TAG, "drawCommonSleepRect startPoint is" + i + "endPoint is " + i2 + "size is " + this.mFitnessDataList.size() + "index is" + i3);
        e eVar = new e();
        e eVar2 = new e();
        eVar.e = ((((float) i) / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
        eVar.d = ((((float) i2) / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
        e ordinaryRectSetting = getOrdinaryRectSetting(this.mCurrentData.d(), this.mCurrentBottom);
        eVar.f2598a = ordinaryRectSetting.f2598a;
        eVar.c = ordinaryRectSetting.c;
        setCurrentRectColor(this.mCurrentData.d());
        setRtlPosition(eVar);
        getTheLastData(this.mCurrentData);
        boolean z = true;
        int i4 = i3 + 1;
        if (i4 < this.mFitnessDataList.size()) {
            this.mEndData = this.mFitnessDataList.get(i4);
            eVar2.e = ((r11.c() / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
            eVar2.d = ((this.mEndData.a() / 1440.0f) * this.mDiagramWidth) + this.mChartBorderWidth;
            boolean z2 = Math.abs(eVar.d - eVar2.e) < PRECISION;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                float f = eVar2.e;
                eVar2.e = this.mViewWidth - eVar2.d;
                eVar2.d = this.mViewWidth - f;
                if (Math.abs(eVar.e - eVar2.d) >= PRECISION) {
                    z = false;
                }
            } else {
                z = z2;
            }
            e ordinaryRectSetting2 = getOrdinaryRectSetting(this.mEndData.d(), this.mCurrentBottom);
            eVar2.f2598a = ordinaryRectSetting2.f2598a;
            eVar2.c = ordinaryRectSetting2.c;
            if (!this.mIsOtherType && z) {
                setConnectionBlock(canvas, this.mCurrentData, eVar, this.mEndData, eVar2);
            }
            setMultiSleep(z);
        }
        drawCyRect(canvas, eVar);
    }

    private void setMultiSleep(boolean z) {
        if (z || this.mIsMultiSleep || this.mEndData.d() == 69) {
            return;
        }
        this.mIsMultiSleep = true;
        LogUtil.a(TAG, "enddata:", this.mEndData, " startData:", this.mCurrentData);
    }

    private void setRtlPosition(e eVar) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f = eVar.e;
            eVar.e = this.mViewWidth - eVar.d;
            eVar.d = this.mViewWidth - f;
        }
    }

    public void drawGoOffBedTime(Canvas canvas) {
        if (this.mFitnessDataList.isEmpty()) {
            LogUtil.h(TAG, "mFitnessDataList is empty");
            return;
        }
        if (this.mIsOnlyNoonSleepType) {
            LogUtil.h(TAG, "mIsOnlyNoonSleepType ");
            return;
        }
        boolean z = this.mIsScienceSleep;
        if (z && !this.mIsPhone && !this.mIsManual) {
            LogUtil.h(TAG, "science sleep do not support bedTime");
            return;
        }
        if (!z && !this.mIsPhone && !this.mIsManual) {
            LogUtil.a(TAG, "common sleep do not show go off bed time");
            return;
        }
        String goBedText = getGoBedText();
        String offBedText = getOffBedText();
        Paint paint = new Paint();
        if (this.mColorType == 1) {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        } else {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        }
        paint.setTextSize(nrf.e(0, this.mContext.getResources().getDimension(R.dimen._2131362708_res_0x7f0a0394)));
        paint.setAntiAlias(true);
        canvas.drawText(goBedText, een.e(24.0f), een.e(10.0f), paint);
        canvas.drawText(offBedText, (getWidth() - een.e(24.0f)) - paint.measureText(offBedText), een.e(10.0f), paint);
    }

    private boolean isCrossDay() {
        if (this.mIsManual) {
            return false;
        }
        return jdl.e(this.mGoBedTime, 20, 0) == this.mGoBedTime || jdl.e(this.mOffBedTime, 20, 0) == this.mOffBedTime || jdl.e(this.mFallAsleepTime, 20, 0) == this.mFallAsleepTime || jdl.e(this.mWakeUpTime, 20, 0) == this.mWakeUpTime;
    }

    private String getGoBedText() {
        int[] hourAndMin = getHourAndMin(this.mGoBedTime);
        int i = hourAndMin[0];
        int i2 = hourAndMin[1];
        if (i == 20 && i2 == 0) {
            return "";
        }
        String formatTime = formatTime(getTimeStr(i) + ":" + getTimeStr(i2));
        if (!this.mIsManual) {
            return formatTime;
        }
        if (!LanguageUtil.j(BaseApplication.getContext())) {
            return nsf.h(R$string.IDS_manual_sleep_go_to_bed) + " " + formatTime;
        }
        return nsf.h(R$string.IDS_manual_sleep_go_to_bed) + formatTime;
    }

    private String getOffBedText() {
        int[] hourAndMin = getHourAndMin(this.mOffBedTime);
        int i = hourAndMin[0];
        int i2 = hourAndMin[1];
        if (i == 20 && i2 == 0) {
            return "";
        }
        String formatTime = formatTime(getTimeStr(i) + ":" + getTimeStr(i2));
        if (!this.mIsManual) {
            return formatTime;
        }
        if (!LanguageUtil.j(BaseApplication.getContext())) {
            return nsf.h(R$string.IDS_manual_sleep_get_out_of_bed) + " " + formatTime;
        }
        return nsf.h(R$string.IDS_manual_sleep_get_out_of_bed) + formatTime;
    }

    public void drawMaxSleepVerticalLine(Canvas canvas) {
        List<ecu> list;
        long j;
        long j2;
        int i;
        int i2;
        if (canvas == null || (list = this.mCopyOfFitnessDataList) == null || list.size() == 0) {
            return;
        }
        if ((this.mRemSleepTimes <= 0 && (!this.mIsScienceSleep || this.mIsOnlyNoonSleepType)) || this.mIsPhone || this.mIsManual) {
            int i3 = 0;
            if (this.mIsOnlyNoonSleepType) {
                ArrayList<ecu> sleepSumList = getSleepSumList();
                LogUtil.a(TAG, "sumSleepList = ", sleepSumList.toString());
                ecu maxSleepData = getMaxSleepData(sleepSumList);
                if (maxSleepData == null) {
                    return;
                }
                i = maxSleepData.h;
                i2 = maxSleepData.f11956a;
                j = maxSleepData.e();
                j2 = maxSleepData.b();
            } else {
                LogUtil.b(TAG, "drawMaxSleepVerticalLine mStartSleepPoint is ", Integer.valueOf(this.mStartSleepPoint), " mLastNightEndPoint is ", Integer.valueOf(this.mLastNightEndPoint));
                if (this.mFitnessDataList.isEmpty()) {
                    j = 0;
                    j2 = 0;
                } else {
                    long e2 = this.mFitnessDataList.get(0).e();
                    j2 = this.mFitnessDataList.get(r3.size() - 1).b();
                    j = e2;
                }
                i = this.mStartSleepPoint;
                i2 = this.mLastNightEndPoint;
            }
            long j3 = j;
            long j4 = j2;
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                int i4 = 1440 - i;
                i = 1440 - i2;
                i2 = i4;
            }
            if ((!this.mIsPhone || this.mIsOnlyNoonSleepType) && !this.mIsManual) {
                i3 = i;
            } else {
                i2 = 1440;
            }
            float f = i3 / 1440.0f;
            float f2 = this.mDiagramWidth;
            float f3 = this.mChartBorderWidth;
            float f4 = (f * f2) + f3;
            float f5 = ((i2 / 1440.0f) * f2) + f3;
            if (this.mIsManual) {
                drawVerticalLine(canvas, f4, 16.0f);
                drawVerticalLine(canvas, f5, 16.0f);
            } else {
                drawStartEndTimeStr(canvas, f4, f5, j3, j4);
            }
        }
    }

    public void drawCursorText(Canvas canvas) {
        if (canvas == null || this.mIsMultiSleep) {
            return;
        }
        drawGoOffBedTime(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(1.0f);
        paint.setTextSize(een.e(10.0f));
        paint.setAntiAlias(true);
        int length = this.mTime.length;
        float[] fArr = new float[length];
        this.mCursorTextHeight = this.mCursorHeight - een.e(4.0f);
        for (int i = 0; i < length; i++) {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            paint.setTypeface(Typeface.create(this.mContext.getResources().getString(R$string.textFontFamilyRegular), 0));
            int i2 = length - 1;
            String str = this.mTime[i2];
            float measureText = (int) paint.measureText(str, 0, str.length());
            if (TextUtils.isEmpty(this.mTime[i2]) && !TextUtils.isEmpty(this.mTime[0])) {
                String str2 = this.mTime[0];
                measureText = (int) paint.measureText(str2, 0, str2.length());
            }
            float f = measureText;
            setTextTransverseWidth(fArr, f, length, i);
            setFloatText(fArr, f, i);
            LogUtil.b(TAG, "drawCursorText offsets is " + Arrays.toString(fArr));
            float f2 = this.mCursorCenter;
            float e2 = een.e(36.0f);
            float f3 = fArr[i];
            if (f2 - e2 < (f / 2.0f) + f3 && f3 < this.mCursorCenter + een.e(36.0f) && this.mIsDayDetailView) {
                paint.setColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                paint.setTypeface(Typeface.create(this.mContext.getResources().getString(R$string.textFontFamilyMedium), 0));
            }
            setDrawText(i, fArr, canvas, paint, length, f);
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
                    float f9 = CURSOR_TEXT_HIGHEST_HEIGHT * HALF_PAST_ONE;
                    if (f8 > f9) {
                        f8 = f9;
                    }
                    this.mCursorTextHeight = this.mCursorHeight - f8;
                    return;
                }
                if (f2 <= f4 && f4 <= f6) {
                    this.mCursorTextHeight = this.mCursorHeight - (CURSOR_TEXT_HIGHEST_HEIGHT * HALF_PAST_ONE);
                    return;
                }
                if (f5 <= f4 && f4 < f2) {
                    float f10 = CURSOR_TEXT_MINIMUM_HEIGHT + (((f4 - f5) / f3) * CURSOR_TEXT_FLOATING_OFFSET);
                    float f11 = CURSOR_TEXT_HIGHEST_HEIGHT * HALF_PAST_ONE;
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
            } else if (this.mIsManual) {
                fArr[i2] = this.mBedSleepStartX;
                return;
            } else {
                fArr[i2] = een.e(16.0f) + f2;
                return;
            }
        }
        int i3 = i - 1;
        if (i2 == i3) {
            if (this.mIsScienceSleep && !this.mIsOnlyNoonSleepType) {
                fArr[i2] = this.mCoreSleepEndX - f2;
                return;
            } else if (this.mIsManual) {
                fArr[i2] = this.mUpSleepEndX;
                return;
            } else {
                fArr[i2] = ((this.mViewWidth - een.e(16.0f)) - f) - f2;
                return;
            }
        }
        fArr[i2] = ((((this.mViewWidth - (een.e(16.0f) * 2.0f)) - f) / i3) * i2) + een.e(16.0f);
    }

    private void setDrawText(int i, float[] fArr, Canvas canvas, Paint paint, int i2, float f) {
        if (i == 0) {
            canvas.drawText(this.mTime[i], fArr[i] + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue(), this.mCursorTextHeight, paint);
        } else if (i == i2 - 1) {
            if (!this.mIsManualAndIncomplete) {
                canvas.drawText(this.mTime[i], fArr[i] - ((Integer) BaseActivity.getSafeRegionWidth().second).intValue(), this.mCursorTextHeight, paint);
            }
        } else {
            canvas.drawText(this.mTime[i], fArr[i], this.mCursorTextHeight, paint);
        }
        String str = this.mStartDate;
        float measureText = (int) paint.measureText(str, 0, str.length());
        String str2 = this.mEndDate;
        float f2 = (f - measureText) / 2.0f;
        float measureText2 = (f - ((int) paint.measureText(str2, 0, str2.length()))) / 2.0f;
        if (i == 0) {
            canvas.drawText(this.mDate[0], fArr[i] + f2 + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue(), this.mCursorTextHeight - een.e(12.0f), paint);
        } else {
            if (i == i2 - 1) {
                if (this.mIsManualAndIncomplete) {
                    return;
                }
                canvas.drawText(this.mDate[1], (fArr[i] + measureText2) - ((Integer) BaseActivity.getSafeRegionWidth().second).intValue(), this.mCursorTextHeight - een.e(12.0f), paint);
                return;
            }
            LogUtil.a(TAG, "i is not start and end");
        }
    }

    private void drawStartEndTimeStr(Canvas canvas, float f, float f2, long j, long j2) {
        String formatTime;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        this.mStartHour = calendar.get(11);
        int i = calendar.get(12);
        this.mStartMin = i;
        String str = "";
        if (this.mStartHour == 20 && i == 0) {
            formatTime = "";
        } else {
            formatTime = formatTime(getTimeStr(this.mStartHour) + ":" + getTimeStr(this.mStartMin));
        }
        calendar.setTimeInMillis(j2);
        this.mEndHour = calendar.get(11);
        int i2 = calendar.get(12);
        this.mEndMin = i2;
        if (this.mEndHour != 20 || i2 != 0) {
            str = formatTime(getTimeStr(this.mEndHour) + ":" + getTimeStr(this.mEndMin));
        }
        Paint paint = new Paint();
        if (this.mColorType == 1) {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        } else {
            paint.setColor(this.mContext.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        }
        paint.setTextSize(nrf.e(0, this.mContext.getResources().getDimension(R.dimen._2131362708_res_0x7f0a0394)));
        paint.setAntiAlias(true);
        if (this.mIsScienceSleep && !this.mIsOnlyNoonSleepType && !this.mIsPhone) {
            canvas.drawText(formatTime, (f - ((int) Math.max(paint.measureText(formatTime, 0, formatTime.length()), paint.measureText(str, 0, str.length())))) - this.mTimeStringMargin, this.mTimeStrY, paint);
            canvas.drawText(str, f2 + this.mTimeStringMargin, this.mTimeStrY, paint);
        } else if (this.mIsOnlyNoonSleepType) {
            drawOnlyNoonSleepStartEndTime(canvas, paint, f, f2);
        }
    }

    private void drawOnlyNoonSleepStartEndTime(Canvas canvas, Paint paint, float f, float f2) {
        String startEndTimeFormat;
        String startEndTimeFormat2;
        float f3;
        boolean z;
        if (this.mIsPhone && !this.mIsOnlyNoonSleepType) {
            int[] hourAndMin = getHourAndMin(this.mGoBedTime);
            this.mStartHour = hourAndMin[0];
            this.mStartMin = hourAndMin[1];
            int[] hourAndMin2 = getHourAndMin(this.mOffBedTime);
            this.mEndHour = hourAndMin2[0];
            this.mEndMin = hourAndMin2[1];
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            startEndTimeFormat2 = getStartEndTimeFormat(this.mStartHour, this.mStartMin);
            startEndTimeFormat = getStartEndTimeFormat(this.mEndHour, this.mEndMin);
        } else {
            startEndTimeFormat = getStartEndTimeFormat(this.mStartHour, this.mStartMin);
            startEndTimeFormat2 = getStartEndTimeFormat(this.mEndHour, this.mEndMin);
        }
        float measureText = (int) paint.measureText(startEndTimeFormat, 0, startEndTimeFormat.length());
        float f4 = (f - measureText) - this.mTimeStringMargin;
        if (0.0f >= f4 - een.e(16.0f)) {
            f3 = een.e(16.0f);
            z = true;
        } else {
            f3 = f4;
            z = false;
        }
        setDrawVerticalLine(canvas, paint, f, f2, measureText, z, false, f3, startEndTimeFormat, startEndTimeFormat2);
    }

    private String getStartEndTimeFormat(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(jdl.d(), jdl.k(System.currentTimeMillis()), 1, i, i2);
        if (this.mIsPhone && !this.mIsOnlyNoonSleepType) {
            return UnitUtil.a(calendar.getTime(), 129);
        }
        return UnitUtil.a(calendar.getTime(), 1);
    }

    private void setDrawVerticalLine(Canvas canvas, Paint paint, float f, float f2, float f3, boolean z, boolean z2, float f4, String str, String str2) {
        boolean z3;
        boolean z4;
        boolean z5;
        float f5;
        float f6;
        float f7 = this.mTimeStringMargin + f2 + f3;
        float e2 = een.e(13.0f);
        if (een.e(16.0f) + f7 > this.mViewWidth) {
            z3 = true;
        } else {
            f7 = this.mTimeStringMargin + f2;
            z3 = z2;
        }
        if (!this.mIsPhone || this.mIsOnlyNoonSleepType) {
            z4 = z3;
            z5 = z;
        } else {
            z5 = false;
            z4 = false;
        }
        LogUtil.a(TAG, "setDrawVerticalLine tipEndX ", Float.valueOf(f7), " tipStartX ", Float.valueOf(f4), " startTextWidth ", Float.valueOf(f3), " mDiagramWidth ", Float.valueOf(this.mDiagramWidth));
        if (!this.mIsPhone || this.mIsOnlyNoonSleepType) {
            f5 = f4;
        } else {
            float f8 = f3 / 2.0f;
            f5 = f - f8;
            f7 = f2 - f8;
        }
        if (!z5 && !z4) {
            drawVerticalLine(canvas, f, 0.0f);
            canvas.drawText(str, f5, e2, paint);
            drawVerticalLine(canvas, f2, 0.0f);
            canvas.drawText(str2, f7, e2, paint);
        }
        String str3 = str + Constants.LINK + str2;
        if (z4) {
            float f9 = this.mViewWidth;
            float f10 = this.mTimeStringMargin;
            float measureText = (int) paint.measureText(str3, 0, str3.length());
            canvas.drawText(str + Constants.LINK + str2, ((f9 - f10) - measureText) - een.e(16.0f), e2, paint);
            f6 = 12.0f;
            drawVerticalLine(canvas, f, 12.0f);
            drawVerticalLine(canvas, f2, 12.0f);
        } else {
            f6 = 12.0f;
        }
        if (z5) {
            drawVerticalLine(canvas, f, f6);
            drawVerticalLine(canvas, f2, f6);
            canvas.drawText(str + Constants.LINK + str2, f5, e2, paint);
        }
    }

    private void drawVerticalLine(Canvas canvas, float f, float f2) {
        float f3;
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
        float e2 = een.e(f2);
        if (this.mIsPhone) {
            f3 = this.mVerticalLineStartY + e2;
            e2 = een.e(13.0f);
        } else {
            f3 = this.mVerticalLineStartY;
        }
        path.moveTo(f, f3 + e2);
        path.lineTo(f, this.mTransparentHeight);
        canvas.drawPath(path, paint);
    }

    private ArrayList<ecu> getSleepSumList() {
        ArrayList<ecu> arrayList = new ArrayList<>(16);
        for (ecu ecuVar : this.mCopyOfFitnessDataList) {
            if (ecuVar.d() != 64) {
                if (arrayList.size() == 0) {
                    arrayList.add(ecuVar);
                } else {
                    ecu ecuVar2 = arrayList.get(arrayList.size() - 1);
                    if (ecuVar2.f11956a == ecuVar.h) {
                        ecuVar2.f11956a = ecuVar.f11956a;
                    } else {
                        arrayList.add(ecuVar);
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
                if (this.mIsManual) {
                    if (this.mColorType == 1) {
                        this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131298749_res_0x7f0909bd);
                        break;
                    } else {
                        this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131298747_res_0x7f0909bb);
                        break;
                    }
                } else if (this.mColorType == 1) {
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
                if (this.mIsManual) {
                    if (this.mColorType == 1) {
                        this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131298756_res_0x7f0909c4);
                        break;
                    } else {
                        this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131298754_res_0x7f0909c2);
                        break;
                    }
                } else if (this.mColorType == 1) {
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
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296716_res_0x7f0901cc);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296717_res_0x7f0901cd);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296836_res_0x7f090244);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296837_res_0x7f090245);
                    break;
                }
            case 67:
                if (this.mIsManual) {
                    if (this.mColorType == 1) {
                        this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131298749_res_0x7f0909bd);
                        this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298749_res_0x7f0909bd);
                        break;
                    } else {
                        this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131298747_res_0x7f0909bb);
                        this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298747_res_0x7f0909bb);
                        break;
                    }
                } else {
                    setAwakeSleepGradientColor();
                    break;
                }
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

    private void drawLineArc(Canvas canvas, a aVar) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color._2131296657_res_0x7f090191));
        paint.setStrokeWidth(1.0f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(new RectF(aVar.f2596a, aVar.c, aVar.f, aVar.e), aVar.b, aVar.d, true, paint);
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
                if (this.mIsManual) {
                    if (this.mColorType == 1) {
                        this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131298756_res_0x7f0909c4);
                        this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298756_res_0x7f0909c4);
                        break;
                    } else {
                        this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131298754_res_0x7f0909c2);
                        this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298754_res_0x7f0909c2);
                        break;
                    }
                } else if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296723_res_0x7f0901d3);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296722_res_0x7f0901d2);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296846_res_0x7f09024e);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296844_res_0x7f09024c);
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
        if (this.mIsManual) {
            if (this.mColorType == 1) {
                this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131298749_res_0x7f0909bd);
                this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298749_res_0x7f0909bd);
                return;
            } else {
                this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131298747_res_0x7f0909bb);
                this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298747_res_0x7f0909bb);
                return;
            }
        }
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
                if (this.mIsManual) {
                    if (this.mColorType == 1) {
                        this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131298756_res_0x7f0909c4);
                        break;
                    } else {
                        this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131298754_res_0x7f0909c2);
                        break;
                    }
                } else if (this.mColorType == 1) {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296743_res_0x7f0901e7);
                    break;
                } else {
                    this.mEndConnectionColor = this.mContext.getResources().getColor(R.color._2131296838_res_0x7f090246);
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
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296748_res_0x7f0901ec);
                    break;
                } else {
                    this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131296847_res_0x7f09024f);
                    break;
                }
            case 67:
                if (this.mIsManual) {
                    if (this.mColorType == 1) {
                        this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131298749_res_0x7f0909bd);
                        break;
                    } else {
                        this.mStartConnectionColor = this.mContext.getResources().getColor(R.color._2131298747_res_0x7f0909bb);
                        break;
                    }
                } else {
                    setAwakeSleepStartConnectionLineColor();
                    break;
                }
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
                if (this.mIsManual) {
                    if (this.mColorType == 1) {
                        this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131298756_res_0x7f0909c4);
                        this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298756_res_0x7f0909c4);
                        break;
                    } else {
                        this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131298754_res_0x7f0909c2);
                        this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131298754_res_0x7f0909c2);
                        break;
                    }
                } else if (this.mColorType == 1) {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296722_res_0x7f0901d2);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296723_res_0x7f0901d3);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296844_res_0x7f09024c);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296846_res_0x7f09024e);
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
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296717_res_0x7f0901cd);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296716_res_0x7f0901cc);
                    break;
                } else {
                    this.mStartLineRectColor = this.mContext.getResources().getColor(R.color._2131296837_res_0x7f090245);
                    this.mEndLineRectColor = this.mContext.getResources().getColor(R.color._2131296836_res_0x7f090244);
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

    public e getOnlyNoonRectSetting(int i, float f) {
        float e2 = een.e(24.0f);
        float f2 = this.mDiagramHeight;
        this.mIsOtherType = false;
        e eVar = new e();
        if (i == 69) {
            eVar.f2598a = ((f2 - e2) * 0.38f) + e2;
            eVar.c = (3.0f * e2) + ((f - e2) * 0.62f);
        } else {
            this.mIsOtherType = true;
        }
        return eVar;
    }

    private e getCurrentRectSetting(int i, float f) {
        float e2 = een.e(24.0f);
        this.mIsOtherType = false;
        e eVar = new e();
        if (this.mIsManualAndOnlyBed) {
            float f2 = e2 + ((f - e2) * 0.33f);
            eVar.f2598a = f2;
            eVar.c = f2 + een.e(32.0f);
            return eVar;
        }
        if (this.mIsPhone || this.mIsManual) {
            switch (i) {
                case 65:
                    float f3 = e2 + ((f - e2) * 0.66f);
                    eVar.f2598a = f3;
                    eVar.c = f3 + een.e(32.0f);
                    break;
                case 66:
                    float f4 = e2 + ((f - e2) * 0.33f);
                    eVar.f2598a = f4;
                    eVar.c = f4 + een.e(32.0f);
                    break;
                case 67:
                    eVar.f2598a = e2;
                    eVar.c = e2 + een.e(32.0f);
                    break;
                default:
                    this.mIsOtherType = true;
                    break;
            }
            return eVar;
        }
        if (!this.mIsScienceSleep) {
            return this.mIsCommonSleep ? getOrdinaryRectSetting(i, f) : eVar;
        }
        switch (i) {
            case 65:
                float f5 = e2 + ((f - e2) * 0.75f);
                eVar.f2598a = f5;
                eVar.c = f5 + een.e(25.5f);
                break;
            case 66:
                float f6 = e2 + ((f - e2) * 0.5f);
                eVar.f2598a = f6;
                eVar.c = f6 + een.e(25.5f);
                break;
            case 67:
                eVar.f2598a = e2;
                eVar.c = e2 + een.e(25.5f);
                break;
            case 68:
                float f7 = e2 + ((f - e2) * 0.25f);
                eVar.f2598a = f7;
                eVar.c = f7 + een.e(25.5f);
                break;
            default:
                this.mIsOtherType = true;
                break;
        }
        return eVar;
    }

    private e getOrdinaryRectSetting(int i, float f) {
        float e2 = een.e(24.0f);
        this.mIsOtherType = false;
        e eVar = new e();
        switch (i) {
            case 65:
                float f2 = e2 + ((f - e2) * 0.66f);
                eVar.f2598a = f2;
                eVar.c = f2 + een.e(32.0f);
                return eVar;
            case 66:
                float f3 = e2 + ((f - e2) * 0.33f);
                eVar.f2598a = f3;
                eVar.c = f3 + een.e(32.0f);
                return eVar;
            case 67:
                eVar.f2598a = e2;
                eVar.c = e2 + een.e(32.0f);
                return eVar;
            default:
                this.mIsOtherType = true;
                return eVar;
        }
    }

    private void setConnectionBlock(Canvas canvas, ecu ecuVar, e eVar, ecu ecuVar2, e eVar2) {
        if (eVar.c - eVar2.c < 0.0f) {
            setTopToBottomConnectionBlock(canvas, ecuVar, eVar, ecuVar2, eVar2);
        } else {
            setBottomToTopConnectionBlock(canvas, ecuVar, eVar, ecuVar2, eVar2);
        }
    }

    private void setBottomToTopConnectionBlock(Canvas canvas, ecu ecuVar, e eVar, ecu ecuVar2, e eVar2) {
        float e2;
        float e3;
        float e4 = een.e(6.0f);
        float e5 = een.e(1.0f);
        if (eVar.d - eVar.e < e4) {
            e2 = (eVar.d - eVar.e) / 2.0f;
        } else {
            e2 = een.e(3.0f);
        }
        float f = e2;
        setBottomToTopStartColor(ecuVar.d());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            drawLineRect(canvas, eVar.e, (eVar.f2598a - f) + e5, eVar.e + f, eVar.f2598a + f);
            drawArc5(canvas, eVar, e5, f);
        } else {
            float f2 = eVar.d;
            drawLineRect(canvas, f2 - f, (eVar.f2598a - f) + e5, eVar.d, eVar.f2598a + f);
            drawArc6(canvas, eVar, e5, f);
        }
        if (eVar2.d - eVar2.e < e4) {
            e3 = (eVar2.d - eVar2.e) / 2.0f;
        } else {
            e3 = een.e(3.0f);
        }
        float f3 = e3;
        setBottomToTopEndColor(ecuVar2.d());
        setBottomToTopLineStartColor(ecuVar.d());
        setBottomToTopLineEndColor(ecuVar2.d());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f4 = eVar2.d;
            drawLineRect(canvas, f4 - f3, eVar2.c - f3, eVar2.d, eVar2.c + f3);
            drawArc7(canvas, eVar2, e5, f3);
            drawConnectionLine(canvas, eVar.e, (eVar.c - f3) - e5, eVar.e, eVar2.f2598a + f3 + e5);
            return;
        }
        drawLineRect(canvas, eVar2.e, eVar2.c - f3, eVar2.e + f3, (eVar2.c + f3) - e5);
        drawArc8(canvas, eVar2, e5, f3);
        drawConnectionLine(canvas, eVar.d, (eVar.c - f3) - e5, eVar.d, eVar2.f2598a + f3 + e5);
    }

    private void drawArc5(Canvas canvas, e eVar, float f, float f2) {
        a aVar = new a();
        aVar.f2596a = eVar.e;
        float f3 = f2 * 2.0f;
        aVar.c = eVar.f2598a - f3;
        aVar.f = eVar.e + f3 + f;
        aVar.e = eVar.f2598a;
        aVar.b = 180.0f;
        aVar.d = -90.0f;
        drawLineArc(canvas, aVar);
    }

    private void drawArc6(Canvas canvas, e eVar, float f, float f2) {
        a aVar = new a();
        float f3 = f2 * 2.0f;
        aVar.f2596a = eVar.d - (f + f3);
        aVar.c = eVar.f2598a - f3;
        aVar.f = eVar.d;
        aVar.e = eVar.f2598a;
        aVar.b = 0.0f;
        aVar.d = 90.0f;
        drawLineArc(canvas, aVar);
    }

    private void drawArc7(Canvas canvas, e eVar, float f, float f2) {
        a aVar = new a();
        float f3 = f2 * 2.0f;
        aVar.f2596a = ((eVar.d - f3) + f) - RTL_EXTRA_LEFT;
        aVar.c = eVar.c;
        aVar.f = eVar.d;
        aVar.e = eVar.c + f3 + 2.0f;
        aVar.b = 0.0f;
        aVar.d = -90.0f;
        drawLineArc(canvas, aVar);
    }

    private void drawArc8(Canvas canvas, e eVar, float f, float f2) {
        a aVar = new a();
        aVar.f2596a = eVar.e;
        aVar.c = eVar.c;
        float f3 = f2 * 2.0f;
        aVar.f = eVar.e + f + f3;
        aVar.e = eVar.c + f3;
        aVar.b = 180.0f;
        aVar.d = 90.0f;
        drawLineArc(canvas, aVar);
    }

    private void setTopToBottomConnectionBlock(Canvas canvas, ecu ecuVar, e eVar, ecu ecuVar2, e eVar2) {
        float e2;
        float e3;
        float e4 = een.e(6.0f);
        float e5 = een.e(1.0f);
        if (eVar.d - eVar.e < e4) {
            e2 = (eVar.d - eVar.e) / 2.0f;
        } else {
            e2 = een.e(3.0f);
        }
        float f = e2;
        setTopToBottomStartColor(ecuVar.d());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            drawLineRect(canvas, eVar.e, eVar.c - f, eVar.e + f, eVar.c + f);
            drawArc1(canvas, eVar, e5, f);
        } else {
            float f2 = eVar.d;
            drawLineRect(canvas, f2 - f, eVar.c - f, eVar.d, (eVar.c + f) - e5);
            drawArc2(canvas, eVar, e5, f);
        }
        if (eVar2.d - eVar2.e < e4) {
            e3 = (eVar2.d - eVar2.e) / 2.0f;
        } else {
            e3 = een.e(3.0f);
        }
        float f3 = e3;
        setTopToBottomEndColor(ecuVar2.d());
        setTopToBottomLineStartColor(ecuVar.d());
        setTopToBottomLineEndColor(ecuVar2.d());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            float f4 = eVar2.d;
            drawLineRect(canvas, f4 - f3, (eVar2.f2598a - f3) + e5, eVar2.d, eVar2.f2598a + f3);
            drawArc3(canvas, eVar2, e5, f3);
            drawConnectionLine(canvas, eVar.e, eVar.f2598a + f3 + e5, eVar.e, (eVar2.c - f3) - e5);
            return;
        }
        drawLineRect(canvas, eVar2.e, (eVar2.f2598a - f3) + e5, eVar2.e + f3, eVar2.f2598a + f3);
        drawArc4(canvas, eVar2, e5, f3);
        drawConnectionLine(canvas, eVar.d, eVar.f2598a + f3 + e5, eVar.d, (eVar2.c - f3) - e5);
    }

    private void drawArc1(Canvas canvas, e eVar, float f, float f2) {
        a aVar = new a();
        aVar.f2596a = eVar.e;
        aVar.c = eVar.c;
        float f3 = f2 * 2.0f;
        aVar.f = eVar.e + f + f3;
        aVar.e = eVar.c + f3 + 2.0f;
        aVar.b = 180.0f;
        aVar.d = 90.0f;
        drawLineArc(canvas, aVar);
    }

    private void drawArc2(Canvas canvas, e eVar, float f, float f2) {
        a aVar = new a();
        float f3 = f2 * 2.0f;
        aVar.f2596a = eVar.d - (f + f3);
        aVar.c = eVar.c;
        aVar.f = eVar.d;
        aVar.e = eVar.c + f3;
        aVar.b = 0.0f;
        aVar.d = -90.0f;
        drawLineArc(canvas, aVar);
    }

    private void drawArc3(Canvas canvas, e eVar, float f, float f2) {
        a aVar = new a();
        float f3 = f2 * 2.0f;
        aVar.f2596a = eVar.d - (f + f3);
        aVar.c = eVar.f2598a - f3;
        aVar.f = eVar.d;
        aVar.e = eVar.f2598a;
        aVar.b = 0.0f;
        aVar.d = 90.0f;
        drawLineArc(canvas, aVar);
    }

    private void drawArc4(Canvas canvas, e eVar, float f, float f2) {
        a aVar = new a();
        aVar.f2596a = eVar.e;
        float f3 = f2 * 2.0f;
        aVar.c = eVar.f2598a - f3;
        aVar.f = eVar.e + f3 + f;
        aVar.e = eVar.f2598a;
        aVar.b = 180.0f;
        aVar.d = -90.0f;
        drawLineArc(canvas, aVar);
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
                    if (this.mIsManual) {
                        setCurrentRectSleepColor();
                        break;
                    } else {
                        setCurrentRectLightColor();
                        break;
                    }
                case 67:
                    if (this.mIsManual) {
                        setCurrentRectBedColor();
                        break;
                    } else {
                        setCurrentRectAwakeColor();
                        break;
                    }
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

    private void setCurrentRectSleepColor() {
        if (this.mColorType == 1) {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131298756_res_0x7f0909c4);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131298756_res_0x7f0909c4);
        } else {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131298754_res_0x7f0909c2);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131298754_res_0x7f0909c2);
        }
    }

    private void setCurrentRectBedColor() {
        if (this.mColorType == 1) {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131298749_res_0x7f0909bd);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131298749_res_0x7f0909bd);
        } else {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131298747_res_0x7f0909bb);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131298747_res_0x7f0909bb);
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
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296749_res_0x7f0901ed);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296742_res_0x7f0901e6);
        } else {
            this.mStartCyColor = this.mContext.getResources().getColor(R.color._2131296848_res_0x7f090250);
            this.mEndCyColor = this.mContext.getResources().getColor(R.color._2131296835_res_0x7f090243);
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

    private void drawCyRect(Canvas canvas, e eVar) {
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(eVar.e, eVar.f2598a, eVar.e, eVar.c, this.mStartCyColor, this.mEndCyColor, Shader.TileMode.MIRROR));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1.0f);
        canvas.drawRoundRect(new RectF(eVar.e, eVar.f2598a, eVar.d, eVar.c), een.e(3.0f), een.e(3.0f), paint);
    }

    private ecu getMaxSleepData(ArrayList<ecu> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ecu ecuVar = arrayList.get(0);
        Iterator<ecu> it = arrayList.iterator();
        while (it.hasNext()) {
            ecu next = it.next();
            if (next.f11956a - next.h > ecuVar.f11956a - ecuVar.h) {
                ecuVar = next;
            }
        }
        return ecuVar;
    }

    private void getTheLastData(ecu ecuVar) {
        boolean z = ecuVar.d() == 67 || ecuVar.d() == 68;
        boolean z2 = ecuVar.d() == 66 || ecuVar.d() == 69 || ecuVar.d() == 65;
        if (z || z2) {
            this.mLastData = ecuVar;
        } else {
            this.mLastData = null;
        }
    }

    private String dateToString(Date date) {
        return DateFormatUtil.b(date.getTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_MD);
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

    private int[] getHourAndMin(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return new int[]{calendar.get(11), calendar.get(12)};
    }

    private void initAccessibilityHelper() {
        if (jcf.c()) {
            createAccessibilityHelper();
        } else {
            setAccessibilityListener();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAccessibilityHelper() {
        ChartTouchHelper chartTouchHelper = new ChartTouchHelper(this, jco.class);
        this.mHelper = chartTouchHelper;
        ViewCompat.setAccessibilityDelegate(this, chartTouchHelper);
    }

    private void setAccessibilityListener() {
        AccessibilityManager bEl_ = jcf.bEl_();
        if (bEl_ == null) {
            LogUtil.h(TAG, "accessibilityManager is ", bEl_);
        } else {
            bEl_.addTouchExplorationStateChangeListener(this.mAccessibilityListerner);
        }
    }

    static class c implements AccessibilityManager.TouchExplorationStateChangeListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<BaseSleepDayDetailView> f2597a;
        private boolean d = true;

        c(BaseSleepDayDetailView baseSleepDayDetailView) {
            this.f2597a = new WeakReference<>(baseSleepDayDetailView);
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            BaseSleepDayDetailView baseSleepDayDetailView = this.f2597a.get();
            if (baseSleepDayDetailView != null && z && this.d) {
                baseSleepDayDetailView.createAccessibilityHelper();
                this.d = false;
            }
        }
    }

    @Override // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        LogUtil.a(TAG, "enter into dispatchHoverEvent");
        ChartTouchHelper chartTouchHelper = this.mHelper;
        if (chartTouchHelper != null && chartTouchHelper.dispatchHoverEvent(motionEvent)) {
            LogUtil.a(TAG, "enter into dispatchHoverEvent != null");
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        LogUtil.a(TAG, "enter into dispatchKeyEvent");
        ChartTouchHelper chartTouchHelper = this.mHelper;
        if (chartTouchHelper != null && chartTouchHelper.dispatchKeyEvent(keyEvent)) {
            LogUtil.a(TAG, "enter into dispatchKeyEvent != null");
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initAccessibilityHelper();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AccessibilityManager bEl_ = jcf.bEl_();
        if (bEl_ == null) {
            LogUtil.h(TAG, "accessibilityManager is ", bEl_);
        } else {
            bEl_.removeTouchExplorationStateChangeListener(this.mAccessibilityListerner);
        }
    }
}
