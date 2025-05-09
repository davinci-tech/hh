package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import defpackage.to;
import defpackage.ueu;
import defpackage.ufe;
import defpackage.ufs;
import defpackage.ugj;
import defpackage.uhy;
import defpackage.uig;
import defpackage.ujm;
import defpackage.ujy;
import java.io.IOException;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import okio.BufferedSource;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Path;
import okio.ZipFileSystem;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\"\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0002\u001a\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u001b\u001a.\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#0\"H\u0000\u001a\f\u0010$\u001a\u00020\u0015*\u00020%H\u0000\u001a\f\u0010&\u001a\u00020'*\u00020%H\u0002\u001a.\u0010(\u001a\u00020)*\u00020%2\u0006\u0010*\u001a\u00020\u00012\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020)0,H\u0002\u001a\u0014\u0010-\u001a\u00020.*\u00020%2\u0006\u0010/\u001a\u00020.H\u0000\u001a\u0018\u00100\u001a\u0004\u0018\u00010.*\u00020%2\b\u0010/\u001a\u0004\u0018\u00010.H\u0002\u001a\u0014\u00101\u001a\u00020'*\u00020%2\u0006\u00102\u001a\u00020'H\u0002\u001a\f\u00103\u001a\u00020)*\u00020%H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00064"}, d2 = {"BIT_FLAG_ENCRYPTED", "", "BIT_FLAG_UNSUPPORTED_MASK", "CENTRAL_FILE_HEADER_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "HEADER_ID_EXTENDED_TIMESTAMP", "HEADER_ID_ZIP64_EXTENDED_INFO", "LOCAL_FILE_HEADER_SIGNATURE", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "", "ZIP64_EOCD_RECORD_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "hex", "", "getHex", "(I)Ljava/lang/String;", "buildIndex", "", "Lokio/Path;", "Lokio/internal/ZipEntry;", "entries", "", "dosDateTimeToEpochMillis", "date", "time", "(II)Ljava/lang/Long;", "openZip", "Lokio/ZipFileSystem;", "zipPath", "fileSystem", "Lokio/FileSystem;", "predicate", "Lkotlin/Function1;", "", "readEntry", "Lokio/BufferedSource;", "readEocdRecord", "Lokio/internal/EocdRecord;", "readExtra", "", "extraSize", "block", "Lkotlin/Function2;", "readLocalHeader", "Lokio/FileMetadata;", "basicMetadata", "readOrSkipLocalHeader", "readZip64EocdRecord", "regularRecord", "skipLocalHeader", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ZipFilesKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
    private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            function1 = new Function1<ZipEntry, Boolean>() { // from class: okio.internal.ZipFilesKt$openZip$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ZipEntry zipEntry) {
                    uhy.e((Object) zipEntry, "");
                    return true;
                }
            };
        }
        return openZip(path, fileSystem, function1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0044, code lost:
    
        r9 = readEocdRecord(r11);
        r10 = r11.readUtf8(r9.getCommentByteCount());
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0051, code lost:
    
        r11.close();
        r5 = r5 - 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
    
        if (r5 <= r7) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005d, code lost:
    
        r5 = okio.Okio.buffer(r4.source(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0067, code lost:
    
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0071, code lost:
    
        if (r6.readIntLe() != okio.internal.ZipFilesKt.ZIP64_LOCATOR_SIGNATURE) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0073, code lost:
    
        r11 = r6.readIntLe();
        r7 = r6.readLongLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0080, code lost:
    
        if (r6.readIntLe() != 1) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0082, code lost:
    
        if (r11 != 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0084, code lost:
    
        r6 = okio.Okio.buffer(r4.source(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008e, code lost:
    
        r7 = r6;
        r8 = r7.readIntLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0098, code lost:
    
        if (r8 != okio.internal.ZipFilesKt.ZIP64_EOCD_RECORD_SIGNATURE) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009a, code lost:
    
        r7 = readZip64EocdRecord(r7, r9);
        r8 = defpackage.ueu.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a0, code lost:
    
        defpackage.closeFinally.d(r6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a3, code lost:
    
        r9 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00cb, code lost:
    
        throw new java.io.IOException("bad zip: expected " + getHex(okio.internal.ZipFilesKt.ZIP64_EOCD_RECORD_SIGNATURE) + " but was " + getHex(r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00cc, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00dc, code lost:
    
        throw new java.io.IOException("unsupported zip: spanned");
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00dd, code lost:
    
        r6 = defpackage.ueu.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00df, code lost:
    
        defpackage.closeFinally.d(r5, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e3, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ec, code lost:
    
        r5 = new java.util.ArrayList();
        r4 = okio.Okio.buffer(r4.source(r9.getCentralDirectoryOffset()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0101, code lost:
    
        r6 = r4;
        r7 = r9.getEntryCount();
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x010c, code lost:
    
        if (r16 >= r7) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x010e, code lost:
    
        r11 = readEntry(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x011c, code lost:
    
        if (r11.getOffset() >= r9.getCentralDirectoryOffset()) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0128, code lost:
    
        if (r24.invoke(r11).booleanValue() == false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x012a, code lost:
    
        r5.add(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0130, code lost:
    
        r16 = r16 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x013a, code lost:
    
        throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x013b, code lost:
    
        r2 = defpackage.ueu.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x013d, code lost:
    
        defpackage.closeFinally.d(r4, null);
        r4 = new okio.ZipFileSystem(r22, r23, buildIndex(r5), r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0149, code lost:
    
        defpackage.closeFinally.d(r3, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x014c, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x014d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x014f, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0150, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0155, code lost:
    
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final okio.ZipFileSystem openZip(okio.Path r22, okio.FileSystem r23, kotlin.jvm.functions.Function1<? super okio.internal.ZipEntry, java.lang.Boolean> r24) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ZipFilesKt.openZip(okio.Path, okio.FileSystem, kotlin.jvm.functions.Function1):okio.ZipFileSystem");
    }

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        Path path = Path.Companion.get$default(Path.INSTANCE, "/", false, 1, (Object) null);
        Map<Path, ZipEntry> c = ufs.c(to.d(path, new ZipEntry(path, true, null, 0L, 0L, 0L, 0, null, 0L, TypedValues.PositionType.TYPE_CURVE_FIT, null)));
        for (ZipEntry zipEntry : ufe.e((Iterable) list, new Comparator() { // from class: okio.internal.ZipFilesKt$buildIndex$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ugj.b(((ZipEntry) t).getCanonicalPath(), ((ZipEntry) t2).getCanonicalPath());
            }
        })) {
            if (c.put(zipEntry.getCanonicalPath(), zipEntry) == null) {
                while (true) {
                    Path parent = zipEntry.getCanonicalPath().parent();
                    if (parent != null) {
                        ZipEntry zipEntry2 = c.get(parent);
                        if (zipEntry2 != null) {
                            zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(parent, true, null, 0L, 0L, 0L, 0, null, 0L, TypedValues.PositionType.TYPE_CURVE_FIT, null);
                        c.put(parent, zipEntry3);
                        zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                        zipEntry = zipEntry3;
                    }
                }
            }
        }
        return c;
    }

    public static final ZipEntry readEntry(final BufferedSource bufferedSource) throws IOException {
        short s;
        long j;
        uhy.e((Object) bufferedSource, "");
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe != CENTRAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(CENTRAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
        }
        bufferedSource.skip(4L);
        short readShortLe = bufferedSource.readShortLe();
        int i = readShortLe & 65535;
        if ((readShortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(i));
        }
        short readShortLe2 = bufferedSource.readShortLe();
        Long dosDateTimeToEpochMillis = dosDateTimeToEpochMillis(bufferedSource.readShortLe() & 65535, bufferedSource.readShortLe() & 65535);
        long readIntLe2 = bufferedSource.readIntLe();
        final uig.b bVar = new uig.b();
        bVar.d = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final uig.b bVar2 = new uig.b();
        bVar2.d = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        short readShortLe3 = bufferedSource.readShortLe();
        short readShortLe4 = bufferedSource.readShortLe();
        short readShortLe5 = bufferedSource.readShortLe();
        bufferedSource.skip(8L);
        final uig.b bVar3 = new uig.b();
        bVar3.d = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        String readUtf8 = bufferedSource.readUtf8(readShortLe3 & 65535);
        if (ujy.d((CharSequence) readUtf8, (char) 0, false, 2, (Object) null)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        if (bVar2.d == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j = 8;
            s = readShortLe2;
        } else {
            s = readShortLe2;
            j = 0;
        }
        if (bVar.d == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j += 8;
        }
        if (bVar3.d == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j += 8;
        }
        final long j2 = j;
        final uig.c cVar = new uig.c();
        readExtra(bufferedSource, readShortLe4 & 65535, new Function2<Integer, Long, ueu>() { // from class: okio.internal.ZipFilesKt$readEntry$1
            @Override // kotlin.jvm.functions.Function2
            public /* synthetic */ ueu invoke(Integer num, Long l) {
                invoke(num.intValue(), l.longValue());
                return ueu.d;
            }

            public final void invoke(int i2, long j3) {
                if (i2 == 1) {
                    if (uig.c.this.c) {
                        throw new IOException("bad zip: zip64 extra repeated");
                    }
                    uig.c.this.c = true;
                    if (j3 < j2) {
                        throw new IOException("bad zip: zip64 extra too short");
                    }
                    uig.b bVar4 = bVar2;
                    bVar4.d = bVar4.d == 4294967295L ? bufferedSource.readLongLe() : bVar2.d;
                    uig.b bVar5 = bVar;
                    bVar5.d = bVar5.d == 4294967295L ? bufferedSource.readLongLe() : 0L;
                    uig.b bVar6 = bVar3;
                    bVar6.d = bVar6.d == 4294967295L ? bufferedSource.readLongLe() : 0L;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }
        });
        if (j2 > 0 && !cVar.c) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        return new ZipEntry(Path.Companion.get$default(Path.INSTANCE, "/", false, 1, (Object) null).resolve(readUtf8), ujy.b(readUtf8, "/", false, 2, (Object) null), bufferedSource.readUtf8(readShortLe5 & 65535), readIntLe2 & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE, bVar.d, bVar2.d, s & 65535, dosDateTimeToEpochMillis, bVar3.d);
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) throws IOException {
        short readShortLe = bufferedSource.readShortLe();
        short readShortLe2 = bufferedSource.readShortLe();
        long readShortLe3 = bufferedSource.readShortLe() & 65535;
        if (readShortLe3 != (bufferedSource.readShortLe() & 65535) || (readShortLe & 65535) != 0 || (readShortLe2 & 65535) != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(4L);
        return new EocdRecord(readShortLe3, MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE & bufferedSource.readIntLe(), bufferedSource.readShortLe() & 65535);
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) throws IOException {
        bufferedSource.skip(12L);
        int readIntLe = bufferedSource.readIntLe();
        int readIntLe2 = bufferedSource.readIntLe();
        long readLongLe = bufferedSource.readLongLe();
        if (readLongLe != bufferedSource.readLongLe() || readIntLe != 0 || readIntLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(8L);
        return new EocdRecord(readLongLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
    }

    private static final void readExtra(BufferedSource bufferedSource, int i, Function2<? super Integer, ? super Long, ueu> function2) {
        long j = i;
        while (j != 0) {
            if (j < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int readShortLe = bufferedSource.readShortLe() & 65535;
            long readShortLe2 = bufferedSource.readShortLe() & 65535;
            long j2 = j - 4;
            if (j2 < readShortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource.require(readShortLe2);
            long size = bufferedSource.getBuffer().size();
            function2.invoke(Integer.valueOf(readShortLe), Long.valueOf(readShortLe2));
            long size2 = (bufferedSource.getBuffer().size() + readShortLe2) - size;
            if (size2 < 0) {
                throw new IOException("unsupported zip: too many bytes processed for " + readShortLe);
            }
            if (size2 > 0) {
                bufferedSource.getBuffer().skip(size2);
            }
            j = j2 - readShortLe2;
        }
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource) {
        uhy.e((Object) bufferedSource, "");
        readOrSkipLocalHeader(bufferedSource, null);
    }

    public static final FileMetadata readLocalHeader(BufferedSource bufferedSource, FileMetadata fileMetadata) {
        uhy.e((Object) bufferedSource, "");
        uhy.e((Object) fileMetadata, "");
        FileMetadata readOrSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, fileMetadata);
        uhy.d(readOrSkipLocalHeader);
        return readOrSkipLocalHeader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FileMetadata readOrSkipLocalHeader(final BufferedSource bufferedSource, FileMetadata fileMetadata) {
        final uig.e eVar = new uig.e();
        eVar.d = fileMetadata != null ? fileMetadata.getLastModifiedAtMillis() : 0;
        final uig.e eVar2 = new uig.e();
        final uig.e eVar3 = new uig.e();
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe != LOCAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
        }
        bufferedSource.skip(2L);
        short readShortLe = bufferedSource.readShortLe();
        int i = readShortLe & 65535;
        if ((readShortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(i));
        }
        bufferedSource.skip(18L);
        long readShortLe2 = bufferedSource.readShortLe();
        int readShortLe3 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(readShortLe2 & 65535);
        if (fileMetadata == null) {
            bufferedSource.skip(readShortLe3);
            return null;
        }
        readExtra(bufferedSource, readShortLe3, new Function2<Integer, Long, ueu>() { // from class: okio.internal.ZipFilesKt$readOrSkipLocalHeader$1
            @Override // kotlin.jvm.functions.Function2
            public /* synthetic */ ueu invoke(Integer num, Long l) {
                invoke(num.intValue(), l.longValue());
                return ueu.d;
            }

            /* JADX WARN: Type inference failed for: r0v13, types: [T, java.lang.Long] */
            /* JADX WARN: Type inference failed for: r10v11, types: [T, java.lang.Long] */
            /* JADX WARN: Type inference failed for: r11v3, types: [T, java.lang.Long] */
            public final void invoke(int i2, long j) {
                if (i2 == 21589) {
                    if (j < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    byte readByte = BufferedSource.this.readByte();
                    boolean z = (readByte & 1) == 1;
                    boolean z2 = (readByte & 2) == 2;
                    boolean z3 = (readByte & 4) == 4;
                    BufferedSource bufferedSource2 = BufferedSource.this;
                    long j2 = z ? 5L : 1L;
                    if (z2) {
                        j2 += 4;
                    }
                    if (z3) {
                        j2 += 4;
                    }
                    if (j < j2) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        eVar.d = Long.valueOf(bufferedSource2.readIntLe() * 1000);
                    }
                    if (z2) {
                        eVar2.d = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                    }
                    if (z3) {
                        eVar3.d = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }
        });
        return new FileMetadata(fileMetadata.getIsRegularFile(), fileMetadata.getIsDirectory(), null, fileMetadata.getSize(), (Long) eVar3.d, (Long) eVar.d, (Long) eVar2.d, null, 128, null);
    }

    private static final Long dosDateTimeToEpochMillis(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        gregorianCalendar.set(((i >> 9) & 127) + 1980, ((i >> 5) & 15) - 1, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1);
        return Long.valueOf(gregorianCalendar.getTime().getTime());
    }

    private static final String getHex(int i) {
        StringBuilder sb = new StringBuilder("0x");
        String num = Integer.toString(i, ujm.e(16));
        uhy.a(num, "");
        sb.append(num);
        return sb.toString();
    }
}
