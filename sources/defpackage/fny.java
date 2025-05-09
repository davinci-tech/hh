package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.devicemgr.api.constant.DataCallbackType;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.CoachController;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Classify;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fny;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes4.dex */
public class fny {

    /* renamed from: a, reason: collision with root package name */
    private CourseApi f12579a;
    private String b;
    private Context c;
    private String d = null;
    private String e;
    private WorkoutRecord f;
    private boolean g;
    private boolean h;
    private FitWorkout i;
    private Handler j;

    public fny(Handler handler, Context context) {
        this.j = handler;
        this.c = context;
    }

    public void b() {
        this.e = CommonUtil.u();
        this.f12579a = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        cun.c().registerDataCallback(new d(this), DataCallbackType.EXERCISE_ADVICE_MANAGER, "Suggestion_CourseDetailDeviceHelper");
        cun.c().registerDataCallback(new d(this), DataCallbackType.DEVICE_FONT_MANAGER, "Suggestion_CourseDetailDeviceHelper");
        o();
    }

    public void c(WorkoutRecord workoutRecord) {
        this.f = workoutRecord;
        if (workoutRecord != null) {
            this.b = workoutRecord.acquireWorkoutId();
        }
    }

    public void c(FitWorkout fitWorkout) {
        this.i = fitWorkout;
    }

    public boolean d() {
        return this.g;
    }

    public void d(boolean z) {
        this.h = z;
    }

    public boolean c() {
        return this.h;
    }

