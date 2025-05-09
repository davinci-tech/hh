package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.hji;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class BasketballScoreView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private String[] f3774a;
    private ImageView b;
    private int c;
    private int d;
    private HealthDivider e;
    private LinearLayout f;
    private HealthTextView g;
    private int[] h;
    private RadarView i;
    private ImageView j;
    private HealthTextView n;
    private String[] o;

    public BasketballScoreView(Context context) {
        super(context);
        this.d = 0;
        a(context);
    }

    public BasketballScoreView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0;
        a(context);
    }

    public BasketballScoreView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        a(context);
    }

    private void a(final Context context) {
        if (context == null) {
            LogUtil.b("Track_BasketballScoreView", "context is null");
            return;
        }
        View.inflate(context, R.layout.track_detail_basketball_score, this);
        this.f3774a = new String[]{context.getString(R.string._2130843325_res_0x7f0216bd), context.getString(R.string._2130843326_res_0x7f0216be), context.getString(R.string._2130843327_res_0x7f0216bf), context.getString(R.string._2130843328_res_0x7f0216c0), context.getString(R.string._2130843329_res_0x7f0216c1)};
        this.o = new String[]{context.getString(R.string._2130843287_res_0x7f021697), context.getString(R.string._2130843288_res_0x7f021698), context.getString(R.string._2130843286_res_0x7f021696), context.getString(R.string._2130843285_res_0x7f021695), context.getString(R.string._2130843289_res_0x7f021699)};
        this.i = (RadarView) findViewById(R.id.radar_view);
        this.n = (HealthTextView) findViewById(R.id.total_performance_with_unit);
        this.b = (ImageView) findViewById(R.id.level_ward);
        this.f = (LinearLayout) findViewById(R.id.total_performance_layout);
        this.j = (ImageView) findViewById(R.id.basketball_performance);
        this.e = (HealthDivider) findViewById(R.id.performance_divider);
        this.g = (HealthTextView) findViewById(R.id.performance_score_info);
        this.n.setText(context.getResources().getString(R.string._2130843200_res_0x7f021640));
        if (nsn.ag(getContext()) && (this.b.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams.setMargins(0, 0, nsn.c(context, 100.0f), 0);
            this.b.setLayoutParams(layoutParams);
        }
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.view.BasketballScoreView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                hji.d(7, context);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void setBasketballStamp(boolean z) {
        if (z && LanguageUtil.m(getContext())) {
            int a2 = hji.a(this.d, this.h);
            if (a2 == 0) {
                this.b.setVisibility(8);
                return;
            } else {
                this.b.setImageDrawable(getContext().getResources().getDrawable(a2));
                return;
            }
        }
        this.b.setVisibility(8);
    }

    public void setActiveTime(int i) {
        this.d = i;
    }

    public void setRadarScore(int[] iArr) {
        if (iArr == null) {
            LogUtil.b("Track_BasketballScoreView", "score is null");
            return;
        }
        this.i.setPercentOfMap(iArr);
        this.h = (int[]) iArr.clone();
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(Integer.valueOf(getResources().getColor(R.color._2131296494_res_0x7f0900ee)));
        arrayList.add(Integer.valueOf(getResources().getColor(R.color._2131296496_res_0x7f0900f0)));
        arrayList.add(Integer.valueOf(getResources().getColor(R.color._2131296497_res_0x7f0900f1)));
        arrayList.add(Integer.valueOf(getResources().getColor(R.color._2131296495_res_0x7f0900ef)));
        this.i.setColorList(arrayList);
    }

    public void setAverageScore(int i) {
        this.i.setAverageScore(i);
        this.c = i;
    }

    public void setTotalScoreGone() {
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.e.setVisibility(8);
    }

    public void setScoreInfoText(Context context) {
        int[] iArr;
        if (context == null || (iArr = this.h) == null || iArr.length != 5) {
            LogUtil.a("Track_BasketballScoreView", "setScoreInfoText context == null || mScores == null || mScores.length != 5");
            return;
        }
        if (Utils.o() || !LanguageUtil.m(context)) {
            LogUtil.a("Track_BasketballScoreView", "setScoreInfoText isOversea and not chineseSimplified");
            this.g.setVisibility(8);
            this.e.setVisibility(8);
            return;
        }
        HashMap hashMap = new HashMap(5);
        int i = 0;
        while (true) {
            int[] iArr2 = this.h;
            if (i >= iArr2.length) {
                break;
            }
            int i2 = iArr2[i];
            if (i2 < 80) {
                hashMap.put(Integer.valueOf(i), Integer.valueOf(i2));
            }
            i++;
        }
        if (hashMap.size() == 0) {
            this.g.setText(context.getString(R.string._2130843284_res_0x7f021694));
            LogUtil.a("Track_BasketballScoreView", "setScoreInfoText scoresMap.size() == 0");
        } else {
            ArrayList arrayList = new ArrayList(hashMap.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<Integer, Integer>>() { // from class: com.huawei.healthcloud.plugintrack.ui.view.BasketballScoreView.2
                @Override // java.util.Comparator
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public int compare(Map.Entry<Integer, Integer> entry, Map.Entry<Integer, Integer> entry2) {
                    return entry.getValue().compareTo(entry2.getValue());
                }
            });
            d(context, arrayList);
        }
    }

    private void d(Context context, List<Map.Entry<Integer, Integer>> list) {
        String c;
        int size = list.size();
        int i = this.c;
        if (i >= 80) {
            c = context.getString(R.string._2130843284_res_0x7f021694);
        } else if (i >= 70) {
            if (size >= 3) {
                c = a(list, context.getString(R.string._2130843283_res_0x7f021693));
            } else if (size == 2) {
                c = e(list, context.getString(R.string._2130843282_res_0x7f021692));
            } else {
                c = c(list, context.getString(R.string._2130843281_res_0x7f021691));
            }
        } else if (i >= 60) {
            if (size >= 3) {
                c = a(list, context.getString(R.string._2130843280_res_0x7f021690));
            } else if (size == 2) {
                c = e(list, context.getString(R.string._2130843279_res_0x7f02168f));
            } else {
                c = c(list, context.getString(R.string._2130843278_res_0x7f02168e));
            }
        } else if (i >= 50) {
            if (size >= 3) {
                c = a(list, context.getString(R.string._2130843277_res_0x7f02168d));
            } else if (size == 2) {
                c = e(list, context.getString(R.string._2130849059_res_0x7f022d23));
            } else {
                c = c(list, context.getString(R.string._2130849058_res_0x7f022d22));
            }
        } else if (size >= 3) {
            c = a(list, context.getString(R.string._2130849057_res_0x7f022d21));
        } else if (size == 2) {
            c = e(list, context.getString(R.string._2130849056_res_0x7f022d20));
        } else {
            c = c(list, context.getString(R.string._2130849055_res_0x7f022d1f));
        }
        this.g.setText(c);
    }

    private String a(List<Map.Entry<Integer, Integer>> list, String str) {
        int intValue = list.get(0).getKey().intValue();
        int intValue2 = list.get(1).getKey().intValue();
        int intValue3 = list.get(2).getKey().intValue();
        int min = Math.min(intValue, Math.min(intValue2, intValue3));
        int max = Math.max(intValue, Math.max(intValue2, intValue3));
        int i = (((intValue + intValue2) + intValue3) - min) - max;
        String[] strArr = this.f3774a;
        String str2 = strArr[min];
        String str3 = strArr[i];
        String str4 = strArr[max];
        String[] strArr2 = this.o;
        return String.format(str, str2, str3, str4, strArr2[min], strArr2[i], strArr2[max]);
    }

    private String e(List<Map.Entry<Integer, Integer>> list, String str) {
        int intValue = list.get(0).getKey().intValue();
        int intValue2 = list.get(1).getKey().intValue();
        if (intValue > intValue2) {
            intValue = intValue2;
            intValue2 = intValue;
        }
        String[] strArr = this.f3774a;
        String str2 = strArr[intValue];
        String str3 = strArr[intValue2];
        String[] strArr2 = this.o;
        return String.format(str, str2, str3, strArr2[intValue], strArr2[intValue2]);
    }

    private String c(List<Map.Entry<Integer, Integer>> list, String str) {
        int intValue = list.get(0).getKey().intValue();
        return String.format(str, this.f3774a[intValue], this.o[intValue]);
    }
}
