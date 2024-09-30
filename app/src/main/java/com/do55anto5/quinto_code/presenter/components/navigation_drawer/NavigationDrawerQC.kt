package com.do55anto5.quinto_code.presenter.components.navigation_drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.navigation.drawer.DrawerItem
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme

@Composable
fun NavigationDrawerQC(
    drawerState: DrawerState,
    items: List<DrawerItem>,
    drawerIndex: Int,
    onClick: (Int) -> Unit
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        content = {},
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = QuintoCodeTheme.colorScheme.defaultColor,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user_line),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp)
                            .border(
                                1.dp,
                                QuintoCodeTheme.colorScheme.borderColor,
                                CircleShape
                            ),
                        contentScale = ContentScale.Crop,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "MockName",
                            style = TextStyle(
                                color = QuintoCodeTheme.colorScheme.textColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = stringResource(R.string.label_show_profile_navigation_drawer_header),
                            style = TextStyle(
                                color = QuintoCodeTheme.colorScheme.textColor,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(QuintoCodeTheme.colorScheme.backgroundColor)
                ) {
                    Spacer(modifier = Modifier.size(16.dp))
                    items.forEachIndexed { index, drawerItem ->
                        androidx.compose.material3.NavigationDrawerItem(
                            label = {
                                Text(
                                    text = stringResource(id = drawerItem.title),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (index == drawerIndex) {
                                            QuintoCodeTheme.colorScheme.drawerItemSelectedColor
                                        } else {
                                            QuintoCodeTheme.colorScheme.drawerItemUnselectedColor
                                        }
                                    )
                                )
                            },
                            selected = index == drawerIndex,
                            onClick = { onClick(index) },
                            modifier = Modifier.padding(
                                NavigationDrawerItemDefaults.ItemPadding
                            ),
                            icon = {
                                Icon(
                                    painter = if (index == drawerIndex) {
                                        painterResource(id = drawerItem.selectedIcon)
                                    } else {
                                        painterResource(id = drawerItem.unselectedIcon)
                                    },
                                    tint = if (index == drawerIndex) {
                                        QuintoCodeTheme.colorScheme.drawerItemSelectedColor
                                    } else {
                                        QuintoCodeTheme.colorScheme.drawerItemUnselectedColor
                                    },
                                    contentDescription = null
                                )
                            },
                            badge = {
                                if (drawerItem.badge > 0) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .background(
                                                QuintoCodeTheme.colorScheme.drawerBadgeColor,
                                                RoundedCornerShape(4.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = drawerItem.badge.toString(),
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                color = QuintoCodeTheme.colorScheme.textColor
                                            )
                                        )
                                    }
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = QuintoCodeTheme.colorScheme.defaultColor,
                                unselectedContainerColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun Preview() {
    QuintoCodeTheme {
        val drawerState = rememberDrawerState(DrawerValue.Open)
        var drawerIndex by remember { mutableIntStateOf(0) }
        NavigationDrawerQC(
            drawerState = drawerState,
            items = DrawerItem.items,
            drawerIndex = drawerIndex,
            onClick = {
                drawerIndex = it
            }
        )
    }
}