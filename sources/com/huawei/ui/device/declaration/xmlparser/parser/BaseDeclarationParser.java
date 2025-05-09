package com.huawei.ui.device.declaration.xmlparser.parser;

import defpackage.nzc;
import defpackage.nzd;
import defpackage.nzf;
import defpackage.nzj;
import defpackage.nzn;

/* loaded from: classes6.dex */
public abstract class BaseDeclarationParser implements XmlParser<nzf> {
    protected abstract void parseConfig(nzc nzcVar);

    protected abstract nzd parseContent();

    protected abstract nzf parseDeclaration();

    protected abstract nzn parsePart();

    protected abstract void parsePlaceholder(nzc nzcVar);

    protected abstract void parseString(nzc nzcVar);

    protected abstract nzj parseTitle();
}
