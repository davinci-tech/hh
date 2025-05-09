package com.huawei.health.section.section;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import com.huawei.ui.commonui.flowlayout.textviewbuilder.SearchTagTextViewBuilder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class Section21_9Search_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private Context f2980a;
    private HealthTextView b;
    private HealthFlowLayout c;
    private HealthSubHeader d;
    private View e;

    public Section21_9Search_01(Context context) {
        this(context, null);
    }

    public Section21_9Search_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section21_9Search_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2980a = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section21_9search_01_layout, (ViewGroup) this, false);
        this.e = inflate;
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section21_9_search_01_sub_header);
        this.d = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.d.setMoreLayoutVisibility(4);
        this.d.setSubHeaderBackgroundColor(ContextCompat.getColor(this.e.getContext(), R.color._2131296971_res_0x7f0902cb));
        this.b = (HealthTextView) this.e.findViewById(R.id.section21_9search_01_right_button);
        this.c = (HealthFlowLayout) this.e.findViewById(R.id.section21_9search_01_flow_layout);
        this.c.setTextViewBuilder(new SearchTagTextViewBuilder(BaseApplication.getContext()));
        return this.e;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        this.d.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        String b = nru.b(hashMap, "ITEM_RIGHT_BTN", "");
        final OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        nsy.cMs_(this.b, b, true);
        nsy.cMn_(this.b, new View.OnClickListener() { // from class: com.huawei.health.section.section.Section21_9Search_01.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OnClickSectionListener onClickSectionListener = a2;
                if (onClickSectionListener != null) {
                    onClickSectionListener.onClick("RIGHT_TOP_TEXT_CLICK_EVENT");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        List<String> d = nru.d(hashMap, "ITEM_TITLE", String.class, new ArrayList());
        this.c.e(c(d, nru.d(hashMap, "RIGHT_ICON_TEXT", String.class, new ArrayList())), false);
        if (koq.b(d)) {
            return;
        }
        e(hashMap, d);
    }

    private void e(final HashMap<String, Object> hashMap, final List<String> list) {
        for (final int i = 0; i < this.c.getChildCount(); i++) {
            View childAt = this.c.getChildAt(i);
            if (childAt instanceof HealthTextView) {
                childAt.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.section.section.Section21_9Search_01.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        OnClickSectionListener onClickSectionListener = (OnClickSectionListener) hashMap.get("CLICK_EVENT_LISTENER");
                        if (onClickSectionListener != null) {
                            if (koq.b(list, i)) {
                                LogUtil.b("Section_Section21_9Search_01", "position is out of bounds");
                                ViewClickInstrumentation.clickOnView(view);
                                return;
                            } else {
                                int i2 = i;
                                onClickSectionListener.onClick(i2, (String) list.get(i2));
                            }
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.ArrayList<defpackage.nmk> c(java.util.List<java.lang.String> r10, java.util.List<java.lang.String> r11) {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = r1
        L7:
            int r3 = r10.size()
            if (r2 >= r3) goto L8a
            java.lang.Object r3 = r10.get(r2)
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = defpackage.koq.d(r11, r2)
            r5 = 1
            if (r4 == 0) goto L7e
            java.lang.Object r4 = r11.get(r2)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r6 = "none"
            if (r4 != 0) goto L25
            r4 = r6
        L25:
            int r7 = r4.hashCode()
            r8 = 103501(0x1944d, float:1.45036E-40)
            if (r7 == r8) goto L4b
            r8 = 108960(0x1a9a0, float:1.52685E-40)
            if (r7 == r8) goto L41
            r8 = 3387192(0x33af38, float:4.746467E-39)
            if (r7 == r8) goto L39
            goto L55
        L39:
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L55
            r4 = 2
            goto L56
        L41:
            java.lang.String r6 = "new"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L55
            r4 = r5
            goto L56
        L4b:
            java.lang.String r6 = "hot"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L55
            r4 = r1
            goto L56
        L55:
            r4 = -1
        L56:
            if (r4 == 0) goto L6d
            if (r4 == r5) goto L5b
            goto L7e
        L5b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r3 = " 🆕"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            goto L7e
        L6d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r3 = " 🔥"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
        L7e:
            nmk r4 = new nmk
            r4.<init>(r2, r3, r5)
            r0.add(r4)
            int r2 = r2 + 1
            goto L7
        L8a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.section.section.Section21_9Search_01.c(java.util.List, java.util.List):java.util.ArrayList");
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Section21_9Search_01";
    }
}
