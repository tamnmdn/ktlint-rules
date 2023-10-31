package com.nmt.ktlint_custom.extension

import com.pinterest.ktlint.core.ast.ElementType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

/**
 * Check if current node is a Binary expression which contains null comparison
 * @return Boolean
 */
fun ASTNode.isNullableComparison(): Boolean {
    if (elementType != ElementType.BINARY_EXPRESSION) return false
    val operation = findChildByType(ElementType.OPERATION_REFERENCE)
    val compareValEle = findChildByType(ElementType.NULL)
    return operation?.firstChildNode?.elementType == ElementType.EQEQ && compareValEle != null
}
