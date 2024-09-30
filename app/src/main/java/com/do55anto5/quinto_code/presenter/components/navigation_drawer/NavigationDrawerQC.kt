package com.do55anto5.quinto_code.presenter.components.navigation_drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                drawerContainerColor = QuintoCodeTheme.colorScheme.backgroundColor,
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