package com.huawei.health.h5pro.core;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.webkit.FileDownloadManager;
import com.huawei.health.h5pro.webkit.HpkManager;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class H5ProLoadingView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    public final ImageView f2370a;
    public final RelativeLayout e;

    public void initLoadingView(H5ProCommand h5ProCommand, H5ProInstance h5ProInstance) {
        final String packageName = h5ProCommand.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            final WeakReference weakReference = new WeakReference(h5ProInstance);
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProLoadingView.1
                @Override // java.lang.Runnable
                public void run() {
                    final boolean hasNewVersion = HpkManager.b.hasNewVersion(H5ProLoadingView.this.getContext(), packageName);
                    H5ProLoadingView.this.post(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProLoadingView.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            H5ProInstance h5ProInstance2 = (H5ProInstance) GeneralUtil.getReferent(weakReference);
                            if (h5ProInstance2 == null || h5ProInstance2.isLoaded()) {
                                LogUtil.i("H5PRO_H5ProLoadingView", "initLoadingView - > h5ProInstance is null or loaded.");
                                return;
                            }
                            H5ProLoadingView.this.e.setVisibility(8);
                            H5ProLoadingView.this.f2370a.setVisibility(hasNewVersion ? 0 : 8);
                            H5ProLoadingView.this.setVisibility(hasNewVersion ? 0 : 8);
                        }
                    });
                    if (hasNewVersion) {
                        H5ProLoadingView.this.e(packageName, (H5ProInstance) GeneralUtil.getReferent(weakReference));
                    }
                }
            });
            newSingleThreadExecutor.shutdown();
            return;
        }
        if (TextUtils.isEmpty(h5ProCommand.getUrl())) {
            setVisibility(8);
            return;
        }
        this.f2370a.setVisibility(8);
        this.e.setVisibility(0);
        setVisibility(0);
    }

    private void e(final H5LoadingConfigObj h5LoadingConfigObj, final H5ProInstance h5ProInstance) {
        post(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProLoadingView.3
            @Override // java.lang.Runnable
            public void run() {
                H5ProInstance h5ProInstance2 = h5ProInstance;
                if (h5ProInstance2 == null || h5ProInstance2.isLoaded()) {
                    LogUtil.i("H5PRO_H5ProLoadingView", "showH5MiniProgramLoadingView - > h5ProInstance is null or loaded.");
                    return;
                }
                H5LoadingConfigObj h5LoadingConfigObj2 = h5LoadingConfigObj;
                if (h5LoadingConfigObj2 == null || TextUtils.isEmpty(h5LoadingConfigObj2.getBg())) {
                    H5ProLoadingView.this.f2370a.setVisibility(8);
                    H5ProLoadingView.this.e.setVisibility(0);
                    return;
                }
                String color = h5LoadingConfigObj.getColor();
                if (!TextUtils.isEmpty(color)) {
                    H5ProLoadingView.this.setBackgroundColor(Color.parseColor(color));
                }
                H5ProLoadingView.this.e.setVisibility(8);
                H5ProLoadingView.this.f2370a.setVisibility(0);
                Glide.with(H5ProLoadingView.this.getContext()).load(h5LoadingConfigObj.getBg()).into(H5ProLoadingView.this.f2370a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2, H5ProInstance h5ProInstance) {
        if (TextUtils.isEmpty(str)) {
            e((H5LoadingConfigObj) null, h5ProInstance);
            return;
        }
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("loading");
            H5LoadingConfigObj h5LoadingConfigObj = optJSONObject == null ? null : (H5LoadingConfigObj) GsonUtil.parseJson(optJSONObject.toString(), H5LoadingConfigObj.class);
            if (h5LoadingConfigObj != null) {
                h5LoadingConfigObj.setBaseUrl(str2);
            }
            e(h5LoadingConfigObj, h5ProInstance);
        } catch (JSONException e) {
            LogUtil.e("H5PRO_H5ProLoadingView", "parsingConfigData: exception -> " + e.getMessage());
            e((H5LoadingConfigObj) null, h5ProInstance);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final String str, final H5ProInstance h5ProInstance) {
        String versionFilePath = HpkManager.b.getVersionFilePath(getContext(), str);
        if (FileDownloadManager.exists(versionFilePath)) {
            b(FileDownloadManager.readFileContent(getContext(), versionFilePath), str, h5ProInstance);
        } else {
            FileDownloadManager.download(HpkManager.b.getVersionDownloadUrl(str), new File(HpkManager.b.getH5WorkPath(getContext(), str), HpkManager.b.getVersionFileName(str)), new FileDownloadManager.DownloadCallback() { // from class: com.huawei.health.h5pro.core.H5ProLoadingView.2
                @Override // com.huawei.health.h5pro.webkit.FileDownloadManager.DownloadCallback
                public void onSuccess(File file) {
                    H5ProLoadingView h5ProLoadingView = H5ProLoadingView.this;
                    h5ProLoadingView.b(FileDownloadManager.readFileContent(h5ProLoadingView.getContext(), file), str, h5ProInstance);
                }

                @Override // com.huawei.health.h5pro.webkit.FileDownloadManager.DownloadCallback
                public void onFailure(int i, String str2) {
                    LogUtil.e("H5PRO_H5ProLoadingView", "initH5MiniProgramLoadingView: onFailure -> " + i + " - " + str2);
                    H5ProLoadingView.this.b("", "", h5ProInstance);
                }
            });
        }
    }

    public H5ProLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, R.layout.view_h5pro_loading, null);
        this.f2370a = (ImageView) inflate.findViewById(R.id.iv_bgView);
        this.e = (RelativeLayout) inflate.findViewById(R.id.rl_defLoadingView);
        addView(inflate);
    }

    public H5ProLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public H5ProLoadingView(Context context) {
        this(context, null);
    }
}
