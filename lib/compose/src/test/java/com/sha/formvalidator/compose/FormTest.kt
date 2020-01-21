package com.sha.formvalidator.compose

import org.junit.Before
import org.junit.Test

class FormTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `isValid, should invoke validator#isValid`() {
        val m1 = FakeValidation.create().addTo(form) as FakeValidation
        val m2 = FakeValidation.create().addTo(form) as FakeValidation

        form.isValid

        assert(m1.isValidInvoked)
        assert(m2.isValidInvoked)
    }

    @Test
    fun `modelByTag(), should work correctly`() {
        form + FakeValidation.create { tag = "m1" }
        form + FakeValidation.create { tag = "m2" }

        assert(form.modelByTag("m1") != null)
        assert(form.modelByTag("m2") != null)
        assert(form.modelByTag("x") == null)
    }

    @Test
    fun `removeByTag(), should work correctly`() {
        val m = form + mandatory { tag = "tag"}
        assert(form.size == 1)

        val removed = form.removeByTag(m.tag!!)
        assert(removed != null)
    }

    @Test
    fun `add(), should work correctly`() {
        assert(form.isEmpty)
        form.add(mandatory())
        assert(!form.isEmpty)
    }

    @Test
    fun `remove(), should work correctly`() {
        val m1 = mandatory()
        val m2 = mandatory()
        val m3 = mandatory()
        form.add(m1, m2, m3)
        assert(form.size == 3)

        form.remove(m1)
        assert(form.size == 2)

        form.remove(m2, m3)
        assert(form.isEmpty)
    }

    @Test
    fun `plus(), should work correctly`() {
        form + mandatory()
        assert(form.size == 1)
    }

    @Test
    fun `minus(), should work correctly`() {
        val m = form +  mandatory()
        assert(form.size == 1)

        form - m
        assert(form.isEmpty)
    }

}