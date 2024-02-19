package com.oliversolutions.dev.calcuonline.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.oliversolutions.dev.calcuonline.R
import com.oliversolutions.dev.calcuonline.presentation.viewmodels.CalculatorViewModel
import com.oliversolutions.dev.calcuonline.utils.orFalse

@Composable
fun SecondaryAppBar(calculatorTitle: String, viewModel: CalculatorViewModel) {

    val selectedCalculator by viewModel.selectedCalculator.observeAsState()

    val isFavoriteIcon = if (selectedCalculator?.isFavorite.orFalse()) {
        R.drawable.loveheart
    } else {
        R.drawable.emptyheart
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(PaddingValues(20.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = calculatorTitle,
                modifier = Modifier.weight(0.9f)
            )
            ClickableImage(
                painter = painterResource(id = isFavoriteIcon),
                onClick = {
                    selectedCalculator?.let {
                        viewModel.saveCalculator(it.copy(isFavorite = !it.isFavorite))
                    }
                }
            )
        }
    }
}