package com.huawei.caas.messageservice;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import com.huawei.caas.messageservice.HwShareMsgInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes8.dex */
public class WebPageShareMsg extends HwShareMsgInfo {
    private static final String TAG = "WebPageShareMsg";
    private String mDescription;
    private byte[] mThumbData;

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected int shareType() {
        return 17;
    }

    public static class Builder extends HwShareMsgInfo.caassharea<Builder> {
        private String mDescription;
        private byte[] mThumbData;

        public Builder setThumbData(byte[] bArr) {
            if (bArr == null) {
                Log.e(WebPageShareMsg.TAG, "thumb data is null");
                return this;
            }
            this.mThumbData = (byte[]) bArr.clone();
            return this;
        }

        public Builder setThumbData(Bitmap bitmap) {
            Bitmap bitmap2;
            if (bitmap == null) {
                Log.e(WebPageShareMsg.TAG, "thumb bitmap is null");
                return this;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                int caassharea = (int) caassharea.caassharea();
                int i = caassharea * 40;
                if (caassharea == 0 || (bitmap.getWidth() <= i && bitmap.getHeight() <= i)) {
                    bitmap2 = bitmap;
                } else {
                    Log.d(WebPageShareMsg.TAG, "setThumbData origin width " + bitmap.getWidth() + " origin height" + bitmap.getHeight());
                    bitmap2 = Bitmap.createScaledBitmap(bitmap, i, i, true);
                }
                bitmap2.compress(Bitmap.CompressFormat.WEBP, 85, byteArrayOutputStream);
                this.mThumbData = byteArrayOutputStream.toByteArray();
                Log.d(WebPageShareMsg.TAG, "setThumbData width " + this.mThumbData.length);
                bitmap2.recycle();
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                return this;
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                    Log.e(WebPageShareMsg.TAG, "byte array error");
                }
            }
        }

        public Builder setDescription(String str) {
            this.mDescription = str;
            return this;
        }

        public WebPageShareMsg build() {
            if (this.mDescription != null && this.mThumbData != null) {
                return new WebPageShareMsg(this);
            }
            Log.e(WebPageShareMsg.TAG, "WebPageShareMsg ERROR");
            return null;
        }
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected void shareDataSerialize(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "bundle is null");
            return;
        }
        super.shareDataSerialize(bundle);
        bundle.putString("ShareDescription", this.mDescription);
        bundle.putByteArray("ShareData", this.mThumbData);
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected boolean shareDataCheckArgs() {
        String str;
        String str2 = this.mDescription;
        if (str2 == null || str2.length() <= 1024) {
            byte[] bArr = this.mThumbData;
            if (bArr == null || bArr.length <= 32768) {
                return super.shareDataCheckArgs();
            }
            str = "check args fail, thumb data is invalid";
        } else {
            str = "check args fail, description is invalid";
        }
        Log.e(TAG, str);
        return false;
    }

    private WebPageShareMsg(Builder builder) {
        super(builder);
        this.mDescription = builder.mDescription;
        this.mThumbData = builder.mThumbData;
    }
}
