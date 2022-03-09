package com.zdez.coder.feature_user_list.presentation.loading.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zdez.coder.R
import com.zdez.coder.feature_user_list.presentation.loading.LoadingViewModel

@Composable
fun ErrorScreen(viewModel: LoadingViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(Icons.Filled.Warning, contentDescription = stringResource(R.string.warning))
        Text(text = stringResource(R.string.discription_error))
        Text(text = stringResource(R.string.promise_to_fix))
        TextButton(
            onClick = { viewModel.loadingUsers() },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}