package io.github.gufeczek.crochet.design.system

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    modifier: Modifier = Modifier.size(50.dp),
    shape: Shape = CircleShape,
    border: BorderStroke = BorderStroke(1.dp, Color.Blue),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue),
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        shape = shape,
        border = border,
        contentPadding = contentPadding,
        colors = colors,
        onClick = onClick
    ) { content() }
}