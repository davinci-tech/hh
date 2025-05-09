package com.huawei.hms.hmsscankit;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.hmsscankit.api.IRemoteCreator;
import com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.huawei.hms.ml.scan.HmsScanFrame;
import com.huawei.hms.mlsdk.common.MLFrame;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.y3;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
class b {

    /* renamed from: a, reason: collision with root package name */
    private static volatile IRemoteHmsDecoderDelegate f4633a = null;
    private static final String b = "b";

    static HmsScan[] a(Context context, MLFrame mLFrame, HmsScanAnalyzerOptions hmsScanAnalyzerOptions) {
        HmsScan[] detectWithByteBuffer;
        int i;
        o4.d("scankit mul", "start detectForHmsDector");
        HmsScan[] hmsScanArr = new HmsScan[0];
        if (f4633a == null) {
            IRemoteCreator c = g.c(context);
            if (c == null) {
                return hmsScanArr;
            }
            try {
                f4633a = c.newRemoteHmsDecoderDelegate();
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }
        if (f4633a != null) {
            try {
                DetailRect detailRect = mLFrame.acquireProperty() != null ? new DetailRect(mLFrame.acquireProperty().getWidth(), mLFrame.acquireProperty().getHeight()) : new DetailRect();
                Bundle bundle = new Bundle();
                if (hmsScanAnalyzerOptions != null && (i = hmsScanAnalyzerOptions.mode) != 0) {
                    bundle.putInt(DetailRect.FORMAT_FLAG, i);
                    bundle.putBoolean(DetailRect.PHOTO_MODE, hmsScanAnalyzerOptions.photoMode);
                }
                bundle.putBoolean(DetailRect.PARSE_RESULT, hmsScanAnalyzerOptions.parseResult);
                bundle.putInt(DetailRect.TYPE_TRANS, 3);
                bundle.putBoolean(DetailRect.SUPPORT_ROLLBACK, g.c);
                bundle.putBoolean(DetailRect.USE_APK, g.f4638a);
                bundle.putAll(y3.a(context));
                if (mLFrame.readBitmap() != null) {
                    o4.d("scankit mul", "end detectForHmsDector");
                    detectWithByteBuffer = f4633a.decodeInBitmap(detailRect, ObjectWrapper.wrap(mLFrame.readBitmap()), ObjectWrapper.wrap(bundle));
                } else {
                    detectWithByteBuffer = f4633a.detectWithByteBuffer(detailRect, ObjectWrapper.wrap(mLFrame.acquireGrayByteBuffer()), ObjectWrapper.wrap(bundle));
                }
                f.a(detectWithByteBuffer);
                if (g.a()) {
                    IRemoteCreator c2 = g.c(context);
                    if (c2 == null) {
                        return hmsScanArr;
                    }
                    try {
                        f4633a = c2.newRemoteHmsDecoderDelegate();
                    } catch (RemoteException unused2) {
                        o4.b(b, "RemoteException");
                    }
                    o4.d("scankit mul", "iRemoteDecoderDelegate rollback");
                    detectWithByteBuffer = mLFrame.readBitmap() != null ? f4633a.decodeInBitmap(detailRect, ObjectWrapper.wrap(mLFrame.readBitmap()), ObjectWrapper.wrap(bundle)) : f4633a.detectWithByteBuffer(detailRect, ObjectWrapper.wrap(mLFrame.acquireGrayByteBuffer()), ObjectWrapper.wrap(bundle));
                }
                if (detectWithByteBuffer != null) {
                    return detectWithByteBuffer;
                }
            } catch (RemoteException unused3) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }
        return hmsScanArr;
    }

    static HmsScan[] a(Context context, HmsScanFrame hmsScanFrame, HmsScanAnalyzerOptions hmsScanAnalyzerOptions) {
        HmsScan[] detectWithByteBuffer;
        HmsScan[] detectWithByteBuffer2;
        o4.d("scankit mul", "start detectForHmsDector");
        HmsScan[] hmsScanArr = new HmsScan[0];
        if (f4633a == null) {
            IRemoteCreator c = g.c(context);
            if (c == null) {
                return hmsScanArr;
            }
            try {
                f4633a = c.newRemoteHmsDecoderDelegate();
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }
        if (f4633a != null) {
            try {
                DetailRect detailRect = new DetailRect(hmsScanFrame.getWidth(), hmsScanFrame.getHeight());
                Bundle bundle = new Bundle();
                if (hmsScanAnalyzerOptions != null) {
                    bundle.putInt(DetailRect.FORMAT_FLAG, hmsScanAnalyzerOptions.mode);
                    bundle.putBoolean(DetailRect.PARSE_RESULT, hmsScanAnalyzerOptions.parseResult);
                    bundle.putBoolean(DetailRect.PHOTO_MODE, hmsScanAnalyzerOptions.photoMode);
                }
                bundle.putBoolean(DetailRect.SUPPORT_ROLLBACK, g.c);
                bundle.putBoolean(DetailRect.USE_APK, g.f4638a);
                bundle.putInt(DetailRect.TYPE_TRANS, 3);
                bundle.putBoolean(DetailRect.NEW_VERSION, true);
                bundle.putString(DetailRect.CP_PACKAGE, y3.b(context));
                bundle.putAll(y3.a(context));
                if (hmsScanFrame.getBitmap() != null) {
                    o4.d("scankit mul", "end detectForHmsDector");
                    detectWithByteBuffer = f4633a.decodeInBitmap(detailRect, ObjectWrapper.wrap(hmsScanFrame.getBitmap()), ObjectWrapper.wrap(bundle));
                } else {
                    detectWithByteBuffer = f4633a.detectWithByteBuffer(detailRect, ObjectWrapper.wrap(ByteBuffer.wrap(hmsScanFrame.getYuvImage().getYuvData())), ObjectWrapper.wrap(bundle));
                }
                f.a(detectWithByteBuffer);
                if (g.a()) {
                    IRemoteCreator c2 = g.c(context);
                    if (c2 == null) {
                        return hmsScanArr;
                    }
                    try {
                        f4633a = c2.newRemoteHmsDecoderDelegate();
                    } catch (RemoteException unused2) {
                        o4.b(b, "RemoteException");
                    }
                    o4.d("scankit mul", "iRemoteDecoderDelegate rollback");
                    if (hmsScanFrame.getBitmap() != null) {
                        detectWithByteBuffer2 = f4633a.decodeInBitmap(detailRect, ObjectWrapper.wrap(hmsScanFrame.getBitmap()), ObjectWrapper.wrap(bundle));
                    } else {
                        detectWithByteBuffer2 = f4633a.detectWithByteBuffer(detailRect, ObjectWrapper.wrap(ByteBuffer.wrap(hmsScanFrame.getYuvImage().getYuvData())), ObjectWrapper.wrap(bundle));
                    }
                    detectWithByteBuffer = detectWithByteBuffer2;
                }
                if (detectWithByteBuffer != null) {
                    return detectWithByteBuffer;
                }
            } catch (RemoteException unused3) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }
        return hmsScanArr;
    }
}
