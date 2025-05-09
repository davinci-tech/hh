package com.huawei.uikit.phone.hwsubtab.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import com.huawei.health.R;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwKeyEventDetector;
import defpackage.smy;

/* loaded from: classes7.dex */
public class HwSubTabWidget extends com.huawei.uikit.hwsubtab.widget.HwSubTabWidget {

    /* renamed from: a, reason: collision with root package name */
    private int f10791a;
    private HwColumnSystem c;
    private String d;
    private int f;
    private int g;
    private SubTabView h;
    private float i;
    private boolean j;
    private Context o;

    public class SubTabView extends HwSubTabWidget.SubTabView {
        public SubTabView(Context context, smy smyVar) {
            super(context, smyVar);
        }

        @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.SubTabView
        public void d() {
            if (HwSubTabWidget.this.c.f() >= 8) {
                setPadding(HwSubTabWidget.this.getSubTabItemPaddingSecondary(), 0, HwSubTabWidget.this.getSubTabItemPaddingSecondary(), 0);
                HwSubTabWidget.this.setSubTabLeftScrollPadding(32);
            } else {
                setPadding(HwSubTabWidget.this.getSubTabItemPadding(), 0, HwSubTabWidget.this.getSubTabItemPadding(), 0);
                HwSubTabWidget.this.setSubTabLeftScrollPadding(28);
            }
        }

        @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.SubTabView, android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            if (accessibilityNodeInfo == null) {
                return;
            }
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (getEventBadgeDrawable() == null || getEventBadgeDrawable().d() == 0) {
                return;
            }
            accessibilityNodeInfo.setHintText(HwSubTabWidget.this.d);
        }
    }

    public HwSubTabWidget(Context context) {
        this(context, null);
    }

    public int getStartOriginPadding() {
        return this.b.getStartOriginPadding();
    }

    public int getStartScrollPadding() {
        return this.b.getStartScrollPadding();
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.j) {
            a(this.c);
        } else {
            b(this.c);
        }
    }

    public HwSubTabWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10791a = -1;
        ejv_(context, attributeSet);
    }

    private void a(HwColumnSystem hwColumnSystem) {
        hwColumnSystem.e(-1);
        hwColumnSystem.d(this.o, this.f, this.g, this.i);
    }

    private void ejv_(Context context, AttributeSet attributeSet) {
        this.o = context;
        this.d = getResources().getString(R.string._2130851406_res_0x7f02364e);
        HwColumnSystem hwColumnSystem = new HwColumnSystem(context);
        this.c = hwColumnSystem;
        hwColumnSystem.e(this.f10791a);
        this.c.e(context);
        b();
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SubTabView d(smy smyVar) {
        SubTabView subTabView = new SubTabView(getContext(), smyVar);
        this.h = subTabView;
        return subTabView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public HwKeyEventDetector d() {
        return new HwKeyEventDetector(getContext());
    }

    public HwSubTabWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10791a = -1;
        ejv_(context, attributeSet);
    }

    private void b() {
        if (Float.compare(this.o.getResources().getConfiguration().fontScale, 1.75f) >= 0) {
            c(this.o.getResources().getDimensionPixelSize(R.dimen._2131362719_res_0x7f0a039f), this.o.getResources().getDimensionPixelSize(R.dimen._2131362721_res_0x7f0a03a1));
            setSubTabItemPadding(this.o.getResources().getDimensionPixelSize(R.dimen._2131364459_res_0x7f0a0a6b));
        }
    }

    private void b(HwColumnSystem hwColumnSystem) {
        hwColumnSystem.e(-1);
        hwColumnSystem.e(this.o);
    }
}
