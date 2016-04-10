package org.rust.ide.typing

import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate.Result
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiFile
import org.rust.lang.core.psi.impl.RustFile

/**
 * Inserts and maintains backslashes at the end of a multiline string upon enter keypress:
 *
 * ```
 * let s = "hello <caret>world";
 *
 * // enter is pressed
 *
 * let s = "hello \
 *          <caret>world";
 *
 * let s = "hello \
 *          worl<caret>d";
 *
 * // enter is pressed
 *
 * let s = "hello \
 *          worl\
 *          <caret>d";
 * ```
 */
class RustStringEnterHandler : EnterHandlerDelegateAdapter() {
    override fun preprocessEnter(file: PsiFile, editor: Editor, caretOffset: Ref<Int>, caretAdvance: Ref<Int>, dataContext: DataContext, originalHandler: EditorActionHandler?): EnterHandlerDelegate.Result? {
        if (file !is RustFile) {
            return Result.Continue
        }

    }
}
