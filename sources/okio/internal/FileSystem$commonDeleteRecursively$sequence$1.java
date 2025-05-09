package okio.internal;

import defpackage.createFailure;
import defpackage.ueu;
import defpackage.uew;
import defpackage.ugw;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import okio.FileSystem;
import okio.Path;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lokio/Path;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "okio.internal.-FileSystem$commonDeleteRecursively$sequence$1", f = "FileSystem.kt", i = {}, l = {75}, m = "invokeSuspend", n = {}, s = {})
/* renamed from: okio.internal.-FileSystem$commonDeleteRecursively$sequence$1, reason: invalid class name */
/* loaded from: classes10.dex */
final class FileSystem$commonDeleteRecursively$sequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super ueu>, Object> {
    final /* synthetic */ Path $fileOrDirectory;
    final /* synthetic */ FileSystem $this_commonDeleteRecursively;
    private /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object a2 = ugw.a();
        int i = this.label;
        if (i == 0) {
            createFailure.b(obj);
            this.label = 1;
            if (FileSystem.collectRecursively((SequenceScope) this.L$0, this.$this_commonDeleteRecursively, new uew(), this.$fileOrDirectory, false, true, this) == a2) {
                return a2;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            createFailure.b(obj);
        }
        return ueu.d;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super ueu> continuation) {
        return ((FileSystem$commonDeleteRecursively$sequence$1) create(sequenceScope, continuation)).invokeSuspend(ueu.d);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<ueu> create(Object obj, Continuation<?> continuation) {
        FileSystem$commonDeleteRecursively$sequence$1 fileSystem$commonDeleteRecursively$sequence$1 = new FileSystem$commonDeleteRecursively$sequence$1(this.$this_commonDeleteRecursively, this.$fileOrDirectory, continuation);
        fileSystem$commonDeleteRecursively$sequence$1.L$0 = obj;
        return fileSystem$commonDeleteRecursively$sequence$1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FileSystem$commonDeleteRecursively$sequence$1(FileSystem fileSystem, Path path, Continuation<? super FileSystem$commonDeleteRecursively$sequence$1> continuation) {
        super(2, continuation);
        this.$this_commonDeleteRecursively = fileSystem;
        this.$fileOrDirectory = path;
    }
}
