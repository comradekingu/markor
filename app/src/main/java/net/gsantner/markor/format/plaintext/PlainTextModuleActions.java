/*#######################################################
 *
 *   Maintained by Gregor Santner, 2018-
 *   https://gsantner.net/
 *
 *   License: Apache 2.0 / Commercial
 *  https://github.com/gsantner/opoc/#licensing
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
#########################################################*/
package net.gsantner.markor.format.plaintext;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import net.gsantner.markor.model.Document;
import net.gsantner.markor.ui.hleditor.TextModuleActions;
import net.gsantner.markor.util.AppSettings;

public class PlainTextModuleActions extends TextModuleActions {

    public PlainTextModuleActions(Activity activity, Document document) {
        super(activity, document);
    }


    @Override
    public void appendTextModuleActionsToBar(ViewGroup barLayout) {
        if (AppSettings.get().isEditor_ShowTextmoduleBar() && barLayout.getChildCount() == 0) {
            setBarVisible(barLayout, true);

            // Regular actions
            for (int[] actions : ACTIONS_ICONS) {
                appendTextModuleActionToBar(barLayout, actions[0], new KeyboardRegularActionListener(ACTIONS[actions[1]]), null);
            }
        } else if (!AppSettings.get().isEditor_ShowTextmoduleBar()) {
            setBarVisible(barLayout, false);
        }
    }

    //
    //
    //

    private static final int[][] ACTIONS_ICONS = {
            {CommonTextModuleActions.ACTION_OPEN_LINK_BROWSER__ICON, 0},
            {CommonTextModuleActions.ACTION_DELETE_LINES_ICON, 1},
            {CommonTextModuleActions.ACTION_SPECIAL_KEY__ICON, 2},
    };
    private static final String[] ACTIONS = {
            CommonTextModuleActions.ACTION_OPEN_LINK_BROWSER,
            CommonTextModuleActions.ACTION_DELETE_LINES,
            CommonTextModuleActions.ACTION_SPECIAL_KEY,
    };

    private class KeyboardRegularActionListener implements View.OnClickListener {
        String _action;

        KeyboardRegularActionListener(String action) {
            _action = action;
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public void onClick(View view) {
            CommonTextModuleActions commonTextModuleActions = new CommonTextModuleActions(_activity, _document, _hlEditor);
            commonTextModuleActions.runAction(_action);
        }
    }
}
