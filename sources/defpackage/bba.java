package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class bba {

    /* renamed from: a, reason: collision with root package name */
    private CustomAlertDialog f309a;
    private Context b;
    private CustomAlertDialog.Builder d;
    private HealthTextView e;
    private long f;
    private boolean g;
    private boolean h;
    private long i;
    private ResponseCallback<HealthLifeTaskBean> j;
    private HealthTextView k;
    private Calendar l;
    private ConstraintLayout m;
    private HealthMultiNumberPicker n;
    private int[] o;
    private HealthLifeTaskBean p;
    private View r;
    private Calendar s;
    private HealthTextView t;
    private int[] u;
    private ConstraintLayout v;
    private HealthTextView w;
    private HealthTextView x;
    private HealthMultiNumberPicker y;
    private final String q = azi.p();
    private final e c = new e(this);

    public bba(Context context, HealthLifeTaskBean healthLifeTaskBean, ResponseCallback<HealthLifeTaskBean> responseCallback) {
        this.b = context;
        this.p = healthLifeTaskBean;
        this.j = responseCallback;
    }

    public void d() {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.manual_input_sleep_data_layout, (ViewGroup) null);
        this.r = inflate;
        this.n = (HealthMultiNumberPicker) inflate.findViewById(R.id.sleep_time_picker);
        this.y = (HealthMultiNumberPicker) this.r.findViewById(R.id.wakeup_time_picker);
        this.k = (HealthTextView) this.r.findViewById(R.id.goToBedTime);
        this.t = (HealthTextView) this.r.findViewById(R.id.goToBedTimeValue);
        this.w = (HealthTextView) this.r.findViewById(R.id.wakeupTime);
        this.x = (HealthTextView) this.r.findViewById(R.id.wakeupTimeValue);
        this.e = (HealthTextView) this.r.findViewById(R.id.input_error_tip);
        this.m = (ConstraintLayout) this.r.findViewById(R.id.goToBed);
        this.v = (ConstraintLayout) this.r.findViewById(R.id.wakeup);
        this.h = nsn.v(this.b);
        a();
        o();
        f();
    }

    private void a() {
        ImageView imageView = (ImageView) this.r.findViewById(R.id.right_icon);
        if (this.h) {
            imageView.setImageDrawable(nrz.cKl_(this.b, R$drawable.ic_right_grey_update_dark));
        } else {
            imageView.setImageDrawable(nrz.cKl_(this.b, R.drawable._2131430339_res_0x7f0b0bc3));
            this.k.setTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
            this.t.setTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: bbd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bba.this.mA_(view);
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() { // from class: bbc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bba.this.mB_(view);
            }
        });
        this.v.setOnClickListener(new View.OnClickListener() { // from class: bbj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bba.this.mC_(view);
            }
        });
        b(true);
        HealthTextView healthTextView = (HealthTextView) this.r.findViewById(R.id.inputDetail);
        jcf.bEA_(healthTextView, nsf.h(R$string.IDS_detail_input), Button.class);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: bbh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bba.this.mD_(view);
            }
        });
    }

    /* synthetic */ void mA_(View view) {
        LogUtil.a("HealthLife_InputSleepDataHelper", "go to target manager activity.");
        AppRouter.b("/PluginHealthModel/HealthModelGoalManagerActivity").c(this.b);
        CustomAlertDialog customAlertDialog = this.f309a;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void mB_(View view) {
        this.n.setVisibility(0);
        this.y.setVisibility(8);
        h();
        m();
        b(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void mC_(View view) {
        this.g = true;
        this.x.setText(this.b.getString(R$string.IDS_today_time, UnitUtil.c(this.s, 129)));
        e();
        this.n.setVisibility(8);
        this.y.setVisibility(0);
        l();
        k();
        b(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void mD_(View view) {
        LogUtil.a("HealthLife_InputSleepDataHelper", "jumpToDetailInputSleepH5.");
        azi.a(this.b, this.l.getTimeInMillis(), this.s.getTimeInMillis());
        CustomAlertDialog customAlertDialog = this.f309a;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(boolean z) {
        jcf.bEI_(this.m, z);
        jcf.bEI_(this.v, !z);
    }

    private void o() {
        HealthLifeTaskBean healthLifeTaskBean = this.p;
        String target = (healthLifeTaskBean == null || healthLifeTaskBean.getHealthLifeBean() == null) ? "7:00" : this.p.getHealthLifeBean().getTarget();
        HealthTextView healthTextView = (HealthTextView) this.r.findViewById(R.id.currentTargetValue);
        healthTextView.setText(UnitUtil.c(a(0, target), 65));
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: bbf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bba.this.mF_(view);
            }
        });
        HealthLifeBean b2 = aus.b(this.q, 6, DateFormatUtil.b(jdl.y(System.currentTimeMillis())));
        if (TextUtils.isEmpty(b2.getTarget())) {
            LogUtil.a("HealthLife_InputSleepDataHelper", "wakeupBean == null");
        } else {
            LogUtil.a("HealthLife_InputSleepDataHelper", "wakeupBean target:", b2.getTarget());
            if (!b2.getTarget().equals(target)) {
                HealthTextView healthTextView2 = (HealthTextView) this.r.findViewById(R.id.targetTips2);
                healthTextView2.setText(this.b.getString(R$string.IDS_change_target_tip, UnitUtil.c(a(0, b2.getTarget()), 65)));
                healthTextView2.setVisibility(0);
            }
        }
        ((HealthTextView) this.r.findViewById(R.id.targetTips1)).setText(this.b.getString(R$string.IDS_wakeup_task_tip, 1));
    }

    /* synthetic */ void mF_(View view) {
        LogUtil.a("HealthLife_InputSleepDataHelper", "go to target manager activity.");
        AppRouter.b("/PluginHealthModel/HealthModelGoalManagerActivity").c(this.b);
        CustomAlertDialog customAlertDialog = this.f309a;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        String target;
        HealthLifeTaskBean healthLifeTaskBean = this.p;
        String target2 = (healthLifeTaskBean == null || healthLifeTaskBean.getHealthLifeBean() == null) ? "7:00" : this.p.getHealthLifeBean().getTarget();
        long j = this.f;
        if (j == 0) {
            this.s = a(0, target2);
        } else {
            this.s = b(0, j);
        }
        if (this.s.getTimeInMillis() > System.currentTimeMillis()) {
            Calendar calendar = Calendar.getInstance();
            this.s = calendar;
            calendar.set(13, 0);
            this.s.set(14, 0);
        }
        HealthLifeBean b2 = aus.b(this.q, 7, DateFormatUtil.b(jdl.v(System.currentTimeMillis())));
        if (TextUtils.isEmpty(b2.getTarget())) {
            target = "23:00";
        } else {
            LogUtil.a("HealthLife_InputSleepDataHelper", "sleepBean target:", b2.getTarget(), " result:", b2.getResult());
            target = b2.getTarget();
        }
        if (this.i == 0) {
            this.l = a(-1, target);
        } else {
            this.l = b(DateFormatUtil.b(this.f) == DateFormatUtil.b(this.i) ? 0 : -1, this.i);
        }
        if (gib.j(this.l.getTimeInMillis()).equals(gib.j(System.currentTimeMillis()))) {
            this.t.setText(this.b.getResources().getString(R$string.IDS_today_time, UnitUtil.c(this.l, 129)));
        } else {
            this.t.setText(UnitUtil.c(this.l, 153));
        }
        LogUtil.a("HealthLife_InputSleepDataHelper", "mSleepTime:", this.l.getTime().toString(), " mWakeUpTime:", this.s.getTime().toString());
        int[] iArr = new int[3];
        this.o = iArr;
        this.u = new int[3];
        e(this.l, iArr);
        e(this.s, this.u);
        c();
        g();
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.b);
        this.d = builder;
        builder.cyp_(this.r).cyo_(R$string.IDS_settings_button_ok, new DialogInterface.OnClickListener() { // from class: bbk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                bba.this.mE_(dialogInterface, i);
            }
        }).cyn_(R$string.IDS_settings_button_cancal, new DialogInterface.OnClickListener() { // from class: bbg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                bba.mz_(dialogInterface, i);
            }
        });
        this.d.b().setTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
        this.d.d().setTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
        this.d.e(this.b.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446), this.b.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446));
        CharSequence text = this.x.getText();
        LogUtil.a("HealthLife_InputSleepDataHelper", "showDialog charSequence ", text);
        if (TextUtils.isEmpty(text) || "--".contentEquals(text)) {
            a(false, 0.4f);
        }
        CustomAlertDialog c = this.d.c();
        this.f309a = c;
        c.show();
        aza.b();
    }

    /* synthetic */ void mE_(DialogInterface dialogInterface, int i) {
        i();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    static /* synthetic */ void mz_(DialogInterface dialogInterface, int i) {
        LogUtil.a("HealthLife_InputSleepDataHelper", "dialog cancel");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void i() {
        long timeInMillis = this.l.getTimeInMillis();
        long j = timeInMillis - (timeInMillis % 60000);
        long timeInMillis2 = this.s.getTimeInMillis();
        long j2 = timeInMillis2 - (timeInMillis2 % 60000);
        int value = DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value();
        int value2 = DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value();
        int value3 = DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_WAKEUP_TIME.value();
        int value4 = DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME.value();
        ReleaseLogUtil.b("R_HealthLife_InputSleepDataHelper", "insertData startTime ", Long.valueOf(j), " endTime ", Long.valueOf(j2), " typeForBedTime ", Integer.valueOf(value), " typeForRisingTime ", Integer.valueOf(value2), " typeForWakeupTime ", Integer.valueOf(value3), " typeForFallAsleepTime ", Integer.valueOf(value4));
        long currentTimeMillis = System.currentTimeMillis();
        HiHealthData c = c(currentTimeMillis, currentTimeMillis, value, j, "-1");
        HiHealthData c2 = c(currentTimeMillis, currentTimeMillis, value2, j2, "-1");
        HiHealthData c3 = c(currentTimeMillis, currentTimeMillis, value3, j2, "-1");
        HiHealthData c4 = c(currentTimeMillis, currentTimeMillis, value4, j, "-1");
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(c);
        arrayList.add(c2);
        arrayList.add(c3);
        arrayList.add(c4);
        arrayList.addAll(a(j, j2));
        LogUtil.a("HealthLife_InputSleepDataHelper", "insertSleepData list.size(): ", Integer.valueOf(arrayList.size()));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: baz
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                bba.this.d(i, obj);
            }
        });
    }

    /* synthetic */ void d(int i, Object obj) {
        LogUtil.a("HealthLife_InputSleepDataHelper", "insertSleepData onResponse! ", Long.valueOf(System.currentTimeMillis()));
        if (i == 0) {
            LogUtil.a("HealthLife_InputSleepDataHelper", "insertSleepData success");
            ResponseCallback<HealthLifeTaskBean> responseCallback = this.j;
            if (responseCallback != null) {
                responseCallback.onResponse(1, this.p);
            }
            nrh.b(this.b, R$string.IDS_manual_sleep_input_success);
            aza.d(this.s, this.l);
            return;
        }
        LogUtil.h("HealthLife_InputSleepDataHelper", "insertSleepData fail: ", Integer.valueOf(i));
        nrh.b(this.b, R$string.IDS_hwh_show_save_failed);
    }

    private HiHealthData c(long j, long j2, int i, long j3, String str) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setTimeInterval(j, j2);
        hiHealthData.setType(i);
        hiHealthData.setValue(j3);
        hiHealthData.setDeviceUuid(str);
        return hiHealthData;
    }

    private List<HiHealthData> a(long j, long j2) {
        ArrayList arrayList = new ArrayList();
        while (j <= j2 - 60000) {
            HiHealthData hiHealthData = new HiHealthData();
            long j3 = 60000 + j;
            hiHealthData.setTimeInterval(j, j3);
            hiHealthData.setType(22107);
            hiHealthData.setDeviceUuid("-1");
            arrayList.add(hiHealthData);
            j = j3;
        }
        return arrayList;
    }

    private void e(Calendar calendar, int[] iArr) {
        iArr[0] = (calendar.get(6) - Calendar.getInstance().get(6)) + 1;
        iArr[1] = calendar.get(11);
        iArr[2] = calendar.get(12);
    }

    private Calendar b(int i, long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i2 = calendar.get(11);
        int i3 = calendar.get(12);
        LogUtil.a("HealthLife_InputSleepDataHelper", "parseTimeToCalendar hour:", Integer.valueOf(i2), " minute:", Integer.valueOf(i3));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(6, i);
        calendar2.set(11, i2);
        calendar2.set(12, i3);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return calendar2;
    }

    private Calendar a(int i, String str) {
        int h;
        int h2;
        String[] split = str.split(":");
        Calendar calendar = Calendar.getInstance();
        if (split.length != 2) {
            calendar.add(6, i);
            return calendar;
        }
        if (split[0].startsWith("0")) {
            h = CommonUtil.h(split[0].substring(1));
        } else {
            h = CommonUtil.h(split[0]);
        }
        if (split[1].startsWith("0")) {
            h2 = CommonUtil.h(split[1].substring(1));
        } else {
            h2 = CommonUtil.h(split[1]);
        }
        calendar.add(6, i);
        calendar.set(11, h);
        calendar.set(12, h2);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }

    private void c() {
        HashMap hashMap = new HashMap(3);
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -1);
        hashMap.put(0, new String[]{UnitUtil.a(calendar.getTime(), 24), this.b.getResources().getString(R$string.IDS_detail_sleep_bottom_btu_day_txt)});
        hashMap.put(1, this.n.d(0, 23, (String) null));
        hashMap.put(2, this.n.d(0, 59, (String) null));
        this.n.setDataContent(3, hashMap, new boolean[]{true, true, true}, this.o);
        this.n.setSelectorTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
        this.n.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: bbi
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public final void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3) {
                bba.this.c(i, healthMultiNumberPicker, i2, i3);
            }
        });
    }

    /* synthetic */ void c(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3) {
        this.o[i] = i3;
        b(i, i2, i3);
        j();
        e();
    }

    private void g() {
        HashMap hashMap = new HashMap(3);
        hashMap.put(0, new String[]{this.b.getResources().getString(R$string.IDS_detail_sleep_bottom_btu_day_txt)});
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        LogUtil.a("HealthLife_InputSleepDataHelper", "hour:", Integer.valueOf(i), " minute:", Integer.valueOf(i2));
        hashMap.put(1, this.y.d(0, i, (String) null));
        if (i == this.u[1]) {
            hashMap.put(2, this.y.d(0, i2, (String) null));
        } else {
            hashMap.put(2, this.y.d(0, 59, (String) null));
        }
        this.y.setDataContent(3, hashMap, new boolean[]{true, true, true}, this.u);
        this.y.setSelectorTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
        this.y.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: bbe
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public final void onValueChange(int i3, HealthMultiNumberPicker healthMultiNumberPicker, int i4, int i5) {
                bba.this.d(i3, healthMultiNumberPicker, i4, i5);
            }
        });
    }

    /* synthetic */ void d(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3) {
        int[] iArr = this.u;
        iArr[i] = i3;
        if (i == 1) {
            b(i2, iArr, this.y);
        }
        n();
        e();
    }

    private void b(int i, int[] iArr, HealthMultiNumberPicker healthMultiNumberPicker) {
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(11);
        int i3 = calendar.get(12);
        LogUtil.a("HealthLife_InputSleepDataHelper", "hour:", Integer.valueOf(i2), " minute:", Integer.valueOf(i3), " oldVal:", Integer.valueOf(i));
        if (iArr[1] != i2) {
            if (i == i2) {
                healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, 59, (String) null), iArr[2]);
            }
        } else if (iArr[2] >= i3) {
            iArr[2] = i3;
            healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, i3, (String) null), i3);
        } else {
            healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, i3, (String) null), iArr[2]);
        }
    }

    private void b(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        LogUtil.a("HealthLife_InputSleepDataHelper", "hour:", Integer.valueOf(i4), " minute:", Integer.valueOf(i5), " oldVal:", Integer.valueOf(i2));
        if (i == 0) {
            if (i3 == 1) {
                b(i4, i5, this.o, this.n);
            } else {
                HealthMultiNumberPicker healthMultiNumberPicker = this.n;
                healthMultiNumberPicker.setDisplayedValues(1, healthMultiNumberPicker.d(0, 23, (String) null), this.o[1]);
                HealthMultiNumberPicker healthMultiNumberPicker2 = this.n;
                healthMultiNumberPicker2.setDisplayedValues(2, healthMultiNumberPicker2.d(0, 59, (String) null), this.o[2]);
            }
        }
        if (i == 1) {
            b(i2, this.o, this.n);
        }
    }

    private void b(int i, int i2, int[] iArr, HealthMultiNumberPicker healthMultiNumberPicker) {
        if (iArr[1] >= i) {
            iArr[1] = i;
            iArr[2] = i2;
            healthMultiNumberPicker.setDisplayedValues(1, healthMultiNumberPicker.d(0, i, (String) null), i);
            healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, i2, (String) null), i2);
            return;
        }
        iArr[2] = i2;
        healthMultiNumberPicker.setDisplayedValues(1, healthMultiNumberPicker.d(0, i, (String) null), iArr[1]);
        healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, 59, (String) null), iArr[2]);
    }

    private Calendar j() {
        String string;
        Calendar calendar = Calendar.getInstance();
        int[] iArr = this.o;
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        calendar.add(6, i - 1);
        calendar.set(11, i2);
        calendar.set(12, i3);
        calendar.set(13, 0);
        calendar.set(14, 0);
        this.l = calendar;
        if (this.o[0] == 0) {
            string = UnitUtil.a(calendar.getTime(), 145);
        } else {
            string = this.b.getResources().getString(R$string.IDS_today_time, UnitUtil.a(calendar.getTime(), 129));
        }
        this.t.setText(string);
        return calendar;
    }

    private void n() {
        Calendar calendar = Calendar.getInstance();
        int[] iArr = this.u;
        int i = iArr[1];
        int i2 = iArr[2];
        calendar.add(6, 0);
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        calendar.set(14, 0);
        this.s = calendar;
        this.x.setText(this.b.getResources().getString(R$string.IDS_today_time, UnitUtil.a(calendar.getTime(), 129)));
    }

    private void e() {
        long timeInMillis = this.s.getTimeInMillis() / 1000;
        long timeInMillis2 = this.l.getTimeInMillis() / 1000;
        if (timeInMillis <= timeInMillis2 && this.g) {
            d(0, this.b.getString(R$string.IDS_sleep_input_error_tip));
            a(false, 0.4f);
            return;
        }
        if (timeInMillis - timeInMillis2 > k.b.m && this.g) {
            d(0, this.b.getString(R$string.IDS_sleep_duration_exceeds_tip, this.b.getResources().getQuantityString(R.plurals._2130903223_res_0x7f0300b7, 24, UnitUtil.e(24.0d, 1, 0))));
            a(false, 0.4f);
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(13, 59);
        calendar.set(14, 999);
        if (timeInMillis * 1000 >= calendar.getTimeInMillis() || timeInMillis2 * 1000 >= calendar.getTimeInMillis()) {
            d(4, "");
            a(false, 0.4f);
        } else {
            d(4, "");
            a(true, 1.0f);
        }
    }

    private void d(final int i, final String str) {
        HandlerExecutor.d(new Runnable() { // from class: bbl
            @Override // java.lang.Runnable
            public final void run() {
                bba.this.e(i, str);
            }
        }, 300L);
    }

    /* synthetic */ void e(int i, String str) {
        if (i == 0) {
            this.e.setVisibility(i);
            this.e.setText(str);
        } else {
            this.e.setVisibility(4);
        }
    }

    private void a(boolean z, float f) {
        CustomAlertDialog.Builder builder = this.d;
        if (builder == null || builder.b() == null) {
            return;
        }
        this.d.b().setClickable(z);
        this.d.b().setAlpha(f);
    }

    private void h() {
        ((ImageView) this.r.findViewById(R.id.bedIcon)).setBackground(ContextCompat.getDrawable(this.b, R.drawable._2131428295_res_0x7f0b03c7));
        if (!this.h) {
            this.k.setTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
            this.t.setTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
        }
        this.m.setBackgroundResource(R.drawable._2131431429_res_0x7f0b1005);
    }

    private void m() {
        ((ImageView) this.r.findViewById(R.id.wakeupIcon)).setBackground(ContextCompat.getDrawable(this.b, R.drawable._2131432005_res_0x7f0b1245));
        if (!this.h) {
            this.w.setTextColor(this.b.getColor(R$color.item_text_color));
            this.x.setTextColor(this.b.getColor(R$color.item_text_color));
        }
        this.v.setBackgroundResource(R.drawable._2131431430_res_0x7f0b1006);
    }

    private void k() {
        ((ImageView) this.r.findViewById(R.id.wakeupIcon)).setBackground(ContextCompat.getDrawable(this.b, R.drawable._2131432004_res_0x7f0b1244));
        if (!this.h) {
            this.w.setTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
            this.x.setTextColor(this.b.getColor(R.color._2131299091_res_0x7f090b13));
        }
        this.v.setBackgroundResource(R.drawable._2131431429_res_0x7f0b1005);
    }

    private void l() {
        ((ImageView) this.r.findViewById(R.id.bedIcon)).setBackground(ContextCompat.getDrawable(this.b, R.drawable._2131428296_res_0x7f0b03c8));
        if (!this.h) {
            this.k.setTextColor(this.b.getColor(R$color.item_text_color));
            this.t.setTextColor(this.b.getColor(R$color.item_text_color));
        }
        this.m.setBackgroundResource(R.drawable._2131431430_res_0x7f0b1006);
    }

    private void f() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        hiDataReadOption.setTimeInterval(String.valueOf(20140101), String.valueOf(b2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value()});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new b(this));
    }

    static class b implements HiDataReadResultListener {
        private final WeakReference<bba> d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        b(bba bbaVar) {
            this.d = new WeakReference<>(bbaVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            bba bbaVar = this.d.get();
            if (bbaVar == null) {
                ReleaseLogUtil.a("R_HealthLife_InputSleepDataHelper", "InnerResultListener onResult helper is null object ", obj);
                return;
            }
            if (!(obj instanceof SparseArray)) {
                ReleaseLogUtil.a("R_HealthLife_InputSleepDataHelper", "InnerResultListener onResult object ", obj);
                azi.lY_(bbaVar.c, 1);
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            List list = (List) sparseArray.get(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value());
            List list2 = (List) sparseArray.get(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value());
            if (koq.c(list)) {
                HiHealthData hiHealthData = (HiHealthData) list.get(0);
                if (hiHealthData == null) {
                    ReleaseLogUtil.a("R_HealthLife_InputSleepDataHelper", "InnerResultListener onResult bedTimeList data is null");
                } else {
                    long value = (long) hiHealthData.getValue();
                    LogUtil.a("HealthLife_InputSleepDataHelper", "InnerResultListener onResult bedTime ", Long.valueOf(value), " statTime ", Long.valueOf(hiHealthData.getStartTime()));
                    bbaVar.i = value;
                }
            }
            if (koq.c(list2)) {
                HiHealthData hiHealthData2 = (HiHealthData) list2.get(0);
                if (hiHealthData2 == null) {
                    ReleaseLogUtil.a("R_HealthLife_InputSleepDataHelper", "InnerResultListener onResult risingTimeList data is null");
                } else {
                    long value2 = (long) hiHealthData2.getValue();
                    LogUtil.a("HealthLife_InputSleepDataHelper", "InnerResultListener onResult risingTime ", Long.valueOf(value2), " statTime ", Long.valueOf(hiHealthData2.getStartTime()));
                    bbaVar.f = value2;
                }
            }
            azi.lY_(bbaVar.c, 1);
        }
    }

    static class e extends BaseHandler<bba> {
        e(bba bbaVar) {
            super(bbaVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: mG_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(bba bbaVar, Message message) {
            if (message.what == 1) {
                bbaVar.b();
            }
        }
    }
}
