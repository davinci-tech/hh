package com.huawei.featureuserprofile.route.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.huawei.agconnect.apms.Agent;
import com.huawei.featureuserprofile.route.activity.RouteExportActivity;
import com.huawei.featureuserprofile.route.navigationgenerator.BaseGenerator;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.route.TrackInfoModel;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.view.shareSelector.ShareSelector;
import com.huawei.ui.commonui.view.shareSelector.ShareSelectorClickCallback;
import defpackage.btq;
import defpackage.btw;
import defpackage.fdu;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

/* loaded from: classes.dex */
public class RouteExportActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthRadioButton f2009a;
    private HealthRadioButton b;
    private boolean c = true;
    private HealthRadioButton d;
    private boolean e;
    private RelativeLayout g;
    private TrackInfoModel i;
    private ShareSelector j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_route_export);
        TrackInfoModel c = btq.c();
        this.i = c;
        if (c != null && !koq.b(c.getPoints())) {
            if (getIntent() != null) {
                this.c = getIntent().getBooleanExtra("IS_NEED_CHANGE_POINT", true);
            }
            b();
            c();
            return;
        }
        finish();
    }

    private void c() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: btm
            @Override // java.lang.Runnable
            public final void run() {
                RouteExportActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.c) {
            TrackInfoModel.Builder routeType = new TrackInfoModel.Builder().routeName(this.i.getRouteName()).sportType(this.i.getSportType()).sportTotalTime(this.i.getSportTotalTime()).sportTotalDistance(this.i.getSportTotalDistance()).cumulativeClimb(this.i.getCumulativeClimb()).cumulativeDecrease(this.i.getCumulativeDecrease()).routeType(this.i.getRouteType());
            routeType.points(btw.d(this.i.getPoints()));
            this.i = routeType.build();
        }
        this.e = true;
        LogUtil.a("track_route_export_activity", "transferTrackPoint waste time:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void b() {
        getWindow().setBackgroundDrawableResource(R.color._2131296690_res_0x7f0901b2);
        ((CustomTitleBar) findViewById(R.id.title_bar)).setTitleText(getString(R.string._2130838790_res_0x7f020506));
        e();
        this.f2009a = (HealthRadioButton) findViewById(R.id.radiobutton_tcx);
        this.b = (HealthRadioButton) findViewById(R.id.radiobutton_gpx);
        this.d = (HealthRadioButton) findViewById(R.id.radiobutton_kml);
        findViewById(R.id.health_card_view_tcx).setOnClickListener(new View.OnClickListener() { // from class: bti
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteExportActivity.this.uy_(view);
            }
        });
        findViewById(R.id.health_card_view_gpx).setOnClickListener(new View.OnClickListener() { // from class: btf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteExportActivity.this.uz_(view);
            }
        });
        findViewById(R.id.health_card_view_kml).setOnClickListener(new View.OnClickListener() { // from class: btg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteExportActivity.this.uA_(view);
            }
        });
    }

    public /* synthetic */ void uy_(View view) {
        if (!this.f2009a.isChecked()) {
            this.f2009a.setChecked(true);
            this.b.setChecked(false);
            this.d.setChecked(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void uz_(View view) {
        if (!this.b.isChecked()) {
            this.f2009a.setChecked(false);
            this.b.setChecked(true);
            this.d.setChecked(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void uA_(View view) {
        if (!this.d.isChecked()) {
            this.f2009a.setChecked(false);
            this.b.setChecked(false);
            this.d.setChecked(true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        ShareSelector shareSelector = (ShareSelector) findViewById(R.id.share_selector);
        this.j = shareSelector;
        shareSelector.setLocalShareVisibility(EnvironmentInfo.k() ? 8 : 0);
        this.j.setShareWeChatVisibility(Utils.o() ? 8 : 0);
        this.j.setMoreVisibility(EnvironmentInfo.k() ? 8 : 0);
        this.j.setShareWeChatFriendsVisibility(8);
        this.j.setSinaVisibility(8);
        this.j.setMoreLayoutText(getString(R.string._2130841847_res_0x7f0210f7));
        this.j.setClickCallback(new ShareSelectorClickCallback() { // from class: com.huawei.featureuserprofile.route.activity.RouteExportActivity.1
            @Override // com.huawei.ui.commonui.view.shareSelector.ShareSelectorClickCallback
            public void onSinaShare() {
            }

            @Override // com.huawei.ui.commonui.view.shareSelector.ShareSelectorClickCallback
            public void onWeChatFriendShare() {
            }

            @Override // com.huawei.ui.commonui.view.shareSelector.ShareSelectorClickCallback
            public void onWeChatShare() {
                RouteExportActivity.this.a(0);
            }

            @Override // com.huawei.ui.commonui.view.shareSelector.ShareSelectorClickCallback
            public void onLocalShare() {
                RouteExportActivity.this.a(1);
            }

            @Override // com.huawei.ui.commonui.view.shareSelector.ShareSelectorClickCallback
            public void onMoreShare() {
                RouteExportActivity.this.a(2);
            }
        });
    }

    /* renamed from: com.huawei.featureuserprofile.route.activity.RouteExportActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends CustomPermissionAction {
        final /* synthetic */ int d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, int i) {
            super(context);
            this.d = i;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            final String c = RouteExportActivity.this.c(this.d);
            if (c == null) {
                LogUtil.b("track_route_export_activity", "onGranted dirPath == null");
                return;
            }
            ViewStub viewStub = (ViewStub) RouteExportActivity.this.findViewById(R.id.export_loading_stub);
            if (RouteExportActivity.this.g == null) {
                RouteExportActivity.this.g = (RelativeLayout) viewStub.inflate();
            }
            RouteExportActivity.this.g.setVisibility(0);
            RouteExportActivity.this.g.setClickable(true);
            ThreadPoolManager d = ThreadPoolManager.d();
            final int i = this.d;
            d.execute(new Runnable() { // from class: btk
                @Override // java.lang.Runnable
                public final void run() {
                    RouteExportActivity.AnonymousClass2.this.b(i, c);
                }
            });
        }

        public /* synthetic */ void b(int i, String str) {
            RouteExportActivity.this.e(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        PermissionUtil.b(this, PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE, new AnonymousClass2(this, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i, String str) {
        final File makeTrackFile;
        String routeName = this.i.getRouteName();
        if (i != 1) {
            FileUtils.b(new File(str), false);
        }
        if (routeName.length() > 100) {
            routeName = routeName.substring(0, 100);
        }
        if (routeName.lastIndexOf(File.separator) == routeName.length() - 1) {
            routeName = routeName.substring(0, routeName.length() - 1);
        }
        if (this.b.isChecked()) {
            makeTrackFile = BaseGenerator.makeTrackFile(this.i, NavigationFileParser.GPX, b(i, str, routeName, NavigationFileParser.GPX));
        } else if (this.f2009a.isChecked()) {
            makeTrackFile = BaseGenerator.makeTrackFile(this.i, NavigationFileParser.TCX, b(i, str, routeName, NavigationFileParser.TCX));
        } else {
            makeTrackFile = BaseGenerator.makeTrackFile(this.i, NavigationFileParser.KML, b(i, str, routeName, NavigationFileParser.KML));
        }
        runOnUiThread(new Runnable() { // from class: btl
            @Override // java.lang.Runnable
            public final void run() {
                RouteExportActivity.this.a(i, makeTrackFile);
            }
        });
    }

    public /* synthetic */ void a(int i, File file) {
        this.g.setVisibility(8);
        e(i, file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(int i) {
        if (!this.e) {
            LogUtil.a("track_route_export_activity", "transfer point not finish,please try again");
            return null;
        }
        this.j.setEnabled(false);
        StringBuilder sb = new StringBuilder();
        sb.append(CommonUtil.j((Context) null).getAbsolutePath());
        sb.append(File.separator);
        sb.append(i == 1 ? "Tracks/Export" : "TracksShare");
        String sb2 = sb.toString();
        File file = new File(sb2);
        try {
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    LogUtil.a("track_route_export_activity", "create dirPath fail");
                    return null;
                }
            }
            return sb2;
        } catch (SecurityException unused) {
            LogUtil.b("track_route_export_activity", "mkdir error");
            return null;
        }
    }

    private void e(int i, File file) {
        String str;
        this.j.setEnabled(true);
        if (!FileUtils.c(file)) {
            c(getString(R.string._2130838791_res_0x7f020507));
            return;
        }
        try {
            str = file.getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.b("track_route_export_activity", "getCanonicalPath IOException");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("track_route_export_activity", "canonicalPath is empty");
            return;
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        if (lastIndexOf != -1) {
            String substring = str.substring(lastIndexOf + 1);
            if (i != 0) {
                if (i == 1) {
                    d(file, str, substring);
                    return;
                } else {
                    d(str, substring, 5);
                    return;
                }
            }
            if (!CommonUtil.aa(this)) {
                c(getString(R.string._2130841392_res_0x7f020f30));
                return;
            } else if (!Utils.c(this, "com.tencent.mm")) {
                c(getString(R.string._2130839536_res_0x7f0207f0));
                return;
            } else {
                d(str, substring, 2);
                return;
            }
        }
        LogUtil.a("track_route_export_activity", "get fileName fail");
    }

    private void d(File file, String str, String str2) {
        OutputStream outputStream;
        OutputStream outputStream2;
        FileInputStream fileInputStream;
        if (Build.VERSION.SDK_INT < 30) {
            if (str.contains(Agent.OS_NAME)) {
                b(String.format(Locale.ENGLISH, getString(R.string._2130838792_res_0x7f020508), str.substring(str.indexOf(Agent.OS_NAME))));
                return;
            }
            if (str.contains("Tracks/Export")) {
                int indexOf = str.indexOf(Environment.DIRECTORY_DOWNLOADS);
                Locale locale = Locale.ENGLISH;
                String string = getString(R.string._2130838792_res_0x7f020508);
                Object[] objArr = new Object[1];
                if (indexOf <= 0) {
                    indexOf = str.indexOf("Tracks/Export");
                }
                objArr[0] = str.substring(indexOf);
                b(String.format(locale, string, objArr));
                return;
            }
            LogUtil.a("track_route_export_activity", "canonicalPath is error:", str);
            return;
        }
        ContentValues contentValues = new ContentValues();
        String str3 = Environment.DIRECTORY_DOWNLOADS + File.separator + "Tracks/Export";
        contentValues.put("_display_name", str2);
        contentValues.put("mime_type", "*/*");
        contentValues.put("title", str2);
        contentValues.put("relative_path", str3);
        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = getContentResolver();
        Uri insert = contentResolver.insert(uri, contentValues);
        LogUtil.a("track_route_export_activity", "insertUri:", insert);
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (IOException unused) {
            outputStream2 = null;
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            OutputStream openOutputStream = contentResolver.openOutputStream(insert);
            if (openOutputStream != null) {
                FileUtils.e(fileInputStream, openOutputStream);
            } else {
                LogUtil.a("track_route_export_activity", "fileInputStream is empty");
            }
            c(fileInputStream, openOutputStream);
        } catch (IOException unused2) {
            outputStream2 = null;
            fileInputStream2 = fileInputStream;
            try {
                LogUtil.b("track_route_export_activity", "IOException");
                c(fileInputStream2, outputStream2);
                b(String.format(Locale.ENGLISH, getString(R.string._2130838792_res_0x7f020508), str3 + File.separator + str2));
            } catch (Throwable th2) {
                outputStream = outputStream2;
                th = th2;
                c(fileInputStream2, outputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
            fileInputStream2 = fileInputStream;
            c(fileInputStream2, outputStream);
            throw th;
        }
        b(String.format(Locale.ENGLISH, getString(R.string._2130838792_res_0x7f020508), str3 + File.separator + str2));
    }

    private void c(FileInputStream fileInputStream, OutputStream outputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException unused) {
                LogUtil.b("track_route_export_activity", "fileInputStream close catch IOException");
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused2) {
                LogUtil.b("track_route_export_activity", "outputStream close catch IOException");
            }
        }
    }

    private void b(String str) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_confirm_route_location, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.tv_remind)).setText(str);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.a(getString(R.string._2130838888_res_0x7f020568)).czh_(inflate, 0, 0).cze_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: bte
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteExportActivity.ux_(view);
            }
        });
        builder.e().show();
    }

    public static /* synthetic */ void ux_(View view) {
        LogUtil.a("track_route_export_activity", "onClick positive view");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(String str) {
        Toast.makeText(this, str, 0).show();
    }

    private String b(int i, String str, String str2, String str3) {
        String str4 = str + File.separator + str2 + "." + str3;
        if (i != 1) {
            return str4;
        }
        int i2 = 1;
        while (FileUtils.c(new File(str4))) {
            str4 = str + File.separator + str2 + Constants.LEFT_BRACKET_ONLY + i2 + ")." + str3;
            i2++;
        }
        return str4;
    }

    private void d(String str, String str2, int i) {
        fdu fduVar = new fdu(10);
        fduVar.e(str);
        fduVar.b(9);
        fduVar.c(str2);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(this, i, fduVar);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.i = null;
        btq.d();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
