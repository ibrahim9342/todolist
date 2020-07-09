import axios from "axios";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import setJWTToken from "../security/setJWTToken";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async dispatch => {
  try {
    await axios.post("/api/auth/signup", newUser);
    history.push("/signin");
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const login = LoginRequest => async dispatch => {
  try {
    // post => Login Request
    const res = await axios.post("/api/auth/signin", LoginRequest);
    // extract token from res.data
    const { accessToken } = res.data;
    // store the token in the localStorage
    localStorage.setItem("jwtToken", accessToken);
    // set our token in header ***
    setJWTToken(accessToken);
    // decode token on React
    const decoded = jwt_decode(accessToken);
    // dispatch to our securityReducer
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err
    });
  }
};

export const logout = () => dispatch => {
  localStorage.removeItem("jwtToken");
  setJWTToken(false);
  dispatch({
    type: SET_CURRENT_USER,
    payload: {}
  });
};

export const user = () => async dispatch => {
  try {
    const res = await axios.get("/api/auth/user");
    const status = res.status;
    if (status === 401) {
      localStorage.removeItem("jwtToken");
      setJWTToken(false);
      dispatch({
        type: SET_CURRENT_USER,
        payload: {}
      });
    } else {
      dispatch({
        type: SET_CURRENT_USER,
        payload: res.data
      });
    }
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err
    });
  }
};
