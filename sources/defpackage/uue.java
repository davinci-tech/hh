package defpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes7.dex */
public class uue {
    public static String d(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IOException("Input stream must not be null");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                sb.append(cArr, 0, read);
            } else {
                return sb.toString();
            }
        }
    }

    public static void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
