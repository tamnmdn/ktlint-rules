package com.nmt.ktlint_custom

import com.pinterest.ktlint.core.RuleSet
import com.pinterest.ktlint.core.RuleSetProvider

class CustomRuleSetProvider : RuleSetProvider {

    override fun get(): RuleSet {
        return RuleSet(
            "ktlint-custom-rules",
            // NoElseIfConditionRule(),
            NoSingleNullableCheckRule()
        )
    }
}
