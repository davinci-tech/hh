package com.huawei.indoorequip.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.view.SportEquipItemDetail;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.ffs;
import defpackage.gvv;
import defpackage.jed;
import defpackage.lbh;
import defpackage.lbv;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class CombineSportEquipItemDrawer {
    private static final float COEFFICIENT_OF_CAL_INSTANTANEOUS_SPEED = 100.0f;
    private static final double DEFAULT_VALUES = 0.0d;
    private static final String GET_QUANTITY_STRING_FORMAT_ARGS = "";
    private static final float ITEM_SPAN = 12.0f;
    private static final int RESISTANCE_TRANSFORM = 10;
    private static final int RUN_POSTURE_TEXT_SIZE = 30;
    private static final int SCIENTIFIC_GUIDE_ON_LEST_SPEED = 8;
    private static final float SKIPPING_HEIGHT_SPAN = 96.0f;
    private static final String TAG = "Track_IDEQ_CombineSportEquipItemDrawer";
    private static final float TAHITI_POSTURE_ICON_SPAN = 50.0f;
    private static final float TEXT_SIZE_REDUCE_FACTOR = 0.7f;
    protected Context mContext;
    private boolean mIsShowHeartRate;
    private boolean mIsShowImperialUnit;
    protected List<SportEquipItemDetail.e> mItemDataList;
    private int mSportType;
    private SupportDataRange mSupportDataRange;

    private boolean isPaceSport(int i) {
        return i == 275 || i == 264;
    }

    public CombineSportEquipItemDrawer(Context context, boolean z) {
        this.mSportType = 0;
        this.mSupportDataRange = null;
        this.mIsShowImperialUnit = UnitUtil.h();
        this.mContext = context;
        this.mIsShowHeartRate = z;
    }

    public CombineSportEquipItemDrawer(Context context, int i, boolean z, SupportDataRange supportDataRange) {
        this.mSupportDataRange = null;
        this.mSportType = i;
        this.mIsShowImperialUnit = UnitUtil.h();
        this.mContext = context;
        this.mIsShowHeartRate = z;
        this.mSupportDataRange = supportDataRange;
    }

    public void setIsShowHeartRate(boolean z) {
        this.mIsShowHeartRate = z;
    }

    private void buildSportDataItemList() {
        List<SportEquipItemDetail.e> list = this.mItemDataList;
        if (list == null) {
            this.mItemDataList = new ArrayList();
        } else {
            list.clear();
        }
    }

    public void setPageItem(int[] iArr, Map<Integer, Object> map) {
        if (iArr == null) {
            return;
        }
        buildSportDataItemList();
        for (int i : iArr) {
            addRunningData(map, i);
            if (i != 22) {
                if (i != 27) {
                    if (i != 37) {
                        if (i != 40006) {
                            if (i != 39) {
                                if (i == 40) {
                                    this.mItemDataList.add(buildGroupCountItem(getValue(map, i)));
                                } else {
                                    switch (i) {
                                        case 40001:
                                            this.mItemDataList.add(buildSkipperNumberItem(getSkippingValue(map, i)));
                                            break;
                                        case 40002:
                                            this.mItemDataList.add(buildStumblingRopeItem(getSkippingValue(map, i)));
                                            break;
                                        case 40003:
                                            this.mItemDataList.add(buildContinuousSkippingJumpItem(getSkippingValue(map, i)));
                                            break;
                                        case 40004:
                                            this.mItemDataList.add(buildSkippingSpeedItem(getSkippingValue(map, i)));
                                            break;
                                        default:
                                            setPageItemSplit(i, map);
                                            break;
                                    }
                                }
                            } else {
                                this.mItemDataList.add(buildActionGroupItem(getValue(map, i)));
                            }
                        } else {
                            this.mItemDataList.add(buildSkippingAvgSpeedItem(getSkippingValue(map, i)));
                        }
                    } else {
                        this.mItemDataList.add(buildWeightItem(getValue(map, i)));
                    }
                } else {
                    this.mItemDataList.add(buildPaddleItem(getValue(map, i)));
                }
            } else {
                setResistanceLevel(map, i);
            }
        }
    }

    private void setPageItemSplit(int i, Map<Integer, Object> map) {
        switch (i) {
            case 31:
                this.mItemDataList.add(buildInstantaneousCadenceItem(getValue(map, i)));
                break;
            case 40013:
                this.mItemDataList.add(buildSkipperIntermittentJumpAccumulatedCount(getSkippingValue(map, i)));
                break;
            case 40014:
                this.mItemDataList.add(buildSkipperIntermittentJumpAccumulatedDuration(getSkippingValue(map, i)));
                break;
            case 40016:
                this.mItemDataList.add(buildSkipperIntermittentJumpRoundNumberItem(getSkippingValue(map, i)));
                break;
            case 40051:
                this.mItemDataList.add(buildTimeItem(initValue(map, i)));
                break;
            case 40057:
            case 40058:
                this.mItemDataList.add(buildCalorieItem(initValue(map, i)));
                break;
            case 40059:
                this.mItemDataList.add(buildSkippingSpeedItem(getSkippingValue(map, i)));
                break;
            case 40063:
                buildRopeShakerItem(getSkippingValue(map, i), R.string._2130846062_res_0x7f02216e);
                break;
            case 40064:
                buildRopeShakerItem(getSkippingValue(map, i), R.string._2130846063_res_0x7f02216f);
                break;
            case 40065:
                buildRopeShakerItem(getSkippingValue(map, i), R.string._2130847681_res_0x7f0227c1);
                break;
            default:
                LogUtil.a(TAG, "other item: ", Integer.valueOf(i));
                break;
        }
    }

    private void setResistanceLevel(Map<Integer, Object> map, int i) {
        int value = getValue(map, i);
        LogUtil.a(TAG, "resistLevel ", Integer.valueOf(value));
        if (this.mSupportDataRange != null) {
            LogUtil.a(TAG, "mSupportDataRange != null");
            LogUtil.a(TAG, "mSupportDataRange min", Integer.valueOf(this.mSupportDataRange.getMinLevel() / 10));
            LogUtil.a(TAG, "mSupportDataRange max", Integer.valueOf(this.mSupportDataRange.getMaxLevel() / 10));
            if (this.mSupportDataRange.getMaxLevel() == 0) {
                return;
            }
            this.mItemDataList.add(buildResistLevelItem(value, this.mSupportDataRange.getMinLevel() / 10, this.mSupportDataRange.getMaxLevel() / 10));
            return;
        }
        this.mItemDataList.add(buildResistLevelItem(value, 0, 15));
    }

    private void addRunningData(Map<Integer, Object> map, int i) {
        if (i == 1) {
            this.mItemDataList.add(buildDistanceItem(initValue(map, i)));
            return;
        }
        if (i == 2) {
            this.mItemDataList.add(buildTimeItem(initValue(map, i)));
            return;
        }
        if (i == 3) {
            this.mItemDataList.add(buildIndoorEquipRunSpeedItem(getSpeedValue(map, i)));
            return;
        }
        if (i == 4) {
            this.mItemDataList.add(buildIndoorEquipRunStepRateItem(initValue(map, i)));
            return;
        }
        if (i == 6) {
            this.mItemDataList.add(buildCalorieItem(initValue(map, i)));
            return;
        }
        if (i == 7) {
            this.mItemDataList.add(buildPowerItem(getValue(map, i)));
            return;
        }
        if (i == 8) {
            this.mItemDataList.add(buildIndoorRunStepItem(getValue(map, i)));
            return;
        }
        if (i == 14) {
            if (isPaceSport(this.mSportType)) {
                this.mItemDataList.add(buildInstanesPaceItem(getPaceValue(map, i)));
            }
        } else {
            if (i != 20002) {
                if (i == 20004 && this.mIsShowHeartRate) {
                    this.mItemDataList.add(buildHeartRateItem(initValue(map, i)));
                    return;
                }
                return;
            }
            buildRunPosetureItem(map);
        }
    }

    private SportEquipItemDetail.e buildInstanesPaceItem(float f) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130840237_res_0x7f020aad), gvv.a(f));
    }

    private float getPaceValue(Map<Integer, Object> map, int i) {
        if (map == null || map.get(Integer.valueOf(i)) == null) {
            return 0.0f;
        }
        float floatValue = new BigDecimal(((Integer) map.get(Integer.valueOf(i))).intValue()).divide(new BigDecimal(100.0d), 2, 4).floatValue();
        LogUtil.a(TAG, "pace is ", Float.valueOf(floatValue));
        return UnitUtil.h() ? (float) UnitUtil.d(floatValue, 3) : floatValue;
    }

    private int getSkippingValue(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            return 0;
        }
        return ((Integer) map.get(Integer.valueOf(i))).intValue();
    }

    private float getSpeedValue(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            return 0.0f;
        }
        return ((Integer) map.get(Integer.valueOf(i))).intValue() / 100.0f;
    }

    private int getValue(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            return 0;
        }
        return ((Integer) map.get(Integer.valueOf(i))).intValue();
    }

    private int initValue(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            return 0;
        }
        return ((Integer) map.get(Integer.valueOf(i))).intValue();
    }

    protected void buildRunPosetureItem(Map<Integer, Object> map) {
        String string;
        Drawable bVS_;
        ffs ffsVar = (ffs) map.get(20002);
        float intValue = map.get(3) == null ? 0.0f : ((Integer) map.get(3)).intValue() / 100.0f;
        if (ffsVar == null) {
            setPostureDefault(intValue);
            return;
        }
        int d = ffsVar.d();
        int h = ffsVar.h();
        int c = ffsVar.c();
        int i = d > h ? d : h;
        if (i > c) {
            c = i;
        }
        if (c <= 0 || c == h) {
            string = this.mContext.getString(R.string._2130840272_res_0x7f020ad0);
            bVS_ = lbv.bVS_(this.mContext, R.drawable._2131428632_res_0x7f0b0518);
        } else if (c == d) {
            string = this.mContext.getString(R.string._2130840271_res_0x7f020acf);
            bVS_ = lbv.bVS_(this.mContext, R.drawable._2131428633_res_0x7f0b0519);
        } else {
            string = this.mContext.getString(R.string._2130842726_res_0x7f021466);
            bVS_ = lbv.bVS_(this.mContext, R.drawable._2131428631_res_0x7f0b0517);
        }
        int i2 = ffsVar.i();
        int b = lbv.b("swing angle", i2, intValue);
        int a2 = ffsVar.a();
        int b2 = lbv.b("eversion angle", a2, intValue);
        this.mItemDataList.add(buildSwingAngleItem(i2, b, intValue));
        this.mItemDataList.add(buildEversionAmplitude(a2, b2, intValue));
        this.mItemDataList.add(buildIndoorLandingMethod(string));
        this.mItemDataList.add(buildIndoorLandingIcon(bVS_));
    }

    private void setPostureDefault(float f) {
        String string = this.mContext.getString(R.string._2130840281_res_0x7f020ad9);
        this.mItemDataList.add(buildSwingAngleItem(0.0d, 0, f));
        this.mItemDataList.add(buildEversionAmplitude(0.0d, 0, f));
        this.mItemDataList.add(buildIndoorLandingMethod(string));
        this.mItemDataList.add(buildIndoorLandingIcon(null));
    }

    private SportEquipItemDetail.e buildPowerItem(int i) {
        String string = this.mContext.getString(R.string._2130843491_res_0x7f021763);
        String string2 = this.mContext.getResources().getString(R.string._2130851304_res_0x7f0235e8);
        if (i > 0) {
            string2 = UnitUtil.e(i, 1, 0);
        }
        return new SportEquipItemDetail.e(null, this.mContext.getString(R.string._2130843879_res_0x7f0218e7, string, this.mContext.getString(R.string._2130843492_res_0x7f021764)), string2);
    }

    private SportEquipItemDetail.e buildPaddleItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getString(R.string._2130843495_res_0x7f021767), UnitUtil.e(i, 1, 0));
    }

    private SportEquipItemDetail.e buildWeightItem(int i) {
        String quantityString;
        String string = this.mContext.getString(R.string._2130845943_res_0x7f0220f7);
        String e = UnitUtil.e(i, 1, 0);
        if (UnitUtil.h()) {
            quantityString = this.mContext.getResources().getQuantityString(R.plurals._2130903216_res_0x7f0300b0, i, "");
        } else {
            quantityString = this.mContext.getResources().getQuantityString(R.plurals._2130903344_res_0x7f030130, i, "");
        }
        return new SportEquipItemDetail.e(null, this.mContext.getString(R.string._2130843879_res_0x7f0218e7, string, quantityString), e);
    }

    private SportEquipItemDetail.e buildGroupCountItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getString(R.string._2130843879_res_0x7f0218e7, this.mContext.getString(R.string._2130845944_res_0x7f0220f8), this.mContext.getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, i, "")), UnitUtil.e(i, 1, 0));
    }

    private SportEquipItemDetail.e buildActionGroupItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getString(R.string._2130843879_res_0x7f0218e7, this.mContext.getString(R.string._2130845945_res_0x7f0220f9), this.mContext.getResources().getQuantityString(R.plurals._2130903395_res_0x7f030163, i, "")), UnitUtil.e(i, 1, 0));
    }

    private SportEquipItemDetail.e buildContinuousSkippingJumpItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130843879_res_0x7f0218e7, this.mContext.getResources().getString(R.string._2130843713_res_0x7f021841), this.mContext.getResources().getQuantityString(R.plurals._2130903273_res_0x7f0300e9, 0)).trim(), dealWithValue(i, "continuousSkippingJump is error"));
    }

    private SportEquipItemDetail.e buildStumblingRopeItem(int i) {
        String string = this.mContext.getResources().getString(R.string._2130843709_res_0x7f02183d);
        return new SportEquipItemDetail.e(null, this.mContext.getString(R.string._2130843879_res_0x7f0218e7, string, this.mContext.getResources().getString(R.string._2130841409_res_0x7f020f41)), dealWithValue(i, "stumblingRope is error"));
    }

    private String dealWithValue(int i, String str) {
        String string = this.mContext.getResources().getString(R.string._2130849885_res_0x7f02305d);
        if (i >= 0) {
            return UnitUtil.e(i, 1, 0);
        }
        LogUtil.b(TAG, str);
        return string;
    }

    private SportEquipItemDetail.e buildSkippingSpeedItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130843879_res_0x7f0218e7, this.mContext.getResources().getString(R.string._2130844076_res_0x7f0219ac), this.mContext.getResources().getString(R.string._2130843710_res_0x7f02183e)).trim(), dealWithValue(i, "skippingSpeed is over speed"));
    }

    private SportEquipItemDetail.e buildSkippingAvgSpeedItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130843879_res_0x7f0218e7, this.mContext.getResources().getString(R.string._2130839763_res_0x7f0208d3), this.mContext.getResources().getString(R.string._2130843710_res_0x7f02183e)).trim(), dealWithValue(i, "skippingAvgSpeed is over speed"));
    }

    private SportEquipItemDetail.e buildSkipperNumberItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130843714_res_0x7f021842), dealWithValue(i, "skipperNumber is over speed"));
    }

    private SportEquipItemDetail.e buildSkipperIntermittentJumpRoundNumberItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130845808_res_0x7f022070), dealWithValue(i, "roundNum is over speed"));
    }

    private SportEquipItemDetail.e buildSkipperIntermittentJumpAccumulatedCount(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130845858_res_0x7f0220a2), dealWithValue(i, "accumulatedCount is over speed"));
    }

    private SportEquipItemDetail.e buildSkipperIntermittentJumpAccumulatedDuration(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130845859_res_0x7f0220a3), UnitUtil.d(i));
    }

    private void buildRopeShakerItem(int i, int i2) {
        if (i == -1) {
            LogUtil.h(TAG, "buildRopeShakerItem() count is invalid value");
            return;
        }
        this.mItemDataList.add(new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130843879_res_0x7f0218e7, this.mContext.getResources().getString(i2), this.mContext.getResources().getQuantityString(R.plurals._2130903273_res_0x7f0300e9, 0)).trim(), dealWithValue(i, "buildRopeShakerItem is error")));
    }

    private SportEquipItemDetail.e buildResistLevelItem(int i, int i2, int i3) {
        String format = String.format(this.mContext.getString(R.string._2130843484_res_0x7f02175c), Integer.valueOf(i2), Integer.valueOf(i3));
        String string = this.mContext.getResources().getString(R.string._2130849885_res_0x7f02305d);
        if (i <= i3 && i >= i2) {
            string = UnitUtil.e(i, 1, 0);
        }
        return new SportEquipItemDetail.e(null, format, string);
    }

    private SportEquipItemDetail.e buildIndoorLandingIcon(Drawable drawable) {
        return new SportEquipItemDetail.e(drawable, null, null);
    }

    private SportEquipItemDetail.e buildIndoorLandingMethod(String str) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130840274_res_0x7f020ad2), str);
    }

    private SportEquipItemDetail.e buildEversionAmplitude(double d, int i, float f) {
        lbh itemConfig;
        if (f < 8.0f) {
            itemConfig = remindAngleNormal();
        } else {
            itemConfig = getItemConfig(i);
        }
        int i2 = (int) d;
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130840275_res_0x7f020ad3), this.mContext.getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, i2, Integer.valueOf(i2)), itemConfig);
    }

    private SportEquipItemDetail.e buildInstantaneousCadenceItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130843879_res_0x7f0218e7, this.mContext.getResources().getString(R.string._2130843486_res_0x7f02175e), this.mContext.getResources().getString(R.string._2130843497_res_0x7f021769)).trim(), dealWithValue(i, "buildInstantaneousCadenceItem is error"));
    }

    private lbh remindAngleNormal() {
        lbh lbhVar = new lbh();
        lbhVar.e(false);
        lbhVar.a(-1);
        lbhVar.b(-1);
        lbhVar.bVC_(null);
        return lbhVar;
    }

    private lbh remindAngleValueTooSmall() {
        lbh lbhVar = new lbh();
        lbhVar.e(true);
        lbhVar.a(-14774785);
        lbhVar.b(-14774785);
        lbhVar.bVC_(this.mContext.getResources().getDrawable(R.drawable._2131427551_res_0x7f0b00df));
        return lbhVar;
    }

    private lbh remindAngleValueTooLarge() {
        lbh lbhVar = new lbh();
        lbhVar.e(true);
        lbhVar.a(-53241);
        lbhVar.b(-53241);
        lbhVar.bVC_(this.mContext.getResources().getDrawable(R.drawable._2131427558_res_0x7f0b00e6));
        return lbhVar;
    }

    private SportEquipItemDetail.e buildSwingAngleItem(double d, int i, float f) {
        lbh itemConfig;
        if (f < 8.0f) {
            itemConfig = remindAngleNormal();
        } else {
            itemConfig = getItemConfig(i);
        }
        int i2 = (int) d;
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130840276_res_0x7f020ad4), this.mContext.getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, i2, Integer.valueOf(i2)), itemConfig);
    }

    private lbh getItemConfig(int i) {
        if (i == 1) {
            return remindAngleValueTooSmall();
        }
        if (i == 2) {
            return remindAngleValueTooLarge();
        }
        return remindAngleNormal();
    }

    private SportEquipItemDetail.e buildIndoorRunStepItem(int i) {
        return new SportEquipItemDetail.e(this.mSportType == 264 ? this.mContext.getResources().getDrawable(R.drawable._2131430237_res_0x7f0b0b5d) : null, this.mContext.getResources().getString(R.string._2130840233_res_0x7f020aa9), UnitUtil.e(i, 1, 0));
    }

    private SportEquipItemDetail.e buildIndoorEquipRunStepRateItem(int i) {
        Drawable drawable = this.mContext.getResources().getDrawable(R.drawable._2131430237_res_0x7f0b0b5d);
        String string = this.mContext.getResources().getString(R.string._2130840231_res_0x7f020aa7);
        String string2 = this.mContext.getResources().getString(R.string._2130851304_res_0x7f0235e8);
        if (i > 0) {
            string2 = UnitUtil.e(i, 1, 0);
        }
        return new SportEquipItemDetail.e(drawable, this.mContext.getString(R.string._2130843879_res_0x7f0218e7, string, this.mContext.getResources().getString(R.string._2130839766_res_0x7f0208d6)), string2);
    }

    private SportEquipItemDetail.e buildIndoorEquipRunSpeedItem(float f) {
        String string = this.mIsShowImperialUnit ? this.mContext.getResources().getString(R.string._2130839825_res_0x7f020911) : this.mContext.getResources().getString(R.string._2130839826_res_0x7f020912);
        String string2 = this.mContext.getResources().getString(R.string._2130851304_res_0x7f0235e8);
        if (f > 0.0f) {
            if (this.mIsShowImperialUnit) {
                string2 = UnitUtil.e(f * 0.6213712d, 1, 1);
            } else {
                string2 = UnitUtil.e(f, 1, 1);
            }
        }
        return new SportEquipItemDetail.e(null, string, string2);
    }

    private SportEquipItemDetail.e buildHeartRateItem(int i) {
        String string;
        String string2;
        Drawable drawable = this.mContext.getResources().getDrawable(R.drawable._2131430577_res_0x7f0b0cb1);
        if (i <= 0 || i > 220) {
            string = this.mContext.getResources().getString(R.string._2130851303_res_0x7f0235e7);
        } else {
            string = UnitUtil.e(i, 1, 0);
        }
        if (this.mSportType == 283) {
            string2 = this.mContext.getResources().getString(R.string._2130843879_res_0x7f0218e7, this.mContext.getResources().getString(R.string._2130840232_res_0x7f020aa8), this.mContext.getResources().getString(R.string.IDS_main_watch_heart_rate_unit_string)).trim();
        } else {
            string2 = this.mContext.getResources().getString(R.string._2130840232_res_0x7f020aa8);
        }
        return new SportEquipItemDetail.e(drawable, string2, string);
    }

    private SportEquipItemDetail.e buildCalorieItem(int i) {
        return new SportEquipItemDetail.e(null, nsf.h(R.string._2130847442_res_0x7f0226d2), UnitUtil.e(i, 1, 0));
    }

    private SportEquipItemDetail.e buildTimeItem(int i) {
        return new SportEquipItemDetail.e(null, this.mContext.getResources().getString(R.string._2130839907_res_0x7f020963), UnitUtil.d(i));
    }

    private SportEquipItemDetail.e buildDistanceItem(int i) {
        String string;
        double b;
        boolean h = UnitUtil.h();
        if (h) {
            string = this.mContext.getResources().getString(R.string._2130851306_res_0x7f0235ea);
        } else {
            string = this.mContext.getResources().getString(R.string._2130840225_res_0x7f020aa1);
        }
        if (h) {
            b = jed.c(i);
        } else {
            b = jed.b(i);
        }
        return new SportEquipItemDetail.e(null, string, UnitUtil.e(b, 1, 2));
    }

    protected void fillTitleContainer(SportEquipItemDrawer sportEquipItemDrawer) {
        float dimension;
        int itemHeight;
        float itemWidth;
        List<SportEquipItemDetail.e> list = this.mItemDataList;
        if (list == null || list.isEmpty()) {
            LogUtil.b(TAG, "fillTitleContainer mItemDataList is null");
            return;
        }
        if (sportEquipItemDrawer == null) {
            LogUtil.b(TAG, "fillTitleContainer container is null");
            return;
        }
        sportEquipItemDrawer.removeAllViews();
        if (!(this.mContext.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager)) {
            LogUtil.b(TAG, "object is invalid type");
            return;
        }
        boolean ag = nsn.ag(this.mContext);
        if (ag) {
            dimension = nsn.c(this.mContext, 50.0f);
        } else {
            dimension = this.mContext.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
        }
        float f = dimension;
        if (this.mSportType == 283) {
            itemHeight = nsn.c(this.mContext, SKIPPING_HEIGHT_SPAN);
            itemWidth = sportEquipItemDrawer.getRopeSkipItemWidth();
        } else {
            itemHeight = ((int) sportEquipItemDrawer.getItemHeight()) + nsn.c(this.mContext, ITEM_SPAN);
            itemWidth = sportEquipItemDrawer.getItemWidth();
        }
        int i = itemHeight;
        int i2 = (int) itemWidth;
        int size = this.mItemDataList.size();
        for (int i3 = 0; i3 < size; i3++) {
            SportEquipItemDetail acquireSportEquipItemDetail = acquireSportEquipItemDetail(ag, (int) f, i, i2, i3);
            if (sportEquipItemDrawer.getChildAt(i3) != null) {
                sportEquipItemDrawer.removeViewAt(i3);
            }
            sportEquipItemDrawer.addView(acquireSportEquipItemDetail, i3);
        }
    }

    private SportEquipItemDetail acquireSportEquipItemDetail(boolean z, int i, int i2, int i3, int i4) {
        SportEquipItemDetail.e eVar = this.mItemDataList.get(i4);
        SportEquipItemDetail sportEquipItemDetail = new SportEquipItemDetail(this.mContext, this.mSportType, i3, eVar.b());
        changeItemShowRule(sportEquipItemDetail, z);
        sportEquipItemDetail.setGroupSize(i3, i2);
        sportEquipItemDetail.setItemView(eVar);
        if (eVar.a() != null && eVar.a().equals(this.mContext.getString(R.string._2130840274_res_0x7f020ad2))) {
            sportEquipItemDetail.a(nsn.c(this.mContext, 30.0f));
        }
        if (eVar.a() == null && eVar.b() == null) {
            sportEquipItemDetail.d(i, this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362058_res_0x7f0a010a));
        }
        return sportEquipItemDetail;
    }

    private void changeItemShowRule(SportEquipItemDetail sportEquipItemDetail, boolean z) {
        if (this.mSportType != 283 && z) {
            sportEquipItemDetail.setGravity(48);
            Context context = this.mContext;
            sportEquipItemDetail.d(context, context.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e));
        }
        if (z) {
            return;
        }
        if (this.mSportType == 283) {
            sportEquipItemDetail.d(this.mContext, 0.0f);
            return;
        }
        sportEquipItemDetail.setGravity(48);
        Context context2 = this.mContext;
        sportEquipItemDetail.d(context2, context2.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e));
    }
}
