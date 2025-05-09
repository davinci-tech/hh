package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.WifiDevicePressureCalibrateGuideActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter.MultiDeviceslistAdapter;
import defpackage.qsa;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class qsa {
    private static CustomAlertDialog b;
    private static final String[] c = {"e4b0b1d5-2003-4d88-8b5f-c4f64542040b", "a8ba095d-4123-43c4-a30a-0240011c58de"};

    public static void d(Context context, String str) {
        ArrayList<dcz> c2 = csf.c();
        if (c2.size() == 1) {
            e(context, str, c2.get(0).t());
        } else if (c2.size() > 1) {
            c(context, str);
        } else {
            d(context);
            LogUtil.a("WeightDevicePressureUtil", "handlePressureCalibrate , productList.size : ", Integer.valueOf(c2.size()));
        }
    }

    private static void e(Context context, String str, String str2) {
        LogUtil.a("WeightDevicePressureUtil", "goToPressureCalibrate");
        Intent intent = new Intent(context, (Class<?>) WifiDevicePressureCalibrateGuideActivity.class);
        intent.putExtra("health_wifi_device_userId", str);
        intent.putExtra("health_wifi_device_productId", str2);
        gnm.aPB_(context, intent);
    }

    private static boolean d(String str) {
        for (String str2 : c) {
            if (str2.equals(str)) {
                LogUtil.a("WeightDevicePressureUtil", "isSupportPressureDevcie()=true ", "productType = ", str);
                return true;
            }
        }
        LogUtil.a("WeightDevicePressureUtil", "isSupportPressureDevcie()=false ", "productType = ", str);
        return false;
    }

    private static void c(final Context context, final String str) {
        LogUtil.a("WeightDevicePressureUtil", "showPressureCalibrateDeviceDialog");
        ArrayList arrayList = new ArrayList();
        ArrayList<ctk> a2 = cjx.e().a();
        if (a2 != null && a2.size() > 0) {
            for (int i = 0; i < a2.size(); i++) {
                ctk ctkVar = a2.get(i);
                if (d(ctkVar.getProductId())) {
                    cjv cjvVar = new cjv();
                    cjvVar.a(0);
                    cjvVar.c(ctkVar);
                    arrayList.add(cjvVar);
                }
            }
        } else {
            LogUtil.h("WeightDevicePressureUtil", "WiFiDevice is null or WiFiDevice size is 0");
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.pressure_multi_devices_list_layout, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(R.id.pressure_multi_devices_list);
        final MultiDeviceslistAdapter multiDeviceslistAdapter = new MultiDeviceslistAdapter(context, arrayList);
        listView.setAdapter((ListAdapter) multiDeviceslistAdapter);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
        builder.e(R$string.IDS_device_wifi_pressure_calibrate_multi_devices_title).cyp_(inflate).cyo_(R$string.IDS_settings_button_cancal, new DialogInterface.OnClickListener() { // from class: qrx
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                qsa.dHQ_(dialogInterface, i2);
            }
        }).e(false);
        CustomAlertDialog c2 = builder.c();
        b = c2;
        c2.show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.main.stories.health.util.WeightDevicePressureUtil$$ExternalSyntheticLambda2
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                qsa.dHR_(MultiDeviceslistAdapter.this, context, str, adapterView, view, i2, j);
            }
        });
    }

    static /* synthetic */ void dHQ_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void dHR_(MultiDeviceslistAdapter multiDeviceslistAdapter, Context context, String str, AdapterView adapterView, View view, int i, long j) {
        cpw.a(false, "WeightDevicePressureUtil", "onItemClick position = " + i);
        cjv item = multiDeviceslistAdapter.getItem(i);
        if (item != null && item.i() != null) {
            try {
                e(context, str, ((ctk) item.i()).getProductId());
            } catch (ClassCastException e) {
                LogUtil.h("WeightDevicePressureUtil", "Object to WiFiDevice ClassCastException:" + e.getMessage());
            }
        }
        CustomAlertDialog customAlertDialog = b;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            b.dismiss();
            b = null;
        }
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    private static void d(Context context) {
        LogUtil.a("WeightDevicePressureUtil", "showNoSupportDialog");
        new NoTitleCustomAlertDialog.Builder(context).e(R$string.IDS_hw_adjust_show_pressure_device_connect).czC_(R$string.IDS_common_notification_know_tips, new View.OnClickListener() { // from class: qrz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qsa.dHP_(view);
            }
        }).e().show();
    }

    static /* synthetic */ void dHP_(View view) {
        LogUtil.a("WeightDevicePressureUtil", "onClick know button");
        ViewClickInstrumentation.clickOnView(view);
    }
}
