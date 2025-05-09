package defpackage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.huawei.agconnect.apms.Agent;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.beans.TitleBean;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

/* loaded from: classes6.dex */
public class mvc {
    public void e(fdu fduVar) {
        int u = fduVar.u();
        if (u == 8) {
            j(fduVar);
        } else if (u == 10) {
            d(fduVar);
        } else {
            b(fduVar);
        }
    }

    private void b(fdu fduVar) {
        Bitmap cpV_ = cpV_(fduVar);
        if (cpV_ == null) {
            LogUtil.a("SaveLocalInteractor", "getShareContentBitmap failed");
            Toast.makeText(BaseApplication.getContext(), R.string._2130842375_res_0x7f021307, 0).show();
        } else {
            mwd.cqE_(BaseApplication.getContext(), mwd.cqs_(cpV_, 50240), fduVar.k());
        }
    }

    private void j(fdu fduVar) {
        if (!new File(fduVar.x()).exists()) {
            LogUtil.a("SaveLocalInteractor", "file not exit");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", "");
        contentValues.put("duration", Integer.valueOf(b(fduVar.x())));
        contentValues.put("mime_type", "video/mp4");
        contentValues.put("_data", fduVar.x());
        if (BaseApplication.getContext().getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues) != null) {
            nrh.c(BaseApplication.getContext(), BaseApplication.getContext().getString(R.string._2130846047_res_0x7f02215f));
        } else {
            nrh.c(BaseApplication.getContext(), BaseApplication.getContext().getString(R.string._2130842375_res_0x7f021307));
        }
    }

    private int b(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
    }

    private Bitmap cpV_(fdu fduVar) {
        Bitmap cqu_ = mwd.cqu_(fduVar);
        if (cqu_ == null) {
            LogUtil.h("SaveLocalInteractor", "getShareContentBitmap failed");
        }
        return cqu_;
    }

    private void d(final fdu fduVar) {
        PermissionUtil.b(BaseApplication.getActivity(), PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE, new CustomPermissionAction(BaseApplication.getActivity()) { // from class: mvc.1
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                mvc.this.a(fduVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(fdu fduVar) {
        String str = CommonUtil.j((Context) null).getAbsolutePath() + File.separator + TitleBean.RIGHT_BTN_TYPE_SHARE;
        File file = new File(str);
        if (!FileUtils.a(file, str)) {
            LogUtil.h("SaveLocalInteractor", "path_crossing saveFile share file is invalid, filePath ", str);
            return;
        }
        try {
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    LogUtil.a("SaveLocalInteractor", "create dirPath fail");
                    return;
                }
            }
            File file2 = new File(fduVar.e());
            String name = file2.getName();
            if (Build.VERSION.SDK_INT < 30) {
                String str2 = str + File.separator + name;
                c(file2, str2);
                if (str2.contains(Agent.OS_NAME)) {
                    c(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string._2130850330_res_0x7f02321a), str2.substring(str2.indexOf(Agent.OS_NAME))));
                    return;
                }
                if (str2.contains(TitleBean.RIGHT_BTN_TYPE_SHARE)) {
                    int indexOf = str2.indexOf(Environment.DIRECTORY_DOWNLOADS);
                    Locale locale = Locale.ENGLISH;
                    String string = BaseApplication.getContext().getString(R.string._2130850330_res_0x7f02321a);
                    Object[] objArr = new Object[1];
                    if (indexOf <= 0) {
                        indexOf = str2.indexOf(TitleBean.RIGHT_BTN_TYPE_SHARE);
                    }
                    objArr[0] = str2.substring(indexOf);
                    c(String.format(locale, string, objArr));
                    return;
                }
                LogUtil.a("SaveLocalInteractor", "path is error:", str2);
                return;
            }
            String str3 = Environment.DIRECTORY_DOWNLOADS + File.separator + TitleBean.RIGHT_BTN_TYPE_SHARE;
            e(file2, name, str3);
            c(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string._2130850330_res_0x7f02321a), str3 + File.separator + name));
        } catch (SecurityException unused) {
            LogUtil.b("SaveLocalInteractor", "mkdir error");
        }
    }

    private void c(File file, String str) {
        File file2 = new File(str);
        FileUtils.d(file2);
        try {
            FileUtils.d(file, file2);
        } catch (IOException unused) {
            LogUtil.b("SaveLocalInteractor", "IOException");
        }
    }

    private void e(File file, String str, String str2) {
        OutputStream outputStream;
        OutputStream outputStream2;
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", "*/*");
        contentValues.put("title", str);
        contentValues.put("relative_path", str2);
        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        Uri insert = contentResolver.insert(uri, contentValues);
        LogUtil.a("SaveLocalInteractor", "insertUri:", insert);
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                if (openOutputStream != null) {
                    FileUtils.e(fileInputStream2, openOutputStream);
                } else {
                    LogUtil.a("SaveLocalInteractor", "fileInputStream is empty");
                }
                a(fileInputStream2, openOutputStream);
            } catch (IOException unused) {
                outputStream2 = null;
                fileInputStream = fileInputStream2;
                try {
                    LogUtil.b("SaveLocalInteractor", "IOException");
                    a(fileInputStream, outputStream2);
                } catch (Throwable th) {
                    outputStream = outputStream2;
                    th = th;
                    a(fileInputStream, outputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                outputStream = null;
                fileInputStream = fileInputStream2;
                a(fileInputStream, outputStream);
                throw th;
            }
        } catch (IOException unused2) {
            outputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
        }
    }

    private void a(FileInputStream fileInputStream, OutputStream outputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException unused) {
                LogUtil.b("SaveLocalInteractor", "fileInputStream close catch IOException");
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused2) {
                LogUtil.b("SaveLocalInteractor", "outputStream close catch IOException");
            }
        }
    }

    private void c(String str) {
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.dialog_confirm_save_location, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.tv_remind)).setText(str);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(BaseApplication.getActivity());
        builder.a(BaseApplication.getContext().getString(R.string._2130850331_res_0x7f02321b)).czh_(inflate, 0, 0).cze_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: mvb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                mvc.cpW_(view);
            }
        });
        builder.e().show();
    }

    static /* synthetic */ void cpW_(View view) {
        LogUtil.a("SaveLocalInteractor", "onClick positive view");
        ViewClickInstrumentation.clickOnView(view);
    }
}
