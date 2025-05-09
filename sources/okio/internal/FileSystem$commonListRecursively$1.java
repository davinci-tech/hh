package okio.internal;

import defpackage.createFailure;
import defpackage.ueu;
import defpackage.uew;
import defpackage.ugw;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import okio.FileSystem;
import okio.Path;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lokio/Path;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "okio.internal.-FileSystem$commonListRecursively$1", f = "FileSystem.kt", i = {0, 0}, l = {96}, m = "invokeSuspend", n = {"$this$sequence", "stack"}, s = {"L$0", "L$1"})
/* renamed from: okio.internal.-FileSystem$commonListRecursively$1, reason: invalid class name */
/* loaded from: classes10.dex */
final class FileSystem$commonListRecursively$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super ueu>, Object> {
    final /* synthetic */ Path $dir;
    final /* synthetic */ boolean $followSymlinks;
    final /* synthetic */ FileSystem $this_commonListRecursively;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SequenceScope sequenceScope;
        uew uewVar;
        Iterator<Path> it;
        Object a2 = ugw.a();
        int i = this.label;
        if (i == 0) {
            createFailure.b(obj);
            sequenceScope = (SequenceScope) this.L$0;
            uewVar = new uew();
            uewVar.c((uew) this.$dir);
            it = this.$this_commonListRecursively.list(this.$dir).iterator();
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (Iterator) this.L$2;
            uewVar = (uew) this.L$1;
            sequenceScope = (SequenceScope) this.L$0;
            createFailure.b(obj);
        }
        uew uewVar2 = uewVar;
        SequenceScope sequenceScope2 = sequenceScope;
        while (it.hasNext()) {
            Path next = it.next();
            this.L$0 = sequenceScope2;
            this.L$1 = uewVar2;
            this.L$2 = it;
            this.label = 1;
            if (FileSystem.collectRecursively(sequenceScope2, this.$this_commonListRecursively, uewVar2, next, this.$followSymlinks, false, this) == a2) {
                return a2;
            }
        }
        return ueu.d;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super ueu> continuation) {
        return ((FileSystem$commonListRecursively$1) create(sequenceScope, continuation)).invokeSuspend(ueu.d);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
        FileSystem$commonListRecursively$1 fileSystem$commonListRecursively$1 = new FileSystem$commonListRecursively$1(this.$dir, this.$this_commonListRecursively, this.$followSymlinks, continuation);
        fileSystem$commonListRecursively$1.L$0 = obj;
        return fileSystem$commonListRecursively$1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FileSystem$commonListRecursively$1(Path path, FileSystem fileSystem, boolean z, Continuation<? super FileSystem$commonListRecursively$1> continuation) {
        super(2, continuation);
        this.$dir = path;
        this.$this_commonListRecursively = fileSystem;
        this.$followSymlinks = z;
    }
}
