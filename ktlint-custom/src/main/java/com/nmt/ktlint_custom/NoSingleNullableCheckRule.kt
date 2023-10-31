package com.nmt.ktlint_custom

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.ElementType
import com.nmt.ktlint_custom.extension.isNullableComparison
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class NoSingleNullableCheckRule : Rule("no-single-nullable-check") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType == KtNodeTypes.IF) {
            node.findChildByType(KtNodeTypes.CONDITION)
                ?.findChildByType(ElementType.BINARY_EXPRESSION)
                ?.let { expressionNode ->
                    if (expressionNode.isNullableComparison()) {
                        emit(
                            node.firstChildNode.startOffset,
                            "Please use syntax ?.let{} ?: run {} for null check instead",
                            false
                        )
                    }
                }
        }
    }
}
