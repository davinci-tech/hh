package com.huawei.healthcloud.plugintrack.ui.fragmentutils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.healthcloud.plugintrack.ui.view.DetailItemContainer;
import com.huawei.healthcloud.plugintrack.ui.view.SportDetailItem;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.divider.HealthDivider;
import defpackage.ffn;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.gya;
import defpackage.hji;
import defpackage.hkc;
import defpackage.hln;
import defpackage.koq;
import defpackage.kxb;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class TrackDetailItemDrawer {

    /* renamed from: a, reason: collision with root package name */
    private Context f3766a;
    private String b;
    private List<SportDetailItem.b> c;
    private TrackDetailItemDrawHelper d;
    private List<SportDetailItem.b> e;
    private boolean f;
    private Resources g;
    private MotionPath h;
    private Map<String, Integer> i;
    private MotionPathSimplify j;
    private gya k;
    private int m;

    public TrackDetailItemDrawer(MotionPathSimplify motionPathSimplify, MotionPath motionPath, Context context, int i) {
        this.j = null;
        this.h = null;
        this.k = null;
        this.c = new ArrayList(16);
        this.e = new ArrayList(16);
        this.b = gvv.e(BaseApplication.getContext());
        this.m = Color.parseColor("#ffffff");
        this.f = false;
        if (motionPathSimplify == null || context == null) {
            throw new RuntimeException("TrackDetailItemDrawer invalid params in constructor");
        }
        this.j = motionPathSimplify;
        this.h = motionPath;
        this.f3766a = context;
        this.g = context.getResources();
        this.i = this.j.requestSportData();
        this.d = new TrackDetailItemDrawHelper(context);
        if (i == 101) {
            this.m = this.g.getColor(R.color._2131296871_res_0x7f090267);
        } else {
            this.m = this.g.getColor(R.color._2131299236_res_0x7f090ba4);
        }
    }

    public TrackDetailItemDrawer(gya gyaVar, Context context, int i) {
        this.j = null;
        this.h = null;
        this.k = null;
        this.c = new ArrayList(16);
        this.e = new ArrayList(16);
        this.b = gvv.e(BaseApplication.getContext());
        this.m = Color.parseColor("#ffffff");
        this.f = false;
        if (context == null) {
            throw new RuntimeException("TrackDetailItemDrawer invalid params in constructor");
        }
        this.k = gyaVar;
        this.f3766a = context;
        this.g = context.getResources();
        this.d = new TrackDetailItemDrawHelper(context);
        if (i == 101) {
            this.m = this.g.getColor(R.color._2131296871_res_0x7f090267);
        } else {
            this.m = this.g.getColor(R.color._2131299236_res_0x7f090ba4);
        }
    }

    public void e(DetailItemContainer detailItemContainer, boolean z, int i, int i2) {
        boolean z2;
        this.f = z;
        if (this.j == null) {
            LogUtil.h("Track_TrackDetailItemDrawer", "drawDetailTable() mTrackDetailDataManager is null");
            return;
        }
        List<SportDetailItem.b> list = this.c;
        if (list == null) {
            this.c = new ArrayList(16);
        } else {
            list.clear();
        }
        List<SportDetailItem.b> list2 = this.e;
        if (list2 == null) {
            this.e = new ArrayList(16);
        } else {
            list2.clear();
        }
        int requestSportType = this.j.requestSportType();
        if (this.j.requestSportDataSource() == 2) {
            t(requestSportType);
        } else if (gwg.b(this.j)) {
            am();
        } else if (i == 1) {
            br();
        } else if (i == 2) {
            r();
        } else {
            an();
        }
        if (this.j.requestSportType() == 283) {
            this.d.d(true);
        }
        if (i == 3 && !(z2 = this.f)) {
            this.d.bhC_(detailItemContainer, i2, this.e, z2, this.m);
        } else {
            this.d.bhC_(detailItemContainer, i2, this.c, this.f, this.m);
        }
    }

    public void a(DetailItemContainer detailItemContainer, boolean z, int i) {
        this.f = z;
        gya gyaVar = this.k;
        if (gyaVar == null || gyaVar.d() == null) {
            LogUtil.h("Track_TrackDetailItemDrawer", "drawTableWithoutDetail() mTrackDetailDataManager is null");
            return;
        }
        List<SportDetailItem.b> list = this.c;
        if (list == null) {
            this.c = new ArrayList(16);
        } else {
            list.clear();
        }
        ad();
        this.d.bhC_(detailItemContainer, i, this.c, this.f, this.m);
    }

    public static class TrackDetailItemDrawHelper {
        private boolean b = false;
        private final Context d;

        public TrackDetailItemDrawHelper(Context context) {
            this.d = context;
        }

        public void bhC_(ViewGroup viewGroup, int i, List<SportDetailItem.b> list, boolean z, int i2) {
            if (koq.b(list)) {
                LogUtil.h("Track_TrackDetailItemDrawer", "fillDetailContainer dataList is null");
                return;
            }
            if (viewGroup == null) {
                LogUtil.h("Track_TrackDetailItemDrawer", "fillDetailContainer container is null");
                return;
            }
            Object systemService = this.d.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            if (!(systemService instanceof WindowManager)) {
                LogUtil.b("Track_TrackDetailItemDrawer", "object is not instanceof WindowManager");
                return;
            }
            WindowManager windowManager = (WindowManager) systemService;
            int h = nsn.h(this.d) - (i * 2);
            int size = list.size();
            if (size == 0) {
                return;
            }
            int dimensionPixelOffset = (z || !this.b) ? h : h - (this.d.getResources().getDimensionPixelOffset(R.dimen._2131362009_res_0x7f0a00d9) * 2);
            if (z) {
                dimensionPixelOffset = h - nsn.c(this.d, 50.0f);
            }
            for (int i3 = 0; i3 < size; i3++) {
                SportDetailItem sportDetailItem = new SportDetailItem(this.d);
                sportDetailItem.setGroupSize((dimensionPixelOffset / 2) - ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
                if (i3 % 2 != 0 && nsn.ag(this.d)) {
                    sportDetailItem.e(this.d);
                }
                SportDetailItem.b bVar = list.get(i3);
                d(sportDetailItem, bVar);
                d(sportDetailItem);
                sportDetailItem.setItemView(bVar);
                sportDetailItem.setTextColor(i2);
                d(sportDetailItem, bVar.e());
                viewGroup.addView(sportDetailItem);
                if (a(i3)) {
                    viewGroup.addView(bhB_(windowManager));
                }
            }
        }

        private void d(SportDetailItem sportDetailItem, SportDetailItem.b bVar) {
            if (nsn.ag(this.d)) {
                LogUtil.a("Track_TrackDetailItemDrawer", "IS TAHITI DISPLAY");
                return;
            }
            if (LanguageUtil.au(this.d)) {
                sportDetailItem.a(0, nsn.c(this.d, 18.0f), nsn.c(this.d, 10.0f));
            }
            if (LanguageUtil.x(this.d) || LanguageUtil.f(this.d) || LanguageUtil.bb(this.d) || LanguageUtil.ag(this.d) || LanguageUtil.ba(this.d)) {
                sportDetailItem.a(nsn.c(this.d, 9.0f), nsn.c(this.d, 18.0f), nsn.c(this.d, 10.0f));
            }
            if ((bVar.d().equals(this.d.getResources().getString(R.string._2130845161_res_0x7f021de9)) || bVar.d().equals(this.d.getResources().getString(R.string._2130845160_res_0x7f021de8))) && LanguageUtil.p(this.d)) {
                sportDetailItem.a(nsn.c(this.d, 13.0f), 0, 0);
            }
            if (bVar.d().equals(this.d.getResources().getString(R.string._2130839763_res_0x7f0208d3))) {
                try {
                    if (Float.parseFloat(bVar.e()) > 10.0f) {
                        sportDetailItem.a((int) this.d.getResources().getDimension(R.dimen._2131363649_res_0x7f0a0741), (int) this.d.getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4), nsn.c(this.d, 10.0f));
                    }
                } catch (NumberFormatException unused) {
                    LogUtil.b("Track_TrackDetailItemDrawer", "NumberFormatException");
                }
            }
            boolean z = LanguageUtil.aj(this.d) || LanguageUtil.c(this.d) || LanguageUtil.ah(this.d) || LanguageUtil.af(this.d);
            boolean z2 = LanguageUtil.ar(this.d) || LanguageUtil.bj(this.d) || LanguageUtil.o(this.d) || LanguageUtil.r(this.d);
            if (z || z2) {
                sportDetailItem.a(nsn.c(this.d, 10.0f), nsn.c(this.d, 18.0f), nsn.c(this.d, 9.0f));
            }
            if (LanguageUtil.h(BaseApplication.getContext()) || !bVar.d().equals(this.d.getResources().getString(R.string._2130839764_res_0x7f0208d4))) {
                return;
            }
            sportDetailItem.a(nsn.c(this.d, 10.0f), nsn.c(this.d, 18.0f), nsn.c(this.d, 9.0f));
        }

        private void d(SportDetailItem sportDetailItem) {
            if (nsn.ag(this.d)) {
                LogUtil.a("Track_TrackDetailItemDrawer", "IS TAHITI DISPLAY");
            }
        }

        private LinearLayout bhB_(WindowManager windowManager) {
            HealthDivider healthDivider = new HealthDivider(this.d);
            healthDivider.setVisibility(8);
            healthDivider.setLayoutParams(new ViewGroup.LayoutParams(windowManager.getDefaultDisplay().getWidth(), this.d.getResources().getDimensionPixelSize(R.dimen._2131364940_res_0x7f0a0c4c)));
            LinearLayout linearLayout = new LinearLayout(this.d);
            linearLayout.addView(healthDivider);
            return linearLayout;
        }

        private boolean a(int i) {
            return i % 2 != 0;
        }

        private void d(SportDetailItem sportDetailItem, String str) {
            if (sportDetailItem == null || str == null || str.isEmpty() || str.equals(gvv.e(BaseApplication.getContext())) || str.matches("[0-9]+")) {
                return;
            }
            if (this.d.getResources().getString(R.string._2130845321_res_0x7f021e89).equals(str)) {
                sportDetailItem.setValueTextSize(this.d.getResources().getDimension(R.dimen._2131362915_res_0x7f0a0463));
            } else {
                sportDetailItem.setValueTextSize(this.d.getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
            }
        }

        public void d(boolean z) {
            this.b = z;
        }
    }

    private void br() {
        int requestSportType = this.j.requestSportType();
        if (requestSportType == 258) {
            bq();
            return;
        }
        if (requestSportType == 259) {
            bp();
        } else if (requestSportType == 262 || requestSportType == 266) {
            bn();
        }
    }

    private void r() {
        ac();
        ai();
        bw();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void an() {
        ArrayList<String> detailFieldWear;
        char c;
        int requestSportType = this.j.requestSportType();
        hln c2 = hln.c(this.f3766a);
        if (c2.d(requestSportType) == null || c2.d(requestSportType).getSportDetail() == null) {
            LogUtil.b("Track_TrackDetailItemDrawer", "can not find sport type in json");
            q();
            return;
        }
        if (by()) {
            detailFieldWear = c2.d(requestSportType).getSportDetail().getDetailOtherField();
        } else if (hji.a(this.j.requestSportDataSource())) {
            detailFieldWear = c2.d(requestSportType).getSportDetail().getDetailFieldIndoor();
        } else {
            detailFieldWear = c2.d(requestSportType).getSportDetail().getDetailFieldWear();
        }
        for (String str : detailFieldWear) {
            str.hashCode();
            switch (str.hashCode()) {
                case -1849873063:
                    if (str.equals(ObserveLabels.HEART_RATE)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -708092139:
                    if (str.equals("AVERAGE_PACE")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -475583453:
                    if (str.equals("AVERAGE_POWER")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -472800411:
                    if (str.equals("AVERAGE_SPEED")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 2575053:
                    if (str.equals("TIME")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 613661446:
                    if (str.equals("CALORIES")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 878168267:
                    if (str.equals("AVERAGE_PACE_500")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 1071086581:
                    if (str.equals("DISTANCE")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 1131185254:
                    if (str.equals("ACTIVE_TIME")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 1761640479:
                    if (str.equals("ACTIVE_CALORIES")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    z();
                    break;
                case 1:
                    f();
                    break;
                case 2:
                    j();
                    break;
                case 3:
                    h();
                    break;
                case 4:
                    ba();
                    break;
                case 5:
                    t();
                    break;
                case 6:
                    i();
                    break;
                case 7:
                    bc();
                    break;
                case '\b':
                    b();
                    a();
                    break;
                case '\t':
                    d();
                    break;
                default:
                    a(str);
                    break;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1661442433:
                if (str.equals("TOTAL_DESCENT")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1633561594:
                if (str.equals("TOTAL_CREEP")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1618725332:
                if (str.equals("TOTAL_STEPS")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1456831383:
                if (str.equals("POOL_LENGTH")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1305718177:
                if (str.equals("STROKE_TIMES")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -840167633:
                if (str.equals("AVERAGE_CADENCE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -831067323:
                if (str.equals("AVERAGE_STROKE_RATE")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -472582045:
                if (str.equals("AVERAGE_SWOLF")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -180651807:
                if (str.equals("STROKE_TYPE")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 2329144:
                if (str.equals("LAPS")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 329496761:
                if (str.equals("STRIDE_ITEM")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1170483889:
                if (str.equals("AVERAGE_STEP_RATE")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1255646437:
                if (str.equals("TOTAL_PADDLE")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1536192167:
                if (str.equals("PADDLE_FREQUENCY")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                bi();
                break;
            case 1:
                s();
                break;
            case 2:
                bj();
                break;
            case 3:
                ap();
                break;
            case 4:
                bd();
                break;
            case 5:
                c();
                break;
            case 6:
                k();
                break;
            case 7:
                n();
                break;
            case '\b':
                be();
                break;
            case '\t':
                ah();
                break;
            case '\n':
                bh();
                break;
            case 11:
                o();
                break;
            case '\f':
                bk();
                break;
            case '\r':
                ar();
                break;
            default:
                c(str);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void c(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1956561001:
                if (str.equals("AVERAGE_SKI_SPEED")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1519813196:
                if (str.equals("RIDE_TIME")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1352339258:
                if (str.equals("SKI_LAPS")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1352093349:
                if (str.equals("SKI_TIME")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1003596533:
                if (str.equals("MAX_SWING_SPEED")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -826808624:
                if (str.equals("MAX_SLOPE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 672350249:
                if (str.equals("SWING_DOWN_TIME")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 706659076:
                if (str.equals("SWING_BACK_TIME")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 945684328:
                if (str.equals("BASKETBALL_JUMP")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1136527101:
                if (str.equals("GOAL_DETAIL")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1696942148:
                if (str.equals("AVERAGE_SWING_SPEED")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1697546008:
                if (str.equals("AVERAGE_SWING_TEMPO")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                at();
                break;
            case 1:
                ao();
                break;
            case 2:
                aw();
                break;
            case 3:
                ax();
                break;
            case 4:
                aj();
                break;
            case 5:
                al();
                break;
            case 6:
                v();
                break;
            case 7:
                p();
                break;
            case '\b':
                ac();
                ai();
                bw();
                break;
            case '\t':
                y();
                break;
            case '\n':
                m();
                break;
            case 11:
                l();
                break;
            default:
                d(str);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2145249727:
                if (str.equals("FINISHED_GROUPS")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1385176616:
                if (str.equals("PEAK_WEIGHT")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1347397436:
                if (str.equals("GRADE_EVALUATION")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -575730697:
                if (str.equals("EXP_DISTANCE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -218582784:
                if (str.equals("SKIPPING_ROPE")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -139160495:
                if (str.equals("TOTAL_ACTION_GROUP")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 78726770:
                if (str.equals("SCORE")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 295552163:
                if (str.equals("EXP_AVERAGE_SPEED")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 912347371:
                if (str.equals("TRAINING_ACTION_COUNT")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                u();
                break;
            case 1:
                aq();
                break;
            case 2:
                av();
                break;
            case 3:
                x();
                break;
            case 4:
                bb();
                bf();
                af();
                as();
                break;
            case 5:
                bl();
                break;
            case 6:
                au();
                break;
            case 7:
                w();
                break;
            case '\b':
                bm();
                break;
        }
    }

    private void u() {
        int extendDataInt = this.j.getExtendDataInt("finishedGroups", -1);
        if (extendDataInt == -1) {
            LogUtil.a("Track_TrackDetailItemDrawer", "groups is invalid in buildFinishedGroupsItem");
        } else {
            this.c.add(new SportDetailItem.b(ContextCompat.getDrawable(this.f3766a, R.drawable._2131429952_res_0x7f0b0a40), this.g.getString(R.string._2130840140_res_0x7f020a4c), UnitUtil.e(extendDataInt, 1, 0), this.g.getQuantityString(R.plurals._2130903395_res_0x7f030163, extendDataInt, "")));
        }
    }

    private void bm() {
        int extendDataInt = this.j.getExtendDataInt("trainingActionCount", -1);
        if (extendDataInt == -1) {
            LogUtil.a("Track_TrackDetailItemDrawer", "count is invalid in buildTrainingActionCountItem");
        } else {
            this.c.add(new SportDetailItem.b(ContextCompat.getDrawable(this.f3766a, R.drawable._2131430550_res_0x7f0b0c96), this.g.getString(R.string._2130840141_res_0x7f020a4d), UnitUtil.e(extendDataInt, 1, 0), this.g.getQuantityString(R.plurals._2130903362_res_0x7f030142, extendDataInt, "")));
        }
    }

    private void w() {
        String b = hji.b(this.j.getExtendDataInt("wayPointDistance"), TimeUnit.MILLISECONDS.toSeconds(this.j.requestTotalTime()));
        if (gvv.e(BaseApplication.getContext()).equals(b)) {
            LogUtil.a("Track_TrackDetailItemDrawer", "buildAverageSpeedItem is 0");
            return;
        }
        String string = this.g.getString(R.string._2130844078_res_0x7f0219ae);
        if (UnitUtil.h()) {
            string = this.g.getString(R.string._2130844079_res_0x7f0219af);
        }
        this.c.add(new SportDetailItem.b(ContextCompat.getDrawable(this.f3766a, R.drawable._2131429731_res_0x7f0b0963), this.g.getString(R.string._2130846653_res_0x7f0223bd), b, string));
    }

    private void x() {
        String e;
        String string;
        int extendDataInt = this.j.getExtendDataInt("wayPointDistance");
        if (extendDataInt == 0) {
            e = this.f3766a.getString(R.string._2130850262_res_0x7f0231d6);
        } else {
            e = hji.e(extendDataInt);
        }
        if (UnitUtil.h()) {
            string = this.g.getString(R.string._2130841383_res_0x7f020f27);
        } else {
            string = this.g.getString(R.string._2130841382_res_0x7f020f26);
        }
        SportDetailItem.b bVar = new SportDetailItem.b(ContextCompat.getDrawable(this.f3766a, R.drawable._2131430576_res_0x7f0b0cb0), this.g.getString(R.string._2130846373_res_0x7f0222a5), e, string);
        if (!this.f) {
            bVar.a("ADVENTURE_WAYPOINT");
        }
        this.c.add(bVar);
    }

    private void au() {
        int extendDataInt = this.j.getExtendDataInt(JsUtil.SCORE);
        String string = this.f3766a.getResources().getString(R.string._2130849086_res_0x7f022d3e);
        String str = "";
        if (extendDataInt > 0) {
            string = UnitUtil.e(extendDataInt, 1, 0);
            str = this.f3766a.getResources().getQuantityString(R.plurals._2130903360_res_0x7f030140, extendDataInt, "");
        }
        this.c.add(new SportDetailItem.b(bhA_(R.drawable._2131430407_res_0x7f0b0c07), this.g.getString(R.string._2130843977_res_0x7f021949), string, str));
    }

    private void av() {
        this.c.add(new SportDetailItem.b(bhA_(R.drawable._2131429972_res_0x7f0b0a54), this.g.getString(R.string._2130846180_res_0x7f0221e4), hkc.d(this.f3766a, this.j.getExtendDataInt("gradeEvaluation")), ""));
    }

    private void ad() {
        az();
        ay();
    }

    private void q() {
        ba();
        t();
        d();
        f();
        h();
        o();
        bh();
        bj();
        z();
        s();
        bi();
        y();
    }

    private void am() {
        ba();
        if (this.f) {
            return;
        }
        t();
    }

    private void bo() {
        ba();
        t();
        if (this.j.requestSportType() != 259) {
            f();
        }
        h();
    }

    private void bt() {
        ba();
        t();
    }

    private void bp() {
        bc();
        ba();
        t();
        d();
        z();
        s();
        bi();
    }

    private void bq() {
        bc();
        ba();
        t();
        d();
        f();
        h();
        o();
        bh();
        s();
        bi();
    }

    private void bn() {
        bc();
        ba();
        t();
        d();
        be();
        if (this.j.requestSportType() == 262) {
            ap();
            ah();
        }
        k();
        n();
        z();
    }

    private void a() {
        if (this.j.requestSportData() == null || !this.j.requestSportData().containsKey("active_time") || this.j.requestSportData().get("active_time") == null) {
            return;
        }
        int intValue = this.j.requestSportData().get("active_time").intValue();
        LogUtil.a("Track_TrackDetailItemDrawer", "activeTime", Integer.valueOf(intValue));
        long requestTotalTime = this.j.requestTotalTime();
        if (intValue > 0) {
            this.c.add(d(requestTotalTime, intValue));
        }
    }

    private void j() {
        int e;
        if (by()) {
            e = this.j.getExtendDataInt("peak_power");
        } else {
            MotionPath motionPath = this.h;
            e = motionPath != null ? hji.e(motionPath.requestPowerList()) : 0;
        }
        if (e > 0) {
            this.c.add(b(e));
        }
    }

    private SportDetailItem.b b(int i) {
        return new SportDetailItem.b(bhA_(R.drawable._2131430259_res_0x7f0b0b73), this.g.getString(by() ? R.string._2130845948_res_0x7f0220fc : R.string._2130846368_res_0x7f0222a0), UnitUtil.e(i, 1, 0), this.g.getString(R.string._2130843492_res_0x7f021764));
    }

    private void t(int i) {
        if (hji.f(i)) {
            bt();
        } else {
            bo();
        }
    }

    private void k() {
        int intValue = this.j.requestSportData().get("swim_pull_freq").intValue();
        if (intValue > 0) {
            this.c.add(i(intValue));
        }
    }

    private SportDetailItem.b i(int i) {
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430510_res_0x7f0b0c6e), this.g.getString(R.string._2130839819_res_0x7f02090b), UnitUtil.e(i, 1, 0), this.g.getQuantityString(R.plurals._2130903224_res_0x7f0300b8, i));
    }

    private void n() {
        int intValue = this.j.requestSportData().get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF).intValue();
        if (intValue > 0) {
            this.c.add(j(intValue));
        }
    }

    private SportDetailItem.b j(int i) {
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430511_res_0x7f0b0c6f), this.g.getString(R.string._2130839822_res_0x7f02090e), UnitUtil.e(i, 1, 0), "");
    }

    private void ay() {
        long duration = this.k.d().getDuration();
        if (duration >= 0) {
            this.c.add(b(duration));
        }
    }

    private void az() {
        int distance = this.k.d().getDistance();
        if (distance >= 0) {
            this.c.add(n(distance));
        }
    }

    private SportDetailItem.b n(int i) {
        return d(i, this.k.d().getSportType() == 262 || this.k.d().getSportType() == 266);
    }

    private void bc() {
        int requestTotalDistance = this.j.requestTotalDistance();
        if (requestTotalDistance > 0) {
            this.c.add(k(requestTotalDistance));
        }
    }

    private boolean cb() {
        return this.j.requestSportType() == 262 || this.j.requestSportType() == 266;
    }

    private SportDetailItem.b k(int i) {
        return d(i, cb());
    }

    private SportDetailItem.b d(int i, boolean z) {
        String e;
        String str;
        String string;
        String a2;
        if (z) {
            if (i == 0) {
                str = this.f3766a.getString(R.string._2130850262_res_0x7f0231d6);
            } else {
                str = hji.i(i);
            }
            if (UnitUtil.h()) {
                string = this.g.getQuantityString(R.plurals._2130903227_res_0x7f0300bb, (int) Math.round(UnitUtil.e(i, 2)));
            } else {
                string = this.g.getString(R.string._2130841568_res_0x7f020fe0);
            }
        } else {
            MotionPathSimplify motionPathSimplify = this.j;
            if (motionPathSimplify != null && hji.j(motionPathSimplify.requestSportType())) {
                if (i == 0) {
                    a2 = nsf.h(R.string._2130850262_res_0x7f0231d6);
                } else {
                    a2 = UnitUtil.a(i, 1, 0, false);
                }
                str = a2;
                string = nsf.h(R.string._2130841568_res_0x7f020fe0);
            } else {
                if (i == 0) {
                    e = this.f3766a.getString(R.string._2130850262_res_0x7f0231d6);
                } else {
                    e = hji.e(i);
                }
                str = e;
                if (UnitUtil.h()) {
                    string = this.g.getString(R.string._2130841383_res_0x7f020f27);
                } else {
                    string = this.g.getString(R.string._2130841382_res_0x7f020f26);
                }
            }
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429919_res_0x7f0b0a1f), this.g.getString(R.string._2130839729_res_0x7f0208b1), str, string);
    }

    private void ba() {
        long requestTotalTime;
        int requestSportType = this.j.requestSportType();
        if (this.j.requestSportDataSource() != 2 && kxb.c(requestSportType)) {
            requestTotalTime = this.j.getExtendDataInt("skiTotalTime");
            if (requestTotalTime == -1) {
                LogUtil.b("Track_TrackDetailItemDrawer", "ski total time is null");
                return;
            }
        } else {
            requestTotalTime = this.j.requestTotalTime();
        }
        if (requestTotalTime > 0) {
            if (requestSportType == 220 && !this.f) {
                this.e.add(b(requestTotalTime));
            } else {
                this.c.add(b(requestTotalTime));
            }
        }
    }

    private SportDetailItem.b b(long j) {
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430482_res_0x7f0b0c52), this.g.getString(R.string._2130839759_res_0x7f0208cf), hji.c(j), "");
    }

    private void t() {
        int requestTotalCalories;
        if (this.j.requestChiefSportDataType() == 1 || this.j.requestChiefSportDataType() == 2 || (requestTotalCalories = this.j.requestTotalCalories()) <= 0) {
            return;
        }
        if (this.j.requestSportType() == 220 && !this.f) {
            this.e.add(g(requestTotalCalories));
        } else {
            this.c.add(g(requestTotalCalories));
        }
    }

    private void d() {
        int requestActiveCalories = this.j.requestActiveCalories();
        int requestTotalCalories = this.j.requestTotalCalories();
        if (requestActiveCalories <= 0 || requestActiveCalories >= requestTotalCalories) {
            return;
        }
        if (this.j.requestSportType() == 220 && !this.f) {
            this.e.add(c(requestActiveCalories));
        } else {
            this.c.add(c(requestActiveCalories));
        }
    }

    private SportDetailItem.b c(int i) {
        return new SportDetailItem.b(nsf.cKq_(R.drawable._2131430533_res_0x7f0b0c85), this.g.getString(R.string._2130847567_res_0x7f02274f), hji.c(i), this.g.getString(R.string._2130839711_res_0x7f02089f));
    }

    private SportDetailItem.b g(int i) {
        String str;
        if (i > 0) {
            str = hji.c(i);
        } else {
            str = this.b;
        }
        return new SportDetailItem.b(nsf.cKq_(R.drawable._2131430549_res_0x7f0b0c95), this.g.getString(R.string._2130847439_res_0x7f0226cf), str, this.g.getString(R.string._2130839711_res_0x7f02089f));
    }

    private void b() {
        int intValue;
        if (this.j.requestSportData() == null || !this.j.requestSportData().containsKey("active_time") || (intValue = this.j.requestSportData().get("active_time").intValue()) <= 0) {
            return;
        }
        this.c.add(e(intValue));
    }

    private SportDetailItem.b e(int i) {
        String str;
        if (i > 0) {
            str = UnitUtil.d(i);
        } else {
            str = this.b;
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429689_res_0x7f0b0939), this.g.getString(R.string._2130843157_res_0x7f021615), str, "");
    }

    private void ao() {
        MotionPath motionPath = this.h;
        if (motionPath == null || !motionPath.isValidCadenceData()) {
            return;
        }
        int bv = bv();
        if (bv == 0) {
            LogUtil.a("Track_TrackDetailItemDrawer", "buildRideTimeDetailItem rideTime is 0");
            return;
        }
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(this.j.requestTotalTime());
        if (bv > seconds) {
            LogUtil.a("Track_TrackDetailItemDrawer", "rideTime is greater than totalTime");
            bv = seconds;
        }
        this.c.add(o(bv));
    }

    private int bv() {
        Iterator<ffn> it = this.h.requestRidePostureDataList().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().e() > 0) {
                i++;
            }
        }
        return i * 5;
    }

    private SportDetailItem.b o(int i) {
        String str;
        if (i > 0) {
            str = UnitUtil.d(i);
        } else {
            str = this.b;
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429689_res_0x7f0b0939), this.g.getString(R.string._2130843687_res_0x7f021827), str, "");
    }

    private SportDetailItem.b d(long j, int i) {
        String str;
        if (j > 0 && i > 0) {
            str = hji.b(j, i);
        } else {
            str = this.b;
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430268_res_0x7f0b0b7c), this.g.getString(R.string._2130843158_res_0x7f021616), str, "");
    }

    private void f() {
        float requestAvgPace = this.j.requestAvgPace();
        if (bu()) {
            if (UnitUtil.h()) {
                requestAvgPace = ((float) UnitUtil.d(requestAvgPace, 2)) / 5.0f;
            }
        } else if (UnitUtil.h()) {
            requestAvgPace = (float) UnitUtil.d(requestAvgPace, 3);
        }
        double d = requestAvgPace;
        if (d > 360000.0d || d <= 3.6d || requestAvgPace <= 0.0f) {
            return;
        }
        this.c.add(d(requestAvgPace));
    }

    private void i() {
        float requestAvgPace = this.j.requestAvgPace() / 2.0f;
        double d = requestAvgPace;
        if (d > 360000.0d || d <= 3.6d || requestAvgPace <= 0.0f) {
            return;
        }
        this.c.add(e(requestAvgPace));
    }

    private boolean bu() {
        return this.j.requestSportType() == 274;
    }

    private SportDetailItem.b d(float f) {
        String str;
        String str2;
        if (f > 0.0f) {
            str = gvv.a(f);
        } else {
            str = this.b;
        }
        if (bu()) {
            str2 = e(this.f3766a);
        } else if (UnitUtil.h()) {
            str2 = "/" + this.g.getString(R.string._2130844081_res_0x7f0219b1);
        } else {
            str2 = "/" + this.g.getString(R.string._2130844082_res_0x7f0219b2);
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429729_res_0x7f0b0961), this.g.getString(R.string._2130839760_res_0x7f0208d0), str, str2);
    }

    private SportDetailItem.b e(float f) {
        String str = this.b;
        if (f > 0.0f) {
            str = gvv.a(f);
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429729_res_0x7f0b0961), nsf.h(R.string._2130839760_res_0x7f0208d0), str, nsf.a(R.plurals._2130903225_res_0x7f0300b9, 500, 500));
    }

    private String e(Context context) {
        if (context == null) {
            LogUtil.h("Track_TrackDetailItemDrawer", "acquire Swim Unit and context is null");
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
        }
        return context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500);
    }

    private void h() {
        float requestAvgPace;
        String o;
        if (UnitUtil.h()) {
            requestAvgPace = this.j.requestAvgPace() * 1.609344f;
        } else {
            requestAvgPace = this.j.requestAvgPace();
        }
        if (bu()) {
            o = hji.o(requestAvgPace * 2.0f);
        } else {
            o = hji.o(requestAvgPace);
        }
        if (gvv.e(BaseApplication.getContext()).equals(o)) {
            LogUtil.a("Track_TrackDetailItemDrawer", "buildAverageSpeedItem is 0");
        } else {
            this.c.add(e(o));
        }
    }

    private SportDetailItem.b e(String str) {
        String string = this.g.getString(R.string._2130844078_res_0x7f0219ae);
        if (UnitUtil.h()) {
            string = this.g.getString(R.string._2130844079_res_0x7f0219af);
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429731_res_0x7f0b0963), this.g.getString(R.string._2130839763_res_0x7f0208d3), str, string);
    }

    private void c() {
        int c;
        if (this.j.requestSportType() == 273 && this.j.getExtendDataInt("crossTrainerCadence") > 0) {
            c = this.j.getExtendDataInt("crossTrainerCadence");
        } else {
            c = hji.c(this.j, this.h);
        }
        if (c > 0) {
            this.c.add(d(c));
        }
    }

    private SportDetailItem.b d(int i) {
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429728_res_0x7f0b0960), this.g.getString(R.string._2130843489_res_0x7f021761), UnitUtil.e(i, 1, 0), this.g.getQuantityString(R.plurals._2130903283_res_0x7f0300f3, i, ""));
    }

    private void ar() {
        int c = hji.c(this.j);
        if (c > 1.0E-9d) {
            this.c.add(a(c));
        }
    }

    private SportDetailItem.b a(int i) {
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429730_res_0x7f0b0962), this.g.getString(R.string._2130843500_res_0x7f02176c), UnitUtil.e(i, 1, 0), this.g.getString(R.string._2130843497_res_0x7f021769));
    }

    private void o() {
        int requestAvgStepRate = this.j.requestAvgStepRate();
        if (requestAvgStepRate <= 0 || !hji.i(this.j.requestSportType())) {
            return;
        }
        this.c.add(f(requestAvgStepRate));
    }

    private SportDetailItem.b f(int i) {
        String str;
        if (i > 0) {
            str = hji.b(i);
        } else {
            str = this.b;
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429732_res_0x7f0b0964), this.g.getString(R.string._2130839765_res_0x7f0208d5), str, this.g.getQuantityString(R.plurals._2130903288_res_0x7f0300f8, 0));
    }

    private void bh() {
        float f;
        MotionPath motionPath;
        int requestTotalSteps = this.j.requestTotalSteps();
        int requestTotalDistance = this.j.requestTotalDistance();
        if (requestTotalSteps <= 0 || requestTotalDistance <= 0 || !hji.i(this.j.requestSportType())) {
            return;
        }
        if (this.j.requestSportDataSource() != 5 || (motionPath = this.h) == null || motionPath.requestStepRateList().size() <= 0 || this.h.requestStepRateList().get(0).acquireStepRate() != this.j.requestAvgStepRate()) {
            f = ((requestTotalDistance * 1.0f) / requestTotalSteps) * 100.0f;
        } else {
            f = (this.j.requestAvgStepRate() == 0 || this.j.requestTotalTime() == 0) ? 0.0f : ((requestTotalDistance * 1.0f) / (this.j.requestAvgStepRate() * TimeUnit.SECONDS.toMinutes(TimeUnit.MILLISECONDS.toSeconds(this.j.requestTotalTime())))) * 100.0f;
        }
        if (f <= 200.0f) {
            this.c.add(b(f));
        }
    }

    private SportDetailItem.b b(float f) {
        String string = this.g.getString(R.string._2130839769_res_0x7f0208d9);
        String j = hji.j(f);
        if (UnitUtil.h()) {
            string = this.g.getString(R.string._2130839770_res_0x7f0208da);
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430494_res_0x7f0b0c5e), this.g.getString(R.string._2130839852_res_0x7f02092c), j, string);
    }

    private void bj() {
        int requestTotalSteps = this.j.requestTotalSteps();
        if (requestTotalSteps > 0) {
            if (hji.i(this.j.requestSportType()) || hji.d(this.j.requestSportType(), this.h)) {
                this.c.add(s(requestTotalSteps));
            }
        }
    }

    private SportDetailItem.b s(int i) {
        String str;
        if (i > 0) {
            str = hji.b(i);
        } else {
            str = this.b;
        }
        return new SportDetailItem.b(bhA_(R.drawable._2131430531_res_0x7f0b0c83), this.g.getString(R.string._2130839767_res_0x7f0208d7), str, this.g.getString(R.string._2130841518_res_0x7f020fae));
    }

    private void bk() {
        int requestTotalSteps = this.j.requestTotalSteps();
        int c = hji.c(this.j);
        if (requestTotalSteps <= 0 || c <= 1.0E-9d) {
            return;
        }
        this.c.add(r(requestTotalSteps));
    }

    private SportDetailItem.b r(int i) {
        String str;
        if (i > 0) {
            str = hji.b(i);
        } else {
            str = this.b;
        }
        return new SportDetailItem.b(bhA_(by() ? R.drawable._2131430532_res_0x7f0b0c84 : R.drawable._2131430530_res_0x7f0b0c82), this.g.getString(by() ? R.string._2130845947_res_0x7f0220fb : R.string._2130843495_res_0x7f021767), str, this.f3766a.getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, i));
    }

    private boolean by() {
        return gvv.c(this.j).equals("291");
    }

    private void z() {
        int requestAvgHeartRate = this.j.requestAvgHeartRate();
        if (requestAvgHeartRate > 0 && this.c != null) {
            if (this.j.requestSportType() == 220 && !this.f) {
                this.e.add(h(requestAvgHeartRate));
                return;
            } else {
                this.c.add(h(requestAvgHeartRate));
                return;
            }
        }
        LogUtil.a("Track_TrackDetailItemDrawer", "no buildHeartRateData");
    }

    private SportDetailItem.b h(int i) {
        String str;
        if (i != 0) {
            str = UnitUtil.e(i, 1, 0);
        } else {
            str = this.b;
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable.ic_heart_rate), this.g.getString(R.string._2130839764_res_0x7f0208d4), str, this.g.getString(R.string.IDS_main_watch_heart_rate_unit_string));
    }

    private boolean f(float f) {
        if (f > 1.0f) {
            return true;
        }
        MotionPath motionPath = this.h;
        if (motionPath == null) {
            return false;
        }
        return motionPath.isValidAltitudeList() && f >= 0.0f;
    }

    private void s() {
        float requestCreepingWave = this.j.requestCreepingWave() / 10.0f;
        if (f(requestCreepingWave)) {
            this.c.add(a(requestCreepingWave));
        }
    }

    private SportDetailItem.b a(float f) {
        String e;
        String str;
        if (UnitUtil.h()) {
            double e2 = UnitUtil.e(f, 1);
            str = this.g.getQuantityString(R.plurals._2130903238_res_0x7f0300c6, (int) Math.round(e2));
            e = UnitUtil.e(e2, 1, 2);
        } else {
            String string = this.g.getString(R.string._2130841568_res_0x7f020fe0);
            e = UnitUtil.e(f, 1, 1);
            str = string;
        }
        return new SportDetailItem.b(bhA_(R.drawable._2131429893_res_0x7f0b0a05), this.g.getString(R.string._2130842325_res_0x7f0212d5), e, str);
    }

    private void bi() {
        float requestTotalDescent = this.j.requestTotalDescent() / 10.0f;
        if (requestTotalDescent <= 0.0f || !f(requestTotalDescent)) {
            return;
        }
        this.c.add(c(requestTotalDescent));
    }

    private SportDetailItem.b c(float f) {
        String e;
        String str;
        if (UnitUtil.h()) {
            double e2 = UnitUtil.e(f, 1);
            str = this.g.getQuantityString(R.plurals._2130903238_res_0x7f0300c6, (int) Math.round(e2));
            e = UnitUtil.e(e2, 1, 2);
        } else {
            String string = this.g.getString(R.string._2130841568_res_0x7f020fe0);
            e = UnitUtil.e(f, 1, 1);
            str = string;
        }
        return new SportDetailItem.b(bhA_(R.drawable._2131429910_res_0x7f0b0a16), this.g.getString(R.string._2130842545_res_0x7f0213b1), e, str);
    }

    private void be() {
        Map<String, Integer> map = this.i;
        if (map == null || map.get("swim_stroke") == null) {
            return;
        }
        this.c.add(bg());
    }

    private SportDetailItem.b bg() {
        String string;
        SportDetailItem.b bVar = new SportDetailItem.b(bhA_(R.drawable._2131430499_res_0x7f0b0c63), this.g.getString(R.string._2130839812_res_0x7f020904), this.b, "");
        int intValue = this.i.get("swim_stroke").intValue();
        if (intValue == 1) {
            string = this.g.getString(R.string._2130839815_res_0x7f020907);
        } else if (intValue == 2) {
            string = this.g.getString(R.string._2130839813_res_0x7f020905);
        } else if (intValue == 3) {
            string = this.g.getString(R.string._2130839816_res_0x7f020908);
        } else if (intValue == 4) {
            string = this.g.getString(R.string._2130839814_res_0x7f020906);
        } else if (intValue == 5) {
            string = this.g.getString(R.string._2130839817_res_0x7f020909);
        } else {
            string = this.g.getString(R.string._2130839813_res_0x7f020905);
        }
        bVar.d(string);
        return bVar;
    }

    private void bd() {
        int intValue;
        Map<String, Integer> map = this.i;
        if (map == null || map.get(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES) == null || (intValue = this.i.get(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES).intValue()) < 0) {
            return;
        }
        this.c.add(p(intValue));
    }

    private SportDetailItem.b p(int i) {
        SportDetailItem.b bVar = new SportDetailItem.b(bhA_(R.drawable._2131430498_res_0x7f0b0c62), this.g.getString(R.string._2130839818_res_0x7f02090a), this.b, this.g.getQuantityString(R.plurals._2130903284_res_0x7f0300f4, i, ""));
        bVar.d(UnitUtil.e(i, 1, 0));
        return bVar;
    }

    private void ap() {
        Map<String, Integer> map = this.i;
        if (map == null || map.get(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH) == null) {
            return;
        }
        double intValue = this.i.get(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH).intValue();
        if (intValue >= 0.0d) {
            this.c.add(i(intValue));
        }
    }

    private SportDetailItem.b i(double d) {
        String string;
        SportDetailItem.b bVar = new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430256_res_0x7f0b0b70), this.g.getString(R.string._2130839820_res_0x7f02090c), this.b, this.g.getString(R.string._2130841568_res_0x7f020fe0));
        if (UnitUtil.h()) {
            string = this.g.getQuantityString(R.plurals._2130903227_res_0x7f0300bb, (int) Math.round(d));
        } else {
            string = this.g.getString(R.string._2130841568_res_0x7f020fe0);
        }
        bVar.e(string);
        bVar.d(UnitUtil.e(hji.c(d), 1, 0));
        return bVar;
    }

    private void ah() {
        Map<String, Integer> map = this.i;
        if (map == null || map.get("swim_laps") == null || this.i.get("swim_laps").intValue() < 0) {
            return;
        }
        this.c.add(ae());
    }

    private SportDetailItem.b ae() {
        SportDetailItem.b bVar = new SportDetailItem.b(bhA_(R.drawable._2131430145_res_0x7f0b0b01), this.g.getString(R.string._2130839821_res_0x7f02090d), this.b, "");
        bVar.d(UnitUtil.e(this.i.get("swim_laps").intValue(), 1, 0));
        return bVar;
    }

    private void y() {
        Map<String, Integer> map = this.i;
        if (map == null || map.get("achieve_percent") == null || this.i.get("achieve_percent").intValue() <= 0) {
            return;
        }
        this.c.add(ab());
    }

    private SportDetailItem.b ab() {
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429964_res_0x7f0b0a4c), this.g.getString(R.string._2130839782_res_0x7f0208e6), UnitUtil.e(this.i.get("achieve_percent").intValue(), 2, 1), "");
    }

    private void ac() {
        MotionPath motionPath = this.h;
        if (motionPath == null || koq.b(motionPath.requestJumpDataList()) || hji.d(this.h.requestJumpDataList()) <= 0) {
            return;
        }
        this.c.add(aa());
    }

    private void bb() {
        if (this.j.getExtendDataInt("skipSpeed") > 0) {
            this.c.add(g());
        }
    }

    private void bf() {
        if (this.h != null) {
            if (g("interruptTimes")) {
                b("interruptTimes");
            } else if (g("stumblingRope")) {
                b("stumblingRope");
            }
        }
    }

    private boolean g(String str) {
        return this.j.requestExtendDataMap().containsKey(str) && this.j.requestExtendDataMap().get(str) != null;
    }

    private void b(String str) {
        if (this.j.getExtendDataInt(str) != -1) {
            this.c.add(j(str));
        }
    }

    private void af() {
        if (this.h == null || !this.j.requestExtendDataMap().containsKey("maxSkippingTimes") || this.j.requestExtendDataMap().get("maxSkippingTimes") == null || this.j.getExtendDataInt("maxSkippingTimes") == -1) {
            return;
        }
        this.c.add(ak());
    }

    private SportDetailItem.b ak() {
        int extendDataInt = this.j.getExtendDataInt("maxSkippingTimes");
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429890_res_0x7f0b0a02), this.g.getString(R.string._2130843712_res_0x7f021840), UnitUtil.e(extendDataInt, 1, 0), this.g.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, extendDataInt, ""));
    }

    private void as() {
        if (g("skippingMode") && this.j.getExtendDataInt("skippingMode") == 3) {
            e("singleShakeNum", R.drawable._2131430434_res_0x7f0b0c22, R.string._2130846062_res_0x7f02216e);
            e("doubleShakeNum", R.drawable._2131429926_res_0x7f0b0a26, R.string._2130846063_res_0x7f02216f);
        }
    }

    private void e(String str, int i, int i2) {
        int extendDataInt;
        if (this.h == null || !g(str) || (extendDataInt = this.j.getExtendDataInt(str)) == -1) {
            return;
        }
        this.c.add(e(extendDataInt, i, i2));
    }

    private SportDetailItem.b e(int i, int i2, int i3) {
        return new SportDetailItem.b(this.g.getDrawable(i2), this.g.getString(i3), UnitUtil.e(i, 1, 0), this.g.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, i, ""));
    }

    private SportDetailItem.b aa() {
        String string;
        String e;
        if (UnitUtil.h()) {
            string = this.g.getString(R.string._2130841897_res_0x7f021129);
            e = UnitUtil.e((int) UnitUtil.e(hji.d(this.h.requestJumpDataList()), 0), 1, 0);
        } else {
            string = this.g.getString(R.string._2130841416_res_0x7f020f48);
            e = UnitUtil.e(hji.d(this.h.requestJumpDataList()), 1, 0);
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430086_res_0x7f0b0ac6), this.g.getString(R.string._2130843161_res_0x7f021619), e, string);
    }

    private SportDetailItem.b j(String str) {
        String quantityString = this.g.getQuantityString(R.plurals._2130903213_res_0x7f0300ad, this.j.getExtendDataInt(str), "");
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430500_res_0x7f0b0c64), this.g.getString(R.string._2130843709_res_0x7f02183d), UnitUtil.e(this.j.getExtendDataInt(str), 1, 0), quantityString);
    }

    private void ai() {
        Map<String, Integer> map = this.i;
        if (map == null || map.get("jump_times") == null || this.i.get("jump_times").intValue() <= 0) {
            return;
        }
        this.c.add(ag());
    }

    private SportDetailItem.b ag() {
        return new SportDetailItem.b(bhA_(R.drawable._2131430134_res_0x7f0b0af6), this.g.getString(R.string._2130843162_res_0x7f02161a), UnitUtil.e(this.i.get("jump_times").intValue(), 1, 0), this.g.getQuantityString(R.plurals._2130903241_res_0x7f0300c9, this.i.get("jump_times").intValue()));
    }

    private SportDetailItem.b g() {
        return new SportDetailItem.b(bhA_(R.drawable._2131429731_res_0x7f0b0963), this.g.getString(R.string._2130839763_res_0x7f0208d3), UnitUtil.e(this.j.getExtendDataInt("skipSpeed"), 1, 0), this.g.getString(R.string._2130843710_res_0x7f02183e));
    }

    private void bw() {
        MotionPath motionPath = this.h;
        if (motionPath == null || koq.b(motionPath.requestJumpDataList()) || hji.b(this.h.requestJumpDataList()) <= 0) {
            return;
        }
        this.c.add(bs());
    }

    private SportDetailItem.b bs() {
        return new SportDetailItem.b(bhA_(R.drawable._2131429929_res_0x7f0b0a29), this.g.getString(R.string._2130843163_res_0x7f02161b), UnitUtil.e(hji.b(this.h.requestJumpDataList()), 1, 0), this.g.getString(R.string._2130842713_res_0x7f021459));
    }

    private void l() {
        double extendDataDouble = this.j.getExtendDataDouble("golfSwingTempo");
        if (extendDataDouble != -1.0d) {
            this.c.add(e(extendDataDouble));
        }
    }

    private SportDetailItem.b e(double d) {
        SportDetailItem.b bVar = new SportDetailItem.b(bhA_(R.drawable._2131430334_res_0x7f0b0bbe), this.g.getString(R.string._2130839898_res_0x7f02095a), this.b, "");
        bVar.d(UnitUtil.e(d / 100.0d, 1, 1));
        return bVar;
    }

    private void p() {
        double extendDataDouble = this.j.getExtendDataDouble("golfBackSwingTime");
        if (extendDataDouble != -1.0d) {
            this.c.add(a(extendDataDouble));
        }
    }

    private SportDetailItem.b a(double d) {
        return new SportDetailItem.b(bhA_(R.drawable._2131429729_res_0x7f0b0961), this.g.getString(R.string._2130839899_res_0x7f02095b), UnitUtil.e(d / 1000.0d, 1, 2), this.g.getString(R.string._2130843202_res_0x7f021642));
    }

    private void v() {
        double extendDataDouble = this.j.getExtendDataDouble("golfDownSwingTime");
        if (extendDataDouble != -1.0d) {
            this.c.add(d(extendDataDouble));
        }
    }

    private SportDetailItem.b d(double d) {
        return new SportDetailItem.b(bhA_(R.drawable._2131429729_res_0x7f0b0961), this.g.getString(R.string._2130839900_res_0x7f02095c), UnitUtil.e(d / 1000.0d, 1, 2), this.g.getString(R.string._2130843202_res_0x7f021642));
    }

    private void m() {
        double extendDataDouble = this.j.getExtendDataDouble("golfSwingSpeed");
        if (extendDataDouble != -1.0d) {
            this.c.add(c(extendDataDouble));
        }
    }

    private SportDetailItem.b c(double d) {
        double d2;
        Drawable bhA_ = bhA_(R.drawable._2131429731_res_0x7f0b0963);
        String string = this.g.getString(R.string._2130844382_res_0x7f021ade);
        if (UnitUtil.h()) {
            d2 = UnitUtil.e((d / 100.0d) * 3.5999999046325684d, 3);
            string = this.g.getString(R.string._2130844079_res_0x7f0219af);
        } else {
            d2 = d / 100.0d;
        }
        return new SportDetailItem.b(bhA_, this.g.getString(R.string._2130839901_res_0x7f02095d), UnitUtil.e(d2, 1, 1), string);
    }

    private void aj() {
        double extendDataDouble = this.j.getExtendDataDouble("golfMaxSwingSpeed");
        if (extendDataDouble != -1.0d) {
            this.c.add(b(extendDataDouble));
        }
    }

    private SportDetailItem.b b(double d) {
        double d2;
        Drawable bhA_ = bhA_(R.drawable._2131429731_res_0x7f0b0963);
        String string = this.g.getString(R.string._2130844382_res_0x7f021ade);
        if (UnitUtil.h()) {
            d2 = UnitUtil.e((d / 100.0d) * 3.5999999046325684d, 3);
            string = this.g.getString(R.string._2130844079_res_0x7f0219af);
        } else {
            d2 = d / 100.0d;
        }
        return new SportDetailItem.b(bhA_, this.g.getString(R.string._2130839902_res_0x7f02095e), UnitUtil.e(d2, 1, 1), string);
    }

    private void aw() {
        int extendDataInt = this.j.getExtendDataInt("skiTripTimes");
        if (extendDataInt == -1) {
            LogUtil.b("Track_TrackDetailItemDrawer", "ski trip times is null.");
        } else {
            this.c.add(l(extendDataInt));
        }
    }

    private SportDetailItem.b l(int i) {
        SportDetailItem.b bVar = new SportDetailItem.b(bhA_(R.drawable._2131430553_res_0x7f0b0c99), this.g.getString(R.string._2130839892_res_0x7f020954), this.b, "");
        bVar.d(UnitUtil.e(i, 1, 0));
        return bVar;
    }

    private void ax() {
        long requestTotalTime = this.j.requestTotalTime();
        if (requestTotalTime > 0) {
            this.c.add(e(requestTotalTime));
        }
    }

    private SportDetailItem.b e(long j) {
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131430482_res_0x7f0b0c52), this.g.getString(R.string._2130839890_res_0x7f020952), hji.c(j), "");
    }

    private void at() {
        int requestTotalDistance = this.j.requestTotalDistance();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(this.j.requestTotalTime());
        if (requestTotalDistance <= 0 || seconds <= 0) {
            return;
        }
        this.c.add(b(requestTotalDistance, seconds));
    }

    private SportDetailItem.b b(int i, long j) {
        String string = this.g.getString(R.string._2130844078_res_0x7f0219ae);
        if (UnitUtil.h()) {
            string = this.g.getString(R.string._2130844079_res_0x7f0219af);
        }
        return new SportDetailItem.b(this.g.getDrawable(R.drawable._2131429731_res_0x7f0b0963), this.g.getString(R.string._2130839893_res_0x7f020955), hji.b(i, j), string);
    }

    private void al() {
        int extendDataInt = this.j.getExtendDataInt("skiMaxSlopeDegree");
        int extendDataInt2 = this.j.getExtendDataInt("skiMaxSlopePercent");
        if (extendDataInt == -1 || extendDataInt2 == -1) {
            LogUtil.b("Track_TrackDetailItemDrawer", "ski max slope is null.");
        } else {
            this.c.add(c(extendDataInt / 10.0f, extendDataInt2 / 10.0f));
        }
    }

    private void bl() {
        int extendDataInt = this.j.getExtendDataInt("total_action_group");
        if (extendDataInt > 0) {
            this.c.add(q(extendDataInt));
        }
    }

    private SportDetailItem.b q(int i) {
        return new SportDetailItem.b(bhA_(R.drawable._2131430529_res_0x7f0b0c81), this.g.getString(R.string._2130845950_res_0x7f0220fe), UnitUtil.e(i, 1, 0), this.f3766a.getResources().getQuantityString(R.plurals._2130903395_res_0x7f030163, i, ""));
    }

    private void aq() {
        int extendDataInt = this.j.getExtendDataInt("peak_weight");
        if (extendDataInt > 0) {
            if (UnitUtil.h()) {
                extendDataInt = (int) Math.round(UnitUtil.h(extendDataInt));
            }
            this.c.add(m(extendDataInt));
        }
    }

    private SportDetailItem.b m(int i) {
        return new SportDetailItem.b(bhA_(R.drawable._2131430236_res_0x7f0b0b5c), this.g.getString(R.string._2130845949_res_0x7f0220fd), UnitUtil.e(i, 1, 0), this.f3766a.getResources().getQuantityString(UnitUtil.h() ? R.plurals._2130903321_res_0x7f030119 : R.plurals._2130903344_res_0x7f030130, i, ""));
    }

    private SportDetailItem.b c(float f, float f2) {
        Drawable bhA_ = bhA_(R.drawable._2131430452_res_0x7f0b0c34);
        int round = Math.round(f);
        return new SportDetailItem.b(bhA_, this.g.getString(R.string._2130839895_res_0x7f020957), String.format(this.g.getString(R.string._2130839918_res_0x7f02096e), this.f3766a.getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, round, Integer.valueOf(round)), UnitUtil.e(f2, 2, 1)), "");
    }

    private Drawable bhA_(int i) {
        if (LanguageUtil.bc(this.f3766a)) {
            return nrz.cKn_(this.f3766a, i);
        }
        return this.f3766a.getResources().getDrawable(i);
    }

    public int e() {
        List<SportDetailItem.b> list = this.c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
