package com.huawei.healthcloud.plugintrack.bolt;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog;
import com.huawei.healthcloud.plugintrack.bolt.BoltDeviceInfoAdapter;
import com.huawei.healthcloud.plugintrack.ui.view.FadingRecyclerView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.RecyclerItemDecoration;
import com.huawei.up.model.UserInfomation;
import defpackage.cwa;
import defpackage.gsy;
import defpackage.gww;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nsj;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class BoltCustomDialog {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3512a;
    private ImageView aa;
    private ImageView ab;
    private ImageView ac;
    private ImageView ad;
    private ImageView ae;
    private ImageView af;
    private ImageView ag;
    private ImageView ah;
    private ImageView ai;
    private ImageView aj;
    private ImageView ak;
    private ImageView al;
    private ImageView am;
    private ImageView an;
    private ImageView ao;
    private ImageView ap;
    private ImageView aq;
    private ImageView ar;
    private ImageView as;
    private LinearLayout at;
    private LinearLayout au;
    private CustomViewDialog av;
    private NoTitleCustomAlertDialog aw;
    private LinearLayout ax;
    private int ay;
    private int az;
    private List<gsy.b> b;
    private List<gsy.b> ba;
    private List<gsy.b> bb;
    private List<gsy.b> bc;
    private HealthProgressBar bd;
    private HealthTextView be;
    private gww bf;
    private HealthButton bg;
    private HealthTextView bh;
    private List<gsy.b> bi;
    private View bj;
    private FadingRecyclerView bk;
    private View bl;
    private View bm;
    private CustomViewDialog c;
    private ImageView d;
    private ImageView e;
    private int f;
    private List<gsy.b> g;
    private HealthButton h;
    private CustomViewDialog i;
    private Context j;
    private boolean k;
    private boolean l;
    private int m;
    private ImageView n;
    private FadingRecyclerView o;
    private ImageView p;
    private ImageView q;
    private ImageView r;
    private ImageView s;
    private ImageView t;
    private ImageView u;
    private ImageView v;
    private ImageView w;
    private ImageView x;
    private ImageView y;
    private ImageView z;

    public interface OnConfirmCallBack {
        void confirmCallBack();
    }

    private BoltCustomDialog() {
        this.f = 0;
        this.m = -1;
        this.k = true;
    }

    public static BoltCustomDialog a() {
        return a.e;
    }

    public boolean d() {
        return this.k;
    }

    public void b(boolean z) {
        this.k = z;
    }

    public void e(Context context, int i) {
        CustomViewDialog customViewDialog = this.av;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            LogUtil.h("Track_BoltCustomDialog", "showBoltNoPositionDialog:mNoWearingDialog is showing");
            return;
        }
        if (context == null) {
            LogUtil.h("Track_BoltCustomDialog", "showBoltNoPositionDialog : context is null");
            return;
        }
        this.j = context;
        this.az = i;
        this.bj = View.inflate(context, R.layout.dialog_bolt_no_wearing_position, null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.czg_(this.bj).cze_(R.string._2130841133_res_0x7f020e2d, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BoltCustomDialog.this.av.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.av = e;
        e.show();
    }

    public void b(Context context, int i, OnConfirmCallBack onConfirmCallBack, int i2) {
        LogUtil.a("Track_BoltCustomDialog", "showBoltDeviceListDialog() context: ", context, ", sportType: ", Integer.valueOf(i), ", sportStatus: ", Integer.valueOf(i2));
        CustomViewDialog customViewDialog = this.i;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.i.dismiss();
        }
        if (context == null) {
            LogUtil.h("Track_BoltCustomDialog", "showBoltDeviceListDialog: context is null!");
            return;
        }
        this.j = context;
        this.az = i;
        this.ay = i2;
        l();
        j();
        f();
        d(false);
        d(context, onConfirmCallBack);
    }

    public void b(Context context, int i, int i2, OnConfirmCallBack onConfirmCallBack) {
        if (context == null) {
            LogUtil.h("Track_BoltCustomDialog", "showBoltConnectDialog: context is null!");
            return;
        }
        CustomViewDialog customViewDialog = this.c;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            LogUtil.a("Track_BoltCustomDialog", "mBoltConnectTipDialog is showing");
            return;
        }
        if (e()) {
            onConfirmCallBack.confirmCallBack();
            return;
        }
        this.j = context;
        this.l = false;
        View aTu_ = aTu_(i, i2, onConfirmCallBack);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.j);
        builder.czg_(aTu_).c(false);
        CustomViewDialog e = builder.e();
        this.c = e;
        e.show();
    }

    private View aTu_(final int i, final int i2, final OnConfirmCallBack onConfirmCallBack) {
        View inflate = View.inflate(this.j, R.layout.dialog_bolt_connect_check, null);
        ((HealthTextView) inflate.findViewById(R.id.bolt_connect_text)).setText(this.j.getResources().getString(R.string._2130840108_res_0x7f020a2c, cwa.e(554, BaseApplication.getContext(), BaseApplication.getContext().getPackageName())));
        aTv_(inflate);
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.bolt_btn_connect);
        healthButton.setText(this.j.getResources().getString(R.string._2130843785_res_0x7f021889));
        HealthButton healthButton2 = (HealthButton) inflate.findViewById(R.id.bolt_btn_start);
        healthButton2.setText(this.j.getResources().getString(R.string._2130840109_res_0x7f020a2d));
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_BoltCustomDialog", "ignore connect, start sport");
                if (onConfirmCallBack != null) {
                    BoltCustomDialog.this.n();
                    onConfirmCallBack.confirmCallBack();
                    BoltCustomDialog boltCustomDialog = BoltCustomDialog.this;
                    boltCustomDialog.a(boltCustomDialog.c);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("Track_BoltCustomDialog", "setDialogTitleAndShow : callBack is null");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_BoltCustomDialog", "start bolt connect");
                BoltCustomDialog.this.n();
                if (i2 == 1) {
                    BoltCustomDialog boltCustomDialog = BoltCustomDialog.this;
                    boltCustomDialog.e(boltCustomDialog.j, i);
                } else {
                    BoltCustomDialog boltCustomDialog2 = BoltCustomDialog.this;
                    boltCustomDialog2.b(boltCustomDialog2.j, i, onConfirmCallBack, 0);
                }
                BoltCustomDialog boltCustomDialog3 = BoltCustomDialog.this;
                boltCustomDialog3.a(boltCustomDialog3.c);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return inflate;
    }

    private void aTv_(View view) {
        HealthCheckBox healthCheckBox = (HealthCheckBox) view.findViewById(R.id.bolt_collect_checkbox);
        String h = h();
        if (TextUtils.isEmpty(h) || e(h) < 2) {
            healthCheckBox.setText(this.j.getString(R.string._2130840110_res_0x7f020a2e, 30));
        } else {
            healthCheckBox.setText(this.j.getString(R.string._2130841446_res_0x7f020f66));
        }
        healthCheckBox.setChecked(this.l);
        healthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BoltCustomDialog.this.l = z;
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    public boolean e() {
        String h = h();
        if (TextUtils.isEmpty(h)) {
            return false;
        }
        if (e(h) >= 3) {
            LogUtil.a("Track_BoltCustomDialog", "The bolt connect tip ignore 3 times.");
            return true;
        }
        if (System.currentTimeMillis() >= nsj.e(a(h), 30)) {
            return false;
        }
        LogUtil.a("Track_BoltCustomDialog", "The bolt connect tip ignore less than 30 days.");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        String str;
        if (this.l) {
            String h = h();
            String l = Long.toString(System.currentTimeMillis());
            if (TextUtils.isEmpty(h)) {
                str = "1#" + l;
            } else {
                str = (e(h) + 1) + "#" + l;
            }
            this.bf.c(str);
            LogUtil.a("Track_BoltCustomDialog", "Do not show bolt connect tips from ", str);
        }
    }

    private String h() {
        if (this.bf == null) {
            this.bf = gsy.e();
        }
        return this.bf.o();
    }

    private int e(String str) {
        String[] split = str.split("#");
        if (split.length > 0) {
            return CommonUtil.m(this.j, split[0]);
        }
        return 0;
    }

    private long a(String str) {
        String[] split = str.split("#");
        if (split.length > 1) {
            return CommonUtil.g(split[1]);
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BaseDialog baseDialog) {
        if (baseDialog == null || !baseDialog.isShowing()) {
            return;
        }
        baseDialog.dismiss();
    }

    private void j() {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            this.m = 1;
        } else {
            UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
            if (userInfo != null) {
                this.m = userInfo.getGenderOrDefaultValue();
            } else {
                LogUtil.h("Track_BoltCustomDialog", "getUserGender : userInfomation is null");
                this.m = 1;
            }
        }
        LogUtil.a("Track_BoltCustomDialog", "initData : mGenderOrDefaultValue =", Integer.valueOf(this.m));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.a("Track_BoltCustomDialog", "setBoltCustomDialogData with DeviceStatusInvoked is ", Boolean.valueOf(z));
        b(this.az);
        k();
        i();
        c(z);
    }

    private void c(boolean z) {
        if (SportSupportUtil.b(this.az)) {
            b(this.bi, this.g);
            a(this.j, this.bi, this.g, this.ay, z);
        }
        if (this.az == 259) {
            b(this.b, this.g);
            b(this.j, this.b, this.g, this.ay, z);
        }
    }

    private void k() {
        if (this.az == 259) {
            this.n.setVisibility(8);
            this.ax.setVisibility(0);
            this.be.setText(this.j.getResources().getString(R.string._2130845094_res_0x7f021da6));
            m();
        }
        if (SportSupportUtil.b(this.az)) {
            this.ax.setVisibility(8);
            this.n.setVisibility(0);
            this.be.setText(this.j.getResources().getString(R.string._2130845093_res_0x7f021da5));
        }
    }

    private void a(Context context, List<gsy.b> list, List<gsy.b> list2, int i, boolean z) {
        LogUtil.a("Track_BoltCustomDialog", "setRunSportDialog");
        if (koq.b(list) && koq.b(list2)) {
            b();
            if (z) {
                return;
            }
            e(this.j, this.az);
            return;
        }
        a(list, 1, i);
        a(list2, 0, i);
        c(this.bc, this.bb);
        c(context, list, list2, i);
    }

    private void b(Context context, List<gsy.b> list, List<gsy.b> list2, int i, boolean z) {
        LogUtil.a("Track_BoltCustomDialog", "setBikeSportDialog");
        if (koq.b(list) && koq.b(list2)) {
            b();
            if (z) {
                return;
            }
            e(this.j, this.az);
            return;
        }
        a(list, 2, i);
        a(list2, 0, i);
        d(this.ba, this.bb);
        a(context, i);
    }

    private void b(List<gsy.b> list, List<gsy.b> list2) {
        if (!koq.b(list) && list.size() <= 2) {
            this.bk.setSpanPixel(0);
        }
        if (koq.b(list2) || list2.size() > 2) {
            return;
        }
        this.o.setSpanPixel(0);
    }

    private void c(List<gsy.b> list, List<gsy.b> list2) {
        LogUtil.a("Track_BoltCustomDialog", "showRunLineOnDialog");
        int size = !koq.b(list) ? list.size() : 0;
        int size2 = !koq.b(list2) ? list2.size() : 0;
        if (size2 == 0 && size > 0) {
            this.at.setVisibility(8);
            this.au.setVisibility(0);
            this.bl.setVisibility(0);
            c(size);
        }
        if (size2 > 0 && size == 0) {
            this.at.setVisibility(0);
            this.au.setVisibility(8);
            this.bl.setVisibility(0);
            e(size2);
        }
        if (size2 <= 0 || size <= 0) {
            return;
        }
        j(size2, size);
        this.bl.setVisibility(8);
    }

    private void c(int i) {
        if (i == 1) {
            if (this.m == 0) {
                this.ac.setVisibility(0);
                return;
            } else {
                this.an.setVisibility(0);
                return;
            }
        }
        if (i == 2) {
            if (this.m == 0) {
                this.aa.setVisibility(0);
                return;
            } else {
                this.an.setVisibility(0);
                return;
            }
        }
        if (this.m == 0) {
            this.ad.setVisibility(0);
        } else {
            this.am.setVisibility(0);
        }
    }

    private void e(int i) {
        if (i == 1) {
            if (this.m == 0) {
                this.w.setVisibility(0);
                return;
            } else {
                this.ak.setVisibility(0);
                return;
            }
        }
        if (i == 2) {
            if (this.m == 0) {
                this.ab.setVisibility(0);
                return;
            } else {
                this.ak.setVisibility(0);
                return;
            }
        }
        if (this.m == 0) {
            this.x.setVisibility(0);
        } else {
            this.al.setVisibility(0);
        }
    }

    private void j(int i, int i2) {
        d(i, i2);
        f(i, i2);
        c(i, i2);
    }

    private void c(int i, int i2) {
        if (i2 >= 3 && i == 1) {
            if (this.m == 0) {
                this.ah.setVisibility(0);
                this.ag.setVisibility(0);
            } else {
                this.ap.setVisibility(0);
                this.as.setVisibility(0);
            }
        }
        if (i2 >= 3 && i == 2) {
            if (this.m == 0) {
                this.ah.setVisibility(0);
                this.ag.setVisibility(0);
            } else {
                this.ap.setVisibility(0);
                this.as.setVisibility(0);
            }
        }
        if (i2 < 3 || i < 3) {
            return;
        }
        if (this.m == 0) {
            this.z.setVisibility(0);
            this.ai.setVisibility(0);
        } else {
            this.aj.setVisibility(0);
            this.ar.setVisibility(0);
        }
    }

    private void f(int i, int i2) {
        if (i2 == 2 && i == 1) {
            if (this.m == 0) {
                this.ah.setVisibility(0);
                this.ag.setVisibility(0);
            } else {
                this.ap.setVisibility(0);
                this.as.setVisibility(0);
            }
        }
        if (i2 == 2 && i == 2) {
            if (this.m == 0) {
                this.ah.setVisibility(0);
                this.ag.setVisibility(0);
            } else {
                this.ap.setVisibility(0);
                this.as.setVisibility(0);
            }
        }
        if (i2 != 2 || i < 3) {
            return;
        }
        if (this.m == 0) {
            this.ah.setVisibility(0);
            this.ag.setVisibility(0);
        } else {
            this.ap.setVisibility(0);
            this.as.setVisibility(0);
        }
    }

    private void d(int i, int i2) {
        if (i2 == 1 && i == 1) {
            if (this.m == 0) {
                this.ah.setVisibility(0);
                this.ag.setVisibility(0);
            } else {
                this.ap.setVisibility(0);
                this.as.setVisibility(0);
            }
        }
        if (i2 == 1 && i == 2) {
            if (this.m == 0) {
                this.ah.setVisibility(0);
                this.ag.setVisibility(0);
            } else {
                this.ap.setVisibility(0);
                this.as.setVisibility(0);
            }
        }
        if (i2 != 1 || i < 3) {
            return;
        }
        if (this.m == 0) {
            this.ah.setVisibility(0);
            this.ag.setVisibility(0);
        } else {
            this.ap.setVisibility(0);
            this.as.setVisibility(0);
        }
    }

    private void d(List<gsy.b> list, List<gsy.b> list2) {
        LogUtil.a("Track_BoltCustomDialog", "showBikeLineOnDialog");
        int size = !koq.b(list) ? list.size() : 0;
        int size2 = !koq.b(list2) ? list2.size() : 0;
        if (size > 0 && size2 == 0) {
            this.r.setVisibility(0);
        }
        if (size == 0 && size2 > 0) {
            this.t.setVisibility(0);
        }
        b(size, size2);
        a(size, size2);
        e(size, size2);
    }

    private void e(int i, int i2) {
        if (i >= 3 && i2 == 1) {
            this.q.setVisibility(0);
            this.v.setVisibility(0);
        }
        if (i >= 3 && i2 == 2) {
            this.q.setVisibility(0);
            this.v.setVisibility(0);
        }
        if (i < 3 || i2 < 3) {
            return;
        }
        this.p.setVisibility(0);
        this.y.setVisibility(0);
    }

    private void a(int i, int i2) {
        if (i == 2 && i2 == 1) {
            this.q.setVisibility(0);
            this.v.setVisibility(0);
        }
        if (i == 2 && i2 == 2) {
            this.q.setVisibility(0);
            this.v.setVisibility(0);
        }
        if (i != 2 || i2 < 3) {
            return;
        }
        this.q.setVisibility(0);
        this.v.setVisibility(0);
    }

    private void b(int i, int i2) {
        if (i == 1 && i2 == 1) {
            this.q.setVisibility(0);
            this.v.setVisibility(0);
        }
        if (i == 1 && i2 == 2) {
            this.q.setVisibility(0);
            this.v.setVisibility(0);
        }
        if (i != 1 || i2 < 3) {
            return;
        }
        this.q.setVisibility(0);
        this.v.setVisibility(0);
    }

    private List<gsy.b> d(List<gsy.b> list, int i, int i2) {
        String y = i == 0 ? this.bf.y() : "";
        if (i == 1) {
            y = this.bf.ad();
        }
        if (i == 2) {
            y = this.bf.u();
        }
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("Track_BoltCustomDialog", "getShowBoltList() : boltList is empty.");
            return arrayList;
        }
        LogUtil.a("Track_BoltCustomDialog", "getShowBoltList : Extract from the SP boltDeviceIdentifyFormSp = ", CommonUtil.l(y), "; wearingPosition = ", Integer.valueOf(i));
        boolean z = false;
        for (int i3 = 0; i3 < list.size(); i3++) {
            gsy.b bVar = list.get(i3);
            if (bVar.e().getDeviceIdentify().equals(y)) {
                bVar.e(true);
                arrayList.add(bVar);
                z = true;
            } else {
                bVar.e(false);
            }
        }
        LogUtil.a("Track_BoltCustomDialog", "getShowBoltList:isSpIdentifyInCurrentBlots = ", Boolean.valueOf(z));
        if (!z) {
            list.get(0).e(true);
            a(list.get(0).e().getDeviceIdentify(), i);
            arrayList.add(list.get(0));
        }
        return (i2 != 1 && i2 == 0) ? list : arrayList;
    }

    private void b(int i) {
        LogUtil.a("Track_BoltCustomDialog", "initData");
        this.f = 0;
        this.az = i;
        this.bf = gsy.e();
        Map<Integer, List<gsy.b>> a2 = gsy.b().a(gsy.b().a(false), false);
        this.bi = a2.get(1);
        this.g = a2.get(0);
        this.b = a2.get(2);
    }

    public void c() {
        List<gsy.b> a2 = a(gsy.b().a(false));
        LogUtil.a("Track_BoltCustomDialog", "showLowTemperatureDialog list size is ", Integer.valueOf(a2.size()));
        if (koq.c(a2)) {
            d(a2);
        }
    }

    private List<gsy.b> a(List<gsy.b> list) {
        ArrayList arrayList = new ArrayList(16);
        for (gsy.b bVar : list) {
            if (bVar != null && bVar.b() == 3) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private void l() {
        gsy.b().c("Track_BoltCustomDialog", new IBaseResponseCallback() { // from class: gsv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BoltCustomDialog.this.c(i, obj);
            }
        });
    }

    public /* synthetic */ void c(int i, Object obj) {
        LogUtil.a("Track_BoltCustomDialog", "registerBoltConnectionStatusListener : onResponse errorCode = ", Integer.valueOf(i), "; objData = ", obj);
        this.k = false;
        d(true);
    }

    private void d(Context context, final OnConfirmCallBack onConfirmCallBack) {
        this.bh.setText(BaseApplication.getContext().getString(R.string._2130840024_res_0x7f0209d8));
        if (nrt.a(context)) {
            this.bg.setBackground(nrf.cJH_(context.getDrawable(R.drawable._2131427673_res_0x7f0b0159), context.getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
        } else {
            this.bg.setBackgroundResource(R.drawable._2131427673_res_0x7f0b0159);
        }
        this.bg.setVisibility(0);
        this.bg.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_BoltCustomDialog", "RightTopButton onClick");
                BoltCustomDialog.this.bg.setVisibility(8);
                BoltCustomDialog.this.bd.setVisibility(0);
                gsy.b().a(true);
                BoltCustomDialog.this.bh.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        BoltCustomDialog.this.bd.setVisibility(8);
                        BoltCustomDialog.this.bg.setVisibility(0);
                        BoltCustomDialog.this.d(false);
                    }
                }, ProfileExtendConstants.TIME_OUT);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_BoltCustomDialog", "mConfirmBtn onClick");
                BoltCustomDialog.this.i.dismiss();
                gsy.b().e("Track_BoltCustomDialog");
                OnConfirmCallBack onConfirmCallBack2 = onConfirmCallBack;
                if (onConfirmCallBack2 == null) {
                    LogUtil.h("Track_BoltCustomDialog", "setDialogTitleAndShow : callBack is null");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    onConfirmCallBack2.confirmCallBack();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.czh_(this.bm, 0, 0).c(false);
        if (this.ay == 1) {
            this.bg.setVisibility(8);
        }
        CustomViewDialog e = builder.e();
        this.i = e;
        e.show();
    }

    public void b() {
        CustomViewDialog customViewDialog = this.i;
        if (customViewDialog == null || !customViewDialog.isShowing()) {
            return;
        }
        this.i.dismiss();
    }

    private void c(Context context, List<gsy.b> list, List<gsy.b> list2, int i) {
        LogUtil.a("Track_BoltCustomDialog", "setRunDialogTips");
        a(i);
        this.f3512a.setText(context.getResources().getString(R.string.IDS_bolt_dialog_device, 1, 1));
        if (this.f == 1 || (list2.size() == 1 && list.size() == 1)) {
            this.f3512a.setText(context.getResources().getString(R.string.IDS_bolt_dialog_device_list));
        }
        if (this.f <= 1 || list2.size() == 1 || list.size() == 1) {
            return;
        }
        this.f3512a.setText(context.getResources().getString(R.string.IDS_bolt_dialog_device, 1, 1));
    }

    private void a(int i) {
        if (i == 1) {
            this.f3512a.setVisibility(8);
        }
        if (i == 0) {
            this.f3512a.setVisibility(0);
        }
    }

    private void a(Context context, int i) {
        LogUtil.a("Track_BoltCustomDialog", "setBikeDialogTips");
        a(i);
        this.f3512a.setText(context.getResources().getString(R.string.IDS_bolt_dialog_device_list));
    }

    private void a(List<gsy.b> list, final int i, int i2) {
        LogUtil.a("Track_BoltCustomDialog", "enter setBoltList()");
        if (koq.b(list)) {
            LogUtil.h("Track_BoltCustomDialog", "setBoltList is empty; wearingPosition = ", Integer.valueOf(i));
            d(i);
            return;
        }
        final List<gsy.b> d = d(list, i, i2);
        b(list, i, i2);
        final BoltDeviceInfoAdapter boltDeviceInfoAdapter = new BoltDeviceInfoAdapter(d, this.j);
        if (i == 1 || i == 2) {
            this.au.setVisibility(0);
            this.bk.setAdapter(boltDeviceInfoAdapter);
            this.f += list.size();
        }
        if (i == 0) {
            this.at.setVisibility(0);
            this.o.setAdapter(boltDeviceInfoAdapter);
            this.f += list.size();
        }
        boltDeviceInfoAdapter.b(new BoltDeviceInfoAdapter.OnItemClickListener() { // from class: gsu
            @Override // com.huawei.healthcloud.plugintrack.bolt.BoltDeviceInfoAdapter.OnItemClickListener
            public final void onItemClick(int i3) {
                BoltCustomDialog.this.c(d, boltDeviceInfoAdapter, i, i3);
            }
        });
    }

    public /* synthetic */ void c(List list, BoltDeviceInfoAdapter boltDeviceInfoAdapter, int i, int i2) {
        LogUtil.a("Track_BoltCustomDialog", "boltAdapter.setOnItemClickListener position: ", Integer.valueOf(i2));
        if (koq.b(list, i2)) {
            LogUtil.h("Track_BoltCustomDialog", "setBoltList showBoltList outOfBounds");
        } else {
            c(i2, boltDeviceInfoAdapter, list, (gsy.b) list.get(i2), i);
        }
    }

    private void d(final List<gsy.b> list) {
        LogUtil.a("Track_BoltCustomDialog", " buildLowTemperatureDialog enter ");
        gsy.b bVar = list.get(0);
        if (bVar == null) {
            LogUtil.b("Track_BoltCustomDialog", "buildLowTemperatureDialog boltDeviceInfo is null");
            return;
        }
        if (bVar.e().getDeviceConnectState() != 2) {
            LogUtil.a("Track_BoltCustomDialog", " buildLowTemperatureDialog getDeviceConnectState has disconnected ");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.aw;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("Track_BoltCustomDialog", " buildLowTemperatureDialog isShowing ");
            return;
        }
        Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b("Track_BoltCustomDialog", "buildLowTemperatureDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(wa_);
        builder.e(String.format(Locale.ROOT, wa_.getString(R.string._2130846708_res_0x7f0223f4), cwa.e(554, wa_, wa_.getPackageName()))).czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_BoltCustomDialog", " buildLowTemperatureDialog click.");
                gsy.b().e(list);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        this.aw = e;
        e.setCancelable(false);
        this.aw.show();
    }

    private void d(int i) {
        if (i == 0) {
            if (!koq.b(this.bb)) {
                this.bb.clear();
            }
            this.at.setVisibility(8);
        }
        if (i == 1) {
            if (!koq.b(this.bc)) {
                this.bc.clear();
            }
            this.au.setVisibility(8);
        }
        if (i == 2) {
            if (!koq.b(this.ba)) {
                this.ba.clear();
            }
            this.au.setVisibility(8);
        }
    }

    private void b(List<gsy.b> list, int i, int i2) {
        if (i == 0) {
            this.bb = d(list, i, i2);
        }
        if (i == 1) {
            this.bc = d(list, i, i2);
        }
        if (i == 2) {
            this.ba = d(list, i, i2);
        }
    }

    private void a(String str, int i) {
        if (this.bf == null) {
            this.bf = gsy.e();
        }
        if (i == 2) {
            this.bf.k(str);
            LogUtil.a("Track_BoltCustomDialog", "saveWearPositionIdentify : save bike identify = ", CommonUtil.l(str));
        }
        if (i == 1) {
            this.bf.o(str);
            LogUtil.a("Track_BoltCustomDialog", "saveWearPositionIdentify : save waist identify = ", CommonUtil.l(str));
        }
        if (i == 0) {
            this.bf.l(str);
            LogUtil.a("Track_BoltCustomDialog", "saveWearPositionIdentify : save foot identify = ", CommonUtil.l(str));
        }
    }

    private void c(int i, BoltDeviceInfoAdapter boltDeviceInfoAdapter, List<gsy.b> list, gsy.b bVar, int i2) {
        if (koq.b(list)) {
            LogUtil.h("Track_BoltCustomDialog", "setBoltListCheckedStatus boltDeviceList is null");
            return;
        }
        int deviceConnectState = bVar.e().getDeviceConnectState();
        LogUtil.a("Track_BoltCustomDialog", "setBoltListCheckedStatus : deviceConnectState = ", Integer.valueOf(deviceConnectState));
        if (deviceConnectState == 2) {
            LogUtil.a("Track_BoltCustomDialog", "setBoltListCheckedStatus : DEVICE_CONNECTED");
            a(i, list);
            a(bVar.e().getDeviceIdentify(), i2);
            gsy.a(bVar.e());
        }
        if (deviceConnectState == 1) {
            LogUtil.a("Track_BoltCustomDialog", "setBoltListCheckedStatus DEVICE_CONNECTING");
        }
        if (deviceConnectState == 3 || deviceConnectState == 4) {
            LogUtil.a("Track_BoltCustomDialog", "setBoltListCheckedStatus : DEVICE_DISCONNECTED");
            String deviceIdentify = bVar.e().getDeviceIdentify();
            gsy.b().d(deviceIdentify);
            if (this.bf == null) {
                this.bf = gsy.e();
            }
            this.bf.j(deviceIdentify);
            b(i, list);
        }
        boltDeviceInfoAdapter.notifyDataSetChanged();
    }

    private void b(int i, List<gsy.b> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            gsy.b bVar = list.get(i2);
            if (i2 == i) {
                bVar.a(true);
            } else {
                bVar.a(false);
            }
        }
    }

    private void a(int i, List<gsy.b> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            gsy.b bVar = list.get(i2);
            if (i2 == i) {
                bVar.e(true);
            } else {
                bVar.e(false);
            }
        }
    }

    private void a(Context context) {
        if (this.m == 0) {
            this.n.setImageDrawable(context.getResources().getDrawable(R.drawable._2131429796_res_0x7f0b09a4));
        } else {
            this.n.setImageDrawable(context.getResources().getDrawable(R.drawable._2131429809_res_0x7f0b09b1));
        }
    }

    private void f() {
        View inflate = View.inflate(this.j, R.layout.dialog_bolt_device_list, null);
        this.bm = inflate;
        this.bh = (HealthTextView) inflate.findViewById(R.id.bolt_dialog_title);
        this.bd = (HealthProgressBar) this.bm.findViewById(R.id.bolt_dialog_title_loading);
        this.bg = (HealthButton) this.bm.findViewById(R.id.bolt_dialog_title_refresh_btn);
        this.h = (HealthButton) this.bm.findViewById(R.id.bolt_confirm_btn);
        View findViewById = this.bm.findViewById(R.id.view_list_Placeholder);
        this.bl = findViewById;
        findViewById.setVisibility(8);
        this.be = (HealthTextView) this.bm.findViewById(R.id.tv_header_waist);
        d(this.j);
        g();
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        FadingRecyclerView fadingRecyclerView = (FadingRecyclerView) this.bm.findViewById(R.id.bolt_waist_recycler_view);
        this.bk = fadingRecyclerView;
        fadingRecyclerView.addItemDecoration(new RecyclerItemDecoration(1, this.j.getResources().getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306), arrayList));
        this.bk.setLayoutManager(new LinearLayoutManager(this.j));
        FadingRecyclerView fadingRecyclerView2 = (FadingRecyclerView) this.bm.findViewById(R.id.bolt_foot_recycler_view);
        this.o = fadingRecyclerView2;
        fadingRecyclerView2.addItemDecoration(new RecyclerItemDecoration(1, this.j.getResources().getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306), arrayList));
        this.o.setLayoutManager(new LinearLayoutManager(this.j));
    }

    private void g() {
        this.ax = (LinearLayout) this.bm.findViewById(R.id.ll_bolt_bike_images);
        this.e = (ImageView) this.bm.findViewById(R.id.bolt_bike_spokes);
        this.d = (ImageView) this.bm.findViewById(R.id.bolt_bike_foot);
        this.r = (ImageView) this.bm.findViewById(R.id.ic_bolt_bike_1_bike);
        this.t = (ImageView) this.bm.findViewById(R.id.ic_bolt_bike_1_foot);
        this.v = (ImageView) this.bm.findViewById(R.id.ic_bolt_bike_2_1_1_foot);
        this.q = (ImageView) this.bm.findViewById(R.id.ic_bolt_bike_2_1_1_bike);
        this.s = (ImageView) this.bm.findViewById(R.id.ic_bolt_bike_2_2_2_bike_2);
        this.u = (ImageView) this.bm.findViewById(R.id.ic_bolt_bike_2_2_2_foot_2);
        this.p = (ImageView) this.bm.findViewById(R.id.ic_bolt_bike_2_3_3_bike);
        this.y = (ImageView) this.bm.findViewById(R.id.ic_bolt_bike_2_3_3_foot);
    }

    private void d(Context context) {
        this.n = (ImageView) this.bm.findViewById(R.id.bolt_man_women);
        a(context);
        this.f3512a = (HealthTextView) this.bm.findViewById(R.id.bolt_wearing_tips);
        this.au = (LinearLayout) this.bm.findViewById(R.id.ll_waist);
        this.at = (LinearLayout) this.bm.findViewById(R.id.ll_foot);
        this.ah = (ImageView) this.bm.findViewById(R.id.ic_bolt_2_foot_1);
        this.ag = (ImageView) this.bm.findViewById(R.id.ic_bolt_2_waist_1);
        this.ae = (ImageView) this.bm.findViewById(R.id.ic_bolt_man_2_2_2_foot);
        this.af = (ImageView) this.bm.findViewById(R.id.ic_bolt_man_2_2_2_waist);
        this.z = (ImageView) this.bm.findViewById(R.id.ic_bolt_man_2_3_3_foot);
        this.ai = (ImageView) this.bm.findViewById(R.id.ic_bolt_man_2_3_3_waist);
        this.w = (ImageView) this.bm.findViewById(R.id.ic_bolt_1_foot_1);
        this.ab = (ImageView) this.bm.findViewById(R.id.ic_bolt_1_foot_2_1);
        this.x = (ImageView) this.bm.findViewById(R.id.ic_bolt_1_foot_3);
        this.ac = (ImageView) this.bm.findViewById(R.id.ic_bolt_1_waist_1);
        this.aa = (ImageView) this.bm.findViewById(R.id.ic_bolt_1_waist_2_1);
        this.ad = (ImageView) this.bm.findViewById(R.id.ic_bolt_1_waist_3);
        this.an = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_1_waist_1);
        this.am = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_1_waist_3);
        this.ak = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_1_foot_1);
        this.al = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_1_foot_3);
        this.ap = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_2_1_1_foot);
        this.as = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_2_1_1_waist);
        this.ao = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_2_2_2_foot);
        this.aq = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_2_2_2_waist);
        this.aj = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_2_3_3_foot);
        this.ar = (ImageView) this.bm.findViewById(R.id.ic_bolt_women_2_3_3_waist);
    }

    private void i() {
        this.ah.setVisibility(8);
        this.ag.setVisibility(8);
        this.ae.setVisibility(8);
        this.af.setVisibility(8);
        this.z.setVisibility(8);
        this.ai.setVisibility(8);
        this.w.setVisibility(8);
        this.ab.setVisibility(8);
        this.x.setVisibility(8);
        this.ac.setVisibility(8);
        this.aa.setVisibility(8);
        this.ad.setVisibility(8);
        this.an.setVisibility(8);
        this.am.setVisibility(8);
        this.ak.setVisibility(8);
        this.al.setVisibility(8);
        this.ap.setVisibility(8);
        this.as.setVisibility(8);
        this.ao.setVisibility(8);
        this.aq.setVisibility(8);
        this.aj.setVisibility(8);
        this.ar.setVisibility(8);
        this.r.setVisibility(8);
        this.t.setVisibility(8);
        this.v.setVisibility(8);
        this.q.setVisibility(8);
        this.s.setVisibility(8);
        this.u.setVisibility(8);
        this.p.setVisibility(8);
        this.y.setVisibility(8);
    }

    private void m() {
        if (this.b.size() == 0 && this.g.size() > 0) {
            this.e.setVisibility(8);
            this.d.setVisibility(0);
        }
        if (this.b.size() > 0 && this.g.size() == 0) {
            this.e.setVisibility(0);
            this.d.setVisibility(8);
        }
        if (this.b.size() <= 0 || this.g.size() <= 0) {
            return;
        }
        this.e.setVisibility(0);
        this.d.setVisibility(0);
    }

    static class a {
        private static final BoltCustomDialog e = new BoltCustomDialog();
    }
}
