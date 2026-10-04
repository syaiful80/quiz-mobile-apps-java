package com.example.model

enum class Topic(val displayName: String, val shortName: String, val description: String) {
    XML_UI("XML & UI Layout", "XML / UI", "Button, EditText, android:id & findViewById()"),
    TOAST("Toast Notification", "Toast", "Toast.makeText(), Context, LENGTH & .show()"),
    EXPLICIT_INTENT("Explicit Intent", "Explicit Intent", "Navigasi antara skrin spesifik & startActivity()"),
    DATA_TRANSFER("Penghantaran Data", "Data Transfer", "putExtra(), getIntent() & getStringExtra()"),
    IMPLICIT_INTENT("Implicit Intent", "Implicit Intent", "ACTION_VIEW, ACTION_DIAL & Uri.parse()"),
    DEBUGGING("Cabaran & Debugging", "Debugging", "Analisis ralat, kunci extra tidak sepadan & missing .show()")
}

enum class CodeLanguage {
    JAVA,
    XML
}

data class QuizOption(
    val letter: String, // "A", "B", "C", "D"
    val codeOrText: String,
    val isCode: Boolean = true
)

data class Question(
    val id: Int,
    val topic: Topic,
    val questionText: String,
    val codeSnippet: String,
    val language: CodeLanguage,
    val options: List<QuizOption>,
    val correctOptionIndex: Int, // 0 for A, 1 for B, 2 for C, 3 for D
    val explanationCorrect: String,
    val explanationIncorrect: String,
    val hint: String? = null
)

enum class AchievementLevel(
    val title: String,
    val description: String,
    val minPercentage: Int,
    val colorHex: Long
) {
    CEMERLANG(
        title = "CEMERLANG",
        description = "Anda telah menguasai asas Intent dan Toast.",
        minPercentage = 80,
        colorHex = 0xFF10B981 // Emerald Green
    ),
    BAIK(
        title = "BAIK",
        description = "Penguasaan baik tetapi beberapa bahagian perlu diulang kaji.",
        minPercentage = 60,
        colorHex = 0xFF3B82F6 // Blue
    ),
    PERLU_LATIHAN(
        title = "PERLU LATIHAN",
        description = "Ulang kaji beberapa konsep sebelum penilaian.",
        minPercentage = 40,
        colorHex = 0xFFF59E0B // Amber
    ),
    ULANG_KAJI_SEMULA(
        title = "ULANG KAJI SEMULA",
        description = "Sila ulang kaji topik Intent dan Toast sebelum mencuba semula.",
        minPercentage = 0,
        colorHex = 0xFFEF4444 // Red
    );

    companion object {
        fun fromPercentage(percentage: Int): AchievementLevel {
            return when {
                percentage >= 80 -> CEMERLANG
                percentage >= 60 -> BAIK
                percentage >= 40 -> PERLU_LATIHAN
                else -> ULANG_KAJI_SEMULA
            }
        }
    }
}

data class TopicScore(
    val topic: Topic,
    val correctCount: Int,
    val totalCount: Int
) {
    val percentage: Int
        get() = if (totalCount > 0) ((correctCount.toFloat() / totalCount) * 100).toInt() else 0
}

data class QuizResult(
    val studentName: String,
    val studentMatric: String,
    val score: Int,
    val totalQuestions: Int,
    val percentage: Int,
    val correctAnswersCount: Int,
    val incorrectAnswersCount: Int,
    val achievementLevel: AchievementLevel,
    val topicScores: List<TopicScore>,
    val weakestTopic: Topic?
)

enum class AppScreen {
    STUDENT_INFO,
    QUIZ,
    RESULT,
    REVIEW
}
