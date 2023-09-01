package org.futo.inputmethod.latin.uix.actions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.Action
import org.futo.inputmethod.latin.uix.ActionWindow
import org.futo.inputmethod.latin.uix.KeyboardManagerForAction
import org.futo.inputmethod.latin.uix.theme.ThemeOptionKeys
import org.futo.inputmethod.latin.uix.theme.ThemeOptions

val ThemeAction = Action(
    icon = R.drawable.eye,
    name = R.string.theme_switcher_action_title,
    simplePressImpl = null,
    windowImpl = { manager, _ ->
        object : ActionWindow {
            @Composable
            override fun windowName(): String {
                return stringResource(R.string.theme_switcher_action_title)
            }

            @Composable
            override fun WindowContents() {
                val context = LocalContext.current
                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .fillMaxWidth()
                )
                {
                    items(ThemeOptionKeys.count()) {
                        val key = ThemeOptionKeys[it]
                        val themeOption = ThemeOptions[key]
                        if (themeOption != null && themeOption.available(context)) {
                            Button(onClick = {
                                manager.updateTheme(
                                    themeOption
                                )
                            }) {
                                Text(stringResource(themeOption.name))
                            }
                        }
                    }
                }
            }

            override fun close() {

            }
        }
    }
)