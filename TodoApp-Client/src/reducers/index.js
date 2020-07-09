import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import todoReducer from "./todoReducer";
import itemReducer from "./itemReducer";
import securityReducer from "./userReducer";


export default combineReducers({
  errors: errorReducer,
  todo: todoReducer,
  item: itemReducer,
  security: securityReducer
});
