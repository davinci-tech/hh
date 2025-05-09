package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.dialog.SelectAdapter;
import com.huawei.health.ecologydevice.ui.dialog.SelectDialog;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ProgressListener;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.dds;
import defpackage.jah;
import defpackage.koq;
import defpackage.lqg;
import defpackage.lqi;
import defpackage.msb;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.UnitUtil;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class MediaManager {
    public static final String ECOLOGY_DEVICE_DIR;
    private static final float FILE_SIZE_CALCULATION = 1024.0f;
    public static final float FILE_SIZE_CALCULATION_ROPE = 10.0f;
    private static final DecimalFormat FORMAT_FILE_SIZE;
    public static final String KEY_MEDIA_LIST = "key_media_list";
    public static final String KEY_MEDIA_TYPE = "key_media_type";
    public static final String KEY_REMAINING_MEMORY = "key_remaining_memory";
    public static final int MEDIA_TYPE_COURSE = 0;
    public static final int MEDIA_TYPE_PLAYLIST = 1;
    public static final int MIN_DELAY_TIME = 500;
    public static final String ROPE_GET_CONFIG = "/hw/course/getConfig";
    public static final String ROPE_GET_MUSIC_CONFIG = "/hw/course/getMusicConfig";
    public static final String ROPE_JSON_FILE;
    public static final String ROPE_MEDIA_COURSE_JSON = "course.json";
    public static final String ROPE_MEDIA_HIGH = "high";
    public static final String ROPE_MEDIA_MUSIC_JSON = "music.json";
    public static final String ROPE_MEDIA_STANDARD = "standard";
    public static final String SPACE = " ";
    private static final String TAG;
    private SelectDialog mDeviceSelectDialog;
    private NoTitleCustomAlertDialog mDownloadDialog;
    private NoTitleCustomAlertDialog mNoTitleCustomAlertDialog;

    public long getRopeMemoryConvertByte(long j) {
        return (long) (((j * FILE_SIZE_CALCULATION) * FILE_SIZE_CALCULATION) / 10.0f);
    }

    static {
        String i = CommonUtils.i("ecologydevice");
        ECOLOGY_DEVICE_DIR = i;
        ROPE_JSON_FILE = i + "/ropejsonfile";
        TAG = "MediaManager";
        FORMAT_FILE_SIZE = new DecimalFormat("###.#");
    }

    public void downloadJsonFile(final int i, boolean z, final CommonUiResponse<String> commonUiResponse) {
        File file = new File(ROPE_JSON_FILE, i == 0 ? ROPE_MEDIA_COURSE_JSON : ROPE_MEDIA_MUSIC_JSON);
        if (file.exists() && file.length() > 0 && z) {
            LogUtil.a(TAG, "file exists and existNotDownload is true");
            return;
        }
        if (file.getParentFile().exists() || file.mkdirs()) {
            ProgressListener<File> progressListener = new ProgressListener<File>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager.1
                @Override // com.huawei.networkclient.ProgressListener
                public void onProgress(long j, long j2, boolean z2) {
                }

                @Override // com.huawei.networkclient.ProgressListener
                public void onFinish(File file2) {
                    LogUtil.a(MediaManager.TAG, "downloadJsonFile onFinish");
                    CommonUiResponse commonUiResponse2 = commonUiResponse;
                    if (commonUiResponse2 == null) {
                        MediaManager.this.getMediaDataFromJson(i);
                    } else {
                        commonUiResponse2.onResponse(0, "");
                    }
                }

                @Override // com.huawei.networkclient.ProgressListener
                public void onFail(Throwable th) {
                    LogUtil.a(MediaManager.TAG, "downloadJsonFile onFail, throwable.getMessage() = ", th.getMessage());
                    CommonUiResponse commonUiResponse2 = commonUiResponse;
                    if (commonUiResponse2 != null) {
                        commonUiResponse2.onResponse(-1, th.getMessage());
                    }
                }
            };
            StringBuilder sb = new StringBuilder();
            sb.append(jah.c().e("domain_rope_course"));
            sb.append(i == 0 ? ROPE_GET_CONFIG : ROPE_GET_MUSIC_CONFIG);
            lqi.d().d(new lqg(sb.toString(), null, file, progressListener));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMediaDataFromJson(int i) {
        CourseModel courseModel;
        String a2 = msb.a(new File(ROPE_JSON_FILE, i == 0 ? ROPE_MEDIA_COURSE_JSON : ROPE_MEDIA_MUSIC_JSON));
        if (TextUtils.isEmpty(a2) || (courseModel = (CourseModel) new Gson().fromJson(a2, new TypeToken<CourseModel>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager.2
        }.getType())) == null) {
            return;
        }
        int dimensionPixelOffset = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363088_res_0x7f0a0510);
        Glide.with(BaseApplication.getContext()).load(courseModel.getBannerImgUrl()).into(dimensionPixelOffset, dimensionPixelOffset);
        if (koq.b(courseModel.getCourses())) {
            return;
        }
        Iterator<CourseModel.Course> it = courseModel.getCourses().iterator();
        while (it.hasNext()) {
            Glide.with(BaseApplication.getContext()).load(it.next().getSmallImgUrl()).into(dimensionPixelOffset, dimensionPixelOffset);
        }
    }

    public void showDownloadDialog(Context context, int i, long j, long j2, final CommonUiResponse<String> commonUiResponse) {
        NoTitleCustomAlertDialog.Builder czz_;
        String string;
        if (context == null || commonUiResponse == null) {
            LogUtil.h(TAG, "showDownloadDialog context or callback is null");
            return;
        }
        if (!CommonUtil.aa(context)) {
            LogUtil.h(TAG, "showDownloadDialog network not connect");
            nrh.e(context, R.string._2130844160_res_0x7f021a00);
            return;
        }
        if (isRopeDeviceConnected(context)) {
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MediaManager.this.m390x7a07990d(view);
                }
            };
            if (j < j2) {
                if (i == 0) {
                    string = context.getString(R.string._2130850424_res_0x7f023278);
                } else {
                    string = context.getString(R.string._2130850425_res_0x7f023279);
                }
                czz_ = new NoTitleCustomAlertDialog.Builder(context).e(string).czz_(R.string._2130841130_res_0x7f020e2a, onClickListener);
            } else if (!NetworkUtil.m()) {
                czz_ = new NoTitleCustomAlertDialog.Builder(context).e(context.getString(R.string._2130850450_res_0x7f023292, context.getResources().getQuantityString(R.plurals._2130903230_res_0x7f0300be, 0, getByteConvertMb(j2)))).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MediaManager.this.m391x792e286c(commonUiResponse, view);
                    }
                }).czz_(R.string._2130841130_res_0x7f020e2a, onClickListener);
            } else {
                commonUiResponse.onResponse(0, "");
                return;
            }
            NoTitleCustomAlertDialog e = czz_.e();
            this.mDownloadDialog = e;
            e.show();
        }
    }

    /* renamed from: lambda$showDownloadDialog$0$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-MediaManager, reason: not valid java name */
    /* synthetic */ void m390x7a07990d(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.mDownloadDialog;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.mDownloadDialog = null;
        } else {
            LogUtil.h(TAG, "showDownloadDialog mDeleteDialog setNegativeButton null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showDownloadDialog$1$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-MediaManager, reason: not valid java name */
    /* synthetic */ void m391x792e286c(CommonUiResponse commonUiResponse, View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.mDownloadDialog;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.mDownloadDialog = null;
            commonUiResponse.onResponse(0, "");
        } else {
            LogUtil.h(TAG, "showDownloadDialog mDeleteDialog setPositiveButton null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void setImageHeight(Context context, int i, float f, HealthImageView healthImageView) {
        if (context == null || healthImageView == null) {
            LogUtil.h(TAG, "setImageHeight context or imageView is null");
            return;
        }
        Object systemService = context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.b(TAG, "object is invalid type");
            return;
        }
        if (((WindowManager) systemService) == null) {
            LogUtil.b(TAG, "windowManager is null");
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) ((r2.getDefaultDisplay().getWidth() - (i + i)) * f));
        layoutParams.setMarginStart(i);
        layoutParams.setMarginEnd(i);
        healthImageView.setLayoutParams(layoutParams);
    }

    public void showRadioButtonSelectDialog(Context context, LinkedHashMap<String, String> linkedHashMap, final CommonUiResponse<String> commonUiResponse) {
        if (context == null || linkedHashMap == null || commonUiResponse == null) {
            LogUtil.h(TAG, "showRadioButtonSelectDialog context or hashMap or callback is null");
            return;
        }
        SelectDialog d = new SelectDialog.Builder(context).c(linkedHashMap).c(R.string._2130841131_res_0x7f020e2b, new SelectAdapter.OnItemClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager$$ExternalSyntheticLambda2
            @Override // com.huawei.health.ecologydevice.ui.dialog.SelectAdapter.OnItemClickListener
            public final void onItemClick(String str) {
                MediaManager.this.m394xd0e3b93e(commonUiResponse, str);
            }
        }).Uf_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MediaManager.this.mDeviceSelectDialog != null) {
                    MediaManager.this.mDeviceSelectDialog.dismiss();
                    MediaManager.this.mDeviceSelectDialog = null;
                } else {
                    LogUtil.h(MediaManager.TAG, "showRadioButtonSelectDialog setNegativeButton is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).d();
        this.mDeviceSelectDialog = d;
        d.show();
    }

    /* renamed from: lambda$showRadioButtonSelectDialog$2$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-MediaManager, reason: not valid java name */
    /* synthetic */ void m394xd0e3b93e(CommonUiResponse commonUiResponse, String str) {
        SelectDialog selectDialog = this.mDeviceSelectDialog;
        if (selectDialog != null) {
            selectDialog.dismiss();
            this.mDeviceSelectDialog = null;
            commonUiResponse.onResponse(0, str);
            return;
        }
        LogUtil.h(TAG, "showRadioButtonSelectDialog setPositiveButton is null");
    }

    public LinkedHashMap<String, String> getSelectHashMap(Context context) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (context == null) {
            return linkedHashMap;
        }
        linkedHashMap.put(ROPE_MEDIA_STANDARD, context.getResources().getString(R.string._2130850433_res_0x7f023281));
        linkedHashMap.put(ROPE_MEDIA_HIGH, context.getResources().getString(R.string._2130850431_res_0x7f02327f));
        return linkedHashMap;
    }

    public void showNoTitleCustomAlertDialog(Context context, int i, int i2, final CommonUiResponse<String> commonUiResponse) {
        if (context == null || commonUiResponse == null) {
            LogUtil.h(TAG, "showNoTitleCustomAlertDialog context or callback is null");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(i).czC_(i2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MediaManager.this.m392x8df7e9de(commonUiResponse, view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MediaManager.this.m393x8d1e793d(view);
            }
        }).e();
        this.mNoTitleCustomAlertDialog = e;
        e.show();
    }

    /* renamed from: lambda$showNoTitleCustomAlertDialog$3$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-MediaManager, reason: not valid java name */
    /* synthetic */ void m392x8df7e9de(CommonUiResponse commonUiResponse, View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.mNoTitleCustomAlertDialog;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.mNoTitleCustomAlertDialog = null;
            commonUiResponse.onResponse(0, "");
        } else {
            LogUtil.h(TAG, "showNoTitleCustomAlertDialog setPositiveButton null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showNoTitleCustomAlertDialog$4$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-mediamanager-MediaManager, reason: not valid java name */
    /* synthetic */ void m393x8d1e793d(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.mNoTitleCustomAlertDialog;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.mNoTitleCustomAlertDialog = null;
        } else {
            LogUtil.h(TAG, "showNoTitleCustomAlertDialog setNegativeButton null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public String getByteConvertMb(long j) {
        return FORMAT_FILE_SIZE.format(j / 1048576.0f);
    }

    public String getRopeMemoryConvertMb(long j) {
        return FORMAT_FILE_SIZE.format(j / 10.0f);
    }

    public SpannableString getSpannableString(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h(TAG, "getSpannableString context null");
            return null;
        }
        return UnitUtil.bCR_(context, str, str2, R.style.img_data_items_big, R.style.img_data_items);
    }

    public boolean isRopeDeviceConnected(Context context) {
        boolean f = dds.c().f();
        if (!f && context != null) {
            LogUtil.h(TAG, "Rope Device disconnect");
            nrh.b(context, R.string._2130845226_res_0x7f021e2a);
        }
        return f;
    }

    public boolean isHasMediaSelected(List<CourseModel.Course> list) {
        if (koq.b(list)) {
            return false;
        }
        Iterator<CourseModel.Course> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().isCheckboxCheck()) {
                return true;
            }
        }
        return false;
    }

    public boolean isAllSelected(boolean z, List<CourseModel.Course> list) {
        if (koq.b(list)) {
            return false;
        }
        for (CourseModel.Course course : list) {
            if (z && course.getStatus() < 2 && !course.isCheckboxCheck()) {
                return false;
            }
            if (!z && course.getStatus() >= 2 && !course.isCheckboxCheck()) {
                return false;
            }
            LogUtil.h(TAG, "isAllSelected else");
        }
        return true;
    }

    public int getIntValue(HashMap<Integer, Object> hashMap, int i) {
        if (hashMap.get(Integer.valueOf(i)) == null || !(hashMap.get(Integer.valueOf(i)) instanceof Integer)) {
            return 0;
        }
        return ((Integer) hashMap.get(Integer.valueOf(i))).intValue();
    }
}
