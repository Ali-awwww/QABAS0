package com.qabas.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qabas.core.CompassManager
import com.qabas.ui.theme.CompassGold

@Composable
fun QabasApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "mihrab"

    Scaffold(
        bottomBar = {
            // شريط التنقل السفلي
            NavigationBar(
                containerColor = Color(0xFF000A14).copy(alpha = 0.8f),
                contentColor = Color.White
            ) {
                // التبويبات الأربعة (المحراب، العلم، الاستوديو، الأثر)
                // سنستخدم أسماء بسيطة الآن، ويمكنك تعديل الأيقونات لاحقاً
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "المحراب") },
                    label = { Text("المحراب") },
                    selected = currentRoute == "mihrab",
                    onClick = {
                        navController.navigate("mihrab") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "طلب العلم") },
                    label = { Text("طلب العلم") },
                    selected = currentRoute == "knowledge",
                    onClick = {
                        navController.navigate("knowledge") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                // FAB الذهبي في المنتصف (البوصلة)
                // NavigationBarItem يبقى فارغاً في المنتصف
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "الأثر") },
                    label = { Text("الأثر") },
                    selected = currentRoute == "impact",
                    onClick = {
                        navController.navigate("impact") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            // زر البوصلة الذهبي (FAB) في المنتصف
            FloatingActionButton(
                onClick = {
                    CompassManager.updateState("مرحلة التوجيه", "compass")
                    navController.navigate("compass")
                },
                containerColor = CompassGold,
                contentColor = Color.Black
            ) {
                Icon(Icons.Default.Home, contentDescription = "البوصلة")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "compass",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("compass") {
                Box(modifier = Modifier.fillMaxSize()) {
                    // هنا سنضع شاشة البوصلة في الخطوة القادمة
                    Text("هذه شاشة البوصلة الذهبية", color = Color.White)
                }
            }
            composable("mihrab") { Text("المحراب", color = Color.White) }
            composable("knowledge") { Text("طلب العلم", color = Color.White) }
            composable("impact") { Text("الأثر", color = Color.White) }
        }
    }
}
