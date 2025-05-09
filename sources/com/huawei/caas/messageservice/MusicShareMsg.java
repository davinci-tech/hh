package com.huawei.caas.messageservice;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import com.huawei.caas.messageservice.HwShareMsgInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes8.dex */
public class MusicShareMsg extends HwShareMsgInfo {
    private static final String TAG = "MusicShareMsg";
    private String mArtistName;
    private String mDataUrl;
    private byte[] mThumbData;

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected int shareType() {
        return 18;
    }

    public static class Builder extends HwShareMsgInfo.caassharea<Builder> {
        private String mArtistName;
        private String mDataUrl;
        private byte[] mThumbData;

        public Builder setThumbData(byte[] bArr) {
            if (bArr == null) {
                Log.e(MusicShareMsg.TAG, "thumb data is null");
                return this;
            }
            this.mThumbData = (byte[]) bArr.clone();
            return this;
        }

        public Builder setThumbData(Bitmap bitmap) {
            if (bitmap == null) {
                Log.e(MusicShareMsg.TAG, "thumb bitmap is null");
                return this;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                int caassharea = (int) caassharea.caassharea();
                int i = caassharea * 40;
                Bitmap createScaledBitmap = (caassharea == 0 || (bitmap.getWidth() <= i && bitmap.getHeight() <= i)) ? bitmap : Bitmap.createScaledBitmap(bitmap, i, i, true);
                createScaledBitmap.compress(Bitmap.CompressFormat.WEBP, 85, byteArrayOutputStream);
                this.mThumbData = byteArrayOutputStream.toByteArray();
                createScaledBitmap.recycle();
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                return this;
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                    Log.e(MusicShareMsg.TAG, "byte array error");
                }
            }
        }

        public Builder setDataUrl(String str) {
            this.mDataUrl = str;
            return this;
        }

        public Builder setArtistName(String str) {
            this.mArtistName = str;
            return this;
        }

        public MusicShareMsg build() {
            if (this.mArtistName != null && this.mThumbData != null && this.mDataUrl != null) {
                return new MusicShareMsg(this);
            }
            Log.e(MusicShareMsg.TAG, "MusicShareMsg data builder error");
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
        bundle.putString("ShareDescription", this.mArtistName);
        bundle.putByteArray("ShareData", this.mThumbData);
        bundle.putString("ShareUrlExt", this.mDataUrl);
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected boolean shareDataCheckArgs() {
        String str;
        String str2 = this.mArtistName;
        if (str2 == null || str2.length() <= 1024) {
            byte[] bArr = this.mThumbData;
            if (bArr == null || bArr.length <= 32768) {
                String str3 = this.mDataUrl;
                if (str3 == null || str3.length() <= 10240) {
                    return super.shareDataCheckArgs();
                }
                str = " data URL is invalid";
            } else {
                str = "thumb data is invalid";
            }
        } else {
            str = "name data is invalid";
        }
        Log.e(TAG, str);
        return false;
    }

    private MusicShareMsg(Builder builder) {
        super(builder);
        this.mArtistName = builder.mArtistName;
        this.mThumbData = builder.mThumbData;
        this.mDataUrl = builder.mDataUrl;
    }
}
