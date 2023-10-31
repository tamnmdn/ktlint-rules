package com.nmt.ktlint_custom

import com.pinterest.ktlint.core.Rule
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class NoElseIfConditionRule : Rule("no-else-if-condition") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType == KtNodeTypes.IF) {
            node.findChildByType(KtNodeTypes.ELSE)?.let {
                if (it.firstChildNode.elementType == KtNodeTypes.IF) {
                    emit(node.firstChildNode.startOffset, "Please use syntax WHEN to replace ELSE IF", false)
                }
            }
        }
    }
}