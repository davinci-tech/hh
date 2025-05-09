package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.HealthDataInputDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriod;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.InputBloodSugarActivity;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryRepository;
import com.huawei.ui.main.stories.template.BaseActivity;
import com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract;
import com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class qle extends HealthDetailCommonActivityPresenter {

    /* renamed from: a, reason: collision with root package name */
    private static String f16467a;
    private static Long b;
    private static String c;
    private static Long d;
    private static H5ProLaunchOption e;
    private Long f;
    private double g;
    private int h;
    private int i;
    private Long j;
    private LastTimeHealthDataReader k;
    private int l;
    private HealthRadioButton[] m;
    private Handler n;
    private int o;
    private long p;
    private HealthTextView q;
    private LinearLayout r;
    private BloodSugarTimePeriodView s;
    private int t;
    private HealthToolBar w;

    public qle() {
        super("Main_BloodSugarActivityPresenter");
        this.h = 0;
        this.i = 0;
        this.l = -1;
        this.o = 0;
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public void getLastDataTimestamp(IBaseResponseCallback iBaseResponseCallback) {
        a(iBaseResponseCallback);
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public void initToolBar() {
        e();
        this.f = Long.valueOf(System.currentTimeMillis());
        f();
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter, com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityPresenter
    public void setChartStartTimeAndEndTime(long j, long j2) {
        d = Long.valueOf(j);
        b = Long.valueOf(j2);
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter, com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityPresenter
    public void notifyChartDateStatus(DateType dateType, boolean z, boolean z2) {
        u();
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter, com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityPresenter
    public void notifyViewPagerChange(int i) {
        this.h = i;
        e();
        u();
    }

    private void f() {
        DataDetailActivityContract.DetailActivityView view = getView();
        if (view == null) {
            ReleaseLogUtil.a("Main_BloodSugarActivityPresenter", "init detailActivityView is null");
            return;
        }
        Context viewContext = view.getViewContext();
        if (viewContext == null) {
            ReleaseLogUtil.a("Main_BloodSugarActivityPresenter", "init context is null");
            return;
        }
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            f16467a = userInfo.getName();
        }
        c = CommonUtil.u();
        if (this.w == null) {
            HealthToolBar healthToolBar = view.getHealthToolBar();
            this.w = healthToolBar;
            if (healthToolBar != null) {
                healthToolBar.cHc_(nsf.cKr_(viewContext, R.layout.hw_toolbar_bottomview, null));
            }
            s();
        }
        ArrayList<String> a2 = cjx.e().a(HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR);
        if (koq.b(a2) || a2.size() == 0) {
            j();
        } else {
            u();
        }
    }

    public void a() {
        if (getView() == null || getView().getViewContext() == null) {
            LogUtil.h("Main_BloodSugarActivityPresenter", "getView is null || getViewContext is null");
            return;
        }
        Context viewContext = getView().getViewContext();
        if (String.valueOf(false).equals(qjv.b(viewContext, "IS_FIRST_ENTRY_BLOOD_SUGAR"))) {
            return;
        }
        View inflate = LayoutInflater.from(viewContext).inflate(R.layout.blood_sugar_first_entry_guide, (ViewGroup) null);
        if (LanguageUtil.bc(viewContext)) {
            inflate.setBackgroundResource(R.drawable._2131427632_res_0x7f0b0130);
        } else {
            inflate.setBackgroundResource(R.drawable._2131427631_res_0x7f0b012f);
        }
        dFg_(viewContext, inflate);
    }

    private void dFg_(final Context context, View view) {
        int i;
        final int c2 = nsn.c(context, 220.0f);
        final PopupWindow popupWindow = new PopupWindow(view, c2, -2, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setSoftInputMode(16);
        popupWindow.setOutsideTouchable(false);
        final TextView textView = (TextView) view.findViewById(R.id.blood_sugar_first_entry_guide_content);
        textView.setOnClickListener(new View.OnClickListener() { // from class: qln
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.dFj_(popupWindow, view2);
            }
        });
        int c3 = nsn.c(context, 24.0f);
        int c4 = nsn.c(context, 24.0f);
        if (LanguageUtil.bc(context)) {
            i = (-c3) / 2;
        } else {
            i = ((c3 * 3) / 2) - c2;
        }
        final int i2 = i;
        view.measure(0, 0);
        final int measuredHeight = view.getMeasuredHeight();
        final int c5 = ((-measuredHeight) - nsn.c(context, 8.0f)) - c4;
        final ImageView imageView = (ImageView) this.w.cHe_(3).findViewById(R.id.toolbar_imageView);
        qjv.a(context, "IS_FIRST_ENTRY_BLOOD_SUGAR", String.valueOf(false));
        popupWindow.showAsDropDown(imageView, i2, c5);
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: qle.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int height = textView.getHeight();
                int c6 = measuredHeight - nsn.c(context, 28.0f);
                if (height > c6) {
                    textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    popupWindow.update(imageView, i2, (c5 - height) + c6, c2, -2);
                }
            }
        });
    }

    static /* synthetic */ void dFj_(PopupWindow popupWindow, View view) {
        popupWindow.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void s() {
        if (this.w == null) {
            LogUtil.b("Main_BloodSugarActivityPresenter", "mToolBar is null");
        } else {
            k();
            this.w.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: qlr
                @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
                public final void onSingleTap(int i) {
                    qle.this.e(i);
                }
            });
        }
    }

    /* synthetic */ void e(int i) {
        if (i == 0) {
            login(String.valueOf(i), AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_DETAIL_RECORD_2030031.value());
            return;
        }
        if (i == 1) {
            login(String.valueOf(i), AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_DETAIL_MEASURE_2030032.value());
        } else if (i == 3) {
            login(String.valueOf(i), "");
        } else {
            if (i != 4) {
                return;
            }
            b(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), "1");
            gnm.aPB_(getView().getViewContext(), new Intent(getView().getViewContext(), (Class<?>) BloodSugarHistoryActivity.class));
        }
    }

    private void k() {
        HealthToolBar healthToolBar = this.w;
        healthToolBar.setIconTitle(0, healthToolBar.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_input));
        this.w.setIconVisible(0, 8);
        HealthToolBar healthToolBar2 = this.w;
        healthToolBar2.setIconTitle(1, healthToolBar2.getResources().getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_mesure));
        this.w.setIconVisible(1, 8);
        HealthToolBar healthToolBar3 = this.w;
        healthToolBar3.setIconTitle(4, healthToolBar3.getResources().getString(R$string.IDS_hw_base_health_data_history_record));
        HealthToolBar healthToolBar4 = this.w;
        healthToolBar4.setIconTitle(3, healthToolBar4.getResources().getString(R$string.IDS_w_m_t_diet_diary));
        this.w.setIconVisible(3, 0);
        Context e2 = BaseApplication.e();
        if (LanguageUtil.bc(e2)) {
            this.w.setIcon(0, nrz.cKn_(e2, R.drawable._2131430283_res_0x7f0b0b8b));
            this.w.setIcon(1, nrz.cKn_(e2, R.drawable._2131429851_res_0x7f0b09db));
            this.w.setIcon(4, nrz.cKn_(e2, R.drawable._2131430309_res_0x7f0b0ba5));
            this.w.setIcon(3, nrz.cKn_(e2, R.drawable._2131429918_res_0x7f0b0a1e));
            return;
        }
        this.w.setIcon(0, R.drawable._2131430283_res_0x7f0b0b8b);
        this.w.setIcon(1, R.drawable._2131429851_res_0x7f0b09db);
        this.w.setIcon(4, R.drawable._2131430309_res_0x7f0b0ba5);
        this.w.setIcon(3, R.drawable._2131429918_res_0x7f0b0a1e);
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public void onLogined(String str) {
        if (String.valueOf(1).equals(str)) {
            c();
            return;
        }
        if (String.valueOf(0).equals(str)) {
            w();
            return;
        }
        if (String.valueOf(3).equals(str)) {
            if (this.h == 0 && n()) {
                p();
                return;
            } else {
                e((String) null);
                return;
            }
        }
        if ("BLOOD_SUGAR_INPUT_DIALOG".equals(str)) {
            LogUtil.a("Main_BloodSugarActivityPresenter", "position=INPUT_DIALOG_KEY");
            g();
        } else {
            LogUtil.h("Main_BloodSugarActivityPresenter", "unknow position=", str);
        }
    }

    private void w() {
        DataDetailActivityContract.DetailActivityView view = getView();
        if (view == null) {
            return;
        }
        a(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_DETAIL_RECORD_2030031.value());
        b(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), "5");
        gnm.aPB_(view.getViewContext(), new Intent(view.getViewContext(), (Class<?>) InputBloodSugarActivity.class));
    }

    private void c() {
        if (e((IBaseResponseCallback) null, 0L) > 0) {
            y();
        } else {
            r();
        }
    }

    private void j() {
        HealthToolBar healthToolBar = this.w;
        if (healthToolBar == null) {
            LogUtil.b("Main_BloodSugarActivityPresenter", "mToolBar is null");
            return;
        }
        healthToolBar.setIconVisible(0, 0);
        if (EnvironmentInfo.k()) {
            this.w.setIconVisible(1, 8);
        } else {
            this.w.setIconVisible(1, 0);
        }
        this.w.setIconVisible(4, 8);
    }

    private void y() {
        DataDetailActivityContract.DetailActivityView view = getView();
        if (view == null) {
            return;
        }
        a(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_DETAIL_MEASURE_2030032.value());
        b(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), "6");
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.d());
        intent.setClassName(BaseApplication.d(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("kind", "HDK_BLOOD_SUGAR");
        intent.putExtra("view", "MeasureDevice");
        gnm.aPB_(view.getViewContext(), intent);
    }

    private void r() {
        DataDetailActivityContract.DetailActivityView view = getView();
        if (view == null) {
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(view.getViewContext());
        builder.e(view.getViewContext().getString(R$string.IDS_hw_plugin_device_selection_click_bind_my_device_select)).czE_(view.getViewContext().getString(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: qlf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFy_(view2);
            }
        }).czA_(view.getViewContext().getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: qlj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.dFk_(view2);
            }
        });
        builder.e().show();
    }

    /* synthetic */ void dFy_(View view) {
        v();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dFk_(View view) {
        LogUtil.c("Main_BloodSugarActivityPresenter", "showBindDialog click negative view");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(getView().getViewContext(), str, hashMap, 0);
    }

    private static void b(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", str2);
        ixx.d().d(BaseApplication.e(), str, hashMap, 0);
    }

    private void v() {
        a(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_DETAIL_BIND_2030033.value());
        b(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), "6");
        dks.e(getView().getViewContext(), "HDK_BLOOD_SUGAR");
    }

    public int e(IBaseResponseCallback iBaseResponseCallback, long j) {
        ArrayList<String> a2 = cjx.e().a(HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR);
        int size = !koq.b(a2) ? a2.size() : 0;
        if (iBaseResponseCallback != null) {
            if (size == 0) {
                iBaseResponseCallback.d(-1, Long.valueOf(j));
                j();
            } else {
                iBaseResponseCallback.d(0, Long.valueOf(j));
                LogUtil.c("Main_BloodSugarActivityPresenter", "has Device");
            }
        }
        return size;
    }

    private void a(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Main_BloodSugarActivityPresenter", "getBloodSugar last data timestamp : callback ==null");
            return;
        }
        DataDetailActivityContract.DetailActivityView view = getView();
        if (view == null) {
            return;
        }
        Context viewContext = view.getViewContext();
        if (viewContext instanceof BaseActivity) {
            if (this.k == null) {
                this.k = new LastTimeHealthDataReader((BaseActivity) viewContext, new IBaseResponseCallback() { // from class: qlk
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        qle.this.c(iBaseResponseCallback, i, obj);
                    }
                });
            }
            this.k.b(LastTimeHealthDataReader.CardData.BLOOD_SUGAR);
        }
    }

    /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (obj instanceof HiHealthData) {
            iBaseResponseCallback.d(0, Long.valueOf(((HiHealthData) obj).getStartTime()));
            return;
        }
        LogUtil.a("Main_BloodSugarActivityPresenter", "objData not instanceof HiHealthData");
        e(iBaseResponseCallback, 1L);
        t();
    }

    private boolean l() {
        if (e((IBaseResponseCallback) null, 0L) > 0) {
            LogUtil.a("Main_BloodSugarActivityPresenter", "has device");
            return false;
        }
        if (sdk.a(Integer.toString(10054), "BLOOD_SUGAR_INPUT_DIALOG", 604800000L)) {
            return true;
        }
        LogUtil.a("Main_BloodSugarActivityPresenter", "not to showBloodSugarInputDialog, cause not over four week");
        return false;
    }

    private void t() {
        if (l()) {
            HealthDataInputDialog.DataSetFormatter dataSetFormatter = new HealthDataInputDialog.DataSetFormatter() { // from class: qly
                @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.DataSetFormatter
                public final String format(int i) {
                    String e2;
                    e2 = UnitUtil.e(i, 1, 0);
                    return e2;
                }
            };
            HealthDataInputDialog.SelectedValueProcessor selectedValueProcessor = new HealthDataInputDialog.SelectedValueProcessor() { // from class: qle.4
                @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
                public int process(int i, int i2) {
                    return i;
                }

                @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
                public String format(int i) {
                    return UnitUtil.e((i * 0.1f) + 1.0f, 1, 1);
                }
            };
            final Context viewContext = getView().getViewContext();
            HealthDataInputDialog.e d2 = new HealthDataInputDialog.e(1, 33, dataSetFormatter, selectedValueProcessor).c(40).a(viewContext.getResources().getString(R$string.IDS_hw_health_show_healthdata_bloodsugar_mmol)).d(1);
            int color = ContextCompat.getColor(viewContext, R.color._2131296540_res_0x7f09011c);
            final HealthDataInputDialog czn_ = new HealthDataInputDialog(viewContext).c(color).e(viewContext.getResources().getString(R$string.IDS_blood_sugar_guide_title)).a(viewContext.getResources().getString(R$string.IDS_blood_sugar_guide_desc)).d(d2).czn_(dFh_(viewContext));
            czn_.c(new HealthDataInputDialog.PositiveBtnClickListener() { // from class: qmb
                @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.PositiveBtnClickListener
                public final void onClick(List list) {
                    qle.this.d(viewContext, czn_, list);
                }
            });
            czn_.czq_(new View.OnClickListener() { // from class: qmd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    qle.this.dFB_(czn_, viewContext, view);
                }
            });
            czn_.show();
            a(viewContext, 0);
        }
    }

    /* synthetic */ void d(Context context, HealthDataInputDialog healthDataInputDialog, List list) {
        if (this.p > System.currentTimeMillis()) {
            nrh.d(context, context.getResources().getString(R$string.IDS_hw_health_show_healthdata_timeerror));
            return;
        }
        this.g = (((Integer) list.get(0)).intValue() * 0.1d) + 1.0d;
        login("BLOOD_SUGAR_INPUT_DIALOG");
        healthDataInputDialog.dismiss();
        a(context, 2);
    }

    /* synthetic */ void dFB_(HealthDataInputDialog healthDataInputDialog, Context context, View view) {
        healthDataInputDialog.dismiss();
        a(context, 1);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(Context context, int i) {
        String value = AnalyticsValue.HEALTH_HOME_BLOOD_SUGAR_SHOW_NO_DATA_VIEW_2210002.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(context, value, hashMap, 0);
    }

    private View dFh_(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.blood_sugar_dialog_time_item, (ViewGroup) null);
        inflate.findViewById(R.id.time_layout).setOnClickListener(new View.OnClickListener() { // from class: qle.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                qle.this.q();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        long timeInMillis = calendar.getTimeInMillis();
        this.p = timeInMillis;
        this.t = scj.e(timeInMillis);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_show_health_data_inputbloodsugar_top_time);
        this.q = healthTextView;
        healthTextView.setText(nsj.c(context, this.p, 1));
        BloodSugarTimePeriodView bloodSugarTimePeriodView = (BloodSugarTimePeriodView) inflate.findViewById(R.id.hw_health_input_bloodsugar_time_period);
        this.s = bloodSugarTimePeriodView;
        bloodSugarTimePeriodView.a();
        this.s.setOnTimePeriodItemChangedListener(new BloodSugarTimePeriodView.OnTimePeriodItemChangedListener() { // from class: qle.3
            @Override // com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView.OnTimePeriodItemChangedListener
            public void onTimePeriodItemChanged(int i, BloodSugarTimePeriod bloodSugarTimePeriod) {
                qle.this.t = bloodSugarTimePeriod.getCode();
            }
        });
        this.s.d(this.p, this.t);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        final Context viewContext = getView().getViewContext();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog((Activity) viewContext, new HealthTimePickerDialog.TimeSelectedListener() { // from class: qle.2
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i, int i2) {
                calendar.set(11, i);
                calendar.set(12, i2);
                qle.this.q.setText(nsj.c(viewContext, calendar.getTimeInMillis(), 1));
                qle.this.s.d(calendar.getTimeInMillis(), scj.e(qle.this.p));
                qle.this.p = calendar.getTimeInMillis();
            }
        });
        healthTimePickerDialog.e(0, 0, 0, calendar.get(11), calendar.get(12));
        healthTimePickerDialog.b(viewContext.getString(R$string.IDS_hw_health_show_healthdata_measure_time));
        healthTimePickerDialog.show();
    }

    private void g() {
        long j = this.p;
        qkh.c().d(BaseApplication.e(), new long[]{j, j}, this.t, this.g, new IBaseResponseCallback() { // from class: qle.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("Main_BloodSugarActivityPresenter", "insertBloodSugarData errorCode=", Integer.valueOf(i), ", ", obj.toString());
                if (i == 0) {
                    qle.this.m();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("recordType", 2);
        hashMap.put("dataType", Integer.valueOf(scj.e(this.t)));
        hashMap.put("from", 3);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_INPUT_2030034.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public List<Integer> getSubscribeList() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(10);
        arrayList.add(Integer.valueOf(HiSubscribeType.e));
        return arrayList;
    }

    private void e() {
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        e(hashMap, this.h);
        if (getView() != null && getView().getViewContext() != null) {
            ixx.d().d(getView().getViewContext(), value, hashMap, 0);
        }
        b();
    }

    private void b() {
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        this.j = Long.valueOf(System.currentTimeMillis());
        hashMap.put("startTime", this.f);
        hashMap.put("endTime", this.j);
        e(hashMap, this.i);
        if (getView() != null && getView().getViewContext() != null) {
            ixx.d().d(getView().getViewContext(), value, hashMap, 0);
        }
        this.i = this.h;
        this.f = this.j;
    }

    private void e(Map<String, Object> map, int i) {
        if (i == 0) {
            map.put("type", "2");
            return;
        }
        if (i == 1) {
            map.put("type", "3");
        } else if (i == 2) {
            map.put("type", "4");
        } else {
            LogUtil.h("Main_BloodSugarActivityPresenter", "Is not correct currentPage");
        }
    }

    private void u() {
        HealthToolBar healthToolBar = this.w;
        if (healthToolBar == null) {
            LogUtil.b("Main_BloodSugarActivityPresenter", "mToolBar is null");
            return;
        }
        healthToolBar.setIconVisible(0, 0);
        if (EnvironmentInfo.k()) {
            this.w.setIconVisible(1, 8);
        } else {
            this.w.setIconVisible(1, 0);
        }
        this.w.setIconVisible(4, 0);
        this.w.setIconVisible(3, 0);
        rzh.e("tool_bar_top_index_y", Integer.valueOf(dFn_(this.w, true)));
    }

    private void dFi_(View view) {
        view.findViewById(R.id.settings_meal_goto_records).setOnClickListener(new View.OnClickListener() { // from class: qlt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFu_(view2);
            }
        });
        final HealthRadioButton healthRadioButton = (HealthRadioButton) view.findViewById(R.id.settings_meal_breakfast_radio);
        final HealthRadioButton healthRadioButton2 = (HealthRadioButton) view.findViewById(R.id.settings_meal_breakfast_extra_radio);
        final HealthRadioButton healthRadioButton3 = (HealthRadioButton) view.findViewById(R.id.settings_meal_lunch_radio);
        final HealthRadioButton healthRadioButton4 = (HealthRadioButton) view.findViewById(R.id.settings_meal_afternoon_extra_radio);
        final HealthRadioButton healthRadioButton5 = (HealthRadioButton) view.findViewById(R.id.settings_meal_dinner_radio);
        final HealthRadioButton healthRadioButton6 = (HealthRadioButton) view.findViewById(R.id.settings_meal_dinner_extra_radio);
        this.m = new HealthRadioButton[]{healthRadioButton, healthRadioButton2, healthRadioButton3, healthRadioButton4, healthRadioButton5, healthRadioButton6};
        this.l = 10;
        healthRadioButton.setChecked(true);
        view.findViewById(R.id.settings_meal_breakfast).setOnClickListener(new View.OnClickListener() { // from class: qll
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFv_(healthRadioButton, view2);
            }
        });
        view.findViewById(R.id.settings_meal_breakfast_extra).setOnClickListener(new View.OnClickListener() { // from class: qlv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFp_(healthRadioButton2, view2);
            }
        });
        view.findViewById(R.id.settings_meal_lunch).setOnClickListener(new View.OnClickListener() { // from class: qlw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFq_(healthRadioButton3, view2);
            }
        });
        view.findViewById(R.id.settings_meal_afternoon_extra).setOnClickListener(new View.OnClickListener() { // from class: qma
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFr_(healthRadioButton4, view2);
            }
        });
        view.findViewById(R.id.settings_meal_dinner).setOnClickListener(new View.OnClickListener() { // from class: qlx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFs_(healthRadioButton5, view2);
            }
        });
        view.findViewById(R.id.settings_meal_dinner_extra).setOnClickListener(new View.OnClickListener() { // from class: qlz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFt_(healthRadioButton6, view2);
            }
        });
    }

    /* synthetic */ void dFu_(View view) {
        e((String) null);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dFv_(HealthRadioButton healthRadioButton, View view) {
        this.l = 10;
        c(healthRadioButton);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dFp_(HealthRadioButton healthRadioButton, View view) {
        this.l = 11;
        c(healthRadioButton);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dFq_(HealthRadioButton healthRadioButton, View view) {
        this.l = 20;
        c(healthRadioButton);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dFr_(HealthRadioButton healthRadioButton, View view) {
        this.l = 21;
        c(healthRadioButton);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dFs_(HealthRadioButton healthRadioButton, View view) {
        this.l = 30;
        c(healthRadioButton);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dFt_(HealthRadioButton healthRadioButton, View view) {
        this.l = 31;
        c(healthRadioButton);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(HealthRadioButton healthRadioButton) {
        HealthRadioButton[] healthRadioButtonArr = this.m;
        int length = healthRadioButtonArr.length;
        for (int i = 0; i < length; i++) {
            HealthRadioButton healthRadioButton2 = healthRadioButtonArr[i];
            healthRadioButton2.setChecked(healthRadioButton2 == healthRadioButton);
        }
    }

    private boolean o() {
        int i = this.l;
        return i == 11 || i == 21 || i == 31;
    }

    private void p() {
        LogUtil.a("Main_BloodSugarActivityPresenter", "showMealPickerDialog()");
        DataDetailActivityContract.DetailActivityView view = getView();
        if (view == null) {
            return;
        }
        Context viewContext = view.getViewContext();
        Object systemService = viewContext.getSystemService("layout_inflater");
        if (!(systemService instanceof LayoutInflater)) {
            LogUtil.a("Main_BloodSugarActivityPresenter", "showMealPickerDialog() object is invalid type");
            return;
        }
        View inflate = ((LayoutInflater) systemService).inflate(R.layout.hw_show_settings_meal_view, (ViewGroup) null);
        if (inflate == null) {
            LogUtil.c("Main_BloodSugarActivityPresenter", "showMealPickerDialog() view is null");
            return;
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(viewContext);
        builder.a(viewContext.getString(R$string.IDS_bsdiet_choose_meal)).czg_(inflate).cze_(R$string.IDS_device_show_next, new View.OnClickListener() { // from class: qls
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                qle.this.dFz_(view2);
            }
        }).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: qlu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        CustomViewDialog e2 = builder.e();
        dFi_(inflate);
        e2.show();
    }

    /* synthetic */ void dFz_(View view) {
        if (o()) {
            d(0L);
        } else {
            i();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i() {
        this.n = new d(this);
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        ReleaseLogUtil.b("Main_BloodSugarActivityPresenter", "getDietTime dayFormat ", Integer.valueOf(b2));
        qvz.d(b2, b2, new e(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<quh> list) {
        long j = 0;
        if (koq.b(list)) {
            a(0L);
            return;
        }
        quh quhVar = list.get(0);
        if (quhVar == null) {
            a(0L);
            return;
        }
        List<qul> a2 = quhVar.a();
        if (!koq.b(a2)) {
            for (qul qulVar : a2) {
                int h = qulVar.h();
                LogUtil.a("Main_BloodSugarActivityPresenter", "queryTimeDietRecord meals is ", Integer.valueOf(a2.size()), " timeType is ", Integer.valueOf(this.l), " whichMeal is ", Integer.valueOf(h));
                if (this.l == h) {
                    j = qulVar.g();
                }
            }
        }
        a(j);
    }

    static final class e implements ResponseCallback<List<quh>> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<qle> f16472a;

        e(qle qleVar) {
            this.f16472a = new WeakReference<>(qleVar);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            LogUtil.a("Main_BloodSugarActivityPresenter", "GetDietRecordDataCallback errorCode ", Integer.valueOf(i), " list ", list);
            qle qleVar = this.f16472a.get();
            if (qleVar != null) {
                qleVar.b(list);
            } else {
                LogUtil.h("Main_BloodSugarActivityPresenter", "GetDietRecordDataCallback bloodSugarActivityPresenter is null");
            }
        }
    }

    private void a(long j) {
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = Long.valueOf(j);
        this.n.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final long j) {
        final Context viewContext = getView().getViewContext();
        LogUtil.a("Main_BloodSugarActivityPresenter", "showMealTimeDialog()");
        if (!(viewContext.getSystemService("layout_inflater") instanceof LayoutInflater) || !(viewContext instanceof Activity)) {
            LogUtil.a("Main_BloodSugarActivityPresenter", "showMealTimeDialog ", "object and context is invalid type");
            return;
        }
        final Calendar calendar = Calendar.getInstance();
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog((Activity) viewContext, new HealthTimePickerDialog.TimeSelectedListener() { // from class: qlp
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public final void onTimeSelected(int i, int i2) {
                qle.this.c(calendar, j, viewContext, i, i2);
            }
        });
        healthTimePickerDialog.b(viewContext.getString(R$string.IDS_bsdiet_meal_start_time, h()));
        healthTimePickerDialog.show();
    }

    /* synthetic */ void c(Calendar calendar, long j, Context context, int i, int i2) {
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        long timeInMillis = calendar.getTimeInMillis();
        LogUtil.c("Main_BloodSugarActivityPresenter", "showMealTimeDialog(), calendarTime = ", Long.valueOf(timeInMillis));
        String a2 = UnitUtil.a("HH:mm", 1000 * j);
        String a3 = UnitUtil.a("HH:mm", timeInMillis);
        if (System.currentTimeMillis() < timeInMillis) {
            nrh.d(context, context.getResources().getString(R$string.IDS_hw_health_show_healthdata_timeerror));
            d(j);
        } else if (a2.equals(a3) || j == 0 || o()) {
            e(timeInMillis);
        } else {
            d(a2, a3, timeInMillis);
        }
    }

    private void d(String str, String str2, final long j) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getView().getViewContext());
        builder.e(getView().getViewContext().getString(R$string.IDS_bsdiet_meal_change_time, h(), str, str2)).czE_(getView().getViewContext().getString(R$string.IDS_hw_common_ui_dialog_confirm), new View.OnClickListener() { // from class: qlo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qle.this.dFA_(j, view);
            }
        }).czA_(getView().getViewContext().getString(R$string.IDS_hw_common_ui_dialog_cancel), new View.OnClickListener() { // from class: qlq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qle.dFm_(view);
            }
        });
        builder.e().show();
    }

    /* synthetic */ void dFA_(long j, View view) {
        e(j);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dFm_(View view) {
        LogUtil.c("Main_BloodSugarActivityPresenter", "showMealWarnDialog click negative view");
        ViewClickInstrumentation.clickOnView(view);
    }

    private String h() {
        Context viewContext = getView().getViewContext();
        int i = this.l;
        if (i == 10) {
            return viewContext.getString(R$string.IDS_bsdiet_breakfast);
        }
        if (i == 11) {
            return viewContext.getString(R$string.IDS_bsdiet_breakfast_extra);
        }
        if (i == 20) {
            return viewContext.getString(R$string.IDS_bsdiet_lunch);
        }
        if (i == 21) {
            return viewContext.getString(R$string.IDS_bsdiet_afternoon_extra);
        }
        if (i == 30) {
            return viewContext.getString(R$string.IDS_bsdiet_dinner);
        }
        if (i == 31) {
            return viewContext.getString(R$string.IDS_bsdiet_dinner_extra);
        }
        LogUtil.h("Main_BloodSugarActivityPresenter", "getTimeDialogTitle mMealType is wrong: ", Integer.valueOf(i));
        return "";
    }

    private void e(long j) {
        long j2 = j / 1000;
        String str = "#/record_breakfast?whichMeal=" + this.l + "&whichMealLabel=" + h() + "&date=" + (jdl.t(j) / 1000) + "&setTime=" + j2;
        LogUtil.a("Main_BloodSugarActivityPresenter", "jumpDietDetails uri = ", str);
        e(str);
    }

    private void e(String str) {
        DataDetailActivityContract.DetailActivityView view = getView();
        if (view == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", 0);
        hashMap.put("click", "1");
        gge.e(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_FEED_BACK_2040134.value(), hashMap);
        DietDiaryRepository.jumpDietDetails(view.getViewContext(), str);
    }

    public int dFn_(View view, boolean z) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (z) {
            return iArr[1];
        }
        return view.getHeight() + iArr[1];
    }

    public static void d() {
        b(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), "7");
        H5proUtil.initH5pro();
        H5ProLaunchOption h5ProLaunchOption = new H5ProLaunchOption();
        e = h5ProLaunchOption;
        h5ProLaunchOption.addCustomizeArg(HwPayConstant.KEY_USER_NAME, f16467a);
        e.addCustomizeArg("startTime", String.valueOf(d));
        e.addCustomizeArg("endTime", String.valueOf(b));
        e.addCustomizeArg("language", c);
        H5ProClient.startH5MiniProgram(BaseApplication.e(), "com.huawei.healthsport.h5-bloodsugar-export", e);
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter, com.huawei.ui.main.stories.template.BasePresenter
    public void detachView() {
        this.w = null;
        super.detachView();
        pyw.e().d();
    }

    private boolean n() {
        return "BLOOD_SUGAR_CONTINUE".equals((String) rzh.d("blood_sugar_data_type", String.class));
    }

    public void d(int i) {
        if (this.r == null) {
            LogUtil.h("Main_BloodSugarActivityPresenter", "handleDietTimeRemindShow mRemindLayout is null");
            return;
        }
        if (qqu.e(getView().getViewContext()) && i == 0 && n() && this.o > 0) {
            this.r.setVisibility(0);
        } else {
            this.r.setVisibility(8);
        }
    }

    public void dFo_(View view) {
        LogUtil.a("Main_BloodSugarActivityPresenter", "handleDietTimeRemind");
        if (!qqu.e(getView().getViewContext())) {
            LogUtil.a("Main_BloodSugarActivityPresenter", "handleDietTimeRemind showRemindTimeWindow() is false!");
            return;
        }
        if (!(view instanceof LinearLayout)) {
            LogUtil.a("Main_BloodSugarActivityPresenter", "handleDietTimeRemind view is wrong!");
            return;
        }
        this.r = (LinearLayout) view;
        this.n = new d(this);
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = DateFormatUtil.b(currentTimeMillis);
        int b3 = DateFormatUtil.b(jdl.d(currentTimeMillis, -30));
        ReleaseLogUtil.b("Main_BloodSugarActivityPresenter", "handleDietTimeRemind startDate ", Integer.valueOf(b3), " endDate ", Integer.valueOf(b2));
        qvz.d(b3, b2, new c(this));
    }

    static class c implements ResponseCallback<List<quh>> {
        private final WeakReference<qle> d;

        c(qle qleVar) {
            this.d = new WeakReference<>(qleVar);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            LogUtil.a("Main_BloodSugarActivityPresenter", "InnerViewDietDiaryCbk errorCode ", Integer.valueOf(i), " list ", list);
            qle qleVar = this.d.get();
            if (qleVar == null) {
                ReleaseLogUtil.a("Main_BloodSugarActivityPresenter", "InnerViewDietDiaryCbk activity is null");
                return;
            }
            if (koq.b(list)) {
                qleVar.a(0);
                return;
            }
            int i2 = 0;
            for (quh quhVar : list) {
                if (quhVar == null) {
                    qleVar.a(0);
                    return;
                }
                List<qul> a2 = quhVar.a();
                if (koq.c(a2)) {
                    for (qul qulVar : a2) {
                        if (qulVar != null && !koq.b(qulVar.c()) && qulVar.g() == 0) {
                            i2++;
                        }
                    }
                }
            }
            qleVar.a(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = Integer.valueOf(i);
        this.n.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        HealthTextView healthTextView;
        LogUtil.a("Main_BloodSugarActivityPresenter", "setBloodSugarDietTimeRemind count = ", Integer.valueOf(i));
        rzh.e("blood_sugar_remind_count", Integer.valueOf(i));
        if (i == 0) {
            return;
        }
        this.o = i;
        if (getView() == null || getView().getViewContext() == null) {
            LogUtil.h("Main_BloodSugarActivityPresenter", "setBloodSugarDietTimeRemind getView or getViewContext is null");
            return;
        }
        final Context viewContext = getView().getViewContext();
        String quantityString = viewContext.getResources().getQuantityString(R.plurals._2130903082_res_0x7f03002a, i, Integer.valueOf(i));
        if (nsn.s()) {
            this.r.findViewById(R.id.blood_sugar_diet_set_time_remind_text).setVisibility(8);
            HealthScrollView healthScrollView = (HealthScrollView) this.r.findViewById(R.id.scroll_blood_sugar_diet_set_time);
            healthScrollView.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = healthScrollView.getLayoutParams();
            layoutParams.height = nsn.j() / 6;
            healthScrollView.setLayoutParams(layoutParams);
            healthTextView = (HealthTextView) this.r.findViewById(R.id.blood_sugar_diet_set_time_remind_text_large);
        } else {
            healthTextView = (HealthTextView) this.r.findViewById(R.id.blood_sugar_diet_set_time_remind_text);
        }
        healthTextView.setText(quantityString);
        TextView textView = (TextView) this.r.findViewById(R.id.blood_sugar_diet_set_time_remind_go);
        textView.setAllCaps(true);
        textView.setOnClickListener(new View.OnClickListener() { // from class: qli
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qle.this.dFw_(viewContext, view);
            }
        });
        this.r.findViewById(R.id.blood_sugar_diet_set_time_remind_cancel).setOnClickListener(new View.OnClickListener() { // from class: qlm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qle.this.dFx_(viewContext, view);
            }
        });
        if (n() && this.h == 0) {
            this.r.setVisibility(0);
        } else {
            this.r.setVisibility(8);
            LogUtil.h("Main_BloodSugarActivityPresenter", "setBloodSugarDietTimeRemind count < 0 ");
        }
    }

    /* synthetic */ void dFw_(Context context, View view) {
        qqu.a(context);
        DietDiaryRepository.jumpDietDetails(context, "#/settime_list");
        this.r.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dFx_(Context context, View view) {
        qqu.a(context);
        this.r.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    static class d extends BaseHandler<qle> {
        d(qle qleVar) {
            super(qleVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dFC_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(qle qleVar, Message message) {
            if (message.what == 0) {
                qleVar.d(message.obj instanceof Long ? ((Long) message.obj).longValue() : 0L);
            }
            if (message.what == 1) {
                qleVar.b(message.obj instanceof Integer ? ((Integer) message.obj).intValue() : 0);
            }
        }
    }
}
