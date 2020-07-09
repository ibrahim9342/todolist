import { GET_TODOS, GET_TODO, DELETE_TODO } from "../actions/types";

const initialState = {
  todos: [],
  todo: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_TODOS:
      return {
        ...state,
        todos: action.payload
      };

    case GET_TODO:
      return {
        ...state,
        todo: action.payload
      };

    case DELETE_TODO:
      return {
        ...state,
        todos: action.payload
        // todos: state.todos.filter(
        //   todo => todo.id !== action.payload
        // )
      };
    default:
      return state;
  }
}
