package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes6.dex */
public class mwc {
    private Context d;
    private String e;
    private fdu i;
    private boolean b = false;
    private Uri c = Uri.EMPTY;

    /* renamed from: a, reason: collision with root package name */
    private boolean f15212a = !PermissionUtil.c();

    public mwc(Context context, fdu fduVar) {
        this.d = context;
        this.i = fduVar;
    }

    public void e() {
        jdx.b(new Runnable() { // from class: mwc.2
            @Override // java.lang.Runnable
            public void run() {
                mwc.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("Share_SystemShareInteractors", "doShare: enter");
        fdu fduVar = this.i;
        if (fduVar == null) {
            LogUtil.b("Share_SystemShareInteractors", "mShareContent is null");
        }
        int u = fduVar.u();
        LogUtil.a("Share_SystemShareInteractors", "doShare shareType=", Integer.valueOf(u));
        switch (u) {
            case 0:
                LogUtil.a("Share_SystemShareInteractors", "SHARE_WAY_TEXT");
                e(this.i.q());
                break;
            case 1:
            case 7:
                a(mwd.cqA_(this.d, this.i.awm_()));
                this.b = true;
                break;
            case 2:
                LogUtil.a("Share_SystemShareInteractors", "SHARE_WAY_WEBPAGE");
                String q = this.i.q();
                String y = this.i.y();
                if (q != null && y != null) {
                    e(q + " " + y);
                    break;
                }
                break;
            case 3:
            case 6:
            default:
                LogUtil.b("Share_SystemShareInteractors", "UNKNOWN ShareWay!");
                break;
            case 4:
            case 5:
                LogUtil.a("Share_SystemShareInteractors", "SHARE_WAY_IMG_PATH");
                a(this.i.i());
                break;
            case 8:
            case 9:
                i();
                break;
            case 10:
            case 11:
                a();
                break;
        }
    }

    private void i() {
        Uri uri;
        LogUtil.a("Share_SystemShareInteractors", "shareVideo enter");
        if (this.i.u() == 8) {
            String c = CommonUtil.c(this.i.x());
            if (TextUtils.isEmpty(c)) {
                return;
            } else {
                uri = mwd.cqv_(this.d, new File(c));
            }
        } else if (this.i.u() == 9) {
            uri = this.i.awo_();
        } else {
            LogUtil.h("Share_SystemShareInteractors", "shareVideo is other share type");
            uri = null;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("video/*");
        intent.putExtra("android.intent.extra.STREAM", uri);
        Intent createChooser = Intent.createChooser(intent, BaseApplication.getContext().getString(R.string._2130850338_res_0x7f023222));
        createChooser.addFlags(268435456);
        createChooser.setFlags(1);
        createChooser.setFlags(2);
        try {
            this.d.startActivity(createChooser);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Share_SystemShareInteractors", "systemShareImage err: ActivityNotFoundException");
        }
    }

    private void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", str);
            intent.setType("text/plain");
            Intent createChooser = Intent.createChooser(intent, BaseApplication.getContext().getString(R.string._2130850338_res_0x7f023222));
            createChooser.addFlags(268435456);
            try {
                this.d.startActivity(createChooser);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("Share_SystemShareInteractors", "systemShareText err: ActivityNotFoundException");
                return;
            }
        }
        LogUtil.b("Share_SystemShareInteractors", "systemShareText", "mShareText is null");
    }

    private void a() {
        String str;
        Uri uri;
        String substring;
        char c;
        LogUtil.a("Share_SystemShareInteractors", "shareFile enter");
        if (this.i.u() == 10) {
            str = this.i.e();
            if (TextUtils.isEmpty(str)) {
                return;
            } else {
                uri = mwd.cqv_(this.d, new File(str));
            }
        } else if (this.i.u() == 11) {
            uri = this.i.awl_();
            str = null;
        } else {
            str = null;
            uri = null;
        }
        if (!TextUtils.isEmpty(str) && str.contains(".")) {
            substring = str.substring(str.lastIndexOf(".") + 1);
        } else {
            substring = (uri == null || TextUtils.isEmpty(uri.getPath())) ? "" : uri.getPath().substring(uri.getPath().lastIndexOf(".") + 1);
        }
        substring.hashCode();
        int hashCode = substring.hashCode();
        if (hashCode == 102575) {
            if (substring.equals(NavigationFileParser.GPX)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 106314) {
            if (hashCode == 114665 && substring.equals(NavigationFileParser.TCX)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (substring.equals(NavigationFileParser.KML)) {
                c = 1;
            }
            c = 65535;
        }
        cqm_(uri, c != 0 ? c != 1 ? c != 2 ? "*/*" : "application/vnd.garmin.tcx+xml" : "application/vnd.google-earth.kml+xml" : "application/gpx+xml");
    }

    private void cqm_(Uri uri, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(str);
        intent.putExtra("android.intent.extra.STREAM", uri);
        Intent createChooser = Intent.createChooser(intent, BaseApplication.getContext().getString(R.string._2130850338_res_0x7f023222));
        createChooser.addFlags(268435456);
        createChooser.setFlags(1);
        createChooser.setFlags(2);
        try {
            this.d.startActivity(createChooser);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Share_SystemShareInteractors", "systemShareFile err: ActivityNotFoundException");
        }
    }

    private void a(String str) {
        c();
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("Share_SystemShareInteractors", "imgPath is invalid");
            return;
        }
        this.e = str;
        File file = new File(str);
        LogUtil.a("Share_SystemShareInteractors", "file length = ", Long.valueOf(file.length()));
        if (!file.exists()) {
            LogUtil.a("Share_SystemShareInteractors", "SystemShare", "share() file not exists()");
            return;
        }
        Uri uri = Uri.EMPTY;
        try {
            if (this.f15212a) {
                String insertImage = MediaStore.Images.Media.insertImage(this.d.getContentResolver(), file.getCanonicalPath(), "health_system_share_tmp.jpg", (String) null);
                if (insertImage != null) {
                    uri = Uri.parse(insertImage);
                } else {
                    LogUtil.b("Share_SystemShareInteractors", "cache image insert failed");
                }
            } else {
                uri = mwd.cqv_(this.d, file);
            }
        } catch (IOException | IllegalArgumentException unused) {
            LogUtil.b("Share_SystemShareInteractors", "cache image failed");
        }
        if (uri == null || uri == Uri.EMPTY) {
            LogUtil.b("Share_SystemShareInteractors", "get imgUri failed");
            return;
        }
        this.c = uri;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(Constants.IMAGE_TYPE);
        intent.putExtra("android.intent.extra.STREAM", this.c);
        Intent createChooser = Intent.createChooser(intent, BaseApplication.getContext().getString(R.string._2130850338_res_0x7f023222));
        createChooser.addFlags(268435456);
        createChooser.setFlags(1);
        createChooser.setFlags(2);
        try {
            this.d.startActivity(createChooser);
        } catch (ActivityNotFoundException unused2) {
            LogUtil.b("Share_SystemShareInteractors", "systemShareImage err: ActivityNotFoundException");
        }
    }

    private void c() {
        if (this.c != Uri.EMPTY) {
            if (this.f15212a) {
                mwd.cqr_(this.d, this.c);
            }
            this.c = Uri.EMPTY;
        }
        if (!this.b || TextUtils.isEmpty(this.e)) {
            return;
        }
        mwd.d(this.e);
        this.e = "";
    }

    public void b() {
        c();
    }
}
