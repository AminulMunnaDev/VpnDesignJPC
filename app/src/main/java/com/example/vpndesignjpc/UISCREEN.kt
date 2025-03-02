package com.example.vpndesignjpc

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VPNHomeScreen() {
    val selectedColor = Color(0xFFAD4E4E)
    val disSelectedColor = Color(0xFF2B6230)
    var isConnected by remember { mutableStateOf(false) }
    var selectedServer by remember { mutableStateOf("Select a server") }
    val servers = listOf("USA - New York", "Germany - Berlin", "Japan - Tokyo","Bangladesh - Dhaka", "Sydney - Australia", "India - Mumbai" )

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF1E1E2E), Color(0xFF9C9C9F))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "DNE VPN",
            fontSize = 32.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(100.dp))

        // Server Selection with Custom Colors
        var expanded by remember { mutableStateOf(false) }
        Box {
            Button(
                onClick = { expanded = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A3A4D)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.shadow(8.dp, RoundedCornerShape(12.dp))
            ) {
                Text(selectedServer, color = Color.White)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color(0xFF2B2B3A)) // Custom dropdown background
            ) {
                servers.forEach { server ->
                    DropdownMenuItem(
                        text = { Text(server, color = Color.White) },
                        onClick = {
                            selectedServer = server
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.White, // Text color
                            leadingIconColor = Color.LightGray,
                            trailingIconColor = Color.LightGray
                        ),
                        modifier = Modifier.background(Color(0xFF3A3A4D)) // Item background color
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // VPN Status Indicator with Animation
        val scale by animateFloatAsState(targetValue = if (isConnected) 1.2f else 1f)

        Box(
            modifier = Modifier
                .size((140 * scale).dp)
                .clip(CircleShape)
                .background(if (isConnected) disSelectedColor else selectedColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isConnected) "Connected" else "Disconnected",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Connect / Disconnect Button
        Button(
            onClick = { isConnected = !isConnected },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isConnected) selectedColor else disSelectedColor
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(10.dp, RoundedCornerShape(16.dp))
        ) {
            Text(
                text = if (isConnected) "Disconnect" else "Connect",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}
