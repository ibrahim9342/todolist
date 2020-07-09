import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Todo/Dashboard";
import Todos from "./components/Todo/Todos";
import Items from "./components/Todo/Items";
import AllItems from "./components/Todo/AllItems";
import AllTodos from "./components/Todo/AllTodos";
import PageNotFound from "./components/Layout/PageNotFound";
import Signin from "./components/User/Signin";
import Signup from "./components/User/Signup";
import NewTodo from "./components/Todo/NewTodo";
import NewItem from "./components/Todo/NewItem"
import Header from "./components/Layout/Header";
import Sidebar from "./components/Layout/Sidebar";
import Footer from "./components/Layout/Footer";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";
import SecuredRoute from "./security/SecureRoute";
import setJWTToken from "./security/setJWTToken";
import { logout, user } from "./actions/userActions";
import jwt_decode from "jwt-decode";

const jwtToken = localStorage.jwtToken;
if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/signin";
  } else {
    store.dispatch(user());
    // store.dispatch({
    //   type: SET_CURRENT_USER,
    //   payload: decoded_jwtToken
    // });
  }
}

class App extends Component {



  render() {

    return (
      <Provider store={store}>
        <Router>
          <div>
            {localStorage.jwtToken === undefined ?
              <div className="hold-transition login-page">
                <Switch>
                  <Route exact path="/" component={Signin} />
                  <Route exact path="/signin" component={Signin} />
                  <Route exact path="/signup" component={Signup} />
                  <Redirect to="/" />
                </Switch>
              </div>
              :
              <div>
                <Header />
                <Sidebar />
                <Switch>
                  <SecuredRoute exact path="/" component={Dashboard} />
                  <SecuredRoute exact path="/dashboard" component={Dashboard} />
                  <SecuredRoute exact path="/todos" component={Todos} />
                  <SecuredRoute exact path="/items" component={Items} />
                  <SecuredRoute exact path="/alltodos" component={AllTodos} />
                  <SecuredRoute exact path="/allitems" component={AllItems} />
                  <SecuredRoute exact path="/newtodo" component={NewTodo} />
                  <SecuredRoute exact path="/updateProject/:id" component={NewTodo} />
                  <SecuredRoute exact path="/newitem" component={NewItem} />
                  <SecuredRoute exact path="/updateitem/:id" component={NewItem} />
                  <Route path="/logout" />
                  <SecuredRoute exact component={PageNotFound} />
                  <Redirect to="/404" />
                </Switch>
                <Footer />
              </div>
            }
          </div>
        </Router >
      </Provider >
    );
  }
}
export default App;
