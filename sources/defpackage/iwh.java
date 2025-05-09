package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.operation.OperationKey;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class iwh {

    /* renamed from: a, reason: collision with root package name */
    private static final String f13636a = File.separator + "hiSequence";

    public static boolean c(String str) {
        return !TextUtils.isEmpty(str) && str.getBytes(StandardCharsets.UTF_8).length > 921600;
    }

    public static boolean e(String str, String str2) {
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.c("HiH_HiSequenceFileUtil", "the input or fileName is empty, not need to save. name:", str2);
            return false;
        }
        ReleaseLogUtil.b("HiH_HiSequenceFileUtil", "saveBigSequence detail in file:", str2, " size:", Integer.valueOf(str.getBytes(StandardCharsets.UTF_8).length));
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("scene", "detailOverSizeLimit");
        linkedHashMap.put("detailLength", String.valueOf(str.length()));
        linkedHashMap.put(ContentResource.FILE_NAME, str2);
        ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_SEQUENCE_DETAIL_OVER_LIMIT_2129012.value(), linkedHashMap, false);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                File file = new File(BaseApplication.e().getFilesDir() + f13636a, str2);
                File parentFile = file.getParentFile();
                if (parentFile == null) {
                    ReleaseLogUtil.c("HiH_HiSequenceFileUtil", "the parentFile is null");
                    return false;
                }
                FileUtils.a(parentFile);
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(str.getBytes(StandardCharsets.ISO_8859_1));
                    FileUtils.d(fileOutputStream);
                    return true;
                } catch (IOException e) {
                    e = e;
                    fileOutputStream2 = fileOutputStream;
                    ReleaseLogUtil.c("HiH_HiSequenceFileUtil", "save detail file.appear exception:", ExceptionUtils.d(e));
                    if (fileOutputStream2 != null) {
                        FileUtils.d(fileOutputStream2);
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                        FileUtils.d(fileOutputStream);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static String c(long j, int i, int i2) {
        return "detail_" + j + "_" + i + "_" + i2;
    }

    public static String a(String str) {
        File file = new File(BaseApplication.e().getFilesDir() + f13636a, str);
        if (!file.exists()) {
            ReleaseLogUtil.c("HiH_HiSequenceFileUtil", "the sequenceDetail file is not exists:", str);
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("scene", "readDetail_FileNotExit");
            linkedHashMap.put(ContentResource.FILE_NAME, str);
            ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_SEQUENCE_DETAIL_OVER_LIMIT_2129012.value(), linkedHashMap, false);
            return "";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        String str2 = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.ISO_8859_1);
                        ReleaseLogUtil.b("HiH_HiSequenceFileUtil", "read sequence detail from file,name:", str, " size:", Integer.valueOf(str2.length()));
                        fileInputStream.close();
                        return str2;
                    }
                }
            } finally {
            }
        } catch (IOException e) {
            ReleaseLogUtil.c("HiH_HiSequenceFileUtil", "readSequenceDetail. name", str, " appear exception:", ExceptionUtils.d(e));
            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
            linkedHashMap2.put("scene", "readDetail_appearIOException");
            linkedHashMap2.put(ContentResource.FILE_NAME, str);
            ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_SEQUENCE_DETAIL_OVER_LIMIT_2129012.value(), linkedHashMap2, false);
            return "";
        }
    }

    public static boolean e(String str) {
        boolean i = FileUtils.i(new File(BaseApplication.e().getFilesDir() + f13636a, str));
        if (!i) {
            ReleaseLogUtil.d("HiH_HiSequenceFileUtil", "deleteSequenceDetail result is false,name:", str);
        }
        return i;
    }
}
