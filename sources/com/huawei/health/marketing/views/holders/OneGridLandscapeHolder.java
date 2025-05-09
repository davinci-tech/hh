package com.huawei.health.marketing.views.holders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.CornerTemplate;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ehy;
import defpackage.eie;
import defpackage.eiv;
import defpackage.ekx;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class OneGridLandscapeHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private String f2912a;
    private ImageView b;
    private HealthTextView c;
    private Context d;
    private int e;
    private LinearLayout f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private ImageView j;
    private HealthTextView k;
    private HealthTextView l;
    private LinearLayout m;
    private HealthTextView n;
    private LinearLayout o;
    private int p;
    private HealthTextView q;
    private RelativeLayout r;
    private HealthTextView s;
    private ResourceBriefInfo t;

    public OneGridLandscapeHolder(Context context, View view, ResourceBriefInfo resourceBriefInfo, int i) {
        super(view);
        this.d = context;
        this.p = i;
        this.t = resourceBriefInfo;
        this.f2912a = resourceBriefInfo.getCategory();
        this.e = resourceBriefInfo.getContentType();
        this.r = (RelativeLayout) view.findViewById(R.id.item_one_landscape_layout);
        this.j = (ImageView) view.findViewById(R.id.item_one_landscape_image);
        this.b = (ImageView) view.findViewById(R.id.audition_btn);
        this.m = (LinearLayout) view.findViewById(R.id.item_one_landscape_in_layout);
        this.q = (HealthTextView) view.findViewById(R.id.item_one_landscape_title);
        this.c = (HealthTextView) view.findViewById(R.id.item_one_landscape_corner);
        this.i = (HealthTextView) view.findViewById(R.id.item_one_landscape_description);
        this.o = (LinearLayout) view.findViewById(R.id.item_one_landscape_out_layout);
        this.l = (HealthTextView) view.findViewById(R.id.item_one_landscape_out_title);
        this.n = (HealthTextView) view.findViewById(R.id.item_one_landscape_out_description);
        this.s = (HealthTextView) view.findViewById(R.id.item_one_landscape_peoples_num);
        this.f = (LinearLayout) view.findViewById(R.id.item_one_landscape_ll_course);
        this.g = (HealthTextView) view.findViewById(R.id.item_one_landscape_course_title);
        this.h = (HealthTextView) view.findViewById(R.id.item_one_landscape_course_details);
        this.k = (HealthTextView) view.findViewById(R.id.item_one_landscape_join_num);
        eiv.a(this.q, false, true);
        eiv.a(this.i, true, false);
        eiv.a(this.l, false, true);
        eiv.a(this.n, true, false);
        eiv.a(this.s, false, false);
        eiv.a(this.g, false, true);
        eiv.a(this.h, true, false);
        eiv.a(this.k, false, false);
    }

    public void d(List<SingleGridContent> list, int i) {
        SingleGridContent singleGridContent = koq.d(list, i) ? list.get(i) : null;
        if (singleGridContent == null) {
            LogUtil.h("OneGridLandscapeHolder", "setOneGridLandscapeLayout singleGridContent is null.");
            return;
        }
        LogUtil.a("OneGridLandscapeHolder", "setOneGridLandscapeLayout mContentType  = ", Integer.valueOf(this.e));
        LogUtil.a("OneGridLandscapeHolder", "setOneGridLandscapeLayout content  = ", singleGridContent);
        int e = eie.e(this.d, this.e, list.size());
        ViewGroup.LayoutParams layoutParams = this.j.getLayoutParams();
        int i2 = (e * 9) / 16;
        layoutParams.width = e;
        layoutParams.height = i2;
        this.j.setLayoutParams(layoutParams);
        nrf.cIS_(this.j, singleGridContent.getPicture(), nrf.d, 1, 0);
        eiv.d(this.c, (CornerTemplate) singleGridContent, false);
        if (this.e == 19) {
            a(singleGridContent, i);
            if (TextUtils.equals(this.f2912a, SingleDailyMomentContent.ACTIVITY_TYPE)) {
                eiv.c(this.k, singleGridContent, this.f2912a);
            }
        } else {
            this.m.setVisibility(8);
            this.s.setVisibility(8);
            this.f.setVisibility(8);
            this.o.setVisibility(0);
            eiv.d(this.l, singleGridContent.getTheme(), singleGridContent.isThemeVisibility());
            eiv.a(this.n, singleGridContent.getDescription(), singleGridContent.getDescriptionVisibility());
            eiv.a(this.n, true, false);
            i2 = -2;
        }
        ViewGroup.LayoutParams layoutParams2 = this.r.getLayoutParams();
        layoutParams2.width = e;
        layoutParams2.height = i2;
        this.r.setLayoutParams(layoutParams2);
        String valueOf = this.c.getVisibility() == 0 ? String.valueOf(this.c.getText()) : "";
        String valueOf2 = this.k.getVisibility() == 0 ? String.valueOf(this.k.getText()) : "";
        CharSequence a2 = eiv.a(singleGridContent);
        if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(valueOf2)) {
            a2 = nsf.b(R$string.accessibility_status_theme_description_join, valueOf, a2, valueOf2);
        } else if (!TextUtils.isEmpty(valueOf)) {
            a2 = nsf.b(R$string.accessibility_status_theme_description, valueOf, a2);
        } else if (!TextUtils.isEmpty(valueOf2)) {
            a2 = nsf.b(R$string.accessibility_theme_description_join, a2, valueOf2);
        }
        jcf.bEA_(this.r, a2, ImageView.class);
        LogUtil.a("OneGridLandscapeHolder", "setOneGridLandscapeLayout contentDescription ", a2);
    }

    private void a(SingleGridContent singleGridContent, int i) {
        this.m.setVisibility(0);
        this.o.setVisibility(8);
        eiv.d(this.q, singleGridContent.getTheme(), singleGridContent.isThemeVisibility());
        eiv.a(this.i, singleGridContent.getDescription(), singleGridContent.getDescriptionVisibility());
        eiv.a(this.i, true, false);
        if (TextUtils.equals(this.f2912a, SingleDailyMomentContent.COURSE_TYPE) || TextUtils.equals(singleGridContent.getItemCategory(), SingleDailyMomentContent.COURSE_TYPE)) {
            this.f.setVisibility(0);
            this.m.setVisibility(8);
            c();
            eiv.a(this.g, singleGridContent.getTheme(), singleGridContent.isThemeVisibility(), true);
            String e = ehy.e(singleGridContent, 19);
            LogUtil.a("OneGridLandscapeHolder", "get courseInfo = ", e);
            HealthTextView healthTextView = this.h;
            if (TextUtils.isEmpty(e)) {
                e = singleGridContent.getDescription();
            }
            eiv.c(healthTextView, e, singleGridContent.getDescriptionVisibility(), true);
            eiv.a(this.h, true, false);
            if (!nsn.r() && singleGridContent.isThemeVisibility()) {
                eiv.c(this.s, singleGridContent, this.f2912a);
                this.s.setVisibility(0);
            }
        } else {
            this.s.setVisibility(8);
        }
        if (singleGridContent.isAuditionVisibility()) {
            this.b.setVisibility(0);
            HashMap hashMap = new HashMap(16);
            hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, String.valueOf(this.p));
            hashMap.put("resourceId", this.t.getResourceId());
            hashMap.put("resourceName", this.t.getResourceName());
            hashMap.put("pullOrder", String.valueOf(i + 1));
            ekx.aqu_(this.itemView, this.b, singleGridContent, hashMap);
            return;
        }
        this.b.setVisibility(8);
    }

    private void c() {
        if (nsn.s()) {
            nsn.b(this.g);
        }
    }
}
