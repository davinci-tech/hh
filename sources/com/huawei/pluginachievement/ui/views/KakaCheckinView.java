package com.huawei.pluginachievement.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.KakaCheckinRecord;
import defpackage.koq;
import defpackage.mct;
import defpackage.mle;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class KakaCheckinView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private KakaCheckinItemView f8465a;
    private KakaCheckinItemView b;
    private KakaCheckinItemView c;
    private KakaCheckinItemView d;
    private Context e;
    private List<KakaCheckinItemView> g;

    public KakaCheckinView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new ArrayList(7);
        this.e = context;
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.kaka_checkin_layout, this);
        KakaCheckinItemView kakaCheckinItemView = (KakaCheckinItemView) inflate.findViewById(R.id.checkin_date_first);
        this.b = kakaCheckinItemView;
        kakaCheckinItemView.setAlpha(0.5f);
        this.c = (KakaCheckinItemView) inflate.findViewById(R.id.checkin_date_second);
        this.f8465a = (KakaCheckinItemView) inflate.findViewById(R.id.checkin_date_third);
        this.d = (KakaCheckinItemView) inflate.findViewById(R.id.checkin_date_fourth);
        this.g.add(this.b);
        this.g.add(this.c);
        this.g.add(this.f8465a);
        this.g.add(this.d);
        int c = nsn.c(getContext(), 10.0f);
        if (nsn.a(3.1f)) {
            LogUtil.a("KakaCheckinView", "isLargeScaledDensityMode wide = ", 5);
            c = nsn.c(getContext(), 5.0f);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(c, nsn.c(getContext(), nsn.c(getContext(), 1.0f)));
        inflate.findViewById(R.id.itemStoneFirst).setLayoutParams(layoutParams);
        inflate.findViewById(R.id.itemStoneSecond).setLayoutParams(layoutParams);
        inflate.findViewById(R.id.itemStoneThird).setLayoutParams(layoutParams);
    }

    public int b(List<KakaCheckinRecord> list) {
        if (koq.b(list)) {
            LogUtil.h("KakaCheckinView", "check record is empty");
            return 0;
        }
        LogUtil.a("KakaCheckinView", "check in records = ", Integer.valueOf(list.size()));
        c();
        return c(list);
    }

    private void c() {
        String a2;
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < this.g.size(); i++) {
            KakaCheckinItemView kakaCheckinItemView = this.g.get(i);
            if (i == 1) {
                a2 = this.e.getString(R.string._2130841407_res_0x7f020f3f);
                kakaCheckinItemView.setDateTextColor(R.color._2131298070_res_0x7f090716);
            } else if (i == 0) {
                a2 = UnitUtil.a("M/d", currentTimeMillis - 86400000);
            } else if (i == 2) {
                a2 = UnitUtil.a("M/d", 86400000 + currentTimeMillis);
            } else {
                a2 = UnitUtil.a("M/d", ((i - 1) * 86400000) + currentTimeMillis);
            }
            kakaCheckinItemView.setDate(a2);
        }
    }

    private int c(List<KakaCheckinRecord> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            KakaCheckinRecord kakaCheckinRecord = list.get(i2);
            int a2 = a(kakaCheckinRecord.getRecordDay());
            LogUtil.a("KakaCheckinView", "check in recordTemp = ", Integer.valueOf(kakaCheckinRecord.getConDays()), "   ", kakaCheckinRecord.getRecordDay());
            if (a2 >= 0 && a2 < this.g.size()) {
                KakaCheckinItemView kakaCheckinItemView = this.g.get(a2);
                if (a2 <= 1) {
                    if (kakaCheckinRecord.getConDays() > 0) {
                        i = Math.max(i, kakaCheckinRecord.getConDays());
                        kakaCheckinItemView.setCheckeinStatus(kakaCheckinRecord.getKaka(), true);
                    } else {
                        kakaCheckinItemView.setCheckeinStatus(kakaCheckinRecord.getKaka(), false);
                    }
                    if (a2 == 1 && kakaCheckinRecord.getConDays() > 0) {
                        LogUtil.a("KakaCheckinView", "CheckedIn:date == " + kakaCheckinRecord.getRecordDay());
                        mct.b(this.e, "kakaLastCheckInTime", kakaCheckinRecord.getRecordDay());
                        mct.b(this.e, "kakaCheckedDays", String.valueOf(i));
                    }
                } else {
                    kakaCheckinItemView.setCheckeinStatus(kakaCheckinRecord.getKaka(), false);
                }
            }
        }
        return i;
    }

    private int a(String str) {
        return (int) (((mle.e(str) + 86400000) - nsj.e(System.currentTimeMillis(), 0)) / 86400000);
    }
}
