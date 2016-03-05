package org.rust.ide.inspections

import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import org.rust.lang.core.psi.RustCompositeElement
import org.rust.lang.core.psi.RustNamedElement
import org.rust.lang.core.psi.RustVisitor

class UnresolvedReferenceInspection : RustLocalInspectionTool() {

    override fun getDisplayName(): String = "Unresolved Reference"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : RustVisitor() {
            override fun visitCompositeElement(o: RustCompositeElement) {
                val ref = o.reference ?: return
                if (!o.textRange.isEmpty && ref.resolve() == null) {
                    val name = if (o is RustNamedElement) o.nameElement ?: o else o
                    holder.registerProblem(name, "Unresolved reference")
                }
            }
        }

}
