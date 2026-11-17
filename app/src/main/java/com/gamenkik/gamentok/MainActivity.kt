package com.gamenkik.gamentok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Neon = Color(0xFFB026FF)
private val Bg = Color(0xFF090611)
private val Panel = Color(0xFF191125)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamenTokApp()
        }
    }
}

data class NavItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun GamenTokApp() {
    var page by remember { mutableIntStateOf(0) }

    val items = listOf(
        NavItem("Лента", Icons.Default.Home),
        NavItem("Поиск", Icons.Default.Search),
        NavItem("Создать", Icons.Default.Add),
        NavItem("Профиль", Icons.Default.Person),
        NavItem("Настройки", Icons.Default.Settings)
    )

    MaterialTheme(
        colorScheme = darkColorScheme(primary = Neon)
    ) {
        Scaffold(
            containerColor = Bg,
            bottomBar = {
                NavigationBar(containerColor = Panel) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = page == index,
                            onClick = { page = index },
                            icon = {
                                Icon(item.icon, contentDescription = item.title)
                            },
                            label = {
                                Text(item.title)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Neon,
                                selectedTextColor = Neon,
                                indicatorColor = Color(0xFF352044),
                                unselectedIconColor = Color.LightGray,
                                unselectedTextColor = Color.LightGray
                            )
                        )
                    }
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when (page) {
                    0 -> FeedScreen()
                    1 -> SimpleScreen("Поиск", "Ищи интересные видео")
                    2 -> SimpleScreen("Создать", "Загрузка видео скоро появится")
                    3 -> SimpleScreen("Профиль", "@gamenkik")
                    4 -> SettingsScreen()
                }
            }
        }
    }
}

@Composable
fun FeedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "GamenTok",
                color = Neon,
                fontSize = 29.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Избранное",
                tint = Color.White
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Подписки", color = Color.Gray)
            Text(
                "Для вас",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Panel, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.PlayCircle,
                    contentDescription = "Видео",
                    tint = Neon,
                    modifier = Modifier.size(75.dp)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    "GamenTok",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Твоя новая видеоплатформа",
                    color = Color.LightGray
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    "Здесь скоро будут видео",
                    color = Neon
                )
            }
        }
    }
}

@Composable
fun SimpleScreen(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            title,
            color = Neon,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        Text(
            description,
            color = Color.White
        )
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            "Настройки",
            color = Neon,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(20.dp))

        Text("GamenTok", color = Color.White)
        Text("Версия 1.0", color = Color.LightGray)
        Text("Автор: Gamenkik", color = Color.LightGray)
    }
}
