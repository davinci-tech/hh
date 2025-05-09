package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;

/* loaded from: classes4.dex */
public class gyf {
    private static final Object b = new Object();
    private static gyf e;
    private Context c;
    private CommonDialog21 d;

    private gyf(Context context) {
        if (context == null) {
            throw new RuntimeException("OfflineDialogController invalid params in constructor");
        }
        this.c = context;
        new CommonDialog21(this.c, R.style.app_update_dialogActivity);
        this.d = CommonDialog21.a(this.c);
    }

    public static gyf b(Context context) {
        synchronized (b) {
            if (e == null) {
                e = new gyf(context);
            }
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        LogUtil.a("Track_OfflineDialogController", "sendBroadcast() action:" + str2);
        Intent intent = new Intent(str2);
        intent.putExtra("TAG_ONE_CITY_STRING_AS", str);
        gut.aUn_(this.c.getApplicationContext(), intent);
    }

    private void c(final String str) {
        String string;
        LogUtil.a("Track_OfflineDialogController", "showNoWifiWaitingDialog()");
        if (Utils.o()) {
            string = this.c.getString(R.string._2130839741_res_0x7f0208bd);
        } else {
            string = this.c.getString(R.string._2130840112_res_0x7f020a30);
        }
        new CustomAlertDialog.Builder(this.c).e(R.string._2130839727_res_0x7f0208af).c(string).cyn_(R.string._2130839728_res_0x7f0208b0, new DialogInterface.OnClickListener() { // from class: gyf.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130839742_res_0x7f0208be, new DialogInterface.OnClickListener() { // from class: gyf.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                gyf.this.a();
                LogUtil.a("Track_OfflineDialogController", "showNoWifiWaitingDialog() onDialogButtonClick Yes");
                gyf.this.a(str, "ACITON_ACTIVITY_WAIT_CITY_AS");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c().show();
    }

    private void b(final String str, final boolean z) {
        String string;
        LogUtil.a("Track_OfflineDialogController", "showNoWifiDialog()");
        if (Utils.o()) {
            string = this.c.getString(R.string._2130839741_res_0x7f0208bd);
        } else {
            string = this.c.getString(R.string._2130840112_res_0x7f020a30);
        }
        new CustomAlertDialog.Builder(this.c).e(R.string._2130839727_res_0x7f0208af).c(string).cyn_(R.string._2130839728_res_0x7f0208b0, new DialogInterface.OnClickListener() { // from class: gyf.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130839742_res_0x7f0208be, new DialogInterface.OnClickListener() { // from class: gyf.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (z) {
                    gyf.this.a(str, "ACTION_MAP_ONCHRCKUPDATE_AS");
                } else {
                    gyf.this.a(str, "ACITON_ACTIVITY_LOAD_CITY_AS");
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c().show();
    }

    public void d(String str) {
        LogUtil.a("Track_OfflineDialogController", "loadWaitCity() enter");
        if (!gyg.b(this.c)) {
            c();
        } else {
            if (gyg.e(this.c)) {
                c(str);
                return;
            }
            a();
            a(str, "ACITON_ACTIVITY_WAIT_CITY_AS");
            LogUtil.a("Track_OfflineDialogController", "loadWaitCity() finish");
        }
    }

    public void c(String str, boolean z) {
        LogUtil.a("Track_OfflineDialogController", "loadCity int isUpdate:", Boolean.valueOf(z));
        if (!gyg.b(this.c)) {
            c();
            return;
        }
        if (gyg.e(this.c)) {
            b(str, z);
        } else if (z) {
            a(str, "ACTION_MAP_ONCHRCKUPDATE_AS");
            LogUtil.a("Track_OfflineDialogController", "loadCity update ok");
        } else {
            a(str, "ACITON_ACTIVITY_LOAD_CITY_AS");
            LogUtil.a("Track_OfflineDialogController", "loadCity ok");
        }
    }

    public void d(String str, boolean z) {
        LogUtil.a("Track_OfflineDialogController", "deleteCity()");
        b(String.format(this.c.getString(R.string._2130839749_res_0x7f0208c5), str));
        Intent intent = new Intent("ACITON_ACTIVITY_DELETE_CITY_AS");
        intent.putExtra("TAG_ONE_CITY_STRING_AS", str);
        intent.putExtra("TAG_BOOLEAN_ISRESTART_AS", z);
        gut.aUn_(this.c.getApplicationContext(), intent);
    }

    public void a(String str) {
        LogUtil.a("Track_OfflineDialogController", "stopLoading");
        a(str, "ACTION_ACTIVITY_PAUSE_CITY_AS");
    }

    private void b(String str) {
        a(str, true);
    }

    public void a() {
        LogUtil.a("Track_OfflineDialogController", "showWaitingDialog enter");
        d(true);
    }

    private void d(boolean z) {
        a(this.c.getString(R.string._2130839497_res_0x7f0207c9), z);
    }

    private void a(String str, boolean z) {
        if (this.d == null) {
            new CommonDialog21(this.c, R.style.app_update_dialogActivity);
            this.d = CommonDialog21.a(this.c);
        }
        this.d.e(str);
        this.d.setCancelable(z);
        this.d.a();
    }

    private void c() {
        LogUtil.a("Track_OfflineDialogController", "showNetworkOpenDialog()");
        new CustomAlertDialog.Builder(this.c).e(R.string._2130839727_res_0x7f0208af).c(this.c.getString(R.string._2130839498_res_0x7f0207ca)).cyn_(R.string._2130839728_res_0x7f0208b0, new DialogInterface.OnClickListener() { // from class: gyf.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130839743_res_0x7f0208bf, new DialogInterface.OnClickListener() { // from class: gyf.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("Track_OfflineDialogController", "showNetworkOpenDialog() onDialogButtonClick Yes");
                try {
                    gyf.this.c.startActivity(new Intent("android.settings.SETTINGS"));
                } catch (ActivityNotFoundException e2) {
                    LogUtil.b("Track_OfflineDialogController", "showNetworkOpenDialog() exception: ", LogAnonymous.b((Throwable) e2));
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c().show();
    }

    public void b() {
        LogUtil.a("Track_OfflineDialogController", "closeWaitingDialog enter mWaitingDialog : ", this.d);
        CommonDialog21 commonDialog21 = this.d;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.d.dismiss();
    }

    public void d() {
        this.d = null;
        this.c = null;
        e();
    }

    private static void e() {
        synchronized (gyf.class) {
            e = null;
        }
    }
}
