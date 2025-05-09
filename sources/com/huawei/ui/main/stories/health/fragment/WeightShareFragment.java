package com.huawei.ui.main.stories.health.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.indicator.HealthLevelIndicator;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.recycleview.RecyclerItemDecoration;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.adapter.WeightBodyIndexRecycleAdapter;
import com.huawei.ui.main.stories.health.fragment.WeightShareFragment;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import com.huawei.ui.main.stories.health.util.WeightCommonView;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.doj;
import defpackage.fdu;
import defpackage.jcu;
import defpackage.jdl;
import defpackage.jdv;
import defpackage.nmm;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrr;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.qkd;
import defpackage.qqv;
import defpackage.qqw;
import defpackage.qqy;
import defpackage.qrf;
import defpackage.qsj;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;

/* loaded from: classes6.dex */
public class WeightShareFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10184a;
    private ImageView aa;
    private WeightCommonView ab;
    private View ac;
    private HealthTextView ad;
    private HealthTextView ai;
    private HealthTextView b;
    private HealthTextView d;
    private LinearLayout e;
    private int f;
    private ImageView g;
    private Context h;
    private long i;
    private HealthLevelIndicator l;
    private boolean n;
    private boolean o;
    private HealthSubHeader p;
    private View q;
    private cfi r;
    private HealthRecycleView s;
    private View t;
    private int u;
    private LinearLayout v;
    private HealthSubHeader w;
    private HealthTextView x;
    private long y;
    private HealthTextView z;
    private List<qkd> j = new ArrayList(31);
    private cfe k = new cfe();
    private List<cfe> c = new ArrayList(31);
    private boolean m = false;

    public void d(boolean z) {
        this.m = z;
    }

    public void dEr_(Activity activity, boolean z) {
        if (activity == null) {
            LogUtil.a("HealthWeight_WeightShareFragment", "context is null");
            return;
        }
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        this.r = currentUser;
        this.h = activity;
        this.o = z;
        if (z) {
            boolean z2 = currentUser == null || TextUtils.isEmpty(currentUser.h());
            if (Utils.g() && z2) {
                dEn_(activity, R.id.viewstub_fragment_weight_share_detail_area_no_cloud, R.id.fragment_weight_share_detail_area_no_cloud_inflated);
            } else {
                dEn_(activity, R.id.viewstub_fragment_weight_share_detail_area, R.id.fragment_weight_share_detail_area_inflated);
            }
        } else {
            dEn_(activity, R.id.viewstub_fragment_weight_share_detail, R.id.fragment_weight_share_detail_inflated);
        }
        if (this.q == null) {
            LogUtil.h("HealthWeight_WeightShareFragment", "initViewInActivity: can't get rootview!");
        } else {
            c();
            f();
        }
    }

    private void dEn_(Activity activity, int i, int i2) {
        ViewStub viewStub = (ViewStub) activity.findViewById(i);
        if (viewStub != null) {
            this.q = viewStub.inflate();
        } else {
            this.q = activity.findViewById(i2);
        }
    }

    private void f() {
        if (nsn.ag(this.h)) {
            ViewGroup.LayoutParams layoutParams = this.g.getLayoutParams();
            layoutParams.height = nrr.e(this.h, 360.0f);
            layoutParams.width = -1;
            this.g.setLayoutParams(layoutParams);
            this.g.setImageResource(R.drawable._2131432071_res_0x7f0b1287);
            return;
        }
        this.g.setImageResource(R.drawable._2131432070_res_0x7f0b1286);
    }

    private void c() {
        this.ab = (WeightCommonView) this.q.findViewById(R.id.hw_show_health_weight_detail_common_view);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.q.findViewById(R.id.weight_suggest_health_subheader);
        this.w = healthSubHeader;
        if (healthSubHeader != null) {
            healthSubHeader.setMarginStartEnd(8.0f, 8.0f);
            this.w.setPaddingStartEnd(16.0f, 16.0f);
        }
        HealthSubHeader healthSubHeader2 = (HealthSubHeader) this.q.findViewById(R.id.peer_comparison_health_subheader);
        this.p = healthSubHeader2;
        if (healthSubHeader2 != null) {
            healthSubHeader2.setMarginStartEnd(8.0f, 8.0f);
            this.p.setPaddingStartEnd(16.0f, 16.0f);
        }
        this.x = (HealthTextView) this.q.findViewById(R.id.weight_share_body_tips);
        this.ai = (HealthTextView) this.q.findViewById(R.id.weight_scoring);
        this.t = this.q.findViewById(R.id.weight_score_parent);
        this.z = (HealthTextView) this.q.findViewById(R.id.weight_user_name);
        this.d = (HealthTextView) this.q.findViewById(R.id.body_fat_scale_source_text);
        this.ad = (HealthTextView) this.q.findViewById(R.id.last_weight_date);
        this.l = (HealthLevelIndicator) this.q.findViewById(R.id.weight_detail_share_specification_indicator);
        this.aa = (ImageView) this.q.findViewById(R.id.core_sleep_share_user_icon);
        this.v = (LinearLayout) this.q.findViewById(R.id.weight_share_fragment);
        this.ac = this.q.findViewById(R.id.weight_scoring_text);
        this.g = (ImageView) this.q.findViewById(R.id.weight_share_short_map);
        this.e = (LinearLayout) this.q.findViewById(R.id.weight_share_body_type_card_layout);
        this.b = (HealthTextView) this.q.findViewById(R.id.weight_share_body_type_des);
        this.f10184a = (ImageView) this.q.findViewById(R.id.weight_share_body_type_img);
        this.e.setVisibility(8);
        d();
    }

    private void d() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.q.findViewById(R.id.hw_show_health_data_bodyindex_detail_recycle);
        this.s = healthRecycleView;
        healthRecycleView.setLayoutManager(new GridLayoutManager(this.h, 3));
        this.s.setIsScroll(false);
        if (this.s.getItemDecorationCount() <= 0) {
            int dimensionPixelSize = this.h.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(Integer.valueOf(dimensionPixelSize));
            arrayList.add(Integer.valueOf(dimensionPixelSize));
            arrayList.add(Integer.valueOf(dimensionPixelSize));
            arrayList.add(Integer.valueOf(this.h.getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a)));
            int dimensionPixelSize2 = this.h.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
            this.s.addItemDecoration(new RecyclerItemDecoration(dimensionPixelSize2, dimensionPixelSize2, arrayList));
        }
    }

    public void a(cfe cfeVar, Context context) {
        if (d(cfeVar, context)) {
            return;
        }
        this.k = cfeVar;
        if (TextUtils.isEmpty(cfeVar.n())) {
            this.d.setVisibility(8);
        } else {
            this.d.setText(context.getString(R$string.IDS_body_fat_scale_source, this.k.n()));
        }
        o();
        this.ad.setText(nsj.c(this.h, cfeVar.au(), 21));
        this.ai.setText(UnitUtil.e(this.k.h(), 1, 0));
        e();
        boolean z = this.o;
        if (this.k.isVisible(31, z)) {
            g();
        } else {
            this.t.setVisibility(4);
            this.ac.setVisibility(4);
            this.x.setVisibility(4);
        }
        if (this.r != null) {
            c(z, context);
        }
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser.d() > 0) {
            d(cfeVar, currentUser);
        } else {
            a(cfeVar);
        }
        this.ab.setWeightCommonView(this.k, z, true);
        l();
    }

    private void l() {
        if (this.m) {
            return;
        }
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).updateShareUserView("HealthWeight_WeightShareFragment", 8, this.aa, this.z);
    }

    private void e() {
        double[] e = doj.e(this.o, this.k.an(), this.k.e());
        if (!qsj.e(e, 2)) {
            health.compact.a.util.LogUtil.c("HealthWeight_WeightShareFragment", "initSpecification, bodyMassIndexValue is out of bound.");
            return;
        }
        float f = (float) e[0];
        float f2 = (float) e[1];
        float f3 = (float) e[2];
        float d = d(f2, 0.1f, true);
        String a2 = qqy.a(0, 1);
        String a3 = qqy.a(0, 2);
        String a4 = qqy.a(0, 3);
        String a5 = qqy.a(0, 4);
        int color = this.h.getColor(qrf.a(1));
        int color2 = this.h.getColor(qrf.a(2));
        int color3 = this.h.getColor(qrf.a(3));
        int color4 = this.h.getColor(qrf.a(4));
        double[] d2 = doj.d(Utils.o());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm((float) d2[0], f, color, a2));
        arrayList.add(new nmm(f, d, color2, a3));
        arrayList.add(new nmm(d, f3, color3, a4));
        arrayList.add(new nmm(f3, (float) d2[1], color4, a5));
        qsj.a(this.l, arrayList, e, d2);
        dEo_(this.q.findViewById(R.id.weight_body_level_rang_low), a2, color);
        dEo_(this.q.findViewById(R.id.weight_body_level_rang_stand), a3, color2);
        dEo_(this.q.findViewById(R.id.weight_body_level_rang_high), a4, color3);
        dEo_(this.q.findViewById(R.id.weight_body_level_rang_super_high), a5, color4);
        SpannableString dEm_ = dEm_(UnitUtil.e(UnitUtil.a(this.k.ax()), 1, this.k.getFractionDigitByType(0)), 34);
        SpannableString dEm_2 = dEm_(qsj.e(), 14);
        this.l.setLevel((float) UnitUtil.a(this.k.j(), 1), dEm_);
        this.l.setLevelUnit(dEm_2);
        this.l.d(nsf.b(R.dimen._2131363029_res_0x7f0a04d5));
        this.l.setVisibility(0);
        this.l.invalidate();
        ReleaseLogUtil.b("HealthWeight_WeightShareFragment", "initLevel mIndicator ", this.l);
    }

    private SpannableString dEm_(String str, int i) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(i, true), 0, str.length(), 18);
        return spannableString;
    }

    private float d(float f, float f2, boolean z) {
        BigDecimal valueOf = BigDecimal.valueOf(f);
        BigDecimal valueOf2 = BigDecimal.valueOf(f2);
        if (z) {
            return valueOf.add(valueOf2).floatValue();
        }
        return valueOf.subtract(valueOf2).floatValue();
    }

    private void dEo_(View view, String str, int i) {
        view.setVisibility(0);
        ImageView imageView = (ImageView) view.findViewById(R.id.weight_body_level_rang_item_icon);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(i);
        imageView.setImageDrawable(gradientDrawable);
        ((HealthTextView) view.findViewById(R.id.weight_body_level_rang_item_name)).setText(str);
        view.findViewById(R.id.weight_body_level_rang_item_content).setVisibility(8);
    }

    private void c(boolean z, Context context) {
        LogUtil.c("HealthWeight_WeightShareFragment", "mMainUser is not null ");
        e(z);
        h();
        this.aa.setImageResource(0);
        if (this.m) {
            return;
        }
        if (!TextUtils.isEmpty(this.r.e())) {
            dEq_(context, this.r.e(), this.aa);
        } else if (this.r.Ex_() == null) {
            this.aa.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
        } else {
            this.aa.setImageBitmap(nrf.cHX_(this.r.Ex_()));
        }
    }

    private void a(final cfe cfeVar) {
        MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: qiw
            @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightShareFragment.this.d(cfeVar, i, (cfi) obj);
            }
        });
    }

    public /* synthetic */ void d(final cfe cfeVar, final int i, final cfi cfiVar) {
        Context context = this.h;
        if (context == null || !(context instanceof Activity)) {
            LogUtil.h("HealthWeight_WeightShareFragment", "mContext is null or mContext is not Activity");
        } else {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: qja
                @Override // java.lang.Runnable
                public final void run() {
                    WeightShareFragment.this.d(cfiVar, i, cfeVar);
                }
            });
        }
    }

    public /* synthetic */ void d(cfi cfiVar, int i, cfe cfeVar) {
        if (cfiVar == null || i != 0) {
            LogUtil.h("HealthWeight_WeightShareFragment", "loadDataSuccess getCurrentUser: currentUser is null return");
            cfiVar = MultiUsersManager.INSTANCE.getCurrentUser();
        }
        d(cfeVar, cfiVar);
    }

    private void d(cfe cfeVar, cfi cfiVar) {
        this.c = qsj.b(cfiVar.i(), cfeVar.au(), cfiVar, this.o);
        b();
        i();
    }

    private void g() {
        if (this.k == null) {
            LogUtil.h("HealthWeight_WeightShareFragment", "mLatestBean is null!");
            return;
        }
        this.t.setVisibility(0);
        this.x.setVisibility(0);
        this.ac.setVisibility(0);
        this.x.setText(qqw.b(this.k));
    }

    private void o() {
        cfi cfiVar;
        if (!this.m && (cfiVar = this.r) != null) {
            if (cfiVar.n() == 1) {
                this.z.setText(((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).getShareNickName());
                this.z.setVisibility(0);
                return;
            } else {
                if (!TextUtils.isEmpty(this.r.h())) {
                    this.z.setText(this.r.h());
                } else {
                    this.z.setText(R$string.IDS_share_nick_default_name);
                }
                this.z.setVisibility(0);
                return;
            }
        }
        if (this.o) {
            this.aa.setVisibility(8);
            this.z.setVisibility(8);
        }
    }

    private void i() {
        if (this.n && !this.o) {
            if (CommonUtil.bv()) {
                this.e.setVisibility(0);
                j();
                return;
            }
            return;
        }
        this.e.setVisibility(8);
    }

    private boolean d(cfe cfeVar, Context context) {
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightShareFragment", "intent get WeightBean is null ..");
            return true;
        }
        if (context == null) {
            LogUtil.h("HealthWeight_WeightShareFragment", "intent get context is null ..");
            return true;
        }
        if (this.q != null) {
            return false;
        }
        LogUtil.h("HealthWeight_WeightShareFragment", "intent get mRootView is null ..");
        return true;
    }

    private void dEq_(Context context, String str, ImageView imageView) {
        if (!"default".equals(str)) {
            Bitmap cIe_ = nrf.cIe_(context, str);
            if (cIe_ != null) {
                imageView.setImageBitmap(cIe_);
                return;
            }
            return;
        }
        imageView.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
    }

    private void h() {
        this.s.setAdapter(new WeightBodyIndexRecycleAdapter(this.h, this.j, this.k, this.o));
    }

    private void e(boolean z) {
        List<qkd> list;
        if (this.k == null || (list = this.j) == null) {
            LogUtil.h("HealthWeight_WeightShareFragment", "setListItem latestBeanis null. ");
            return;
        }
        list.clear();
        this.j.addAll(qqv.e(this.k, false, z));
        if (this.j.size() > 0) {
            int i = -1;
            int i2 = -1;
            int i3 = -1;
            for (int i4 = 0; i4 < this.j.size(); i4++) {
                if (this.j.get(i4).e() == 9) {
                    i = i4;
                }
                if (this.j.get(i4).e() == 13) {
                    i2 = i4;
                }
                if (this.j.get(i4).e() == 25) {
                    i3 = i4;
                }
            }
            if (z) {
                if (i != -1) {
                    this.j.remove(i);
                }
                if (i2 != -1) {
                    this.j.remove(i2);
                }
                if (i3 != -1) {
                    this.j.remove(i3);
                    return;
                }
                return;
            }
            return;
        }
        LogUtil.h("HealthWeight_WeightShareFragment", "mIndexRecycleItems size == 0  ");
    }

    private String dEp_(Bitmap bitmap) {
        FileOutputStream openOutputStream;
        String c = CommonUtil.c(jcu.f);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("HealthWeight_WeightShareFragment", "saveBmpToFile pathName is null");
            return "";
        }
        File file = new File(c);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.h("HealthWeight_WeightShareFragment", "saveBmpToFile:mkdirs error ");
        }
        File file2 = new File(file, "weight_share_tmp.jpg");
        try {
            String c2 = CommonUtil.c(file2.getCanonicalPath());
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    try {
                        openOutputStream = FileUtils.openOutputStream(file2);
                    } catch (IOException unused) {
                        LogUtil.b("HealthWeight_WeightShareFragment", "saveBmpToFile IOException");
                        if (0 != 0) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused2) {
                                LogUtil.b("HealthWeight_WeightShareFragment", "saveBmpToFile IOException finally");
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    LogUtil.b("HealthWeight_WeightShareFragment", "saveBmpToFile IllegalArgumentException ", e.getMessage());
                    if (0 != 0) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused3) {
                            LogUtil.b("HealthWeight_WeightShareFragment", "saveBmpToFile IOException finally");
                        }
                    }
                }
                if (!CommonUtil.a(file2, c2)) {
                    LogUtil.h("HealthWeight_WeightShareFragment", "saveBmpToFile invalidate file path");
                    if (openOutputStream != null) {
                        try {
                            openOutputStream.close();
                        } catch (IOException unused4) {
                            LogUtil.b("HealthWeight_WeightShareFragment", "saveBmpToFile IOException finally");
                        }
                    }
                    return "";
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, openOutputStream);
                openOutputStream.flush();
                if (openOutputStream != null) {
                    try {
                        openOutputStream.close();
                    } catch (IOException unused5) {
                        LogUtil.b("HealthWeight_WeightShareFragment", "saveBmpToFile IOException finally");
                    }
                }
                return c2;
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused6) {
                        LogUtil.b("HealthWeight_WeightShareFragment", "saveBmpToFile IOException finally");
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            LogUtil.h("HealthWeight_WeightShareFragment", "generate ioexception when saveBitToFile:", e2.getMessage());
            return "";
        } catch (SecurityException e3) {
            LogUtil.h("HealthWeight_WeightShareFragment", "generate securityException when saveBitToFile:", e3.getMessage());
            return "";
        }
    }

    public void a() {
        fdu fduVar;
        LinearLayout linearLayout = this.v;
        if (linearLayout == null) {
            LogUtil.h("HealthWeight_WeightShareFragment", "shareWeightFragment: has no parent Activity or view null!");
            nrh.e(this.h, R$string.IDS_motiontrack_share_fail_tip);
            return;
        }
        Bitmap bGg_ = jdv.bGg_(linearLayout);
        if (bGg_ == null) {
            LogUtil.h("HealthWeight_WeightShareFragment", "screenCut is null");
            nrh.e(this.h, R$string.IDS_motiontrack_share_fail_tip);
            return;
        }
        String dEp_ = dEp_(bGg_);
        if (TextUtils.isEmpty(dEp_)) {
            LogUtil.h("HealthWeight_WeightShareFragment", "The path is invalid, return");
            return;
        }
        if (Utils.g()) {
            fduVar = new fdu(1);
            fduVar.awp_(bGg_);
        } else {
            fdu fduVar2 = new fdu(4);
            fduVar2.d(dEp_);
            fduVar = fduVar2;
        }
        ReleaseLogUtil.b("HealthWeight_WeightShareFragment", "shareWeightFragment mIsGuestMeasureShare:", Boolean.valueOf(this.m));
        fduVar.h(!this.m);
        CardConstants.d(fduVar, this.h, AnalyticsValue.BI_TRACK_WEIGHT_DATA_SHARE_BUTTON_2100012);
    }

    private boolean b() {
        this.n = false;
        this.u = 0;
        this.f = 0;
        List<cfe> list = this.c;
        if (list == null || list.size() <= 1) {
            LogUtil.h("HealthWeight_WeightShareFragment", "isShowBadyTypeCard mBodyTypeDataList is null or size <= 1");
            return this.n;
        }
        LogUtil.a("HealthWeight_WeightShareFragment", "isShowBadyTypeCard mBodyTypeDataList size = ", Integer.valueOf(this.c.size()));
        List<cfe> list2 = this.c;
        cfe cfeVar = list2.get(list2.size() - 1);
        if (jdl.f(cfeVar.au(), this.k.au())) {
            return this.n;
        }
        int size = this.c.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            if (jdl.f(cfeVar.au(), this.c.get(size).au())) {
                size--;
            } else if (this.c.get(size).au() == this.k.au() && cfeVar.t() > 0) {
                this.u = qsj.e(cfeVar);
                this.f = qsj.e(this.k);
                this.y = cfeVar.au();
                this.i = this.k.au();
            }
        }
        LogUtil.a("HealthWeight_WeightShareFragment", "isShowBadyTypeCard, mStartType = ", Integer.valueOf(this.u), "; mEndType = ", Integer.valueOf(this.f), "; mStartTime=", Long.valueOf(this.y), "; mEndTime=", Long.valueOf(this.i));
        if (this.u != this.f) {
            this.n = true;
        }
        return this.n;
    }

    private void j() {
        String string;
        String a2 = UnitUtil.a(new Date(this.y), 16);
        String a3 = UnitUtil.a(new Date(this.i), 16);
        int i = this.f;
        if (i == 7 || i == 4 || i == 8) {
            string = this.h.getResources().getString(R$string.IDS_hw_weight_body_type_interpretation_negative);
        } else {
            string = this.h.getResources().getString(R$string.IDS_hw_weight_body_type_interpretation_positive);
        }
        this.b.setText(String.format(string, a2, a3, qqy.a(this.u), qqy.a(this.f)));
        CardConstants.dFe_(this.f, this.f10184a);
    }
}
