package com.do55anto5.quinto_code.core.navigation.drawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.do55anto5.quinto_code.R

sealed class DrawerItem(
    val type: DrawerItemType,
    val badge: Int,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {

    data object Home : DrawerItem(
        type = DrawerItemType.HOME,
        badge = 0,
        title = R.string.label_home_navigation_drawer_item,
        selectedIcon = R.drawable.ic_home_fill,
        unselectedIcon = R.drawable.ic_home_line
    )

    data object Search : DrawerItem(
        type = DrawerItemType.SEARCH,
        badge = 0,
        title = R.string.label_search_navigation_drawer_item,
        selectedIcon = R.drawable.ic_search_fill,
        unselectedIcon = R.drawable.ic_search_line
    )

    data object Bag : DrawerItem(
        type = DrawerItemType.BAG,
        badge = 0,
        title = R.string.label_search_navigation_drawer_item,
        selectedIcon = R.drawable.ic_bag_fill,
        unselectedIcon = R.drawable.ic_bag_line
    )

    data object Hub : DrawerItem(
        type = DrawerItemType.HUB,
        badge = 0,
        title = R.string.label_hub_navigation_drawer_item,
        selectedIcon = R.drawable.ic_hub_fill,
        unselectedIcon = R.drawable.ic_hub_line
    )

    data object Favorite : DrawerItem(
        type = DrawerItemType.FAVORITE,
        badge = 0,
        title = R.string.label_favorite_navigation_drawer_item,
        selectedIcon = R.drawable.ic_favorite_fill,
        unselectedIcon = R.drawable.ic_favorite_line
    )

    data object Notification : DrawerItem(
        type = DrawerItemType.NOTIFICATION,
        badge = 0,
        title = R.string.label_favorite_navigation_drawer_item,
        selectedIcon = R.drawable.ic_notification_fill,
        unselectedIcon = R.drawable.ic_notification_line
    )

    enum class DrawerItemType {
        HOME,
        SEARCH,
        BAG,
        HUB,
        FAVORITE,
        NOTIFICATION
    }

    companion object {
        val items = listOf(
            Home,
            Search,
            Bag,
            Hub,
            Favorite,
            Notification
        )
    }

}