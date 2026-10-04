package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.QuizQuestions
import com.example.model.AchievementLevel
import com.example.model.Topic
import com.example.viewmodel.QuizViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Intent & Toast", appName)
    }

    @Test
    fun `verify exactly 20 questions exist`() {
        val questions = QuizQuestions.questions
        assertEquals(20, questions.size)
    }

    @Test
    fun `verify all required topics are covered`() {
        val questions = QuizQuestions.questions
        val topics = questions.map { it.topic }.toSet()

        assertTrue(topics.contains(Topic.XML_UI))
        assertTrue(topics.contains(Topic.TOAST))
        assertTrue(topics.contains(Topic.EXPLICIT_INTENT))
        assertTrue(topics.contains(Topic.DATA_TRANSFER))
        assertTrue(topics.contains(Topic.IMPLICIT_INTENT))
        assertTrue(topics.contains(Topic.DEBUGGING))
    }

    @Test
    fun `verify all questions have 4 options and valid correct index`() {
        QuizQuestions.questions.forEach { q ->
            assertEquals("Soalan #${q.id} mesti mempunyai 4 pilihan", 4, q.options.size)
            assertTrue("Index jawapan betul mesti antara 0 dan 3", q.correctOptionIndex in 0..3)
            assertNotNull(q.explanationCorrect)
            assertNotNull(q.explanationIncorrect)
        }
    }

    @Test
    fun `verify achievement level calculation`() {
        assertEquals(AchievementLevel.CEMERLANG, AchievementLevel.fromPercentage(80))
        assertEquals(AchievementLevel.CEMERLANG, AchievementLevel.fromPercentage(100))
        assertEquals(AchievementLevel.BAIK, AchievementLevel.fromPercentage(75))
        assertEquals(AchievementLevel.PERLU_LATIHAN, AchievementLevel.fromPercentage(50))
        assertEquals(AchievementLevel.ULANG_KAJI_SEMULA, AchievementLevel.fromPercentage(35))
    }
}
