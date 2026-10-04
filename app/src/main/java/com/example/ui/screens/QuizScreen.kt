package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Question
import com.example.model.QuizOption
import com.example.ui.components.CodeSnippetView

@Composable
fun QuizScreen(
    question: Question,
    questionIndex: Int,
    totalQuestions: Int,
    selectedOptionIndex: Int?,
    isAnswerChecked: Boolean,
    studentName: String,
    studentMatric: String,
    onSelectOption: (Int) -> Unit,
    onCheckAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val progress = (questionIndex + 1).toFloat() / totalQuestions.toFloat()
    val isCorrect = isAnswerChecked && selectedOptionIndex == question.correctOptionIndex
    val isLast = questionIndex == totalQuestions - 1

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0B132B),
                        Color(0xFF0F172A),
                        Color(0xFF1E1B4B)
                    )
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF1E293B).copy(alpha = 0.95f),
                shadowElevation = 6.dp
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = onBack,
                                modifier = Modifier
                                    .size(36.dp)
                                    .testTag("quiz_back_button")
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Kembali ke Skrin Utama",
                                    tint = Color(0xFF94A3B8)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(
                                    text = "Soalan ${questionIndex + 1} / $totalQuestions",
                                    color = Color.White,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                    modifier = Modifier.testTag("question_counter")
                                )
                                Text(
                                    text = question.topic.displayName,
                                    color = Color(0xFF38BDF8),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        // Student Mini Badge
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF0F172A),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Color(0xFF60A5FA),
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = studentName.take(14) + if (studentName.length > 14) "…" else "",
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = studentMatric,
                                        color = Color(0xFF94A3B8),
                                        fontSize = 9.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Progress Bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .weight(1f)
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = Color(0xFF3B82F6),
                            trackColor = Color(0xFF334155),
                            strokeCap = StrokeCap.Round
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "${(progress * 100).toInt()}%",
                            color = Color(0xFF94A3B8),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                // Topic tag
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF1E293B),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF3B82F6).copy(alpha = 0.5f))
                ) {
                    Text(
                        text = "TOPIK: ${question.topic.displayName.uppercase()} (${question.topic.shortName})",
                        color = Color(0xFF93C5FD),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Question Prompt
                Text(
                    text = question.questionText,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 22.sp,
                    modifier = Modifier.testTag("question_text")
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Code Snippet with Syntax Highlighting
                CodeSnippetView(
                    code = question.codeSnippet,
                    language = question.language,
                    modifier = Modifier.testTag("code_snippet_view")
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Pilih satu jawapan yang betul:",
                    color = Color(0xFFCBD5E1),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 4 Choices: A, B, C, D
                question.options.forEachIndexed { index, option ->
                    val isSelected = selectedOptionIndex == index
                    val isThisOptionCorrect = index == question.correctOptionIndex

                    OptionCard(
                        option = option,
                        isSelected = isSelected,
                        isAnswerChecked = isAnswerChecked,
                        isCorrect = isThisOptionCorrect,
                        onClick = {
                            if (!isAnswerChecked) {
                                onSelectOption(index)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Answer Selection & Feedback Area
                if (!isAnswerChecked) {
                    if (selectedOptionIndex != null) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF1E293B),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF3B82F6)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = Color(0xFF38BDF8),
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Jawapan anda telah dipilih.",
                                    color = Color(0xFFE2E8F0),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.testTag("answer_selected_label")
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Button: SEMAK JAWAPAN
                        Button(
                            onClick = onCheckAnswer,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("check_answer_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2563EB),
                                contentColor = Color.White
                            ),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                text = "SEMAK JAWAPAN",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                    } else {
                        Text(
                            text = "Sila tekan pada pilihan A, B, C atau D di atas.",
                            color = Color(0xFF94A3B8),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                } else {
                    // Answer Checked: Show Feedback Banner & Next Button
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInVertically()
                    ) {
                        Column {
                            FeedbackCard(
                                isCorrect = isCorrect,
                                explanation = if (isCorrect) question.explanationCorrect else question.explanationIncorrect
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Button: SOALAN SETERUSNYA / LIHAT KEPUTUSAN
                            Button(
                                onClick = onNextQuestion,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .testTag("next_question_button"),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isLast) Color(0xFF10B981) else Color(0xFF2563EB),
                                    contentColor = Color.White
                                ),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = if (isLast) "LIHAT KEPUTUSAN" else "SOALAN SETERUSNYA",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.sp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(
                                        imageVector = if (isLast) Icons.Default.EmojiEvents else Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

@Composable
private fun OptionCard(
    option: QuizOption,
    isSelected: Boolean,
    isAnswerChecked: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit
) {
    val (borderColor, containerColor, badgeColor) = when {
        isAnswerChecked && isCorrect -> Triple(
            Color(0xFF10B981), // Green
            Color(0xFF064E3B).copy(alpha = 0.6f),
            Color(0xFF10B981)
        )
        isAnswerChecked && isSelected && !isCorrect -> Triple(
            Color(0xFFEF4444), // Red
            Color(0xFF7F1D1D).copy(alpha = 0.6f),
            Color(0xFFEF4444)
        )
        isSelected -> Triple(
            Color(0xFF3B82F6), // Blue
            Color(0xFF1E3A8A).copy(alpha = 0.5f),
            Color(0xFF3B82F6)
        )
        else -> Triple(
            Color(0xFF334155),
            Color(0xFF1E293B).copy(alpha = 0.7f),
            Color(0xFF475569)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isAnswerChecked, onClick = onClick)
            .testTag("option_${option.letter}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected || (isAnswerChecked && isCorrect)) 2.dp else 1.dp,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Letter Badge (A, B, C, D)
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(badgeColor),
                contentAlignment = Alignment.Center
            ) {
                if (isAnswerChecked && isCorrect) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Betul",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                } else if (isAnswerChecked && isSelected && !isCorrect) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Salah",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                } else {
                    Text(
                        text = option.letter,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Option Content
            Text(
                text = option.codeOrText,
                color = Color.White,
                fontSize = if (option.isCode) 13.sp else 14.sp,
                fontFamily = if (option.isCode) FontFamily.Monospace else FontFamily.Default,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                lineHeight = 20.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun FeedbackCard(
    isCorrect: Boolean,
    explanation: String
) {
    val containerColor = if (isCorrect) Color(0xFF064E3B).copy(alpha = 0.9f) else Color(0xFF7F1D1D).copy(alpha = 0.9f)
    val borderColor = if (isCorrect) Color(0xFF10B981) else Color(0xFFEF4444)
    val titleColor = if (isCorrect) Color(0xFF34D399) else Color(0xFFFCA5A5)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("feedback_card"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = androidx.compose.foundation.BorderStroke(2.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Close,
                    contentDescription = null,
                    tint = titleColor,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isCorrect) "✓ Betul!" else "✗ Belum tepat.",
                    color = titleColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.testTag("feedback_title")
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = explanation,
                color = Color(0xFFF1F5F9),
                fontSize = 13.sp,
                lineHeight = 19.sp,
                modifier = Modifier.testTag("feedback_explanation")
            )
        }
    }
}
