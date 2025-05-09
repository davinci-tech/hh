package com.huawei.openalliance.ad.download.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.PermissionEntity;
import com.huawei.openalliance.ad.ph;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.y;
import java.util.List;

/* loaded from: classes5.dex */
public class h {

    public interface a {
        void a();

        void a(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context, AppInfo appInfo, final a aVar) {
        AlertDialog.Builder a2 = y.a(context);
        a2.setTitle("");
        if (aVar != null) {
            a2.setPositiveButton(R.string._2130851068_res_0x7f0234fc, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.download.app.h.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    a.this.a();
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            });
            a2.setNeutralButton(R.string._2130851070_res_0x7f0234fe, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.download.app.h.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    a.this.a(1006);
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            });
        } else {
            a2.setNeutralButton(R.string._2130851071_res_0x7f0234ff, (DialogInterface.OnClickListener) null);
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.hiad_permission_dialog_cotent, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.hiad_permissions_dialog_content_title_tv)).setText(context.getResources().getString(R.string._2130851122_res_0x7f023532, appInfo.getAppName()));
        ((ListView) inflate.findViewById(R.id.hiad_permissions_dialog_content_lv)).setAdapter((ListAdapter) new g(context, appInfo.getPermissions()));
        a2.setView(inflate);
        AlertDialog create = a2.create();
        ho.a("AppPermissionsDialog", "show, time:%d", Long.valueOf(System.currentTimeMillis()));
        a(context, create);
    }

    private static void c(final Context context, final AppInfo appInfo, final a aVar) {
        AlertDialog.Builder a2 = y.a(context);
        a2.setView(LayoutInflater.from(context).inflate(R.layout.hiad_loading_dialog_content, (ViewGroup) null));
        final AlertDialog create = a2.create();
        a(context, create);
        new ph(context, new ph.a() { // from class: com.huawei.openalliance.ad.download.app.h.1
            @Override // com.huawei.openalliance.ad.ph.a
            public void a(final List<PermissionEntity> list) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.h.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        create.dismiss();
                        if (!bg.a(list)) {
                            h.d(context, appInfo, aVar);
                            return;
                        }
                        Toast.makeText(context, R.string._2130851111_res_0x7f023527, 0).show();
                        if (aVar != null) {
                            aVar.a(1005);
                        }
                    }
                });
            }
        }).a(appInfo);
    }

    public static void a(Context context, AppInfo appInfo, a aVar) {
        if (appInfo == null) {
            ho.c("AppPermissionsDialog", "appInfo is null.");
            return;
        }
        ho.b("AppPermissionsDialog", "show, context:" + context);
        if (!bg.a(appInfo.getPermissions())) {
            d(context, appInfo, aVar);
        } else {
            ho.b("AppPermissionsDialog", "permissions is empty");
            c(context, appInfo, aVar);
        }
    }

    public static void a(Context context, AppInfo appInfo) {
        a(context, appInfo, null);
    }

    private static void a(Context context, AlertDialog alertDialog) {
        Window window;
        if (!(context instanceof Activity) && (window = alertDialog.getWindow()) != null) {
            window.setType(2038);
        }
        alertDialog.show();
    }
}
