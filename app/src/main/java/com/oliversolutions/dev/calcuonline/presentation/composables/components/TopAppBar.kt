package com.oliversolutions.dev.calcuonline.presentation.composables.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.oliversolutions.dev.calcuonline.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.deep_ocean_blue))
    )
}
