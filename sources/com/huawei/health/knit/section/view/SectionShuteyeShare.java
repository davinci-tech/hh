package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nru;
import defpackage.nsy;
import health.compact.a.LogUtil;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionShuteyeShare extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private int f2732a;
    private int b;
    private HealthTextView c;
    private Context d;
    private int e;
    private int f;
    private int g;
    private HealthTextView h;
    private HealthTextView i;
    private LinearLayout j;
    private HealthTextView m;

    public SectionShuteyeShare(Context context) {
        this(context, null);
    }

    public SectionShuteyeShare(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionShuteyeShare(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0;
        this.g = 0;
        this.f2732a = 0;
        this.f = 0;
        this.e = 8;
        this.d = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.c("SectionShuteyeShare", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_fitness_core_sleep_share_shuteye, (ViewGroup) this, false);
        this.j = (LinearLayout) inflate.findViewById(R.id.share_dream_talkTen);
        this.i = (HealthTextView) inflate.findViewById(R.id.shareDreamTalkText);
        this.c = (HealthTextView) inflate.findViewById(R.id.dreamTalkMintesText);
        this.h = (HealthTextView) inflate.findViewById(R.id.shareSnoreText);
        this.m = (HealthTextView) inflate.findViewById(R.id.snoreMintesText);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.c("SectionShuteyeShare", "no need to bind");
            return;
        }
        LogUtil.c("SectionShuteyeShare", "bindParamsToView");
        String b = nru.b(hashMap, "SHARE_DREAM_TALK", "");
        String b2 = nru.b(hashMap, "SHARE_SNORE", "");
        String b3 = nru.b(hashMap, "SHARE_DREAM_TALK_MINTES", "");
        String b4 = nru.b(hashMap, "SHARE_SNORE_MINTES", "");
        nsy.cMs_(this.i, b, true);
        nsy.cMs_(this.h, b2, true);
        nsy.cMs_(this.c, b3, true);
        nsy.cMs_(this.m, b4, true);
        LogUtil.c("SectionShuteyeShare", "shareDreamTalktext=" + b + ",shareDreamMinentsText=" + b3);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void setViewSHow(int i) {
        this.e = i;
        this.j.setVisibility(i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionShuteyeShare";
    }
}
