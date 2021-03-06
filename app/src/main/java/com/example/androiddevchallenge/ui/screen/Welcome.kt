/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.widget.BloomButton

@Composable
fun WelcomeScreen(welcomeCompleted: () -> Unit) {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
        ConstraintLayout {
            val (
                backgroundImage,
                illosImage, logo,
                title,
                registerButton,
                loginButton
            ) = createRefs()
            Image(
                modifier = Modifier
                    .constrainAs(backgroundImage) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxSize(),
                painter = painterResource(R.drawable.ic_welcome_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier
                    .constrainAs(illosImage) {
                        start.linkTo(parent.start, margin = 88.dp)
                        top.linkTo(parent.top, margin = 72.dp)
                    },
                painter = painterResource(R.drawable.ic_welcome_illos),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier
                    .constrainAs(logo) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(illosImage.bottom, margin = 48.dp)
                    },
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(logo.bottom)
                    }
                    .paddingFromBaseline(32.dp),
                text = stringResource(R.string.welcome_title),
                style = MaterialTheme.typography.subtitle1
            )
            BloomButton(
                onClick = welcomeCompleted,
                modifier = Modifier
                    .constrainAs(registerButton) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(title.bottom, margin = 40.dp)
                    }
            ) { Text(stringResource(R.string.welcome_create_account_button)) }
            TextButton(
                onClick = welcomeCompleted,
                modifier = Modifier
                    .constrainAs(loginButton) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(registerButton.bottom, margin = 16.dp)
                    }
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.secondary),
            ) { Text(stringResource(R.string.welcome_login_button)) }
        }
    }
}
