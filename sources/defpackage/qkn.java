package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.FileProvider;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.R$string;
import java.io.File;
import java.io.IOException;

/* loaded from: classes8.dex */
public class qkn {
    private static Uri b;
    private static File e;

    /* renamed from: a, reason: collision with root package name */
    private CustomPermissionAction f16461a;
    private final Activity c;
    private CustomPermissionAction d;
    private File f;
    private boolean g = false;

    public qkn(Activity activity, CustomPermissionAction customPermissionAction, CustomPermissionAction customPermissionAction2) {
        this.c = activity;
        this.f16461a = customPermissionAction;
        this.d = customPermissionAction2;
    }

    public static void dER_(Activity activity) {
        if (activity == null) {
            LogUtil.h("SelectPhotoInteractor", "activity is null");
            return;
        }
        LogUtil.a("SelectPhotoInteractor", "goCamera");
        try {
            e = WebViewUtils.createImageFile();
        } catch (IOException e2) {
            LogUtil.b("SelectPhotoInteractor", e2.getMessage());
        }
        if (e != null) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.addFlags(3);
            Uri uriForFile = FileProvider.getUriForFile(activity, jcu.f13746a, e);
            b = uriForFile;
            intent.putExtra("output", uriForFile);
            PackageManager packageManager = activity.getPackageManager();
            if (packageManager != null) {
                ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 1048576);
                if (resolveActivity == null || resolveActivity.activityInfo == null) {
                    LogUtil.h("SelectPhotoInteractor", "resolveInfo or resolveInfo.activityInfo is null");
                    return;
                } else {
                    intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                    activity.startActivityForResult(intent, 11);
                    return;
                }
            }
            return;
        }
        LogUtil.h("SelectPhotoInteractor", "outputImage == null");
    }

    public static void dET_(Activity activity) {
        String str;
        if (activity == null) {
            LogUtil.h("SelectPhotoInteractor", "activity is null");
            return;
        }
        String str2 = null;
        Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Constants.IMAGE_TYPE);
        PackageManager packageManager = activity.getPackageManager();
        if (packageManager != null) {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 1048576);
            ActivityInfo activityInfo = resolveActivity != null ? resolveActivity.activityInfo : null;
            if (activityInfo != null) {
                str2 = activityInfo.packageName;
                str = activityInfo.name;
            } else {
                str = null;
            }
            if (str2 != null && str != null) {
                intent.setComponent(new ComponentName(str2, str));
            }
        }
        activity.startActivityForResult(intent, 13);
    }

    public void dEW_(int i, Intent intent) {
        if (i == -1) {
            if (this.c == null) {
                LogUtil.h("SelectPhotoInteractor", "mActivity is null");
                return;
            }
            LogUtil.a("SelectPhotoInteractor", "version >= 6.0");
            Uri data = (intent == null || intent.getData() == null || i != -1) ? b : intent.getData();
            if (data != null) {
                dEU_(data);
                return;
            }
            return;
        }
        LogUtil.h("SelectPhotoInteractor", "unkonw resultCode");
    }

    private void dES_(Intent intent) {
        PackageManager packageManager = this.c.getPackageManager();
        if (packageManager == null) {
            return;
        }
        String str = null;
        String str2 = null;
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 1048576)) {
            if (resolveInfo == null) {
                LogUtil.h("SelectPhotoInteractor", "info is null");
            } else {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null) {
                    str = activityInfo.packageName;
                    str2 = activityInfo.name;
                }
                if (str != null && str2 != null) {
                    intent.setComponent(new ComponentName(str, str2));
                }
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                    LogUtil.h("SelectPhotoInteractor", "resolveInfo or resolveInfo.activityInfo is null");
                } else {
                    try {
                        if (intent.resolveActivity(packageManager) != null) {
                            this.c.startActivityForResult(intent, 14);
                            return;
                        }
                        LogUtil.h("SelectPhotoInteractor", "startActivitySecurity : cannot start the activity");
                    } catch (SecurityException unused) {
                        LogUtil.b("SelectPhotoInteractor", "cropPic startActivityForResult happens error");
                    }
                }
            }
        }
    }

    public void dEU_(Uri uri) {
        LogUtil.a("SelectPhotoInteractor", "cropPic2, uri = ", uri);
        try {
            this.f = WebViewUtils.createImageFile();
        } catch (IOException e2) {
            LogUtil.b("SelectPhotoInteractor", e2.getMessage());
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(3);
        intent.setDataAndType(uri, Constants.IMAGE_TYPE);
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("return-data", false);
        LogUtil.a("SelectPhotoInteractor", "cropPic, intent = ", intent);
        File file = this.f;
        if (file != null) {
            intent.putExtra("output", Uri.fromFile(file));
        }
        if (this.c != null) {
            LogUtil.a("SelectPhotoInteractor", "cropPic startActivitySecurity");
            dES_(intent);
        } else {
            LogUtil.h("SelectPhotoInteractor", "mActivity is null");
        }
    }

    public void dEV_(Uri uri) {
        LogUtil.a("SelectPhotoInteractor", "cropPic2,uri =", uri);
        try {
            this.f = WebViewUtils.createImageFile();
        } catch (IOException e2) {
            LogUtil.b("SelectPhotoInteractor", e2.getMessage());
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(3);
        intent.setDataAndType(uri, Constants.IMAGE_TYPE);
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("return-data", false);
        LogUtil.a("SelectPhotoInteractor", "cropPic, intent = ", intent);
        File file = this.f;
        if (file != null) {
            intent.putExtra("output", Uri.fromFile(file));
        }
        if (this.c != null) {
            LogUtil.a("SelectPhotoInteractor", "cropPicImage startActivitySecurity");
            dES_(intent);
        } else {
            LogUtil.h("SelectPhotoInteractor", "mActivity is null");
        }
    }

    public void dEX_(final Activity activity) {
        if (activity == null) {
            LogUtil.h("SelectPhotoInteractor", "activity is null");
            return;
        }
        View inflate = ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.dialog_select_user_photo, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(activity);
        builder.czc_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: qkn.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a(activity.getString(R$string.IDS_hwh_home_healthshop_select_upload_way)).czg_(inflate);
        final CustomViewDialog e2 = builder.e();
        inflate.findViewById(R.id.line_take_photo).setOnClickListener(new View.OnClickListener() { // from class: qkn.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                qkn.this.dEQ_(activity, false);
                e2.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        inflate.findViewById(R.id.line_gallery).setOnClickListener(new View.OnClickListener() { // from class: qkn.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                qkn.this.dEQ_(activity, true);
                e2.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dEQ_(Activity activity, boolean z) {
        this.g = z;
        LogUtil.a("SelectPhotoInteractor", "checkHavePermission");
        dEY_(activity);
    }

    public void dEY_(Activity activity) {
        if (!this.g) {
            PermissionUtil.b(activity, PermissionUtil.PermissionType.CAMERA, this.d);
            return;
        }
        if (activity == null) {
            LogUtil.b("SelectPhotoInteractor", "takePhoto: activity is null");
        } else if (PermissionUtil.g()) {
            nsn.cKU_(activity, 2);
        } else {
            PermissionUtil.b(activity, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, this.f16461a);
        }
    }

    public File a() {
        return this.f;
    }
}
