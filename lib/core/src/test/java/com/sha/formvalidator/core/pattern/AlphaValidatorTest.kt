package com.sha.formvalidator.core.pattern

import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.pattern.AlphaValidator
import org.junit.Before
import org.junit.Test

class AlphaValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = AlphaValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        validator.value = "rrlll"
        assert(validator.validate())
    }

    @Test
    fun validate_invalid() {
        validator.value = "11*%"
        assert(!validator.validate())
    }
}