package com.qabas.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.qabas.core.CompassManager
import com.qabas.ui.theme.*

@Composable
fun CompassScreen(navController: NavController) {
    val state by CompassManager.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SpaceDark)
    ) {
        // 1. البوصلة الذهبية المركزية
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(160.dp)
                .clip(CircleShape)
                .background(CompassGold.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(CompassGold),
                contentAlignment = Alignment.Center
            ) {
                // استخدم الأيقونة أو الصورة (ضع الصورة هنا لاحقاً)
                Icon(
                    imageVector = Icons.Default.Explore,
                    contentDescription = "البوصلة",
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        // 2. الدوائر الخمس المحيطة (الوحدات)
        val unitColorMap = mapOf(
            "المحراب" to MihrabGreen,
            "الرفيق" to CompanionPurple,
            "طلب العلم" to KnowledgeTeal,
            "الاستوديو" to StudioBlue,
            "الأثر" to ImpactOlive
        )

        val unitIconsMap = mapOf(
            "المحراب" to Icons.Default.Mosque,
            "الرفيق" to Icons.Default.Face,
            "طلب العلم" to Icons.Default.MenuBook,
            "الاستوديو" to Icons.Default.VideoLibrary,
            "الأثر" to Icons.Default.EmojiNature
        )

        // (لتبسيط العرض على الهاتف سنضعها بشكل عمودي أو دائري بسيط، الصورة المثالية تتطلب تخطيطاً دائرياً)
        // نضعها في أسفل الشاشة بشكل دائري بسيط بدلاً من توزيعها في كل الزوايا لأن الهاتف لا يدعم الرسم الدقيق
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                UnitCircle("المحراب", unitColorMap["المحراب"]!!, unitIconsMap["المحراب"]!!, navController)
                UnitCircle("الرفيق", unitColorMap["الرفيق"]!!, unitIconsMap["الرفيق"]!!, navController)
                UnitCircle("طلب العلم", unitColorMap["طلب العلم"]!!, unitIconsMap["طلب العلم"]!!, navController)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                UnitCircle("الاستوديو", unitColorMap["الاستوديو"]!!, unitIconsMap["الاستوديو"]!!, navController)
                UnitCircle("الأثر", unitColorMap["الأثر"]!!, unitIconsMap["الأثر"]!!, navController)
            }
        }

        // 3. نص التوجيه (مرحلة التنظيم)
        Text(
            text = state.stageDescription,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )
    }
}

@Composable
fun UnitCircle(name: String, color: Color, icon: androidx.compose.ui.graphics.vector.ImageVector, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { 
            // توجيه المستخدم للوحدة المحددة (سنفعلها لاحقاً عند بناء الوحدات)
            // navController.navigate(name) 
        }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = name,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Text(
            text = name,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
