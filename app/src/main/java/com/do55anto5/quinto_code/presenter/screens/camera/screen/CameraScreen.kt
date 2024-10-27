@file:OptIn(ExperimentalMaterial3Api::class)

package com.do55anto5.quinto_code.presenter.screens.camera.screen


import android.app.Activity
import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.util.Permissions.Companion.CAMERAX_PERMISSIONS
import com.do55anto5.quinto_code.core.util.hasCameraPermission
import com.do55anto5.quinto_code.presenter.components.camera.CameraPreview
import com.do55anto5.quinto_code.presenter.components.bottom_sheet.PhotoBottomSheetUI
import com.do55anto5.quinto_code.presenter.screens.camera.action.CameraAction
import com.do55anto5.quinto_code.presenter.screens.camera.state.CameraState
import com.do55anto5.quinto_code.presenter.screens.camera.viewmodel.CameraViewModel
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<CameraViewModel>()
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }

    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { true }
    )

    if (!hasCameraPermission(context = context)) {
        ActivityCompat.requestPermissions(
            context as Activity, CAMERAX_PERMISSIONS, 0
        )
    }

    LaunchedEffect(state.isPhotoTaken) {
        if (state.isPhotoTaken) {
            showBottomSheet.value = true
        }
    }

    CameraContent(
        viewModel = viewModel,
        state = state,
        sheetState = sheetState,
        showBottomSheet = showBottomSheet,
        context = context,
        action = viewModel::submitAction,
        controller = controller
    )
}

@Composable
private fun CameraContent(
    viewModel: CameraViewModel,
    state: CameraState,
    sheetState: SheetState,
    showBottomSheet: MutableState<Boolean>,
    context: Context,
    action: (CameraAction) -> Unit,
    controller: LifecycleCameraController
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("secondary-loading.json"))

    Scaffold(
        containerColor = QuintoCodeTheme.colorScheme.backgroundColor,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CameraPreview(
                controller = controller,
                modifier = Modifier
                    .fillMaxSize()
            )

            IconButton(
                onClick = {
                    controller.cameraSelector =
                        if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                            CameraSelector.DEFAULT_FRONT_CAMERA
                        } else {
                            CameraSelector.DEFAULT_BACK_CAMERA
                        }
                },
                modifier = Modifier
                    .offset(x = 16.dp, y = 16.dp)
                    .padding(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Cameraswitch,
                    tint = QuintoCodeTheme.colorScheme.textColor,
                    contentDescription = stringResource(R.string.switch_camera_description_camera_screen),
                    modifier = Modifier.size(35.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = {
                        action(
                            CameraAction.OnTakePhoto(
                                context = context,
                                controller = controller,
                                onPhotoTaken = viewModel::onPhotoTaken
                            )
                        )
                    },
                    enabled = state.isLoading.not(),
                    colors = IconButtonDefaults.iconButtonColors(

                    ),
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .border(
                            width = 2.dp,
                            color = QuintoCodeTheme.colorScheme.greyscale200Color,
                            shape = RoundedCornerShape(110.dp)
                        )
                ) {
                    if (state.isLoading) {
                        LottieAnimation(
                            composition = composition,
                            modifier = Modifier
                                .size(70.dp),
                            iterations = LottieConstants.IterateForever,
                            maintainOriginalImageBounds = true,
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            tint = QuintoCodeTheme.colorScheme.greyscale500Color,
                            contentDescription = stringResource(R.string.take_photo_description_camera_screen),
                            modifier = Modifier.size(35.dp),
                        )
                    }
                }

                if (showBottomSheet.value) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet.value = false
                            action(CameraAction.OnCancel)
                        },
                        sheetState = sheetState,
                        containerColor = QuintoCodeTheme.colorScheme.backgroundColor
                    ) {
                        PhotoBottomSheetUI(
                            bitmap = state.bitmap,
                            modifier = Modifier
                                .fillMaxWidth(),
                            onDismiss = {
                                showBottomSheet.value = !showBottomSheet.value
                                action(CameraAction.OnCancel)
                            }
                        )
                    }
                }
            }
        }
    }
}





























































