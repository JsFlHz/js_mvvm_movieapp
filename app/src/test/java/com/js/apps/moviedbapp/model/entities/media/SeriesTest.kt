package com.js.apps.moviedbapp.model.entities.media

import com.google.gson.annotations.SerializedName
import org.junit.Assert
import org.junit.Test


class SeriesTest {
    private lateinit var serie:Serie
    @Test
    fun calculateImage() {
        serie = Serie(
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
            serie.getRatingPorcent(0.0f)
        )
        Assert.assertEquals(
            40,
            serie.getRatingPorcent(4.0f)
        )
        Assert.assertEquals(
            0,
            serie.getRatingPorcent(-23f)
        )
        Assert.assertNotNull(
            serie.getRatingPorcent(-23f)
        )
    }
}