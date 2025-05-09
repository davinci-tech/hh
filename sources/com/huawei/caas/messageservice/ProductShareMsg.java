package com.huawei.caas.messageservice;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import com.huawei.caas.messageservice.HwShareMsgInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes8.dex */
public class ProductShareMsg extends HwShareMsgInfo {
    private static final String TAG = "ProductShareMsg";
    private String mPrice;
    private byte[] mThumbData;

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected int shareType() {
        return 15;
    }

    public static class Builder extends HwShareMsgInfo.caassharea<Builder> {
        private String mPrice;
        private byte[] mThumbData;

        public Builder setThumbData(byte[] bArr) {
            if (bArr == null) {
                Log.e(ProductShareMsg.TAG, "thumb data is null");
                return this;
            }
            this.mThumbData = (byte[]) bArr.clone();
            return this;
        }

        public Builder setThumbData(Bitmap bitmap) {
            if (bitmap == null) {
                Log.e(ProductShareMsg.TAG, "thumb bitmap is null");
                return this;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                int caassharea = (int) caassharea.caassharea();
                int i = caassharea * 190;
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
                    Log.e(ProductShareMsg.TAG, "byte array error");
                }
            }
        }

        public Builder setPrice(String str) {
            this.mPrice = str;
            return this;
        }

        public ProductShareMsg build() {
            if (this.mPrice == null || this.mThumbData == null) {
                return null;
            }
            return new ProductShareMsg(this);
        }
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected void shareDataSerialize(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "bundle is null");
            return;
        }
        super.shareDataSerialize(bundle);
        bundle.putString("ShareDescription", this.mPrice);
        bundle.putByteArray("ShareData", this.mThumbData);
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected boolean shareDataCheckArgs() {
        String str;
        String str2 = this.mPrice;
        if (str2 == null || str2.length() <= 1024) {
            byte[] bArr = this.mThumbData;
            if (bArr == null || bArr.length <= 153600) {
                return super.shareDataCheckArgs();
            }
            str = "check args fail, thumb data is invalid";
        } else {
            str = "check args fail, description is invalid";
        }
        Log.e(TAG, str);
        return false;
    }

    private ProductShareMsg(Builder builder) {
        super(builder);
        this.mPrice = builder.mPrice;
        this.mThumbData = builder.mThumbData;
    }
}
