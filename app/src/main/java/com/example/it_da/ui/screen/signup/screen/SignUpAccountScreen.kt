package com.example.it_da.ui.screen.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.screen.signup.component.SignUpOutlinedTextField
import com.example.it_da.ui.screen.signup.component.SignUpPrimaryButton
import com.example.it_da.ui.screen.signup.component.SignUpTopBar
import com.example.it_da.ui.screen.signup.state.SignUpAccountUiState
import com.example.it_da.ui.theme.ITDATheme
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val SignUpSectionTitleWeight = FontWeight.Medium
private val SignUpDescriptionWeight = FontWeight.Normal

// Assembles the first sign-up step from focused form components.
@Composable
fun SignUpAccountScreen(
    uiState: SignUpAccountUiState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        SignUpTopBar(title = "회원 가입")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(37.dp))

            Text(
                text = "계정 만들기",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = SignUpSectionTitleWeight,
                    fontSize = 21.sp,
                    lineHeight = 21.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "서비스를 이용하기 위해 기본 정보를 입력해 주세요",
                color = ItdaSecondaryTextColor,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = SignUpDescriptionWeight,
                    fontSize = 13.sp,
                    lineHeight = 13.sp
                )
            )

            Spacer(modifier = Modifier.height(29.dp))

            SignUpOutlinedTextField(
                label = "아이디",
                value = uiState.id,
                onValueChange = onIdChange,
                placeholder = "6~15글자"
            )

            Spacer(modifier = Modifier.height(21.dp))

            SignUpOutlinedTextField(
                label = "비밀번호",
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = "8~20글자",
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(21.dp))

            SignUpOutlinedTextField(
                label = "비밀번호 확인",
                value = uiState.passwordConfirm,
                onValueChange = onPasswordConfirmChange,
                placeholder = "8~20글자",
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.weight(1f))

            SignUpPrimaryButton(
                enabled = uiState.isNextEnabled,
                onClick = onNextClick
            )

            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpAccountScreenPreview() {
    ITDATheme {
        SignUpAccountScreen(
            uiState = SignUpAccountUiState(),
            onIdChange = {},
            onPasswordChange = {},
            onPasswordConfirmChange = {},
            onNextClick = {}
        )
    }
}
