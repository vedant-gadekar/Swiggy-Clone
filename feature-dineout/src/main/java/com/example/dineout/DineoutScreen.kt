package com.example.dineout


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dineout.utils.Strings
import com.example.dineout.utils.Colors
import com.example.dineout.utils.Dimensions
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Dineout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(color = Colors.RED)
    ) {
        Text(
            text = Strings.DINEOUT_TITLE,
            fontWeight = FontWeight.Bold,
            color = Colors.WHITE,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = Dimensions.TITLE_FONT_SIZE
        )
    }
}