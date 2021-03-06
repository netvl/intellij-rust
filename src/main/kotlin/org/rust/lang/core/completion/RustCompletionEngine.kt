package org.rust.lang.core.completion

import org.rust.lang.core.psi.*
import org.rust.lang.core.psi.impl.mixin.basePath
import org.rust.lang.core.psi.impl.mixin.letDeclarationsVisibleAt
import org.rust.lang.core.resolve.enumerateScopesFor
import org.rust.lang.core.resolve.scope.RustResolveScope
import org.rust.lang.core.resolve.scope.boundElements
import java.util.*

object RustCompletionEngine {
    fun complete(ref: RustQualifiedReferenceElement): Collection<RustNamedElement> =
        collectNamedElements(ref).filter { it.name != null }

    fun complete(glob: RustUseGlob): Collection<RustNamedElement> =
        glob.basePath?.reference?.resolve()
            .completionsFromResolveScope()

    private fun collectNamedElements(ref: RustQualifiedReferenceElement): Collection<RustNamedElement> {
        val qual = ref.qualifier
        if (qual != null) {
            return qual.reference.resolve()
                .completionsFromResolveScope()
        }

        val visitor = CompletionScopeVisitor(ref)
        for (scope in enumerateScopesFor(ref)) {
            scope.accept(visitor)
        }

        return visitor.completions
    }
}


private class CompletionScopeVisitor(private val context: RustQualifiedReferenceElement) : RustVisitor() {

    val completions: MutableSet<RustNamedElement> = HashSet()

    override fun visitModItem(o: RustModItem)                         = visitResolveScope(o)
    override fun visitScopedLetExpr(o: RustScopedLetExpr)             = visitResolveScope(o)
    override fun visitLambdaExpr(o: RustLambdaExpr)                   = visitResolveScope(o)
    override fun visitTraitMethodMember(o: RustTraitMethodMember)     = visitResolveScope(o)
    override fun visitFnItem(o: RustFnItem)                           = visitResolveScope(o)

    override fun visitResolveScope(scope: RustResolveScope) {
        completions.addAll(scope.boundElements)
    }

    override fun visitForExpr(o: RustForExpr) {
        completions.addAll(o.scopedForDecl.boundElements)
    }

    override fun visitBlock(block: RustBlock) {
        block.letDeclarationsVisibleAt(context)
            .flatMapTo(completions) { it.boundElements.asSequence() }
    }
}

private fun RustNamedElement?.completionsFromResolveScope(): Collection<RustNamedElement> =
    when (this) {
        is RustResolveScope -> boundElements
        else -> emptyList()
    }
