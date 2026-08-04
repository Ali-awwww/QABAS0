package com.qabas.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.qabas.feature.knowledge.KnowledgeScreen
import com.qabas.feature.mihrab.MihrabScreen
import com.qabas.ui.theme.CompassGold

@Composable
fun QabasApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "compass"

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF000A14).copy(alpha = 0.8f),
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Mosque, contentDescription = "المحراب") },
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
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "طلب العلم") },
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
                // FAB الذهبي في المنتصف
                NavigationBarItem(
                    icon = { Icon(Icons.Default.EmojiNature, contentDescription = "الأثر") },
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
            FloatingActionButton(
                onClick = {
                    CompassManager.updateState("مرحلة التوجيه", "compass")
                    navController.navigate("compass")
                },
                containerColor = CompassGold,
                contentColor = Color.Black
            ) {
                Icon(Icons.Default.Explore, contentDescription = "البوصلة")
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
            composable("compass") { CompassScreen(navController) }
            composable("mihrab") { MihrabScreen() }
            composable("knowledge") { KnowledgeScreen() }
            composable("impact") { 
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text(text = "وحدة الأثر (قيد الإنشاء)", color = Color.White)
                }
            }
        }
    }
}
