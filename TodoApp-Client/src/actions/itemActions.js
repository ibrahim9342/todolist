import axios from "axios";
import { GET_ERRORS, GET_ITEMS, GET_ITEM, DELETE_ITEM, GET_ITEMS_AllTODO } from "./types";

export const getUserItems = (page, sizePerPage) => async dispatch => {
    const res = await axios.get("/api/item/list", {
        params: {
            page: page,
            sizePerPage: sizePerPage
        }
    });
    dispatch({
        type: GET_ITEMS,
        payload: res.data
    });
};

export const getAllTodo = () => async dispatch => {
  const res = await axios.get("/api/todo/alllist");
  dispatch({
      type: GET_ITEMS_AllTODO,
      payload: res.data
  });
};

export const getItem = (id, history) => async dispatch => {
    try {
      const res = await axios.get(`/api/item/getitem/${id}`);
      dispatch({
        type: GET_ITEM,
        payload: res.data
      });
    } catch (error) {
      history.push("/items");
    }
  };

  export const createItem = (todoId,item, history) => async dispatch => {
    try {
      const res = await axios.post(`/api/item/add/${todoId}`, item);
      history.push("/items");
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

export const deleteItem = id => async dispatch => {
    if (
        window.confirm(
            "Are you sure? This will delete the project and all the data related to it"
        )
    ) {
        await axios.delete(`/api/item/delete/${id}`);

        const res = await axios.get("/api/item/list", {
            params: {
                page: 0,
                sizePerPage: 10
            }
        });
        dispatch({
            type: DELETE_ITEM,
            payload: res.data
            //payload: id
        });
    }
};