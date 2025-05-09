package org.apache.commons.io.file.attribute;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* loaded from: classes10.dex */
public final class FileTimes {
    static final long WINDOWS_EPOCH_OFFSET = -116444736000000000L;
    public static final FileTime EPOCH = FileTime.from(Instant.EPOCH);
    private static final long HUNDRED_NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1) / 100;
    static final long HUNDRED_NANOS_PER_MILLISECOND = TimeUnit.MILLISECONDS.toNanos(1) / 100;

    public static boolean isUnixTime(long j) {
        return -2147483648L <= j && j <= 2147483647L;
    }

    public static FileTime fromUnixTime(long j) {
        return FileTime.from(j, TimeUnit.SECONDS);
    }

    public static boolean isUnixTime(FileTime fileTime) {
        return isUnixTime(toUnixTime(fileTime));
    }

    public static FileTime minusMillis(FileTime fileTime, long j) {
        return FileTime.from(fileTime.toInstant().minusMillis(j));
    }

    public static FileTime minusNanos(FileTime fileTime, long j) {
        return FileTime.from(fileTime.toInstant().minusNanos(j));
    }

    public static FileTime minusSeconds(FileTime fileTime, long j) {
        return FileTime.from(fileTime.toInstant().minusSeconds(j));
    }

    public static FileTime now() {
        return FileTime.from(Instant.now());
    }

    public static Date ntfsTimeToDate(long j) {
        return new Date(Math.floorDiv(Math.addExact(j, WINDOWS_EPOCH_OFFSET), HUNDRED_NANOS_PER_MILLISECOND));
    }

    public static FileTime ntfsTimeToFileTime(long j) {
        long addExact = Math.addExact(j, WINDOWS_EPOCH_OFFSET);
        long j2 = HUNDRED_NANOS_PER_SECOND;
        return FileTime.from(Instant.ofEpochSecond(Math.floorDiv(addExact, j2), Math.floorMod(addExact, j2) * 100));
    }

    public static FileTime plusMillis(FileTime fileTime, long j) {
        return FileTime.from(fileTime.toInstant().plusMillis(j));
    }

    public static FileTime plusNanos(FileTime fileTime, long j) {
        return FileTime.from(fileTime.toInstant().plusNanos(j));
    }

    public static FileTime plusSeconds(FileTime fileTime, long j) {
        return FileTime.from(fileTime.toInstant().plusSeconds(j));
    }

    public static void setLastModifiedTime(Path path) throws IOException {
        Files.setLastModifiedTime(path, now());
    }

    public static Date toDate(FileTime fileTime) {
        if (fileTime != null) {
            return new Date(fileTime.toMillis());
        }
        return null;
    }

    public static FileTime toFileTime(Date date) {
        if (date != null) {
            return FileTime.fromMillis(date.getTime());
        }
        return null;
    }

    public static long toNtfsTime(Date date) {
        return Math.subtractExact(date.getTime() * HUNDRED_NANOS_PER_MILLISECOND, WINDOWS_EPOCH_OFFSET);
    }

    public static long toNtfsTime(FileTime fileTime) {
        return Math.subtractExact((fileTime.toInstant().getEpochSecond() * HUNDRED_NANOS_PER_SECOND) + (r6.getNano() / 100), WINDOWS_EPOCH_OFFSET);
    }

    public static long toNtfsTime(long j) {
        return Math.subtractExact(j * HUNDRED_NANOS_PER_MILLISECOND, WINDOWS_EPOCH_OFFSET);
    }

    public static long toUnixTime(FileTime fileTime) {
        if (fileTime != null) {
            return fileTime.to(TimeUnit.SECONDS);
        }
        return 0L;
    }

    private FileTimes() {
    }
}
