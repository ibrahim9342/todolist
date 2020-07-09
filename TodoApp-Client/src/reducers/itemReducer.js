import { GET_ITEMS, GET_ITEM, DELETE_ITEM, GET_ITEMS_AllTODO } from "../actions/types";

const initialState = {
  items: [],
  item: {},
  todos: []
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_ITEMS:
      return {
        ...state,
        items: action.payload
      };

    case GET_ITEM:
      return {
        ...state,
        item: action.payload
      };

    case GET_ITEMS_AllTODO:
      return {
        ...state,
        todos: action.payload
      };

    case DELETE_ITEM:
      return {
        ...state,
        items: action.payload
      };
    default:
      return state;
  }
}
