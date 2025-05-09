package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import org.apache.commons.io.input.BoundedInputStream;

/* loaded from: classes4.dex */
public class gwx {
    public static void a(int i) {
        LogUtil.a("TrackDataMockUtil", "insertTrackDetailToDataBase ,day ", Integer.valueOf(i));
        if (i > 7) {
            d();
        } else {
            a();
        }
    }

    private static void a() {
        for (int i = 1; i <= 7; i++) {
            MotionPathSimplify e = e("track", d(i));
            if (e != null) {
                b(e, e("track", c(i), e.requestSportType()), i);
            }
        }
    }

    private static void d() {
        for (int i = 8; i < 38; i++) {
            MotionPathSimplify e = e("track", d(i));
            if (e != null) {
                b(e, e("track", c(i), e.requestSportType()), i);
            }
        }
    }

    private static String d(int i) {
        return "simplemotion_" + String.valueOf(i) + ".txt";
    }

    private static String c(int i) {
        return "motion_path_" + String.valueOf(i) + ".txt";
    }

    private static long e(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i2 = calendar.get(11);
        int i3 = calendar.get(12);
        int i4 = calendar.get(13);
        int i5 = calendar.get(14);
        calendar.clear();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(5, calendar.get(5) - i);
        calendar.set(11, i2);
        calendar.set(12, i3);
        calendar.set(13, i4);
        calendar.set(14, i5);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.clear();
        return timeInMillis;
    }

