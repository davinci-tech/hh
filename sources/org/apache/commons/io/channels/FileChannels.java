package org.apache.commons.io.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

/* loaded from: classes10.dex */
public final class FileChannels {
    public static boolean contentEquals(FileChannel fileChannel, FileChannel fileChannel2, int i) throws IOException {
        if (Objects.equals(fileChannel, fileChannel2)) {
            return true;
        }
        long size = size(fileChannel);
        long size2 = size(fileChannel2);
        if (size != size2) {
            return false;
        }
        if (size == 0 && size2 == 0) {
            return true;
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i);
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(i);
        do {
            int read = fileChannel.read(allocateDirect);
            int read2 = fileChannel2.read(allocateDirect2);
            allocateDirect.clear();
            allocateDirect2.clear();
            if (read == -1 && read2 == -1) {
                return allocateDirect.equals(allocateDirect2);
            }
            if (read != read2) {
                return false;
            }
        } while (allocateDirect.equals(allocateDirect2));
        return false;
    }

    private static long size(FileChannel fileChannel) throws IOException {
        if (fileChannel != null) {
            return fileChannel.size();
        }
        return 0L;
    }

    private FileChannels() {
    }
}
