package demo.at.ram.presentation.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import demo.at.ram.shared.R

@Composable
fun AboutScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.builder),
            contentDescription = "Builder"
        )
    }
}