package com.huawei.health.h5pro.jsbridge.system.media;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.huawei.health.h5pro.utils.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class H5ProMediaScanner {
    public MediaScannerConnection b;

    public interface OnScanFileCallback extends MediaScannerConnection.OnScanCompletedListener {
        void onScanFailure(String str);
    }

    public void scanFile(Context context, final String str, final String[] strArr, final OnScanFileCallback onScanFileCallback) {
        final int[] iArr = {0};
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection((Context) new WeakReference(context).get(), new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.huawei.health.h5pro.jsbridge.system.media.H5ProMediaScanner.1
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str2, Uri uri) {
                int[] iArr2 = iArr;
                int i = iArr2[0] + 1;
                iArr2[0] = i;
                if (i >= strArr.length) {
                    onScanFileCallback.onScanCompleted(str2, uri);
                    H5ProMediaScanner.this.b.disconnect();
                    LogUtil.i("H5ProMediaScanner", "scanFile: onScanCompleted");
                }
            }

            @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
            public void onMediaScannerConnected() {
                LogUtil.i("H5ProMediaScanner", "scanFile: onMediaScannerConnected");
                try {
                    if (strArr.length == 0) {
                        e(str, null);
                        return;
                    }
                    int i = 0;
                    while (true) {
                        String[] strArr2 = strArr;
                        if (i >= strArr2.length) {
                            return;
                        }
                        e(str, strArr2[i]);
                        i++;
                    }
                } catch (IllegalStateException e) {
                    LogUtil.w("H5ProMediaScanner", "onMediaScannerConnected: exception -> " + e.getMessage());
                }
            }

            private void e(String str2, String str3) {
                if (H5ProMediaScanner.this.b.isConnected()) {
                    H5ProMediaScanner.this.b.scanFile(str2, str3);
                }
            }
        });
        this.b = mediaScannerConnection;
        mediaScannerConnection.connect();
    }

    public void disconnect() {
        MediaScannerConnection mediaScannerConnection = this.b;
        if (mediaScannerConnection != null) {
            mediaScannerConnection.disconnect();
        }
    }
}
