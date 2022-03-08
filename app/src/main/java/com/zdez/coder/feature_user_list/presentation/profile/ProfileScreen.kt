package com.zdez.coder.feature_user_list.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zdez.coder.R


@Composable
fun ProfileScreen(
    id: String,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    Scaffold {
        Column {
            //return back
            Icon(Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.arrow_back_icon))
            //Image
            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                //painter = rememberGlidePainter(user.avatarUrl),
                contentDescription = viewModel.user.firstName + " " + viewModel.user.lastName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
            )
            // Name and lastname and tag
            Row {
                Text(text = viewModel.user.firstName + " " + viewModel.user.lastName)
                Text(text = viewModel.user.userTag)
            }
            //department
            Text(text = viewModel.user.department)
            //icon Star next Birthday and old age
            Row {
                Icon(Icons.Filled.Star, contentDescription = stringResource(R.string.icon_star))
                Text(text = viewModel.user.birthday)
                Text(text = "24 Old")
            }
            //phone number
            Row {
                Icon(Icons.Filled.Phone, contentDescription = stringResource(R.string.Icon_phone))
                Text(text = viewModel.user.phone)
            }
        }
    }
}