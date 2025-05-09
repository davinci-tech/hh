package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import com.huawei.haf.application.BaseApplication;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes7.dex */
public class fbc {
    private static MediaPlayer b;
    private static AudioManager d;

    public static void a(Context context) {
        if (d == null) {
            Object systemService = context.getSystemService(PresenterUtils.AUDIO);
            if (!(systemService instanceof AudioManager)) {
                LogUtil.a("RecognizeUtil", "playCaptureSound failed, service error: ", systemService);
                return;
            }
            d = (AudioManager) systemService;
        }
        int streamVolume = d.getStreamVolume(5);
        if (streamVolume <= 0) {
            LogUtil.c("RecognizeUtil", "playCaptureSound return, silent volume: ", Integer.valueOf(streamVolume));
            return;
        }
        if (b == null) {
            b = MediaPlayer.create(context, Uri.parse("file:///system/media/audio/ui/camera_click.ogg"));
        }
        MediaPlayer mediaPlayer = b;
        if (mediaPlayer == null) {
            LogUtil.a("RecognizeUtil", "playCaptureSound failed, mediaPlayer is null");
        } else {
            mediaPlayer.start();
        }
    }

    public static void d() {
        MediaPlayer mediaPlayer = b;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            b = null;
        }
    }

    public static Bitmap avg_(Bitmap bitmap, float f) {
        if (bitmap == null) {
            LogUtil.e("RecognizeUtil", "rotateBitmap bitmapOri is null");
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static int avf_(Uri uri) {
        Cursor query = BaseApplication.e().getContentResolver().query(uri, new String[]{ParamConstants.Param.ORIENTATION}, null, null, null);
        int i = 0;
        if (query == null) {
            return 0;
        }
        try {
            if (query.getCount() != 1) {
                return 0;
            }
            query.moveToFirst();
            i = query.getInt(0);
            query.close();
            return i;
        } catch (IllegalStateException e) {
            ReleaseLogUtil.c("RecognizeUtil", "getAlbumPicOrientation failed, e: " + e);
            return i;
        }
    }
}
