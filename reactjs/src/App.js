import Login from "./Pages/Login/Login";
import {BrowserRouter as Router, Route, Link} from "react-router-dom"
import {Routes} from "react-router"

function App() {
    return (
        <Router>
            {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
            <Routes>
                <Route path = "/login" component = {<Login/>}/>
            </Routes>
        </Router>
    );
}

export default App;
