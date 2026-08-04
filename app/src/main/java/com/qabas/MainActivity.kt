package com.qabas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QabasApp()
        }
    }
}

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
                    onClick = { navController.navigate("mihrab") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "طلب العلم") },
                    label = { Text("طلب العلم") },
                    selected = currentRoute == "knowledge",
                    onClick = { navController.navigate("knowledge") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.EmojiNature, contentDescription = "الأثر") },
                    label = { Text("الأثر") },
                    selected = currentRoute == "impact",
                    onClick = { navController.navigate("impact") }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("compass") },
                containerColor = Color(0xFFECC156),
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
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            composable("compass") { 
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "✨ البوصلة الذهبية ✨", color = Color(0xFFECC156))
                }
            }
            composable("mihrab") { 
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "🕌 المحراب", color = Color(0xFF52865E))
                }
            }
            composable("knowledge") { 
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "📚 طلب العلم", color = Color(0xFF3EA5AA))
                }
            }
            composable("impact") { 
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "🌱 الأثر", color = Color(0xFFBC9D4B))
                }
            }
        }
    }
}
