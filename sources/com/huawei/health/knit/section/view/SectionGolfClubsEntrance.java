package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.SectionGolfClubsEntrance;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eet;
import defpackage.nru;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionGolfClubsEntrance extends BaseSection {
    private View e;

    public SectionGolfClubsEntrance(Context context) {
        super(context);
    }

    public SectionGolfClubsEntrance(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionGolfClubsEntrance(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        if (this.e == null) {
            this.e = LayoutInflater.from(context).inflate(R.layout.section_golf_clubs_enter, (ViewGroup) this, false);
        }
        return this.e;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        Object d = nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null);
        if (eet.a(d)) {
            final OnClickSectionListener onClickSectionListener = (OnClickSectionListener) d;
            setOnClickListener(new View.OnClickListener() { // from class: egy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SectionGolfClubsEntrance.aji_(OnClickSectionListener.this, view);
                }
            });
        }
    }

    public static /* synthetic */ void aji_(OnClickSectionListener onClickSectionListener, View view) {
        onClickSectionListener.onClick("TITLE_SHOW_MORE_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Track_SectionGolfClubsEntrance";
    }
}
