package com.huawei.hms.adapter.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.manager.AgHmsUpdateInfo;
import com.huawei.hms.update.manager.UpdateManager;
import com.huawei.hms.update.ui.AbsUpdateWizard;
import com.huawei.hms.update.ui.UpdateBean;
import com.huawei.hms.utils.HEX;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IOUtils;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.hwcloudjs.g.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

/* loaded from: classes9.dex */
public class InstallerAdapter {
    public static String sHmsApkHash;

    private boolean a(Activity activity) {
        FileOutputStream fileOutputStream;
        StringBuilder sb = new StringBuilder("com.huawei.hms");
        String str = File.separator;
        sb.append(str);
        sb.append("hms.apk");
        InputStream inputStream = null;
        try {
            try {
                InputStream open = activity.getApplicationContext().getAssets().open(sb.toString());
                try {
                    if (open == null) {
                        HMSLog.e("InstallerAdapter", "open hms.apk error, inputStream is null");
                        IOUtils.closeQuietly(open);
                        IOUtils.closeQuietly((OutputStream) null);
                        return false;
                    }
                    File file = new File(activity.getApplicationContext().getExternalCacheDir().getCanonicalPath() + str + a.c + str + "hms.apk");
                    if (file.getParentFile() != null && !file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                        HMSLog.e("InstallerAdapter", "failed to create the directory for storing the HMS");
                        IOUtils.closeQuietly(open);
                        IOUtils.closeQuietly((OutputStream) null);
                        return false;
                    }
                    if (!file.exists() && !file.createNewFile()) {
                        HMSLog.e("InstallerAdapter", "failed to create the dest file");
                        IOUtils.closeQuietly(open);
                        IOUtils.closeQuietly((OutputStream) null);
                        return false;
                    }
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[4096];
                        int i = 0;
                        while (true) {
                            int read = open.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            i += read;
                            messageDigest.update(bArr, 0, read);
                            fileOutputStream.write(bArr, 0, read);
                        }
                        if (i > 0) {
                            sHmsApkHash = HEX.encodeHexString(messageDigest.digest(), true);
                            HMSLog.i("InstallerAdapter", "hms.apk hash: " + sHmsApkHash);
                        }
                        HMSLog.i("InstallerAdapter", "copy hms.apk, size: " + i);
                        IOUtils.closeQuietly(open);
                        IOUtils.closeQuietly((OutputStream) fileOutputStream);
                        return true;
                    } catch (IOException e) {
                        e = e;
                        inputStream = open;
                        HMSLog.e("InstallerAdapter", "copy assets hms.apk error: " + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly((OutputStream) fileOutputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = open;
                        HMSLog.e("InstallerAdapter", "copy assets hms.apk throwable: " + th.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly((OutputStream) fileOutputStream);
                        return false;
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
            } catch (Throwable th3) {
                IOUtils.closeQuietly((InputStream) null);
                IOUtils.closeQuietly((OutputStream) "hms.apk");
                throw th3;
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    public void checkHmsUpdateInfo(Context context) {
        if (context == null) {
            HMSLog.e("InstallerAdapter", "checkHmsUpdateInfo: context is null");
        } else {
            HMSLog.i("InstallerAdapter", "enter checkHmsUpdateInfo <context>");
            AgHmsUpdateInfo.getInstance().checkHmsAuthAndUpdateInfo(context);
        }
    }

    public UpdateBean setUpdateBean(Activity activity, int i, boolean z) {
        String str;
        if (activity != null) {
            str = HMSPackageManager.getInstance(activity.getApplicationContext()).getHMSPackageName();
            if (TextUtils.isEmpty(str)) {
                str = "com.huawei.hwid";
            }
        } else {
            HMSLog.w("InstallerAdapter", "activity is null");
            str = null;
        }
        UpdateBean updateBean = new UpdateBean();
        updateBean.setHmsOrApkUpgrade(true);
        updateBean.setResolutionInstallHMS(z);
        HMSLog.i("InstallerAdapter", "target HMS Core packageName is " + str);
        updateBean.setClientPackageName(str);
        updateBean.setClientVersionCode(i);
        updateBean.setClientAppId("C10132067");
        if (ResourceLoaderUtil.getmContext() == null && activity != null) {
            ResourceLoaderUtil.setmContext(activity.getApplicationContext());
        }
        updateBean.setClientAppName(ResourceLoaderUtil.getString("hms_update_title"));
        return updateBean;
    }

    public void startUpdateHms(Activity activity, UpdateBean updateBean, int i) {
        if (SystemUtils.isEMUI() || !a(activity)) {
            HMSLog.i("InstallerAdapter", "old framework HMSCore upgrade process");
            UpdateManager.startUpdate(activity, i, updateBean);
        } else {
            Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, AbsUpdateWizard.getClassName(8));
            intentStartBridgeActivity.putExtra(BridgeActivity.EXTRA_DELEGATE_UPDATE_INFO, updateBean);
            activity.startActivityForResult(intentStartBridgeActivity, i);
        }
    }

    public void checkHmsUpdateInfo(Activity activity) {
        if (activity == null) {
            HMSLog.e("InstallerAdapter", "checkHmsUpdateInfo: activity is null");
        } else {
            HMSLog.i("InstallerAdapter", "enter checkHmsUpdateInfo <activity>");
            AgHmsUpdateInfo.getInstance().checkHmsAuthAndUpdateInfo(activity);
        }
    }
}
