package com.huawei.agconnect.config.impl;

import android.util.Log;
import com.huawei.agconnect.AGCRoutePolicy;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class Utils {
    private static final int BUFF_SIZE = 4096;
    public static final String DEFAULT_NAME = "DEFAULT_INSTANCE";
    private static final String TAG = "Utils";

    public static String toString(InputStream inputStream, String str) throws UnsupportedEncodingException, IOException {
        StringWriter stringWriter = new StringWriter();
        copy(new InputStreamReader(inputStream, str), stringWriter);
        return stringWriter.toString();
    }

    public static AGCRoutePolicy getRoutePolicyFromJson(String str, String str2) {
        char c;
        if (str == null) {
            if (str2 != null) {
                if (str2.contains("connect-drcn")) {
                    return AGCRoutePolicy.CHINA;
                }
                if (str2.contains("connect-dre")) {
                    return AGCRoutePolicy.GERMANY;
                }
                if (str2.contains("connect-drru")) {
                    return AGCRoutePolicy.RUSSIA;
                }
                if (str2.contains("connect-dra")) {
                    return AGCRoutePolicy.SINGAPORE;
                }
            }
            return AGCRoutePolicy.UNKNOWN;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 2155) {
            if (str.equals("CN")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 2177) {
            if (str.equals("DE")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 2627) {
            if (hashCode == 2644 && str.equals("SG")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("RU")) {
                c = 2;
            }
            c = 65535;
        }
        return c != 0 ? c != 1 ? c != 2 ? c != 3 ? AGCRoutePolicy.UNKNOWN : AGCRoutePolicy.SINGAPORE : AGCRoutePolicy.RUSSIA : AGCRoutePolicy.GERMANY : AGCRoutePolicy.CHINA;
    }

    public static String fixPath(String str) {
        int i = 0;
        if (str.length() > 0) {
            while (str.charAt(i) == '/') {
                i++;
            }
        }
        return "/" + str.substring(i);
    }

    public static Map<String, String> fixKeyPathMap(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashMap.put(fixPath(entry.getKey()), entry.getValue());
        }
        return hashMap;
    }

    public static void copy(Reader reader, Writer writer, char[] cArr) throws IOException {
        while (true) {
            int read = reader.read(cArr);
            if (-1 == read) {
                return;
            } else {
                writer.write(cArr, 0, read);
            }
        }
    }

    public static void copy(Reader reader, Writer writer) throws IOException {
        copy(reader, writer, new char[4096]);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                Log.e("Utils", "Exception when closing the 'Closeable'.");
            }
        }
    }
}
