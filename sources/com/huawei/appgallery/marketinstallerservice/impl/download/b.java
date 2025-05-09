package com.huawei.appgallery.marketinstallerservice.impl.download;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.AsyncTask;
import androidx.core.content.FileProvider;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec;
import com.huawei.appgallery.marketinstallerservice.ui.MarketDownloadActivity;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import defpackage.agj;
import defpackage.agr;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public class b extends AsyncTask<Void, Void, Boolean> {

    /* renamed from: a, reason: collision with root package name */
    private InstallParamSpec f1880a;
    private a b;
    private String c;

    public interface a {
        void a(boolean z);

        Context b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        agr.c("MarketInstallTask", "start install task result:" + bool);
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(bool.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Void... voidArr) {
        PendingIntent activity;
        a aVar = this.b;
        if (aVar == null) {
            agr.e("MarketInstallTask", "callback is null!");
            return false;
        }
        File file = new File(agj.a(aVar.b()));
        if (!file.exists()) {
            agr.c("MarketInstallTask", "download file is empty!");
            return false;
        }
        PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
        PackageInstaller packageInstaller = this.b.b().getPackageManager().getPackageInstaller();
        PackageInstaller.Session session = null;
        try {
            try {
                int createSession = packageInstaller.createSession(sessionParams);
                session = packageInstaller.openSession(createSession);
                boolean hK_ = hK_(file, session);
                if (hK_) {
                    InstallParamSpec installParamSpec = this.f1880a;
                    if (installParamSpec == null || !installParamSpec.isSilentDownload()) {
                        activity = PendingIntent.getActivity(this.b.b(), createSession, new Intent(this.b.b(), (Class<?>) MarketDownloadActivity.class), AMapEngineUtils.HALF_MAX_P20_WIDTH);
                    } else {
                        Intent intent = new Intent(this.b.b(), (Class<?>) MarketInstallReceiver.class);
                        intent.putExtra("callback_key", this.c);
                        activity = PendingIntent.getBroadcast(this.b.b(), createSession, intent, AMapEngineUtils.HALF_MAX_P20_WIDTH);
                    }
                    session.commit(activity.getIntentSender());
                }
                if (session != null) {
                    session.close();
                }
                return Boolean.valueOf(hK_);
            } catch (Exception unused) {
                agr.e("MarketInstallTask", "start install Exception!");
                if (session != null) {
                    session.close();
                }
                return false;
            }
        } catch (Throwable th) {
            if (session != null) {
                session.close();
            }
            throw th;
        }
    }

    public static boolean e(Context context) {
        try {
        } catch (Exception unused) {
            agr.c("MarketInstallTask", "has not silent install permission Exception!");
        }
        if (context.getPackageManager().checkPermission("android.permission.INSTALL_PACKAGES", context.getPackageName()) == 0) {
            return true;
        }
        agr.c("MarketInstallTask", "has not silent install permission!");
        return false;
    }

    private boolean hK_(File file, PackageInstaller.Session session) {
        OutputStream outputStream;
        FileInputStream fileInputStream;
        byte[] bArr = new byte[131072];
        FileInputStream fileInputStream2 = null;
        r2 = null;
        OutputStream outputStream2 = null;
        fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (IOException e) {
            e = e;
            outputStream = null;
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            outputStream2 = session.openWrite("MarketInstallService", 0L, file.length());
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    session.fsync(outputStream2);
                    c(fileInputStream, outputStream2);
                    return true;
                }
                outputStream2.write(bArr, 0, read);
            }
        } catch (IOException e2) {
            e = e2;
            outputStream = outputStream2;
            fileInputStream2 = fileInputStream;
            try {
                agr.a("MarketInstallTask", "copyApk apk error", e);
                c(fileInputStream2, outputStream);
                return false;
            } catch (Throwable th2) {
                th = th2;
                c(fileInputStream2, outputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = outputStream2;
            fileInputStream2 = fileInputStream;
            c(fileInputStream2, outputStream);
            throw th;
        }
    }

    private void c(InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                agr.c("MarketInstallTask", "fis close Exception");
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused2) {
                agr.c("MarketInstallTask", "fos close Exception");
            }
        }
    }

    public static Intent hJ_(Context context) {
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setDataAndType(FileProvider.getUriForFile(context, context.getPackageName() + ".marketinstall.fileprovider", new File(agj.a(context))), "application/vnd.android.package-archive");
        intent.addFlags(1);
        intent.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
        intent.putExtra("android.intent.extra.RETURN_RESULT", true);
        return intent;
    }

    public b(a aVar, InstallParamSpec installParamSpec, String str) {
        this.b = aVar;
        this.f1880a = installParamSpec;
        this.c = str;
    }

    public b(a aVar) {
        this.f1880a = null;
        this.c = "";
        this.b = aVar;
    }
}