    public void b(boolean z) {
        if (this.h) {
            CoachController.d().e(false);
        } else {
            CoachController.d().e(fpq.b() || ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).isDeviceConnected("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c"));
            c(z);
        }
        CoachController.d().c(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT);
        FitWorkout fitWorkout = this.i;
        if (fitWorkout == null || fitWorkout.getPrimaryClassify() == null) {
            return;
        }
        for (Classify classify : this.i.getPrimaryClassify()) {
            if (classify != null && classify.getClassifyId() == 11087) {
                CoachController.d().c(137);
            }
        }
    }

    public void c(boolean z) {
        CoachController.d().a(fpq.c());
        if (this.i == null || this.f == null || !CoachController.d().g()) {
            return;
        }
        try {
            CoachController.d().b(ggn.e(this.i, this.f.acquireRecordType(), z));
        } catch (cbe e) {
            ReleaseLogUtil.c("Suggestion_CourseDetailDeviceHelper", "SportHiWearException ", LogAnonymous.b((Throwable) e));
        }
    }

    public boolean e() {
        if (!cap.a()) {
            return false;
        }
        ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkToGetDeviceSportStatus");
        final b bVar = new b(this, 103, 102);
        cap.b(bVar);
        HandlerExecutor.d(new Runnable() { // from class: fol
            @Override // java.lang.Runnable
            public final void run() {
                fny.this.b(bVar);
            }
        }, 2000L);
        return true;
    }

    /* synthetic */ void b(b bVar) {
        if (this.j == null) {
            ReleaseLogUtil.d("Suggestion_CourseDetailDeviceHelper", "handler is null.");
        } else {
            if (bVar.b) {
                ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkToGetDeviceSportStatus has response.no need handle.");
                return;
            }
            ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkToGetDeviceSportStatus has timeout.");
            this.j.sendEmptyMessage(102);
            bVar.b = true;
        }
    }

    public void d(final int i) {
        if (a() && !this.i.isLongExplainVideoCourse()) {
            ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkDeviceSportStatus");
            final b bVar = new b(this, 107, i);
            cap.b(bVar);
            HandlerExecutor.d(new Runnable() { // from class: foi
                @Override // java.lang.Runnable
                public final void run() {
                    fny.this.b(bVar, i);
                }
            }, 2000L);
            return;
        }
        this.j.sendEmptyMessage(i);
    }

    /* synthetic */ void b(b bVar, int i) {
        if (this.j == null) {
            ReleaseLogUtil.d("Suggestion_CourseDetailDeviceHelper", "handler is null.");
        } else {
            if (bVar.b) {
                ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkDeviceStatusAndStartCoach has response.no need handle.");
                return;
            }
            ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkDeviceStatusAndStartCoach has timeout.");
            this.j.sendEmptyMessage(i);
            bVar.b = true;
        }
    }

    public void a(final int i) {
        if (cap.a()) {
            ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkDeviceSportStatus");
            final b bVar = new b(this, 107, i);
            cap.b(bVar);
            HandlerExecutor.d(new Runnable() { // from class: fom
                @Override // java.lang.Runnable
                public final void run() {
                    fny.this.c(bVar, i);
                }
            }, 2000L);
            return;
        }
        this.j.sendEmptyMessage(i);
    }

    /* synthetic */ void c(b bVar, int i) {
        if (this.j == null) {
            ReleaseLogUtil.d("Suggestion_CourseDetailDeviceHelper", "handler is null.");
        } else {
            if (bVar.b) {
                ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkDeviceStatusAndStartTrackActivity has response.no need handle.");
                return;
            }
            ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkDeviceStatusAndStartTrackActivity has timeout.");
            this.j.sendEmptyMessage(i);
            bVar.b = true;
        }
    }

    public boolean a() {
        boolean a2 = ggx.a(BaseApplication.e().getApplicationContext(), gij.b());
        boolean b2 = fpq.b();
        boolean i = kxz.i(arx.b());
        ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkDeviceSportStatus connect wear device:", Boolean.valueOf(a2), Boolean.valueOf(b2), "bandOtaStatus:", Boolean.valueOf(i));
        return a2 && b2 && !i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (this.d == null || i == -1) {
            return;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append(this.d);
        sb.append("_");
        sb.append(i);
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(arx.b(), String.valueOf(101010), "device_support_run_course_key", sb.toString(), storageParams);
        LogUtil.a("Suggestion_CourseDetailDeviceHelper", "record device ability ", Integer.valueOf(i));
    }

    private void o() {
        if (!i() && ggx.a(BaseApplication.e().getApplicationContext(), gij.b())) {
            cun.c().sendDeviceData(22, 12, null, null, "Suggestion_CourseDetailDeviceHelper");
        }
    }

    private boolean i() {
        String b2;
        String str;
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_CourseDetailDeviceHelper");
        if (deviceInfo != null) {
            String securityDeviceId = deviceInfo.getSecurityDeviceId();
            this.d = securityDeviceId;
            if (securityDeviceId == null || (b2 = SharedPreferenceManager.b(arx.b(), String.valueOf(101010), "device_support_run_course_key")) == null) {
                return false;
            }
            String[] split = b2.split("_");
            int i = -1;
            if (split == null || split.length != 2) {
                str = "";
            } else {
                str = split[0];
                try {
                    i = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    LogUtil.b("Suggestion_CourseDetailDeviceHelper", "parse value failed ", LogAnonymous.b((Throwable) e));
                }
            }
            if (this.d.equals(str)) {
                if (i == 1) {
                    Message obtainMessage = this.j.obtainMessage(0);
                    obtainMessage.arg1 = 1;
                    this.j.sendMessage(obtainMessage);
                    this.g = true;
                }
                return true;
            }
        }
        return false;
    }

    static class b implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        int f12580a;
        volatile boolean b = false;
        WeakReference<fny> c;
        int e;

        b(fny fnyVar, int i, int i2) {
            this.c = new WeakReference<>(fnyVar);
            this.f12580a = i;
            this.e = i2;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "checkDeviceSportStatus: onResponse ", obj, " response:", Boolean.valueOf(this.b));
            if (this.b) {
                return;
            }
            fny fnyVar = this.c.get();
            if (fnyVar != null && fnyVar.j != null) {
                this.b = true;
                Handler handler = fnyVar.j;
                if ((obj instanceof Integer) && ((Integer) obj).intValue() == 0) {
                    ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "not running");
                    handler.sendEmptyMessage(this.e);
                    fnyVar.h = false;
                    return;
                } else {
                    handler.sendEmptyMessage(this.f12580a);
                    fnyVar.h = true;
                    return;
                }
            }
            ReleaseLogUtil.d("Suggestion_CourseDetailDeviceHelper", "activity or handler is null");
        }
    }

    private void b(int i) {
        ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "showDeviceDialog heartRateDeviceStatus:", Integer.valueOf(i));
        if (i == 1) {
            e(R.string._2130848503_res_0x7f022af7);
        } else if (i == 2) {
            k();
        }
    }

    private void k() {
        new CustomTextAlertDialog.Builder(this.c).b(this.c.getString(R.string.IDS_plugin_fitnessadvice_binding_device)).e(this.c.getString(R.string.IDS_plugin_fitnessadvice_need_device)).cyU_(R.string._2130848498_res_0x7f022af2, new View.OnClickListener() { // from class: fof
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fny.this.aBQ_(view);
            }
        }).cyR_(R$string.IDS_plugin_fitnessadvice_cancal, new View.OnClickListener() { // from class: fod
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    /* synthetic */ void aBQ_(View view) {
        ggx.e(this.c);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(int i) {
        new CustomTextAlertDialog.Builder(this.c).b(this.c.getString(R.string.IDS_plugin_fitnessadvice_connect_device)).e(this.c.getString(i)).cyU_(R.string._2130848497_res_0x7f022af1, new View.OnClickListener() { // from class: fok
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fny.this.aBR_(view);
            }
        }).cyR_(R$string.IDS_plugin_fitnessadvice_cancal, new View.OnClickListener() { // from class: for
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    /* synthetic */ void aBR_(View view) {
        f();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(Context context) {
        Intent intent = new Intent();
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage());
        intent.setClassName(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.adddevice.AllDeviceListActivity");
        intent.putExtra("progressbar", 0);
        intent.putExtra("isHeartRateDevice", 1);
        intent.putExtra("arg1", "DeviceList");
        intent.putExtra("isFromFitnessAdvice", true);
        intent.putExtra("title", this.c.getResources().getString(R.string.sug_run_workout_selection_devices));
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 1001);
        } else {
            gnm.aPB_(context, intent);
        }
    }

    public void aBT_(int i, int i2, Intent intent) {
        if (i == 1001 && ggx.a(this.c, gij.b())) {
            cun.c().sendDeviceData(22, 12, null, null, "Suggestion_CourseDetailDeviceHelper");
        }
    }

    public void f() {
        if (ggx.c()) {
            ggx.b(this.c);
        } else {
            if (gij.b()) {
                return;
            }
            h();
        }
    }

    public void h() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.c).e(this.c.getResources().getString(R$string.IDS_device_bluetooth_open_request)).czC_(com.huawei.ui.commonui.R$string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: fog
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fny.aBL_(view);
            }
        }).czz_(com.huawei.ui.commonui.R$string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: foj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void aBL_(View view) {
        LogUtil.a("Suggestion_CourseDetailDeviceHelper", "user choose open BT");
        try {
            BluetoothAdapter.getDefaultAdapter().enable();
        } catch (RuntimeException e) {
            ReleaseLogUtil.c("Suggestion_CourseDetailDeviceHelper", "user choose open BT error :", LogAnonymous.b((Throwable) e));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void j() {
        if (n()) {
            q();
            return;
        }
        ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "user has click not remind for course push");
        if (!ggx.a(this.c, gij.b())) {
            p();
        } else {
            m();
        }
    }

    private void q() {
        View inflate = View.inflate(this.c, R.layout.sug_push_course_dialog, null);
        ((HealthTextView) inflate.findViewById(R.id.sug_dialog_title)).setText(R.string._2130848494_res_0x7f022aee);
        ((HealthCheckBox) inflate.findViewById(R.id.sug_privacy_dialog_box)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.helper.CourseDetailDeviceHelper$$ExternalSyntheticLambda10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                fny.this.aBS_(compoundButton, z);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.c);
        aBO_(inflate, builder);
        builder.e().show();
    }

    public /* synthetic */ void aBS_(CompoundButton compoundButton, boolean z) {
        LogUtil.a("Suggestion_CourseDetailDeviceHelper", "initPlanFinishedDialog onClick not remind");
        a(z);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void a(boolean z) {
        SharedPreferenceManager.e(arx.b(), String.valueOf(101010), "not_remind_push_course_key", String.valueOf(z), new StorageParams());
    }

    private boolean n() {
        String b2 = SharedPreferenceManager.b(arx.b(), String.valueOf(101010), "not_remind_push_course_key");
        return TextUtils.isEmpty(b2) || "false".equals(b2);
    }

    private void aBO_(View view, CustomViewDialog.Builder builder) {
        builder.czg_(view).czd_(this.c.getString(R$string.IDS_plugin_fitnessadvice_cancal).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: foe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czf_(this.c.getString(R.string._2130848357_res_0x7f022a65).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: foh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                fny.this.aBP_(view2);
            }
        });
    }

    /* synthetic */ void aBP_(View view) {
        if (!ggx.a(this.c, gij.b())) {
            p();
        } else {
            m();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void p() {
        e(com.huawei.ui.commonui.R$string.IDS_plugin_fitnessadvice_push_need_connect);
    }

    private void m() {
        cun.c().sendDeviceData(12, 2, null, null, "Suggestion_CourseDetailDeviceHelper");
        gge.e("1130024");
    }

    public void d(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        if (l()) {
            if (!gij.b()) {
                if (z) {
                    h();
                }
                iBaseResponseCallback.d(0, 3);
                return;
            }
            iBaseResponseCallback.d(0, 0);
            return;
        }
        if (ggx.e(BaseApplication.wa_(), gij.b())) {
            iBaseResponseCallback.d(0, 0);
        } else if (ggx.d()) {
            iBaseResponseCallback.d(0, 1);
        } else {
            iBaseResponseCallback.d(0, 2);
        }
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        d(iBaseResponseCallback, true);
    }

    public void d(final IBaseResponseCallback iBaseResponseCallback) {
        e(new IBaseResponseCallback() { // from class: fon
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                fny.this.a(iBaseResponseCallback, i, obj);
            }
        });
    }

    /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("Suggestion_CourseDetailDeviceHelper", "guideConnectHeartRateDevice() errorCode = ", Integer.valueOf(i), " objData = ", obj);
        if (obj instanceof Integer) {
            b(((Integer) obj).intValue());
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i, obj);
            }
        }
    }

    private boolean l() {
        boolean e = ggx.e();
        LogUtil.a("Suggestion_CourseDetailDeviceHelper", "isHaveOtherHeartRateDevice() = ", Boolean.valueOf(e));
        return e;
    }

    static class d implements IBaseResponseCallback {
        private WeakReference<fny> d;

        d(fny fnyVar) {
            if (fnyVar != null) {
                this.d = new WeakReference<>(fnyVar);
            }
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("FitnessSug_DeviceStateCallback", "err_code:", Integer.valueOf(i), " objData:", obj);
            WeakReference<fny> weakReference = this.d;
            if (weakReference == null) {
                ReleaseLogUtil.d("FitnessSug_DeviceStateCallback", "onResponse mActivity == null");
                return;
            }
            fny fnyVar = weakReference.get();
            if (fnyVar != null) {
                if (i == 2) {
                    if (obj == null) {
                        LogUtil.b("FitnessSug_DeviceStateCallback", "GET_DEVICE_FONT_INFO objData == null");
                        fnyVar.g();
                        return;
                    } else {
                        if (obj instanceof String[]) {
                            b(fnyVar, (String[]) obj);
                            return;
                        }
                        return;
                    }
                }
                if (i == 12) {
                    if (obj instanceof Integer) {
                        d(fnyVar, ((Integer) obj).intValue());
                    }
                } else {
                    if (i == 13) {
                        if (obj instanceof Integer) {
                            a(fnyVar, ((Integer) obj).intValue());
                            return;
                        }
                        return;
                    }
                    LogUtil.h("FitnessSug_DeviceStateCallback", "COMMAND_ID not found!");
                }
            }
        }

        private void b(fny fnyVar, String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                ReleaseLogUtil.c("FitnessSug_DeviceStateCallback", "GET_DEVICE_FONT_DATA failed");
            } else {
                for (String str : strArr) {
                    fnyVar.e = str;
                }
            }
            fnyVar.g();
        }

        private void d(fny fnyVar, int i) {
            ReleaseLogUtil.e("FitnessSug_DeviceStateCallback", "PUSH_FIT_RUN_COURSE_DATA receive:", Integer.valueOf(i));
            if (i != -1) {
                fnyVar.c(1);
                Handler handler = fnyVar.j;
                Message obtainMessage = handler.obtainMessage(0);
                obtainMessage.arg1 = 1;
                handler.sendMessage(obtainMessage);
                fnyVar.g = true;
            }
        }

        private void a(fny fnyVar, int i) {
            ReleaseLogUtil.e("FitnessSug_DeviceStateCallback", "GET_FIT_RUN_COURSE_INFO receive:", Integer.valueOf(i));
            if (i == 100000) {
                LogUtil.a("FitnessSug_DeviceStateCallback", "push success!");
                Handler handler = fnyVar.j;
                handler.sendMessage(handler.obtainMessage(1));
            } else if (i == 107003) {
                LogUtil.a("FitnessSug_DeviceStateCallback", "course exceed the limit!");
                Handler handler2 = fnyVar.j;
                handler2.sendMessage(handler2.obtainMessage(2));
            }
        }
    }

    public void g() {
        ReleaseLogUtil.e("Suggestion_CourseDetailDeviceHelper", "device language is: ", this.e, " app language is: ", CommonUtil.u());
        WorkoutRecord workoutRecord = this.f;
        String acquireVersion = workoutRecord != null ? workoutRecord.acquireVersion() : "";
        if (this.e != null && !CommonUtil.u().equalsIgnoreCase(this.e)) {
            LogUtil.a("Suggestion_CourseDetailDeviceHelper", "get device language fitworkout courseId: ", this.b, "version: ", acquireVersion, "devicefont: ", this.e);
            c cVar = new c(this);
            CourseApi courseApi = this.f12579a;
            if (courseApi == null) {
                LogUtil.h("Suggestion_CourseDetailDeviceHelper", "pushFitRunCourseData: mCourseApi is null.");
                return;
            } else {
                courseApi.getCourseById(this.b, acquireVersion, this.e, cVar);
                return;
            }
        }
        cun.c().pushFitRunCourseData(this.i);
    }

    static class c extends UiCallback<Workout> {
        private WeakReference<fny> e;

        c(fny fnyVar) {
            this.e = new WeakReference<>(fnyVar);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            fny fnyVar = this.e.get();
            if (fnyVar != null) {
                FitWorkout fitWorkout = fnyVar.i;
                if (fitWorkout != null) {
                    ReleaseLogUtil.d("Suggestion_CourseDetailDeviceHelper", "get device language fitworkout failed,use the app's language course");
                    cun.c().pushFitRunCourseData(fitWorkout);
                    return;
                } else {
                    ReleaseLogUtil.c("Suggestion_CourseDetailDeviceHelper", "get device language fitworkout failed and app's fitworkout is null");
                    return;
                }
            }
            LogUtil.h("Suggestion_CourseDetailDeviceHelper", "GetFitRunCourseCallback onFailure trainDetail is null");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Workout workout) {
            if (ffy.c(workout)) {
                cun.c().pushFitRunCourseData(mod.a(workout));
                return;
            }
            fny fnyVar = this.e.get();
            if (fnyVar != null) {
                FitWorkout fitWorkout = fnyVar.i;
                if (fitWorkout != null) {
                    ReleaseLogUtil.d("Suggestion_CourseDetailDeviceHelper", "get device language workout failed,use the app's language");
                    cun.c().pushFitRunCourseData(fitWorkout);
                    return;
                } else {
                    ReleaseLogUtil.c("Suggestion_CourseDetailDeviceHelper", "get device language fitworkout failed and app's fitworkout is null");
                    return;
                }
            }
            LogUtil.h("Suggestion_CourseDetailDeviceHelper", "GetFitRunCourseCallback onSuccess trainDetail is null");
        }
    }
}