    private static void b(MotionPathSimplify motionPathSimplify, MotionPath motionPath, int i) {
        if (motionPathSimplify == null || motionPath == null) {
            return;
        }
        if (motionPathSimplify.requestSportType() != 512 && motionPathSimplify.requestFatherSportItem() == null) {
            long e = e(motionPathSimplify.requestStartTime(), i);
            motionPathSimplify.saveStartTime(e);
            motionPathSimplify.saveEndTime(e + motionPathSimplify.requestTotalTime());
        }
        if (Utils.g()) {
            motionPathSimplify.saveMapType(2);
        }
        if (gso.e().c(motionPathSimplify, motionPath) == 0) {
            LogUtil.a("TrackDataMockUtil", "saveTrackToDatabase success, day", Integer.valueOf(i));
        } else {
            LogUtil.a("TrackDataMockUtil", "saveTrackToDatabase failed, day", Integer.valueOf(i));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v19, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v19 */
    /* JADX WARN: Type inference failed for: r12v24, types: [java.io.InputStream, org.apache.commons.io.input.BoundedInputStream] */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    private static MotionPath e(String str, String str2, int i) {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        IndexOutOfBoundsException e;
        IOException e2;
        InputStreamReader inputStreamReader2;
        BufferedReader bufferedReader2;
        InputStream inputStream;
        BoundedInputStream boundedInputStream;
        InputStream inputStream2;
        BoundedInputStream boundedInputStream2;
        InputStream inputStream3;
        BoundedInputStream boundedInputStream3 = null;
        r5 = null;
        r5 = null;
        MotionPath motionPath = null;
        InputStreamReader inputStreamReader3 = null;
        try {
            try {
                str = moh.e(str, str2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (FileNotFoundException unused) {
            str = 0;
        } catch (IOException e3) {
            e = e3;
            str = 0;
        } catch (IndexOutOfBoundsException e4) {
            e = e4;
            str = 0;
        } catch (Throwable th2) {
            th = th2;
            str = 0;
        }
        try {
            str2 = new BoundedInputStream(str, 102400L);
            try {
                inputStreamReader2 = new InputStreamReader((InputStream) str2, "UTF-8");
            } catch (FileNotFoundException unused2) {
                inputStreamReader2 = null;
                bufferedReader2 = null;
                LogUtil.a("TrackDataMockUtil", "fileNotFound");
                inputStream3 = str;
                boundedInputStream2 = str2;
                e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
                return motionPath;
            } catch (IOException e5) {
                e2 = e5;
                inputStreamReader2 = null;
                bufferedReader2 = null;
                LogUtil.a("TrackDataMockUtil", "readMotionPathToData ", LogAnonymous.b((Throwable) e2));
                inputStream3 = str;
                boundedInputStream2 = str2;
                e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
                return motionPath;
            } catch (IndexOutOfBoundsException e6) {
                e = e6;
                inputStreamReader2 = null;
                bufferedReader2 = null;
                LogUtil.a("TrackDataMockUtil", "readMotionPathToData ", e.getMessage());
                inputStream3 = str;
                boundedInputStream2 = str2;
                e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
                return motionPath;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                inputStream2 = str;
                boundedInputStream = str2;
                InputStreamReader inputStreamReader4 = inputStreamReader3;
                boundedInputStream3 = boundedInputStream;
                th = th;
                inputStreamReader = inputStreamReader4;
                inputStream = inputStream2;
                e(boundedInputStream3, bufferedReader, inputStreamReader, inputStream);
                throw th;
            }
        } catch (FileNotFoundException unused3) {
            str2 = 0;
            inputStreamReader2 = null;
            bufferedReader2 = null;
            LogUtil.a("TrackDataMockUtil", "fileNotFound");
            inputStream3 = str;
            boundedInputStream2 = str2;
            e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
            return motionPath;
        } catch (IOException e7) {
            e = e7;
            e2 = e;
            str2 = 0;
            inputStreamReader2 = null;
            bufferedReader2 = null;
            LogUtil.a("TrackDataMockUtil", "readMotionPathToData ", LogAnonymous.b((Throwable) e2));
            inputStream3 = str;
            boundedInputStream2 = str2;
            e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
            return motionPath;
        } catch (IndexOutOfBoundsException e8) {
            e = e8;
            e = e;
            str2 = 0;
            inputStreamReader2 = null;
            bufferedReader2 = null;
            LogUtil.a("TrackDataMockUtil", "readMotionPathToData ", e.getMessage());
            inputStream3 = str;
            boundedInputStream2 = str2;
            e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
            return motionPath;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            bufferedReader = null;
            inputStream = str;
            e(boundedInputStream3, bufferedReader, inputStreamReader, inputStream);
            throw th;
        }
        try {
            bufferedReader2 = new BufferedReader(inputStreamReader2, 2048);
            try {
                StringBuffer stringBuffer = new StringBuffer(16);
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                    stringBuffer.append(System.lineSeparator());
                }
                motionPath = gvz.a(stringBuffer.toString(), i);
                inputStream3 = str;
                boundedInputStream2 = str2;
            } catch (FileNotFoundException unused4) {
                LogUtil.a("TrackDataMockUtil", "fileNotFound");
                inputStream3 = str;
                boundedInputStream2 = str2;
                e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
                return motionPath;
            } catch (IOException e9) {
                e2 = e9;
                LogUtil.a("TrackDataMockUtil", "readMotionPathToData ", LogAnonymous.b((Throwable) e2));
                inputStream3 = str;
                boundedInputStream2 = str2;
                e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
                return motionPath;
            } catch (IndexOutOfBoundsException e10) {
                e = e10;
                LogUtil.a("TrackDataMockUtil", "readMotionPathToData ", e.getMessage());
                inputStream3 = str;
                boundedInputStream2 = str2;
                e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
                return motionPath;
            }
        } catch (FileNotFoundException unused5) {
            bufferedReader2 = null;
        } catch (IOException e11) {
            e2 = e11;
            bufferedReader2 = null;
        } catch (IndexOutOfBoundsException e12) {
            e = e12;
            bufferedReader2 = null;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = null;
            inputStreamReader3 = inputStreamReader2;
            inputStream2 = str;
            boundedInputStream = str2;
            InputStreamReader inputStreamReader42 = inputStreamReader3;
            boundedInputStream3 = boundedInputStream;
            th = th;
            inputStreamReader = inputStreamReader42;
            inputStream = inputStream2;
            e(boundedInputStream3, bufferedReader, inputStreamReader, inputStream);
            throw th;
        }
        e(boundedInputStream2, bufferedReader2, inputStreamReader2, inputStream3);
        return motionPath;
    }

    private static void e(BoundedInputStream boundedInputStream, BufferedReader bufferedReader, InputStreamReader inputStreamReader, InputStream inputStream) {
        if (boundedInputStream != null) {
            try {
                boundedInputStream.close();
            } catch (FileNotFoundException unused) {
                LogUtil.a("TrackDataMockUtil", "fileNotFound");
            } catch (IOException e) {
                LogUtil.a("TrackDataMockUtil", "readMotionPathToData boundedInputStream finally ", LogAnonymous.b((Throwable) e));
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (FileNotFoundException unused2) {
                LogUtil.a("TrackDataMockUtil", "fileNotFound");
            } catch (IOException e2) {
                LogUtil.a("TrackDataMockUtil", "readMotionPathToData bufferedReader finally ", LogAnonymous.b((Throwable) e2));
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (FileNotFoundException unused3) {
                LogUtil.a("TrackDataMockUtil", "fileNotFound");
            } catch (IOException e3) {
                LogUtil.a("TrackDataMockUtil", "readMotionPathToData inputStreamReader finally ", LogAnonymous.b((Throwable) e3));
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (FileNotFoundException unused4) {
                LogUtil.a("TrackDataMockUtil", "fileNotFound");
            } catch (IOException e4) {
                LogUtil.a("TrackDataMockUtil", "readMotionPathToData inputStream finally ", LogAnonymous.b((Throwable) e4));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v19 */
    /* JADX WARN: Type inference failed for: r11v22, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v23, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v32, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v33, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v34 */
    /* JADX WARN: Type inference failed for: r11v39, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v40, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v46 */
    /* JADX WARN: Type inference failed for: r11v51 */
    /* JADX WARN: Type inference failed for: r11v52 */
    /* JADX WARN: Type inference failed for: r11v53 */
    /* JADX WARN: Type inference failed for: r11v54 */
    /* JADX WARN: Type inference failed for: r11v55 */
    /* JADX WARN: Type inference failed for: r11v56 */
    /* JADX WARN: Type inference failed for: r11v57 */
    /* JADX WARN: Type inference failed for: r11v58 */
    /* JADX WARN: Type inference failed for: r11v59 */
    /* JADX WARN: Type inference failed for: r11v60 */
    /* JADX WARN: Type inference failed for: r11v61 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v7, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r12v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify e(java.lang.String r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gwx.e(java.lang.String, java.lang.String):com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify");
    }
}
