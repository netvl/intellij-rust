package org.rust.ide.structure

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import org.rust.lang.core.psi.RustStructDeclField

class RustStructDeclFieldTreeElement(element: RustStructDeclField) : PsiTreeElementBase<RustStructDeclField>(element) {

    override fun getPresentableText() = "${element?.identifier?.text}: ${element?.type?.text}"

    override fun getChildrenBase() = arrayListOf<StructureViewTreeElement>()
}
