package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

/* loaded from: classes8.dex */
public class aej {
    public static void gl_(File file, String str, Bitmap bitmap) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file.getCanonicalFile(), str));
            try {
                if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                    throw new IOException("compress bitmap failed");
                }
                fileOutputStream.close();
            } finally {
            }
        } catch (IOException e) {
            Log.e("FileUtils", "save bitmap failed, file name: " + str);
            throw e;
        }
    }

    public static Bitmap gk_(File file, String str) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        try {
            return (Bitmap) Optional.ofNullable(BitmapFactory.decodeFile(file.getCanonicalFile() + File.separator + str, options)).orElseThrow(new Supplier() { // from class: ael
                @Override // java.util.function.Supplier
                public final Object get() {
                    return aej.b();
                }
            });
        } catch (IOException e) {
            Log.e("FileUtils", "load bitmap failed, file name: " + str);
            throw e;
        }
    }

    static /* synthetic */ IOException b() {
        return new IOException("image data could not be decoded");
    }
}
