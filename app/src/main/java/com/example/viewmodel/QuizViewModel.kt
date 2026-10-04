package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.QuizQuestions
import com.example.model.AchievementLevel
import com.example.model.AppScreen
import com.example.model.Question
import com.example.model.QuizResult
import com.example.model.Topic
import com.example.model.TopicScore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class QuizUiState(
    val currentScreen: AppScreen = AppScreen.STUDENT_INFO,
    val studentName: String = "",
    val studentMatric: String = "",
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerChecked: Boolean = false,
    val userAnswers: Map<Int, Int> = emptyMap(), // questionId -> chosenOptionIndex (0..3)
    val quizResult: QuizResult? = null
) {
    val currentQuestion: Question
        get() = QuizQuestions.questions.getOrElse(currentQuestionIndex) { QuizQuestions.questions[0] }

    val totalQuestions: Int
        get() = QuizQuestions.questions.size

    val isStudentInfoValid: Boolean
        get() = studentName.trim().isNotBlank() && studentMatric.trim().isNotBlank()

    val isLastQuestion: Boolean
        get() = currentQuestionIndex == QuizQuestions.questions.size - 1

    val isCurrentAnswerCorrect: Boolean
        get() = selectedOptionIndex == currentQuestion.correctOptionIndex
}

class QuizViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun updateStudentName(name: String) {
        _uiState.update { it.copy(studentName = name) }
    }

    fun updateStudentMatric(matric: String) {
        _uiState.update { it.copy(studentMatric = matric.uppercase()) }
    }

    fun startQuiz() {
        if (!_uiState.value.isStudentInfoValid) return
        _uiState.update {
            it.copy(
                currentScreen = AppScreen.QUIZ,
                currentQuestionIndex = 0,
                selectedOptionIndex = null,
                isAnswerChecked = false,
                userAnswers = emptyMap(),
                quizResult = null
            )
        }
    }

    fun selectOption(index: Int) {
        // Do not allow changing once checked
        if (_uiState.value.isAnswerChecked) return
        _uiState.update { it.copy(selectedOptionIndex = index) }
    }

    fun checkAnswer() {
        val state = _uiState.value
        val selected = state.selectedOptionIndex ?: return
        if (state.isAnswerChecked) return

        val currentQ = state.currentQuestion
        val updatedAnswers = state.userAnswers + (currentQ.id to selected)

        _uiState.update {
            it.copy(
                isAnswerChecked = true,
                userAnswers = updatedAnswers
            )
        }
    }

    fun nextQuestion() {
        val state = _uiState.value
        if (!state.isAnswerChecked) return

        if (state.isLastQuestion) {
            // Calculate final result
            val result = calculateResult(state.userAnswers, state.studentName, state.studentMatric)
            _uiState.update {
                it.copy(
                    quizResult = result,
                    currentScreen = AppScreen.RESULT
                )
            }
        } else {
            val nextIndex = state.currentQuestionIndex + 1
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedOptionIndex = null,
                    isAnswerChecked = false
                )
            }
        }
    }

    fun openReviewScreen() {
        _uiState.update { it.copy(currentScreen = AppScreen.REVIEW) }
    }

    fun backToResult() {
        _uiState.update { it.copy(currentScreen = AppScreen.RESULT) }
    }

    fun retakeQuiz() {
        _uiState.update {
            it.copy(
                currentScreen = AppScreen.QUIZ,
                currentQuestionIndex = 0,
                selectedOptionIndex = null,
                isAnswerChecked = false,
                userAnswers = emptyMap(),
                quizResult = null
            )
        }
    }

    fun resetToStudentInfo() {
        _uiState.update {
            it.copy(
                currentScreen = AppScreen.STUDENT_INFO,
                currentQuestionIndex = 0,
                selectedOptionIndex = null,
                isAnswerChecked = false,
                userAnswers = emptyMap(),
                quizResult = null
            )
        }
    }

    fun handleBackPress(): Boolean {
        // Return true if handled internally
        val state = _uiState.value
        return when (state.currentScreen) {
            AppScreen.REVIEW -> {
                backToResult()
                true
            }
            AppScreen.RESULT -> {
                resetToStudentInfo()
                true
            }
            AppScreen.QUIZ -> {
                // Return to student info or confirm
                resetToStudentInfo()
                true
            }
            AppScreen.STUDENT_INFO -> false
        }
    }

    private fun calculateResult(
        answers: Map<Int, Int>,
        name: String,
        matric: String
    ): QuizResult {
        val questions = QuizQuestions.questions
        var correctCount = 0

        val topicMap = Topic.values().associateWith { topic ->
            val topicQuestions = questions.filter { it.topic == topic }
            val topicCorrect = topicQuestions.count { q ->
                answers[q.id] == q.correctOptionIndex
            }
            TopicScore(
                topic = topic,
                correctCount = topicCorrect,
                totalCount = topicQuestions.size
            )
        }

        questions.forEach { q ->
            if (answers[q.id] == q.correctOptionIndex) {
                correctCount++
            }
        }

        val total = questions.size
        val percentage = ((correctCount.toFloat() / total) * 100).toInt()
        val incorrectCount = total - correctCount
        val level = AchievementLevel.fromPercentage(percentage)
        val topicScores = topicMap.values.toList()

        // Weakest topic: sort by percentage ascending, then pick first
        // If all 100%, weakestTopic can still be null or identified
        val weakest = if (percentage == 100) null else {
            topicScores.minByOrNull { it.percentage }?.topic
        }

        return QuizResult(
            studentName = name.trim(),
            studentMatric = matric.trim(),
            score = correctCount,
            totalQuestions = total,
            percentage = percentage,
            correctAnswersCount = correctCount,
            incorrectAnswersCount = incorrectCount,
            achievementLevel = level,
            topicScores = topicScores,
            weakestTopic = weakest
        )
    }
}
