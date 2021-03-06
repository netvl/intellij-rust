package org.rust.ide.spellchecker

import com.intellij.psi.PsiElement
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy
import com.intellij.spellchecker.tokenizer.Tokenizer
import org.rust.lang.RustLanguage
import org.rust.lang.core.psi.RustTokenElementTypes.STRING_LITERAL

class RustSpellcheckingStrategy : SpellcheckingStrategy() {
    private val stringLiteralTokenizer = StringLiteralTokenizer()

    override fun isMyContext(element: PsiElement) = RustLanguage.`is`(element.language)

    override fun getTokenizer(element: PsiElement?): Tokenizer<*> {
        if (element?.node?.elementType == STRING_LITERAL) {
            return stringLiteralTokenizer
        }
        return super.getTokenizer(element)
    }
}

