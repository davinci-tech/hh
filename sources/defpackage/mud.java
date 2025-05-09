package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginsocialshare.camera.MyCropActivity;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes6.dex */
public class mud {
    public static void cpy_(Activity activity) {
        if (activity == null) {
            LogUtil.b("Share_CropPictureUtils", "choosePic: activity is null");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Constants.IMAGE_TYPE);
            activity.startActivityForResult(intent, 2);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Share_CropPictureUtils", "open system gallery fail:", e.getMessage());
        } catch (SecurityException e2) {
            LogUtil.b("Share_CropPictureUtils", "open system gallery fail securityException: ", e2.getMessage());
        }
    }

    public static void cpE_(Activity activity) {
        if (activity == null) {
            LogUtil.b("Share_CropPictureUtils", "takePhoto: activity is null");
            return;
        }
        if (e() == null) {
            LogUtil.a("Share_CropPictureUtils", "takePhoto getSaveFile is null");
            return;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(1);
        intent.setFlags(2);
        intent.putExtra("camerasensortype", 2);
        intent.putExtra("autofocus", true);
        intent.putExtra("fullScreen", true);
        intent.putExtra("showActionIcons", false);
        intent.putExtra("output", mwd.cqv_(activity, e()));
        try {
            activity.startActivityForResult(intent, 4);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Share_CropPictureUtils", "open system camera fail:", e.getMessage());
        } catch (SecurityException e2) {
            LogUtil.b("Share_CropPictureUtils", "open system camera fail securityException: ", e2.getMessage());
        }
    }

    public static void cpD_(final Activity activity, Intent intent) {
        final String cpz_;
        if (activity == null) {
            LogUtil.b("Share_CropPictureUtils", "startCrop: activity is null");
            return;
        }
        if (intent != null) {
            Uri data = intent.getData();
            if (data == null) {
                LogUtil.b("Share_CropPictureUtils", "startCrop: data.getData() returns null");
                return;
            }
            if (TextUtils.isEmpty(data.getAuthority())) {
                cpz_ = data.getPath();
            } else {
                cpz_ = cpz_(activity, data);
            }
            if (TextUtils.isEmpty(cpz_)) {
                LogUtil.a("Share_CropPictureUtils", "Crop Image is empty");
                return;
            } else {
                activity.runOnUiThread(new Runnable() { // from class: mub
                    @Override // java.lang.Runnable
                    public final void run() {
                        mud.cpB_(activity, cpz_);
                    }
                });
                return;
            }
        }
        LogUtil.a("Share_CropPictureUtils", "data is null");
    }

    static /* synthetic */ void cpB_(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) MyCropActivity.class);
        intent.putExtra(BleConstants.KEY_PATH, str);
        activity.startActivityForResult(intent, 3);
    }

    public static void cpC_(Activity activity) {
        if (activity == null) {
            LogUtil.b("Share_CropPictureUtils", "choosePic: activity is null");
        } else {
            if (e() == null) {
                LogUtil.a("Share_CropPictureUtils", "startCrop getSaveFile is null");
                return;
            }
            Intent intent = new Intent(activity, (Class<?>) MyCropActivity.class);
            intent.putExtra(BleConstants.KEY_PATH, e().getPath());
            activity.startActivityForResult(intent, 3);
        }
    }

    private static File e() {
        String externalStorageState = Environment.getExternalStorageState();
        if (!"mounted".equals(externalStorageState)) {
            LogUtil.a("Share_CropPictureUtils", "getSaveFile sdState ", externalStorageState);
            return null;
        }
        File file = new File(jcu.f);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            LogUtil.a("Share_CropPictureUtils", "dirResult:", Boolean.valueOf(mkdirs));
            if (!mkdirs) {
                return null;
            }
        }
        return new File(jcu.f, "ShareClipTemp.png");
    }

    private static String cpz_(Activity activity, Uri uri) {
        Bitmap cpA_ = cpA_(activity, uri);
        try {
            String c = CommonUtil.c(CommonUtil.j(activity.getApplicationContext()).getCanonicalPath() + "/Huawei/Health/ShareTmp/crop_tmp");
            if (TextUtils.isEmpty(c) || cpA_ == null || cpA_.isRecycled() || !nrf.cJt_(cpA_, c)) {
                return null;
            }
            return c;
        } catch (IOException unused) {
            LogUtil.h("Share_CropPictureUtils", "dealUri getExternalFilesDirectory fail，IOException");
            return null;
        }
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x009c: MOVE (r6 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:53:0x009c */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap cpA_(android.app.Activity r18, android.net.Uri r19) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mud.cpA_(android.app.Activity, android.net.Uri):android.graphics.Bitmap");
    }

    public static long c() {
        long maxMemory = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory();
        LogUtil.a("Share_CropPictureUtils", "freeMemory:", Long.valueOf(maxMemory));
        return maxMemory;
    }
}
