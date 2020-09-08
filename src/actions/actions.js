import * as con from "../components/constants.js";

export function changeLanguage(langFunction) {
  return (dispatch) => {
    dispatch({
      type: con.CHANGE_LANGUAGE,
      payload: {
        language: langFunction,
      },
    });
  };
}
