package com.huawei.pluginsocialshare.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.activity.DownloadInterface;
import com.huawei.pluginsocialshare.adapter.EditShareAdapter;
import com.huawei.pluginsocialshare.view.EditShareCustomFragment;
import com.huawei.pluginsocialshare.view.ShareButtonView;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.view.ShareSquareLayout;
import defpackage.fdu;
import defpackage.fdy;
import defpackage.fdz;
import defpackage.fea;
import defpackage.feb;
import defpackage.fec;
import defpackage.fef;
import defpackage.feh;
import defpackage.koq;
import defpackage.mto;
import defpackage.mud;
import defpackage.mus;
import defpackage.mut;
import defpackage.muu;
import defpackage.mva;
import defpackage.mvl;
import defpackage.mvp;
import defpackage.mvs;
import defpackage.mwa;
import defpackage.mwd;
import defpackage.mwe;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class EditShareCustomFragment extends BaseFragment implements EditShareAdapter.OnBackgroundChangeListener, DownloadInterface {
    private String aa;
    private float ab;
    private String ac;
    private boolean ad;
    private RelativeLayout ae;
    private ImageView af;
    private RelativeLayout ag;
    private float ai;
    private int al;
    private HealthScrollView am;
    private ShareButtonView ao;
    private fdz ap;
    private ImageView aq;
    private ShareSquareLayout ar;
    private fdu as;
    private String at;
    private HealthRecycleView au;
    private mva av;
    private feh aw;
    private EditShareAdapter ax;
    private EditShareAdapter az;
    private HealthRecycleView ba;
    private HealthSwitchButton bb;
    private boolean c;
    private EditShareAdapter f;
    private boolean g;
    private boolean h;
    private HealthRecycleView i;
    private boolean j;
    private HealthColumnSystem k;
    private float l;
    private Bitmap o;
    private Context r;
    private FrameLayout t;
    private RelativeLayout v;
    private HealthTextView w;
    private boolean y;
    private String z;
    private static final Rect d = new Rect(0, 720, 1080, 1080);
    private static final Rect b = new Rect(800, 48, 1032, 168);

    /* renamed from: a, reason: collision with root package name */
    private static final Rect f8527a = new Rect(0, 1046, 660, 1436);
    private static final int[] e = {R.mipmap.hw_health_edit_share_pic_stickers_gas, R.mipmap.hw_health_edit_share_pic_stickers_lightning, R.mipmap.hw_health_edit_share_pic_stickers_lightning2, R.mipmap.hw_health_edit_share_pic_stickers_sweat};
    private List<ShareDataInfo> n = new ArrayList(8);
    private List<ShareDataInfo> p = new ArrayList(8);
    private List<ShareDataInfo> an = new ArrayList(8);
    private List<ShareDataInfo> ak = new ArrayList(8);
    private List<EditShareCommonView> q = new ArrayList(8);
    private int bd = -1;
    private int x = -1;
    private int ay = -1;
    private int s = 0;
    private int m = 1;
    private int ah = -1;
    private int bc = -1;
    private int aj = 0;
    private List<Integer> u = new ArrayList();

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void notifyShareDataChanged(fea feaVar) {
        LogUtil.a("Share_EditShareCustomFragment", "notifyShareDataChanged");
        this.at = "path_type";
        d(feaVar);
        g(feaVar);
        j(feaVar);
        h(feaVar);
        updateAllFragment(true);
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void notifyBackgroudChanged(fea feaVar) {
        List<ShareDataInfo> e2 = mwd.e(feaVar.d());
        mwd.b(e2);
        if (this.f != null) {
            if (this.ad || LanguageUtil.m(this.r)) {
                this.f.b(e2);
            }
        }
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void notifyBackgroudFail() {
        if (this.f != null) {
            if ((this.ad || !LanguageUtil.m(this.r)) && !this.ad) {
                return;
            }
            this.f.e();
        }
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void notifyDownloadDataFail() {
        b(this.ap.c());
        updateAllFragment(true);
    }

    private void d(fea feaVar) {
        this.c = true;
        List<ShareDataInfo> e2 = mwd.e(feaVar.d());
        mwd.b(e2);
        this.n.clear();
        i();
        this.n.addAll(e2);
        c(e2);
    }

    private void g(fea feaVar) {
        List<ShareDataInfo> e2 = mwd.e(feaVar.a());
        mwd.b(e2);
        refreshDataMark(e2);
    }

    private void d(List<ShareDataInfo> list, boolean z) {
        Iterator<ShareDataInfo> it = list.iterator();
        while (it.hasNext()) {
            ShareDataInfo next = it.next();
            if (z && (next instanceof fdy)) {
                if (this.u.contains(Integer.valueOf(((fdy) next).a()))) {
                    it.remove();
                }
            } else if (next != null && this.u.contains(Integer.valueOf(next.getId()))) {
                it.remove();
            }
        }
    }

    private void j(fea feaVar) {
        List<ShareDataInfo> e2 = mwd.e(feaVar.b());
        mwd.b(e2);
        this.an.clear();
        this.an.addAll(e2);
    }

    private void h(fea feaVar) {
        List<ShareDataInfo> e2 = mwd.e(feaVar.e());
        mwd.b(e2);
        d(e2, true);
        this.ak.clear();
        this.ak.addAll(e2);
    }

    private void r() {
        f();
        if ((!TextUtils.isEmpty(this.z) || (TextUtils.isEmpty(this.ac) && TextUtils.isEmpty(this.aa))) && koq.d(this.ak, this.aj) && (this.ak.get(this.aj) instanceof fdy)) {
            s();
        } else {
            p();
            refreshShareLayoutNoRecommend();
        }
    }

    private void p() {
        if (TextUtils.isEmpty(this.ac) || TextUtils.isEmpty(this.aa)) {
            return;
        }
        d(CommonUtil.h(this.ac), CommonUtil.h(this.aa), -1);
        if (this.m == -1) {
            this.m = 1;
        }
        if (this.s == -1) {
            this.s = 0;
        }
    }

    private void f() {
        if (!TextUtils.isEmpty(this.z)) {
            for (int i = 0; i < this.ak.size(); i++) {
                ShareDataInfo shareDataInfo = this.ak.get(i);
                if (shareDataInfo != null && shareDataInfo.getId() == CommonUtil.h(this.z)) {
                    this.aj = i;
                    return;
                }
            }
            return;
        }
        if (TextUtils.isEmpty(this.ac) || TextUtils.isEmpty(this.aa)) {
            return;
        }
        this.aj = -1;
    }

    private void s() {
        if (koq.d(this.ak, this.aj) && (this.ak.get(this.aj) instanceof fdy)) {
            fdy fdyVar = (fdy) this.ak.get(this.aj);
            int a2 = fdyVar.a();
            EditShareCommonView a3 = a(a2);
            if (a3 == null && !this.q.isEmpty() && this.q.get(0) != null) {
                a3 = this.q.get(0);
                a2 = a3.getWatermarkId();
            }
            int d2 = fdyVar.d();
            String c = c(d2, 2);
            if (TextUtils.isEmpty(c) && this.n.size() > 1) {
                c = this.n.get(1).getPath();
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            this.o = BitmapFactory.decodeFile(c, options);
            this.ar.setBackground(new BitmapDrawable(this.r.getResources(), this.o));
            Bitmap bitmap = this.o;
            Rect rect = d;
            this.bd = nrf.c(nrf.cJm_(bitmap, rect));
            this.x = nrf.c(nrf.cJl_(this.o, rect));
            this.ay = nrf.c(nrf.cJm_(this.o, b));
            int e2 = fdyVar.e();
            d(d2, a2, e2);
            if (this.m == -1) {
                this.m = 1;
            }
            v();
            if (a3 != null) {
                a3.refreshUi(this.bd, this.x);
                a3.refreshTopUi(this.ay);
                View view = a3.getView();
                if (view != null) {
                    cqO_(view);
                }
            } else {
                w();
            }
            String c2 = c(e2, 4);
            if (c2 != null) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(this.r.getResources(), BitmapFactory.decodeFile(c2, options));
                this.af.setVisibility(0);
                this.af.setBackground(bitmapDrawable);
            }
        }
    }

    private void d(int i, int i2, int i3) {
        int i4 = 0;
        while (true) {
            if (i4 >= this.n.size()) {
                break;
            }
            if (this.n.get(i4).getId() == i) {
                this.m = i4;
                break;
            } else {
                this.m = -1;
                i4++;
            }
        }
        int i5 = 0;
        while (true) {
            if (i5 >= this.p.size()) {
                break;
            }
            if (this.p.get(i5).getId() == i2) {
                this.s = i5;
                break;
            } else {
                this.s = -1;
                i5++;
            }
        }
        for (int i6 = 0; i6 < this.an.size(); i6++) {
            if (this.an.get(i6).getId() == i3) {
                this.ah = i6;
                this.bc = i6;
                return;
            } else {
                this.ah = -1;
                this.bc = -1;
            }
        }
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void refreshShareLayoutNoRecommend() {
        boolean z = EnvironmentInfo.k() || !(EnvironmentInfo.k() || this.m == 0);
        if (koq.c(this.n) && z && koq.d(this.n, this.m)) {
            ShareDataInfo shareDataInfo = this.n.get(this.m);
            if (shareDataInfo instanceof mvp) {
                this.o = BitmapFactory.decodeResource(this.r.getResources(), ((mvp) shareDataInfo).e());
            } else {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                this.o = BitmapFactory.decodeFile(this.n.get(this.m).getPath(), options);
            }
        }
        Bitmap bitmap = this.o;
        if (bitmap != null) {
            cqY_(bitmap);
            this.ar.setBackground(nrf.cHq_(this.o));
        }
        q();
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void updateAllFragment(boolean z) {
        r();
        EditShareAdapter editShareAdapter = this.ax;
        if (editShareAdapter != null) {
            editShareAdapter.c(this.an, this.ah, false);
        }
        EditShareAdapter editShareAdapter2 = this.az;
        if (editShareAdapter2 != null) {
            editShareAdapter2.c(this.p, this.s, false);
        }
        if (this.f != null) {
            if (this.i.getLayoutManager() instanceof GridLayoutManager) {
                if ((nsn.ag(this.r) && this.n.size() > 15) || (!nsn.ag(this.r) && this.n.size() > 9)) {
                    ((GridLayoutManager) this.i.getLayoutManager()).setSpanCount(2);
                } else {
                    ((GridLayoutManager) this.i.getLayoutManager()).setSpanCount(1);
                }
            }
            this.f.c(this.n, this.m, true);
        }
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void refreshDataMark(List<ShareDataInfo> list) {
        mwe mweVar = new mwe(this.aw, this.r);
        d(mweVar);
        mweVar.constructDownloadWatermarkViewList(list);
        mweVar.setDoMainColor(this.x);
        mweVar.setTopWidgetColor(this.ay);
        mweVar.setWidgetColor(this.bd);
        this.q.clear();
        for (EditShareCommonView editShareCommonView : mweVar.getEditShareCommonViewList()) {
            if (editShareCommonView != null) {
                if (editShareCommonView.getIsNeedHide()) {
                    this.u.add(Integer.valueOf(editShareCommonView.getWatermarkId()));
                } else {
                    this.q.add(editShareCommonView);
                }
            }
        }
        this.p.clear();
        d(list, false);
        this.p.addAll(list);
    }

    private void d(mwe mweVar) {
        fdu fduVar = this.as;
        if (fduVar == null) {
            fduVar = mto.d();
            LogUtil.a("Share_EditShareCustomFragment", "setRequestShareModule getShareContent");
        }
        if (fduVar == null) {
            LogUtil.h("Share_EditShareCustomFragment", "setRequestShareModule shareContent is null");
        } else {
            mweVar.c(fduVar.m());
        }
    }

    private void q() {
        v();
        if (koq.d(this.q, this.s)) {
            EditShareCommonView editShareCommonView = this.q.get(this.s);
            editShareCommonView.refreshUi(this.bd, this.x);
            editShareCommonView.refreshTopUi(this.ay);
            View view = editShareCommonView.getView();
            if (view != null) {
                cqO_(view);
            }
        }
    }

    private void v() {
        if (koq.d(this.p, this.s) && (this.p.get(this.s) instanceof fef)) {
            String b2 = ((fef) this.p.get(this.s)).b();
            if (TextUtils.isEmpty(b2)) {
                return;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap decodeFile = BitmapFactory.decodeFile(b2, options);
            Rect rect = f8527a;
            this.bd = nrf.c(nrf.cJm_(decodeFile, rect));
            this.x = nrf.c(nrf.cJl_(decodeFile, rect));
            this.ay = nrf.c(nrf.cJm_(decodeFile, rect));
        }
    }

    private void cqY_(Bitmap bitmap) {
        Rect rect = d;
        this.bd = nrf.c(nrf.cJm_(bitmap, rect));
        this.x = nrf.c(nrf.cJl_(bitmap, rect));
        this.ay = nrf.c(nrf.cJm_(bitmap, b));
    }

    private EditShareCommonView a(int i) {
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            EditShareCommonView editShareCommonView = this.q.get(i2);
            if (editShareCommonView.getWatermarkId() == i) {
                this.s = i2;
                return editShareCommonView;
            }
        }
        return null;
    }

    private String c(int i, int i2) {
        List arrayList = new ArrayList();
        if (i2 == 2) {
            arrayList = this.n;
        } else if (i2 == 4) {
            arrayList = this.an;
        } else {
            LogUtil.a("Share_EditShareCustomFragment", "type is unknow");
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ShareDataInfo shareDataInfo = (ShareDataInfo) arrayList.get(i3);
            if (shareDataInfo.getId() == i) {
                if (i2 == 2) {
                    this.m = i3;
                } else {
                    this.ah = i3;
                }
                return shareDataInfo.getPath();
            }
        }
        return "";
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Share_EditShareCustomFragment", "onCreate");
        this.r = getContext();
        fdu d2 = mto.d();
        this.as = d2;
        if (d2 == null) {
            LogUtil.a("Share_EditShareCustomFragment", "EditShareActivity_data mShareContent is null");
            return;
        }
        Iterator<fdz> it = d2.b().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            fdz next = it.next();
            if (next != null && next.h()) {
                mto.b().b(this.r, next, ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo());
                break;
            }
        }
        fdz c = mto.c();
        this.ap = c;
        if (c == null) {
            LogUtil.a("Share_EditShareCustomFragment", "EditShareActivity_data mShareEditContent is null");
            return;
        }
        this.av = new mva(this);
        this.k = new HealthColumnSystem(this.r, 1);
        this.y = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a("Share_EditShareCustomFragment", "enter onResume");
        setUserVisibleHint(getUserVisibleHint());
        a();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        LogUtil.a("Share_EditShareCustomFragment", "setUserVisibleHint, isVisibleToUser :", Boolean.valueOf(z), " mIsFirst :", Boolean.valueOf(this.y));
        if (z && this.y) {
            t();
            l();
            g();
            this.y = false;
        }
    }

    private void g() {
        GridLayoutManager gridLayoutManager;
        if ((nsn.ag(this.r) && this.n.size() > 15) || (!nsn.ag(this.r) && this.n.size() > 9)) {
            gridLayoutManager = new GridLayoutManager(this.r, 2, 0, false);
        } else {
            gridLayoutManager = new GridLayoutManager(this.r, 1, 0, false);
        }
        this.i.setLayoutManager(gridLayoutManager);
        this.f = new EditShareAdapter(getActivity(), this.i, this.m, this, 1);
        if (!this.ad && LanguageUtil.m(this.r)) {
            this.f.e(new EditShareAdapter.OnMoreListener() { // from class: mwg
                @Override // com.huawei.pluginsocialshare.adapter.EditShareAdapter.OnMoreListener
                public final void onLoadingMore() {
                    EditShareCustomFragment.this.b();
                }
            });
        }
        this.f.c(this.n, this.m, true);
        this.i.setAdapter(this.f);
        this.ba.setLayoutManager(new GridLayoutManager(this.r, 1, 0, false));
        EditShareAdapter editShareAdapter = new EditShareAdapter(getActivity(), this.ba, this.s, this, 2);
        this.az = editShareAdapter;
        editShareAdapter.c(this.p, this.s, false);
        this.ba.setAdapter(this.az);
        this.au.setLayoutManager(new GridLayoutManager(this.r, 1, 0, false));
        EditShareAdapter editShareAdapter2 = new EditShareAdapter(getActivity(), this.au, this.ah, this, 3);
        this.ax = editShareAdapter2;
        editShareAdapter2.c(this.an, this.ah, false);
        this.au.setAdapter(this.ax);
    }

    public /* synthetic */ void b() {
        int c = this.ap.c();
        if (this.c) {
            mva mvaVar = this.av;
            if (mvaVar != null) {
                mvaVar.cqh_(true, getActivity(), c);
            }
            this.f.d();
        }
    }

    private void c(List<ShareDataInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (ShareDataInfo shareDataInfo : list) {
            if (!(shareDataInfo instanceof mut) && !(shareDataInfo instanceof mvp)) {
                arrayList.add(shareDataInfo);
            }
        }
        this.f.e(arrayList);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.hw_health_custom_layout, viewGroup, false);
        this.ae = (RelativeLayout) inflate.findViewById(R.id.activity_main);
        if (LanguageUtil.bc(this.r)) {
            this.ae.setRotationY(180.0f);
        }
        cqQ_(inflate);
        m();
        cqU_(inflate);
        return inflate;
    }

    private void cqU_(View view) {
        ShareButtonView shareButtonView = (ShareButtonView) view.findViewById(R.id.share_button_view);
        this.ao = shareButtonView;
        shareButtonView.setShareContent(this.as);
        this.ao.setPerViewImage(this.ar);
        this.ao.setLogLayout(this.ag);
        this.ao.setBeforeClick(new ShareButtonView.DoBeforeClick() { // from class: com.huawei.pluginsocialshare.view.EditShareCustomFragment.1
            @Override // com.huawei.pluginsocialshare.view.ShareButtonView.DoBeforeClick
            public void onBeforeClick() {
                HashMap<String, String> hashMap = new HashMap<>();
                EditShareCustomFragment.this.a(hashMap);
                EditShareCustomFragment.this.ao.setMap(hashMap);
            }
        });
    }

    private void m() {
        this.z = SharedPreferenceManager.b(this.r, Integer.toString(20002), "shareLastRecommend" + this.ap.c());
        this.ac = SharedPreferenceManager.b(this.r, Integer.toString(20002), "shareLastBackground" + this.ap.c());
        String b2 = SharedPreferenceManager.b(this.r, Integer.toString(20002), "shareLastDataMark" + this.ap.c());
        this.aa = b2;
        LogUtil.a("Share_EditShareCustomFragment", "mLastRecommendId --", this.z, "--mLastBackgroundId--", this.ac, "--mLastDataMarkId--", b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, int i) {
        LogUtil.a("Share_EditShareCustomFragment", "enter downLoadShareSource");
        if (z) {
            mvs.c(i);
        }
        mva mvaVar = this.av;
        if (mvaVar != null) {
            if (this.ad) {
                mvaVar.cqg_(true, getActivity(), i, false);
            } else {
                mvaVar.cqg_(true, getActivity(), i, true);
            }
        }
    }

    private void cqQ_(View view) {
        this.ag = (RelativeLayout) view.findViewById(R.id.hw_health_share_log);
        this.aq = (ImageView) view.findViewById(R.id.hw_health_edit_share_logo);
        this.w = (HealthTextView) view.findViewById(R.id.hw_health_edit_share_tv);
        this.ar = (ShareSquareLayout) view.findViewById(R.id.hw_health_edit_share_show);
        this.v = (RelativeLayout) view.findViewById(R.id.download_error_layout);
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.sub_header_backgroud);
        HealthSubHeader healthSubHeader2 = (HealthSubHeader) view.findViewById(R.id.sub_header_watermark);
        HealthSubHeader healthSubHeader3 = (HealthSubHeader) view.findViewById(R.id.sub_header_stick);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        healthSubHeader2.setSubHeaderBackgroundColor(0);
        healthSubHeader3.setSubHeaderBackgroundColor(0);
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginsocialshare.view.EditShareCustomFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EditShareCustomFragment.this.v.setVisibility(8);
                EditShareCustomFragment.this.d(true, EditShareCustomFragment.this.ap.c());
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        a(false);
        this.t = (FrameLayout) view.findViewById(R.id.hw_health_edit_share_activity_show);
        this.af = (ImageView) view.findViewById(R.id.water_mack_imgview);
        HealthScrollView healthScrollView = (HealthScrollView) view.findViewById(R.id.hw_health_edit_share_scrollview);
        this.am = healthScrollView;
        healthScrollView.setOverScrollable(false);
        this.i = (HealthRecycleView) view.findViewById(R.id.rv_backgroud);
        this.ba = (HealthRecycleView) view.findViewById(R.id.rv_watermark);
        this.au = (HealthRecycleView) view.findViewById(R.id.rv_stick);
        cqV_(view);
        ab();
    }

    private void cqV_(View view) {
        if (CommonUtil.bv() || CommonUtil.as()) {
            return;
        }
        ((RelativeLayout) view.findViewById(R.id.layout_share_userinfo)).setVisibility(0);
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.sub_header_userinfo);
        healthSubHeader.setVisibility(0);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) view.findViewById(R.id.share_userinfo_switch);
        this.bb = healthSwitchButton;
        healthSwitchButton.setChecked(mwd.i());
        this.bb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.pluginsocialshare.view.EditShareCustomFragment$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditShareCustomFragment.this.crd_(compoundButton, z);
            }
        });
    }

    /* synthetic */ void crd_(CompoundButton compoundButton, boolean z) {
        if (this.g) {
            LogUtil.a("Share_EditShareCustomFragment", "just update status");
            this.g = false;
            b(z);
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        if (z) {
            mwd.e(true);
            b(true);
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            mwd.cqJ_(this.r, R.string._2130847207_res_0x7f0225e7, R.string._2130847201_res_0x7f0225e1, new View.OnClickListener() { // from class: mwl
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    EditShareCustomFragment.this.crb_(view);
                }
            }, new View.OnClickListener() { // from class: mwj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    EditShareCustomFragment.this.crc_(view);
                }
            });
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    public /* synthetic */ void crb_(View view) {
        mwd.e(false);
        b(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void crc_(View view) {
        this.bb.setChecked(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(boolean z) {
        if (Utils.o() && z) {
            this.aq.setVisibility(8);
            this.w.setText(R.string._2130843902_res_0x7f0218fe);
        } else {
            this.aq.setVisibility(0);
            this.w.setText(R.string.IDS_app_name_health);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ab();
    }

    private void ab() {
        if (this.ar == null || this.r == null || this.ag == null) {
            LogUtil.h("Share_EditShareCustomFragment", "mShareLayout mContext or mLogoLayout is null");
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.j = activity.isInMultiWindowMode();
        }
        LogUtil.a("Share_EditShareCustomFragment", "tahitiAdaptation isMultiWindowMode:", Boolean.valueOf(this.j));
        this.al = getResources().getDisplayMetrics().widthPixels;
        this.h = EnvironmentInfo.k() && nsn.ac(this.r);
        if (nsn.ag(this.r) || this.k.f() >= 8 || this.h) {
            aa();
            return;
        }
        if (this.j) {
            y();
            return;
        }
        ImageView imageView = this.af;
        HealthScrollView healthScrollView = this.am;
        int i = this.al;
        imageView.setOnTouchListener(new muu(healthScrollView, i, i));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.af.setLayoutParams(layoutParams);
        this.ar.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.height = nsn.c(this.r, 48.0f);
        this.ag.setLayoutParams(layoutParams2);
    }

    private void y() {
        ImageView imageView = this.af;
        HealthScrollView healthScrollView = this.am;
        int i = this.al;
        imageView.setOnTouchListener(new muu(healthScrollView, i, i));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        int c = nsn.c(this.r, this.al < 1080 || getResources().getDisplayMetrics().heightPixels > 1800 ? 24.0f : 100.0f);
        int c2 = nsn.c(this.r, 0.0f);
        layoutParams.setMargins(c, c2, c, c2);
        layoutParams.width = this.al;
        layoutParams.height = -2;
        this.ar.setLayoutParams(layoutParams);
    }

    private void aa() {
        float f;
        HealthColumnSystem healthColumnSystem;
        this.ai = this.r.getResources().getDimension(R.dimen._2131364635_res_0x7f0a0b1b);
        if (this.h) {
            this.l = this.k.d(2) + this.r.getResources().getDimension(R.dimen._2131363591_res_0x7f0a0707);
            f = 0.0f;
        } else {
            int i = 4;
            if (nsn.ae(this.r)) {
                if (this.j) {
                    healthColumnSystem = this.k;
                } else {
                    healthColumnSystem = this.k;
                    i = 6;
                }
                this.l = healthColumnSystem.d(i);
            } else {
                this.l = this.k.d(4) + this.r.getResources().getDimension(R.dimen._2131363696_res_0x7f0a0770);
            }
            f = 28.0f;
        }
        float f2 = this.ai;
        float f3 = this.l;
        this.ab = f2 + f3;
        int i2 = (int) f3;
        this.af.setOnTouchListener(new muu(this.am, i2, i2));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.af.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.height = (int) this.l;
        layoutParams2.width = (int) this.l;
        layoutParams2.addRule(13);
        layoutParams2.setMargins(nsn.c(this.r, this.ab), nsn.c(this.r, f), nsn.c(this.r, this.ab), nsn.c(this.r, 0.0f));
        this.ar.setLayoutParams(layoutParams2);
        int c = nsn.c(this.r, 28.0f);
        int d2 = (int) (this.k.d(2) + nrr.b(this.r) + this.ai);
        if (nsn.ae(this.r)) {
            d2 = (int) (this.k.d(1) + nrr.b(this.r) + this.ai);
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, c + nsn.c(this.r, 8.0f));
        layoutParams3.setMarginStart(d2);
        layoutParams3.setMarginEnd(d2);
        this.ag.setLayoutParams(layoutParams3);
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void showDownloadError() {
        LogUtil.a("Share_EditShareCustomFragment", "showDownloadError");
        this.v.setVisibility(0);
    }

    private void a(fea feaVar) {
        if (feaVar == null || koq.b(feaVar.a())) {
            k();
        } else {
            this.an.clear();
            this.an.addAll(feaVar.b());
        }
    }

    private void k() {
        this.an.clear();
        h();
    }

    private void h() {
        this.an.clear();
        for (int i : e) {
            mvp mvpVar = new mvp();
            mvpVar.a(i);
            this.an.add(mvpVar);
        }
    }

    private void t() {
        if (CommonUtil.bu() || Utils.l()) {
            o();
            return;
        }
        int c = this.ap.c();
        this.ad = Utils.o();
        this.aw = this.ap.g();
        if (CommonUtil.aa(this.r) && ((!this.ad && LanguageUtil.m(this.r)) || this.ad)) {
            this.at = "path_type";
            b(c);
            d(true, c);
        } else if (!CommonUtil.aa(BaseApplication.getContext()) && ((!this.ad && LanguageUtil.m(BaseApplication.getContext())) || this.ad)) {
            nrh.b(BaseApplication.getContext(), R.string._2130841392_res_0x7f020f30);
            b(c);
        } else {
            o();
        }
    }

    private void b(int i) {
        fea c = mvl.b().c(i);
        e(c);
        b(c);
        a(c);
        c(c);
    }

    private void c(fea feaVar) {
        if (feaVar == null || !koq.c(feaVar.e())) {
            return;
        }
        List<ShareDataInfo> e2 = feaVar.e();
        d(e2, true);
        this.ak.clear();
        this.ak.addAll(e2);
    }

    private void e(fea feaVar) {
        List<Integer> m = this.ap.m();
        if (feaVar == null || koq.b(feaVar.d())) {
            this.at = "id_type";
            a(m);
        } else {
            this.at = "path_type";
            b(feaVar.d());
        }
    }

    private void o() {
        this.at = "id_type";
        a(this.ap.m());
        n();
        k();
    }

    private void b(fea feaVar) {
        if (feaVar == null || koq.b(feaVar.a())) {
            n();
        } else {
            refreshDataMark(feaVar.a());
        }
    }

    private void n() {
        List<EditShareCommonView> e2 = e(this.ap.f());
        this.p.clear();
        this.q.clear();
        for (EditShareCommonView editShareCommonView : e2) {
            if (editShareCommonView != null && !editShareCommonView.getIsNeedHide()) {
                mvp mvpVar = new mvp();
                mvpVar.a(editShareCommonView.getBitmap());
                mvpVar.setId(editShareCommonView.getWatermarkId());
                this.p.add(mvpVar);
                this.q.add(editShareCommonView);
            }
        }
    }

    private List<EditShareCommonView> e(List<feb> list) {
        if (koq.b(list)) {
            LogUtil.h("Share_EditShareCustomFragment", "constructLocalDefaultWatermark localDefaultWatermarkInfoList is null");
            return new ArrayList(0);
        }
        LogUtil.a("Share_EditShareCustomFragment", "constructLocalDefaultWatermark");
        mwe mweVar = new mwe(this.ap.g(), this.r);
        mweVar.setDoMainColor(this.x);
        mweVar.setTopWidgetColor(this.bd);
        mweVar.setWidgetColor(this.bd);
        d(mweVar);
        LogUtil.a("Share_EditShareCustomFragment", "localDefaultWatermarkInfoList size:", Integer.valueOf(list.size()));
        mweVar.constructLocalDefaultWatermarkViewList(list);
        LogUtil.a("Share_EditShareCustomFragment", "editShareCommonViewList size:", Integer.valueOf(mweVar.getEditShareCommonViewList().size()));
        ArrayList arrayList = new ArrayList(8);
        for (EditShareCommonView editShareCommonView : mweVar.getEditShareCommonViewList()) {
            if (editShareCommonView != null && !editShareCommonView.getIsNeedHide()) {
                arrayList.add(editShareCommonView);
            }
        }
        return arrayList;
    }

    private void a(List<Integer> list) {
        this.n.clear();
        i();
        if (koq.b(list)) {
            return;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            mvp mvpVar = new mvp();
            mvpVar.a(intValue);
            Integer num = mus.c.get(Integer.valueOf(intValue));
            if (num != null) {
                mvpVar.setId(num.intValue());
            }
            this.n.add(mvpVar);
        }
    }

    private void i() {
        if (EnvironmentInfo.k()) {
            return;
        }
        mvp mvpVar = new mvp();
        mvpVar.a(R.mipmap.hw_health_edit_share_photo_pic);
        mvpVar.setId(-1);
        this.n.add(0, mvpVar);
    }

    private void b(List<ShareDataInfo> list) {
        this.n.clear();
        i();
        this.n.addAll(list);
    }

    private void l() {
        r();
    }

    private void x() {
        Bitmap decodeResource;
        LogUtil.a("Share_EditShareCustomFragment", "refreshSticker");
        this.af.setVisibility(0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        if (koq.d(this.an, this.ah)) {
            if ("path_type".equals(this.at)) {
                decodeResource = BitmapFactory.decodeFile(this.an.get(this.ah).getPath(), options);
            } else {
                if (!(this.an.get(this.ah) instanceof mvp)) {
                    return;
                }
                decodeResource = BitmapFactory.decodeResource(this.r.getResources(), ((mvp) this.an.get(this.ah)).e(), options);
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.r.getResources(), decodeResource);
            cqP_(this.af, bitmapDrawable);
            this.af.setBackground(bitmapDrawable);
        }
    }

    private void cqP_(View view, Drawable drawable) {
        int top = ((ViewGroup) view.getParent()).getTop();
        int top2 = view.getTop() + (view.getHeight() / 2);
        int left = view.getLeft();
        int width = view.getWidth() / 2;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i = (left + width) - (intrinsicWidth / 2);
        int i2 = top2 - (intrinsicHeight / 2);
        ((RelativeLayout.LayoutParams) view.getLayoutParams()).setMargins(i, i2, 0, 0);
        if (this.al - view.getLeft() < intrinsicWidth && (this.al + top) - top2 < intrinsicHeight) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            int i3 = this.al;
            layoutParams.setMargins(i3 - intrinsicWidth, (top + i3) - intrinsicHeight, 0, 0);
        } else if (this.al - view.getLeft() < intrinsicWidth) {
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).setMargins(this.al - intrinsicWidth, i2, 0, 0);
        } else if ((this.al + top) - top2 < intrinsicHeight) {
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).setMargins(i, (top + this.al) - intrinsicHeight, 0, 0);
        }
    }

    private void cqO_(View view) {
        this.t.removeAllViews();
        this.t.removeAllViewsInLayout();
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        this.t.addView(view);
    }

    private void w() {
        this.t.removeAllViews();
        this.t.removeAllViewsInLayout();
    }

    private Bitmap cqT_() {
        this.ag.setVisibility(0);
        LogUtil.a("Share_EditShareCustomFragment", "getWatermarkBitmap mWaterMarkLayout");
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(this.ag.getMeasuredWidth(), this.ag.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            this.ag.draw(canvas);
            canvas.save();
            canvas.restore();
        } catch (IllegalArgumentException | IllegalStateException | OutOfMemoryError unused) {
            LogUtil.a("Share_EditShareCustomFragment", "createBitmap failed!");
        }
        this.ag.setVisibility(4);
        return bitmap;
    }

    public void cra_(int i, Intent intent) {
        a(true);
        if (i == 2) {
            cqZ_(intent);
        } else if (i == 3) {
            cqS_(intent);
        } else {
            if (i != 4) {
                return;
            }
            mud.cpC_(getActivity());
        }
    }

    private void cqZ_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginsocialshare.view.EditShareCustomFragment.3
            @Override // java.lang.Runnable
            public void run() {
                mud.cpD_(EditShareCustomFragment.this.getActivity(), intent);
            }
        });
    }

    private void cqS_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginsocialshare.view.EditShareCustomFragment.4
            @Override // java.lang.Runnable
            public void run() {
                EditShareCustomFragment.this.cqR_(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cqR_(Intent intent) {
        LogUtil.a("Share_EditShareCustomFragment", "dealCropResult");
        String stringExtra = intent.getStringExtra("bitmap");
        if (stringExtra == null) {
            LogUtil.b("Share_EditShareCustomFragment", "dealCropResult:bitmapPath from intent is null!");
            return;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        this.o = nrf.cHC_(stringExtra, options);
        FragmentActivity activity = getActivity();
        if (this.o == null || activity == null) {
            LogUtil.h("Share_EditShareCustomFragment", "dealCropResult:bitmap or activity is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: mwf
                @Override // java.lang.Runnable
                public final void run() {
                    EditShareCustomFragment.this.d();
                }
            });
            a(stringExtra);
        }
    }

    public /* synthetic */ void d() {
        this.f.a();
        cqX_(this.o);
    }

    private void a(String str) {
        mwa.a(str);
    }

    private void cqX_(Bitmap bitmap) {
        LogUtil.a("Share_EditShareCustomFragment", "refreshBackgroundOnUi");
        cqW_(bitmap);
    }

    private void cqW_(Bitmap bitmap) {
        LogUtil.a("Share_EditShareCustomFragment", "refreshBackground");
        if (this.ar != null) {
            this.ar.setBackground(new BitmapDrawable(this.r.getResources(), bitmap));
        }
        cqY_(bitmap);
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HashMap<String, String> hashMap) {
        hashMap.put("currentSportType", Integer.toString(this.ap.c()));
        int i = this.m;
        if (i == 0) {
            if (this.n != null && this.f.c().size() > 0) {
                this.n.add(1, this.f.c().get(0));
            }
            i = 1;
        }
        hashMap.put("shareLastRecommend" + this.ap.c(), "");
        if (koq.d(this.n, i)) {
            ShareDataInfo shareDataInfo = this.n.get(i);
            mto.e(shareDataInfo instanceof fec ? (fec) shareDataInfo : null);
            hashMap.put("shareLastBackground" + this.ap.c(), Integer.toString(shareDataInfo.getId()));
        }
        if (koq.d(this.p, this.s)) {
            hashMap.put("shareLastDataMark" + this.ap.c(), Integer.toString(this.p.get(this.s).getId()));
        }
    }

    private void u() {
        View view;
        mva mvaVar = this.av;
        if (mvaVar != null) {
            mvaVar.cqc_(getActivity());
            this.av.e();
            this.av = null;
        }
        if (this.t == null) {
            LogUtil.a("Share_EditShareCustomFragment", "removeView: mDataModelLayout null");
            return;
        }
        List<EditShareCommonView> list = this.q;
        if (list == null || list.isEmpty()) {
            LogUtil.a("Share_EditShareCustomFragment", "removeView:mDataMarkViewList empty");
            return;
        }
        LogUtil.a("Share_EditShareCustomFragment", "removeView:start");
        this.t.removeAllViews();
        this.t.removeAllViewsInLayout();
        int i = this.s;
        if (i < 0 || i >= this.q.size() || (view = this.q.get(this.s).getView()) == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    public void e() {
        fdu fduVar;
        Bitmap cHO_ = nrf.cHO_(this.ar);
        if (cHO_ == null) {
            LogUtil.a("Share_EditShareCustomFragment", "share pic is null ");
            return;
        }
        Bitmap cJj_ = nrf.cJj_(cHO_, 0, cqT_(), cHO_.getHeight());
        LogUtil.a("Share_EditShareCustomFragment", "onClick: screenCut: ", cJj_);
        if (cJj_ == null) {
            LogUtil.h("Share_EditShareCustomFragment", "onClick: screenCut is null!");
            return;
        }
        LogUtil.a("Share_EditShareCustomFragment", "onClick:screenCut.getByteCount() size: ", Integer.valueOf(cJj_.getByteCount()));
        if (cJj_.getByteCount() > 1048576) {
            fduVar = new fdu(4);
            String cqB_ = mwd.cqB_(this.r, "EditShareActivity_Share.webp", cJj_);
            LogUtil.a("Share_EditShareCustomFragment", "onClick:saved= ", cqB_);
            fduVar.d(cqB_);
        } else {
            fduVar = new fdu(1);
            fduVar.awp_(cJj_);
        }
        fduVar.i(false);
        fduVar.b(this.ap.d());
        HashMap<String, String> hashMap = new HashMap<>();
        a(hashMap);
        fduVar.b(this.ap.b());
        fduVar.b(hashMap);
        fduVar.c(false);
        mto.d(fduVar);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        ThreadPoolManager.d().execute(new Runnable() { // from class: mwh
            @Override // java.lang.Runnable
            public final void run() {
                mvl.b().e();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        u();
    }

    @Override // com.huawei.pluginsocialshare.adapter.EditShareAdapter.OnBackgroundChangeListener
    public void onBackgroundChange(int i, int i2, View view) {
        if (view instanceof ShareRecycleScrollItemView) {
            ShareRecycleScrollItemView shareRecycleScrollItemView = (ShareRecycleScrollItemView) view;
            if (i == 1) {
                this.n.clear();
                this.n.addAll(this.f.b());
                this.m = i2;
                if (i2 == 0 && getActivity() != null && !EnvironmentInfo.k()) {
                    shareRecycleScrollItemView.crn_(getActivity());
                    return;
                } else {
                    a(false);
                    refreshShareLayoutNoRecommend();
                    return;
                }
            }
            if (i == 2) {
                this.p.clear();
                this.p.addAll(this.az.b());
                this.s = i2;
                refreshShareLayoutNoRecommend();
                return;
            }
            if (i != 3) {
                return;
            }
            this.an.clear();
            this.an.addAll(this.ax.b());
            if (this.bc == i2 && !shareRecycleScrollItemView.isSelected()) {
                this.ah = -1;
                this.af.setVisibility(8);
                this.bc = -1;
            } else {
                this.ah = i2;
                x();
                this.bc = i2;
            }
        }
    }

    public void a() {
        boolean i;
        if (CommonUtil.bv() || CommonUtil.as() || this.bb == null || this.bb.isChecked() == (i = mwd.i())) {
            return;
        }
        this.g = true;
        this.bb.setChecked(i);
    }

    private void b(boolean z) {
        if (koq.b(this.q)) {
            return;
        }
        for (EditShareCommonView editShareCommonView : this.q) {
            if (editShareCommonView != null) {
                if (!z) {
                    editShareCommonView.setUserInfo(null, null);
                } else {
                    editShareCommonView.setUserInfo(mwd.cqw_(this.r), mwd.b());
                }
            }
        }
    }
}
