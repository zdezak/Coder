package com.zdez.coder.feature_user_list.presentation.users.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zdez.coder.R
import com.zdez.coder.feature_user_list.domain.model.User

@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Image(
            painterResource(id = R.drawable.ic_launcher_background),
            //painter = rememberGlidePainter(user.avatarUrl),
            contentDescription = user.firstName + " " + user.lastName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Color.Black, CircleShape)
        )
        Column {
            Row {
                Text(text = user.firstName)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = user.lastName)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = user.userTag)
            }
            Text(text = user.department)
        }
    }
}