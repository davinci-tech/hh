package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.text.SpannableString;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.ui.main.R$string;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.phw;
import health.compact.a.UnitUtil;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class ActiveCumulatveAndAverageProvider extends MinorProvider<phw> {
    private phw d;
    private SectionBean e;

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        this.d = new phw();
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        super.loadData(context, sectionBean);
        this.e = sectionBean;
        sectionBean.e(this.d);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap hashMap, phw phwVar) {
        super.parseParams(context, hashMap, phwVar);
        this.d = phwVar;
        hashMap.put("CUMULATIVE_SUM", dpH_(phwVar.d()));
        hashMap.put("DAILY_AVERAGE", dpH_(phwVar.b()));
    }

    private SpannableString dpH_(int i) {
        String e = UnitUtil.e(i, 1, 0);
        SpannableString spannableString = new SpannableString(nsf.a(R.plurals._2130903199_res_0x7f03009f, i, e));
        nsi.cKJ_(spannableString, e, nsf.b(R.dimen._2131362955_res_0x7f0a048b));
        nsi.cKI_(spannableString, e, R.color._2131299236_res_0x7f090ba4);
        nsi.cKL_(spannableString, e, R$string.textFontFamilyMedium);
        return spannableString;
    }
}
