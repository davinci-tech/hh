package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudApi;
import com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.framework.network.restclient.hwhttp.trans.MultipartBody;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.ui.commonui.base.BaseActivity;
import health.compact.a.Base64;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class bro {
    private static final Object c = new Object();
    private static volatile bro e;

    /* renamed from: a, reason: collision with root package name */
    private String f483a;
    private Context b;
    private HiCloudApi d = (HiCloudApi) lqi.d().b(HiCloudApi.class);

    private bro(Context context) {
        if (context == null) {
            this.b = BaseApplication.e();
        } else {
            this.b = context.getApplicationContext();
        }
        this.f483a = GRSManager.a(this.b).getUrl("hiCloudUrl");
    }

    public static bro e(Context context) {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new bro(BaseApplication.e());
                }
            }
        }
        return e;
    }

    public void e(String str, HiCloudCbk<String> hiCloudCbk) {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "multipart/related;boundary=OP8XTaXZ0UZs-Sjlefcj2OWskqXWwVQO");
        hashMap.put("Cache-Control", "no-cache");
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + LoginInit.getInstance(this.b).getAccountInfo(1015));
        if (CommonUtil.aq()) {
            hashMap.put("version", "abc");
        }
        File file = new File(str);
        if (!file.exists()) {
            ReleaseLogUtil.d("R_HiCloudUtil", "uploadFileByName file not exit");
            hiCloudCbk.onFailure(-1, "file not exit, pls check it");
            return;
        }
        HashMap hashMap2 = new HashMap();
        String str2 = System.currentTimeMillis() + file.getName();
        BaseActivity.isMiui();
        if (str2.length() > 250) {
            str2 = str2.substring(str2.length() - 250);
        }
        hashMap2.put(ContentResource.FILE_NAME, str2);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("applicationData");
        hashMap2.put("parentFolder", arrayList);
        MultipartBody.Builder builder = new MultipartBody.Builder("OP8XTaXZ0UZs-Sjlefcj2OWskqXWwVQO");
        builder.setType(MediaType.parse("multipart/related"));
        RequestBody create = RequestBody.create(MediaType.parse(FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM), new File(str));
        builder.addPart(RequestBody.create(MediaType.parse("application/json"), lql.b(hashMap2)));
        builder.addPart(create);
        LogUtil.d("HiCloudUtil", "uploadFileByName req: ", lql.b(hashMap2));
        try {
            Response<brn> execute = this.d.uploadFileByName(this.f483a + "/upload/drive/v1/files?uploadType=multipart&fields=*", hashMap, builder.build()).execute();
            if (execute == null) {
                hiCloudCbk.onFailure(-2, "unknow exception");
                return;
            }
            if (execute.isSuccessful() && execute.getBody() != null) {
                String d2 = execute.getBody().d();
                LogUtil.d("HiCloudUtil", "uploadFileByName: ", d2);
                SharedPreferenceManager.e(this.b, "HICLOUD_DATA", d2, str, (StorageParams) null);
                hiCloudCbk.onSuccess(d2);
                return;
            }
            e(execute.getErrorBody(), hiCloudCbk);
        } catch (IOException e2) {
            hiCloudCbk.onFailure(-3, VastAttribute.ERROR);
            ReleaseLogUtil.c("R_HiCloudUtil", "uploadFileByName exception: ", ExceptionUtils.d(e2));
        }
    }

    public void b(String str, HiCloudCbk<String> hiCloudCbk) {
        ReleaseLogUtil.e("R_HiCloudUtil", "enter getFileInstream");
        String b2 = SharedPreferenceManager.b(this.b, "HICLOUD_DATA", str);
        if (!TextUtils.isEmpty(b2)) {
            String b3 = b(b2);
            if (!TextUtils.isEmpty(b3)) {
                hiCloudCbk.onSuccess(b3);
                return;
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + LoginInit.getInstance(this.b).getAccountInfo(1015));
        try {
            Response<ResponseBody> execute = this.d.downloadFile(this.f483a + "/drive/v1/files/" + str + "?form=content", hashMap).execute();
            if (execute == null) {
                hiCloudCbk.onFailure(-2, "unknow exception");
            } else if (execute.isSuccessful() && execute.getBody() != null) {
                ReleaseLogUtil.e("R_HiCloudUtil", "getFileInstream from cloud");
                hiCloudCbk.onSuccess(b(execute.getBody().getInputStream()));
            } else {
                e(execute.getErrorBody(), hiCloudCbk);
            }
        } catch (IOException e2) {
            hiCloudCbk.onFailure(-3, VastAttribute.ERROR);
            ReleaseLogUtil.c("HiCloudUtil", "getFileInstream excepotion: ", ExceptionUtils.d(e2));
        }
    }

    public void c(String str, HiCloudCbk<String> hiCloudCbk) {
        ReleaseLogUtil.e("R_HiCloudUtil", "enter getFileAsImage");
        String b2 = SharedPreferenceManager.b(this.b, "HICLOUD_DATA", str);
        if (!TextUtils.isEmpty(b2)) {
            String b3 = b(b2);
            if (!TextUtils.isEmpty(b3)) {
                hiCloudCbk.onSuccess(b3);
                return;
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + LoginInit.getInstance(this.b).getAccountInfo(1015));
        try {
            Response<ResponseBody> execute = this.d.downloadImage(this.f483a + "/drive/v1/thumbnails/" + str + "?form=content", hashMap).execute();
            if (execute == null) {
                hiCloudCbk.onFailure(-2, "unknow exception");
            } else if (execute.isSuccessful() && execute.getBody() != null) {
                hiCloudCbk.onSuccess(b(execute.getBody().getInputStream()));
                ReleaseLogUtil.e("R_HiCloudUtil", "getFileAsImage success");
            } else {
                e(execute.getErrorBody(), hiCloudCbk);
            }
        } catch (IOException e2) {
            hiCloudCbk.onFailure(-3, VastAttribute.ERROR);
            ReleaseLogUtil.c("R_HiCloudUtil", "getFileAsImage exception: ", ExceptionUtils.d(e2));
        }
    }

    public void d(String str, HiCloudCbk<Boolean> hiCloudCbk) {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("Cache-Control", "no-cache");
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + LoginInit.getInstance(this.b).getAccountInfo(1015));
        try {
            Response<String> execute = this.d.deleteFile(this.f483a + "/drive/v1/files/" + str, hashMap).execute();
            if (execute == null) {
                hiCloudCbk.onFailure(-2, "unknow exception");
                return;
            }
            if (execute.isSuccessful()) {
                ReleaseLogUtil.e("R_HiCloudUtil", "deleteFileById deleteSuccess");
                hiCloudCbk.onSuccess(true);
                SharedPreferenceManager.d("HICLOUD_DATA", str);
            } else {
                ReleaseLogUtil.e("HiCloudUtil", "deleteFileById please check again");
                e(execute.getErrorBody(), hiCloudCbk);
            }
        } catch (IOException e2) {
            ReleaseLogUtil.c("R_HiCloudUtil", "deleteFileById exception: ", ExceptionUtils.d(e2));
            hiCloudCbk.onFailure(0, "deleteFail");
        }
    }

    private static String b(String str) {
        Throwable th;
        FileInputStream fileInputStream;
        File file = new File(str);
        if (!file.exists()) {
            return "";
        }
        if (!FileUtils.a(file, str)) {
            LogUtil.c("HiCloudUtil", "path_crossing filetoBase64 file is invalid, fileName ", str);
            return "";
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (IOException e2) {
            e = e2;
        } catch (Throwable th2) {
            th = th2;
            FileUtils.d(fileInputStream2);
            throw th;
        }
        try {
            String b2 = b(fileInputStream);
            FileUtils.d(fileInputStream);
            return b2;
        } catch (IOException e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            try {
                ReleaseLogUtil.c("HiCloudUtil", "filetoBase64 IOException: ", ExceptionUtils.d(e));
                FileUtils.d(fileInputStream2);
                return "";
            } catch (Throwable th3) {
                fileInputStream = fileInputStream2;
                th = th3;
                th = th;
                fileInputStream2 = fileInputStream;
                FileUtils.d(fileInputStream2);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            th = th;
            fileInputStream2 = fileInputStream;
            FileUtils.d(fileInputStream2);
            throw th;
        }
    }

    private static String b(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                if (inputStream == null) {
                    ReleaseLogUtil.c("HiCloudUtil", "inputStreamToBase64 in is null");
                } else {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byteArrayOutputStream.flush();
                }
            } catch (IOException e2) {
                ReleaseLogUtil.d("HiCloudUtil", "inputStreamToBase64 error: ", ExceptionUtils.d(e2));
            }
            FileUtils.d(inputStream);
            FileUtils.d(byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.c(byteArray, 0, byteArray.length);
        } catch (Throwable th) {
            FileUtils.d(inputStream);
            FileUtils.d(byteArrayOutputStream);
            throw th;
        }
    }

    private void e(byte[] bArr, HiCloudCbk hiCloudCbk) {
        String str = new String(bArr, StandardCharsets.UTF_8);
        ReleaseLogUtil.e("HiCloudUtil", "dealErrorCode errorCode: ", str);
        try {
            e eVar = (e) HiJsonUtil.e(str, e.class);
            if (eVar != null && eVar.d() != null && !koq.b(eVar.d().get("errorDetail"))) {
                List<d> list = eVar.d().get("errorDetail");
                hiCloudCbk.onFailure(Integer.parseInt(list.get(0).c()), list.get(0).e());
                return;
            }
            b bVar = (b) HiJsonUtil.e(str, b.class);
            if (bVar != null && bVar.a() != null && !koq.b(bVar.a().c())) {
                List<d> c2 = bVar.a().c();
                hiCloudCbk.onFailure(Integer.parseInt(c2.get(0).c()), c2.get(0).e());
            } else {
                hiCloudCbk.onFailure(-2, "error return from HiCloud");
            }
        } catch (JsonParseException | NumberFormatException e2) {
            ReleaseLogUtil.c("HiCloudUtil", "dealErrorCode error: ", ExceptionUtils.d(e2));
            hiCloudCbk.onFailure(-2, ExceptionUtils.d(e2));
        }
    }

    /* loaded from: classes8.dex */
    static class b {

        @SerializedName(VastAttribute.ERROR)
        private e e;

        private b() {
        }

        public e a() {
            return this.e;
        }
    }

    /* loaded from: classes8.dex */
    static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("description")
        private String f485a;

        @SerializedName("errorDetail")
        private List<d> c;

        @SerializedName("code")
        private String d;

        @SerializedName(VastAttribute.ERROR)
        private Map<String, List<d>> e;

        private e() {
        }

        public Map<String, List<d>> d() {
            return this.e;
        }

        public List<d> c() {
            return this.c;
        }
    }

    /* loaded from: classes8.dex */
    static class d {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("description")
        private String f484a;

        @SerializedName("errorCode")
        private String d;

        @SerializedName("reason")
        private String e;

        private d() {
        }

        public String e() {
            return this.f484a;
        }

        public String c() {
            return this.d;
        }
    }
}
