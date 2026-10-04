package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CodeLanguage

object CodeThemeColors {
    val Background = Color(0xFF0F172A) // Slate 900
    val HeaderBg = Color(0xFF1E293B) // Slate 800
    val Border = Color(0xFF334155) // Slate 700
    val LineNumber = Color(0xFF64748B) // Slate 500
    val Keyword = Color(0xFF60A5FA) // Blue 400
    val TypeName = Color(0xFFA78BFA) // Purple 400
    val StringColor = Color(0xFF34D399) // Emerald 400
    val CommentColor = Color(0xFF94A3B8) // Slate 400
    val XmlTag = Color(0xFFF472B6) // Pink 400
    val XmlAttr = Color(0xFFFBBF24) // Amber 400
    val MissingBadgeBg = Color(0xFF78350F) // Amber 900
    val MissingBadgeText = Color(0xFFFDE68A) // Amber 200
    val MissingBadgeBorder = Color(0xFFF59E0B) // Amber 500
    val DefaultCodeText = Color(0xFFE2E8F0) // Slate 200
}

@Composable
fun CodeSnippetView(
    code: String,
    language: CodeLanguage,
    modifier: Modifier = Modifier,
    showLineNumbers: Boolean = true,
    title: String? = null
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, CodeThemeColors.Border, RoundedCornerShape(12.dp)),
        color = CodeThemeColors.Background
    ) {
        Column {
            // Editor Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CodeThemeColors.HeaderBg)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 3 IDE dots
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(10.dp)
                            .height(10.dp)
                            .background(Color(0xFFEF4444), RoundedCornerShape(5.dp))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .width(10.dp)
                            .height(10.dp)
                            .background(Color(0xFFF59E0B), RoundedCornerShape(5.dp))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .width(10.dp)
                            .height(10.dp)
                            .background(Color(0xFF10B981), RoundedCornerShape(5.dp))
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    imageVector = Icons.Default.Code,
                    contentDescription = null,
                    tint = CodeThemeColors.Keyword,
                    modifier = Modifier.width(16.dp).height(16.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = title ?: if (language == CodeLanguage.JAVA) "Java Android Studio" else "XML Layout",
                    color = Color(0xFFCBD5E1),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Monospace
                )
            }

            // Code Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .horizontalScroll(scrollState)
            ) {
                val lines = code.trimIndent().split("\n")
                Row(modifier = Modifier.padding(horizontal = 14.dp)) {
                    if (showLineNumbers) {
                        Column(horizontalAlignment = Alignment.End) {
                            lines.indices.forEach { index ->
                                Text(
                                    text = "${index + 1}",
                                    color = CodeThemeColors.LineNumber,
                                    fontSize = 13.sp,
                                    fontFamily = FontFamily.Monospace,
                                    lineHeight = 22.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height((lines.size * 22).dp)
                                .background(CodeThemeColors.Border)
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                    }

                    Column {
                        lines.forEach { line ->
                            val annotated = highlightCodeLine(line, language)
                            Text(
                                text = annotated,
                                fontSize = 13.sp,
                                fontFamily = FontFamily.Monospace,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InlineCodeSnippet(
    code: String,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean = false
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                1.dp,
                if (isHighlighted) CodeThemeColors.MissingBadgeBorder else CodeThemeColors.Border,
                RoundedCornerShape(8.dp)
            ),
        color = if (isHighlighted) Color(0xFF1E293B) else CodeThemeColors.Background
    ) {
        val annotated = highlightCodeLine(code, CodeLanguage.JAVA)
        Text(
            text = annotated,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace,
            lineHeight = 18.sp
        )
    }
}

private fun highlightCodeLine(line: String, language: CodeLanguage): AnnotatedString {
    return buildAnnotatedString {
        val trimmed = line.trimStart()

        // Missing code highlight
        if (line.contains("[ KOD ? ]") || line.contains("[ KOD YANG TERTINGGAL ]") || line.contains("[ MASALAH BERLAKU DI SINI ]")) {
            val token = when {
                line.contains("[ KOD ? ]") -> "[ KOD ? ]"
                line.contains("[ KOD YANG TERTINGGAL ]") -> "[ KOD YANG TERTINGGAL ]"
                else -> "[ MASALAH BERLAKU DI SINI ]"
            }
            val parts = line.split(token)
            if (parts.isNotEmpty()) {
                appendCodeSpan(parts[0], language)
            }
            pushStyle(
                SpanStyle(
                    color = CodeThemeColors.MissingBadgeText,
                    background = CodeThemeColors.MissingBadgeBg,
                    fontWeight = FontWeight.Bold
                )
            )
            append(" $token ")
            pop()
            if (parts.size > 1) {
                appendCodeSpan(parts[1], language)
            }
            return@buildAnnotatedString
        }

        // Full line comment
        if (trimmed.startsWith("//") || trimmed.startsWith("<!--")) {
            pushStyle(SpanStyle(color = CodeThemeColors.CommentColor, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic))
            append(line)
            pop()
            return@buildAnnotatedString
        }

        appendCodeSpan(line, language)
    }
}

private fun AnnotatedString.Builder.appendCodeSpan(text: String, language: CodeLanguage) {
    if (language == CodeLanguage.XML) {
        appendXmlHighlight(text)
    } else {
        appendJavaHighlight(text)
    }
}

private fun AnnotatedString.Builder.appendJavaHighlight(text: String) {
    val keywords = setOf(
        "public", "private", "protected", "class", "interface", "extends", "implements",
        "new", "return", "if", "else", "switch", "case", "default", "break", "continue",
        "int", "void", "boolean", "float", "double", "char", "long", "null", "true", "false",
        "import", "package", "super", "this", "final", "static", "try", "catch"
    )

    val types = setOf(
        "String", "Intent", "Toast", "Button", "EditText", "TextView", "View", "Bundle",
        "Uri", "Context", "Activity", "MainActivity", "SecondActivity", "ProfileActivity",
        "DetailActivity", "ResultActivity", "FirstActivity", "OnClickListener", "CharSequence",
        "URL", "Editable"
    )

    val regex = "(\"[^\"]*\"|//.*|@[A-Za-z]+|[A-Za-z_][A-Za-z0-9_]*|[^A-Za-z0-9_\"\\s]+|\\s+)".toRegex()
    val matches = regex.findAll(text)

    for (match in matches) {
        val word = match.value
        when {
            word.startsWith("\"") -> {
                pushStyle(SpanStyle(color = CodeThemeColors.StringColor))
                append(word)
                pop()
            }
            word.startsWith("//") -> {
                pushStyle(SpanStyle(color = CodeThemeColors.CommentColor))
                append(word)
                pop()
            }
            word.startsWith("@") -> {
                pushStyle(SpanStyle(color = CodeThemeColors.XmlAttr, fontWeight = FontWeight.Bold))
                append(word)
                pop()
            }
            word in keywords -> {
                pushStyle(SpanStyle(color = CodeThemeColors.Keyword, fontWeight = FontWeight.SemiBold))
                append(word)
                pop()
            }
            word in types -> {
                pushStyle(SpanStyle(color = CodeThemeColors.TypeName, fontWeight = FontWeight.Medium))
                append(word)
                pop()
            }
            word.startsWith("ACTION_") || word.startsWith("LENGTH_") -> {
                pushStyle(SpanStyle(color = Color(0xFFF97316), fontWeight = FontWeight.Bold))
                append(word)
                pop()
            }
            word == "putExtra" || word == "startActivity" || word == "getStringExtra" ||
            word == "getText" || word == "toString" || word == "findViewById" ||
            word == "setOnClickListener" || word == "makeText" || word == "show" ||
            word == "parse" -> {
                pushStyle(SpanStyle(color = Color(0xFF38BDF8))) // Cyan
                append(word)
                pop()
            }
            else -> {
                pushStyle(SpanStyle(color = CodeThemeColors.DefaultCodeText))
                append(word)
                pop()
            }
        }
    }
}

private fun AnnotatedString.Builder.appendXmlHighlight(text: String) {
    val regex = "(\"[^\"]*\"|<!--.*?-->|</?[A-Za-z0-9_:]+|[A-Za-z0-9_:]+=|[^\"<>=/\\s]+|\\s+)".toRegex()
    val matches = regex.findAll(text)

    for (match in matches) {
        val word = match.value
        when {
            word.startsWith("\"") -> {
                pushStyle(SpanStyle(color = CodeThemeColors.StringColor))
                append(word)
                pop()
            }
            word.startsWith("<!--") -> {
                pushStyle(SpanStyle(color = CodeThemeColors.CommentColor))
                append(word)
                pop()
            }
            word.startsWith("<") || word.startsWith("</") -> {
                pushStyle(SpanStyle(color = CodeThemeColors.XmlTag, fontWeight = FontWeight.Bold))
                append(word)
                pop()
            }
            word.endsWith("=") -> {
                pushStyle(SpanStyle(color = CodeThemeColors.XmlAttr))
                append(word)
                pop()
            }
            else -> {
                pushStyle(SpanStyle(color = CodeThemeColors.DefaultCodeText))
                append(word)
                pop()
            }
        }
    }
}
