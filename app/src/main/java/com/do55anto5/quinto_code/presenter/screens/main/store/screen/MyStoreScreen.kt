package com.do55anto5.quinto_code.presenter.screens.main.store.screen


import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.enums.input.CreateStoreFieldType.*
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.components.image.ImageUI
import com.do55anto5.quinto_code.presenter.components.text_field.TextFieldUI
import com.do55anto5.quinto_code.presenter.screens.main.store.action.MyStoreAction
import com.do55anto5.quinto_code.presenter.screens.main.store.action.MyStoreAction.OnSave
import com.do55anto5.quinto_code.presenter.screens.main.store.action.MyStoreAction.OnValueChange
import com.do55anto5.quinto_code.presenter.screens.main.store.state.MyStoreState
import com.do55anto5.quinto_code.presenter.screens.main.store.viewmodel.MyStoreViewModel
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyStoreScreen() {

    val viewModel = koinViewModel<MyStoreViewModel>()
    val state by viewModel.state.collectAsState()
    var uriSaveable by rememberSaveable { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uriSaveable = uri
    }

    CreateMyStoreScreenContent(
        state = state,
        action = viewModel::submitAction,
        galleryLauncher = galleryLauncher
    )
}

@Composable
private fun CreateMyStoreScreenContent(
    state: MyStoreState,
    action: (MyStoreAction) -> Unit,
    galleryLauncher: ManagedActivityResultLauncher<String, Uri?>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(QuintoCodeTheme.colorScheme.backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.label_title_create_content_store_screen),
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 38.4.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight.Bold,
                color = QuintoCodeTheme.colorScheme.textColor,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 8.dp),
            content = {
                if (state.compressedImage?.first != null) {
                    ImageUI(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(60.dp))
                            .border(
                                width = 2.dp,
                                color = QuintoCodeTheme.colorScheme.defaultColor,
                                shape = RoundedCornerShape(110.dp)
                            )
                            .size(120.dp),
                        imageModel = state.compressedImage.first!!,
                        contentScale = ContentScale.Crop,
                        onClick = { galleryLauncher.launch("image/*") },
                        isLoading = state.isLoading
                    )
                } else {
                    ImageUI(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(60.dp))
                            .border(
                                width = 2.dp,
                                color = QuintoCodeTheme.colorScheme.defaultColor,
                                shape = RoundedCornerShape(110.dp)
                            )
                            .size(120.dp),
                        imageModel = state.logo ?: "",
                        contentScale = ContentScale.Crop,
                        onClick = { galleryLauncher.launch("image/*") },
                        isLoading = state.isLoading
                    )
                    Icon(
                        modifier = Modifier
                            .align(BottomEnd),
                        painter = painterResource(id = R.drawable.ic_camera_fill),
                        contentDescription = stringResource(R.string.icon_description_edit_label_profile_screen),
                        tint = QuintoCodeTheme.colorScheme.defaultColor
                    )
                }
            }
        )

        TextFieldUI(
            value = state.name,
            label = stringResource(R.string.text_field_label_name_create_content_store_screen),
            placeholder = state.name,
            mLeadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_pencil_fill),
                    contentDescription = null,
                    tint = QuintoCodeTheme.colorScheme.greyscale500Color
                )
            },
            enabled = state.isLoading.not(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onValueChange = {
                action(
                    OnValueChange(
                        type = NAME,
                        value = it
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldUI(
            value = state.description,
            label = stringResource(R.string.text_field_label_description_create_content_store_screen),
            placeholder = state.description,
            mLeadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_pencil_fill),
                    contentDescription = null,
                    tint = QuintoCodeTheme.colorScheme.greyscale500Color
                )
            },
            enabled = state.isLoading.not(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onValueChange = {
                action(
                    OnValueChange(
                        type = DESCRIPTION,
                        value = it
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldUI(
            value = state.address,
            label = stringResource(R.string.text_field_label_address_create_content_store_screen),
            placeholder = state.address,
            mLeadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_pencil_fill),
                    contentDescription = null,
                    tint = QuintoCodeTheme.colorScheme.greyscale500Color
                )
            },
            enabled = state.isLoading.not(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onValueChange = {
                action(
                    OnValueChange(
                        type = ADDRESS,
                        value = it
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldUI(
            value = state.city,
            label = stringResource(R.string.text_field_label_city_create_content_store_screen),
            placeholder = state.city,
            mLeadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_pencil_fill),
                    contentDescription = null,
                    tint = QuintoCodeTheme.colorScheme.greyscale500Color
                )
            },
            enabled = state.isLoading.not(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onValueChange = {
                action(
                    OnValueChange(
                        type = CITY,
                        value = it
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldUI(
            value = state.phoneNumber,
            label = stringResource(R.string.text_field_label_phone_number_create_content_store_screen),
            placeholder = state.phoneNumber,
            mLeadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_pencil_fill),
                    contentDescription = null,
                    tint = QuintoCodeTheme.colorScheme.greyscale500Color
                )
            },
            enabled = state.isLoading.not(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onValueChange = {
                action(
                    OnValueChange(
                        type = PHONE_NUMBER,
                        value = it
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        PrimaryButton(
            text = stringResource(R.string.text_button_edit_profile_screen),
            isLoading = state.isLoading,
            onClick = { action(OnSave) }
        )
    }
}

@Composable
private fun MyStoreScreenContent(
    state: MyStoreState,
    action: (MyStoreAction) -> Unit
) {

}

@PreviewLightDark
@Composable
private fun MyStoreScreenPreview() {
    MyStoreScreen()
}