package com.itsvks.layouteditor.utils;

import androidx.appcompat.app.AlertDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.itsvks.layouteditor.adapters.models.DrawableFile;
import com.itsvks.layouteditor.R;
import java.util.List;
import java.util.regex.Pattern;

/** This class is used to check the name error conditions. */
public class NameErrorChecker {

  /**
   * This method checks if the name provided by the user is valid.
   *
   * @param name          the name provided by the user
   * @param inputLayout   object of TextInputLayout
   * @param dialog        object of AlertDialog
   * @param drawableList  list of DrawableFile objects
   */
  public static void checkForDrawable(
      String name,
      TextInputLayout inputLayout,
      AlertDialog dialog,
      List<DrawableFile> drawableList) {
    // Check if name is not empty
    if (!name.isEmpty()) {
      // First character should not be a number
      if (Character.isDigit(name.charAt(0))) {
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(dialog.getContext().getString(R.string.msg_first_letter_not_number));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        return;
      }
      // Name should not have space
      if (name.contains(" ")) {
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(dialog.getContext().getString(R.string.msg_space_not_allowed));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        return;
      }
      // Name should contain only letters and numbers
      if (!Pattern.matches("[a-z][a-z0-9_]*", name)) {
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(dialog.getContext().getString(R.string.msg_only_letters_and_numbers));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        return;
      }
    } else {
      // Name cannot be empty
      inputLayout.setErrorEnabled(true);
      inputLayout.setError(dialog.getContext().getString(R.string.msg_cannnot_empty));
      dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
      return;
    }

    // Check if the name already exists in the drawableList
    for (DrawableFile item : drawableList) {
      if (item.name.substring(0, item.name.lastIndexOf(".")).equals(name)) {
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(dialog.getContext().getString(R.string.msg_current_name_unavailable));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        return;
      }
    }

    // Name is valid
    inputLayout.setErrorEnabled(false);
    inputLayout.setError("");
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
  }
}
