package com.huawei.operation.utils;

import android.content.Context;
import android.content.Intent;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.operation.activity.WebViewActivity;

@ApiDefine(uri = OperationWebActivityIntentBuilderApi.class)
@Singleton
/* loaded from: classes5.dex */
public class OperationWebActivityIntentBuilderImpl implements OperationWebActivityIntentBuilderApi {
    @Override // com.huawei.operation.utils.OperationWebActivityIntentBuilderApi
    public OperationWebActivityIntentBuilder builder(Context context, String str) {
        return new BuilderImpl(str, new Intent(context, (Class<?>) WebViewActivity.class));
    }

    static class BuilderImpl implements OperationWebActivityIntentBuilder {
        private final Intent intent;

        public BuilderImpl(String str, Intent intent) {
            this.intent = intent;
            intent.putExtra("url", str);
        }

        @Override // com.huawei.operation.utils.OperationWebActivityIntentBuilder
        public Intent build() {
            return this.intent;
        }

        @Override // com.huawei.operation.utils.OperationWebActivityIntentBuilder
        public OperationWebActivityIntentBuilder setBI(String str, String str2, String str3, String str4) {
            this.intent.putExtra("EXTRA_BI_ID", str);
            this.intent.putExtra("EXTRA_BI_NAME", str2);
            this.intent.putExtra("EXTRA_BI_SOURCE", str3);
            this.intent.putExtra("EXTRA_BI_SHOWTIME", str4);
            return this;
        }

        @Override // com.huawei.operation.utils.OperationWebActivityIntentBuilder
        public OperationWebActivityIntentBuilder setFlags(int i) {
            this.intent.setFlags(i);
            return this;
        }

        @Override // com.huawei.operation.utils.OperationWebActivityIntentBuilder
        public OperationWebActivityIntentBuilder setIntType(int i) {
            this.intent.putExtra("type", i);
            return this;
        }

        @Override // com.huawei.operation.utils.OperationWebActivityIntentBuilder
        public OperationWebActivityIntentBuilder setStringType(String str) {
            this.intent.putExtra("type", str);
            return this;
        }
    }
}
