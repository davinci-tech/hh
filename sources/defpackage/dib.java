package defpackage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.RecordAction;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;

/* loaded from: classes3.dex */
public class dib {
    private static dib c;

    public boolean b(int i) {
        return i != 0;
    }

    private dib() {
    }

    public static dib c() {
        if (c == null) {
            c = new dib();
        }
        return c;
    }

    public String a(int i) {
        if (i == 1) {
            return HealthDevice.HealthDeviceKind.HDK_TREADMILL.name();
        }
        return i == 2 ? HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE.name() : "";
    }

    public String UU_(ContentValues contentValues) {
        return contentValues == null ? "" : e(contentValues.getAsString("kind"), contentValues.getAsString("uniqueId"));
    }

    public String d(MeasurableDevice measurableDevice) {
        return measurableDevice == null ? "" : e(measurableDevice.getKind().name(), measurableDevice.getUniqueId());
    }

    private String e(String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        String str3 = "HDK_EXERCISE_BIKE".equals(str) ? BleConstants.SPORT_TYPE_BIKE : "31";
        sb.append(RecordAction.ACT_COST_TIME_TAG);
        sb.append(str3);
        sb.append("&ftmp=1&ble=");
        sb.append(str2);
        return sb.toString();
    }

    public void c(Context context, int i, String str, MeasurableDevice measurableDevice) {
        if (context == null) {
            LogUtil.a("DeviceCoursesHelper", "startIndoorEquipConnectedActivity context is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.indoorequip.activity.IndoorEquipConnectedActivity");
        intent.putExtra("PAYLOAD_FROM_NFC", d(measurableDevice));
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", i);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", str);
        gnm.aPB_(context, intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public void b(Context context, String str) {
        String str2;
        if (context == null) {
            LogUtil.a("DeviceCoursesHelper", "showTipDialog context is null");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_TREADMILL.name().equals(str)) {
            str2 = context.getResources().getString(R.string._2130845053_res_0x7f021d7d);
        } else if (HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE.name().equals(str)) {
            str2 = context.getResources().getString(R.string._2130845054_res_0x7f021d7e);
        } else {
            LogUtil.a("DeviceCoursesHelper", "Course equipmentType", str);
            str2 = "";
        }
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(context).e(R.string.IDS_course_device_nonmatch_dialog_title).c(str2).cyn_(R.string.IDS_course_device_nonmatch_dialog_ok, new DialogInterface.OnClickListener() { // from class: dih
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dib.UT_(dialogInterface, i);
            }
        }).c();
        c2.setCancelable(false);
        c2.show();
    }

    static /* synthetic */ void UT_(DialogInterface dialogInterface, int i) {
        LogUtil.h("DeviceCoursesHelper", "showTipDialog，click known button");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public void UV_(Intent intent, Bundle bundle) {
        if (intent == null || bundle == null) {
            LogUtil.a("DeviceCoursesHelper", "intent or bundle is null");
            return;
        }
        int intExtra = intent.getIntExtra("KEY_INTENT_COURSE_ENTRANCE", 0);
        if (b(intExtra)) {
            String stringExtra = intent.getStringExtra("KEY_INTENT_EQUIPMENT_TYPE");
            bundle.putInt("KEY_INTENT_COURSE_ENTRANCE", intExtra);
            bundle.putString("KEY_INTENT_EQUIPMENT_TYPE", stringExtra);
        }
    }
}
