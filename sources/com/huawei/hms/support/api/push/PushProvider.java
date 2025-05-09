package com.huawei.hms.support.api.push;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

/* loaded from: classes9.dex */
public class PushProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        if (uri == null || !uri.toString().endsWith(WatchFaceConstant.XML_SUFFIX)) {
            return null;
        }
        return "xml";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        HMSLog.i("PushProvider", "use sdk PushProvider openFile");
        if (!"xml".equals(getType(uri))) {
            HMSLog.w("PushProvider", "Incorrect file uri");
            throw new FileNotFoundException(uri.getPath());
        }
        File file = new File(((Context) Objects.requireNonNull(getContext())).createDeviceProtectedStorageContext().getDataDir() + "/shared_prefs/push_notify_flag.xml");
        if (file.exists()) {
            return ParcelFileDescriptor.open(file, 268435456);
        }
        File file2 = new File(getContext().getDataDir() + "/shared_prefs/push_notify_flag.xml");
        if (file2.exists()) {
            return ParcelFileDescriptor.open(file2, 268435456);
        }
        throw new FileNotFoundException(uri.getPath());
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
