package androidx.room.util;

import androidx.core.location.LocationRequestCompat;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/* loaded from: classes2.dex */
public class FileUtil {
    public static void copy(ReadableByteChannel readableByteChannel, FileChannel fileChannel) throws IOException {
        try {
            fileChannel.transferFrom(readableByteChannel, 0L, LocationRequestCompat.PASSIVE_INTERVAL);
            fileChannel.force(false);
        } finally {
            readableByteChannel.close();
            fileChannel.close();
        }
    }

    private FileUtil() {
    }
}
