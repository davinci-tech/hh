package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/* loaded from: classes7.dex */
public class twh {
    public static String c(InputStream inputStream, String str) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
        try {
            StringWriter stringWriter = new StringWriter();
            try {
                a(inputStreamReader, stringWriter);
                String stringWriter2 = stringWriter.toString();
                stringWriter.close();
                inputStreamReader.close();
                return stringWriter2;
            } finally {
            }
        } catch (Throwable th) {
            try {
                inputStreamReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long b(Reader reader, Writer writer, char[] cArr) throws IOException {
        int read = reader.read(cArr);
        long j = 0;
        while (-1 != read) {
            writer.write(cArr, 0, read);
            j += read;
            read = reader.read(cArr);
        }
        return j;
    }

    public static long a(Reader reader, Writer writer) throws IOException {
        return b(reader, writer, new char[4096]);
    }
}
