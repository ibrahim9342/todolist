import axios from "axios";
import { GET_ERRORS, GET_TODOS, GET_TODO, DELETE_TODO } from "./types";


export const createProject = (project, history) => async dispatch => {
  try {
    const res = await axios.post("/api/todo/add", project);
    history.push("/todos");
    dispatch({
      type: GET_ERRORS,
      payload: res.data
    });

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


export const getUserTodos = (page,sizePerPage) => async dispatch => {
  const res = await axios.get("/api/todo/list",{
    params:{
      page: page,
      sizePerPage: sizePerPage
    }
  });
  dispatch({
    type: GET_TODOS,
    payload: res.data
  });
};

export const getTodo = (id, history) => async dispatch => {
  try {
    const res = await axios.get(`/api/todo/gettodo/${id}`);
    dispatch({
      type: GET_TODO,
      payload: res.data
    });
  } catch (error) {
    history.push("/todos");
  }
};

export const deleteTodo = id => async dispatch => {
  if (
    window.confirm(
      "Are you sure? This will delete the project and all the data related to it"
    )
  ) {
    await axios.delete(`/api/todo/delete/${id}`);

    const res = await axios.get("/api/todo/list",{
      params:{
        page: 0,
        sizePerPage: 10
      }
    });
    dispatch({
      type: DELETE_TODO,
      payload: res.data
      //payload: id
    });
  }
};
