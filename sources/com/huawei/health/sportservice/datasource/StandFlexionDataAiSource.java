package com.huawei.health.sportservice.datasource;

import android.util.Pair;
import androidx.collection.SimpleArrayMap;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.pluginsport.aiforwardflex.OnSportCodeListenerWrapper;
import defpackage.kxa;
import health.compact.a.CommonUtil;

@SportComponentType(classify = 3, name = ComponentName.STAND_FLEXION_DATA_AI_SOURCE)
/* loaded from: classes8.dex */
public class StandFlexionDataAiSource extends BaseSource implements SportLifecycle, OnSportCodeListenerWrapper {
    private static final String TAG = "SportService_StandFlexionDataAiSource";
    private int mHighestScore;
    private int mInitCurrentScore;
    private long mInitCurrentTime;
    private int mTempMaxScore;
    private final kxa mPoint = new kxa();
    private boolean mIsNeedPlayDistinguish = true;
    private boolean mIsUpdateMaxScore = false;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().setParas(SportParamsType.AI_SPORT_EXAM_SOURCE_LISTENER, this);
    }

    @Override // com.huawei.pluginsport.aiforwardflex.OnSportCodeListenerWrapper
    public void getData(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap == null) {
            return;
        }
        getStatus(simpleArrayMap);
        if (BaseSportManager.getInstance().getStatus() != 1) {
            return;
        }
        getBodyPoint(simpleArrayMap);
        getScore(simpleArrayMap);
    }

    private void getScore(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey(JsUtil.SCORE)) {
            Object obj = simpleArrayMap.get(JsUtil.SCORE);
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                boolean isLegalScore = isLegalScore(intValue);
                getHighestScore(isLegalScore);
                sendDistinguishLegalScoreMsg(isLegalScore);
                BaseSportManager.getInstance().stagingAndNotification("STAND_FLEXION_SCORE_DATA", Integer.valueOf(intValue));
            }
        }
    }

    private void sendDistinguishLegalScoreMsg(boolean z) {
        if (!z) {
            BaseSportManager.getInstance().stagingAndNotification("STAND_FLEXION_IS_LEGAL_SCORE", false);
            this.mIsNeedPlayDistinguish = true;
        } else if (this.mIsNeedPlayDistinguish || this.mIsUpdateMaxScore) {
            BaseSportManager.getInstance().stagingAndNotification("STAND_FLEXION_IS_LEGAL_SCORE", true);
            this.mIsNeedPlayDistinguish = false;
        }
    }

    private boolean isLegalScore(int i) {
        if (CommonUtil.c(this.mInitCurrentTime)) {
            this.mInitCurrentTime = System.currentTimeMillis();
        }
        int i2 = this.mInitCurrentScore;
        boolean z = false;
        if (i < i2 - 10 || i > i2 + 10) {
            this.mInitCurrentScore = i;
            this.mInitCurrentTime = System.currentTimeMillis();
            this.mTempMaxScore = 0;
            return false;
        }
        if (i > this.mTempMaxScore) {
            this.mTempMaxScore = i;
            this.mIsUpdateMaxScore = true;
        } else {
            this.mIsUpdateMaxScore = false;
        }
        if (System.currentTimeMillis() - this.mInitCurrentTime >= 2000 && i > 0) {
            z = true;
        }
        LogUtil.a(TAG, "isLegal:", Boolean.valueOf(z), "mTempMaxScore:", Integer.valueOf(this.mTempMaxScore));
        return z;
    }

    private void getHighestScore(boolean z) {
        LogUtil.a(TAG, "getHighestScore mTempMaxScore:", Integer.valueOf(this.mTempMaxScore), ", isLegal:", Boolean.valueOf(z));
        if (!z || this.mTempMaxScore <= 0) {
            return;
        }
        BaseSportManager.getInstance().stagingAndNotification("STAND_FLEXION_HIGHEST_SCORE_TEMP", Integer.valueOf(this.mTempMaxScore));
    }

    private void getBodyPoint(SimpleArrayMap simpleArrayMap) {
        boolean z;
        boolean z2 = true;
        if (getGroupPoint(simpleArrayMap, "footX", "footY") != null) {
            this.mPoint.b(((Float) r0.first).intValue());
            this.mPoint.j(((Float) r0.second).intValue());
            z = true;
        } else {
            z = false;
        }
        if (getGroupPoint(simpleArrayMap, "ankleX", "ankleY") != null) {
            this.mPoint.d(((Float) r2.first).intValue());
            this.mPoint.c(((Float) r2.second).intValue());
            z = true;
        }
        if (getGroupPoint(simpleArrayMap, "handX", "handY") != null) {
            this.mPoint.i(((Float) r2.first).intValue());
            this.mPoint.g(((Float) r2.second).intValue());
            z = true;
        }
        if (getGroupPoint(simpleArrayMap, "kneeX", "kneeY") != null) {
            this.mPoint.k(((Float) r2.first).intValue());
            this.mPoint.l(((Float) r2.second).intValue());
            z = true;
        }
        if (getGroupPoint(simpleArrayMap, "hipX", "hipY") != null) {
            this.mPoint.h(((Float) r2.first).intValue());
            this.mPoint.f(((Float) r2.second).intValue());
        } else {
            z2 = z;
        }
        if (isChangeGroupDirectionPoint(simpleArrayMap, HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION) || z2) {
            BaseSportManager.getInstance().stagingAndNotification("BODY_POINT_DATA", this.mPoint);
        }
    }

    private Pair<Float, Float> getGroupPoint(SimpleArrayMap simpleArrayMap, String str, String str2) {
        if (!simpleArrayMap.containsKey(str) || !simpleArrayMap.containsKey(str2)) {
            return null;
        }
        Object obj = simpleArrayMap.get(str);
        Object obj2 = simpleArrayMap.get(str2);
        if ((obj instanceof Float) && (obj2 instanceof Float)) {
            return new Pair<>(Float.valueOf(((Float) obj).floatValue()), Float.valueOf(((Float) obj2).floatValue()));
        }
        return null;
    }

    private boolean isChangeGroupDirectionPoint(SimpleArrayMap simpleArrayMap, String str) {
        if (!simpleArrayMap.containsKey(str)) {
            return false;
        }
        Object obj = simpleArrayMap.get(str);
        if (!(obj instanceof Integer)) {
            return false;
        }
        this.mPoint.e(((Integer) obj).intValue());
        return true;
    }

    private void getStatus(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("errCode")) {
            Object obj = simpleArrayMap.get("errCode");
            if (obj instanceof Integer) {
                BaseSportManager.getInstance().stagingAndNotification("STATUS_CODE_DATA", Integer.valueOf(((Integer) obj).intValue()));
            }
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
