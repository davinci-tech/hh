package defpackage;

import com.google.gson.reflect.TypeToken;
import com.huawei.tabtemplate.BaseTemplateConfig;
import com.huawei.ui.main.stories.template.BaseParser;

/* loaded from: classes7.dex */
public class rsm implements BaseParser {
    @Override // com.huawei.ui.main.stories.template.BaseParser
    public <T extends BaseTemplateConfig> rxy<T> parse(String str) {
        return (rxy) rzl.e(str, new TypeToken<rxy<rsf>>() { // from class: rsm.3
        }.getType());
    }
}
