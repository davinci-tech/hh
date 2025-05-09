package com.huawei.ui.main.stories.configuredpage.adpters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.Utils;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import defpackage.cdu;
import defpackage.cdy;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.pfh;
import defpackage.pfl;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class ConfiguredLayoutAdapter extends RecyclerView.Adapter<a> {

    /* renamed from: a, reason: collision with root package name */
    private int f9687a;
    private int b;
    private Context c;
    private List<List<cdu>> d;
    private LayoutInflater e;
    private List<cdu> f;
    private boolean g;
    private int h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private String o;
    private int q;
    private OperationInteractorsApi s;

    private ConfiguredLayoutAdapter(boolean z, Context context, cdy cdyVar) {
        this.j = true;
        this.c = context;
        this.e = LayoutInflater.from(context);
        this.i = z;
        if (cdyVar == null) {
            return;
        }
        this.h = cdyVar.c();
        this.k = cdyVar.b();
        this.n = cdyVar.h();
        this.l = cdyVar.f();
        this.o = cdyVar.g();
        this.q = cdyVar.o();
        this.s = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
    }

    public ConfiguredLayoutAdapter(Context context, List<cdu> list, cdy cdyVar) {
        this(false, context, cdyVar);
        this.f = list;
    }

    public ConfiguredLayoutAdapter(Context context, List<List<cdu>> list, cdy cdyVar, boolean z) {
        this(false, context, cdyVar);
        this.d = list;
        this.g = z;
    }

    public ConfiguredLayoutAdapter(boolean z, Context context, List<cdu> list, cdy cdyVar) {
        this(z, context, cdyVar);
        this.f = list;
    }

    public ConfiguredLayoutAdapter(boolean z, Context context, List<List<cdu>> list, cdy cdyVar, boolean z2) {
        this(z, context, cdyVar);
        this.d = list;
        this.g = z2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dnf_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        if (i == 2 || i == 4) {
            if (this.k == 5) {
                inflate = this.e.inflate(R.layout.item_configure_image_grid_two_optimization, viewGroup, false);
            } else {
                inflate = this.e.inflate(R.layout.configured_page_item_common_layout, viewGroup, false);
            }
        } else if (i != 14) {
            switch (i) {
                case 6:
                    int i2 = this.k;
                    if (i2 != 3 || !this.i) {
                        if (i2 == 3) {
                            inflate = this.e.inflate(R.layout.configured_page_item_huawei_activity_harmony, viewGroup, false);
                            break;
                        } else {
                            inflate = this.e.inflate(R.layout.configured_page_item_common_layout, viewGroup, false);
                            break;
                        }
                    } else {
                        inflate = this.e.inflate(R.layout.configured_page_item_huawei_activity, viewGroup, false);
                        break;
                    }
                case 7:
                    inflate = this.e.inflate(R.layout.item_configure_image_text, viewGroup, false);
                    break;
                case 8:
                    inflate = this.e.inflate(R.layout.item_configure_image_grid_vertical, viewGroup, false);
                    break;
                case 9:
                    if (nsn.ag(BaseApplication.getContext())) {
                        inflate = this.e.inflate(R.layout.item_configure_image_grid_two_optimization, viewGroup, false);
                        break;
                    } else {
                        inflate = this.e.inflate(R.layout.item_configure_image_vertical_attach, viewGroup, false);
                        break;
                    }
                case 10:
                    inflate = this.e.inflate(R.layout.configured_page_item_discover_icon, viewGroup, false);
                    break;
                case 11:
                    inflate = this.e.inflate(R.layout.item_configure_information_image_text, viewGroup, false);
                    break;
                case 12:
                    inflate = this.e.inflate(R.layout.item_configure_image_grid_four_optimization, viewGroup, false);
                    break;
                default:
                    inflate = this.e.inflate(R.layout.configured_page_item_common_layout, viewGroup, false);
                    break;
            }
        } else {
            inflate = this.e.inflate(R.layout.configured_page_item_three_list, viewGroup, false);
        }
        return new a(inflate, this.k, i, this.i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.h;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        if (this.g) {
            if (koq.b(this.d, i)) {
                return;
            }
            c(aVar);
            return;
        }
        if (koq.b(this.f, i)) {
            return;
        }
        int i2 = this.h;
        if (i2 == 2 || i2 == 4) {
            n(aVar, i);
            return;
        }
        if (i2 != 14) {
            switch (i2) {
                case 6:
                    k(aVar, i);
                    break;
                case 7:
                    f(aVar, i);
                    break;
                case 8:
                    o(aVar, i);
                    break;
                case 9:
                    if (nsn.ag(BaseApplication.getContext())) {
                        m(aVar, i);
                        break;
                    } else {
                        c(aVar);
                        break;
                    }
                case 10:
                    h(aVar, i);
                    break;
                case 11:
                    i(aVar, i);
                    break;
                case 12:
                    a(aVar, i);
                    break;
                default:
                    j(aVar, i);
                    break;
            }
            return;
        }
        l(aVar, i);
    }

    private void k(a aVar, int i) {
        int i2 = this.k;
        if (i2 == 3 && this.i) {
            b(aVar, i);
        } else if (i2 == 3) {
            e(aVar, i);
        } else {
            j(aVar, i);
        }
    }

    private void n(a aVar, int i) {
        if (this.k == 5) {
            m(aVar, i);
        } else {
            j(aVar, i);
        }
    }

    private cdu d(a aVar, int i) {
        if (aVar == null || koq.b(this.f)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "getCardItemObject() holder or mListSingle or position is null.");
            return null;
        }
        if (koq.d(this.f, i)) {
            return this.f.get(i);
        }
        LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "getCardItemObject() outOfBounds, position = ", Integer.valueOf(i));
        return null;
    }

    private void i(a aVar, int i) {
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setGraphicalSeparationLayout itemObject is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = aVar.l.getLayoutParams();
        int b = pfh.b(this.c, 11, this.n);
        int i2 = (b * 9) / 21;
        layoutParams.width = b;
        layoutParams.height = i2;
        aVar.l.setLayoutParams(layoutParams);
        String c = c(d);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setInformationImageTextLayout() imageUrl is empty.");
        } else {
            aVar.m.setLayoutParams(new RelativeLayout.LayoutParams(b, i2));
            nrf.cIS_(aVar.m, c, nrf.d, 1, 0);
        }
        e(d.o(), d.i(), aVar.s);
        String d2 = d.d();
        int m = d.m();
        if (TextUtils.isEmpty(d2) || m != 1) {
            aVar.n.setVisibility(8);
        } else {
            aVar.n.setVisibility(0);
            aVar.n.setText(d2);
        }
        dnd_(d, aVar.l);
    }

    private void a(a aVar, int i) {
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setApplicationServerImageTextLayout itemObject is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = aVar.b.getLayoutParams();
        int c = nsn.c(this.c, 64.0f);
        layoutParams.width = c;
        layoutParams.height = (c * 3) / 2;
        aVar.b.setLayoutParams(layoutParams);
        String c2 = c(d);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setApplicationServerImageTextLayout() imageUrl is empty.");
        } else {
            nrf.cIS_(aVar.c, c2, nrf.e, 0, 0);
        }
        e(d.o(), d.i(), aVar.f9689a);
        dnd_(d, aVar.b);
    }

    private void m(a aVar, int i) {
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setOptimizationLayout itemObject is null.");
            return;
        }
        int b = pfh.b(this.c, this.h, this.n);
        ViewGroup.LayoutParams layoutParams = aVar.ao.getLayoutParams();
        layoutParams.width = b;
        if (this.h != 9) {
            b = (b * 5) / 4;
        }
        layoutParams.height = b;
        aVar.ao.setLayoutParams(layoutParams);
        e(d.o(), d.i(), aVar.as);
        d(d.d(), d.m(), aVar.an);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        aVar.as.measure(makeMeasureSpec, makeMeasureSpec2);
        aVar.an.measure(makeMeasureSpec, makeMeasureSpec2);
        int measuredHeight = aVar.as.getMeasuredHeight();
        int measuredHeight2 = aVar.an.getMeasuredHeight();
        int c = nsn.c(this.c, 12.0f);
        int c2 = (((((b - measuredHeight) - c) - nsn.c(this.c, 4.0f)) - measuredHeight2) - nsn.c(this.c, 20.0f)) - nsn.c(this.c, 16.0f);
        ViewGroup.LayoutParams layoutParams2 = aVar.ap.getLayoutParams();
        layoutParams2.height = c2;
        layoutParams2.width = c2;
        aVar.ap.setLayoutParams(layoutParams2);
        dnb_(c(d), aVar.ap);
        dnd_(d, aVar.ao);
    }

    private void c(a aVar) {
        LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setImageVerticalAttachLayout() enter");
        if (aVar == null || koq.b(this.d)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setImageVerticalAttachLayout holder or mGridVerticalList or position is null.");
            return;
        }
        List<cdu> list = this.d.get(0);
        if (koq.b(list, 2)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setImageVerticalAttachLayout outOfBounds.");
            return;
        }
        if (koq.b(list, 0) || koq.b(list, 1) || koq.b(list, 2)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "cardItemObjectList outOfBounds.");
            return;
        }
        cdu cduVar = list.get(0);
        cdu cduVar2 = list.get(1);
        cdu cduVar3 = list.get(2);
        if (cduVar == null || cduVar2 == null || cduVar3 == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setImageVerticalAttachLayout itemObject is null.");
            return;
        }
        d(aVar, cduVar);
        d(aVar, cduVar2);
        d(aVar, cduVar3);
    }

    private void d(a aVar, cdu cduVar) {
        if (aVar == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setVerticalLayoutShow holder or object is null.");
            return;
        }
        this.b = cduVar.p();
        String c = c(cduVar);
        int b = pfh.b(this.c, 9, this.n);
        this.f9687a = (b * 5) / 4;
        int dimensionPixelSize = this.c.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        int i = this.f9687a;
        this.m = (i - dimensionPixelSize) / 2;
        int i2 = this.b;
        if (i2 != 1) {
            if (i2 == 2) {
                a(aVar, cduVar, c, b);
                return;
            } else {
                LogUtil.a("ConfiguredPage_ConfiguredLayoutAdapter", "invalid imageType.");
                return;
            }
        }
        if (this.k == 4) {
            dnc_(cduVar, b, i, aVar.ab, aVar.aj);
            return;
        }
        if (aVar.ab == null || aVar.aj == null) {
            return;
        }
        aVar.ab.setVisibility(0);
        aVar.ab.setLayoutParams(new RelativeLayout.LayoutParams(b, this.f9687a));
        aVar.ab.setBackground(this.c.getResources().getDrawable(R.drawable._2131430721_res_0x7f0b0d41));
        aVar.aj.setVisibility(8);
        dne_(cduVar, c, aVar.ba, aVar.au, aVar.ad);
        dnd_(cduVar, aVar.ab);
    }

    private void a(a aVar, cdu cduVar, String str, int i) {
        if (this.j) {
            this.j = false;
            if (this.n == 15 && this.k == 4) {
                dnc_(cduVar, i, this.m, aVar.am, aVar.al);
                return;
            }
            if (aVar.am == null || aVar.al == null) {
                return;
            }
            aVar.am.setVisibility(0);
            aVar.am.setLayoutParams(new RelativeLayout.LayoutParams(i, this.m));
            aVar.am.setBackground(this.c.getResources().getDrawable(R.drawable._2131430721_res_0x7f0b0d41));
            aVar.al.setVisibility(8);
            dne_(cduVar, str, aVar.bc, aVar.at, aVar.ag);
            dnd_(cduVar, aVar.am);
            return;
        }
        this.j = true;
        if (this.n == 15 && this.k == 4) {
            dnc_(cduVar, i, this.m, aVar.af, aVar.ak);
            return;
        }
        if (aVar.af == null || aVar.ak == null) {
            return;
        }
        aVar.af.setVisibility(0);
        aVar.af.setLayoutParams(new RelativeLayout.LayoutParams(i, this.m));
        aVar.af.setBackground(this.c.getResources().getDrawable(R.drawable._2131430721_res_0x7f0b0d41));
        aVar.ak.setVisibility(8);
        dne_(cduVar, str, aVar.bb, aVar.av, aVar.ai);
        dnd_(cduVar, aVar.af);
    }

    private void dne_(cdu cduVar, String str, HealthTextView healthTextView, HealthTextView healthTextView2, ImageView imageView) {
        int c;
        if (cduVar == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setOptimizationImageItem object is null.");
            return;
        }
        e(cduVar.o(), cduVar.i(), healthTextView);
        d(cduVar.d(), cduVar.m(), healthTextView2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        healthTextView.measure(makeMeasureSpec, makeMeasureSpec2);
        healthTextView2.measure(makeMeasureSpec, makeMeasureSpec2);
        int measuredHeight = healthTextView.getMeasuredHeight();
        int measuredHeight2 = healthTextView2.getMeasuredHeight();
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int i = this.b;
        if (i == 1) {
            int i2 = this.f9687a;
            c = (((((i2 - measuredHeight) - measuredHeight2) - nsn.c(this.c, 12.0f)) - nsn.c(this.c, 4.0f)) - nsn.c(this.c, 20.0f)) - nsn.c(this.c, 16.0f);
        } else if (i == 2) {
            int i3 = this.m;
            c = (((i3 - measuredHeight) - nsn.c(this.c, 12.0f)) - nsn.c(this.c, 4.0f)) - nsn.c(this.c, 4.0f);
        } else {
            c = nsn.c(this.c, 108.0f);
        }
        layoutParams.height = c;
        layoutParams.width = c;
        imageView.setLayoutParams(layoutParams);
        dnb_(str, imageView);
    }

    private String c(cdu cduVar) {
        if (cduVar == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "getImageUrl object is null.");
            return "";
        }
        if (nsn.ag(BaseApplication.getContext())) {
            String g = cduVar.g();
            return TextUtils.isEmpty(g) ? cduVar.f() : g;
        }
        return cduVar.f();
    }

    private void dnc_(cdu cduVar, int i, int i2, LinearLayout linearLayout, ImageView imageView) {
        if (linearLayout == null || imageView == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setInformationImageItem optimizationLayout or informationImageView is null.");
            return;
        }
        linearLayout.setVisibility(8);
        imageView.setVisibility(0);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(i, i2));
        nrf.cIS_(imageView, cduVar.f(), (int) this.c.getResources().getDimension(R.dimen._2131362819_res_0x7f0a0403), 1, 0);
        dnd_(cduVar, imageView);
    }

    private View.OnClickListener dna_(final cdu cduVar) {
        return new View.OnClickListener() { // from class: com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (cduVar == null || nsn.o()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                int n = cduVar.n();
                if (n != 3 && n != 1 && n != 2 && !Utils.isNotSupportBrowseUrl(cduVar.e())) {
                    ConfiguredLayoutAdapter.this.e(cduVar.o(), cduVar.j());
                    pfh.e(ConfiguredLayoutAdapter.this.c, cduVar);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter.2.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (i == 0) {
                                ConfiguredLayoutAdapter.this.e(cduVar.o(), cduVar.j());
                                pfh.e(ConfiguredLayoutAdapter.this.c, cduVar);
                            } else {
                                LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "getViewClickListener errorCode = ", Integer.valueOf(i));
                            }
                        }
                    }, AnalyticsValue.HEALTH_CONFIGURE_PAGE_FRAGMENT_2020029.value());
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        };
    }

    private void dnd_(cdu cduVar, View view) {
        if (cduVar == null || view == null) {
            return;
        }
        if (TextUtils.isEmpty(cduVar.e())) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setOnClickListener() detailUrl is empty.");
        } else {
            view.setOnClickListener(dna_(cduVar));
        }
    }

    private void dnb_(String str, ImageView imageView) {
        if (!TextUtils.isEmpty(str) && imageView != null) {
            nrf.cIS_(imageView, str, (int) this.c.getResources().getDimension(R.dimen._2131362819_res_0x7f0a0403), 1, 0);
        } else {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "imageUrl is empty or imageView is null.");
        }
    }

    private void d(String str, int i, HealthTextView healthTextView) {
        if (healthTextView == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "textView is null.");
        } else if (TextUtils.isEmpty(str) || i != 1) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        }
    }

    private void e(String str, int i, HealthTextView healthTextView) {
        if (healthTextView == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "titleView is null.");
        } else if (TextUtils.isEmpty(str) || i != 1) {
            healthTextView.setVisibility(4);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        }
    }

    private void h(a aVar, int i) {
        int i2;
        if (aVar == null || koq.b(this.f)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setDiscoverItemLayout() holder or mListSingle or position is null.");
            return;
        }
        if (koq.b(this.f, i)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setDiscoverItemLayout() outOfBounds, position = ", Integer.valueOf(i));
            return;
        }
        cdu cduVar = this.f.get(i);
        if (cduVar == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setDiscoverItemLayout() cardItemObject is null.");
            return;
        }
        int c = pfh.c();
        if (this.f.size() == 3) {
            i2 = (c - (nrr.e(this.c, 40.0f) * 2)) / 3;
        } else {
            i2 = c / 4;
        }
        ViewGroup.LayoutParams layoutParams = aVar.o.getLayoutParams();
        layoutParams.width = i2;
        aVar.o.setLayoutParams(layoutParams);
        e(cduVar.o(), cduVar.i(), aVar.k);
        nrf.cIS_(aVar.j, c(cduVar), (int) this.c.getResources().getDimension(R.dimen._2131362819_res_0x7f0a0403), 0, 0);
        dnd_(cduVar, aVar.o);
    }

    private void f(a aVar, int i) {
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setGraphicalSeparationLayout cardItemObject is null.");
            return;
        }
        String c = c(d);
        ViewGroup.LayoutParams layoutParams = aVar.ah.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.height = nrr.e(BaseApplication.getContext(), 54.0f);
            layoutParams2.width = (layoutParams2.height * 16) / 9;
            aVar.ah.setLayoutParams(layoutParams2);
        }
        nrf.cIS_(aVar.ah, c, nrf.e, 0, 0);
        e(d.o(), d.i(), aVar.az);
        String d2 = d.d();
        int m = d.m();
        if (TextUtils.isEmpty(d2) || m != 1) {
            aVar.ay.setVisibility(8);
        } else {
            aVar.ay.setVisibility(0);
            aVar.ay.setText(d2);
        }
        if (nsn.ag(BaseApplication.getContext()) || i == this.f.size() - 1) {
            aVar.t.setVisibility(8);
        } else {
            aVar.t.setVisibility(0);
        }
        dnd_(d, aVar.ae);
    }

    private void o(a aVar, int i) {
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setSecondPalaceVerticalLayout object is null.");
            return;
        }
        int b = pfh.b(this.c, 8, this.n);
        ViewGroup.LayoutParams layoutParams = aVar.aw.getLayoutParams();
        layoutParams.width = b;
        layoutParams.height = (b * 9) / 21;
        aVar.aw.setLayoutParams(layoutParams);
        nrf.cIS_(aVar.ar, c(d), (int) this.c.getResources().getDimension(R.dimen._2131362819_res_0x7f0a0403), 1, 0);
        e(d.o(), d.i(), aVar.ax);
        d(d.d(), d.m(), aVar.aq);
        dnd_(d, aVar.aw);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
    
        if (r3 != 5) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void j(com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter.a r8, int r9) {
        /*
            r7 = this;
            cdu r9 = r7.d(r8, r9)
            if (r9 != 0) goto L7
            return
        L7:
            android.content.Context r0 = r7.c
            int r1 = r7.h
            int r2 = r7.n
            int r0 = defpackage.pfh.b(r0, r1, r2)
            android.widget.ImageView r1 = com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter.a.dnz_(r8)
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            r1.width = r0
            int r2 = r0 * 9
            int r2 = r2 / 21
            int r3 = r7.h
            r4 = 3
            r5 = 2
            if (r3 == r5) goto L3b
            if (r3 == r4) goto L39
            r5 = 4
            if (r3 == r5) goto L2e
            r4 = 5
            if (r3 == r4) goto L39
            goto L3e
        L2e:
            boolean r2 = r7.i
            if (r2 == 0) goto L36
            int r2 = r0 * 2
            int r2 = r2 / r4
            goto L3e
        L36:
            int r2 = r0 / 2
            goto L3e
        L39:
            r2 = r0
            goto L3e
        L3b:
            int r2 = r0 * 2
            int r2 = r2 / r4
        L3e:
            r1.height = r2
            android.widget.ImageView r3 = com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter.a.dnz_(r8)
            r3.setLayoutParams(r1)
            java.lang.String r1 = r7.c(r9)
            android.widget.ImageView r3 = com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter.a.dnz_(r8)
            int r4 = defpackage.nrf.d
            r5 = 1
            r6 = 0
            defpackage.nrf.cIS_(r3, r1, r4, r5, r6)
            boolean r1 = r7.i
            if (r1 == 0) goto L5e
            r7.a(r8, r9, r0, r2)
            goto L61
        L5e:
            r7.b(r8, r9, r0, r2)
        L61:
            android.widget.RelativeLayout r8 = com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter.a.dnA_(r8)
            r7.dnd_(r9, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter.j(com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter$a, int):void");
    }

    private void a(a aVar, cdu cduVar, int i, int i2) {
        String o = cduVar.o();
        int i3 = cduVar.i();
        ViewGroup.LayoutParams layoutParams = aVar.d.getLayoutParams();
        layoutParams.width = i;
        if (this.q == 1) {
            aVar.i.setVisibility(8);
            if (TextUtils.isEmpty(o) || i3 != 1) {
                aVar.h.setVisibility(8);
            } else {
                aVar.h.setVisibility(0);
                aVar.h.setText(o);
            }
            layoutParams.height = i2;
        } else {
            aVar.h.setVisibility(8);
            if (TextUtils.isEmpty(o) || i3 != 1) {
                aVar.i.setVisibility(8);
            } else {
                aVar.i.setVisibility(0);
                aVar.g.setText(o);
                d(cduVar.d(), cduVar.m(), aVar.f);
            }
            layoutParams.height = -2;
        }
        aVar.d.setLayoutParams(layoutParams);
    }

    private void b(a aVar, cdu cduVar, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = aVar.d.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        pfl.dou_(aVar.h, aVar.i, aVar.g, cduVar, this.q);
        int i3 = this.q;
        if (i3 != 1 && i3 != 2) {
            layoutParams.height = -2;
            if (!TextUtils.isEmpty(cduVar.o()) && cduVar.i() == 1) {
                aVar.i.setVisibility(0);
                d(cduVar.d(), cduVar.m(), aVar.f);
            }
        }
        aVar.d.setLayoutParams(layoutParams);
    }

    private void b(a aVar, int i) {
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setActivityLayout cardItemObject is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = aVar.w.getLayoutParams();
        int b = pfh.b(this.c, 6, this.n);
        int i2 = (b * 9) / 21;
        layoutParams.width = b;
        layoutParams.height = i2;
        aVar.w.setLayoutParams(layoutParams);
        c(aVar, d, b, i2);
        String o = d.o();
        int i3 = d.i();
        if (d.a() == 1) {
            a(aVar, i, o, i3);
        } else {
            aVar.u.setVisibility(8);
            aVar.z.setVisibility(4);
            aVar.y.setVisibility(8);
            e(o, i3, aVar.ac);
        }
        dnd_(d, aVar.w);
    }

    private void c(a aVar, cdu cduVar, int i, int i2) {
        if (aVar == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "loadRoundImage holder or itemObject is null.");
            return;
        }
        String c = c(cduVar);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "loadRoundImage() imageUrl is empty.");
            return;
        }
        aVar.v.setLayoutParams(new RelativeLayout.LayoutParams(i, i2));
        nrf.cIS_(aVar.v, c, (int) this.c.getResources().getDimension(R.dimen._2131362819_res_0x7f0a0403), 1, 0);
    }

    private void a(a aVar, int i, String str, int i2) {
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setHuaweiActivityLayout itemObject is null.");
            return;
        }
        String k = d.k();
        if (!TextUtils.isEmpty(k)) {
            try {
                if (Integer.parseInt(k) < 0) {
                    aVar.y.setVisibility(8);
                }
            } catch (NumberFormatException e) {
                LogUtil.b("ConfiguredPage_ConfiguredLayoutAdapter", "setHuaweiActivityLayout NumberFormatException ", e.getMessage());
            }
            aVar.y.setVisibility(0);
            aVar.y.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_activity_social_people_attended, k));
        } else {
            aVar.y.setVisibility(8);
        }
        String c = d.c();
        String h = d.h();
        if (i2 == 1) {
            if (TextUtils.isEmpty(str)) {
                aVar.ac.setVisibility(8);
            } else {
                aVar.ac.setVisibility(0);
                aVar.ac.setText(str);
            }
            if (TextUtils.isEmpty(c) || TextUtils.isEmpty(h)) {
                aVar.u.setVisibility(8);
            } else {
                aVar.u.setVisibility(0);
                aVar.aa.setText(this.s.getGMTtoLocal(c));
                aVar.x.setText(this.s.getGMTtoLocal(h));
            }
        } else {
            aVar.ac.setVisibility(8);
            aVar.u.setVisibility(8);
        }
        d(d, aVar, c, h);
    }

    private void d(cdu cduVar, a aVar, String str, String str2) {
        if (cduVar == null || aVar == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setDateAndStatusUi() itemObject or holder is null.");
            return;
        }
        int activityStatus = this.s.getActivityStatus(SharedPreferenceManager.b(this.c, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageActivityServerCurrentTime" + this.n), str, str2);
        if (activityStatus == 0) {
            aVar.z.setVisibility(0);
            aVar.z.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_activity_social_coming_soon));
            aVar.z.setBackground(this.c.getResources().getDrawable(R.drawable._2131427525_res_0x7f0b00c5));
        } else if (activityStatus == 1) {
            aVar.z.setVisibility(0);
            aVar.z.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_group_underway));
            aVar.z.setBackground(this.c.getResources().getDrawable(R.drawable._2131427526_res_0x7f0b00c6));
        } else if (activityStatus == -1) {
            aVar.z.setVisibility(0);
            aVar.z.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_activity_social_is_over));
            aVar.z.setBackground(this.c.getResources().getDrawable(R.drawable._2131427523_res_0x7f0b00c3));
        } else {
            aVar.z.setVisibility(8);
            LogUtil.a("ConfiguredPage_ConfiguredLayoutAdapter", "Activity Status is empty.");
        }
    }

    private void e(a aVar, int i) {
        String c;
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setActivityLayout cardItemObject is null.");
            return;
        }
        int b = pfh.b(this.c, 6, this.n);
        int i2 = (b * 9) / 16;
        ViewGroup.LayoutParams layoutParams = aVar.v.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.width = b;
            layoutParams2.height = i2;
            aVar.v.setLayoutParams(layoutParams2);
        }
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) aVar.w.getLayoutParams();
        layoutParams3.width = b;
        layoutParams3.height = i2;
        aVar.w.setLayoutParams(layoutParams3);
        if (!TextUtils.isEmpty(d.l())) {
            c = d.l();
        } else {
            c = c(d);
        }
        nrf.cIS_(aVar.v, c, 0, 1, 0);
        pfl.d(aVar.ac, d.o(), d.i());
        pfl.c(aVar.q, d.d(), d.m());
        pfl.d(aVar.r, d);
        pfl.d(aVar.y, d, this.k);
        if (d.a() == 1) {
            g(aVar, i);
        } else {
            aVar.z.setVisibility(8);
        }
        dnd_(d, aVar.p);
    }

    private void l(a aVar, int i) {
        cdu d = d(aVar, i);
        if (d == null) {
            return;
        }
        int b = pfh.b(this.c, this.h, this.n);
        ViewGroup.LayoutParams layoutParams = aVar.bg.getLayoutParams();
        layoutParams.width = b;
        layoutParams.height = b;
        aVar.bg.setLayoutParams(layoutParams);
        nrf.cIS_(aVar.bg, c(d), 0, 1, 0);
        if (d.t() == 1) {
            aVar.bi.setVisibility(0);
        } else {
            aVar.bi.setVisibility(4);
        }
        int s = d.s();
        if (s > 0) {
            aVar.bj.setVisibility(0);
            aVar.bd.setVisibility(0);
            aVar.bd.setText(String.valueOf(s));
        } else {
            aVar.bj.setVisibility(8);
            aVar.bd.setVisibility(8);
        }
        e(d.o(), d.i(), aVar.be);
        ViewGroup.LayoutParams layoutParams2 = aVar.bf.getLayoutParams();
        layoutParams2.width = b;
        layoutParams2.height = b;
        aVar.bf.setLayoutParams(layoutParams2);
        dnd_(d, aVar.bh);
    }

    private void g(a aVar, int i) {
        cdu d = d(aVar, i);
        if (d == null) {
            LogUtil.h("ConfiguredPage_ConfiguredLayoutAdapter", "setHuaweiActivityLayout itemObject is null.");
        } else {
            pfl.c(aVar.z, d, this.n, this.k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i) {
        HashMap hashMap = new HashMap(7);
        hashMap.put("click", "1");
        hashMap.put(CommonUtil.PAGE_TYPE, Integer.valueOf(this.n));
        hashMap.put("moduleType", Integer.valueOf(this.k));
        hashMap.put("moduleName", this.o);
        hashMap.put(Constants.BI_MODULE_ID, Integer.valueOf(this.l));
        hashMap.put("name", str);
        hashMap.put("id", Integer.valueOf(i));
        ixx.d().d(this.c, AnalyticsValue.HEALTH_CONFIGURE_PAGE_FRAGMENT_2020029.value(), hashMap, 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.g) {
            List<List<cdu>> list = this.d;
            if (list == null) {
                return 0;
            }
            return list.size();
        }
        List<cdu> list2 = this.f;
        if (list2 == null) {
            return 0;
        }
        return list2.size();
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f9689a;
        private HealthTextView aa;
        private LinearLayout ab;
        private HealthTextView ac;
        private ImageView ad;
        private LinearLayout ae;
        private LinearLayout af;
        private ImageView ag;
        private ImageView ah;
        private ImageView ai;
        private ImageView aj;
        private ImageView ak;
        private ImageView al;
        private LinearLayout am;
        private HealthTextView an;
        private LinearLayout ao;
        private ImageView ap;
        private HealthTextView aq;
        private ImageView ar;
        private HealthTextView as;
        private HealthTextView at;
        private HealthTextView au;
        private HealthTextView av;
        private LinearLayout aw;
        private HealthTextView ax;
        private HealthTextView ay;
        private HealthTextView az;
        private LinearLayout b;
        private HealthTextView ba;
        private HealthTextView bb;
        private HealthTextView bc;
        private HealthTextView bd;
        private HealthTextView be;
        private RelativeLayout bf;
        private ImageView bg;
        private RelativeLayout bh;
        private ImageView bi;
        private ImageView bj;
        private ImageView c;
        private RelativeLayout d;
        private ImageView e;
        private HealthTextView f;
        private HealthTextView g;
        private HealthTextView h;
        private LinearLayout i;
        private ImageView j;
        private HealthTextView k;
        private RelativeLayout l;
        private ImageView m;
        private HealthTextView n;
        private LinearLayout o;
        private HealthCardView p;
        private HealthTextView q;
        private HealthTextView r;
        private HealthTextView s;
        private HealthDivider t;
        private LinearLayout u;
        private ImageView v;
        private RelativeLayout w;
        private HealthTextView x;
        private HealthTextView y;
        private HealthTextView z;

        a(View view, int i, int i2, boolean z) {
            super(view);
            if (i2 == 2 || i2 == 4) {
                if (i == 5) {
                    dnT_(view);
                    return;
                } else {
                    dnO_(view);
                    return;
                }
            }
            if (i2 != 14) {
                switch (i2) {
                    case 6:
                        if (i != 3 || !z) {
                            if (i == 3) {
                                dnQ_(view);
                                break;
                            } else {
                                dnO_(view);
                                break;
                            }
                        } else {
                            dnN_(view);
                            break;
                        }
                    case 7:
                        dnR_(view);
                        break;
                    case 8:
                        dnU_(view);
                        break;
                    case 9:
                        if (nsn.ag(BaseApplication.getContext())) {
                            dnT_(view);
                            break;
                        } else {
                            dnW_(view);
                            break;
                        }
                    case 10:
                        dnP_(view);
                        break;
                    case 11:
                        dnS_(view);
                        break;
                    case 12:
                        dnM_(view);
                        break;
                    default:
                        dnO_(view);
                        break;
                }
                return;
            }
            dnV_(view);
        }

        private void dnP_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getDiscoverIconItemView() itemView is null.");
                return;
            }
            this.o = (LinearLayout) view.findViewById(R.id.item_discover_root_layout);
            this.j = (ImageView) view.findViewById(R.id.item_discover_icon);
            this.k = (HealthTextView) view.findViewById(R.id.item_discover_title);
        }

        private void dnO_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getCommonHolder() itemView is null.");
                return;
            }
            this.d = (RelativeLayout) view.findViewById(R.id.item_common_root_layout);
            this.e = (ImageView) view.findViewById(R.id.item_common_image_view);
            this.h = (HealthTextView) view.findViewById(R.id.item_common_title);
            this.i = (LinearLayout) view.findViewById(R.id.item_common_text_area_layout);
            this.g = (HealthTextView) view.findViewById(R.id.item_common_text_area_title);
            this.f = (HealthTextView) view.findViewById(R.id.item_common_text_area_description);
        }

        private void dnM_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getApplicationServerImageTextGridItemView() itemView is null.");
                return;
            }
            this.b = (LinearLayout) view.findViewById(R.id.application_server_item_image_text_layout);
            this.c = (ImageView) view.findViewById(R.id.application_server_item_image);
            this.f9689a = (HealthTextView) view.findViewById(R.id.application_server_item_title);
        }

        private void dnS_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getInformationImageTextGridItemView() itemView is null.");
                return;
            }
            this.l = (RelativeLayout) view.findViewById(R.id.information_item_image_text_layout);
            this.m = (ImageView) view.findViewById(R.id.information_item_image);
            this.s = (HealthTextView) view.findViewById(R.id.information_item_title);
            this.n = (HealthTextView) view.findViewById(R.id.information_item_description);
        }

        private void dnU_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getSecondPalaceVerticalHolder() itemView is null.");
                return;
            }
            this.aw = (LinearLayout) view.findViewById(R.id.item_configure_message_layout);
            this.ar = (ImageView) view.findViewById(R.id.img_item_configure);
            this.ax = (HealthTextView) view.findViewById(R.id.item_title);
            this.aq = (HealthTextView) view.findViewById(R.id.item_describe);
        }

        private void dnT_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getOptimizationGridItemView() itemView is null.");
                return;
            }
            this.ao = (LinearLayout) view.findViewById(R.id.item_layout);
            this.as = (HealthTextView) view.findViewById(R.id.item_title);
            this.an = (HealthTextView) view.findViewById(R.id.item_describe);
            this.ap = (ImageView) view.findViewById(R.id.img_item);
        }

        private void dnW_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getVerticalImageAttachItemView() itemView is null.");
                return;
            }
            this.ab = (LinearLayout) view.findViewById(R.id.item_configure_big_layout);
            this.aj = (ImageView) view.findViewById(R.id.big_item_image);
            this.al = (ImageView) view.findViewById(R.id.top_item_image);
            this.ak = (ImageView) view.findViewById(R.id.bottom_item_image);
            this.am = (LinearLayout) view.findViewById(R.id.item_configure_small_layout_top);
            this.af = (LinearLayout) view.findViewById(R.id.item_configure_small_layout_bottom);
            this.ad = (ImageView) view.findViewById(R.id.img_item_configure_1);
            this.ba = (HealthTextView) view.findViewById(R.id.item_title_1);
            this.au = (HealthTextView) view.findViewById(R.id.item_describe_1);
            this.ag = (ImageView) view.findViewById(R.id.img_item_configure_2);
            this.bc = (HealthTextView) view.findViewById(R.id.item_title_2);
            this.at = (HealthTextView) view.findViewById(R.id.item_describe_2);
            this.ai = (ImageView) view.findViewById(R.id.img_item_configure_3);
            this.bb = (HealthTextView) view.findViewById(R.id.item_title_3);
            this.av = (HealthTextView) view.findViewById(R.id.item_describe_3);
        }

        private void dnR_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getImageLeftAndTextRightHolder() itemView is null.");
                return;
            }
            this.ae = (LinearLayout) view.findViewById(R.id.item_configure_image_text_layout);
            this.ah = (ImageView) view.findViewById(R.id.left_img_item_configure);
            this.az = (HealthTextView) view.findViewById(R.id.text_area_title);
            this.ay = (HealthTextView) view.findViewById(R.id.text_area_description);
            this.t = (HealthDivider) view.findViewById(R.id.right_img_item_line);
        }

        private void dnQ_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getHuaweiActivityHolder() itemView is null.");
                return;
            }
            this.p = (HealthCardView) view.findViewById(R.id.item_card);
            this.w = (RelativeLayout) view.findViewById(R.id.huawei_image_layout);
            this.v = (ImageView) view.findViewById(R.id.activity_image);
            this.z = (HealthTextView) view.findViewById(R.id.activity_status);
            this.ac = (HealthTextView) view.findViewById(R.id.activity_title);
            this.y = (HealthTextView) view.findViewById(R.id.activity_join_num);
            this.r = (HealthTextView) view.findViewById(R.id.page_attribute_text);
            this.q = (HealthTextView) view.findViewById(R.id.activity_description);
        }

        private void dnN_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getHuaweiActivityHolder() itemView is null.");
                return;
            }
            this.w = (RelativeLayout) view.findViewById(R.id.activity_layout);
            this.v = (ImageView) view.findViewById(R.id.activity_img);
            this.z = (HealthTextView) view.findViewById(R.id.activity_status);
            this.ac = (HealthTextView) view.findViewById(R.id.activity_title);
            this.u = (LinearLayout) view.findViewById(R.id.activity_duration);
            this.aa = (HealthTextView) view.findViewById(R.id.activity_start_date);
            this.x = (HealthTextView) view.findViewById(R.id.activity_end_date);
            this.y = (HealthTextView) view.findViewById(R.id.activity_join_num);
        }

        private void dnV_(View view) {
            if (view == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageHolder", "getThreeGridListHolder() itemView is null.");
                return;
            }
            this.bh = (RelativeLayout) view.findViewById(R.id.item_three_list_root_layout);
            this.bf = (RelativeLayout) view.findViewById(R.id.item_three_list_image_layout);
            this.bg = (ImageView) view.findViewById(R.id.item_three_list_image_view);
            this.bj = (ImageView) view.findViewById(R.id.item_three_list_icon);
            this.bi = (ImageView) view.findViewById(R.id.item_three_list_image_new);
            this.bd = (HealthTextView) view.findViewById(R.id.item_three_hot_amount);
            this.be = (HealthTextView) view.findViewById(R.id.item_three_list_text_area_title);
        }
    }
}
