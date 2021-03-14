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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.widget.BloomButton
import com.example.androiddevchallenge.ui.widget.BloomTextField

@Composable
fun LogInScreen(loginSucceed: () -> Unit) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequest = remember { FocusRequester() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.login_title),
                Modifier.paddingFromBaseline(184.dp),
                style = MaterialTheme.typography.h1
            )
            Email(
                modifier = Modifier.padding(top = 16.dp),
                email = email,
                onEmailChanged = { email = it },
                onNext = { passwordFocusRequest.requestFocus() }
            )
            Password(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .focusRequester(passwordFocusRequest),
                password = password,
                onPasswordChanged = { password = it },
                onDone = { focusManager.clearFocus() }
            )
            Text(
                stringResource(R.string.login_license_text),
                Modifier.paddingFromBaseline(top = 32.dp, bottom = 16.dp),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center
            )
            BloomButton(onClick = loginSucceed) {
                Text(text = stringResource(R.string.login_button))
            }
        }
    }
}

@Composable
private fun Email(
    modifier: Modifier,
    email: String,
    onEmailChanged: (String) -> Unit,
    onNext: () -> Unit
) {
    BloomTextField(
        modifier = modifier,
        value = email,
        onValueChange = onEmailChanged,
        label = { Text(stringResource(R.string.login_email_label)) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = { onNext() })
    )
}

@Composable
private fun Password(
    modifier: Modifier,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onDone: () -> Unit
) {
    BloomTextField(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChanged,
        label = { Text(stringResource(R.string.login_password_label)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(onDone = { onDone() })
    )
}
