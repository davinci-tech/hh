package com.huawei.ui.commonui.chartanimation;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize;
import defpackage.nol;
import defpackage.nom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class TweenAnimatiionMgr {
    private UserLevelView c = UserLevelView.CHART_UNKNOWN;
    private List<HwHealthBaseScrollBarLineChart> d = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private Map<UserLevelView, HwHealthBaseScrollBarLineChart> f8792a = new HashMap();
    private OnSwitchLevelView e = null;
    private nol b = new nol();

    public interface OnSwitchLevelView {
        void onNotifySwitchStatus(UserLevelView userLevelView, UserLevelView userLevelView2, float f);
    }

    public void a(UserLevelView userLevelView, HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        this.d.add(hwHealthBaseScrollBarLineChart);
        this.f8792a.put(userLevelView, hwHealthBaseScrollBarLineChart);
    }

    public void d(OnSwitchLevelView onSwitchLevelView) {
        this.e = onSwitchLevelView;
    }

    public void e(UserLevelView userLevelView) {
        this.c = userLevelView;
    }

    public boolean b(UserLevelView userLevelView) {
        return this.f8792a.get(userLevelView) != null;
    }

    public void c(final UserLevelView userLevelView) {
        c();
        final UserLevelView userLevelView2 = this.c;
        final HwHealthBaseScrollBarLineChart d = d();
        final HwHealthBaseScrollBarLineChart d2 = d(userLevelView);
        final long a2 = a(d.queryMarkerViewTimeMills(), userLevelView);
        final AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(new Handler(Looper.getMainLooper())) { // from class: com.huawei.ui.commonui.chartanimation.TweenAnimatiionMgr.3
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize
            public void onFailed(int i) {
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize
            public void onSuccess(Map map) {
            }
        };
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.Action() { // from class: com.huawei.ui.commonui.chartanimation.TweenAnimatiionMgr.1
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public int getFailedValue() {
                return 0;
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                TweenAnimatiionMgr.this.b.cCv_(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.commonui.chartanimation.TweenAnimatiionMgr.1.4
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float a3 = TweenAnimatiionMgr.this.b.a();
                        TweenAnimatiionMgr.this.a(d, d2);
                        boolean blinkToUnixTime = d2.blinkToUnixTime((int) (a2 / 60000));
                        d2.highlightDefValue(-1, false);
                        if (a3 == 0.0f) {
                            TweenAnimatiionMgr.this.c = userLevelView;
                        }
                        TweenAnimatiionMgr.this.a(userLevelView2, userLevelView, a3);
                        if (blinkToUnixTime) {
                            return;
                        }
                        float translationX = d2.getTranslationX();
                        d2.setTranslationX(1.0f + translationX);
                        d2.setTranslationX(translationX);
                        asyncSelectorSerialize.next(null);
                    }
                }, 800);
            }
        });
        asyncSelectorSerialize.run();
    }

    private long a(long j, UserLevelView userLevelView) {
        if (userLevelView == UserLevelView.CHART_DAY) {
            return nom.b(j);
        }
        if (userLevelView == UserLevelView.CHART_WEEK) {
            return nom.n(j);
        }
        if (userLevelView == UserLevelView.CHART_MONTH) {
            return nom.j(j);
        }
        if (userLevelView == UserLevelView.CHART_YEAR) {
            return nom.s(j);
        }
        if (userLevelView == UserLevelView.CHART_ALL) {
            throw new RuntimeException("unsupport showMode in computeSpecifiedLevelTimeBoarder");
        }
        throw new RuntimeException("unsupport showMode in computeSpecifiedLevelTimeBoarder");
    }

    private HwHealthBaseScrollBarLineChart d(UserLevelView userLevelView) {
        HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = this.f8792a.get(userLevelView);
        if (hwHealthBaseScrollBarLineChart != null) {
            return hwHealthBaseScrollBarLineChart;
        }
        throw new RuntimeException("level unknown,queryLevelChart error:" + userLevelView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(UserLevelView userLevelView, UserLevelView userLevelView2, float f) {
        this.e.onNotifySwitchStatus(userLevelView, userLevelView2, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart2) {
        if (!hwHealthBaseScrollBarLineChart.isMarkerViewEnable() || !hwHealthBaseScrollBarLineChart2.isMarkerViewEnable()) {
            throw new RuntimeException("TweenAnimatiionMgr base on markerview,error markerview disable!!!");
        }
        if (!hwHealthBaseScrollBarLineChart.containsMarkViewShow()) {
            throw new RuntimeException("TweenAnimatiionMgr base on markerview,error not contains markerview!!!");
        }
        if (!hwHealthBaseScrollBarLineChart.isMarkerViewTimeSelected()) {
            throw new RuntimeException("TweenAnimatiionMgr base on markerview,error markerview time not validate!!!");
        }
    }

    private void c() {
        if (this.d.size() < 1) {
            throw new RuntimeException("checkViewPosition charts size less than one,error");
        }
        int[] iArr = new int[2];
        this.d.get(0).getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        if (iArr[0] == 0 && iArr[1] == 0) {
            return;
        }
        Iterator<HwHealthBaseScrollBarLineChart> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().getLocationInWindow(iArr2);
            int i = iArr2[0];
            if (i != 0 || iArr2[1] != 0) {
                if (iArr[0] != i || iArr[1] != iArr2[1]) {
                    throw new RuntimeException("charts's LocationInWindow not same,error");
                }
            }
        }
    }

    public HwHealthBaseScrollBarLineChart d() {
        HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = this.f8792a.get(this.c);
        if (hwHealthBaseScrollBarLineChart != null) {
            return hwHealthBaseScrollBarLineChart;
        }
        throw new RuntimeException("level unknown,getCurrentLevelChart error");
    }

    public enum UserLevelView {
        CHART_ALL(5),
        CHART_YEAR(4),
        CHART_MONTH(3),
        CHART_WEEK(2),
        CHART_DAY(1),
        CHART_UNKNOWN(0);

        int mLevel;

        UserLevelView(int i) {
            this.mLevel = i;
        }

        public static UserLevelView query(DataInfos dataInfos) {
            if (dataInfos.isDayData()) {
                return CHART_DAY;
            }
            if (dataInfos.isWeekData()) {
                return CHART_WEEK;
            }
            if (dataInfos.isMonthData()) {
                return CHART_MONTH;
            }
            if (dataInfos.isYearData()) {
                return CHART_YEAR;
            }
            if (dataInfos.isAllData()) {
                return CHART_ALL;
            }
            throw new RuntimeException("query UserLevelView unknown Exception");
        }
    }
}
