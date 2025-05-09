package defpackage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.huawei.featureuserprofile.healthrecord.js.HealthRecordsJsApi;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.bloodpressure.BloodPressureApi;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.pressure.PressureApi;
import com.huawei.health.userprofilemgr.model.HealthRecordCbk;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.wearengine.sensor.DataResult;
import health.compact.a.Services;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class brg {
    private static brh c;

    public static void tK_(Intent intent, brh brhVar, HealthRecordCbk<String> healthRecordCbk) {
        c = brhVar;
        int d = brhVar.d();
        LogUtil.a("HealthRecordRemindHelper", " registerRemind remindTime: " + c.i() + " requestCode:" + d);
        intent.putExtra(JsbMapKeyNames.H5_USER_ID, c.j());
        intent.putExtra("physicalExaminationId", d);
        if (c.h()) {
            tI_(d, intent, healthRecordCbk);
        }
        sqa.ekx_(d, intent, 201326592, 0, c.i());
        healthRecordCbk.onSuccess("registerRemind success");
        LogUtil.a("HealthRecordRemindHelper", "registerRemind success");
        if (c()) {
            e();
        }
    }

    public static void tI_(int i, Intent intent, HealthRecordCbk<String> healthRecordCbk) {
        LogUtil.a("HealthRecordRemindHelper", " cancelRemind requestCode:" + i);
        sqa.ekn_(i, intent, 201326592);
        healthRecordCbk.onSuccess("cancelRemind success");
        if (c()) {
            a();
        }
    }

    private static boolean c() {
        if (TextUtils.isEmpty(c.e())) {
            LogUtil.h("HealthRecordRemindHelper", "checkCalendarPermission name is null, return");
            return false;
        }
        if (nrk.d(BaseApplication.getContext())) {
            return true;
        }
        LogUtil.h("HealthRecordRemindHelper", "checkCalendarPermission no calender permission name = ", c.e());
        return false;
    }

    private static void e() {
        if (c.a() == 0 || TextUtils.isEmpty(c.e())) {
            LogUtil.h("HealthRecordRemindHelper", "addCalenderEvent examinationDate or recordName is null ");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bri
                @Override // java.lang.Runnable
                public final void run() {
                    brg.d();
                }
            });
        }
    }

    static /* synthetic */ void d() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventTimezone", String.valueOf(TimeZone.getTimeZone(DataResult.UTC)));
        contentValues.put("allDay", (Integer) 1);
        contentValues.put("title", c.e());
        contentValues.put("description", c.b());
        contentValues.put("eventLocation", c.c());
        contentValues.put("eventStatus", (Integer) 1);
        contentValues.put("selfAttendeeStatus", (Integer) 1);
        contentValues.put("dtstart", Long.valueOf(c.a()));
        LogUtil.a("HealthRecordRemindHelper", "addCalenderEvent DTSTART = ", Long.valueOf(c.a()));
        contentValues.put("dtend", Long.valueOf(c.a() + 86400000));
        contentValues.put("hasAlarm", (Integer) 1);
        contentValues.put("calendar_id", Integer.valueOf(nrk.b(BaseApplication.getContext())));
        contentValues.put("guestsCanModify", (Boolean) false);
        contentValues.put("isOrganizer", (Boolean) true);
        contentValues.put("accessLevel", (Integer) 0);
        contentValues.put("guestsCanInviteOthers", (Boolean) false);
        contentValues.put("guestsCanModify", (Boolean) false);
        contentValues.put("customAppUri", "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.health-record&from=healthRecord&type=1&examinationId=" + c.d());
        LogUtil.a("HealthRecordRemindHelper", "calenderSync name = ", c.e(), " calenderSync id = ", Integer.valueOf(c.d()));
        LogUtil.a("HealthRecordRemindHelper", "insert event into android calendar, uri=" + BaseApplication.getContext().getContentResolver().insert(CalendarContract.Events.CONTENT_URI, contentValues));
    }

    private static void a() {
        final ContentResolver contentResolver = arx.b().getContentResolver();
        final String str = "(customAppUri = ?)";
        ThreadPoolManager.d().execute(new Runnable() { // from class: brj
            @Override // java.lang.Runnable
            public final void run() {
                LogUtil.a("HealthRecordRemindHelper", "deleteCalendar success " + contentResolver.delete(CalendarContract.Events.CONTENT_URI, str, new String[]{"huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.health-record&from=healthRecord&type=1&examinationId=" + brg.c.d()}) + " events of the invalid plan");
            }
        });
    }

    public static void b(Context context, String str, String str2) {
        LogUtil.a("HealthRecordRemindHelper", "gotoHealthRecordH5Page enter examinationId = ", str);
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.b("HealthRecordRemindHelper", "gotoHealthRecordH5Page context|path|healthRecordId is null.");
            return;
        }
        PressureApi pressureApi = (PressureApi) Services.c("Main", PressureApi.class);
        BloodPressureApi bloodPressureApi = (BloodPressureApi) Services.c("Main", BloodPressureApi.class);
        String str3 = "0".equals(str) ? "2" : "1";
        bzs.e().initH5Pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().setImmerse().showStatusBar().setNeedSoftInputAdapter().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("JsInteraction", bzs.e().getCommonJsModule("JsInteraction")).addCustomizeJsModule("device", bzs.e().getCommonJsModule("device")).addCustomizeJsModule("PressureNewJsApi", pressureApi.getPressureNewJsApi()).addCustomizeArg("examinationId", str).addPath("#/?from=" + str3 + "&cardType=" + str2).build();
        H5ProClient.getServiceManager().registerService(HealthRecordsJsApi.class);
        H5ProClient.getServiceManager().registerService((Class<?>) pressureApi.getPressureJsApi());
        H5ProClient.getServiceManager().registerService((Class<?>) bloodPressureApi.getBloodPressureJsApi());
        H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.health-record", build);
        LogUtil.a("HealthRecordRemindHelper", "gotoHealthRecordH5Page success");
    }
}
