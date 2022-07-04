package com.js.apps.moviedbapp.model.entities.media

import org.junit.Assert
import org.junit.Test


class SeriesTest {
    private lateinit var testObject: Serie

    @Test
    fun calculateImage() {
        testObject = Serie(
            0,
            "title",
            "/route/23443j",
            "",
            "",
            "",
            2.3f,
            "",
            "",
            7.3f,
            3
        )
        Assert.assertEquals(
            0,
            testObject.getRatingPorcent(0.0f)
        )
        Assert.assertEquals(
            40,
            testObject.getRatingPorcent(4.0f)
        )
        Assert.assertEquals(
            0,
            testObject.getRatingPorcent(-23f)
        )
        Assert.assertNotNull(
            testObject.getRatingPorcent(-23f)
        )
    }
}