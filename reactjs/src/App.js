import Login from "./Pages/Login/Login";
import {BrowserRouter as Router, Route, Link} from "react-router-dom"
import {Routes} from "react-router"
import LandingPage from "./Pages/Landing Page/landing page";
import Wrapper from "./Pages/HomeWrapper"
import Home from "./Pages/Home/Home"

function App() {
    return (
        <Router>
            <Routes>
                <Route path = "/land" element = {<LandingPage/>}/>
                <Route path = "/home" element = {<Home/>}/>
                <Route path = "/" element = {<Wrapper/>}/>
            </Routes>

        </Router>
    );
}

export default App;
