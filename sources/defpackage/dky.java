package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.util.LogUtil;

/* loaded from: classes8.dex */
public class dky {
    public static void d(Fragment fragment, boolean z) {
        if (fragment == null) {
            LogUtil.c("TurnOnBleUtils", "startIntent, context is null");
        } else if (!e(fragment.getActivity())) {
            WD_(fragment.getActivity());
        } else {
            a(fragment, z);
        }
    }

    public static boolean e(Context context) {
        if (context == null) {
            LogUtil.c("TurnOnBleUtils", "isSupportBluetooth, context is null");
            return false;
        }
        if ((context.getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) context.getSystemService("bluetooth") : null) != null) {
            return true;
        }
        LogUtil.c("TurnOnBleUtils", "no BT Manager in this phone");
        return false;
    }

    public static void WD_(Activity activity) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(activity).e(R.string._2130844216_res_0x7f021a38).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: dky.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("TurnOnBleUtils", "Bluetooth not supported");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    private static void a(Fragment fragment, boolean z) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && !defaultAdapter.isEnabled()) {
            PermissionDialogHelper.e(fragment, 102);
        } else {
            diy.c(fragment.getContext(), z);
        }
    }
}
