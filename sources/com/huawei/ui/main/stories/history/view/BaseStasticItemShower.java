package com.huawei.ui.main.stories.history.view;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;
import java.util.Map;

/* loaded from: classes7.dex */
public abstract class BaseStasticItemShower implements InterfaceItemDataShower {
    protected static final double DEFAULT_VALUE = 0.0d;
    protected static final int NUMBER_LIMIT = 100000;
    protected static final String TAG = "Track_BaseStasticItemShower";
    protected static final double TIME_SCALE = 60.0d;
    protected static final int UNIT_FACTOR_FOR_SHORT = 10000;
    protected boolean mIsChinese;
    protected Map<String, Double> mItemDataMap;
    protected int mSportType;

    public abstract String processDataToString(double d);

    public abstract double standardize();

    @Override // com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticData() {
        return processDataToString(standardize());
    }

    @Override // com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public void setBaseInformation(Map<String, Double> map, int i) {
        this.mItemDataMap = map;
        this.mSportType = i;
        this.mIsChinese = LanguageUtil.m(BaseApplication.getContext());
    }

    @Override // com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public int getSportType() {
        return this.mSportType;
    }

    protected boolean isChiefDataDistanceSportType() {
        int i = this.mSportType;
        return i == 217 || i == 260 || i == 512 || i == 257 || i == 258 || i == 259 || i == 262;
    }

    protected boolean isDataMapEmpty(Map<String, Double> map) {
        if (map == null) {
            LogUtil.b(TAG, "map is null");
            return true;
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getKey() == null) {
                LogUtil.b(TAG, "map key is null");
                return true;
            }
            if (entry.getValue() == null) {
                LogUtil.b(TAG, "map value is null");
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        return "--";
    }

    @Override // com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        return "--";
    }
}
