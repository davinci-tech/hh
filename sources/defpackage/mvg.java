package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXFileObject;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class mvg {

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f15199a;
    private Context c;
    private String d = "wx36bda3d35fbcfd06";
    private IWXAPI e;

    mvg(Context context) {
        this.c = context;
        e(context);
    }

    private void e(Context context) {
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, this.d, true);
        this.e = createWXAPI;
        createWXAPI.registerApp(this.d);
    }

    int a(fdu fduVar, int i) {
        ReleaseLogUtil.e("Share_WeChatShareManager", "shareByWeChat enter: shareType is ", Integer.valueOf(i));
        if (!d()) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "shareByWeChat is not installed");
            Toast.makeText(this.c, R.string._2130839536_res_0x7f0207f0, 0).show();
            return 1;
        }
        if (fduVar == null) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "shareByWeChat get NULL shareContent!");
            return -1;
        }
        int u = fduVar.u();
        if (u == 0) {
            d(i, fduVar);
        } else if (u == 1) {
            c(i, fduVar);
        } else if (u == 2) {
            b(i, fduVar);
        } else if (u == 4) {
            a(i, fduVar);
        } else if (u == 5) {
            e(i, fduVar);
        } else {
            switch (u) {
                case 8:
                case 9:
                    f(fduVar);
                    break;
                case 10:
                case 11:
                    a(fduVar);
                    break;
                default:
                    ReleaseLogUtil.c("Share_WeChatShareManager", "shareByWeChat unknown WeChat shareType!");
                    return -1;
            }
        }
        return 0;
    }

    private void d(int i, fdu fduVar) {
        if (this.e == null || fduVar == null) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "ERROR mIwxApi in shareText()!");
            return;
        }
        String q = fduVar.q();
        WXTextObject wXTextObject = new WXTextObject();
        wXTextObject.text = q;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = q;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = mwd.c("textshare");
        req.message = wXMediaMessage;
        req.scene = i;
        LogUtil.a("Share_WeChatShareManager", "shareText mIwxApi.sendReq(req);");
        this.e.sendReq(req);
    }

    private void e(int i, fdu fduVar) {
        if (i != 1) {
            if (i == 0) {
                a(i, fduVar);
                return;
            } else {
                ReleaseLogUtil.c("Share_WeChatShareManager", "shareMultipleImage Unknown WeChat share type:", Integer.valueOf(i));
                return;
            }
        }
        long a2 = a();
        if (a2 < 60703) {
            d(fduVar);
            return;
        }
        if (a2 == 60703) {
            b(fduVar);
        } else if (a2 >= 70000) {
            e(fduVar);
        } else {
            ReleaseLogUtil.c("Share_WeChatShareManager", "shareMultipleImage WeChat version unknown");
        }
    }

    private void b(fdu fduVar) {
        ArrayList<Uri> a2 = mwd.a(this.c, fduVar, 2);
        if (a2 != null && !a2.isEmpty()) {
            int size = a2.size();
            final ArrayList arrayList = new ArrayList(1);
            arrayList.add(a2.get(0));
            int i = size - 1;
            new NoTitleCustomAlertDialog.Builder(this.c).e(this.c.getResources().getQuantityString(R.plurals._2130903514_res_0x7f0301da, i, Integer.valueOf(i))).czE_(this.c.getString(R.string._2130850332_res_0x7f02321c), new View.OnClickListener() { // from class: mvg.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    mvg.this.e((ArrayList<Uri>) arrayList);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e().show();
            return;
        }
        ReleaseLogUtil.c("Share_WeChatShareManager", "guideToMomentMultiImage imageUriList is empty");
    }

    private void e(fdu fduVar) {
        ArrayList<Uri> a2 = mwd.a(this.c, fduVar, 1);
        if (a2 != null && !a2.isEmpty()) {
            int size = a2.size();
            String quantityString = this.c.getResources().getQuantityString(R.plurals._2130903514_res_0x7f0301da, size, Integer.valueOf(size));
            new NoTitleCustomAlertDialog.Builder(this.c).e(quantityString).czE_(this.c.getString(R.string._2130850332_res_0x7f02321c), new View.OnClickListener() { // from class: mvg.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    mvg.this.b();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e().show();
            return;
        }
        ReleaseLogUtil.c("Share_WeChatShareManager", "guideToWeChatMultiImage imageUriList is empty");
    }

    private void d(fdu fduVar) {
        ArrayList<Uri> a2 = mwd.a(this.c, fduVar, 0);
        if (a2 != null && !a2.isEmpty()) {
            e(a2);
        } else {
            ReleaseLogUtil.c("Share_WeChatShareManager", "directShareMultiImage imageUriList is empty");
        }
    }

    private long a() {
        long j = 0;
        try {
            String str = this.c.getPackageManager().getPackageInfo("com.tencent.mm", 0).versionName;
            if (str.contains(".")) {
                int length = str.split("\\.").length;
                long j2 = PreConnectManager.CONNECT_INTERNAL;
                for (int i = 0; i < length; i++) {
                    j += Integer.parseInt(r3[i]) * j2;
                    j2 /= 100;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "getWeChatVersion fail ", e.getMessage());
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "getWeChatVersion fail: NumberFormatException");
        }
        ReleaseLogUtil.e("Share_WeChatShareManager", "getWeChatVersion is ", Long.valueOf(j));
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ArrayList<Uri> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "shareMomentMultiImage get images failed");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND_MULTIPLE");
        intent.addFlags(268435456);
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        intent.setType(Constants.IMAGE_TYPE);
        intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI"));
        try {
            this.c.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "shareMomentMultiImage err: ", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(268435456);
        intent.setComponent(componentName);
        try {
            this.c.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "jumpToWeChatLauncher err: ", e.getMessage());
        }
    }

    private void c(int i, fdu fduVar) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap cJu_;
        Bitmap bitmap3;
        LogUtil.a("Share_WeChatShareManager", "sharePicture()");
        if (this.e == null || fduVar == null || fduVar.awm_() == null) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "ERROR mIwxApi in sharePicture()");
            return;
        }
        if (fduVar.awm_().isRecycled()) {
            ReleaseLogUtil.d("Share_WeChatShareManager", "ERROR sharePicture is recycled");
            return;
        }
        try {
            bitmap = fduVar.awm_().copy(Bitmap.Config.RGB_565, true);
        } catch (OutOfMemoryError unused) {
            ReleaseLogUtil.d("Share_WeChatShareManager", "sharePicture copy bitmap fail: OutOfMemoryError ");
            bitmap = null;
        }
        if (bitmap == null) {
            ReleaseLogUtil.d("Share_WeChatShareManager", "sharePicture fail bmp is null");
            return;
        }
        if (fduVar.h() && (bitmap3 = this.f15199a) != null) {
            bitmap2 = nrf.cHm_(this.c, bitmap, bitmap3);
        } else {
            LogUtil.a("Share_WeChatShareManager", "let shareBitmap = originBitmap;");
            bitmap2 = bitmap;
        }
        if (bitmap2 == null) {
            ReleaseLogUtil.e("Share_WeChatShareManager", "sharePicture fail: new shareBitmap is null");
            return;
        }
        if (fduVar.ab()) {
            cJu_ = nrf.cJw_(bitmap2, fduVar.g());
        } else {
            cJu_ = nrf.cJu_(bitmap2);
        }
        if (cJu_ == null) {
            ReleaseLogUtil.d("Share_WeChatShareManager", "sharePicture fail: after compressed, new shareBitmap is null");
            return;
        }
        int cqn_ = mwd.cqn_(cJu_);
        ReleaseLogUtil.e("Share_WeChatShareManager", "sharePicture WeChatInteractors bitmap size = ", Integer.valueOf(cqn_));
        if ((cqn_ / 1024) / 1024 >= 1) {
            String str = jcu.f + "wechat_share_pic.jpg";
            nrf.cJt_(cJu_, str);
            fduVar.d(str);
            a(i, fduVar);
            return;
        }
        cql_(i, cJu_, bitmap);
    }

    private void cql_(int i, Bitmap bitmap, Bitmap bitmap2) {
        WXImageObject wXImageObject = new WXImageObject(bitmap);
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.thumbData = nrf.cHV_(bitmap2, true);
        c(wXMediaMessage, i);
    }

    private void a(final int i, final fdu fduVar) {
        LogUtil.a("Share_WeChatShareManager", "sharePictureByPath()");
        if (this.e == null || fduVar == null || fduVar.i() == null) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "sharePictureByPath param invalid ", fduVar);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mvf
                @Override // java.lang.Runnable
                public final void run() {
                    mvg.this.b(fduVar, i);
                }
            });
        }
    }

    /* synthetic */ void b(fdu fduVar, int i) {
        String i2 = fduVar.i();
        e(i2);
        if (mwd.d() && e()) {
            i2 = d(this.c, new File(i2));
        }
        if (TextUtils.isEmpty(i2)) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "sharePictureByPath imgPath is null");
            return;
        }
        WXImageObject wXImageObject = new WXImageObject();
        wXImageObject.setImagePath(i2);
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.thumbData = nrf.cHV_(nrf.cHB_(fduVar.i()), true);
        c(wXMediaMessage, i);
    }

    private void c(WXMediaMessage wXMediaMessage, int i) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = mwd.c("imgshareappdata");
        req.message = wXMediaMessage;
        req.scene = i;
        LogUtil.a("Share_WeChatShareManager", "sendApiShareMessage mIwxApi.sendReq(req);");
        this.e.sendReq(req);
    }

    private void b(int i, fdu fduVar) {
        if (this.e == null || fduVar == null) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "ERROR mIwxApi in shareWebPage()");
            return;
        }
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = fduVar.y();
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        wXMediaMessage.title = fduVar.t();
        wXMediaMessage.description = fduVar.q();
        Bitmap awm_ = fduVar.awm_();
        if (awm_ == null || awm_.isRecycled()) {
            wXMediaMessage.thumbData = nrf.cHp_(BitmapFactory.decodeResource(this.c.getResources(), R.mipmap._2131820779_res_0x7f1100eb), false);
            ReleaseLogUtil.e("Share_WeChatShareManager", "shareWebPage() thumb is null");
        } else {
            wXMediaMessage.thumbData = nrf.cHp_(nrf.cJx_(awm_, 300, 300), false);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = mwd.c("webpage");
        req.message = wXMediaMessage;
        req.scene = i;
        ReleaseLogUtil.e("Share_WeChatShareManager", "shareWebPage mIwxApi.sendReq(req);");
        this.e.sendReq(req);
    }

    private void e(String str) {
        LogUtil.a("Share_WeChatShareManager", "ensureMaxHeight start");
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "ensureMaxHeight fail:orgFilePath is null");
            return;
        }
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            if (decodeFile == null) {
                ReleaseLogUtil.e("Share_WeChatShareManager", "ensureMaxHeight decodeFile from orgFilePath return null");
                return;
            }
            int height = decodeFile.getHeight();
            Matrix matrix = new Matrix();
            if (height > 50240) {
                ReleaseLogUtil.e("Share_WeChatShareManager", "ensureMaxHeight height exceeds, rescale it");
                float f = 50240.0f / height;
                matrix.setScale(f, f);
                nrf.cJt_(Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true), str);
            }
        } catch (IllegalArgumentException e) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "ensureMaxHeight IllegalArgumentException: ", e.getMessage());
        } catch (OutOfMemoryError e2) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "ensureMaxHeight OutOfMemoryError: ", e2.getMessage());
        }
    }

    private boolean d() {
        IWXAPI iwxapi = this.e;
        if (iwxapi != null) {
            return iwxapi.isWXAppInstalled();
        }
        return false;
    }

    private void f(fdu fduVar) {
        Uri uri;
        LogUtil.a("Share_WeChatShareManager", "shareVideo enter");
        if (fduVar.u() == 8) {
            String x = fduVar.x();
            if (TextUtils.isEmpty(x)) {
                return;
            }
            if (mwd.d() && e()) {
                uri = Uri.parse(d(this.c, new File(x)));
            } else {
                uri = Uri.fromFile(new File(x));
            }
        } else if (fduVar.u() == 9) {
            uri = fduVar.awo_();
        } else {
            ReleaseLogUtil.d("Share_WeChatShareManager", "shareVideo is other share type");
            uri = null;
        }
        if (uri != null && !TextUtils.isEmpty(uri.toString())) {
            ReleaseLogUtil.e("Share_WeChatShareManager", "videoUri:", uri.getPath());
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("video/*");
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI"));
            intent.addFlags(268435456);
            intent.setFlags(1);
            intent.setFlags(2);
            try {
                this.c.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                ReleaseLogUtil.c("Share_WeChatShareManager", "shareVideo ActivityNotFoundException: ", e.getMessage());
                return;
            }
        }
        ReleaseLogUtil.c("Share_WeChatShareManager", "shareVideo videoUri is null");
    }

    private void a(final fdu fduVar) {
        LogUtil.a("Share_WeChatShareManager", "shareFileByPath()");
        if (this.e == null || fduVar == null || fduVar.e() == null) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "shareFileByPath param invalid ", fduVar);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mvi
                @Override // java.lang.Runnable
                public final void run() {
                    mvg.this.c(fduVar);
                }
            });
        }
    }

    /* synthetic */ void c(fdu fduVar) {
        String e = fduVar.e();
        if (mwd.d() && e()) {
            e = d(this.c, new File(e));
        }
        if (TextUtils.isEmpty(e)) {
            ReleaseLogUtil.c("Share_WeChatShareManager", "shareFile filePath is null");
            return;
        }
        WXFileObject wXFileObject = new WXFileObject();
        wXFileObject.setFilePath(e);
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXFileObject;
        Bitmap awn_ = fduVar.awn_();
        if (awn_ != null) {
            wXMediaMessage.setThumbImage(awn_);
        }
        wXMediaMessage.title = fduVar.t();
        c(wXMediaMessage, 0);
    }

    private boolean e() {
        return this.e.getWXAppSupportAPI() >= 654314752;
    }

    private String d(Context context, File file) {
        if (file == null || !file.exists()) {
            ReleaseLogUtil.d("Share_WeChatShareManager", "context is null or file is not exists");
            return "";
        }
        Uri cqv_ = mwd.cqv_(context, file);
        if (cqv_ == null) {
            ReleaseLogUtil.d("Share_WeChatShareManager", "getFileUri contentUri is null");
            return "";
        }
        context.grantUriPermission("com.tencent.mm", cqv_, 1);
        return cqv_.toString();
    }
}
