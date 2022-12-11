package com.urlshort.shortly.feature.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.urlshort.shortly.R
import com.urlshort.shortly.base.model.domain.ShortenData
import com.urlshort.shortly.base.test.TestTags
import com.urlshort.shortly.base.ui.theme.ActionButtonStyle
import com.urlshort.shortly.base.ui.theme.ActionInputStyle
import com.urlshort.shortly.base.ui.theme.Blue
import com.urlshort.shortly.base.ui.theme.EmptyDescriptionStyle
import com.urlshort.shortly.base.ui.theme.EmptySubtitleStyle
import com.urlshort.shortly.base.ui.theme.Grey1
import com.urlshort.shortly.base.ui.theme.HistoryBackground
import com.urlshort.shortly.base.ui.theme.LinkButtonStyle
import com.urlshort.shortly.base.ui.theme.LinkHistoryTitleStyle
import com.urlshort.shortly.base.ui.theme.LinkShortStyle
import com.urlshort.shortly.base.ui.theme.LinkTitleStyle
import com.urlshort.shortly.base.ui.theme.Purple
import com.urlshort.shortly.base.ui.theme.Red
import com.urlshort.shortly.base.ui.theme.White
import com.urlshort.shortly.feature.home.viewmodel.HomeDataEvent
import com.urlshort.shortly.feature.home.viewmodel.HomeViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@InternalCoroutinesApi
@Composable
fun HomeScreen() {
    HomeComponent()
}

@InternalCoroutinesApi
@Composable
fun HomeComponent(viewModel: HomeViewModel = hiltViewModel()) {

    viewModel.setStateForEvent(HomeDataEvent.FetchShortenedUrls)

    Column(modifier = Modifier.fillMaxWidth()) {
        if (viewModel.shortUrlList.isEmpty()) {
            EmptyHomeComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .testTag(TestTags.TEST_TAGS_EMPTY_COMPONENT)
            )

        } else {
            HomeListComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), viewModel, viewModel.shortUrlList
            )

        }
        HomeInputComponent(modifier = Modifier.fillMaxWidth(), viewModel)
    }

}

@OptIn(InternalCoroutinesApi::class)
@Composable
fun EmptyHomeComponent(modifier: Modifier) {

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            modifier = Modifier.size(width = 120.dp, height = 32.dp),
            painter = rememberImagePainter(data = R.drawable.ic_logo),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = rememberImagePainter(data = R.drawable.ic_illustration),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = stringResource(R.string.empty_home_subtitle), style = EmptySubtitleStyle)
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            modifier = Modifier.padding(horizontal = 75.dp),
            text = stringResource(R.string.empty_home_description),
            style = EmptyDescriptionStyle,
            textAlign = TextAlign.Center
        )
    }
}

@InternalCoroutinesApi
@Composable
fun HomeListComponent(
    modifier: Modifier,
    viewModel: HomeViewModel,
    shortenUrls: List<ShortenData>
) {
    Column(
        modifier = modifier
            .background(HistoryBackground)
            .testTag(TestTags.TEST_TAGS_HISTORY_LIST),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = stringResource(R.string.link_history_title), style = LinkHistoryTitleStyle)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(shortenUrls.size) {
                HomeListItemComponent(modifier = Modifier, viewModel, shortenUrls[it])
            }
        }
    }
}

@InternalCoroutinesApi
@Composable
fun HomeListItemComponent(modifier: Modifier, viewModel: HomeViewModel, shortenData: ShortenData) {

    val copied = viewModel.copiedUrl.isNotEmpty() && viewModel.copiedUrl == shortenData.shortLink
    val buttonText = if (copied) {
        stringResource(R.string.copied)
    } else {
        stringResource(R.string.copy)
    }.uppercase()
    val buttonBackground = if (copied) {
        Purple
    } else {
        Blue
    }
    Surface(
        modifier = modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        color = White,
        shape = RoundedCornerShape(size = 8.dp)
    ) {
        val clipboardManager: ClipboardManager = LocalClipboardManager.current

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 23.dp, start = 23.dp, end = 23.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = shortenData.originalLink,
                    maxLines = 1,
                    style = LinkTitleStyle,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier.size(width = 14.dp, height = 18.dp),
                    onClick = { viewModel.deleteUrl(shortenData.shortLink) }) {
                    Icon(
                        painter = rememberImagePainter(data = R.drawable.ic_delete),
                        contentDescription = ""
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Grey1)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 23.dp, end = 23.dp, bottom = 23.dp),
                text = shortenData.shortLink,
                maxLines = 1,
                color = Blue,
                style = LinkShortStyle
            )
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 23.dp, end = 23.dp, bottom = 23.dp), onClick = {
                    viewModel.copyUrl(shortenData.shortLink)
                    clipboardManager.setText(AnnotatedString((shortenData.shortLink)))
                }, colors = ButtonDefaults.buttonColors(backgroundColor = buttonBackground)
            ) {
                Text(text = buttonText, style = LinkButtonStyle)
            }
        }
    }

}

@InternalCoroutinesApi
@Composable
fun HomeInputComponent(modifier: Modifier, viewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple)
    ) {
        Image(
            modifier = Modifier
                .size(width = 237.dp, height = 128.dp)
                .align(Alignment.TopEnd),
            painter = rememberImagePainter(data = R.drawable.ic_home_action_shape),
            contentDescription = ""
        )

        Column(
            modifier = modifier
                .padding(horizontal = 48.dp, vertical = 46.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var text by remember { mutableStateOf("") }

            Box {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag(TestTags.TEST_TAGS_URL_INPUT),
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.home_input_hint),
                            style = ActionInputStyle,
                            textAlign = TextAlign.Center
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = White,
                        textColor = Grey1
                    ),
                    textStyle = ActionInputStyle.copy(textAlign = TextAlign.Center)
                )

                LaunchedEffect(viewModel.timeout) {
                    if (viewModel.timeout > 0) {
                        delay(1000)
                        viewModel.timeout -= 1
                    } else {
                        viewModel.inputError = ""
                    }
                }

                if (viewModel.inputError.isNotEmpty()) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(2.dp, Red)
                    ) {
                        Text(
                            modifier = Modifier.background(Blue),
                            text = viewModel.inputError,
                            style = ActionInputStyle,
                            color = Red,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag(TestTags.TEST_TAGS_URL_ACTION),
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
                onClick = {
                    viewModel.timeout = 3
                    viewModel.setStateForEvent(HomeDataEvent.ShortenUrl(text))
                }) {
                Text(
                    text = stringResource(R.string.home_action).uppercase(),
                    style = ActionButtonStyle
                )
            }
        }
    }
}