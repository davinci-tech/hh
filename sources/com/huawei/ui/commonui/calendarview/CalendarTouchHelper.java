package com.huawei.ui.commonui.calendarview;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.huawei.ui.commonui.R$string;
import defpackage.nku;
import defpackage.nsf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class CalendarTouchHelper extends ExploreByTouchHelper {

    /* renamed from: a, reason: collision with root package name */
    private String f8769a;
    private List<nku> b;
    private final int c;
    private Map<String, Integer> d;
    private String e;
    private String g;
    private String h;

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
        return false;
    }

    public CalendarTouchHelper(View view) {
        super(view);
        this.b = new ArrayList();
        this.d = new HashMap();
        this.h = nsf.h(R$string.IDS_detail_sleep_bottom_btu_day_txt);
        this.g = null;
        this.e = null;
        this.f8769a = null;
        this.c = -1;
    }

    public void d(nku nkuVar) {
        this.b.add(nkuVar);
    }

    public int d() {
        return this.b.size();
    }

    public boolean a(String str) {
        return this.d.containsKey(str);
    }

    public void a(String str, Integer num) {
        this.d.put(str, num);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public int getVirtualViewAt(float f, float f2) {
        int i = (int) f;
        int i2 = (int) f2;
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            nku nkuVar = this.b.get(i3);
            int b = nkuVar.b();
            if (b * b > ((i - nkuVar.e()) * (i - nkuVar.e())) + ((i2 - nkuVar.c()) * (i2 - nkuVar.c()))) {
                return i3;
            }
        }
        return -1;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void getVisibleVirtualViews(List<Integer> list) {
        for (int i = 0; i < this.b.size(); i++) {
            list.add(Integer.valueOf(i));
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (i < 0 || i >= this.b.size()) {
            return;
        }
        nku nkuVar = this.b.get(i);
        int e = nkuVar.e();
        int b = nkuVar.b();
        int e2 = nkuVar.e();
        int b2 = nkuVar.b();
        accessibilityNodeInfoCompat.setBoundsInParent(new Rect(e - b, nkuVar.c() - nkuVar.b(), e2 + b2, nkuVar.c() + nkuVar.b()));
        c(nkuVar, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setSelected(nkuVar.h());
    }

    public void d(Object[] objArr) {
        if (objArr.length != 4) {
            return;
        }
        Object obj = objArr[0];
        if (obj instanceof String) {
            this.h = (String) obj;
        }
        Object obj2 = objArr[1];
        if (obj2 instanceof String) {
            this.g = (String) obj2;
        }
        Object obj3 = objArr[2];
        if (obj3 instanceof String) {
            this.e = (String) obj3;
        }
        Object obj4 = objArr[3];
        if (obj4 instanceof String) {
            this.f8769a = (String) obj4;
        }
    }

    private void c(nku nkuVar, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String d;
        if (!TextUtils.isEmpty(this.g) && nkuVar.h()) {
            d = nsf.b(R$string.IDS_two_parts, nkuVar.d(), this.g);
        } else if (!TextUtils.isEmpty(this.h) && nkuVar.g()) {
            d = nsf.b(R$string.IDS_two_parts, nkuVar.d(), this.h);
        } else if (!TextUtils.isEmpty(this.e) && nkuVar.a()) {
            d = nsf.b(R$string.IDS_two_parts, nkuVar.d(), this.e);
        } else if (!TextUtils.isEmpty(this.f8769a)) {
            d = nsf.b(R$string.IDS_two_parts, nkuVar.d(), this.f8769a);
        } else {
            d = nkuVar.d();
        }
        accessibilityNodeInfoCompat.setContentDescription(d);
    }

    public void a(int i, nku nkuVar) {
        this.b.set(i, nkuVar);
    }

    public int b(String str) {
        return this.d.get(str).intValue();
    }
}
