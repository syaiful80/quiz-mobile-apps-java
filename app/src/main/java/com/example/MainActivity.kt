package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.AppScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ResultScreen
import com.example.ui.screens.ReviewScreen
import com.example.ui.screens.StudentInfoScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppContent()
            }
        }
    }
}

@Composable
fun MainAppContent(quizViewModel: QuizViewModel = viewModel()) {
    val uiState by quizViewModel.uiState.collectAsState()
    var showExitQuizDialog by remember { mutableStateOf(false) }

    // Custom back navigation handling
    when (uiState.currentScreen) {
        AppScreen.STUDENT_INFO -> {
            // Default activity back behavior
        }
        AppScreen.QUIZ -> {
            BackHandler {
                showExitQuizDialog = true
            }
        }
        AppScreen.RESULT -> {
            BackHandler {
                quizViewModel.resetToStudentInfo()
            }
        }
        AppScreen.REVIEW -> {
            BackHandler {
                quizViewModel.backToResult()
            }
        }
    }

    if (showExitQuizDialog) {
        AlertDialog(
            onDismissRequest = { showExitQuizDialog = false },
            title = {
                Text(
                    text = "Keluar dari Kuiz?",
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = "Adakah anda pasti ingin kembali ke skrin maklumat pelajar? Progres kuiz semasa anda akan diset semula.",
                    color = Color(0xFFCBD5E1)
                )
            },
            containerColor = Color(0xFF1E293B),
            confirmButton = {
                TextButton(
                    onClick = {
                        showExitQuizDialog = false
                        quizViewModel.resetToStudentInfo()
                    }
                ) {
                    Text("Ya, Keluar", color = Color(0xFFEF4444))
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitQuizDialog = false }) {
                    Text("Teruskan Kuiz", color = Color(0xFF60A5FA))
                }
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF0F172A)
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        when (uiState.currentScreen) {
            AppScreen.STUDENT_INFO -> {
                StudentInfoScreen(
                    studentName = uiState.studentName,
                    studentMatric = uiState.studentMatric,
                    isValid = uiState.isStudentInfoValid,
                    onNameChange = { quizViewModel.updateStudentName(it) },
                    onMatricChange = { quizViewModel.updateStudentMatric(it) },
                    onStartQuiz = { quizViewModel.startQuiz() },
                    modifier = modifier
                )
            }

            AppScreen.QUIZ -> {
                QuizScreen(
                    question = uiState.currentQuestion,
                    questionIndex = uiState.currentQuestionIndex,
                    totalQuestions = uiState.totalQuestions,
                    selectedOptionIndex = uiState.selectedOptionIndex,
                    isAnswerChecked = uiState.isAnswerChecked,
                    studentName = uiState.studentName,
                    studentMatric = uiState.studentMatric,
                    onSelectOption = { quizViewModel.selectOption(it) },
                    onCheckAnswer = { quizViewModel.checkAnswer() },
                    onNextQuestion = { quizViewModel.nextQuestion() },
                    onBack = { showExitQuizDialog = true },
                    modifier = modifier
                )
            }

            AppScreen.RESULT -> {
                uiState.quizResult?.let { result ->
                    ResultScreen(
                        result = result,
                        onReviewAnswers = { quizViewModel.openReviewScreen() },
                        onRetakeQuiz = { quizViewModel.retakeQuiz() },
                        onResetToHome = { quizViewModel.resetToStudentInfo() },
                        modifier = modifier
                    )
                }
            }

            AppScreen.REVIEW -> {
                ReviewScreen(
                    userAnswers = uiState.userAnswers,
                    onBack = { quizViewModel.backToResult() },
                    modifier = modifier
                )
            }
        }
    }
}
