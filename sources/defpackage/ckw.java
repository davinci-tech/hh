package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.dialog.CustomDeviceSelectDialog;
import com.huawei.health.device.ui.dialog.DeviceCheckAdapter;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.dcz;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class ckw {
    private static volatile ckw c;

    private ckw() {
    }

    public static ckw a() {
        if (c == null) {
            synchronized (ckw.class) {
                if (c == null) {
                    c = new ckw();
                }
            }
        }
        return c;
    }

    public void a(final Context context, ArrayList<ContentValues> arrayList) {
        LogUtil.a("RopeDeviceHelper", "showSelectDeviceDialog: productsList.size = ", Integer.valueOf(arrayList.size()));
        CustomDeviceSelectDialog e = new CustomDeviceSelectDialog.Builder(context).b(R.string.IDS_plugin_device_select).e(HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING).d(R.string._2130841131_res_0x7f020e2b, new DeviceCheckAdapter.OnItemClickListener() { // from class: cla
            @Override // com.huawei.health.device.ui.dialog.DeviceCheckAdapter.OnItemClickListener
            public final void onItemClick(cjv cjvVar) {
                ckw.this.a(context, cjvVar);
            }
        }).Hx_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cky
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ckw.Ho_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    /* synthetic */ void a(Context context, cjv cjvVar) {
        if (cjvVar != null) {
            d(context, cjvVar);
        } else {
            LogUtil.a("RopeDeviceHelper", "select no device");
        }
    }

    static /* synthetic */ void Ho_(View view) {
        LogUtil.a("RopeDeviceHelper", "cancel clicked");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(Context context, cjv cjvVar) {
        ContentValues FT_ = cjvVar.FT_();
        String asString = FT_.getAsString("productId");
        String asString2 = FT_.getAsString("uniqueId");
        dcz d = ResourceManager.e().d(asString);
        if (d == null) {
            LogUtil.h("RopeDeviceHelper", "handleSelectDevice: productInfo is null");
            return;
        }
        dcz.e m = d.m();
        if (m == null) {
            LogUtil.h("RopeDeviceHelper", "handleSelectDevice: introduction is null");
            return;
        }
        if (BleConstants.BLE_THIRD_DEVICE_H5.equals(m.d())) {
            Intent Wx_ = dks.Wx_(d, asString, asString2);
            if (Wx_ != null) {
                context.startActivity(Wx_);
                return;
            }
            return;
        }
        d(context, asString, asString2);
    }

    public void d(Context context, String str, String str2) {
        LogUtil.a("RopeDeviceHelper", "startRopeDeviceConnectingPage: productId=", str, ", uniqueId=", cpw.a(str2));
        dcz d = ResourceManager.e().d(str);
        if (context == null || d == null) {
            LogUtil.a("RopeDeviceHelper", "startRopeDeviceConnectingPage: context or productInfo is null");
        } else {
            context.startActivity(Hp_(d.t(), str2, d.l().name(), dcx.d(d.t(), d.n().b()), d.n().d()));
        }
    }

    public Intent Hp_(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getContext(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("productId", str);
        intent.putExtra("arg1", "ropeDeviceConnecting");
        intent.putExtra("DeviceType", str3);
        intent.putExtra("DeviceName", str4);
        intent.putExtra("DeviceIconPath", str5);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("productId", str);
        intent.putExtra("commonDeviceInfo", contentValues);
        return intent;
    }
}
