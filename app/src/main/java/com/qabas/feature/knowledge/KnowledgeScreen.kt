package com.qabas.feature.knowledge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KnowledgeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000F1E))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "📚 طلب العلم", color = Color(0xFF3EA5AA), fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "الدروس متاحة محلياً", color = Color.White)
    }
}
