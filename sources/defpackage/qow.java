package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InputTemperatureActivity;
import com.huawei.ui.main.stories.health.temperature.activity.TemperatureIntroduceActivity;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.template.BaseActivity;
import com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class qow extends HealthDetailCommonActivityPresenter {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f16528a;
    private boolean b;
    private WeakReference<CustomTitleBar> c;
    private LastTimeHealthDataReader<BaseActivity> d;
    private Context e;
    private HealthToolBar j;

    public qow() {
        super("TemperatureActivityPresenter");
        boolean o = Utils.o();
        this.f16528a = o;
        this.mNeedResolution = !o;
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public void initTitleBar(String str, String str2) {
        super.initTitleBar(str, str2);
        if (getView() == null) {
            LogUtil.h("TemperatureActivityPresenter", "initTitleBar getView is null");
            return;
        }
        if (!isViewAttached()) {
            LogUtil.h("TemperatureActivityPresenter", "initTitleBar isViewAttached false");
            return;
        }
        Context viewContext = getView().getViewContext();
        this.e = viewContext;
        if (viewContext == null) {
            this.e = BaseApplication.e();
        }
        if (this.f16528a) {
            getView().setTitle(this.e.getString(R$string.IDS_health_skin_temperature));
        } else {
            getView().setTitle(this.e.getString(R$string.IDS_settings_health_temperature));
        }
        WeakReference<CustomTitleBar> weakReference = new WeakReference<>(getView().getCustomTitleBar());
        this.c = weakReference;
        final CustomTitleBar customTitleBar = weakReference.get();
        if (customTitleBar == null) {
            LogUtil.h("TemperatureActivityPresenter", "initTitleBar customTitleBar is null");
            return;
        }
        customTitleBar.setRightButtonDrawable(ContextCompat.getDrawable(this.e, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: qpd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qow.this.dGS_(customTitleBar, view);
            }
        });
        a();
    }

    /* synthetic */ void dGS_(CustomTitleBar customTitleBar, View view) {
        c(customTitleBar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        Context context = this.e;
        if (context instanceof HealthDataDetailActivity) {
            if (((HealthDataDetailActivity) context).c() > 0) {
                a(0);
                this.b = false;
            } else {
                a(8);
            }
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public void initToolBar() {
        if (getView() == null) {
            LogUtil.h("TemperatureActivityPresenter", "initToolBar getView is null");
            return;
        }
        if (!this.f16528a && this.j == null) {
            this.j = getView().getHealthToolBar();
            View inflate = View.inflate(this.e, R.layout.hw_toolbar_bottomview, null);
            inflate.setBackgroundColor(ContextCompat.getColor(this.e, R.color._2131297799_res_0x7f090607));
            this.j.cHc_(inflate);
            this.j.setIcon(1, dGM_());
            HealthToolBar healthToolBar = this.j;
            healthToolBar.setIconTitle(1, healthToolBar.getResources().getString(R$string.IDS_hw_health_show_healthdata_input));
            this.j.setIcon(3, R.mipmap._2131821130_res_0x7f11024a);
            HealthToolBar healthToolBar2 = this.j;
            healthToolBar2.setIconTitle(3, healthToolBar2.getResources().getString(R$string.IDS_device_group_name_temperature_scale));
            if (EnvironmentInfo.k()) {
                this.j.setIconVisible(3, 8);
            }
            this.j.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: qoz
                @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
                public final void onSingleTap(int i) {
                    qow.this.b(i);
                }
            });
        }
    }

    /* synthetic */ void b(int i) {
        if (i == 1) {
            login(String.valueOf(1));
        } else if (i == 3) {
            login(String.valueOf(3));
        } else {
            LogUtil.h("TemperatureActivityPresenter", "unKnow click");
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public void onLogined(String str) {
        if (String.valueOf(1).equals(str)) {
            c();
        } else if (String.valueOf(3).equals(str)) {
            j();
        } else {
            LogUtil.h("TemperatureActivityPresenter", "unknow position=", str);
        }
    }

    private void c(CustomTitleBar customTitleBar) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.temperature_toolbar_popupwindow, (ViewGroup) null);
        final nqc nqcVar = new nqc(this.e, inflate);
        nqcVar.cEh_(customTitleBar, 17);
        inflate.findViewById(R.id.temperature_declare_text).setOnClickListener(new View.OnClickListener() { // from class: qow.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nqcVar.b();
                qow.this.d(1);
                gnm.aPB_(qow.this.e, new Intent(qow.this.e, (Class<?>) TemperatureIntroduceActivity.class));
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        inflate.findViewById(R.id.temperature_data_text).setOnClickListener(new View.OnClickListener() { // from class: qow.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nqcVar.b();
                qow.this.d(0);
                PageModelArgs pageModelArgs = new PageModelArgs(qow.this.f16528a ? 113 : 106, "PrivacyDataConstructor", 3, 1);
                pageModelArgs.setClassType(0);
                PrivacyDataModelActivity.b(qow.this.e, pageModelArgs);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        dGQ_(inflate);
    }

    private void dGQ_(View view) {
        if (this.b) {
            view.findViewById(R.id.temperature_data_text).setVisibility(8);
            view.findViewById(R.id.divider_line).setVisibility(8);
        } else {
            view.findViewById(R.id.temperature_data_text).setVisibility(0);
            view.findViewById(R.id.divider_line).setVisibility(0);
        }
    }

    private Drawable dGM_() {
        Drawable drawable = ContextCompat.getDrawable(this.e, R.drawable._2131430283_res_0x7f0b0b8b);
        return LanguageUtil.bc(this.e) ? nrz.cKm_(this.e, drawable) : drawable;
    }

    private void c() {
        if (nsn.a(500)) {
            return;
        }
        String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_INPUT_2060049.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(this.e, value, hashMap, 0);
        Intent intent = new Intent(this.e, (Class<?>) InputTemperatureActivity.class);
        intent.putExtra("isShowInput", true);
        gnm.aPB_(this.e, intent);
    }

    private void j() {
        if (nsn.a(500)) {
            return;
        }
        if (e() > 0) {
            d();
        } else {
            f();
        }
    }

    private void d() {
        final Intent intent = new Intent();
        intent.setPackage(BaseApplication.d());
        intent.setClassName(BaseApplication.d(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("kind", "HDK_BODY_TEMPERATURE");
        final BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 12) {
            dGP_(intent);
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
        builder.e(R$string.IDS_device_bluetooth_open_request);
        builder.czC_(R$string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: qoy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qow.this.dGR_(defaultAdapter, intent, view);
            }
        });
        builder.czz_(R$string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: qpb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* synthetic */ void dGR_(BluetoothAdapter bluetoothAdapter, Intent intent, View view) {
        if (bluetoothAdapter != null) {
            try {
                if (bluetoothAdapter.enable()) {
                    dGP_(intent);
                }
            } catch (SecurityException unused) {
                nrh.b(this.e, R$string.IDS_nearby_permission_exception);
                ReleaseLogUtil.c("TemperatureActivityPresenter", "gotoMeasure SecurityException");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private int e() {
        ArrayList<String> a2 = cjx.e().a(HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE);
        if (koq.b(a2)) {
            return 0;
        }
        return a2.size();
    }

    private void dGP_(Intent intent) {
        String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_DETAIL_MEASURE_2060047.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(this.e, value, hashMap, 0);
        intent.putExtra("view", "MeasureDevice");
        gnm.aPB_(this.e, intent);
    }

    private void f() {
        String string = this.e.getString(R$string.IDS_hw_temperature_no_device_tip, 1, 2);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.e);
        builder.e(R$string.IDS_hw_temperature_no_device_title).c(string).cyo_(R$string.IDS_hw_temperature_no_device_bind, new DialogInterface.OnClickListener() { // from class: qpi
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                qow.this.dGT_(dialogInterface, i);
            }
        }).cyn_(R$string.IDS_hw_health_show_common_dialog_cancle_button, new DialogInterface.OnClickListener() { // from class: qpe
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                qow.dGO_(dialogInterface, i);
            }
        });
        builder.c().show();
    }

    /* synthetic */ void dGT_(DialogInterface dialogInterface, int i) {
        String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_DETAIL_BIND_2060048.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(this.e, value, hashMap, 0);
        i();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    static /* synthetic */ void dGO_(DialogInterface dialogInterface, int i) {
        LogUtil.a("TemperatureActivityPresenter", "onClick negative button");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void i() {
        dks.e(getView().getViewContext(), "HDK_BODY_TEMPERATURE");
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public void getLastDataTimestamp(IBaseResponseCallback iBaseResponseCallback) {
        b(iBaseResponseCallback);
    }

    private void b(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("TemperatureActivityPresenter", "readLastTimeForDataPlatform callback is null");
        } else if (this.e instanceof BaseActivity) {
            if (this.d == null) {
                this.d = new LastTimeHealthDataReader<>((BaseActivity) this.e, new IBaseResponseCallback() { // from class: qpc
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        qow.this.c(iBaseResponseCallback, i, obj);
                    }
                });
            }
            this.d.b(LastTimeHealthDataReader.CardData.TEMPERATURE);
        }
    }

    /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (obj instanceof HiHealthData) {
            this.b = false;
            a(0);
            HiHealthData hiHealthData = (HiHealthData) obj;
            iBaseResponseCallback.d(0, Long.valueOf(hiHealthData.getStartTime()));
            LogUtil.a("TemperatureActivityPresenter", "readLastTimeForDataPlatform lastTime ", Long.valueOf(hiHealthData.getStartTime()));
            return;
        }
        if (this.f16528a) {
            a(0);
            iBaseResponseCallback.d(0, Long.valueOf(System.currentTimeMillis()));
        } else {
            c(iBaseResponseCallback);
        }
    }

    private void a(final int i) {
        HandlerExecutor.e(new Runnable() { // from class: qpa
            @Override // java.lang.Runnable
            public final void run() {
                qow.this.c(i);
            }
        });
    }

    /* synthetic */ void c(int i) {
        CustomTitleBar customTitleBar = this.c.get();
        if (customTitleBar == null) {
            LogUtil.h("TemperatureActivityPresenter", "showTitleBar customTitleBar is null");
        } else {
            customTitleBar.setRightButtonVisibility(i);
        }
    }

    private void c(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        int e = e();
        boolean c = jll.c();
        LogUtil.a("TemperatureActivityPresenter", "showByBonded device number ", Integer.valueOf(e), " isSupported ", Boolean.valueOf(c));
        if (e > 0 || c) {
            a(0);
            this.b = true;
            iBaseResponseCallback.d(2, 1L);
        } else {
            a(8);
            this.b = false;
            iBaseResponseCallback.d(-1, 1L);
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter
    public List<Integer> getSubscribeList() {
        ArrayList arrayList = new ArrayList(4);
        qpk d = qpk.d();
        arrayList.add(Integer.valueOf(d.g()));
        arrayList.add(Integer.valueOf(d.m()));
        arrayList.add(Integer.valueOf(d.a()));
        arrayList.add(Integer.valueOf(d.e()));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        gge.e(AnalyticsValue.TEMPERATURE_MORE_2060079.value(), hashMap);
    }

    @Override // com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter, com.huawei.ui.main.stories.template.BasePresenter
    public void detachView() {
        super.detachView();
        this.j = null;
        this.e = null;
    }
}
