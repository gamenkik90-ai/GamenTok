package com.gamenkik.gamentok import 
android.os.Bundle import 
androidx.activity.ComponentActivity import 
androidx.activity.compose.setContent import 
androidx.compose.foundation.background import 
androidx.compose.foundation.layout.* import 
androidx.compose.foundation.shape.RoundedCornerShape 
import androidx.compose.material.icons.Icons 
import 
androidx.compose.material.icons.filled.* 
import androidx.compose.material3.* import 
androidx.compose.runtime.* import 
androidx.compose.ui.Alignment import 
androidx.compose.ui.Modifier import 
androidx.compose.ui.graphics.Color import 
androidx.compose.ui.text.font.FontWeight 
import androidx.compose.ui.unit.dp import 
androidx.compose.ui.unit.sp private val Neon 
= Color(0xFFB026FF) private val Bg = 
Color(0xFF090611) private val Panel = 
Color(0xFF191125) class MainActivity : 
ComponentActivity() {
    override fun onCreate(savedInstanceState: 
    Bundle?) {
        super.onCreate(savedInstanceState) 
        setContent { GamenTokApp() }
    }
}
@Composable fun GamenTokApp() { var page by 
    remember { mutableIntStateOf(0) } 
    MaterialTheme(colorScheme = 
    darkColorScheme(primary = Neon)) {
        Scaffold( containerColor = Bg, 
            bottomBar = {
                NavigationBar(containerColor 
                = Panel) {
                    val items = listOf( 
                        "Лента" to 
                        Icons.Default.Home, 
                        "Поиск" to 
                        Icons.Default.Search, 
                        "Создать" to 
                        Icons.Default.Add, 
                        "Профиль" to 
                        Icons.Default.Person, 
                        "Настройки" to 
                        Icons.Default.Settings
                    ) items.forEachIndexed { 
                    index, item ->
                        NavigationBarItem( 
                            selected = page 
                            == index, onClick 
                            = { page = index 
                            },
                            icon = { 
                            Icon(item.second, 
                            null) }, label = 
                            { 
                            Text(item.first) 
                            },
                            colors = 
                            NavigationBarItemDefaults.colors(
                                selectedIconColor 
                                = Neon, 
                                selectedTextColor 
                                = Neon, 
                                indicatorColor 
                                = 
                                Color(0xFF352044)
                            ) )
                    }
                }
            }
        ) { padding -> Box( 
                Modifier.fillMaxSize().padding(padding)
            ) { when (page) { 0 -> 
                    FeedScreen() 1 -> 
                    SimpleScreen("Поиск", 
                    "Ищи интересные видео") 2 
                    -> 
                    SimpleScreen("Создать", 
                    "Загрузка видео скоро 
                    появится") 3 -> 
                    SimpleScreen("Профиль", 
                    "@gamenkik") 4 -> 
                    SettingsScreen()
                }
            }
        }
    }
}
@Composable fun FeedScreen() { Column( 
        Modifier.fillMaxSize().padding(16.dp)
    ) { Row( Modifier.fillMaxWidth(), 
            horizontalArrangement = 
            Arrangement.SpaceBetween, 
            verticalAlignment = 
            Alignment.CenterVertically
        ) { Text( "GamenTok", color = Neon, 
                fontSize = 29.sp, fontWeight 
                = FontWeight.ExtraBold
            ) Icon(Icons.Default.Favorite, 
            null, tint = Color.White)
        }
        Spacer(Modifier.height(20.dp)) 
        Row(horizontalArrangement = 
        Arrangement.spacedBy(20.dp)) {
            Text("Подписки", color = 
            Color.Gray) Text("Для вас", color 
            = Color.White,
                fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(16.dp)) Box( 
            Modifier.fillMaxWidth()
                .weight(1f) 
                .background(Panel, 
                RoundedCornerShape(20.dp)),
            contentAlignment = 
            Alignment.Center
        ) { Column(horizontalAlignment = 
            Alignment.CenterHorizontally) {
                Icon( 
                    Icons.Default.PlayCircle, 
                    null, tint = Neon, 
                    modifier = 
                    Modifier.size(75.dp)
                ) 
                Spacer(Modifier.height(12.dp)) 
                Text(
                    "GamenTok", color = 
                    Color.White, fontSize = 
                    25.sp, fontWeight = 
                    FontWeight.Bold
                ) Text("Твоя новая 
                видеоплатформа", color = 
                Color.LightGray) 
                Spacer(Modifier.height(12.dp)) 
                Text("Здесь скоро будут 
                видео", color = Neon)
            }
        }
    }
}
@Composable fun SimpleScreen(title: String, 
description: String) {
    Column( 
        Modifier.fillMaxSize().padding(24.dp), 
        verticalArrangement = 
        Arrangement.Center, 
        horizontalAlignment = 
        Alignment.CenterHorizontally
    ) { Text(title, color = Neon, fontSize = 
        28.sp,
            fontWeight = FontWeight.Bold) 
        Spacer(Modifier.height(12.dp))
        Text(description, color = Color.White)
