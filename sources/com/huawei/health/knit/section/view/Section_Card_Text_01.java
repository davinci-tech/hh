package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section_Card_Text_01 extends BaseSection {
    private HealthTextView b;
    private HealthSubHeader c;

    public Section_Card_Text_01(Context context) {
        this(context, null);
    }

    public Section_Card_Text_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section_Card_Text_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_card_text_1_layout, (ViewGroup) this, false);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.sub_header);
        this.c = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(context.getColor(R.color._2131296971_res_0x7f0902cb));
        this.b = (HealthTextView) inflate.findViewById(R.id.text1);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_Card_Text_01", "no need to bind");
            return;
        }
        this.c.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        int d = nru.d((Map) hashMap, "SUB_HEADER_MAX_LINES", -1);
        if (d != -1) {
            this.c.setHeadTitleMaxLine(d);
        }
        nsy.cMs_(this.b, nru.b(hashMap, "TEXT1", ""), true);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Card_Text_01";
    }
}
