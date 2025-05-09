package com.huawei.health.h5pro.jsbridge.system.shareplus;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.jsbridge.system.share.AndroidShare;
import com.huawei.health.h5pro.jsbridge.system.share.Share;
import com.huawei.health.h5pro.jsbridge.system.share.ShareEntry;
import com.huawei.health.h5pro.jsbridge.system.share.ShareImageObj;
import com.huawei.health.h5pro.jsbridge.system.storage.AndroidStorage;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.view.FloatingBarAdapter;
import com.huawei.health.h5pro.view.FloatingBarItem;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.videoedit.gles.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SharePlusImpl implements SharePlus {
    public static long b;

    /* renamed from: a, reason: collision with root package name */
    public H5ProInstance f2425a;
    public Analyzer c;
    public Context d;

    @Override // com.huawei.health.h5pro.jsbridge.system.shareplus.SharePlus
    public void shareFile(SharePlusParam sharePlusParam) {
        File file;
        if (Constant.TEXT.equalsIgnoreCase(sharePlusParam.getType())) {
            file = e(sharePlusParam);
        } else if ("base64".equalsIgnoreCase(sharePlusParam.getType())) {
            file = d(sharePlusParam);
        } else {
            String filePath = sharePlusParam.getFilePath();
            if (!GeneralUtil.isSafePath(filePath)) {
                LogUtil.w("H5PRO_SharePlusImpl", "shareFile: invalid file path");
                return;
            }
            file = new File(filePath);
        }
        if (file == null || !file.exists()) {
            LogUtil.w("H5PRO_SharePlusImpl", "shareFile: the file does not exist");
            return;
        }
        String mimeType = sharePlusParam.getMimeType();
        if (TextUtils.isEmpty(mimeType) || !mimeType.startsWith(Constant.TYPE_PHOTO)) {
            b(file, sharePlusParam);
        } else {
            c(file, sharePlusParam);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.shareplus.SharePlus
    public void destroy() {
        this.d = null;
        this.f2425a = null;
        this.c = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void XO_(ActivityInfo activityInfo, Intent intent, SharePlusParam sharePlusParam, ShareTypeEnum shareTypeEnum) {
        if (XM_(activityInfo.packageName, intent)) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(activityInfo.packageName);
            intent2.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
            intent2.addFlags(268435457);
            this.d.grantUriPermission(activityInfo.packageName, intent2.getData(), 1);
            try {
                this.d.startActivity(intent2);
            } catch (ActivityNotFoundException e) {
                LogUtil.e("H5PRO_SharePlusImpl", "shareToTarget: exception -> " + e.getMessage());
            }
            c(shareTypeEnum, sharePlusParam);
        }
    }

    private void c(File file, SharePlusParam sharePlusParam) {
        ShareImageObj shareImageObj = new ShareImageObj();
        shareImageObj.setModuleId(sharePlusParam.getModuleId());
        shareImageObj.setShareBiMap(sharePlusParam.getShareBiMap());
        shareImageObj.setIsReport(sharePlusParam.isReport());
        shareImageObj.setExtra(sharePlusParam.getExtra());
        try {
            shareImageObj.setFilePath(file.getCanonicalPath());
            Share shareImpl = ShareEntry.getShareImpl();
            if (shareImpl == null) {
                shareImpl = new AndroidShare(this.d);
            }
            shareImpl.shareImage(shareImageObj, new Share.ShareCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.shareplus.SharePlusImpl.1
                @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                public void onSuccess() {
                    LogUtil.i("H5PRO_SharePlusImpl", "shareImageCompact: success");
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.share.Share.ShareCallback
                public void onFailure(int i, String str) {
                    LogUtil.w("H5PRO_SharePlusImpl", "shareImageCompact(onFailure): " + i + " - " + str);
                }
            });
        } catch (IOException unused) {
            LogUtil.e("H5PRO_SharePlusImpl", "share compact fail: file invalid");
        }
    }

    private void b(File file, SharePlusParam sharePlusParam) {
        Intent XL_ = XL_(file, sharePlusParam.getMimeType());
        LinkedList linkedList = new LinkedList();
        List<FloatingBarItem> XK_ = XK_(XL_, sharePlusParam);
        if (!XK_.isEmpty()) {
            linkedList.addAll(XK_);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            linkedList.add(XH_(XL_, file.getName(), sharePlusParam));
        }
        linkedList.add(XI_(XL_, sharePlusParam));
        this.f2425a.getAppLoadListener().onFloatingBarRequested(new FloatingBarAdapter(linkedList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(File file, String str, String str2) {
        String str3 = Environment.DIRECTORY_DOCUMENTS + File.separator + "Health";
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str2);
        contentValues.put("mime_type", str);
        contentValues.put("relative_path", str3);
        contentValues.put("title", str2);
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        ContentResolver contentResolver = this.d.getContentResolver();
        Uri insert = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues);
        try {
            ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(insert, "w");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(openFileDescriptor.getFileDescriptor());
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, read);
                            }
                        }
                        fileOutputStream.flush();
                        contentValues.clear();
                        contentValues.put("is_pending", (Integer) 0);
                        contentResolver.update(insert, contentValues, null, null);
                        OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                        if (openOutputStream == null) {
                            try {
                                throw new IOException("Failed to get output stream.");
                            } finally {
                            }
                        }
                        openOutputStream.close();
                        Toast.makeText(this.d, this.d.getString(R.string._2130842374_res_0x7f021306) + " " + str3 + File.separator + XJ_(insert), 0).show();
                        fileInputStream.close();
                        fileOutputStream.close();
                        openFileDescriptor.close();
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            contentResolver.delete(insert, null, null);
            LogUtil.e("H5PRO_SharePlusImpl", "save meet IOException");
            Toast.makeText(this.d, R.string._2130842375_res_0x7f021307, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ShareTypeEnum shareTypeEnum, SharePlusParam sharePlusParam) {
        if (shareTypeEnum.shareType != -1) {
            Map<String, Object> shareBiMap = sharePlusParam.getShareBiMap();
            if (shareBiMap == null) {
                shareBiMap = new LinkedHashMap<>();
            }
            shareBiMap.put("type", Integer.valueOf(shareTypeEnum.shareType));
            shareBiMap.put(Constants.BI_MODULE_ID, sharePlusParam.getModuleId());
            String json = GsonUtil.toJson(shareBiMap);
            LogUtil.d("H5PRO_SharePlusImpl", "putShareFileBiEvent: " + json);
            if (this.c == null) {
                Analyzer analyzer = new Analyzer();
                this.c = analyzer;
                H5ProInstance h5ProInstance = this.f2425a;
                if (h5ProInstance != null) {
                    analyzer.setAppInfo(h5ProInstance.getAppInfo());
                }
            }
            this.c.putBiEvent("11000334", json);
            return;
        }
        LogUtil.w("H5PRO_SharePlusImpl", "Platform '" + shareTypeEnum.packageName + "' does not need dotting");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void XN_(Intent intent) {
        if (XM_("", intent)) {
            List<ResolveInfo> queryIntentActivities = this.d.getPackageManager().queryIntentActivities(intent, 65536);
            if (queryIntentActivities != null) {
                Iterator<ResolveInfo> it = queryIntentActivities.iterator();
                while (it.hasNext()) {
                    this.d.grantUriPermission(it.next().activityInfo.packageName, intent.getData(), 1);
                }
            }
            Intent createChooser = Intent.createChooser(intent, "");
            createChooser.addFlags(268435457);
            this.d.startActivity(createChooser);
        }
    }

    private boolean XM_(String str, Intent intent) {
        String valueOf;
        if (TextUtils.equals(ShareTypeEnum.SHARE_TYPE_WECHAT_CHAT.packageName, str) || TextUtils.isEmpty(str)) {
            if (System.currentTimeMillis() - b < 2000) {
                LogUtil.i("H5PRO_SharePlusImpl", "wechatChat: Too frequent clicks");
                return false;
            }
            b = System.currentTimeMillis();
            Serializable serializableExtra = intent.getSerializableExtra("KEY_SHARE_FILE");
            if (!(serializableExtra instanceof File)) {
                LogUtil.w("H5PRO_SharePlusImpl", "wechatChat: shareFileSerializable is null");
                return false;
            }
            File file = (File) serializableExtra;
            String name = file.getName();
            if (TextUtils.isEmpty(name) || !name.contains(".")) {
                valueOf = String.valueOf(System.currentTimeMillis());
            } else {
                valueOf = System.currentTimeMillis() + "." + name.split("\\.")[1];
            }
            File file2 = new File(file.getParentFile(), valueOf);
            if (!file.renameTo(file2) || !file2.exists()) {
                LogUtil.w("H5PRO_SharePlusImpl", "wechatChat: Failed to rename the file");
                return false;
            }
            intent.putExtra("KEY_SHARE_FILE", file2);
            Uri uriFromFile = StorageUtil.getUriFromFile(this.d, file2);
            intent.setDataAndType(uriFromFile, intent.getType());
            intent.putExtra("android.intent.extra.STREAM", uriFromFile);
        }
        return true;
    }

    private Intent XL_(File file, String str) {
        Uri uriFromFile = StorageUtil.getUriFromFile(this.d, file);
        return ShareCompat.IntentBuilder.from((Activity) this.d).setType(str).setStream(uriFromFile).getIntent().setDataAndType(uriFromFile, str).putExtra("KEY_SHARE_FILE", file);
    }

    private List<FloatingBarItem> XK_(final Intent intent, final SharePlusParam sharePlusParam) {
        PackageManager packageManager;
        SharePlusImpl sharePlusImpl = this;
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager2 = sharePlusImpl.d.getPackageManager();
        List<ResolveInfo> queryIntentActivities = sharePlusImpl.d.getPackageManager().queryIntentActivities(intent, 65536);
        if (queryIntentActivities == null) {
            return arrayList;
        }
        ShareTypeEnum[] values = ShareTypeEnum.values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            final ShareTypeEnum shareTypeEnum = values[i];
            Iterator<ResolveInfo> it = queryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    packageManager = packageManager2;
                    break;
                }
                ResolveInfo next = it.next();
                if (shareTypeEnum.isShow(next.activityInfo)) {
                    String string = shareTypeEnum.showNameResourceId == -1 ? "" : sharePlusImpl.d.getResources().getString(shareTypeEnum.showNameResourceId);
                    if (TextUtils.isEmpty(string)) {
                        string = next.loadLabel(packageManager2).toString();
                    }
                    final String str = string;
                    final ActivityInfo activityInfo = next.activityInfo;
                    packageManager = packageManager2;
                    arrayList.add(new FloatingBarItem(str, next.loadIcon(packageManager2), new View.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.shareplus.SharePlusImpl.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            LogUtil.i("H5PRO_SharePlusImpl", "share: " + str);
                            SharePlusImpl.this.XO_(activityInfo, intent, sharePlusParam, shareTypeEnum);
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }));
                }
            }
            i++;
            sharePlusImpl = this;
            packageManager2 = packageManager;
        }
        return arrayList;
    }

    private String XJ_(Uri uri) {
        Cursor query = this.d.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    query.close();
                    return string;
                }
            } catch (Throwable th) {
                try {
                    query.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (query == null) {
            return "";
        }
        query.close();
        return "";
    }

    /* loaded from: classes8.dex */
    public enum ShareTypeEnum {
        SHARE_TYPE_WECHAT_CHAT(1, R.string._2130843623_res_0x7f0217e7, "com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI"),
        SHARE_TYPE_WECHAT_TIME_LINE_UI(2, -1, "com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI"),
        SHARE_TYPE_QQ(-1, R.string._2130850335_res_0x7f02321f, "com.tencent.mobileqq", ""),
        SHARE_TYPE_EMAIL(-1, -1, "com.android.email", ""),
        SHARE_TYPE_SAVE_LOCAL(4, -1, "", ""),
        SHARE_TYPE_MORE(5, -1, "", "");

        public final String className;
        public final String packageName;
        public final int shareType;
        public final int showNameResourceId;

        public boolean isShow(ActivityInfo activityInfo) {
            if (activityInfo == null || TextUtils.isEmpty(this.packageName) || !TextUtils.equals(this.packageName, activityInfo.packageName)) {
                return false;
            }
            return TextUtils.isEmpty(this.className) || TextUtils.equals(this.className, activityInfo.name);
        }

        ShareTypeEnum(int i, int i2, String str, String str2) {
            this.shareType = i;
            this.showNameResourceId = i2;
            this.packageName = str;
            this.className = str2;
        }
    }

    private FloatingBarItem XI_(final Intent intent, final SharePlusParam sharePlusParam) {
        return new FloatingBarItem(this.d.getString(R.string._2130841847_res_0x7f0210f7), ContextCompat.getDrawable(this.d, R.mipmap._2131821011_res_0x7f1101d3), new View.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.shareplus.SharePlusImpl.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.i("H5PRO_SharePlusImpl", "share: system");
                SharePlusImpl.this.XN_(intent);
                SharePlusImpl.this.c(ShareTypeEnum.SHARE_TYPE_MORE, sharePlusParam);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private FloatingBarItem XH_(final Intent intent, final String str, final SharePlusParam sharePlusParam) {
        return new FloatingBarItem(this.d.getString(R.string._2130842376_res_0x7f021308), ContextCompat.getDrawable(this.d, R.mipmap._2131821472_res_0x7f1103a0), new View.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.shareplus.SharePlusImpl.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.i("H5PRO_SharePlusImpl", "share: save");
                Serializable serializableExtra = intent.getSerializableExtra("KEY_SHARE_FILE");
                if (serializableExtra instanceof File) {
                    SharePlusImpl.this.a((File) serializableExtra, intent.getType(), str);
                    SharePlusImpl.this.c(ShareTypeEnum.SHARE_TYPE_SAVE_LOCAL, sharePlusParam);
                } else {
                    LogUtil.w("H5PRO_SharePlusImpl", "share: failed to save the file");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private File e(SharePlusParam sharePlusParam) {
        String fileName = sharePlusParam.getFileName();
        if (!GeneralUtil.isSafePath(fileName)) {
            LogUtil.w("H5PRO_SharePlusImpl", "cacheTextFile: invalid file name");
            return null;
        }
        String str = StorageUtil.getAppFilePath(this.d, this.f2425a) + fileName;
        try {
            new AndroidStorage(this.d).writeText(str, sharePlusParam.getText(), false);
        } catch (IOException unused) {
            LogUtil.e("H5PRO_SharePlusImpl", "cacheTextFile fail");
        }
        return new File(str);
    }

    private File d(SharePlusParam sharePlusParam) {
        String fileName = sharePlusParam.getFileName();
        if (!GeneralUtil.isSafePath(fileName)) {
            LogUtil.w("H5PRO_SharePlusImpl", "cacheTextFile: invalid file name");
            return null;
        }
        String str = StorageUtil.getAppFilePath(this.d, this.f2425a) + fileName;
        try {
            new AndroidStorage(this.d).base64ToFile(str, sharePlusParam.getBase64());
        } catch (IOException unused) {
            LogUtil.e("H5PRO_SharePlusImpl", "cacheTextFile fail");
        }
        return new File(str);
    }

    public SharePlusImpl(Context context, H5ProInstance h5ProInstance) {
        this.d = context;
        this.f2425a = h5ProInstance;
    }
}
